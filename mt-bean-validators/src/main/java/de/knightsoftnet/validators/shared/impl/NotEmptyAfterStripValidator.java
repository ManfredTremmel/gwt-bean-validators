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

import de.knightsoftnet.validators.shared.NotEmptyAfterStrip;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * he annotated element must not be null or empty after strip.
 *
 * @author Manfred Tremmel
 *
 */
public class NotEmptyAfterStripValidator
    implements ConstraintValidator<NotEmptyAfterStrip, Object> {

  /**
   * characters which should be striped.
   */
  private String stripcharacters;

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final NotEmptyAfterStrip pconstraintAnnotation) {
    this.stripcharacters = pconstraintAnnotation.stripcharacters();
  }

  /**
   * {@inheritDoc} check if given string is a not empty after strip.
   *
   * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
   *      javax.validation.ConstraintValidatorContext)
   */
  @Override
  public final boolean isValid(final Object pvalue, final ConstraintValidatorContext pcontext) {
    final String valueAsString =
        StringUtils.strip(Objects.toString(pvalue, null), this.stripcharacters);

    return StringUtils.isNotEmpty(valueAsString);
  }
}
