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

import de.knightsoftnet.validators.shared.beans.HibernateCreditCardNumberTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for credit card test.
 *
 * @author Manfred Tremmel
 *
 */
public class HibernateCreditCardNumberTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final HibernateCreditCardNumberTestBean getEmptyTestBean() {
    return new HibernateCreditCardNumberTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<HibernateCreditCardNumberTestBean> getCorrectTestBeans() {
    final List<HibernateCreditCardNumberTestBean> correctCases =
        new ArrayList<HibernateCreditCardNumberTestBean>();
    correctCases.add(new HibernateCreditCardNumberTestBean("4417123456789113"));
    correctCases.add(new HibernateCreditCardNumberTestBean("4222222222222"));
    correctCases.add(new HibernateCreditCardNumberTestBean("378282246310005"));
    correctCases.add(new HibernateCreditCardNumberTestBean("5105105105105100"));
    correctCases.add(new HibernateCreditCardNumberTestBean("6011000990139424"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernateCreditCardNumberTestBean> getWrongTestBeans() {
    final List<HibernateCreditCardNumberTestBean> wrongCases =
        new ArrayList<HibernateCreditCardNumberTestBean>();
    wrongCases.add(new HibernateCreditCardNumberTestBean("123456789012"));
    wrongCases.add(new HibernateCreditCardNumberTestBean("4417123456789112"));
    wrongCases.add(new HibernateCreditCardNumberTestBean("4417q23456w89113"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernateCreditCardNumberTestBean> getWrongSizeTestBeans() {
    final List<HibernateCreditCardNumberTestBean> wrongCases =
        new ArrayList<HibernateCreditCardNumberTestBean>();
    wrongCases.add(new HibernateCreditCardNumberTestBean("12345678901234567890"));
    return wrongCases;
  }
}
