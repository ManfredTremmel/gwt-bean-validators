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

import de.knightsoftnet.validators.server.data.CreatePostalCodesMapConstantsClass;
import de.knightsoftnet.validators.shared.PostalCode;
import de.knightsoftnet.validators.shared.data.PostalCodesMapSharedConstants;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check if a postal code field is valid for the selected country.
 *
 * @author Manfred Tremmel
 *
 */
public class PostalCodeValidator implements ConstraintValidator<PostalCode, Object> {

  /**
   * map of the postal code values.
   */
  private static final PostalCodesMapSharedConstants POSTAL_CODE_MAP =
      CreatePostalCodesMapConstantsClass.create();

  /**
   * error message key.
   */
  private String message;

  /**
   * field name of the country code field.
   */
  private String fieldCountryCode;

  /**
   * are lower case country codes allowed (true/false).
   */
  private boolean allowLowerCaseCountryCode;

  /**
   * field name of the postal code (zip) field.
   */
  private String fieldPostalCode;

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final PostalCode pconstraintAnnotation) {
    this.message = pconstraintAnnotation.message();
    this.fieldCountryCode = pconstraintAnnotation.fieldCountryCode();
    this.allowLowerCaseCountryCode = pconstraintAnnotation.allowLowerCaseCountryCode();
    this.fieldPostalCode = pconstraintAnnotation.fieldPostalCode();
  }

  /**
   * {@inheritDoc} check if given object is valid.
   *
   * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
   *      javax.validation.ConstraintValidatorContext)
   */
  @Override
  public final boolean isValid(final Object pvalue, final ConstraintValidatorContext pcontext) {
    if (pvalue == null) {
      return true;
    }
    try {
      String countryCode = BeanUtils.getProperty(pvalue, this.fieldCountryCode);
      final String postalCode = BeanUtils.getProperty(pvalue, this.fieldPostalCode);
      if (StringUtils.isEmpty(postalCode)) {
        return true;
      }

      if (this.allowLowerCaseCountryCode) {
        countryCode = StringUtils.upperCase(countryCode);
      }

      final String regExCheck = POSTAL_CODE_MAP.postalCodes().get(countryCode);
      if (regExCheck == null) {
        return true;
      }
      if (postalCode.matches(regExCheck)) {
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
    pcontext.buildConstraintViolationWithTemplate(this.message).addNode(this.fieldPostalCode)
        .addConstraintViolation();
  }
}
