/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package org.hibernate.validator.internal.util.logging;

import com.google.gwt.core.shared.GWT;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.internal.engine.path.NodeImpl;

import java.lang.annotation.ElementType;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.validation.ConstraintDeclarationException;
import javax.validation.ConstraintDefinitionException;
import javax.validation.ElementKind;
import javax.validation.GroupDefinitionException;
import javax.validation.Path;
import javax.validation.UnexpectedTypeException;
import javax.validation.ValidationException;

/**
 * The Hibernate Validator logger interface for JBoss Logging.
 *
 * <b>Note</b>:<br>
 * <p>
 * New log messages must always use a new (incremented) message id. Don't re-use of existing message
 * ids, even if a given log method is not used anymore. Unused messages can be deleted.
 * </p>
 *
 * @author Kevin Pollet &lt;kevin.pollet@serli.com&gt; (C) 2012 SERLI
 * @author Manfred Tremmel - GWT port
 */
@SuppressWarnings({"checkstyle:abbreviationaswordinname", "checkstyle:linelength",
    "checkstyle:javadocmethod"})
public class Log {

  private static final LogMessages MESSAGE = GWT.create(LogMessages.class);

  public void version(final String version) {
    GWT.log(MESSAGE.version(version));
  }

  public void ignoringXmlConfiguration() {
    GWT.log(MESSAGE.ignoringXmlConfiguration());
  }

  public void usingConstraintFactory(final String constraintFactoryClassName) {
    GWT.log(MESSAGE.usingConstraintFactory(constraintFactoryClassName));
  }

  public void usingMessageInterpolator(final String messageInterpolatorClassName) {
    GWT.log(MESSAGE.usingMessageInterpolator(messageInterpolatorClassName));
  }

  public void usingTraversableResolver(final String traversableResolverClassName) {
    GWT.log(MESSAGE.usingTraversableResolver(traversableResolverClassName));
  }

  void usingValidationProvider(final String validationProviderClassName) {
    GWT.log(MESSAGE.usingValidationProvider(validationProviderClassName));
  }

  public void parsingXMLFile(final String fileName) {
    GWT.log(MESSAGE.parsingXMLFile(fileName));
  }

  public void unableToCloseInputStream() {
    GWT.log(MESSAGE.unableToCloseInputStream());
  }

  public void unableToCloseXMLFileInputStream(final String fileName) {
    GWT.log(MESSAGE.unableToCloseXMLFileInputStream(fileName));
  }

  public void unableToCreateSchema(final String fileName, final String message) {
    GWT.log(MESSAGE.unableToCreateSchema(fileName, message));
  }

  public ValidationException getUnableToCreateAnnotationForConfiguredConstraintException(
      final RuntimeException exception) {
    return new ValidationException(
        MESSAGE.getUnableToCreateAnnotationForConfiguredConstraintException(), exception);
  }

  public ValidationException getUnableToFindPropertyWithAccessException(final Class<?> beanClass,
      final String property, final ElementType elementType) {
    return new ValidationException(MESSAGE.getUnableToFindPropertyWithAccessException(
        beanClass.getName(), property, elementType.name()));
  }

  public IllegalArgumentException getUnableToFindMethodException(final Class<?> beanClass,
      final String method) {
    return new IllegalArgumentException(MESSAGE.getUnableToFindMethodException(beanClass, method));
  }

  public IllegalArgumentException getInvalidBigDecimalFormatException(final String value,
      final NumberFormatException exception) {
    return new IllegalArgumentException(MESSAGE.getInvalidBigDecimalFormatException(value),
        exception);
  }

  public IllegalArgumentException getInvalidLengthForIntegerPartException() {
    return new IllegalArgumentException(MESSAGE.getInvalidLengthForIntegerPartException());
  }

  public IllegalArgumentException getInvalidLengthForFractionPartException() {
    return new IllegalArgumentException(MESSAGE.getInvalidLengthForFractionPartException());
  }

  public IllegalArgumentException getMinCannotBeNegativeException() {
    return new IllegalArgumentException(MESSAGE.getMinCannotBeNegativeException());
  }

  public IllegalArgumentException getMaxCannotBeNegativeException() {
    return new IllegalArgumentException(MESSAGE.getMaxCannotBeNegativeException());
  }

  public IllegalArgumentException getLengthCannotBeNegativeException() {
    return new IllegalArgumentException(MESSAGE.getLengthCannotBeNegativeException());
  }

  public IllegalArgumentException getInvalidRegularExpressionException(final Exception exception) {
    return new IllegalArgumentException(MESSAGE.getInvalidRegularExpressionException(), exception);
  }

  public ConstraintDeclarationException getErrorDuringScriptExecutionException(final String script,
      final Exception exception) {
    return new ConstraintDeclarationException(
        MESSAGE.getErrorDuringScriptExecutionException(script), exception);
  }

  public ConstraintDeclarationException getScriptMustReturnTrueOrFalseException(
      final String script) {
    return new ConstraintDeclarationException(
        MESSAGE.getScriptMustReturnTrueOrFalseExceptionSingle(script));
  }

  public ConstraintDeclarationException getScriptMustReturnTrueOrFalseException(final String script,
      final Object executionResult, final String type) {
    return new ConstraintDeclarationException(
        MESSAGE.getScriptMustReturnTrueOrFalseException(script, executionResult.toString(), type));
  }

  public ValidationException getInconsistentConfigurationException() {
    return new ValidationException(MESSAGE.getInconsistentConfigurationException());
  }

  public ValidationException getUnableToFindProviderException(final Class<?> providerClass) {
    return new ValidationException(MESSAGE.getUnableToFindProviderException(providerClass));
  }

  public ValidationException getExceptionDuringIsValidCallException(
      final RuntimeException exception) {
    return new ValidationException(MESSAGE.getExceptionDuringIsValidCallException(), exception);
  }

  public ValidationException getConstraintFactoryMustNotReturnNullException(
      final String validatorClassName) {
    return new ValidationException(
        MESSAGE.getConstraintFactoryMustNotReturnNullException(validatorClassName));
  }

  public UnexpectedTypeException getNoValidatorFoundForTypeException(final String constraintType,
      final String validatedValueType, final String path) {
    return new UnexpectedTypeException(
        MESSAGE.getNoValidatorFoundForTypeException(constraintType, validatedValueType, path));
  }

  // public UnexpectedTypeException getMoreThanOneValidatorFoundForTypeException(final Type type,
  // final String validatorClasses) {
  // return new UnexpectedTypeException(
  // MESSAGE.getMoreThanOneValidatorFoundForTypeException(type, validatorClasses));
  // }

  public ValidationException getUnableToInitializeConstraintValidatorException(
      final String validatorClassName, final RuntimeException exception) {
    return new ValidationException(
        MESSAGE.getUnableToInitializeConstraintValidatorException(validatorClassName), exception);
  }

  public ValidationException getAtLeastOneCustomMessageMustBeCreatedException() {
    return new ValidationException(MESSAGE.getAtLeastOneCustomMessageMustBeCreatedException());
  }

  public IllegalArgumentException getInvalidJavaIdentifierException(final String identifier) {
    return new IllegalArgumentException(MESSAGE.getInvalidJavaIdentifierException(identifier));
  }

  public IllegalArgumentException getUnableToParsePropertyPathException(final String propertyPath) {
    return new IllegalArgumentException(
        MESSAGE.getUnableToParsePropertyPathException(propertyPath));
  }

  public ValidationException getTypeNotSupportedForUnwrappingException(final Class<?> type) {
    return new ValidationException(MESSAGE.getTypeNotSupportedForUnwrappingException(type));
  }

  public ValidationException getInconsistentFailFastConfigurationException() {
    return new ValidationException(MESSAGE.getInconsistentFailFastConfigurationException());
  }


  public IllegalArgumentException getInvalidPropertyPathException() {
    return new IllegalArgumentException(MESSAGE.getInvalidPropertyPathExceptionNoArg());
  }

  public IllegalArgumentException getInvalidPropertyPathException(final String propertyName,
      final String beanClassName) {
    return new IllegalArgumentException(
        MESSAGE.getInvalidPropertyPathException(propertyName, beanClassName));
  }

  public IllegalArgumentException getPropertyPathMustProvideIndexOrMapKeyException() {
    return new IllegalArgumentException(MESSAGE.getPropertyPathMustProvideIndexOrMapKeyException());
  }

  public ValidationException getErrorDuringCallOfTraversableResolverIsReachableException(
      final RuntimeException exception) {
    return new ValidationException(
        MESSAGE.getErrorDuringCallOfTraversableResolverIsReachableException(), exception);
  }

  public ValidationException getErrorDuringCallOfTraversableResolverIsCascadableException(
      final RuntimeException exception) {
    return new ValidationException(
        MESSAGE.getErrorDuringCallOfTraversableResolverIsCascadableException(), exception);
  }

  public GroupDefinitionException getUnableToExpandDefaultGroupListException(
      final List<?> defaultGroupList, final List<?> groupList) {
    return new GroupDefinitionException(
        MESSAGE.getUnableToExpandDefaultGroupListException(defaultGroupList, groupList));
  }

  public IllegalArgumentException getAtLeastOneGroupHasToBeSpecifiedException() {
    return new IllegalArgumentException(MESSAGE.getAtLeastOneGroupHasToBeSpecifiedException());
  }

  public ValidationException getGroupHasToBeAnInterfaceException(final String className) {
    return new ValidationException(MESSAGE.getGroupHasToBeAnInterfaceException(className));
  }

  public GroupDefinitionException getSequenceDefinitionsNotAllowedException() {
    return new GroupDefinitionException(MESSAGE.getSequenceDefinitionsNotAllowedException());
  }

  public GroupDefinitionException getCyclicDependencyInGroupsDefinitionException() {
    return new GroupDefinitionException(MESSAGE.getCyclicDependencyInGroupsDefinitionException());
  }

  public GroupDefinitionException getUnableToExpandGroupSequenceException() {
    return new GroupDefinitionException(MESSAGE.getUnableToExpandGroupSequenceException());
  }

  public GroupDefinitionException getInvalidDefaultGroupSequenceDefinitionException() {
    return new GroupDefinitionException(
        MESSAGE.getInvalidDefaultGroupSequenceDefinitionException());
  }

  public GroupDefinitionException getNoDefaultGroupInGroupSequenceException() {
    return new GroupDefinitionException(MESSAGE.getNoDefaultGroupInGroupSequenceException());
  }

  public GroupDefinitionException getBeanClassMustBePartOfRedefinedDefaultGroupSequenceException(
      final String beanClassName) {
    return new GroupDefinitionException(
        MESSAGE.getBeanClassMustBePartOfRedefinedDefaultGroupSequenceException(beanClassName));
  }

  public GroupDefinitionException getWrongDefaultGroupSequenceProviderTypeException(
      final String beanClassName) {
    return new GroupDefinitionException(
        MESSAGE.getWrongDefaultGroupSequenceProviderTypeException(beanClassName));
  }

  public IllegalArgumentException getInvalidExecutableParameterIndexException(
      final String executable, final int index) {
    return new IllegalArgumentException(
        MESSAGE.getInvalidExecutableParameterIndexException(executable, index));
  }

  public ValidationException getUnableToRetrieveAnnotationParameterValueException(
      final Exception exception) {
    return new ValidationException(MESSAGE.getUnableToRetrieveAnnotationParameterValueException(),
        exception);
  }

  public IllegalArgumentException getInvalidLengthOfParameterMetaDataListException(
      final String executableName, final int nbParameters, final int listSize) {
    return new IllegalArgumentException(MESSAGE
        .getInvalidLengthOfParameterMetaDataListException(executableName, nbParameters, listSize));
  }

  public ValidationException getUnableToInstantiateException(final String className,
      final Exception exception) {
    return new ValidationException(MESSAGE.getUnableToInstantiateExceptionOneArg(className),
        exception);
  }

  public ValidationException getUnableToInstantiateException(final Class<?> clazz,
      final Exception exception) {
    return new ValidationException(MESSAGE.getUnableToInstantiateExceptionOneArg(clazz.getName()),
        exception);
  }

  public ValidationException getUnableToInstantiateException(final String message,
      final Class<?> clazz, final Exception exception) {
    return new ValidationException(MESSAGE.getUnableToInstantiateException(message, clazz),
        exception);
  }

  public ValidationException getUnableToLoadClassException(final String className,
      final ClassLoader loader) {
    return new ValidationException(MESSAGE.getUnableToLoadClassException(className, loader));
  }

  public ValidationException getUnableToLoadClassException(final String className,
      final ClassLoader loader, final Exception exception) {
    return new ValidationException(MESSAGE.getUnableToLoadClassException(className, loader),
        exception);
  }

  public IllegalArgumentException getStartIndexCannotBeNegativeException(final int startIndex) {
    return new IllegalArgumentException(MESSAGE.getStartIndexCannotBeNegativeException(startIndex));
  }

  public IllegalArgumentException getEndIndexCannotBeNegativeException(final int endIndex) {
    return new IllegalArgumentException(MESSAGE.getEndIndexCannotBeNegativeException(endIndex));
  }

  public IllegalArgumentException getInvalidRangeException(final int startIndex,
      final int endIndex) {
    return new IllegalArgumentException(MESSAGE.getInvalidRangeException(startIndex, endIndex));
  }

  public IllegalArgumentException getInvalidCheckDigitException(final int startIndex,
      final int endIndex) {
    return new IllegalArgumentException(
        MESSAGE.getInvalidCheckDigitException(startIndex, endIndex));
  }

  public NumberFormatException getCharacterIsNotADigitException(final char character) {
    return new NumberFormatException(MESSAGE.getCharacterIsNotADigitException(character));
  }

  public ConstraintDefinitionException getConstraintParametersCannotStartWithValidException() {
    return new ConstraintDefinitionException(
        MESSAGE.getConstraintParametersCannotStartWithValidException());
  }

  public ConstraintDefinitionException getConstraintWithoutMandatoryParameterException(
      final String parameterName, final String constraintName) {
    return new ConstraintDefinitionException(
        MESSAGE.getConstraintWithoutMandatoryParameterException(parameterName, constraintName));
  }

  public ConstraintDefinitionException getWrongDefaultValueForPayloadParameterException(
      final String constraintName) {
    return new ConstraintDefinitionException(
        MESSAGE.getWrongDefaultValueForPayloadParameterException(constraintName));
  }

  public ConstraintDefinitionException getWrongTypeForPayloadParameterException(
      final String constraintName, final ClassCastException exception) {
    return new ConstraintDefinitionException(
        MESSAGE.getWrongTypeForPayloadParameterException(constraintName), exception);
  }

  public ConstraintDefinitionException getWrongDefaultValueForGroupsParameterException(
      final String constraintName) {
    return new ConstraintDefinitionException(
        MESSAGE.getWrongDefaultValueForGroupsParameterException(constraintName));
  }

  public ConstraintDefinitionException getWrongTypeForGroupsParameterException(
      final String constraintName, final ClassCastException exception) {
    return new ConstraintDefinitionException(
        MESSAGE.getWrongTypeForGroupsParameterException(constraintName), exception);
  }

  public ConstraintDefinitionException getWrongTypeForMessageParameterException(
      final String constraintName) {
    return new ConstraintDefinitionException(
        MESSAGE.getWrongTypeForMessageParameterException(constraintName));
  }

  public ConstraintDefinitionException getOverriddenConstraintAttributeNotFoundException(
      final String attributeName) {
    return new ConstraintDefinitionException(
        MESSAGE.getOverriddenConstraintAttributeNotFoundException(attributeName));
  }

  public ConstraintDefinitionException getWrongAttributeTypeForOverriddenConstraintException(
      final String expectedReturnType, final Class<?> currentReturnType) {
    return new ConstraintDefinitionException(
        MESSAGE.getWrongAttributeTypeForOverriddenConstraintException(expectedReturnType,
            currentReturnType));
  }

  public ValidationException getWrongParameterTypeException(final String expectedType,
      final String currentType) {
    return new ValidationException(
        MESSAGE.getWrongParameterTypeException(expectedType, currentType));
  }

  public ValidationException getUnableToFindAnnotationParameterException(final String parameterName,
      final NoSuchMethodException exception) {
    return new ValidationException(
        MESSAGE.getUnableToFindAnnotationParameterException(parameterName), exception);
  }

  public ValidationException getUnableToGetAnnotationParameterException(final String parameterName,
      final String annotationName, final Exception exception) {
    return new ValidationException(
        MESSAGE.getUnableToGetAnnotationParameterException(parameterName, annotationName),
        exception);
  }

  public IllegalArgumentException getNoValueProvidedForAnnotationParameterException(
      final String parameterName, final String annotation) {
    return new IllegalArgumentException(
        MESSAGE.getNoValueProvidedForAnnotationParameterException(parameterName, annotation));
  }

  public RuntimeException getTryingToInstantiateAnnotationWithUnknownParametersException(
      final Class<?> annotationType, final Set<String> unknownParameters) {
    return new RuntimeException(
        MESSAGE.getTryingToInstantiateAnnotationWithUnknownParametersException(annotationType,
            unknownParameters));
  }

  public IllegalArgumentException getPropertyNameCannotBeNullOrEmptyException() {
    return new IllegalArgumentException(MESSAGE.getPropertyNameCannotBeNullOrEmptyException());
  }

  public IllegalArgumentException getElementTypeHasToBeFieldOrMethodException() {
    return new IllegalArgumentException(MESSAGE.getElementTypeHasToBeFieldOrMethodException());
  }

  // public IllegalArgumentException getMemberIsNeitherAFieldNorAMethodException(final Member
  // member) {
  // return new IllegalArgumentException(
  // MESSAGE.getMemberIsNeitherAFieldNorAMethodException(member));
  // }

  public ValidationException getUnableToAccessMemberException(final String memberName,
      final Exception exception) {
    return new ValidationException(MESSAGE.getUnableToAccessMemberException(memberName), exception);
  }

  public IllegalArgumentException getHasToBeAPrimitiveTypeException(final Class<?> clazz) {
    return new IllegalArgumentException(MESSAGE.getHasToBeAPrimitiveTypeException(clazz));
  }

  public ValidationException getNullIsAnInvalidTypeForAConstraintValidatorException() {
    return new ValidationException(
        MESSAGE.getNullIsAnInvalidTypeForAConstraintValidatorException());
  }

  // public IllegalArgumentException getMissingActualTypeArgumentForTypeParameterException(
  // final TypeVariable<?> typeParameter) {
  // return new IllegalArgumentException(
  // MESSAGE.getMissingActualTypeArgumentForTypeParameterException(typeParameter));
  // }

  public ValidationException getUnableToInstantiateConstraintFactoryClassException(
      final String constraintFactoryClassName, final ValidationException exception) {
    return new ValidationException(
        MESSAGE.getUnableToInstantiateConstraintFactoryClassException(constraintFactoryClassName),
        exception);
  }

  public ValidationException getUnableToOpenInputStreamForMappingFileException(
      final String mappingFileName) {
    return new ValidationException(
        MESSAGE.getUnableToOpenInputStreamForMappingFileException(mappingFileName));
  }

  public ValidationException getUnableToInstantiateMessageInterpolatorClassException(
      final String messageInterpolatorClassName, final Exception exception) {
    return new ValidationException(MESSAGE.getUnableToInstantiateMessageInterpolatorClassException(
        messageInterpolatorClassName), exception);
  }

  public ValidationException getUnableToInstantiateTraversableResolverClassException(
      final String traversableResolverClassName, final Exception exception) {
    return new ValidationException(MESSAGE.getUnableToInstantiateTraversableResolverClassException(
        traversableResolverClassName), exception);
  }

  public ValidationException getUnableToInstantiateValidationProviderClassException(
      final String providerClassName, final Exception exception) {
    return new ValidationException(
        MESSAGE.getUnableToInstantiateValidationProviderClassException(providerClassName),
        exception);
  }

  public ValidationException getUnableToParseValidationXmlFileException(final String file,
      final Exception exception) {
    return new ValidationException(MESSAGE.getUnableToParseValidationXmlFileException(file),
        exception);
  }

  public ValidationException getIsNotAnAnnotationException(final String annotationClassName) {
    return new ValidationException(MESSAGE.getIsNotAnAnnotationException(annotationClassName));
  }

  public ValidationException getIsNotAConstraintValidatorClassException(
      final Class<?> validatorClass) {
    return new ValidationException(
        MESSAGE.getIsNotAConstraintValidatorClassException(validatorClass));
  }

  public ValidationException getBeanClassHasAlreadyBeConfiguredInXmlException(
      final String beanClassName) {
    return new ValidationException(
        MESSAGE.getBeanClassHasAlreadyBeConfiguredInXmlException(beanClassName));
  }

  public ValidationException getIsDefinedTwiceInMappingXmlForBeanException(final String name,
      final String beanClassName) {
    return new ValidationException(
        MESSAGE.getIsDefinedTwiceInMappingXmlForBeanException(name, beanClassName));
  }

  public ValidationException getBeanDoesNotContainTheFieldException(final String beanClassName,
      final String fieldName) {
    return new ValidationException(
        MESSAGE.getBeanDoesNotContainTheFieldException(beanClassName, fieldName));
  }

  public ValidationException getBeanDoesNotContainThePropertyException(final String beanClassName,
      final String getterName) {
    return new ValidationException(
        MESSAGE.getBeanDoesNotContainThePropertyException(beanClassName, getterName));
  }

  public ValidationException getAnnotationDoesNotContainAParameterException(
      final String annotationClassName, final String parameterName) {
    return new ValidationException(
        MESSAGE.getAnnotationDoesNotContainAParameterException(annotationClassName, parameterName));
  }

  public ValidationException getAttemptToSpecifyAnArrayWhereSingleValueIsExpectedException() {
    return new ValidationException(
        MESSAGE.getAttemptToSpecifyAnArrayWhereSingleValueIsExpectedException());
  }

  public ValidationException getUnexpectedParameterValueException() {
    return new ValidationException(MESSAGE.getUnexpectedParameterValueException());
  }

  public ValidationException getUnexpectedParameterValueException(
      final ClassCastException exception) {
    return new ValidationException(MESSAGE.getUnexpectedParameterValueException(), exception);
  }

  public ValidationException getInvalidNumberFormatException(final String formatName,
      final NumberFormatException exception) {
    return new ValidationException(MESSAGE.getInvalidNumberFormatException(formatName), exception);
  }

  public ValidationException getInvalidCharValueException(final String value) {
    return new ValidationException(MESSAGE.getInvalidCharValueException(value));
  }

  public ValidationException getInvalidReturnTypeException(final Class<?> returnType,
      final ClassCastException exception) {
    return new ValidationException(MESSAGE.getInvalidReturnTypeException(returnType), exception);
  }

  public ValidationException getReservedParameterNamesException(final String messageParameterName,
      final String groupsParameterName, final String payloadParameterName) {
    return new ValidationException(MESSAGE.getReservedParameterNamesException(messageParameterName,
        groupsParameterName, payloadParameterName));
  }

  public ValidationException getWrongPayloadClassException(final String payloadClassName) {
    return new ValidationException(MESSAGE.getWrongPayloadClassException(payloadClassName));
  }

  public ValidationException getErrorParsingMappingFileException(final Exception exception) {
    return new ValidationException(MESSAGE.getErrorParsingMappingFileException(), exception);
  }

  public IllegalArgumentException getIllegalArgumentException(final String message) {
    return new IllegalArgumentException(MESSAGE.getIllegalArgumentException(message));
  }

  // public ClassCastException getUnableToNarrowNodeTypeException(final String actualDescriptorType,
  // final ElementKind kind, final String expectedDescriptorType) {
  // return new ClassCastException(MESSAGE.getUnableToNarrowNodeTypeException(actualDescriptorType,
  // kind, expectedDescriptorType));
  // }

  public void usingParameterNameProvider(final String parameterNameProviderClassName) {
    GWT.log(MESSAGE.usingParameterNameProvider(parameterNameProviderClassName));
  }

  public ValidationException getUnableToInstantiateParameterNameProviderClassException(
      final String parameterNameProviderClassName, final ValidationException exception) {
    return new ValidationException(MESSAGE
        .getUnableToInstantiateParameterNameProviderClassException(parameterNameProviderClassName),
        exception);
  }

  public ValidationException getUnableToDetermineSchemaVersionException(final String file,
      final Exception exception) {
    return new ValidationException(MESSAGE.getUnableToDetermineSchemaVersionException(file),
        exception);
  }

  public ValidationException getUnsupportedSchemaVersionException(final String file,
      final String version) {
    return new ValidationException(MESSAGE.getUnsupportedSchemaVersionException(file, version));
  }

  public ConstraintDeclarationException getMultipleGroupConversionsForSameSourceException(
      final Class<?> from, final Set<Class<?>> tos) {
    return new ConstraintDeclarationException(
        MESSAGE.getMultipleGroupConversionsForSameSourceException(from, tos));
  }

  public ConstraintDeclarationException getGroupConversionOnNonCascadingElementException(
      final String location) {
    return new ConstraintDeclarationException(
        MESSAGE.getGroupConversionOnNonCascadingElementException(location));
  }

  public ConstraintDeclarationException getGroupConversionForSequenceException(
      final Class<?> from) {
    return new ConstraintDeclarationException(MESSAGE.getGroupConversionForSequenceException(from));
  }

  public void unknownPropertyInExpressionLanguage(final String expression,
      final Exception exception) {
    GWT.log(MESSAGE.unknownPropertyInExpressionLanguage(expression), exception);
  }

  public void errorInExpressionLanguage(final String expression, final Exception exception) {
    GWT.log(MESSAGE.errorInExpressionLanguage(expression), exception);
  }

  // public ConstraintDeclarationException
  // getMethodReturnValueMustNotBeMarkedMoreThanOnceForCascadedValidationException(
  // final Member member1, final Member member2) {
  // return new ConstraintDeclarationException(
  // MESSAGE.getMethodReturnValueMustNotBeMarkedMoreThanOnceForCascadedValidationException(
  // member1, member2));
  // }

  // public ConstraintDeclarationException getVoidMethodsMustNotBeConstrainedException(
  // final Member member) {
  // return new ConstraintDeclarationException(
  // MESSAGE.getVoidMethodsMustNotBeConstrainedException(member));
  // }

  public ValidationException getBeanDoesNotContainConstructorException(final String beanClassName,
      final String parameterTypes) {
    return new ValidationException(
        MESSAGE.getBeanDoesNotContainConstructorException(beanClassName, parameterTypes));
  }

  public ValidationException getInvalidParameterTypeException(final String type,
      final String beanClassName) {
    return new ValidationException(MESSAGE.getInvalidParameterTypeException(type, beanClassName));
  }

  public ValidationException getBeanDoesNotContainMethodException(final String beanClassName,
      final String methodName, final List<Class<?>> parameterTypes) {
    return new ValidationException(
        MESSAGE.getBeanDoesNotContainMethodException(beanClassName, methodName, parameterTypes));
  }

  public ValidationException getUnableToLoadConstraintAnnotationClassException(
      final String constraintAnnotationClass, final Exception exception) {
    return new ValidationException(
        MESSAGE.getUnableToLoadConstraintAnnotationClassException(constraintAnnotationClass),
        exception);
  }

  public ValidationException getMethodIsDefinedTwiceInMappingXmlForBeanException(final String name,
      final String beanClassName) {
    return new ValidationException(
        MESSAGE.getMethodIsDefinedTwiceInMappingXmlForBeanException(name, beanClassName));
  }

  public ValidationException getConstructorIsDefinedTwiceInMappingXmlForBeanException(
      final String name, final String beanClassName) {
    return new ValidationException(
        MESSAGE.getConstructorIsDefinedTwiceInMappingXmlForBeanException(name, beanClassName));
  }

  public ConstraintDefinitionException getMultipleCrossParameterValidatorClassesException(
      final String constraint) {
    return new ConstraintDefinitionException(
        MESSAGE.getMultipleCrossParameterValidatorClassesException(constraint));
  }

  public ConstraintDeclarationException getImplicitConstraintTargetInAmbiguousConfigurationException(
      final String constraint) {
    return new ConstraintDeclarationException(
        MESSAGE.getImplicitConstraintTargetInAmbiguousConfigurationException(constraint));
  }

  public ConstraintDeclarationException getCrossParameterConstraintOnMethodWithoutParametersException(
      final String constraint, final String member) {
    return new ConstraintDeclarationException(
        MESSAGE.getCrossParameterConstraintOnMethodWithoutParametersException(constraint, member));
  }

  public ConstraintDeclarationException getCrossParameterConstraintOnClassException(
      final String constraint) {
    return new ConstraintDeclarationException(
        MESSAGE.getCrossParameterConstraintOnClassException(constraint));
  }

  public ConstraintDeclarationException getCrossParameterConstraintOnFieldException(
      final String constraint, final String field) {
    return new ConstraintDeclarationException(
        MESSAGE.getCrossParameterConstraintOnFieldException(constraint, field));
  }

  public IllegalStateException getParameterNodeAddedForNonCrossParameterConstraintException(
      final Path path) {
    return new IllegalStateException(
        MESSAGE.getParameterNodeAddedForNonCrossParameterConstraintException(path));
  }

  public ValidationException getConstrainedElementConfiguredMultipleTimesException(
      final String location) {
    return new ValidationException(
        MESSAGE.getConstrainedElementConfiguredMultipleTimesException(location));
  }

  public void evaluatingExpressionLanguageExpressionCausedException(final String expression,
      final Exception exception) {
    GWT.log(MESSAGE.evaluatingExpressionLanguageExpressionCausedException(expression), exception);
  }

  public ValidationException getExceptionOccurredDuringMessageInterpolationException(
      final Exception exception) {
    return new ValidationException(
        MESSAGE.getExceptionOccurredDuringMessageInterpolationException(), exception);
  }

  public UnexpectedTypeException getMultipleValidatorsForSameTypeException(final String constraint,
      final String type) {
    return new UnexpectedTypeException(
        MESSAGE.getMultipleValidatorsForSameTypeException(constraint, type));
  }

  // public ConstraintDeclarationException getParameterConfigurationAlteredInSubTypeException(
  // final Member superMethod, final Member subMethod) {
  // return new ConstraintDeclarationException(
  // MESSAGE.getParameterConfigurationAlteredInSubTypeException(superMethod, subMethod));
  // }
  //
  // public ConstraintDeclarationException
  // getParameterConstraintsDefinedInMethodsFromParallelTypesException(
  // final Member method1, final Member method2) {
  // return new ConstraintDeclarationException(MESSAGE
  // .getParameterConstraintsDefinedInMethodsFromParallelTypesException(method1, method2));
  // }

  // public ConstraintDeclarationException
  // getParametersOrReturnValueConstraintTargetGivenAtNonExecutableException(
  // final String constraint, final ConstraintTarget target) {
  // return new ConstraintDeclarationException(
  // MESSAGE.getParametersOrReturnValueConstraintTargetGivenAtNonExecutableException(constraint,
  // target));
  // }

  public ConstraintDefinitionException getCrossParameterConstraintHasNoValidatorException(
      final String constraint) {
    return new ConstraintDefinitionException(
        MESSAGE.getCrossParameterConstraintHasNoValidatorException(constraint));
  }

  // public ConstraintDefinitionException
  // getComposedAndComposingConstraintsHaveDifferentTypesException(
  // final String composedConstraintTypeName, final String composingConstraintTypeName,
  // final ConstraintType composedConstraintType, final ConstraintType composingConstraintType) {
  // return new ConstraintDefinitionException(MESSAGE
  // .getComposedAndComposingConstraintsHaveDifferentTypesException(composedConstraintTypeName,
  // composingConstraintTypeName, composedConstraintType, composingConstraintType));
  // }

  public ConstraintDefinitionException getGenericAndCrossParameterConstraintDoesNotDefineValidationAppliesToParameterException(
      final String constraint) {
    return new ConstraintDefinitionException(MESSAGE
        .getGenericAndCrossParameterConstraintDoesNotDefineValidationAppliesToParameterException(
            constraint));
  }

  public ConstraintDefinitionException getValidationAppliesToParameterMustHaveReturnTypeConstraintTargetException(
      final String constraint) {
    return new ConstraintDefinitionException(MESSAGE
        .getValidationAppliesToParameterMustHaveReturnTypeConstraintTargetException(constraint));
  }

  public ConstraintDefinitionException getValidationAppliesToParameterMustHaveDefaultValueImplicitException(
      final String constraint) {
    return new ConstraintDefinitionException(
        MESSAGE.getValidationAppliesToParameterMustHaveDefaultValueImplicitException(constraint));
  }

  public ConstraintDefinitionException getValidationAppliesToParameterMustNotBeDefinedForNonGenericAndCrossParameterConstraintException(
      final String constraint) {
    return new ConstraintDefinitionException(MESSAGE
        .getValidationAppliesToParameterMustNotBeDefinedForNonGenericAndCrossParameterConstraintException(
            constraint));
  }

  public ConstraintDefinitionException getValidatorForCrossParameterConstraintMustEitherValidateObjectOrObjectArrayException(
      final String constraint) {
    return new ConstraintDefinitionException(MESSAGE
        .getValidatorForCrossParameterConstraintMustEitherValidateObjectOrObjectArrayException(
            constraint));
  }

  // public ConstraintDeclarationException
  // getMethodsFromParallelTypesMustNotDefineGroupConversionsForCascadedReturnValueException(
  // final Member method1, final Member method2) {
  // return new ConstraintDeclarationException(MESSAGE
  // .getMethodsFromParallelTypesMustNotDefineGroupConversionsForCascadedReturnValueException(
  // method1, method2));
  // }
  //
  // public IllegalArgumentException getMethodOrConstructorNotDefinedByValidatedTypeException(
  // final String validatedTypeName, final Member member) {
  // return new IllegalArgumentException(MESSAGE
  // .getMethodOrConstructorNotDefinedByValidatedTypeException(validatedTypeName, member));
  // }
  //
  // public IllegalArgumentException getParameterTypesDoNotMatchException(final String actualType,
  // final String expectedType, final int index, final Member member) {
  // return new IllegalArgumentException(
  // MESSAGE.getParameterTypesDoNotMatchException(actualType, expectedType, index, member));
  // }

  public IllegalArgumentException getHasToBeABoxedTypeException(final Class<?> clazz) {
    return new IllegalArgumentException(MESSAGE.getHasToBeABoxedTypeException(clazz));
  }

  public IllegalArgumentException getMixingImplicitWithOtherExecutableTypesException() {
    return new IllegalArgumentException(
        MESSAGE.getMixingImplicitWithOtherExecutableTypesException());
  }

  // public ValidationException getValidateOnExecutionOnOverriddenOrInterfaceMethodException(
  // final Method m) {
  // return new ValidationException(
  // MESSAGE.getValidateOnExecutionOnOverriddenOrInterfaceMethodException(m));
  // }

  public ValidationException getOverridingConstraintDefinitionsInMultipleMappingFilesException(
      final String constraintClass) {
    return new ValidationException(
        MESSAGE.getOverridingConstraintDefinitionsInMultipleMappingFilesException(constraintClass));
  }

  // public MessageDescriptorFormatException getNonTerminatedParameterException(
  // final String messageDescriptor, final char character) {
  // return new MessageDescriptorFormatException(
  // MESSAGE.getNonTerminatedParameterException(messageDescriptor, character));
  // }
  //
  // public MessageDescriptorFormatException getNestedParameterException(
  // final String messageDescriptor) {
  // return new MessageDescriptorFormatException(
  // MESSAGE.getNestedParameterException(messageDescriptor));
  // }

  public ConstraintDeclarationException getCreationOfScriptExecutorFailedException(
      final String languageName, final Exception exception) {
    return new ConstraintDeclarationException(
        MESSAGE.getCreationOfScriptExecutorFailedException(languageName), exception);
  }

  public ValidationException getBeanClassHasAlreadyBeConfiguredViaProgrammaticApiException(
      final String beanClassName) {
    return new ValidationException(
        MESSAGE.getBeanClassHasAlreadyBeConfiguredViaProgrammaticApiException(beanClassName));
  }

  public ValidationException getPropertyHasAlreadyBeConfiguredViaProgrammaticApiException(
      final String beanClassName, final String propertyName) {
    return new ValidationException(MESSAGE
        .getPropertyHasAlreadyBeConfiguredViaProgrammaticApiException(beanClassName, propertyName));
  }

  public ValidationException getMethodHasAlreadyBeConfiguredViaProgrammaticApiException(
      final String beanClassName, final String method) {
    return new ValidationException(
        MESSAGE.getMethodHasAlreadyBeConfiguredViaProgrammaticApiException(beanClassName, method));
  }

  public ValidationException getParameterHasAlreadyBeConfiguredViaProgrammaticApiException(
      final String beanClassName, final String executable, final int parameterIndex) {
    return new ValidationException(
        MESSAGE.getParameterHasAlreadyBeConfiguredViaProgrammaticApiException(beanClassName,
            executable, parameterIndex));
  }

  public ValidationException getReturnValueHasAlreadyBeConfiguredViaProgrammaticApiException(
      final String beanClassName, final String executable) {
    return new ValidationException(
        MESSAGE.getReturnValueHasAlreadyBeConfiguredViaProgrammaticApiException(beanClassName,
            executable));
  }

  public ValidationException getConstructorHasAlreadyBeConfiguredViaProgrammaticApiException(
      final String beanClassName, final String constructor) {
    return new ValidationException(
        MESSAGE.getConstructorHasAlreadyBeConfiguredViaProgrammaticApiException(beanClassName,
            constructor));
  }

  public ValidationException getCrossParameterElementHasAlreadyBeConfiguredViaProgrammaticApiException(
      final String beanClassName, final String executable) {
    return new ValidationException(
        MESSAGE.getCrossParameterElementHasAlreadyBeConfiguredViaProgrammaticApiException(
            beanClassName, executable));
  }

  public IllegalArgumentException getMultiplierCannotBeNegativeException(final int multiplier) {
    return new IllegalArgumentException(MESSAGE.getMultiplierCannotBeNegativeException(multiplier));
  }

  public IllegalArgumentException getWeightCannotBeNegativeException(final int weight) {
    return new IllegalArgumentException(MESSAGE.getWeightCannotBeNegativeException(weight));
  }

  public IllegalArgumentException getTreatCheckAsIsNotADigitNorALetterException(final int weight) {
    return new IllegalArgumentException(
        MESSAGE.getTreatCheckAsIsNotADigitNorALetterException(weight));
  }

  public IllegalArgumentException getInvalidParameterCountForExecutableException(
      final String executable, final int expectedParameterCount, final int actualParameterCount) {
    return new IllegalArgumentException(MESSAGE.getInvalidParameterCountForExecutableException(
        executable, expectedParameterCount, actualParameterCount));
  }

  public ValidationException getNoUnwrapperFoundForTypeException(final String typeName) {
    return new ValidationException(MESSAGE.getNoUnwrapperFoundForTypeException(typeName));
  }

  public ValidationException getMissingELDependenciesException() {
    return new ValidationException(MESSAGE.getMissingELDependenciesException());
  }

  public void creationOfParameterMessageInterpolation() {
    GWT.log(MESSAGE.creationOfParameterMessageInterpolation());
  }

  public void getElUnsupported(final String expression) {
    GWT.log(MESSAGE.getElUnsupported(expression));
  }

  public UnexpectedTypeException getConstraintValidatorExistsForWrapperAndWrappedValueException(
      final String property, final String constraint, final String valueHandler) {
    return new UnexpectedTypeException(
        MESSAGE.getConstraintValidatorExistsForWrapperAndWrappedValueException(property, constraint,
            valueHandler));
  }

  public ValidationException getTypeAnnotationConstraintOnIterableRequiresUseOfValidAnnotationException(
      final String declaringClass, final String name) {
    return new ValidationException(
        MESSAGE.getTypeAnnotationConstraintOnIterableRequiresUseOfValidAnnotationException(
            declaringClass, name));
  }

  void parameterizedTypeWithMoreThanOneTypeArgumentIsNotSupported(final String type) {
    GWT.log(MESSAGE.parameterizedTypeWithMoreThanOneTypeArgumentIsNotSupported(type));
  }

  public ConstraintDeclarationException getInconsistentValueUnwrappingConfigurationBetweenFieldAndItsGetterException(
      final String property, final String clazz) {
    return new ConstraintDeclarationException(
        MESSAGE.getInconsistentValueUnwrappingConfigurationBetweenFieldAndItsGetterException(
            property, clazz));
  }

  public ValidationException getUnableToCreateXMLEventReader(final String file,
      final Exception exception) {
    return new ValidationException(MESSAGE.getUnableToCreateXMLEventReader(file), exception);
  }

  public ValidationException validatedValueUnwrapperCannotBeCreated(final String className,
      final Exception exception) {
    return new ValidationException(MESSAGE.validatedValueUnwrapperCannotBeCreated(className),
        exception);
  }

  public void unknownJvmVersion(final String vmVersionStr) {
    GWT.log(MESSAGE.unknownJvmVersion(vmVersionStr));
  }

  public ValidationException getConstraintHasAlreadyBeenConfiguredViaProgrammaticApiException(
      final String annotationClassName) {
    return new ValidationException(MESSAGE
        .getConstraintHasAlreadyBeenConfiguredViaProgrammaticApiException(annotationClassName));
  }

  public ValidationException getEmptyElementOnlySupportedWhenCharSequenceIsExpectedExpection() {
    return new ValidationException(
        MESSAGE.getEmptyElementOnlySupportedWhenCharSequenceIsExpectedExpection());
  }

  public ValidationException getUnableToReachPropertyToValidateException(final Object bean,
      final Path path) {
    return new ValidationException(
        MESSAGE.getUnableToReachPropertyToValidateException(bean, Objects.toString(path)));
  }

  public ValidationException getUnableToConvertTypeToClassException(final Object type) {
    return new ValidationException(
        MESSAGE.getUnableToConvertTypeToClassException(Objects.toString(type)));
  }

  public void debugf(final String pstring, final Object pdescriptor) {
    GWT.log(StringUtils.replace(pstring, "%s", Objects.toString(pdescriptor)));
  }

  public boolean isDebugEnabled() {
    return !GWT.isProdMode();
  }

  public ClassCastException getUnableToNarrowNodeTypeException(
      final Class<? extends NodeImpl> pclass, final ElementKind pkind, final Class<?> pnodeType) {
    final ClassCastException result = new ClassCastException();
    final StackTraceElement[] st = result.getStackTrace();
    result.setStackTrace(Arrays.copyOfRange(st, 1, st.length));
    return result;
  }
}
