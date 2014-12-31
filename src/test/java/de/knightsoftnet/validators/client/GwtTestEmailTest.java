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

import de.knightsoftnet.validators.shared.EmailTestBean;

public class GwtTestEmailTest extends AbstractValidationTest<EmailTestBean> {

  /**
   * empty gln is allowed.
   */
  public final void testEmptyEmailIsAllowed() {
    super.validationTest(new EmailTestBean(null), true, null);
  }

  /**
   * correct emails are allowed.
   */
  public final void testCorrectEmailsAreAllowed() {
    super.validationTest(new EmailTestBean("jsmith@apache.org"), true, null);
    super.validationTest(new EmailTestBean("jsmith@apache.com"), true, null);
    super.validationTest(new EmailTestBean("jsmith@apache.net"), true, null);
    super.validationTest(new EmailTestBean("jsmith@apache.info"), true, null);
    super.validationTest(new EmailTestBean("someone@yahoo.museum"), true, null);
  }

  /**
   * wrong emails are not allowed.
   */
  public final void testWrongEmailsAreWrong() {
    super.validationTest(new EmailTestBean("jsmith@apache."), false,
        "de.knightsoftnet.validators.shared.impl.EmailValidator");
    super.validationTest(new EmailTestBean("jsmith@apache.c"), false,
        "de.knightsoftnet.validators.shared.impl.EmailValidator");
    super.validationTest(new EmailTestBean("someone@yahoo.mu-seum"), false,
        "de.knightsoftnet.validators.shared.impl.EmailValidator");
  }
}
