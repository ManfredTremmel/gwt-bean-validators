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

import de.knightsoftnet.validators.shared.impl.PhoneNumberValueRestValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * The annotated element must be a valid phone number format, it's the same as PhoneNumberValue on
 * server side, on client side the check is done by a rest call to the server (instead of keeping
 * all the data on client side).<br>
 * Supported types are Strings, other Objects are transfered to Strings, <code>null</code> elements
 * are considered valid. Different formats can be turned on/off with <code>allowDin5008</code>,
 * <code>allowE123</code>, <code>allowUri</code>, <code>allowMs</code> and <code>allowCommon</code>.
 * By default all formats are turned on, you can turn it off with <code>false</code>.<br>
 * There are numeric, size and checksum tests by apache commons validation routines.<br>
 *
 * @author Manfred Tremmel
 *
 */
@Documented
@Constraint(validatedBy = PhoneNumberValueRestValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumberValueRest {
  /**
   * localized message.
   *
   * @return localized validation message
   */
  String message() default "{deKnightsoftnetValidatorsSharedValidationPhoneNumberMessage}";

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
   * field name of the country code field.
   *
   * @return field/path contains country code
   */
  String fieldCountryCode() default "countryCode";

  /**
   * field name of the phone number field.
   *
   * @return field/path contains phone number
   */
  String fieldPhoneNumber() default "phoneNumber";

  /**
   * are lower case country codes allowed (true/false).
   *
   * @return true if lower case country code is allowed
   */
  boolean allowLowerCaseCountryCode() default false;

  /**
   * allow din 5008 format (true/false).
   *
   * @return true if din 5008 format is allowed
   */
  boolean allowDin5008() default true;

  /**
   * allow E123 format (true/false).
   *
   * @return true if din e123 format is allowed
   */
  boolean allowE123() default true;

  /**
   * allow uri format (true/false).
   *
   * @return true if din uri format is allowed
   */
  boolean allowUri() default true;

  /**
   * allow microsoft format (true/false).
   *
   * @return true if din ms format is allowed
   */
  boolean allowMs() default true;

  /**
   * allow not standardized but common format (true/false).
   *
   * @return true if din common format is allowed
   */
  boolean allowCommon() default true;

  /**
   * Defines several {@code @PhoneNumberValueRest} annotations on the same element.
   */
  @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  @interface List {
    /**
     * phone number value.
     *
     * @return value
     */
    PhoneNumberValueRest[] value();
  }
}
