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

import de.knightsoftnet.validators.shared.impl.PasswordValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * The annotated element must be a valid password.<br />
 * Supported types are Strings, other Objects are transfered to Strings, <code>null</code> elements
 * are considered valid.<br />
 * There are the following rules checked (with <code>minRules</code> parameter the number rules can
 * be defined that have to be fullfilled):
 * <ul>
 * <li>upper-/lowercase</li>
 * <li>digits</li>
 * <li>special character</li>
 * </ul>
 * size limits should be checked by separate size annotation.
 *
 * @author Manfred Tremmel
 *
 *
 */
@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
    ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
  /**
   * localized message.
   */
  String message() default "{deKnightsoftnetValidatorsSharedValidationPasswordMessage}";

  /**
   * groups to use.
   */
  Class<?>[] groups() default {};

  /**
   * minimum number rules that have to be fulfilled.
   */
  int minRules();

  /**
   * payload whatever.
   */
  Class<? extends Payload>[] payload() default {};

  /**
   * Defines several {@code Password} annotations on the same element.
   */
  @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
      ElementType.CONSTRUCTOR, ElementType.PARAMETER})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  public @interface List {
    /**
     * password value.
     */
    Password[] value();
  }
}
