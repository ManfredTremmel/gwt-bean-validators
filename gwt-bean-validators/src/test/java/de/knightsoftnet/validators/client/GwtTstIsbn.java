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

import de.knightsoftnet.validators.shared.beans.IsbnTestBean;
import de.knightsoftnet.validators.shared.testcases.IsbnTestCases;

/**
 * test for isbn validator.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtTstIsbn extends AbstractValidationTst<IsbnTestBean> {

  /**
   * empty isbn is allowed.
   */
  public final void testEmptyIsbnIsAllowed() {
    super.validationTest(IsbnTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct isbn is allowed.
   */
  public final void testCorrectIsbnIsAllowed() {
    for (final IsbnTestBean testBean : IsbnTestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * isbn with wrong checksum.
   */
  public final void testWrongChecksumIsbnIsWrong() {
    for (final IsbnTestBean testBean : IsbnTestCases.getWrongTestBeans()) {
      super
          .validationTest(testBean, false, "de.knightsoftnet.validators.shared.impl.IsbnValidator");
    }
  }

  /**
   * isbn size is not valid.
   */
  public final void testWrongSizeIsbnIsWrong() {
    for (final IsbnTestBean testBean : IsbnTestCases.getWrongSizeTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.AlternateSizeValidator");
    }
  }

  /**
   * isbn which is not numeric.
   */
  public final void testNotNumericIsbnIsWrong() {
    for (final IsbnTestBean testBean : IsbnTestCases.getNotNumericTestBeans()) {
      super
          .validationTest(testBean, false, "de.knightsoftnet.validators.shared.impl.IsbnValidator");
    }
  }
}
