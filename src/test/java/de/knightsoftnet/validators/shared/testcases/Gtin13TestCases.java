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

import de.knightsoftnet.validators.shared.beans.Gtin13TestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for gtin13/ean13 test.
 *
 * @author Manfred Tremmel
 *
 */
public class Gtin13TestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final Gtin13TestBean getEmptyTestBean() {
    return new Gtin13TestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<Gtin13TestBean> getCorrectTestBeans() {
    final List<Gtin13TestBean> correctCases = new ArrayList<Gtin13TestBean>();
    correctCases.add(new Gtin13TestBean("4035600210708"));
    correctCases.add(new Gtin13TestBean("4250155401375"));
    correctCases.add(new Gtin13TestBean("9004617011702"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<Gtin13TestBean> getWrongTestBeans() {
    final List<Gtin13TestBean> wrongCases = new ArrayList<Gtin13TestBean>();
    wrongCases.add(new Gtin13TestBean("4035600210078"));
    wrongCases.add(new Gtin13TestBean("4250515401375"));
    wrongCases.add(new Gtin13TestBean("4035601210078"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<Gtin13TestBean> getToSmallTestBeans() {
    final List<Gtin13TestBean> wrongCases = new ArrayList<Gtin13TestBean>();
    wrongCases.add(new Gtin13TestBean("4035600210"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<Gtin13TestBean> getToBigTestBeans() {
    final List<Gtin13TestBean> wrongCases = new ArrayList<Gtin13TestBean>();
    wrongCases.add(new Gtin13TestBean("40356002107081"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<Gtin13TestBean> getNotNumericTestBeans() {
    final List<Gtin13TestBean> wrongCases = new ArrayList<Gtin13TestBean>();
    wrongCases.add(new Gtin13TestBean("403560021070Y"));
    return wrongCases;
  }
}
