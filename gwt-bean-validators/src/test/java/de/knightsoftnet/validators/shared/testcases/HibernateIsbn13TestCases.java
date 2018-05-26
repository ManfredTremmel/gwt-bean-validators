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

import de.knightsoftnet.validators.shared.beans.HibernateIsbn13TestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for isbn13 test.
 *
 * @author Manfred Tremmel
 *
 */
public class HibernateIsbn13TestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final HibernateIsbn13TestBean getEmptyTestBean() {
    return new HibernateIsbn13TestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<HibernateIsbn13TestBean> getCorrectTestBeans() {
    final List<HibernateIsbn13TestBean> correctCases = new ArrayList<>();
    correctCases.add(new HibernateIsbn13TestBean("9783836218023"));
    correctCases.add(new HibernateIsbn13TestBean("9783836215077"));
    correctCases.add(new HibernateIsbn13TestBean("9783898644716"));
    correctCases.add(new HibernateIsbn13TestBean("978-123-456-789-7"));
    correctCases.add(new HibernateIsbn13TestBean("978-91-983989-1-5"));
    correctCases.add(new HibernateIsbn13TestBean("978-988-785-411-1"));
    correctCases.add(new HibernateIsbn13TestBean("978-1-56619-909-4"));
    correctCases.add(new HibernateIsbn13TestBean("978-1-4028-9462-6"));
    correctCases.add(new HibernateIsbn13TestBean("978-0-85131-041-1"));
    correctCases.add(new HibernateIsbn13TestBean("978-0-684-84328-5"));
    correctCases.add(new HibernateIsbn13TestBean("978-1-84356-028-9"));
    correctCases.add(new HibernateIsbn13TestBean("978-0-54560-495-6"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernateIsbn13TestBean> getWrongTestBeans() {
    final List<HibernateIsbn13TestBean> wrongCases = new ArrayList<>();
    wrongCases.add(new HibernateIsbn13TestBean("9783836218032"));
    wrongCases.add(new HibernateIsbn13TestBean("9783838215077"));
    wrongCases.add(new HibernateIsbn13TestBean("9783899644716"));
    wrongCases.add(new HibernateIsbn13TestBean("978-123-456-789-6"));
    wrongCases.add(new HibernateIsbn13TestBean("978-91-983989-1-4"));
    wrongCases.add(new HibernateIsbn13TestBean("978-988-785-411-2"));
    wrongCases.add(new HibernateIsbn13TestBean("978-1-56619-909-1"));
    wrongCases.add(new HibernateIsbn13TestBean("978-1-4028-9462-0"));
    wrongCases.add(new HibernateIsbn13TestBean("978-0-85131-041-5"));
    wrongCases.add(new HibernateIsbn13TestBean("978-0-684-84328-1"));
    wrongCases.add(new HibernateIsbn13TestBean("978-1-84356-028-1"));
    wrongCases.add(new HibernateIsbn13TestBean("978-0-54560-495-4"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernateIsbn13TestBean> getToSmallTestBeans() {
    final List<HibernateIsbn13TestBean> wrongCases = new ArrayList<>();
    wrongCases.add(new HibernateIsbn13TestBean("978383621803"));
    wrongCases.add(new HibernateIsbn13TestBean(""));
    wrongCases.add(new HibernateIsbn13TestBean("978-0-54560-4"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernateIsbn13TestBean> getToBigTestBeans() {
    final List<HibernateIsbn13TestBean> wrongCases = new ArrayList<>();
    wrongCases.add(new HibernateIsbn13TestBean("97838362180321"));
    wrongCases.add(new HibernateIsbn13TestBean("978-0-55555555555555"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernateIsbn13TestBean> getNotNumericTestBeans() {
    final List<HibernateIsbn13TestBean> wrongCases = new ArrayList<>();
    wrongCases.add(new HibernateIsbn13TestBean("978383621803Y"));
    return wrongCases;
  }
}
