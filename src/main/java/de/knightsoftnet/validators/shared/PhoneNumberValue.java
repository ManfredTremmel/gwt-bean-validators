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

import de.knightsoftnet.validators.shared.impl.PhoneNumberValueValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * The annotated element must be a valid phone number format.<br />
 * Supported types are Strings, other Objects are transfered to Strings, <code>null</code> elements
 * are considered valid. Different formats can be turned on/off with <code>allowDin5008</code>,
 * <code>allowE123</code>, <code>allowUri</code>, <code>allowMs</code> and <code>allowCommon</code>.
 * By default all formats are turned on, you can turn it off with <code>false</code>.<br />
 * There are numeric, size and checksum tests by apache commons validation routines.<br />
 *
 * @author Manfred Tremmel
 *
 */
@Documented
@Constraint(validatedBy = PhoneNumberValueValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumberValue {
  /**
   * localized message.
   */
  String message() default "{deKnightsoftnetValidatorsSharedValidationPhoneNumberMessage}";

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
   * field name of the phone number field.
   */
  String fieldPhoneNumber() default "phoneNumber";

  /**
   * are lower case country codes allowed (true/false).
   */
  boolean allowLowerCaseCountryCode() default false;

  /**
   * allow din 5008 format (true/false).
   */
  boolean allowDin5008() default true;

  /**
   * allow E123 format (true/false).
   */
  boolean allowE123() default true;

  /**
   * allow uri format (true/false).
   */
  boolean allowUri() default true;

  /**
   * allow microsoft format (true/false).
   */
  boolean allowMs() default true;

  /**
   * allow not standardized but common format (true/false).
   */
  boolean allowCommon() default true;

  /**
   * Defines several {@code @PhoneNumberValue} annotations on the same element.
   */
  @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  public @interface List {
    /**
     * phone number value.
     */
    PhoneNumberValue[] value();
  }
}
