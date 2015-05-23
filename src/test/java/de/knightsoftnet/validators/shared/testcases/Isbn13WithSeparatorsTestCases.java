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

import de.knightsoftnet.validators.shared.beans.Isbn13WithSeparatorsTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for isbn13 with separators test.
 *
 * @author Manfred Tremmel
 *
 */
public class Isbn13WithSeparatorsTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final Isbn13WithSeparatorsTestBean getEmptyTestBean() {
    return new Isbn13WithSeparatorsTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<Isbn13WithSeparatorsTestBean> getCorrectTestBeans() {
    final List<Isbn13WithSeparatorsTestBean> correctCases =
        new ArrayList<Isbn13WithSeparatorsTestBean>();
    correctCases.add(new Isbn13WithSeparatorsTestBean("9783836218023"));
    correctCases.add(new Isbn13WithSeparatorsTestBean("9783836215077"));
    correctCases.add(new Isbn13WithSeparatorsTestBean("9783898644716"));
    correctCases.add(new Isbn13WithSeparatorsTestBean("978-3-83-621802-3"));
    correctCases.add(new Isbn13WithSeparatorsTestBean("978-3-83-621507-7"));
    correctCases.add(new Isbn13WithSeparatorsTestBean("978-3-89-864471-6"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<Isbn13WithSeparatorsTestBean> getWrongTestBeans() {
    final List<Isbn13WithSeparatorsTestBean> wrongCases =
        new ArrayList<Isbn13WithSeparatorsTestBean>();
    wrongCases.add(new Isbn13WithSeparatorsTestBean("9783836218032"));
    wrongCases.add(new Isbn13WithSeparatorsTestBean("9783838215077"));
    wrongCases.add(new Isbn13WithSeparatorsTestBean("9783899644716"));
    wrongCases.add(new Isbn13WithSeparatorsTestBean("978-3-83-621803-2"));
    wrongCases.add(new Isbn13WithSeparatorsTestBean("978-3-83-821507-7"));
    wrongCases.add(new Isbn13WithSeparatorsTestBean("978-3-89-964471-6"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<Isbn13WithSeparatorsTestBean> getToSmallTestBeans() {
    final List<Isbn13WithSeparatorsTestBean> wrongCases =
        new ArrayList<Isbn13WithSeparatorsTestBean>();
    wrongCases.add(new Isbn13WithSeparatorsTestBean("978383621803"));
    wrongCases.add(new Isbn13WithSeparatorsTestBean("978-3-83-621803"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<Isbn13WithSeparatorsTestBean> getToBigTestBeans() {
    final List<Isbn13WithSeparatorsTestBean> wrongCases =
        new ArrayList<Isbn13WithSeparatorsTestBean>();
    wrongCases.add(new Isbn13WithSeparatorsTestBean("97838362180321"));
    wrongCases.add(new Isbn13WithSeparatorsTestBean("978-3-83-621803-21"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<Isbn13WithSeparatorsTestBean> getNotNumericTestBeans() {
    final List<Isbn13WithSeparatorsTestBean> wrongCases =
        new ArrayList<Isbn13WithSeparatorsTestBean>();
    wrongCases.add(new Isbn13WithSeparatorsTestBean("978383621803Y"));
    wrongCases.add(new Isbn13WithSeparatorsTestBean("978-3-89-8A4471-6"));
    return wrongCases;
  }
}
