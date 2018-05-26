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

import de.knightsoftnet.validators.shared.Email;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check a string if it's a valid eMail.
 *
 * @author Manfred Tremmel
 *
 */
public class EmailValidator implements ConstraintValidator<Email, String> {
  /**
   * maximum length of a eMail.
   */
  public static final int LENGTH_MAIL = 255;

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final Email pconstraintAnnotation) {
    // nothing to do
  }

  /**
   * {@inheritDoc} check if given string is a valid mail.
   *
   * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
   *      javax.validation.ConstraintValidatorContext)
   */
  @Override
  public final boolean isValid(final String pvalue, final ConstraintValidatorContext pcontext) {
    if (StringUtils.isEmpty(pvalue)) {
      return true;
    }
    if (pvalue.length() > LENGTH_MAIL) {
      // Email is to long, but that's handled by size annotation
      return true;
    }
    if (!StringUtils.equals(pvalue, StringUtils.trim(pvalue))) {
      // mail contains leading or trailing space(s), that's not correct
      return false;
    }
    return org.apache.commons.validator.routines.EmailValidator.getInstance().isValid(pvalue);
  }
}
