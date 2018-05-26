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

import de.knightsoftnet.validators.shared.beans.HibernateCpfTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for cpf test.
 *
 * @author Manfred Tremmel
 *
 */
public class HibernateCpfTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final HibernateCpfTestBean getEmptyTestBean() {
    return new HibernateCpfTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<HibernateCpfTestBean> getCorrectTestBeans() {
    final List<HibernateCpfTestBean> correctCases = new ArrayList<>();
    correctCases.add(new HibernateCpfTestBean("390.533.447-05"));
    correctCases.add(new HibernateCpfTestBean("263.946.533-30"));
    correctCases.add(new HibernateCpfTestBean("263.946.533-30"));
    correctCases.add(new HibernateCpfTestBean("043.033.407-90"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernateCpfTestBean> getWrongTestBeans() {
    final List<HibernateCpfTestBean> wrongCases = new ArrayList<>();
    wrongCases.add(new HibernateCpfTestBean("231.002.999-00"));
    wrongCases.add(new HibernateCpfTestBean("999.444.333-55"));
    wrongCases.add(new HibernateCpfTestBean("123456789012"));
    wrongCases.add(new HibernateCpfTestBean("4417123456789112"));
    wrongCases.add(new HibernateCpfTestBean("4417q23456w89113"));
    return wrongCases;
  }
}
