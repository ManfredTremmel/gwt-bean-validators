/*
 * Copyright 2012 Google Inc. Copyright 2016 Manfred Tremmel
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

package de.knightsoftnet.validators.rebind;

import de.knightsoftnet.validators.client.impl.GwtSpecificValidator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.metadata.BeanDescriptor;
import javax.validation.metadata.PropertyDescriptor;

/**
 * A cache and factory for BeanHelpers. There should be one BeanHelperCache per compilation run.
 * <p>
 * (public for tests)
 * </p>
 */
public class BeanHelperCache { // public for testing

  private final Map<JClassType, BeanHelper> cache;
  private final Validator serverSideValidator;

  /**
   * Creates a cache. There should be one cache per compiler run. (public for tests.)
   */
  public BeanHelperCache() {
    cache = new HashMap<>();
    serverSideValidator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  /**
   * Clears the cache. (Public for testing.)
   */
  public void clear() {
    cache.clear();
  }

  /**
   * Creates a BeanHelper and writes an interface containing its instance. Also, recursively creates
   * any BeanHelpers on its constrained properties. (Public for testing.)
   *
   * @param clazz class type
   * @param logger tree logger to use
   * @param context generator context
   * @return bean helper instance
   * @throws UnableToCompleteException if generation can not be done
   */
  public BeanHelper createHelper(final Class<?> clazz, final TreeLogger logger,
      final GeneratorContext context) throws UnableToCompleteException {
    final JClassType beanType = context.getTypeOracle().findType(clazz.getCanonicalName());
    return doCreateHelper(clazz, beanType, logger, context);
  }

  /**
   * Creates a BeanHelper and writes an interface containing its instance. Also, recursively creates
   * any BeanHelpers on its constrained properties.
   */
  BeanHelper createHelper(final JClassType pjtype, final TreeLogger plogger,
      final GeneratorContext pcontext) throws UnableToCompleteException {
    final JClassType erasedType = pjtype.getErasedType();
    try {
      final Class<?> clazz = Class.forName(erasedType.getQualifiedBinaryName());
      return doCreateHelper(clazz, erasedType, plogger, pcontext);
    } catch (final ClassNotFoundException e) {
      plogger.log(TreeLogger.ERROR, "Unable to create BeanHelper for " + erasedType, e);
      throw new UnableToCompleteException(); // NOPMD
    }
  }

  List<BeanHelper> getAllBeans() {
    return Util.sortMostSpecificFirst(cache.values(), BeanHelper.TO_CLAZZ);
  }

  BeanHelper getBean(final JClassType key) {
    return cache.get(key);
  }

  boolean isClassConstrained(final Class<?> clazz) {
    return serverSideValidator.getConstraintsForClass(clazz).isBeanConstrained();
  }

  private BeanHelper doCreateHelper(final Class<?> clazz, final JClassType beanType,
      final TreeLogger logger, final GeneratorContext context) throws UnableToCompleteException {
    BeanHelper helper = getBean(beanType);
    if (helper == null) {
      BeanDescriptor bean;
      try {
        bean = serverSideValidator.getConstraintsForClass(clazz);
      } catch (final ValidationException e) {
        logger.log(TreeLogger.ERROR, "Unable to create a validator for " + clazz.getCanonicalName()
            + " because " + e.getMessage(), e);
        throw new UnableToCompleteException(); // NOPMD
      }
      helper = new BeanHelper(beanType, clazz, bean);
      cache.put(helper.getJClass(), helper);

      writeInterface(context, logger, helper);

      // now recurse on all Cascaded elements
      for (final PropertyDescriptor p : bean.getConstrainedProperties()) {
        // TODO(idol) only bother creating objects for properties that have constrains in the groups
        // specified in @GwtValidation, but not others
        if (p.isCascaded()) {
          doCreateHelperForProp(p, helper, logger, context);
        }
      }
    }
    return helper;
  }

  /**
   * Creates the appropriate BeanHelper for a property on a bean.
   */
  private void doCreateHelperForProp(final PropertyDescriptor propertyDescriptor,
      final BeanHelper parent, final TreeLogger logger, final GeneratorContext context)
      throws UnableToCompleteException {
    final Class<?> elementClass = propertyDescriptor.getElementClass();
    if (GwtSpecificValidatorCreator.isIterableOrMap(elementClass)) {
      if (parent.hasField(propertyDescriptor)) {
        final JClassType type = parent.getAssociationType(propertyDescriptor, true);

        this.createHelper(type.getErasedType(), logger, context);
      }
      if (parent.hasGetter(propertyDescriptor)) {
        final JClassType type = parent.getAssociationType(propertyDescriptor, false);

        this.createHelper(type.getErasedType(), logger, context);
      }
    } else {
      if (serverSideValidator.getConstraintsForClass(elementClass).isBeanConstrained()) {
        this.createHelper(elementClass, logger, context);
      }
    }
  }

  /**
   * Write an Empty Interface implementing
   * {@link de.knightsoftnet.validators.client.impl.AbstractGwtSpecificValidator} with Generic
   * parameter of the bean type.
   */
  private void writeInterface(final GeneratorContext context, final TreeLogger logger,
      final BeanHelper bean) {
    final PrintWriter pw = context.tryCreate(logger, bean.getPackage(), bean.getValidatorName());
    if (pw != null) {
      final TreeLogger interfaceLogger = logger.branch(TreeLogger.TRACE,
          "Creating the interface for " + bean.getFullyQualifiedValidatorName());

      final ClassSourceFileComposerFactory factory =
          new ClassSourceFileComposerFactory(bean.getPackage(), bean.getValidatorName());
      factory.addImplementedInterface(
          GwtSpecificValidator.class.getCanonicalName() + " <" + bean.getTypeCanonicalName() + ">");
      factory.addImport(GWT.class.getCanonicalName());
      factory.makeInterface();
      final SourceWriter sw = factory.createSourceWriter(context, pw);

      // static MyValidator INSTANCE = GWT.create(MyValidator.class);
      sw.print("static ");
      sw.print(bean.getValidatorName());
      sw.print(" INSTANCE = GWT.create(");
      sw.print(bean.getValidatorName());
      sw.println(".class);");

      sw.commit(interfaceLogger);
      pw.close();
    }
  }
}
