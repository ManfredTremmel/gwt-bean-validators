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

import de.knightsoftnet.validators.client.impl.metadata.BeanMetadata;
import de.knightsoftnet.validators.client.impl.metadata.ValidationGroupsMetadata;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.metadata.ConstraintDescriptor;
import javax.validation.metadata.ConstructorDescriptor;
import javax.validation.metadata.MethodDescriptor;
import javax.validation.metadata.MethodType;
import javax.validation.metadata.PropertyDescriptor;

/**
 * Abstract BeanDescriptor for use by generated {@link GwtBeanDescriptor}.
 * <p>
 * Subclasses are expected to call setDescriptorMap from the constructor.
 * </p>
 *
 * @param <T> the bean Type
 */
public class GwtBeanDescriptorImpl<T> implements GwtBeanDescriptor<T> {

  /**
   * Builder for {@link GwtBeanDescriptor}s.
   *
   * @param <T> the bean Type
   */
  public static final class Builder<T> {

    private final Class<T> clazz;
    private final Map<String, PropertyDescriptorImpl> descriptorMap = new HashMap<>();
    private final Set<ConstraintDescriptorImpl<? extends Annotation>> constraints = new HashSet<>();
    private boolean isConstrained;
    private BeanMetadata beanMetadata;

    private Builder(final Class<T> clazz) {
      this.clazz = clazz;
    }

    public Builder<T> add(
        final ConstraintDescriptorImpl<? extends Annotation> constraintDescriptor) {
      this.constraints.add(constraintDescriptor);
      return this;
    }

    public GwtBeanDescriptorImpl<T> build() {
      return new GwtBeanDescriptorImpl<>(this.clazz, this.isConstrained, // NOPMD
          this.descriptorMap, this.beanMetadata, this.constraints);
    }

    public Builder<T> put(final String key, final PropertyDescriptorImpl value) {
      this.descriptorMap.put(key, value.shallowCopy());
      return this;
    }

    public Builder<T> setBeanMetadata(final BeanMetadata beanMetadata) {
      this.beanMetadata = beanMetadata;
      return this;
    }

    public Builder<T> setConstrained(final boolean isConstrained) {
      this.isConstrained = isConstrained;
      return this;
    }
  }

  public static <T> Builder<T> builder(final Class<T> clazz) {
    return new Builder<>(clazz); // NOPMD
  }

  private final Class<T> clazz;
  private final Set<ConstraintDescriptorImpl<?>> constraints = new HashSet<>();

  private final Map<String, PropertyDescriptorImpl> descriptorMap = new HashMap<>();
  private final boolean isConstrained;

  private final BeanMetadata beanMetadata;

  private ValidationGroupsMetadata validationGroupsMetadata;

  protected GwtBeanDescriptorImpl(final Class<T> clazz, final boolean isConstrained,
      final Map<String, PropertyDescriptorImpl> descriptorMap, final BeanMetadata beanMetadata,
      final Set<ConstraintDescriptorImpl<?>> constraints) {
    this.clazz = clazz;
    this.isConstrained = isConstrained;
    this.beanMetadata = beanMetadata;
    this.descriptorMap.putAll(descriptorMap);
    this.constraints.addAll(constraints);
  }

  @Override
  public ConstraintFinder findConstraints() {
    return new ConstraintFinderImpl(this.beanMetadata, this.validationGroupsMetadata,
        this.constraints);
  }

  @Override
  public Set<PropertyDescriptor> getConstrainedProperties() {
    final Collection<PropertyDescriptorImpl> props = this.descriptorMap.values();
    for (final PropertyDescriptorImpl prop : props) {
      prop.setValidationGroupsMetadata(this.validationGroupsMetadata);
    }
    return new HashSet<>(props);
  }

  @Override
  public Set<ConstraintDescriptor<?>> getConstraintDescriptors() {
    return this.findConstraints().getConstraintDescriptors();
  }

  @Override
  public PropertyDescriptor getConstraintsForProperty(final String propertyName) {
    final PropertyDescriptorImpl propDesc = this.descriptorMap.get(propertyName);
    if (propDesc != null) {
      propDesc.setValidationGroupsMetadata(this.validationGroupsMetadata);
    }
    return propDesc;
  }

  @Override
  public Class<?> getElementClass() {
    return this.clazz;
  }


  @Override
  public boolean hasConstraints() {
    return !this.constraints.isEmpty();
  }

  @Override
  public boolean isBeanConstrained() {
    return this.isConstrained;
  }

  @Override
  public void setValidationGroupsMetadata(final ValidationGroupsMetadata validationGroupsMetadata) {
    // TODO(idol) Find some way to pass this via the constructor rather than after creation
    this.validationGroupsMetadata = validationGroupsMetadata;
  }

  @Override
  public MethodDescriptor getConstraintsForMethod(final String pmethodName,
      final Class<?>... pparameterTypes) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Set<MethodDescriptor> getConstrainedMethods(final MethodType pmethodType,
      final MethodType... pmethodTypes) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ConstructorDescriptor getConstraintsForConstructor(final Class<?>... pparameterTypes) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Set<ConstructorDescriptor> getConstrainedConstructors() {
    // TODO Auto-generated method stub
    return null;
  }
}
