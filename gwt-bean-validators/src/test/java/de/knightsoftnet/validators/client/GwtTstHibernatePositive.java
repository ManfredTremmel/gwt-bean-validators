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

import de.knightsoftnet.validators.shared.beans.HibernatePositiveTestBean;
import de.knightsoftnet.validators.shared.testcases.HibernatePositiveTestCases;

/**
 * test for hibernate Positive validator.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtTstHibernatePositive extends AbstractValidationTst<HibernatePositiveTestBean> {

  /**
   * empty Positive is allowed.
   */
  public final void testEmptyPositiveIsAllowed() {
    super.validationTest(HibernatePositiveTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct Positive are allowed.
   */
  public final void testCorrectPositivesAreAllowed() {
    for (final HibernatePositiveTestBean testBean : HibernatePositiveTestCases
        .getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * to small Positive are not allowed.
   */
  public final void testWrongEntries() {
    for (final HibernatePositiveTestBean testBean : HibernatePositiveTestCases
        .getWrongTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.bv.number.sign."
              + "PositiveValidatorForBigDecimal");
    }
  }
}
