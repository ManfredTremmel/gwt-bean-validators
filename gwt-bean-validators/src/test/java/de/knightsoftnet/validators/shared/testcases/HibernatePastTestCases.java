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

import de.knightsoftnet.validators.shared.beans.HibernatePastTestBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * get test cases for hibernate past test.
 *
 * @author Manfred Tremmel
 *
 */
public class HibernatePastTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final HibernatePastTestBean getEmptyTestBean() {
    return new HibernatePastTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<HibernatePastTestBean> getCorrectTestBeans() {
    final List<HibernatePastTestBean> correctCases = new ArrayList<HibernatePastTestBean>();
    correctCases.add(new HibernatePastTestBean(null));
    correctCases.add(new HibernatePastTestBean(getPastDate()));

    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernatePastTestBean> getWrongtoSmallTestBeans() {
    final List<HibernatePastTestBean> wrongCases = new ArrayList<HibernatePastTestBean>();
    wrongCases.add(new HibernatePastTestBean(getFutureDate()));

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
