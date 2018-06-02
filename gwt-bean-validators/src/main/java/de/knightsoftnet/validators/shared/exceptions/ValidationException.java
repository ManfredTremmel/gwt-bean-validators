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

import org.hibernate.validator.internal.engine.ConstraintViolationImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;

/**
 * The ValidationException is thrown, when a argument is not valid by bean validation.
 *
 * @author Manfred Tremmel
 */
public class ValidationException extends Exception implements Serializable {
  /**
   * serial version unique id.
   */
  private static final long serialVersionUID = 2524551230822310527L;

  /**
   * validation error set.
   */
  private ArrayList<SerializeableConstraintValidationImpl<?>> validationErrorSet;

  /**
   * default constructor.
   */
  public ValidationException() {
    super();
    validationErrorSet = new ArrayList<>(1);
  }

  /**
   * constructor adding a set of validation errors.
   *
   * @param pvalidationErrorSet a set of validation errors
   */
  @SuppressWarnings("rawtypes")
  public ValidationException(final Set pvalidationErrorSet) {
    super();
    this.setValidationErrorSet(pvalidationErrorSet);
  }

  /**
   * constructor adding a message text.
   *
   * @param pmessage message to add to the exception
   */
  public ValidationException(final String pmessage) {
    super(pmessage);
    validationErrorSet = new ArrayList<>(1);
  }

  /**
   * constructor adding a text and another exception/throwable to the exception.
   *
   * @param pmessage message to add to the exception
   * @param pvalidationErrorSet a set of validation errors
   */
  @SuppressWarnings("rawtypes")
  public ValidationException(final String pmessage, final Set pvalidationErrorSet) {
    super(pmessage);
    this.setValidationErrorSet(pvalidationErrorSet);
  }

  /**
   * get validation error set.
   *
   * @param pclass class to get data from
   * @return the validationErrorSet
   */
  @SuppressWarnings({"unchecked"})
  public final ArrayList<ConstraintViolation<?>> getValidationErrorSet(final Object pclass) {
    return new ArrayList<>(validationErrorSet.stream()
        .map(violation -> ConstraintViolationImpl.forBeanValidation( //
            violation.getMessageTemplate(), //
            Collections.emptyMap(), //
            Collections.emptyMap(), //
            violation.getMessage(), //
            ((SerializeableConstraintValidationImpl<Object>) violation).getRootBeanClass(), //
            pclass, //
            violation.getLeafBean(), //
            null, //
            violation.getPropertyPath(), //
            violation.getConstraintDescriptor(), //
            null, //
            null))
        .collect(Collectors.toList()));
  }

  /**
   * set validation error set.
   *
   * @param pvalidationErrorSet the validationErrorSet to set
   */
  @SuppressWarnings({"rawtypes", "unchecked"})
  public final void setValidationErrorSet(final Set pvalidationErrorSet) {
    validationErrorSet =
        new ArrayList<>((List<SerializeableConstraintValidationImpl<?>>) pvalidationErrorSet
            .stream().map(violation -> new SerializeableConstraintValidationImpl(
                (ConstraintViolation<?>) violation))
            .collect(Collectors.toList()));
  }

  /**
   * set validation error set.
   *
   * @param pvalidationErrorSet the validationErrorSet to set
   */
  @SuppressWarnings({"rawtypes", "unchecked"})
  public final void setValidationErrorSet(
      final ArrayList<ConstraintViolation<?>> pvalidationErrorSet) {
    validationErrorSet = new ArrayList<>(pvalidationErrorSet.stream().map(
        violation -> (SerializeableConstraintValidationImpl<?>) new SerializeableConstraintValidationImpl(
            violation))
        .collect(Collectors.toList()));
  }
}
