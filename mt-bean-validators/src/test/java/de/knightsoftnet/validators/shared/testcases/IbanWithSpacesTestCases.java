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

import de.knightsoftnet.validators.shared.beans.IbanWithSpacesTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for iban test.
 *
 * @author Manfred Tremmel
 *
 */
public class IbanWithSpacesTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final IbanWithSpacesTestBean getEmptyTestBean() {
    return new IbanWithSpacesTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<IbanWithSpacesTestBean> getCorrectTestBeans() {
    final List<IbanWithSpacesTestBean> correctCases = new ArrayList<IbanWithSpacesTestBean>();
    correctCases.add(new IbanWithSpacesTestBean("DE16701600000000555444"));
    correctCases.add(new IbanWithSpacesTestBean("DE49430609670000033401"));
    correctCases.add(new IbanWithSpacesTestBean("AT242011182221219800"));
    correctCases.add(new IbanWithSpacesTestBean("CH1609000000877768766"));
    correctCases.add(new IbanWithSpacesTestBean("IT73O0501803200000000125125"));
    correctCases.add(new IbanWithSpacesTestBean("BE51363036445162"));
    correctCases.add(new IbanWithSpacesTestBean("DK6280650002007198"));
    correctCases.add(new IbanWithSpacesTestBean("NL42INGB0006391952"));
    correctCases.add(new IbanWithSpacesTestBean("SE2850000000053041002965"));
    correctCases.add(new IbanWithSpacesTestBean("SI56020100011603397"));
    correctCases.add(new IbanWithSpacesTestBean("DE16 7016 0000 0000 5554 44"));
    correctCases.add(new IbanWithSpacesTestBean("DE49 4306 0967 0000 0334 01"));
    correctCases.add(new IbanWithSpacesTestBean("AT24 2011 1822 2121 9800"));
    correctCases.add(new IbanWithSpacesTestBean("CH16 0900 0000 8777 6876 6"));
    correctCases.add(new IbanWithSpacesTestBean("IT73 O050 1803 2000 0000 0125 125"));
    correctCases.add(new IbanWithSpacesTestBean("BE51 3630 3644 5162"));
    correctCases.add(new IbanWithSpacesTestBean("DK62 8065 0002 0071 98"));
    correctCases.add(new IbanWithSpacesTestBean("NL42 INGB 0006 3919 52"));
    correctCases.add(new IbanWithSpacesTestBean("SE28 5000 0000 0530 4100 2965"));
    correctCases.add(new IbanWithSpacesTestBean("SI56 0201 0001 1603 397"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<IbanWithSpacesTestBean> getWrongTestBeans() {
    final List<IbanWithSpacesTestBean> wrongCases = new ArrayList<IbanWithSpacesTestBean>();
    wrongCases.add(new IbanWithSpacesTestBean("XY16701600000000555444"));
    wrongCases.add(new IbanWithSpacesTestBean("XY16 7016 0000 0000 5554 44"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<IbanWithSpacesTestBean> getToSmallTestBeans() {
    final List<IbanWithSpacesTestBean> wrongCases = new ArrayList<IbanWithSpacesTestBean>();
    wrongCases.add(new IbanWithSpacesTestBean("DE123"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<IbanWithSpacesTestBean> getToBigTestBeans() {
    final List<IbanWithSpacesTestBean> wrongCases = new ArrayList<IbanWithSpacesTestBean>();
    wrongCases.add(new IbanWithSpacesTestBean("DE167016000000005554441234567890123"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<IbanWithSpacesTestBean> getWrongChecksumTestBeans() {
    final List<IbanWithSpacesTestBean> wrongCases = new ArrayList<IbanWithSpacesTestBean>();
    wrongCases.add(new IbanWithSpacesTestBean("DE16706100000000555444"));
    wrongCases.add(new IbanWithSpacesTestBean("DE16 7061 0000 0000 5554 44"));
    return wrongCases;
  }
}
