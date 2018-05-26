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

import de.knightsoftnet.validators.shared.beans.IsbnFormatedTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for isbn formated test.
 *
 * @author Manfred Tremmel
 *
 */
public class IsbnFormatedTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final IsbnFormatedTestBean getEmptyTestBean() {
    return new IsbnFormatedTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<IsbnFormatedTestBean> getCorrectTestBeans() {
    final List<IsbnFormatedTestBean> correctCases = new ArrayList<IsbnFormatedTestBean>();
    correctCases.add(new IsbnFormatedTestBean("978-3-83-621802-3"));
    correctCases.add(new IsbnFormatedTestBean("978-3-83-621507-7"));
    correctCases.add(new IsbnFormatedTestBean("978-3-89-864471-6"));

    correctCases.add(new IsbnFormatedTestBean("3-80-770171-0"));
    correctCases.add(new IsbnFormatedTestBean("3-80-770205-9"));
    correctCases.add(new IsbnFormatedTestBean("3-80-770192-3"));
    correctCases.add(new IsbnFormatedTestBean("3-86-640001-2"));
    correctCases.add(new IsbnFormatedTestBean("3-93-751412-0"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<IsbnFormatedTestBean> getWrongTestBeans() {
    final List<IsbnFormatedTestBean> wrongCases = new ArrayList<IsbnFormatedTestBean>();
    wrongCases.add(new IsbnFormatedTestBean("978-3-83-621803-2"));
    wrongCases.add(new IsbnFormatedTestBean("978-3-83-821507-7"));
    wrongCases.add(new IsbnFormatedTestBean("978-3-89-964471-6"));

    wrongCases.add(new IsbnFormatedTestBean("3-80-770170-0"));
    wrongCases.add(new IsbnFormatedTestBean("3-80-770205-8"));
    wrongCases.add(new IsbnFormatedTestBean("3-80-770193-2"));
    wrongCases.add(new IsbnFormatedTestBean("3-86-640201-2"));
    wrongCases.add(new IsbnFormatedTestBean("3-93-571412-0"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<IsbnFormatedTestBean> getWrongSizeTestBeans() {
    final List<IsbnFormatedTestBean> wrongCases = new ArrayList<IsbnFormatedTestBean>();
    wrongCases.add(new IsbnFormatedTestBean("3-80-770205"));
    wrongCases.add(new IsbnFormatedTestBean("3-80-770192-32"));
    wrongCases.add(new IsbnFormatedTestBean("3-93-75141"));
    wrongCases.add(new IsbnFormatedTestBean("978-3-83-621803"));
    wrongCases.add(new IsbnFormatedTestBean("978-3-89-964471-65"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<IsbnFormatedTestBean> getWrongFormatedTestBeans() {
    final List<IsbnFormatedTestBean> wrongCases = new ArrayList<IsbnFormatedTestBean>();
    wrongCases.add(new IsbnFormatedTestBean("978 3-83-621803-2"));
    wrongCases.add(new IsbnFormatedTestBean("978-3-83-82-507-7"));
    wrongCases.add(new IsbnFormatedTestBean("978-3-89-964471+6"));
    wrongCases.add(new IsbnFormatedTestBean("3 86-640201-2"));
    wrongCases.add(new IsbnFormatedTestBean("3+93-571412-0"));
    wrongCases.add(new IsbnFormatedTestBean("3-80/770193-2"));
    wrongCases.add(new IsbnFormatedTestBean("3-866-40201-2"));
    wrongCases.add(new IsbnFormatedTestBean("3-93-571-4120"));
    return wrongCases;
  }
}
