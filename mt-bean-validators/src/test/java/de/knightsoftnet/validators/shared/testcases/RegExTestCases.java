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

import de.knightsoftnet.validators.shared.beans.RegExTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for regular expressions test.
 *
 * @author Manfred Tremmel
 *
 */
public class RegExTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final RegExTestBean getEmptyTestBean() {
    return new RegExTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<RegExTestBean> getCorrectTestBeans() {
    final List<RegExTestBean> correctCases = new ArrayList<RegExTestBean>();
    correctCases.add(new RegExTestBean("^\\$(\\d{1,3}(\\,\\d{3})*|(\\d+))(\\.\\d{2})?$"));
    correctCases.add(new RegExTestBean(
        ".*(\\.[Jj][Pp][Gg]|\\.[Gg][Ii][Ff]|\\.[Jj][Pp][Ee][Gg]|\\.[Pp][Nn][Gg])"));
    correctCases.add(new RegExTestBean("^([a-zA-Z0-9]+(?: [a-zA-Z0-9]+)*)$"));
    correctCases.add(new RegExTestBean("^http[s]?://twitter\\.com/(#!/)?[a-zA-Z0-9]{1,15}[/]?$"));
    correctCases.add(new RegExTestBean("((EE|EL|DE|PT)-?)?[0-9]{9}"));
    correctCases.add(new RegExTestBean("^((\\-|d|l|p|s){1}(\\-|r|w|x){9})$"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<RegExTestBean> getWrongTestBeans() {
    final List<RegExTestBean> wrongCases = new ArrayList<RegExTestBean>();
    wrongCases.add(new RegExTestBean("^\\$(\\d{1,3}(\\,\\d{3})*|(\\d+))(\\.\\d{d2})?$"));
    wrongCases.add(new RegExTestBean(
        ".*\\.[Jj][Pp][Gg]|\\.[Gg][Ii][Ff]|\\.[Jj][Pp][Ee][Gg]|\\.[Pp][Nn][Gg])"));
    wrongCases.add(new RegExTestBean("^([a-zA-Z0-9+(?: [a-zA-Z0-9]+)*)$"));
    wrongCases.add(new RegExTestBean("^http[s]?://twitter\\.com/(#!/)?[a-zA-Z0-9]{1,a5}[/]?$"));
    wrongCases.add(new RegExTestBean("((EE|EL|DE|PT)-?)?[0-9]{9"));
    wrongCases.add(new RegExTestBean("^((\\-|d|l|p|s){1}(\\-|r|w|\\x){9})$"));
    return wrongCases;
  }
}
