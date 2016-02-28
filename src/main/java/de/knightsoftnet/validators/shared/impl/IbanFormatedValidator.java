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

import de.knightsoftnet.validators.server.data.CreateIbanLengthMapConstantsClass;
import de.knightsoftnet.validators.shared.IbanFormated;
import de.knightsoftnet.validators.shared.data.IbanLengthMapSharedConstants;
import de.knightsoftnet.validators.shared.util.IbanUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.checkdigit.IBANCheckDigit;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check a string if it's a valid IBAN formated.
 *
 * @author Manfred Tremmel
 *
 */
public class IbanFormatedValidator implements ConstraintValidator<IbanFormated, Object> {
  /**
   * definition of IBAN length minimum.
   */
  public static final int IBAN_LENGTH_MIN = 19;
  /**
   * definition of IBAN length maximum.
   */
  public static final int IBAN_LENGTH_MAX = 42;

  /**
   * map of swift countries and the length of the ibans.
   */
  private static final IbanLengthMapSharedConstants IBAN_LENGTH_MAP =
      CreateIbanLengthMapConstantsClass.create();

  /**
   * apache commons class to check/calculate IBAN check sums.
   */
  private static final IBANCheckDigit CHECK_IBAN = new IBANCheckDigit();

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final IbanFormated pconstraintAnnotation) {
    // nothing to do
  }

  /**
   * {@inheritDoc} check if given string is a valid IBAN.
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
    if (valueAsString.length() < IBAN_LENGTH_MIN || valueAsString.length() > IBAN_LENGTH_MAX) {
      // to short or to long, but it's handled by size validator!
      return true;
    }
    final String countryCode = valueAsString.substring(0, 2);
    final String validIbanLength = IBAN_LENGTH_MAP.ibanLengths().get(countryCode);
    if (validIbanLength == null || valueAsString.replaceAll("\\s", StringUtils.EMPTY)
        .length() != Integer.parseInt(validIbanLength)) {
      // unknown country or wrong length for the country!
      return false;
    }
    if (!valueAsString.matches("^[A-Z]{2}[0-9A-Z]{2} ([0-9A-Z]{4}[ ])+[0-9A-Z]{1,4}$")) {
      // wrong format
      return false;
    }

    return CHECK_IBAN.isValid(IbanUtil.ibanCompress(valueAsString));
  }

}
