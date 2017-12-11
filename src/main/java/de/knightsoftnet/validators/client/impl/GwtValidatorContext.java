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

    this.factoryConstraintValidatorfactory = validatorFactory.getConstraintValidatorFactory();
    this.constraintValidatorfactoryEntry = validatorFactory.getConstraintValidatorFactory();

    this.factoryMessageInterpolator = validatorFactory.getMessageInterpolator();
    this.messageInterpolatorEntry = validatorFactory.getMessageInterpolator();

    this.factoryTraversableResolver = validatorFactory.getTraversableResolver();
    this.traversableResolverEntry = validatorFactory.getTraversableResolver();

    this.factoryParameterNameProvider = validatorFactory.getParameterNameProvider();
    this.parameterNameProviderEntry = validatorFactory.getParameterNameProvider();
  }

  @Override
  public ValidatorContext constraintValidatorFactory(
      final ConstraintValidatorFactory constraintValidatorfactory) {
    if (constraintValidatorfactory == null) {
      this.constraintValidatorfactoryEntry = this.factoryConstraintValidatorfactory;
    } else {
      this.constraintValidatorfactoryEntry = constraintValidatorfactory;
    }
    return this;
  }

  @Override
  public Validator getValidator() {
    final AbstractGwtValidator validator = this.validatorFactory.createValidator();
    validator.init(this.constraintValidatorfactoryEntry, this.messageInterpolatorEntry,
        this.traversableResolverEntry, this.parameterNameProviderEntry);
    return validator;
  }

  @Override
  public ValidatorContext messageInterpolator(final MessageInterpolator messageInterpolator) {
    if (messageInterpolator == null) {
      this.messageInterpolatorEntry = this.factoryMessageInterpolator;
    } else {
      this.messageInterpolatorEntry = messageInterpolator;
    }
    return this;
  }

  @Override
  public ValidatorContext traversableResolver(final TraversableResolver traversableResolver) {
    if (traversableResolver == null) {
      this.traversableResolverEntry = this.factoryTraversableResolver;
    } else {
      this.traversableResolverEntry = traversableResolver;
    }
    return this;
  }

  @Override
  public ValidatorContext parameterNameProvider(
      final ParameterNameProvider pparameterNameProvider) {
    if (pparameterNameProvider == null) {
      this.parameterNameProviderEntry = this.factoryParameterNameProvider;
    } else {
      this.parameterNameProviderEntry = pparameterNameProvider;
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
