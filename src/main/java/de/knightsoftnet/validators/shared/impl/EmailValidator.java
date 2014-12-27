package de.knightsoftnet.validators.shared.impl;

import de.knightsoftnet.validators.shared.Email;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check a string if it's a valid eMail.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
public class EmailValidator implements ConstraintValidator<Email, String> {
  /**
   * maximum length of a eMail.
   */
  public static final int LENGTH_MAIL = 255;

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final Email pconstraintAnnotation) {
    // nothing to do
  }

  /**
   * {@inheritDoc} check if given string is a valid mail.
   *
   * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
   *      javax.validation.ConstraintValidatorContext)
   */
  @Override
  public final boolean isValid(final String pvalue, final ConstraintValidatorContext pcontext) {
    if (StringUtils.isEmpty(pvalue)) {
      return true;
    }
    if (pvalue.length() > LENGTH_MAIL) {
      // Email is to long, but that's handled by size annotation
      return true;
    }
    return org.apache.commons.validator.routines.EmailValidator.getInstance().isValid(pvalue);
  }
}
