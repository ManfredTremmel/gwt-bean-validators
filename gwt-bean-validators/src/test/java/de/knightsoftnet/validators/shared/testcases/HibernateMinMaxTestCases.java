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

import de.knightsoftnet.validators.shared.beans.HibernateMinMaxTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for hibernate min max test.
 *
 * @author Manfred Tremmel
 *
 */
public class HibernateMinMaxTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final HibernateMinMaxTestBean getEmptyTestBean() {
    return new HibernateMinMaxTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<HibernateMinMaxTestBean> getCorrectTestBeans() {
    final List<HibernateMinMaxTestBean> correctCases = new ArrayList<HibernateMinMaxTestBean>();
    correctCases.add(new HibernateMinMaxTestBean(null));
    correctCases.add(new HibernateMinMaxTestBean(Long.valueOf(100L)));
    correctCases.add(new HibernateMinMaxTestBean(Long.valueOf(5000L)));
    correctCases.add(new HibernateMinMaxTestBean(Long.valueOf(99999L)));
    correctCases.add(new HibernateMinMaxTestBean(Long.valueOf(500000L)));

    return correctCases;
  }

  /**
   * get wrong, to small test beans.
   *
   * @return wrong, to small test beans
   */
  public static final List<HibernateMinMaxTestBean> getWrongtoSmallTestBeans() {
    final List<HibernateMinMaxTestBean> wrongCases = new ArrayList<HibernateMinMaxTestBean>();
    wrongCases.add(new HibernateMinMaxTestBean(Long.valueOf(0L)));
    wrongCases.add(new HibernateMinMaxTestBean(Long.valueOf(10L)));
    wrongCases.add(new HibernateMinMaxTestBean(Long.valueOf(50L)));
    wrongCases.add(new HibernateMinMaxTestBean(Long.valueOf(99L)));

    return wrongCases;
  }

  /**
   * get wrong, to big test beans.
   *
   * @return wrong, to big test beans
   */
  public static final List<HibernateMinMaxTestBean> getWrongtoBigTestBeans() {
    final List<HibernateMinMaxTestBean> wrongCases = new ArrayList<HibernateMinMaxTestBean>();
    wrongCases.add(new HibernateMinMaxTestBean(Long.valueOf(500001L)));
    wrongCases.add(new HibernateMinMaxTestBean(Long.valueOf(1000000L)));
    wrongCases.add(new HibernateMinMaxTestBean(Long.valueOf(5000000L)));
    wrongCases.add(new HibernateMinMaxTestBean(Long.valueOf(990000000L)));

    return wrongCases;
  }
}
