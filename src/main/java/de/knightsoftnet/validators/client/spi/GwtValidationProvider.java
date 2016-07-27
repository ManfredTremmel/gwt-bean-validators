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

import de.knightsoftnet.validators.client.AbstractGwtValidatorFactory;
import de.knightsoftnet.validators.client.impl.AbstractBaseGwtConfiguration;
import de.knightsoftnet.validators.client.impl.GwtConfiguration;

import com.google.gwt.core.client.GWT;

import javax.validation.Configuration;
import javax.validation.ValidatorFactory;
import javax.validation.spi.BootstrapState;
import javax.validation.spi.ConfigurationState;
import javax.validation.spi.ValidationProvider;

/**
 * GWT {@link ValidationProvider}.
 */
public final class GwtValidationProvider //
    implements ValidationProvider<AbstractBaseGwtConfiguration> {

  @Override
  public ValidatorFactory buildValidatorFactory(final ConfigurationState configurationState) {
    final AbstractGwtValidatorFactory validatorFactory =
        (AbstractGwtValidatorFactory) GWT.create(ValidatorFactory.class);
    validatorFactory.init(configurationState);
    return validatorFactory;
  }

  @Override
  public Configuration<?> createGenericConfiguration(final BootstrapState state) {
    return new GwtConfiguration(this, state);
  }

  @Override
  public GwtConfiguration createSpecializedConfiguration(final BootstrapState state) {
    return new GwtConfiguration(this, state);
  }
}
