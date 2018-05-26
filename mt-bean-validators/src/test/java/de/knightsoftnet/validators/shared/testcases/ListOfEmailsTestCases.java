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
import de.knightsoftnet.validators.shared.beans.ListOfEmailsTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for a list of email test.
 *
 * @author Manfred Tremmel
 *
 */
public class ListOfEmailsTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final ListOfEmailsTestBean getEmptyTestBean() {
    return new ListOfEmailsTestBean();
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<ListOfEmailsTestBean> getCorrectTestBeans() {
    final List<ListOfEmailsTestBean> correctCases = new ArrayList<>();
    final ListOfEmailsTestBean entry = new ListOfEmailsTestBean();
    entry.getEmailList().add(new EmailTestBean("jsmith@apache.org"));
    entry.getEmailList().add(new EmailTestBean("jsmith@apache.com"));
    entry.getEmailList().add(new EmailTestBean("jsmith@apache.net"));
    entry.getEmailList().add(new EmailTestBean("jsmith@apache.info"));
    entry.getEmailList().add(new EmailTestBean("someone@yahoo.museum"));
    correctCases.add(entry);
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<ListOfEmailsTestBean> getWrongTestBeans() {
    final List<ListOfEmailsTestBean> wrongCases = new ArrayList<>();

    // second one is wrong
    final ListOfEmailsTestBean entry1 = new ListOfEmailsTestBean();
    entry1.getEmailList().add(new EmailTestBean("jsmith@apache.org"));
    entry1.getEmailList().add(new EmailTestBean(" jsmith@apache.com"));
    entry1.getEmailList().add(new EmailTestBean("jsmith@apache.net"));
    entry1.getEmailList().add(new EmailTestBean("jsmith@apache.info"));
    entry1.getEmailList().add(new EmailTestBean("someone@yahoo.museum"));
    entry1.setPath("emailList[1].email");
    wrongCases.add(entry1);

    // third one is wrong
    final ListOfEmailsTestBean entry2 = new ListOfEmailsTestBean();
    entry2.getEmailList().add(new EmailTestBean("jsmith@apache.org"));
    entry2.getEmailList().add(new EmailTestBean("jsmith@apache.com"));
    entry2.getEmailList().add(new EmailTestBean("jsmith@apache."));
    entry2.getEmailList().add(new EmailTestBean("jsmith@apache.info"));
    entry2.getEmailList().add(new EmailTestBean("someone@yahoo.museum"));
    entry2.setPath("emailList[2].email");
    wrongCases.add(entry2);

    return wrongCases;
  }
}
