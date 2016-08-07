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

import de.knightsoftnet.validators.shared.beans.HibernateEan13TestBean;
import de.knightsoftnet.validators.shared.testcases.HibernateEan13TestCases;

/**
 * test for hibernate ean 13 validator.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtTstHibernateEan13 extends AbstractValidationTst<HibernateEan13TestBean> {

  /**
   * empty gtin13 is allowed.
   */
  public final void testEmptyHibernateEan13IsAllowed() {
    super.validationTest(HibernateEan13TestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct gtin13 is allowed.
   */
  public final void testCorrectHibernateEan13IsAllowed() {
    for (final HibernateEan13TestBean testBean : HibernateEan13TestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * gtin13 with wrong checksum.
   */
  public final void testWrongChecksumHibernateEan13IsWrong() {
    for (final HibernateEan13TestBean testBean : HibernateEan13TestCases.getWrongTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.hv.EANValidator");
    }
  }

  /**
   * gtin13 which is to small.
   */
  public final void testToSmallHibernateEan13IsWrong() {
    for (final HibernateEan13TestBean testBean : HibernateEan13TestCases.getToSmallTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.hv.EANValidator");
    }
  }

  /**
   * gtin13 which is to big.
   */
  public final void testToBigHibernateEan13IsWrong() {
    for (final HibernateEan13TestBean testBean : HibernateEan13TestCases.getToBigTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.hv.EANValidator");
    }
  }

  /**
   * gtin13 which is not numeric.
   */
  public final void testNotNumericHibernateEan13IsWrong() {
    for (final HibernateEan13TestBean testBean : HibernateEan13TestCases.getNotNumericTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.hv.EANValidator");
    }
  }
}
