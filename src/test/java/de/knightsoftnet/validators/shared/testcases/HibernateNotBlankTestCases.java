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

import de.knightsoftnet.validators.shared.beans.HibernateNotBlankTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for hibernate not null test.
 *
 * @author Manfred Tremmel
 *
 */
public class HibernateNotBlankTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final HibernateNotBlankTestBean getEmptyTestBean() {
    return new HibernateNotBlankTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<HibernateNotBlankTestBean> getCorrectTestBeans() {
    final List<HibernateNotBlankTestBean> correctCases = new ArrayList<HibernateNotBlankTestBean>();
    correctCases.add(new HibernateNotBlankTestBean("abcde"));

    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernateNotBlankTestBean> getWrongtoSmallTestBeans() {
    final List<HibernateNotBlankTestBean> wrongCases = new ArrayList<HibernateNotBlankTestBean>();
    wrongCases.add(new HibernateNotBlankTestBean(null));
    wrongCases.add(new HibernateNotBlankTestBean(""));
    wrongCases.add(new HibernateNotBlankTestBean(" "));
    wrongCases.add(new HibernateNotBlankTestBean("\t"));
    wrongCases.add(new HibernateNotBlankTestBean("\n"));

    return wrongCases;
  }
}
