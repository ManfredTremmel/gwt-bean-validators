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

import de.knightsoftnet.validators.shared.beans.LimitCharsetTestBean;
import de.knightsoftnet.validators.shared.testcases.LimitCharsetTestCases;

import org.junit.Test;

/**
 * test the limit charset validator.
 *
 * @author Manfred Tremmel
 *
 */
public class LimitCharsetTest extends AbstractValidationTest<LimitCharsetTestBean> {

  /**
   * empty texts is allowed.
   */
  @Test
  public final void testEmptyLimitCharsetIsAllowed() {
    super.validationTest(LimitCharsetTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct texts are allowed.
   */
  @Test
  public final void testCorrectLimitCharsetsAreAllowed() {
    for (final LimitCharsetTestBean testBean : LimitCharsetTestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * wrong texts are not allowed.
   */
  @Test
  public final void testWrongLimitCharsetsAreWrong() {
    for (final LimitCharsetTestBean testBean : LimitCharsetTestCases.getWrongTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.LimitCharsetValidator");
    }
  }
}
