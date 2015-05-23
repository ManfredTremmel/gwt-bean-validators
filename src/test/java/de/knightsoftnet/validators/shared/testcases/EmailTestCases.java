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

import de.knightsoftnet.validators.shared.beans.EmailTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for email test.
 *
 * @author Manfred Tremmel
 *
 */
public class EmailTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final EmailTestBean getEmptyTestBean() {
    return new EmailTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<EmailTestBean> getCorrectTestBeans() {
    final List<EmailTestBean> correctCases = new ArrayList<EmailTestBean>();
    correctCases.add(new EmailTestBean("jsmith@apache.org"));
    correctCases.add(new EmailTestBean("jsmith@apache.com"));
    correctCases.add(new EmailTestBean("jsmith@apache.net"));
    correctCases.add(new EmailTestBean("jsmith@apache.info"));
    correctCases.add(new EmailTestBean("someone@yahoo.museum"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<EmailTestBean> getWrongTestBeans() {
    final List<EmailTestBean> wrongCases = new ArrayList<EmailTestBean>();
    wrongCases.add(new EmailTestBean("jsmith@apache."));
    wrongCases.add(new EmailTestBean("jsmith@apache.c"));
    wrongCases.add(new EmailTestBean("someone@yahoo.mu-seum"));
    return wrongCases;
  }
}
