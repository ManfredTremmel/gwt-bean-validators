/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package org.hibernate.validator.internal.constraintvalidators.bv.time;

import java.lang.annotation.Annotation;
import java.time.Clock;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Base class for all time validators that are based on the {@code java.time} package.
 *
 */
public abstract class AbstractJavaTimeValidator<C extends Annotation, T>
    implements ConstraintValidator<C, T> {

  @Override
  public boolean isValid(final T value, final ConstraintValidatorContext context) {
    throw new UnsupportedOperationException("Not supported by GWT.");
  }

  protected abstract boolean isValid(int result);

  protected abstract T getReferenceValue(Clock reference);
}
