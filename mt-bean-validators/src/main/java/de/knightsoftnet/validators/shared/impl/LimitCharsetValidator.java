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

import de.knightsoftnet.validators.shared.LimitCharset;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * The annotated element must contain only characters which are allowed in the given charset.
 *
 * @author Manfred Tremmel
 *
 */
public class LimitCharsetValidator implements ConstraintValidator<LimitCharset, String> {

  /**
   * encoder for the given charset.
   */
  private CharsetEncoder charsetEncoder;

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final LimitCharset pconstraintAnnotation) {
    this.charsetEncoder = Charset.forName(pconstraintAnnotation.charset()).newEncoder();
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
    return this.charsetEncoder.canEncode(pvalue);
  }
}
