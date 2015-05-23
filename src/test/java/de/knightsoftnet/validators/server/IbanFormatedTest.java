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

package de.knightsoftnet.validators.server;

import de.knightsoftnet.validators.shared.beans.IbanFormatedTestBean;
import de.knightsoftnet.validators.shared.testcases.IbanFormatedTestCases;

import org.junit.Test;

/**
 * test for iban formated validator.
 *
 * @author Manfred Tremmel
 *
 */
public class IbanFormatedTest extends AbstractValidationTest<IbanFormatedTestBean> {

  /**
   * empty iban is allowed.
   */
  @Test
  public final void testEmptyIbanIsAllowed() {
    super.validationTest(IbanFormatedTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct iban is allowed.
   */
  @Test
  public final void testCorrectIbanIsAllowed() {
    for (final IbanFormatedTestBean testBean : IbanFormatedTestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * iban with country which is not part of SEPA country list.
   */
  @Test
  public final void testWrongCountryIbanIsWrong() {
    for (final IbanFormatedTestBean testBean : IbanFormatedTestCases.getWrongTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.IbanFormatedValidator");
    }
  }

  /**
   * iban with country which is not part of SEPA country list.
   */
  @Test
  public final void testToSmallIbanIsWrong() {
    for (final IbanFormatedTestBean testBean : IbanFormatedTestCases.getToSmallTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.constraints.impl.SizeValidatorForString");
    }
  }

  /**
   * iban with country which is not part of SEPA country list.
   */
  @Test
  public final void testToBigIbanIsWrong() {
    for (final IbanFormatedTestBean testBean : IbanFormatedTestCases.getToBigTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.constraints.impl.SizeValidatorForString");
    }
  }

  /**
   * iban with checksum error.
   */
  @Test
  public final void testChecksumErrorIbanIsWrong() {
    for (final IbanFormatedTestBean testBean : IbanFormatedTestCases.getWrongChecksumTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.IbanFormatedValidator");
    }
  }

  /**
   * correct iban, but wrong formated error.
   */
  @Test
  public final void testWrongformatedIbanIsWrong() {
    for (final IbanFormatedTestBean testBean : IbanFormatedTestCases.getWrongFormatedTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.IbanFormatedValidator");
    }
  }
}
