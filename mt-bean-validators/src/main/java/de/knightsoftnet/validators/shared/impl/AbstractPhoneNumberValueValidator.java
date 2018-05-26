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

import de.knightsoftnet.validators.shared.data.PhoneNumberData;
import de.knightsoftnet.validators.shared.util.BeanPropertyReaderUtil;
import de.knightsoftnet.validators.shared.util.PhoneNumberUtil;

import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check a string if it's a valid PhoneNumber.
 *
 * @author Manfred Tremmel
 *
 * @param <VT> the value type
 */
public abstract class AbstractPhoneNumberValueValidator<V extends Annotation>
    implements ConstraintValidator<V, Object> {

  /**
   * error message key.
   */
  protected String message;

  /**
   * field name of the country code field.
   */
  protected String fieldCountryCode;

  /**
   * field name of the phone number field.
   */
  protected String fieldPhoneNumber;

  /**
   * are lower case country codes allowed (true/false).
   */
  protected boolean allowLowerCaseCountryCode;

  /**
   * allow din 5008 format (true/false).
   */
  protected boolean allowDin5008;

  /**
   * allow E123 format (true/false).
   */
  protected boolean allowE123;

  /**
   * allow uri format (true/false).
   */
  protected boolean allowUri;

  /**
   * allow microsoft format (true/false).
   */
  protected boolean allowMs;

  /**
   * allow not standardized but common format (true/false).
   */
  protected boolean allowCommon;

  /**
   * phone number utils to test phone numbers.
   */
  protected PhoneNumberUtil phoneNumberUtil;

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public abstract void initialize(final V pconstraintAnnotation);

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
      String countryCode =
          BeanPropertyReaderUtil.getNullSaveStringProperty(pvalue, this.fieldCountryCode);
      final String phoneNumber =
          BeanPropertyReaderUtil.getNullSaveStringProperty(pvalue, this.fieldPhoneNumber);
      if (StringUtils.isEmpty(phoneNumber)) {
        return true;
      }

      if (this.allowLowerCaseCountryCode) {
        countryCode = StringUtils.upperCase(countryCode);
      }
      this.phoneNumberUtil.setCountryCode(countryCode);
      final PhoneNumberData parsedNumber = this.phoneNumberUtil.parsePhoneNumber(phoneNumber);

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
    pcontext.buildConstraintViolationWithTemplate(this.message)
        .addPropertyNode(this.fieldPhoneNumber).addConstraintViolation();
  }
}
