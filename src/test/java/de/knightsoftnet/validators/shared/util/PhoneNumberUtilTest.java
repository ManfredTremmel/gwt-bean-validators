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
import de.knightsoftnet.validators.shared.testcases.PhoneNumberUtilTestCases;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map.Entry;

public class PhoneNumberUtilTest {

  private PhoneNumberUtil phoneNumberUtil;

  /**
   * set up for testing.
   */
  @Before
  public void setUp() {
    this.phoneNumberUtil = new PhoneNumberUtil("DE");
  }

  /**
   * test parsing of phone number.
   */
  @Test
  public void testParsing() {
    for (final Entry<String, PhoneNumberData> testCase : PhoneNumberUtilTestCases.getParseCases()
        .entrySet()) {
      final PhoneNumberData parsedNumber = this.phoneNumberUtil.parsePhoneNumber(testCase.getKey());
      Assert.assertEquals("Parsing " + testCase.getKey(), testCase.getValue(), parsedNumber);
    }
  }

  /**
   * test formating phone number in E123 format.
   */
  @Test
  public void testFormatE123() {
    for (final Entry<PhoneNumberData, String> testCase : PhoneNumberUtilTestCases
        .getFormatE123Cases().entrySet()) {
      final String formatedNumber = this.phoneNumberUtil.formatE123(testCase.getKey());
      Assert.assertEquals("Formating E123 " + testCase.getKey().toString(), testCase.getValue(),
          formatedNumber);
    }
  }

  /**
   * test formating phone number in E123 international format.
   */
  @Test
  public void testFormatE123International() {
    for (final Entry<PhoneNumberData, String> testCase : PhoneNumberUtilTestCases
        .getFormatE123InternationalCases().entrySet()) {
      final String formatedNumber = this.phoneNumberUtil.formatE123International(testCase.getKey());
      Assert.assertEquals("Formating E123 international " + testCase.getKey().toString(),
          testCase.getValue(), formatedNumber);
    }
  }

  /**
   * test formating phone number in E123 national format.
   */
  @Test
  public void testFormatE123National() {
    for (final Entry<PhoneNumberData, String> testCase : PhoneNumberUtilTestCases
        .getFormatE123NationalCases().entrySet()) {
      final String formatedNumber = this.phoneNumberUtil.formatE123National(testCase.getKey());
      Assert.assertEquals("Formating E123 national " + testCase.getKey().toString(),
          testCase.getValue(), formatedNumber);
    }
  }

  /**
   * test formating phone number in DIN 5008 format.
   */
  @Test
  public void testFormatDin5008() {
    for (final Entry<PhoneNumberData, String> testCase : PhoneNumberUtilTestCases
        .getFormatDin5008Cases().entrySet()) {
      final String formatedNumber = this.phoneNumberUtil.formatDin5008(testCase.getKey());
      Assert.assertEquals("Formating DIN 5008 " + testCase.getKey().toString(), testCase.getValue(),
          formatedNumber);
    }
  }

  /**
   * test formating phone number in DIN 5008 international format.
   */
  @Test
  public void testFormatDin5008International() {
    for (final Entry<PhoneNumberData, String> testCase : PhoneNumberUtilTestCases
        .getFormatDin5008InternationalCases().entrySet()) {
      final String formatedNumber =
          this.phoneNumberUtil.formatDin5008International(testCase.getKey());
      Assert.assertEquals("Formating DIN 5008 international " + testCase.getKey().toString(),
          testCase.getValue(), formatedNumber);
    }
  }

  /**
   * test formating phone number in DIN 5008 national format.
   */
  @Test
  public void testFormatDin5008National() {
    for (final Entry<PhoneNumberData, String> testCase : PhoneNumberUtilTestCases
        .getFormatDin5008NationalCases().entrySet()) {
      final String formatedNumber = this.phoneNumberUtil.formatDin5008National(testCase.getKey());
      Assert.assertEquals("Formating DIN 5008 national " + testCase.getKey().toString(),
          testCase.getValue(), formatedNumber);
    }
  }

  /**
   * test formating phone number in RFC 3966 format.
   */
  @Test
  public void testFormatRfc3966() {
    for (final Entry<PhoneNumberData, String> testCase : PhoneNumberUtilTestCases
        .getFormatRfc3966Cases().entrySet()) {
      final String formatedNumber = this.phoneNumberUtil.formatRfc3966(testCase.getKey());
      Assert.assertEquals("Formating RFC 3966 " + testCase.getKey().toString(), testCase.getValue(),
          formatedNumber);
    }
  }

  /**
   * test formating phone number in Microsoft canonical address format.
   */
  @Test
  public void testFormatMs() {
    for (final Entry<PhoneNumberData, String> testCase : PhoneNumberUtilTestCases.getFormatMsCases()
        .entrySet()) {
      final String formatedNumber = this.phoneNumberUtil.formatMs(testCase.getKey());
      Assert.assertEquals("Formating MS " + testCase.getKey().toString(), testCase.getValue(),
          formatedNumber);
    }
  }

  /**
   * test formating phone number in Url format.
   */
  @Test
  public void testFormatUrl() {
    for (final Entry<PhoneNumberData, String> testCase : PhoneNumberUtilTestCases
        .getFormatUrlCases().entrySet()) {
      final String formatedNumber = this.phoneNumberUtil.formatUrl(testCase.getKey());
      Assert.assertEquals("Formating URL " + testCase.getKey().toString(), testCase.getValue(),
          formatedNumber);
    }
  }
}
