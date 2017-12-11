/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package org.hibernate.validator.internal.constraintvalidators.bv.time.pastorpresent;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.PastOrPresent;

/**
 * Check that the <code>java.util.Date</code> passed to be validated is in the past.
 *
 * @author Alaa Nassef
 * @author Manfred Tremmel - GWT port
 */
public class PastOrPresentValidatorForDate implements ConstraintValidator<PastOrPresent, Date> {

  @Override
  public void initialize(final PastOrPresent constraintAnnotation) {}

  @Override
  public boolean isValid(final Date date,
      final ConstraintValidatorContext constraintValidatorContext) {
    // null values are valid
    if (date == null) {
      return true;
    }
    final Date now = new Date();

    return date.getTime() <= now.getTime();
  }
}
