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

import de.knightsoftnet.validators.shared.beans.BicWithSpacesTestBean;
import de.knightsoftnet.validators.shared.testcases.BicWithSpacesTestCases;

import org.junit.Test;

/**
 * test for bic with spaces validator.
 *
 * @author Manfred Tremmel
 *
 */
public class BicWithSpacesTest extends AbstractValidationTest<BicWithSpacesTestBean> {

  /**
   * empty bic is allowed.
   */
  @Test
  public final void testEmptyBicIsAllowed() {
    super.validationTest(BicWithSpacesTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct bics are allowed.
   */
  @Test
  public final void testCorrectBicIsAllowed() {
    for (final BicWithSpacesTestBean testBean : BicWithSpacesTestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * bic with country which is not part of SEPA country list.
   */
  @Test
  public final void testWrongCountryBicIsWrong() {
    for (final BicWithSpacesTestBean testBean : BicWithSpacesTestCases.getWrongCountryTestBeans()) {
      super.validationTest(testBean, false, "de.knightsoftnet.validators.shared.impl.BicValidator");
    }
  }

  /**
   * bic with wrong format.
   */
  @Test
  public final void testWrongFormatBicIsWrong() {
    for (final BicWithSpacesTestBean testBean : BicWithSpacesTestCases.getWrongFormatTestBeans()) {
      super.validationTest(testBean, false, "de.knightsoftnet.validators.shared.impl.BicValidator");
    }
  }

  /**
   * bic is to short.
   */
  @Test
  public final void testToShortBicIsWrong() {
    for (final BicWithSpacesTestBean testBean : BicWithSpacesTestCases.getWrongToShortTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.AlternateSizeValidator");
    }
  }

  /**
   * bic is to long.
   */
  @Test
  public final void testToLongBicIsWrong() {
    for (final BicWithSpacesTestBean testBean : BicWithSpacesTestCases.getWrongToBigTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.AlternateSizeValidator");
    }
  }
}
