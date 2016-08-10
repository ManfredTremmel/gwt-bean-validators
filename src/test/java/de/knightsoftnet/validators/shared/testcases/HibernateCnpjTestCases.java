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

import de.knightsoftnet.validators.shared.beans.HibernateCnpjTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for cnpj test.
 *
 * @author Manfred Tremmel
 *
 */
public class HibernateCnpjTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final HibernateCnpjTestBean getEmptyTestBean() {
    return new HibernateCnpjTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<HibernateCnpjTestBean> getCorrectTestBeans() {
    final List<HibernateCnpjTestBean> correctCases = new ArrayList<>();
    correctCases.add(new HibernateCnpjTestBean("40074158000120"));
    correctCases.add(new HibernateCnpjTestBean("98806711000152"));
    correctCases.add(new HibernateCnpjTestBean("67974542000136"));
    correctCases.add(new HibernateCnpjTestBean("27673143000110"));
    correctCases.add(new HibernateCnpjTestBean("72560284000181"));
    correctCases.add(new HibernateCnpjTestBean("14857711000191"));
    correctCases.add(new HibernateCnpjTestBean("79208813000121"));
    correctCases.add(new HibernateCnpjTestBean("64755522000186"));
    correctCases.add(new HibernateCnpjTestBean("55757959000168"));
    correctCases.add(new HibernateCnpjTestBean("77882422000162"));
    correctCases.add(new HibernateCnpjTestBean("40.074.158/0001-20"));
    correctCases.add(new HibernateCnpjTestBean("98.806.711/0001-52"));
    correctCases.add(new HibernateCnpjTestBean("67.974.542/0001-36"));
    correctCases.add(new HibernateCnpjTestBean("27.673.143/0001-10"));
    correctCases.add(new HibernateCnpjTestBean("72.560.284/0001-81"));
    correctCases.add(new HibernateCnpjTestBean("14.857.711/0001-91"));
    correctCases.add(new HibernateCnpjTestBean("79.208.813/0001-21"));
    correctCases.add(new HibernateCnpjTestBean("64.755.522/0001-86"));
    correctCases.add(new HibernateCnpjTestBean("55.757.959/0001-68"));
    correctCases.add(new HibernateCnpjTestBean("77.882.422/0001-62"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernateCnpjTestBean> getWrongTestBeans() {
    final List<HibernateCnpjTestBean> wrongCases = new ArrayList<>();
    wrongCases.add(new HibernateCnpjTestBean("40.074.158/0001-2"));
    wrongCases.add(new HibernateCnpjTestBean("40074158000210"));
    wrongCases.add(new HibernateCnpjTestBean("123456789012"));
    wrongCases.add(new HibernateCnpjTestBean("4417123456789112"));
    wrongCases.add(new HibernateCnpjTestBean("4417q23456w89113"));
    return wrongCases;
  }
}
