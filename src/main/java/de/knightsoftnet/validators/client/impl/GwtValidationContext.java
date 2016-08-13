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

import org.hibernate.validator.internal.engine.path.NodeImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import javax.validation.MessageInterpolator;
import javax.validation.TraversableResolver;
import javax.validation.metadata.BeanDescriptor;
import javax.validation.metadata.ConstraintDescriptor;

/**
 * Context for a {@link de.knightsoftnet.validators.client.GwtValidation}.
 *
 * <p>
 * NOTE: This class is not thread safe.
 * </p>
 *
 * @param <T> the type of the root bean.
 *
 */
public class GwtValidationContext<T> {

  private final BeanDescriptor beanDescriptor;
  private PathImpl path = PathImpl.createRootPath();
  private final Class<T> rootBeanClass;
  private final T rootBean;
  private final MessageInterpolator messageInterpolator;
  private final TraversableResolver traversableResolver;
  private final AbstractGwtValidator validator;

  /**
   * The set of validated object.
   * <p>
   * This set is shared with and updated by children contexts created by {@link #append(String)},
   * {@link #appendIndex(String, int)} and {@link #appendKey(String, Object)}.
   * </p>
   */
  private final Set<Object> validatedObjects;

  public GwtValidationContext(final Class<T> rootBeanClass, final T rootBean,
      final BeanDescriptor beanDescriptor, final MessageInterpolator messageInterpolator,
      final TraversableResolver traversableResolver, final AbstractGwtValidator validator) {
    this(rootBeanClass, rootBean, beanDescriptor, messageInterpolator, traversableResolver,
        validator, new HashSet<>());
  }

  private GwtValidationContext(final Class<T> rootBeanClass, final T rootBean,
      final BeanDescriptor beanDescriptor, final MessageInterpolator messageInterpolator,
      final TraversableResolver traversableResolver, final AbstractGwtValidator validator,
      final Set<Object> validatedObjects) {
    this.rootBeanClass = rootBeanClass;
    this.rootBean = rootBean;
    this.beanDescriptor = beanDescriptor;
    this.messageInterpolator = messageInterpolator;
    this.traversableResolver = traversableResolver;
    this.validator = validator;
    this.validatedObjects = new HashSet<>(validatedObjects);
  }

  public void addValidatedObject(final Object pobject) {
    this.validatedObjects.add(pobject);
  }

  public boolean alreadyValidated(final Object pobject) {
    return this.validatedObjects.contains(pobject);
  }

  /**
   * Append a node named name to the path..
   *
   * @return the new GwtValidationContext.
   */
  public GwtValidationContext<T> append(final String name) {
    final GwtValidationContext<T> temp = new GwtValidationContext<>(this.rootBeanClass,
        this.rootBean, this.beanDescriptor, this.messageInterpolator, this.traversableResolver,
        this.validator, this.validatedObjects);
    temp.path = PathImpl.createCopy(this.path);
    temp.path.addPropertyNode(name);
    return temp;
  }

  /**
   * Append an indexed node to the path.
   *
   * @return the new GwtValidationContext.
   */
  public GwtValidationContext<T> appendIndex(final String name, final int index) {
    final GwtValidationContext<T> temp = new GwtValidationContext<>(this.rootBeanClass,
        this.rootBean, this.beanDescriptor, this.messageInterpolator, this.traversableResolver,
        this.validator, this.validatedObjects);
    temp.path = PathImpl.createCopy(this.path);
    temp.path.addParameterNode(name, index);
    return temp;
  }

  /**
   * Append an iterable node to the path.
   *
   * @return the new GwtValidationContext.
   */
  public GwtValidationContext<T> appendIterable(final String name) {
    final GwtValidationContext<T> temp = new GwtValidationContext<>(this.rootBeanClass,
        this.rootBean, this.beanDescriptor, this.messageInterpolator, this.traversableResolver,
        this.validator, this.validatedObjects);
    temp.path = PathImpl.createCopy(this.path);
    temp.path.addPropertyNode(name);
    temp.path.makeLeafNodeIterable();
    return temp;
  }

  /**
   * Append a keyed node to the path.
   *
   * @return the new GwtValidationContext.
   */
  public GwtValidationContext<T> appendKey(final String name, final Object key) {
    final GwtValidationContext<T> temp = new GwtValidationContext<>(this.rootBeanClass,
        this.rootBean, this.beanDescriptor, this.messageInterpolator, this.traversableResolver,
        this.validator, this.validatedObjects);
    temp.path = PathImpl.createCopy(this.path);
    temp.path.addPropertyNode(name);
    NodeImpl.setMapKey(temp.path.getLeafNode(), key);
    return temp;
  }

  /**
   * create constraint validator context.
   *
   * @param descriptor constraint descriptor
   * @return constraint validator context implementation
   */
  public <A extends Annotation, V> ConstraintValidatorContextImpl<A, V> //
      createConstraintValidatorContext(final ConstraintDescriptor<A> descriptor) {
    return new ConstraintValidatorContextImpl<>(PathImpl.createCopy(this.path), descriptor);
  }

  public MessageInterpolator getMessageInterpolator() {
    return this.messageInterpolator;
  }

  public PathImpl getPath() {
    return this.path;
  }

  public T getRootBean() {
    return this.rootBean;
  }

  public Class<T> getRootBeanClass() {
    return this.rootBeanClass;
  }

  public TraversableResolver getTraversableResolver() {
    return this.traversableResolver;
  }

  public AbstractGwtValidator getValidator() {
    return this.validator;
  }
}
