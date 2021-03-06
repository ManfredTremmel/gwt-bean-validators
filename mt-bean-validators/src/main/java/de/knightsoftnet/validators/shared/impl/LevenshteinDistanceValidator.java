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
import de.knightsoftnet.validators.shared.util.BeanPropertyReaderUtil;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check if the Levenshtein Distance of two field entries reach a minimum value.
 *
 * @author Valentin Pricop
 */
public class LevenshteinDistanceValidator
    implements ConstraintValidator<LevenshteinDistance, Object> {

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
    message = pconstraintAnnotation.message();
    field1Name = pconstraintAnnotation.field1();
    field2Name = pconstraintAnnotation.field2();
    minDistance = pconstraintAnnotation.minDistance();
    addErrorToField1 = pconstraintAnnotation.addErrorToField1();
    addErrorToField2 = pconstraintAnnotation.addErrorToField2();
  }

  @SuppressWarnings("deprecation")
  @Override
  public boolean isValid(final Object pvalue, final ConstraintValidatorContext pcontext) {
    if (pvalue == null) {
      return true;
    }
    try {
      final String field1Value =
          BeanPropertyReaderUtil.getNullSaveStringProperty(pvalue, field1Name);
      final String field2Value =
          BeanPropertyReaderUtil.getNullSaveStringProperty(pvalue, field2Name);
      final boolean oneFieldIsEmpty =
          StringUtils.isEmpty(field1Value) || StringUtils.isEmpty(field2Value);
      if (oneFieldIsEmpty
          || StringUtils.getLevenshteinDistance(field1Value, field2Value) >= minDistance) {
        return true;
      }
      switchContext(pcontext);
      return false;
    } catch (final Exception pexception) {
      switchContext(pcontext);
      return false;
    }
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
