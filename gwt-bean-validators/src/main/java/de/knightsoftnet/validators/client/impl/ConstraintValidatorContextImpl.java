/*
 * Copyright 2010 Google Inc. Copyright 2016 Manfred Tremmel
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

package de.knightsoftnet.validators.client.impl;

import de.knightsoftnet.validators.client.impl.metadata.MessageAndPath;

import org.hibernate.validator.internal.engine.path.NodeImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ClockProvider;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder.LeafNodeBuilderCustomizableContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderDefinedContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder.NodeContextBuilder;
import javax.validation.ConstraintViolation;
import javax.validation.metadata.ConstraintDescriptor;

/**
 * GWT safe immutable implementation of {@link ConstraintValidatorContext}.
 * <p>
 * These objects are very short lived.
 * </p>
 *
 * @param <A> the constraint being validated
 * @param <T> the type of object being validated
 */
public class ConstraintValidatorContextImpl<A extends Annotation, T>
    implements ConstraintValidatorContext {

  /**
   * Builder for {@link ConstraintValidatorContextImpl}.
   */
  public final class ConstraintViolationBuilderImpl implements ConstraintViolationBuilder {

    private final ConstraintValidatorContextImpl<A, T> context;
    private final String messageTemplate;

    /**
     * constructor.
     *
     * @param constraintValidatorContextImpl context
     * @param messageTemplate message template
     */
    public ConstraintViolationBuilderImpl(
        final ConstraintValidatorContextImpl<A, T> constraintValidatorContextImpl,
        final String messageTemplate) {
      this.context = constraintValidatorContextImpl;
      this.messageTemplate = messageTemplate;
    }

    @Override
    public ConstraintValidatorContext addConstraintViolation() {
      messages.add(new MessageAndPath(context.basePath, messageTemplate));
      return context;
    }

    @Override
    public NodeBuilderDefinedContext addNode(final String name) {
      context.basePath.addPropertyNode(name);
      return new NodeBuilderDefinedContextImpl(this, messageTemplate, context.basePath);
    }

    @Override
    public NodeBuilderCustomizableContext addPropertyNode(final String pname) {
      context.basePath.addPropertyNode(pname);
      return new NodeBuilderCustomizableContextImpl(this, messageTemplate, context.basePath);
    }

    @Override
    public LeafNodeBuilderCustomizableContext addBeanNode() {
      throw new UnsupportedOperationException("GWT Validation does not support addBeanNode()");
    }

    @Override
    public ContainerElementNodeBuilderCustomizableContext addContainerElementNode(final String name,
        final Class<?> containerType, final Integer typeArgumentIndex) {
      throw new UnsupportedOperationException(
          "GWT Validation does not support addContainerElementNode(String, Class, Integer).");
    }

    @Override
    public NodeBuilderDefinedContext addParameterNode(final int pindex) {
      context.basePath.addParameterNode(null, pindex);
      return new NodeBuilderDefinedContextImpl(this, messageTemplate, context.basePath);
    }
  }

  /**
   * Immutable GWT safe {@link NodeBuilderCustomizableContext}.
   */
  public final class NodeBuilderCustomizableContextImpl implements NodeBuilderCustomizableContext {
    private final String messageTemplate;
    private final ConstraintViolationBuilderImpl parent;
    private final PathImpl path;

    /**
     * constructor.
     *
     * @param parent ConstraintViolationBuilderImpl which is parent
     * @param messageTemplate message template
     * @param path path of the element
     */
    public NodeBuilderCustomizableContextImpl(final ConstraintViolationBuilderImpl parent,
        final String messageTemplate, final PathImpl path) {
      super();
      this.parent = parent;
      this.messageTemplate = messageTemplate;
      this.path = path;
    }

    @Override
    public ConstraintValidatorContext addConstraintViolation() {
      messages.add(new MessageAndPath(path, messageTemplate));
      return parent.context;
    }

    @Override
    public NodeBuilderCustomizableContext addNode(final String name) {
      path.addPropertyNode(name);
      return new NodeBuilderCustomizableContextImpl(parent, messageTemplate, path);
    }

    @Override
    public NodeBuilderCustomizableContext addPropertyNode(final String pname) {
      path.addPropertyNode(pname);
      return new NodeBuilderCustomizableContextImpl(parent, messageTemplate, path);
    }

    @Override
    public NodeContextBuilder inIterable() {
      return new NodeContextBuilderImpl(path, messageTemplate, parent);
    }

    @Override
    public NodeBuilderCustomizableContext inContainer(final Class<?> containerClass,
        final Integer typeArgumentIndex) {
      throw new UnsupportedOperationException(
          "GWT Validation does not support inContainer(Class, Integer).");
    }

    @Override
    public LeafNodeBuilderCustomizableContext addBeanNode() {
      throw new UnsupportedOperationException("GWT Validation does not support addBeanNode()");
    }

    @Override
    public ConstraintViolationBuilder.ContainerElementNodeBuilderCustomizableContext //
        addContainerElementNode(final String name, final Class<?> containerType,
            final Integer typeArgumentIndex) {
      throw new UnsupportedOperationException(
          "GWT Validation does not support addContainerElementNode(String, Class, Integer).");
    }
  }

  /**
   * Immutable GWT safe {@link NodeBuilderDefinedContext}.
   */
  public final class NodeBuilderDefinedContextImpl implements NodeBuilderDefinedContext {

    private final String messageTemplate;
    private final ConstraintViolationBuilderImpl parent;
    private final PathImpl path;

    /**
     * constructor.
     *
     * @param parent ConstraintViolationBuilderImpl which is parent
     * @param messageTemplate message template
     * @param path path of the element
     */
    public NodeBuilderDefinedContextImpl(final ConstraintViolationBuilderImpl parent,
        final String messageTemplate, final PathImpl path) {
      this.parent = parent;
      this.messageTemplate = messageTemplate;
      this.path = path;
    }

    @Override
    public ConstraintValidatorContext addConstraintViolation() {
      ConstraintValidatorContextImpl.this.messages
          .add(new MessageAndPath(this.path, this.messageTemplate));
      return this.parent.context;
    }

    @Override
    public NodeBuilderCustomizableContext addNode(final String name) {
      this.path.addPropertyNode(name);
      return new NodeBuilderCustomizableContextImpl(this.parent, this.messageTemplate, this.path);
    }

    @Override
    public NodeBuilderCustomizableContext addPropertyNode(final String pname) {
      this.path.addPropertyNode(pname);
      return new NodeBuilderCustomizableContextImpl(this.parent, this.messageTemplate, this.path);
    }

    @Override
    public LeafNodeBuilderCustomizableContext addBeanNode() {
      throw new UnsupportedOperationException("GWT Validation does not support addBeanNode()");
    }

    @Override
    public ConstraintViolationBuilder.ContainerElementNodeBuilderCustomizableContext //
        addContainerElementNode(final String name, final Class<?> containerType,
            final Integer typeArgumentIndex) {
      throw new UnsupportedOperationException(
          "GWT Validation does not support addContainerElementNode(String, Class, Integer).");
    }
  }

  /**
   * Immutable GWT safe {@link NodeContextBuilder}.
   */
  public final class NodeContextBuilderImpl implements NodeContextBuilder {

    private final String messageTemplate;

    private final ConstraintViolationBuilderImpl parent;
    private final PathImpl path;

    /**
     * constructor.
     *
     * @param parent ConstraintViolationBuilderImpl which is parent
     * @param messageTemplate message template
     * @param path path of the element
     */
    public NodeContextBuilderImpl(final PathImpl path, final String messageTemplate,
        final ConstraintViolationBuilderImpl parent) {
      super();
      this.path = path;
      this.messageTemplate = messageTemplate;
      this.parent = parent;
    }

    @Override
    public ConstraintValidatorContext addConstraintViolation() {
      return null;
    }

    @Override
    public NodeBuilderCustomizableContext addNode(final String name) {
      this.path.addPropertyNode(name);
      return new NodeBuilderCustomizableContextImpl(this.parent, this.messageTemplate, this.path);
    }

    @Override
    public NodeBuilderCustomizableContext addPropertyNode(final String pname) {
      this.path.addPropertyNode(pname);
      return new NodeBuilderCustomizableContextImpl(this.parent, this.messageTemplate, this.path);
    }

    @Override
    public NodeBuilderDefinedContext atIndex(final Integer index) {
      this.path.addParameterNode(null, index.intValue());
      return new NodeBuilderDefinedContextImpl(this.parent, this.messageTemplate, this.path);
    }

    @Override
    public NodeBuilderDefinedContext atKey(final Object key) {
      NodeImpl.makeIterableAndSetMapKey(this.path.getLeafNode(), key);
      return new NodeBuilderDefinedContextImpl(this.parent, this.messageTemplate, this.path);
    }

    @Override
    public LeafNodeBuilderCustomizableContext addBeanNode() {
      throw new UnsupportedOperationException("GWT Validation does not support addBeanNode()");
      // this.path.addBeanNode();
      // return new NodeBuilderCustomizableContextImpl(this.parent, this.messageTemplate,
      // this.path);
    }

    @Override
    public ConstraintViolationBuilder.ContainerElementNodeBuilderCustomizableContext //
        addContainerElementNode(final String name, final Class<?> containerType,
            final Integer typeArgumentIndex) {
      throw new UnsupportedOperationException(
          "GWT Validation does not support addContainerElementNode(String, Class, Integer).");
    }
  }

  private final PathImpl basePath;
  private final ConstraintDescriptor<A> descriptor;

  private boolean disableDefault;
  private final Set<ConstraintViolation<T>> violations = new HashSet<>();
  private final HashSet<MessageAndPath> messages = new HashSet<>();

  /**
   * constructor.
   *
   * @param path path of the element
   * @param descriptor constraint descriptor
   */
  public ConstraintValidatorContextImpl(final PathImpl path,
      final ConstraintDescriptor<A> descriptor) {
    super();
    this.basePath = path;
    this.descriptor = descriptor;
  }

  @Override
  public ConstraintViolationBuilder buildConstraintViolationWithTemplate(
      final String messageTemplate) {
    return new ConstraintViolationBuilderImpl(new ConstraintValidatorContextImpl<A, T>(
        PathImpl.createCopy(this.basePath), this.descriptor), messageTemplate);
  }

  @Override
  public void disableDefaultConstraintViolation() {
    this.disableDefault = true;
  }

  @Override
  public String getDefaultConstraintMessageTemplate() {
    return (String) this.descriptor.getAttributes().get("message");
  }

  @Override
  public ClockProvider getClockProvider() {
    throw new UnsupportedOperationException("GWT Validation does not support getClockProvider().");
  }

  /**
   * getter for message and path.
   *
   * @return set which includes message and path
   */
  public Set<MessageAndPath> getMessageAndPaths() {
    if (!this.disableDefault) {
      this.messages
          .add(new MessageAndPath(this.basePath, this.getDefaultConstraintMessageTemplate()));
    }
    return this.messages;
  }

  public Set<ConstraintViolation<T>> getViolations() {
    return this.violations;
  }

  @SuppressWarnings("hiding")
  @Override
  public <T> T unwrap(final Class<T> ptype) {
    throw new UnsupportedOperationException("GWT Validation does not support upwrap()");
  }
}
