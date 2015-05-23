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

import de.knightsoftnet.validators.shared.beans.VatIdTestBean;
import de.knightsoftnet.validators.shared.testcases.VatIdTestCases;

import org.junit.Test;

/**
 * test for vat id validator.
 *
 * @author Manfred Tremmel
 *
 */
public class VatIdTest extends AbstractValidationTest<VatIdTestBean> {

  /**
   * empty vat id is allowed.
   */
  @Test
  public final void testEmptyVatIdIsAllowed() {
    super.validationTest(VatIdTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct vat id's are allowed.
   */
  @Test
  public final void testCorrectVatIdIsAllowed() {
    for (final VatIdTestBean testBean : VatIdTestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * vat id's with wrong checksumms are not allowed.
   */
  @Test
  public final void testWrongChecksumVatIdIsWrong() {
    for (final VatIdTestBean testBean : VatIdTestCases.getWrongChecksumTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.VatIdValidator");
    }
  }

  /**
   * correct vat id not matching country rules.
   */
  @Test
  public final void testWrongVatIdIsWrong() {
    for (final VatIdTestBean testBean : VatIdTestCases.getWrongCountryTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.VatIdValidator");
    }
  }
}
