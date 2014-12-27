package de.knightsoftnet.validators.shared.impl;

import de.knightsoftnet.validators.shared.Isbn;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.checkdigit.EAN13CheckDigit;
import org.apache.commons.validator.routines.checkdigit.ISBN10CheckDigit;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check a string if it's a valid ISBN (10 or 13 digits long).
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
public class IsbnValidator implements ConstraintValidator<Isbn, Object> {

  /**
   * apache commons class to check/calculate ISBN10 check sums.
   */
  private static final ISBN10CheckDigit CHECK_ISBN10 = new ISBN10CheckDigit();

  /**
   * apache commons class to check/calculate GLN/EAN13 check sums.
   */
  private static final EAN13CheckDigit CHECK_ISBN13 = new EAN13CheckDigit();

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final Isbn pconstraintAnnotation) {
    // nothing to do
  }

  /**
   * {@inheritDoc} check if given string is a valid isbn.
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
      // ISBN10 must be numeric, but that's handled by digits annotation
      return true;
    }
    if (valueAsString.length() == Isbn10Validator.ISBN10_LENGTH) {
      // we do have 10 digits, lets test the checksum
      return CHECK_ISBN10.isValid(valueAsString);
    }
    if (valueAsString.length() == Isbn13Validator.ISBN13_LENGTH) {
      // we do have 13 digits, lets test the checksum
      return CHECK_ISBN13.isValid(valueAsString);
    }
    // other sizes are wrong, but this is reported by AlternateSize
    return true;
  }
}
