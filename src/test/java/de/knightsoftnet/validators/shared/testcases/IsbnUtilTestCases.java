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
 * get test cases for isbn util test.
 *
 * @author Manfred Tremmel
 *
 */
public class IsbnUtilTestCases {

  /**
   * get format isbn 10 test cases with expected results.
   *
   * @return map of strings with expected parse result
   */
  public static final Map<String, String> getIsbn10FormatCases() {
    final Map<String, String> testData = new HashMap<>();
    testData.put("3-80-770171-0", "3807701710");
    testData.put("3-80-770205-9", "3807702059");
    testData.put("3-80-770192-3", "3807701923");
    testData.put("3-86-640001-2", "3866400012");
    testData.put("3-80-770171-0", "3807701710");
    return testData;
  }

  /**
   * get format isbn 13 test cases with expected results.
   *
   * @return map of strings with expected parse result
   */
  public static final Map<String, String> getIsbn13FormatCases() {
    final Map<String, String> testData = new HashMap<>();
    testData.put("978-3-83-621802-3", "9783836218023");
    testData.put("978-3-83-621507-7", "9783836215077");
    testData.put("978-3-89-864471-6", "9783898644716");
    return testData;
  }

  /**
   * get format isbn test cases with expected results.
   *
   * @return map of strings with expected parse result
   */
  public static final Map<String, String> getIsbnFormatCases() {
    final Map<String, String> testData = getIsbn10FormatCases();
    testData.putAll(getIsbn13FormatCases());
    return testData;
  }

  /**
   * get compress test cases with expected results.
   *
   * @return map of PhoneNumberInterface with expected format result strings
   */
  public static final Map<String, String> getCompressCases() {
    final Map<String, String> testData = new HashMap<>();
    testData.put("3807701710", "3-80-770171-0");
    testData.put("3807702059", "3-80-770205-9");
    testData.put("3807701923", "3-80-770192-3");
    testData.put("3866400012", "3-86-640001-2");
    testData.put("3807701710", "3-80-770171-0");
    testData.put("9783836218023", "978-3-83-621802-3");
    testData.put("9783836215077", "978-3-83-621507-7");
    testData.put("9783898644716", "978-3-89-864471-6");
    return testData;
  }
}
