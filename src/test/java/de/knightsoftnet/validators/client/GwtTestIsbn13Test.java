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

import de.knightsoftnet.validators.shared.Isbn13TestBean;

/**
 * test for isbn 13 validator.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtTestIsbn13Test extends AbstractValidationTest<Isbn13TestBean> {


  /**
   * empty isbn13 is allowed.
   */
  public final void testEmptyIsbn13IsAllowed() {
    super.validationTest(new Isbn13TestBean(null), true, null);
  }

  /**
   * correct isbn13 is allowed.
   */
  public final void testCorrectIsbn13IsAllowed() {
    super.validationTest(new Isbn13TestBean("9783836218023"), true, null);
    super.validationTest(new Isbn13TestBean("9783836215077"), true, null);
    super.validationTest(new Isbn13TestBean("9783898644716"), true, null);
  }

  /**
   * isbn13 with wrong checksum.
   */
  public final void testWrongChecksumIsbn13IsWrong() {
    super.validationTest(new Isbn13TestBean("9783836218032"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn13Validator");
    super.validationTest(new Isbn13TestBean("9783838215077"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn13Validator");
    super.validationTest(new Isbn13TestBean("9783899644716"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn13Validator");
  }

  /**
   * isbn13 which is to small.
   */
  public final void testToSmallIsbn13IsWrong() {
    super.validationTest(new Isbn13TestBean("978383621803"), false,
        "org.hibernate.validator.constraints.impl.SizeValidatorForString");
  }

  /**
   * isbn13 which is to big.
   */
  public final void testToBigIsbn13IsWrong() {
    super.validationTest(new Isbn13TestBean("97838362180321"), false,
        "org.hibernate.validator.constraints.impl.DigitsValidatorForString");
  }

  /**
   * isbn13 which is not numeric.
   */
  public final void testNotNumericIsbn13IsWrong() {
    super.validationTest(new Isbn13TestBean("978383621803Y"), false,
        "org.hibernate.validator.constraints.impl.DigitsValidatorForString");
  }
}
