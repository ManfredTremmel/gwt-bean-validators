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

import de.knightsoftnet.validators.shared.beans.NotEmptyIfOtherIsNotEmptyTestBean;
import de.knightsoftnet.validators.shared.testcases.NotEmptyIfOtherIsNotEmptyTestCases;

import org.junit.Test;

/**
 * test for not empty if other is net empty validator.
 *
 * @author Manfred Tremmel
 *
 */
public class NotEmptyIfOtherIsNotEmptyTest extends
    AbstractValidationTest<NotEmptyIfOtherIsNotEmptyTestBean> {

  /**
   * both fields are not set, so it's ok.
   */
  @Test
  public final void testNoStreetNoNumberIsAllowed() {
    for (final NotEmptyIfOtherIsNotEmptyTestBean testBean : NotEmptyIfOtherIsNotEmptyTestCases
        .getBothEmptyBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * street is set, so street number can be what ever it wants.
   */
  @Test
  public final void testStreetFlexibleNumberIsAllowed() {
    for (final NotEmptyIfOtherIsNotEmptyTestBean testBean : NotEmptyIfOtherIsNotEmptyTestCases
        .getCompareNotEmptyBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * street number is set, but no street, that's wrong.
   */
  @Test
  public final void testNumberWithoutStreetWrong() {
    for (final NotEmptyIfOtherIsNotEmptyTestBean testBean : NotEmptyIfOtherIsNotEmptyTestCases
        .getWrongCompareEmptyTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.NotEmptyIfOtherIsNotEmptyValidator");
    }
  }
}
