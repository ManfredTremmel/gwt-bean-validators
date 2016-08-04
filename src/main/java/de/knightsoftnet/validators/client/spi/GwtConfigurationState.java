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

import java.io.InputStream;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintValidatorFactory;
import javax.validation.MessageInterpolator;
import javax.validation.TraversableResolver;

/**
 * Only the GWT incompatible parts.
 */
public final class GwtConfigurationState extends AbstractBaseConfigurationState {

  public GwtConfigurationState(final ConstraintValidatorFactory constraintValidatorFactory,
      final MessageInterpolator messageInterpolator, final Map<String, String> properties,
      final TraversableResolver traversableResolver) {
    super(constraintValidatorFactory, messageInterpolator, properties, traversableResolver);
  }

  @Override
  public Set<InputStream> getMappingStreams() {
    throw new UnsupportedOperationException("GWT Validation does not support getMappingStreams");
  }
}
