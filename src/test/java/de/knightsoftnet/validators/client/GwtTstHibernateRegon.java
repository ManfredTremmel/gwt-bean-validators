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

import de.knightsoftnet.validators.shared.beans.HibernateRegonTestBean;
import de.knightsoftnet.validators.shared.testcases.HibernateRegonTestCases;

/**
 * test for regon validator.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtTstHibernateRegon extends AbstractValidationTst<HibernateRegonTestBean> {

  /**
   * empty regon is allowed.
   */
  public final void testEmptyHibernateRegonIsAllowed() {
    super.validationTest(HibernateRegonTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct regons are allowed.
   */
  public final void testCorrectHibernateRegonsAreAllowed() {
    for (final HibernateRegonTestBean testBean : HibernateRegonTestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * wrong regons are not allowed.
   */
  public final void testWrongHibernateRegonsAreWrong() {
    for (final HibernateRegonTestBean testBean : HibernateRegonTestCases.getWrongTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.hv.pl.REGONValidator");
    }
  }
}
