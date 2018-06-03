mt-bean-validators
===================

A collection of JSR-303/JSR-349/JSR-380 bean validators.

Included Validators - field-level constraints
---------------------------------------------

|Annotation | Parameters | Checks
|------|----------|----------
|AgeLimitCheck | `minYears` (no default) | checks a date field, it must be `minYears` ago
|AlternateSize | `size1` and `size2` (no defaults), `ignoreWhiteSpaces`, `ignoreMinus` and `ignoreSlashes` (default false) | instead of the default `Size` validator, which only can set a min and max size, this validator can check two alternate allowd sizes e.G. a GTIN number can be 8 or 13 digits long. With the `ignore*` parameters some characters can be filtered out, before the check is done.
|Bic | `ignoreWhitspaces` (default false) | Size and format check of a Business Identifier Code (BIC-Code or SWIFT-Code), only SWIFT-Countries are allowed
|BicValue | `ignoreWhitspaces` (default false) | Makes not only a formal check, it also compares against a list of registered BIC numbers
|CreditCardNumber | - | uses check routines of apache commons validator to check credit card numbers
|Email | - | uses email-check routines of apache commons validator
|Gln | - | checks length, format and checksum of a Global Location Number (also known as International Location Number)
|Gtin | - | checks length, format and checksum of a Global Trade Item Number, short (8 digits) and long (13 digits) are allowed (Gtin is also known as European Article Number)
|Gtin8 | - | checks length, format and checksum of a Global Trade Item Number, only short version (8 digits) is allowed (Gtin is also known as European Article Number)
|Gtin13 | - | checks length, format and checksum of a Global Trade Item Number, only long (13 digits) are allowed (Gtin is also known as European Article Number)
|Iban | `ignoreWhitspaces` (default false) | checks length, format and checksum of a International Bank Account Number, the country specific length definitions are used, iban must be written in one block
|IbanFormated | - | checks length, format and checksum of a International Bank Account Number, the country specific length definitions are used, iban must be written in groups separated with spaces
|Isbn | `ignoreSeparators` (default false) | checks length, format and checksum of a International Standard Book Number, short (10 digits) and long (13 digits) are allowed, isbn must be written in one block, if `ignoreSeparators` is set to true, minus signs are filtered
|IsbnFormated | - | checks length, numeric and checksum of a International Standard Book Number, short (10 digits) and long (13 digits) are allowed, isbn must be grouped with - as separator
|Isbn10 | `ignoreSeparators` (default false) | checks length, format and checksum of a International Standard Book Number, only short version (10 digits) is allowed, isbn must be written in one block, if `ignoreSeparators` is set to true, minus signs are filtered
|Isbn10Formated | - | checks length, format and checksum of a International Standard Book Number, only short version (10 digits) is allowed, isbn must be grouped with - as separator
|Isbn13 | `ignoreSeparators` (default false) | checks length, format and checksum of a International Standard Book Number, only long version (13 digits) is allowed, isbn must be written in one block, if `ignoreSeparators` is set to true, minus signs are filtered
|Isbn13Formated | - | checks length, format and checksum of a International Standard Book Number, only long version (13 digits) is allowed, isbn must be grouped with - as separator
|Isin | - | checks length, format and checksum of a International Securities Identification Number
|LimitCharset | `charset` (no default) | checks a string if all characters are part of the given `charset` (e.g. ISO-8859-1)
|NotEmptyAfterStrip | `stripcharacters` (" 0") | strips the characters out of the string, result must not be empty or null 
|Password | `minRules` (no default), `blacklist` (default empty), `disalowedStartChars` (default empty) and `maxRepeatChar` (default 0) | checks password for a number of rules, uper case characters, lower case character, digits and special characters, with `minRules` you can define, how many rules must be fulfilled. The `blacklist` can contain comma separated words which are not allowed in the password. With `disalowedStartChars` you can define charactes which are not allowed as first character of the password. With `maxRepeatChar` you can limit the repeat of a single character in a row.
|PhoneNumber | `allowDin5008`, `allowE123`, `allowUri`, `allowMs`, and `allowCommon` (default for all true) | checks phone number, with the parameters you can disable formats
|RegularExpression | - | checks if value itself is a regular expression
|SizeWithoutSeparators | `min` (default 0) and `max` (default max integer), `ignoreWhiteSpaces`, `ignoreMinus` and `ignoreSlashes` (default false) | works like default `Size` validator for Strings, with the `ignore*` parameters some characters can be filtered out, before the check is done.
|Url | - | uses url-check routines of apache commons validator


Included Validators - class-level constraint
--------------------------------------------

|Annotation | Parameters | Checks
|------|----------|----------
|BankCountry | `fieldCountryCode` (default = countryCode), `fieldIban` (default = iban), `fieldBic` (default = bic) and `allowLowerCaseCountryCode` (default: false) | in a sepa bank account, iban and bic contains the country code, both must match and can be validated against another countryCode field
|EmailMustHaveSameDomain | `field1`, `field2` (no defaults), `addErrorToField1` and `addErrorToField2` (default true) | can be used to check if two eMails have the same domain
|EmptyIfOtherHasValue | `field`, `fieldCompare` and `valueCompare` (no defaults) | can be used for dependency checks, `field` must be empty if `fieldCompare` contains `valueCompare`
|EmptyIfOtherIsEmpty | `field` and `fieldCompare` (no defaults) | can be used for dependency checks, `field` must be empty if `fieldCompare` is empty
|EmptyIfOtherIsNotEmpty | `field` and `fieldCompare` (no defaults) | can be used for dependency checks, `field` must be empty if `fieldCompare` is not empty
|LevenshteinDistance | `field1`, `field2`, `minDistance` (no defaults), `addErrorToField1` and `addErrorToField2` (default true) | calculates the levenshtein distance between the values of `field1` and `field2`, it must be equal or greater then the value of `minDistance`
|MustBeEqual | `field1`, `field2` (no defaults), `addErrorToField1` and `addErrorToField2` (default true) | can be used for dependency checks e.G. for two password input fields, `field1` must have the same value as `field2`
|MustNotBeEqual | `field1`, `field2` (no defaults), `addErrorToField1` and `addErrorToField2` (default true) | can be used for dependency checks e.G. for password new and old input fields, `field1` must not have the same value as `field2`
|MustBeBigger | `field1`, `field2` (no defaults), `addErrorToField1` (default true) and `addErrorToField2` (default false) | can be used for range checks e.G. from/to dates, `field2` entry must be bigger then `field1` entry
|MustBeBiggerOrEqual | `field1`, `field2` (no defaults), `addErrorToField1` (default true) and `addErrorToField2` (default false) | can be used for range checks e.G. from/to dates, `field2` entry must be bigger or equal then `field1` entry
|MustBeSmaller | `field1`, `field2` (no defaults), `addErrorToField1` (default true) and `addErrorToField2` (default false) | can be used for range checks e.G. from/to dates, `field1` entry must be smaller then `field2` entry
|MustBeSmallerOrEqual | `field1`, `field2` (no defaults), `addErrorToField1` (default true) and `addErrorToField2` (default false) | can be used for range checks e.G. from/to dates, `field1` entry must be smaller or equal then `field2` entry
|NotEmptyAlternateIfOtherHasValue | `field`, `fieldAlternate`, `fieldCompare` and `valueCompare` (no defaults) | can be used for dependency checks, `field` or alternate `fieldAlternate` must be filled if `fieldCompare` has the value `valueCompare`
|NotEmptyAlternateIfOtherIsEmpty | `field`, `fieldAlternate` and `fieldCompare` (no defaults) | can be used for dependency checks, `field` or alternate `fieldAlternate` must be filled if `fieldCompare` is empty
|NotEmptyAlternateIfOtherIsNotEmpty | `field`, `fieldAlternate` and `fieldCompare` (no defaults) | can be used for dependency checks, `field` or alternate `fieldAlternate` must be filled if `fieldCompare` is not empty
|NotEmptyIfOtherHasValue | `field`, `fieldCompare` and `valueCompare` (no defaults) | can be used for dependency checks, `field` must be filled if `fieldCompare` contains `valueCompare`
|NotEmptyIfOtherIsEmpty | `field` and `fieldCompare` (no defaults) | can be used for dependency checks, `field` must be filled if `fieldCompare` is empty
|NotEmptyIfOtherIsNotEmpty | `field` and `fieldCompare` (no defaults) | can be used for dependency checks, `field` must be filled if `fieldCompare` is not empty
|PhoneNumberValue | `fieldCountryCode` (default: countryCode), `fieldPhoneNumber` (default phoneNumber), `allowLowerCaseCountryCode` (default: false), `allowDin5008`, `allowE123`, `allowUri`, `allowMs`, and `allowCommon` (default for all true) | checks phone number based on country- and area code data, with the parameters you can disable formats
|PostalCode | `fieldCountryCode` (default: countryCode), `fieldPostalCode` (default postalCode) and `allowLowerCaseCountryCode` (default: false) | postal code, post code or zip is validated using country specific rules, the country code is read from `fieldCountryCode`, there are rules for 161 countries included
|TaxNumber | `fieldCountryCode` (default: countryCode), `fieldTaxNumber` (default taxNumber) and `allowLowerCaseCountryCode` (default: false) | Tax Number is used in different countries and has country depending tests of format and checksums. Most countries have only Tax Identification Number and no separate Tax Number, in this case this validation also works
|Tin | `fieldCountryCode` (default: countryCode), `fieldTin` (default tin) and `allowLowerCaseCountryCode` (default: false) | Tax Identification Number (TIN) is used in different countries and has country depending tests of format and checksums 
|VatId | `fieldCountryCode` (default: countryCode), `fieldVatId` (default vatId) and `allowLowerCaseCountryCode` (default: false) | vat registration number is validated using country specific rules, the country code is read from `fieldCountryCode`, there are format rules for 49 and checksum checks for 16 countries included


Maven integraten
----------------

Add the following to your POM file, that's all:

```
    <dependency>
      <groupId>de.knightsoft-net</groupId>
      <artifactId>mt-bean-validators</artifactId>
      <version>0.55.3</version>
    </dependency>
```

With version 0.20.0 I've switched from validation-api 1.0 to 1.1 and with 0.50.0 to validation-api 2.0. If your project needs api 1.0 compatibility, please use version 0.10.1 of this package.
