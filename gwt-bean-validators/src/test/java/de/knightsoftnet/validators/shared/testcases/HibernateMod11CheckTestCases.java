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

import de.knightsoftnet.validators.shared.beans.HibernateMod11CheckTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for hibernate mod 11 check test.
 *
 * @author Manfred Tremmel
 *
 */
public class HibernateMod11CheckTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final HibernateMod11CheckTestBean getEmptyTestBean() {
    return new HibernateMod11CheckTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<HibernateMod11CheckTestBean> getCorrectTestBeans() {
    final List<HibernateMod11CheckTestBean> correctCases = new ArrayList<>();
    correctCases.add(new HibernateMod11CheckTestBean("23322023583"));
    correctCases.add(new HibernateMod11CheckTestBean("378.796.950-01"));
    correctCases.add(new HibernateMod11CheckTestBean("331.814.296-43"));

    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernateMod11CheckTestBean> getWrongTestBeans() {
    final List<HibernateMod11CheckTestBean> wrongCases = new ArrayList<>();
    wrongCases.add(new HibernateMod11CheckTestBean("23322023584"));
    wrongCases.add(new HibernateMod11CheckTestBean("378.796.950-02"));
    wrongCases.add(new HibernateMod11CheckTestBean("331.814.296-52"));

    return wrongCases;
  }
}
