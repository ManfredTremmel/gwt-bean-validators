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

import de.knightsoftnet.gwtp.spring.client.rest.helper.CachedSyncHttpGetCall;
import de.knightsoftnet.gwtp.spring.shared.Parameters;
import de.knightsoftnet.gwtp.spring.shared.ResourcePaths.PhoneNumber;
import de.knightsoftnet.validators.shared.PhoneNumberValueRest;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.URL;

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
public class PhoneNumberValueRestValidator
    implements ConstraintValidator<PhoneNumberValueRest, Object> {

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
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final PhoneNumberValueRest pconstraintAnnotation) {
    message = pconstraintAnnotation.message();
    fieldPhoneNumber = pconstraintAnnotation.fieldPhoneNumber();
    fieldCountryCode = pconstraintAnnotation.fieldCountryCode();
    allowDin5008 = pconstraintAnnotation.allowDin5008();
    allowE123 = pconstraintAnnotation.allowE123();
    allowUri = pconstraintAnnotation.allowUri();
    allowMs = pconstraintAnnotation.allowMs();
    allowCommon = pconstraintAnnotation.allowCommon();
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
      String countryCode = BeanUtils.getProperty(pvalue, fieldCountryCode);
      final String phoneNumber = BeanUtils.getProperty(pvalue, fieldPhoneNumber);
      if (StringUtils.isEmpty(phoneNumber)) {
        return true;
      }

      if (allowLowerCaseCountryCode) {
        countryCode = StringUtils.upperCase(countryCode);
      }
      final String url = StringUtils
          .removeEnd(StringUtils.removeEnd(StringUtils.removeEnd(GWT.getModuleBaseURL(), "/"),
              GWT.getModuleName()), "/")
          + PhoneNumber.ROOT + PhoneNumber.VALIDATE //
          + "?" + Parameters.COUNTRY + "=" + countryCode //
          + "&" + Parameters.PHONE_NUMBER + "=" + urlEncode(phoneNumber) //
          + "&" + Parameters.DIN_5008 + "=" + PhoneNumberValueRestValidator.this.allowDin5008 //
          + "&" + Parameters.E123 + "=" + PhoneNumberValueRestValidator.this.allowE123 //
          + "&" + Parameters.URI + "=" + PhoneNumberValueRestValidator.this.allowUri //
          + "&" + Parameters.MS + "=" + PhoneNumberValueRestValidator.this.allowMs //
          + "&" + Parameters.COMMON + "=" + PhoneNumberValueRestValidator.this.allowCommon;
      final String restResult = CachedSyncHttpGetCall.syncRestCall(url);
      if (StringUtils.equalsIgnoreCase("TRUE", restResult)) {
        return true;
      }
      switchContext(pcontext);
      return false;
    } catch (final Exception ignore) {
      switchContext(pcontext);
      return false;
    }
  }

  private String urlEncode(final String pphoneNumber) {
    if (StringUtils.startsWith(pphoneNumber, "+")) {
      return "%2B" + URL.encode(StringUtils.substring(pphoneNumber, 1));
    } else {
      return URL.encode(pphoneNumber);
    }
  }

  @SuppressWarnings("deprecation")
  private void switchContext(final ConstraintValidatorContext pcontext) {
    pcontext.disableDefaultConstraintViolation();
    pcontext.buildConstraintViolationWithTemplate(message).addNode(fieldPhoneNumber)
        .addConstraintViolation();
  }
}
