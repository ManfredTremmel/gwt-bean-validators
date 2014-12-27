package de.knightsoftnet.validators.shared.impl;

import de.knightsoftnet.validators.shared.CreditCardNumber;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.CreditCardValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check a string if it's a valid credit card number.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
public class CreditCardNumberValidator implements ConstraintValidator<CreditCardNumber, String> {
  /**
   * maximum length of a credit card numbers.
   */
  public static final int LENGTH_CREDIT_CARDNUMBER = 16;

  /**
   * apache commons class to check credit card number.
   */
  private static final CreditCardValidator CHECK_CREDIT_CARD = new CreditCardValidator();

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final CreditCardNumber pconstraintAnnotation) {
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
    if (pvalue.length() > LENGTH_CREDIT_CARDNUMBER) {
      // credit card number is to long, but that's handled by size
      // annotation
      return true;
    }
    return CHECK_CREDIT_CARD.isValid(pvalue);
  }
}
