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

import de.knightsoftnet.validators.shared.beans.IsbnWithSeparatorsTestBean;
import de.knightsoftnet.validators.shared.testcases.IsbnWithSeparatorsTestCases;

import org.junit.Test;

/**
 * test for isbn with separators validator.
 *
 * @author Manfred Tremmel
 *
 */
public class IsbnWithSeparatorsTest extends AbstractValidationTest<IsbnWithSeparatorsTestBean> {

  /**
   * empty isbn is allowed.
   */
  @Test
  public final void testEmptyIsbnIsAllowed() {
    super.validationTest(IsbnWithSeparatorsTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct isbn is allowed.
   */
  @Test
  public final void testCorrectIsbnIsAllowed() {
    for (final IsbnWithSeparatorsTestBean testBean : IsbnWithSeparatorsTestCases
        .getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * isbn with wrong checksum.
   */
  @Test
  public final void testWrongChecksumIsbnIsWrong() {
    for (final IsbnWithSeparatorsTestBean testBean : IsbnWithSeparatorsTestCases
        .getWrongTestBeans()) {
      super
          .validationTest(testBean, false, "de.knightsoftnet.validators.shared.impl.IsbnValidator");
    }
  }

  /**
   * isbn size is not valid.
   */
  @Test
  public final void testWrongSizeIsbnIsWrong() {
    for (final IsbnWithSeparatorsTestBean testBean : IsbnWithSeparatorsTestCases
        .getWrongSizeTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.AlternateSizeValidator");
    }
  }

  /**
   * isbn which is not numeric.
   */
  @Test
  public final void testNotNumericIsbnIsWrong() {
    for (final IsbnWithSeparatorsTestBean testBean : IsbnWithSeparatorsTestCases
        .getNotNumericTestBeans()) {
      super
          .validationTest(testBean, false, "de.knightsoftnet.validators.shared.impl.IsbnValidator");
    }
  }
}
