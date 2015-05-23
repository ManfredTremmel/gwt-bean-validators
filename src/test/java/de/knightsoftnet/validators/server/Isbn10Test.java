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

import de.knightsoftnet.validators.shared.beans.Isbn10TestBean;
import de.knightsoftnet.validators.shared.testcases.Isbn10TestCases;

import org.junit.Test;

/**
 * test for isbn 10 validator.
 *
 * @author Manfred Tremmel
 *
 */
public class Isbn10Test extends AbstractValidationTest<Isbn10TestBean> {

  /**
   * empty isbn10 is allowed.
   */
  @Test
  public final void testEmptyIsbn10IsAllowed() {
    super.validationTest(Isbn10TestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct isbn10 is allowed.
   */
  @Test
  public final void testCorrectIsbn10IsAllowed() {
    for (final Isbn10TestBean testBean : Isbn10TestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * isbn10 with wrong checksum.
   */
  @Test
  public final void testWrongChecksumIsbn10IsWrong() {
    for (final Isbn10TestBean testBean : Isbn10TestCases.getWrongTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.Isbn10Validator");
    }
  }

  /**
   * isbn10 which is to small.
   */
  @Test
  public final void testToSmallIsbn10IsWrong() {
    for (final Isbn10TestBean testBean : Isbn10TestCases.getToSmallTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.SizeWithoutSeparatorsValidator");
    }
  }

  /**
   * isbn10 which is to big.
   */
  @Test
  public final void testToBigIsbn10IsWrong() {
    for (final Isbn10TestBean testBean : Isbn10TestCases.getToBigTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.SizeWithoutSeparatorsValidator");
    }
  }

  /**
   * isbn10 which is not numeric.
   */
  @Test
  public final void testNotNumericIsbn10IsWrong() {
    for (final Isbn10TestBean testBean : Isbn10TestCases.getNotNumericTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.Isbn10Validator");
    }
  }
}
