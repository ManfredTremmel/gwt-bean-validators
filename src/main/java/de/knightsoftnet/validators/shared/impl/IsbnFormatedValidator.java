package de.knightsoftnet.validators.shared.impl;

import de.knightsoftnet.validators.shared.IsbnFormated;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check a string if it's a valid ISBN (10 or 13 digits long).
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
public class IsbnFormatedValidator implements ConstraintValidator<IsbnFormated, Object> {

  /**
   * apache commons class to check/calculate ISBN check sums.
   */
  private static final org.apache.commons.validator.routines.ISBNValidator CHECK_ISBN =
      new org.apache.commons.validator.routines.ISBNValidator();

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final IsbnFormated pconstraintAnnotation) {
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
    if (valueAsString.length() == Isbn10FormatedValidator.ISBN10_LENGTH
        || valueAsString.length() == Isbn13FormatedValidator.ISBN13_LENGTH) {
      return CHECK_ISBN.isValid(valueAsString);
    }
    // other sizes are wrong, but this is reported by AlternateSize
    return true;
  }
}
