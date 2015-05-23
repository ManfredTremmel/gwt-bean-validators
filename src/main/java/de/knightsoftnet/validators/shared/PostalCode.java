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

import de.knightsoftnet.validators.shared.impl.PostalCodeValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * The annotated bean must contain two properties:
 * <ul>
 * <li>country code (option <code>fieldCountryCode</code>)</li>
 * <li>postal code/zip (option <code>fieldPostalCode</code>)</li>
 * </ul>
 * The postal code is checked against country specific rules for validity.<br />
 * Supported types are beans, <code>null</code> elements are considered valid.<br />
 * If <code>allowLowerCaseCountryCode</code> is set to true, lower case country codes are accepted.
 *
 * @author Manfred Tremmel
 *
 */
@Documented
@Constraint(validatedBy = PostalCodeValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PostalCode {
  /**
   * localized message.
   */
  String message() default "{deKnightsoftnetValidatorsSharedValidationPostalCodeMessage}";

  /**
   * groups to use.
   */
  Class<?>[] groups() default {};

  /**
   * payload whatever.
   */
  Class<? extends Payload>[] payload() default {};

  /**
   * field name of the country code field.
   */
  String fieldCountryCode() default "countryCode";

  /**
   * are lower case country codes allowed (true/false).
   */
  boolean allowLowerCaseCountryCode() default false;

  /**
   * field name of the postal code (zip) field.
   */
  String fieldPostalCode() default "postalCode";

  /**
   * Defines several {@code @PostalCode} annotations on the same element.
   */
  @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  public @interface List {
    /**
     * postal code value.
     */
    PostalCode[] value();
  }
}
