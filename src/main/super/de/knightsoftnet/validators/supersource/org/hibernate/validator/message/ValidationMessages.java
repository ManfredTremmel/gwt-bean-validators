package org.hibernate.validator.message;

/**
 * Interface to represent the constants contained in resource bundle:
 * 'validation/ValidationMessages.properties'.
 */
@SuppressWarnings("checkstyle:abbreviationaswordinname")
public interface ValidationMessages extends com.google.gwt.i18n.client.ConstantsWithLookup {

  /**
   * Translated "must be false".
   *
   * @return translated "must be false"
   */
  @DefaultStringValue("must be false")
  @Key("javax.validation.constraints.AssertFalse.message")
  String javax_validation_constraints_AssertFalse_message(); // NOPMD

  /**
   * Translated "must be true".
   *
   * @return translated "must be true"
   */
  @DefaultStringValue("must be true")
  @Key("javax.validation.constraints.AssertTrue.message")
  String javax_validation_constraints_AssertTrue_message(); // NOPMD

  /**
   * Translated "must be less than or equal to {value}".
   *
   * @return translated "must be less than or equal to {value}"
   */
  @DefaultStringValue("must be less than or equal to {value}")
  @Key("javax.validation.constraints.DecimalMax.message")
  String javax_validation_constraints_DecimalMax_message(); // NOPMD

  /**
   * Translated "must be greater than or equal to {value}".
   *
   * @return translated "must be greater than or equal to {value}"
   */
  @DefaultStringValue("must be greater than or equal to {value}")
  @Key("javax.validation.constraints.DecimalMin.message")
  String javax_validation_constraints_DecimalMin_message(); // NOPMD

  /**
   * Translated "numeric value out of bounds (<{integer} digits>.<{fraction} digits> expected)".
   *
   * @return translated "numeric value out of bounds (<{integer} digits>.<{fraction} digits>
   *         expected)"
   */
  @DefaultStringValue("numeric value out of bounds "
      + "(<{integer} digits>.<{fraction} digits> expected)")
  @Key("javax.validation.constraints.Digits.message")
  String javax_validation_constraints_Digits_message(); // NOPMD

  /**
   * Translated "must be in the future".
   *
   * @return translated "must be in the future"
   */
  @DefaultStringValue("must be in the future")
  @Key("javax.validation.constraints.Future.message")
  String javax_validation_constraints_Future_message(); // NOPMD

  /**
   * Translated "must be less than or equal to {value}".
   *
   * @return translated "must be less than or equal to {value}"
   */
  @DefaultStringValue("must be less than or equal to {value}")
  @Key("javax.validation.constraints.Max.message")
  String javax_validation_constraints_Max_message(); // NOPMD

  /**
   * Translated "must be greater than or equal to {value}".
   *
   * @return translated "must be greater than or equal to {value}"
   */
  @DefaultStringValue("must be greater than or equal to {value}")
  @Key("javax.validation.constraints.Min.message")
  String javax_validation_constraints_Min_message(); // NOPMD

  /**
   * Translated "may not be null".
   *
   * @return translated "may not be null"
   */
  @DefaultStringValue("may not be null")
  @Key("javax.validation.constraints.NotNull.message")
  String javax_validation_constraints_NotNull_message(); // NOPMD

  /**
   * Translated "must be null".
   *
   * @return translated "must be null"
   */
  @DefaultStringValue("must be null")
  @Key("javax.validation.constraints.Null.message")
  String javax_validation_constraints_Null_message(); // NOPMD

  /**
   * Translated "must be in the past".
   *
   * @return translated "must be in the past"
   */
  @DefaultStringValue("must be in the past")
  @Key("javax.validation.constraints.Past.message")
  String javax_validation_constraints_Past_message(); // NOPMD

  /**
   * Translated "must match \"{regexp}\"".
   *
   * @return translated "must match \"{regexp}\""
   */
  @DefaultStringValue("must match \"{regexp}\"")
  @Key("javax.validation.constraints.Pattern.message")
  String javax_validation_constraints_Pattern_message(); // NOPMD

  /**
   * Translated "size must be between {min} and {max}".
   *
   * @return translated "size must be between {min} and {max}"
   */
  @DefaultStringValue("size must be between {min} and {max}")
  @Key("javax.validation.constraints.Size.message")
  String javax_validation_constraints_Size_message(); // NOPMD

  /**
   * Translated "invalid credit card number".
   *
   * @return translated "invalid credit card number"
   */
  @DefaultStringValue("invalid credit card number")
  @Key("org.hibernate.validator.constraints.CreditCardNumber.message")
  String org_hibernate_validator_constraints_CreditCardNumber_message(); // NOPMD

  /**
   * Translated "invalid {type} barcode".
   *
   * @return translated "invalid {type} barcode"
   */
  @DefaultStringValue("invalid {type} barcode")
  @Key("org.hibernate.validator.constraints.EAN.message")
  String org_hibernate_validator_constraints_EAN_message(); // NOPMD

  /**
   * Translated "not a well-formed email address".
   *
   * @return translated "not a well-formed email address"
   */
  @DefaultStringValue("not a well-formed email address")
  @Key("org.hibernate.validator.constraints.Email.message")
  String org_hibernate_validator_constraints_Email_message(); // NOPMD

  /**
   * Translated "length must be between {min} and {max}".
   *
   * @return translated "length must be between {min} and {max}"
   */
  @DefaultStringValue("length must be between {min} and {max}")
  @Key("org.hibernate.validator.constraints.Length.message")
  String org_hibernate_validator_constraints_Length_message(); // NOPMD

  /**
   * Translated "The check digit for {value} is invalid, Luhn Modulo 10 checksum failed".
   *
   * @return translated "The check digit for {value} is invalid, Luhn Modulo 10 checksum failed"
   */
  @DefaultStringValue("The check digit for {value} is invalid, " + "Luhn Modulo 10 checksum failed")
  @Key("org.hibernate.validator.constraints.LuhnCheck.message")
  String org_hibernate_validator_constraints_LuhnCheck_message(); // NOPMD

  /**
   * Translated "The check digit for {value} is invalid, Modulo 10 checksum failed".
   *
   * @return translated "The check digit for {value} is invalid, Modulo 10 checksum failed"
   */
  @DefaultStringValue("The check digit for {value} is invalid, Modulo 10 checksum failed")
  @Key("org.hibernate.validator.constraints.Mod10Check.message")
  String org_hibernate_validator_constraints_Mod10Check_message(); // NOPMD

  /**
   * Translated "The check digit for {value} is invalid, Modulo 11 checksum failed".
   *
   * @return translated "The check digit for {value} is invalid, Modulo 11 checksum failed"
   */
  @DefaultStringValue("The check digit for {value} is invalid, Modulo 11 checksum failed")
  @Key("org.hibernate.validator.constraints.Mod11Check.message")
  String org_hibernate_validator_constraints_Mod11Check_message(); // NOPMD

  /**
   * Translated "The check digit for {value} is invalid, {modType} checksum failed".
   *
   * @return translated "The check digit for {value} is invalid, {modType} checksum failed"
   */
  @DefaultStringValue("The check digit for {value} is invalid, {modType} " + "checksum failed")
  @Key("org.hibernate.validator.constraints.ModCheck.message")
  String org_hibernate_validator_constraints_ModCheck_message(); // NOPMD

  /**
   * Translated "may not be empty".
   *
   * @return translated "may not be empty"
   */
  @DefaultStringValue("may not be empty")
  @Key("org.hibernate.validator.constraints.NotBlank.message")
  String org_hibernate_validator_constraints_NotBlank_message(); // NOPMD

  /**
   * Translated "may not be empty".
   *
   * @return translated "may not be empty"
   */
  @DefaultStringValue("may not be empty")
  @Key("org.hibernate.validator.constraints.NotEmpty.message")
  String org_hibernate_validator_constraints_NotEmpty_message(); // NOPMD

  /**
   * Translated "script expression "{script}" didn't evaluate to true".
   *
   * @return translated "script expression "{script}" didn't evaluate to true"
   */
  @DefaultStringValue("script expression \"{script}\" didn't evaluate to true")
  @Key("org.hibernate.validator.constraints.ParametersScriptAssert.message")
  String org_hibernate_validator_constraints_ParametersScriptAssert_message(); // NOPMD

  /**
   * Translated "must be between {min} and {max}".
   *
   * @return translated "must be between {min} and {max}"
   */
  @DefaultStringValue("must be between {min} and {max}")
  @Key("org.hibernate.validator.constraints.Range.message")
  String org_hibernate_validator_constraints_Range_message(); // NOPMD

  /**
   * Translated "may have unsafe html content".
   *
   * @return translated "may have unsafe html content"
   */
  @DefaultStringValue("may have unsafe html content")
  @Key("org.hibernate.validator.constraints.SafeHtml.message")
  String org_hibernate_validator_constraints_SafeHtml_message(); // NOPMD

  /**
   * Translated "script expression \"{script}\" didn't evaluate to true".
   *
   * @return translated "script expression \"{script}\" didn't evaluate to true"
   */
  @DefaultStringValue("script expression \"{script}\" didn't evaluate to true")
  @Key("org.hibernate.validator.constraints.ScriptAssert.message")
  String org_hibernate_validator_constraints_ScriptAssert_message(); // NOPMD

  /**
   * Translated "must be a valid URL".
   *
   * @return translated "must be a valid URL"
   */
  @DefaultStringValue("must be a valid URL")
  @Key("org.hibernate.validator.constraints.URL.message")
  String org_hibernate_validator_constraints_URL_message(); // NOPMD

  /**
   * Translated "invalid Brazilian corporate taxpayer registry number (CNPJ)".
   *
   * @return translated "invalid Brazilian corporate taxpayer registry number (CNPJ)"
   */
  @DefaultStringValue("invalid Brazilian corporate taxpayer registry number (CNPJ)")
  @Key("org.hibernate.validator.constraints.br.CNPJ.message")
  String org_hibernate_validator_constraints_br_CNPJ_message(); // NOPMD

  /**
   * Translated "invalid Brazilian individual taxpayer registry number (CPF)".
   *
   * @return translated "invalid Brazilian individual taxpayer registry number (CPF)"
   */
  @DefaultStringValue("invalid Brazilian individual taxpayer registry number (CPF)")
  @Key("org.hibernate.validator.constraints.br.CPF.message")
  String org_hibernate_validator_constraints_br_CPF_message(); // NOPMD

  /**
   * Translated "invalid Brazilian Voter ID card number".
   *
   * @return translated "invalid Brazilian Voter ID card number"
   */
  @DefaultStringValue("invalid Brazilian Voter ID card number")
  @Key("org.hibernate.validator.constraints.br.TituloEleitoral.message")
  String org_hibernate_validator_constraints_br_TituloEleitoral_message(); // NOPMD
}
