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

import de.knightsoftnet.validators.shared.beans.HibernateRangeTestBean;
import de.knightsoftnet.validators.shared.testcases.HibernateRangeTestCases;

/**
 * test for hibernate range validator.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtTstHibernateRange extends AbstractValidationTst<HibernateRangeTestBean> {

  /**
   * empty min max is allowed.
   */
  public final void testEmptyRangeIsAllowed() {
    super.validationTest(HibernateRangeTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct min max are allowed.
   */
  public final void testCorrectRangesAreAllowed() {
    for (final HibernateRangeTestBean testBean : HibernateRangeTestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * to small min max are not allowed.
   */
  public final void testToSmallRangesAreWrong() {
    for (final HibernateRangeTestBean testBean : HibernateRangeTestCases
        .getWrongtoSmallTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.bv.number.bound."
              + "MinValidatorForBigDecimal");
    }
  }

  /**
   * to big min max are not allowed.
   */
  public final void testToBigRangesAreWrong() {
    for (final HibernateRangeTestBean testBean : HibernateRangeTestCases.getWrongtoBigTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.bv.number.bound."
              + "MaxValidatorForBigDecimal");
    }
  }
}
