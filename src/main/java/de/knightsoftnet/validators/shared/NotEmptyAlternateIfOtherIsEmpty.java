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

import de.knightsoftnet.validators.shared.impl.NotEmptyAlternateIfOtherIsEmptyValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * The annotated bean must contain at least three properties:
 * <ul>
 * <li>a field that has to be checked (option <code>field</code>)</li>
 * <li>a field that has alternate to be checked (option <code>fieldAlternate</code>)</li>
 * <li>a field which entry is compared (option <code>fieldCompare</code>)</li>
 * </ul>
 * if the entry of <code>fieldCompare</code> is empty (null or ""), <code>field</code> or alternate
 * <code>fieldAlternate</code> must not be empty (null or "").<br />
 * Supported types are beans, <code>null</code> elements are considered valid.<br />
 *
 * @author Manfred Tremmel
 *
 */
@Documented
@Constraint(validatedBy = NotEmptyAlternateIfOtherIsEmptyValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmptyAlternateIfOtherIsEmpty {
  /**
   * localized message.
   */
  String message() default "{deKnightsoftnetValidatorsSharedValidationNotEmptyAlternateIfOther"
      + "IsEmptyMessage}";

  /**
   * groups to use.
   */
  Class<?>[] groups() default {};

  /**
   * field name to check.
   */
  String field();

  /**
   * alternate field name to check.
   */
  String fieldAlternate();

  /**
   * field name to compare.
   */
  String fieldCompare();

  /**
   * payload whatever.
   */
  Class<? extends Payload>[] payload() default {};

  /**
   * Defines several {@code MustBeAlternateFilledIfOtherIsEmpty} annotations on the same element.
   */
  @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  public @interface List {
    /**
     * not empty alternate if other is empty value.
     */
    NotEmptyAlternateIfOtherIsEmpty[] value();
  }
}
