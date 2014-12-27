package de.knightsoftnet.validators.shared.impl;

import de.knightsoftnet.validators.shared.Isbn13Formated;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check a string if it's a valid ISBN13.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
public class Isbn13FormatedValidator implements ConstraintValidator<Isbn13Formated, Object> {
  /**
   * definition of gln length.
   */
  public static final int ISBN13_LENGTH = 17;

  /**
   * apache commons class to check/calculate GLN/EAN13 check sums.
   */
  private static final org.apache.commons.validator.routines.ISBNValidator CHECK_ISBN =
      new org.apache.commons.validator.routines.ISBNValidator();

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final Isbn13Formated pconstraintAnnotation) {
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
    if (valueAsString.length() != ISBN13_LENGTH) {
      // ISBN13 size is wrong, but that's handled by size annotation
      return true;
    }
    // calculate and check checksum (GTIN-13/GLN)
    return CHECK_ISBN.isValidISBN13(valueAsString);
  }
}
