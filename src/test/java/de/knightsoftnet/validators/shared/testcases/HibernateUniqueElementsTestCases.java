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

import de.knightsoftnet.validators.shared.beans.HibernateUniqueElementsTestBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * get test cases for hibernate UniqueElements test.
 *
 * @author Manfred Tremmel
 *
 */
public class HibernateUniqueElementsTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final HibernateUniqueElementsTestBean getEmptyTestBean() {
    return new HibernateUniqueElementsTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<HibernateUniqueElementsTestBean> getCorrectTestBeans() {
    final List<HibernateUniqueElementsTestBean> correctCases = new ArrayList<>();
    correctCases.add(new HibernateUniqueElementsTestBean(null));
    correctCases.add(new HibernateUniqueElementsTestBean(Arrays.asList("A", "B", "C")));

    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernateUniqueElementsTestBean> getWrongTestBeans() {
    final List<HibernateUniqueElementsTestBean> wrongCases = new ArrayList<>();
    wrongCases.add(new HibernateUniqueElementsTestBean(Arrays.asList("A", "B", "C", "A")));

    return wrongCases;
  }
}
