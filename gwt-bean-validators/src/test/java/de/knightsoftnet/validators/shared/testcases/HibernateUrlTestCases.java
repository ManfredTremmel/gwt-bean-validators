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

import de.knightsoftnet.validators.shared.beans.HibernateUrlTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for hibernate url test.
 *
 * @author Manfred Tremmel
 *
 */
public class HibernateUrlTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final HibernateUrlTestBean getEmptyTestBean() {
    return new HibernateUrlTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<HibernateUrlTestBean> getCorrectTestBeans() {
    final List<HibernateUrlTestBean> correctCases = new ArrayList<HibernateUrlTestBean>();
    correctCases.add(new HibernateUrlTestBean("http://my.domain.com/index.html"));
    correctCases.add(new HibernateUrlTestBean("http://my.domain.com/index.htm"));
    correctCases.add(new HibernateUrlTestBean("ftp://abc.de"));
    correctCases.add(new HibernateUrlTestBean("ftp://abc.de:21"));
    correctCases.add(new HibernateUrlTestBean("http://www.hibernate.org:80"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernateUrlTestBean> getWrongTestBeans() {
    final List<HibernateUrlTestBean> wrongCases = new ArrayList<HibernateUrlTestBean>();
    wrongCases.add(new HibernateUrlTestBean("http"));
    wrongCases.add(new HibernateUrlTestBean("tz:\\temp\\ fi*le?na:m<e>.doc"));
    wrongCases.add(new HibernateUrlTestBean("(http://www.krumedia.com)"));
    return wrongCases;
  }
}
