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

package de.knightsoftnet.validators.shared.testcases;

import de.knightsoftnet.validators.shared.data.PhoneNumberData;

import java.util.HashMap;
import java.util.Map;

/**
 * get test cases for phone number util test.
 *
 * @author Manfred Tremmel
 *
 */
public class PhoneNumberUtilTestCases {

  /**
   * get parse test cases with expected results.
   * 
   * @return map of strings with expected parse result
   */
  public static final Map<String, PhoneNumberData> getParseCases() {
    final Map<String, PhoneNumberData> testData = new HashMap<>();
    testData.put("+49891234-5678", new PhoneNumberData("49", "89", "1234", "5678"));
    testData.put("+49-89-1234-5678", new PhoneNumberData("49", "89", "1234", "5678"));
    testData.put("+49 89 1234-5678", new PhoneNumberData("49", "89", "1234", "5678"));
    testData.put("+49 89 12 34 - 56 78", new PhoneNumberData("49", "89", "1234", "5678"));
    testData.put("+49 (89) 1234-5678", new PhoneNumberData("49", "89", "1234", "5678"));
    testData.put("+49 (0)89 1234-5678", new PhoneNumberData("49", "89", "1234", "5678"));
    testData.put("089 1234-5678", new PhoneNumberData("49", "89", "1234", "5678"));
    testData.put("(089) 1234 5678", new PhoneNumberData("49", "89", "12345678", null));
    testData.put("0 89 / 12 34 56 78", new PhoneNumberData("49", "89", "12345678", null));
    testData.put("+49 (89) 12345678", new PhoneNumberData("49", "89", "12345678", null));
    return testData;
  }
}
