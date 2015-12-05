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
import de.knightsoftnet.validators.shared.data.PhoneNumberInterface;
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
    assertNull("Should be null", this.phoneNumberUtil.parsePhoneNumber(null));
    for (final Entry<String, PhoneNumberInterface> testCase : PhoneNumberUtilTestCases
        .getParseCases().entrySet()) {
      final PhoneNumberInterface parsedNumber =
          this.phoneNumberUtil.parsePhoneNumber(testCase.getKey());
      assertEquals("Parsing " + testCase.getKey(), testCase.getValue(), parsedNumber);
    }
  }

  /**
   * test formating phone number in E123 format.
   */
  public void testFormatE123() {
    assertNull("Should be null", this.phoneNumberUtil.formatE123((PhoneNumberData) null));
    for (final Entry<PhoneNumberInterface, String> testCase : PhoneNumberUtilTestCases
        .getFormatE123Cases().entrySet()) {
      final String formatedNumber = this.phoneNumberUtil.formatE123(testCase.getKey());
      assertEquals("Formating E123 " + testCase.getKey().toString(), testCase.getValue(),
          formatedNumber);
    }
  }

  /**
   * test formating phone number in E123 international format.
   */
  public void testFormatE123International() {
    assertNull("Should be null",
        this.phoneNumberUtil.formatE123International((PhoneNumberData) null));
    for (final Entry<PhoneNumberInterface, String> testCase : PhoneNumberUtilTestCases
        .getFormatE123InternationalCases().entrySet()) {
      final String formatedNumber = this.phoneNumberUtil.formatE123International(testCase.getKey());
      assertEquals("Formating E123 international " + testCase.getKey().toString(),
          testCase.getValue(), formatedNumber);
    }
  }

  /**
   * test formating phone number in E123 national format.
   */
  public void testFormatE123National() {
    assertNull("Should be null", this.phoneNumberUtil.formatE123National((PhoneNumberData) null));
    for (final Entry<PhoneNumberInterface, String> testCase : PhoneNumberUtilTestCases
        .getFormatE123NationalCases().entrySet()) {
      final String formatedNumber = this.phoneNumberUtil.formatE123National(testCase.getKey());
      assertEquals("Formating E123 national " + testCase.getKey().toString(), testCase.getValue(),
          formatedNumber);
    }
  }

  /**
   * test formating phone number in DIN 5008 format.
   */
  public void testFormatDin5008() {
    assertNull("Should be null", this.phoneNumberUtil.formatDin5008((PhoneNumberData) null));
    for (final Entry<PhoneNumberInterface, String> testCase : PhoneNumberUtilTestCases
        .getFormatDin5008Cases().entrySet()) {
      final String formatedNumber = this.phoneNumberUtil.formatDin5008(testCase.getKey());
      assertEquals("Formating DIN 5008 " + testCase.getKey().toString(), testCase.getValue(),
          formatedNumber);
    }
  }

  /**
   * test formating phone number in DIN 5008 international format.
   */
  public void testFormatDin5008International() {
    assertNull("Should be null",
        this.phoneNumberUtil.formatDin5008International((PhoneNumberData) null));
    for (final Entry<PhoneNumberInterface, String> testCase : PhoneNumberUtilTestCases
        .getFormatDin5008InternationalCases().entrySet()) {
      final String formatedNumber =
          this.phoneNumberUtil.formatDin5008International(testCase.getKey());
      assertEquals("Formating DIN 5008 international " + testCase.getKey().toString(),
          testCase.getValue(), formatedNumber);
    }
  }

  /**
   * test formating phone number in DIN 5008 national format.
   */
  public void testFormatDin5008National() {
    assertNull("Should be null",
        this.phoneNumberUtil.formatDin5008National((PhoneNumberData) null));
    for (final Entry<PhoneNumberInterface, String> testCase : PhoneNumberUtilTestCases
        .getFormatDin5008NationalCases().entrySet()) {
      final String formatedNumber = this.phoneNumberUtil.formatDin5008National(testCase.getKey());
      assertEquals("Formating DIN 5008 national " + testCase.getKey().toString(),
          testCase.getValue(), formatedNumber);
    }
  }

  /**
   * test formating phone number in RFC 3966 format.
   */
  public void testFormatRfc3966() {
    assertNull("Should be null", this.phoneNumberUtil.formatRfc3966((PhoneNumberData) null));
    for (final Entry<PhoneNumberInterface, String> testCase : PhoneNumberUtilTestCases
        .getFormatRfc3966Cases().entrySet()) {
      final String formatedNumber = this.phoneNumberUtil.formatRfc3966(testCase.getKey());
      assertEquals("Formating RFC 3966 " + testCase.getKey().toString(), testCase.getValue(),
          formatedNumber);
    }
  }

  /**
   * test formating phone number in Microsoft canonical address format.
   */
  public void testFormatMs() {
    assertNull("Should be null", this.phoneNumberUtil.formatMs((PhoneNumberData) null));
    for (final Entry<PhoneNumberInterface, String> testCase : PhoneNumberUtilTestCases
        .getFormatMsCases().entrySet()) {
      final String formatedNumber = this.phoneNumberUtil.formatMs(testCase.getKey());
      assertEquals("Formating MS " + testCase.getKey().toString(), testCase.getValue(),
          formatedNumber);
    }
  }

  /**
   * test formating phone number in Url format.
   */
  public void testFormatUrl() {
    assertNull("Should be null", this.phoneNumberUtil.formatUrl((PhoneNumberData) null));
    for (final Entry<PhoneNumberInterface, String> testCase : PhoneNumberUtilTestCases
        .getFormatUrlCases().entrySet()) {
      final String formatedNumber = this.phoneNumberUtil.formatUrl(testCase.getKey());
      assertEquals("Formating URL " + testCase.getKey().toString(), testCase.getValue(),
          formatedNumber);
    }
  }

  /**
   * test formating phone number in common format.
   */
  public void testFormatCommon() {
    assertNull("Should be null", this.phoneNumberUtil.formatCommon((PhoneNumberData) null));
    for (final Entry<PhoneNumberInterface, String> testCase : PhoneNumberUtilTestCases
        .getFormatCommonCases().entrySet()) {
      final String formatedNumber = this.phoneNumberUtil.formatCommon(testCase.getKey());
      assertEquals("Formating common " + testCase.getKey().toString(), testCase.getValue(),
          formatedNumber);
    }
  }

  /**
   * test formating phone number in common international format.
   */
  public void testFormatCommonInternational() {
    assertNull("Should be null",
        this.phoneNumberUtil.formatCommonInternational((PhoneNumberData) null));
    for (final Entry<PhoneNumberInterface, String> testCase : PhoneNumberUtilTestCases
        .getFormatCommonInternationalCases().entrySet()) {
      final String formatedNumber =
          this.phoneNumberUtil.formatCommonInternational(testCase.getKey());
      assertEquals("Formating common international " + testCase.getKey().toString(),
          testCase.getValue(), formatedNumber);
    }
  }

  /**
   * test formating phone number in common national format.
   */
  public void testFormatCommonNational() {
    assertNull("Should be null", this.phoneNumberUtil.formatCommonNational((PhoneNumberData) null));
    for (final Entry<PhoneNumberInterface, String> testCase : PhoneNumberUtilTestCases
        .getFormatCommonNationalCases().entrySet()) {
      final String formatedNumber = this.phoneNumberUtil.formatCommonNational(testCase.getKey());
      assertEquals("Formating common national " + testCase.getKey().toString(), testCase.getValue(),
          formatedNumber);
    }
  }
}
