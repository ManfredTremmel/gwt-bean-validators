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

import de.knightsoftnet.validators.shared.beans.BankCountryTestBean;
import de.knightsoftnet.validators.shared.testcases.BankCountryTestCases;

/**
 * test for bank country validator.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtTstBankCountry extends AbstractValidationTst<BankCountryTestBean> {

  /**
   * empty bank, iban and bic are allowed.
   */
  public final void testEmptyBankCountryIsAllowed() {
    super.validationTest(BankCountryTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct bank, iban and bic are allowed.
   */
  public final void testCorrectBankCountryIsAllowed() {
    for (final BankCountryTestBean testBean : BankCountryTestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * correct bank, iban and bic with wrong country.
   */
  public final void testWrongCountryBankCountryIsWrong() {
    for (final BankCountryTestBean testBean : BankCountryTestCases.getWrongCountryTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.BankCountryValidator");
    }
  }

  /**
   * correct bank, iban and bic with wrong country in iban.
   */
  public final void testWrongCountryBankCountryIbanIsWrong() {
    for (final BankCountryTestBean testBean : BankCountryTestCases.getWrongIbanCountryTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.BankCountryValidator");
    }
  }

  /**
   * correct bank, iban and bic with wrong country in bic.
   */
  public final void testWrongCountryBankCountryBicIsWrong() {
    for (final BankCountryTestBean testBean : BankCountryTestCases.getWrongBicCountryTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.BankCountryValidator");
    }
  }

  /**
   * correct bank, iban and bic with bic doesn't match iban.
   */
  public final void testWrongBicIbanRelationBicIsWrong() {
    for (final BankCountryTestBean testBean : BankCountryTestCases.getWrongBicForIbanTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.BankCountryValidator");
    }
  }
}
