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
import de.knightsoftnet.validators.shared.testcases.GtinTestCases;

import org.junit.Test;

/**
 * test for gtin validator.
 *
 * @author Manfred Tremmel
 *
 */
public class GtinTest extends AbstractValidationTest<GtinTestBean> {

  /**
   * empty gtin is allowed.
   */
  @Test
  public final void testEmptyGtinIsAllowed() {
    super.validationTest(GtinTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct gtin is allowed.
   */
  @Test
  public final void testCorrectGtinIsAllowed() {
    for (final GtinTestBean testBean : GtinTestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * gtin with wrong checksum.
   */
  @Test
  public final void testWrongChecksumGtinIsWrong() {
    for (final GtinTestBean testBean : GtinTestCases.getWrongTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.GtinValidator");
    }
  }

  /**
   * gtin which has the wrong size.
   */
  @Test
  public final void testWrongSizeGtinIsWrong() {
    for (final GtinTestBean testBean : GtinTestCases.getWrongSizeTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.AlternateSizeValidator");
    }
  }

  /**
   * gtin which is not numeric.
   */
  @Test
  public final void testNotNumericGtinIsWrong() {
    for (final GtinTestBean testBean : GtinTestCases.getNotNumericTestBeans()) {
      super.validationTest(testBean, false, DIGITS_VALIDATOR);
    }
  }
}
