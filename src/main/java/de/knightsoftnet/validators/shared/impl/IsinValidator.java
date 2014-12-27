package de.knightsoftnet.validators.shared.impl;

import de.knightsoftnet.validators.shared.Isin;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.checkdigit.ISINCheckDigit;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check a string if it's a valid ISIN.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
public class IsinValidator implements ConstraintValidator<Isin, Object> {
  /**
   * definition of gln length.
   */
  public static final int ISIN_LENGTH = 12;

  /**
   * apache commons class to check/calculate ISIN check sums.
   */
  private static final ISINCheckDigit CHECK_ISIN = new ISINCheckDigit();

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final Isin pconstraintAnnotation) {
    // nothing to do
  }

  /**
   * {@inheritDoc} check if given string is a valid gln.
   *
   * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
   *      javax.validation.ConstraintValidatorContext)
   */
  @Override
  public final boolean isValid(final Object pvalue, final ConstraintValidatorContext pcontext) {
    final String valueAsString = ObjectUtils.toString(pvalue);
    if (StringUtils.isEmpty(valueAsString)) {
      return true;
    }
    if (valueAsString.length() != ISIN_LENGTH) {
      // ISIN size is wrong, but that's handled by size annotation
      return true;
    }
    // calculate and check checksum (ISIN)
    return CHECK_ISIN.isValid(valueAsString);
  }
}
