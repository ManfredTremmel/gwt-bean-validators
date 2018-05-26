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

import de.knightsoftnet.validators.shared.beans.EmptyIfOtherIsEmptyTestBean;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for empty if other is empty test.
 *
 * @author Manfred Tremmel
 *
 */
public class EmptyIfOtherIsEmptyTestCases {
  /**
   * get empty test beans.
   *
   * @return empty test bean
   */
  public static final List<EmptyIfOtherIsEmptyTestBean> getEmptyTestBeans() {
    final List<EmptyIfOtherIsEmptyTestBean> correctCases =
        new ArrayList<EmptyIfOtherIsEmptyTestBean>();
    correctCases.add(new EmptyIfOtherIsEmptyTestBean(null, null));
    correctCases.add(new EmptyIfOtherIsEmptyTestBean(null, StringUtils.EMPTY));
    correctCases.add(new EmptyIfOtherIsEmptyTestBean(StringUtils.EMPTY, null));
    correctCases.add(new EmptyIfOtherIsEmptyTestBean(StringUtils.EMPTY, StringUtils.EMPTY));
    return correctCases;
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<EmptyIfOtherIsEmptyTestBean> getCorrectTestBeans() {
    final List<EmptyIfOtherIsEmptyTestBean> correctCases =
        new ArrayList<EmptyIfOtherIsEmptyTestBean>();
    correctCases.add(new EmptyIfOtherIsEmptyTestBean("filled", null));
    correctCases.add(new EmptyIfOtherIsEmptyTestBean("filled", StringUtils.EMPTY));
    correctCases.add(new EmptyIfOtherIsEmptyTestBean("filled", "filled"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<EmptyIfOtherIsEmptyTestBean> getWrongTestBeans() {
    final List<EmptyIfOtherIsEmptyTestBean> wrongCases =
        new ArrayList<EmptyIfOtherIsEmptyTestBean>();
    wrongCases.add(new EmptyIfOtherIsEmptyTestBean(null, "filled"));
    wrongCases.add(new EmptyIfOtherIsEmptyTestBean(StringUtils.EMPTY, "filled"));
    return wrongCases;
  }
}
