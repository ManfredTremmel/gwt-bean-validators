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

import de.knightsoftnet.validators.shared.NotEmptyIfOtherIsNotEmpty;
import de.knightsoftnet.validators.shared.util.BeanPropertyReaderUtil;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check if a field is filled if another field is filled.
 *
 * @author Manfred Tremmel
 *
 */
public class NotEmptyIfOtherIsNotEmptyValidator
    implements ConstraintValidator<NotEmptyIfOtherIsNotEmpty, Object> {

  /**
   * error message key.
   */
  private String message;
  /**
   * field name to check.
   */
  private String fieldCheckName;
  /**
   * field name to compare.
   */
  private String fieldCompareName;

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final NotEmptyIfOtherIsNotEmpty pconstraintAnnotation) {
    this.message = pconstraintAnnotation.message();
    this.fieldCheckName = pconstraintAnnotation.field();
    this.fieldCompareName = pconstraintAnnotation.fieldCompare();
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
      final String fieldCheckValue =
          BeanPropertyReaderUtil.getNullSaveStringProperty(pvalue, this.fieldCheckName);
      final String fieldCompareValue =
          BeanPropertyReaderUtil.getNullSaveStringProperty(pvalue, this.fieldCompareName);
      if (StringUtils.isEmpty(fieldCheckValue) && StringUtils.isNotEmpty(fieldCompareValue)) {
        this.switchContext(pcontext);
        return false;
      }
      return true;
    } catch (final Exception ignore) {
      this.switchContext(pcontext);
      return false;
    }
  }

  private void switchContext(final ConstraintValidatorContext pcontext) {
    pcontext.disableDefaultConstraintViolation();
    pcontext.buildConstraintViolationWithTemplate(this.message).addPropertyNode(this.fieldCheckName)
        .addConstraintViolation();
  }
}
