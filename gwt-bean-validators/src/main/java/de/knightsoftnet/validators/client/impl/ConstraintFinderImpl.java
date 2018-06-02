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

package de.knightsoftnet.validators.client.impl;

import de.knightsoftnet.validators.client.impl.metadata.BeanMetadata;
import de.knightsoftnet.validators.client.impl.metadata.ValidationGroupsMetadata;

import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.groups.Default;
import javax.validation.metadata.ConstraintDescriptor;
import javax.validation.metadata.ElementDescriptor.ConstraintFinder;
import javax.validation.metadata.Scope;

/**
 * Finds constraints declared on an element using specified criteria.
 */
public final class ConstraintFinderImpl implements ConstraintFinder {
  private final Set<ConstraintDescriptorImpl<?>> constraintDescriptors;
  private final ValidationGroupsMetadata validationGroupsMetadata;
  private List<Class<?>> groups;
  private final Set<ConstraintOrigin> definedInSet;
  private final Set<ElementType> elementTypes;
  private final BeanMetadata beanMetadata;

  /**
   * constructor.
   *
   * @param beanMetadata bean meta data to set
   * @param validationGroupsMetadata validation groups meta data
   * @param constraintDescriptors constraint descriptors
   */
  public ConstraintFinderImpl(final BeanMetadata beanMetadata,
      final ValidationGroupsMetadata validationGroupsMetadata,
      final Set<ConstraintDescriptorImpl<?>> constraintDescriptors) {
    this.validationGroupsMetadata = validationGroupsMetadata;
    this.constraintDescriptors = constraintDescriptors;
    this.beanMetadata = beanMetadata;
    elementTypes = new HashSet<>();
    elementTypes.add(ElementType.TYPE);
    elementTypes.add(ElementType.METHOD);
    elementTypes.add(ElementType.FIELD);
    definedInSet = new HashSet<>();
    definedInSet.add(ConstraintOrigin.DEFINED_LOCALLY);
    definedInSet.add(ConstraintOrigin.DEFINED_IN_HIERARCHY);
    groups = Collections.emptyList();
  }

  @Override
  public ConstraintFinder declaredOn(final ElementType... types) {
    elementTypes.clear();
    elementTypes.addAll(Arrays.asList(types));
    return this;
  }

  @Override
  public Set<ConstraintDescriptor<?>> getConstraintDescriptors() {
    if (validationGroupsMetadata == null) {
      // sanity check - this could be null if the caller does not set group metadata first
      throw new IllegalStateException("ConstraintFinderImpl not initialized properly. A "
          + "ValidationGroupsMetadata object is required by GWT to properly find all constraint "
          + "descriptors.");
    }
    final Set<ConstraintDescriptor<?>> matchingDescriptors = new HashSet<>();
    findMatchingDescriptors(matchingDescriptors);
    return Collections.unmodifiableSet(matchingDescriptors);
  }

  @Override
  public boolean hasConstraints() {
    return !getConstraintDescriptors().isEmpty();
  }

  @Override
  public ConstraintFinder lookingAt(final Scope scope) {
    if (scope.equals(Scope.LOCAL_ELEMENT)) {
      definedInSet.remove(ConstraintOrigin.DEFINED_IN_HIERARCHY);
    }
    return this;
  }

  @Override
  public ConstraintFinder unorderedAndMatchingGroups(final Class<?>... groups) {
    this.groups = new ArrayList<>();
    for (final Class<?> clazz : groups) {
      if (Default.class.equals(clazz) && beanMetadata.defaultGroupSequenceIsRedefined()) {
        this.groups.addAll(beanMetadata.getDefaultGroupSequence());
      } else {
        this.groups.add(clazz);
      }
    }
    return this;
  }

  private void addMatchingDescriptorsForGroup(final Class<?> group,
      final Set<ConstraintDescriptor<?>> matchingDescriptors) {
    matchingDescriptors.addAll(constraintDescriptors.stream()
        .filter(descriptor -> definedInSet.contains(descriptor.getDefinedOn())
            && elementTypes.contains(descriptor.getElementType())
            && descriptor.getGroups().contains(group))
        .collect(Collectors.toSet()));
  }

  private void findMatchingDescriptors(final Set<ConstraintDescriptor<?>> matchingDescriptors) {
    if (groups.isEmpty()) {
      for (final ConstraintDescriptorImpl<?> descriptor : constraintDescriptors) {
        if (definedInSet.contains(descriptor.getDefinedOn())
            && elementTypes.contains(descriptor.getElementType())) {
          matchingDescriptors.add(descriptor);
        }
      }
    } else {
      final GroupChain groupChain =
          new GroupChainGenerator(validationGroupsMetadata).getGroupChainFor(groups);
      final Iterator<Group> groupIterator = groupChain.getGroupIterator();
      while (groupIterator.hasNext()) {
        final Group g = groupIterator.next();
        addMatchingDescriptorsForGroup(g.getGroup(), matchingDescriptors);
      }
    }
  }

}
