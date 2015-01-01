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

import de.knightsoftnet.validators.shared.beans.Isbn10TestBean;

public class GwtTestIsbn10Test extends AbstractValidationTest<Isbn10TestBean> {


  /**
   * empty isbn10 is allowed.
   */
  public final void testEmptyIsbn10IsAllowed() {
    super.validationTest(new Isbn10TestBean(null), true, null);
  }

  /**
   * correct isbn10 is allowed.
   */
  public final void testCorrectIsbn10IsAllowed() {
    super.validationTest(new Isbn10TestBean("3807701710"), true, null);
    super.validationTest(new Isbn10TestBean("3807702059"), true, null);
    super.validationTest(new Isbn10TestBean("3807701923"), true, null);
    super.validationTest(new Isbn10TestBean("3866400012"), true, null);
    super.validationTest(new Isbn10TestBean("3937514120"), true, null);
  }

  /**
   * isbn10 with wrong checksum.
   */
  public final void testWrongChecksumIsbn10IsWrong() {
    super.validationTest(new Isbn10TestBean("3807701700"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn10Validator");
    super.validationTest(new Isbn10TestBean("3807702058"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn10Validator");
    super.validationTest(new Isbn10TestBean("3807701932"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn10Validator");
    super.validationTest(new Isbn10TestBean("3866402012"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn10Validator");
    super.validationTest(new Isbn10TestBean("3935714120"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn10Validator");
  }

  /**
   * isbn10 which is to small.
   */
  public final void testToSmallIsbn10IsWrong() {
    super.validationTest(new Isbn10TestBean("380770193"), false,
        "org.hibernate.validator.constraints.impl.SizeValidatorForString");
  }

  /**
   * isbn10 which is to big.
   */
  public final void testToBigIsbn10IsWrong() {
    super.validationTest(new Isbn10TestBean("380770192354"), false,
        "org.hibernate.validator.constraints.impl.DigitsValidatorForString");
  }

  /**
   * isbn10 which is not numeric.
   */
  public final void testNotNumericIsbn10IsWrong() {
    super.validationTest(new Isbn10TestBean("38077Y1923"), false,
        "org.hibernate.validator.constraints.impl.DigitsValidatorForString");
  }
}
