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

import de.knightsoftnet.validators.shared.beans.IsbnTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for isbn test.
 *
 * @author Manfred Tremmel
 *
 */
public class IsbnTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final IsbnTestBean getEmptyTestBean() {
    return new IsbnTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<IsbnTestBean> getCorrectTestBeans() {
    final List<IsbnTestBean> correctCases = new ArrayList<IsbnTestBean>();
    correctCases.add(new IsbnTestBean("9783836218023"));
    correctCases.add(new IsbnTestBean("9783836215077"));
    correctCases.add(new IsbnTestBean("9783898644716"));

    correctCases.add(new IsbnTestBean("3807701710"));
    correctCases.add(new IsbnTestBean("3807702059"));
    correctCases.add(new IsbnTestBean("3807701923"));
    correctCases.add(new IsbnTestBean("3866400012"));
    correctCases.add(new IsbnTestBean("3937514120"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<IsbnTestBean> getWrongTestBeans() {
    final List<IsbnTestBean> wrongCases = new ArrayList<IsbnTestBean>();
    wrongCases.add(new IsbnTestBean("9783836218032"));
    wrongCases.add(new IsbnTestBean("9783838215077"));
    wrongCases.add(new IsbnTestBean("9783899644716"));

    wrongCases.add(new IsbnTestBean("3807701700"));
    wrongCases.add(new IsbnTestBean("3807702058"));
    wrongCases.add(new IsbnTestBean("3807701932"));
    wrongCases.add(new IsbnTestBean("3866402012"));
    wrongCases.add(new IsbnTestBean("3935714120"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<IsbnTestBean> getWrongSizeTestBeans() {
    final List<IsbnTestBean> wrongCases = new ArrayList<IsbnTestBean>();
    wrongCases.add(new IsbnTestBean("308770192"));
    wrongCases.add(new IsbnTestBean("32512253"));
    wrongCases.add(new IsbnTestBean("34531365468"));
    wrongCases.add(new IsbnTestBean("403560821070"));
    wrongCases.add(new IsbnTestBean("978345313654"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<IsbnTestBean> getNotNumericTestBeans() {
    final List<IsbnTestBean> wrongCases = new ArrayList<IsbnTestBean>();
    wrongCases.add(new IsbnTestBean("978383621803Y"));
    wrongCases.add(new IsbnTestBean("38077Y1923"));
    return wrongCases;
  }
}
