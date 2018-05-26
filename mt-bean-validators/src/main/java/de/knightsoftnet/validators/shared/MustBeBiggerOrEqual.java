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

import de.knightsoftnet.validators.shared.impl.MustBeBiggerOrEqualValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * The annotated bean must contain at least two properties:
 * <ul>
 * <li>a field to compare (option <code>field1</code>)</li>
 * <li>another field to compared (option <code>field2</code>)</li>
 * <li>add the error to field1 (option <code>addErrorToField1</code>, default true)</li>
 * <li>add the error to field2 (option <code>addErrorToField2</code>, default false)</li>
 * </ul>
 * the entry of <code>field1</code> must be bigger or equal then entry of <code>field2</code>, can
 * be used e.g. for date or number "from"/"to" fields.<br>
 * Supported types are beans, <code>null</code> elements are considered valid.<br>
 * The fields must implement Comparable!<br>
 *
 * @author Manfred Tremmel
 *
 */
@Documented
@Constraint(validatedBy = MustBeBiggerOrEqualValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MustBeBiggerOrEqual {
  /**
   * localized message.
   *
   * @return localized validation message
   */
  String message() default "{deKnightsoftnetValidatorsSharedValidationMustBeBiggerOrEqualMessage}";

  /**
   * groups to use.
   *
   * @return array of validation groups
   */
  Class<?>[] groups() default {};

  /**
   * field1 name to compare.
   *
   * @return field/path contains value to compare
   */
  String field1();

  /**
   * field2 name to compare.
   *
   * @return field/path contains value to compare
   */
  String field2();

  /**
   * add error to field1 (default true).
   *
   * @return true if error should be added to field1
   */
  boolean addErrorToField1() default true;

  /**
   * add error to field2 (default false).
   *
   * @return true if error should be added to field2
   */
  boolean addErrorToField2() default false;

  /**
   * payload whatever.
   *
   * @return payload class
   */
  Class<? extends Payload>[] payload() default {};

  /**
   * Defines several {@code MustBeBiggerOrEqual} annotations on the same element.
   */
  @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  @interface List {
    /**
     * must be bigger or equal value.
     *
     * @return value
     */
    MustBeBiggerOrEqual[] value();
  }
}
