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

import de.knightsoftnet.validators.shared.beans.Isbn13TestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for isbn13 test.
 *
 * @author Manfred Tremmel
 *
 */
public class Isbn13TestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final Isbn13TestBean getEmptyTestBean() {
    return new Isbn13TestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<Isbn13TestBean> getCorrectTestBeans() {
    final List<Isbn13TestBean> correctCases = new ArrayList<Isbn13TestBean>();
    correctCases.add(new Isbn13TestBean("9783836218023"));
    correctCases.add(new Isbn13TestBean("9783836215077"));
    correctCases.add(new Isbn13TestBean("9783898644716"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<Isbn13TestBean> getWrongTestBeans() {
    final List<Isbn13TestBean> wrongCases = new ArrayList<Isbn13TestBean>();
    wrongCases.add(new Isbn13TestBean("9783836218032"));
    wrongCases.add(new Isbn13TestBean("9783838215077"));
    wrongCases.add(new Isbn13TestBean("9783899644716"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<Isbn13TestBean> getToSmallTestBeans() {
    final List<Isbn13TestBean> wrongCases = new ArrayList<Isbn13TestBean>();
    wrongCases.add(new Isbn13TestBean("978383621803"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<Isbn13TestBean> getToBigTestBeans() {
    final List<Isbn13TestBean> wrongCases = new ArrayList<Isbn13TestBean>();
    wrongCases.add(new Isbn13TestBean("97838362180321"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<Isbn13TestBean> getNotNumericTestBeans() {
    final List<Isbn13TestBean> wrongCases = new ArrayList<Isbn13TestBean>();
    wrongCases.add(new Isbn13TestBean("978383621803Y"));
    return wrongCases;
  }
}
