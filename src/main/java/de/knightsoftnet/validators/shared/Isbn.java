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

import de.knightsoftnet.validators.shared.impl.Isbn10Validator;
import de.knightsoftnet.validators.shared.impl.Isbn13Validator;
import de.knightsoftnet.validators.shared.impl.IsbnValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Digits;

/**
 * Check a string if it's a valid ISBN (10 or 13 digits long).
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
@Documented
@Constraint(validatedBy = IsbnValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
    ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@AlternateSize(size1 = Isbn10Validator.ISBN10_LENGTH, size2 = Isbn13Validator.ISBN13_LENGTH)
@Digits(integer = Isbn13Validator.ISBN13_LENGTH, fraction = 0)
public @interface Isbn {
  /**
   * localized message.
   */
  String message() default "{deKnightsoftnetValidatorsSharedValidationIsbnMessage}";

  /**
   * groups to use.
   */
  Class<?>[] groups() default {};

  /**
   * payload whatever.
   */
  Class<? extends Payload>[] payload() default {};

  /**
   * should separating minus signs be ignored (true/false).
   */
  boolean ignoreSeparators() default false;

  /**
   * Defines several {@code @ISBN} annotations on the same element.
   */
  @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
      ElementType.CONSTRUCTOR, ElementType.PARAMETER})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  public @interface List {
    /**
     * email value.
     */
    Isbn[] value();
  }
}
