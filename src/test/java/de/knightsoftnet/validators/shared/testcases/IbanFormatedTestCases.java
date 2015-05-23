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

import de.knightsoftnet.validators.shared.beans.IbanFormatedTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for iban formated test.
 *
 * @author Manfred Tremmel
 *
 */
public class IbanFormatedTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final IbanFormatedTestBean getEmptyTestBean() {
    return new IbanFormatedTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<IbanFormatedTestBean> getCorrectTestBeans() {
    final List<IbanFormatedTestBean> correctCases = new ArrayList<IbanFormatedTestBean>();
    correctCases.add(new IbanFormatedTestBean("DE16 7016 0000 0000 5554 44"));
    correctCases.add(new IbanFormatedTestBean("DE49 4306 0967 0000 0334 01"));
    correctCases.add(new IbanFormatedTestBean("AT24 2011 1822 2121 9800"));
    correctCases.add(new IbanFormatedTestBean("CH16 0900 0000 8777 6876 6"));
    correctCases.add(new IbanFormatedTestBean("IT73 O050 1803 2000 0000 0125 125"));
    correctCases.add(new IbanFormatedTestBean("BE51 3630 3644 5162"));
    correctCases.add(new IbanFormatedTestBean("DK62 8065 0002 0071 98"));
    correctCases.add(new IbanFormatedTestBean("NL42 INGB 0006 3919 52"));
    correctCases.add(new IbanFormatedTestBean("SE28 5000 0000 0530 4100 2965"));
    correctCases.add(new IbanFormatedTestBean("SI56 0201 0001 1603 397"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<IbanFormatedTestBean> getWrongTestBeans() {
    final List<IbanFormatedTestBean> wrongCases = new ArrayList<IbanFormatedTestBean>();
    wrongCases.add(new IbanFormatedTestBean("XY16 7016 0000 0000 5554 44"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<IbanFormatedTestBean> getToSmallTestBeans() {
    final List<IbanFormatedTestBean> wrongCases = new ArrayList<IbanFormatedTestBean>();
    wrongCases.add(new IbanFormatedTestBean("DE12 3456 1"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<IbanFormatedTestBean> getToBigTestBeans() {
    final List<IbanFormatedTestBean> wrongCases = new ArrayList<IbanFormatedTestBean>();
    wrongCases.add(new IbanFormatedTestBean("DE16 7016 0000 0000 5554 4412 3456 7890 123"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<IbanFormatedTestBean> getWrongChecksumTestBeans() {
    final List<IbanFormatedTestBean> wrongCases = new ArrayList<IbanFormatedTestBean>();
    wrongCases.add(new IbanFormatedTestBean("DE16 7061 0000 0000 5554 44"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<IbanFormatedTestBean> getWrongFormatedTestBeans() {
    final List<IbanFormatedTestBean> wrongCases = new ArrayList<IbanFormatedTestBean>();
    wrongCases.add(new IbanFormatedTestBean("DE167 016 0000 0000 5554 44"));
    wrongCases.add(new IbanFormatedTestBean("DE49 4306 09670000 0334 01"));
    wrongCases.add(new IbanFormatedTestBean("AT24 2011 1822 2121 980 0"));
    wrongCases.add(new IbanFormatedTestBean("CH16-0900-0000-8777-6876-6"));
    return wrongCases;
  }
}
