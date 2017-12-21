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

import de.knightsoftnet.validators.shared.beans.HibernateIsbn10TestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for isbn10 test.
 *
 * @author Manfred Tremmel
 *
 */
public class HibernateIsbn10TestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final HibernateIsbn10TestBean getEmptyTestBean() {
    return new HibernateIsbn10TestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<HibernateIsbn10TestBean> getCorrectTestBeans() {
    final List<HibernateIsbn10TestBean> correctCases = new ArrayList<>();
    correctCases.add(new HibernateIsbn10TestBean("3807701710"));
    correctCases.add(new HibernateIsbn10TestBean("3807702059"));
    correctCases.add(new HibernateIsbn10TestBean("3807701923"));
    correctCases.add(new HibernateIsbn10TestBean("3866400012"));
    correctCases.add(new HibernateIsbn10TestBean("3937514120"));
    correctCases.add(new HibernateIsbn10TestBean("99921-58-10-7"));
    correctCases.add(new HibernateIsbn10TestBean("9971-5-0210-0"));
    correctCases.add(new HibernateIsbn10TestBean("960-425-059-0"));
    correctCases.add(new HibernateIsbn10TestBean("80-902734-1-6"));
    correctCases.add(new HibernateIsbn10TestBean("0-9752298-0-X"));
    correctCases.add(new HibernateIsbn10TestBean("0-85131-041-9"));
    correctCases.add(new HibernateIsbn10TestBean("0-684-84328-5"));
    correctCases.add(new HibernateIsbn10TestBean("1-84356-028-3"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernateIsbn10TestBean> getWrongTestBeans() {
    final List<HibernateIsbn10TestBean> wrongCases = new ArrayList<>();
    wrongCases.add(new HibernateIsbn10TestBean("3807701700"));
    wrongCases.add(new HibernateIsbn10TestBean("3807702058"));
    wrongCases.add(new HibernateIsbn10TestBean("3807701932"));
    wrongCases.add(new HibernateIsbn10TestBean("3866402012"));
    wrongCases.add(new HibernateIsbn10TestBean("3935714120"));
    wrongCases.add(new HibernateIsbn10TestBean("99921-58-10-8"));
    wrongCases.add(new HibernateIsbn10TestBean("9971-5-0210-1"));
    wrongCases.add(new HibernateIsbn10TestBean("960-425-059-2"));
    wrongCases.add(new HibernateIsbn10TestBean("80-902734-1-8"));
    wrongCases.add(new HibernateIsbn10TestBean("0-9752298-0-3"));
    wrongCases.add(new HibernateIsbn10TestBean("0-85131-041-X"));
    wrongCases.add(new HibernateIsbn10TestBean("0-684-84328-7"));
    wrongCases.add(new HibernateIsbn10TestBean("1-84356-028-1"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernateIsbn10TestBean> getToSmallTestBeans() {
    final List<HibernateIsbn10TestBean> wrongCases = new ArrayList<>();
    wrongCases.add(new HibernateIsbn10TestBean("380770193"));
    wrongCases.add(new HibernateIsbn10TestBean(""));
    wrongCases.add(new HibernateIsbn10TestBean("978-0-5"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernateIsbn10TestBean> getToBigTestBeans() {
    final List<HibernateIsbn10TestBean> wrongCases = new ArrayList<>();
    wrongCases.add(new HibernateIsbn10TestBean("380770192354"));
    wrongCases.add(new HibernateIsbn10TestBean("978-0-55555555555555"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernateIsbn10TestBean> getNotNumericTestBeans() {
    final List<HibernateIsbn10TestBean> wrongCases = new ArrayList<>();
    wrongCases.add(new HibernateIsbn10TestBean("38077Y1923"));
    return wrongCases;
  }
}
