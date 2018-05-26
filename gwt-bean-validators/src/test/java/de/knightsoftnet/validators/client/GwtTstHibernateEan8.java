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

import de.knightsoftnet.validators.shared.beans.HibernateEan8TestBean;
import de.knightsoftnet.validators.shared.testcases.HibernateEan8TestCases;

/**
 * test for hibernate ean 8 validator.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtTstHibernateEan8 extends AbstractValidationTst<HibernateEan8TestBean> {

  /**
   * empty gtin8 is allowed.
   */
  public final void testEmptyHibernateEan8IsAllowed() {
    super.validationTest(HibernateEan8TestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct gtin8 is allowed.
   */
  public final void testCorrectHibernateEan8IsAllowed() {
    for (final HibernateEan8TestBean testBean : HibernateEan8TestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * gtin8 with wrong checksum.
   */
  public final void testWrongChecksumHibernateEan8IsWrong() {
    for (final HibernateEan8TestBean testBean : HibernateEan8TestCases.getWrongTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.hv.EANValidator");
    }
  }

  /**
   * gtin8 which is to small.
   */
  public final void testToSmallHibernateEan8IsWrong() {
    for (final HibernateEan8TestBean testBean : HibernateEan8TestCases.getToSmallTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.hv.EANValidator");
    }
  }

  /**
   * gtin8 which is to big.
   */
  public final void testToBigHibernateEan8IsWrong() {
    for (final HibernateEan8TestBean testBean : HibernateEan8TestCases.getToBigTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.hv.EANValidator");
    }
  }

  /**
   * gtin8 which is not numeric.
   */
  public final void testNotNumericHibernateEan8IsWrong() {
    for (final HibernateEan8TestBean testBean : HibernateEan8TestCases.getNotNumericTestBeans()) {
      super.validationTest(testBean, false,
          "org.hibernate.validator.internal.constraintvalidators.hv.EANValidator");
    }
  }
}
