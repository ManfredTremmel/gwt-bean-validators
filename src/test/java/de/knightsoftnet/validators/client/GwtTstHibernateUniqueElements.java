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

import de.knightsoftnet.validators.shared.beans.HibernateUniqueElementsTestBean;
import de.knightsoftnet.validators.shared.testcases.HibernateUniqueElementsTestCases;

/**
 * test for hibernate unique elements validator.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtTstHibernateUniqueElements
    extends AbstractValidationTst<HibernateUniqueElementsTestBean> {

  /**
   * empty not unique elements is allowed.
   */
  public final void testEmptyUniqueElementsIsAllowed() {
    super.validationTest(HibernateUniqueElementsTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct unique elements are allowed.
   */
  public final void testCorrectUniqueElementsAreAllowed() {
    for (final HibernateUniqueElementsTestBean testBean : HibernateUniqueElementsTestCases
        .getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * wrong unique elements are not allowed.
   */
  public final void testWrongUniqueElementsAreWrong() {
    for (final HibernateUniqueElementsTestBean testBean : HibernateUniqueElementsTestCases
        .getWrongTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.hv.UniqueElementsValidator");
    }
  }
}
