/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package org.hibernate.validator.internal.constraintvalidators.bv.time.past;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Past;

/**
 * Check that the <code>java.util.Date</code> passed to be validated is in the past.
 *
 * @author Alaa Nassef
 * @author Manfred Tremmel - GWT port
 */
public class PastValidatorForDate implements ConstraintValidator<Past, Date> {

  @Override
  public void initialize(final Past constraintAnnotation) {}

  @Override
  public boolean isValid(final Date date,
      final ConstraintValidatorContext constraintValidatorContext) {
    // null values are valid
    if (date == null) {
      return true;
    }

    return date.before(new Date());
  }
}
