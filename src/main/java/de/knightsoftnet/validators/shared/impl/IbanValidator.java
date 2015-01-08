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

import de.knightsoftnet.validators.shared.Iban;
import de.knightsoftnet.validators.shared.data.SwiftDefinitions;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.checkdigit.IBANCheckDigit;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check a string if it's a valid IBAN.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
public class IbanValidator implements ConstraintValidator<Iban, Object> {
  /**
   * definition of IBAN length minimum.
   */
  public static final int IBAN_LENGTH_MIN = 16;
  /**
   * definition of IBAN length maximum.
   */
  public static final int IBAN_LENGTH_MAX = 34;

  /**
   * apache commons class to check/calculate IBAN check sums.
   */
  private static final IBANCheckDigit CHECK_IBAN = new IBANCheckDigit();

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
  public final void initialize(final Iban pconstraintAnnotation) {
    this.ignoreWhitspaces = pconstraintAnnotation.ignoreWhitspaces();
  }

  /**
   * {@inheritDoc} check if given string is a valid IBAN.
   *
   * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
   *      javax.validation.ConstraintValidatorContext)
   */
  @Override
  public final boolean isValid(final Object pvalue, final ConstraintValidatorContext pcontext) {
    final String valueAsString;
    if (this.ignoreWhitspaces) {
      valueAsString = Objects.toString(pvalue, "").replaceAll("\\s+", "");
    } else {
      valueAsString = Objects.toString(pvalue, null);
    }
    if (StringUtils.isEmpty(valueAsString)) {
      // empty field is ok
      return true;
    }
    if (valueAsString.length() < IBAN_LENGTH_MIN || valueAsString.length() > IBAN_LENGTH_MAX) {
      // to short or to long, but it's handled by size validator!
      return true;
    }
    final String countryCode = valueAsString.substring(0, 2);
    final Integer validIbanLength = SwiftDefinitions.COUNTRY_IBAN_LENGTH.get(countryCode);
    if (validIbanLength == null || valueAsString.length() != validIbanLength.intValue()) {
      // unknown country or wrong length for the country!
      return false;
    }

    return CHECK_IBAN.isValid(valueAsString);
  }

}
