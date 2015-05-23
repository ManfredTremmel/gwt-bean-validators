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

import de.knightsoftnet.validators.shared.Password;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check passwords if they fulfill some complexity rules.
 * <ul>
 * <li>upper-/lowercase</li>
 * <li>digits</li>
 * <li>special character</li>
 * </ul>
 * size limits should be added by separate size annotation.
 *
 * @author Manfred Tremmel
 *
 */
public class PasswordValidator implements ConstraintValidator<Password, Object> {

  /**
   * patterns to check.
   */
  private static final String[] PATTERNS = new String[] {"[a-z]", "[A-Z]", "[0-9]", "[^\\s]"};

  /**
   * minimum number rules that have to be fulfilled.
   */
  private int minRules;

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final Password pconstraintAnnotation) {
    this.minRules = pconstraintAnnotation.minRules();
  }

  /**
   * {@inheritDoc} check if given object is valid.
   *
   * @see javax.validation.ConstraintValidator#isValid(Object,
   *      javax.validation.ConstraintValidatorContext)
   */
  @Override
  public final boolean isValid(final Object pvalue, final ConstraintValidatorContext pcontext) {
    final String valueAsString = Objects.toString(pvalue, null);
    if (StringUtils.isEmpty(valueAsString)) {
      return true;
    }
    return this.minRules < this.countCriteriaMatches(valueAsString);
  }

  /**
   * count criteria matches.
   *
   * @param ppassword the password to check
   * @return number of fulfilled criteria matches with the given pw
   */
  private int countCriteriaMatches(final String ppassword) {
    String password = ppassword;
    int fulFilledCriterias = 0;
    for (final String pattern : PasswordValidator.PATTERNS) {
      if (password.matches(".*" + pattern + ".*")) {
        fulFilledCriterias += 1;
        password = password.replaceAll(pattern, StringUtils.EMPTY);
      }
    }
    return fulFilledCriterias;
  }
}
