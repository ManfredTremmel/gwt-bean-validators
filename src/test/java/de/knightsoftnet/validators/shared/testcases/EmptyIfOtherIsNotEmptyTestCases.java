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

import de.knightsoftnet.validators.shared.beans.EmptyIfOtherIsNotEmptyTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for empty if other is not empty test.
 *
 * @author Manfred Tremmel
 *
 */
public class EmptyIfOtherIsNotEmptyTestCases {
  /**
   * get empty test beans.
   *
   * @return empty test bean
   */
  public static final List<EmptyIfOtherIsNotEmptyTestBean> getEmptyTestBeans() {
    final List<EmptyIfOtherIsNotEmptyTestBean> correctCases =
        new ArrayList<EmptyIfOtherIsNotEmptyTestBean>();
    correctCases.add(new EmptyIfOtherIsNotEmptyTestBean(null, null));
    correctCases.add(new EmptyIfOtherIsNotEmptyTestBean(null, ""));
    correctCases.add(new EmptyIfOtherIsNotEmptyTestBean("", null));
    correctCases.add(new EmptyIfOtherIsNotEmptyTestBean("", ""));
    return correctCases;
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<EmptyIfOtherIsNotEmptyTestBean> getCorrectTestBeans() {
    final List<EmptyIfOtherIsNotEmptyTestBean> correctCases =
        new ArrayList<EmptyIfOtherIsNotEmptyTestBean>();
    correctCases.add(new EmptyIfOtherIsNotEmptyTestBean("filled", null));
    correctCases.add(new EmptyIfOtherIsNotEmptyTestBean("filled", ""));
    correctCases.add(new EmptyIfOtherIsNotEmptyTestBean(null, "filled"));
    correctCases.add(new EmptyIfOtherIsNotEmptyTestBean("", "filled"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<EmptyIfOtherIsNotEmptyTestBean> getWrongTestBeans() {
    final List<EmptyIfOtherIsNotEmptyTestBean> wrongCases =
        new ArrayList<EmptyIfOtherIsNotEmptyTestBean>();
    wrongCases.add(new EmptyIfOtherIsNotEmptyTestBean("filled", "filled"));
    return wrongCases;
  }
}
