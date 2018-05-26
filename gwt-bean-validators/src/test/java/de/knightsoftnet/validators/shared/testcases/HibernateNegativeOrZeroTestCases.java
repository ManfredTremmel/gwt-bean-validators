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

import de.knightsoftnet.validators.shared.beans.HibernateNegativeOrZeroTestBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for hibernate negative test.
 *
 * @author Manfred Tremmel
 *
 */
public class HibernateNegativeOrZeroTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final HibernateNegativeOrZeroTestBean getEmptyTestBean() {
    return new HibernateNegativeOrZeroTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<HibernateNegativeOrZeroTestBean> getCorrectTestBeans() {
    final List<HibernateNegativeOrZeroTestBean> correctCases = new ArrayList<>();
    correctCases.add(new HibernateNegativeOrZeroTestBean(BigDecimal.ZERO));
    correctCases.add(new HibernateNegativeOrZeroTestBean(BigDecimal.valueOf(-0.11)));
    correctCases.add(new HibernateNegativeOrZeroTestBean(BigDecimal.valueOf(-1)));

    return correctCases;
  }

  /**
   * get wrong, to small test beans.
   *
   * @return wrong, to small test beans
   */
  public static final List<HibernateNegativeOrZeroTestBean> getWrongTestBeans() {
    final List<HibernateNegativeOrZeroTestBean> wrongCases = new ArrayList<>();
    wrongCases.add(new HibernateNegativeOrZeroTestBean(BigDecimal.valueOf(0.11)));
    wrongCases.add(new HibernateNegativeOrZeroTestBean(BigDecimal.valueOf(12)));

    return wrongCases;
  }
}
