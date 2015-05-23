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

import de.knightsoftnet.validators.shared.beans.IbanTestBean;
import de.knightsoftnet.validators.shared.testcases.IbanTestCases;

import org.junit.Test;

/**
 * test for iban validator.
 *
 * @author Manfred Tremmel
 *
 */
public class IbanTest extends AbstractValidationTest<IbanTestBean> {

  /**
   * empty iban is allowed.
   */
  @Test
  public final void testEmptyIbanIsAllowed() {
    super.validationTest(IbanTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct iban is allowed.
   */
  @Test
  public final void testCorrectIbanIsAllowed() {
    for (final IbanTestBean testBean : IbanTestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * iban with country which is not part of SEPA country list.
   */
  @Test
  public final void testWrongCountryIbanIsWrong() {
    for (final IbanTestBean testBean : IbanTestCases.getWrongTestBeans()) {
      super
          .validationTest(testBean, false, "de.knightsoftnet.validators.shared.impl.IbanValidator");
    }
  }

  /**
   * iban with country which is not part of SEPA country list.
   */
  @Test
  public final void testToSmallIbanIsWrong() {
    for (final IbanTestBean testBean : IbanTestCases.getToSmallTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.SizeWithoutSeparatorsValidator");
    }
  }

  /**
   * iban with country which is not part of SEPA country list.
   */
  @Test
  public final void testToBigIbanIsWrong() {
    for (final IbanTestBean testBean : IbanTestCases.getToBigTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.SizeWithoutSeparatorsValidator");
    }
  }

  /**
   * iban with checksum error.
   */
  @Test
  public final void testChecksumErrorIbanIsWrong() {
    for (final IbanTestBean testBean : IbanTestCases.getWrongChecksumTestBeans()) {
      super
          .validationTest(testBean, false, "de.knightsoftnet.validators.shared.impl.IbanValidator");
    }
  }
}
