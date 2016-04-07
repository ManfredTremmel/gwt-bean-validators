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

import de.knightsoftnet.validators.shared.beans.PhoneNumberValueRestTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for phone number test.
 *
 * @author Manfred Tremmel
 *
 */
public class PhoneNumberValueRestTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final PhoneNumberValueRestTestBean getEmptyTestBean() {
    return new PhoneNumberValueRestTestBean("DE", null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<PhoneNumberValueRestTestBean> getCorrectTestBeans() {
    final List<PhoneNumberValueRestTestBean> correctCases =
        new ArrayList<PhoneNumberValueRestTestBean>();
    correctCases.add(new PhoneNumberValueRestTestBean("DE", "+49 30 12345-67"));
    correctCases.add(new PhoneNumberValueRestTestBean("DE", "+49 30 1234567"));
    correctCases.add(new PhoneNumberValueRestTestBean("DE", "+49 (30) 1234567"));
    correctCases.add(new PhoneNumberValueRestTestBean("DE", "+49-30-1234567"));
    correctCases.add(new PhoneNumberValueRestTestBean("DE", "+49 (0)30 12345-67"));
    correctCases.add(new PhoneNumberValueRestTestBean("DE", "030 12345-67"));
    correctCases.add(new PhoneNumberValueRestTestBean("DE", "(030) 12345 67"));
    correctCases.add(new PhoneNumberValueRestTestBean("DE", "0 30 / 12 34 - 56"));
    correctCases.add(new PhoneNumberValueRestTestBean("DE", "+43 1 58058-0"));
    correctCases.add(new PhoneNumberValueRestTestBean("AT", "01 58058-0"));
    correctCases.add(new PhoneNumberValueRestTestBean("DE", "0 26 32 / 41 1 - 13"));
    correctCases.add(new PhoneNumberValueRestTestBean("CH", "0 26 / 32 41 1 - 13"));
    correctCases.add(new PhoneNumberValueRestTestBean("CH", "+41 (0)26 32411-13"));
    correctCases.add(new PhoneNumberValueRestTestBean("AF", "+93 (20) 2101100"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<PhoneNumberValueRestTestBean> getWrongTestBeans() {
    final List<PhoneNumberValueRestTestBean> wrongCases =
        new ArrayList<PhoneNumberValueRestTestBean>();
    wrongCases.add(new PhoneNumberValueRestTestBean("DE", "+49 30 12345+67"));
    wrongCases.add(new PhoneNumberValueRestTestBean("DE", "+49 30 123456789/67"));
    wrongCases.add(new PhoneNumberValueRestTestBean("DE", "+(49 30) 1234567"));
    wrongCases.add(new PhoneNumberValueRestTestBean("DE", "+43 1 5B058-0"));
    wrongCases.add(new PhoneNumberValueRestTestBean("DE", "01 58058-0"));
    wrongCases.add(new PhoneNumberValueRestTestBean("CH", "+41 26 32 / 41 1 - 13"));
    wrongCases.add(new PhoneNumberValueRestTestBean("CH", "0 26 32 / 41 1 - 13"));
    wrongCases.add(new PhoneNumberValueRestTestBean("DE", "0 26 / 32 41 1 - 13"));
    wrongCases.add(new PhoneNumberValueRestTestBean("DE", "+49 (2658) 1234567"));
    wrongCases.add(new PhoneNumberValueRestTestBean("DE", "+49 26581234567"));
    wrongCases.add(new PhoneNumberValueRestTestBean("DE", "+49 30 1"));
    wrongCases.add(new PhoneNumberValueRestTestBean("AF", "+93 (20) 210110"));
    wrongCases.add(new PhoneNumberValueRestTestBean("AF", "+93 (20) 21011000"));
    return wrongCases;
  }
}
