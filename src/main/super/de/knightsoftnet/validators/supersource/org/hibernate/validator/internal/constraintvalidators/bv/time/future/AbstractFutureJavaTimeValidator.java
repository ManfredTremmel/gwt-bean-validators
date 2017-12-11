/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package org.hibernate.validator.internal.constraintvalidators.bv.time.future;

import org.hibernate.validator.internal.constraintvalidators.bv.time.AbstractJavaTimeValidator;

import javax.validation.constraints.Future;

/**
 * Base class for all {@code @Future} validators that are based on the {@code java.time} package.
 */
public abstract class AbstractFutureJavaTimeValidator<T>
    extends AbstractJavaTimeValidator<Future, T> {
  @Override
  protected boolean isValid(final int result) {
    throw new UnsupportedOperationException("Not supported by GWT.");
  }
}
