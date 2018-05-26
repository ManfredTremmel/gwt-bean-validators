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

import de.knightsoftnet.validators.shared.beans.LevenshteinDistanceTestBean;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for levenshtein distance test.
 *
 * @author Manfred Tremmel
 *
 */
public class LevenshteinDistanceTestCases {
  /**
   * get empty test beans.
   *
   * @return empty test bean
   */
  public static final List<LevenshteinDistanceTestBean> getEmptyTestBeans() {
    final List<LevenshteinDistanceTestBean> correctCases =
        new ArrayList<LevenshteinDistanceTestBean>();
    correctCases.add(new LevenshteinDistanceTestBean(null, null));
    correctCases.add(new LevenshteinDistanceTestBean(StringUtils.EMPTY, StringUtils.EMPTY));
    return correctCases;
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<LevenshteinDistanceTestBean> getCorrectTestBeans() {
    final List<LevenshteinDistanceTestBean> correctCases =
        new ArrayList<LevenshteinDistanceTestBean>();
    correctCases.add(new LevenshteinDistanceTestBean("fly", "ant"));
    correctCases.add(new LevenshteinDistanceTestBean("elephant", "hippo"));
    correctCases.add(new LevenshteinDistanceTestBean("hippo", "elephant"));
    correctCases.add(new LevenshteinDistanceTestBean("hippo", "zzzzzzzz"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<LevenshteinDistanceTestBean> getWrongTestBeans() {
    final List<LevenshteinDistanceTestBean> wrongCases =
        new ArrayList<LevenshteinDistanceTestBean>();
    wrongCases.add(new LevenshteinDistanceTestBean("hallo", "hallo"));
    wrongCases.add(new LevenshteinDistanceTestBean("hello", "hallo"));
    wrongCases.add(new LevenshteinDistanceTestBean("frog", "fog"));
    return wrongCases;
  }
}
