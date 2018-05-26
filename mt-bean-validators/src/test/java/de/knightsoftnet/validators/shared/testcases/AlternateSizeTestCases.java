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

import de.knightsoftnet.validators.shared.beans.AlternateSizeTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for alternate size test.
 *
 * @author Manfred Tremmel
 *
 */
public class AlternateSizeTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final AlternateSizeTestBean getEmptyTestBean() {
    return new AlternateSizeTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<AlternateSizeTestBean> getCorrectTestBeans() {
    final List<AlternateSizeTestBean> correctCases = new ArrayList<AlternateSizeTestBean>();
    correctCases.add(new AlternateSizeTestBean("abcABCou!3"));
    correctCases.add(new AlternateSizeTestBean("3251202537"));
    correctCases.add(new AlternateSizeTestBean("3453136446"));
    correctCases.add(new AlternateSizeTestBean("4035600210708"));
    correctCases.add(new AlternateSizeTestBean("9783453136441"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<AlternateSizeTestBean> getWrongTestBeans() {
    final List<AlternateSizeTestBean> wrongCases = new ArrayList<AlternateSizeTestBean>();
    wrongCases.add(new AlternateSizeTestBean("308770192"));
    wrongCases.add(new AlternateSizeTestBean("32512253"));
    wrongCases.add(new AlternateSizeTestBean("34531365468"));
    wrongCases.add(new AlternateSizeTestBean("403560821070"));
    wrongCases.add(new AlternateSizeTestBean("978345313654"));
    return wrongCases;
  }
}
