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

import de.knightsoftnet.validators.shared.beans.Isbn13FormatedTestBean;
import de.knightsoftnet.validators.shared.testcases.Isbn13FormatedTestCases;

import org.junit.Test;

/**
 * test for isbn 13 formated.
 *
 * @author Manfred Tremmel
 *
 */
public class Isbn13FormatedTest extends AbstractValidationTest<Isbn13FormatedTestBean> {

  /**
   * empty isbn13 is allowed.
   */
  @Test
  public final void testEmptyIsbn13IsAllowed() {
    super.validationTest(Isbn13FormatedTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct isbn13 is allowed.
   */
  @Test
  public final void testCorrectIsbn13IsAllowed() {
    for (final Isbn13FormatedTestBean testBean : Isbn13FormatedTestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * isbn13 with wrong checksum.
   */
  @Test
  public final void testWrongChecksumIsbn13IsWrong() {
    for (final Isbn13FormatedTestBean testBean : Isbn13FormatedTestCases.getWrongTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.Isbn13FormatedValidator");
    }
  }

  /**
   * isbn13 which is to small.
   */
  @Test
  public final void testToSmallIsbn13IsWrong() {
    for (final Isbn13FormatedTestBean testBean : Isbn13FormatedTestCases.getToSmallTestBeans()) {
      super.validationTest(testBean, false, SIZE_VALIDATOR);
    }
  }

  /**
   * isbn13 which is to big.
   */
  @Test
  public final void testToBigIsbn13IsWrong() {
    for (final Isbn13FormatedTestBean testBean : Isbn13FormatedTestCases.getToBigTestBeans()) {
      super.validationTest(testBean, false, SIZE_VALIDATOR);
    }
  }

  /**
   * isbn13 which has wrong format.
   */
  @Test
  public final void testWrongFormatIsbn13IsWrong() {
    for (final Isbn13FormatedTestBean testBean : Isbn13FormatedTestCases
        .getWrongFormatedTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.Isbn13FormatedValidator");
    }
  }
}
