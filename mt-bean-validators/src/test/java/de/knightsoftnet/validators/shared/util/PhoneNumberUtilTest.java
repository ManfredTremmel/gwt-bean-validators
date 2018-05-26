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
import de.knightsoftnet.validators.shared.data.PhoneNumberInterface;
import de.knightsoftnet.validators.shared.data.ValueWithPos;
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
    Assert.assertNull("Should be null", this.phoneNumberUtil.parsePhoneNumber((String) null));
    for (final Entry<String, PhoneNumberInterface> testCase : PhoneNumberUtilTestCases
        .getParseCases().entrySet()) {
      final PhoneNumberInterface parsedNumber =
          this.phoneNumberUtil.parsePhoneNumber(testCase.getKey());
      Assert.assertEquals("Parsing " + testCase.getKey(), testCase.getValue(), parsedNumber);
    }
  }

  /**
   * test formating phone number in E123 format.
   */
  @Test
  public void testFormatE123() {
    Assert.assertNull("Should be null", this.phoneNumberUtil.formatE123((PhoneNumberData) null));
    for (final Entry<PhoneNumberInterface, String> testCase : PhoneNumberUtilTestCases
        .getFormatE123Cases().entrySet()) {
      final String formatedNumber = this.phoneNumberUtil.formatE123(testCase.getKey());
      Assert.assertEquals("Formating E123 " + testCase.getKey().toString(), testCase.getValue(),
          formatedNumber);
    }
  }

  /**
   * test formating phone number in E123 format with cursor position.
   */
  @Test
  public void testFormatE123WithPos() {
    Assert.assertNull("Should be null",
        this.phoneNumberUtil.formatE123((ValueWithPos<String>) null));
    for (final Entry<ValueWithPos<String>, ValueWithPos<String>> testCase : PhoneNumberUtilTestCases
        .getFormatE123WithPosCases().entrySet()) {
      final ValueWithPos<String> formatedNumber =
          this.phoneNumberUtil.formatE123(testCase.getKey());
      Assert.assertEquals("Formating E123 " + testCase.getKey().toString(), testCase.getValue(),
          formatedNumber);
    }
  }

  /**
   * test formating phone number in E123 international format.
   */
  @Test
  public void testFormatE123International() {
    Assert.assertNull("Should be null",
        this.phoneNumberUtil.formatE123International((PhoneNumberData) null));
    for (final Entry<PhoneNumberInterface, String> testCase : PhoneNumberUtilTestCases
        .getFormatE123InternationalCases().entrySet()) {
      final String formatedNumber = this.phoneNumberUtil.formatE123International(testCase.getKey());
      Assert.assertEquals("Formating E123 international " + testCase.getKey().toString(),
          testCase.getValue(), formatedNumber);
    }
  }

  /**
   * test formating phone number in E123 international format with cursor position.
   */
  @Test
  public void testFormatE123InternationalWithPos() {
    Assert.assertNull("Should be null",
        this.phoneNumberUtil.formatE123International((ValueWithPos<String>) null));
    for (final Entry<ValueWithPos<String>, ValueWithPos<String>> testCase : PhoneNumberUtilTestCases
        .getFormatE123InternationalpCases().entrySet()) {
      final ValueWithPos<String> formatedNumber =
          this.phoneNumberUtil.formatE123International(testCase.getKey());
      Assert.assertEquals("Formating E123 international " + testCase.getKey().toString(),
          testCase.getValue(), formatedNumber);
    }
  }

  /**
   * test formating phone number in E123 national format.
   */
  @Test
  public void testFormatE123National() {
    Assert.assertNull("Should be null",
        this.phoneNumberUtil.formatE123National((PhoneNumberData) null));
    for (final Entry<PhoneNumberInterface, String> testCase : PhoneNumberUtilTestCases
        .getFormatE123NationalCases().entrySet()) {
      final String formatedNumber = this.phoneNumberUtil.formatE123National(testCase.getKey());
      Assert.assertEquals("Formating E123 national " + testCase.getKey().toString(),
          testCase.getValue(), formatedNumber);
    }
  }

  /**
   * test formating phone number in E123 national format with cursor position.
   */
  @Test
  public void testFormatE123NationalWithPos() {
    Assert.assertNull("Should be null",
        this.phoneNumberUtil.formatE123National((ValueWithPos<String>) null));
    for (final Entry<ValueWithPos<String>, ValueWithPos<String>> testCase : PhoneNumberUtilTestCases
        .getFormatE123NationalPosCases().entrySet()) {
      final ValueWithPos<String> formatedNumber =
          this.phoneNumberUtil.formatE123National(testCase.getKey());
      Assert.assertEquals("Formating E123 national " + testCase.getKey().toString(),
          testCase.getValue(), formatedNumber);
    }
  }

  /**
   * test formating phone number in DIN 5008 format.
   */
  @Test
  public void testFormatDin5008() {
    Assert.assertNull("Should be null", this.phoneNumberUtil.formatDin5008((PhoneNumberData) null));
    for (final Entry<PhoneNumberInterface, String> testCase : PhoneNumberUtilTestCases
        .getFormatDin5008Cases().entrySet()) {
      final String formatedNumber = this.phoneNumberUtil.formatDin5008(testCase.getKey());
      Assert.assertEquals("Formating DIN 5008 " + testCase.getKey().toString(), testCase.getValue(),
          formatedNumber);
    }
  }

  /**
   * test formating phone number in DIN 5008 format with cursor position.
   */
  @Test
  public void testFormatDin5008WithPos() {
    Assert.assertNull("Should be null",
        this.phoneNumberUtil.formatDin5008((ValueWithPos<String>) null));
    for (final Entry<ValueWithPos<String>, ValueWithPos<String>> testCase : PhoneNumberUtilTestCases
        .getFormatDin5008WithPosCases().entrySet()) {
      final ValueWithPos<String> formatedNumber =
          this.phoneNumberUtil.formatDin5008(testCase.getKey());
      Assert.assertEquals("Formating DIN 5008 " + testCase.getKey().toString(), testCase.getValue(),
          formatedNumber);
    }
  }

  /**
   * test formating phone number in DIN 5008 international format.
   */
  @Test
  public void testFormatDin5008International() {
    Assert.assertNull("Should be null",
        this.phoneNumberUtil.formatDin5008International((PhoneNumberData) null));
    for (final Entry<PhoneNumberInterface, String> testCase : PhoneNumberUtilTestCases
        .getFormatDin5008InternationalCases().entrySet()) {
      final String formatedNumber =
          this.phoneNumberUtil.formatDin5008International(testCase.getKey());
      Assert.assertEquals("Formating DIN 5008 international " + testCase.getKey().toString(),
          testCase.getValue(), formatedNumber);
    }
  }

  /**
   * test formating phone number in DIN 5008 international format with cursor position.
   */
  @Test
  public void testFormatDin5008InternationalWithPos() {
    Assert.assertNull("Should be null",
        this.phoneNumberUtil.formatDin5008International((ValueWithPos<String>) null));
    for (final Entry<ValueWithPos<String>, ValueWithPos<String>> testCase : PhoneNumberUtilTestCases
        .getFormatDin5008InternPosCases().entrySet()) {
      final ValueWithPos<String> formatedNumber =
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
    Assert.assertNull("Should be null",
        this.phoneNumberUtil.formatDin5008National((PhoneNumberData) null));
    for (final Entry<PhoneNumberInterface, String> testCase : PhoneNumberUtilTestCases
        .getFormatDin5008NationalCases().entrySet()) {
      final String formatedNumber = this.phoneNumberUtil.formatDin5008National(testCase.getKey());
      Assert.assertEquals("Formating DIN 5008 national " + testCase.getKey().toString(),
          testCase.getValue(), formatedNumber);
    }
  }

  /**
   * test formating phone number in DIN 5008 national format with cursor position.
   */
  @Test
  public void testFormatDin5008NationalWithPos() {
    Assert.assertNull("Should be null",
        this.phoneNumberUtil.formatDin5008National((ValueWithPos<String>) null));
    for (final Entry<ValueWithPos<String>, ValueWithPos<String>> testCase : PhoneNumberUtilTestCases
        .getFormatDin5008NationalPosCases().entrySet()) {
      final ValueWithPos<String> formatedNumber =
          this.phoneNumberUtil.formatDin5008National(testCase.getKey());
      Assert.assertEquals("Formating DIN 5008 national " + testCase.getKey().toString(),
          testCase.getValue(), formatedNumber);
    }
  }

  /**
   * test formating phone number in RFC 3966 format.
   */
  @Test
  public void testFormatRfc3966() {
    Assert.assertNull("Should be null", this.phoneNumberUtil.formatRfc3966((PhoneNumberData) null));
    for (final Entry<PhoneNumberInterface, String> testCase : PhoneNumberUtilTestCases
        .getFormatRfc3966Cases().entrySet()) {
      final String formatedNumber = this.phoneNumberUtil.formatRfc3966(testCase.getKey());
      Assert.assertEquals("Formating RFC 3966 " + testCase.getKey().toString(), testCase.getValue(),
          formatedNumber);
    }
  }

  /**
   * test formating phone number in RFC 3966 format with cursor position.
   */
  @Test
  public void testFormatRfc3966WithPos() {
    Assert.assertNull("Should be null",
        this.phoneNumberUtil.formatRfc3966((ValueWithPos<String>) null));
    for (final Entry<ValueWithPos<String>, ValueWithPos<String>> testCase : PhoneNumberUtilTestCases
        .getFormatRfc3966WithPosCases().entrySet()) {
      final ValueWithPos<String> formatedNumber =
          this.phoneNumberUtil.formatRfc3966(testCase.getKey());
      Assert.assertEquals("Formating Rfc3966 " + testCase.getKey().toString(), testCase.getValue(),
          formatedNumber);
    }
  }

  /**
   * test formating phone number in Microsoft canonical address format.
   */
  @Test
  public void testFormatMs() {
    Assert.assertNull("Should be null", this.phoneNumberUtil.formatMs((PhoneNumberData) null));
    for (final Entry<PhoneNumberInterface, String> testCase : PhoneNumberUtilTestCases
        .getFormatMsCases().entrySet()) {
      final String formatedNumber = this.phoneNumberUtil.formatMs(testCase.getKey());
      Assert.assertEquals("Formating MS " + testCase.getKey().toString(), testCase.getValue(),
          formatedNumber);
    }
  }

  /**
   * test formating phone number in Microsoft canonical address format.
   */
  @Test
  public void testFormatMsWithPos() {
    Assert.assertNull("Should be null", this.phoneNumberUtil.formatMs((ValueWithPos<String>) null));
    for (final Entry<ValueWithPos<String>, ValueWithPos<String>> testCase : PhoneNumberUtilTestCases
        .getFormatMsWithLengthCases().entrySet()) {
      final ValueWithPos<String> formatedNumber = this.phoneNumberUtil.formatMs(testCase.getKey());
      Assert.assertEquals("Formating MS " + testCase.getKey().toString(), testCase.getValue(),
          formatedNumber);
    }
  }

  /**
   * test formating phone number in Url format.
   */
  @Test
  public void testFormatUrl() {
    Assert.assertNull("Should be null", this.phoneNumberUtil.formatUrl((PhoneNumberData) null));
    for (final Entry<PhoneNumberInterface, String> testCase : PhoneNumberUtilTestCases
        .getFormatUrlCases().entrySet()) {
      final String formatedNumber = this.phoneNumberUtil.formatUrl(testCase.getKey());
      Assert.assertEquals("Formating URL " + testCase.getKey().toString(), testCase.getValue(),
          formatedNumber);
    }
  }

  /**
   * test formating phone number in URL format with cursor position.
   */
  @Test
  public void testFormatUrlWithPos() {
    Assert.assertNull("Should be null",
        this.phoneNumberUtil.formatUrl((ValueWithPos<String>) null));
    for (final Entry<ValueWithPos<String>, ValueWithPos<String>> testCase : PhoneNumberUtilTestCases
        .getFormatUrlWithPosCases().entrySet()) {
      final ValueWithPos<String> formatedNumber = this.phoneNumberUtil.formatUrl(testCase.getKey());
      Assert.assertEquals("Formating Url " + testCase.getKey().toString(), testCase.getValue(),
          formatedNumber);
    }
  }

  /**
   * test formating phone number in common format.
   */
  @Test
  public void testFormatCommon() {
    Assert.assertNull("Should be null", this.phoneNumberUtil.formatCommon((PhoneNumberData) null));
    for (final Entry<PhoneNumberInterface, String> testCase : PhoneNumberUtilTestCases
        .getFormatCommonCases().entrySet()) {
      final String formatedNumber = this.phoneNumberUtil.formatCommon(testCase.getKey());
      Assert.assertEquals("Formating common " + testCase.getKey().toString(), testCase.getValue(),
          formatedNumber);
    }
  }

  /**
   * test formating phone number in common format with cursor position.
   */
  @Test
  public void testFormatCommonWithPos() {
    Assert.assertNull("Should be null",
        this.phoneNumberUtil.formatCommon((ValueWithPos<String>) null));
    for (final Entry<ValueWithPos<String>, ValueWithPos<String>> testCase : PhoneNumberUtilTestCases
        .getFormatCommonWithPosCases().entrySet()) {
      final ValueWithPos<String> formatedNumber =
          this.phoneNumberUtil.formatCommon(testCase.getKey());
      Assert.assertEquals("Formating Common " + testCase.getKey().toString(), testCase.getValue(),
          formatedNumber);
    }
  }

  /**
   * test formating phone number in common international format.
   */
  @Test
  public void testFormatCommonInternational() {
    Assert.assertNull("Should be null",
        this.phoneNumberUtil.formatCommonInternational((PhoneNumberData) null));
    for (final Entry<PhoneNumberInterface, String> testCase : PhoneNumberUtilTestCases
        .getFormatCommonInternationalCases().entrySet()) {
      final String formatedNumber =
          this.phoneNumberUtil.formatCommonInternational(testCase.getKey());
      Assert.assertEquals("Formating common international " + testCase.getKey().toString(),
          testCase.getValue(), formatedNumber);
    }
  }

  /**
   * test formating phone number in common international format with cursor position.
   */
  @Test
  public void testFormatCommonInternationalWithPos() {
    Assert.assertNull("Should be null",
        this.phoneNumberUtil.formatCommonInternational((ValueWithPos<String>) null));
    for (final Entry<ValueWithPos<String>, ValueWithPos<String>> testCase : PhoneNumberUtilTestCases
        .getFormatCommonInterPosCases().entrySet()) {
      final ValueWithPos<String> formatedNumber =
          this.phoneNumberUtil.formatCommonInternational(testCase.getKey());
      Assert.assertEquals("Formating Common international " + testCase.getKey().toString(),
          testCase.getValue(), formatedNumber);
    }
  }

  /**
   * test formating phone number in common national format.
   */
  @Test
  public void testFormatCommonNational() {
    Assert.assertNull("Should be null",
        this.phoneNumberUtil.formatCommonNational((PhoneNumberData) null));
    for (final Entry<PhoneNumberInterface, String> testCase : PhoneNumberUtilTestCases
        .getFormatCommonNationalCases().entrySet()) {
      final String formatedNumber = this.phoneNumberUtil.formatCommonNational(testCase.getKey());
      Assert.assertEquals("Formating common national " + testCase.getKey().toString(),
          testCase.getValue(), formatedNumber);
    }
  }

  /**
   * test formating phone number in common national format with cursor position.
   */
  @Test
  public void testFormatCommonNationalWithPos() {
    Assert.assertNull("Should be null",
        this.phoneNumberUtil.formatCommonNational((ValueWithPos<String>) null));
    for (final Entry<ValueWithPos<String>, ValueWithPos<String>> testCase : PhoneNumberUtilTestCases
        .getFormatCommonNationalPosCases().entrySet()) {
      final ValueWithPos<String> formatedNumber =
          this.phoneNumberUtil.formatCommonNational(testCase.getKey());
      Assert.assertEquals("Formating Common national " + testCase.getKey().toString(),
          testCase.getValue(), formatedNumber);
    }
  }
}
