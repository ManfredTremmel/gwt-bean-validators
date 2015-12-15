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

import java.util.HashMap;
import java.util.Map;

/**
 * get test cases for reg ex util test.
 *
 * @author Manfred Tremmel
 *
 */
public class RegExUtilTestCases {

  /**
   * get allowed characters for regex cases with expected results.
   *
   * @return map of regex with expected result strings
   */
  public static final Map<String, String> getAllowedCharactersForRegExCases() {
    final Map<String, String> testData = new HashMap<>();
    testData.put("^AD[0-9]{3}$", "0123456789AD");
    testData.put("^\\d{5}$", "0123456789");
    testData.put("[0-9]{4}*", "0123456789");
    testData.put("^([A-HJ-NP-Z])?\\d{4}([A-Z]{3})?$", "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    testData.put("^96799$", "679");
    testData.put("^(BB\\d{5})?$", "0123456789B");
    testData.put("^[ABCEGHJKLMNPRSTVXY]\\d[ABCEGHJ-NPRSTV-Z][ ]?\\d[ABCEGHJ-NPRSTV-Z]\\d$",
        " 0123456789ABCEGHJKLMNPRSTVWXYZ");
    testData.put("^\\d{4,5}|\\d{3}-\\d{4}$", "-0123456789");
    testData.put("^(948[5-9])|(949[0-7])$", "0123456789");
    return testData;
  }
}
