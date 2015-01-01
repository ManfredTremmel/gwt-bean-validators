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

import de.knightsoftnet.validators.shared.beans.Isbn10FormatedTestBean;

import org.junit.Test;

public class Isbn10FormatedTest extends AbstractValidationTest<Isbn10FormatedTestBean> {


  /**
   * empty isbn10 is allowed.
   */
  @Test
  public final void testEmptyIsbn10IsAllowed() {
    super.validationTest(new Isbn10FormatedTestBean(null), true, null);
  }

  /**
   * correct isbn10 is allowed.
   */
  @Test
  public final void testCorrectIsbn10IsAllowed() {
    super.validationTest(new Isbn10FormatedTestBean("3-80-770171-0"), true, null);
    super.validationTest(new Isbn10FormatedTestBean("3-80-770205-9"), true, null);
    super.validationTest(new Isbn10FormatedTestBean("3-80-770192-3"), true, null);
    super.validationTest(new Isbn10FormatedTestBean("3-86-640001-2"), true, null);
    super.validationTest(new Isbn10FormatedTestBean("3-93-751412-0"), true, null);
  }

  /**
   * isbn10 with wrong checksum.
   */
  @Test
  public final void testWrongChecksumIsbn10IsWrong() {
    super.validationTest(new Isbn10FormatedTestBean("3-80-770170-0"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn10FormatedValidator");
    super.validationTest(new Isbn10FormatedTestBean("3-80-770205-8"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn10FormatedValidator");
    super.validationTest(new Isbn10FormatedTestBean("3-80-770193-2"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn10FormatedValidator");
    super.validationTest(new Isbn10FormatedTestBean("3-86-640201-2"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn10FormatedValidator");
    super.validationTest(new Isbn10FormatedTestBean("3-93-571412-0"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn10FormatedValidator");
  }

  /**
   * isbn10 which is to small.
   */
  @Test
  public final void testToSmallIsbn10IsWrong() {
    super.validationTest(new Isbn10FormatedTestBean("3-80-770193"), false,
        "org.hibernate.validator.constraints.impl.SizeValidatorForString");
  }

  /**
   * isbn10 which is to big.
   */
  @Test
  public final void testToBigIsbn10IsWrong() {
    super.validationTest(new Isbn10FormatedTestBean("3-80-770192-354"), false,
        "org.hibernate.validator.constraints.impl.SizeValidatorForString");
  }

  /**
   * isbn10 which has wrong format.
   */
  @Test
  public final void testNotNumericIsbn10IsWrong() {
    super.validationTest(new Isbn10FormatedTestBean("3 86-640201-2"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn10FormatedValidator");
    super.validationTest(new Isbn10FormatedTestBean("3+93-571412-0"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn10FormatedValidator");
    super.validationTest(new Isbn10FormatedTestBean("3-80-770193-2"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn10FormatedValidator");
    super.validationTest(new Isbn10FormatedTestBean("3-866-40201-2"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn10FormatedValidator");
    super.validationTest(new Isbn10FormatedTestBean("3-93-571-4120"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn10FormatedValidator");
  }
}
