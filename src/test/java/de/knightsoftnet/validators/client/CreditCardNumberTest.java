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

import de.knightsoftnet.validators.shared.CreditCardNumber;

import org.junit.Test;

public class CreditCardNumberTest extends
    AbstractValidationTest<CreditCardNumberTest.CreditCardNumberTestBean> {

  public class CreditCardNumberTestBean {

    @CreditCardNumber
    private final String creditCard;

    public CreditCardNumberTestBean(final String pcreditCard) {
      super();
      this.creditCard = pcreditCard;
    }

    public String getCreditCard() {
      return this.creditCard;
    }
  }

  /**
   * empty credit card is allowed.
   */
  @Test
  public final void testEmptyCreditCardIsAllowed() {
    super.validationTest(new CreditCardNumberTestBean(null), true, null);
  }

  /**
   * correct credit card numbers are allowed.
   */
  @Test
  public final void testCorrectCreditCardsAreAllowed() {
    super.validationTest(new CreditCardNumberTestBean("4417123456789113"), true, null);
    super.validationTest(new CreditCardNumberTestBean("4222222222222"), true, null);
    super.validationTest(new CreditCardNumberTestBean("378282246310005"), true, null);
    super.validationTest(new CreditCardNumberTestBean("5105105105105100"), true, null);
    super.validationTest(new CreditCardNumberTestBean("6011000990139424"), true, null);
  }

  /**
   * wrong credit card numbers are not allowed.
   */
  @Test
  public final void testWrongCreditCardsAreWrong() {
    super.validationTest(new CreditCardNumberTestBean("123456789012"), false,
        "de.knightsoftnet.validators.shared.impl.CreditCardNumberValidator");
    super.validationTest(new CreditCardNumberTestBean("12345678901234567890"), false,
        "org.hibernate.validator.constraints.impl.SizeValidatorForString");
    super.validationTest(new CreditCardNumberTestBean("4417123456789112"), false,
        "de.knightsoftnet.validators.shared.impl.CreditCardNumberValidator");
    super.validationTest(new CreditCardNumberTestBean("4417q23456w89113"), false,
        "de.knightsoftnet.validators.shared.impl.CreditCardNumberValidator");
  }
}
