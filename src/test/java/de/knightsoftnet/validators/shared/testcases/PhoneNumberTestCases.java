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

import de.knightsoftnet.validators.shared.beans.PhoneNumberTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for phone number test.
 *
 * @author Manfred Tremmel
 *
 */
public class PhoneNumberTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final PhoneNumberTestBean getEmptyTestBean() {
    return new PhoneNumberTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<PhoneNumberTestBean> getCorrectTestBeans() {
    final List<PhoneNumberTestBean> correctCases = new ArrayList<PhoneNumberTestBean>();
    correctCases.add(new PhoneNumberTestBean("+49 30 12345-67"));
    correctCases.add(new PhoneNumberTestBean("+49 30 1234567"));
    correctCases.add(new PhoneNumberTestBean("+49 (30) 1234567"));
    correctCases.add(new PhoneNumberTestBean("+49-30-1234567"));
    correctCases.add(new PhoneNumberTestBean("+49 (0)30 12345-67"));
    correctCases.add(new PhoneNumberTestBean("030 12345-67"));
    correctCases.add(new PhoneNumberTestBean("(030) 12345 67"));
    correctCases.add(new PhoneNumberTestBean("0900 5 123456"));
    correctCases.add(new PhoneNumberTestBean("0 30 / 12 34 56"));
    correctCases.add(new PhoneNumberTestBean("+43 1 58058-0"));
    correctCases.add(new PhoneNumberTestBean("01 58058-0"));
    correctCases.add(new PhoneNumberTestBean("026 324 11 13"));
    correctCases.add(new PhoneNumberTestBean("+41 26 324 11 13"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<PhoneNumberTestBean> getWrongTestBeans() {
    final List<PhoneNumberTestBean> wrongCases = new ArrayList<PhoneNumberTestBean>();
    wrongCases.add(new PhoneNumberTestBean("+49 30 12345+67"));
    wrongCases.add(new PhoneNumberTestBean("+49 30 123456789/67"));
    wrongCases.add(new PhoneNumberTestBean("+(49 30) 1234567"));
    wrongCases.add(new PhoneNumberTestBean("+43 1 5B058-0"));
    return wrongCases;
  }
}
