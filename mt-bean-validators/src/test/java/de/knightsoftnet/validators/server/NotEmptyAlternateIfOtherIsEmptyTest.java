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

import de.knightsoftnet.validators.shared.beans.NotEmptyAlternateIfOtherIsEmptyTestBean;
import de.knightsoftnet.validators.shared.testcases.NotEmptyAlternateIfOieTestCases;

import org.junit.Test;

/**
 * test for not empty alternate if other is empty validator.
 *
 * @author Manfred Tremmel
 *
 */
public class NotEmptyAlternateIfOtherIsEmptyTest extends
    AbstractValidationTest<NotEmptyAlternateIfOtherIsEmptyTestBean> {

  /**
   * the compare field is set, alternate fields can be filled in every way.
   */
  @Test
  public final void testCompareIsSetAlternateEverythingIsAllowed() {
    for (final NotEmptyAlternateIfOtherIsEmptyTestBean testBean : NotEmptyAlternateIfOieTestCases
        .getCompareFieldSetOkBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * the compare field is empty, one of the alternate field (or both) have to be set.
   */
  @Test
  public final void testCompareIsEmptyOneOfTheAlternateHasTobEeSetAllowed() {
    for (final NotEmptyAlternateIfOtherIsEmptyTestBean testBean : NotEmptyAlternateIfOieTestCases
        .getCompareFieldEmptyOkBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * the compare field is empty and both alternate fields are empty.
   */
  @Test
  public final void testCompareIsEmptyBothAlternatesAreEmptyWrong() {
    for (final NotEmptyAlternateIfOtherIsEmptyTestBean testBean : NotEmptyAlternateIfOieTestCases
        .getWrongEmptyTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.NotEmptyAlternateIfOtherIsEmptyValidator");
    }
  }
}
