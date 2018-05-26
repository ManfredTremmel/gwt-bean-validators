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

import de.knightsoftnet.validators.shared.beans.HibernateFutureTestBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * get test cases for hibernate future test.
 *
 * @author Manfred Tremmel
 *
 */
public class HibernateFutureTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final HibernateFutureTestBean getEmptyTestBean() {
    return new HibernateFutureTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<HibernateFutureTestBean> getCorrectTestBeans() {
    final List<HibernateFutureTestBean> correctCases = new ArrayList<HibernateFutureTestBean>();
    correctCases.add(new HibernateFutureTestBean(null));
    correctCases.add(new HibernateFutureTestBean(getFutureDate()));

    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernateFutureTestBean> getWrongtoSmallTestBeans() {
    final List<HibernateFutureTestBean> wrongCases = new ArrayList<HibernateFutureTestBean>();
    wrongCases.add(new HibernateFutureTestBean(getPastDate()));

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
