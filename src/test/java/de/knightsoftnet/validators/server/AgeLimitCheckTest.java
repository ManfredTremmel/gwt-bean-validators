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

import de.knightsoftnet.validators.shared.beans.AgeLimitTestBean;
import de.knightsoftnet.validators.shared.testcases.AgeLimitTestCases;

import org.junit.Test;

/**
 * age limit check test.
 *
 * @author Manfred Tremmel
 *
 */
public class AgeLimitCheckTest extends AbstractValidationTest<AgeLimitTestBean> {

  /**
   * empty value is allowed.
   */
  @Test
  public final void testEmptyAgeIsAllowed() {
    super.validationTest(AgeLimitTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct ages are allowed.
   */
  @Test
  public final void testCorrectAgesAreAllowed() {
    for (final AgeLimitTestBean testBean : AgeLimitTestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * wrong ages are not allowed.
   */
  @Test
  public final void testWrongAgesSizeAreWrong() {
    for (final AgeLimitTestBean testBean : AgeLimitTestCases.getWrongTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.AgeLimitCheckValidator");
    }
  }
}
