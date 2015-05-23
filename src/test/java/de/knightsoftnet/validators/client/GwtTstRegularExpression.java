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

import de.knightsoftnet.validators.shared.beans.RegExTestBean;
import de.knightsoftnet.validators.shared.testcases.RegExTestCases;

/**
 * test for regular expression validator.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtTstRegularExpression extends AbstractValidationTst<RegExTestBean> {

  /**
   * empty regular expression is allowed.
   */
  public final void testEmptyRecExIsAllowed() {
    super.validationTest(RegExTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct regular expressions are allowed.
   */
  public final void testCorrectRegExAreAllowed() {
    for (final RegExTestBean testBean : RegExTestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * wrong regular expressions are not allowed.
   */
  public final void testWrongRegExAreWrong() {
    for (final RegExTestBean testBean : RegExTestCases.getWrongTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.RegularExpressionValidator");
    }
  }
}
