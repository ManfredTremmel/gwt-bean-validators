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

import de.knightsoftnet.validators.shared.beans.HibernateAssertFalseTestBean;
import de.knightsoftnet.validators.shared.testcases.HibernateAssertFalseTestCases;

/**
 * test for hibernate assert false validator.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtTstHibernateAssertFalse
    extends AbstractValidationTst<HibernateAssertFalseTestBean> {

  /**
   * empty value is allowed.
   */
  public final void testEmptyUrlIsAllowed() {
    super.validationTest(HibernateAssertFalseTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * false or null is allowed.
   */
  public final void testCorrectUrlsAreAllowed() {
    for (final HibernateAssertFalseTestBean testBean : HibernateAssertFalseTestCases
        .getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * true is not allowed.
   */
  public final void testWrongUrlsAreWrong() {
    for (final HibernateAssertFalseTestBean testBean : HibernateAssertFalseTestCases
        .getWrongTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.bv.AssertFalseValidator");
    }
  }
}
