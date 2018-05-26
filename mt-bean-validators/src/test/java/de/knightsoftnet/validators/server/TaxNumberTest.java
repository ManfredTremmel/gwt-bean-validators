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

import de.knightsoftnet.validators.shared.beans.TaxNumberTestBean;
import de.knightsoftnet.validators.shared.testcases.TaxNumberTestCases;

import org.junit.Test;

/**
 * test for taxNumber validator.
 *
 * @author Manfred Tremmel
 *
 */
public class TaxNumberTest extends AbstractValidationTest<TaxNumberTestBean> {

  /**
   * empty tax number is allowed.
   */
  @Test
  public final void testEmptyTaxNumberIsAllowed() {
    super.validationTest(TaxNumberTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct taxNumber's are allowed.
   */
  @Test
  public final void testCorrectTaxNumberIsAllowed() {
    for (final TaxNumberTestBean testBean : TaxNumberTestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * taxNumber's with wrong checksumms are not allowed.
   */
  @Test
  public final void testWrongChecksumTaxNumberIsWrong() {
    for (final TaxNumberTestBean testBean : TaxNumberTestCases.getWrongChecksumTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.TaxNumberValidator");
    }
  }

  /**
   * correct vat taxNumber matching country rules.
   */
  @Test
  public final void testWrongTaxNumberIsWrong() {
    for (final TaxNumberTestBean testBean : TaxNumberTestCases.getWrongCountryTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.TaxNumberValidator");
    }
  }
}
