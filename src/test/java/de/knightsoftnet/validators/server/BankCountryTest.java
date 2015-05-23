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

import de.knightsoftnet.validators.shared.beans.BankCountryTestBean;
import de.knightsoftnet.validators.shared.testcases.BankCountryTestCases;

import org.junit.Test;

/**
 * test for bank country validator.
 *
 * @author Manfred Tremmel
 *
 */
public class BankCountryTest extends AbstractValidationTest<BankCountryTestBean> {

  /**
   * empty bank, iban and bic are allowed.
   */
  @Test
  public final void testEmptyBankCountryIsAllowed() {
    super.validationTest(BankCountryTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct bank, iban and bic are allowed.
   */
  @Test
  public final void testCorrectBankCountryIsAllowed() {
    for (final BankCountryTestBean testBean : BankCountryTestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * correct bank, iban and bic with wrong country.
   */
  @Test
  public final void testWrongCountryBankCountryIsWrong() {
    for (final BankCountryTestBean testBean : BankCountryTestCases.getWrongCountryTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.BankCountryValidator");
    }
  }

  /**
   * correct bank, iban and bic with wrong country in iban.
   */
  @Test
  public final void testWrongCountryBankCountryIbanIsWrong() {
    for (final BankCountryTestBean testBean : BankCountryTestCases.getWrongIbanCountryTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.BankCountryValidator");
    }
  }

  /**
   * correct bank, iban and bic with wrong country in bic.
   */
  @Test
  public final void testWrongCountryBankCountryBicIsWrong() {
    for (final BankCountryTestBean testBean : BankCountryTestCases.getWrongBicCountryTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.BankCountryValidator");
    }
  }
}
