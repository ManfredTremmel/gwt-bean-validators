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
import de.knightsoftnet.validators.client.impl.metadata.MessageAndPath;
import de.knightsoftnet.validators.client.impl.metadata.ValidationGroupsMetadata;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintViolation;
import javax.validation.MessageInterpolator;
import javax.validation.ValidationException;
import javax.validation.groups.Default;

/**
 * Base methods for implementing a {@link GwtSpecificValidator}.
 * <p>
 * All methods that do not need to be generated go here.
 * </p>
 *
 * @param <G> the type object to validate
 */
public abstract class AbstractGwtSpecificValidator<G> implements GwtSpecificValidator<G> {

  /**
   * Builds attributes one at a time.
   * <p>
   * Used to create a attribute map for annotations.
   * </p>
   */
  public static final class AttributeBuilder {
    private final HashMap<String, Object> tempMap = new HashMap<>();

    private AttributeBuilder() {
      super();
    }

    public Map<String, Object> build() {
      return Collections.unmodifiableMap(this.tempMap);
    }

    public AttributeBuilder put(final String key, final Object value) {
      this.tempMap.put(key, value);
      return this;
    }
  }

  public static AttributeBuilder attributeBuilder() {
    return new AttributeBuilder(); // NOPMD
  }

  protected static Class<?>[] groupsToClasses(final Group... groups) {
    final int numGroups = groups.length;
    final Class<?>[] array = new Class<?>[numGroups];
    for (int i = 0; i < numGroups; i++) {
      array[i] = groups[i].getGroup();
    }
    return array;
  }

  @Override
  public <T> Set<ConstraintViolation<T>> validate(final GwtValidationContext<T> context,
      final G object, final Class<?>... groups) {
    context.addValidatedObject(object);
    try {
      final GroupValidator classGroupValidator = new ClassGroupValidator(object);
      final GroupChain groupChain = this.createGroupChainFromGroups(context, groups);
      final BeanMetadata beanMetadata = this.getBeanMetadata();
      final List<Class<?>> defaultGroupSeq = beanMetadata.getDefaultGroupSequence();
      if (beanMetadata.defaultGroupSequenceIsRedefined()) {
        // only need to check this on class-level validation
        groupChain.checkDefaultGroupSequenceIsExpandable(defaultGroupSeq);
      }
      return this.validateGroups(context, classGroupValidator, groupChain);
    } catch (final IllegalArgumentException e) { // NOPMD
      throw e;
    } catch (final ValidationException e) { // NOPMD
      throw e;
    } catch (final Exception e) {
      throw new ValidationException("Error validating " + object.getClass(), e);
    }
  }

  /**
   * Perform the actual validation of a single {@link ConstraintValidator}.
   * <p>
   * As a side effect {@link ConstraintViolation}s may be added to {@code violations}.
   * </p>
   *
   * @return true if there was any constraint violations
   */
  protected <A extends Annotation, T, V> boolean validate(final GwtValidationContext<T> context,
      final Set<ConstraintViolation<T>> violations, final G object, final V value,
      final ConstraintValidator<A, ? super V> validator,
      final ConstraintDescriptorImpl<A> constraintDescriptor, final Class<?>... groups) {
    validator.initialize(constraintDescriptor.getAnnotation());
    final ConstraintValidatorContextImpl<A, V> constraintValidatorContext =
        context.createConstraintValidatorContext(constraintDescriptor);

    final List<Class<?>> groupsList = Arrays.asList(groups);
    final ValidationGroupsMetadata validationGroupsMetadata =
        context.getValidator().getValidationGroupsMetadata();
    final Set<Class<?>> constraintGroups = constraintDescriptor.getGroups();

    // check groups requested are in the set of constraint groups (including the implicit group)
    if (!this.containsAny(groupsList, constraintGroups)
        && !groupsList.contains(this.getConstraints(validationGroupsMetadata).getElementClass())) {
      return false;
    }

    if (!validator.isValid(value, constraintValidatorContext)) {
      this.addViolations(//
          context, //
          violations, //
          object, //
          value, //
          constraintDescriptor, //
          constraintValidatorContext);
      return true;
    }
    return false;
  }

  @Override
  public <T> Set<ConstraintViolation<T>> validateProperty(final GwtValidationContext<T> context,
      final G object, final String propertyName, final Class<?>... groups)
      throws ValidationException {
    try {
      final GroupValidator propertyGroupValidator =
          new PropertyGroupValidator(object, propertyName);
      final GroupChain groupChain = this.createGroupChainFromGroups(context, groups);
      return this.validateGroups(context, propertyGroupValidator, groupChain);
    } catch (final IllegalArgumentException e) { // NOPMD
      throw e;
    } catch (final ValidationException e) { // NOPMD
      throw e;
    } catch (final Exception e) {
      throw new ValidationException(
          "Error validating property " + propertyName + " of " + object.getClass(), e);
    }
  }

  @Override
  public <T> Set<ConstraintViolation<T>> validateValue(final GwtValidationContext<T> context,
      final Class<G> beanType, final String propertyName, final Object value,
      final Class<?>... groups) throws ValidationException {
    try {
      final GroupValidator valueGroupValidator =
          new ValueGroupValidator(beanType, propertyName, value);
      final GroupChain groupChain = this.createGroupChainFromGroups(context, groups);
      return this.validateGroups(context, valueGroupValidator, groupChain);
    } catch (final IllegalArgumentException e) { // NOPMD
      throw e;
    } catch (final ValidationException e) { // NOPMD
      throw e;
    } catch (final Exception e) {
      throw new ValidationException(
          "Error validating property " + propertyName + " with value " + value + " of " + beanType,
          e);
    }
  }

  protected List<Class<?>> addDefaultGroupWhenEmpty(final List<Class<?>> pgroups) {
    List<Class<?>> groups = pgroups;
    if (groups.isEmpty()) {
      groups = new ArrayList<>();
      groups.add(Default.class);
    }
    return groups;
  }

  protected <V, T, A extends Annotation> void addSingleViolation(
      final GwtValidationContext<T> context, final Set<ConstraintViolation<T>> violations,
      final G object, final V value, final ConstraintDescriptorImpl<A> constraintDescriptor) {
    final ConstraintValidatorContextImpl<A, V> constraintValidatorContext =
        context.createConstraintValidatorContext(constraintDescriptor);
    this.addViolations(context, violations, object, value, constraintDescriptor,
        constraintValidatorContext);
  }

  private <V, T, A extends Annotation> void addViolations(final GwtValidationContext<T> context,
      final Set<ConstraintViolation<T>> violations, final G object, final V value,
      final ConstraintDescriptorImpl<A> constraintDescriptor,
      final ConstraintValidatorContextImpl<A, V> constraintValidatorContext) {
    final Set<MessageAndPath> mps = constraintValidatorContext.getMessageAndPaths();
    for (final MessageAndPath messageAndPath : mps) {
      final ConstraintViolation<T> violation = this.createConstraintViolation(//
          context, //
          object, //
          value, //
          constraintDescriptor, //
          messageAndPath);
      violations.add(violation);
    }
  }

  private <T> boolean containsAny(final Collection<T> left, final Collection<T> right) {
    for (final T t : left) {
      if (right.contains(t)) {
        return true;
      }
    }
    return false;
  }

  private <T, V, A extends Annotation> ConstraintViolation<T> createConstraintViolation(
      final GwtValidationContext<T> context, final G object, final V value,
      final ConstraintDescriptorImpl<A> constraintDescriptor, final MessageAndPath messageAndPath) {
    final MessageInterpolator messageInterpolator = context.getMessageInterpolator();
    final de.knightsoftnet.validators.client.impl.MessageInterpolatorContextImpl messageContext =
        new MessageInterpolatorContextImpl(constraintDescriptor, value);
    final String message =
        messageInterpolator.interpolate(messageAndPath.getMessage(), messageContext);
    return ConstraintViolationImpl.<T>builder() //
        .setConstraintDescriptor(constraintDescriptor) //
        .setInvalidValue(value) //
        .setLeafBean(object) //
        .setMessage(message) //
        .setMessageTemplate(messageAndPath.getMessage()) //
        .setPropertyPath(messageAndPath.getPath()) //
        .setRootBean(context.getRootBean()) //
        .setRootBeanClass(context.getRootBeanClass()) //
        .setElementType(constraintDescriptor.getElementType()) //
        .build();
  }

  private <T> GroupChain createGroupChainFromGroups(final GwtValidationContext<T> context,
      final Class<?>... groups) {
    final List<Class<?>> groupsList = this.addDefaultGroupWhenEmpty(Arrays.asList(groups));
    final ValidationGroupsMetadata validationGroupsMetadata =
        context.getValidator().getValidationGroupsMetadata();
    return new GroupChainGenerator(validationGroupsMetadata).getGroupChainFor(groupsList);
  }

  /**
   * Performs the top-level validation using a helper {@link GroupValidator}. This takes group
   * sequencing and Default group overriding into account.
   */
  private <T> Set<ConstraintViolation<T>> validateGroups(final GwtValidationContext<T> context,
      final GroupValidator groupValidator, final GroupChain groupChain) {

    final Set<ConstraintViolation<T>> violations = new HashSet<>();

    final Collection<Group> allGroups = groupChain.getAllGroups();
    final Group[] allGroupsArray = allGroups.toArray(new Group[allGroups.size()]);
    groupValidator.validateGroups(context, violations, allGroupsArray);

    // handle sequences
    final Iterator<List<Group>> sequenceIterator = groupChain.getSequenceIterator();
    while (sequenceIterator.hasNext()) {
      final List<Group> sequence = sequenceIterator.next();
      for (final Group group : sequence) {
        final int numberOfViolations = violations.size();
        groupValidator.validateGroups(context, violations, group);
        if (violations.size() > numberOfViolations) {
          // stop processing when an error occurs
          break;
        }
      }
    }
    return violations;
  }

  private class ClassGroupValidator implements GroupValidator {
    private final G object;

    public ClassGroupValidator(final G object) {
      this.object = object;
    }

    @Override
    public <T> void validateGroups(final GwtValidationContext<T> context,
        final Set<ConstraintViolation<T>> violations, final Group... groups) {
      AbstractGwtSpecificValidator.this.expandDefaultAndValidateClassGroups(context, this.object,
          violations, groups);
    }
  }

  private class PropertyGroupValidator implements GroupValidator {
    private final G object;
    private final String propertyName;

    public PropertyGroupValidator(final G object, final String propertyName) {
      this.object = object;
      this.propertyName = propertyName;
    }

    @Override
    public <T> void validateGroups(final GwtValidationContext<T> context,
        final Set<ConstraintViolation<T>> violations, final Group... groups) {
      AbstractGwtSpecificValidator.this.expandDefaultAndValidatePropertyGroups(context, this.object,
          this.propertyName, violations, groups);
    }
  }

  private class ValueGroupValidator implements GroupValidator {
    private final Class<G> beanType;
    private final String propertyName;
    private final Object value;

    public ValueGroupValidator(final Class<G> beanType, final String propertyName,
        final Object value) {
      this.beanType = beanType;
      this.propertyName = propertyName;
      this.value = value;
    }

    @Override
    public <T> void validateGroups(final GwtValidationContext<T> context,
        final Set<ConstraintViolation<T>> violations, final Group... groups) {
      AbstractGwtSpecificValidator.this.expandDefaultAndValidateValueGroups(context, this.beanType,
          this.propertyName, this.value, violations, //
          groups);
    }
  }
}
