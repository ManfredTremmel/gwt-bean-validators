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

import de.knightsoftnet.validators.shared.beans.EmptyIfOtherIsEmptyTestBean;

public class GwtTestEmptyIfOtherIsEmptyTest extends
    AbstractValidationTest<EmptyIfOtherIsEmptyTestBean> {


  /**
   * both fields are not set, so it's ok.
   */
  public final void testNoStreetNoNumberIsAllowed() {
    super.validationTest(new EmptyIfOtherIsEmptyTestBean(null, null), true, null);
    super.validationTest(new EmptyIfOtherIsEmptyTestBean(null, ""), true, null);
    super.validationTest(new EmptyIfOtherIsEmptyTestBean("", null), true, null);
    super.validationTest(new EmptyIfOtherIsEmptyTestBean("", ""), true, null);
  }

  /**
   * street is set, so street number can be what ever it wants.
   */
  public final void testStreetFlexibleNumberIsAllowed() {
    super.validationTest(new EmptyIfOtherIsEmptyTestBean("filled", null), true, null);
    super.validationTest(new EmptyIfOtherIsEmptyTestBean("filled", ""), true, null);
    super.validationTest(new EmptyIfOtherIsEmptyTestBean("filled", "filled"), true, null);
  }

  /**
   * street number is set, but no street, that's wrong.
   */
  public final void testNumberWithoutStreetWrong() {
    super.validationTest(new EmptyIfOtherIsEmptyTestBean(null, "filled"), false,
        "de.knightsoftnet.validators.shared.impl.EmptyIfOtherIsEmptyValidator");
    super.validationTest(new EmptyIfOtherIsEmptyTestBean("", "filled"), false,
        "de.knightsoftnet.validators.shared.impl.EmptyIfOtherIsEmptyValidator");
  }
}