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

import de.knightsoftnet.validators.shared.beans.CreditCardNumberTestBean;
import de.knightsoftnet.validators.shared.testcases.CreditCardTestCases;

import org.junit.Test;

/**
 * test for credit card number validator.
 *
 * @author Manfred Tremmel
 *
 */
public class CreditCardNumberTest extends AbstractValidationTest<CreditCardNumberTestBean> {

  /**
   * empty credit card is allowed.
   */
  @Test
  public final void testEmptyCreditCardIsAllowed() {
    super.validationTest(CreditCardTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct credit card numbers are allowed.
   */
  @Test
  public final void testCorrectCreditCardsAreAllowed() {
    for (final CreditCardNumberTestBean testBean : CreditCardTestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * wrong credit card numbers are not allowed.
   */
  @Test
  public final void testWrongCreditCardsAreWrong() {
    for (final CreditCardNumberTestBean testBean : CreditCardTestCases.getWrongTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.CreditCardNumberValidator");
    }
  }

  /**
   * wrong credit card numbers are not allowed.
   */
  @Test
  public final void testWrongCreditCardsSizeAreWrong() {
    for (final CreditCardNumberTestBean testBean : CreditCardTestCases.getWrongSizeTestBeans()) {
      super.validationTest(testBean, false, SIZE_VALIDATOR);
    }
  }
}
