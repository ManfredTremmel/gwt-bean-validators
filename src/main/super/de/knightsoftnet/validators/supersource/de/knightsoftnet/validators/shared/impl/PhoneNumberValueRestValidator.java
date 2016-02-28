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

import de.knightsoftnet.validators.client.rest.api.PhoneNumberServiceAsync;
import de.knightsoftnet.validators.shared.PhoneNumberValue;
import de.knightsoftnet.validators.shared.data.PhoneNumberDataWithFormats;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.i18n.client.LocaleInfo;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestServiceProxy;

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
    implements ConstraintValidator<PhoneNumberValue, Object> {

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
   * phone number rest service.
   */
  private PhoneNumberServiceAsync service;

  private Boolean valid;

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
    final Resource resource = new Resource(GWT.getModuleBaseURL());
    this.service = GWT.create(PhoneNumberServiceAsync.class);
    ((RestServiceProxy) this.service).setResource(resource);
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

    this.valid = null;

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
      this.service.parseAndFormatPhoneNumber(LocaleInfo.getCurrentLocale().getLocaleName(),
          countryCode, phoneNumber, new MethodCallback<PhoneNumberDataWithFormats>() {

            @Override
            public void onFailure(final Method pmethod, final Throwable pexception) {
              // error, validation can't be done
              GWT.log(pexception.getMessage(), pexception);
              PhoneNumberValueRestValidator.this.valid = Boolean.TRUE;
            }

            @Override
            public void onSuccess(final Method pmethod,
                final PhoneNumberDataWithFormats presponse) {
              PhoneNumberValueRestValidator.this.valid = Boolean.FALSE;
              if (presponse.isValid()) {
                PhoneNumberValueRestValidator.this.valid = Boolean.TRUE;
                if (PhoneNumberValueRestValidator.this.allowDin5008
                    && (StringUtils.equals(phoneNumber, presponse.getDin5008National())
                        || StringUtils.equals(phoneNumber, presponse.getDin5008International()))) {
                  PhoneNumberValueRestValidator.this.valid = Boolean.TRUE;
                }
                if (PhoneNumberValueRestValidator.this.allowE123
                    && (StringUtils.equals(phoneNumber, presponse.getE123National())
                        || StringUtils.equals(phoneNumber, presponse.getE123International()))) {
                  PhoneNumberValueRestValidator.this.valid = Boolean.TRUE;
                }
                if (PhoneNumberValueRestValidator.this.allowUri
                    && StringUtils.equals(phoneNumber, presponse.getUrl())) {
                  PhoneNumberValueRestValidator.this.valid = Boolean.TRUE;
                }
                if (PhoneNumberValueRestValidator.this.allowMs
                    && StringUtils.equals(phoneNumber, presponse.getMs())) {
                  PhoneNumberValueRestValidator.this.valid = Boolean.TRUE;
                }
                if (PhoneNumberValueRestValidator.this.allowCommon
                    && (StringUtils.equals(phoneNumber, presponse.getCommonNational())
                        || StringUtils.equals(phoneNumber, presponse.getCommonInternational()))) {
                  PhoneNumberValueRestValidator.this.valid = Boolean.TRUE;
                }
              }
            }

          });

      final Scheduler.RepeatingCommand cmd = new Scheduler.RepeatingCommand() {
        @Override
        public boolean execute() {
          return PhoneNumberValueRestValidator.this.valid == null;
        }
      };
      Scheduler.get().scheduleIncremental(cmd);
      if (BooleanUtils.isNotFalse(this.valid)) {
        return true;
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
