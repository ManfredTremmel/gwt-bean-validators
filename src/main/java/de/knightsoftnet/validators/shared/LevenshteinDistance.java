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

package de.knightsoftnet.validators.shared;

import de.knightsoftnet.validators.shared.impl.LevenshteinDistanceValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * The annotated bean must contain exact three properties:
 * <ul>
 * <li>a field that has to be checked (option <code>field1</code>)</li>
 * <li>a field which entry is compared (option <code>field2</code>)</li>
 * <li>the minimum levenshtein distance both field entries must have (option
 * <code>minDistance</code>)</li>
 * <li>add the error to field1 (option <code>addErrorToField1</code>, default true)</li>
 * <li>add the error to field2 (option <code>addErrorToField2</code>, default true)</li>
 * </ul>
 * The Levenshtein distance between the entry of <code>fieldCompare</code> and <code>field</code> is
 * calculated, it must be equal or greater then the value of minDistance. not be empty (null or "").
 * <br />
 * Supported types are beans, <code>null</code> elements are considered valid.<br />
 *
 * @author Valentin Pricop
 *
 */
@Documented
@Constraint(validatedBy = LevenshteinDistanceValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LevenshteinDistance {
  /**
   * localized message.
   */
  String message() default "{deKnightsoftnetValidatorsSharedValidationLevenshteinDistanceMessage}";

  /**
   * groups to use.
   */
  Class<?>[] groups() default {};

  /**
   * field name to check.
   */
  String field1();

  /**
   * field name to compare.
   */
  String field2();

  /**
   * minimum levenshtein distance.
   */
  int minDistance();

  /**
   * add error to field1 (default true).
   */
  boolean addErrorToField1() default true;

  /**
   * add error to field2 (default true).
   */
  boolean addErrorToField2() default true;

  /**
   * payload whatever.
   */
  Class<? extends Payload>[] payload() default {};

  /**
   * Defines several {@code @LevenshteinDistance} annotations on the same element.
   */
  @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  public @interface List {
    /**
     * levenshtein distance value.
     */
    LevenshteinDistance[] value();
  }
}
