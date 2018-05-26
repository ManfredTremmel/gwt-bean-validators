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

import de.knightsoftnet.validators.shared.beans.PostalCodeTestBean;
import de.knightsoftnet.validators.shared.beans.TinTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * get test cases for tin test.
 *
 * @author Manfred Tremmel
 *
 */
public class TinTestCases {
  /**
   * get empty test bean.
   *
   * @return empty test bean
   */
  public static final TinTestBean getEmptyTestBean() {
    return new TinTestBean(null, null);
  }

  /**
   * get correct test beans.
   *
   * @return correct test beans
   */
  public static final List<TinTestBean> getCorrectTestBeans() {
    final List<TinTestBean> correctCases = new ArrayList<>();

    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "901234567"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "039999032"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "049999048"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "069999068"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "079999074"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "089999080"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "099999096"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "109999102"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "129999124"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "159999150"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "169999166"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "189999188"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "229999222"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "239999238"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "299999292"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "339999336"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "389999384"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "419999412"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "469999460"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "519999510"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "529999526"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "539999532"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "549999548"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "579999574"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "599999596"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "619999618"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "659999650"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "679999672"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "689999688"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "699999694"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "719999716"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "729999722"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "819999814"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "829999820"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "839999836"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "849999842"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "909999906"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "919999912"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "939999934"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "979999976"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "989999982"));

    correctCases.add(new TinTestBean(new PostalCodeTestBean("DE", null), "81872495633"));

    correctCases.add(new TinTestBean(new PostalCodeTestBean("DK", null), "1111111118"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("DK", null), "2110625629"));

    correctCases.add(new TinTestBean(new PostalCodeTestBean("EE", null), "21107190012"));

    correctCases.add(new TinTestBean(new PostalCodeTestBean("ES", null), "12345678Z"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("ES", null), "A58818501"));

    correctCases.add(new TinTestBean(new PostalCodeTestBean("FI", null), "010150-521X"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("FI", null), "211271-246U"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("FI", null), "100185-0000"));

    correctCases.add(new TinTestBean(new PostalCodeTestBean("FR", null), "1234567890123"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("FR", null), "1230987654123"));

    correctCases.add(new TinTestBean(new PostalCodeTestBean("HR", null), "94577403194"));

    correctCases.add(new TinTestBean(new PostalCodeTestBean("LT", null), "33309240064"));

    correctCases.add(new TinTestBean(new PostalCodeTestBean("LU", null), "1893120105732"));

    correctCases.add(new TinTestBean(new PostalCodeTestBean("NL", null), "999999990"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("NL", null), "123456782"));

    correctCases.add(new TinTestBean(new PostalCodeTestBean("PL", null), "44051401359"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("PL", null), "26083006995"));
    correctCases.add(new TinTestBean(new PostalCodeTestBean("PL", null), "1234563218"));

    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<TinTestBean> getWrongChecksumTestBeans() {
    final List<TinTestBean> wrongCases = new ArrayList<>();

    wrongCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "909999907"));

    wrongCases.add(new TinTestBean(new PostalCodeTestBean("DE", null), "81872495634"));

    wrongCases.add(new TinTestBean(new PostalCodeTestBean("DK", null), "1111111117"));

    wrongCases.add(new TinTestBean(new PostalCodeTestBean("EE", null), "21107190013"));

    wrongCases.add(new TinTestBean(new PostalCodeTestBean("ES", null), "12345678X"));

    wrongCases.add(new TinTestBean(new PostalCodeTestBean("HR", null), "94577043194"));

    wrongCases.add(new TinTestBean(new PostalCodeTestBean("LT", null), "33309240065"));

    wrongCases.add(new TinTestBean(new PostalCodeTestBean("NL", null), "123456783"));

    wrongCases.add(new TinTestBean(new PostalCodeTestBean("PL", null), "44051401358"));
    wrongCases.add(new TinTestBean(new PostalCodeTestBean("PL", null), "1234563210"));

    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<TinTestBean> getWrongCountryTestBeans() {
    final List<TinTestBean> wrongCases = new ArrayList<>();

    wrongCases.add(new TinTestBean(new PostalCodeTestBean("AT", null), "81872495634"));

    wrongCases.add(new TinTestBean(new PostalCodeTestBean("DE", null), "909999906"));

    return wrongCases;
  }
}
