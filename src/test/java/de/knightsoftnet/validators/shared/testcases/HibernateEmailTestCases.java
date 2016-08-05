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

import de.knightsoftnet.validators.shared.beans.HibernateEmailTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for hibernate email test.
 *
 * @author Manfred Tremmel
 *
 */
public class HibernateEmailTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final HibernateEmailTestBean getEmptyTestBean() {
    return new HibernateEmailTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<HibernateEmailTestBean> getCorrectTestBeans() {
    final List<HibernateEmailTestBean> correctCases = new ArrayList<HibernateEmailTestBean>();
    correctCases.add(new HibernateEmailTestBean("emmanuel@hibernate.org"));
    correctCases.add(new HibernateEmailTestBean("emmanuel@hibernate"));
    correctCases.add(new HibernateEmailTestBean("emma-n_uel@hibernate"));
    correctCases.add(new HibernateEmailTestBean("emma+nuel@hibernate.org"));
    correctCases.add(new HibernateEmailTestBean("emma=nuel@hibernate.org"));
    correctCases.add(new HibernateEmailTestBean("emmanuel@[123.12.2.11]"));
    correctCases.add(new HibernateEmailTestBean("*@example.net"));
    correctCases.add(new HibernateEmailTestBean("fred&barny@example.com"));
    correctCases.add(new HibernateEmailTestBean("---@example.com"));
    correctCases.add(new HibernateEmailTestBean("foo-bar@example.net"));
    correctCases.add(new HibernateEmailTestBean("mailbox.sub1.sub2@this-domain"));

    correctCases.add(new HibernateEmailTestBean("Test^Email@example.com"));

    correctCases.add(new HibernateEmailTestBean("myname@östereich.at"));
    correctCases.add(new HibernateEmailTestBean("θσερ@εχαμπλε.ψομ"));

    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<HibernateEmailTestBean> getWrongTestBeans() {
    final List<HibernateEmailTestBean> wrongCases = new ArrayList<HibernateEmailTestBean>();
    wrongCases.add(new HibernateEmailTestBean("emmanuel.hibernate.org"));
    wrongCases.add(new HibernateEmailTestBean("emma nuel@hibernate.org"));
    wrongCases.add(new HibernateEmailTestBean("emma(nuel@hibernate.org"));
    wrongCases.add(new HibernateEmailTestBean("emmanuel@"));
    wrongCases.add(new HibernateEmailTestBean("emma\nnuel@hibernate.org"));
    wrongCases.add(new HibernateEmailTestBean("emma@nuel@hibernate.org"));
    wrongCases.add(new HibernateEmailTestBean("Just a string"));
    wrongCases.add(new HibernateEmailTestBean("string"));
    wrongCases.add(new HibernateEmailTestBean("me@"));
    wrongCases.add(new HibernateEmailTestBean("@example.com"));
    wrongCases.add(new HibernateEmailTestBean("me.@example.com"));
    wrongCases.add(new HibernateEmailTestBean(".me@example.com"));
    wrongCases.add(new HibernateEmailTestBean("me@example..com"));
    wrongCases.add(new HibernateEmailTestBean("me\\@example.com"));

    wrongCases.add(new HibernateEmailTestBean("θσερ.εχαμπλε.ψομ"));

    wrongCases.add(new HibernateEmailTestBean("validation@hibernate.com@"));
    wrongCases.add(new HibernateEmailTestBean("validation@hibernate.com@@"));
    wrongCases.add(new HibernateEmailTestBean("validation@hibernate.com@@@"));

    return wrongCases;
  }
}
