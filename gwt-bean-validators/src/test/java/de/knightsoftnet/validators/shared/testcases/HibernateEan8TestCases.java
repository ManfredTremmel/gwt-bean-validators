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

import de.knightsoftnet.validators.shared.beans.HibernateEan8TestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for gtin8/ean8 test.
 *
 * @author Manfred Tremmel
 *
 */
public class HibernateEan8TestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final HibernateEan8TestBean getEmptyTestBean() {
    return new HibernateEan8TestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<HibernateEan8TestBean> getCorrectTestBeans() {
    final List<HibernateEan8TestBean> correctCases = new ArrayList<HibernateEan8TestBean>();
    correctCases.add(new HibernateEan8TestBean("12345670"));
    correctCases.add(new HibernateEan8TestBean("40267708"));
    correctCases.add(new HibernateEan8TestBean("96385074"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernateEan8TestBean> getWrongTestBeans() {
    final List<HibernateEan8TestBean> wrongCases = new ArrayList<HibernateEan8TestBean>();
    wrongCases.add(new HibernateEan8TestBean("12345678"));
    wrongCases.add(new HibernateEan8TestBean("40627708"));
    wrongCases.add(new HibernateEan8TestBean("96386074"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernateEan8TestBean> getToSmallTestBeans() {
    final List<HibernateEan8TestBean> wrongCases = new ArrayList<HibernateEan8TestBean>();
    wrongCases.add(new HibernateEan8TestBean("1234567"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernateEan8TestBean> getToBigTestBeans() {
    final List<HibernateEan8TestBean> wrongCases = new ArrayList<HibernateEan8TestBean>();
    wrongCases.add(new HibernateEan8TestBean("123456701"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernateEan8TestBean> getNotNumericTestBeans() {
    final List<HibernateEan8TestBean> wrongCases = new ArrayList<HibernateEan8TestBean>();
    wrongCases.add(new HibernateEan8TestBean("1234567Y"));
    return wrongCases;
  }
}
