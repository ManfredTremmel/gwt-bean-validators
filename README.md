gwt-bean-validators
===================

A collection of JSR-303 bean validators that can also be used on the client side of gwt and also missing functionality in gwt like getter reflection, editor component which validates entries on typing, on change or on submit and decorators to display errors inside the form.

A list of the included validators you can find in the [mt-bean-validators](https://github.com/ManfredTremmel/mt-bean-validators) project.


Dependencies
------------
If you want to use this validators only on server side, you needn't add any gwt libraries, the code of the validators itself uses no gwt specific functions, include [mt-bean-validators](https://github.com/ManfredTremmel/mt-bean-validators) instead of gwt-bean-validators.
On the gwt frontend side, instead of apache-commons-lang3 and apache-commons-validators my [gwt-commons-lang3](https://github.com/ManfredTremmel/gwt-commons-lang3) and [gwt-commons-validators](https://github.com/ManfredTremmel/gwt-commons-validator) packages are used, you shouldn't add both in your project.

Maven integraten
----------------

The dependency itself for GWT-Projects:

```
    <dependency>
      <groupId>de.knightsoft-net</groupId>
      <artifactId>gwt-bean-validators</artifactId>
      <version>0.10.0</version>
    </dependency>
```
For non GWT-Projects you can use [mt-bean-validators](https://github.com/ManfredTremmel/mt-bean-validators) instead which contains only the validators and has no dependencies to gwt:

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
