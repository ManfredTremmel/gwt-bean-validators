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

import de.knightsoftnet.validators.shared.Iban;

import org.junit.Test;

public class IbanTest extends AbstractValidationTest<IbanTest.IbanTestBean> {

  public class IbanTestBean {

    @Iban
    private final String iban;

    public IbanTestBean(final String piban) {
      super();
      this.iban = piban;
    }

    public String getIban() {
      return this.iban;
    }
  }

  /**
   * empty iban is allowed.
   */
  @Test
  public final void testEmptyIbanIsAllowed() {
    super.validationTest(new IbanTestBean(null), true, null);
  }

  /**
   * correct iban is allowed.
   */
  @Test
  public final void testCorrectIbanIsAllowed() {
    super.validationTest(new IbanTestBean("DE16701600000000555444"), true, null);
    super.validationTest(new IbanTestBean("DE49430609670000033401"), true, null);
    super.validationTest(new IbanTestBean("AT242011182221219800"), true, null);
    super.validationTest(new IbanTestBean("CH1609000000877768766"), true, null);
    super.validationTest(new IbanTestBean("IT73O0501803200000000125125"), true, null);
  }

  /**
   * iban with country which is not part of SEPA country list.
   */
  @Test
  public final void testWrongCountryIbanIsWrong() {
    super.validationTest(new IbanTestBean("XY16701600000000555444"), false,
        "de.knightsoftnet.validators.shared.impl.IbanValidator");
  }

  /**
   * iban with country which is not part of SEPA country list.
   */
  @Test
  public final void testToSmallIbanIsWrong() {
    super.validationTest(new IbanTestBean("DE123"), false,
        "org.hibernate.validator.constraints.impl.SizeValidatorForString");
  }

  /**
   * iban with country which is not part of SEPA country list.
   */
  @Test
  public final void testToBigIbanIsWrong() {
    super.validationTest(new IbanTestBean("DE167016000000005554441234567890123"), false,
        "org.hibernate.validator.constraints.impl.SizeValidatorForString");
  }

  /**
   * iban with checksum error.
   */
  @Test
  public final void testChecksumErrorIbanIsWrong() {
    super.validationTest(new IbanTestBean("DE16706100000000555444"), false,
        "de.knightsoftnet.validators.shared.impl.IbanValidator");
  }
}
