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

package de.knightsoftnet.validators.server;

import de.knightsoftnet.validators.shared.beans.NotEmptyAlternateIfOtherIsEmptyTestBean;

import org.junit.Test;

public class NotEmptyAlternateIfOtherIsEmptyTest extends
    AbstractValidationTest<NotEmptyAlternateIfOtherIsEmptyTestBean> {


  /**
   * the compare field is set, alternate fields can be filled in every way.
   */
  @Test
  public final void testCompareIsSetAlternateEverythingIsAllowed() {
    super.validationTest(new NotEmptyAlternateIfOtherIsEmptyTestBean("filled", null, null), true,
        null);
    super.validationTest(new NotEmptyAlternateIfOtherIsEmptyTestBean("filled", "", null), true,
        null);
    super.validationTest(new NotEmptyAlternateIfOtherIsEmptyTestBean("filled", null, ""), true,
        null);
    super.validationTest(new NotEmptyAlternateIfOtherIsEmptyTestBean("filled", "", ""), true, null);
    super.validationTest(new NotEmptyAlternateIfOtherIsEmptyTestBean("filled", "filled", null),
        true, null);
    super.validationTest(new NotEmptyAlternateIfOtherIsEmptyTestBean("filled", null, "filled"),
        true, null);
    super.validationTest(new NotEmptyAlternateIfOtherIsEmptyTestBean("filled", "filled", "filled"),
        true, null);
    super.validationTest(new NotEmptyAlternateIfOtherIsEmptyTestBean("filled", "filled", ""), true,
        null);
    super.validationTest(new NotEmptyAlternateIfOtherIsEmptyTestBean("filled", "", "filled"), true,
        null);
  }

  /**
   * the compare field is empty, one of the alternate field (or both) have to be set.
   */
  @Test
  public final void testCompareIsEmptyOneOfTheAlternateHasTobEeSetAllowed() {
    super.validationTest(new NotEmptyAlternateIfOtherIsEmptyTestBean(null, "filled", null), true,
        null);
    super.validationTest(new NotEmptyAlternateIfOtherIsEmptyTestBean(null, null, "filled"), true,
        null);
    super.validationTest(new NotEmptyAlternateIfOtherIsEmptyTestBean(null, "filled", "filled"),
        true, null);
    super.validationTest(new NotEmptyAlternateIfOtherIsEmptyTestBean(null, "filled", ""), true,
        null);
    super.validationTest(new NotEmptyAlternateIfOtherIsEmptyTestBean(null, "", "filled"), true,
        null);

    super.validationTest(new NotEmptyAlternateIfOtherIsEmptyTestBean("", "filled", null), true,
        null);
    super.validationTest(new NotEmptyAlternateIfOtherIsEmptyTestBean("", null, "filled"), true,
        null);
    super.validationTest(new NotEmptyAlternateIfOtherIsEmptyTestBean("", "filled", "filled"), true,
        null);
    super.validationTest(new NotEmptyAlternateIfOtherIsEmptyTestBean("", "filled", ""), true, null);
    super.validationTest(new NotEmptyAlternateIfOtherIsEmptyTestBean("", "", "filled"), true, null);
  }

  /**
   * the compare field is empty and both alternate fields are empty.
   */
  @Test
  public final void testCompareIsEmptyBothAlternatesAreEmptyWrong() {
    super.validationTest(new NotEmptyAlternateIfOtherIsEmptyTestBean(null, null, null), false,
        "de.knightsoftnet.validators.shared.impl.NotEmptyAlternateIfOtherIsEmptyValidator");
    super.validationTest(new NotEmptyAlternateIfOtherIsEmptyTestBean(null, "", null), false,
        "de.knightsoftnet.validators.shared.impl.NotEmptyAlternateIfOtherIsEmptyValidator");
    super.validationTest(new NotEmptyAlternateIfOtherIsEmptyTestBean(null, null, ""), false,
        "de.knightsoftnet.validators.shared.impl.NotEmptyAlternateIfOtherIsEmptyValidator");
    super.validationTest(new NotEmptyAlternateIfOtherIsEmptyTestBean(null, "", ""), false,
        "de.knightsoftnet.validators.shared.impl.NotEmptyAlternateIfOtherIsEmptyValidator");

    super.validationTest(new NotEmptyAlternateIfOtherIsEmptyTestBean("", null, null), false,
        "de.knightsoftnet.validators.shared.impl.NotEmptyAlternateIfOtherIsEmptyValidator");
    super.validationTest(new NotEmptyAlternateIfOtherIsEmptyTestBean("", "", null), false,
        "de.knightsoftnet.validators.shared.impl.NotEmptyAlternateIfOtherIsEmptyValidator");
    super.validationTest(new NotEmptyAlternateIfOtherIsEmptyTestBean("", null, ""), false,
        "de.knightsoftnet.validators.shared.impl.NotEmptyAlternateIfOtherIsEmptyValidator");
    super.validationTest(new NotEmptyAlternateIfOtherIsEmptyTestBean("", "", ""), false,
        "de.knightsoftnet.validators.shared.impl.NotEmptyAlternateIfOtherIsEmptyValidator");
  }
}
