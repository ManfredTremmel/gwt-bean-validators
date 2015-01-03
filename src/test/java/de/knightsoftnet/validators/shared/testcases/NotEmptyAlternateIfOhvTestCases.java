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

import de.knightsoftnet.validators.shared.beans.NotEmptyAlternateIfOtherHasValueTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for not empty alternate if other has value test.
 *
 * @author Manfred Tremmel
 *
 */
public class NotEmptyAlternateIfOhvTestCases {

  /**
   * get beans with empty value.
   *
   * @return correct test beans
   */
  public static final List<NotEmptyAlternateIfOtherHasValueTestBean> getValueIsNotSetBeans() {
    final List<NotEmptyAlternateIfOtherHasValueTestBean> correctCases =
        new ArrayList<NotEmptyAlternateIfOtherHasValueTestBean>();

    correctCases.add(new NotEmptyAlternateIfOtherHasValueTestBean(null, null, null));
    correctCases.add(new NotEmptyAlternateIfOtherHasValueTestBean(null, "", null));
    correctCases.add(new NotEmptyAlternateIfOtherHasValueTestBean(null, null, ""));
    correctCases.add(new NotEmptyAlternateIfOtherHasValueTestBean(null, "", ""));
    correctCases.add(new NotEmptyAlternateIfOtherHasValueTestBean(null, "filled", null));
    correctCases.add(new NotEmptyAlternateIfOtherHasValueTestBean(null, null, "filled"));
    correctCases.add(new NotEmptyAlternateIfOtherHasValueTestBean(null, "filled", "filled"));
    correctCases.add(new NotEmptyAlternateIfOtherHasValueTestBean(null, "filled", ""));
    correctCases.add(new NotEmptyAlternateIfOtherHasValueTestBean(null, "", "filled"));
    correctCases.add(new NotEmptyAlternateIfOtherHasValueTestBean(null, "filled", "filled"));

    correctCases.add(new NotEmptyAlternateIfOtherHasValueTestBean(Boolean.FALSE, null, null));
    correctCases.add(new NotEmptyAlternateIfOtherHasValueTestBean(Boolean.FALSE, "", null));
    correctCases.add(new NotEmptyAlternateIfOtherHasValueTestBean(Boolean.FALSE, null, ""));
    correctCases.add(new NotEmptyAlternateIfOtherHasValueTestBean(Boolean.FALSE, "", ""));
    correctCases.add(new NotEmptyAlternateIfOtherHasValueTestBean(Boolean.FALSE, "filled", null));
    correctCases.add(new NotEmptyAlternateIfOtherHasValueTestBean(Boolean.FALSE, null, "filled"));
    correctCases
        .add(new NotEmptyAlternateIfOtherHasValueTestBean(Boolean.FALSE, "filled", "filled"));
    correctCases.add(new NotEmptyAlternateIfOtherHasValueTestBean(Boolean.FALSE, "filled", ""));
    correctCases.add(new NotEmptyAlternateIfOtherHasValueTestBean(Boolean.FALSE, "", "filled"));
    correctCases
        .add(new NotEmptyAlternateIfOtherHasValueTestBean(Boolean.FALSE, "filled", "filled"));

    return correctCases;
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<NotEmptyAlternateIfOtherHasValueTestBean> getCorrectTestBeans() {
    final List<NotEmptyAlternateIfOtherHasValueTestBean> correctCases =
        new ArrayList<NotEmptyAlternateIfOtherHasValueTestBean>();
    correctCases.add(new NotEmptyAlternateIfOtherHasValueTestBean(Boolean.TRUE, "filled", null));
    correctCases.add(new NotEmptyAlternateIfOtherHasValueTestBean(Boolean.TRUE, "filled", ""));
    correctCases.add(new NotEmptyAlternateIfOtherHasValueTestBean(Boolean.TRUE, null, "filled"));
    correctCases.add(new NotEmptyAlternateIfOtherHasValueTestBean(Boolean.TRUE, "", "filled"));
    correctCases
        .add(new NotEmptyAlternateIfOtherHasValueTestBean(Boolean.TRUE, "filled", "filled"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<NotEmptyAlternateIfOtherHasValueTestBean> getWrongEmptyTestBeans() {
    final List<NotEmptyAlternateIfOtherHasValueTestBean> wrongCases =
        new ArrayList<NotEmptyAlternateIfOtherHasValueTestBean>();
    wrongCases.add(new NotEmptyAlternateIfOtherHasValueTestBean(Boolean.TRUE, null, null));
    wrongCases.add(new NotEmptyAlternateIfOtherHasValueTestBean(Boolean.TRUE, null, ""));
    wrongCases.add(new NotEmptyAlternateIfOtherHasValueTestBean(Boolean.TRUE, "", null));
    wrongCases.add(new NotEmptyAlternateIfOtherHasValueTestBean(Boolean.TRUE, "", ""));
    return wrongCases;
  }
}
