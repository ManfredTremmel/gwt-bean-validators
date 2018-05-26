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

import de.knightsoftnet.validators.shared.beans.MustBeBiggerDateTestBean;
import de.knightsoftnet.validators.shared.testcases.MustBeBiggerDateTestCases;

import org.junit.Test;

/**
 * test for must be bigger validator with date values.
 *
 * @author Manfred Tremmel
 *
 */
public class MustBeBiggerDateTest extends AbstractValidationTest<MustBeBiggerDateTestBean> {

  /**
   * both entries are empty, allowed.
   */
  @Test
  public final void testBothEmptyIsAllowed() {
    for (final MustBeBiggerDateTestBean testBean : MustBeBiggerDateTestCases.getEmptyTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * both entries are equal, allowed.
   */
  @Test
  public final void testBothEqualIsAllowed() {
    for (final MustBeBiggerDateTestBean testBean : MustBeBiggerDateTestCases
        .getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * both entries differ, wrong.
   */
  @Test
  public final void testBothDifferWrong() {
    for (final MustBeBiggerDateTestBean testBean : MustBeBiggerDateTestCases.getWrongTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.MustBeBiggerValidator");
    }
  }
}
