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

package de.knightsoftnet.validators.client;

import de.knightsoftnet.validators.shared.beans.PhoneNumberTestBean;
import de.knightsoftnet.validators.shared.testcases.PhoneNumberTestCases;

public class GwtTstPhoneNumber extends AbstractValidationTst<PhoneNumberTestBean> {

  /**
   * empty PhoneNumber is allowed.
   */
  public final void testEmptyPhoneNumberIsAllowed() {
    super.validationTest(PhoneNumberTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct PhoneNumber is allowed.
   */
  public final void testCorrectPhoneNumberIsAllowed() {
    for (final PhoneNumberTestBean testBean : PhoneNumberTestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * PhoneNumber with wrong checksum.
   */
  public final void testWrongChecksumPhoneNumberIsWrong() {
    for (final PhoneNumberTestBean testBean : PhoneNumberTestCases.getWrongTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.PhoneNumberValidator");
    }
  }
}
