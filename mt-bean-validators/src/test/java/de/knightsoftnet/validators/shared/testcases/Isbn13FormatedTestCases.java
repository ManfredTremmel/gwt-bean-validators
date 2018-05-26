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

import de.knightsoftnet.validators.shared.beans.Isbn13FormatedTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for isbn13 formated test.
 *
 * @author Manfred Tremmel
 *
 */
public class Isbn13FormatedTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final Isbn13FormatedTestBean getEmptyTestBean() {
    return new Isbn13FormatedTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<Isbn13FormatedTestBean> getCorrectTestBeans() {
    final List<Isbn13FormatedTestBean> correctCases = new ArrayList<Isbn13FormatedTestBean>();
    correctCases.add(new Isbn13FormatedTestBean("978-3-83-621802-3"));
    correctCases.add(new Isbn13FormatedTestBean("978-3-83-621507-7"));
    correctCases.add(new Isbn13FormatedTestBean("978-3-89-864471-6"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<Isbn13FormatedTestBean> getWrongTestBeans() {
    final List<Isbn13FormatedTestBean> wrongCases = new ArrayList<Isbn13FormatedTestBean>();
    wrongCases.add(new Isbn13FormatedTestBean("978-3-83-621803-2"));
    wrongCases.add(new Isbn13FormatedTestBean("978-3-83-821507-7"));
    wrongCases.add(new Isbn13FormatedTestBean("978-3-89-964471-6"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<Isbn13FormatedTestBean> getToSmallTestBeans() {
    final List<Isbn13FormatedTestBean> wrongCases = new ArrayList<Isbn13FormatedTestBean>();
    wrongCases.add(new Isbn13FormatedTestBean("978-3-83-621803"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<Isbn13FormatedTestBean> getToBigTestBeans() {
    final List<Isbn13FormatedTestBean> wrongCases = new ArrayList<Isbn13FormatedTestBean>();
    wrongCases.add(new Isbn13FormatedTestBean("978-3-83-621803-21"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<Isbn13FormatedTestBean> getWrongFormatedTestBeans() {
    final List<Isbn13FormatedTestBean> wrongCases = new ArrayList<Isbn13FormatedTestBean>();
    wrongCases.add(new Isbn13FormatedTestBean("978 3-83-621803-2"));
    wrongCases.add(new Isbn13FormatedTestBean("978-3-83-82-507-7"));
    wrongCases.add(new Isbn13FormatedTestBean("978-3-89-964471+6"));
    return wrongCases;
  }
}
