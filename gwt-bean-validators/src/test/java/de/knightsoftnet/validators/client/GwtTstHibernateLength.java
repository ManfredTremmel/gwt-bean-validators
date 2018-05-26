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

import de.knightsoftnet.validators.shared.beans.HibernateLengthTestBean;
import de.knightsoftnet.validators.shared.testcases.HibernateLengthTestCases;

/**
 * test for hibernate length validator.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtTstHibernateLength extends AbstractValidationTst<HibernateLengthTestBean> {

  /**
   * empty size is allowed.
   */
  public final void testEmptyLengthIsAllowed() {
    super.validationTest(HibernateLengthTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct size are allowed.
   */
  public final void testCorrectLengthAreAllowed() {
    for (final HibernateLengthTestBean testBean : HibernateLengthTestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * wrong size are not allowed.
   */
  public final void testWrongLengthAreWrong() {
    for (final HibernateLengthTestBean testBean : HibernateLengthTestCases
        .getWrongtoSmallTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.hv.LengthValidator");
    }
  }
}
