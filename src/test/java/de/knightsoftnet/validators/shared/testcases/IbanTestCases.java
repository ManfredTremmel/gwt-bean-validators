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

import de.knightsoftnet.validators.shared.beans.IbanTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for iban test.
 *
 * @author Manfred Tremmel
 *
 */
public class IbanTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final IbanTestBean getEmptyTestBean() {
    return new IbanTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<IbanTestBean> getCorrectTestBeans() {
    final List<IbanTestBean> correctCases = new ArrayList<IbanTestBean>();
    correctCases.add(new IbanTestBean("DE16701600000000555444"));
    correctCases.add(new IbanTestBean("DE49430609670000033401"));
    correctCases.add(new IbanTestBean("AT242011182221219800"));
    correctCases.add(new IbanTestBean("CH1609000000877768766"));
    correctCases.add(new IbanTestBean("IT73O0501803200000000125125"));
    correctCases.add(new IbanTestBean("CH1609000000877768766"));
    correctCases.add(new IbanTestBean("BE51363036445162"));
    correctCases.add(new IbanTestBean("DK6280650002007198"));
    correctCases.add(new IbanTestBean("NL42INGB0006391952"));
    correctCases.add(new IbanTestBean("SE2850000000053041002965"));
    correctCases.add(new IbanTestBean("SI56020100011603397"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<IbanTestBean> getWrongTestBeans() {
    final List<IbanTestBean> wrongCases = new ArrayList<IbanTestBean>();
    wrongCases.add(new IbanTestBean("XY16701600000000555444"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<IbanTestBean> getToSmallTestBeans() {
    final List<IbanTestBean> wrongCases = new ArrayList<IbanTestBean>();
    wrongCases.add(new IbanTestBean("DE123"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<IbanTestBean> getToBigTestBeans() {
    final List<IbanTestBean> wrongCases = new ArrayList<IbanTestBean>();
    wrongCases.add(new IbanTestBean("DE167016000000005554441234567890123"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<IbanTestBean> getWrongChecksumTestBeans() {
    final List<IbanTestBean> wrongCases = new ArrayList<IbanTestBean>();
    wrongCases.add(new IbanTestBean("DE16706100000000555444"));
    return wrongCases;
  }
}
