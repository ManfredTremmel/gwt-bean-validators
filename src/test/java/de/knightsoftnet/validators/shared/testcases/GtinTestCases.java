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

import de.knightsoftnet.validators.shared.beans.GtinTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for gtin/ean test.
 *
 * @author Manfred Tremmel
 *
 */
public class GtinTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final GtinTestBean getEmptyTestBean() {
    return new GtinTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<GtinTestBean> getCorrectTestBeans() {
    final List<GtinTestBean> correctCases = new ArrayList<GtinTestBean>();
    correctCases.add(new GtinTestBean("4035600210708"));
    correctCases.add(new GtinTestBean("4250155401375"));
    correctCases.add(new GtinTestBean("9004617011702"));
    correctCases.add(new GtinTestBean("12345670"));
    correctCases.add(new GtinTestBean("40267708"));
    correctCases.add(new GtinTestBean("96385074"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<GtinTestBean> getWrongTestBeans() {
    final List<GtinTestBean> wrongCases = new ArrayList<GtinTestBean>();
    wrongCases.add(new GtinTestBean("4035600210078"));
    wrongCases.add(new GtinTestBean("4250515401375"));
    wrongCases.add(new GtinTestBean("4035601210078"));
    wrongCases.add(new GtinTestBean("12345678"));
    wrongCases.add(new GtinTestBean("40627708"));
    wrongCases.add(new GtinTestBean("96386074"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<GtinTestBean> getWrongSizeTestBeans() {
    final List<GtinTestBean> wrongCases = new ArrayList<GtinTestBean>();
    wrongCases.add(new GtinTestBean("4035600210"));
    wrongCases.add(new GtinTestBean("1234567"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<GtinTestBean> getNotNumericTestBeans() {
    final List<GtinTestBean> wrongCases = new ArrayList<GtinTestBean>();
    wrongCases.add(new GtinTestBean("403560021070Y"));
    wrongCases.add(new GtinTestBean("1234567Y"));
    return wrongCases;
  }
}
