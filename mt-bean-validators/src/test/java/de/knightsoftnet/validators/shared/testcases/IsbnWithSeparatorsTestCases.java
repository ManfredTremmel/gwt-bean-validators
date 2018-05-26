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

import de.knightsoftnet.validators.shared.beans.IsbnWithSeparatorsTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for isbn with separators test.
 *
 * @author Manfred Tremmel
 *
 */
public class IsbnWithSeparatorsTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final IsbnWithSeparatorsTestBean getEmptyTestBean() {
    return new IsbnWithSeparatorsTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<IsbnWithSeparatorsTestBean> getCorrectTestBeans() {
    final List<IsbnWithSeparatorsTestBean> correctCases =
        new ArrayList<IsbnWithSeparatorsTestBean>();
    correctCases.add(new IsbnWithSeparatorsTestBean("9783836218023"));
    correctCases.add(new IsbnWithSeparatorsTestBean("9783836215077"));
    correctCases.add(new IsbnWithSeparatorsTestBean("9783898644716"));

    correctCases.add(new IsbnWithSeparatorsTestBean("3807701710"));
    correctCases.add(new IsbnWithSeparatorsTestBean("3807702059"));
    correctCases.add(new IsbnWithSeparatorsTestBean("3807701923"));
    correctCases.add(new IsbnWithSeparatorsTestBean("3866400012"));
    correctCases.add(new IsbnWithSeparatorsTestBean("3937514120"));

    correctCases.add(new IsbnWithSeparatorsTestBean("978-3-83-621802-3"));
    correctCases.add(new IsbnWithSeparatorsTestBean("978-3-83-621507-7"));
    correctCases.add(new IsbnWithSeparatorsTestBean("978-3-89-864471-6"));

    correctCases.add(new IsbnWithSeparatorsTestBean("3-80-770171-0"));
    correctCases.add(new IsbnWithSeparatorsTestBean("3-80-770205-9"));
    correctCases.add(new IsbnWithSeparatorsTestBean("3-80-770192-3"));
    correctCases.add(new IsbnWithSeparatorsTestBean("3-86-640001-2"));
    correctCases.add(new IsbnWithSeparatorsTestBean("3-93-751412-0"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<IsbnWithSeparatorsTestBean> getWrongTestBeans() {
    final List<IsbnWithSeparatorsTestBean> wrongCases = new ArrayList<IsbnWithSeparatorsTestBean>();
    wrongCases.add(new IsbnWithSeparatorsTestBean("9783836218032"));
    wrongCases.add(new IsbnWithSeparatorsTestBean("9783838215077"));
    wrongCases.add(new IsbnWithSeparatorsTestBean("9783899644716"));

    wrongCases.add(new IsbnWithSeparatorsTestBean("3807701700"));
    wrongCases.add(new IsbnWithSeparatorsTestBean("3807702058"));
    wrongCases.add(new IsbnWithSeparatorsTestBean("3807701932"));
    wrongCases.add(new IsbnWithSeparatorsTestBean("3866402012"));
    wrongCases.add(new IsbnWithSeparatorsTestBean("3935714120"));

    wrongCases.add(new IsbnWithSeparatorsTestBean("978-3-83-621803-2"));
    wrongCases.add(new IsbnWithSeparatorsTestBean("978-3-83-821507-7"));
    wrongCases.add(new IsbnWithSeparatorsTestBean("978-3-89-964471-6"));

    wrongCases.add(new IsbnWithSeparatorsTestBean("3-80-770170-0"));
    wrongCases.add(new IsbnWithSeparatorsTestBean("3-80-770205-8"));
    wrongCases.add(new IsbnWithSeparatorsTestBean("3-80-770193-2"));
    wrongCases.add(new IsbnWithSeparatorsTestBean("3-86-640201-2"));
    wrongCases.add(new IsbnWithSeparatorsTestBean("3-93-571412-0"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<IsbnWithSeparatorsTestBean> getWrongSizeTestBeans() {
    final List<IsbnWithSeparatorsTestBean> wrongCases = new ArrayList<IsbnWithSeparatorsTestBean>();
    wrongCases.add(new IsbnWithSeparatorsTestBean("308770192"));
    wrongCases.add(new IsbnWithSeparatorsTestBean("32512253"));
    wrongCases.add(new IsbnWithSeparatorsTestBean("34531365468"));
    wrongCases.add(new IsbnWithSeparatorsTestBean("403560821070"));
    wrongCases.add(new IsbnWithSeparatorsTestBean("978345313654"));
    wrongCases.add(new IsbnWithSeparatorsTestBean("3-80-770205"));
    wrongCases.add(new IsbnWithSeparatorsTestBean("3-80-770192-32"));
    wrongCases.add(new IsbnWithSeparatorsTestBean("3-93-75141"));
    wrongCases.add(new IsbnWithSeparatorsTestBean("978-3-83-621803"));
    wrongCases.add(new IsbnWithSeparatorsTestBean("978-3-89-964471-65"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<IsbnWithSeparatorsTestBean> getNotNumericTestBeans() {
    final List<IsbnWithSeparatorsTestBean> wrongCases = new ArrayList<IsbnWithSeparatorsTestBean>();
    wrongCases.add(new IsbnWithSeparatorsTestBean("978383621803Y"));
    wrongCases.add(new IsbnWithSeparatorsTestBean("38077Y1923"));
    return wrongCases;
  }
}
