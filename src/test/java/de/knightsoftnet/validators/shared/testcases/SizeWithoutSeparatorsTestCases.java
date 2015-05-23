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

import de.knightsoftnet.validators.shared.beans.SizeWithoutSeparatorsTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for size without separators test.
 *
 * @author Manfred Tremmel
 *
 */
public class SizeWithoutSeparatorsTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final SizeWithoutSeparatorsTestBean getEmptyTestBean() {
    return new SizeWithoutSeparatorsTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<SizeWithoutSeparatorsTestBean> getCorrectTestBeans() {
    final List<SizeWithoutSeparatorsTestBean> correctCases =
        new ArrayList<SizeWithoutSeparatorsTestBean>();
    correctCases.add(new SizeWithoutSeparatorsTestBean("12345"));
    correctCases.add(new SizeWithoutSeparatorsTestBean("12 34 5"));
    correctCases.add(new SizeWithoutSeparatorsTestBean("12-34-5"));
    correctCases.add(new SizeWithoutSeparatorsTestBean("12/34/5"));
    correctCases.add(new SizeWithoutSeparatorsTestBean("12345678"));
    correctCases.add(new SizeWithoutSeparatorsTestBean("12 34 56 78"));
    correctCases.add(new SizeWithoutSeparatorsTestBean("12-34-56-78"));
    correctCases.add(new SizeWithoutSeparatorsTestBean("12/34/56/78"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<SizeWithoutSeparatorsTestBean> getWrongTestBeans() {
    final List<SizeWithoutSeparatorsTestBean> wrongCases =
        new ArrayList<SizeWithoutSeparatorsTestBean>();
    wrongCases.add(new SizeWithoutSeparatorsTestBean("1234"));
    wrongCases.add(new SizeWithoutSeparatorsTestBean("12 34"));
    wrongCases.add(new SizeWithoutSeparatorsTestBean("12-34-"));
    wrongCases.add(new SizeWithoutSeparatorsTestBean("1/2/3/4"));
    wrongCases.add(new SizeWithoutSeparatorsTestBean("123456789"));
    wrongCases.add(new SizeWithoutSeparatorsTestBean("12 34 56 78 9"));
    wrongCases.add(new SizeWithoutSeparatorsTestBean("12-34-56-78-9"));
    wrongCases.add(new SizeWithoutSeparatorsTestBean("12/34/56/78/9"));
    return wrongCases;
  }
}
