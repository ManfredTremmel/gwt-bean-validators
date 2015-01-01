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

import de.knightsoftnet.validators.shared.beans.Gtin8TestBean;

import org.junit.Test;

public class Gtin8Test extends AbstractValidationTest<Gtin8TestBean> {


  /**
   * empty gtin8 is allowed.
   */
  @Test
  public final void testEmptyGtin8IsAllowed() {
    super.validationTest(new Gtin8TestBean(null), true, null);
  }

  /**
   * correct gtin8 is allowed.
   */
  @Test
  public final void testCorrectGtin8IsAllowed() {
    super.validationTest(new Gtin8TestBean("12345670"), true, null);
    super.validationTest(new Gtin8TestBean("40267708"), true, null);
    super.validationTest(new Gtin8TestBean("96385074"), true, null);
  }

  /**
   * gtin8 with wrong checksum.
   */
  @Test
  public final void testWrongChecksumGtin8IsWrong() {
    super.validationTest(new Gtin8TestBean("12345678"), false,
        "de.knightsoftnet.validators.shared.impl.Gtin8Validator");
    super.validationTest(new Gtin8TestBean("40627708"), false,
        "de.knightsoftnet.validators.shared.impl.Gtin8Validator");
    super.validationTest(new Gtin8TestBean("96386074"), false,
        "de.knightsoftnet.validators.shared.impl.Gtin8Validator");
  }

  /**
   * gtin8 which is to small.
   */
  @Test
  public final void testToSmallGtin8IsWrong() {
    super.validationTest(new Gtin8TestBean("1234567"), false,
        "org.hibernate.validator.constraints.impl.SizeValidatorForString");
  }

  /**
   * gtin8 which is to big.
   */
  @Test
  public final void testToBigGtin8IsWrong() {
    super.validationTest(new Gtin8TestBean("123456701"), false,
        "org.hibernate.validator.constraints.impl.DigitsValidatorForString");
  }

  /**
   * gtin8 which is not numeric.
   */
  @Test
  public final void testNotNumericGtin8IsWrong() {
    super.validationTest(new Gtin8TestBean("1234567Y"), false,
        "org.hibernate.validator.constraints.impl.DigitsValidatorForString");
  }
}
