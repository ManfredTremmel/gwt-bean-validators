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

import de.knightsoftnet.validators.server.data.CreateBicMapConstantsClass;
import de.knightsoftnet.validators.shared.BicValue;
import de.knightsoftnet.validators.shared.data.BicMapSharedConstants;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check a string if it's a valid BIC.
 *
 * @author Manfred Tremmel
 *
 */
public class BicValueValidator implements ConstraintValidator<BicValue, Object> {

  /**
   * map of the bic values.
   */
  private static final BicMapSharedConstants BIC_MAP = CreateBicMapConstantsClass.create();

  /**
   * should whitespaces be ignored (true/false).
   */
  private boolean ignoreWhitspaces;

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final BicValue pconstraintAnnotation) {
    this.ignoreWhitspaces = pconstraintAnnotation.ignoreWhitspaces();
  }

  /**
   * {@inheritDoc} check if given string is a valid BIC.
   *
   * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
   *      javax.validation.ConstraintValidatorContext)
   */
  @Override
  public final boolean isValid(final Object pvalue, final ConstraintValidatorContext pcontext) {
    final String valueAsString;
    if (this.ignoreWhitspaces) {
      valueAsString =
          Objects.toString(pvalue, StringUtils.EMPTY).replaceAll("\\s+", StringUtils.EMPTY);
    } else {
      valueAsString = Objects.toString(pvalue, null);
    }
    if (StringUtils.isEmpty(valueAsString)) {
      // empty field is ok
      return true;
    }
    if (valueAsString.length() != BicValidator.BIC_LENGTH_MIN
        && valueAsString.length() != BicValidator.BIC_LENGTH_MAX) {
      // to short or to long, but it's handled by size validator!
      return true;
    }
    if (!valueAsString.matches(BicValidator.BIC_REGEX)) {
      // format is wrong!
      return false;
    }
    // final BicMapConstants BIC_MAP = CreateClass.create(BicMapConstants.class);
    return BIC_MAP.bics().containsKey(valueAsString) || BIC_MAP.bics()
        .containsKey(StringUtils.substring(valueAsString, 0, BicValidator.BIC_LENGTH_MIN));
  }
}
