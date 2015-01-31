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

import de.knightsoftnet.validators.shared.beans.PostalCodeTestBean;
import de.knightsoftnet.validators.shared.testcases.PostalCodeTestCases;

public class GwtTstPostalCode extends AbstractValidationTst<PostalCodeTestBean> {


  /**
   * empty postal codes are allowed.
   */
  public final void testEmptyPostalCodeIsAllowed() {
    super.validationTest(PostalCodeTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct postal codes are allowed.
   */
  public final void testCorrectPostalCodeIsAllowed() {
    for (final PostalCodeTestBean testBean : PostalCodeTestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * correct postal codes not matching country rules.
   */
  public final void testWrongPostalCodeIsWrong() {
    for (final PostalCodeTestBean testBean : PostalCodeTestCases.getWrongTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.PostalCodeValidator");
    }
  }
}