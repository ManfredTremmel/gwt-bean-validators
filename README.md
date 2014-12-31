gwt-bean-validators
===================

A collection of JSR-303 bean validators that can also be used on the client side of gwt

Included Validators - single field
----------------------------------

|Annotation | Parameters | Checks
|------|----------|----------
|AgeLimit | `minYears` (no default) | checks a date field, it must be `minYears` ago
|AlternateSize | `size1` and `size2` (no defaults) | instead of the default `Size` validator, which only can set a min and max size, this validator can check two alternate allowd sizes e.G. a GTIN number can be 8 or 13 digits long.
|Bic | - | Size and format check of a Business Identifier Code (BIC-Code or SWIFT-Code), only SWIFT-Countries are allowed
|CreditCardNumber | - | uses check routines of apache commons validator to check credit card numbers
|Email | - | formal email check
|Gln | - | checks length, format and checksum of a Global Location Number (also known as International Location Number)
|Gtin | - | checks length, format and checksum of a Global Trade Item Number, short (8 digits) and long (13 digits) are allowed (Gtin is also known as European Article Number)
|Gtin8 | - | checks length, format and checksum of a Global Trade Item Number, only short version (8 digits) is allowed (Gtin is also known as European Article Number)
|Gtin13 | - | checks length, format and checksum of a Global Trade Item Number, only long (13 digits) are allowed (Gtin is also known as European Article Number)
|Iban | - | checks length, format and checksum of a International Bank Account Number, the country specific length definitions are used, iban must be written in one block
|IbanFormated | - | checks length, format and checksum of a International Bank Account Number, the country specific length definitions are used, iban must be written in groups separated with spaces
|Isbn | - | checks length, format and checksum of a International Standard Book Number, short (10 digits) and long (13 digits) are allowed, isbn must be written in one block
|IsbnFormated | - | checks length, numeric and checksum of a International Standard Book Number, short (10 digits) and long (13 digits) are allowed, isbn must be grouped with - as separator
|Isbn10 | - | checks length, format and checksum of a International Standard Book Number, only short version (10 digits) is allowed, isbn must be written in one block
|Isbn10Formated | - | checks length, format and checksum of a International Standard Book Number, only short version (10 digits) is allowed, isbn must be grouped with - as separator
|Isbn13 | - | checks length, format and checksum of a International Standard Book Number, only long version (13 digits) is allowed, isbn must be written in one block
|Isbn13Formated | - | checks length, format and checksum of a International Standard Book Number, only long version (13 digits) is allowed, isbn must be grouped with - as separator
|Isin | - | checks length, format and checksum of a International Securities Identification Number
|Password | `minRules` | checks password for a number of rules, uper/lower case character, digits and special characters, with `minRules` you can define, how many rules must be fullfilled 
|RegularExpression | - | checks if value itself is a regular expression
|Url | - | formal url check


Included Validators - multi fields
----------------------------------

|Annotation | Parameters | Checks
|------|----------|----------
|BankCountry | `fieldCountryCode` (default = countryCode), `fieldIban` (default = iban), `fieldBic` (default = bic), | in a sepa bank account, iban and bic contains the country code, both must match and can be validated against another countryCode field
|EmptyIfOtherHasValue | `field`, `fieldCompare` and `valueCompare` (no defaults) | can be used for dependency checks, `field` must be empty if `fieldCompare` contains `valueCompare`
|EmptyIfOtherIsEmpty | `field` and `fieldCompare` (no defaults) | can be used for dependency checks, `field` must be empty if `fieldCompare` is empty
|EmptyIfOtherIsNotEmpty | `field` and `fieldCompare` (no defaults) | can be used for dependency checks, `field` must be empty if `fieldCompare` is not empty
|NotEmptyAlternateIfOtherHasValue | `field`, `fieldAlternate`, `fieldCompare` and `valueCompare` (no defaults) | can be used for dependency checks, `field` or alternate `fieldAlternate` must be filled if `fieldCompare` has the value `fieldCompare`
|NotEmptyAlternateIfOtherIsEmpty | `field`, `fieldAlternate` and `fieldCompare` (no defaults) | can be used for dependency checks, `field` or alternate `fieldAlternate` must be filled if `fieldCompare` is empty
|NotEmptyAlternateIfOtherIsNotEmpty | `field`, `fieldAlternate` and `fieldCompare` (no defaults) | can be used for dependency checks, `field` or alternate `fieldAlternate` must be filled if `fieldCompare` is not empty
|NotEmptyIfOtherHasValue | `field`, `fieldCompare` and `valueCompare` (no defaults) | can be used for dependency checks, `field` must be filled if `fieldCompare` contains `valueCompare`
|NotEmptyIfOtherIsEmpty | `field` and `fieldCompare` (no defaults) | can be used for dependency checks, `field` must be filled if `fieldCompare` is empty
|NotEmptyIfOtherIsNotEmpty | `field` and `fieldCompare` (no defaults) | can be used for dependency checks, `field` must be filled if `fieldCompare` is not empty
|PostalCode | `fieldCountryCode` (default: countryCode) and `fieldPostalCode` (default postalCode) | postal code, post code or zip is validated using country specific rules, the country code is read from `fieldCountryCode`, there are rules for 161 countries included
|VatId | `fieldCountryCode` (default: countryCode) and `fieldVatId` (default vatId) | vat registration number is validated using country specific rules, the country code is read from `fieldCountryCode`, there are format rules for 29 and checksum checks for 15 countries  included


Dependencies
------------
If you wan't to use this validators only on server side, you needn't add any gwt libraries, the code of the validators itself uses no gwt specific functions. You simply can add apache-commons-lang3 3.3.2, apache-commons-validator 1.4.0 and apache-commons-beanutils-core 1.8.3.
On the gwt frontend side, you have to include my gwt-commons-lang3 3.3.2-SNAPSHOT and gwt-commons-validators 1.4.0-SNAPSHOT src.jars instead. Maven will include them automaticly.

Maven integraten
----------------

The build packages are currently not on a indexed maven repository, but you can add my private repository with the following settings:

```
  <repositories>
    <repository>    
      <id>de.knightsoft-net</id>    
      <url>http://www.knightsoft-net.de/maven/</url>    
      <snapshots>      
        <enabled>true</enabled> 
      </snapshots>    
      <releases>      
        <enabled>true</enabled>
      </releases>  
    </repository>
  </repositories>
```

The dependency itself:

```
    <dependency>
      <groupId>gwt-bean-validators</groupId>
      <artifactId>gwt-bean-validators</artifactId>
      <version>0.5.5</version>
    </dependency>
```

GWT Integration
---------------

Add this inherit command into your project .gwt.xml file:

```
<inherits name="de.knightsoftnet.validators.GwtBeanValidators" />
```
