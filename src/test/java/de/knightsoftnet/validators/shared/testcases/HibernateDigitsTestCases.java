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

import de.knightsoftnet.validators.shared.beans.HibernateDigitsTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for hibernate digits test.
 *
 * @author Manfred Tremmel
 *
 */
public class HibernateDigitsTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final HibernateDigitsTestBean getEmptyTestBean() {
    return new HibernateDigitsTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<HibernateDigitsTestBean> getCorrectTestBeans() {
    final List<HibernateDigitsTestBean> correctCases = new ArrayList<HibernateDigitsTestBean>();
    correctCases.add(new HibernateDigitsTestBean(null));
    correctCases.add(new HibernateDigitsTestBean(500.2));
    correctCases.add(new HibernateDigitsTestBean(-12345.12));
    correctCases.add(new HibernateDigitsTestBean(-0.22));

    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernateDigitsTestBean> getWrongTestBeans() {
    final List<HibernateDigitsTestBean> wrongCases = new ArrayList<HibernateDigitsTestBean>();
    wrongCases.add(new HibernateDigitsTestBean(-123456.12));
    wrongCases.add(new HibernateDigitsTestBean(-123456.123));
    wrongCases.add(new HibernateDigitsTestBean(-12345.123));
    wrongCases.add(new HibernateDigitsTestBean(12345.123));
    wrongCases.add(new HibernateDigitsTestBean(256874d));
    wrongCases.add(new HibernateDigitsTestBean(12.0001));

    return wrongCases;
  }
}
