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

import de.knightsoftnet.validators.shared.Gtin13;

import org.junit.Test;

public class Gtin13Test extends AbstractValidationTest<Gtin13Test.Gtin13TestBean> {

  public class Gtin13TestBean {

    @Gtin13
    private final String gtin;

    public Gtin13TestBean(final String pgtin) {
      super();
      this.gtin = pgtin;
    }

    public String getGtin() {
      return this.gtin;
    }
  }

  /**
   * empty gtin13 is allowed.
   */
  @Test
  public final void testEmptyGtin13IsAllowed() {
    super.validationTest(new Gtin13TestBean(null), true, null);
  }

  /**
   * correct gtin13 is allowed.
   */
  @Test
  public final void testCorrectGtin13IsAllowed() {
    super.validationTest(new Gtin13TestBean("4035600210708"), true, null);
    super.validationTest(new Gtin13TestBean("4250155401375"), true, null);
    super.validationTest(new Gtin13TestBean("9004617011702"), true, null);
  }

  /**
   * gtin13 with wrong checksum.
   */
  @Test
  public final void testWrongChecksumGtin13IsWrong() {
    super.validationTest(new Gtin13TestBean("4035600210078"), false,
        "de.knightsoftnet.validators.shared.impl.Gtin13Validator");
    super.validationTest(new Gtin13TestBean("4250515401375"), false,
        "de.knightsoftnet.validators.shared.impl.Gtin13Validator");
    super.validationTest(new Gtin13TestBean("4035601210078"), false,
        "de.knightsoftnet.validators.shared.impl.Gtin13Validator");
  }

  /**
   * gtin13 which is to small.
   */
  @Test
  public final void testToSmallGtin13IsWrong() {
    super.validationTest(new Gtin13TestBean("4035600210"), false,
        "org.hibernate.validator.constraints.impl.SizeValidatorForString");
  }

  /**
   * gtin13 which is to big.
   */
  @Test
  public final void testToBigGtin13IsWrong() {
    super.validationTest(new Gtin13TestBean("40356002107081"), false,
        "org.hibernate.validator.constraints.impl.DigitsValidatorForString");
  }

  /**
   * gtin13 which is not numeric.
   */
  @Test
  public final void testNotNumericGtin13IsWrong() {
    super.validationTest(new Gtin13TestBean("403560021070Y"), false,
        "org.hibernate.validator.constraints.impl.DigitsValidatorForString");
  }
}
