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

import de.knightsoftnet.validators.shared.beans.GlnTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for gln test.
 *
 * @author Manfred Tremmel
 *
 */
public class GlnTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final GlnTestBean getEmptyTestBean() {
    return new GlnTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<GlnTestBean> getCorrectTestBeans() {
    final List<GlnTestBean> correctCases = new ArrayList<GlnTestBean>();
    correctCases.add(new GlnTestBean("4035600210708"));
    correctCases.add(new GlnTestBean("4250155401375"));
    correctCases.add(new GlnTestBean("9004617011702"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<GlnTestBean> getWrongTestBeans() {
    final List<GlnTestBean> wrongCases = new ArrayList<GlnTestBean>();
    wrongCases.add(new GlnTestBean("4035600210078"));
    wrongCases.add(new GlnTestBean("4250515401375"));
    wrongCases.add(new GlnTestBean("4035601210078"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<GlnTestBean> getToSmallTestBeans() {
    final List<GlnTestBean> wrongCases = new ArrayList<GlnTestBean>();
    wrongCases.add(new GlnTestBean("4035600210"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<GlnTestBean> getToBigTestBeans() {
    final List<GlnTestBean> wrongCases = new ArrayList<GlnTestBean>();
    wrongCases.add(new GlnTestBean("40356002107081"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<GlnTestBean> getNotNumericTestBeans() {
    final List<GlnTestBean> wrongCases = new ArrayList<GlnTestBean>();
    wrongCases.add(new GlnTestBean("403560021070Y"));
    return wrongCases;
  }
}
