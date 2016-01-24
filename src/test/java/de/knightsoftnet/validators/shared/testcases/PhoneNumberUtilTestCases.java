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
import de.knightsoftnet.validators.shared.data.PhoneNumberInterface;
import de.knightsoftnet.validators.shared.data.ValueWithPos;

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
  public static final Map<String, PhoneNumberInterface> getParseCases() {
    final Map<String, PhoneNumberInterface> testData = new HashMap<>();
    testData.put("+49891234-5678", new PhoneNumberData("49", "89", "1234", "5678"));
    testData.put("+49-89-1234-5678", new PhoneNumberData("49", "89", "1234", "5678"));
    testData.put("+49 89 1234-5678", new PhoneNumberData("49", "89", "1234", "5678"));
    testData.put("+49 89 12 34 - 56 78", new PhoneNumberData("49", "89", "1234", "5678"));
    testData.put("+49 (89) 1234-5678", new PhoneNumberData("49", "89", "1234", "5678"));
    testData.put("+49 (0)89 1234-5678", new PhoneNumberData("49", "89", "1234", "5678"));
    testData.put("089 1234-5678", new PhoneNumberData("49", "89", "1234", "5678"));
    testData.put("(089) 1234 5678", new PhoneNumberData("49", "89", "1234", "5678"));
    testData.put("0 89 / 12 34 56 78", new PhoneNumberData("49", "89", "123456", "78"));
    testData.put("+49 (89) 12345678", new PhoneNumberData("49", "89", "12345678", null));
    testData.put("+491512312345678", new PhoneNumberData("49", "1512", "312345678", null));
    testData.put("+491582312345678", new PhoneNumberData("49", "15823", "12345678", null));
    testData.put("+390612345678", new PhoneNumberData("39", "06", "12345678", null));
    testData.put("00390612345678", new PhoneNumberData("39", "06", "12345678", null));
    testData.put("+41 26 324 11 13", new PhoneNumberData("41", "26", "32411", "13"));
    testData.put("+1216123456", new PhoneNumberData("1", "216", "123456", null));
    testData.put("+222123456", new PhoneNumberData("222", null, "123456", null));
    testData.put("+22263123456", new PhoneNumberData("222", "63", "123456", null));
    testData.put("+3162912345", new PhoneNumberData("31", "629", "12345", null));
    return testData;
  }

  /**
   * get format E123 test cases with expected results.
   *
   * @return map of PhoneNumberInterface with expected format result strings
   */
  public static final Map<PhoneNumberInterface, String> getFormatE123Cases() {
    final Map<PhoneNumberInterface, String> testData = new HashMap<>();
    testData.put(new PhoneNumberData("49", "89", "1234", "5678"), "(089) 1234 5678");
    testData.put(new PhoneNumberData("49", "89", "12345678", null), "(089) 12345678");
    testData.put(new PhoneNumberData("43", "662", "1234", "5678"), "+43 662 12345678");
    testData.put(new PhoneNumberData("43", "662", "12345678", null), "+43 662 12345678");
    return testData;
  }

  /**
   * get format E123 format test cases with expected results.
   *
   * @return map of PhoneNumberInterface with expected format result strings
   */
  public static Map<ValueWithPos<String>, ValueWithPos<String>> getFormatE123WithPosCases() {
    final Map<ValueWithPos<String>, ValueWithPos<String>> testData = new HashMap<>();

    testData.put(new ValueWithPos<String>("089/1234-5678", 0),
        new ValueWithPos<String>("(089) 1234 5678", 1));
    testData.put(new ValueWithPos<String>("089/1234-5678", 1),
        new ValueWithPos<String>("(089) 1234 5678", 2));
    testData.put(new ValueWithPos<String>("089/1234-5678", 2),
        new ValueWithPos<String>("(089) 1234 5678", 3));
    testData.put(new ValueWithPos<String>("089/1234-5678", 3),
        new ValueWithPos<String>("(089) 1234 5678", 6));
    testData.put(new ValueWithPos<String>("089/1234-5678", 4),
        new ValueWithPos<String>("(089) 1234 5678", 6));
    testData.put(new ValueWithPos<String>("089/1234-5678", 5),
        new ValueWithPos<String>("(089) 1234 5678", 7));
    testData.put(new ValueWithPos<String>("089/1234-5678", 6),
        new ValueWithPos<String>("(089) 1234 5678", 8));
    testData.put(new ValueWithPos<String>("089/1234-5678", 7),
        new ValueWithPos<String>("(089) 1234 5678", 9));
    testData.put(new ValueWithPos<String>("089/1234-5678", 8),
        new ValueWithPos<String>("(089) 1234 5678", 11));
    testData.put(new ValueWithPos<String>("089/1234-5678", 9),
        new ValueWithPos<String>("(089) 1234 5678", 11));
    testData.put(new ValueWithPos<String>("089/1234-5678", 10),
        new ValueWithPos<String>("(089) 1234 5678", 12));
    testData.put(new ValueWithPos<String>("089/1234-5678", 11),
        new ValueWithPos<String>("(089) 1234 5678", 13));
    testData.put(new ValueWithPos<String>("089/1234-5678", 12),
        new ValueWithPos<String>("(089) 1234 5678", 14));
    testData.put(new ValueWithPos<String>("089/1234-5678", 13),
        new ValueWithPos<String>("(089) 1234 5678", 15));
    testData.put(new ValueWithPos<String>("089/1234-5678", 14),
        new ValueWithPos<String>("(089) 1234 5678", 15));

    testData.put(new ValueWithPos<String>("0043662/1234-5678", 0),
        new ValueWithPos<String>("+43 662 12345678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 1),
        new ValueWithPos<String>("+43 662 12345678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 2),
        new ValueWithPos<String>("+43 662 12345678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 3),
        new ValueWithPos<String>("+43 662 12345678", 2));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 4),
        new ValueWithPos<String>("+43 662 12345678", 4));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 5),
        new ValueWithPos<String>("+43 662 12345678", 5));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 6),
        new ValueWithPos<String>("+43 662 12345678", 6));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 7),
        new ValueWithPos<String>("+43 662 12345678", 8));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 8),
        new ValueWithPos<String>("+43 662 12345678", 8));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 9),
        new ValueWithPos<String>("+43 662 12345678", 9));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 10),
        new ValueWithPos<String>("+43 662 12345678", 10));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 11),
        new ValueWithPos<String>("+43 662 12345678", 11));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 12),
        new ValueWithPos<String>("+43 662 12345678", 12));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 13),
        new ValueWithPos<String>("+43 662 12345678", 12));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 14),
        new ValueWithPos<String>("+43 662 12345678", 13));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 15),
        new ValueWithPos<String>("+43 662 12345678", 14));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 16),
        new ValueWithPos<String>("+43 662 12345678", 15));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 17),
        new ValueWithPos<String>("+43 662 12345678", 16));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 18),
        new ValueWithPos<String>("+43 662 12345678", 16));
    return testData;
  }

  /**
   * get format E123 international test cases with expected results.
   *
   * @return map of PhoneNumberInterface with expected format result strings
   */
  public static final Map<PhoneNumberInterface, String> getFormatE123InternationalCases() {
    final Map<PhoneNumberInterface, String> testData = new HashMap<>();
    testData.put(new PhoneNumberData("49", "89", "1234", "5678"), "+49 89 12345678");
    testData.put(new PhoneNumberData("49", "89", "12345678", null), "+49 89 12345678");
    testData.put(new PhoneNumberData("43", "662", "1234", "5678"), "+43 662 12345678");
    testData.put(new PhoneNumberData("43", "662", "12345678", null), "+43 662 12345678");
    return testData;
  }

  /**
   * get format E123 international format test cases with expected results.
   *
   * @return map of PhoneNumberInterface with expected format result strings
   */
  public static Map<ValueWithPos<String>, ValueWithPos<String>> getFormatE123InternationalpCases() {
    final Map<ValueWithPos<String>, ValueWithPos<String>> testData = new HashMap<>();

    testData.put(new ValueWithPos<String>("089/1234-5678", 0),
        new ValueWithPos<String>("+49 89 12345678", 1));
    testData.put(new ValueWithPos<String>("089/1234-5678", 1),
        new ValueWithPos<String>("+49 89 12345678", 4));
    testData.put(new ValueWithPos<String>("089/1234-5678", 2),
        new ValueWithPos<String>("+49 89 12345678", 5));
    testData.put(new ValueWithPos<String>("089/1234-5678", 3),
        new ValueWithPos<String>("+49 89 12345678", 7));
    testData.put(new ValueWithPos<String>("089/1234-5678", 4),
        new ValueWithPos<String>("+49 89 12345678", 7));
    testData.put(new ValueWithPos<String>("089/1234-5678", 5),
        new ValueWithPos<String>("+49 89 12345678", 8));
    testData.put(new ValueWithPos<String>("089/1234-5678", 6),
        new ValueWithPos<String>("+49 89 12345678", 9));
    testData.put(new ValueWithPos<String>("089/1234-5678", 7),
        new ValueWithPos<String>("+49 89 12345678", 10));
    testData.put(new ValueWithPos<String>("089/1234-5678", 8),
        new ValueWithPos<String>("+49 89 12345678", 11));
    testData.put(new ValueWithPos<String>("089/1234-5678", 9),
        new ValueWithPos<String>("+49 89 12345678", 11));
    testData.put(new ValueWithPos<String>("089/1234-5678", 10),
        new ValueWithPos<String>("+49 89 12345678", 12));
    testData.put(new ValueWithPos<String>("089/1234-5678", 11),
        new ValueWithPos<String>("+49 89 12345678", 13));
    testData.put(new ValueWithPos<String>("089/1234-5678", 12),
        new ValueWithPos<String>("+49 89 12345678", 14));
    testData.put(new ValueWithPos<String>("089/1234-5678", 13),
        new ValueWithPos<String>("+49 89 12345678", 15));
    testData.put(new ValueWithPos<String>("089/1234-5678", 14),
        new ValueWithPos<String>("+49 89 12345678", 15));

    testData.put(new ValueWithPos<String>("0043662/1234-5678", 0),
        new ValueWithPos<String>("+43 662 12345678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 1),
        new ValueWithPos<String>("+43 662 12345678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 2),
        new ValueWithPos<String>("+43 662 12345678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 3),
        new ValueWithPos<String>("+43 662 12345678", 2));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 4),
        new ValueWithPos<String>("+43 662 12345678", 4));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 5),
        new ValueWithPos<String>("+43 662 12345678", 5));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 6),
        new ValueWithPos<String>("+43 662 12345678", 6));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 7),
        new ValueWithPos<String>("+43 662 12345678", 8));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 8),
        new ValueWithPos<String>("+43 662 12345678", 8));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 9),
        new ValueWithPos<String>("+43 662 12345678", 9));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 10),
        new ValueWithPos<String>("+43 662 12345678", 10));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 11),
        new ValueWithPos<String>("+43 662 12345678", 11));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 12),
        new ValueWithPos<String>("+43 662 12345678", 12));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 13),
        new ValueWithPos<String>("+43 662 12345678", 12));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 14),
        new ValueWithPos<String>("+43 662 12345678", 13));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 15),
        new ValueWithPos<String>("+43 662 12345678", 14));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 16),
        new ValueWithPos<String>("+43 662 12345678", 15));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 17),
        new ValueWithPos<String>("+43 662 12345678", 16));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 18),
        new ValueWithPos<String>("+43 662 12345678", 16));
    return testData;
  }


  /**
   * get format E123 national test cases with expected results.
   *
   * @return map of PhoneNumberInterface with expected format result strings
   */
  public static final Map<PhoneNumberInterface, String> getFormatE123NationalCases() {
    final Map<PhoneNumberInterface, String> testData = new HashMap<>();
    testData.put(new PhoneNumberData("49", "89", "1234", "5678"), "(089) 1234 5678");
    testData.put(new PhoneNumberData("49", "89", "12345678", null), "(089) 12345678");
    testData.put(new PhoneNumberData("43", "662", "1234", "5678"), "(0662) 1234 5678");
    testData.put(new PhoneNumberData("43", "662", "12345678", null), "(0662) 12345678");
    return testData;
  }

  /**
   * get format E123 national format test cases with expected results.
   *
   * @return map of PhoneNumberInterface with expected format result strings
   */
  public static Map<ValueWithPos<String>, ValueWithPos<String>> getFormatE123NationalPosCases() {
    final Map<ValueWithPos<String>, ValueWithPos<String>> testData = new HashMap<>();

    testData.put(new ValueWithPos<String>("089/1234-5678", 0),
        new ValueWithPos<String>("(089) 1234 5678", 1));
    testData.put(new ValueWithPos<String>("089/1234-5678", 1),
        new ValueWithPos<String>("(089) 1234 5678", 2));
    testData.put(new ValueWithPos<String>("089/1234-5678", 2),
        new ValueWithPos<String>("(089) 1234 5678", 3));
    testData.put(new ValueWithPos<String>("089/1234-5678", 3),
        new ValueWithPos<String>("(089) 1234 5678", 6));
    testData.put(new ValueWithPos<String>("089/1234-5678", 4),
        new ValueWithPos<String>("(089) 1234 5678", 6));
    testData.put(new ValueWithPos<String>("089/1234-5678", 5),
        new ValueWithPos<String>("(089) 1234 5678", 7));
    testData.put(new ValueWithPos<String>("089/1234-5678", 6),
        new ValueWithPos<String>("(089) 1234 5678", 8));
    testData.put(new ValueWithPos<String>("089/1234-5678", 7),
        new ValueWithPos<String>("(089) 1234 5678", 9));
    testData.put(new ValueWithPos<String>("089/1234-5678", 8),
        new ValueWithPos<String>("(089) 1234 5678", 11));
    testData.put(new ValueWithPos<String>("089/1234-5678", 9),
        new ValueWithPos<String>("(089) 1234 5678", 11));
    testData.put(new ValueWithPos<String>("089/1234-5678", 10),
        new ValueWithPos<String>("(089) 1234 5678", 12));
    testData.put(new ValueWithPos<String>("089/1234-5678", 11),
        new ValueWithPos<String>("(089) 1234 5678", 13));
    testData.put(new ValueWithPos<String>("089/1234-5678", 12),
        new ValueWithPos<String>("(089) 1234 5678", 14));
    testData.put(new ValueWithPos<String>("089/1234-5678", 13),
        new ValueWithPos<String>("(089) 1234 5678", 15));
    testData.put(new ValueWithPos<String>("089/1234-5678", 14),
        new ValueWithPos<String>("(089) 1234 5678", 15));

    testData.put(new ValueWithPos<String>("0043662/1234-5678", 0),
        new ValueWithPos<String>("(0662) 1234 5678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 1),
        new ValueWithPos<String>("(0662) 1234 5678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 2),
        new ValueWithPos<String>("(0662) 1234 5678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 3),
        new ValueWithPos<String>("(0662) 1234 5678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 4),
        new ValueWithPos<String>("(0662) 1234 5678", 2));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 5),
        new ValueWithPos<String>("(0662) 1234 5678", 3));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 6),
        new ValueWithPos<String>("(0662) 1234 5678", 4));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 7),
        new ValueWithPos<String>("(0662) 1234 5678", 7));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 8),
        new ValueWithPos<String>("(0662) 1234 5678", 7));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 9),
        new ValueWithPos<String>("(0662) 1234 5678", 8));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 10),
        new ValueWithPos<String>("(0662) 1234 5678", 9));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 11),
        new ValueWithPos<String>("(0662) 1234 5678", 10));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 12),
        new ValueWithPos<String>("(0662) 1234 5678", 12));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 13),
        new ValueWithPos<String>("(0662) 1234 5678", 12));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 14),
        new ValueWithPos<String>("(0662) 1234 5678", 13));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 15),
        new ValueWithPos<String>("(0662) 1234 5678", 14));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 16),
        new ValueWithPos<String>("(0662) 1234 5678", 15));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 17),
        new ValueWithPos<String>("(0662) 1234 5678", 16));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 18),
        new ValueWithPos<String>("(0662) 1234 5678", 16));
    return testData;
  }

  /**
   * get format DIN 5008 test cases with expected results.
   *
   * @return map of PhoneNumberInterface with expected format result strings
   */
  public static final Map<PhoneNumberInterface, String> getFormatDin5008Cases() {
    final Map<PhoneNumberInterface, String> testData = new HashMap<>();
    testData.put(new PhoneNumberData("49", "89", "1234", "5678"), "089 1234-5678");
    testData.put(new PhoneNumberData("49", "89", "12345678", null), "089 12345678");
    testData.put(new PhoneNumberData("43", "662", "1234", "5678"), "+43 662 1234-5678");
    testData.put(new PhoneNumberData("43", "662", "12345678", null), "+43 662 12345678");
    return testData;
  }

  /**
   * get format Din5008 format test cases with expected results.
   *
   * @return map of PhoneNumberInterface with expected format result strings
   */
  public static Map<ValueWithPos<String>, ValueWithPos<String>> getFormatDin5008WithPosCases() {
    final Map<ValueWithPos<String>, ValueWithPos<String>> testData = new HashMap<>();

    testData.put(new ValueWithPos<String>("089/1234-5678", 0),
        new ValueWithPos<String>("089 1234-5678", 0));
    testData.put(new ValueWithPos<String>("089/1234-5678", 1),
        new ValueWithPos<String>("089 1234-5678", 1));
    testData.put(new ValueWithPos<String>("089/1234-5678", 2),
        new ValueWithPos<String>("089 1234-5678", 2));
    testData.put(new ValueWithPos<String>("089/1234-5678", 3),
        new ValueWithPos<String>("089 1234-5678", 4));
    testData.put(new ValueWithPos<String>("089/1234-5678", 4),
        new ValueWithPos<String>("089 1234-5678", 4));
    testData.put(new ValueWithPos<String>("089/1234-5678", 5),
        new ValueWithPos<String>("089 1234-5678", 5));
    testData.put(new ValueWithPos<String>("089/1234-5678", 6),
        new ValueWithPos<String>("089 1234-5678", 6));
    testData.put(new ValueWithPos<String>("089/1234-5678", 7),
        new ValueWithPos<String>("089 1234-5678", 7));
    testData.put(new ValueWithPos<String>("089/1234-5678", 8),
        new ValueWithPos<String>("089 1234-5678", 9));
    testData.put(new ValueWithPos<String>("089/1234-5678", 9),
        new ValueWithPos<String>("089 1234-5678", 9));
    testData.put(new ValueWithPos<String>("089/1234-5678", 10),
        new ValueWithPos<String>("089 1234-5678", 10));
    testData.put(new ValueWithPos<String>("089/1234-5678", 11),
        new ValueWithPos<String>("089 1234-5678", 11));
    testData.put(new ValueWithPos<String>("089/1234-5678", 12),
        new ValueWithPos<String>("089 1234-5678", 12));
    testData.put(new ValueWithPos<String>("089/1234-5678", 13),
        new ValueWithPos<String>("089 1234-5678", 13));
    testData.put(new ValueWithPos<String>("089/1234-5678", 14),
        new ValueWithPos<String>("089 1234-5678", 13));

    testData.put(new ValueWithPos<String>("0043662/1234-5678", 0),
        new ValueWithPos<String>("+43 662 1234-5678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 1),
        new ValueWithPos<String>("+43 662 1234-5678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 2),
        new ValueWithPos<String>("+43 662 1234-5678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 3),
        new ValueWithPos<String>("+43 662 1234-5678", 2));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 4),
        new ValueWithPos<String>("+43 662 1234-5678", 4));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 5),
        new ValueWithPos<String>("+43 662 1234-5678", 5));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 6),
        new ValueWithPos<String>("+43 662 1234-5678", 6));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 7),
        new ValueWithPos<String>("+43 662 1234-5678", 8));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 8),
        new ValueWithPos<String>("+43 662 1234-5678", 8));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 9),
        new ValueWithPos<String>("+43 662 1234-5678", 9));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 10),
        new ValueWithPos<String>("+43 662 1234-5678", 10));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 11),
        new ValueWithPos<String>("+43 662 1234-5678", 11));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 12),
        new ValueWithPos<String>("+43 662 1234-5678", 13));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 13),
        new ValueWithPos<String>("+43 662 1234-5678", 13));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 14),
        new ValueWithPos<String>("+43 662 1234-5678", 14));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 15),
        new ValueWithPos<String>("+43 662 1234-5678", 15));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 16),
        new ValueWithPos<String>("+43 662 1234-5678", 16));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 17),
        new ValueWithPos<String>("+43 662 1234-5678", 17));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 18),
        new ValueWithPos<String>("+43 662 1234-5678", 17));
    return testData;
  }

  /**
   * get format DIN 5008 international test cases with expected results.
   *
   * @return map of PhoneNumberInterface with expected format result strings
   */
  public static final Map<PhoneNumberInterface, String> getFormatDin5008InternationalCases() {
    final Map<PhoneNumberInterface, String> testData = new HashMap<>();
    testData.put(new PhoneNumberData("49", "89", "1234", "5678"), "+49 89 1234-5678");
    testData.put(new PhoneNumberData("49", "89", "12345678", null), "+49 89 12345678");
    testData.put(new PhoneNumberData("43", "662", "1234", "5678"), "+43 662 1234-5678");
    testData.put(new PhoneNumberData("43", "662", "12345678", null), "+43 662 12345678");
    return testData;
  }

  /**
   * get format Din5008 international format test cases with expected results.
   *
   * @return map of PhoneNumberInterface with expected format result strings
   */
  public static Map<ValueWithPos<String>, ValueWithPos<String>> getFormatDin5008InternPosCases() {
    final Map<ValueWithPos<String>, ValueWithPos<String>> testData = new HashMap<>();

    testData.put(new ValueWithPos<String>("089/1234-5678", 0),
        new ValueWithPos<String>("+49 89 1234-5678", 1));
    testData.put(new ValueWithPos<String>("089/1234-5678", 1),
        new ValueWithPos<String>("+49 89 1234-5678", 4));
    testData.put(new ValueWithPos<String>("089/1234-5678", 2),
        new ValueWithPos<String>("+49 89 1234-5678", 5));
    testData.put(new ValueWithPos<String>("089/1234-5678", 3),
        new ValueWithPos<String>("+49 89 1234-5678", 7));
    testData.put(new ValueWithPos<String>("089/1234-5678", 4),
        new ValueWithPos<String>("+49 89 1234-5678", 7));
    testData.put(new ValueWithPos<String>("089/1234-5678", 5),
        new ValueWithPos<String>("+49 89 1234-5678", 8));
    testData.put(new ValueWithPos<String>("089/1234-5678", 6),
        new ValueWithPos<String>("+49 89 1234-5678", 9));
    testData.put(new ValueWithPos<String>("089/1234-5678", 7),
        new ValueWithPos<String>("+49 89 1234-5678", 10));
    testData.put(new ValueWithPos<String>("089/1234-5678", 8),
        new ValueWithPos<String>("+49 89 1234-5678", 12));
    testData.put(new ValueWithPos<String>("089/1234-5678", 9),
        new ValueWithPos<String>("+49 89 1234-5678", 12));
    testData.put(new ValueWithPos<String>("089/1234-5678", 10),
        new ValueWithPos<String>("+49 89 1234-5678", 13));
    testData.put(new ValueWithPos<String>("089/1234-5678", 11),
        new ValueWithPos<String>("+49 89 1234-5678", 14));
    testData.put(new ValueWithPos<String>("089/1234-5678", 12),
        new ValueWithPos<String>("+49 89 1234-5678", 15));
    testData.put(new ValueWithPos<String>("089/1234-5678", 13),
        new ValueWithPos<String>("+49 89 1234-5678", 16));
    testData.put(new ValueWithPos<String>("089/1234-5678", 14),
        new ValueWithPos<String>("+49 89 1234-5678", 16));

    testData.put(new ValueWithPos<String>("0043662/1234-5678", 0),
        new ValueWithPos<String>("+43 662 1234-5678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 1),
        new ValueWithPos<String>("+43 662 1234-5678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 2),
        new ValueWithPos<String>("+43 662 1234-5678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 3),
        new ValueWithPos<String>("+43 662 1234-5678", 2));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 4),
        new ValueWithPos<String>("+43 662 1234-5678", 4));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 5),
        new ValueWithPos<String>("+43 662 1234-5678", 5));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 6),
        new ValueWithPos<String>("+43 662 1234-5678", 6));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 7),
        new ValueWithPos<String>("+43 662 1234-5678", 8));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 8),
        new ValueWithPos<String>("+43 662 1234-5678", 8));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 9),
        new ValueWithPos<String>("+43 662 1234-5678", 9));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 10),
        new ValueWithPos<String>("+43 662 1234-5678", 10));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 11),
        new ValueWithPos<String>("+43 662 1234-5678", 11));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 12),
        new ValueWithPos<String>("+43 662 1234-5678", 13));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 13),
        new ValueWithPos<String>("+43 662 1234-5678", 13));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 14),
        new ValueWithPos<String>("+43 662 1234-5678", 14));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 15),
        new ValueWithPos<String>("+43 662 1234-5678", 15));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 16),
        new ValueWithPos<String>("+43 662 1234-5678", 16));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 17),
        new ValueWithPos<String>("+43 662 1234-5678", 17));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 18),
        new ValueWithPos<String>("+43 662 1234-5678", 17));
    return testData;
  }

  /**
   * get format DIN 5008 national test cases with expected results.
   *
   * @return map of PhoneNumberInterface with expected format result strings
   */
  public static final Map<PhoneNumberInterface, String> getFormatDin5008NationalCases() {
    final Map<PhoneNumberInterface, String> testData = new HashMap<>();
    testData.put(new PhoneNumberData("49", "89", "1234", "5678"), "089 1234-5678");
    testData.put(new PhoneNumberData("49", "89", "12345678", null), "089 12345678");
    testData.put(new PhoneNumberData("43", "662", "1234", "5678"), "0662 1234-5678");
    testData.put(new PhoneNumberData("43", "662", "12345678", null), "0662 12345678");
    return testData;
  }

  /**
   * get format Din5008 national format test cases with expected results.
   *
   * @return map of PhoneNumberInterface with expected format result strings
   */
  public static Map<ValueWithPos<String>, ValueWithPos<String>> getFormatDin5008NationalPosCases() {
    final Map<ValueWithPos<String>, ValueWithPos<String>> testData = new HashMap<>();

    testData.put(new ValueWithPos<String>("089/1234-5678", 0),
        new ValueWithPos<String>("089 1234-5678", 0));
    testData.put(new ValueWithPos<String>("089/1234-5678", 1),
        new ValueWithPos<String>("089 1234-5678", 1));
    testData.put(new ValueWithPos<String>("089/1234-5678", 2),
        new ValueWithPos<String>("089 1234-5678", 2));
    testData.put(new ValueWithPos<String>("089/1234-5678", 3),
        new ValueWithPos<String>("089 1234-5678", 4));
    testData.put(new ValueWithPos<String>("089/1234-5678", 4),
        new ValueWithPos<String>("089 1234-5678", 4));
    testData.put(new ValueWithPos<String>("089/1234-5678", 5),
        new ValueWithPos<String>("089 1234-5678", 5));
    testData.put(new ValueWithPos<String>("089/1234-5678", 6),
        new ValueWithPos<String>("089 1234-5678", 6));
    testData.put(new ValueWithPos<String>("089/1234-5678", 7),
        new ValueWithPos<String>("089 1234-5678", 7));
    testData.put(new ValueWithPos<String>("089/1234-5678", 8),
        new ValueWithPos<String>("089 1234-5678", 9));
    testData.put(new ValueWithPos<String>("089/1234-5678", 9),
        new ValueWithPos<String>("089 1234-5678", 9));
    testData.put(new ValueWithPos<String>("089/1234-5678", 10),
        new ValueWithPos<String>("089 1234-5678", 10));
    testData.put(new ValueWithPos<String>("089/1234-5678", 11),
        new ValueWithPos<String>("089 1234-5678", 11));
    testData.put(new ValueWithPos<String>("089/1234-5678", 12),
        new ValueWithPos<String>("089 1234-5678", 12));
    testData.put(new ValueWithPos<String>("089/1234-5678", 13),
        new ValueWithPos<String>("089 1234-5678", 13));
    testData.put(new ValueWithPos<String>("089/1234-5678", 14),
        new ValueWithPos<String>("089 1234-5678", 13));

    testData.put(new ValueWithPos<String>("0043662/1234-5678", 0),
        new ValueWithPos<String>("0662 1234-5678", 0));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 1),
        new ValueWithPos<String>("0662 1234-5678", 0));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 2),
        new ValueWithPos<String>("0662 1234-5678", 0));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 3),
        new ValueWithPos<String>("0662 1234-5678", 0));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 4),
        new ValueWithPos<String>("0662 1234-5678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 5),
        new ValueWithPos<String>("0662 1234-5678", 2));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 6),
        new ValueWithPos<String>("0662 1234-5678", 3));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 7),
        new ValueWithPos<String>("0662 1234-5678", 5));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 8),
        new ValueWithPos<String>("0662 1234-5678", 5));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 9),
        new ValueWithPos<String>("0662 1234-5678", 6));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 10),
        new ValueWithPos<String>("0662 1234-5678", 7));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 11),
        new ValueWithPos<String>("0662 1234-5678", 8));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 12),
        new ValueWithPos<String>("0662 1234-5678", 10));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 13),
        new ValueWithPos<String>("0662 1234-5678", 10));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 14),
        new ValueWithPos<String>("0662 1234-5678", 11));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 15),
        new ValueWithPos<String>("0662 1234-5678", 12));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 16),
        new ValueWithPos<String>("0662 1234-5678", 13));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 17),
        new ValueWithPos<String>("0662 1234-5678", 14));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 18),
        new ValueWithPos<String>("0662 1234-5678", 14));
    return testData;
  }

  /**
   * get format RFC 3966 test cases with expected results.
   *
   * @return map of PhoneNumberInterface with expected format result strings
   */
  public static final Map<PhoneNumberInterface, String> getFormatRfc3966Cases() {
    final Map<PhoneNumberInterface, String> testData = new HashMap<>();
    testData.put(new PhoneNumberData("49", "89", "1234", "5678"), "+49-89-12345678");
    testData.put(new PhoneNumberData("49", "89", "12345678", null), "+49-89-12345678");
    testData.put(new PhoneNumberData("43", "662", "1234", "5678"), "+43-662-12345678");
    testData.put(new PhoneNumberData("43", "662", "12345678", null), "+43-662-12345678");
    return testData;
  }

  /**
   * get format Rfc3966 international format test cases with expected results.
   *
   * @return map of PhoneNumberInterface with expected format result strings
   */
  public static Map<ValueWithPos<String>, ValueWithPos<String>> getFormatRfc3966WithPosCases() {
    final Map<ValueWithPos<String>, ValueWithPos<String>> testData = new HashMap<>();

    testData.put(new ValueWithPos<String>("089/1234-5678", 0),
        new ValueWithPos<String>("+49-89-12345678", 1));
    testData.put(new ValueWithPos<String>("089/1234-5678", 1),
        new ValueWithPos<String>("+49-89-12345678", 4));
    testData.put(new ValueWithPos<String>("089/1234-5678", 2),
        new ValueWithPos<String>("+49-89-12345678", 5));
    testData.put(new ValueWithPos<String>("089/1234-5678", 3),
        new ValueWithPos<String>("+49-89-12345678", 7));
    testData.put(new ValueWithPos<String>("089/1234-5678", 4),
        new ValueWithPos<String>("+49-89-12345678", 7));
    testData.put(new ValueWithPos<String>("089/1234-5678", 5),
        new ValueWithPos<String>("+49-89-12345678", 8));
    testData.put(new ValueWithPos<String>("089/1234-5678", 6),
        new ValueWithPos<String>("+49-89-12345678", 9));
    testData.put(new ValueWithPos<String>("089/1234-5678", 7),
        new ValueWithPos<String>("+49-89-12345678", 10));
    testData.put(new ValueWithPos<String>("089/1234-5678", 8),
        new ValueWithPos<String>("+49-89-12345678", 11));
    testData.put(new ValueWithPos<String>("089/1234-5678", 9),
        new ValueWithPos<String>("+49-89-12345678", 11));
    testData.put(new ValueWithPos<String>("089/1234-5678", 10),
        new ValueWithPos<String>("+49-89-12345678", 12));
    testData.put(new ValueWithPos<String>("089/1234-5678", 11),
        new ValueWithPos<String>("+49-89-12345678", 13));
    testData.put(new ValueWithPos<String>("089/1234-5678", 12),
        new ValueWithPos<String>("+49-89-12345678", 14));
    testData.put(new ValueWithPos<String>("089/1234-5678", 13),
        new ValueWithPos<String>("+49-89-12345678", 15));
    testData.put(new ValueWithPos<String>("089/1234-5678", 14),
        new ValueWithPos<String>("+49-89-12345678", 15));

    testData.put(new ValueWithPos<String>("0043662/1234-5678", 0),
        new ValueWithPos<String>("+43-662-12345678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 1),
        new ValueWithPos<String>("+43-662-12345678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 2),
        new ValueWithPos<String>("+43-662-12345678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 3),
        new ValueWithPos<String>("+43-662-12345678", 2));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 4),
        new ValueWithPos<String>("+43-662-12345678", 4));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 5),
        new ValueWithPos<String>("+43-662-12345678", 5));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 6),
        new ValueWithPos<String>("+43-662-12345678", 6));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 7),
        new ValueWithPos<String>("+43-662-12345678", 8));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 8),
        new ValueWithPos<String>("+43-662-12345678", 8));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 9),
        new ValueWithPos<String>("+43-662-12345678", 9));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 10),
        new ValueWithPos<String>("+43-662-12345678", 10));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 11),
        new ValueWithPos<String>("+43-662-12345678", 11));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 12),
        new ValueWithPos<String>("+43-662-12345678", 12));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 13),
        new ValueWithPos<String>("+43-662-12345678", 12));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 14),
        new ValueWithPos<String>("+43-662-12345678", 13));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 15),
        new ValueWithPos<String>("+43-662-12345678", 14));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 16),
        new ValueWithPos<String>("+43-662-12345678", 15));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 17),
        new ValueWithPos<String>("+43-662-12345678", 16));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 18),
        new ValueWithPos<String>("+43-662-12345678", 16));
    return testData;
  }

  /**
   * get format Microsoft canonical address format test cases with expected results.
   *
   * @return map of PhoneNumberInterface with expected format result strings
   */
  public static final Map<PhoneNumberInterface, String> getFormatMsCases() {
    final Map<PhoneNumberInterface, String> testData = new HashMap<>();
    testData.put(new PhoneNumberData("49", "89", "1234", "5678"), "+49 (89) 1234 - 5678");
    testData.put(new PhoneNumberData("49", "89", "12345678", null), "+49 (89) 12345678");
    testData.put(new PhoneNumberData("43", "662", "1234", "5678"), "+43 (662) 1234 - 5678");
    testData.put(new PhoneNumberData("43", "662", "12345678", null), "+43 (662) 12345678");
    return testData;
  }

  /**
   * get format Microsoft canonical address format test cases with expected results.
   *
   * @return map of PhoneNumberInterface with expected format result strings
   */
  public static Map<ValueWithPos<String>, ValueWithPos<String>> getFormatMsWithLengthCases() {
    final Map<ValueWithPos<String>, ValueWithPos<String>> testData = new HashMap<>();

    testData.put(new ValueWithPos<String>("089/1234-5678", 0),
        new ValueWithPos<String>("+49 (89) 1234 - 5678", 1));
    testData.put(new ValueWithPos<String>("089/1234-5678", 1),
        new ValueWithPos<String>("+49 (89) 1234 - 5678", 5));
    testData.put(new ValueWithPos<String>("089/1234-5678", 2),
        new ValueWithPos<String>("+49 (89) 1234 - 5678", 6));
    testData.put(new ValueWithPos<String>("089/1234-5678", 3),
        new ValueWithPos<String>("+49 (89) 1234 - 5678", 9));
    testData.put(new ValueWithPos<String>("089/1234-5678", 4),
        new ValueWithPos<String>("+49 (89) 1234 - 5678", 9));
    testData.put(new ValueWithPos<String>("089/1234-5678", 5),
        new ValueWithPos<String>("+49 (89) 1234 - 5678", 10));
    testData.put(new ValueWithPos<String>("089/1234-5678", 6),
        new ValueWithPos<String>("+49 (89) 1234 - 5678", 11));
    testData.put(new ValueWithPos<String>("089/1234-5678", 7),
        new ValueWithPos<String>("+49 (89) 1234 - 5678", 12));
    testData.put(new ValueWithPos<String>("089/1234-5678", 8),
        new ValueWithPos<String>("+49 (89) 1234 - 5678", 16));
    testData.put(new ValueWithPos<String>("089/1234-5678", 9),
        new ValueWithPos<String>("+49 (89) 1234 - 5678", 16));
    testData.put(new ValueWithPos<String>("089/1234-5678", 10),
        new ValueWithPos<String>("+49 (89) 1234 - 5678", 17));
    testData.put(new ValueWithPos<String>("089/1234-5678", 11),
        new ValueWithPos<String>("+49 (89) 1234 - 5678", 18));
    testData.put(new ValueWithPos<String>("089/1234-5678", 12),
        new ValueWithPos<String>("+49 (89) 1234 - 5678", 19));
    testData.put(new ValueWithPos<String>("089/1234-5678", 13),
        new ValueWithPos<String>("+49 (89) 1234 - 5678", 20));
    testData.put(new ValueWithPos<String>("089/1234-5678", 14),
        new ValueWithPos<String>("+49 (89) 1234 - 5678", 20));

    testData.put(new ValueWithPos<String>("0043662/1234-5678", 0),
        new ValueWithPos<String>("+43 (662) 1234 - 5678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 1),
        new ValueWithPos<String>("+43 (662) 1234 - 5678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 2),
        new ValueWithPos<String>("+43 (662) 1234 - 5678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 3),
        new ValueWithPos<String>("+43 (662) 1234 - 5678", 2));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 4),
        new ValueWithPos<String>("+43 (662) 1234 - 5678", 5));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 5),
        new ValueWithPos<String>("+43 (662) 1234 - 5678", 6));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 6),
        new ValueWithPos<String>("+43 (662) 1234 - 5678", 7));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 7),
        new ValueWithPos<String>("+43 (662) 1234 - 5678", 10));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 8),
        new ValueWithPos<String>("+43 (662) 1234 - 5678", 10));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 9),
        new ValueWithPos<String>("+43 (662) 1234 - 5678", 11));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 10),
        new ValueWithPos<String>("+43 (662) 1234 - 5678", 12));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 11),
        new ValueWithPos<String>("+43 (662) 1234 - 5678", 13));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 12),
        new ValueWithPos<String>("+43 (662) 1234 - 5678", 17));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 13),
        new ValueWithPos<String>("+43 (662) 1234 - 5678", 17));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 14),
        new ValueWithPos<String>("+43 (662) 1234 - 5678", 18));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 15),
        new ValueWithPos<String>("+43 (662) 1234 - 5678", 19));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 16),
        new ValueWithPos<String>("+43 (662) 1234 - 5678", 20));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 17),
        new ValueWithPos<String>("+43 (662) 1234 - 5678", 21));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 18),
        new ValueWithPos<String>("+43 (662) 1234 - 5678", 21));
    return testData;
  }

  /**
   * get format Url test cases with expected results.
   *
   * @return map of PhoneNumberInterface with expected format result strings
   */
  public static final Map<PhoneNumberInterface, String> getFormatUrlCases() {
    final Map<PhoneNumberInterface, String> testData = new HashMap<>();
    testData.put(new PhoneNumberData("49", "89", "1234", "5678"), "+49-89-1234-5678");
    testData.put(new PhoneNumberData("49", "89", "12345678", null), "+49-89-12345678");
    testData.put(new PhoneNumberData("43", "662", "1234", "5678"), "+43-662-1234-5678");
    testData.put(new PhoneNumberData("43", "662", "12345678", null), "+43-662-12345678");
    return testData;
  }

  /**
   * get format URL format test cases with expected results.
   *
   * @return map of PhoneNumberInterface with expected format result strings
   */
  public static Map<ValueWithPos<String>, ValueWithPos<String>> getFormatUrlWithPosCases() {
    final Map<ValueWithPos<String>, ValueWithPos<String>> testData = new HashMap<>();

    testData.put(new ValueWithPos<String>("089/1234-5678", 0),
        new ValueWithPos<String>("+49-89-1234-5678", 1));
    testData.put(new ValueWithPos<String>("089/1234-5678", 1),
        new ValueWithPos<String>("+49-89-1234-5678", 4));
    testData.put(new ValueWithPos<String>("089/1234-5678", 2),
        new ValueWithPos<String>("+49-89-1234-5678", 5));
    testData.put(new ValueWithPos<String>("089/1234-5678", 3),
        new ValueWithPos<String>("+49-89-1234-5678", 7));
    testData.put(new ValueWithPos<String>("089/1234-5678", 4),
        new ValueWithPos<String>("+49-89-1234-5678", 7));
    testData.put(new ValueWithPos<String>("089/1234-5678", 5),
        new ValueWithPos<String>("+49-89-1234-5678", 8));
    testData.put(new ValueWithPos<String>("089/1234-5678", 6),
        new ValueWithPos<String>("+49-89-1234-5678", 9));
    testData.put(new ValueWithPos<String>("089/1234-5678", 7),
        new ValueWithPos<String>("+49-89-1234-5678", 10));
    testData.put(new ValueWithPos<String>("089/1234-5678", 8),
        new ValueWithPos<String>("+49-89-1234-5678", 12));
    testData.put(new ValueWithPos<String>("089/1234-5678", 9),
        new ValueWithPos<String>("+49-89-1234-5678", 12));
    testData.put(new ValueWithPos<String>("089/1234-5678", 10),
        new ValueWithPos<String>("+49-89-1234-5678", 13));
    testData.put(new ValueWithPos<String>("089/1234-5678", 11),
        new ValueWithPos<String>("+49-89-1234-5678", 14));
    testData.put(new ValueWithPos<String>("089/1234-5678", 12),
        new ValueWithPos<String>("+49-89-1234-5678", 15));
    testData.put(new ValueWithPos<String>("089/1234-5678", 13),
        new ValueWithPos<String>("+49-89-1234-5678", 16));
    testData.put(new ValueWithPos<String>("089/1234-5678", 14),
        new ValueWithPos<String>("+49-89-1234-5678", 16));

    testData.put(new ValueWithPos<String>("0043662/1234-5678", 0),
        new ValueWithPos<String>("+43-662-1234-5678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 1),
        new ValueWithPos<String>("+43-662-1234-5678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 2),
        new ValueWithPos<String>("+43-662-1234-5678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 3),
        new ValueWithPos<String>("+43-662-1234-5678", 2));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 4),
        new ValueWithPos<String>("+43-662-1234-5678", 4));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 5),
        new ValueWithPos<String>("+43-662-1234-5678", 5));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 6),
        new ValueWithPos<String>("+43-662-1234-5678", 6));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 7),
        new ValueWithPos<String>("+43-662-1234-5678", 8));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 8),
        new ValueWithPos<String>("+43-662-1234-5678", 8));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 9),
        new ValueWithPos<String>("+43-662-1234-5678", 9));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 10),
        new ValueWithPos<String>("+43-662-1234-5678", 10));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 11),
        new ValueWithPos<String>("+43-662-1234-5678", 11));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 12),
        new ValueWithPos<String>("+43-662-1234-5678", 13));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 13),
        new ValueWithPos<String>("+43-662-1234-5678", 13));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 14),
        new ValueWithPos<String>("+43-662-1234-5678", 14));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 15),
        new ValueWithPos<String>("+43-662-1234-5678", 15));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 16),
        new ValueWithPos<String>("+43-662-1234-5678", 16));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 17),
        new ValueWithPos<String>("+43-662-1234-5678", 17));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 18),
        new ValueWithPos<String>("+43-662-1234-5678", 17));
    return testData;
  }

  /**
   * get format common test cases with expected results.
   *
   * @return map of PhoneNumberInterface with expected format result strings
   */
  public static final Map<PhoneNumberInterface, String> getFormatCommonCases() {
    final Map<PhoneNumberInterface, String> testData = new HashMap<>();
    testData.put(new PhoneNumberData("49", "89", "1234", "5678"), "0 89 / 12 34 - 56 78");
    testData.put(new PhoneNumberData("49", "89", "12345678", null), "0 89 / 12 34 56 78");
    testData.put(new PhoneNumberData("43", "662", "1234", "5678"), "+43 (0)662 1234-5678");
    testData.put(new PhoneNumberData("43", "662", "12345678", null), "+43 (0)662 12345678");
    return testData;
  }

  /**
   * get format Common format test cases with expected results.
   *
   * @return map of PhoneNumberInterface with expected format result strings
   */
  public static Map<ValueWithPos<String>, ValueWithPos<String>> getFormatCommonWithPosCases() {
    final Map<ValueWithPos<String>, ValueWithPos<String>> testData = new HashMap<>();

    testData.put(new ValueWithPos<String>("089/1234-5678", 0),
        new ValueWithPos<String>("0 89 / 12 34 - 56 78", 0));
    testData.put(new ValueWithPos<String>("089/1234-5678", 1),
        new ValueWithPos<String>("0 89 / 12 34 - 56 78", 2));
    testData.put(new ValueWithPos<String>("089/1234-5678", 2),
        new ValueWithPos<String>("0 89 / 12 34 - 56 78", 3));
    testData.put(new ValueWithPos<String>("089/1234-5678", 3),
        new ValueWithPos<String>("0 89 / 12 34 - 56 78", 7));
    testData.put(new ValueWithPos<String>("089/1234-5678", 4),
        new ValueWithPos<String>("0 89 / 12 34 - 56 78", 7));
    testData.put(new ValueWithPos<String>("089/1234-5678", 5),
        new ValueWithPos<String>("0 89 / 12 34 - 56 78", 8));
    testData.put(new ValueWithPos<String>("089/1234-5678", 6),
        new ValueWithPos<String>("0 89 / 12 34 - 56 78", 10));
    testData.put(new ValueWithPos<String>("089/1234-5678", 7),
        new ValueWithPos<String>("0 89 / 12 34 - 56 78", 11));
    testData.put(new ValueWithPos<String>("089/1234-5678", 8),
        new ValueWithPos<String>("0 89 / 12 34 - 56 78", 15));
    testData.put(new ValueWithPos<String>("089/1234-5678", 9),
        new ValueWithPos<String>("0 89 / 12 34 - 56 78", 15));
    testData.put(new ValueWithPos<String>("089/1234-5678", 10),
        new ValueWithPos<String>("0 89 / 12 34 - 56 78", 16));
    testData.put(new ValueWithPos<String>("089/1234-5678", 11),
        new ValueWithPos<String>("0 89 / 12 34 - 56 78", 18));
    testData.put(new ValueWithPos<String>("089/1234-5678", 12),
        new ValueWithPos<String>("0 89 / 12 34 - 56 78", 19));
    testData.put(new ValueWithPos<String>("089/1234-5678", 13),
        new ValueWithPos<String>("0 89 / 12 34 - 56 78", 20));
    testData.put(new ValueWithPos<String>("089/1234-5678", 14),
        new ValueWithPos<String>("0 89 / 12 34 - 56 78", 20));

    testData.put(new ValueWithPos<String>("0043662/1234-5678", 0),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 1),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 2),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 3),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 2));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 4),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 7));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 5),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 8));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 6),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 9));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 7),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 11));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 8),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 11));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 9),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 12));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 10),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 13));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 11),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 14));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 12),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 16));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 13),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 16));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 14),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 17));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 15),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 18));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 16),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 19));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 17),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 20));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 18),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 20));
    return testData;
  }

  /**
   * get format common international test cases with expected results.
   *
   * @return map of PhoneNumberInterface with expected format result strings
   */
  public static final Map<PhoneNumberInterface, String> getFormatCommonInternationalCases() {
    final Map<PhoneNumberInterface, String> testData = new HashMap<>();
    testData.put(new PhoneNumberData("49", "89", "1234", "5678"), "+49 (0)89 1234-5678");
    testData.put(new PhoneNumberData("49", "89", "12345678", null), "+49 (0)89 12345678");
    testData.put(new PhoneNumberData("43", "662", "1234", "5678"), "+43 (0)662 1234-5678");
    testData.put(new PhoneNumberData("43", "662", "12345678", null), "+43 (0)662 12345678");
    return testData;
  }

  /**
   * get format Common international format test cases with expected results.
   *
   * @return map of PhoneNumberInterface with expected format result strings
   */
  public static Map<ValueWithPos<String>, ValueWithPos<String>> getFormatCommonInterPosCases() {
    final Map<ValueWithPos<String>, ValueWithPos<String>> testData = new HashMap<>();

    testData.put(new ValueWithPos<String>("089/1234-5678", 0),
        new ValueWithPos<String>("+49 (0)89 1234-5678", 1));
    testData.put(new ValueWithPos<String>("089/1234-5678", 1),
        new ValueWithPos<String>("+49 (0)89 1234-5678", 7));
    testData.put(new ValueWithPos<String>("089/1234-5678", 2),
        new ValueWithPos<String>("+49 (0)89 1234-5678", 8));
    testData.put(new ValueWithPos<String>("089/1234-5678", 3),
        new ValueWithPos<String>("+49 (0)89 1234-5678", 10));
    testData.put(new ValueWithPos<String>("089/1234-5678", 4),
        new ValueWithPos<String>("+49 (0)89 1234-5678", 10));
    testData.put(new ValueWithPos<String>("089/1234-5678", 5),
        new ValueWithPos<String>("+49 (0)89 1234-5678", 11));
    testData.put(new ValueWithPos<String>("089/1234-5678", 6),
        new ValueWithPos<String>("+49 (0)89 1234-5678", 12));
    testData.put(new ValueWithPos<String>("089/1234-5678", 7),
        new ValueWithPos<String>("+49 (0)89 1234-5678", 13));
    testData.put(new ValueWithPos<String>("089/1234-5678", 8),
        new ValueWithPos<String>("+49 (0)89 1234-5678", 15));
    testData.put(new ValueWithPos<String>("089/1234-5678", 9),
        new ValueWithPos<String>("+49 (0)89 1234-5678", 15));
    testData.put(new ValueWithPos<String>("089/1234-5678", 10),
        new ValueWithPos<String>("+49 (0)89 1234-5678", 16));
    testData.put(new ValueWithPos<String>("089/1234-5678", 11),
        new ValueWithPos<String>("+49 (0)89 1234-5678", 17));
    testData.put(new ValueWithPos<String>("089/1234-5678", 12),
        new ValueWithPos<String>("+49 (0)89 1234-5678", 18));
    testData.put(new ValueWithPos<String>("089/1234-5678", 13),
        new ValueWithPos<String>("+49 (0)89 1234-5678", 19));
    testData.put(new ValueWithPos<String>("089/1234-5678", 14),
        new ValueWithPos<String>("+49 (0)89 1234-5678", 19));

    testData.put(new ValueWithPos<String>("0043662/1234-5678", 0),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 1),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 2),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 1));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 3),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 2));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 4),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 7));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 5),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 8));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 6),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 9));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 7),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 11));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 8),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 11));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 9),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 12));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 10),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 13));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 11),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 14));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 12),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 16));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 13),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 16));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 14),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 17));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 15),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 18));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 16),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 19));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 17),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 20));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 18),
        new ValueWithPos<String>("+43 (0)662 1234-5678", 20));
    return testData;
  }

  /**
   * get format common national test cases with expected results.
   *
   * @return map of PhoneNumberInterface with expected format result strings
   */
  public static final Map<PhoneNumberInterface, String> getFormatCommonNationalCases() {
    final Map<PhoneNumberInterface, String> testData = new HashMap<>();
    testData.put(new PhoneNumberData("49", "89", "1234", "5678"), "0 89 / 12 34 - 56 78");
    testData.put(new PhoneNumberData("49", "89", "12345678", null), "0 89 / 12 34 56 78");
    testData.put(new PhoneNumberData("43", "662", "1234", "5678"), "0 66 2 / 12 34 - 56 78");
    testData.put(new PhoneNumberData("43", "662", "12345678", null), "0 66 2 / 12 34 56 78");
    return testData;
  }

  /**
   * get format Common national format test cases with expected results.
   *
   * @return map of PhoneNumberInterface with expected format result strings
   */
  public static Map<ValueWithPos<String>, ValueWithPos<String>> getFormatCommonNationalPosCases() {
    final Map<ValueWithPos<String>, ValueWithPos<String>> testData = new HashMap<>();

    testData.put(new ValueWithPos<String>("089/1234-5678", 0),
        new ValueWithPos<String>("0 89 / 12 34 - 56 78", 0));
    testData.put(new ValueWithPos<String>("089/1234-5678", 1),
        new ValueWithPos<String>("0 89 / 12 34 - 56 78", 2));
    testData.put(new ValueWithPos<String>("089/1234-5678", 2),
        new ValueWithPos<String>("0 89 / 12 34 - 56 78", 3));
    testData.put(new ValueWithPos<String>("089/1234-5678", 3),
        new ValueWithPos<String>("0 89 / 12 34 - 56 78", 7));
    testData.put(new ValueWithPos<String>("089/1234-5678", 4),
        new ValueWithPos<String>("0 89 / 12 34 - 56 78", 7));
    testData.put(new ValueWithPos<String>("089/1234-5678", 5),
        new ValueWithPos<String>("0 89 / 12 34 - 56 78", 8));
    testData.put(new ValueWithPos<String>("089/1234-5678", 6),
        new ValueWithPos<String>("0 89 / 12 34 - 56 78", 10));
    testData.put(new ValueWithPos<String>("089/1234-5678", 7),
        new ValueWithPos<String>("0 89 / 12 34 - 56 78", 11));
    testData.put(new ValueWithPos<String>("089/1234-5678", 8),
        new ValueWithPos<String>("0 89 / 12 34 - 56 78", 15));
    testData.put(new ValueWithPos<String>("089/1234-5678", 9),
        new ValueWithPos<String>("0 89 / 12 34 - 56 78", 15));
    testData.put(new ValueWithPos<String>("089/1234-5678", 10),
        new ValueWithPos<String>("0 89 / 12 34 - 56 78", 16));
    testData.put(new ValueWithPos<String>("089/1234-5678", 11),
        new ValueWithPos<String>("0 89 / 12 34 - 56 78", 18));
    testData.put(new ValueWithPos<String>("089/1234-5678", 12),
        new ValueWithPos<String>("0 89 / 12 34 - 56 78", 19));
    testData.put(new ValueWithPos<String>("089/1234-5678", 13),
        new ValueWithPos<String>("0 89 / 12 34 - 56 78", 20));
    testData.put(new ValueWithPos<String>("089/1234-5678", 14),
        new ValueWithPos<String>("0 89 / 12 34 - 56 78", 20));

    testData.put(new ValueWithPos<String>("0043662/1234-5678", 0),
        new ValueWithPos<String>("0 66 2 / 12 34 - 56 78", 0));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 1),
        new ValueWithPos<String>("0 66 2 / 12 34 - 56 78", 0));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 2),
        new ValueWithPos<String>("0 66 2 / 12 34 - 56 78", 0));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 3),
        new ValueWithPos<String>("0 66 2 / 12 34 - 56 78", 0));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 4),
        new ValueWithPos<String>("0 66 2 / 12 34 - 56 78", 2));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 5),
        new ValueWithPos<String>("0 66 2 / 12 34 - 56 78", 3));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 6),
        new ValueWithPos<String>("0 66 2 / 12 34 - 56 78", 5));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 7),
        new ValueWithPos<String>("0 66 2 / 12 34 - 56 78", 9));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 8),
        new ValueWithPos<String>("0 66 2 / 12 34 - 56 78", 9));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 9),
        new ValueWithPos<String>("0 66 2 / 12 34 - 56 78", 10));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 10),
        new ValueWithPos<String>("0 66 2 / 12 34 - 56 78", 12));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 11),
        new ValueWithPos<String>("0 66 2 / 12 34 - 56 78", 13));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 12),
        new ValueWithPos<String>("0 66 2 / 12 34 - 56 78", 17));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 13),
        new ValueWithPos<String>("0 66 2 / 12 34 - 56 78", 17));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 14),
        new ValueWithPos<String>("0 66 2 / 12 34 - 56 78", 18));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 15),
        new ValueWithPos<String>("0 66 2 / 12 34 - 56 78", 20));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 16),
        new ValueWithPos<String>("0 66 2 / 12 34 - 56 78", 21));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 17),
        new ValueWithPos<String>("0 66 2 / 12 34 - 56 78", 22));
    testData.put(new ValueWithPos<String>("0043662/1234-5678", 18),
        new ValueWithPos<String>("0 66 2 / 12 34 - 56 78", 22));
    return testData;
  }
}
