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

import de.knightsoftnet.validators.shared.Isbn13FormatedTestBean;

import org.junit.Test;

public class Isbn13FormatedTest extends AbstractValidationTest<Isbn13FormatedTestBean> {


  /**
   * empty isbn13 is allowed.
   */
  @Test
  public final void testEmptyIsbn13IsAllowed() {
    super.validationTest(new Isbn13FormatedTestBean(null), true, null);
  }

  /**
   * correct isbn13 is allowed.
   */
  @Test
  public final void testCorrectIsbn13IsAllowed() {
    super.validationTest(new Isbn13FormatedTestBean("978-3-83-621802-3"), true, null);
    super.validationTest(new Isbn13FormatedTestBean("978-3-83-621507-7"), true, null);
    super.validationTest(new Isbn13FormatedTestBean("978-3-89-864471-6"), true, null);
  }

  /**
   * isbn13 with wrong checksum.
   */
  @Test
  public final void testWrongChecksumIsbn13IsWrong() {
    super.validationTest(new Isbn13FormatedTestBean("978-3-83-621803-2"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn13FormatedValidator");
    super.validationTest(new Isbn13FormatedTestBean("978-3-83-821507-7"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn13FormatedValidator");
    super.validationTest(new Isbn13FormatedTestBean("978-3-89-964471-6"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn13FormatedValidator");
  }

  /**
   * isbn13 which is to small.
   */
  @Test
  public final void testToSmallIsbn13IsWrong() {
    super.validationTest(new Isbn13FormatedTestBean("978-3-83-621803"), false,
        "org.hibernate.validator.constraints.impl.SizeValidatorForString");
  }

  /**
   * isbn13 which is to big.
   */
  @Test
  public final void testToBigIsbn13IsWrong() {
    super.validationTest(new Isbn13FormatedTestBean("978-3-83-621803-21"), false,
        "org.hibernate.validator.constraints.impl.SizeValidatorForString");
  }
}
