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

import de.knightsoftnet.validators.shared.impl.LimitCharsetValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * The annotated element must contain only characters which are allowed in the given charset.<br>
 * Supported types are Strings, other Objects are transfered to Strings, <code>null</code> elements
 * are considered valid.<br>
 * The <code>charset</code> must be given as parameter.<br>
 *
 * @author Manfred Tremmel
 *
 */
@Documented
@Constraint(validatedBy = LimitCharsetValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
    ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface LimitCharset {
  /**
   * localized messages.
   *
   * @return localized validation message
   */
  String message() default "{deKnightsoftnetValidatorsSharedValidationLimitCharsetMessage}";

  /**
   * groups to use.
   *
   * @return array of validation groups
   */
  Class<?>[] groups() default {};

  /**
   * payload whatever.
   *
   * @return payload class
   */
  Class<? extends Payload>[] payload() default {};

  /**
   * name of the charset to check against.
   *
   * @return charset to check against
   */
  String charset();

  /**
   * Defines several {@code @LimitCharset} annotations on the same element.
   */
  @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
      ElementType.CONSTRUCTOR, ElementType.PARAMETER})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  @interface List {
    /**
     * limit charset value.
     *
     * @return value
     */
    LimitCharset[] value();
  }
}
