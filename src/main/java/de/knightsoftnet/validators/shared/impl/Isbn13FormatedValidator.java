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

import de.knightsoftnet.validators.shared.Isbn13Formated;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check a string if it's a valid formated ISBN13.
 *
 * @author Manfred Tremmel
 *
 */
public class Isbn13FormatedValidator implements ConstraintValidator<Isbn13Formated, Object> {
  /**
   * definition of isbn13 length (formated.
   */
  public static final int ISBN13_LENGTH = 17;

  /**
   * apache commons class to check/calculate GLN/EAN13 check sums.
   */
  private static final org.apache.commons.validator.routines.ISBNValidator CHECK_ISBN =
      new org.apache.commons.validator.routines.ISBNValidator();

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final Isbn13Formated pconstraintAnnotation) {
    // nothing to do
  }

  /**
   * {@inheritDoc} check if given string is a valid isbn13.
   *
   * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
   *      javax.validation.ConstraintValidatorContext)
   */
  @Override
  public final boolean isValid(final Object pvalue, final ConstraintValidatorContext pcontext) {
    final String valueAsString = Objects.toString(pvalue, null);
    if (StringUtils.isEmpty(valueAsString)) {
      return true;
    }
    if (valueAsString.length() != ISBN13_LENGTH) {
      // ISBN13 size is wrong, but that's handled by size annotation
      return true;
    }
    // calculate and check checksum (ISBN13 )
    return CHECK_ISBN.isValidISBN13(valueAsString);
  }
}
