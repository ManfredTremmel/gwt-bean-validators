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

import de.knightsoftnet.validators.shared.beans.NotEmptyIfOtherHasValueTestBean;
import de.knightsoftnet.validators.shared.testcases.NotEmptyIfOtherHasValueTestCases;

import org.junit.Test;

/**
 * test for not empty if other has value validator.
 *
 * @author Manfred Tremmel
 *
 */
public class NotEmptyIfOtherHasValueTest extends
    AbstractValidationTest<NotEmptyIfOtherHasValueTestBean> {

  /**
   * the value we request is not set, so everything is allowed.
   */
  @Test
  public final void testValueIsNotSetEverythingIsAllowed() {
    for (final NotEmptyIfOtherHasValueTestBean testBean : NotEmptyIfOtherHasValueTestCases
        .getValueIsNotSetBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * the value we request is set to a type, we do not request, so everything is allowed.
   */
  @Test
  public final void testValueIsSetDifferentEverythingIsAllowed() {
    for (final NotEmptyIfOtherHasValueTestBean testBean : NotEmptyIfOtherHasValueTestCases
        .getValueUncheckedSetBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * the value we request is set, and requested fields match.
   */
  @Test
  public final void testValueIsSetFieldsToAllowed() {
    for (final NotEmptyIfOtherHasValueTestBean testBean : NotEmptyIfOtherHasValueTestCases
        .getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * the value we request is set, and requested fields do not match.
   */
  @Test
  public final void testValueIsSetFieldsNotWrong() {
    for (final NotEmptyIfOtherHasValueTestBean testBean : NotEmptyIfOtherHasValueTestCases
        .getWrongNotEmptyTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.NotEmptyIfOtherHasValueValidator");
    }
  }
}
