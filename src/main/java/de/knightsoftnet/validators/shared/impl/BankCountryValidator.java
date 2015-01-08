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

import de.knightsoftnet.validators.shared.BankCountry;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check if a country field and the country in iban and bic match, implementation.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
public class BankCountryValidator implements ConstraintValidator<BankCountry, Object> {

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
   * field name of the iban field.
   */
  private String fieldIban;

  /**
   * field name of the bic field.
   */
  private String fieldBic;

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final BankCountry pconstraintAnnotation) {
    this.fieldCountryCode = pconstraintAnnotation.fieldCountryCode();
    this.allowLowerCaseCountryCode = pconstraintAnnotation.allowLowerCaseCountryCode();
    this.fieldIban = pconstraintAnnotation.fieldIban();
    this.fieldBic = pconstraintAnnotation.fieldBic();
    this.message = pconstraintAnnotation.message();
  }

  /**
   * {@inheritDoc} check if given object.
   *
   * @see javax.validation.ConstraintValidator#isValid(Object,
   *      javax.validation.ConstraintValidatorContext)
   */
  @Override
  public final boolean isValid(final Object pvalue, final ConstraintValidatorContext pcontext) {
    try {
      String valueCountry = BeanUtils.getProperty(pvalue, this.fieldCountryCode);
      final String valueIban = BeanUtils.getProperty(pvalue, this.fieldIban);
      final String valueBic = BeanUtils.getProperty(pvalue, this.fieldBic);

      if (StringUtils.isEmpty(valueIban) && StringUtils.isEmpty(valueBic)) {
        return true;
      } else if (StringUtils.isEmpty(valueIban)) {
        pcontext.disableDefaultConstraintViolation();
        pcontext
            .buildConstraintViolationWithTemplate(
                "org.hibernate.validator.constraints.NotEmpty.message").addNode(this.fieldIban)
            .addConstraintViolation();
        return false;
      } else if (StringUtils.isEmpty(valueBic)) {
        pcontext.disableDefaultConstraintViolation();
        pcontext
            .buildConstraintViolationWithTemplate(
                "org.hibernate.validator.constraints.NotEmpty.message").addNode(this.fieldBic)
            .addConstraintViolation();
        return false;
      } else if (StringUtils.length(valueIban) >= IbanValidator.IBAN_LENGTH_MIN
          && StringUtils.length(valueBic) >= BicValidator.BIC_LENGTH_MIN) {
        String countryIban = valueIban.replaceAll("\\s+", "").substring(0, 2);
        String countryBic = valueBic.replaceAll("\\s+", "").substring(4, 6);
        if (StringUtils.length(valueCountry) != 2) {
          // missing country selection, us country of iban
          valueCountry = countryIban;
        }

        if (this.allowLowerCaseCountryCode) {
          valueCountry = StringUtils.upperCase(valueCountry);
          countryIban = StringUtils.upperCase(countryIban);
          countryBic = StringUtils.upperCase(countryIban);
        }

        if (!valueCountry.equals(countryIban) || !valueCountry.equals(countryBic)) {
          pcontext.disableDefaultConstraintViolation();
          if (!valueCountry.equals(countryIban)) {
            pcontext.buildConstraintViolationWithTemplate(this.message).addNode(this.fieldIban)
                .addConstraintViolation();
          }
          if (!valueCountry.equals(countryBic)) {
            pcontext.buildConstraintViolationWithTemplate(this.message).addNode(this.fieldBic)
                .addConstraintViolation();
          }
          return false;
        }
        return true;
      } else {
        // wrong format, should be handled by other validators
        return true;
      }
    } catch (final Exception ignore) {
      return false;
    }
  }
}
