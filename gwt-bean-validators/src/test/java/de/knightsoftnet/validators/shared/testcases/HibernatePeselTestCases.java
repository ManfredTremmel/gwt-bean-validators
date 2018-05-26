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

import de.knightsoftnet.validators.shared.beans.HibernatePeselTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for Pesel test.
 *
 * @author Manfred Tremmel
 *
 */
public class HibernatePeselTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final HibernatePeselTestBean getEmptyTestBean() {
    return new HibernatePeselTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<HibernatePeselTestBean> getCorrectTestBeans() {
    final List<HibernatePeselTestBean> correctCases = new ArrayList<>();
    correctCases.add(new HibernatePeselTestBean("92041903790"));
    correctCases.add(new HibernatePeselTestBean("44051401359"));
    correctCases.add(new HibernatePeselTestBean("70100619901"));
    correctCases.add(new HibernatePeselTestBean("80082107231"));
    correctCases.add(new HibernatePeselTestBean("00301202868"));
    correctCases.add(new HibernatePeselTestBean("00271100559"));
    correctCases.add(new HibernatePeselTestBean("12241301417"));
    correctCases.add(new HibernatePeselTestBean("12252918020"));
    correctCases.add(new HibernatePeselTestBean("12262911406"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernatePeselTestBean> getWrongTestBeans() {
    final List<HibernatePeselTestBean> wrongCases = new ArrayList<>();
    wrongCases.add(new HibernatePeselTestBean("44051401358"));
    wrongCases.add(new HibernatePeselTestBean("92041903791"));
    wrongCases.add(new HibernatePeselTestBean("80082107232"));
    wrongCases.add(new HibernatePeselTestBean("80062210349"));
    wrongCases.add(new HibernatePeselTestBean("00301202866"));
    wrongCases.add(new HibernatePeselTestBean("00271100557"));
    wrongCases.add(new HibernatePeselTestBean("12241301418"));
    wrongCases.add(new HibernatePeselTestBean("12252918029"));
    wrongCases.add(new HibernatePeselTestBean("12262911402"));
    return wrongCases;
  }
}
