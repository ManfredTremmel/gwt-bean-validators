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
 * get test cases for iban util test.
 *
 * @author Manfred Tremmel
 *
 */
public class IbanUtilTestCases {

  /**
   * get format test cases with expected results.
   *
   * @return map of strings with expected format result strings
   */
  public static final Map<String, String> getFormatCases() {
    final Map<String, String> testData = new HashMap<>();
    testData.put("DE16 7016 0000 0000 5554 44", "DE16701600000000555444");
    testData.put("DE49 4306 0967 0000 0334 01", "DE49430609670000033401");
    testData.put("AT24 2011 1822 2121 9800", "AT242011182221219800");
    testData.put("CH16 0900 0000 8777 6876 6", "CH1609000000877768766");
    testData.put("IT73 O050 1803 2000 0000 0125 125", "IT73O0501803200000000125125");
    testData.put("BE51 3630 3644 5162", "BE51363036445162");
    testData.put("DK62 8065 0002 0071 98", "DK6280650002007198");
    testData.put("NL42 INGB 0006 3919 52", "NL42INGB0006391952");
    testData.put("SE28 5000 0000 0530 4100 2965", "SE2850000000053041002965");
    testData.put("SI56 0201 0001 1603 397", "SI56020100011603397");
    return testData;
  }

  /**
   * get format test cases with expected results.
   *
   * @return map of strings with expected format result strings
   */
  public static Map<ValueWithPos<String>, ValueWithPos<String>> getFormatWithPosCases() {
    final Map<ValueWithPos<String>, ValueWithPos<String>> testData = new HashMap<>();
    testData.put(new ValueWithPos<String>("DE16 7016 0000 0000 5554 44", 0),
        new ValueWithPos<String>("DE16701600000000555444", -1));

    testData.put(new ValueWithPos<String>("DE16 7016 0000 0000 5554 44", 0),
        new ValueWithPos<String>("DE16701600000000555444", 0));
    testData.put(new ValueWithPos<String>("DE16 7016 0000 0000 5554 44", 1),
        new ValueWithPos<String>("DE16701600000000555444", 1));
    testData.put(new ValueWithPos<String>("DE16 7016 0000 0000 5554 44", 2),
        new ValueWithPos<String>("DE16701600000000555444", 2));
    testData.put(new ValueWithPos<String>("DE16 7016 0000 0000 5554 44", 3),
        new ValueWithPos<String>("DE16701600000000555444", 3));

    testData.put(new ValueWithPos<String>("DE16 7016 0000 0000 5554 44", 5),
        new ValueWithPos<String>("DE16701600000000555444", 4));
    testData.put(new ValueWithPos<String>("DE16 7016 0000 0000 5554 44", 6),
        new ValueWithPos<String>("DE16701600000000555444", 5));
    testData.put(new ValueWithPos<String>("DE16 7016 0000 0000 5554 44", 7),
        new ValueWithPos<String>("DE16701600000000555444", 6));
    testData.put(new ValueWithPos<String>("DE16 7016 0000 0000 5554 44", 8),
        new ValueWithPos<String>("DE16701600000000555444", 7));

    testData.put(new ValueWithPos<String>("DE16 7016 0000 0000 5554 44", 10),
        new ValueWithPos<String>("DE16701600000000555444", 8));
    testData.put(new ValueWithPos<String>("DE16 7016 0000 0000 5554 44", 11),
        new ValueWithPos<String>("DE16701600000000555444", 9));
    testData.put(new ValueWithPos<String>("DE16 7016 0000 0000 5554 44", 12),
        new ValueWithPos<String>("DE16701600000000555444", 10));
    testData.put(new ValueWithPos<String>("DE16 7016 0000 0000 5554 44", 13),
        new ValueWithPos<String>("DE16701600000000555444", 11));

    testData.put(new ValueWithPos<String>("DE16 7016 0000 0000 5554 44", 15),
        new ValueWithPos<String>("DE16701600000000555444", 12));
    testData.put(new ValueWithPos<String>("DE16 7016 0000 0000 5554 44", 16),
        new ValueWithPos<String>("DE16701600000000555444", 13));
    testData.put(new ValueWithPos<String>("DE16 7016 0000 0000 5554 44", 17),
        new ValueWithPos<String>("DE16701600000000555444", 14));
    testData.put(new ValueWithPos<String>("DE16 7016 0000 0000 5554 44", 18),
        new ValueWithPos<String>("DE16701600000000555444", 15));

    testData.put(new ValueWithPos<String>("DE16 7016 0000 0000 5554 44", 20),
        new ValueWithPos<String>("DE16701600000000555444", 16));
    testData.put(new ValueWithPos<String>("DE16 7016 0000 0000 5554 44", 21),
        new ValueWithPos<String>("DE16701600000000555444", 17));
    testData.put(new ValueWithPos<String>("DE16 7016 0000 0000 5554 44", 22),
        new ValueWithPos<String>("DE16701600000000555444", 18));
    testData.put(new ValueWithPos<String>("DE16 7016 0000 0000 5554 44", 23),
        new ValueWithPos<String>("DE16701600000000555444", 19));

    testData.put(new ValueWithPos<String>("DE16 7016 0000 0000 5554 44", 25),
        new ValueWithPos<String>("DE16701600000000555444", 20));
    testData.put(new ValueWithPos<String>("DE16 7016 0000 0000 5554 44", 26),
        new ValueWithPos<String>("DE16701600000000555444", 21));
    testData.put(new ValueWithPos<String>("DE16 7016 0000 0000 5554 44", 27),
        new ValueWithPos<String>("DE16701600000000555444", 22));
    testData.put(new ValueWithPos<String>("DE16 7016 0000 0000 5554 44", 27),
        new ValueWithPos<String>("DE16701600000000555444", 23));

    return testData;
  }

  /**
   * get compress test cases with expected results.
   *
   * @return map of strings with expected parse result
   */
  public static final Map<String, String> getCompressCases() {
    final Map<String, String> testData = new HashMap<>();
    testData.put("DE16701600000000555444", "DE16 7016 0000 0000 5554 44");
    testData.put("DE49430609670000033401", "DE49 4306 0967 0000 0334 01");
    testData.put("AT242011182221219800", "AT24 2011 1822 2121 9800");
    testData.put("CH1609000000877768766", "CH16 0900 0000 8777 6876 6");
    testData.put("IT73O0501803200000000125125", "IT73 O050 1803 2000 0000 0125 125");
    testData.put("BE51363036445162", "BE51 3630 3644 5162");
    testData.put("DK6280650002007198", "DK62 8065 0002 0071 98");
    testData.put("NL42INGB0006391952", "NL42 INGB 0006 3919 52");
    testData.put("SE2850000000053041002965", "SE28 5000 0000 0530 4100 2965");
    testData.put("SI56020100011603397", "SI56 0201 0001 1603 397");
    return testData;
  }
}
