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

import de.knightsoftnet.validators.shared.beans.MustBeSmallerIntegerTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for must be smaller test with integer values.
 *
 * @author Manfred Tremmel
 *
 */
public class MustBeSmallerIntegerTestCases {
  /**
   * get empty test beans.
   *
   * @return empty test bean
   */
  public static final List<MustBeSmallerIntegerTestBean> getEmptyTestBeans() {
    final List<MustBeSmallerIntegerTestBean> correctCases =
        new ArrayList<>();
    correctCases.add(new MustBeSmallerIntegerTestBean(null, null));
    return correctCases;
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<MustBeSmallerIntegerTestBean> getCorrectTestBeans() {
    final List<MustBeSmallerIntegerTestBean> correctCases =
        new ArrayList<>();
    correctCases.add(new MustBeSmallerIntegerTestBean(null, Integer.valueOf(3)));
    correctCases.add(new MustBeSmallerIntegerTestBean(Integer.valueOf(2), Integer.valueOf(3)));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<MustBeSmallerIntegerTestBean> getWrongTestBeans() {
    final List<MustBeSmallerIntegerTestBean> wrongCases =
        new ArrayList<>();
    wrongCases.add(new MustBeSmallerIntegerTestBean(Integer.valueOf(3), Integer.valueOf(2)));
    wrongCases.add(new MustBeSmallerIntegerTestBean(Integer.valueOf(2), Integer.valueOf(2)));
    wrongCases.add(new MustBeSmallerIntegerTestBean(Integer.valueOf(2), null));
    return wrongCases;
  }
}
