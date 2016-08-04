/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package org.hibernate.validator.internal.engine;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.util.Map;
import java.util.Objects;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.metadata.ConstraintDescriptor;

/**
 * An implementation of {@link ConstraintViolation}.
 *
 * @param <T> the type of bean validated.
 *
 * @author Emmanuel Bernard
 * @author Hardy Ferentschik
 * @author Manfred Tremmel - gwt port
 */
public final class ConstraintViolationImpl<T> implements ConstraintViolation<T>, Serializable {
  private static final long serialVersionUID = -4970067626703103139L;

  private final String interpolatedMessage;
  private final T rootBean;
  private final Object value;
  private final Path propertyPath;
  private final Object leafBeanInstance;
  private final ConstraintDescriptor<?> constraintDescriptor;
  private final String messageTemplate;
  private final Map<String, Object> expressionVariables;
  private final Class<T> rootBeanClass;
  private final ElementType elementType;
  private final Object[] executableParameters;
  private final Object executableReturnValue;
  private final int hashCodeValue;

  /**
   * create ConstraintViolation for bean validation.
   */
  public static <T> ConstraintViolation<T> forBeanValidation(final String messageTemplate,
      final Map<String, Object> expressionVariables, final String interpolatedMessage,
      final Class<T> rootBeanClass, final T rootBean, final Object leafBeanInstance,
      final Object value, final Path propertyPath,
      final ConstraintDescriptor<?> constraintDescriptor, final ElementType elementType) {
    return new ConstraintViolationImpl<T>(messageTemplate, expressionVariables, interpolatedMessage,
        rootBeanClass, rootBean, leafBeanInstance, value, propertyPath, constraintDescriptor,
        elementType, null, null);
  }

  /**
   * create ConstraintViolation for parameter validation.
   */
  public static <T> ConstraintViolation<T> forParameterValidation(final String messageTemplate,
      final Map<String, Object> expressionVariables, final String interpolatedMessage,
      final Class<T> rootBeanClass, final T rootBean, final Object leafBeanInstance,
      final Object value, final Path propertyPath,
      final ConstraintDescriptor<?> constraintDescriptor, final ElementType elementType,
      final Object[] executableParameters) {
    return new ConstraintViolationImpl<T>(messageTemplate, expressionVariables, interpolatedMessage,
        rootBeanClass, rootBean, leafBeanInstance, value, propertyPath, constraintDescriptor,
        elementType, executableParameters, null);
  }

  /**
   * create ConstraintViolation for return value validation.
   */
  public static <T> ConstraintViolation<T> forReturnValueValidation(final String messageTemplate,
      final Map<String, Object> expressionVariables, final String interpolatedMessage,
      final Class<T> rootBeanClass, final T rootBean, final Object leafBeanInstance,
      final Object value, final Path propertyPath,
      final ConstraintDescriptor<?> constraintDescriptor, final ElementType elementType,
      final Object executableReturnValue) {
    return new ConstraintViolationImpl<T>(messageTemplate, expressionVariables, interpolatedMessage,
        rootBeanClass, rootBean, leafBeanInstance, value, propertyPath, constraintDescriptor,
        elementType, null, executableReturnValue);
  }

  private ConstraintViolationImpl(final String messageTemplate,
      final Map<String, Object> expressionVariables, final String interpolatedMessage,
      final Class<T> rootBeanClass, final T rootBean, final Object leafBeanInstance,
      final Object value, final Path propertyPath,
      final ConstraintDescriptor<?> constraintDescriptor, final ElementType elementType,
      final Object[] executableParameters, final Object executableReturnValue) {
    this.messageTemplate = messageTemplate;
    this.expressionVariables = expressionVariables;
    this.interpolatedMessage = interpolatedMessage;
    this.rootBean = rootBean;
    this.value = value;
    this.propertyPath = propertyPath;
    this.leafBeanInstance = leafBeanInstance;
    this.constraintDescriptor = constraintDescriptor;
    this.rootBeanClass = rootBeanClass;
    this.elementType = elementType;
    this.executableParameters = executableParameters;
    this.executableReturnValue = executableReturnValue;
    // pre-calculate hash code, the class is immutable and hashCode is needed often
    this.hashCodeValue = this.createHashCode();
  }

  @Override
  public String getMessage() {
    return this.interpolatedMessage;
  }

  @Override
  public String getMessageTemplate() {
    return this.messageTemplate;
  }

  /**
   * get expression variables.
   *
   * @return the expression variables added using
   *         {@link ConstraintValidatorContextImpl#addExpressionVariable(String, Object)}
   */
  public Map<String, Object> getExpressionVariables() {
    return this.expressionVariables;
  }

  @Override
  public T getRootBean() {
    return this.rootBean;
  }

  @Override
  public Class<T> getRootBeanClass() {
    return this.rootBeanClass;
  }

  @Override
  public Object getLeafBean() {
    return this.leafBeanInstance;
  }

  @Override
  public Object getInvalidValue() {
    return this.value;
  }

  @Override
  public Path getPropertyPath() {
    return this.propertyPath;
  }

  @Override
  public ConstraintDescriptor<?> getConstraintDescriptor() {
    return this.constraintDescriptor;
  }

  @Override
  public <C> C unwrap(final Class<C> type) {
    throw new UnsupportedOperationException("GWT Validation does not support upwrap()");
  }

  @Override
  public Object[] getExecutableParameters() {
    return this.executableParameters;
  }

  @Override
  public Object getExecutableReturnValue() {
    return this.executableReturnValue;
  }

  @Override
  // IMPORTANT - some behaviour of Validator depends on the correct implementation of this equals
  // method! (HF)

  // Do not take expressionVariables into account here. If everything else matches, the two CV
  // should be considered
  // equals (and because of the scary comment above). After all, expressionVariables is just a hint
  // about how we got
  // to the actual CV. (NF)
  public boolean equals(final Object pobject) {
    if (this == pobject) {
      return true;
    }
    if (pobject == null || this.getClass() != pobject.getClass()) {
      return false;
    }

    final ConstraintViolationImpl<?> that = (ConstraintViolationImpl<?>) pobject;

    return Objects.equals(this.interpolatedMessage, that.interpolatedMessage)
        && Objects.equals(this.propertyPath, that.propertyPath)
        && Objects.equals(this.rootBean, that.rootBean)
        && Objects.equals(this.leafBeanInstance, that.leafBeanInstance)
        && Objects.equals(this.constraintDescriptor, that.constraintDescriptor)
        && Objects.equals(this.elementType, that.elementType)
        && Objects.equals(this.messageTemplate, that.messageTemplate)
        && Objects.equals(this.rootBeanClass, that.rootBeanClass)
        && Objects.equals(this.value, that.value);
  }

  @Override
  public int hashCode() {
    return this.hashCodeValue;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder(128);
    sb.append("ConstraintViolationImpl");
    sb.append("{interpolatedMessage='").append(this.interpolatedMessage).append('\'');
    sb.append(", propertyPath=").append(this.propertyPath);
    sb.append(", rootBeanClass=").append(this.rootBeanClass);
    sb.append(", messageTemplate='").append(this.messageTemplate).append('\'');
    sb.append('}');
    return sb.toString();
  }

  // Same as for equals, do not take expressionVariables into account here.
  private int createHashCode() {
    return Objects.hash(this.interpolatedMessage, this.propertyPath, this.rootBean,
        this.leafBeanInstance, this.value, this.constraintDescriptor, this.messageTemplate,
        this.rootBeanClass, this.elementType);
  }
}
