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

import de.knightsoftnet.validators.client.AbstractGwtValidatorFactory;

import javax.validation.ClockProvider;
import javax.validation.ConstraintValidatorFactory;
import javax.validation.MessageInterpolator;
import javax.validation.ParameterNameProvider;
import javax.validation.TraversableResolver;
import javax.validation.Validator;
import javax.validation.ValidatorContext;
import javax.validation.valueextraction.ValueExtractor;

/**
 * GWT {@link ValidatorContext}.
 */
public class GwtValidatorContext implements ValidatorContext {

  private final AbstractGwtValidatorFactory validatorFactory;

  private final ConstraintValidatorFactory factoryConstraintValidatorfactory;
  private final MessageInterpolator factoryMessageInterpolator;
  private final TraversableResolver factoryTraversableResolver;
  private final ParameterNameProvider factoryParameterNameProvider;

  private ConstraintValidatorFactory constraintValidatorfactoryEntry = null;
  private MessageInterpolator messageInterpolatorEntry = null;
  private TraversableResolver traversableResolverEntry = null;
  private ParameterNameProvider parameterNameProviderEntry = null;

  /**
   * constructor.
   *
   * @param validatorFactory validator factory
   */
  public GwtValidatorContext(final AbstractGwtValidatorFactory validatorFactory) {
    this.validatorFactory = validatorFactory;

    factoryConstraintValidatorfactory = validatorFactory.getConstraintValidatorFactory();
    constraintValidatorfactoryEntry = validatorFactory.getConstraintValidatorFactory();

    factoryMessageInterpolator = validatorFactory.getMessageInterpolator();
    messageInterpolatorEntry = validatorFactory.getMessageInterpolator();

    factoryTraversableResolver = validatorFactory.getTraversableResolver();
    traversableResolverEntry = validatorFactory.getTraversableResolver();

    factoryParameterNameProvider = validatorFactory.getParameterNameProvider();
    parameterNameProviderEntry = validatorFactory.getParameterNameProvider();
  }

  @Override
  public ValidatorContext constraintValidatorFactory(
      final ConstraintValidatorFactory constraintValidatorfactory) {
    if (constraintValidatorfactory == null) {
      constraintValidatorfactoryEntry = factoryConstraintValidatorfactory;
    } else {
      constraintValidatorfactoryEntry = constraintValidatorfactory;
    }
    return this;
  }

  @Override
  public Validator getValidator() {
    final AbstractGwtValidator validator = validatorFactory.createValidator();
    validator.init(constraintValidatorfactoryEntry, messageInterpolatorEntry,
        traversableResolverEntry, parameterNameProviderEntry);
    return validator;
  }

  @Override
  public ValidatorContext messageInterpolator(final MessageInterpolator messageInterpolator) {
    if (messageInterpolator == null) {
      messageInterpolatorEntry = factoryMessageInterpolator;
    } else {
      messageInterpolatorEntry = messageInterpolator;
    }
    return this;
  }

  @Override
  public ValidatorContext traversableResolver(final TraversableResolver traversableResolver) {
    if (traversableResolver == null) {
      traversableResolverEntry = factoryTraversableResolver;
    } else {
      traversableResolverEntry = traversableResolver;
    }
    return this;
  }

  @Override
  public ValidatorContext parameterNameProvider(
      final ParameterNameProvider pparameterNameProvider) {
    if (pparameterNameProvider == null) {
      parameterNameProviderEntry = factoryParameterNameProvider;
    } else {
      parameterNameProviderEntry = pparameterNameProvider;
    }
    return this;
  }

  @Override
  public ValidatorContext clockProvider(final ClockProvider clockProvider) {
    throw new UnsupportedOperationException(
        "GWT Validation does not support clockProvider(ClockProvider).");
  }

  @Override
  public ValidatorContext addValueExtractor(final ValueExtractor<?> extractor) {
    throw new UnsupportedOperationException("GWT Validation does not support addValueExtractor().");
  }
}
