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

import de.knightsoftnet.validators.shared.beans.HibernateRegonTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for Regon test.
 *
 * @author Manfred Tremmel
 *
 */
public class HibernateRegonTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final HibernateRegonTestBean getEmptyTestBean() {
    return new HibernateRegonTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<HibernateRegonTestBean> getCorrectTestBeans() {
    final List<HibernateRegonTestBean> correctCases = new ArrayList<>();
    correctCases.add(new HibernateRegonTestBean("123456785"));
    correctCases.add(new HibernateRegonTestBean("691657182"));
    correctCases.add(new HibernateRegonTestBean("180204898"));
    correctCases.add(new HibernateRegonTestBean("180000960"));
    correctCases.add(new HibernateRegonTestBean("180159761"));
    correctCases.add(new HibernateRegonTestBean("180175352"));
    correctCases.add(new HibernateRegonTestBean("180204898"));
    correctCases.add(new HibernateRegonTestBean("558505989"));
    correctCases.add(new HibernateRegonTestBean("858336997"));
    correctCases.add(new HibernateRegonTestBean("737024234"));
    correctCases.add(new HibernateRegonTestBean("074635672"));
    correctCases.add(new HibernateRegonTestBean("593908869"));
    correctCases.add(new HibernateRegonTestBean("12345678512347"));
    correctCases.add(new HibernateRegonTestBean("59418566359965"));
    correctCases.add(new HibernateRegonTestBean("65485163947915"));
    correctCases.add(new HibernateRegonTestBean("89385161104781"));
    correctCases.add(new HibernateRegonTestBean("95697475666436"));
    correctCases.add(new HibernateRegonTestBean("57435387084379"));
    correctCases.add(new HibernateRegonTestBean("39289346827756"));
    correctCases.add(new HibernateRegonTestBean("35543437342533"));
    correctCases.add(new HibernateRegonTestBean("45257314860534"));
    correctCases.add(new HibernateRegonTestBean("49905531368510"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernateRegonTestBean> getWrongTestBeans() {
    final List<HibernateRegonTestBean> wrongCases = new ArrayList<>();
    wrongCases.add(new HibernateRegonTestBean("123456784"));
    wrongCases.add(new HibernateRegonTestBean("691657185"));
    wrongCases.add(new HibernateRegonTestBean("180204896"));
    wrongCases.add(new HibernateRegonTestBean("180000967"));
    wrongCases.add(new HibernateRegonTestBean("180159768"));
    wrongCases.add(new HibernateRegonTestBean("180175359"));
    wrongCases.add(new HibernateRegonTestBean("180204891"));
    wrongCases.add(new HibernateRegonTestBean("558505982"));
    wrongCases.add(new HibernateRegonTestBean("858336993"));
    wrongCases.add(new HibernateRegonTestBean("737024237"));
    wrongCases.add(new HibernateRegonTestBean("074635675"));
    wrongCases.add(new HibernateRegonTestBean("593908866"));
    wrongCases.add(new HibernateRegonTestBean("12345678512341"));
    wrongCases.add(new HibernateRegonTestBean("59418566359962"));
    wrongCases.add(new HibernateRegonTestBean("65485163947913"));
    wrongCases.add(new HibernateRegonTestBean("89385161104784"));
    wrongCases.add(new HibernateRegonTestBean("95697475666435"));
    wrongCases.add(new HibernateRegonTestBean("57435387084376"));
    wrongCases.add(new HibernateRegonTestBean("39289346827757"));
    wrongCases.add(new HibernateRegonTestBean("35543437342538"));
    wrongCases.add(new HibernateRegonTestBean("45257314860539"));
    wrongCases.add(new HibernateRegonTestBean("49905531368512"));
    return wrongCases;
  }
}
