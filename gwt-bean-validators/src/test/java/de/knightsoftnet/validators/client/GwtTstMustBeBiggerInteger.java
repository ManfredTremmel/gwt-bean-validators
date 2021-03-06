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

package de.knightsoftnet.validators.client;

import de.knightsoftnet.validators.shared.beans.MustBeBiggerIntegerTestBean;
import de.knightsoftnet.validators.shared.testcases.MustBeBiggerIntegerTestCases;

/**
 * test for must be bigger validator with integer values.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtTstMustBeBiggerInteger extends AbstractValidationTst<MustBeBiggerIntegerTestBean> {

  /**
   * both entries are empty, allowed.
   */
  public final void testBothEmptyIsAllowed() {
    for (final MustBeBiggerIntegerTestBean testBean : MustBeBiggerIntegerTestCases
        .getEmptyTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * both entries are equal, allowed.
   */
  public final void testBothEqualIsAllowed() {
    for (final MustBeBiggerIntegerTestBean testBean : MustBeBiggerIntegerTestCases
        .getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * both entries differ, wrong.
   */
  public final void testBothDifferWrong() {
    for (final MustBeBiggerIntegerTestBean testBean : MustBeBiggerIntegerTestCases
        .getWrongTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.MustBeBiggerValidator");
    }
  }
}
