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
import de.knightsoftnet.validators.shared.Bic;
import de.knightsoftnet.validators.shared.data.IbanLengthMapSharedConstants;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check a string if it's a valid BIC.
 *
 * @author Manfred Tremmel
 *
 */
public class BicValidator implements ConstraintValidator<Bic, Object> {
  /**
   * definition of BIC length minimum.
   */
  public static final int BIC_LENGTH_MIN = 8;
  /**
   * definition of BIC length maximum.
   */
  public static final int BIC_LENGTH_MAX = 11;

  /**
   * map of swift countries and the length of the ibans.
   */
  private static final IbanLengthMapSharedConstants IBAN_LENGTH_MAP =
      CreateIbanLengthMapConstantsClass.create();

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
  public final void initialize(final Bic pconstraintAnnotation) {
    this.ignoreWhitspaces = pconstraintAnnotation.ignoreWhitspaces();
  }

  /**
   * {@inheritDoc} check if given string is a valid BIC.
   *
   * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
   *      javax.validation.ConstraintValidatorContext)
   */
  @Override
  public final boolean isValid(final Object pvalue, final ConstraintValidatorContext pcontext) {
    final String valueAsString;
    if (this.ignoreWhitspaces) {
      valueAsString =
          Objects.toString(pvalue, StringUtils.EMPTY).replaceAll("\\s+", StringUtils.EMPTY);
    } else {
      valueAsString = Objects.toString(pvalue, null);
    }
    if (StringUtils.isEmpty(valueAsString)) {
      // empty field is ok
      return true;
    }
    if (valueAsString.length() != BIC_LENGTH_MIN && valueAsString.length() != BIC_LENGTH_MAX) {
      // to short or to long, but it's handled by size validator!
      return true;
    }
    if (!valueAsString
        .matches("^[A-Z]{4}[A-Z]{2}([01][A-Z]|[A-Z2-9][A-Z0-9])(XXX|[A-WYZ0-9][A-Z0-9]{2}|)$")) {
      // format is wrong!
      return false;
    }
    final String countryCode = valueAsString.substring(4, 6);
    final String validBicLength = IBAN_LENGTH_MAP.ibanLengths().get(countryCode);

    return validBicLength != null;
  }
}
