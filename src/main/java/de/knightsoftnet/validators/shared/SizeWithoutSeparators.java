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

import de.knightsoftnet.validators.shared.impl.SizeWithoutSeparatorsValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * The annotated element size must be between the specified boundaries (included).<br />
 * Supported types are Strings, other Objects are transfered to Strings, <code>null</code> elements
 * are considered valid.<br />
 * With the following options separator characters can be removed before size is checked:
 * <ul>
 * <li>ignoreWhiteSpaces (true/false, default false)</li>
 * <li>ignoreMinus (true/false, default false)</li>
 * <li>ignoreSlashes (true/false, default false)</li>
 * </ul>
 *
 * @author Manfred Tremmel
 *
 */
@Documented
@Constraint(validatedBy = SizeWithoutSeparatorsValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
    ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface SizeWithoutSeparators {
  /**
   * localized message.
   */
  String message() default "{javax.validation.constraints.Size.message}";

  /**
   * groups to use.
   */
  Class<?>[] groups() default {};

  /**
   * payload whatever.
   */
  Class<? extends Payload>[] payload() default {};

  /**
   * size the element must be higher or equal to.
   */
  int min() default 0;

  /**
   * size the element must be lower or equal to.
   */
  int max() default Integer.MAX_VALUE;

  /**
   * true if whitespaces should be ignored.
   */
  boolean ignoreWhiteSpaces() default false;

  /**
   * true if minus should be ignored.
   */
  boolean ignoreMinus() default false;

  /**
   * true if slashes should be ignored.
   */
  boolean ignoreSlashes() default false;

  /**
   * Defines several {@code SizeWithoutSeparators} annotations on the same element.
   */
  @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
      ElementType.CONSTRUCTOR, ElementType.PARAMETER})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  public @interface List {
    /**
     * array of validators.
     */
    SizeWithoutSeparators[] value();
  }
}
