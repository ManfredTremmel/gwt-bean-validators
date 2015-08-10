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

import de.knightsoftnet.validators.shared.LevenshteinDistance;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * Check if the Levenshtein Distance of two field entries reach a minimum value.
 *
 * @author Valentin Pricop
 */
public class LevenshteinDistanceValidator implements
    ConstraintValidator<LevenshteinDistance, Object> {

  /**
   * error message key.
   */
  private String message;
  /**
   * field name to check.
   */
  private String field1Name;
  /**
   * field name to compare.
   */
  private String field2Name;

  /**
   * minimum levenshtein distance.
   */
  private int minDistance;

  /**
   * add error to field1.
   */
  private boolean addErrorToField1;

  /**
   * add error to field2.
   */
  private boolean addErrorToField2;

  @Override
  public void initialize(final LevenshteinDistance pconstraintAnnotation) {
    this.message = pconstraintAnnotation.message();
    this.field1Name = pconstraintAnnotation.field1();
    this.field2Name = pconstraintAnnotation.field2();
    this.minDistance = pconstraintAnnotation.minDistance();
    this.addErrorToField1 = pconstraintAnnotation.addErrorToField1();
    this.addErrorToField2 = pconstraintAnnotation.addErrorToField2();
  }

  @Override
  public boolean isValid(final Object pvalue, final ConstraintValidatorContext pcontext) {
    if (pvalue == null) {
      return true;
    }
    try {
      final String field1Value = BeanUtils.getProperty(pvalue, this.field1Name);
      final String field2Value = BeanUtils.getProperty(pvalue, this.field2Name);
      final boolean oneFieldIsEmpty =
          StringUtils.isEmpty(field1Value) || StringUtils.isEmpty(field2Value);
      if (oneFieldIsEmpty
          || StringUtils.getLevenshteinDistance(field1Value, field2Value) >= this.minDistance) {
        return true;
      }
      this.switchContext(pcontext);
      return false;
    } catch (final Exception e) {
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
