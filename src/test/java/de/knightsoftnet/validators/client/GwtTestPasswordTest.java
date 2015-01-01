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

public class GwtTestPasswordTest extends AbstractValidationTest<PasswordTestBean> {


  /**
   * empty gln is allowed.
   */
  public final void testEmptyPasswordIsAllowed() {
    super.validationTest(new PasswordTestBean(null), true, null);
  }

  /**
   * correct passwords are allowed.
   */
  public final void testCorrectPasswordsAreAllowed() {
    super.validationTest(new PasswordTestBean("Test123"), true, null);
    super.validationTest(new PasswordTestBean("Password!"), true, null);
    super.validationTest(new PasswordTestBean("1password%"), true, null);
  }

  /**
   * wrong passwords are not allowed.
   */
  public final void testWrongPasswordsAreWrong() {
    super.validationTest(new PasswordTestBean("test"), false,
        "de.knightsoftnet.validators.shared.impl.PasswordValidator");
    super.validationTest(new PasswordTestBean("test123"), false,
        "de.knightsoftnet.validators.shared.impl.PasswordValidator");
    super.validationTest(new PasswordTestBean("müller"), false,
        "de.knightsoftnet.validators.shared.impl.PasswordValidator");
  }
}