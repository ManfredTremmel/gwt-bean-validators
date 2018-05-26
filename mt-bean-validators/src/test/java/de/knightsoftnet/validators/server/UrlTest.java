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

import de.knightsoftnet.validators.shared.beans.UrlTestBean;
import de.knightsoftnet.validators.shared.testcases.UrlTestCases;

import org.junit.Test;

/**
 * test for url validator.
 *
 * @author Manfred Tremmel
 *
 */
public class UrlTest extends AbstractValidationTest<UrlTestBean> {

  /**
   * empty url is allowed.
   */
  @Test
  public final void testEmptyUrlIsAllowed() {
    super.validationTest(UrlTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct urls are allowed.
   */
  @Test
  public final void testCorrectUrlsAreAllowed() {
    for (final UrlTestBean testBean : UrlTestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * wrong urls are not allowed.
   */
  @Test
  public final void testWrongUrlsAreWrong() {
    for (final UrlTestBean testBean : UrlTestCases.getWrongTestBeans()) {
      super.validationTest(testBean, false, "de.knightsoftnet.validators.shared.impl.UrlValidator");
    }
  }
}
