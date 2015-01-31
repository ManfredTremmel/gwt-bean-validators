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

package de.knightsoftnet.validators.client;

import com.google.gwt.junit.tools.GWTTestSuite;

import junit.framework.Test;
import junit.framework.TestCase;

/**
 * combine the single tests to speed up run.
 *
 * @author Manfred Tremmel
 *
 */
public class BeanValidatorsGwtTestSuite extends TestCase {
  /**
   * build a suite of all gwt unit tests.
   *
   * @return the test suite
   */
  public static Test suite() { // NOPMD
    final GWTTestSuite suite = new GWTTestSuite("All Gwt Tests go in here");
    suite.addTestSuite(GwtTestAgeLimitCheckTest.class);
    suite.addTestSuite(GwtTestAlternateSizeTest.class);
    suite.addTestSuite(GwtTestBankCountryTest.class);
    suite.addTestSuite(GwtTestBicTest.class);
    suite.addTestSuite(GwtTestBicWithSpacesTest.class);
    suite.addTestSuite(GwtTestCreditCardNumberTest.class);
    suite.addTestSuite(GwtTestEmailTest.class);
    suite.addTestSuite(GwtTestEmptyIfOtherHasValueTest.class);
    suite.addTestSuite(GwtTestEmptyIfOtherIsEmptyTest.class);
    suite.addTestSuite(GwtTestEmptyIfOtherIsNotEmptyTest.class);
    suite.addTestSuite(GwtTestGlnTest.class);
    suite.addTestSuite(GwtTestGtin13Test.class);
    suite.addTestSuite(GwtTestGtin8Test.class);
    suite.addTestSuite(GwtTestGtinTest.class);
    suite.addTestSuite(GwtTestIbanFormatedTest.class);
    suite.addTestSuite(GwtTestIbanTest.class);
    suite.addTestSuite(GwtTestIbanWithSpacesTest.class);
    suite.addTestSuite(GwtTestIsbn10FormatedTest.class);
    suite.addTestSuite(GwtTestIsbn10Test.class);
    suite.addTestSuite(GwtTestIsbn10WithSeparatorsTest.class);
    suite.addTestSuite(GwtTestIsbn13FormatedTest.class);
    suite.addTestSuite(GwtTestIsbn13Test.class);
    suite.addTestSuite(GwtTestIsbn13WithSeparatorsTest.class);
    suite.addTestSuite(GwtTestIsbnFormatedTest.class);
    suite.addTestSuite(GwtTestIsbnTest.class);
    suite.addTestSuite(GwtTestIsbnWithSeparatorsTest.class);
    suite.addTestSuite(GwtTestIsinTest.class);
    suite.addTestSuite(GwtTestNotEmptyAlternateIfOtherHasValueTest.class);
    suite.addTestSuite(GwtTestNotEmptyAlternateIfOtherIsEmptyTest.class);
    suite.addTestSuite(GwtTestNotEmptyAlternateIfOtherIsNotEmptyTest.class);
    suite.addTestSuite(GwtTestNotEmptyIfOtherHasValueTest.class);
    suite.addTestSuite(GwtTestNotEmptyIfOtherIsEmptyTest.class);
    suite.addTestSuite(GwtTestNotEmptyIfOtherIsNotEmptyTest.class);
    suite.addTestSuite(GwtTestPasswordTest.class);
    suite.addTestSuite(GwtTestPostalCodeTest.class);
    suite.addTestSuite(GwtTestRegularExpressionTest.class);
    suite.addTestSuite(GwtTestSizeWithoutSeparatorsTest.class);
    suite.addTestSuite(GwtTestUrlTest.class);
    suite.addTestSuite(GwtTestVatIdTest.class);
    return suite;
  }
}
