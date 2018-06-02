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

import de.knightsoftnet.validators.client.impl.metadata.ValidationGroupsMetadata;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintValidatorFactory;
import javax.validation.ConstraintViolation;
import javax.validation.MessageInterpolator;
import javax.validation.ParameterNameProvider;
import javax.validation.TraversableResolver;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;

/**
 * Base methods for implementing {@link Validator} in GWT.
 * <p>
 * All methods that do not need to be generated go here.
 * </p>
 */
public abstract class AbstractGwtValidator implements Validator {

  private final Set<Class<?>> validGroups;
  private final ValidationGroupsMetadata validationGroupsMetadata;
  private ConstraintValidatorFactory contraintValidatorFactory;
  private MessageInterpolator messageInterpolator;
  private TraversableResolver traversableResolver;
  private ParameterNameProvider parameterNameProvider;

  /**
   * Creates a validator initialized with the default group inheritance map.
   *
   * @see #AbstractGwtValidator(ValidationGroupsMetadata)
   */
  public AbstractGwtValidator() {
    this(ValidationGroupsMetadata.builder().build());
  }

  /**
   * constructor.
   *
   * @param validationGroupsMetadata Validation group metadata.
   */
  public AbstractGwtValidator(final ValidationGroupsMetadata validationGroupsMetadata) {
    validGroups = validationGroupsMetadata.getAllGroupsAndSequences();
    this.validationGroupsMetadata = validationGroupsMetadata;
  }

  public ValidationGroupsMetadata getValidationGroupsMetadata() {
    return validationGroupsMetadata;
  }

  /**
   * initialize values.
   *
   * @param factory constraint validator factory to set
   * @param messageInterpolator message interpolator to set
   * @param traversableResolver traversable resolver to set
   * @param pparameterNameProvider parameter resolver to set
   */
  public void init(final ConstraintValidatorFactory factory,
      final MessageInterpolator messageInterpolator, final TraversableResolver traversableResolver,
      final ParameterNameProvider pparameterNameProvider) {
    contraintValidatorFactory = factory;
    this.messageInterpolator = messageInterpolator;
    this.traversableResolver = traversableResolver;
    parameterNameProvider = pparameterNameProvider;
  }

  @Override
  public <T> T unwrap(final Class<T> type) {
    throw new ValidationException();
  }

  public abstract <T> Set<ConstraintViolation<T>> validate(GwtValidationContext<T> context,
      Object object, Class<?>... groups) throws ValidationException;

  protected void checkGroups(final Class<?>... groups) {
    if (!validGroups.containsAll(Arrays.asList(groups))) {
      final HashSet<Class<?>> unknown = new HashSet<>();
      unknown.addAll(Arrays.asList(groups));
      unknown.removeAll(validGroups);
      throw new IllegalArgumentException(getClass() + " only processes the following groups "
          + validGroups + ". " + "The following groups could not be processed " + unknown);
    }
  }

  protected void checkNotNull(final Object object, final String name)
      throws IllegalArgumentException {
    if (object == null) {
      throw new IllegalArgumentException(name + " can not be null.");
    }
  }

  protected ConstraintValidatorFactory getConstraintValidatorFactory() {
    return contraintValidatorFactory;
  }

  public MessageInterpolator getMessageInterpolator() {
    return messageInterpolator;
  }

  public TraversableResolver getTraversableResolver() {
    return traversableResolver;
  }

  protected ParameterNameProvider getParameterNameProvider() {
    return parameterNameProvider;
  }

  @Override
  public ExecutableValidator forExecutables() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * reflection replacement to get property of an object.
   *
   * @param object object to get property from
   * @param propertyName property name
   * @return object value
   * @throws NoSuchMethodException if there is no getter for this property name
   * @throws ReflectiveOperationException if no reflection for this class is available
   */
  public abstract Object getProperty(final Object object, final String propertyName)
      throws NoSuchMethodException, ReflectiveOperationException;
}
