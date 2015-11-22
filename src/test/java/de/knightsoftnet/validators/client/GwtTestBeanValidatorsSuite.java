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

import de.knightsoftnet.validators.client.util.GwtTstPhoneNumberUtil;

import com.google.gwt.junit.tools.GWTTestSuite;

import junit.framework.Test;

/**
 * combine the single tests to speed up run.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtTestBeanValidatorsSuite extends GWTTestSuite {
  /**
   * build a suite of all gwt unit tests.
   *
   * @return the test suite
   */
  public static Test suite() { // NOPMD
    final GWTTestSuite suite = new GWTTestSuite("All Gwt Tests go in here");
    suite.addTestSuite(GwtTstAgeLimitCheck.class);
    suite.addTestSuite(GwtTstAlternateSize.class);
    suite.addTestSuite(GwtTstBankCountry.class);
    suite.addTestSuite(GwtTstBic.class);
    suite.addTestSuite(GwtTstBicValue.class);
    suite.addTestSuite(GwtTstBicWithSpaces.class);
    suite.addTestSuite(GwtTstCreditCardNumber.class);
    suite.addTestSuite(GwtTstEmail.class);
    suite.addTestSuite(GwtTstEmptyIfOtherHasValue.class);
    suite.addTestSuite(GwtTstEmptyIfOtherIsEmpty.class);
    suite.addTestSuite(GwtTstEmptyIfOtherIsNotEmpty.class);
    suite.addTestSuite(GwtTstGln.class);
    suite.addTestSuite(GwtTstGtin13.class);
    suite.addTestSuite(GwtTstGtin8.class);
    suite.addTestSuite(GwtTstGtin.class);
    suite.addTestSuite(GwtTstIbanFormated.class);
    suite.addTestSuite(GwtTstIban.class);
    suite.addTestSuite(GwtTstIbanWithSpaces.class);
    suite.addTestSuite(GwtTstIsbn10Formated.class);
    suite.addTestSuite(GwtTstIsbn10.class);
    suite.addTestSuite(GwtTstIsbn10WithSeparators.class);
    suite.addTestSuite(GwtTstIsbn13Formated.class);
    suite.addTestSuite(GwtTstIsbn13.class);
    suite.addTestSuite(GwtTstIsbn13WithSeparators.class);
    suite.addTestSuite(GwtTstIsbnFormated.class);
    suite.addTestSuite(GwtTstIsbn.class);
    suite.addTestSuite(GwtTstIsbnWithSeparators.class);
    suite.addTestSuite(GwtTstIsin.class);
    suite.addTestSuite(GwtTstLevenshteinDistance.class);
    suite.addTestSuite(GwtTstMustBeEqual.class);
    suite.addTestSuite(GwtTstMustNotBeEqual.class);
    suite.addTestSuite(GwtTstNotEmptyAlternateIfOtherHasValue.class);
    suite.addTestSuite(GwtTstNotEmptyAlternateIfOtherIsEmpty.class);
    suite.addTestSuite(GwtTstNotEmptyAlternateIfOtherIsNotEmpty.class);
    suite.addTestSuite(GwtTstNotEmptyIfOtherHasValue.class);
    suite.addTestSuite(GwtTstNotEmptyIfOtherIsEmpty.class);
    suite.addTestSuite(GwtTstNotEmptyIfOtherIsNotEmpty.class);
    suite.addTestSuite(GwtTstPassword.class);
    suite.addTestSuite(GwtTstPhoneNumber.class);
    suite.addTestSuite(GwtTstPostalCode.class);
    suite.addTestSuite(GwtTstRegularExpression.class);
    suite.addTestSuite(GwtTstSizeWithoutSeparators.class);
    suite.addTestSuite(GwtTstUrl.class);
    suite.addTestSuite(GwtTstVatId.class);
    suite.addTestSuite(GwtTstPhoneNumberUtil.class);
    return suite;
  }
}
