gwt-bean-validators-restygwt-jaxrs
==================================

This package contains the rest service based on jaxrs and restygwt for phone number services and the PhoneNumberValueRest validation routine which use it to keep client side code small. It includes [gwt-bean-validators](https://github.com/ManfredTremmel/gwt-bean-validators/gwt-bean-validators) which provides all the other stuff. 

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
      <artifactId>gwt-bean-validators-restygwt-jaxrs</artifactId>
      <version>0.55.0</version>
    </dependency>
```


GWT Integration
---------------

```
<inherits name="de.knightsoftnet.validators.GwtBeanValidatorsResty" />
```
