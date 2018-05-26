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

import de.knightsoftnet.validators.shared.beans.HibernateDecimalMinMaxTestBean;
import de.knightsoftnet.validators.shared.testcases.HibernateDecimalMinMaxTestCases;

/**
 * test for hibernate decimal min max validator.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtTstHibernateDecimalMinMax
    extends AbstractValidationTst<HibernateDecimalMinMaxTestBean> {

  /**
   * empty decimal min max is allowed.
   */
  public final void testEmptyDecimalMinMaxIsAllowed() {
    super.validationTest(HibernateDecimalMinMaxTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct decimal min max are allowed.
   */
  public final void testCorrectDecimalMinMaxsAreAllowed() {
    for (final HibernateDecimalMinMaxTestBean testBean : HibernateDecimalMinMaxTestCases
        .getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * to small decimal min max are not allowed.
   */
  public final void testToSmallDecimalMinMaxsAreWrong() {
    for (final HibernateDecimalMinMaxTestBean testBean : HibernateDecimalMinMaxTestCases
        .getWrongtoSmallTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.bv.number.bound.decimal."
              + "DecimalMinValidatorForBigDecimal");
    }
  }

  /**
   * to big decimal min max are not allowed.
   */
  public final void testToBigDecimalMinMaxsAreWrong() {
    for (final HibernateDecimalMinMaxTestBean testBean : HibernateDecimalMinMaxTestCases
        .getWrongtoBigTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.bv.number.bound.decimal."
              + "DecimalMaxValidatorForBigDecimal");
    }
  }
}
