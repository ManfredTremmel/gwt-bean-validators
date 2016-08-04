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

import de.knightsoftnet.validators.client.DefaultTraversableResolver;
import de.knightsoftnet.validators.client.spi.GwtConfigurationState;
import de.knightsoftnet.validators.client.spi.GwtValidationProvider;

import com.google.gwt.core.client.GWT;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Configuration;
import javax.validation.ConstraintValidatorFactory;
import javax.validation.MessageInterpolator;
import javax.validation.TraversableResolver;
import javax.validation.ValidatorFactory;
import javax.validation.spi.BootstrapState;

/**
 * Base GWT {@link Configuration}.
 */
public abstract class AbstractBaseGwtConfiguration
    implements Configuration<AbstractBaseGwtConfiguration> {

  protected final GwtValidationProvider provider;
  protected final BootstrapState state;
  protected final Map<String, String> properties = new HashMap<String, String>();
  protected ConstraintValidatorFactory constraintValidatorFactoryProperty;
  protected MessageInterpolator messageInterpolatorProperty;
  protected TraversableResolver traversableResolverProperty;

  public AbstractBaseGwtConfiguration(final GwtValidationProvider gwtValidationProvider,
      final BootstrapState state) {
    this.provider = gwtValidationProvider;
    this.state = state;
  }

  @Override
  public final AbstractBaseGwtConfiguration addProperty(final String name, final String value) {
    this.properties.put(name, value);
    return this;
  }

  @Override
  public final ValidatorFactory buildValidatorFactory() {
    final GwtConfigurationState configurationState = new GwtConfigurationState( //
        this.constraintValidatorFactoryProperty, //
        this.messageInterpolatorProperty, //
        this.properties, //
        this.traversableResolverProperty);
    return this.provider.buildValidatorFactory(configurationState);
  }

  /**
   * <b>{@link ConstraintValidatorFactory} is unsupported in GWT.</b> Constraint validators are
   * instead created using GWT.create- with no factory.
   */
  @Override
  public final AbstractBaseGwtConfiguration constraintValidatorFactory(
      final ConstraintValidatorFactory constraintValidatorFactory) {
    this.constraintValidatorFactoryProperty = constraintValidatorFactory;
    return this;
  }

  @Override
  public final ConstraintValidatorFactory getDefaultConstraintValidatorFactory() {
    return GWT.create(ConstraintValidatorFactory.class);
  }

  @Override
  public final MessageInterpolator getDefaultMessageInterpolator() {
    return GWT.create(MessageInterpolator.class);
  }

  @Override
  public final TraversableResolver getDefaultTraversableResolver() {
    return new DefaultTraversableResolver();
  }

  @Override
  public final AbstractBaseGwtConfiguration ignoreXmlConfiguration() {
    // Always ignore XML anyway
    return this;
  }

  @Override
  public final AbstractBaseGwtConfiguration messageInterpolator(
      final MessageInterpolator interpolator) {
    this.messageInterpolatorProperty = interpolator;
    return this;
  }

  @Override
  public final AbstractBaseGwtConfiguration traversableResolver(
      final TraversableResolver resolver) {
    this.traversableResolverProperty = resolver;
    return this;
  }
}
