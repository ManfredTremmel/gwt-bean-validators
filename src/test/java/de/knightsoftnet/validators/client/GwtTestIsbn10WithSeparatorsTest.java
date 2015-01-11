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

import de.knightsoftnet.validators.shared.beans.Isbn10WithSeparatorsTestBean;
import de.knightsoftnet.validators.shared.testcases.Isbn10WithSeparatorsTestCases;

public class GwtTestIsbn10WithSeparatorsTest extends
    AbstractValidationTest<Isbn10WithSeparatorsTestBean> {


  /**
   * empty isbn10 is allowed.
   */
  public final void testEmptyIsbn10IsAllowed() {
    super.validationTest(Isbn10WithSeparatorsTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct isbn10 is allowed.
   */
  public final void testCorrectIsbn10IsAllowed() {
    for (final Isbn10WithSeparatorsTestBean testBean : Isbn10WithSeparatorsTestCases
        .getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * isbn10 with wrong checksum.
   */
  public final void testWrongChecksumIsbn10IsWrong() {
    for (final Isbn10WithSeparatorsTestBean testBean : Isbn10WithSeparatorsTestCases
        .getWrongTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.Isbn10Validator");
    }
  }

  /**
   * isbn10 which is to small.
   */
  public final void testToSmallIsbn10IsWrong() {
    for (final Isbn10WithSeparatorsTestBean testBean : Isbn10WithSeparatorsTestCases
        .getToSmallTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.SizeWithoutSeparatorsValidator");
    }
  }

  /**
   * isbn10 which is to big.
   */
  public final void testToBigIsbn10IsWrong() {
    for (final Isbn10WithSeparatorsTestBean testBean : Isbn10WithSeparatorsTestCases
        .getToBigTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.SizeWithoutSeparatorsValidator");
    }
  }

  /**
   * isbn10 which is not numeric.
   */
  public final void testNotNumericIsbn10IsWrong() {
    for (final Isbn10WithSeparatorsTestBean testBean : Isbn10WithSeparatorsTestCases
        .getNotNumericTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.Isbn10Validator");
    }
  }
}
