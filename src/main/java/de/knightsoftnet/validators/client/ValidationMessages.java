/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.knightsoftnet.validators.client;

import com.google.gwt.i18n.client.ConstantsWithLookup;

/**
 * The <code>ValidationMessages</code> contains the messages for jsr 303 validation annotations.
 *
 * @author Manfred Tremmel
 *
 */
@SuppressWarnings("checkstyle:abbreviationaswordinname")
public interface ValidationMessages extends ConstantsWithLookup {

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
   * Translated "invalid currency (must be one of {value})".
   *
   * @return translated "invalid currency (must be one of {value})"
   */
  @DefaultStringValue("invalid currency (must be one of {value})")
  @Key("org.hibernate.validator.constraints.Currency.message")
  String org_hibernate_validator_constraints_Currency_message(); // NOPMD

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

  /**
   * Translated "Invalid Polish Taxpayer Identification Number (REGON)".
   *
   * @return translated "Invalid Polish Taxpayer Identification Number (REGON)"
   */
  @DefaultStringValue("Invalid Polish Taxpayer Identification Number (REGON)")
  @Key("org.hibernate.validator.constraints.pl.REGON.message")
  String org_hibernate_validator_constraints_pl_REGON_message(); // NOPMD

  /**
   * Translated "Invalid VAT Identification Number (NIP)".
   *
   * @return translated "Invalid VAT Identification Number (NIP)"
   */
  @DefaultStringValue("Invalid VAT Identification Number (NIP)")
  @Key("org.hibernate.validator.constraints.pl.NIP.message")
  String org_hibernate_validator_constraints_pl_NIP_message(); // NOPMD

  /**
   * Translated "Invalid Polish National Identification Number (PESEL)".
   *
   * @return translated "Invalid Polish National Identification Number (PESEL)"
   */
  @DefaultStringValue("Invalid Polish National Identification Number (PESEL)")
  @Key("org.hibernate.validator.constraints.pl.PESEL.message")
  String org_hibernate_validator_constraints_pl_PESEL_message(); // NOPMD

  /**
   * message used for age limit check.
   *
   * @return deKnightsoftnetValidatorsSharedValidationAgeLimitCheckMessage
   */
  @DefaultStringValue("you must be at least {minYears} years old")
  @Key("deKnightsoftnetValidatorsSharedValidationAgeLimitCheckMessage")
  String deKnightsoftnetValidatorsSharedValidationAgeLimitCheckMessage();

  /**
   * message used for alternate size check with two different sizes.
   *
   * @return deKnightsoftnetValidatorsSharedValidationAlternateSizeMessage
   */
  @DefaultStringValue("size must be {size1} or {size2}")
  @Key("deKnightsoftnetValidatorsSharedValidationAlternateSizeMessage")
  String deKnightsoftnetValidatorsSharedValidationAlternateSizeMessage();

  /**
   * message used for bank account country check validation.
   *
   * @return deKnightsoftnetValidatorsSharedValidationBankCountryMessage
   */
  @DefaultStringValue("country in {fieldBic} and {fieldIban} must match")
  @Key("deKnightsoftnetValidatorsSharedValidationBankCountryMessage")
  String deKnightsoftnetValidatorsSharedValidationBankCountryMessage();

  /**
   * message used for bic check validation.
   *
   * @return deKnightsoftnetValidatorsSharedValidationBicMessage
   */
  @DefaultStringValue("format of bic is wrong")
  @Key("deKnightsoftnetValidatorsSharedValidationBicMessage")
  String deKnightsoftnetValidatorsSharedValidationBicMessage();

  /**
   * message used for gln check validation.
   *
   * @return deKnightsoftnetValidatorsSharedValidationGlnMessage
   */
  @DefaultStringValue("checksum of GLN is wrong")
  @Key("deKnightsoftnetValidatorsSharedValidationGlnMessage")
  String deKnightsoftnetValidatorsSharedValidationGlnMessage();

  /**
   * message used for gtin check validation.
   *
   * @return deKnightsoftnetValidatorsSharedValidationGtinMessage
   */
  @DefaultStringValue("checksum of GTIN is wrong")
  @Key("deKnightsoftnetValidatorsSharedValidationGtinMessage")
  String deKnightsoftnetValidatorsSharedValidationGtinMessage();

  /**
   * message used for gtin8 check validation.
   *
   * @return deKnightsoftnetValidatorsSharedValidationGtin8Message
   */
  @DefaultStringValue("checksum of Gtin8 is wrong")
  @Key("deKnightsoftnetValidatorsSharedValidationGtin8Message")
  String deKnightsoftnetValidatorsSharedValidationGtin8Message();

  /**
   * message used for gtin13 check validation.
   *
   * @return deKnightsoftnetValidatorsSharedValidationGtin13Message
   */
  @DefaultStringValue("checksum of GTIN13 is wrong")
  @Key("deKnightsoftnetValidatorsSharedValidationGtin13Message")
  String deKnightsoftnetValidatorsSharedValidationGtin13Message();

  /**
   * message used for iban check validation.
   *
   * @return deKnightsoftnetValidatorsSharedValidationIbanMessage
   */
  @DefaultStringValue("checksum of IBAN is wrong")
  @Key("deKnightsoftnetValidatorsSharedValidationIbanMessage")
  String deKnightsoftnetValidatorsSharedValidationIbanMessage();

  /**
   * message used for iban formated check validation.
   *
   * @return deKnightsoftnetValidatorsSharedValidationIbanFormatedMessage
   */
  @DefaultStringValue("checksum or format of IBAN is wrong")
  @Key("deKnightsoftnetValidatorsSharedValidationIbanFormatedMessage")
  String deKnightsoftnetValidatorsSharedValidationIbanFormatedMessage();

  /**
   * message used for isbn check validation.
   *
   * @return deKnightsoftnetValidatorsSharedValidationIsbnMessage
   */
  @DefaultStringValue("checksum of ISBN is wrong")
  @Key("deKnightsoftnetValidatorsSharedValidationIsbnMessage")
  String deKnightsoftnetValidatorsSharedValidationIsbnMessage();

  /**
   * message used for isbn10 check validation.
   *
   * @return deKnightsoftnetValidatorsSharedValidationIsbn10Message
   */
  @DefaultStringValue("checksum of ISBN10 is wrong")
  @Key("deKnightsoftnetValidatorsSharedValidationIsbn10Message")
  String deKnightsoftnetValidatorsSharedValidationIsbn10Message();

  /**
   * message used for isbn13 check validation.
   *
   * @return deKnightsoftnetValidatorsSharedValidationIsbn13Message
   */
  @DefaultStringValue("checksum of ISBN13 is wrong")
  @Key("deKnightsoftnetValidatorsSharedValidationIsbn13Message")
  String deKnightsoftnetValidatorsSharedValidationIsbn13Message();

  /**
   * message used for formated isbn check validation.
   *
   * @return deKnightsoftnetValidatorsSharedValidationIsbnFormatedMessage
   */
  @DefaultStringValue("checksum or format of ISBN is wrong")
  @Key("deKnightsoftnetValidatorsSharedValidationIsbnFormatedMessage")
  String deKnightsoftnetValidatorsSharedValidationIsbnFormatedMessage();

  /**
   * message used for formated isbn10 check validation.
   *
   * @return deKnightsoftnetValidatorsSharedValidationIsbn10FormatedMessage
   */
  @DefaultStringValue("checksum or format of ISBN10 is wrong")
  @Key("deKnightsoftnetValidatorsSharedValidationIsbn10FormatedMessage")
  String deKnightsoftnetValidatorsSharedValidationIsbn10FormatedMessage();

  /**
   * message used for formated isbn13 formated check validation.
   *
   * @return deKnightsoftnetValidatorsSharedValidationIsbn13FormatedMessage
   */
  @DefaultStringValue("checksum or format of ISBN13 is wrong")
  @Key("deKnightsoftnetValidatorsSharedValidationIsbn13FormatedMessage")
  String deKnightsoftnetValidatorsSharedValidationIsbn13FormatedMessage();

  /**
   * message used for isin check validation.
   *
   * @return deKnightsoftnetValidatorsSharedValidationISINMessage
   */
  @DefaultStringValue("checksum of ISIN is wrong")
  @Key("deKnightsoftnetValidatorsSharedValidationIsinMessage")
  String deKnightsoftnetValidatorsSharedValidationIsinMessage();

  /**
   * message used for Levenshtein Distance validation.
   *
   * @return deKnightsoftnetValidatorsSharedValidationLevenshteinDistanceMessage
   */
  @DefaultStringValue("the values of {field1} and {field2} are to similar")
  @Key("deKnightsoftnetValidatorsSharedValidationLevenshteinDistanceMessage")
  String deKnightsoftnetValidatorsSharedValidationLevenshteinDistanceMessage();

  /**
   * message used for must be equal validation.
   *
   * @return deKnightsoftnetValidatorsSharedValidationMustBeEqualMessage
   */
  @DefaultStringValue("the value must be equal")
  @Key("deKnightsoftnetValidatorsSharedValidationMustBeEqualMessage")
  String deKnightsoftnetValidatorsSharedValidationMustBeEqualMessage();

  /**
   * message used for must not be equal validation.
   *
   * @return deKnightsoftnetValidatorsSharedValidationMustNotBeEqualMessage
   */
  @DefaultStringValue("the value must not be equal")
  @Key("deKnightsoftnetValidatorsSharedValidationMustNotBeEqualMessage")
  String deKnightsoftnetValidatorsSharedValidationMustNotBeEqualMessage();

  /**
   * message used for must be alternate filled if other has a specified value validation.
   *
   * @return deKnightsoftnetValidatorsSharedValidationNotEmptyAlternateIfOtherHasValueMessage
   */
  @DefaultStringValue("must be alternate filled if {fieldCompare} contains the entry "
      + "\"{valueCompare}\"")
  @Key("deKnightsoftnetValidatorsSharedValidationNotEmptyAlternateIfOtherHasValueMessage")
  String deKnightsoftnetValidatorsSharedValidationNotEmptyAlternateIfOtherHasValueMessage();

  /**
   * message used for must be alternate filled if other is empty validation.
   *
   * @return deKnightsoftnetValidatorsSharedValidationNotEmptyAlternateIfOtherIsEmptyMessage
   */
  @DefaultStringValue("must be alternate filled if {fieldCompare} is empty")
  @Key("deKnightsoftnetValidatorsSharedValidationNotEmptyAlternateIfOtherIsEmptyMessage")
  String deKnightsoftnetValidatorsSharedValidationNotEmptyAlternateIfOtherIsEmptyMessage();

  /**
   * message used for must be alternate filled if other is not empty validation.
   *
   * @return deKnightsoftnetValidatorsSharedValidationNotEmptyAlternateIfOtherIsNotEmptyMessage
   */
  @DefaultStringValue("must be alternate filled if {fieldCompare} is not empty")
  @Key("deKnightsoftnetValidatorsSharedValidationNotEmptyAlternateIfOtherIsNotEmptyMessage")
  String deKnightsoftnetValidatorsSharedValidationNotEmptyAlternateIfOtherIsNotEmptyMessage();

  /**
   * message used for must be empty if other field has a given value check validation.
   *
   * @return deKnightsoftnetValidatorsSharedValidationEmptyIfOtherHasValueMessage
   */
  @DefaultStringValue("must be empty if {fieldCompare} contains the entry \"{valueCompare}\"")
  @Key("deKnightsoftnetValidatorsSharedValidationEmptyIfOtherHasValueMessage")
  String deKnightsoftnetValidatorsSharedValidationEmptyIfOtherHasValueMessage();

  /**
   * message used for must be empty if other field is empty check validation.
   *
   * @return deKnightsoftnetValidatorsSharedValidationEmptyIfOtherIsEmptyMessage
   */
  @DefaultStringValue("must be empty if {fieldCompare} is empty")
  @Key("deKnightsoftnetValidatorsSharedValidationEmptyIfOtherIsEmptyMessage")
  String deKnightsoftnetValidatorsSharedValidationEmptyIfOtherIsEmptyMessage();

  /**
   * message used for must be empty if other field is filled check validation.
   *
   * @return deKnightsoftnetValidatorsSharedValidationEmptyIfOtherIsNotEmptyMessage
   */
  @DefaultStringValue("must be empty if {fieldCompare} is filled")
  @Key("deKnightsoftnetValidatorsSharedValidationEmptyIfOtherIsNotEmptyMessage")
  String deKnightsoftnetValidatorsSharedValidationEmptyIfOtherIsNotEmptyMessage();

  /**
   * message used for must be filled if other field has given value check validation.
   *
   * @return deKnightsoftnetValidatorsSharedValidationNotEmptyIfOtherHasValueMessage
   */
  @DefaultStringValue("must not be empty if {fieldCompare} contains the entry \"{valueCompare}\"")
  @Key("deKnightsoftnetValidatorsSharedValidationNotEmptyIfOtherHasValueMessage")
  String deKnightsoftnetValidatorsSharedValidationNotEmptyIfOtherHasValueMessage();

  /**
   * message used for must be filled if other field is empty check validation.
   *
   * @return deKnightsoftnetValidatorsSharedValidationNotEmptyIfOtherIsEmptyMessage
   */
  @DefaultStringValue("must not be empty if {fieldCompare} is empty")
  @Key("deKnightsoftnetValidatorsSharedValidationNotEmptyIfOtherIsEmptyMessage")
  String deKnightsoftnetValidatorsSharedValidationNotEmptyIfOtherIsEmptyMessage();

  /**
   * message used for must be filled if other field is filled check validation.
   *
   * @return deKnightsoftnetValidatorsSharedValidationNotEmptyIfOtherIsNotEmptyMessage
   */
  @DefaultStringValue("must not be empty if {fieldCompare} is filled")
  @Key("deKnightsoftnetValidatorsSharedValidationNotEmptyIfOtherIsNotEmptyMessage")
  String deKnightsoftnetValidatorsSharedValidationNotEmptyIfOtherIsNotEmptyMessage();

  /**
   * message used for password check validation.
   *
   * @return deKnightsoftnetValidatorsSharedValidationPasswordMessage
   */
  @DefaultStringValue("must be fulfill {minRules} of the following rules: "
      + "upper-/lowercase, digits and special characters")
  @Key("deKnightsoftnetValidatorsSharedValidationPasswordMessage")
  String deKnightsoftnetValidatorsSharedValidationPasswordMessage();

  /**
   * message used for phone number check validation.
   *
   * @return deKnightsoftnetValidatorsSharedValidationPhoneNumberMessage
   */
  @DefaultStringValue("format of phone number is wrong")
  @Key("deKnightsoftnetValidatorsSharedValidationPhoneNumberMessage")
  String deKnightsoftnetValidatorsSharedValidationPhoneNumberMessage();

  /**
   * message used for postal code check validation.
   *
   * @return deKnightsoftnetValidatorsSharedValidationPostalCodeMessage
   */
  @DefaultStringValue("postal code format is wrong for the selected country")
  @Key("deKnightsoftnetValidatorsSharedValidationPostalCodeMessage")
  String deKnightsoftnetValidatorsSharedValidationPostalCodeMessage();

  /**
   * message used for regular expression check validation.
   *
   * @return deKnightsoftnetValidatorsSharedValidationRegularExpressionMessage
   */
  @DefaultStringValue("is no valid regular expression")
  @Key("deKnightsoftnetValidatorsSharedValidationRegularExpressionMessage")
  String deKnightsoftnetValidatorsSharedValidationRegularExpressionMessage();

  /**
   * message used for tax identification number check.
   *
   * @return deKnightsoftnetValidatorsSharedValidationTinMessage
   */
  @DefaultStringValue("no valid tax identification number")
  @Key("deKnightsoftnetValidatorsSharedValidationTinMessage")
  String deKnightsoftnetValidatorsSharedValidationTinMessage();

  /**
   * message used for vat registration number check.
   *
   * @return deKnightsoftnetValidatorsSharedValidationVatIdMessage
   */
  @DefaultStringValue("no valid vat registration number")
  @Key("deKnightsoftnetValidatorsSharedValidationVatIdMessage")
  String deKnightsoftnetValidatorsSharedValidationVatIdMessage();

  /**
   * message used for limit charset check.
   *
   * @return deKnightsoftnetValidatorsSharedValidationLimitCharsetMessage
   */
  @DefaultStringValue("only characters from charset {charset} are allowed")
  @Key("deKnightsoftnetValidatorsSharedValidationLimitCharsetMessage")
  String deKnightsoftnetValidatorsSharedValidationLimitCharsetMessage();

  /**
   * message used for must be smaller check.
   *
   * @return deKnightsoftnetValidatorsSharedValidationMustBeSmallerMessage
   */
  @DefaultStringValue("the values of {field1} must be smaller then the value of {field2}")
  @Key("deKnightsoftnetValidatorsSharedValidationMustBeSmallerMessage")
  String deKnightsoftnetValidatorsSharedValidationMustBeSmallerMessage();

  /**
   * message used for must be smaller or equal check.
   *
   * @return deKnightsoftnetValidatorsSharedValidationMustBeSmallerOrEqualMessage
   */
  @DefaultStringValue("the values of {field1} must be smaller (or equal) then the value of "
      + "{field2}")
  @Key("deKnightsoftnetValidatorsSharedValidationMustBeSmallerOrEqualMessage")
  String deKnightsoftnetValidatorsSharedValidationMustBeSmallerOrEqualMessage();

  /**
   * message used for must be bigger check.
   *
   * @return deKnightsoftnetValidatorsSharedValidationMustBeBiggerMessage
   */
  @DefaultStringValue("the values of {field1} must be bigger then the value of {field2}")
  @Key("deKnightsoftnetValidatorsSharedValidationMustBeBiggerMessage")
  String deKnightsoftnetValidatorsSharedValidationMustBeBiggerMessage();

  /**
   * message used for must be bigger or equal check.
   *
   * @return deKnightsoftnetValidatorsSharedValidationMustBeBiggerOrEqualMessage
   */
  @DefaultStringValue("the values of {field1} must be bigger (or equal) then the value of {field2}")
  @Key("deKnightsoftnetValidatorsSharedValidationMustBeBiggerOrEqualMessage")
  String deKnightsoftnetValidatorsSharedValidationMustBeBiggerOrEqualMessage();
}
