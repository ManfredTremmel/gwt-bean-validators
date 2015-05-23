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

import de.knightsoftnet.validators.shared.beans.Isbn10TestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for isbn10 test.
 *
 * @author Manfred Tremmel
 *
 */
public class Isbn10TestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final Isbn10TestBean getEmptyTestBean() {
    return new Isbn10TestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<Isbn10TestBean> getCorrectTestBeans() {
    final List<Isbn10TestBean> correctCases = new ArrayList<Isbn10TestBean>();
    correctCases.add(new Isbn10TestBean("3807701710"));
    correctCases.add(new Isbn10TestBean("3807702059"));
    correctCases.add(new Isbn10TestBean("3807701923"));
    correctCases.add(new Isbn10TestBean("3866400012"));
    correctCases.add(new Isbn10TestBean("3937514120"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<Isbn10TestBean> getWrongTestBeans() {
    final List<Isbn10TestBean> wrongCases = new ArrayList<Isbn10TestBean>();
    wrongCases.add(new Isbn10TestBean("3807701700"));
    wrongCases.add(new Isbn10TestBean("3807702058"));
    wrongCases.add(new Isbn10TestBean("3807701932"));
    wrongCases.add(new Isbn10TestBean("3866402012"));
    wrongCases.add(new Isbn10TestBean("3935714120"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<Isbn10TestBean> getToSmallTestBeans() {
    final List<Isbn10TestBean> wrongCases = new ArrayList<Isbn10TestBean>();
    wrongCases.add(new Isbn10TestBean("380770193"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<Isbn10TestBean> getToBigTestBeans() {
    final List<Isbn10TestBean> wrongCases = new ArrayList<Isbn10TestBean>();
    wrongCases.add(new Isbn10TestBean("380770192354"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<Isbn10TestBean> getNotNumericTestBeans() {
    final List<Isbn10TestBean> wrongCases = new ArrayList<Isbn10TestBean>();
    wrongCases.add(new Isbn10TestBean("38077Y1923"));
    return wrongCases;
  }
}
