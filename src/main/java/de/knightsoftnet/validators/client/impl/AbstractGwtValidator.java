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
   * @param validationGroupsMetadata Validation group metadata.
   */
  public AbstractGwtValidator(final ValidationGroupsMetadata validationGroupsMetadata) {
    this.validGroups = validationGroupsMetadata.getAllGroupsAndSequences();
    this.validationGroupsMetadata = validationGroupsMetadata;
  }

  public ValidationGroupsMetadata getValidationGroupsMetadata() {
    return this.validationGroupsMetadata;
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
    this.contraintValidatorFactory = factory;
    this.messageInterpolator = messageInterpolator;
    this.traversableResolver = traversableResolver;
    this.parameterNameProvider = pparameterNameProvider;
  }

  @Override
  public <T> T unwrap(final Class<T> type) {
    throw new ValidationException();
  }

  public abstract <T> Set<ConstraintViolation<T>> validate(GwtValidationContext<T> context,
      Object object, Class<?>... groups) throws ValidationException;

  protected void checkGroups(final Class<?>... groups) {
    if (!this.validGroups.containsAll(Arrays.asList(groups))) {
      final HashSet<Class<?>> unknown = new HashSet<Class<?>>();
      unknown.addAll(Arrays.asList(groups));
      unknown.removeAll(this.validGroups);
      throw new IllegalArgumentException(this.getClass() + " only processes the following groups "
          + this.validGroups + ". " + "The following groups could not be processed " + unknown);
    }
  }

  protected void checkNotNull(final Object object, final String name)
      throws IllegalArgumentException {
    if (object == null) {
      throw new IllegalArgumentException(name + " can not be null.");
    }
  }

  protected ConstraintValidatorFactory getConstraintValidatorFactory() {
    return this.contraintValidatorFactory;
  }

  protected MessageInterpolator getMessageInterpolator() {
    return this.messageInterpolator;
  }

  protected TraversableResolver getTraversableResolver() {
    return this.traversableResolver;
  }

  protected ParameterNameProvider getParameterNameProvider() {
    return this.parameterNameProvider;
  }

  @Override
  public ExecutableValidator forExecutables() {
    // TODO Auto-generated method stub
    return null;
  }
}
