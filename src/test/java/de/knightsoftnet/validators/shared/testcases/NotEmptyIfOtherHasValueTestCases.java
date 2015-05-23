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

import de.knightsoftnet.validators.shared.beans.NotEmptyIfOtherHasValueTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for (not) empty if other has value test.
 *
 * @author Manfred Tremmel
 *
 */
public class NotEmptyIfOtherHasValueTestCases {

  /**
   * get beans with empty value.
   *
   * @return correct test beans
   */
  public static final List<NotEmptyIfOtherHasValueTestBean> getValueIsNotSetBeans() {
    final List<NotEmptyIfOtherHasValueTestBean> correctCases =
        new ArrayList<NotEmptyIfOtherHasValueTestBean>();
    correctCases.add(new NotEmptyIfOtherHasValueTestBean(null, null, null));
    correctCases.add(new NotEmptyIfOtherHasValueTestBean(null, "", null));
    correctCases.add(new NotEmptyIfOtherHasValueTestBean(null, null, ""));
    correctCases.add(new NotEmptyIfOtherHasValueTestBean(null, "", ""));
    correctCases.add(new NotEmptyIfOtherHasValueTestBean(null, "filled", null));
    correctCases.add(new NotEmptyIfOtherHasValueTestBean(null, null, "filled"));
    correctCases.add(new NotEmptyIfOtherHasValueTestBean(null, "filled", "filled"));
    correctCases.add(new NotEmptyIfOtherHasValueTestBean(null, "filled", ""));
    correctCases.add(new NotEmptyIfOtherHasValueTestBean(null, "", "filled"));
    correctCases.add(new NotEmptyIfOtherHasValueTestBean(null, "filled", "filled"));
    return correctCases;
  }

  /**
   * get beans with unchecked value.
   *
   * @return correct test beans
   */
  public static final List<NotEmptyIfOtherHasValueTestBean> getValueUncheckedSetBeans() {
    final List<NotEmptyIfOtherHasValueTestBean> correctCases =
        new ArrayList<NotEmptyIfOtherHasValueTestBean>();
    correctCases.add(new NotEmptyIfOtherHasValueTestBean("filled", null, null));
    correctCases.add(new NotEmptyIfOtherHasValueTestBean("filled", "", null));
    correctCases.add(new NotEmptyIfOtherHasValueTestBean("filled", null, ""));
    correctCases.add(new NotEmptyIfOtherHasValueTestBean("filled", "", ""));
    correctCases.add(new NotEmptyIfOtherHasValueTestBean("filled", "filled", null));
    correctCases.add(new NotEmptyIfOtherHasValueTestBean("filled", "filled", ""));
    correctCases.add(new NotEmptyIfOtherHasValueTestBean("filled", null, "filled"));
    correctCases.add(new NotEmptyIfOtherHasValueTestBean("filled", "", "filled"));
    correctCases.add(new NotEmptyIfOtherHasValueTestBean("filled", "filled", "filled"));
    return correctCases;
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<NotEmptyIfOtherHasValueTestBean> getCorrectTestBeans() {
    final List<NotEmptyIfOtherHasValueTestBean> correctCases =
        new ArrayList<NotEmptyIfOtherHasValueTestBean>();
    correctCases.add(new NotEmptyIfOtherHasValueTestBean("street", "filled", null));
    correctCases.add(new NotEmptyIfOtherHasValueTestBean("street", "filled", ""));
    correctCases.add(new NotEmptyIfOtherHasValueTestBean("postOfficeBox", null, "filled"));
    correctCases.add(new NotEmptyIfOtherHasValueTestBean("postOfficeBox", "", "filled"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<NotEmptyIfOtherHasValueTestBean> getWrongEmptyTestBeans() {
    final List<NotEmptyIfOtherHasValueTestBean> wrongCases =
        new ArrayList<NotEmptyIfOtherHasValueTestBean>();
    wrongCases.add(new NotEmptyIfOtherHasValueTestBean("street", "filled", "filled"));
    wrongCases.add(new NotEmptyIfOtherHasValueTestBean("postOfficeBox", "filled", "filled"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<NotEmptyIfOtherHasValueTestBean> getWrongNotEmptyTestBeans() {
    final List<NotEmptyIfOtherHasValueTestBean> wrongCases =
        new ArrayList<NotEmptyIfOtherHasValueTestBean>();
    wrongCases.add(new NotEmptyIfOtherHasValueTestBean("street", null, null));
    wrongCases.add(new NotEmptyIfOtherHasValueTestBean("street", "", null));
    wrongCases.add(new NotEmptyIfOtherHasValueTestBean("postOfficeBox", null, null));
    wrongCases.add(new NotEmptyIfOtherHasValueTestBean("postOfficeBox", null, ""));
    return wrongCases;
  }
}
