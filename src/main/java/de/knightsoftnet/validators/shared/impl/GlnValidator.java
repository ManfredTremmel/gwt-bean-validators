package de.knightsoftnet.validators.shared.impl;

import de.knightsoftnet.validators.shared.Gln;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.checkdigit.EAN13CheckDigit;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check a string if it's a valid GLN.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
public class GlnValidator implements ConstraintValidator<Gln, Object> {
  /**
   * definition of gln length.
   */
  public static final int GLN_LENGTH = 13;

  /**
   * apache commons class to check/calculate GLN/EAN13 check sums.
   */
  private static final EAN13CheckDigit CHECK_GLN = new EAN13CheckDigit();

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final Gln pconstraintAnnotation) {
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
      // GLN must be numeric, but that's handled by digits annotation
      return true;
    }
    if (valueAsString.length() != GLN_LENGTH) {
      // GLN size is wrong, but that's handled by size annotation
      return true;
    }
    // calculate and check checksum (GTIN-13/GLN)
    return CHECK_GLN.isValid(valueAsString);
  }
}
