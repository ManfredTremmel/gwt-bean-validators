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

import de.knightsoftnet.validators.shared.beans.HibernatePeselTestBean;
import de.knightsoftnet.validators.shared.testcases.HibernatePeselTestCases;

/**
 * test for pesel validator.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtTstHibernatePesel extends AbstractValidationTst<HibernatePeselTestBean> {

  /**
   * empty pesel is allowed.
   */
  public final void testEmptyHibernatePeselIsAllowed() {
    super.validationTest(HibernatePeselTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct pesels are allowed.
   */
  public final void testCorrectHibernatePeselsAreAllowed() {
    for (final HibernatePeselTestBean testBean : HibernatePeselTestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * wrong pesels are not allowed.
   */
  public final void testWrongHibernatePeselsAreWrong() {
    for (final HibernatePeselTestBean testBean : HibernatePeselTestCases.getWrongTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.hv.pl.PESELValidator");
    }
  }
}
