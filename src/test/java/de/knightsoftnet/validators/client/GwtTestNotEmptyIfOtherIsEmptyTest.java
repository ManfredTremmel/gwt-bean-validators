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

import de.knightsoftnet.validators.shared.beans.NotEmptyIfOtherIsEmptyTestBean;

public class GwtTestNotEmptyIfOtherIsEmptyTest extends
    AbstractValidationTest<NotEmptyIfOtherIsEmptyTestBean> {


  /**
   * both are filled is allowed.
   */
  public final void testBothFilledIsAllowed() {
    super.validationTest(new NotEmptyIfOtherIsEmptyTestBean("filled", "filled"), true, null);
  }

  /**
   * alternate fill is allowed.
   */
  public final void testAlternateFillIsAllowed() {
    super.validationTest(new NotEmptyIfOtherIsEmptyTestBean("filled", null), true, null);
    super.validationTest(new NotEmptyIfOtherIsEmptyTestBean("filled", ""), true, null);
    super.validationTest(new NotEmptyIfOtherIsEmptyTestBean(null, "filled"), true, null);
    super.validationTest(new NotEmptyIfOtherIsEmptyTestBean("", "filled"), true, null);
  }

  /**
   * both entries are not allowed to be empty.
   */
  public final void testBothEmptyIsWrong() {
    super.validationTest(new NotEmptyIfOtherIsEmptyTestBean(null, null), false,
        "de.knightsoftnet.validators.shared.impl.NotEmptyIfOtherIsEmptyValidator");
    super.validationTest(new NotEmptyIfOtherIsEmptyTestBean(null, ""), false,
        "de.knightsoftnet.validators.shared.impl.NotEmptyIfOtherIsEmptyValidator");
    super.validationTest(new NotEmptyIfOtherIsEmptyTestBean("", null), false,
        "de.knightsoftnet.validators.shared.impl.NotEmptyIfOtherIsEmptyValidator");
  }
}
