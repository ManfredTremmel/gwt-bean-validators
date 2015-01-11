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

import de.knightsoftnet.validators.shared.beans.BicWithSpacesTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for bic test.
 *
 * @author Manfred Tremmel
 *
 */
public class BicWithSpacesTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final BicWithSpacesTestBean getEmptyTestBean() {
    return new BicWithSpacesTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<BicWithSpacesTestBean> getCorrectTestBeans() {
    final List<BicWithSpacesTestBean> correctCases = new ArrayList<BicWithSpacesTestBean>();
    correctCases.add(new BicWithSpacesTestBean("GENODEFF701"));
    correctCases.add(new BicWithSpacesTestBean("GENO DE FF 701"));
    correctCases.add(new BicWithSpacesTestBean("GENODEM1GLS"));
    correctCases.add(new BicWithSpacesTestBean("GENO DE M1 GLS"));
    correctCases.add(new BicWithSpacesTestBean("GIBAATWWXXX"));
    correctCases.add(new BicWithSpacesTestBean("GIBA AT WW XXX"));
    correctCases.add(new BicWithSpacesTestBean("POFICHBEXXX"));
    correctCases.add(new BicWithSpacesTestBean("POFI CH BE XXX"));
    correctCases.add(new BicWithSpacesTestBean("CCRTIT21"));
    correctCases.add(new BicWithSpacesTestBean("CCRT IT 21"));
    correctCases.add(new BicWithSpacesTestBean("HELADEF1RRS"));
    correctCases.add(new BicWithSpacesTestBean("HELA DE F1 RRS"));
    correctCases.add(new BicWithSpacesTestBean("CHASGB2LXXX"));
    correctCases.add(new BicWithSpacesTestBean("CHAS GB 2L XXX"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<BicWithSpacesTestBean> getWrongCountryTestBeans() {
    final List<BicWithSpacesTestBean> wrongCases = new ArrayList<BicWithSpacesTestBean>();
    wrongCases.add(new BicWithSpacesTestBean("GENOXYFF701"));
    wrongCases.add(new BicWithSpacesTestBean("GENO XY FF 701"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<BicWithSpacesTestBean> getWrongFormatTestBeans() {
    final List<BicWithSpacesTestBean> wrongCases = new ArrayList<BicWithSpacesTestBean>();
    wrongCases.add(new BicWithSpacesTestBean("GeNODEFF701"));
    wrongCases.add(new BicWithSpacesTestBean("GeNO DE FF 701"));
    wrongCases.add(new BicWithSpacesTestBean("CCRTIT01"));
    wrongCases.add(new BicWithSpacesTestBean("CCRT IT 01"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<BicWithSpacesTestBean> getWrongToShortTestBeans() {
    final List<BicWithSpacesTestBean> wrongCases = new ArrayList<BicWithSpacesTestBean>();
    wrongCases.add(new BicWithSpacesTestBean("GENODEFF70"));
    wrongCases.add(new BicWithSpacesTestBean("GENO DE FF 70"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<BicWithSpacesTestBean> getWrongToBigTestBeans() {
    final List<BicWithSpacesTestBean> wrongCases = new ArrayList<BicWithSpacesTestBean>();
    wrongCases.add(new BicWithSpacesTestBean("GENODEFF7012"));
    wrongCases.add(new BicWithSpacesTestBean("GENO DE FF 7012"));
    return wrongCases;
  }
}
