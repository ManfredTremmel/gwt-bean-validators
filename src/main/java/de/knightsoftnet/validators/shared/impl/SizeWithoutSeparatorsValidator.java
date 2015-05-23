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

import de.knightsoftnet.validators.shared.SizeWithoutSeparators;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check if a field's size has one of the two given sizes.
 *
 * @author Manfred Tremmel
 *
 */
public class SizeWithoutSeparatorsValidator implements
    ConstraintValidator<SizeWithoutSeparators, Object> {

  /**
   * size the element must be higher or equal to.
   */
  private int min;

  /**
   * size the element must be lower or equal to.
   */
  private int max;

  /**
   * true if whitespaces should be ignored.
   */
  private boolean ignoreWhiteSpaces;

  /**
   * true if minus should be ignored.
   */
  private boolean ignoreMinus;

  /**
   * true if slashes should be ignored.
   */
  private boolean ignoreSlashes;

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final SizeWithoutSeparators pconstraintAnnotation) {
    this.min = pconstraintAnnotation.min();
    this.max = pconstraintAnnotation.max();
    this.ignoreWhiteSpaces = pconstraintAnnotation.ignoreWhiteSpaces();
    this.ignoreMinus = pconstraintAnnotation.ignoreMinus();
    this.ignoreSlashes = pconstraintAnnotation.ignoreSlashes();
    this.validateParameters();
  }

  /**
   * {@inheritDoc} check the given object.
   *
   * @see javax.validation.ConstraintValidator#isValid(Object,
   *      javax.validation.ConstraintValidatorContext)
   */
  @Override
  public final boolean isValid(final Object pvalue, final ConstraintValidatorContext pcontext) {
    String valueAsString = Objects.toString(pvalue, StringUtils.EMPTY);
    if (StringUtils.isEmpty(valueAsString)) {
      return true;
    }
    if (this.ignoreWhiteSpaces) {
      valueAsString = valueAsString.replaceAll("\\s", StringUtils.EMPTY);
    }
    if (this.ignoreMinus) {
      valueAsString = valueAsString.replaceAll("-", StringUtils.EMPTY);
    }
    if (this.ignoreSlashes) {
      valueAsString = valueAsString.replaceAll("/", StringUtils.EMPTY);
    }
    final int length = valueAsString.length();
    return length >= this.min && length <= this.max;
  }

  /**
   * check validity of the the parameters.
   */
  private void validateParameters() {
    if (this.min < 0) {
      throw new IllegalArgumentException("The min parameter cannot be negative.");
    }
    if (this.max < 0) {
      throw new IllegalArgumentException("The max parameter cannot be negative.");
    }
    if (this.max < this.min) {
      throw new IllegalArgumentException("The length cannot be negative.");
    }
  }
}
