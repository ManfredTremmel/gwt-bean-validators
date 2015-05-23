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

import de.knightsoftnet.validators.shared.beans.NotEmptyIfOtherIsEmptyTestBean;
import de.knightsoftnet.validators.shared.testcases.NotEmptyIfOtherIsEmptyTestCases;

import org.junit.Test;

/**
 * test for not empty if other is empty validator.
 *
 * @author Manfred Tremmel
 *
 */
public class NotEmptyIfOtherIsEmptyTest extends
    AbstractValidationTest<NotEmptyIfOtherIsEmptyTestBean> {

  /**
   * both are filled is allowed.
   */
  @Test
  public final void testBothFilledIsAllowed() {
    for (final NotEmptyIfOtherIsEmptyTestBean testBean : NotEmptyIfOtherIsEmptyTestCases
        .getBothFilledBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * alternate fill is allowed.
   */
  @Test
  public final void testAlternateFillIsAllowed() {
    for (final NotEmptyIfOtherIsEmptyTestBean testBean : NotEmptyIfOtherIsEmptyTestCases
        .getAlternateFilledBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * both entries are not allowed to be empty.
   */
  @Test
  public final void testBothEmptyIsWrong() {
    for (final NotEmptyIfOtherIsEmptyTestBean testBean : NotEmptyIfOtherIsEmptyTestCases
        .getWrongEmptyTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.NotEmptyIfOtherIsEmptyValidator");
    }
  }
}
