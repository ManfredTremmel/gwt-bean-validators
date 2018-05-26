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

import de.knightsoftnet.validators.shared.beans.ListOfEmailsTestBean;
import de.knightsoftnet.validators.shared.testcases.ListOfEmailsTestCases;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

import javax.validation.ConstraintViolation;

/**
 * test for list of emails validator.
 *
 * @author Manfred Tremmel
 *
 */
public class ListOfEmailsTest extends AbstractValidationTest<ListOfEmailsTestBean> {

  /**
   * empty email list is not allowed.
   */
  @Test
  public final void testEmptyEmailIsNotAllowed() {
    super.validationTest(ListOfEmailsTestCases.getEmptyTestBean(), false,
        "org.hibernate.validator.internal.constraintvalidators.bv.size."
            + "SizeValidatorForCharSequence");
  }

  /**
   * correct emails are allowed.
   */
  @Test
  public final void testCorrectEmailsAreAllowed() {
    for (final ListOfEmailsTestBean testBean : ListOfEmailsTestCases.getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * wrong emails are not allowed.
   */
  @Test
  public final void testWrongEmailsAreWrong() {
    for (final ListOfEmailsTestBean testBean : ListOfEmailsTestCases.getWrongTestBeans()) {
      final Set<ConstraintViolation<ListOfEmailsTestBean>> cv1 = super.validationTest(testBean,
          false, "de.knightsoftnet.validators.shared.impl.EmailValidator");
      Assert.assertEquals("Path should contain list position", testBean.getPath(),
          cv1.iterator().next().getPropertyPath().toString());
    }
  }
}
