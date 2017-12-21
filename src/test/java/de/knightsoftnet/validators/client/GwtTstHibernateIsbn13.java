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

import de.knightsoftnet.validators.shared.beans.HibernateIsbn13TestBean;
import de.knightsoftnet.validators.shared.testcases.HibernateIsbn13TestCases;

/**
 * test for isbn 13 validator.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtTstHibernateIsbn13 extends AbstractValidationTst<HibernateIsbn13TestBean> {

  /**
   * empty isbn13 is allowed.
   */
  public final void testEmptyIsbn13IsAllowed() {
    super.validationTest(HibernateIsbn13TestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct isbn13 is allowed.
   */
  public final void testCorrectIsbn13IsAllowed() {
    for (final HibernateIsbn13TestBean testBean : HibernateIsbn13TestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * isbn13 with wrong checksum.
   */
  public final void testWrongChecksumIsbn13IsWrong() {
    for (final HibernateIsbn13TestBean testBean : HibernateIsbn13TestCases.getWrongTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.hv.ISBNValidator");
    }
  }

  /**
   * isbn13 which is to small.
   */
  public final void testToSmallIsbn13IsWrong() {
    for (final HibernateIsbn13TestBean testBean : HibernateIsbn13TestCases.getToSmallTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.hv.ISBNValidator");
    }
  }

  /**
   * isbn13 which is to big.
   */
  public final void testToBigIsbn13IsWrong() {
    for (final HibernateIsbn13TestBean testBean : HibernateIsbn13TestCases.getToBigTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.hv.ISBNValidator");
    }
  }

  /**
   * isbn13 which is not numeric.
   */
  public final void testNotNumericIsbn13IsWrong() {
    for (final HibernateIsbn13TestBean testBean : HibernateIsbn13TestCases
        .getNotNumericTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.hv.ISBNValidator");
    }
  }
}
