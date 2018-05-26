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

import de.knightsoftnet.validators.shared.beans.NotEmptyAlternateIfOtherIsNotEmptyTestBean;
import de.knightsoftnet.validators.shared.testcases.NotEmptyAlternateIfOinTestCases;

import org.junit.Test;

/**
 * test for not empty alternate if other is not empty validator.
 *
 * @author Manfred Tremmel
 *
 */
public class NotEmptyAlternateIfOtherIsNotEmptyTest extends
    AbstractValidationTest<NotEmptyAlternateIfOtherIsNotEmptyTestBean> {

  /**
   * the compare field is empty, alternate fields can be filled in every way.
   */
  @Test
  public final void testCompareIsEmptyAlternateEverythingIsAllowed() {
    for (final NotEmptyAlternateIfOtherIsNotEmptyTestBean testBean : NotEmptyAlternateIfOinTestCases
        .getCompareFieldEmptyBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * the compare field is filled, one of the alternate field (or both) have to be set.
   */
  @Test
  public final void testCompareIsFilledOneOfTheAlternateHasTobEeSetAllowed() {
    for (final NotEmptyAlternateIfOtherIsNotEmptyTestBean testBean : NotEmptyAlternateIfOinTestCases
        .getCompareFieldSetOkBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * the compare field is set and both alternate fields are empty.
   */
  @Test
  public final void testCompareIsFilledBothAlternatesAreEmptyWrong() {
    for (final NotEmptyAlternateIfOtherIsNotEmptyTestBean testBean : NotEmptyAlternateIfOinTestCases
        .getWrongEmptyTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.NotEmptyAlternateIfOtherIsNotEmptyValidator");
    }
  }
}
