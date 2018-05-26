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

import de.knightsoftnet.validators.shared.beans.HibernateNotEmptyTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for hibernate not null test.
 *
 * @author Manfred Tremmel
 *
 */
public class HibernateNotEmptyTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final HibernateNotEmptyTestBean getEmptyTestBean() {
    return new HibernateNotEmptyTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<HibernateNotEmptyTestBean> getCorrectTestBeans() {
    final List<HibernateNotEmptyTestBean> correctCases = new ArrayList<HibernateNotEmptyTestBean>();
    correctCases.add(new HibernateNotEmptyTestBean(" "));
    correctCases.add(new HibernateNotEmptyTestBean("\t"));
    correctCases.add(new HibernateNotEmptyTestBean("\n"));
    correctCases.add(new HibernateNotEmptyTestBean("abcde"));

    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernateNotEmptyTestBean> getWrongtoSmallTestBeans() {
    final List<HibernateNotEmptyTestBean> wrongCases = new ArrayList<HibernateNotEmptyTestBean>();
    wrongCases.add(new HibernateNotEmptyTestBean(null));
    wrongCases.add(new HibernateNotEmptyTestBean(""));

    return wrongCases;
  }
}
