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

import de.knightsoftnet.validators.shared.beans.BicTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for bic test.
 *
 * @author Manfred Tremmel
 *
 */
public class BicTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final BicTestBean getEmptyTestBean() {
    return new BicTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<BicTestBean> getCorrectTestBeans() {
    final List<BicTestBean> correctCases = new ArrayList<BicTestBean>();
    correctCases.add(new BicTestBean("GENODEFF701"));
    correctCases.add(new BicTestBean("GENODEM1GLS"));
    correctCases.add(new BicTestBean("GIBAATWWXXX"));
    correctCases.add(new BicTestBean("POFICHBEXXX"));
    correctCases.add(new BicTestBean("CCRTIT21"));
    correctCases.add(new BicTestBean("HELADEF1RRS"));
    correctCases.add(new BicTestBean("CHASGB2LXXX"));
    correctCases.add(new BicTestBean("BBRUBEBB"));
    correctCases.add(new BicTestBean("SYBKDK22"));
    correctCases.add(new BicTestBean("INGBNL2A"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<BicTestBean> getWrongCountryTestBeans() {
    final List<BicTestBean> wrongCases = new ArrayList<BicTestBean>();
    wrongCases.add(new BicTestBean("GENOXYFF701"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<BicTestBean> getWrongFormatTestBeans() {
    final List<BicTestBean> wrongCases = new ArrayList<BicTestBean>();
    wrongCases.add(new BicTestBean("GeNODEFF701"));
    wrongCases.add(new BicTestBean("CCRTIT01"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<BicTestBean> getWrongToShortTestBeans() {
    final List<BicTestBean> wrongCases = new ArrayList<BicTestBean>();
    wrongCases.add(new BicTestBean("GENODEFF70"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<BicTestBean> getWrongToBigTestBeans() {
    final List<BicTestBean> wrongCases = new ArrayList<BicTestBean>();
    wrongCases.add(new BicTestBean("GENODEFF7012"));
    return wrongCases;
  }
}
