/*
 * Copyright 2010 Google Inc. Copyright 2016 Manfred Tremmel
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.knightsoftnet.validators.client.impl;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.util.Objects;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.metadata.ConstraintDescriptor;

/**
 * An implementation of {@link ConstraintViolation}.
 *
 * @param <T> the type of bean validated.
 */
public class ConstraintViolationImpl<T> implements ConstraintViolation<T>, Serializable {

  /**
   * Builder for ConstraintViolations.
   *
   * @param <T> the type of bean validated.
   */
  public static class Builder<T> {
    private String message;
    private String messageTemplate;
    private T rootBean;
    private Class<T> rootBeanClass;
    private Object leafBean;
    private Path propertyPath;
    private Object invalidValue;
    private ElementType elementType;
    private ConstraintDescriptor<?> constraintDescriptor;

    /**
     * build new constraint violation implementation.
     *
     * @return ConstraintViolationImpl
     */
    public ConstraintViolationImpl<T> build() {
      return new ConstraintViolationImpl<>(this.message, this.messageTemplate, // NOPMD
          this.rootBean, this.rootBeanClass, this.leafBean, this.propertyPath, this.invalidValue,
          this.elementType, this.constraintDescriptor);
    }

    public Builder<T> setConstraintDescriptor(final ConstraintDescriptor<?> constraintDescriptor) {
      this.constraintDescriptor = constraintDescriptor;
      return this;
    }

    public Builder<T> setElementType(final ElementType elementType) {
      this.elementType = elementType;
      return this;
    }

    public Builder<T> setInvalidValue(final Object invalidValue) {
      this.invalidValue = invalidValue;
      return this;
    }

    public Builder<T> setLeafBean(final Object leafBean) {
      this.leafBean = leafBean;
      return this;
    }

    public Builder<T> setMessage(final String message) {
      this.message = message;
      return this;
    }

    public Builder<T> setMessageTemplate(final String messageTemplate) {
      this.messageTemplate = messageTemplate;
      return this;
    }

    public Builder<T> setPropertyPath(final Path propertyPath) {
      this.propertyPath = propertyPath;
      return this;
    }

    public Builder<T> setRootBean(final T rootBean) {
      this.rootBean = rootBean;
      return this;
    }

    public Builder<T> setRootBeanClass(final Class<T> rootBeanClass) {
      this.rootBeanClass = rootBeanClass;
      return this;
    }
  }

  private static final long serialVersionUID = 1L;

  public static <T> Builder<T> builder() {
    return new Builder<>();
  }

  private final String message;
  private final String messageTemplate;
  private final T rootBean;
  private final Class<T> rootBeanClass;
  private final Object leafBean;
  private final Path propertyPath;
  private final Object invalidValue;
  private final ElementType elementType;
  private final ConstraintDescriptor<?> constraintDescriptor;

  protected ConstraintViolationImpl(final String message, final String messageTemplate,
      final T rootBean, final Class<T> rootBeanClass, final Object leafBean,
      final Path propertyPath, final Object invalidValue, final ElementType elementType,
      final ConstraintDescriptor<?> constraintDescriptor) {
    super();
    this.message = message;
    this.messageTemplate = messageTemplate;
    this.rootBean = rootBean;
    this.rootBeanClass = rootBeanClass;
    this.leafBean = leafBean;
    this.propertyPath = propertyPath;
    this.invalidValue = invalidValue;
    this.elementType = elementType;
    this.constraintDescriptor = constraintDescriptor;
  }

  @Override
  public boolean equals(final Object pobject) {
    if (this == pobject) {
      return true;
    }
    if (!(pobject instanceof ConstraintViolationImpl)) {
      return false;
    }
    final ConstraintViolationImpl<?> other = (ConstraintViolationImpl<?>) pobject;
    return Objects.equals(this.message, other.message)
        && Objects.equals(this.propertyPath, other.propertyPath)
        && Objects.equals(this.rootBean, other.rootBean)
        && Objects.equals(this.leafBean, other.leafBean)
        && Objects.equals(this.elementType, other.elementType)
        && Objects.equals(this.invalidValue, other.invalidValue);
  }

  @Override
  public ConstraintDescriptor<?> getConstraintDescriptor() {
    return this.constraintDescriptor;
  }

  @Override
  public Object getInvalidValue() {
    return this.invalidValue;
  }

  @Override
  public Object getLeafBean() {
    return this.leafBean;
  }

  @Override
  public String getMessage() {
    return this.message;
  }

  @Override
  public String getMessageTemplate() {
    return this.messageTemplate;
  }

  @Override
  public Path getPropertyPath() {
    return this.propertyPath;
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
  public int hashCode() {
    return Objects.hash(this.message, this.propertyPath, this.rootBean, this.leafBean,
        this.elementType, this.invalidValue);
  }

  /**
   * For debugging only. Do not rely on the format. It can change at any time.
   */
  @Override
  public String toString() {
    return "ConstraintViolationImpl(message= " + this.message //
        + ", path= " + this.propertyPath //
        + ", invalidValue=" + this.invalidValue //
        + ", desc=" + this.constraintDescriptor //
        + ", elementType=" + this.elementType + ")";
  }

  @Override
  public Object[] getExecutableParameters() {
    // not supported by gwt implementation
    return new Object[0];
  }

  @Override
  public Object getExecutableReturnValue() {
    // not supported by gwt implementation
    return null;
  }

  @Override
  public <U> U unwrap(final Class<U> ptype) {
    throw new UnsupportedOperationException("GWT Validation does not support upwrap()");
  }
}
