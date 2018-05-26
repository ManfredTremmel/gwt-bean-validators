/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package org.hibernate.validator.internal.constraintvalidators.bv.time.future;

import java.util.Calendar;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Future;

/**
 * Check that the <code>java.util.Calendar</code> passed to be validated is in the future.
 *
 * @author Alaa Nassef
 * @author Manfred Tremmel - GWT port
 */
public class FutureValidatorForCalendar implements ConstraintValidator<Future, Calendar> {

  @Override
  public void initialize(final Future constraintAnnotation) {}

  @Override
  public boolean isValid(final Calendar date,
      final ConstraintValidatorContext constraintValidatorContext) {
    // null values are valid
    if (date == null) {
      return true;
    }

    return date.after(Calendar.getInstance());
  }
}
