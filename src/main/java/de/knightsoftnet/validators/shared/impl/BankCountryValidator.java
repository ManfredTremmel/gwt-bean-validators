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
                "{org.hibernate.validator.constraints.NotEmpty.message}")
            .addNode(this.fieldIban).addConstraintViolation();
        return false;
      } else if (StringUtils.isEmpty(valueBic)) {
        pcontext.disableDefaultConstraintViolation();
        pcontext
            .buildConstraintViolationWithTemplate(
                "{org.hibernate.validator.constraints.NotEmpty.message}")
            .addNode(this.fieldBic).addConstraintViolation();
        return false;
      } else if (StringUtils.length(valueIban) >= IbanValidator.IBAN_LENGTH_MIN
          && StringUtils.length(valueBic) >= BicValidator.BIC_LENGTH_MIN) {
        String countryIban = valueIban.replaceAll("\\s+", StringUtils.EMPTY).substring(0, 2);
        String countryBic = valueBic.replaceAll("\\s+", StringUtils.EMPTY).substring(4, 6);
        if (StringUtils.length(valueCountry) != 2) {
          // missing country selection, us country of iban
          valueCountry = countryIban;
        }

        if (this.allowLowerCaseCountryCode) {
          valueCountry = StringUtils.upperCase(valueCountry);
          countryIban = StringUtils.upperCase(countryIban);
          countryBic = StringUtils.upperCase(countryIban);
        }

        boolean ibanCodeMatches = false;
        boolean bicCodeMatches = false;
        switch (valueCountry) {
          case "GF": // French Guyana
          case "GP": // Guadeloupe
          case "MQ": // Martinique
          case "RE": // Reunion
          case "PF": // French Polynesia
          case "TF": // French Southern Territories
          case "YT": // Mayotte
          case "NC": // New Caledonia
          case "BL": // Saint Barthelemy
          case "MF": // Saint Martin
          case "PM": // Saint Pierre et Miquelon
          case "WF": // Wallis and Futuna Islands
            // special solution for French oversea teritorials with french registry
            ibanCodeMatches = "FR".equals(countryIban);
            bicCodeMatches = "FR".equals(countryBic);
            break;
          case "JE": // Jersey
          case "GG": // Guernsey
            // they can use GB or FR registry, but iban and bic code must match
            ibanCodeMatches = ("GB".equals(countryIban) || "FR".equals(countryIban))
                && countryBic.equals(countryIban);
            bicCodeMatches = "GB".equals(countryBic) || "FR".equals(countryBic);
            break;
          default:
            ibanCodeMatches = valueCountry.equals(countryIban);
            bicCodeMatches = valueCountry.equals(countryBic);
            break;
        }
        if (ibanCodeMatches && bicCodeMatches) {
          return true;
        }
        pcontext.disableDefaultConstraintViolation();
        if (!ibanCodeMatches) {
          pcontext.buildConstraintViolationWithTemplate(this.message).addNode(this.fieldIban)
              .addConstraintViolation();
        }
        if (!bicCodeMatches) {
          pcontext.buildConstraintViolationWithTemplate(this.message).addNode(this.fieldBic)
              .addConstraintViolation();
        }
        return false;
      } else {
        // wrong format, should be handled by other validators
        return true;
      }
    } catch (final Exception ignore) {
      return false;
    }
  }
}
