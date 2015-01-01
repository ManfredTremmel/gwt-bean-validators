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

import de.knightsoftnet.validators.shared.beans.IsbnTestBean;

public class GwtTestIsbnTest extends AbstractValidationTest<IsbnTestBean> {


  /**
   * empty isbn is allowed.
   */
  public final void testEmptyIsbnIsAllowed() {
    super.validationTest(new IsbnTestBean(null), true, null);
  }

  /**
   * correct isbn is allowed.
   */
  public final void testCorrectIsbnIsAllowed() {
    super.validationTest(new IsbnTestBean("9783836218023"), true, null);
    super.validationTest(new IsbnTestBean("9783836215077"), true, null);
    super.validationTest(new IsbnTestBean("9783898644716"), true, null);
    super.validationTest(new IsbnTestBean("3807701710"), true, null);
    super.validationTest(new IsbnTestBean("3807702059"), true, null);
    super.validationTest(new IsbnTestBean("3807701923"), true, null);
    super.validationTest(new IsbnTestBean("3866400012"), true, null);
    super.validationTest(new IsbnTestBean("3937514120"), true, null);
  }

  /**
   * isbn with wrong checksum.
   */
  public final void testWrongChecksumIsbnIsWrong() {
    super.validationTest(new IsbnTestBean("9783836218032"), false,
        "de.knightsoftnet.validators.shared.impl.IsbnValidator");
    super.validationTest(new IsbnTestBean("9783838215077"), false,
        "de.knightsoftnet.validators.shared.impl.IsbnValidator");
    super.validationTest(new IsbnTestBean("9783899644716"), false,
        "de.knightsoftnet.validators.shared.impl.IsbnValidator");
    super.validationTest(new IsbnTestBean("3807701700"), false,
        "de.knightsoftnet.validators.shared.impl.IsbnValidator");
    super.validationTest(new IsbnTestBean("3807702058"), false,
        "de.knightsoftnet.validators.shared.impl.IsbnValidator");
    super.validationTest(new IsbnTestBean("3807701932"), false,
        "de.knightsoftnet.validators.shared.impl.IsbnValidator");
    super.validationTest(new IsbnTestBean("3866402012"), false,
        "de.knightsoftnet.validators.shared.impl.IsbnValidator");
    super.validationTest(new IsbnTestBean("3935714120"), false,
        "de.knightsoftnet.validators.shared.impl.IsbnValidator");
  }

  /**
   * isbn size is not valid.
   */
  public final void testWrongSizeIsbnIsWrong() {
    super.validationTest(new IsbnTestBean("308770192"), false,
        "de.knightsoftnet.validators.shared.impl.AlternateSizeValidator");
    super.validationTest(new IsbnTestBean("32512253"), false,
        "de.knightsoftnet.validators.shared.impl.AlternateSizeValidator");
    super.validationTest(new IsbnTestBean("34531365468"), false,
        "de.knightsoftnet.validators.shared.impl.AlternateSizeValidator");
    super.validationTest(new IsbnTestBean("403560821070"), false,
        "de.knightsoftnet.validators.shared.impl.AlternateSizeValidator");
    super.validationTest(new IsbnTestBean("978345313654"), false,
        "de.knightsoftnet.validators.shared.impl.AlternateSizeValidator");
  }

  /**
   * isbn which is not numeric.
   */
  public final void testNotNumericIsbnIsWrong() {
    super.validationTest(new IsbnTestBean("38077Y1923"), false,
        "org.hibernate.validator.constraints.impl.DigitsValidatorForString");
  }
}
