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

import de.knightsoftnet.validators.shared.beans.BankCountryTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for bank country test.
 *
 * @author Manfred Tremmel
 *
 */
public class BankCountryTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final BankCountryTestBean getEmptyTestBean() {
    return new BankCountryTestBean(null, null, null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<BankCountryTestBean> getCorrectTestBeans() {
    final List<BankCountryTestBean> correctCases = new ArrayList<BankCountryTestBean>();
    correctCases.add(new BankCountryTestBean("DE", "DE16701600000000555444", "GENODEFF701"));
    correctCases.add(new BankCountryTestBean("DE", "DE49430609670000033401", "GENODEM1GLS"));
    correctCases.add(new BankCountryTestBean("AT", "AT242011182221219800", "GIBAATWWXXX"));
    correctCases.add(new BankCountryTestBean("CH", "CH1609000000877768766", "POFICHBEXXX"));
    correctCases.add(new BankCountryTestBean("IT", "IT73O0501803200000000125125", "CCRTIT21"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<BankCountryTestBean> getWrongCountryTestBeans() {
    final List<BankCountryTestBean> wrongCases = new ArrayList<BankCountryTestBean>();
    wrongCases.add(new BankCountryTestBean("AT", "DE16701600000000555444", "GENODEFF701"));
    wrongCases.add(new BankCountryTestBean("CH", "DE49430609670000033401", "GENODEM1GLS"));
    wrongCases.add(new BankCountryTestBean("IT", "AT242011182221219800", "GIBAATWWXXX"));
    wrongCases.add(new BankCountryTestBean("DE", "CH1609000000877768766", "POFICHBEXXX"));
    wrongCases.add(new BankCountryTestBean("DE", "IT73O0501803200000000125125", "CCRTIT21"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<BankCountryTestBean> getWrongIbanCountryTestBeans() {
    final List<BankCountryTestBean> wrongCases = new ArrayList<BankCountryTestBean>();
    wrongCases.add(new BankCountryTestBean("DE", "AT242011182221219800", "GENODEFF701"));
    wrongCases.add(new BankCountryTestBean("DE", "CH1609000000877768766", "GENODEM1GLS"));
    wrongCases.add(new BankCountryTestBean("AT", "IT73O0501803200000000125125", "GIBAATWWXXX"));
    wrongCases.add(new BankCountryTestBean("CH", "DE16701600000000555444", "POFICHBEXXX"));
    wrongCases.add(new BankCountryTestBean("IT", "DE49430609670000033401", "CCRTIT21"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<BankCountryTestBean> getWrongBicCountryTestBeans() {
    final List<BankCountryTestBean> wrongCases = new ArrayList<BankCountryTestBean>();
    wrongCases.add(new BankCountryTestBean("DE", "DE16701600000000555444", "GIBAATWWXXX"));
    wrongCases.add(new BankCountryTestBean("DE", "DE49430609670000033401", "POFICHBEXXX"));
    wrongCases.add(new BankCountryTestBean("AT", "AT242011182221219800", "CCRTIT21"));
    wrongCases.add(new BankCountryTestBean("CH", "CH1609000000877768766", "GENODEFF701"));
    wrongCases.add(new BankCountryTestBean("IT", "IT73O0501803200000000125125", "GENODEM1GLS"));
    return wrongCases;
  }
}
