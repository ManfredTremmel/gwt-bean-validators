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

import de.knightsoftnet.validators.shared.beans.HibernateCodePointLengthTestBean;
import de.knightsoftnet.validators.shared.testcases.HibernateCodePointLengthTestCases;

/**
 * test for hibernate code point length validator.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtTstHibernateCodePointLength
    extends AbstractValidationTst<HibernateCodePointLengthTestBean> {

  private static final String VALIDATION_CLASS =
      "org.hibernate.validator.internal.constraintvalidators.hv.CodePointLengthValidator";

  /**
   * empty not blank is ok.
   */
  public final void testEmptyCodePointLengthIsWrong() {
    super.validationTest(HibernateCodePointLengthTestCases.getEmptyTestBean(), true, null);
  }

  /**
   * correct entries.
   */
  public final void testCorrectCodePointLengthAreAllowed() {
    for (final HibernateCodePointLengthTestBean testBean : HibernateCodePointLengthTestCases
        .getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * wrong entries.
   */
  public final void testWrongCodePointLengthAreWrong() {
    for (final HibernateCodePointLengthTestBean testBean : HibernateCodePointLengthTestCases
        .getWrongTestBeans()) {
      super.validationTest(testBean, false, VALIDATION_CLASS);
    }
  }
}
