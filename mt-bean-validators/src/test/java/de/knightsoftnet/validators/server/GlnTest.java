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
import de.knightsoftnet.validators.shared.testcases.GlnTestCases;

import org.junit.Test;

/**
 * test for gln validator.
 *
 * @author Manfred Tremmel
 *
 */
public class GlnTest extends AbstractValidationTest<GlnTestBean> {

  /**
   * empty gln is allowed.
   */
  @Test
  public final void testEmptyGlnIsAllowed() {
    super.validationTest(GlnTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct gln is allowed.
   */
  @Test
  public final void testCorrectGlnIsAllowed() {
    for (final GlnTestBean testBean : GlnTestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * gln with wrong checksum.
   */
  @Test
  public final void testWrongChecksumGlnIsWrong() {
    for (final GlnTestBean testBean : GlnTestCases.getWrongTestBeans()) {
      super.validationTest(testBean, false, "de.knightsoftnet.validators.shared.impl.GlnValidator");
    }
  }

  /**
   * gln which is to small.
   */
  @Test
  public final void testToSmallGlnIsWrong() {
    for (final GlnTestBean testBean : GlnTestCases.getToSmallTestBeans()) {
      super.validationTest(testBean, false, SIZE_VALIDATOR);
    }
  }

  /**
   * gln which is to big.
   */
  @Test
  public final void testToBigGlnIsWrong() {
    for (final GlnTestBean testBean : GlnTestCases.getToBigTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.bv."
              + "DigitsValidatorForCharSequence");
    }
  }

  /**
   * gln which is not numeric.
   */
  @Test
  public final void testNotNumericGlnIsWrong() {
    for (final GlnTestBean testBean : GlnTestCases.getNotNumericTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.bv."
              + "DigitsValidatorForCharSequence");
    }
  }
}
