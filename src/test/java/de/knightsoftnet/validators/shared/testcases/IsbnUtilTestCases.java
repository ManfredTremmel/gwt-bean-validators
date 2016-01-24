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

import de.knightsoftnet.validators.shared.data.ValueWithPos;

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
   * get format isbn 10 test cases with expected results.
   *
   * @return map of strings with expected parse result and position of the cursor
   */
  public static Map<ValueWithPos<String>, ValueWithPos<String>> getIsbn10FormatWithPosCases() {
    final Map<ValueWithPos<String>, ValueWithPos<String>> testData = new HashMap<>();
    testData.put(new ValueWithPos<String>("3-80-770171-0", 0),
        new ValueWithPos<String>("3807701710", -1));

    testData.put(new ValueWithPos<String>("3-80-770171-0", 0),
        new ValueWithPos<String>("3807701710", 0));

    testData.put(new ValueWithPos<String>("3-80-770171-0", 2),
        new ValueWithPos<String>("3807701710", 1));
    testData.put(new ValueWithPos<String>("3-80-770171-0", 3),
        new ValueWithPos<String>("3807701710", 2));

    testData.put(new ValueWithPos<String>("3-80-770171-0", 5),
        new ValueWithPos<String>("3807701710", 3));
    testData.put(new ValueWithPos<String>("3-80-770171-0", 6),
        new ValueWithPos<String>("3807701710", 4));
    testData.put(new ValueWithPos<String>("3-80-770171-0", 7),
        new ValueWithPos<String>("3807701710", 5));
    testData.put(new ValueWithPos<String>("3-80-770171-0", 8),
        new ValueWithPos<String>("3807701710", 6));
    testData.put(new ValueWithPos<String>("3-80-770171-0", 9),
        new ValueWithPos<String>("3807701710", 7));
    testData.put(new ValueWithPos<String>("3-80-770171-0", 10),
        new ValueWithPos<String>("3807701710", 8));

    testData.put(new ValueWithPos<String>("3-80-770171-0", 12),
        new ValueWithPos<String>("3807701710", 9));
    testData.put(new ValueWithPos<String>("3-80-770171-0", 13),
        new ValueWithPos<String>("3807701710", 10));
    testData.put(new ValueWithPos<String>("3-80-770171-0", 13),
        new ValueWithPos<String>("3807701710", 11));

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
   * get format isbn 13 test cases with expected results.
   *
   * @return map of strings with expected parse result and position of the cursor
   */
  public static Map<ValueWithPos<String>, ValueWithPos<String>> getIsbn13FormatWithPosCases() {
    final Map<ValueWithPos<String>, ValueWithPos<String>> testData = new HashMap<>();
    testData.put(new ValueWithPos<String>("978-3-83-621802-3", 0),
        new ValueWithPos<String>("9783836218023", -1));

    testData.put(new ValueWithPos<String>("978-3-83-621802-3", 0),
        new ValueWithPos<String>("9783836218023", 0));
    testData.put(new ValueWithPos<String>("978-3-83-621802-3", 1),
        new ValueWithPos<String>("9783836218023", 1));
    testData.put(new ValueWithPos<String>("978-3-83-621802-3", 2),
        new ValueWithPos<String>("9783836218023", 2));

    testData.put(new ValueWithPos<String>("978-3-83-621802-3", 4),
        new ValueWithPos<String>("9783836218023", 3));

    testData.put(new ValueWithPos<String>("978-3-83-621802-3", 6),
        new ValueWithPos<String>("9783836218023", 4));
    testData.put(new ValueWithPos<String>("978-3-83-621802-3", 7),
        new ValueWithPos<String>("9783836218023", 5));

    testData.put(new ValueWithPos<String>("978-3-83-621802-3", 9),
        new ValueWithPos<String>("9783836218023", 6));
    testData.put(new ValueWithPos<String>("978-3-83-621802-3", 10),
        new ValueWithPos<String>("9783836218023", 7));
    testData.put(new ValueWithPos<String>("978-3-83-621802-3", 11),
        new ValueWithPos<String>("9783836218023", 8));
    testData.put(new ValueWithPos<String>("978-3-83-621802-3", 12),
        new ValueWithPos<String>("9783836218023", 9));
    testData.put(new ValueWithPos<String>("978-3-83-621802-3", 13),
        new ValueWithPos<String>("9783836218023", 10));
    testData.put(new ValueWithPos<String>("978-3-83-621802-3", 14),
        new ValueWithPos<String>("9783836218023", 11));

    testData.put(new ValueWithPos<String>("978-3-83-621802-3", 16),
        new ValueWithPos<String>("9783836218023", 12));
    testData.put(new ValueWithPos<String>("978-3-83-621802-3", 17),
        new ValueWithPos<String>("9783836218023", 13));
    testData.put(new ValueWithPos<String>("978-3-83-621802-3", 17),
        new ValueWithPos<String>("9783836218023", 14));

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
   * get format isbn test cases with expected results.
   *
   * @return map of strings with expected parse result and position of the cursor
   */
  public static Map<ValueWithPos<String>, ValueWithPos<String>> getIsbnFormatWithPosCases() {
    final Map<ValueWithPos<String>, ValueWithPos<String>> testData = getIsbn10FormatWithPosCases();
    testData.putAll(getIsbn13FormatWithPosCases());
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
