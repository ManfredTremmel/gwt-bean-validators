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

import de.knightsoftnet.validators.shared.beans.MustBeEqualTestBean;
import de.knightsoftnet.validators.shared.testcases.MustNotBeEqualTestCases;

import org.junit.Test;

/**
 * test for must not be equal validator.
 *
 * @author Manfred Tremmel
 *
 */
public class MustNotBeEqualTest extends AbstractValidationTest<MustBeEqualTestBean> {

  /**
   * both entries are empty, allowed.
   */
  @Test
  public final void testBothEmptyIsAllowed() {
    for (final MustBeEqualTestBean testBean : MustNotBeEqualTestCases.getEmptyTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * both entries are equal, allowed.
   */
  @Test
  public final void testBothEqualIsAllowed() {
    for (final MustBeEqualTestBean testBean : MustNotBeEqualTestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * both entries differ, wrong.
   */
  @Test
  public final void testBothDifferWrong() {
    for (final MustBeEqualTestBean testBean : MustNotBeEqualTestCases.getWrongTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.MustNotBeEqualValidator");
    }
  }
}
