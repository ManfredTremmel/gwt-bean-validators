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

import de.knightsoftnet.validators.shared.beans.HibernatePositiveOrZeroTestBean;
import de.knightsoftnet.validators.shared.testcases.HibernatePositiveOrZeroTestCases;

/**
 * test for hibernate PositiveOrZero validator.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtTstHibernatePositiveOrZero
    extends AbstractValidationTst<HibernatePositiveOrZeroTestBean> {

  /**
   * empty PositiveOrZero is allowed.
   */
  public final void testEmptyPositiveOrZeroIsAllowed() {
    super.validationTest(HibernatePositiveOrZeroTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct PositiveOrZero are allowed.
   */
  public final void testCorrectPositiveOrZerosAreAllowed() {
    for (final HibernatePositiveOrZeroTestBean testBean : HibernatePositiveOrZeroTestCases
        .getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * to small PositiveOrZero are not allowed.
   */
  public final void testWrongEntries() {
    for (final HibernatePositiveOrZeroTestBean testBean : HibernatePositiveOrZeroTestCases
        .getWrongTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.bv.number.sign."
              + "PositiveOrZeroValidatorForBigDecimal");
    }
  }
}
