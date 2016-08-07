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

import de.knightsoftnet.validators.shared.beans.HibernateSizeArrayTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for hibernate size array test.
 *
 * @author Manfred Tremmel
 *
 */
public class HibernateSizeArrayTestCases {

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<HibernateSizeArrayTestBean> getCorrectTestBeans() {
    final List<HibernateSizeArrayTestBean> correctCases =
        new ArrayList<HibernateSizeArrayTestBean>();
    correctCases.add(new HibernateSizeArrayTestBean(5));
    correctCases.add(new HibernateSizeArrayTestBean(7));
    correctCases.add(new HibernateSizeArrayTestBean(10));
    correctCases.add(new HibernateSizeArrayTestBean(15));
    correctCases.add(new HibernateSizeArrayTestBean(20));

    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernateSizeArrayTestBean> getWrongtoSmallTestBeans() {
    final List<HibernateSizeArrayTestBean> wrongCases = new ArrayList<HibernateSizeArrayTestBean>();
    wrongCases.add(new HibernateSizeArrayTestBean(0));
    wrongCases.add(new HibernateSizeArrayTestBean(2));
    wrongCases.add(new HibernateSizeArrayTestBean(4));
    wrongCases.add(new HibernateSizeArrayTestBean(21));
    wrongCases.add(new HibernateSizeArrayTestBean(100));

    return wrongCases;
  }
}
