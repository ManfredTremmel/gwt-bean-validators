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

package de.knightsoftnet.validators.server;

import de.knightsoftnet.validators.shared.beans.PhoneNumberValueRestTestBean;
import de.knightsoftnet.validators.shared.testcases.PhoneNumberValueRestTestCases;

import org.junit.Test;

/**
 * test for phone number value rest validator.
 *
 * @author Manfred Tremmel
 *
 */
public class PhoneNumberValueRestTest extends AbstractValidationTest<PhoneNumberValueRestTestBean> {

  /**
   * empty PhoneNumber is allowed.
   */
  @Test
  public final void testEmptyPhoneNumberIsAllowed() {
    super.validationTest(PhoneNumberValueRestTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct PhoneNumber is allowed.
   */
  @Test
  public final void testCorrectPhoneNumberIsAllowed() {
    for (final PhoneNumberValueRestTestBean testBean : PhoneNumberValueRestTestCases
        .getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * PhoneNumber with wrong checksum.
   */
  @Test
  public final void testWrongPhoneNumberIsWrong() {
    for (final PhoneNumberValueRestTestBean testBean : PhoneNumberValueRestTestCases
        .getWrongTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.PhoneNumberValueRestValidator");
    }
  }
}
