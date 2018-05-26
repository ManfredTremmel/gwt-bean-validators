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

import de.knightsoftnet.validators.shared.beans.Gtin13TestBean;
import de.knightsoftnet.validators.shared.testcases.Gtin13TestCases;

import org.junit.Test;

/**
 * test for gtin 13 validator.
 *
 * @author Manfred Tremmel
 *
 */
public class Gtin13Test extends AbstractValidationTest<Gtin13TestBean> {

  /**
   * empty gtin13 is allowed.
   */
  @Test
  public final void testEmptyGtin13IsAllowed() {
    super.validationTest(Gtin13TestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct gtin13 is allowed.
   */
  @Test
  public final void testCorrectGtin13IsAllowed() {
    for (final Gtin13TestBean testBean : Gtin13TestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * gtin13 with wrong checksum.
   */
  @Test
  public final void testWrongChecksumGtin13IsWrong() {
    for (final Gtin13TestBean testBean : Gtin13TestCases.getWrongTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.Gtin13Validator");
    }
  }

  /**
   * gtin13 which is to small.
   */
  @Test
  public final void testToSmallGtin13IsWrong() {
    for (final Gtin13TestBean testBean : Gtin13TestCases.getToSmallTestBeans()) {
      super.validationTest(testBean, false, SIZE_VALIDATOR);
    }
  }

  /**
   * gtin13 which is to big.
   */
  @Test
  public final void testToBigGtin13IsWrong() {
    for (final Gtin13TestBean testBean : Gtin13TestCases.getToBigTestBeans()) {
      super.validationTest(testBean, false, DIGITS_VALIDATOR);
    }
  }

  /**
   * gtin13 which is not numeric.
   */
  @Test
  public final void testNotNumericGtin13IsWrong() {
    for (final Gtin13TestBean testBean : Gtin13TestCases.getNotNumericTestBeans()) {
      super.validationTest(testBean, false, DIGITS_VALIDATOR);
    }
  }
}
