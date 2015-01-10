gwt-bean-validators
===================

A collection of JSR-303 bean validators that can also be used on the client side of gwt

Included Validators - single field
----------------------------------

|Annotation | Parameters | Checks
|------|----------|----------
|AgeLimitCheck | `minYears` (no default) | checks a date field, it must be `minYears` ago
|AlternateSize | `size1` and `size2` (no defaults) | instead of the default `Size` validator, which only can set a min and max size, this validator can check two alternate allowd sizes e.G. a GTIN number can be 8 or 13 digits long.
|Bic | `ignoreWhitspaces` (default false) | Size and format check of a Business Identifier Code (BIC-Code or SWIFT-Code), only SWIFT-Countries are allowed
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
|Password | `minRules` | checks password for a number of rules, uper/lower case character, digits and special characters, with `minRules` you can define, how many rules must be fullfilled
|RegularExpression | - | checks if value itself is a regular expression
|Url | - | uses url-check routines of apache commons validator


Included Validators - multi fields
----------------------------------

|Annotation | Parameters | Checks
|------|----------|----------
|BankCountry | `fieldCountryCode` (default = countryCode), `fieldIban` (default = iban), `fieldBic` (default = bic) and `allowLowerCaseCountryCode` (default: false) | in a sepa bank account, iban and bic contains the country code, both must match and can be validated against another countryCode field
|EmptyIfOtherHasValue | `field`, `fieldCompare` and `valueCompare` (no defaults) | can be used for dependency checks, `field` must be empty if `fieldCompare` contains `valueCompare`
|EmptyIfOtherIsEmpty | `field` and `fieldCompare` (no defaults) | can be used for dependency checks, `field` must be empty if `fieldCompare` is empty
|EmptyIfOtherIsNotEmpty | `field` and `fieldCompare` (no defaults) | can be used for dependency checks, `field` must be empty if `fieldCompare` is not empty
|NotEmptyAlternateIfOtherHasValue | `field`, `fieldAlternate`, `fieldCompare` and `valueCompare` (no defaults) | can be used for dependency checks, `field` or alternate `fieldAlternate` must be filled if `fieldCompare` has the value `fieldCompare`
|NotEmptyAlternateIfOtherIsEmpty | `field`, `fieldAlternate` and `fieldCompare` (no defaults) | can be used for dependency checks, `field` or alternate `fieldAlternate` must be filled if `fieldCompare` is empty
|NotEmptyAlternateIfOtherIsNotEmpty | `field`, `fieldAlternate` and `fieldCompare` (no defaults) | can be used for dependency checks, `field` or alternate `fieldAlternate` must be filled if `fieldCompare` is not empty
|NotEmptyIfOtherHasValue | `field`, `fieldCompare` and `valueCompare` (no defaults) | can be used for dependency checks, `field` must be filled if `fieldCompare` contains `valueCompare`
|NotEmptyIfOtherIsEmpty | `field` and `fieldCompare` (no defaults) | can be used for dependency checks, `field` must be filled if `fieldCompare` is empty
|NotEmptyIfOtherIsNotEmpty | `field` and `fieldCompare` (no defaults) | can be used for dependency checks, `field` must be filled if `fieldCompare` is not empty
|PostalCode | `fieldCountryCode` (default: countryCode), `fieldPostalCode` (default postalCode) and `allowLowerCaseCountryCode` (default: false) | postal code, post code or zip is validated using country specific rules, the country code is read from `fieldCountryCode`, there are rules for 161 countries included
|VatId | `fieldCountryCode` (default: countryCode), `fieldVatId` (default vatId) and `allowLowerCaseCountryCode` (default: false) | vat registration number is validated using country specific rules, the country code is read from `fieldCountryCode`, there are format rules for 29 and checksum checks for 15 countries  included


Dependencies
------------
If you want to use this validators only on server side, you needn't add any gwt libraries, the code of the validators itself uses no gwt specific functions. You simply can add [apache-commons-lang3 3.3.2](https://commons.apache.org/proper/commons-lang/), [apache-commons-validator 1.4.0](https://commons.apache.org/proper/commons-validator/) and [apache-commons-beanutils-core 1.8.3] (https://commons.apache.org/proper/commons-beanutils/).
On the gwt frontend side, you have to include my [gwt-commons-lang3 3.3.2-SNAPSHOT](https://github.com/ManfredTremmel/gwt-commons-lang3) and [gwt-commons-validators 1.4.0-SNAPSHOT](https://github.com/ManfredTremmel/gwt-commons-validator) src.jars additional and the corresponding binary jars as replacement of apache-commons-lang3 and apache-commons-validator (optional), this are gwt-ports of the same libraries. If you use Maven, it will include everything automaticly for you.

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
      <version>0.6.7</version>
    </dependency>
```

GWT Integration
---------------

The basics of Bean Validation in GWT you can find on the [GWT-Project Page](http://www.gwtproject.org/doc/latest/DevGuideValidation.html). This package should make it easier to enable bean validation. All depending libs are provided by maven (if you use it) and a message resolver with localized texts ([English](https://github.com/ManfredTremmel/gwt-bean-validators/blob/master/src/main/resources/de/knightsoftnet/validators/client/ValidationMessages.properties) and [German](https://github.com/ManfredTremmel/gwt-bean-validators/blob/master/src/main/resources/de/knightsoftnet/validators/client/ValidationMessages_de.properties) at the moment, more translations are welcome, just translate the property file) is already activated, but you can override it in your project .gwt.xml file.

What you still have to do, inherit GwtBeanValidators into your project .gwt.xml file:

```
<inherits name="de.knightsoftnet.validators.GwtBeanValidators" />
```

Because we don't have Reflections in GWT, you have to add a Validator Factory Implementation with all your beans to validate, annotated with @GwtValidation. As Example you can take a look in the test cases of this Project: [ValidatorFactory.java](https://github.com/ManfredTremmel/gwt-bean-validators/blob/master/src/test/java/de/knightsoftnet/validators/client/factories/ValidatorFactory.java) you have to add this Factory as replacement for javax.validation.ValidatorFactory, you can see this also in the [Project .gwt.xml file of the test cases](https://github.com/ManfredTremmel/gwt-bean-validators/blob/master/src/test/resources/de/knightsoftnet/validators/GwtBeanValidatorsJUnit.gwt.xml)

Multi value annotations, which are not annotated on the property field itself, but on the top of the bean, need still access to the properties to check. To make this work without reflections, I've included a code generator which generates a helper class out of the beans and a BeanUtil replacement which uses it, so the Validators can work with the server side common `BeanUtils.getProperty(bean, name)` even on the client. To tell the generator which beans need such access, you also have to generate a Factory class. It looks nearly the same way as the ValidatoryFactory and has also to be annotated with @GwtValidation which includes the beans. The version in the test cases should show you, how to use it: [ReflectGetterFactory.java](https://github.com/ManfredTremmel/gwt-bean-validators/blob/master/src/test/java/de/knightsoftnet/validators/client/factories/ReflectGetterFactory.java), it has to replace de.knightsoftnet.validators.client.GwtReflectGetterFactoryInterface in the project gwt.xml file, you can see this also in the [Project .gwt.xml file of the test cases](https://github.com/ManfredTremmel/gwt-bean-validators/blob/master/src/test/resources/de/knightsoftnet/validators/GwtBeanValidatorsJUnit.gwt.xml)

Because we never can trust client side checks, this checks always should be repeated on server side. To bring back validation results to the frontend, to display them with the editor framework, I've also included the [ValidationException.java](https://github.com/ManfredTremmel/gwt-bean-validators/blob/master/src/main/java/de/knightsoftnet/validators/shared/exceptions/ValidationException.java). If you use RPC, you can just add the set of validation errors to the constructor of the bean and throw it, on client side catch it and add the results to the editor.
