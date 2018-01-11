/*
 * Copyright 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.web.bindery.requestfactory.shared.impl;

import static com.google.web.bindery.requestfactory.shared.impl.BaseProxyCategory.stableId;
import static com.google.web.bindery.requestfactory.shared.impl.Constants.REQUEST_CONTEXT_STATE;
import static com.google.web.bindery.requestfactory.shared.impl.Constants.STABLE_ID;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.google.web.bindery.autobean.shared.AutoBeanVisitor;
import com.google.web.bindery.autobean.shared.Splittable;
import com.google.web.bindery.autobean.shared.ValueCodex;
import com.google.web.bindery.autobean.shared.impl.AbstractAutoBean;
import com.google.web.bindery.autobean.shared.impl.EnumMap;
import com.google.web.bindery.autobean.shared.impl.StringQuoter;
import com.google.web.bindery.event.shared.UmbrellaException;
import com.google.web.bindery.requestfactory.shared.BaseProxy;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.EntityProxyChange;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.FanoutReceiver;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestTransport.TransportReceiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.google.web.bindery.requestfactory.shared.WriteOperation;
import com.google.web.bindery.requestfactory.shared.impl.posers.DatePoser;
import com.google.web.bindery.requestfactory.shared.messages.IdMessage;
import com.google.web.bindery.requestfactory.shared.messages.IdMessage.Strength;
import com.google.web.bindery.requestfactory.shared.messages.InvocationMessage;
import com.google.web.bindery.requestfactory.shared.messages.JsonRpcRequest;
import com.google.web.bindery.requestfactory.shared.messages.MessageFactory;
import com.google.web.bindery.requestfactory.shared.messages.OperationMessage;
import com.google.web.bindery.requestfactory.shared.messages.RequestMessage;
import com.google.web.bindery.requestfactory.shared.messages.ResponseMessage;
import com.google.web.bindery.requestfactory.shared.messages.ServerFailureMessage;
import com.google.web.bindery.requestfactory.shared.messages.ViolationMessage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.metadata.ConstraintDescriptor;

/**
 * Base implementations for RequestContext services.
 */
public abstract class AbstractRequestContext implements RequestContext, EntityCodex.EntitySource {
  /**
   * Allows the payload dialect to be injected into the AbstractRequestContext without the caller
   * needing to be concerned with how the implementation object is instantiated.
   */
  public enum Dialect {
    STANDARD {
      @Override
      DialectImpl create(final AbstractRequestContext context) {
        return context.new StandardPayloadDialect();
      }
    },
    JSON_RPC {
      @Override
      DialectImpl create(final AbstractRequestContext context) {
        return context.new JsonRpcPayloadDialect();
      }
    };
    abstract DialectImpl create(AbstractRequestContext context);
  }

  /**
   * Encapsulates all state contained by the AbstractRequestContext.
   */
  protected static class State {
    /**
     * Supports the case where chained contexts are used and a response comes back from the server
     * with a proxy type not reachable from the canonical context.
     */
    public Set<AbstractRequestContext> appendedContexts;
    public final AbstractRequestContext canonical;
    public final DialectImpl dialect;
    public FanoutReceiver<Void> fanout;
    /**
     * When {@code true} the {@link AbstractRequestContext#fire()} method will be a no-op.
     */
    public boolean fireDisabled;
    public final List<AbstractRequest<?, ?>> invocations = new ArrayList<>();

    public boolean locked;
    /**
     * See http://code.google.com/p/google-web-toolkit/issues/detail?id=5952.
     */
    public boolean diffing;
    /**
     * A map of all EntityProxies that the RequestContext has interacted with. Objects are placed
     * into this map by being returned from {@link #create}, passed into {@link #edit}, or used as
     * an invocation argument.
     */
    public final Map<SimpleProxyId<?>, AutoBean<? extends BaseProxy>> editedProxies =
        new LinkedHashMap<>();
    /**
     * A map that contains the canonical instance of an entity to return in the return graph, since
     * this is built from scratch.
     */
    public final Map<SimpleProxyId<?>, AutoBean<?>> returnedProxies = new HashMap<>();

    public final AbstractRequestFactory requestFactory;

    /**
     * A map that allows us to handle the case where the server has sent back an unpersisted entity.
     * Because we assume that the server is stateless, the client will need to swap out the
     * request-local ids with a regular client-allocated id.
     */
    public final Map<Integer, SimpleProxyId<?>> syntheticIds = new HashMap<>();

    public State(final AbstractRequestFactory requestFactory, final DialectImpl dialect,
        final AbstractRequestContext canonical) {
      this.requestFactory = requestFactory;
      this.canonical = canonical;
      this.dialect = dialect;
    }

    public void addContext(final AbstractRequestContext ctx) {
      if (this.appendedContexts == null) {
        this.appendedContexts = Collections.singleton(ctx);
      } else {
        if (this.appendedContexts.size() == 1) {
          this.appendedContexts = new LinkedHashSet<>(this.appendedContexts);
        }
        this.appendedContexts.add(ctx);
      }
    }

    public AbstractRequestContext getCanonicalContext() {
      return this.canonical;
    }

    public boolean isClean() {
      return this.editedProxies.isEmpty() && this.invocations.isEmpty() && !this.locked
          && this.returnedProxies.isEmpty() && this.syntheticIds.isEmpty();
    }

    public boolean isCompatible(final State state) {
      // Object comparison intentional
      return this.requestFactory == state.requestFactory
          && this.dialect.getClass().equals(state.dialect.getClass());
    }
  }

  interface DialectImpl {

    void addInvocation(AbstractRequest<?, ?> request);

    String makePayload();

    void processPayload(Receiver<Void> receiver, String payload);
  }

  class JsonRpcPayloadDialect implements DialectImpl {
    /**
     * Called by generated subclasses to enqueue a method invocation.
     */
    @Override
    public void addInvocation(final AbstractRequest<?, ?> request) {
      /*
       * TODO(bobv): Support for multiple invocations per request needs to be ironed out. Once this
       * is done, addInvocation() can be removed from the DialectImpl interface and restored to to
       * AbstractRequestContext.
       */
      if (!AbstractRequestContext.this.state.invocations.isEmpty()) {
        throw new RuntimeException("Only one invocation per request, pending backend support");
      }
      AbstractRequestContext.this.state.invocations.add(request);
      for (final Object arg : request.getRequestData().getOrderedParameters()) {
        AbstractRequestContext.this.retainArg(arg);
      }
    }

    @Override
    public String makePayload() {
      final RequestData data =
          AbstractRequestContext.this.state.invocations.get(0).getRequestData();

      final AutoBean<JsonRpcRequest> bean = MessageFactoryHolder.FACTORY.jsonRpcRequest();
      final JsonRpcRequest request = bean.as();

      request.setVersion("2.0");
      request.setApiVersion(data.getApiVersion());
      request.setId(payloadId++);

      final Map<String, Splittable> params = new HashMap<>();
      for (final Map.Entry<String, Object> entry : data.getNamedParameters().entrySet()) {
        final Object obj = entry.getValue();
        final Splittable value = this.encode(obj);
        params.put(entry.getKey(), value);
      }
      if (data.getRequestResource() != null) {
        params.put("resource", this.encode(data.getRequestResource()));
      }
      request.setParams(params);
      request.setMethod(data.getOperation());

      return AutoBeanCodex.encode(bean).getPayload();
    }

    @Override
    public void processPayload(final Receiver<Void> receiver, final String payload) {
      final Splittable raw = StringQuoter.split(payload);

      @SuppressWarnings("unchecked")
      final Receiver<Object> callback =
          (Receiver<Object>) AbstractRequestContext.this.state.invocations.get(0).getReceiver();

      if (!raw.isNull("error")) {
        final Splittable error = raw.get("error");
        final ServerFailure failure = new ServerFailure(error.get("message").asString(),
            error.get("code").asString(), payload, true);
        AbstractRequestContext.this.fail(receiver, failure);
        return;
      }

      final Splittable result = raw.get("result");
      @SuppressWarnings("unchecked")
      final Class<BaseProxy> target =
          (Class<BaseProxy>) AbstractRequestContext.this.state.invocations.get(0).getRequestData()
              .getReturnType();

      final SimpleProxyId<BaseProxy> id =
          AbstractRequestContext.this.getRequestFactory().allocateId(target);
      final AutoBean<BaseProxy> bean = AbstractRequestContext.this.createProxy(target, id, true);
      // XXX expose this as a proper API
      ((AbstractAutoBean<?>) bean).setData(result);
      // AutoBeanCodex.decodeInto(result, bean);

      if (callback != null) {
        callback.onSuccess(bean.as());
      }
      if (receiver != null) {
        receiver.onSuccess(null);
      }
    }

    Splittable encode(final Object obj) {
      if (obj == null) {
        return Splittable.NULL;
      } else if (obj instanceof Collection) {
        return this.collectionEncode((Collection<?>) obj);
      }
      return this.nonCollectionEncode(obj);
    }

    private Splittable collectionEncode(final Collection<?> collection) {
      final StringBuilder sb = new StringBuilder("[");
      final Iterator<?> it = collection.iterator();
      if (it.hasNext()) {
        // TODO: Allow for the encoding of nested collections. See issue 5974.
        sb.append(this.nonCollectionEncode(it.next()).getPayload());
        while (it.hasNext()) {
          sb.append(",");
          // TODO: Allow for the encoding of nested collections. See issue 5974.
          sb.append(this.nonCollectionEncode(it.next()).getPayload());
        }
      }
      sb.append("]");

      return StringQuoter.split(sb.toString());
    }

    private Splittable nonCollectionEncode(final Object obj) {
      if (obj instanceof Collection) {
        // TODO: Allow for the encoding of nested collections. See issue 5974.
        // Once we do this, this can turn into an assert.
        throw new RuntimeException(
            "Unable to encode request as JSON payload; Request methods must have parameters of "
                + "the form List<T> or Set<T>, where T is a scalar (non-collection) type.");
      }
      Splittable value;
      if (obj instanceof Enum
          && AbstractRequestContext.this.getAutoBeanFactory() instanceof EnumMap) {
        value = ValueCodex.encode(
            ((EnumMap) AbstractRequestContext.this.getAutoBeanFactory()).getToken((Enum<?>) obj));
      } else if (ValueCodex.canDecode(obj.getClass())) {
        value = ValueCodex.encode(obj);
      } else {
        // XXX user-provided implementation of interface?
        value = AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(obj));
      }
      return value;
    }
  }

  class StandardPayloadDialect implements DialectImpl {

    /**
     * Called by generated subclasses to enqueue a method invocation.
     */
    @Override
    public void addInvocation(final AbstractRequest<?, ?> request) {
      AbstractRequestContext.this.state.invocations.add(request);
      for (final Object arg : request.getRequestData().getOrderedParameters()) {
        AbstractRequestContext.this.retainArg(arg);
      }
    }

    /**
     * Assemble all of the state that has been accumulated in this context. This includes:
     * <ul>
     * <li>Diffs accumulated on objects passed to {@link #edit}.
     * <li>Invocations accumulated as Request subtypes passed to {@link #addInvocation}.
     * </ul>
     */
    @Override
    public String makePayload() {
      // Get the factory from the runtime-specific holder.
      final MessageFactory f = MessageFactoryHolder.FACTORY;

      final List<OperationMessage> operations = AbstractRequestContext.this.makePayloadOperations();
      final List<InvocationMessage> invocationMessages =
          AbstractRequestContext.this.makePayloadInvocations();

      // Create the outer envelope message
      final AutoBean<RequestMessage> bean = f.request();
      final RequestMessage requestMessage = bean.as();
      requestMessage
          .setRequestFactory(AbstractRequestContext.this.getRequestFactory().getFactoryTypeToken());
      if (!invocationMessages.isEmpty()) {
        requestMessage.setInvocations(invocationMessages);
      }
      if (!operations.isEmpty()) {
        requestMessage.setOperations(operations);
      }
      return AutoBeanCodex.encode(bean).getPayload();
    }

    @Override
    public void processPayload(final Receiver<Void> receiver, final String payload) {
      final ResponseMessage response =
          AutoBeanCodex.decode(MessageFactoryHolder.FACTORY, ResponseMessage.class, payload).as();
      if (response.getGeneralFailure() != null) {
        final ServerFailureMessage failure = response.getGeneralFailure();
        final ServerFailure fail = new ServerFailure(failure.getMessage(),
            failure.getExceptionType(), failure.getStackTrace(), failure.isFatal());

        AbstractRequestContext.this.fail(receiver, fail);
        return;
      }

      // Process violations and then stop
      if (response.getViolations() != null) {
        final Set<ConstraintViolation<?>> errors = new HashSet<>();
        for (final ViolationMessage message : response.getViolations()) {
          errors.add(new MyConstraintViolation(message));
        }

        AbstractRequestContext.this.violation(receiver, errors);
        return;
      }

      // Process operations
      AbstractRequestContext.this.processReturnOperations(response);

      // Send return values
      Set<Throwable> causes = null;
      for (int i = 0, j = AbstractRequestContext.this.state.invocations.size(); i < j; i++) {
        try {
          if (response.getStatusCodes().get(i)) {
            AbstractRequestContext.this.state.invocations.get(i)
                .onSuccess(response.getInvocationResults().get(i));
          } else {
            final ServerFailureMessage failure = AutoBeanCodex.decode(MessageFactoryHolder.FACTORY,
                ServerFailureMessage.class, response.getInvocationResults().get(i)).as();
            AbstractRequestContext.this.state.invocations.get(i)
                .onFail(new ServerFailure(failure.getMessage(), failure.getExceptionType(),
                    failure.getStackTrace(), failure.isFatal()));
          }
        } catch (final Throwable t) {
          if (causes == null) {
            causes = new HashSet<>();
          }
          causes.add(t);
        }
      }

      if (receiver != null) {
        try {
          receiver.onSuccess(null);
        } catch (final Throwable t) {
          if (causes == null) {
            causes = new HashSet<>();
          }
          causes.add(t);
        }
      }
      // After success, shut down the context
      AbstractRequestContext.this.state.editedProxies.clear();
      AbstractRequestContext.this.state.invocations.clear();
      AbstractRequestContext.this.state.returnedProxies.clear();

      if (causes != null) {
        throw new UmbrellaException(causes);
      }
    }
  }

  private class MyConstraintViolation implements ConstraintViolation<BaseProxy> {
    private final BaseProxy leafBean;
    private final String messageTemplate;
    private final String message;
    private final String path;
    private final BaseProxy rootBean;
    private final Class<? extends BaseProxy> rootBeanClass;

    public MyConstraintViolation(final ViolationMessage msg) {
      final AutoBean<? extends BaseProxy> leafProxy = this.findEditedProxy(msg.getLeafBeanId());
      this.leafBean = leafProxy == null ? null : leafProxy.as();
      this.message = msg.getMessage();
      this.messageTemplate = msg.getMessageTemplate();
      this.path = msg.getPath();
      final AutoBean<? extends BaseProxy> rootProxy = this.findEditedProxy(msg.getRootBeanId());
      this.rootBeanClass = rootProxy.getType();
      this.rootBean = rootProxy.as();
    }

    @Override
    public ConstraintDescriptor<?> getConstraintDescriptor() {
      return null;
    }

    @Override
    public Object getInvalidValue() {
      return null;
    }

    @Override
    public Object getLeafBean() {
      return this.leafBean;
    }

    @Override
    public String getMessage() {
      return this.message;
    }

    @Override
    public String getMessageTemplate() {
      return this.messageTemplate;
    }

    @Override
    public Path getPropertyPath() {
      return new Path() {
        @Override
        public Iterator<Node> iterator() {
          return Collections.<Node>emptyList().iterator();
        }

        @Override
        public String toString() {
          return MyConstraintViolation.this.path;
        }
      };
    }

    @Override
    public BaseProxy getRootBean() {
      return this.rootBean;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class<BaseProxy> getRootBeanClass() {
      return (Class<BaseProxy>) this.rootBeanClass;
    }

    private AutoBean<? extends BaseProxy> findEditedProxy(final IdMessage idMessage) {
      // Support violations for value objects.
      final SimpleProxyId<BaseProxy> rootId = AbstractRequestContext.this.getId(idMessage);

      // The stub is empty, since we don't process any OperationMessages
      final AutoBean<BaseProxy> stub =
          AbstractRequestContext.this.getProxyForReturnPayloadGraph(rootId);

      // So pick up the instance that we just sent to the server
      return AbstractRequestContext.this.state.editedProxies.get(BaseProxyCategory.stableId(stub));
    }

    @Override
    public Object[] getExecutableParameters() {
      // not supported by gwt implementation
      return new Object[0];
    }

    @Override
    public Object getExecutableReturnValue() {
      // not supported by gwt implementation
      return null;
    }

    @Override
    public <U> U unwrap(final Class<U> ptype) {
      throw new UnsupportedOperationException("GWT Validation does not support upwrap()");
    }
  }

  private static final WriteOperation[] DELETE_ONLY = {WriteOperation.DELETE};
  private static final WriteOperation[] PERSIST_AND_UPDATE =
      {WriteOperation.PERSIST, WriteOperation.UPDATE};
  private static final WriteOperation[] UPDATE_ONLY = {WriteOperation.UPDATE};
  private static int payloadId = 100;

  private State state;

  protected AbstractRequestContext(final AbstractRequestFactory factory, final Dialect dialect) {
    this.setState(new State(factory, dialect.create(this), this));
  }

  @Override
  public <T extends RequestContext> T append(final T other) {
    final AbstractRequestContext child = (AbstractRequestContext) other;
    if (!this.state.isCompatible(child.state)) {
      throw new IllegalStateException(
          this.getClass().getName() + " and " + child.getClass().getName() + " are not compatible");
    }
    if (!child.state.isClean()) {
      throw new IllegalStateException("The provided RequestContext has been changed");
    }
    child.setState(this.state);
    return other;
  }

  /**
   * Create a new object, with an ephemeral id.
   */
  @Override
  public <T extends BaseProxy> T create(final Class<T> clazz) {
    this.checkLocked();

    final SimpleProxyId<T> id = this.state.requestFactory.allocateId(clazz);
    final AutoBean<T> created = this.createProxy(clazz, id, false);
    return this.takeOwnership(created);
  }

  @Override
  public <T extends BaseProxy> T edit(final T object) {
    return this.editProxy(object);
  }

  /**
   * Take ownership of a proxy instance and make it editable.
   */
  public <T extends BaseProxy> T editProxy(final T object) {
    AutoBean<T> bean = this.checkStreamsNotCrossed(object);
    this.checkLocked();

    @SuppressWarnings("unchecked")
    final AutoBean<T> previouslySeen =
        (AutoBean<T>) this.state.editedProxies.get(BaseProxyCategory.stableId(bean));
    if (previouslySeen != null && !previouslySeen.isFrozen()) {
      /*
       * If we've seen the object before, it might be because it was passed in as a method argument.
       * This does not guarantee its mutability, so check that here before returning the cached
       * object.
       */
      return previouslySeen.as();
    }

    // Create editable copies
    final AutoBean<T> parent = bean;
    bean = this.cloneBeanAndCollections(bean);
    bean.setTag(Constants.PARENT_OBJECT, parent);
    return bean.as();
  }

  @Override
  public <P extends EntityProxy> Request<P> find(final EntityProxyId<P> proxyId) {
    return new AbstractRequest<BaseProxy, P>(this) {
      {
        this.requestContext.addInvocation(this);
      }

      @Override
      protected RequestData makeRequestData() {
        // This method is normally generated, hence the ugly constructor
        return new RequestData(Constants.FIND_METHOD_OPERATION, new Object[] {proxyId},
            this.propertyRefs, proxyId.getProxyClass(), null);
      }
    };
  }

  /**
   * Make sure there's a default receiver so errors don't get dropped. This behavior should be
   * revisited when chaining is supported, depending on whether or not chained invocations can fail
   * independently.
   */
  @Override
  public void fire() {
    boolean needsReceiver = true;
    for (final AbstractRequest<?, ?> request : this.state.invocations) {
      if (request.hasReceiver()) {
        needsReceiver = false;
        break;
      }
    }

    if (needsReceiver) {
      this.doFire(new Receiver<Void>() {
        @Override
        public void onSuccess(final Void response) {
          // Don't care
        }
      });
    } else {
      this.doFire(null);
    }
  }

  @Override
  public void fire(final Receiver<Void> receiver) {
    if (receiver == null) {
      throw new IllegalArgumentException();
    }
    this.doFire(receiver);
  }

  /**
   * EntityCodex support.
   */
  @Override
  public <Q extends BaseProxy> AutoBean<Q> getBeanForPayload(final Splittable serializedProxyId) {
    final IdMessage ref =
        AutoBeanCodex.decode(MessageFactoryHolder.FACTORY, IdMessage.class, serializedProxyId).as();
    @SuppressWarnings("unchecked")
    final SimpleProxyId<Q> id = (SimpleProxyId<Q>) this.getId(ref);
    return this.getProxyForReturnPayloadGraph(id);
  }

  @Override
  public AbstractRequestFactory getRequestFactory() {
    return this.state.requestFactory;
  }

  /**
   * EntityCodex support.
   */
  @Override
  public Splittable getSerializedProxyId(final SimpleProxyId<?> stableId) {
    final AutoBean<IdMessage> bean = MessageFactoryHolder.FACTORY.id();
    final IdMessage ref = bean.as();
    ref.setServerId(stableId.getServerId());
    ref.setTypeToken(this.getRequestFactory().getTypeToken(stableId.getProxyClass()));
    if (stableId.isSynthetic()) {
      ref.setStrength(Strength.SYNTHETIC);
      ref.setSyntheticId(stableId.getSyntheticId());
    } else if (stableId.isEphemeral()) {
      ref.setStrength(Strength.EPHEMERAL);
      ref.setClientId(stableId.getClientId());
    }
    return AutoBeanCodex.encode(bean);
  }

  @Override
  public boolean isChanged() {
    /*
     * NB: Don't use the presence of ephemeral objects for this test.
     *
     * Diff the objects until one is found to be different. It's not just a simple flag-check
     * because of the possibility of "unmaking" a change, per the JavaDoc.
     *
     * TODO: try to get rid of the 'diffing' flag and optimize the diffing of objects:
     * http://code.google.com/p/google-web-toolkit/issues/detail?id=7379
     */
    assert !this.state.diffing;
    this.state.diffing = true;
    try {
      for (final AutoBean<? extends BaseProxy> bean : this.state.editedProxies.values()) {
        AutoBean<?> previous = bean.getTag(Constants.PARENT_OBJECT);
        if (previous == null) {
          // Compare to empty object
          final Class<?> proxyClass = stableId(bean).getProxyClass();
          previous = this.getAutoBeanFactory().create(proxyClass);
        }
        if (!AutoBeanUtils.diff(previous, bean).isEmpty()) {
          return true;
        }
      }
      return false;
    } finally {
      this.state.diffing = false;
    }
  }

  /**
   * EntityCodex support.
   */
  @Override
  public boolean isEntityType(final Class<?> clazz) {
    return this.state.requestFactory.isEntityType(clazz);
  }

  public boolean isLocked() {
    return this.state.locked;
  }

  /**
   * EntityCodex support.
   */
  @Override
  public boolean isValueType(final Class<?> clazz) {
    return this.state.requestFactory.isValueType(clazz);
  }

  public void setFireDisabled(final boolean disabled) {
    this.state.fireDisabled = disabled;
  }

  /**
   * Called by generated subclasses to enqueue a method invocation.
   */
  protected void addInvocation(final AbstractRequest<?, ?> request) {
    this.state.dialect.addInvocation(request);
  }

  /**
   * Creates a new proxy with an assigned ID.
   *
   * @param clazz The proxy type
   * @param id The id to be assigned to the new proxy
   * @param useAppendedContexts if {@code true} use the AutoBeanFactory types associated with any
   *        contexts that have been passed into {@link #append(RequestContext)}. If {@code false},
   *        this method will only create proxy types reachable from the implemented RequestContext
   *        interface.
   * @throws IllegalArgumentException if the requested proxy type cannot be created
   */
  protected <T extends BaseProxy> AutoBean<T> createProxy(final Class<T> clazz,
      final SimpleProxyId<T> id, final boolean useAppendedContexts) {
    AutoBean<T> created = null;
    if (useAppendedContexts) {
      for (final AbstractRequestContext ctx : this.state.appendedContexts) {
        created = ctx.getAutoBeanFactory().create(clazz);
        if (created != null) {
          break;
        }
      }
    } else {
      created = this.getAutoBeanFactory().create(clazz);
    }
    if (created != null) {
      created.setTag(STABLE_ID, id);
      return created;
    }
    throw new IllegalArgumentException("Unknown proxy type " + clazz.getName());
  }

  /**
   * Invoke the appropriate {@code onFailure} callbacks, possibly throwing an
   * {@link UmbrellaException} if one or more callbacks fails.
   */
  protected void fail(final Receiver<Void> receiver, final ServerFailure failure) {
    this.reuse();
    failure.setRequestContext(this);
    Set<Throwable> causes = null;
    for (final AbstractRequest<?, ?> request : new ArrayList<>(this.state.invocations)) {
      try {
        request.onFail(failure);
      } catch (final Throwable t) {
        if (causes == null) {
          causes = new HashSet<>();
        }
        causes.add(t);
      }
    }
    if (receiver != null) {
      try {
        receiver.onFailure(failure);
      } catch (final Throwable t) {
        if (causes == null) {
          causes = new HashSet<>();
        }
        causes.add(t);
      }
    }

    if (causes != null) {
      throw new UmbrellaException(causes);
    }
  }

  /**
   * Returns an AutoBeanFactory that can produce the types reachable only from this RequestContext.
   */
  protected abstract AutoBeanFactory getAutoBeanFactory();

  /**
   * Invoke the appropriate {@code onViolation} callbacks, possibly throwing an
   * {@link UmbrellaException} if one or more callbacks fails.
   */
  protected void violation(final Receiver<Void> receiver,
      final Set<ConstraintViolation<?>> errors) {
    this.reuse();
    Set<Throwable> causes = null;
    for (final AbstractRequest<?, ?> request : new ArrayList<>(this.state.invocations)) {
      try {
        request.onViolation(errors);
      } catch (final Throwable t) {
        if (causes == null) {
          causes = new HashSet<>();
        }
        causes.add(t);
      }
    }
    if (receiver != null) {
      try {
        receiver.onConstraintViolation(errors);
      } catch (final Throwable t) {
        if (causes == null) {
          causes = new HashSet<>();
        }
        causes.add(t);
      }
    }

    if (causes != null) {
      throw new UmbrellaException(causes);
    }
  }

  /**
   * Resolves an IdMessage into an SimpleProxyId.
   */
  SimpleProxyId<BaseProxy> getId(final IdMessage op) {
    if (Strength.SYNTHETIC.equals(op.getStrength())) {
      return this.allocateSyntheticId(op.getTypeToken(), op.getSyntheticId());
    }
    return this.state.requestFactory.getId(op.getTypeToken(), op.getServerId(), op.getClientId());
  }

  /**
   * Creates or retrieves a new canonical AutoBean to represent the given id in the returned
   * payload.
   */
  <Q extends BaseProxy> AutoBean<Q> getProxyForReturnPayloadGraph(final SimpleProxyId<Q> id) {
    @SuppressWarnings("unchecked")
    AutoBean<Q> bean = (AutoBean<Q>) this.state.returnedProxies.get(id);
    if (bean == null) {
      final Class<Q> proxyClass = id.getProxyClass();
      bean = this.createProxy(proxyClass, id, true);
      this.state.returnedProxies.put(id, bean);
    }

    return bean;
  }

  /**
   * Whether the RequestContext is currently diffing proxies.
   * <p>
   * This flag is used in {@link BaseProxyCategory} and {@link EntityProxyCategory} to influence the
   * way proxies are being compared for equality, and to prevent auto-editing proxies when walking
   * reference properties.
   * </p>
   * <p>
   * See http://code.google.com/p/google-web-toolkit/issues/detail?id=5952
   * </p>
   * <p>
   * TODO: try to get rid of this flag. See
   * http://code.google.com/p/google-web-toolkit/issues/detail?id=7379
   * </p>
   */
  boolean isDiffing() {
    return this.state.diffing;
  }

  /**
   * Create a single OperationMessage that encapsulates the state of a proxy AutoBean.
   */
  AutoBean<OperationMessage> makeOperationMessage(final SimpleProxyId<BaseProxy> stableId,
      final AutoBean<?> proxyBean, final boolean useDelta) {

    // The OperationMessages describes operations on exactly one entity
    final AutoBean<OperationMessage> toReturn = MessageFactoryHolder.FACTORY.operation();
    final OperationMessage operation = toReturn.as();
    operation.setTypeToken(this.state.requestFactory.getTypeToken(stableId.getProxyClass()));

    // Find the object to compare against
    AutoBean<?> parent;
    if (stableId.isEphemeral()) {
      // Newly-created object, use a blank object to compare against
      parent = this.createProxy(stableId.getProxyClass(), stableId, true);

      // Newly-created objects go into the persist operation bucket
      operation.setOperation(WriteOperation.PERSIST);
      // The ephemeral id is passed to the server
      operation.setClientId(stableId.getClientId());
      operation.setStrength(Strength.EPHEMERAL);
    } else if (stableId.isSynthetic()) {
      // Newly-created object, use a blank object to compare against
      parent = this.createProxy(stableId.getProxyClass(), stableId, true);

      // Newly-created objects go into the persist operation bucket
      operation.setOperation(WriteOperation.PERSIST);
      // The ephemeral id is passed to the server
      operation.setSyntheticId(stableId.getSyntheticId());
      operation.setStrength(Strength.SYNTHETIC);
    } else {
      parent = proxyBean.getTag(Constants.PARENT_OBJECT);
      // Requests involving existing objects use the persisted id
      operation.setServerId(stableId.getServerId());
      operation.setOperation(WriteOperation.UPDATE);
    }
    assert !useDelta || parent != null;

    // Send our version number to the server to cut down on future payloads
    final String version = proxyBean.getTag(Constants.VERSION_PROPERTY_B64);
    if (version != null) {
      operation.setVersion(version);
    }

    Map<String, Object> diff = Collections.emptyMap();
    if (this.isEntityType(stableId.getProxyClass())) {
      // Compute what's changed on the client
      diff = useDelta ? AutoBeanUtils.diff(parent, proxyBean)
          : AutoBeanUtils.getAllProperties(proxyBean);
    } else if (this.isValueType(stableId.getProxyClass())) {
      // Send everything
      diff = AutoBeanUtils.getAllProperties(proxyBean);
    }

    if (!diff.isEmpty()) {
      final Map<String, Splittable> propertyMap = new HashMap<>();
      for (final Map.Entry<String, Object> entry : diff.entrySet()) {
        propertyMap.put(entry.getKey(), EntityCodex.encode(this, entry.getValue()));
      }
      operation.setPropertyMap(propertyMap);
    }
    return toReturn;
  }

  /**
   * Create a new EntityProxy from a snapshot in the return payload.
   *
   * @param id the EntityProxyId of the object
   * @param returnRecord the JSON map containing property/value pairs
   * @param operations the WriteOperation eventns to broadcast over the EventBus
   */
  <Q extends BaseProxy> Q processReturnOperation(final SimpleProxyId<Q> id,
      final OperationMessage op, final WriteOperation... operations) {

    final AutoBean<Q> toMutate = this.getProxyForReturnPayloadGraph(id);
    toMutate.setTag(Constants.VERSION_PROPERTY_B64, op.getVersion());

    final Map<String, Splittable> properties = op.getPropertyMap();
    if (properties != null) {
      // Apply updates
      toMutate.accept(new AutoBeanVisitor() {
        @Override
        public boolean visitReferenceProperty(final String propertyName, final AutoBean<?> value,
            final PropertyContext ctx) {
          if (ctx.canSet()) {
            if (properties.containsKey(propertyName)) {
              final Splittable raw = properties.get(propertyName);
              Object decoded = null;
              if (ctx.getType() == Map.class) {
                final MapPropertyContext mapCtx = (MapPropertyContext) ctx;
                final Class<?> keyType = mapCtx.getKeyType();
                final Class<?> valueType = mapCtx.getValueType();
                decoded = EntityCodex.decode(AbstractRequestContext.this, mapCtx.getType(), keyType,
                    valueType, raw);
              } else {
                final Class<?> elementType = ctx instanceof CollectionPropertyContext
                    ? ((CollectionPropertyContext) ctx).getElementType()
                    : null;
                decoded = EntityCodex.decode(AbstractRequestContext.this, ctx.getType(),
                    elementType, raw);
              }
              ctx.set(decoded);
            }
          }
          return false;
        }

        @Override
        public boolean visitValueProperty(final String propertyName, final Object value,
            final PropertyContext ctx) {
          if (ctx.canSet()) {
            if (properties.containsKey(propertyName)) {
              final Splittable raw = properties.get(propertyName);
              Object decoded = ValueCodex.decode(ctx.getType(), raw);
              /*
               * Hack for Date subtypes, consider generalizing for "custom serializers"
               */
              if (decoded != null && Date.class.equals(ctx.getType())) {
                decoded = new DatePoser((Date) decoded);
              }
              ctx.set(decoded);
            }
          }
          return false;
        }
      });
    }

    // Finished applying updates, freeze the bean
    this.makeImmutable(toMutate);
    final Q proxy = toMutate.as();

    /*
     * Notify subscribers if the object differs from when it first came into the RequestContext.
     */
    if (operations != null && this.state.requestFactory.isEntityType(id.getProxyClass())) {
      for (final WriteOperation writeOperation : operations) {
        if (writeOperation.equals(WriteOperation.UPDATE)
            && !this.state.requestFactory.hasVersionChanged(id, op.getVersion())) {
          // No updates if the server reports no change
          continue;
        }
        this.state.requestFactory.getEventBus().fireEventFromSource(
            new EntityProxyChange<>((EntityProxy) proxy, writeOperation), id.getProxyClass());
      }
    }
    return proxy;
  }

  /**
   * Get-or-create method for synthetic ids.
   *
   * @see #syntheticIds
   */
  private <Q extends BaseProxy> SimpleProxyId<Q> allocateSyntheticId(final String typeToken,
      final int syntheticId) {
    @SuppressWarnings("unchecked")
    SimpleProxyId<Q> toReturn = (SimpleProxyId<Q>) this.state.syntheticIds.get(syntheticId);
    if (toReturn == null) {
      toReturn = this.state.requestFactory
          .allocateId(this.state.requestFactory.<Q>getTypeFromToken(typeToken));
      this.state.syntheticIds.put(syntheticId, toReturn);
    }
    return toReturn;
  }

  private void checkLocked() {
    if (this.state.locked) {
      throw new IllegalStateException("A request is already in progress");
    }
  }

  /**
   * This method checks that a proxy object is either immutable, or already edited by this context.
   */
  private <T> AutoBean<T> checkStreamsNotCrossed(final T object) {
    final AutoBean<T> bean = AutoBeanUtils.getAutoBean(object);
    if (bean == null) {
      // Unexpected; some kind of foreign implementation?
      throw new IllegalArgumentException(object.getClass().getName());
    }

    final State otherState = bean.getTag(REQUEST_CONTEXT_STATE);
    if (!bean.isFrozen() && otherState != this.state) {
      /*
       * This means something is way off in the weeds. If a bean is editable, it's supposed to be
       * associated with a RequestContext.
       */
      assert otherState != null : "Unfrozen bean with null RequestContext";

      /*
       * Already editing the object in another context or it would have been in the editing map.
       */
      throw new IllegalArgumentException(
          "Attempting to edit an EntityProxy" + " previously edited by another RequestContext");
    }
    return bean;
  }

  /**
   * Shallow-clones an autobean and makes duplicates of the collection types. A regular
   * {@link AutoBean#clone} won't duplicate reference properties.
   */
  private <T extends BaseProxy> AutoBean<T> cloneBeanAndCollections(final AutoBean<T> toClone) {
    final AutoBean<T> clone = toClone.getFactory().create(toClone.getType());
    clone.setTag(STABLE_ID, toClone.getTag(STABLE_ID));
    clone.setTag(Constants.VERSION_PROPERTY_B64, toClone.getTag(Constants.VERSION_PROPERTY_B64));
    /*
     * Take ownership here to prevent cycles in value objects from overflowing the stack.
     */
    this.takeOwnership(clone);
    clone.accept(new AutoBeanVisitor() {
      final Map<String, Object> values = AutoBeanUtils.getAllProperties(toClone);

      @Override
      public boolean visitCollectionProperty(final String propertyName,
          AutoBean<Collection<?>> value, final CollectionPropertyContext ctx) {
        // javac generics bug
        value = AutoBeanUtils.<Collection<?>, Collection<?>>getAutoBean(
            (Collection<?>) this.values.get(propertyName));
        if (value != null) {
          Collection<Object> collection;
          if (List.class == ctx.getType()) {
            collection = new ArrayList<>();
          } else if (Set.class == ctx.getType()) {
            collection = new HashSet<>();
          } else {
            // Should not get here if the validator works correctly
            throw new IllegalArgumentException(ctx.getType().getName());
          }

          if (AbstractRequestContext.this.isValueType(ctx.getElementType())
              || AbstractRequestContext.this.isEntityType(ctx.getElementType())) {
            /*
             * Proxies must be edited up-front so that the elements in the collection have stable
             * identity.
             */
            for (final Object o : value.as()) {
              if (o == null) {
                collection.add(null);
              } else {
                collection.add(AbstractRequestContext.this.editProxy((BaseProxy) o));
              }
            }
          } else {
            // For simple values, just copy the values
            collection.addAll(value.as());
          }

          ctx.set(collection);
        }
        return false;
      }

      @Override
      public boolean visitReferenceProperty(final String propertyName, AutoBean<?> value,
          final PropertyContext ctx) {
        value = AutoBeanUtils.getAutoBean(this.values.get(propertyName));
        if (value != null) {
          if (AbstractRequestContext.this.isValueType(ctx.getType())
              || AbstractRequestContext.this.isEntityType(ctx.getType())) {
            /*
             * Value proxies must be cloned upfront, since the value is replaced outright.
             */
            @SuppressWarnings("unchecked")
            final AutoBean<BaseProxy> valueBean = (AutoBean<BaseProxy>) value;
            ctx.set(AbstractRequestContext.this.editProxy(valueBean.as()));
          } else {
            ctx.set(value.as());
          }
        }
        return false;
      }

      @Override
      public boolean visitValueProperty(final String propertyName, final Object value,
          final PropertyContext ctx) {
        ctx.set(this.values.get(propertyName));
        return false;
      }
    });
    return clone;
  }

  private void doFire(final Receiver<Void> receiver) {
    final Receiver<Void> finalReceiver;
    if (this.state.fireDisabled) {
      if (receiver != null) {
        if (this.state.fanout == null) {
          this.state.fanout = new FanoutReceiver<>();
        }
        this.state.fanout.add(receiver);
      }
      return;
    } else if (this.state.fanout != null) {
      if (receiver != null) {
        this.state.fanout.add(receiver);
      }
      finalReceiver = this.state.fanout;
    } else {
      finalReceiver = receiver;
    }
    this.checkLocked();
    this.state.locked = true;

    this.freezeEntities(true);

    final String payload = this.state.dialect.makePayload();
    this.state.requestFactory.getRequestTransport().send(payload, new TransportReceiver() {
      @Override
      public void onTransportFailure(final ServerFailure failure) {
        AbstractRequestContext.this.fail(finalReceiver, failure);
      }

      @Override
      public void onTransportSuccess(final String payload) {
        AbstractRequestContext.this.state.dialect.processPayload(finalReceiver, payload);
      }
    });
  }

  /**
   * Set the frozen status of all EntityProxies owned by this context.
   */
  private void freezeEntities(final boolean frozen) {
    for (final AutoBean<?> bean : this.state.editedProxies.values()) {
      bean.setFrozen(frozen);
    }
  }

  /**
   * Make an EntityProxy immutable.
   */
  private void makeImmutable(final AutoBean<? extends BaseProxy> toMutate) {
    // Always diff'ed against itself, producing a no-op
    toMutate.setTag(Constants.PARENT_OBJECT, toMutate);
    // Act with entity-identity semantics
    toMutate.setTag(REQUEST_CONTEXT_STATE, null);
    toMutate.setFrozen(true);
  }

  /**
   * Create an InvocationMessage for each remote method call being made by the context.
   */
  private List<InvocationMessage> makePayloadInvocations() {
    final MessageFactory f = MessageFactoryHolder.FACTORY;

    final List<InvocationMessage> invocationMessages = new ArrayList<>();
    for (final AbstractRequest<?, ?> invocation : this.state.invocations) {
      // RequestData is produced by the generated subclass
      final RequestData data = invocation.getRequestData();
      final InvocationMessage message = f.invocation().as();

      // Operation; essentially a method descriptor
      message.setOperation(data.getOperation());

      // The arguments to the with() calls
      final Set<String> refsToSend = data.getPropertyRefs();
      if (!refsToSend.isEmpty()) {
        message.setPropertyRefs(refsToSend);
      }

      // Parameter values or references
      final List<Splittable> parameters = new ArrayList<>(data.getOrderedParameters().length);
      for (final Object param : data.getOrderedParameters()) {
        parameters.add(EntityCodex.encode(this, param));
      }
      if (!parameters.isEmpty()) {
        message.setParameters(parameters);
      }

      invocationMessages.add(message);
    }
    return invocationMessages;
  }

  /**
   * Compute deltas for each entity seen by the context.
   * <p>
   * TODO(t.broyer): reduce payload size by only sending proxies that are directly referenced by
   * invocation arguments or by other proxies. For backwards-compatibility with no-op requests and
   * operation-only requests, only do so when there's at least one invocation (or we can choose to
   * break backwards compatibility for those edge-cases).
   * </p>
   */
  private List<OperationMessage> makePayloadOperations() {
    assert this.isLocked();
    assert !this.state.diffing;
    this.state.diffing = true;
    try {
      final List<OperationMessage> operations = new ArrayList<>();
      for (final AutoBean<? extends BaseProxy> currentView : this.state.editedProxies.values()) {
        final OperationMessage operation = this
            .makeOperationMessage(BaseProxyCategory.stableId(currentView), currentView, true).as();
        operations.add(operation);
      }
      return operations;
    } finally {
      this.state.diffing = false;
    }
  }

  /**
   * Process an array of OperationMessages.
   */
  private void processReturnOperations(final ResponseMessage response) {
    final List<OperationMessage> ops = response.getOperations();

    // If there are no observable effects, this will be null
    if (ops == null) {
      return;
    }

    for (final OperationMessage op : ops) {
      final SimpleProxyId<?> id = this.getId(op);
      WriteOperation[] toPropagate = null;

      // May be null if the server is returning an unpersisted object
      final WriteOperation effect = op.getOperation();
      if (effect != null) {
        switch (effect) {
          case DELETE:
            toPropagate = DELETE_ONLY;
            break;
          case PERSIST:
            toPropagate = PERSIST_AND_UPDATE;
            break;
          case UPDATE:
            toPropagate = UPDATE_ONLY;
            break;
          default:
            // Should never reach here
            throw new RuntimeException(effect.toString());
        }
      }
      this.processReturnOperation(id, op, toPropagate);
    }

    assert this.state.returnedProxies.size() == ops.size();
  }

  /**
   * Ensures that any method arguments are retained in the context's sphere of influence.
   */
  private void retainArg(final Object arg) {
    if (arg instanceof Iterable<?>) {
      for (final Object o : (Iterable<?>) arg) {
        this.retainArg(o);
      }
    } else if (arg instanceof BaseProxy) {
      // Calling edit will validate and set up the tracking we need
      this.edit((BaseProxy) arg);
    }
  }

  /**
   * Returns the requests that were dequeued as part of reusing the context.
   */
  private void reuse() {
    this.freezeEntities(false);
    this.state.locked = false;
  }

  private void setState(final State state) {
    this.state = state;
    state.addContext(this);
  }

  /**
   * Make the EnityProxy bean edited and owned by this RequestContext.
   */
  private <T extends BaseProxy> T takeOwnership(final AutoBean<T> bean) {
    this.state.editedProxies.put(stableId(bean), bean);
    bean.setTag(REQUEST_CONTEXT_STATE, this.state);
    return bean.as();
  }
}
