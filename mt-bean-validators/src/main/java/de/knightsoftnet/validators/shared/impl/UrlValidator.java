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

import de.knightsoftnet.validators.shared.Url;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check a string if it's a valid URL.
 *
 * @author Manfred Tremmel
 *
 */
public class UrlValidator implements ConstraintValidator<Url, String> {

  /**
   * maximum length of a url.
   */
  public static final int LENGTH_URL = 255;

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final Url pconstraintAnnotation) {
    // nothing to do
  }

  /**
   * {@inheritDoc} check if given string is a valid url.
   *
   * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
   *      javax.validation.ConstraintValidatorContext)
   */
  @Override
  public final boolean isValid(final String pvalue, final ConstraintValidatorContext pcontext) {
    if (StringUtils.isEmpty(pvalue)) {
      return true;
    }
    if (pvalue.length() > LENGTH_URL) {
      // url is to long, but that's handled by size annotation
      return true;
    }
    return org.apache.commons.validator.routines.UrlValidator.getInstance().isValid(pvalue);
  }
}
