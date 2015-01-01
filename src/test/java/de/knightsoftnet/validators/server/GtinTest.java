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

import de.knightsoftnet.validators.shared.beans.GtinTestBean;

import org.junit.Test;

public class GtinTest extends AbstractValidationTest<GtinTestBean> {


  /**
   * empty gtin is allowed.
   */
  @Test
  public final void testEmptyGtinIsAllowed() {
    super.validationTest(new GtinTestBean(null), true, null);
  }

  /**
   * correct gtin is allowed.
   */
  @Test
  public final void testCorrectGtinIsAllowed() {
    super.validationTest(new GtinTestBean("12345670"), true, null);
    super.validationTest(new GtinTestBean("40267708"), true, null);
    super.validationTest(new GtinTestBean("96385074"), true, null);
    super.validationTest(new GtinTestBean("4035600210708"), true, null);
    super.validationTest(new GtinTestBean("4250155401375"), true, null);
    super.validationTest(new GtinTestBean("9004617011702"), true, null);
  }

  /**
   * gtin with wrong checksum.
   */
  @Test
  public final void testWrongChecksumGtinIsWrong() {
    super.validationTest(new GtinTestBean("12345678"), false,
        "de.knightsoftnet.validators.shared.impl.GtinValidator");
    super.validationTest(new GtinTestBean("40627708"), false,
        "de.knightsoftnet.validators.shared.impl.GtinValidator");
    super.validationTest(new GtinTestBean("96386074"), false,
        "de.knightsoftnet.validators.shared.impl.GtinValidator");
    super.validationTest(new GtinTestBean("4035600210078"), false,
        "de.knightsoftnet.validators.shared.impl.GtinValidator");
    super.validationTest(new GtinTestBean("4250515401375"), false,
        "de.knightsoftnet.validators.shared.impl.GtinValidator");
    super.validationTest(new GtinTestBean("4035601210078"), false,
        "de.knightsoftnet.validators.shared.impl.GtinValidator");
  }

  /**
   * gtin which has the wrong size.
   */
  @Test
  public final void testWrongSizeGtinIsWrong() {
    super.validationTest(new GtinTestBean("1234567"), false,
        "de.knightsoftnet.validators.shared.impl.AlternateSizeValidator");
    super.validationTest(new GtinTestBean("4035600210"), false,
        "de.knightsoftnet.validators.shared.impl.AlternateSizeValidator");
  }

  /**
   * gtin which is not numeric.
   */
  @Test
  public final void testNotNumericGtinIsWrong() {
    super.validationTest(new GtinTestBean("1234567Y"), false,
        "org.hibernate.validator.constraints.impl.DigitsValidatorForString");
    super.validationTest(new GtinTestBean("403560021070Y"), false,
        "org.hibernate.validator.constraints.impl.DigitsValidatorForString");
  }
}
