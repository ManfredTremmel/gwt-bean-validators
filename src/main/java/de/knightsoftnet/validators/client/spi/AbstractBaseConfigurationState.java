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

package de.knightsoftnet.validators.client.spi;

import java.util.Collections;
import java.util.Map;

import javax.validation.ConstraintValidatorFactory;
import javax.validation.MessageInterpolator;
import javax.validation.ParameterNameProvider;
import javax.validation.TraversableResolver;
import javax.validation.spi.ConfigurationState;

/**
 * Base GWT implementation of {@link ConfigurationState}.
 */
public abstract class AbstractBaseConfigurationState implements ConfigurationState {
  private final ConstraintValidatorFactory constraintValidatorFactory;
  private final MessageInterpolator messageInterpolator;
  private final Map<String, String> properties;
  private final TraversableResolver traversableResolver;

  /**
   * constructor.
   *
   * @param constraintValidatorFactory constraint validator factory
   * @param messageInterpolator message interpolator
   * @param properties map with properties
   * @param traversableResolver traversable resolver
   */
  public AbstractBaseConfigurationState(final ConstraintValidatorFactory constraintValidatorFactory,
      final MessageInterpolator messageInterpolator, final Map<String, String> properties,
      final TraversableResolver traversableResolver) {
    this.constraintValidatorFactory = constraintValidatorFactory;
    this.messageInterpolator = messageInterpolator;
    this.properties = Collections.unmodifiableMap(properties);
    this.traversableResolver = traversableResolver;
  }

  @Override
  public ConstraintValidatorFactory getConstraintValidatorFactory() {
    return this.constraintValidatorFactory;
  }

  @Override
  public MessageInterpolator getMessageInterpolator() {
    return this.messageInterpolator;
  }

  @Override
  public Map<String, String> getProperties() {
    return this.properties;
  }

  @Override
  public TraversableResolver getTraversableResolver() {
    return this.traversableResolver;
  }

  @Override
  public boolean isIgnoreXmlConfiguration() {
    // Always ignore XML
    return false;
  }

  @Override
  public ParameterNameProvider getParameterNameProvider() {
    return null;
  }
}
