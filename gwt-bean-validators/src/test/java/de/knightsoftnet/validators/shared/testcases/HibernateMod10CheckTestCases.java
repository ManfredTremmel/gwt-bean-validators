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

import de.knightsoftnet.validators.shared.beans.HibernateMod10CheckTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for hibernate mod 10 check test.
 *
 * @author Manfred Tremmel
 *
 */
public class HibernateMod10CheckTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final HibernateMod10CheckTestBean getEmptyTestBean() {
    return new HibernateMod10CheckTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<HibernateMod10CheckTestBean> getCorrectTestBeans() {
    final List<HibernateMod10CheckTestBean> correctCases = new ArrayList<>();
    correctCases.add(new HibernateMod10CheckTestBean("978-85-61411-03-9"));
    correctCases.add(new HibernateMod10CheckTestBean("978-1-4302-1957-6"));
    correctCases.add(new HibernateMod10CheckTestBean("4 007630 00011 6"));
    correctCases.add(new HibernateMod10CheckTestBean("1 234567 89012 8"));
    correctCases.add(new HibernateMod10CheckTestBean("0 40 07630 00011 6"));
    correctCases.add(new HibernateMod10CheckTestBean("3 07 12345 00001 0"));

    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernateMod10CheckTestBean> getWrongTestBeans() {
    final List<HibernateMod10CheckTestBean> wrongCases = new ArrayList<>();
    wrongCases.add(new HibernateMod10CheckTestBean("978-85-61411-03-8"));
    wrongCases.add(new HibernateMod10CheckTestBean("1 234576 89012 8"));

    return wrongCases;
  }
}
