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

import de.knightsoftnet.validators.shared.beans.HibernateDecimalMinMaxTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for hibernate decimal min max test.
 *
 * @author Manfred Tremmel
 *
 */
public class HibernateDecimalMinMaxTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final HibernateDecimalMinMaxTestBean getEmptyTestBean() {
    return new HibernateDecimalMinMaxTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<HibernateDecimalMinMaxTestBean> getCorrectTestBeans() {
    final List<HibernateDecimalMinMaxTestBean> correctCases =
        new ArrayList<HibernateDecimalMinMaxTestBean>();
    correctCases.add(new HibernateDecimalMinMaxTestBean(0.11));
    correctCases.add(new HibernateDecimalMinMaxTestBean(0.2));
    correctCases.add(new HibernateDecimalMinMaxTestBean(1.0));
    correctCases.add(new HibernateDecimalMinMaxTestBean(1.1));
    correctCases.add(new HibernateDecimalMinMaxTestBean(2.0));

    return correctCases;
  }

  /**
   * get wrong, to small test beans.
   *
   * @return wrong, to small test beans
   */
  public static final List<HibernateDecimalMinMaxTestBean> getWrongtoSmallTestBeans() {
    final List<HibernateDecimalMinMaxTestBean> wrongCases =
        new ArrayList<HibernateDecimalMinMaxTestBean>();
    wrongCases.add(new HibernateDecimalMinMaxTestBean(-5.0));
    wrongCases.add(new HibernateDecimalMinMaxTestBean(0.0));
    wrongCases.add(new HibernateDecimalMinMaxTestBean(0.1));

    return wrongCases;
  }

  /**
   * get wrong, to big test beans.
   *
   * @return wrong, to big test beans
   */
  public static final List<HibernateDecimalMinMaxTestBean> getWrongtoBigTestBeans() {
    final List<HibernateDecimalMinMaxTestBean> wrongCases =
        new ArrayList<HibernateDecimalMinMaxTestBean>();
    wrongCases.add(new HibernateDecimalMinMaxTestBean(2.01));
    wrongCases.add(new HibernateDecimalMinMaxTestBean(200.0));

    return wrongCases;
  }
}
