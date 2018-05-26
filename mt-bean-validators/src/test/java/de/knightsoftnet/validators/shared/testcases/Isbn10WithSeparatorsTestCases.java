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

import de.knightsoftnet.validators.shared.beans.Isbn10WithSeparatorsTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for isbn10 with spaces test.
 *
 * @author Manfred Tremmel
 *
 */
public class Isbn10WithSeparatorsTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final Isbn10WithSeparatorsTestBean getEmptyTestBean() {
    return new Isbn10WithSeparatorsTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<Isbn10WithSeparatorsTestBean> getCorrectTestBeans() {
    final List<Isbn10WithSeparatorsTestBean> correctCases =
        new ArrayList<Isbn10WithSeparatorsTestBean>();
    correctCases.add(new Isbn10WithSeparatorsTestBean("3807701710"));
    correctCases.add(new Isbn10WithSeparatorsTestBean("3807702059"));
    correctCases.add(new Isbn10WithSeparatorsTestBean("3807701923"));
    correctCases.add(new Isbn10WithSeparatorsTestBean("3866400012"));
    correctCases.add(new Isbn10WithSeparatorsTestBean("3937514120"));
    correctCases.add(new Isbn10WithSeparatorsTestBean("3-80-770171-0"));
    correctCases.add(new Isbn10WithSeparatorsTestBean("3-80-770205-9"));
    correctCases.add(new Isbn10WithSeparatorsTestBean("3-80-770192-3"));
    correctCases.add(new Isbn10WithSeparatorsTestBean("3-86-640001-2"));
    correctCases.add(new Isbn10WithSeparatorsTestBean("3-93-751412-0"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<Isbn10WithSeparatorsTestBean> getWrongTestBeans() {
    final List<Isbn10WithSeparatorsTestBean> wrongCases =
        new ArrayList<Isbn10WithSeparatorsTestBean>();
    wrongCases.add(new Isbn10WithSeparatorsTestBean("3807701700"));
    wrongCases.add(new Isbn10WithSeparatorsTestBean("3807702058"));
    wrongCases.add(new Isbn10WithSeparatorsTestBean("3807701932"));
    wrongCases.add(new Isbn10WithSeparatorsTestBean("3866402012"));
    wrongCases.add(new Isbn10WithSeparatorsTestBean("3935714120"));
    wrongCases.add(new Isbn10WithSeparatorsTestBean("3-80-770170-0"));
    wrongCases.add(new Isbn10WithSeparatorsTestBean("3-80-770205-8"));
    wrongCases.add(new Isbn10WithSeparatorsTestBean("3-80-770193-2"));
    wrongCases.add(new Isbn10WithSeparatorsTestBean("3-86-640201-2"));
    wrongCases.add(new Isbn10WithSeparatorsTestBean("3-93-571412-0"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<Isbn10WithSeparatorsTestBean> getToSmallTestBeans() {
    final List<Isbn10WithSeparatorsTestBean> wrongCases =
        new ArrayList<Isbn10WithSeparatorsTestBean>();
    wrongCases.add(new Isbn10WithSeparatorsTestBean("380770193"));
    wrongCases.add(new Isbn10WithSeparatorsTestBean("3-80-770193"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<Isbn10WithSeparatorsTestBean> getToBigTestBeans() {
    final List<Isbn10WithSeparatorsTestBean> wrongCases =
        new ArrayList<Isbn10WithSeparatorsTestBean>();
    wrongCases.add(new Isbn10WithSeparatorsTestBean("380770192354"));
    wrongCases.add(new Isbn10WithSeparatorsTestBean("3-80-770192-354"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<Isbn10WithSeparatorsTestBean> getNotNumericTestBeans() {
    final List<Isbn10WithSeparatorsTestBean> wrongCases =
        new ArrayList<Isbn10WithSeparatorsTestBean>();
    wrongCases.add(new Isbn10WithSeparatorsTestBean("38077Y1923"));
    wrongCases.add(new Isbn10WithSeparatorsTestBean("3-86-6C0201-2"));
    return wrongCases;
  }
}
