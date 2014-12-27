package de.knightsoftnet.validators.shared.impl;

import de.knightsoftnet.validators.shared.Gtin13;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.checkdigit.EAN13CheckDigit;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check a string if it's a valid GTIN13/EAN13.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
public class Gtin13Validator implements ConstraintValidator<Gtin13, Object> {
  /**
   * definition of gln length.
   */
  public static final int GTIN13_LENGTH = 13;

  /**
   * apache commons class to check/calculate GTIN13/EAN13 check sums.
   */
  private static final EAN13CheckDigit CHECK_GTIN13 = new EAN13CheckDigit();

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final Gtin13 pconstraintAnnotation) {
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
    if (!StringUtils.isNumeric(valueAsString)) {
      // EAN13 must be numeric, but that's handled by digits annotation
      return true;
    }
    if (valueAsString.length() != GTIN13_LENGTH) {
      // EAN13 size is wrong, but that's handled by size annotation
      return true;
    }
    // calculate and check checksum (GTIN13/EAN13)
    return CHECK_GTIN13.isValid(valueAsString);
  }
}
