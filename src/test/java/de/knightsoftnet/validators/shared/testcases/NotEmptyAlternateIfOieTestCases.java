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

import de.knightsoftnet.validators.shared.beans.NotEmptyAlternateIfOtherIsEmptyTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for not empty alternate if other has value test.
 *
 * @author Manfred Tremmel
 *
 */
public class NotEmptyAlternateIfOieTestCases {

  /**
   * get beans with empty value.
   *
   * @return correct test beans
   */
  public static final List<NotEmptyAlternateIfOtherIsEmptyTestBean> getCompareFieldSetOkBeans() {
    final List<NotEmptyAlternateIfOtherIsEmptyTestBean> correctCases =
        new ArrayList<NotEmptyAlternateIfOtherIsEmptyTestBean>();

    correctCases.add(new NotEmptyAlternateIfOtherIsEmptyTestBean("filled", null, null));
    correctCases.add(new NotEmptyAlternateIfOtherIsEmptyTestBean("filled", "", null));
    correctCases.add(new NotEmptyAlternateIfOtherIsEmptyTestBean("filled", null, ""));
    correctCases.add(new NotEmptyAlternateIfOtherIsEmptyTestBean("filled", "", ""));
    correctCases.add(new NotEmptyAlternateIfOtherIsEmptyTestBean("filled", "filled", null));
    correctCases.add(new NotEmptyAlternateIfOtherIsEmptyTestBean("filled", null, "filled"));
    correctCases.add(new NotEmptyAlternateIfOtherIsEmptyTestBean("filled", "filled", "filled"));
    correctCases.add(new NotEmptyAlternateIfOtherIsEmptyTestBean("filled", "filled", ""));
    correctCases.add(new NotEmptyAlternateIfOtherIsEmptyTestBean("filled", "", "filled"));

    return correctCases;
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<NotEmptyAlternateIfOtherIsEmptyTestBean> getCompareFieldEmptyOkBeans() {
    final List<NotEmptyAlternateIfOtherIsEmptyTestBean> correctCases =
        new ArrayList<NotEmptyAlternateIfOtherIsEmptyTestBean>();
    correctCases.add(new NotEmptyAlternateIfOtherIsEmptyTestBean(null, "filled", null));
    correctCases.add(new NotEmptyAlternateIfOtherIsEmptyTestBean(null, null, "filled"));
    correctCases.add(new NotEmptyAlternateIfOtherIsEmptyTestBean(null, "filled", "filled"));
    correctCases.add(new NotEmptyAlternateIfOtherIsEmptyTestBean(null, "filled", ""));
    correctCases.add(new NotEmptyAlternateIfOtherIsEmptyTestBean(null, "", "filled"));

    correctCases.add(new NotEmptyAlternateIfOtherIsEmptyTestBean("", "filled", null));
    correctCases.add(new NotEmptyAlternateIfOtherIsEmptyTestBean("", null, "filled"));
    correctCases.add(new NotEmptyAlternateIfOtherIsEmptyTestBean("", "filled", "filled"));
    correctCases.add(new NotEmptyAlternateIfOtherIsEmptyTestBean("", "filled", ""));
    correctCases.add(new NotEmptyAlternateIfOtherIsEmptyTestBean("", "", "filled"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<NotEmptyAlternateIfOtherIsEmptyTestBean> getWrongEmptyTestBeans() {
    final List<NotEmptyAlternateIfOtherIsEmptyTestBean> wrongCases =
        new ArrayList<NotEmptyAlternateIfOtherIsEmptyTestBean>();
    wrongCases.add(new NotEmptyAlternateIfOtherIsEmptyTestBean(null, null, null));
    wrongCases.add(new NotEmptyAlternateIfOtherIsEmptyTestBean(null, "", null));
    wrongCases.add(new NotEmptyAlternateIfOtherIsEmptyTestBean(null, null, ""));
    wrongCases.add(new NotEmptyAlternateIfOtherIsEmptyTestBean(null, "", ""));

    wrongCases.add(new NotEmptyAlternateIfOtherIsEmptyTestBean("", null, null));
    wrongCases.add(new NotEmptyAlternateIfOtherIsEmptyTestBean("", "", null));
    wrongCases.add(new NotEmptyAlternateIfOtherIsEmptyTestBean("", null, ""));
    wrongCases.add(new NotEmptyAlternateIfOtherIsEmptyTestBean("", "", ""));
    return wrongCases;
  }
}
