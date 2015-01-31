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

import de.knightsoftnet.validators.shared.beans.Isbn13WithSeparatorsTestBean;
import de.knightsoftnet.validators.shared.testcases.Isbn13WithSeparatorsTestCases;

/**
 * test for isbn 13 validator.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtTstIsbn13WithSeparators extends
    AbstractValidationTst<Isbn13WithSeparatorsTestBean> {


  /**
   * empty isbn13 is allowed.
   */
  public final void testEmptyIsbn13IsAllowed() {
    super.validationTest(Isbn13WithSeparatorsTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct isbn13 is allowed.
   */
  public final void testCorrectIsbn13IsAllowed() {
    for (final Isbn13WithSeparatorsTestBean testBean : Isbn13WithSeparatorsTestCases
        .getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * isbn13 with wrong checksum.
   */
  public final void testWrongChecksumIsbn13IsWrong() {
    for (final Isbn13WithSeparatorsTestBean testBean : Isbn13WithSeparatorsTestCases
        .getWrongTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.Isbn13Validator");
    }
  }

  /**
   * isbn13 which is to small.
   */
  public final void testToSmallIsbn13IsWrong() {
    for (final Isbn13WithSeparatorsTestBean testBean : Isbn13WithSeparatorsTestCases
        .getToSmallTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.SizeWithoutSeparatorsValidator");
    }
  }

  /**
   * isbn13 which is to big.
   */
  public final void testToBigIsbn13IsWrong() {
    for (final Isbn13WithSeparatorsTestBean testBean : Isbn13WithSeparatorsTestCases
        .getToBigTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.SizeWithoutSeparatorsValidator");
    }
  }

  /**
   * isbn13 which is not numeric.
   */
  public final void testNotNumericIsbn13IsWrong() {
    for (final Isbn13WithSeparatorsTestBean testBean : Isbn13WithSeparatorsTestCases
        .getNotNumericTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.Isbn13Validator");
    }
  }
}
