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

import javax.validation.MessageInterpolator.Context;
import javax.validation.metadata.ConstraintDescriptor;

/**
 * Implementation of {@link Context}.
 */
public final class MessageInterpolatorContextImpl implements Context {

  private final ConstraintDescriptor<?> constraintDescriptor;
  private final Object value;

  public MessageInterpolatorContextImpl(final ConstraintDescriptor<?> constraintDescriptor,
      final Object value) {
    this.constraintDescriptor = constraintDescriptor;
    this.value = value;
  }

  @Override
  public ConstraintDescriptor<?> getConstraintDescriptor() {
    return this.constraintDescriptor;
  }

  @Override
  public Object getValidatedValue() {
    return this.value;
  }
}
