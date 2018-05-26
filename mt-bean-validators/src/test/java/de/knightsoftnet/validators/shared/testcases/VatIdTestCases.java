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
    final List<VatIdTestBean> correctCases = new ArrayList<>();
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("AT", null), "ATU13585627"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("AT", null), "ATU12345675"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("AT", null), "ATU45870008"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("AT", null), "ATU46832307"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("AT", null), "ATU41884305"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("AT", null), "ATU66572924"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("AT", null), "ATU66567468"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("AT", null), "ATU63313202"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("AT", null), "ATU61292988"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("AT", null), "ATU43010905"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("AT", null), "ATU64727246"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("AT", null), "ATU22260005"));

    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("BE", null), "BE0136695962"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("BE", null), "BE0869703978"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("BE", null), "BE0222343301"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("BE", null), "BE0896755397"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("BE", null), "BE0349294822"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("BE", null), "BE0419052272"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("BE", null), "BE0850123935"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("BE", null), "BE0261958396"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("BE", null), "BE0408299625"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("BE", null), "BE0896755397"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("BE", null), "BE0429016053"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("BE", null), "BE0403448140"));

    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("DK", null), "DK13585628"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("DK", null), "DK15850698"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("DK", null), "DK34695156"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("DK", null), "DK19133850"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("DK", null), "DK18567709"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("DK", null), "DK77339914"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("DK", null), "DK79162728"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("DK", null), "DK34670781"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("DK", null), "DK12394926"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("DK", null), "DK14745106"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("DK", null), "DK31922224"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("DK", null), "DK30060946"));

    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("DE", null), "DE136695976"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("DE", null), "DE129272852"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("DE", null), "DE281058777"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("DE", null), "DE145779017"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("DE", null), "DE814521375"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("DE", null), "DE129424028"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("DE", null), "DE811115368"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("DE", null), "DE129274202"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("DE", null), "DE115235681"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("DE", null), "DE813501887"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("DE", null), "DE212442423"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("DE", null), "DE209654301"));

    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("FI", null), "FI01132769"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("FI", null), "FI09498247"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("FI", null), "FI10071494"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("FI", null), "FI13669598"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("FI", null), "FI15481994"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("FI", null), "FI20561243"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("FI", null), "FI23894502"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("FI", null), "FI25180829"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("FI", null), "FI25462201"));

    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("FR", null), "FR05502486608"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("FR", null), "FR09308195577"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("FR", null), "FR26400656500"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("FR", null), "FR31753341841"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("FR", null), "FR32340214444"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("FR", null), "FR34349166025"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("FR", null), "FR37507803997"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("FR", null), "FR37955803929"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("FR", null), "FR44000070465"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("FR", null), "FR65799077417"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("FR", null), "FR69529714982"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("FR", null), "FR83404833048"));

    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("GR", null), "EL090012763"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("GR", null), "EL094263543"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("GR", null), "EL094368710"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("GR", null), "EL094468339"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("GR", null), "EL095452597"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("GR", null), "EL099256461"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("GR", null), "EL117749540"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("GR", null), "EL123456783"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("GR", null), "EL800399111"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("GR", null), "EL997276654"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("GR", null), "EL997475274"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("GR", null), "EL997759744"));

    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("HR", null), "HR02573674713"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("HR", null), "HR19819724166"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("HR", null), "HR42992093253"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("HR", null), "HR49903344364"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("HR", null), "HR57480970486"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("HR", null), "HR63625874835"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("HR", null), "HR68383212326"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("HR", null), "HR69644127206"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("HR", null), "HR76023745044"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("HR", null), "HR76169484118"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("HR", null), "HR91559034829"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("HR", null), "HR95024967787"));

    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("IE", null), "IE4569590K"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("IE", null), "IE4600630O"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("IE", null), "IE6402687J"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("IE", null), "IE6589445G"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("IE", null), "IE8278666G"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("IE", null), "IE8279188B"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("IE", null), "IE8473625E"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("IE", null), "IE8G05913L"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("IE", null), "IE9715950S"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("IE", null), "IE9782962G"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("IE", null), "IE9789674N"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("IE", null), "IE9793487N"));

    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("IT", null), "IT00203820998"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("IT", null), "IT00634780985"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("IT", null), "IT00760750216"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("IT", null), "IT00711390211"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("IT", null), "IT01021090319"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("IT", null), "IT01187190218"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("IT", null), "IT01390230462"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("IT", null), "IT01986880027"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("IT", null), "IT02736510211"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("IT", null), "IT03431820285"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("IT", null), "IT11257611001"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("IT", null), "IT12345670785"));

    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("NL", null), "NL123456782B12"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("NL", null), "NL800189620B01"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("NL", null), "NL802327497B01"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("NL", null), "NL804115217B01"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("NL", null), "NL804693304B01"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("NL", null), "NL809892534B01"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("NL", null), "NL810584384B01"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("NL", null), "NL811784915B01"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("NL", null), "NL818058675B01"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("NL", null), "NL818151778B01"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("NL", null), "NL818176738B01"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("NL", null), "NL820646660B01"));

    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("NO", null), "NO912349578"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("NO", null), "NO920473865"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("NO", null), "NO954349667"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("NO", null), "NO983767206"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("NO", null), "NO990003327"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("NO", null), "NO990022321"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("NO", null), "NO991358919"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("NO", null), "NO992350466"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("NO", null), "NO993872636"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("NO", null), "NO994387677"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("NO", null), "NO994888455"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("NO", null), "NO997411579"));

    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("PL", null), "PL5580001325"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("PL", null), "PL5891998450"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("PL", null), "PL5992312572"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("PL", null), "PL6330005110"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("PL", null), "PL6750002236"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("PL", null), "PL6750000444"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("PL", null), "PL7772337068"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("PL", null), "PL8270007105"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("PL", null), "PL8567346215"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("PL", null), "PL8981047033"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("PL", null), "PL9291785452"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("PL", null), "PL9460004760"));

    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("PT", null), "PT136695973"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("PT", null), "PT500049440"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("PT", null), "PT500108137"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("PT", null), "PT500333041"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("PT", null), "PT500666474"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("PT", null), "PT500948470"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("PT", null), "PT500990441"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("PT", null), "PT501216901"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("PT", null), "PT504167600"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("PT", null), "PT505324539"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("PT", null), "PT510685722"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("PT", null), "PT511144121"));

    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("SE", null), "SE212000130601"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("SE", null), "SE556221562301"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("SE", null), "SE556310948601"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("SE", null), "SE556331636201"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("SE", null), "SE556435614401"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("SE", null), "SE556553874001"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("SE", null), "SE556672075001"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("SE", null), "SE556694972201"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("SE", null), "SE556749956001"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("SE", null), "SE556789211101"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("SE", null), "SE556866042601"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("SE", null), "SE556983570401"));

    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("SI", null), "SI59082437"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("SI", null), "SI13345486"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("SI", null), "SI77172876"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("SI", null), "SI13859455"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("SI", null), "SI56012390"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("SI", null), "SI56116802"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("SI", null), "SI25296418"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("SI", null), "SI92859976"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("SI", null), "SI18190995"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("SI", null), "SI62634186"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("SI", null), "SI54045312"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("SI", null), "SI60595256"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("SI", null), "SI34384243"));

    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("ES", null), "ESA13585625"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("ES", null), "ESN0040274C"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("ES", null), "ESB40192700"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("ES", null), "ESB57406787"));
    correctCases.add(new VatIdTestBean(new PostalCodeTestBean("ES", null), "ESJ55118814"));

    return correctCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<VatIdTestBean> getWrongChecksumTestBeans() {
    final List<VatIdTestBean> wrongCases = new ArrayList<>();
    wrongCases.add(new VatIdTestBean(new PostalCodeTestBean("AT", null), "ATU13586527"));
    wrongCases.add(new VatIdTestBean(new PostalCodeTestBean("BE", null), "BE0136695963"));
    wrongCases.add(new VatIdTestBean(new PostalCodeTestBean("DK", null), "DK13585627"));
    wrongCases.add(new VatIdTestBean(new PostalCodeTestBean("DE", null), "DE136659976"));
    wrongCases.add(new VatIdTestBean(new PostalCodeTestBean("FI", null), "FI13669589"));
    wrongCases.add(new VatIdTestBean(new PostalCodeTestBean("GR", null), "EL123456784"));
    wrongCases.add(new VatIdTestBean(new PostalCodeTestBean("IE", null), "IE8463625E"));
    wrongCases.add(new VatIdTestBean(new PostalCodeTestBean("IT", null), "IT12354670785"));
    wrongCases.add(new VatIdTestBean(new PostalCodeTestBean("NL", null), "NL123456783B12"));
    wrongCases.add(new VatIdTestBean(new PostalCodeTestBean("PL", null), "PL8567436215"));
    wrongCases.add(new VatIdTestBean(new PostalCodeTestBean("PT", null), "PT136695974"));
    wrongCases.add(new VatIdTestBean(new PostalCodeTestBean("SE", null), "SE136699575523"));
    wrongCases.add(new VatIdTestBean(new PostalCodeTestBean("SI", null), "SI59082436"));
    // wrongCases.add(new VatIdTestBean(new PostalCodeTestBean("ES", null), "ESA13558625"));
    return wrongCases;
  }

  /**
   * get wrong test beans.
   *
   * @return wrong test beans
   */
  public static final List<VatIdTestBean> getWrongCountryTestBeans() {
    final List<VatIdTestBean> wrongCases = new ArrayList<>();
    wrongCases.add(new VatIdTestBean(new PostalCodeTestBean("ES", null), "ATU13585627"));
    wrongCases.add(new VatIdTestBean(new PostalCodeTestBean("AT", null), "BE136695962"));
    wrongCases.add(new VatIdTestBean(new PostalCodeTestBean("BE", null), "DK13585628"));
    wrongCases.add(new VatIdTestBean(new PostalCodeTestBean("DK", null), "DE136695976"));
    wrongCases.add(new VatIdTestBean(new PostalCodeTestBean("DE", null), "FI13669598"));
    wrongCases.add(new VatIdTestBean(new PostalCodeTestBean("FI", null), "EL123456783"));
    wrongCases.add(new VatIdTestBean(new PostalCodeTestBean("GR", null), "IE8473625E"));
    wrongCases.add(new VatIdTestBean(new PostalCodeTestBean("IE", null), "IT12345670785"));
    wrongCases.add(new VatIdTestBean(new PostalCodeTestBean("IT", null), "NL123456782B12"));
    wrongCases.add(new VatIdTestBean(new PostalCodeTestBean("NL", null), "PL8567346215"));
    wrongCases.add(new VatIdTestBean(new PostalCodeTestBean("PL", null), "PT136695973"));
    wrongCases.add(new VatIdTestBean(new PostalCodeTestBean("PT", null), "SE136695975523"));
    wrongCases.add(new VatIdTestBean(new PostalCodeTestBean("SE", null), "SI59082437"));
    wrongCases.add(new VatIdTestBean(new PostalCodeTestBean("SI", null), "ESA13585625"));
    return wrongCases;
  }
}
