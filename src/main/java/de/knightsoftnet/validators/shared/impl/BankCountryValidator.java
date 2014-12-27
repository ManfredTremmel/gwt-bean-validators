package de.knightsoftnet.validators.shared.impl;

import de.knightsoftnet.validators.shared.BankCountry;
import de.knightsoftnet.validators.shared.interfaces.HasGetFieldByName;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check if a country field and the country in iban and bic match, implementation.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
public class BankCountryValidator implements ConstraintValidator<BankCountry, HasGetFieldByName> {

  /**
   * error message key.
   */
  private String message;

  /**
   * field name of the country code field.
   */
  private String fieldCountryCode;

  /**
   * field name of the iban field.
   */
  private String fieldIban;

  /**
   * field name of the bic field.
   */
  private String fieldBic;

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final BankCountry pconstraintAnnotation) {
    this.fieldCountryCode = pconstraintAnnotation.fieldCountryCode();
    this.fieldIban = pconstraintAnnotation.fieldIban();
    this.fieldBic = pconstraintAnnotation.fieldBic();
    this.message = pconstraintAnnotation.message();
  }

  /**
   * {@inheritDoc} check if given object.
   *
   * @see javax.validation.ConstraintValidator#isValid(HasGetFieldByName,
   *      javax.validation.ConstraintValidatorContext)
   */
  @Override
  public final boolean isValid(final HasGetFieldByName pvalue,
      final ConstraintValidatorContext pcontext) {
    String valueCountry = Objects.toString(pvalue.getFieldByName(this.fieldCountryCode), null);
    final String valueIban = Objects.toString(pvalue.getFieldByName(this.fieldIban), null);
    final String valueBic = Objects.toString(pvalue.getFieldByName(this.fieldBic), null);
    if (StringUtils.isEmpty(valueIban) && StringUtils.isEmpty(valueBic)) {
      return true;
    } else if (StringUtils.isEmpty(valueIban)) {
      pcontext.disableDefaultConstraintViolation();
      pcontext
          .buildConstraintViolationWithTemplate(
              "org.hibernate.validator.constraints.NotEmpty.message").addNode(this.fieldIban)
          .addConstraintViolation();
      return false;
    } else if (StringUtils.isEmpty(valueBic)) {
      pcontext.disableDefaultConstraintViolation();
      pcontext
          .buildConstraintViolationWithTemplate(
              "org.hibernate.validator.constraints.NotEmpty.message").addNode(this.fieldBic)
          .addConstraintViolation();
      return false;
    } else if (StringUtils.length(valueIban) >= IbanValidator.IBAN_LENGTH_MIN
        && StringUtils.length(valueBic) >= BicValidator.BIC_LENGTH_MIN) {
      final String countryIban = valueIban.substring(0, 2);
      final String countryBic = valueBic.substring(4, 6);
      if (StringUtils.length(valueCountry) != 2) {
        // missing country selection, us country of iban
        valueCountry = countryIban;
      }
      if (!valueCountry.equals(countryIban) || !valueCountry.equals(countryBic)) {
        pcontext.disableDefaultConstraintViolation();
        if (!valueCountry.equals(countryIban)) {
          pcontext.buildConstraintViolationWithTemplate(this.message).addNode(this.fieldIban)
              .addConstraintViolation();
        }
        if (!valueCountry.equals(countryBic)) {
          pcontext.buildConstraintViolationWithTemplate(this.message).addNode(this.fieldBic)
              .addConstraintViolation();
        }
        return false;
      }
      return true;
    } else {
      // wrong format, should be handled by other validators
      return true;
    }
  }
}
