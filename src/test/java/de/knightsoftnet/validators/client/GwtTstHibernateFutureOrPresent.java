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

import de.knightsoftnet.validators.shared.beans.HibernateFutureOrPresentTestBean;
import de.knightsoftnet.validators.shared.testcases.HibernateFutureOrPresentTestCases;

/**
 * test for hibernate future validator.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtTstHibernateFutureOrPresent
    extends AbstractValidationTst<HibernateFutureOrPresentTestBean> {

  /**
   * empty future is allowed.
   */
  public final void testEmptyFutureIsAllowed() {
    super.validationTest(HibernateFutureOrPresentTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct future is allowed.
   */
  public final void testCorrectFutureAreAllowed() {
    for (final HibernateFutureOrPresentTestBean testBean : HibernateFutureOrPresentTestCases
        .getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * wrong future is not allowed.
   */
  public final void testWrongFutureAreWrong() {
    for (final HibernateFutureOrPresentTestBean testBean : HibernateFutureOrPresentTestCases
        .getWrongtoSmallTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent."
              + "FutureOrPresentValidatorForCalendar");
    }
  }
}
