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

import de.knightsoftnet.validators.shared.AgeLimitCheck;
import de.knightsoftnet.validators.shared.interfaces.HasGetTime;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check if a given date is minYears years ago.
 *
 * @author Manfred Tremmel
 *
 */
public class AgeLimitCheckValidator implements ConstraintValidator<AgeLimitCheck, Object> {

  /**
   * minimum years.
   */
  private int minYears;

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final AgeLimitCheck pconstraintAnnotation) {
    this.minYears = pconstraintAnnotation.minYears();
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
    final Date value;
    if (pvalue instanceof Date) {
      value = (Date) pvalue;
    } else if (pvalue instanceof Calendar) {
      value = ((Calendar) pvalue).getTime();
    } else if (pvalue instanceof HasGetTime) {
      value = ((HasGetTime) pvalue).getTime();
    } else {
      throw new IllegalArgumentException(
          "Object for validation with AgeLimitCheckValidator must be of type "
              + "Date, Calendar or HasGetTime.");
    }
    final Date dateLimit = DateUtils.truncate(DateUtils.addYears(new Date(), 0 - this.minYears),
        Calendar.DAY_OF_MONTH);
    final Date birthday = DateUtils.truncate(value, Calendar.DAY_OF_MONTH);
    return !dateLimit.before(birthday);
  }
}
