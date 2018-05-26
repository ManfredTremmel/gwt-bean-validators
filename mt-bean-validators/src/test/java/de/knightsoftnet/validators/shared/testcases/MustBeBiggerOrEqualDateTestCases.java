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

import de.knightsoftnet.validators.shared.beans.MustBeBiggerOrEqualDateTestBean;

import org.apache.commons.lang3.time.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * get test cases for must be bigger or equal test with date values.
 *
 * @author Manfred Tremmel
 *
 */
public class MustBeBiggerOrEqualDateTestCases {
  /**
   * get empty test beans.
   *
   * @return empty test bean
   */
  public static final List<MustBeBiggerOrEqualDateTestBean> getEmptyTestBeans() {
    final List<MustBeBiggerOrEqualDateTestBean> correctCases = new ArrayList<>();
    correctCases.add(new MustBeBiggerOrEqualDateTestBean(null, null));
    return correctCases;
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<MustBeBiggerOrEqualDateTestBean> getCorrectTestBeans() {
    final List<MustBeBiggerOrEqualDateTestBean> correctCases = new ArrayList<>();
    final Date now = new Date();
    final Date future = DateUtils.addMilliseconds(now, 1);
    correctCases.add(new MustBeBiggerOrEqualDateTestBean(null, now));
    correctCases.add(new MustBeBiggerOrEqualDateTestBean(now, now));
    correctCases.add(new MustBeBiggerOrEqualDateTestBean(now, future));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<MustBeBiggerOrEqualDateTestBean> getWrongTestBeans() {
    final List<MustBeBiggerOrEqualDateTestBean> wrongCases = new ArrayList<>();
    final Date now = new Date();
    final Date future = DateUtils.addMilliseconds(now, 1);
    wrongCases.add(new MustBeBiggerOrEqualDateTestBean(future, now));
    wrongCases.add(new MustBeBiggerOrEqualDateTestBean(now, null));
    return wrongCases;
  }
}
