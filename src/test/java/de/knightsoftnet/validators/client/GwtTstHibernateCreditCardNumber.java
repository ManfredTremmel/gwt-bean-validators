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

import de.knightsoftnet.validators.shared.beans.HibernateCreditCardNumberTestBean;
import de.knightsoftnet.validators.shared.testcases.HibernateCreditCardNumberTestCases;

/**
 * test for hibernate credit card number validator.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtTstHibernateCreditCardNumber
    extends AbstractValidationTst<HibernateCreditCardNumberTestBean> {

  /**
   * empty credit card is allowed.
   */
  public final void testEmptyHibernateCreditCardIsAllowed() {
    super.validationTest(HibernateCreditCardNumberTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct credit card numbers are allowed.
   */
  public final void testCorrectHibernateCreditCardsAreAllowed() {
    for (final HibernateCreditCardNumberTestBean testBean : HibernateCreditCardNumberTestCases
        .getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * wrong credit card numbers are not allowed.
   */
  public final void testWrongHibernateCreditCardsAreWrong() {
    for (final HibernateCreditCardNumberTestBean testBean : HibernateCreditCardNumberTestCases
        .getWrongTestBeans()) {
      super.validationTest(testBean, false, null);
    }
  }

  /**
   * wrong credit card numbers are not allowed.
   */
  public final void testWrongHibernateCreditCardsSizeAreWrong() {
    for (final HibernateCreditCardNumberTestBean testBean : HibernateCreditCardNumberTestCases
        .getWrongSizeTestBeans()) {
      super.validationTest(testBean, false, null);
    }
  }
}
