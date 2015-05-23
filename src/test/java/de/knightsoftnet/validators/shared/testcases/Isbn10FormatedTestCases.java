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

import de.knightsoftnet.validators.shared.beans.Isbn10FormatedTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for isbn10 formated test.
 *
 * @author Manfred Tremmel
 *
 */
public class Isbn10FormatedTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final Isbn10FormatedTestBean getEmptyTestBean() {
    return new Isbn10FormatedTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<Isbn10FormatedTestBean> getCorrectTestBeans() {
    final List<Isbn10FormatedTestBean> correctCases = new ArrayList<Isbn10FormatedTestBean>();
    correctCases.add(new Isbn10FormatedTestBean("3-80-770171-0"));
    correctCases.add(new Isbn10FormatedTestBean("3-80-770205-9"));
    correctCases.add(new Isbn10FormatedTestBean("3-80-770192-3"));
    correctCases.add(new Isbn10FormatedTestBean("3-86-640001-2"));
    correctCases.add(new Isbn10FormatedTestBean("3-93-751412-0"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<Isbn10FormatedTestBean> getWrongTestBeans() {
    final List<Isbn10FormatedTestBean> wrongCases = new ArrayList<Isbn10FormatedTestBean>();
    wrongCases.add(new Isbn10FormatedTestBean("3-80-770170-0"));
    wrongCases.add(new Isbn10FormatedTestBean("3-80-770205-8"));
    wrongCases.add(new Isbn10FormatedTestBean("3-80-770193-2"));
    wrongCases.add(new Isbn10FormatedTestBean("3-86-640201-2"));
    wrongCases.add(new Isbn10FormatedTestBean("3-93-571412-0"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<Isbn10FormatedTestBean> getToSmallTestBeans() {
    final List<Isbn10FormatedTestBean> wrongCases = new ArrayList<Isbn10FormatedTestBean>();
    wrongCases.add(new Isbn10FormatedTestBean("3-80-770193"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<Isbn10FormatedTestBean> getToBigTestBeans() {
    final List<Isbn10FormatedTestBean> wrongCases = new ArrayList<Isbn10FormatedTestBean>();
    wrongCases.add(new Isbn10FormatedTestBean("3-80-770192-354"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<Isbn10FormatedTestBean> getWrongFormatedTestBeans() {
    final List<Isbn10FormatedTestBean> wrongCases = new ArrayList<Isbn10FormatedTestBean>();
    wrongCases.add(new Isbn10FormatedTestBean("3 86-640201-2"));
    wrongCases.add(new Isbn10FormatedTestBean("3+93-571412-0"));
    wrongCases.add(new Isbn10FormatedTestBean("3-80/770193-2"));
    wrongCases.add(new Isbn10FormatedTestBean("3-866-40201-2"));
    wrongCases.add(new Isbn10FormatedTestBean("3-93-571-4120"));
    return wrongCases;
  }
}
