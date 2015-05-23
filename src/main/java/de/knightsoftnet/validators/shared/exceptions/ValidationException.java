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

import org.hibernate.validator.engine.ConstraintViolationImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

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
    this.validationErrorSet = new ArrayList<SerializeableConstraintValidationImpl<?>>(1);
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
    this.validationErrorSet = new ArrayList<SerializeableConstraintValidationImpl<?>>(1);
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
   * @return the validationErrorSet
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  public final ArrayList<ConstraintViolation<?>> getValidationErrorSet(final Object pclass) {
    final ArrayList<ConstraintViolation<?>> violations =
        new ArrayList<ConstraintViolation<?>>(this.validationErrorSet.size());
    for (final SerializeableConstraintValidationImpl<?> violation : this.validationErrorSet) {
      violations.add(new ConstraintViolationImpl(violation.getMessageTemplate(), violation
          .getMessage(), violation.getRootBeanClass(), pclass, violation.getLeafBean(), null,
          violation.getPropertyPath(), violation.getConstraintDescriptor(), null));
    }
    return violations;
  }

  /**
   * set validation error set.
   *
   * @param pvalidationErrorSet the validationErrorSet to set
   */
  @SuppressWarnings({"rawtypes", "unchecked"})
  public final void setValidationErrorSet(final Set pvalidationErrorSet) {
    this.validationErrorSet =
        new ArrayList<SerializeableConstraintValidationImpl<?>>(pvalidationErrorSet.size());
    final Iterator iterator = pvalidationErrorSet.iterator();
    while (iterator.hasNext()) {
      final ConstraintViolation<?> violation = (ConstraintViolation<?>) iterator.next();
      // this.validationErrorSet.add(new ConstraintViolationImpl(violation.getMessageTemplate(),
      // violation.getMessage(), violation.getRootBeanClass(), violation.getRootBean(), violation
      // .getLeafBean(), null, violation.getPropertyPath(), violation
      // .getConstraintDescriptor(), null));
      this.validationErrorSet.add(new SerializeableConstraintValidationImpl(violation));
    }
  }

  /**
   * set validation error set.
   *
   * @param pvalidationErrorSet the validationErrorSet to set
   */
  @SuppressWarnings({"rawtypes", "unchecked"})
  public final void setValidationErrorSet(
      final ArrayList<ConstraintViolation<?>> pvalidationErrorSet) {
    this.validationErrorSet =
        new ArrayList<SerializeableConstraintValidationImpl<?>>(pvalidationErrorSet.size());
    for (final ConstraintViolation<?> violation : pvalidationErrorSet) {
      // this.validationErrorSet.add(new ConstraintViolationImpl(violation.getMessageTemplate(),
      // violation.getMessage(), violation.getRootBeanClass(), violation.getRootBean(), violation
      // .getLeafBean(), null, violation.getPropertyPath(), violation
      // .getConstraintDescriptor(), null));
      this.validationErrorSet.add(new SerializeableConstraintValidationImpl(violation));
    }
  }
}
