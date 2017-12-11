/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package org.hibernate.validator.internal.constraintvalidators.bv.time.pastorpresent;

import org.hibernate.validator.internal.constraintvalidators.bv.time.AbstractJavaTimeValidator;

import javax.validation.constraints.PastOrPresent;

/**
 * Base class for all {@code @Past} validators that are based on the {@code java.time} package.
 */
public abstract class AbstractPastOrPresentJavaTimeValidator<T>
    extends AbstractJavaTimeValidator<PastOrPresent, T> {
  @Override
  protected boolean isValid(final int result) {
    throw new UnsupportedOperationException("Not supported by GWT.");
  }
}
