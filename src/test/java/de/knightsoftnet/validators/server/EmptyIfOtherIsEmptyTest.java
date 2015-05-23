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

import de.knightsoftnet.validators.shared.beans.EmptyIfOtherIsEmptyTestBean;
import de.knightsoftnet.validators.shared.testcases.EmptyIfOtherIsEmptyTestCases;

import org.junit.Test;

/**
 * test for empty if other is empty validator.
 *
 * @author Manfred Tremmel
 *
 */
public class EmptyIfOtherIsEmptyTest extends AbstractValidationTest<EmptyIfOtherIsEmptyTestBean> {

  /**
   * both fields are not set, so it's ok.
   */
  @Test
  public final void testNoStreetNoNumberIsAllowed() {
    for (final EmptyIfOtherIsEmptyTestBean testBean : EmptyIfOtherIsEmptyTestCases
        .getEmptyTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * street is set, so street number can be what ever it wants.
   */
  @Test
  public final void testStreetFlexibleNumberIsAllowed() {
    for (final EmptyIfOtherIsEmptyTestBean testBean : EmptyIfOtherIsEmptyTestCases
        .getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * street number is set, but no street, that's wrong.
   */
  @Test
  public final void testNumberWithoutStreetWrong() {
    for (final EmptyIfOtherIsEmptyTestBean testBean : EmptyIfOtherIsEmptyTestCases
        .getWrongTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.EmptyIfOtherIsEmptyValidator");
    }
  }
}
