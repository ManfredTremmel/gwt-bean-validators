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

package de.knightsoftnet.validators.client.util;

import de.knightsoftnet.validators.shared.data.PhoneNumberData;
import de.knightsoftnet.validators.shared.testcases.PhoneNumberUtilTestCases;
import de.knightsoftnet.validators.shared.util.PhoneNumberUtil;

import com.google.gwt.junit.client.GWTTestCase;

import java.util.Map.Entry;

public class GwtTstPhoneNumberUtil extends GWTTestCase {

  private final PhoneNumberUtil phoneNumberUtil;

  /**
   * set up for testing.
   */
  public GwtTstPhoneNumberUtil() {
    super();
    this.phoneNumberUtil = new PhoneNumberUtil("DE");
  }

  @Override
  public String getModuleName() {
    return "de.knightsoftnet.validators.GwtBeanValidatorsJUnit";
  }

  /**
   * test parsing of phone number.
   */
  public void testParsing() {
    for (final Entry<String, PhoneNumberData> testCase : PhoneNumberUtilTestCases.getParseCases()
        .entrySet()) {
      final PhoneNumberData parsedNumber = this.phoneNumberUtil.parsePhoneNumber(testCase.getKey());
      assertEquals("Should be equal", testCase.getValue(), parsedNumber);
    }
  }
}
