/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.FutureOrPresent;

/**
 * Check that the {@code java.util.Date} passed to be validated is in the future.
 *
 * @author Alaa Nassef
 * @author Guillaume Smet
 * @author Manfred Tremmel - GWT port
 */
public class FutureOrPresentValidatorForDate implements ConstraintValidator<FutureOrPresent, Date> {

  @Override
  public void initialize(final FutureOrPresent constraintAnnotation) {}

  @Override
  public boolean isValid(final Date date,
      final ConstraintValidatorContext constraintValidatorContext) {
    // null values are valid
    if (date == null) {
      return true;
    }
    final Date now = new Date();

    return date.getTime() >= now.getTime();
  }
}
