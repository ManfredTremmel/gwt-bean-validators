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

import de.knightsoftnet.validators.shared.beans.AgeLimitTestBean;

import org.apache.commons.lang3.time.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * get test cases for age limit test.
 *
 * @author Manfred Tremmel
 *
 */
public class AgeLimitTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final AgeLimitTestBean getEmptyTestBean() {
    return new AgeLimitTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<AgeLimitTestBean> getCorrectTestBeans() {
    final List<AgeLimitTestBean> correctCases = new ArrayList<AgeLimitTestBean>();
    correctCases.add(new AgeLimitTestBean(DateUtils.addYears(new Date(),
        0 - AgeLimitTestBean.AGE_LIMIT - 1)));
    correctCases.add(new AgeLimitTestBean(DateUtils.addYears(new Date(),
        0 - AgeLimitTestBean.AGE_LIMIT)));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<AgeLimitTestBean> getWrongTestBeans() {
    final List<AgeLimitTestBean> wrongCases = new ArrayList<AgeLimitTestBean>();
    wrongCases.add(new AgeLimitTestBean(new Date()));
    wrongCases.add(new AgeLimitTestBean(DateUtils.addDays(
        DateUtils.addYears(new Date(), 0 - AgeLimitTestBean.AGE_LIMIT), 1)));
    return wrongCases;
  }
}
