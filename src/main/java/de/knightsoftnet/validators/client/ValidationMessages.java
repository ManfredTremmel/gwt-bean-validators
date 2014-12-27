package de.knightsoftnet.validators.client;

import com.google.gwt.i18n.client.ConstantsWithLookup;

/**
 * The <code>ValidationMessages</code> contains the messages for jsr 303 validation annotations.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 */
public interface ValidationMessages extends ConstantsWithLookup {

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
   * message used for vat registration number check.
   *
   * @return deKnightsoftnetValidatorsSharedValidationVatIdMessage
   */
  @DefaultStringValue("no valid vat registration number")
  @Key("deKnightsoftnetValidatorsSharedValidationVatIdMessage")
  String deKnightsoftnetValidatorsSharedValidationVatIdMessage();
}
