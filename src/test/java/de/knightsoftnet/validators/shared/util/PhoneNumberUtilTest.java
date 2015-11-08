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

package de.knightsoftnet.validators.shared.util;

import de.knightsoftnet.validators.shared.data.PhoneNumberData;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class PhoneNumberUtilTest {

  private PhoneNumberUtil phoneNumberUtil;
  private Map<String, PhoneNumberData> testData;

  /**
   * set up for testing.
   */
  @Before
  public void setUp() {
    this.phoneNumberUtil = new PhoneNumberUtil();
    this.testData = new HashMap<>();
    this.testData.put("+49891234-5678", new PhoneNumberData("49", "89", "1234", "5678"));
    this.testData.put("+49-89-1234-5678", new PhoneNumberData("49", "89", "1234", "5678"));
    this.testData.put("+49 89 1234-5678", new PhoneNumberData("49", "89", "1234", "5678"));
    this.testData.put("+49 89 12 34 - 56 78", new PhoneNumberData("49", "89", "1234", "5678"));
    this.testData.put("+49 (89) 1234-5678", new PhoneNumberData("49", "89", "1234", "5678"));
    this.testData.put("+49 (89) 12345678", new PhoneNumberData("49", "89", "12345678", null));
  }

  /**
   * test parsing of phone number.
   */
  @Test
  public void parseTest() {
    for (final Entry<String, PhoneNumberData> testCase : this.testData.entrySet()) {
      final PhoneNumberData parsedNumber = this.phoneNumberUtil.parsePhoneNumber(testCase.getKey());
      Assert.assertEquals(testCase.getValue(), parsedNumber);
    }
  }
}
