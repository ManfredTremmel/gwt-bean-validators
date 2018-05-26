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

import de.knightsoftnet.validators.shared.beans.PostalCodeTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for postal code test.
 *
 * @author Manfred Tremmel
 *
 */
public class PostalCodeTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final PostalCodeTestBean getEmptyTestBean() {
    return new PostalCodeTestBean(null, null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<PostalCodeTestBean> getCorrectTestBeans() {
    final List<PostalCodeTestBean> correctCases = new ArrayList<PostalCodeTestBean>();
    correctCases.add(new PostalCodeTestBean("DE", "81925"));
    correctCases.add(new PostalCodeTestBean("AT", "1100"));
    correctCases.add(new PostalCodeTestBean("GB", "N1 2PN"));
    correctCases.add(new PostalCodeTestBean("US", "20001"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<PostalCodeTestBean> getWrongTestBeans() {
    final List<PostalCodeTestBean> wrongCases = new ArrayList<PostalCodeTestBean>();
    wrongCases.add(new PostalCodeTestBean("AT", "81925"));
    wrongCases.add(new PostalCodeTestBean("US", "1100"));
    wrongCases.add(new PostalCodeTestBean("DE", "N1 2PN"));
    wrongCases.add(new PostalCodeTestBean("GB", "20001"));
    return wrongCases;
  }
}
