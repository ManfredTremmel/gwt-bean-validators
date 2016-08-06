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

import de.knightsoftnet.validators.shared.beans.HibernateNullTestBean;
import de.knightsoftnet.validators.shared.testcases.HibernateNullTestCases;

/**
 * test for hibernate null validator.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtTstHibernateNull extends AbstractValidationTst<HibernateNullTestBean> {

  /**
   * empty not null is allowed.
   */
  public final void testEmptyNullIsAllowed() {
    super.validationTest(HibernateNullTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct null are allowed.
   */
  public final void testCorrectNullAreAllowed() {
    for (final HibernateNullTestBean testBean : HibernateNullTestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * wrong null are not allowed.
   */
  public final void testWrongNullAreWrong() {
    for (final HibernateNullTestBean testBean : HibernateNullTestCases.getWrongtoSmallTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.bv.NullValidator");
    }
  }
}
