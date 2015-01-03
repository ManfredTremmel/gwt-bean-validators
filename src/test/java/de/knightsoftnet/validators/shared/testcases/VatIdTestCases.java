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

import de.knightsoftnet.validators.shared.beans.VatIdTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for vat id test.
 *
 * @author Manfred Tremmel
 *
 */
public class VatIdTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final VatIdTestBean getEmptyTestBean() {
    return new VatIdTestBean(null, null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<VatIdTestBean> getCorrectTestBeans() {
    final List<VatIdTestBean> correctCases = new ArrayList<VatIdTestBean>();
    correctCases.add(new VatIdTestBean("AT", "ATU13585627"));
    correctCases.add(new VatIdTestBean("BE", "BE0136695962"));
    correctCases.add(new VatIdTestBean("DK", "DK13585628"));
    correctCases.add(new VatIdTestBean("DE", "DE136695976"));
    correctCases.add(new VatIdTestBean("FI", "FI13669598"));
    correctCases.add(new VatIdTestBean("GR", "EL123456783"));
    correctCases.add(new VatIdTestBean("IE", "IE8473625E"));
    correctCases.add(new VatIdTestBean("IT", "IT12345670785"));
    correctCases.add(new VatIdTestBean("NL", "NL123456782B12"));
    correctCases.add(new VatIdTestBean("PL", "PL8567346215"));
    correctCases.add(new VatIdTestBean("PT", "PT136695973"));
    correctCases.add(new VatIdTestBean("SE", "SE136695975523"));
    correctCases.add(new VatIdTestBean("SI", "SI59082437"));
    correctCases.add(new VatIdTestBean("ES", "ESA13585625"));
    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<VatIdTestBean> getWrongChecksumTestBeans() {
    final List<VatIdTestBean> wrongCases = new ArrayList<VatIdTestBean>();
    wrongCases.add(new VatIdTestBean("AT", "ATU13586527"));
    wrongCases.add(new VatIdTestBean("BE", "BE0136695963"));
    wrongCases.add(new VatIdTestBean("DK", "DK13585627"));
    wrongCases.add(new VatIdTestBean("DE", "DE136659976"));
    wrongCases.add(new VatIdTestBean("FI", "FI13669589"));
    wrongCases.add(new VatIdTestBean("GR", "EL123456784"));
    wrongCases.add(new VatIdTestBean("IE", "IE8463625E"));
    wrongCases.add(new VatIdTestBean("IT", "IT12354670785"));
    wrongCases.add(new VatIdTestBean("NL", "NL123456783B12"));
    wrongCases.add(new VatIdTestBean("PL", "PL8567436215"));
    wrongCases.add(new VatIdTestBean("PT", "PT136695974"));
    wrongCases.add(new VatIdTestBean("SE", "SE136699575523"));
    wrongCases.add(new VatIdTestBean("SI", "SI59082436"));
    wrongCases.add(new VatIdTestBean("ES", "ESA13558625"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<VatIdTestBean> getWrongCountryTestBeans() {
    final List<VatIdTestBean> wrongCases = new ArrayList<VatIdTestBean>();
    wrongCases.add(new VatIdTestBean("ES", "ATU13585627"));
    wrongCases.add(new VatIdTestBean("AT", "BE136695962"));
    wrongCases.add(new VatIdTestBean("BE", "DK13585628"));
    wrongCases.add(new VatIdTestBean("DK", "DE136695976"));
    wrongCases.add(new VatIdTestBean("DE", "FI13669598"));
    wrongCases.add(new VatIdTestBean("FI", "EL123456783"));
    wrongCases.add(new VatIdTestBean("GR", "IE8473625E"));
    wrongCases.add(new VatIdTestBean("IE", "IT12345670785"));
    wrongCases.add(new VatIdTestBean("IT", "NL123456782B12"));
    wrongCases.add(new VatIdTestBean("NL", "PL8567346215"));
    wrongCases.add(new VatIdTestBean("PL", "PT136695973"));
    wrongCases.add(new VatIdTestBean("PT", "SE136695975523"));
    wrongCases.add(new VatIdTestBean("SE", "SI59082437"));
    wrongCases.add(new VatIdTestBean("SI", "ESA13585625"));
    return wrongCases;
  }
}
