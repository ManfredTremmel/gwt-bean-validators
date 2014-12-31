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

import de.knightsoftnet.validators.shared.IbanFormatedTestBean;

import org.junit.Test;

public class IbanFormatedTest extends AbstractValidationTest<IbanFormatedTestBean> {


  /**
   * empty iban is allowed.
   */
  @Test
  public final void testEmptyIbanIsAllowed() {
    super.validationTest(new IbanFormatedTestBean(null), true, null);
  }

  /**
   * correct iban is allowed.
   */
  @Test
  public final void testCorrectIbanIsAllowed() {
    super.validationTest(new IbanFormatedTestBean("DE16 7016 0000 0000 5554 44"), true, null);
    super.validationTest(new IbanFormatedTestBean("DE49 4306 0967 0000 0334 01"), true, null);
    super.validationTest(new IbanFormatedTestBean("AT24 2011 1822 2121 9800"), true, null);
    super.validationTest(new IbanFormatedTestBean("CH16 0900 0000 8777 6876 6"), true, null);
    super.validationTest(new IbanFormatedTestBean("IT73 O050 1803 2000 0000 0125 125"), true, null);
  }

  /**
   * iban with country which is not part of SEPA country list.
   */
  @Test
  public final void testWrongCountryIbanIsWrong() {
    super.validationTest(new IbanFormatedTestBean("XY16 7016 0000 0000 5554 44"), false,
        "de.knightsoftnet.validators.shared.impl.IbanFormatedValidator");
  }

  /**
   * iban with country which is not part of SEPA country list.
   */
  @Test
  public final void testToSmallIbanIsWrong() {
    super.validationTest(new IbanFormatedTestBean("DE12 3456 1"), false,
        "org.hibernate.validator.constraints.impl.SizeValidatorForString");
  }

  /**
   * iban with country which is not part of SEPA country list.
   */
  @Test
  public final void testToBigIbanIsWrong() {
    super.validationTest(new IbanFormatedTestBean("DE16 7016 0000 0000 5554 4412 3456 7890 123"),
        false, "org.hibernate.validator.constraints.impl.SizeValidatorForString");
  }

  /**
   * iban with checksum error.
   */
  @Test
  public final void testChecksumErrorIbanIsWrong() {
    super.validationTest(new IbanFormatedTestBean("DE16 7061 0000 0000 5554 44"), false,
        "de.knightsoftnet.validators.shared.impl.IbanFormatedValidator");
  }

  /**
   * correct iban, but wrong formated error.
   */
  @Test
  public final void testWrongformatedIbanIsWrong() {
    super.validationTest(new IbanFormatedTestBean("DE167 016 0000 0000 5554 44"), false,
        "de.knightsoftnet.validators.shared.impl.IbanFormatedValidator");
    super.validationTest(new IbanFormatedTestBean("DE49 4306 09670000 0334 01"), false,
        "de.knightsoftnet.validators.shared.impl.IbanFormatedValidator");
    super.validationTest(new IbanFormatedTestBean("AT24 2011 1822 2121 980 0"), false,
        "de.knightsoftnet.validators.shared.impl.IbanFormatedValidator");
    super.validationTest(new IbanFormatedTestBean("CH16-0900-0000-8777-6876-6"), false,
        "de.knightsoftnet.validators.shared.impl.IbanFormatedValidator");
  }
}
