package de.knightsoftnet.validators.shared.impl;

import de.knightsoftnet.validators.shared.Url;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check a string if it's a valid URL.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
public class UrlValidator implements ConstraintValidator<Url, String> {

  /**
   * maximum length of a url.
   */
  public static final int LENGTH_URL = 255;

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final Url pconstraintAnnotation) {
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
    if (pvalue.length() > LENGTH_URL) {
      // Email is to long, but that's handled by size annotation
      return true;
    }
    return org.apache.commons.validator.routines.UrlValidator.getInstance().isValid(pvalue);
  }

}
