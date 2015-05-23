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

package de.knightsoftnet.validators.shared.testcases;

import de.knightsoftnet.validators.shared.beans.CreditCardNumberTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for credit card test.
 *
 * @author Manfred Tremmel
 *
 */
public class CreditCardTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final CreditCardNumberTestBean getEmptyTestBean() {
    return new CreditCardNumberTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<CreditCardNumberTestBean> getCorrectTestBeans() {
    final List<CreditCardNumberTestBean> correctCases = new ArrayList<CreditCardNumberTestBean>();
    correctCases.add(new CreditCardNumberTestBean("4417123456789113"));
    correctCases.add(new CreditCardNumberTestBean("4222222222222"));
    correctCases.add(new CreditCardNumberTestBean("378282246310005"));
    correctCases.add(new CreditCardNumberTestBean("5105105105105100"));
    correctCases.add(new CreditCardNumberTestBean("6011000990139424"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<CreditCardNumberTestBean> getWrongTestBeans() {
    final List<CreditCardNumberTestBean> wrongCases = new ArrayList<CreditCardNumberTestBean>();
    wrongCases.add(new CreditCardNumberTestBean("123456789012"));
    wrongCases.add(new CreditCardNumberTestBean("4417123456789112"));
    wrongCases.add(new CreditCardNumberTestBean("4417q23456w89113"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<CreditCardNumberTestBean> getWrongSizeTestBeans() {
    final List<CreditCardNumberTestBean> wrongCases = new ArrayList<CreditCardNumberTestBean>();
    wrongCases.add(new CreditCardNumberTestBean("12345678901234567890"));
    return wrongCases;
  }
}
