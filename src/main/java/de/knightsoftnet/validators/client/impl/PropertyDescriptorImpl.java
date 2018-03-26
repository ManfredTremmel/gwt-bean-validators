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

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.validation.metadata.ConstraintDescriptor;
import javax.validation.metadata.ContainerElementTypeDescriptor;
import javax.validation.metadata.GroupConversionDescriptor;
import javax.validation.metadata.PropertyDescriptor;

/**
 * Describes a constrained bean property.
 */
public class PropertyDescriptorImpl implements PropertyDescriptor {

  private final boolean cascaded;
  private final Set<ConstraintDescriptorImpl<?>> descriptors;
  private final Class<?> elementClass;
  private final String name;
  private ValidationGroupsMetadata validationGroupsMetadata;
  private final BeanMetadata parentBeanMetadata;
  private final Set<GroupConversionDescriptor> groupConversions;

  public PropertyDescriptorImpl(final String name, final Class<?> elementClass,
      final boolean cascaded, final BeanMetadata parentBeanMetadata,
      final ConstraintDescriptorImpl<?>... descriptors) {
    this(name, elementClass, cascaded, parentBeanMetadata, null, descriptors);
  }

  /**
   * constructor.
   *
   * @param name name of the property
   * @param elementClass class type
   * @param cascaded true if should be cascaded
   * @param parentBeanMetadata meta data of parent bean
   * @param validationGroupsMetadata meta data of validation groups
   * @param descriptors array of constraint discriptors
   */
  public PropertyDescriptorImpl(final String name, final Class<?> elementClass,
      final boolean cascaded, final BeanMetadata parentBeanMetadata,
      final ValidationGroupsMetadata validationGroupsMetadata,
      final ConstraintDescriptorImpl<?>... descriptors) {
    super();

    this.elementClass = elementClass;
    this.cascaded = cascaded;
    this.name = name;
    this.validationGroupsMetadata = validationGroupsMetadata;
    this.parentBeanMetadata = parentBeanMetadata;
    this.descriptors = new HashSet<>(Arrays.asList(descriptors));
    this.groupConversions = Collections.emptySet();
  }

  @Override
  public ConstraintFinder findConstraints() {
    return new ConstraintFinderImpl(this.parentBeanMetadata, this.validationGroupsMetadata,
        this.descriptors);
  }

  @Override
  public Set<ConstraintDescriptor<?>> getConstraintDescriptors() {
    return this.findConstraints().getConstraintDescriptors();
  }

  @Override
  public Class<?> getElementClass() {
    return this.elementClass;
  }

  @Override
  public String getPropertyName() {
    return this.name;
  }

  @Override
  public boolean hasConstraints() {
    return !this.descriptors.isEmpty();
  }

  @Override
  public boolean isCascaded() {
    return this.cascaded;
  }

  @Override
  public Set<GroupConversionDescriptor> getGroupConversions() {
    return this.groupConversions;
  }

  public void setValidationGroupsMetadata(final ValidationGroupsMetadata validationGroupsMetadata) {
    // TODO(idol) Find some way to pass this via the constructor rather than after creation
    this.validationGroupsMetadata = validationGroupsMetadata;
  }

  /**
   * create a copy of this instance and return it.
   *
   * @return copy of the class
   */
  public PropertyDescriptorImpl shallowCopy() {
    final ConstraintDescriptorImpl<?>[] desc =
        new ConstraintDescriptorImpl<?>[this.descriptors.size()];
    this.descriptors.toArray(desc);
    return new PropertyDescriptorImpl( //
        this.name, //
        this.elementClass, //
        this.cascaded, //
        this.parentBeanMetadata, //
        this.validationGroupsMetadata, //
        desc);
  }

  @Override
  public Set<ContainerElementTypeDescriptor> getConstrainedContainerElementTypes() {
    throw new UnsupportedOperationException(
        "GWT Validation does not support getConstrainedContainerElementTypes().");
  }
}
