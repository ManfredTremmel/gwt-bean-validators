gwt-bean-validators
===================

A collection of JSR-303/JSR-349/JSR-380 bean validators that can also be used on the client side of gwt. It also includes missing functionality in gwt like getter reflection, editor component which validates entries on typing, on change or on submit and decorators to display errors inside the form.

Starting with version 0.20.0, gwt-bean-validators replaces the bean validation implementation of gwt, because it's outdated (validation-api 1.0.0.GA and hibernate-validator 4.1.0) and should be removed in future gwt versions (deprecated since gwt 2.8-rc1). It started as one by one copy and was updated to current validation-api 1.1.0 (meanwhile 2.0.1) and hibernate-validator 5.2.4 (meanwhile 6.0.9), so switching to new version is easy (see below: GWT Integration). If you prefer the internal gwt validation, you can still use gwt-bean-validators 0.10.1.
Hibernate-validator 6 requires java 8, so we also require gwt > 2.8, if you use gwt 2.7 or older, please use gwt-bean-validators 0.45.0. 

A list of the included validators you can find in the [mt-bean-validators](https://github.com/ManfredTremmel/gwt-bean-validators/mt-bean-validators) project. Also the hibernate-validator validation routines have replacements if needed, and work on client side expect @Future and @Past validation for types which are not supported in gwt (you can use it with java.util.Date, but not with java.time.chrono.ChronoZonedDateTime or org.joda.time.ReadableInstant) and the @ParameterScriptAssert, @ScriptAssert and @SafeHtml validations are also not supported at the moment. With hibernet-validator 5.4.0 support for JavaMoney was added, because of a missing JavaMoney Implementation for gwt, there is also no support for the @Currency validation and the @Min and @Max validation for the monetary types. If anybody knows a JavaMoney port for gwt, let me know.


Supported Validation Annotations
--------------------------------
A list of the included validators you can find in the [mt-bean-validators](https://github.com/ManfredTremmel/gwt-bean-validators/mt-bean-validators) project, they all work fine with GWT. Also the [hibernate-validator](https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#section-builtin-constraints) routines are nearly complete usable. Here's a list of the tested routines:

|Annotation | Support state 
|------|------------------
|AssertFalse | works
|AssertTrue | works
|DecimalMax | works
|DecimalMin | works
|Digits | works
|Email | works
|Future | works, limited to Date and Calendar because of limited JRE support of other types
|FutureOrPresent | works, limited to Date and Calendar because of limited JRE support of other types
|Max | works
|Min | works
|NotBlank | works
|NotEmpty | works
|NotNull | works
|Negative | works
|NegativeOrZero | works
|Null | works
|Past | works, limited to Date and Calendar because of limited JRE support of other types
|PastOrPresent | works, limited to Date and Calendar because of limited JRE support of other types
|Pattern | works
|Positive | works
|PositiveOrZero | works
|Size | works
|Currency | not supported because of a missing JavaMoney implementation
|DurationMax | not supported because of missing java.time.Duration implementation
|DurationMin | not supported because of missing java.time.Duration implementation
|EAN | works
|ISBN | works
|Length | works
|CodePointLength | works
|LuhnCheck | works
|Mod10Check | works
|Mod11Check | works
|Range | works
|SafeHtml | not supported
|ScriptAssert | not supported
|UniqueElements | works
|URL | works
|CNPJ | works
|CPF | works
|TituloEleitoral | works
|NIP | works
|PESEL | works
|REGON | works


Dependencies
------------
If you want to use this validators only on server side, you needn't add any gwt libraries, the code of the validators itself uses no gwt specific functions, include [mt-bean-validators](https://github.com/ManfredTremmel/gwt-bean-validators/mt-bean-validators) instead of gwt-bean-validators.
On the gwt frontend side, apache-commons-lang3 and apache-commons-validators are replaced with my [gwt-commons-lang3](https://github.com/ManfredTremmel/gwt-commons-lang3) and [gwt-commons-validators](https://github.com/ManfredTremmel/gwt-commons-validator) packages, you shouldn't add both in your project.

Maven integration
----------------

Add the dependencies itself for GWT-Projects:

```
    <dependency>
      <groupId>javax.validation</groupId>
      <artifactId>validation-api</artifactId>
      <version>2.0.1.Final</version>
    </dependency>
    <dependency>
      <groupId>javax.validation</groupId>
      <artifactId>validation-api</artifactId>
      <version>2.0.1.Final</version>
      <classifier>sources</classifier>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>de.knightsoft-net</groupId>
      <artifactId>gwt-bean-validators</artifactId>
      <version>0.55.3</version>
    </dependency>
```

And exclude **validation-api** from your **gwt-user** dependency (otherwise old 1.0.0.GA will be included):

```
    <dependency>
      <groupId>com.google.gwt</groupId>
      <artifactId>gwt-user</artifactId>
      <version>2.8.2</version>
      <exclusions>
        <exclusion>
          <groupId>javax.validation</groupId>
          <artifactId>validation-api</artifactId>
        </exclusion>
      </exclusions>
    </dependency>
```
For non GWT-Projects you can use [mt-bean-validators](https://github.com/ManfredTremmel/gwt-bean-validators/mt-bean-validators) instead, which contains only the validators and has no dependencies to gwt.

GWT Integration
---------------

The basics of Bean Validation in GWT you can find on the [GWT-Project Page](http://www.gwtproject.org/doc/latest/DevGuideValidation.html), the gwt-bean-validators implementation works nearly the same, just replace all `com.google.gwt.validation` classes with `de.knightsoftnet.validators` version (in existing projects you can search and replace it in the complete project). All depending libs are provided by maven (if you use it) and a message resolver with localized texts ([English](https://github.com/ManfredTremmel/gwt-bean-validators/mt-bean-validators/blob/master/src/main/resources/de/knightsoftnet/validators/client/ValidationMessages.properties) and [German](https://github.com/ManfredTremmel/gwt-bean-validators/mt-bean-validators/blob/master/src/main/resources/de/knightsoftnet/validators/client/ValidationMessages_de.properties) at the moment, more translations are welcome, just translate the property file) is already activated, but you can override it in your project .gwt.xml file.

What you still have to do, inherit GwtBeanValidators into your project .gwt.xml file and remove (if exists) the inherits entries for `org.hibernate.validator.validator.NewHibernateValidator`, `javax.validation.Validation` and `com.google.gwt.validation.Validation`, they all conflict with the gwt-bean-validators validation implementation:

```
<inherits name="de.knightsoftnet.validators.GwtBeanValidators" />
```

Because we don't have Reflections in GWT, you have to add a Validator Factory Implementation with all your beans to validate, annotated with @GwtValidation. As Example you can take a look in the test cases of this project: [ValidatorFactory.java](https://github.com/ManfredTremmel/gwt-bean-validators/gwt-bean-validators/blob/master/src/test/java/de/knightsoftnet/validators/client/factories/ValidatorFactory.java) you have to add this Factory as replacement for `javax.validation.ValidatorFactory`.
The generated Validation implementation is a code splitting killer, it depends on all validating routines of all beans you want to validate. I've spent a lot of time to fix this, but was not able to do it. What I was able to do, is to include Validation generation into `BeanValidationEditorDriver`, so if you use this driver for validating your beans, you needn't add the class entries in the Factory. Yes, you needn't add a Validator Factory your own, when you do not use validation groups, a empty Validator Factory is automatically included and that's all you need, the rest of the generation is done by the EditorDriver generator.

Multi value annotations, which are not annotated on the property field itself, but on the top of the bean, need still access to the properties to check. To make this work without reflections, I've included a code generator which generates a helper class out of the beans and a BeanUtil replacement which uses it, so the Validators can work with the server side common `BeanUtils.getProperty(bean, name)` even on the client. Since gwt-bean-validators 0.53.0 the generation is included in the Validator generating process and needs no additional configuration.

Because we never can trust client side checks, this checks always should be repeated on server side. To bring back validation results to the frontend, to display them with the editor framework, I've also included the [ValidationException.java](https://github.com/ManfredTremmel/gwt-bean-validators/gwt-bean-validators/blob/master/src/main/java/de/knightsoftnet/validators/shared/exceptions/ValidationException.java). If you use RPC, you can just add the set of validation errors to the constructor of the bean and throw it, on client side catch it and add the results to the editor.

For client side validation I've added the [BeanValidationEditorDriver.java](https://github.com/ManfredTremmel/gwt-bean-validators/gwt-bean-validators/blob/master/src/main/java/de/knightsoftnet/validators/client/editor/BeanValidationEditorDriver.java), if you use it as driver for the editor framework, validation is made automatically on every change or keyup (if widget suports it). With setSubmitButton(widget) you can add a submit button which is enabled or disabled, depending on the validation results, only if the results are valid, it's enabled.
Two Decorators, the decent [UniversalDecorator.java](https://github.com/ManfredTremmel/gwt-bean-validators/gwt-bean-validators/blob/master/src/main/java/de/knightsoftnet/validators/client/decorators/UniversalDecorator.java) and the more decorativ [UniversalDecoratorWithIcon.java](https://github.com/ManfredTremmel/gwt-bean-validators/gwt-bean-validators/blob/master/src/main/java/de/knightsoftnet/validators/client/decorators/UniversalDecoratorWithIcons.java) can be used to display the validation results on every input field. For own Designs, take [UniversalDecoratorWithIcon.java](https://github.com/ManfredTremmel/gwt-bean-validators/gwt-bean-validators/blob/master/src/main/java/de/knightsoftnet/validators/client/decorators/UniversalDecoratorWithIcons.java) as base and replace the Stylesheet. In the UiBinder you simply surround your widget with the Decorator tags like this:

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

Alternate, you can use widgets which include error handling by implementing the HasEditorErrors interface. All the widgets from [gwt-mt-widgets](https://github.com/ManfredTremmel/gwt-bean-validators/gwt-mt-widgets) containing support for error handling by using HTML5 methods.
I've added a little [example application on GitHub](https://github.com/ManfredTremmel/gwt-bean-validators-example), where you can see, how to use bean validation on client and server side, based on gwt/gwtp and spring with REST webservices. The concepts of the application are documented on the [wiki page](https://github.com/ManfredTremmel/gwt-bean-validators-example/wiki).
