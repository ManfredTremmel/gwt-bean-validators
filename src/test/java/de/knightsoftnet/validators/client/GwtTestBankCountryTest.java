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

import de.knightsoftnet.validators.shared.BankCountryTestBean;

public class GwtTestBankCountryTest extends AbstractValidationTest<BankCountryTestBean> {


  /**
   * empty bank, iban and bic are allowed.
   */
  public final void testEmptyBankCountryIsAllowed() {
    super.validationTest(new BankCountryTestBean(null, null, null), true, null);
  }

  /**
   * correct bank, iban and bic are allowed.
   */
  public final void testCorrectBankCountryIsAllowed() {
    super.validationTest(new BankCountryTestBean("DE", "DE16701600000000555444", "GENODEFF701"),
        true, null);
    super.validationTest(new BankCountryTestBean("DE", "DE49430609670000033401", "GENODEM1GLS"),
        true, null);
    super.validationTest(new BankCountryTestBean("AT", "AT242011182221219800", "GIBAATWWXXX"),
        true, null);
    super.validationTest(new BankCountryTestBean("CH", "CH1609000000877768766", "POFICHBEXXX"),
        true, null);
    super.validationTest(new BankCountryTestBean("IT", "IT73O0501803200000000125125", "CCRTIT21"),
        true, null);
  }

  /**
   * correct bank, iban and bic with wrong country.
   */
  public final void testWrongCountryBankCountryIsWrong() {
    super.validationTest(new BankCountryTestBean("AT", "DE16701600000000555444", "GENODEFF701"),
        false, "de.knightsoftnet.validators.shared.impl.BankCountryValidator");
    super.validationTest(new BankCountryTestBean("CH", "DE49430609670000033401", "GENODEM1GLS"),
        false, "de.knightsoftnet.validators.shared.impl.BankCountryValidator");
    super.validationTest(new BankCountryTestBean("IT", "AT242011182221219800", "GIBAATWWXXX"),
        false, "de.knightsoftnet.validators.shared.impl.BankCountryValidator");
    super.validationTest(new BankCountryTestBean("DE", "CH1609000000877768766", "POFICHBEXXX"),
        false, "de.knightsoftnet.validators.shared.impl.BankCountryValidator");
    super.validationTest(new BankCountryTestBean("DE", "IT73O0501803200000000125125", "CCRTIT21"),
        false, "de.knightsoftnet.validators.shared.impl.BankCountryValidator");
  }

  /**
   * correct bank, iban and bic with wrong country in iban.
   */
  public final void testWrongCountryBankCountryIbanIsWrong() {
    super.validationTest(new BankCountryTestBean("DE", "AT242011182221219800", "GENODEFF701"),
        false, "de.knightsoftnet.validators.shared.impl.BankCountryValidator");
    super.validationTest(new BankCountryTestBean("DE", "CH1609000000877768766", "GENODEM1GLS"),
        false, "de.knightsoftnet.validators.shared.impl.BankCountryValidator");
    super.validationTest(
        new BankCountryTestBean("AT", "IT73O0501803200000000125125", "GIBAATWWXXX"), false,
        "de.knightsoftnet.validators.shared.impl.BankCountryValidator");
    super.validationTest(new BankCountryTestBean("CH", "DE16701600000000555444", "POFICHBEXXX"),
        false, "de.knightsoftnet.validators.shared.impl.BankCountryValidator");
    super.validationTest(new BankCountryTestBean("IT", "DE49430609670000033401", "CCRTIT21"),
        false, "de.knightsoftnet.validators.shared.impl.BankCountryValidator");
  }

  /**
   * correct bank, iban and bic with wrong country in bic.
   */
  public final void testWrongCountryBankCountryBicIsWrong() {
    super.validationTest(new BankCountryTestBean("DE", "DE16701600000000555444", "GIBAATWWXXX"),
        false, "de.knightsoftnet.validators.shared.impl.BankCountryValidator");
    super.validationTest(new BankCountryTestBean("DE", "DE49430609670000033401", "POFICHBEXXX"),
        false, "de.knightsoftnet.validators.shared.impl.BankCountryValidator");
    super.validationTest(new BankCountryTestBean("AT", "AT242011182221219800", "CCRTIT21"), false,
        "de.knightsoftnet.validators.shared.impl.BankCountryValidator");
    super.validationTest(new BankCountryTestBean("CH", "CH1609000000877768766", "GENODEFF701"),
        false, "de.knightsoftnet.validators.shared.impl.BankCountryValidator");
    super.validationTest(
        new BankCountryTestBean("IT", "IT73O0501803200000000125125", "GENODEM1GLS"), false,
        "de.knightsoftnet.validators.shared.impl.BankCountryValidator");
  }
}
