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

import de.knightsoftnet.validators.shared.beans.MustBeEqualTestBean;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for must be equal test.
 *
 * @author Manfred Tremmel
 *
 */
public class MustBeEqualTestCases {
  /**
   * get empty test beans.
   *
   * @return empty test bean
   */
  public static final List<MustBeEqualTestBean> getEmptyTestBeans() {
    final List<MustBeEqualTestBean> correctCases = new ArrayList<MustBeEqualTestBean>();
    correctCases.add(new MustBeEqualTestBean(null, null, null));
    correctCases
        .add(new MustBeEqualTestBean(StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY));
    return correctCases;
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<MustBeEqualTestBean> getCorrectTestBeans() {
    final List<MustBeEqualTestBean> correctCases = new ArrayList<MustBeEqualTestBean>();
    correctCases.add(new MustBeEqualTestBean("old", "filled", "filled"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<MustBeEqualTestBean> getWrongTestBeans() {
    final List<MustBeEqualTestBean> wrongCases = new ArrayList<MustBeEqualTestBean>();
    wrongCases.add(new MustBeEqualTestBean("old", null, "filled"));
    wrongCases.add(new MustBeEqualTestBean("old", StringUtils.EMPTY, "filled"));
    wrongCases.add(new MustBeEqualTestBean("old", "filled", null));
    wrongCases.add(new MustBeEqualTestBean("old", "filled", StringUtils.EMPTY));
    wrongCases.add(new MustBeEqualTestBean("old", "filled", "filledother"));
    return wrongCases;
  }
}
