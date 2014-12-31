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

import de.knightsoftnet.validators.shared.NotEmptyIfOtherHasValueTestBean;

public class GwtTestEmptyIfOtherHasValueTest extends
    AbstractValidationTest<NotEmptyIfOtherHasValueTestBean> {


  /**
   * the value we request is not set, so everything is allowed.
   */
  public final void testValueIsNotSetEverythingIsAllowed() {
    super.validationTest(new NotEmptyIfOtherHasValueTestBean(null, null, null), true, null);
    super.validationTest(new NotEmptyIfOtherHasValueTestBean(null, "", null), true, null);
    super.validationTest(new NotEmptyIfOtherHasValueTestBean(null, null, ""), true, null);
    super.validationTest(new NotEmptyIfOtherHasValueTestBean(null, "", ""), true, null);
    super.validationTest(new NotEmptyIfOtherHasValueTestBean(null, "filled", null), true, null);
    super.validationTest(new NotEmptyIfOtherHasValueTestBean(null, null, "filled"), true, null);
    super.validationTest(new NotEmptyIfOtherHasValueTestBean(null, "filled", "filled"), true, null);
    super.validationTest(new NotEmptyIfOtherHasValueTestBean(null, "filled", ""), true, null);
    super.validationTest(new NotEmptyIfOtherHasValueTestBean(null, "", "filled"), true, null);
    super.validationTest(new NotEmptyIfOtherHasValueTestBean(null, "filled", "filled"), true, null);
  }

  /**
   * the value we request is set to a type, we do not request, so everything is allowed.
   */
  public final void testValueIsSetDifferentEverythingIsAllowed() {
    super.validationTest(new NotEmptyIfOtherHasValueTestBean("filled", null, null), true, null);
    super.validationTest(new NotEmptyIfOtherHasValueTestBean("filled", "", null), true, null);
    super.validationTest(new NotEmptyIfOtherHasValueTestBean("filled", null, ""), true, null);
    super.validationTest(new NotEmptyIfOtherHasValueTestBean("filled", "", ""), true, null);
    super.validationTest(new NotEmptyIfOtherHasValueTestBean("filled", "filled", null), true, null);
    super.validationTest(new NotEmptyIfOtherHasValueTestBean("filled", null, "filled"), true, null);
    super.validationTest(new NotEmptyIfOtherHasValueTestBean("filled", "filled", "filled"), true,
        null);
    super.validationTest(new NotEmptyIfOtherHasValueTestBean("filled", "filled", ""), true, null);
    super.validationTest(new NotEmptyIfOtherHasValueTestBean("filled", "", "filled"), true, null);
    super.validationTest(new NotEmptyIfOtherHasValueTestBean("filled", "filled", "filled"), true,
        null);
  }

  /**
   * the value we request is set, and requested fields match.
   */
  public final void testValueIsSetFieldsToAllowed() {
    super.validationTest(new NotEmptyIfOtherHasValueTestBean("street", "filled", null), true, null);
    super.validationTest(new NotEmptyIfOtherHasValueTestBean("street", "filled", ""), true, null);
    super.validationTest(new NotEmptyIfOtherHasValueTestBean("postOfficeBox", null, "filled"),
        true, null);
    super.validationTest(new NotEmptyIfOtherHasValueTestBean("postOfficeBox", "", "filled"), true,
        null);
  }

  /**
   * the value we request is set, and requested fields do not match.
   */
  public final void testValueIsSetFieldsNotWrong() {
    super.validationTest(new NotEmptyIfOtherHasValueTestBean("street", "filled", "filled"), false,
        "de.knightsoftnet.validators.shared.impl.EmptyIfOtherHasValueValidator");
    super.validationTest(new NotEmptyIfOtherHasValueTestBean("postOfficeBox", "filled", "filled"),
        false, "de.knightsoftnet.validators.shared.impl.EmptyIfOtherHasValueValidator");
  }
}
