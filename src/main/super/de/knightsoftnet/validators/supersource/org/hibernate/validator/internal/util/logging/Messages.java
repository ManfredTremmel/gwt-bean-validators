/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package org.hibernate.validator.internal.util.logging;

import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

/**
 * localizeable messages.
 *
 * @author Hardy Ferentschik
 * @author Manfred Tremmel - GWT implementation
 */
@DefaultLocale("en")
public interface Messages extends com.google.gwt.i18n.client.Messages {

  @DefaultMessage("must not be null.")
  String mustNotBeNull();

  @DefaultMessage("%s must not be null.")
  String mustNotBeNull(String parameterName);

  @DefaultMessage("The parameter \"{0}\" must not be null.")
  String parameterMustNotBeNull(String parameterName);

  @DefaultMessage("The parameter \"{0}\" must not be empty.")
  String parameterMustNotBeEmpty(String parameterName);

  @DefaultMessage("The bean type cannot be null.")
  String beanTypeCannotBeNull();

  @DefaultMessage("null is not allowed as property path.")
  String propertyPathCannotBeNull();

  @DefaultMessage("The property name must not be empty.")
  String propertyNameMustNotBeEmpty();

  @DefaultMessage("null passed as group name.")
  String groupMustNotBeNull();

  @DefaultMessage("The bean type must not be null when creating a constraint mapping.")
  String beanTypeMustNotBeNull();

  @DefaultMessage("The method name must not be null.")
  String methodNameMustNotBeNull();

  @DefaultMessage("The object to be validated must not be null.")
  String validatedObjectMustNotBeNull();

  @DefaultMessage("The method to be validated must not be null.")
  String validatedMethodMustNotBeNull();

  @DefaultMessage("The class cannot be null.")
  String classCannotBeNull();

  @DefaultMessage("Class is null.")
  String classIsNull();

  @DefaultMessage("No JSR 223 script engine found for language \"{0}\".")
  String unableToFindScriptEngine(String languageName);

  @DefaultMessage("The constructor to be validated must not be null.")
  String validatedConstructorMustNotBeNull();

  @DefaultMessage("The method parameter array cannot not be null.")
  String validatedParameterArrayMustNotBeNull();

  @DefaultMessage("The created instance must not be null.")
  String validatedConstructorCreatedInstanceMustNotBeNull();

  @DefaultMessage("The input stream for #addMapping() cannot be null.")
  String inputStreamCannotBeNull();

  @DefaultMessage("Constraints on the parameters of constructors of non-static inner classes "
      + "are not supported if those parameters have a generic type due to JDK bug JDK-5087240.")
  String constraintOnConstructorOfNonStaticInnerClass();

  @DefaultMessage("Custom parameterized types with more than one type argument are not supported "
      + "and will not be checked for type use constraints.")
  String parameterizedTypesWithMoreThanOneTypeArgument();

  @DefaultMessage("Hibernate Validator cannot instantiate AggregateResourceBundle.CONTROL. "
      + "This can happen most notably in a Google App Engine environment. "
      + "A PlatformResourceBundleLocator without bundle aggregation was created. "
      + "This only effects you in case you are using multiple ConstraintDefinitionContributor "
      + "jars. " + "ConstraintDefinitionContributors are a Hibernate Validator specific feature. "
      + "All Bean Validation "
      + "features work as expected. See also https://hibernate.atlassian.net/browse/HV-1023.")
  String unableToUseResourceBundleAggregation();
}


