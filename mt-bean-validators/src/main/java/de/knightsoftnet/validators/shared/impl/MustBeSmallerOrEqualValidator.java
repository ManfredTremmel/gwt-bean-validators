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

package de.knightsoftnet.validators.shared.impl;

import de.knightsoftnet.validators.shared.MustBeSmallerOrEqual;
import de.knightsoftnet.validators.shared.util.BeanPropertyReaderUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check if entry of field1 is smaller then field2.
 *
 * @author Manfred Tremmel
 *
 */
public class MustBeSmallerOrEqualValidator
    implements ConstraintValidator<MustBeSmallerOrEqual, Object> {

  /**
   * error message key.
   */
  private String message;
  /**
   * field1 name to compare.
   */
  private String field1Name;
  /**
   * field2 name to compare.
   */
  private String field2Name;

  /**
   * add error to field1.
   */
  private boolean addErrorToField1;

  /**
   * add error to field2.
   */
  private boolean addErrorToField2;

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final MustBeSmallerOrEqual pconstraintAnnotation) {
    message = pconstraintAnnotation.message();
    field1Name = pconstraintAnnotation.field1();
    field2Name = pconstraintAnnotation.field2();
    addErrorToField1 = pconstraintAnnotation.addErrorToField1();
    addErrorToField2 = pconstraintAnnotation.addErrorToField2();
  }

  /**
   * {@inheritDoc} check if given object is valid.
   *
   * @see javax.validation.ConstraintValidator#isValid(Object,
   *      javax.validation.ConstraintValidatorContext)
   */
  @Override
  public final boolean isValid(final Object pvalue, final ConstraintValidatorContext pcontext) {
    if (pvalue == null) {
      return true;
    }
    try {
      final Object field1Value = BeanPropertyReaderUtil.getNullSaveProperty(pvalue, field1Name);
      final Object field2Value = BeanPropertyReaderUtil.getNullSaveProperty(pvalue, field2Name);
      if (!smaller(field1Value, field2Value)) {
        switchContext(pcontext);
        return false;
      }
      return true;
    } catch (final Exception ignore) {
      switchContext(pcontext);
      return false;
    }
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  private boolean smaller(final Object pfield1Value, final Object pfield2Value) {
    if (pfield1Value == null) {
      return true;
    }
    if (pfield2Value == null) {
      return false;
    }
    return pfield1Value instanceof Comparable
        && ((Comparable) pfield1Value).compareTo(pfield2Value) <= 0;
  }

  private void switchContext(final ConstraintValidatorContext pcontext) {
    if (addErrorToField1 || addErrorToField2) {
      pcontext.disableDefaultConstraintViolation();
      if (addErrorToField1) {
        pcontext.buildConstraintViolationWithTemplate(message).addPropertyNode(field1Name)
            .addConstraintViolation();
      }
      if (addErrorToField2) {
        pcontext.buildConstraintViolationWithTemplate(message).addPropertyNode(field2Name)
            .addConstraintViolation();
      }
    }
  }
}
