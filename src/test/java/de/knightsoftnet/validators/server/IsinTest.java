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

import de.knightsoftnet.validators.shared.beans.IsinTestBean;

import org.junit.Test;

public class IsinTest extends AbstractValidationTest<IsinTestBean> {

  /**
   * empty isin is allowed.
   */
  @Test
  public final void testEmptyIsbnIsAllowed() {
    super.validationTest(new IsinTestBean(null), true, null);
  }

  /**
   * correct isin is allowed.
   */
  @Test
  public final void testCorrectIsbnIsAllowed() {
    super.validationTest(new IsinTestBean("EU0009652627"), true, null);
    super.validationTest(new IsinTestBean("EU000A0T74M4"), true, null);
    super.validationTest(new IsinTestBean("DE000BAY0017"), true, null);
    super.validationTest(new IsinTestBean("AU0000XVGZA3"), true, null);
    super.validationTest(new IsinTestBean("XF0000C14922"), true, null);
  }

  /**
   * isin with wrong checksum.
   */
  @Test
  public final void testWrongChecksumIsbnIsWrong() {
    super.validationTest(new IsinTestBean("EU1009652627"), false,
        "de.knightsoftnet.validators.shared.impl.IsinValidator");
    super.validationTest(new IsinTestBean("EU000A0T74M5"), false,
        "de.knightsoftnet.validators.shared.impl.IsinValidator");
    super.validationTest(new IsinTestBean("DE100BAY0017"), false,
        "de.knightsoftnet.validators.shared.impl.IsinValidator");
    super.validationTest(new IsinTestBean("AU0000XVGZB3"), false,
        "de.knightsoftnet.validators.shared.impl.IsinValidator");
    super.validationTest(new IsinTestBean("XF0000C14822"), false,
        "de.knightsoftnet.validators.shared.impl.IsinValidator");
  }

  /**
   * isin size is not valid.
   */
  @Test
  public final void testWrongSizeIsbnIsWrong() {
    super.validationTest(new IsinTestBean("EU000965262"), false,
        "org.hibernate.validator.constraints.impl.SizeValidatorForString");
    super.validationTest(new IsinTestBean("EU000A0T74M45"), false,
        "org.hibernate.validator.constraints.impl.SizeValidatorForString");
  }
}
