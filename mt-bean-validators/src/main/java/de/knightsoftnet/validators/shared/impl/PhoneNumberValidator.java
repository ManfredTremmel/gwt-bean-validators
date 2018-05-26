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

import de.knightsoftnet.validators.shared.PhoneNumber;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check a string if it's a valid PhoneNumber.
 *
 * @author Manfred Tremmel
 *
 */
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, Object> {

  /**
   * phone number format din 5008.
   */
  private static final String PHONE_NUMBER_DIN5008 =
      "^(\\+[1-9]\\d{0,3}\\s{0,2}|0)\\d{1,7}\\s{0,2}\\d{2,12}(\\s{0,2}\\-\\s{0,2}\\d{1,9}$|$)";
  /**
   * phone number format E123.
   */
  private static final String PHONE_NUMBER_E123 =
      "^(\\+[1-9]\\d{0,3}\\s{0,2}|\\(0\\d{1,7}\\))\\s{0,2}\\d{2,12}(\\s{0,2}\\d{1,9}$|$)";
  /**
   * phone number format uri.
   */
  private static final String PHONE_NUMBER_URI =
      "^\\+[1-9]\\d{0,3}\\-\\d{1,7}\\-\\d{2,12}(\\-\\d{1,9}$|$)";
  /**
   * phone number format microsoft.
   */
  private static final String PHONE_NUMBER_MS =
      "^\\+[1-9]\\d{0,3}\\s{0,2}\\(\\d{1,7}\\)\\s{0,2}\\d{2,12}(\\s{0,2}\\-\\s{0,2}\\d{1,9}$|$)";
  /**
   * phone number not standardized, but common writing.
   */
  private static final String PHONE_NUMBER_COMMON =
      "^(\\+[1-9]\\d{0,3}\\s{0,2}(\\(0\\)){0,1}|0)(\\s{0,2}\\d{1,2}){1,4}(\\s{0,2}\\/){0,1}"
          + "(\\s{0,2}\\d{1,2}){2,6}(\\s{0,2}\\-(\\s{0,2}\\d{1,2})*$|$)";

  /**
   * allow din 5008 format (true/false).
   */
  private boolean allowDin5008;

  /**
   * allow E123 format (true/false).
   */
  private boolean allowE123;

  /**
   * allow uri format (true/false).
   */
  private boolean allowUri;

  /**
   * allow microsoft format (true/false).
   */
  private boolean allowMs;

  /**
   * allow not standardized but common format (true/false).
   */
  private boolean allowCommon;

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final PhoneNumber pconstraintAnnotation) {
    this.allowDin5008 = pconstraintAnnotation.allowDin5008();
    this.allowE123 = pconstraintAnnotation.allowE123();
    this.allowUri = pconstraintAnnotation.allowUri();
    this.allowMs = pconstraintAnnotation.allowMs();
    this.allowCommon = pconstraintAnnotation.allowCommon();
  }

  /**
   * {@inheritDoc} check if given string is a valid gln.
   *
   * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
   *      javax.validation.ConstraintValidatorContext)
   */
  @Override
  public final boolean isValid(final Object pvalue, final ConstraintValidatorContext pcontext) {
    final String valueAsString = Objects.toString(pvalue, null);

    if (StringUtils.isEmpty(valueAsString)) {
      // empty field is ok
      return true;
    }
    return this.allowDin5008 && valueAsString.matches(PHONE_NUMBER_DIN5008) //
        || this.allowE123 && valueAsString.matches(PHONE_NUMBER_E123) //
        || this.allowUri && valueAsString.matches(PHONE_NUMBER_URI) //
        || this.allowMs && valueAsString.matches(PHONE_NUMBER_MS) //
        || this.allowCommon && valueAsString.matches(PHONE_NUMBER_COMMON);
  }
}
