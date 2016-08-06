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

import de.knightsoftnet.validators.shared.beans.HibernateMinMaxTestBean;
import de.knightsoftnet.validators.shared.testcases.HibernateMinMaxTestCases;

/**
 * test for hibernate min max validator.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtTstHibernateMinMax extends AbstractValidationTst<HibernateMinMaxTestBean> {

  /**
   * empty min max is allowed.
   */
  public final void testEmptyMinMaxIsAllowed() {
    super.validationTest(HibernateMinMaxTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct min max are allowed.
   */
  public final void testCorrectMinMaxsAreAllowed() {
    for (final HibernateMinMaxTestBean testBean : HibernateMinMaxTestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * to small min max are not allowed.
   */
  public final void testToSmallMinMaxsAreWrong() {
    for (final HibernateMinMaxTestBean testBean : HibernateMinMaxTestCases
        .getWrongtoSmallTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.bv.MinValidatorForNumber");
    }
  }

  /**
   * to big min max are not allowed.
   */
  public final void testToBigMinMaxsAreWrong() {
    for (final HibernateMinMaxTestBean testBean : HibernateMinMaxTestCases
        .getWrongtoBigTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.bv.MaxValidatorForNumber");
    }
  }
}
