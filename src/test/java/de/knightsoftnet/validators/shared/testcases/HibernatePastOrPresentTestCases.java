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

import de.knightsoftnet.validators.shared.beans.HibernatePastOrPresentTestBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * get test cases for hibernate past or present test.
 *
 * @author Manfred Tremmel
 *
 */
public class HibernatePastOrPresentTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final HibernatePastOrPresentTestBean getEmptyTestBean() {
    return new HibernatePastOrPresentTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<HibernatePastOrPresentTestBean> getCorrectTestBeans() {
    final List<HibernatePastOrPresentTestBean> correctCases =
        new ArrayList<HibernatePastOrPresentTestBean>();
    correctCases.add(new HibernatePastOrPresentTestBean(null));
    correctCases.add(new HibernatePastOrPresentTestBean(getPastDate()));

    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernatePastOrPresentTestBean> getWrongtoSmallTestBeans() {
    final List<HibernatePastOrPresentTestBean> wrongCases =
        new ArrayList<HibernatePastOrPresentTestBean>();
    wrongCases.add(new HibernatePastOrPresentTestBean(getFutureDate()));

    return wrongCases;
  }

  private static Date getFutureDate() {
    final Date date = new Date();
    final long timeStamp = date.getTime();
    date.setTime(timeStamp + 31557600000L);
    return date;
  }

  private static Date getPastDate() {
    final Date date = new Date();
    final long timeStamp = date.getTime();
    date.setTime(timeStamp - 31557600000L);
    return date;
  }
}
