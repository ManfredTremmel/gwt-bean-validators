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

import de.knightsoftnet.validators.shared.beans.PasswordTestBean;
import de.knightsoftnet.validators.shared.testcases.PasswordTestCases;

/**
 * test for password validator.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtTstPassword extends AbstractValidationTst<PasswordTestBean> {

  /**
   * empty gln is allowed.
   */
  public final void testEmptyPasswordIsAllowed() {
    super.validationTest(PasswordTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct passwords are allowed.
   */
  public final void testCorrectPasswordsAreAllowed() {
    for (final PasswordTestBean testBean : PasswordTestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * wrong passwords are not allowed.
   */
  public final void testWrongPasswordsAreWrong() {
    for (final PasswordTestBean testBean : PasswordTestCases.getWrongTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.PasswordValidator");
    }
  }

  /**
   * blacklisted passwords are not allowed.
   */
  public final void testBlacklistedPasswordsAreWrong() {
    for (final PasswordTestBean testBean : PasswordTestCases.getBlackListedTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.PasswordValidator");
    }
  }

  /**
   * wrong start character passwords are not allowed.
   */
  public final void testWrongStartCharAreWrong() {
    for (final PasswordTestBean testBean : PasswordTestCases.getWrongStartCharTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.PasswordValidator");
    }
  }

  /**
   * exceeded character repeat passwords are not allowed.
   */
  public final void testExceededRepeatAreWrong() {
    for (final PasswordTestBean testBean : PasswordTestCases.getExceededRepeatTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.PasswordValidator");
    }
  }
}
