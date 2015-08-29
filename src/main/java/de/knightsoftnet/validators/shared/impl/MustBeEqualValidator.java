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

import de.knightsoftnet.validators.shared.MustBeEqual;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check if two field entries are equal.
 *
 * @author Manfred Tremmel
 *
 */
public class MustBeEqualValidator implements ConstraintValidator<MustBeEqual, Object> {

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
  public final void initialize(final MustBeEqual pconstraintAnnotation) {
    this.message = pconstraintAnnotation.message();
    this.field1Name = pconstraintAnnotation.field1();
    this.field2Name = pconstraintAnnotation.field2();
    this.addErrorToField1 = pconstraintAnnotation.addErrorToField1();
    this.addErrorToField2 = pconstraintAnnotation.addErrorToField2();
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
      final String field1Value = BeanUtils.getProperty(pvalue, this.field1Name);
      final String field2Value = BeanUtils.getProperty(pvalue, this.field2Name);
      if (!StringUtils.equals(field1Value, field2Value)) {
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
    if (this.addErrorToField1 || this.addErrorToField2) {
      pcontext.disableDefaultConstraintViolation();
      if (this.addErrorToField1) {
        pcontext.buildConstraintViolationWithTemplate(this.message).addNode(this.field1Name)
            .addConstraintViolation();
      }
      if (this.addErrorToField2) {
        pcontext.buildConstraintViolationWithTemplate(this.message).addNode(this.field2Name)
            .addConstraintViolation();
      }
    }
  }
}
