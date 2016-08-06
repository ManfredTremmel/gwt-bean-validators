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

import de.knightsoftnet.validators.shared.beans.HibernatePatternTestBean;
import de.knightsoftnet.validators.shared.testcases.HibernatePatternTestCases;

/**
 * test for hibernate pattern validator.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtTstHibernatePattern extends AbstractValidationTst<HibernatePatternTestBean> {

  /**
   * empty size is allowed.
   */
  public final void testEmptyPatternIsAllowed() {
    super.validationTest(HibernatePatternTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct pattern is allowed.
   */
  public final void testCorrectPatternAreAllowed() {
    for (final HibernatePatternTestBean testBean : HibernatePatternTestCases
        .getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * wrong pattern is not allowed.
   */
  public final void testWrongPatternAreWrong() {
    for (final HibernatePatternTestBean testBean : HibernatePatternTestCases
        .getWrongtoSmallTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.bv.PatternValidator");
    }
  }
}
