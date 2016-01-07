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

import de.knightsoftnet.validators.shared.PhoneNumberValue;
import de.knightsoftnet.validators.shared.data.PhoneNumberData;
import de.knightsoftnet.validators.shared.util.PhoneNumberUtil;

import org.apache.commons.beanutils.BeanUtils;
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
public class PhoneNumberValueValidator implements ConstraintValidator<PhoneNumberValue, Object> {

  /**
   * error message key.
   */
  private String message;

  /**
   * field name of the country code field.
   */
  private String fieldCountryCode;

  /**
   * field name of the phone number field.
   */
  private String fieldPhoneNumber;

  /**
   * are lower case country codes allowed (true/false).
   */
  private boolean allowLowerCaseCountryCode;

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
   * phone number utils to test phone numbers.
   */
  private PhoneNumberUtil phoneNumberUtil;

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final PhoneNumberValue pconstraintAnnotation) {
    this.message = pconstraintAnnotation.message();
    this.fieldPhoneNumber = pconstraintAnnotation.fieldPhoneNumber();
    this.fieldCountryCode = pconstraintAnnotation.fieldCountryCode();
    this.allowDin5008 = pconstraintAnnotation.allowDin5008();
    this.allowE123 = pconstraintAnnotation.allowE123();
    this.allowUri = pconstraintAnnotation.allowUri();
    this.allowMs = pconstraintAnnotation.allowMs();
    this.allowCommon = pconstraintAnnotation.allowCommon();
    this.phoneNumberUtil = new PhoneNumberUtil();
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
    try {
      String countryCode = BeanUtils.getProperty(pvalue, this.fieldCountryCode);
      final String phoneNumber = BeanUtils.getProperty(pvalue, this.fieldPhoneNumber);
      if (StringUtils.isEmpty(phoneNumber)) {
        return true;
      }

      if (this.allowLowerCaseCountryCode) {
        countryCode = StringUtils.upperCase(countryCode);
      }
      this.phoneNumberUtil.setCountryCode(countryCode);
      final PhoneNumberData parsedNumber =
          (PhoneNumberData) this.phoneNumberUtil.parsePhoneNumber(phoneNumber);

      if (parsedNumber.isValid()) {
        if (this.allowDin5008 && (StringUtils.equals(phoneNumber,
            this.phoneNumberUtil.formatDin5008National(parsedNumber))
            || StringUtils.equals(phoneNumber,
                this.phoneNumberUtil.formatDin5008International(parsedNumber)))) {
          return true;
        }
        if (this.allowE123 && (StringUtils.equals(phoneNumber,
            this.phoneNumberUtil.formatE123National(parsedNumber))
            || StringUtils.equals(phoneNumber,
                this.phoneNumberUtil.formatE123International(parsedNumber)))) {
          return true;
        }
        if (this.allowUri
            && StringUtils.equals(phoneNumber, this.phoneNumberUtil.formatUrl(parsedNumber))) {
          return true;
        }
        if (this.allowMs
            && StringUtils.equals(phoneNumber, this.phoneNumberUtil.formatMs(parsedNumber))) {
          return true;
        }
        if (this.allowCommon && (StringUtils.equals(phoneNumber,
            this.phoneNumberUtil.formatCommonNational(parsedNumber))
            || StringUtils.equals(phoneNumber,
                this.phoneNumberUtil.formatCommonInternational(parsedNumber)))) {
          return true;
        }
      }
      this.switchContext(pcontext);
      return false;
    } catch (final Exception ignore) {
      this.switchContext(pcontext);
      return false;
    }
  }

  private void switchContext(final ConstraintValidatorContext pcontext) {
    pcontext.disableDefaultConstraintViolation();
    pcontext.buildConstraintViolationWithTemplate(this.message).addNode(this.fieldPhoneNumber)
        .addConstraintViolation();
  }
}
