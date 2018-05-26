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

import de.knightsoftnet.validators.shared.beans.HibernateNegativeTestBean;
import de.knightsoftnet.validators.shared.testcases.HibernateNegativeTestCases;

/**
 * test for hibernate negative validator.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtTstHibernateNegative extends AbstractValidationTst<HibernateNegativeTestBean> {

  /**
   * empty negative is allowed.
   */
  public final void testEmptyNegativeIsAllowed() {
    super.validationTest(HibernateNegativeTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct negative are allowed.
   */
  public final void testCorrectNegativesAreAllowed() {
    for (final HibernateNegativeTestBean testBean : HibernateNegativeTestCases
        .getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * to small negative are not allowed.
   */
  public final void testWrongEntries() {
    for (final HibernateNegativeTestBean testBean : HibernateNegativeTestCases
        .getWrongTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.bv.number.sign."
              + "NegativeValidatorForBigDecimal");
    }
  }
}
