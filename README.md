gwt-bean-validators
===================

A collection of JSR-303 bean validators that can also be used on the client side of gwt

Included Validators - single field
----------------------------------

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
|Password | `minRules` (no default), `blacklist` (default empty), `disalowedStartChars` (default empty) and `maxRepeatChar` (default 0) | checks password for a number of rules, uper case characters, lower case character, digits and special characters, with `minRules` you can define, how many rules must be fulfilled. The `blacklist` can contain comma separated words which are not allowed in the password. With `disalowedStartChars` you can define charactes which are not allowed as first character of the password. With `maxRepeatChar` you can limit the repeat of a single character in a row.
|PhoneNumber | `allowDin5008`, `allowE123`, `allowUri`, `allowMs`, and `allowCommon` (default for all true) | checks phone number, with the parameters you can disable formats
|RegularExpression | - | checks if value itself is a regular expression
|SizeWithoutSeparators | `min` (default 0) and `max` (default max integer), `ignoreWhiteSpaces`, `ignoreMinus` and `ignoreSlashes` (default false) | works like default `Size` validator for Strings, with the `ignore*` parameters some characters can be filtered out, before the check is done.
|Url | - | uses url-check routines of apache commons validator


Included Validators - multi fields
----------------------------------

|Annotation | Parameters | Checks
|------|----------|----------
|BankCountry | `fieldCountryCode` (default = countryCode), `fieldIban` (default = iban), `fieldBic` (default = bic) and `allowLowerCaseCountryCode` (default: false) | in a sepa bank account, iban and bic contains the country code, both must match and can be validated against another countryCode field
|EmptyIfOtherHasValue | `field`, `fieldCompare` and `valueCompare` (no defaults) | can be used for dependency checks, `field` must be empty if `fieldCompare` contains `valueCompare`
|EmptyIfOtherIsEmpty | `field` and `fieldCompare` (no defaults) | can be used for dependency checks, `field` must be empty if `fieldCompare` is empty
|EmptyIfOtherIsNotEmpty | `field` and `fieldCompare` (no defaults) | can be used for dependency checks, `field` must be empty if `fieldCompare` is not empty
|LevenshteinDistance | `field1`, `field2`, `minDistance` (no defaults), `addErrorToField1` and `addErrorToField2` (default true) | calculates the levenshtein distance between the values of `field1` and `field2`, it must be equal or greater then the value of `minDistance`
|MustBeEqual | `field1`, `field2` (no defaults), `addErrorToField1` and `addErrorToField2` (default true) | can be used for dependency checks e.G. for two password input fields, `field2` must have the same value as `field2`
|MustNotBeEqual | `field1`, `field2` (no defaults), `addErrorToField1` and `addErrorToField2` (default true) | can be used for dependency checks e.G. for password new and old input fields, `field2` must not have the same value as `field2`
|NotEmptyAlternateIfOtherHasValue | `field`, `fieldAlternate`, `fieldCompare` and `valueCompare` (no defaults) | can be used for dependency checks, `field` or alternate `fieldAlternate` must be filled if `fieldCompare` has the value `fieldCompare`
|NotEmptyAlternateIfOtherIsEmpty | `field`, `fieldAlternate` and `fieldCompare` (no defaults) | can be used for dependency checks, `field` or alternate `fieldAlternate` must be filled if `fieldCompare` is empty
|NotEmptyAlternateIfOtherIsNotEmpty | `field`, `fieldAlternate` and `fieldCompare` (no defaults) | can be used for dependency checks, `field` or alternate `fieldAlternate` must be filled if `fieldCompare` is not empty
|NotEmptyIfOtherHasValue | `field`, `fieldCompare` and `valueCompare` (no defaults) | can be used for dependency checks, `field` must be filled if `fieldCompare` contains `valueCompare`
|NotEmptyIfOtherIsEmpty | `field` and `fieldCompare` (no defaults) | can be used for dependency checks, `field` must be filled if `fieldCompare` is empty
|NotEmptyIfOtherIsNotEmpty | `field` and `fieldCompare` (no defaults) | can be used for dependency checks, `field` must be filled if `fieldCompare` is not empty
|PhoneNumberValue | `fieldCountryCode` (default: countryCode), `fieldPhoneNumber` (default phoneNumber), `allowLowerCaseCountryCode` (default: false), `allowDin5008`, `allowE123`, `allowUri`, `allowMs`, and `allowCommon` (default for all true) | checks phone number based on country- and area code data, with the parameters you can disable formats
|PostalCode | `fieldCountryCode` (default: countryCode), `fieldPostalCode` (default postalCode) and `allowLowerCaseCountryCode` (default: false) | postal code, post code or zip is validated using country specific rules, the country code is read from `fieldCountryCode`, there are rules for 161 countries included
|VatId | `fieldCountryCode` (default: countryCode), `fieldVatId` (default vatId) and `allowLowerCaseCountryCode` (default: false) | vat registration number is validated using country specific rules, the country code is read from `fieldCountryCode`, there are format rules for 49 and checksum checks for 16 countries included


Dependencies
------------
If you want to use this validators only on server side, you needn't add any gwt libraries, the code of the validators itself uses no gwt specific functions. You simply can add [apache-commons-lang3 3.4](https://commons.apache.org/proper/commons-lang/), [apache-commons-validator 1.4.1](https://commons.apache.org/proper/commons-validator/) and [apache-commons-beanutils-core 1.9.2] (https://commons.apache.org/proper/commons-beanutils/).
On the gwt frontend side, you have to include my [gwt-commons-lang3 3.4-1](https://github.com/ManfredTremmel/gwt-commons-lang3) and [gwt-commons-validators 1.5.0-1](https://github.com/ManfredTremmel/gwt-commons-validator) src.jars additional and the corresponding binary jars as replacement of apache-commons-lang3 and apache-commons-validator (optional), this are gwt-ports of the same libraries. If you use Maven, it will include everything automaticly for you.

Maven integraten
----------------

The dependency itself for GWT-Projects:

```
    <dependency>
      <groupId>de.knightsoft-net</groupId>
      <artifactId>gwt-bean-validators</artifactId>
      <version>0.9.2</version>
    </dependency>
    <dependency>
      <groupId>de.knightsoft-net</groupId>
      <artifactId>gwt-commons-lang3</artifactId>
      <version>3.4-1</version>
      <classifier>sources</classifier>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>de.knightsoft-net</groupId>
      <artifactId>gwt-commons-validator</artifactId>
      <version>1.5.0-1</version>
      <classifier>sources</classifier>
      <scope>provided</scope>
    </dependency>
```
For non GWT-Projects you can get rid of all gwt dependencies and use the original apache commons libs:

```
    <dependency>
      <groupId>de.knightsoft-net</groupId>
      <artifactId>gwt-bean-validators</artifactId>
      <version>0.9.2</version>
      <exclusions>
        <exclusion>
          <groupId>com.google.gwt</groupId>
          <artifactId>gwt-servlet</artifactId>
        </exclusion>
        <exclusion>
          <groupId>de.knightsoft-net</groupId>
          <artifactId>gwt-commons-lang3</artifactId>
        </exclusion>
        <exclusion>
          <groupId>de.knightsoft-net</groupId>
          <artifactId>gwt-commons-validator</artifactId>
        </exclusion>
      </exclusions>
    </dependency>
    <dependency>
      <groupId>org.apache.commons</groupId>
      <artifactId>commons-lang3</artifactId>
      <version>3.4</version>
    </dependency>
    <dependency>
      <groupId>commons-validator</groupId>
      <artifactId>commons-validator</artifactId>
      <version>1.5.0</version>
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

For client side validation I've added the [BeanValidationEditorDriver.java](https://github.com/ManfredTremmel/gwt-bean-validators/blob/master/src/main/java/de/knightsoftnet/validators/client/editor/BeanValidationEditorDriver.java), if you use it as driver for the editor framework, validation is made automaticaly on every change or keyup (if widget suports it). With setSubmitButton(widget) you can add a submit button which is enabled or disabled, depending on the validation results, only if the results are valid, it's enabled.
Two Decorators, the decent [UniversalDecorator.java](https://github.com/ManfredTremmel/gwt-bean-validators/blob/master/src/main/java/de/knightsoftnet/validators/client/decorators/UniversalDecorator.java) and the more decorativ [UniversalDecoratorWithIcon.java](https://github.com/ManfredTremmel/gwt-bean-validators/blob/master/src/main/java/de/knightsoftnet/validators/client/decorators/UniversalDecoratorWithIcons.java) can be used to display the validation results on every input field. For own Designs, take [UniversalDecoratorWithIcon.java](https://github.com/ManfredTremmel/gwt-bean-validators/blob/master/src/main/java/de/knightsoftnet/validators/client/decorators/UniversalDecoratorWithIcons.java) as base and replace the Stylesheet. In the UiBinder you simply surround your widget with the Decorator tags like this:

```
<!DOCTYPE ui:UiBinder SYSTEM "http://dl.google.com/gwt/DTD/xhtml.ent">
<ui:UiBinder xmlns:ui="urn:ui:com.google.gwt.uibinder"
  xmlns:g="urn:import:com.google.gwt.user.client.ui"
  xmlns:e="urn:import:de.knightsoftnet.validators.client.decorators">
...
        <e:UniversalDecoratorWithIcons errorLocation="RIGHT" ui:field="checkbox" >
          <e:widget>
            <g:CheckBox />
          </e:widget>
        </e:UniversalDecoratorWithIcons>
...
</ui:UiBinder>
```

I've added a little [example application on GitHub](https://github.com/ManfredTremmel/gwt-bean-validators-example), where you can see, how to use bean validation on client and server side.
