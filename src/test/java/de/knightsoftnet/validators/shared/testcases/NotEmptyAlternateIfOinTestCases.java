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

import de.knightsoftnet.validators.shared.beans.NotEmptyAlternateIfOtherIsNotEmptyTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for not empty alternate if other has value test.
 *
 * @author Manfred Tremmel
 *
 */
public class NotEmptyAlternateIfOinTestCases {

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<NotEmptyAlternateIfOtherIsNotEmptyTestBean> getCompareFieldEmptyBeans() {
    final List<NotEmptyAlternateIfOtherIsNotEmptyTestBean> correctCases =
        new ArrayList<NotEmptyAlternateIfOtherIsNotEmptyTestBean>();
    correctCases.add(new NotEmptyAlternateIfOtherIsNotEmptyTestBean(null, null, null));
    correctCases.add(new NotEmptyAlternateIfOtherIsNotEmptyTestBean(null, "", null));
    correctCases.add(new NotEmptyAlternateIfOtherIsNotEmptyTestBean(null, null, ""));
    correctCases.add(new NotEmptyAlternateIfOtherIsNotEmptyTestBean(null, "", ""));
    correctCases.add(new NotEmptyAlternateIfOtherIsNotEmptyTestBean(null, "filled", null));
    correctCases.add(new NotEmptyAlternateIfOtherIsNotEmptyTestBean(null, null, "filled"));
    correctCases.add(new NotEmptyAlternateIfOtherIsNotEmptyTestBean(null, "filled", "filled"));
    correctCases.add(new NotEmptyAlternateIfOtherIsNotEmptyTestBean(null, "filled", ""));
    correctCases.add(new NotEmptyAlternateIfOtherIsNotEmptyTestBean(null, "", "filled"));

    correctCases.add(new NotEmptyAlternateIfOtherIsNotEmptyTestBean("", null, null));
    correctCases.add(new NotEmptyAlternateIfOtherIsNotEmptyTestBean("", "", null));
    correctCases.add(new NotEmptyAlternateIfOtherIsNotEmptyTestBean("", null, ""));
    correctCases.add(new NotEmptyAlternateIfOtherIsNotEmptyTestBean("", "", ""));
    correctCases.add(new NotEmptyAlternateIfOtherIsNotEmptyTestBean("", "filled", null));
    correctCases.add(new NotEmptyAlternateIfOtherIsNotEmptyTestBean("", null, "filled"));
    correctCases.add(new NotEmptyAlternateIfOtherIsNotEmptyTestBean("", "filled", "filled"));
    correctCases.add(new NotEmptyAlternateIfOtherIsNotEmptyTestBean("", "filled", ""));
    correctCases.add(new NotEmptyAlternateIfOtherIsNotEmptyTestBean("", "", "filled"));
    return correctCases;
  }

  /**
   * get beans with empty value.
   *
   * @return correct test beans
   */
  public static final List<NotEmptyAlternateIfOtherIsNotEmptyTestBean> getCompareFieldSetOkBeans() {
    final List<NotEmptyAlternateIfOtherIsNotEmptyTestBean> correctCases =
        new ArrayList<NotEmptyAlternateIfOtherIsNotEmptyTestBean>();

    correctCases.add(new NotEmptyAlternateIfOtherIsNotEmptyTestBean("filled", "filled", null));
    correctCases.add(new NotEmptyAlternateIfOtherIsNotEmptyTestBean("filled", "filled", ""));
    correctCases.add(new NotEmptyAlternateIfOtherIsNotEmptyTestBean("filled", null, "filled"));
    correctCases.add(new NotEmptyAlternateIfOtherIsNotEmptyTestBean("filled", "", "filled"));
    correctCases.add(new NotEmptyAlternateIfOtherIsNotEmptyTestBean("filled", "filled", "filled"));

    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<NotEmptyAlternateIfOtherIsNotEmptyTestBean> getWrongEmptyTestBeans() {
    final List<NotEmptyAlternateIfOtherIsNotEmptyTestBean> wrongCases =
        new ArrayList<NotEmptyAlternateIfOtherIsNotEmptyTestBean>();
    wrongCases.add(new NotEmptyAlternateIfOtherIsNotEmptyTestBean("filled", null, null));
    wrongCases.add(new NotEmptyAlternateIfOtherIsNotEmptyTestBean("filled", "", null));
    wrongCases.add(new NotEmptyAlternateIfOtherIsNotEmptyTestBean("filled", null, ""));
    wrongCases.add(new NotEmptyAlternateIfOtherIsNotEmptyTestBean("filled", "", ""));
    return wrongCases;
  }
}
