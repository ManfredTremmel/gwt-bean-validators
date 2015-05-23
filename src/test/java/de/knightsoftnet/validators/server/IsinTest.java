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

import de.knightsoftnet.validators.shared.beans.IsinTestBean;
import de.knightsoftnet.validators.shared.testcases.IsinTestCases;

import org.junit.Test;

/**
 * test for isin validator.
 *
 * @author Manfred Tremmel
 *
 */
public class IsinTest extends AbstractValidationTest<IsinTestBean> {

  /**
   * empty isin is allowed.
   */
  @Test
  public final void testEmptyIsinIsAllowed() {
    super.validationTest(IsinTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct isin is allowed.
   */
  @Test
  public final void testCorrectIsinIsAllowed() {
    for (final IsinTestBean testBean : IsinTestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * isin with wrong checksum.
   */
  @Test
  public final void testWrongChecksumIsinIsWrong() {
    for (final IsinTestBean testBean : IsinTestCases.getWrongTestBeans()) {
      super
          .validationTest(testBean, false, "de.knightsoftnet.validators.shared.impl.IsinValidator");
    }
  }

  /**
   * isin size is not valid.
   */
  @Test
  public final void testWrongSizeIsinIsWrong() {
    for (final IsinTestBean testBean : IsinTestCases.getWrongSizeTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.constraints.impl.SizeValidatorForString");
    }
  }
}
