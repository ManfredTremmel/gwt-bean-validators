package de.knightsoftnet.validators.shared.impl;

import de.knightsoftnet.validators.shared.AgeLimitCheck;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check if a field's size has one of the two given sizes.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
public class AgeLimitCheckValidator implements ConstraintValidator<AgeLimitCheck, Date> {

  /**
   * minimum years.
   */
  private int minYears;

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final AgeLimitCheck pconstraintAnnotation) {
    this.minYears = pconstraintAnnotation.minYears();
  }

  /**
   * {@inheritDoc} check if given object.
   *
   * @see javax.validation.ConstraintValidator#isValid(Object,
   *      javax.validation.ConstraintValidatorContext)
   */
  @Override
  public final boolean isValid(final Date pvalue, final ConstraintValidatorContext pcontext) {
    if (pvalue == null) {
      return true;
    }
    final Date dateLimit =
        DateUtils
        .truncate(DateUtils.addYears(new Date(), 0 - this.minYears), Calendar.DAY_OF_MONTH);
    final Date birthday = DateUtils.truncate(pvalue, Calendar.DAY_OF_MONTH);
    return !dateLimit.before(birthday);
  }
}
