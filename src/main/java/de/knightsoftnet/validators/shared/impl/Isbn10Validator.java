package de.knightsoftnet.validators.shared.impl;

import de.knightsoftnet.validators.shared.Isbn10;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.checkdigit.ISBN10CheckDigit;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check a string if it's a valid ISBN10.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
public class Isbn10Validator implements ConstraintValidator<Isbn10, Object> {
  /**
   * definition of isbn10 length.
   */
  public static final int ISBN10_LENGTH = 10;

  /**
   * apache commons class to check/calculate ISBN10 check sums.
   */
  private static final ISBN10CheckDigit CHECK_ISBN10 = new ISBN10CheckDigit();

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final Isbn10 pconstraintAnnotation) {
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
      // ISBN10 must be numeric, but that's handled by digits annotation
      return true;
    }
    if (valueAsString.length() != ISBN10_LENGTH) {
      // ISBN10 size is wrong, but that's handled by size annotation
      return true;
    }
    // calculate and check checksum (ISBN10)
    return CHECK_ISBN10.isValid(valueAsString);
  }
}
