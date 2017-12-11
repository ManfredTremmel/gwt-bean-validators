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

import de.knightsoftnet.validators.shared.beans.HibernateNotEmptyTestBean;
import de.knightsoftnet.validators.shared.testcases.HibernateNotEmptyTestCases;

/**
 * test for hibernate not empty validator.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtTstHibernateNotEmpty extends AbstractValidationTst<HibernateNotEmptyTestBean> {

  private static final String VALIDATION_CLASS =
      "org.hibernate.validator.internal.constraintvalidators.bv.notempty."
          + "NotEmptyValidatorForCharSequence";

  /**
   * empty not empty is not allowed.
   */
  public final void testEmptyNotEmptyIsWrong() {
    super.validationTest(HibernateNotEmptyTestCases.getEmptyTestBean(), false, VALIDATION_CLASS);
  }

  /**
   * correct not empty is allowed.
   */
  public final void testCorrectNotEmptyAreAllowed() {
    for (final HibernateNotEmptyTestBean testBean : HibernateNotEmptyTestCases
        .getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * wrong not empty is not allowed.
   */
  public final void testWrongNotEmptyAreWrong() {
    for (final HibernateNotEmptyTestBean testBean : HibernateNotEmptyTestCases
        .getWrongtoSmallTestBeans()) {
      super.validationTest(testBean, false, VALIDATION_CLASS);
    }
  }
}
