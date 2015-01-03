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

import de.knightsoftnet.validators.shared.beans.IsinTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for isin test.
 *
 * @author Manfred Tremmel
 *
 */
public class IsinTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final IsinTestBean getEmptyTestBean() {
    return new IsinTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<IsinTestBean> getCorrectTestBeans() {
    final List<IsinTestBean> correctCases = new ArrayList<IsinTestBean>();
    correctCases.add(new IsinTestBean("EU0009652627"));
    correctCases.add(new IsinTestBean("EU000A0T74M4"));
    correctCases.add(new IsinTestBean("DE000BAY0017"));
    correctCases.add(new IsinTestBean("AU0000XVGZA3"));
    correctCases.add(new IsinTestBean("XF0000C14922"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<IsinTestBean> getWrongTestBeans() {
    final List<IsinTestBean> wrongCases = new ArrayList<IsinTestBean>();
    wrongCases.add(new IsinTestBean("EU1009652627"));
    wrongCases.add(new IsinTestBean("EU000A0T74M5"));
    wrongCases.add(new IsinTestBean("DE100BAY0017"));
    wrongCases.add(new IsinTestBean("AU0000XVGZB3"));
    wrongCases.add(new IsinTestBean("XF0000C14822"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<IsinTestBean> getWrongSizeTestBeans() {
    final List<IsinTestBean> wrongCases = new ArrayList<IsinTestBean>();
    wrongCases.add(new IsinTestBean("EU000965262"));
    wrongCases.add(new IsinTestBean("EU000A0T74M45"));
    return wrongCases;
  }
}
