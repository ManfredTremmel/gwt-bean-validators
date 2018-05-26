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

import de.knightsoftnet.validators.shared.beans.HibernateSizeTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for hibernate size test.
 *
 * @author Manfred Tremmel
 *
 */
public class HibernateSizeTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final HibernateSizeTestBean getEmptyTestBean() {
    return new HibernateSizeTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<HibernateSizeTestBean> getCorrectTestBeans() {
    final List<HibernateSizeTestBean> correctCases = new ArrayList<HibernateSizeTestBean>();
    correctCases.add(new HibernateSizeTestBean(null));
    correctCases.add(new HibernateSizeTestBean("abcde"));
    correctCases.add(new HibernateSizeTestBean("abcdefghij"));
    correctCases.add(new HibernateSizeTestBean("abcdefghijklmno"));
    correctCases.add(new HibernateSizeTestBean("abcdefghijklmnopqrst"));

    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernateSizeTestBean> getWrongtoSmallTestBeans() {
    final List<HibernateSizeTestBean> wrongCases = new ArrayList<HibernateSizeTestBean>();
    wrongCases.add(new HibernateSizeTestBean(""));
    wrongCases.add(new HibernateSizeTestBean("abcd"));
    wrongCases.add(new HibernateSizeTestBean("abcdefghijklmnopqrstu"));

    return wrongCases;
  }
}
