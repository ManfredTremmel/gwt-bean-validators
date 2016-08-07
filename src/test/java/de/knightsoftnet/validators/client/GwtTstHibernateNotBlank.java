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

import de.knightsoftnet.validators.shared.beans.HibernateNotBlankTestBean;
import de.knightsoftnet.validators.shared.testcases.HibernateNotBlankTestCases;

/**
 * test for hibernate not empty validator.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtTstHibernateNotBlank extends AbstractValidationTst<HibernateNotBlankTestBean> {

  /**
   * empty not blank is not allowed.
   */
  public final void testEmptyNotBlankIsWrong() {
    super.validationTest(HibernateNotBlankTestCases.getEmptyTestBean(), false,
        "org.hibernate.validator.internal.constraintvalidators.hv.NotBlankValidator");
  }

  /**
   * correct not blank is allowed.
   */
  public final void testCorrectNotBlankAreAllowed() {
    for (final HibernateNotBlankTestBean testBean : HibernateNotBlankTestCases
        .getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * wrong not blank is not allowed.
   */
  public final void testWrongNotBlankAreWrong() {
    for (final HibernateNotBlankTestBean testBean : HibernateNotBlankTestCases
        .getWrongtoSmallTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.hv.NotBlankValidator");
    }
  }
}
