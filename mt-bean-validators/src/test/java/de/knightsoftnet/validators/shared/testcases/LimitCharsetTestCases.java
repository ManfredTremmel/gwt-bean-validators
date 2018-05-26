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

import de.knightsoftnet.validators.shared.beans.LimitCharsetTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for limit charset test.
 *
 * @author Manfred Tremmel
 *
 */
public class LimitCharsetTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final LimitCharsetTestBean getEmptyTestBean() {
    return new LimitCharsetTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<LimitCharsetTestBean> getCorrectTestBeans() {
    final List<LimitCharsetTestBean> correctCases = new ArrayList<>();
    correctCases.add(new LimitCharsetTestBean("abcdefghijklmnopqrstuvwxyz"));
    correctCases.add(new LimitCharsetTestBean("1234567890"));
    correctCases.add(new LimitCharsetTestBean("!,.-/(){[]}"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<LimitCharsetTestBean> getWrongTestBeans() {
    final List<LimitCharsetTestBean> wrongCases = new ArrayList<>();
    wrongCases.add(new LimitCharsetTestBean("ḃ ċ ḋ ḟ ġ ṁ ṗ ṡ ṫ"));
    wrongCases.add(new LimitCharsetTestBean("Σίγμα"));
    return wrongCases;
  }
}
