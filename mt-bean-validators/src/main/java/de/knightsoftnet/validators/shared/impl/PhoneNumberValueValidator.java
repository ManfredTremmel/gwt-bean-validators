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

import de.knightsoftnet.validators.shared.PhoneNumberValue;
import de.knightsoftnet.validators.shared.util.PhoneNumberUtil;

/**
 * Check a string if it's a valid PhoneNumber.
 *
 * @author Manfred Tremmel
 *
 */
public class PhoneNumberValueValidator extends AbstractPhoneNumberValueValidator<PhoneNumberValue> {

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final PhoneNumberValue pconstraintAnnotation) {
    message = pconstraintAnnotation.message();
    fieldPhoneNumber = pconstraintAnnotation.fieldPhoneNumber();
    fieldCountryCode = pconstraintAnnotation.fieldCountryCode();
    allowDin5008 = pconstraintAnnotation.allowDin5008();
    allowE123 = pconstraintAnnotation.allowE123();
    allowUri = pconstraintAnnotation.allowUri();
    allowMs = pconstraintAnnotation.allowMs();
    allowCommon = pconstraintAnnotation.allowCommon();
    phoneNumberUtil = new PhoneNumberUtil();
  }
}
