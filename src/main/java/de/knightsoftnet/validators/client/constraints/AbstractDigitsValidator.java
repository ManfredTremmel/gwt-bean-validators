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

package de.knightsoftnet.validators.client.constraints;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.constraints.Digits;

/**
 * Abstract {@link Digits} constraint validator implementation for a <code>T</code>.
 *
 * @param <T> the type of object to validate
 */
public abstract class AbstractDigitsValidator<T> implements ConstraintValidator<Digits, T> {

  private int fraction;
  private int integer;

  @Override
  public final void initialize(final Digits constraintAnnotation) {
    if (!(constraintAnnotation.fraction() >= 0)) {
      throw new IllegalArgumentException("@Digits.fraction must be a nonnegative nubmer");
    }
    if (!(constraintAnnotation.integer() >= 0)) {
      throw new IllegalArgumentException("@Digits.integer must be a nonnegative nubmer");
    }
    this.fraction = constraintAnnotation.fraction();
    this.integer = constraintAnnotation.integer();
  }

  protected final boolean isValid(final BigDecimal bigValue) {
    final int integerLength = bigValue.precision() - bigValue.scale();
    if (integerLength > this.integer) {
      return false;
    }
    final int fractionalLength = bigValue.scale() < 0 ? 0 : bigValue.scale();
    return fractionalLength <= this.fraction;
  }
}
