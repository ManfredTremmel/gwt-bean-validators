package org.hibernate.validator.internal.util.logging;

import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

import java.util.List;
import java.util.Set;

import javax.validation.Path;

/**
 * localizeable log messages.
 *
 * @author Manfred Tremmel - GWT implementation
 */
@SuppressWarnings({"checkstyle:abbreviationaswordinname", "checkstyle:linelength"})
@DefaultLocale("en")
public interface LogMessages extends com.google.gwt.i18n.client.Messages {

  @DefaultMessage("Hibernate Validator {0}")
  String version(String version);

  @DefaultMessage("Ignoring XML configuration.")
  String ignoringXmlConfiguration();

  @DefaultMessage("Using {0} as constraint factory.")
  String usingConstraintFactory(String constraintFactoryClassName);

  @DefaultMessage("Using {0} as message interpolator.")
  String usingMessageInterpolator(String messageInterpolatorClassName);

  @DefaultMessage("Using {0} as traversable resolver.")
  String usingTraversableResolver(String traversableResolverClassName);

  @DefaultMessage("Using {0} as validation provider.")
  String usingValidationProvider(String validationProviderClassName);

  @DefaultMessage("{0} found. Parsing XML based configuration.")
  String parsingXMLFile(String fileName);

  @DefaultMessage("Unable to close input stream.")
  String unableToCloseInputStream();

  @DefaultMessage("Unable to close input stream for {0}.")
  String unableToCloseXMLFileInputStream(String fileName);

  @DefaultMessage("Unable to create schema for {0}: {1}")
  String unableToCreateSchema(String fileName, String message);

  @DefaultMessage("Unable to create annotation for configured constraint")
  String getUnableToCreateAnnotationForConfiguredConstraintException();

  @DefaultMessage("The class {0} does not have a property ''{1}'' with access {2}.")
  String getUnableToFindPropertyWithAccessException(String beanClass, String property,
      String elementType);

  @DefaultMessage("Type {0} doesn''t have a method {1}.")
  String getUnableToFindMethodException(Class<?> beanClass, String method);

  @DefaultMessage("{0} does not represent a valid BigDecimal format.")
  String getInvalidBigDecimalFormatException(String value);

  @DefaultMessage("The length of the integer part cannot be negative.")
  String getInvalidLengthForIntegerPartException();

  @DefaultMessage("The length of the fraction part cannot be negative.")
  String getInvalidLengthForFractionPartException();

  @DefaultMessage("The min parameter cannot be negative.")
  String getMinCannotBeNegativeException();

  @DefaultMessage("The max parameter cannot be negative.")
  String getMaxCannotBeNegativeException();

  @DefaultMessage("The length cannot be negative.")
  String getLengthCannotBeNegativeException();

  @DefaultMessage("Invalid regular expression.")
  String getInvalidRegularExpressionException();

  @DefaultMessage("Error during execution of script \"{0}\" occurred.")
  String getErrorDuringScriptExecutionException(String script);

  @DefaultMessage("Script \"{0}\" returned null, but must return either true or false.")
  String getScriptMustReturnTrueOrFalseExceptionSingle(String script);

  @DefaultMessage("Script \"{0}\" returned {1} (of type {2}), "
      + "but must return either true or false.")
  String getScriptMustReturnTrueOrFalseException(String script, String executionResult,
      String type);

  @DefaultMessage("Assertion error: inconsistent ConfigurationImpl construction.")
  String getInconsistentConfigurationException();

  @DefaultMessage("Unable to find provider: {0}.")
  String getUnableToFindProviderException(Class<?> providerClass);

  @DefaultMessage("Unexpected exception during isValid call.")
  String getExceptionDuringIsValidCallException();

  @DefaultMessage("Constraint factory returned null when trying to create instance of {0}.")
  String getConstraintFactoryMustNotReturnNullException(String validatorClassName);

  @DefaultMessage("No validator could be found for constraint ''{0}'' validating type ''{1}''. "
      + "Check configuration for ''{2}''")
  String getNoValidatorFoundForTypeException(String constraintType, String validatedValueType,
      String path);

  // @DefaultMessage("There are multiple validator classes which could validate the type {0}. "
  // + "The validator classes are: {1}.")
  // String getMoreThanOneValidatorFoundForTypeException(Type type, String validatorClasses);

  @DefaultMessage("Unable to initialize {0}.")
  String getUnableToInitializeConstraintValidatorException(String validatorClassName);

  @DefaultMessage("At least one custom message must be created if the default "
      + "error message gets disabled.")
  String getAtLeastOneCustomMessageMustBeCreatedException();

  @DefaultMessage("{0} is not a valid Java Identifier.")
  String getInvalidJavaIdentifierException(String identifier);

  @DefaultMessage("Unable to parse property path {0}.")
  String getUnableToParsePropertyPathException(String propertyPath);

  @DefaultMessage("Type {0} not supported for unwrapping.")
  String getTypeNotSupportedForUnwrappingException(Class<?> type);

  @DefaultMessage("Inconsistent fail fast configuration. Fail fast enabled via programmatic API, "
      + "but explicitly disabled via properties.")
  String getInconsistentFailFastConfigurationException();

  @DefaultMessage("Invalid property path.")
  String getInvalidPropertyPathExceptionNoArg();

  @DefaultMessage("Invalid property path. Either there is no property {1} in entity {0} or it is not possible to cascade to the property.")
  String getInvalidPropertyPathException(String propertyName, String beanClassName);

  @DefaultMessage("Property path must provide index or map key.")
  String getPropertyPathMustProvideIndexOrMapKeyException();

  @DefaultMessage("Call to TraversableResolver.isReachable() threw an exception.")
  String getErrorDuringCallOfTraversableResolverIsReachableException();

  @DefaultMessage("Call to TraversableResolver.isCascadable() threw an exception.")
  String getErrorDuringCallOfTraversableResolverIsCascadableException();

  @DefaultMessage("Unable to expand default group list {0} into sequence {1}.")
  String getUnableToExpandDefaultGroupListException(List<?> defaultGroupList, List<?> groupList);

  @DefaultMessage("At least one group has to be specified.")
  String getAtLeastOneGroupHasToBeSpecifiedException();

  @DefaultMessage("A group has to be an interface. {0} is not.")
  String getGroupHasToBeAnInterfaceException(String className);

  @DefaultMessage("Sequence definitions are not allowed as composing parts of a sequence.")
  String getSequenceDefinitionsNotAllowedException();

  @DefaultMessage("Cyclic dependency in groups definition")
  String getCyclicDependencyInGroupsDefinitionException();

  @DefaultMessage("Unable to expand group sequence.")
  String getUnableToExpandGroupSequenceException();

  @DefaultMessage("Default group sequence and default group sequence provider cannot "
      + "be defined at the same time.")
  String getInvalidDefaultGroupSequenceDefinitionException();

  @DefaultMessage("''Default.class'' cannot appear in default group sequence list.")
  String getNoDefaultGroupInGroupSequenceException();

  @DefaultMessage("{0} must be part of the redefined default group sequence.")
  String getBeanClassMustBePartOfRedefinedDefaultGroupSequenceException(String beanClassName);

  @DefaultMessage("The default group sequence provider defined for {0} has the wrong type")
  String getWrongDefaultGroupSequenceProviderTypeException(String beanClassName);

  @DefaultMessage("Method or constructor {0} doesn''t have a parameter with index {1}.")
  String getInvalidExecutableParameterIndexException(String executable, int index);

  @DefaultMessage("Unable to retrieve annotation parameter value.")
  String getUnableToRetrieveAnnotationParameterValueException();

  @DefaultMessage("Method or constructor {0} has {1} parameters, but the passed list of "
      + "parameter meta data has a size of {2}.")
  String getInvalidLengthOfParameterMetaDataListException(String executableName, int nbParameters,
      int listSize);

  @DefaultMessage("Unable to instantiate {0}.")
  String getUnableToInstantiateExceptionOneArg(String className);

  @DefaultMessage("Unable to instantiate {0}: {1}.")
  String getUnableToInstantiateException(String message, Class<?> clazz);

  @DefaultMessage("Unable to load class: {0} from {1}.")
  String getUnableToLoadClassException(String className, Object loader);

  @DefaultMessage("Start index cannot be negative: {0}.")
  String getStartIndexCannotBeNegativeException(int startIndex);

  @DefaultMessage("End index cannot be negative: {0}.")
  String getEndIndexCannotBeNegativeException(int endIndex);

  @DefaultMessage("Invalid Range: {0} > {1}.")
  String getInvalidRangeException(int startIndex, int endIndex);

  @DefaultMessage("A explicitly specified check digit must lie outside the interval: [{0}, {1}].")
  String getInvalidCheckDigitException(int startIndex, int endIndex);

  @DefaultMessage("''{0}'' is not a digit.")
  String getCharacterIsNotADigitException(char character);

  @DefaultMessage("Parameters starting with ''valid'' are not allowed in a constraint.")
  String getConstraintParametersCannotStartWithValidException();

  @DefaultMessage("{1} contains Constraint annotation, but does not contain a {0} parameter.")
  String getConstraintWithoutMandatoryParameterException(String parameterName,
      String constraintName);

  @DefaultMessage("{0} contains Constraint annotation, but the payload parameter "
      + "default value is not the empty array.")
  String getWrongDefaultValueForPayloadParameterException(String constraintName);

  @DefaultMessage("{0} contains Constraint annotation, but the payload parameter is of wrong type.")
  String getWrongTypeForPayloadParameterException(String constraintName);

  @DefaultMessage("{0} contains Constraint annotation, but the groups parameter "
      + "default value is not the empty array.")
  String getWrongDefaultValueForGroupsParameterException(String constraintName);

  @DefaultMessage("{0} contains Constraint annotation, but the groups parameter is of wrong type.")
  String getWrongTypeForGroupsParameterException(String constraintName);

  @DefaultMessage("{0} contains Constraint annotation, but the message parameter "
      + "is not of type java.lang.String.")
  String getWrongTypeForMessageParameterException(String constraintName);

  @DefaultMessage("Overridden constraint does not define an attribute with name {0}.")
  String getOverriddenConstraintAttributeNotFoundException(String attributeName);

  @DefaultMessage("The overriding type of a composite constraint must be identical to the "
      + "overridden one. Expected {0} found {1}.")
  String getWrongAttributeTypeForOverriddenConstraintException(String expectedReturnType,
      Class<?> currentReturnType);

  @DefaultMessage("Wrong parameter type. Expected: {0} Actual: {1}.")
  String getWrongParameterTypeException(String expectedType, String currentType);

  @DefaultMessage("The specified annotation defines no parameter ''{0}''.")
  String getUnableToFindAnnotationParameterException(String parameterName);

  @DefaultMessage("Unable to get ''{0}'' from {1}.")
  String getUnableToGetAnnotationParameterException(String parameterName, String annotationName);

  @DefaultMessage("No value provided for parameter ''{0}'' of annotation {1}.")
  String getNoValueProvidedForAnnotationParameterException(String parameterName, String annotation);

  @DefaultMessage("Trying to instantiate {0} with unknown parameter(s): {1}.")
  String getTryingToInstantiateAnnotationWithUnknownParametersException(Class<?> annotationType,
      Set<String> unknownParameters);

  @DefaultMessage("Property name cannot be null or empty.")
  String getPropertyNameCannotBeNullOrEmptyException();

  @DefaultMessage("Element type has to be FIELD or METHOD.")
  String getElementTypeHasToBeFieldOrMethodException();

  // @DefaultMessage("Member {0} is neither a field nor a method.")
  // String getMemberIsNeitherAFieldNorAMethodException(Member member);

  @DefaultMessage("Unable to access {0}.")
  String getUnableToAccessMemberException(String memberName);

  @DefaultMessage("{0} has to be a primitive type.")
  String getHasToBeAPrimitiveTypeException(Class<?> clazz);

  @DefaultMessage("null is an invalid type for a constraint validator.")
  String getNullIsAnInvalidTypeForAConstraintValidatorException();

  // @DefaultMessage("Missing actual type argument for type parameter: {0}.")
  // String getMissingActualTypeArgumentForTypeParameterException(TypeVariable<?> typeParameter);

  @DefaultMessage("Unable to instantiate constraint factory class {0}.")
  String getUnableToInstantiateConstraintFactoryClassException(String constraintFactoryClassName);

  @DefaultMessage("Unable to open input stream for mapping file {0}.")
  String getUnableToOpenInputStreamForMappingFileException(String mappingFileName);

  @DefaultMessage("Unable to instantiate message interpolator class {0}.")
  String getUnableToInstantiateMessageInterpolatorClassException(
      String messageInterpolatorClassName);

  @DefaultMessage("Unable to instantiate traversable resolver class {0}.")
  String getUnableToInstantiateTraversableResolverClassException(
      String traversableResolverClassName);

  @DefaultMessage("Unable to instantiate validation provider class {0}.")
  String getUnableToInstantiateValidationProviderClassException(String providerClassName);

  @DefaultMessage("Unable to parse {0}.")
  String getUnableToParseValidationXmlFileException(String file);

  @DefaultMessage("{0} is not an annotation.")
  String getIsNotAnAnnotationException(String annotationClassName);

  @DefaultMessage("{0} is not a constraint validator class.")
  String getIsNotAConstraintValidatorClassException(Class<?> validatorClass);

  @DefaultMessage("{0} is configured at least twice in xml.")
  String getBeanClassHasAlreadyBeConfiguredInXmlException(String beanClassName);

  @DefaultMessage("{0} is defined twice in mapping xml for bean {1}.")
  String getIsDefinedTwiceInMappingXmlForBeanException(String name, String beanClassName);

  @DefaultMessage("{0} does not contain the fieldType {1}.")
  String getBeanDoesNotContainTheFieldException(String beanClassName, String fieldName);

  @DefaultMessage("{0} does not contain the property {1}.")
  String getBeanDoesNotContainThePropertyException(String beanClassName, String getterName);

  @DefaultMessage("Annotation of type {0} does not contain a parameter {1}.")
  String getAnnotationDoesNotContainAParameterException(String annotationClassName,
      String parameterName);

  @DefaultMessage("Attempt to specify an array where single value is expected.")
  String getAttemptToSpecifyAnArrayWhereSingleValueIsExpectedException();

  @DefaultMessage("Unexpected parameter value.")
  String getUnexpectedParameterValueException();

  @DefaultMessage("Invalid {0} format.")
  String getInvalidNumberFormatException(String formatName);

  @DefaultMessage("Invalid char value: {0}.")
  String getInvalidCharValueException(String value);

  @DefaultMessage("Invalid return type: {0}. Should be a enumeration type.")
  String getInvalidReturnTypeException(Class<?> returnType);

  @DefaultMessage("{0}, {1}, {2} are reserved parameter names.")
  String getReservedParameterNamesException(String messageParameterName, String groupsParameterName,
      String payloadParameterName);

  @DefaultMessage("Specified payload class {0} does not implement javax.validation.Payload")
  String getWrongPayloadClassException(String payloadClassName);

  @DefaultMessage("Error parsing mapping file.")
  String getErrorParsingMappingFileException();

  @DefaultMessage("{0}")
  String getIllegalArgumentException(String message);

  // @DefaultMessage("Unable to cast {0} (with element kind {1}) to {2}")
  // String getUnableToNarrowNodeTypeException(String actualDescriptorType, ElementKind kind,
  // String expectedDescriptorType);

  @DefaultMessage("Using {0} as parameter name provider.")
  String usingParameterNameProvider(String parameterNameProviderClassName);

  @DefaultMessage("Unable to instantiate parameter name provider class {0}.")
  String getUnableToInstantiateParameterNameProviderClassException(
      String parameterNameProviderClassName);

  @DefaultMessage("Unable to parse {0}.")
  String getUnableToDetermineSchemaVersionException(String file);

  @DefaultMessage("Unsupported schema version for {0}: {1}.")
  String getUnsupportedSchemaVersionException(String file, String version);

  @DefaultMessage("Found multiple group conversions for source group {0}: {1}.")
  String getMultipleGroupConversionsForSameSourceException(Class<?> from, Set<Class<?>> tos);

  @DefaultMessage("Found group conversions for non-cascading element: {0}.")
  String getGroupConversionOnNonCascadingElementException(String location);

  @DefaultMessage("Found group conversion using a group sequence as source: {0}.")
  String getGroupConversionForSequenceException(Class<?> from);

  @DefaultMessage("EL expression ''{0}'' references an unknown property")
  String unknownPropertyInExpressionLanguage(String expression);

  @DefaultMessage("Error in EL expression ''{0}''")
  String errorInExpressionLanguage(String expression);

  // @DefaultMessage("A method return value must not be marked for cascaded validation more then "
  // + "once in a class hierarchy, but the following two methods are marked as such: {0}, {1}.")
  // String getMethodReturnValueMustNotBeMarkedMoreThanOnceForCascadedValidationException(
  // Member member1, Member member2);
  //
  // @DefaultMessage("Void methods must not be constrained or marked for cascaded "
  // + "validation, but method {0} is.")
  // String getVoidMethodsMustNotBeConstrainedException(Member member);

  @DefaultMessage("{0} does not contain a constructor with the parameter types {1}.")
  String getBeanDoesNotContainConstructorException(String beanClassName, String parameterTypes);

  @DefaultMessage("Unable to load parameter of type ''{0}'' in {1}.")
  String getInvalidParameterTypeException(String type, String beanClassName);

  @DefaultMessage("{0} does not contain a method with the name ''{1}'' and parameter types {2}.")
  String getBeanDoesNotContainMethodException(String beanClassName, String methodName,
      List<Class<?>> parameterTypes);

  @DefaultMessage("The specified constraint annotation class {0} cannot be loaded.")
  String getUnableToLoadConstraintAnnotationClassException(String constraintAnnotationClass);

  @DefaultMessage("The method ''{0}'' is defined twice in the mapping xml for bean {1}.")
  String getMethodIsDefinedTwiceInMappingXmlForBeanException(String name, String beanClassName);

  @DefaultMessage("The constructor ''{0}'' is defined twice in the mapping xml for bean {1}.")
  String getConstructorIsDefinedTwiceInMappingXmlForBeanException(String name,
      String beanClassName);

  @DefaultMessage("The constraint ''{0}'' defines multiple cross parameter validators. "
      + "Only one is allowed.")
  String getMultipleCrossParameterValidatorClassesException(String constraint);

  @DefaultMessage("The constraint {0} used ConstraintTarget#IMPLICIT where the "
      + "target cannot be inferred.")
  String getImplicitConstraintTargetInAmbiguousConfigurationException(String constraint);

  @DefaultMessage("Cross parameter constraint {0} is illegally placed on a parameterless method "
      + "or constructor ''{1}''.")
  String getCrossParameterConstraintOnMethodWithoutParametersException(String constraint,
      String member);

  @DefaultMessage("Cross parameter constraint {0} is illegally placed on class level.")
  String getCrossParameterConstraintOnClassException(String constraint);

  @DefaultMessage("Cross parameter constraint {0} is illegally placed on field ''{1}''.")
  String getCrossParameterConstraintOnFieldException(String constraint, String field);

  @DefaultMessage("No parameter nodes may be added since path {0} doesn''t refer to a "
      + "cross-parameter constraint.")
  String getParameterNodeAddedForNonCrossParameterConstraintException(Path path);

  @DefaultMessage("{0} is configured multiple times (note, <getter> and <method> nodes for "
      + "the same method are not allowed)")
  String getConstrainedElementConfiguredMultipleTimesException(String location);

  @DefaultMessage("An exception occurred during evaluation of EL expression ''{0}''")
  String evaluatingExpressionLanguageExpressionCausedException(String expression);

  @DefaultMessage("An exception occurred during message interpolation")
  String getExceptionOccurredDuringMessageInterpolationException();

  @DefaultMessage("The constraint ''{0}'' defines multiple validators for the type ''{1}''. "
      + "Only one is allowed.")
  String getMultipleValidatorsForSameTypeException(String constraint, String type);

  // @DefaultMessage("A method overriding another method must not alter the parameter constraint "
  // + "configuration, but method {1} changes the configuration of {0}.")
  // String getParameterConfigurationAlteredInSubTypeException(Member superMethod, Member
  // subMethod);
  //
  // @DefaultMessage("Two methods defined in parallel types must not declare parameter constraints,
  // "
  // + "if they are overridden by the same method, but methods {0} and {1} both define parameter "
  // + "constraints.")
  // String getParameterConstraintsDefinedInMethodsFromParallelTypesException(Member method1,
  // Member method2);

  // @DefaultMessage("The constraint {0} used ConstraintTarget#{1} but is not specified on "
  // + "a method or constructor.")
  // String getParametersOrReturnValueConstraintTargetGivenAtNonExecutableException(String
  // constraint,
  // ConstraintTarget target);

  @DefaultMessage("Cross parameter constraint {0} has no cross-parameter validator.")
  String getCrossParameterConstraintHasNoValidatorException(String constraint);

  // @DefaultMessage("Composed and composing constraints must have the same constraint type, "
  // + "but composed constraint {0} has type {2}, while composing constraint {1} has type {3}.")
  // String getComposedAndComposingConstraintsHaveDifferentTypesException(
  // String composedConstraintTypeName, String composingConstraintTypeName,
  // ConstraintType composedConstraintType, ConstraintType composingConstraintType);

  @DefaultMessage("Constraints with generic as well as cross-parameter validators must define an "
      + "attribute validationAppliesTo(), but constraint {0} doesn''t.")
  String getGenericAndCrossParameterConstraintDoesNotDefineValidationAppliesToParameterException(
      String constraint);

  @DefaultMessage("Return type of the attribute validationAppliesTo() of the constraint {0} "
      + "must be javax.validation.ConstraintTarget.")
  String getValidationAppliesToParameterMustHaveReturnTypeConstraintTargetException(
      String constraint);

  @DefaultMessage("Default value of the attribute validationAppliesTo() of the constraint {0} "
      + "must be ConstraintTarget#IMPLICIT.")
  String getValidationAppliesToParameterMustHaveDefaultValueImplicitException(String constraint);

  @DefaultMessage("Only constraints with generic as well as cross-parameter validators must "
      + "define an attribute validationAppliesTo(), but constraint {0} does.")
  String getValidationAppliesToParameterMustNotBeDefinedForNonGenericAndCrossParameterConstraintException(
      String constraint);

  @DefaultMessage("Validator for cross-parameter constraint {0} does not validate Object "
      + "nor Object[].")
  String getValidatorForCrossParameterConstraintMustEitherValidateObjectOrObjectArrayException(
      String constraint);

  // @DefaultMessage("Two methods defined in parallel types must not define group conversions for "
  // + "a cascaded method return value, if they are overridden by the same method, but methods "
  // + "{0} and {1} both define parameter constraints.")
  // String getMethodsFromParallelTypesMustNotDefineGroupConversionsForCascadedReturnValueException(
  // Member method1, Member method2);
  //
  // @DefaultMessage("The validated type {0} does not specify the constructor/method: {1}")
  // String getMethodOrConstructorNotDefinedByValidatedTypeException(String validatedTypeName,
  // Member member);
  //
  // @DefaultMessage("The actual parameter type ''{0}'' is not assignable to the expected one
  // ''{1}'' "
  // + "for parameter {2} of ''{3}''")
  // String getParameterTypesDoNotMatchException(String actualType, String expectedType, int index,
  // Member member);

  @DefaultMessage("{0} has to be a auto-boxed type.")
  String getHasToBeABoxedTypeException(Class<?> clazz);

  @DefaultMessage("Mixing IMPLICIT and other executable types is not allowed.")
  String getMixingImplicitWithOtherExecutableTypesException();

  // @DefaultMessage("@ValidateOnExecution is not allowed on methods overriding a superclass "
  // + "method or implementing an interface. Check configuration for {0}")
  // String getValidateOnExecutionOnOverriddenOrInterfaceMethodException(Method method);

  @DefaultMessage("A given constraint definition can only be overridden in one mapping file. "
      + "{0} is overridden in multiple files")
  String getOverridingConstraintDefinitionsInMultipleMappingFilesException(String constraintClass);

  @DefaultMessage("The message descriptor ''{0}'' contains an unbalanced meta character "
      + "''{1}'' parameter.")
  String getNonTerminatedParameterException(String messageDescriptor, char character);

  @DefaultMessage("The message descriptor ''{0}'' has nested parameters.")
  String getNestedParameterException(String messageDescriptor);

  @DefaultMessage("No JSR-223 scripting engine could be bootstrapped for language \"{0}\".")
  String getCreationOfScriptExecutorFailedException(String languageName);

  @DefaultMessage("{0} is configured more than once via the programmatic constraint "
      + "declaration API.")
  String getBeanClassHasAlreadyBeConfiguredViaProgrammaticApiException(String beanClassName);

  @DefaultMessage("Property \"{1}\" of type {0} is configured more than once via the programmatic "
      + "constraint declaration API.")
  String getPropertyHasAlreadyBeConfiguredViaProgrammaticApiException(String beanClassName,
      String propertyName);

  @DefaultMessage("Method {1} of type {0} is configured more than once via the programmatic "
      + "constraint declaration API.")
  String getMethodHasAlreadyBeConfiguredViaProgrammaticApiException(String beanClassName,
      String method);

  @DefaultMessage("Parameter {2} of method or constructor {1} of type {0} is configured more "
      + "than once via the programmatic constraint declaration API.")
  String getParameterHasAlreadyBeConfiguredViaProgrammaticApiException(String beanClassName,
      String executable, int parameterIndex);

  @DefaultMessage("The return value of method or constructor {1} of type {0} is configured more "
      + "than once via the programmatic constraint declaration API.")
  String getReturnValueHasAlreadyBeConfiguredViaProgrammaticApiException(String beanClassName,
      String executable);

  @DefaultMessage("Constructor {1} of type {0} is configured more than once via the programmatic "
      + "constraint declaration API.")
  String getConstructorHasAlreadyBeConfiguredViaProgrammaticApiException(String beanClassName,
      String constructor);

  @DefaultMessage("Cross-parameter constraints for the method or constructor {1} of type {0} are "
      + "declared more than once via the programmatic constraint declaration API.")
  String getCrossParameterElementHasAlreadyBeConfiguredViaProgrammaticApiException(
      String beanClassName, String executable);

  @DefaultMessage("Multiplier cannot be negative: {0}.")
  String getMultiplierCannotBeNegativeException(int multiplier);

  @DefaultMessage("Weight cannot be negative: {0}.")
  String getWeightCannotBeNegativeException(int weight);

  @DefaultMessage("''{0}'' is not a digit nor a letter.")
  String getTreatCheckAsIsNotADigitNorALetterException(int weight);

  @DefaultMessage("Wrong number of parameters. Method or constructor {0} expects {1} parameters, "
      + "but got {2}.")
  String getInvalidParameterCountForExecutableException(String executable,
      int expectedParameterCount, int actualParameterCount);

  @DefaultMessage("No validation value unwrapper is registered for type ''{0}''.")
  String getNoUnwrapperFoundForTypeException(String typeName);

  @DefaultMessage("Unable to load ''javax.el.ExpressionFactory''. Check that you have the EL "
      + "dependencies on the classpath, or use ParameterMessageInterpolator instead")
  String getMissingELDependenciesException();

  @DefaultMessage("ParameterMessageInterpolator has been chosen, EL interpolation will not be "
      + "supported")
  String creationOfParameterMessageInterpolation();

  @DefaultMessage("Message contains EL expression: {0}, which is unsupported with chosen "
      + "Interpolator")
  String getElUnsupported(String expression);

  @DefaultMessage("The constraint of type ''{1}'' defined on ''{0}'' has multiple matching "
      + "constraint validators which is due to an additional value handler of type ''{2}''. "
      + "It is unclear which value needs validating. Clarify configuration via "
      + "@UnwrapValidatedValue.")
  String getConstraintValidatorExistsForWrapperAndWrappedValueException(String property,
      String constraint, String valueHandler);

  @DefaultMessage("When using type annotation constraints on parameterized iterables or map "
      + "@Valid must be used. Check {0}#{1}")
  String getTypeAnnotationConstraintOnIterableRequiresUseOfValidAnnotationException(
      String declaringClass, String name);

  @DefaultMessage("Parameterized type with more than one argument is not supported: {0}")
  String parameterizedTypeWithMoreThanOneTypeArgumentIsNotSupported(String type);

  @DefaultMessage("The configuration of value unwrapping for property ''{0}'' of bean ''{1}'' is "
      + "inconsistent between the field and its getter.")
  String getInconsistentValueUnwrappingConfigurationBetweenFieldAndItsGetterException(
      String property, String clazz);

  @DefaultMessage("Unable to parse {0}.")
  String getUnableToCreateXMLEventReader(String file);

  @DefaultMessage("Error creating unwrapper: {0}")
  String validatedValueUnwrapperCannotBeCreated(String className);

  @DefaultMessage("Couldn''t determine Java version from value {0}; Not enabling features "
      + "requiring Java 8")
  String unknownJvmVersion(String vmVersionStr);

  @DefaultMessage("{0} is configured more than once via the programmatic constraint definition API.")
  String getConstraintHasAlreadyBeenConfiguredViaProgrammaticApiException(
      String annotationClassName);

  @DefaultMessage("An empty element is only supported when a CharSequence is expected.")
  String getEmptyElementOnlySupportedWhenCharSequenceIsExpectedExpection();

  @DefaultMessage("Unable to reach the property to validate for the bean {0} and the property path {1}. A property is null along the way.")
  String getUnableToReachPropertyToValidateException(Object bean, String path);

  @DefaultMessage("Unable to convert the Type {0} to a Class.")
  String getUnableToConvertTypeToClassException(String type);

  @DefaultMessage("Constraint validator payload set to {0}.")
  String logConstraintValidatorPayload(Object payload);
}


