package de.knightsoftnet.validators.shared.impl;

import de.knightsoftnet.validators.shared.PostalCode;
import de.knightsoftnet.validators.shared.data.PostalCodeDefinitions;
import de.knightsoftnet.validators.shared.interfaces.HasGetFieldByName;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check if a postal code field is valid for the selected country.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
public class PostalCodeValidator implements ConstraintValidator<PostalCode, HasGetFieldByName> {

  /**
   * error message key.
   */
  private String message;

  /**
   * field name of the country code field.
   */
  private String fieldCountryCode;

  /**
   * field name of the postal code (zip) field.
   */
  private String fieldPostalCode;

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final PostalCode pconstraintAnnotation) {
    this.message = pconstraintAnnotation.message();
    this.fieldCountryCode = pconstraintAnnotation.fieldCountryCode();
    this.fieldPostalCode = pconstraintAnnotation.fieldPostalCode();
  }

  /**
   * {@inheritDoc} check if given object.
   *
   * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
   *      javax.validation.ConstraintValidatorContext)
   */
  @Override
  public final boolean isValid(final HasGetFieldByName pvalue,
      final ConstraintValidatorContext pcontext) {
    try {
      final String countryCode =
          Objects.toString(pvalue.getFieldByName(this.fieldCountryCode), null);
      final String postalCode = Objects.toString(pvalue.getFieldByName(this.fieldPostalCode), null);
      if (StringUtils.isEmpty(postalCode)) {
        return true;
      }

      final String regExCheck = PostalCodeDefinitions.COUNTRY_POSTAL_CODE_REGEX.get(countryCode);
      if (regExCheck == null) {
        return true;
      }
      if (postalCode.matches(regExCheck)) {
        return true;
      }
      pcontext.disableDefaultConstraintViolation();
      pcontext.buildConstraintViolationWithTemplate(this.message).addNode(this.fieldPostalCode)
      .addConstraintViolation();
      return false;
    } catch (final Exception ignore) {
      return false;
    }
  }
}
