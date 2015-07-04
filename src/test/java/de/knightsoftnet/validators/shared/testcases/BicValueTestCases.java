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

import de.knightsoftnet.validators.shared.beans.BicValueTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for bic value test.
 *
 * @author Manfred Tremmel
 *
 */
public class BicValueTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final BicValueTestBean getEmptyTestBean() {
    return new BicValueTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<BicValueTestBean> getCorrectTestBeans() {
    final List<BicValueTestBean> correctCases = new ArrayList<BicValueTestBean>();
    correctCases.add(new BicValueTestBean("GENODEFF701"));
    correctCases.add(new BicValueTestBean("GENODEM1GLS"));
    correctCases.add(new BicValueTestBean("GIBAATWWXXX"));
    correctCases.add(new BicValueTestBean("POFICHBEXXX"));
    correctCases.add(new BicValueTestBean("CCRTIT21"));
    correctCases.add(new BicValueTestBean("HELADEF1RRS"));
    correctCases.add(new BicValueTestBean("CHASGB2LXXX"));
    correctCases.add(new BicValueTestBean("BBRUBEBB"));
    correctCases.add(new BicValueTestBean("SYBKDK22"));
    correctCases.add(new BicValueTestBean("INGBNL2A"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<BicValueTestBean> getWrongCountryTestBeans() {
    final List<BicValueTestBean> wrongCases = new ArrayList<BicValueTestBean>();
    wrongCases.add(new BicValueTestBean("GENOXYFF701"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<BicValueTestBean> getWrongFormatTestBeans() {
    final List<BicValueTestBean> wrongCases = new ArrayList<BicValueTestBean>();
    wrongCases.add(new BicValueTestBean("GeNODEFF701"));
    wrongCases.add(new BicValueTestBean("CCRTIT01"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<BicValueTestBean> getWrongToShortTestBeans() {
    final List<BicValueTestBean> wrongCases = new ArrayList<BicValueTestBean>();
    wrongCases.add(new BicValueTestBean("GENODEFF70"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<BicValueTestBean> getWrongToBigTestBeans() {
    final List<BicValueTestBean> wrongCases = new ArrayList<BicValueTestBean>();
    wrongCases.add(new BicValueTestBean("GENODEFF7012"));
    return wrongCases;
  }
}
