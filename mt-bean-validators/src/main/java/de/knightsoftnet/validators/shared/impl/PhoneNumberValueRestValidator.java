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

package de.knightsoftnet.validators.shared.impl;

import de.knightsoftnet.validators.shared.PhoneNumberValueRest;
import de.knightsoftnet.validators.shared.util.PhoneNumberUtil;

/**
 * Check a string if it's a valid PhoneNumber.
 *
 * @author Manfred Tremmel
 *
 */
public class PhoneNumberValueRestValidator
    extends AbstractPhoneNumberValueValidator<PhoneNumberValueRest> {

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final PhoneNumberValueRest pconstraintAnnotation) {
    this.message = pconstraintAnnotation.message();
    this.fieldPhoneNumber = pconstraintAnnotation.fieldPhoneNumber();
    this.fieldCountryCode = pconstraintAnnotation.fieldCountryCode();
    this.allowDin5008 = pconstraintAnnotation.allowDin5008();
    this.allowE123 = pconstraintAnnotation.allowE123();
    this.allowUri = pconstraintAnnotation.allowUri();
    this.allowMs = pconstraintAnnotation.allowMs();
    this.allowCommon = pconstraintAnnotation.allowCommon();
    this.phoneNumberUtil = new PhoneNumberUtil();
  }
}
