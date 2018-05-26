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

import de.knightsoftnet.validators.shared.beans.HibernatePatternTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for hibernate pattern test.
 *
 * @author Manfred Tremmel
 *
 */
public class HibernatePatternTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final HibernatePatternTestBean getEmptyTestBean() {
    return new HibernatePatternTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<HibernatePatternTestBean> getCorrectTestBeans() {
    final List<HibernatePatternTestBean> correctCases = new ArrayList<HibernatePatternTestBean>();
    correctCases.add(new HibernatePatternTestBean(null));
    correctCases.add(new HibernatePatternTestBean(""));
    correctCases.add(new HibernatePatternTestBean("foo"));
    correctCases.add(new HibernatePatternTestBean("a b c foo"));

    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernatePatternTestBean> getWrongtoSmallTestBeans() {
    final List<HibernatePatternTestBean> wrongCases = new ArrayList<HibernatePatternTestBean>();
    wrongCases.add(new HibernatePatternTestBean("bla bla"));

    return wrongCases;
  }
}
