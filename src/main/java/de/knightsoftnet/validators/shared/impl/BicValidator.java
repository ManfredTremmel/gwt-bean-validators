package de.knightsoftnet.validators.shared.impl;

import de.knightsoftnet.validators.shared.Bic;
import de.knightsoftnet.validators.shared.data.SwiftDefinitions;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check a string if it's a valid BIC.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
public class BicValidator implements ConstraintValidator<Bic, Object> {
  /**
   * definition of BIC length minimum.
   */
  public static final int BIC_LENGTH_MIN = 8;
  /**
   * definition of BIC length maximum.
   */
  public static final int BIC_LENGTH_MAX = 11;

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final Bic pconstraintAnnotation) {
    // nothing to do
  }

  /**
   * {@inheritDoc} check if given string is a valid BIC.
   *
   * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
   *      javax.validation.ConstraintValidatorContext)
   */
  @Override
  public final boolean isValid(final Object pvalue, final ConstraintValidatorContext pcontext) {
    final String valueAsString = ObjectUtils.toString(pvalue);
    if (StringUtils.isEmpty(valueAsString)) {
      // empty field is ok
      return true;
    }
    if (valueAsString.length() != BIC_LENGTH_MIN && valueAsString.length() != BIC_LENGTH_MAX) {
      // to short or to long, but it's handled by size validator!
      return true;
    }
    if (!valueAsString.matches("^[A-Z0-9]{4}[A-Z]{2}([A-Z0-9]{2}|[A-Z0-9]{5})$")) {
      // format is wrong!
      return false;
    }
    final String countryCode = valueAsString.substring(4, 6);
    final Integer validBicLength = SwiftDefinitions.COUNTRY_IBAN_LENGTH.get(countryCode);
    if (validBicLength == null) {
      // unknown country!
      return false;
    }

    // sorry, no more tests available
    return true;
  }

}
