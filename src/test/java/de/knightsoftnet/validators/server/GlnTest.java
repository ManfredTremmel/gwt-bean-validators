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

import de.knightsoftnet.validators.shared.beans.GlnTestBean;

import org.junit.Test;

public class GlnTest extends AbstractValidationTest<GlnTestBean> {


  /**
   * empty gln is allowed.
   */
  @Test
  public final void testEmptyGlnIsAllowed() {
    super.validationTest(new GlnTestBean(null), true, null);
  }

  /**
   * correct gln is allowed.
   */
  @Test
  public final void testCorrectGlnIsAllowed() {
    super.validationTest(new GlnTestBean("4035600210708"), true, null);
    super.validationTest(new GlnTestBean("4250155401375"), true, null);
    super.validationTest(new GlnTestBean("9004617011702"), true, null);
  }

  /**
   * gln with wrong checksum.
   */
  @Test
  public final void testWrongChecksumGlnIsWrong() {
    super.validationTest(new GlnTestBean("4035600210078"), false,
        "de.knightsoftnet.validators.shared.impl.GlnValidator");
    super.validationTest(new GlnTestBean("4250515401375"), false,
        "de.knightsoftnet.validators.shared.impl.GlnValidator");
    super.validationTest(new GlnTestBean("4035601210078"), false,
        "de.knightsoftnet.validators.shared.impl.GlnValidator");
  }

  /**
   * gln which is to small.
   */
  @Test
  public final void testToSmallGlnIsWrong() {
    super.validationTest(new GlnTestBean("4035600210"), false,
        "org.hibernate.validator.constraints.impl.SizeValidatorForString");
  }

  /**
   * gln which is to big.
   */
  @Test
  public final void testToBigGlnIsWrong() {
    super.validationTest(new GlnTestBean("40356002107081"), false,
        "org.hibernate.validator.constraints.impl.DigitsValidatorForString");
  }

  /**
   * gln which is not numeric.
   */
  @Test
  public final void testNotNumericGlnIsWrong() {
    super.validationTest(new GlnTestBean("403560021070Y"), false,
        "org.hibernate.validator.constraints.impl.DigitsValidatorForString");
  }
}
