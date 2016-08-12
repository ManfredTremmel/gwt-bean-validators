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

import de.knightsoftnet.validators.client.spi.GwtValidationProvider;

import java.io.InputStream;

import javax.validation.BootstrapConfiguration;
import javax.validation.ParameterNameProvider;
import javax.validation.spi.BootstrapState;

/**
 * Extends {@link AbstractBaseGwtConfiguration} with just the parts that are not GWT compatible.
 */
public class GwtConfiguration extends AbstractBaseGwtConfiguration {

  public GwtConfiguration(final GwtValidationProvider gwtValidationProvider,
      final BootstrapState state) {
    super(gwtValidationProvider, state);
  }

  /**
   * Unsupported. Always throws an {@link UnsupportedOperationException}.
   */
  @Override
  public GwtConfiguration addMapping(final InputStream stream) {
    throw new UnsupportedOperationException("GWT does not support InputStreams");
  }

  @Override
  public AbstractBaseGwtConfiguration parameterNameProvider(
      final ParameterNameProvider pparameterNameProvider) {
    throw new UnsupportedOperationException("GWT does not support xml configuration");
  }

  @Override
  public ParameterNameProvider getDefaultParameterNameProvider() {
    throw new UnsupportedOperationException("GWT does not support xml configuration");
  }

  @Override
  public BootstrapConfiguration getBootstrapConfiguration() {
    throw new UnsupportedOperationException("GWT does not support xml configuration");
  }
}
