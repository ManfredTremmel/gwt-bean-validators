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

import de.knightsoftnet.validators.shared.beans.Gtin8TestBean;
import de.knightsoftnet.validators.shared.testcases.Gtin8TestCases;

/**
 * test for gtin 8 validator.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtTstGtin8 extends AbstractValidationTst<Gtin8TestBean> {

  /**
   * empty gtin8 is allowed.
   */
  public final void testEmptyGtin8IsAllowed() {
    super.validationTest(Gtin8TestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct gtin8 is allowed.
   */
  public final void testCorrectGtin8IsAllowed() {
    for (final Gtin8TestBean testBean : Gtin8TestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * gtin8 with wrong checksum.
   */
  public final void testWrongChecksumGtin8IsWrong() {
    for (final Gtin8TestBean testBean : Gtin8TestCases.getWrongTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.Gtin8Validator");
    }
  }

  /**
   * gtin8 which is to small.
   */
  public final void testToSmallGtin8IsWrong() {
    for (final Gtin8TestBean testBean : Gtin8TestCases.getToSmallTestBeans()) {
      super.validationTest(testBean, false, SIZE_VALIDATOR);
    }
  }

  /**
   * gtin8 which is to big.
   */
  public final void testToBigGtin8IsWrong() {
    for (final Gtin8TestBean testBean : Gtin8TestCases.getToBigTestBeans()) {
      super.validationTest(testBean, false, DIGITS_VALIDATOR);
    }
  }

  /**
   * gtin8 which is not numeric.
   */
  public final void testNotNumericGtin8IsWrong() {
    for (final Gtin8TestBean testBean : Gtin8TestCases.getNotNumericTestBeans()) {
      super.validationTest(testBean, false, DIGITS_VALIDATOR);
    }
  }
}
