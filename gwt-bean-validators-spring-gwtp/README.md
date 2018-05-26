gwt-bean-validators-spring-gwtp
==================================

This package contains the rest service based on spring rest controller and gwtp rest dispatcher for phone number services and the PhoneNumberValueRest validation routine which use it to keep client side code small. It includes [gwt-bean-validators](https://github.com/ManfredTremmel/gwt-bean-validators) which provides all the other stuff.

With version 0.55.0 the spring integration stuff was moved into [gwtp-spring-integration](https://github.com/ManfredTremmel/gwtp-spring-integration) package, to solve failed imports replace `de.knightsoftnet.navigation.*` and `de.knightsoftnet.validators.*` with `de.knightsoftnet.gwtp.spring.*`.

Included Validators - multi fields
----------------------------------

|Annotation | Parameters | Checks
|------|----------|----------
|PhoneNumberValueRest | `fieldCountryCode` (default: countryCode), `fieldPhoneNumber` (default phoneNumber), `allowLowerCaseCountryCode` (default: false), `allowDin5008`, `allowE123`, `allowUri`, `allowMs`, and `allowCommon` (default for all true) | same as PhoneNumberValue, but check is done on server using rest call to keep client code small



Maven integraten
----------------

The dependency itself for GWT-Projects:

```
    <dependency>
      <groupId>de.knightsoft-net</groupId>
      <artifactId>gwt-bean-validators-spring-gwtp</artifactId>
      <version>0.55.0</version>
    </dependency>
```

GWT Integration
---------------

```
<inherits name="de.knightsoftnet.validators.GwtBeanValidatorsSpringGwtp" />
```
