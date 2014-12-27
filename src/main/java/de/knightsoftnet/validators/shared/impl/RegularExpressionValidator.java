package de.knightsoftnet.validators.shared.impl;

import de.knightsoftnet.validators.shared.RegularExpression;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.RegexValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check a string if it's a valid Regular Expression.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
public class RegularExpressionValidator implements ConstraintValidator<RegularExpression, String> {

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final RegularExpression pconstraintAnnotation) {
    // nothing to do
  }

  /**
   * {@inheritDoc} check if given string is a valid regular expression.
   *
   * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
   *      javax.validation.ConstraintValidatorContext)
   */
  @Override
  public final boolean isValid(final String pvalue, final ConstraintValidatorContext pcontext) {
    if (StringUtils.isEmpty(pvalue)) {
      return true;
    }
    try {
      // compile regular expression, result doesn't matter
      new RegexValidator(pvalue);
      return true;
    } catch (final Exception e) {
      // regular expression is invalid
      return false;
    }
  }

}
