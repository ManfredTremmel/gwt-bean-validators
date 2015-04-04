/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.knightsoftnet.validators.shared.exceptions;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.engine.PathImpl;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.ConstraintViolation;
import javax.validation.metadata.ConstraintDescriptor;
import javax.validation.Path;

/**
 * This is a limited implementation of ConstraintViolation interface which can be serialized and
 * sent from server to client. Only the values are included which are needed for GWT to handle the
 * validation errors in the editor.
 *
 * @author Manfred Tremmel
 *
 * @param <T> class type which is handled
 */
public class SerializeableConstraintValidationImpl<T> implements ConstraintViolation<T>,
    Serializable {

  /**
   * serial version unique id.
   */
  private static final long serialVersionUID = -8212465590635669253L;

  private String interpolatedMessage;
  private String propertyPath;


  /**
   * default constructor, only added for serialization.
   */
  public SerializeableConstraintValidationImpl() {
    super();
  }

  /**
   * constructor initializing fields from another ConstraintViolation implementation.
   *
   * @param pviolation ConstraintViolation to take values from
   */
  public SerializeableConstraintValidationImpl(final ConstraintViolation<T> pviolation) {
    super();
    this.setMessage(pviolation.getMessage());
    this.setPropertyPath(pviolation.getPropertyPath());
  }

  @Override
  public String getMessage() {
    return this.interpolatedMessage;
  }

  @Override
  public String getMessageTemplate() {
    return null;
  }

  @Override
  public T getRootBean() {
    return null;
  }

  @Override
  public Class<T> getRootBeanClass() {
    return null;
  }

  @Override
  public Object getLeafBean() {
    return null;
  }

  @Override
  public Object getInvalidValue() {
    return null;
  }

  @Override
  public Path getPropertyPath() {
    return PathImpl.createPathFromString(this.propertyPath);
  }

  @Override
  @SuppressWarnings({"unchecked", "rawtypes"})
  public ConstraintDescriptor getConstraintDescriptor() {
    return null;
  }

  private void setMessage(final String pinterpolatedMessage) {
    this.interpolatedMessage = pinterpolatedMessage;
  }

  private void setPropertyPath(final Path ppropertyPath) {
    this.propertyPath = ppropertyPath.toString();
  }

  @Override
  public final boolean equals(final Object pobject) {
    if (this == pobject) {
      return true;
    }
    if (!(pobject instanceof SerializeableConstraintValidationImpl<?>)) {
      return false;
    }

    @SuppressWarnings("unchecked")
    final SerializeableConstraintValidationImpl<T> that =
        (SerializeableConstraintValidationImpl<T>) pobject;

    if (!StringUtils.equals(this.interpolatedMessage, that.interpolatedMessage)) {
      return false;
    }
    if (!Objects.equals(this.propertyPath, that.propertyPath)) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.interpolatedMessage, this.propertyPath);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder(128);
    sb.append("SerializeableConstraintValidationImpl");
    sb.append("{interpolatedMessage='").append(this.interpolatedMessage).append('\'');
    sb.append(", propertyPath=").append(this.propertyPath).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
