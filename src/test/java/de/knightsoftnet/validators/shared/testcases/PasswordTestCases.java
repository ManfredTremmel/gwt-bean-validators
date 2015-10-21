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

import de.knightsoftnet.validators.shared.beans.PasswordTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for password test.
 *
 * @author Manfred Tremmel
 *
 */
public class PasswordTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final PasswordTestBean getEmptyTestBean() {
    return new PasswordTestBean(null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<PasswordTestBean> getCorrectTestBeans() {
    final List<PasswordTestBean> correctCases = new ArrayList<PasswordTestBean>();
    correctCases.add(new PasswordTestBean("Test123"));
    correctCases.add(new PasswordTestBean("Password!"));
    correctCases.add(new PasswordTestBean("1password%"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<PasswordTestBean> getWrongTestBeans() {
    final List<PasswordTestBean> wrongCases = new ArrayList<PasswordTestBean>();
    wrongCases.add(new PasswordTestBean("test"));
    wrongCases.add(new PasswordTestBean("test123"));
    wrongCases.add(new PasswordTestBean("müller"));
    return wrongCases;
  }

  /**
   * get blacklisted test beans.
   *
   * @return blacklisted test beans
   */
  public static final List<PasswordTestBean> getBlackListedTestBeans() {
    final List<PasswordTestBean> wrongCases = new ArrayList<PasswordTestBean>();
    wrongCases.add(new PasswordTestBean("Secret!"));
    wrongCases.add(new PasswordTestBean("seCrEt123"));
    wrongCases.add(new PasswordTestBean("123Geheim"));
    return wrongCases;
  }

  /**
   * get test beans with wrong start character.
   *
   * @return wrong start character test beans
   */
  public static final List<PasswordTestBean> getWrongStartCharTestBeans() {
    final List<PasswordTestBean> wrongCases = new ArrayList<PasswordTestBean>();
    wrongCases.add(new PasswordTestBean("!Test123"));
    wrongCases.add(new PasswordTestBean("?Test123"));
    wrongCases.add(new PasswordTestBean("!Password!"));
    return wrongCases;
  }

  /**
   * get test beans with exceeded repeats of a character.
   *
   * @return exceeded repeat character test beans
   */
  public static final List<PasswordTestBean> getExceededRepeatTestBeans() {
    final List<PasswordTestBean> wrongCases = new ArrayList<PasswordTestBean>();
    wrongCases.add(new PasswordTestBean("Aaaa123!"));
    wrongCases.add(new PasswordTestBean("Huiboooooh!"));
    wrongCases.add(new PasswordTestBean("Bäääääähhhh1234"));
    return wrongCases;
  }
}
