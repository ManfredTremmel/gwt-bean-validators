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

import de.knightsoftnet.validators.client.util.GwtTstIbanUtil;
import de.knightsoftnet.validators.client.util.GwtTstIsbnUtil;
import de.knightsoftnet.validators.client.util.GwtTstPhoneNumberUtil;
import de.knightsoftnet.validators.client.util.GwtTstRegExUtil;

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

    GwtTestBeanValidatorsSuite.appendMtBeanValidatorTests(suite);
    GwtTestBeanValidatorsSuite.appendHibernateBeanValidatorTests(suite);

    return suite;
  }

  private static void appendMtBeanValidatorTests(final GWTTestSuite psuite) {
    psuite.addTestSuite(GwtTstAgeLimitCheck.class);
    psuite.addTestSuite(GwtTstAlternateSize.class);
    psuite.addTestSuite(GwtTstBankCountry.class);
    psuite.addTestSuite(GwtTstBic.class);
    psuite.addTestSuite(GwtTstBicValue.class);
    psuite.addTestSuite(GwtTstBicWithSpaces.class);
    psuite.addTestSuite(GwtTstCreditCardNumber.class);
    psuite.addTestSuite(GwtTstEmail.class);
    psuite.addTestSuite(GwtTstEmptyIfOtherHasValue.class);
    psuite.addTestSuite(GwtTstEmptyIfOtherIsEmpty.class);
    psuite.addTestSuite(GwtTstEmptyIfOtherIsNotEmpty.class);
    psuite.addTestSuite(GwtTstGln.class);
    psuite.addTestSuite(GwtTstGtin13.class);
    psuite.addTestSuite(GwtTstGtin8.class);
    psuite.addTestSuite(GwtTstGtin.class);
    psuite.addTestSuite(GwtTstIbanFormated.class);
    psuite.addTestSuite(GwtTstIban.class);
    psuite.addTestSuite(GwtTstIbanWithSpaces.class);
    psuite.addTestSuite(GwtTstIsbn10Formated.class);
    psuite.addTestSuite(GwtTstIsbn10.class);
    psuite.addTestSuite(GwtTstIsbn10WithSeparators.class);
    psuite.addTestSuite(GwtTstIsbn13Formated.class);
    psuite.addTestSuite(GwtTstIsbn13.class);
    psuite.addTestSuite(GwtTstIsbn13WithSeparators.class);
    psuite.addTestSuite(GwtTstIsbnFormated.class);
    psuite.addTestSuite(GwtTstIsbn.class);
    psuite.addTestSuite(GwtTstIsbnWithSeparators.class);
    psuite.addTestSuite(GwtTstIsin.class);
    psuite.addTestSuite(GwtTstLevenshteinDistance.class);
    psuite.addTestSuite(GwtTstMustBeEqual.class);
    psuite.addTestSuite(GwtTstMustNotBeEqual.class);
    psuite.addTestSuite(GwtTstNotEmptyAlternateIfOtherHasValue.class);
    psuite.addTestSuite(GwtTstNotEmptyAlternateIfOtherIsEmpty.class);
    psuite.addTestSuite(GwtTstNotEmptyAlternateIfOtherIsNotEmpty.class);
    psuite.addTestSuite(GwtTstNotEmptyIfOtherHasValue.class);
    psuite.addTestSuite(GwtTstNotEmptyIfOtherIsEmpty.class);
    psuite.addTestSuite(GwtTstNotEmptyIfOtherIsNotEmpty.class);
    psuite.addTestSuite(GwtTstPassword.class);
    psuite.addTestSuite(GwtTstPhoneNumber.class);
    psuite.addTestSuite(GwtTstPhoneNumberValue.class);
    psuite.addTestSuite(GwtTstPostalCode.class);
    psuite.addTestSuite(GwtTstRegularExpression.class);
    psuite.addTestSuite(GwtTstSizeWithoutSeparators.class);
    psuite.addTestSuite(GwtTstUrl.class);
    psuite.addTestSuite(GwtTstVatId.class);
    psuite.addTestSuite(GwtTstIbanUtil.class);
    psuite.addTestSuite(GwtTstIsbnUtil.class);
    psuite.addTestSuite(GwtTstPhoneNumberUtil.class);
    psuite.addTestSuite(GwtTstRegExUtil.class);
  }

  private static void appendHibernateBeanValidatorTests(final GWTTestSuite psuite) {
    psuite.addTestSuite(GwtTstHibernateAssertFalse.class);
    psuite.addTestSuite(GwtTstHibernateAssertTrue.class);
    psuite.addTestSuite(GwtTstHibernateDecimalMinMax.class);
    psuite.addTestSuite(GwtTstHibernateDigits.class);
    psuite.addTestSuite(GwtTstHibernateEmail.class);
    psuite.addTestSuite(GwtTstHibernateMinMax.class);
    psuite.addTestSuite(GwtTstHibernateNotNull.class);
    psuite.addTestSuite(GwtTstHibernateNotBlank.class);
    psuite.addTestSuite(GwtTstHibernateNotEmpty.class);
    psuite.addTestSuite(GwtTstHibernateNull.class);
    psuite.addTestSuite(GwtTstHibernatePattern.class);
    psuite.addTestSuite(GwtTstHibernateSize.class);
    psuite.addTestSuite(GwtTstHibernateSizeArray.class);
    psuite.addTestSuite(GwtTstHibernateSizeCollection.class);
    psuite.addTestSuite(GwtTstHibernateFuture.class);
    psuite.addTestSuite(GwtTstHibernatePast.class);
    psuite.addTestSuite(GwtTstHibernateEan8.class);
    psuite.addTestSuite(GwtTstHibernateEan13.class);
    psuite.addTestSuite(GwtTstHibernateLength.class);
    psuite.addTestSuite(GwtTstHibernateCreditCardNumber.class);
    psuite.addTestSuite(GwtTstHibernateUrl.class);
    psuite.addTestSuite(GwtTstHibernateCnpj.class);
    psuite.addTestSuite(GwtTstHibernateCpf.class);
  }
}
