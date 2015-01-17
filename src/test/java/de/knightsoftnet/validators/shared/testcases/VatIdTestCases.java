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
    correctCases.add(new VatIdTestBean("AT", "ATU12345675"));
    correctCases.add(new VatIdTestBean("AT", "ATU45870008"));
    correctCases.add(new VatIdTestBean("AT", "ATU46832307"));
    correctCases.add(new VatIdTestBean("AT", "ATU41884305"));
    correctCases.add(new VatIdTestBean("AT", "ATU66572924"));
    correctCases.add(new VatIdTestBean("AT", "ATU66567468"));
    correctCases.add(new VatIdTestBean("AT", "ATU63313202"));
    correctCases.add(new VatIdTestBean("AT", "ATU61292988"));
    correctCases.add(new VatIdTestBean("AT", "ATU43010905"));
    correctCases.add(new VatIdTestBean("AT", "ATU64727246"));
    correctCases.add(new VatIdTestBean("AT", "ATU22260005"));

    correctCases.add(new VatIdTestBean("BE", "BE0136695962"));
    correctCases.add(new VatIdTestBean("BE", "BE0869703978"));
    correctCases.add(new VatIdTestBean("BE", "BE0222343301"));
    correctCases.add(new VatIdTestBean("BE", "BE0896755397"));
    correctCases.add(new VatIdTestBean("BE", "BE0349294822"));
    correctCases.add(new VatIdTestBean("BE", "BE0419052272"));
    correctCases.add(new VatIdTestBean("BE", "BE0850123935"));
    correctCases.add(new VatIdTestBean("BE", "BE0261958396"));
    correctCases.add(new VatIdTestBean("BE", "BE0408299625"));
    correctCases.add(new VatIdTestBean("BE", "BE0896755397"));
    correctCases.add(new VatIdTestBean("BE", "BE0429016053"));
    correctCases.add(new VatIdTestBean("BE", "BE0403448140"));

    correctCases.add(new VatIdTestBean("DK", "DK13585628"));
    correctCases.add(new VatIdTestBean("DK", "DK15850698"));
    correctCases.add(new VatIdTestBean("DK", "DK34695156"));
    correctCases.add(new VatIdTestBean("DK", "DK19133850"));
    correctCases.add(new VatIdTestBean("DK", "DK18567709"));
    correctCases.add(new VatIdTestBean("DK", "DK77339914"));
    correctCases.add(new VatIdTestBean("DK", "DK79162728"));
    correctCases.add(new VatIdTestBean("DK", "DK34670781"));
    correctCases.add(new VatIdTestBean("DK", "DK12394926"));
    correctCases.add(new VatIdTestBean("DK", "DK14745106"));
    correctCases.add(new VatIdTestBean("DK", "DK31922224"));
    correctCases.add(new VatIdTestBean("DK", "DK30060946"));

    correctCases.add(new VatIdTestBean("DE", "DE136695976"));
    correctCases.add(new VatIdTestBean("DE", "DE129272852"));
    correctCases.add(new VatIdTestBean("DE", "DE281058777"));
    correctCases.add(new VatIdTestBean("DE", "DE145779017"));
    correctCases.add(new VatIdTestBean("DE", "DE814521375"));
    correctCases.add(new VatIdTestBean("DE", "DE129424028"));
    correctCases.add(new VatIdTestBean("DE", "DE811115368"));
    correctCases.add(new VatIdTestBean("DE", "DE129274202"));
    correctCases.add(new VatIdTestBean("DE", "DE115235681"));
    correctCases.add(new VatIdTestBean("DE", "DE813501887"));
    correctCases.add(new VatIdTestBean("DE", "DE212442423"));
    correctCases.add(new VatIdTestBean("DE", "DE209654301"));

    correctCases.add(new VatIdTestBean("FI", "FI01132769"));
    correctCases.add(new VatIdTestBean("FI", "FI09498247"));
    correctCases.add(new VatIdTestBean("FI", "FI10071494"));
    correctCases.add(new VatIdTestBean("FI", "FI13669598"));
    correctCases.add(new VatIdTestBean("FI", "FI15481994"));
    correctCases.add(new VatIdTestBean("FI", "FI20561243"));
    correctCases.add(new VatIdTestBean("FI", "FI23894502"));
    correctCases.add(new VatIdTestBean("FI", "FI25180829"));
    correctCases.add(new VatIdTestBean("FI", "FI25462201"));

    correctCases.add(new VatIdTestBean("FR", "FR05502486608"));
    correctCases.add(new VatIdTestBean("FR", "FR09308195577"));
    correctCases.add(new VatIdTestBean("FR", "FR26400656500"));
    correctCases.add(new VatIdTestBean("FR", "FR31753341841"));
    correctCases.add(new VatIdTestBean("FR", "FR32340214444"));
    correctCases.add(new VatIdTestBean("FR", "FR34349166025"));
    correctCases.add(new VatIdTestBean("FR", "FR37507803997"));
    correctCases.add(new VatIdTestBean("FR", "FR37955803929"));
    correctCases.add(new VatIdTestBean("FR", "FR44000070465"));
    correctCases.add(new VatIdTestBean("FR", "FR65799077417"));
    correctCases.add(new VatIdTestBean("FR", "FR69529714982"));
    correctCases.add(new VatIdTestBean("FR", "FR83404833048"));

    correctCases.add(new VatIdTestBean("GR", "EL090012763"));
    correctCases.add(new VatIdTestBean("GR", "EL094263543"));
    correctCases.add(new VatIdTestBean("GR", "EL094368710"));
    correctCases.add(new VatIdTestBean("GR", "EL094468339"));
    correctCases.add(new VatIdTestBean("GR", "EL095452597"));
    correctCases.add(new VatIdTestBean("GR", "EL099256461"));
    correctCases.add(new VatIdTestBean("GR", "EL117749540"));
    correctCases.add(new VatIdTestBean("GR", "EL123456783"));
    correctCases.add(new VatIdTestBean("GR", "EL800399111"));
    correctCases.add(new VatIdTestBean("GR", "EL997276654"));
    correctCases.add(new VatIdTestBean("GR", "EL997475274"));
    correctCases.add(new VatIdTestBean("GR", "EL997759744"));

    correctCases.add(new VatIdTestBean("HR", "HR02573674713"));
    correctCases.add(new VatIdTestBean("HR", "HR19819724166"));
    correctCases.add(new VatIdTestBean("HR", "HR42992093253"));
    correctCases.add(new VatIdTestBean("HR", "HR49903344364"));
    correctCases.add(new VatIdTestBean("HR", "HR57480970486"));
    correctCases.add(new VatIdTestBean("HR", "HR63625874835"));
    correctCases.add(new VatIdTestBean("HR", "HR68383212326"));
    correctCases.add(new VatIdTestBean("HR", "HR69644127206"));
    correctCases.add(new VatIdTestBean("HR", "HR76023745044"));
    correctCases.add(new VatIdTestBean("HR", "HR76169484118"));
    correctCases.add(new VatIdTestBean("HR", "HR91559034829"));
    correctCases.add(new VatIdTestBean("HR", "HR95024967787"));

    correctCases.add(new VatIdTestBean("IE", "IE4569590K"));
    correctCases.add(new VatIdTestBean("IE", "IE4600630O"));
    correctCases.add(new VatIdTestBean("IE", "IE6402687J"));
    correctCases.add(new VatIdTestBean("IE", "IE6589445G"));
    correctCases.add(new VatIdTestBean("IE", "IE8278666G"));
    correctCases.add(new VatIdTestBean("IE", "IE8279188B"));
    correctCases.add(new VatIdTestBean("IE", "IE8473625E"));
    correctCases.add(new VatIdTestBean("IE", "IE8G05913L"));
    correctCases.add(new VatIdTestBean("IE", "IE9715950S"));
    correctCases.add(new VatIdTestBean("IE", "IE9782962G"));
    correctCases.add(new VatIdTestBean("IE", "IE9789674N"));
    correctCases.add(new VatIdTestBean("IE", "IE9793487N"));

    correctCases.add(new VatIdTestBean("IT", "IT00203820998"));
    correctCases.add(new VatIdTestBean("IT", "IT00634780985"));
    correctCases.add(new VatIdTestBean("IT", "IT00760750216"));
    correctCases.add(new VatIdTestBean("IT", "IT00711390211"));
    correctCases.add(new VatIdTestBean("IT", "IT01021090319"));
    correctCases.add(new VatIdTestBean("IT", "IT01187190218"));
    correctCases.add(new VatIdTestBean("IT", "IT01390230462"));
    correctCases.add(new VatIdTestBean("IT", "IT01986880027"));
    correctCases.add(new VatIdTestBean("IT", "IT02736510211"));
    correctCases.add(new VatIdTestBean("IT", "IT03431820285"));
    correctCases.add(new VatIdTestBean("IT", "IT11257611001"));
    correctCases.add(new VatIdTestBean("IT", "IT12345670785"));

    correctCases.add(new VatIdTestBean("NL", "NL123456782B12"));
    correctCases.add(new VatIdTestBean("NL", "NL800189620B01"));
    correctCases.add(new VatIdTestBean("NL", "NL802327497B01"));
    correctCases.add(new VatIdTestBean("NL", "NL804115217B01"));
    correctCases.add(new VatIdTestBean("NL", "NL804693304B01"));
    correctCases.add(new VatIdTestBean("NL", "NL809892534B01"));
    correctCases.add(new VatIdTestBean("NL", "NL810584384B01"));
    correctCases.add(new VatIdTestBean("NL", "NL811784915B01"));
    correctCases.add(new VatIdTestBean("NL", "NL818058675B01"));
    correctCases.add(new VatIdTestBean("NL", "NL818151778B01"));
    correctCases.add(new VatIdTestBean("NL", "NL818176738B01"));
    correctCases.add(new VatIdTestBean("NL", "NL820646660B01"));

    correctCases.add(new VatIdTestBean("NO", "NO912349578"));
    correctCases.add(new VatIdTestBean("NO", "NO920473865"));
    correctCases.add(new VatIdTestBean("NO", "NO954349667"));
    correctCases.add(new VatIdTestBean("NO", "NO983767206"));
    correctCases.add(new VatIdTestBean("NO", "NO990003327"));
    correctCases.add(new VatIdTestBean("NO", "NO990022321"));
    correctCases.add(new VatIdTestBean("NO", "NO991358919"));
    correctCases.add(new VatIdTestBean("NO", "NO992350466"));
    correctCases.add(new VatIdTestBean("NO", "NO993872636"));
    correctCases.add(new VatIdTestBean("NO", "NO994387677"));
    correctCases.add(new VatIdTestBean("NO", "NO994888455"));
    correctCases.add(new VatIdTestBean("NO", "NO997411579"));

    correctCases.add(new VatIdTestBean("PL", "PL5580001325"));
    correctCases.add(new VatIdTestBean("PL", "PL5891998450"));
    correctCases.add(new VatIdTestBean("PL", "PL5992312572"));
    correctCases.add(new VatIdTestBean("PL", "PL6330005110"));
    correctCases.add(new VatIdTestBean("PL", "PL6750002236"));
    correctCases.add(new VatIdTestBean("PL", "PL6750000444"));
    correctCases.add(new VatIdTestBean("PL", "PL7772337068"));
    correctCases.add(new VatIdTestBean("PL", "PL8270007105"));
    correctCases.add(new VatIdTestBean("PL", "PL8567346215"));
    correctCases.add(new VatIdTestBean("PL", "PL8981047033"));
    correctCases.add(new VatIdTestBean("PL", "PL9291785452"));
    correctCases.add(new VatIdTestBean("PL", "PL9460004760"));

    correctCases.add(new VatIdTestBean("PT", "PT136695973"));
    correctCases.add(new VatIdTestBean("PT", "PT500049440"));
    correctCases.add(new VatIdTestBean("PT", "PT500108137"));
    correctCases.add(new VatIdTestBean("PT", "PT500333041"));
    correctCases.add(new VatIdTestBean("PT", "PT500666474"));
    correctCases.add(new VatIdTestBean("PT", "PT500948470"));
    correctCases.add(new VatIdTestBean("PT", "PT500990441"));
    correctCases.add(new VatIdTestBean("PT", "PT501216901"));
    correctCases.add(new VatIdTestBean("PT", "PT504167600"));
    correctCases.add(new VatIdTestBean("PT", "PT505324539"));
    correctCases.add(new VatIdTestBean("PT", "PT510685722"));
    correctCases.add(new VatIdTestBean("PT", "PT511144121"));

    correctCases.add(new VatIdTestBean("SE", "SE212000130601"));
    correctCases.add(new VatIdTestBean("SE", "SE556221562301"));
    correctCases.add(new VatIdTestBean("SE", "SE556310948601"));
    correctCases.add(new VatIdTestBean("SE", "SE556331636201"));
    correctCases.add(new VatIdTestBean("SE", "SE556435614401"));
    correctCases.add(new VatIdTestBean("SE", "SE556553874001"));
    correctCases.add(new VatIdTestBean("SE", "SE556672075001"));
    correctCases.add(new VatIdTestBean("SE", "SE556694972201"));
    correctCases.add(new VatIdTestBean("SE", "SE556749956001"));
    correctCases.add(new VatIdTestBean("SE", "SE556789211101"));
    correctCases.add(new VatIdTestBean("SE", "SE556866042601"));
    correctCases.add(new VatIdTestBean("SE", "SE556983570401"));

    correctCases.add(new VatIdTestBean("SI", "SI59082437"));
    correctCases.add(new VatIdTestBean("SI", "SI13345486"));
    correctCases.add(new VatIdTestBean("SI", "SI77172876"));
    correctCases.add(new VatIdTestBean("SI", "SI13859455"));
    correctCases.add(new VatIdTestBean("SI", "SI56012390"));
    correctCases.add(new VatIdTestBean("SI", "SI56116802"));
    correctCases.add(new VatIdTestBean("SI", "SI25296418"));
    correctCases.add(new VatIdTestBean("SI", "SI92859976"));
    correctCases.add(new VatIdTestBean("SI", "SI18190995"));
    correctCases.add(new VatIdTestBean("SI", "SI62634186"));
    correctCases.add(new VatIdTestBean("SI", "SI54045312"));
    correctCases.add(new VatIdTestBean("SI", "SI60595256"));
    correctCases.add(new VatIdTestBean("SI", "SI34384243"));

    correctCases.add(new VatIdTestBean("ES", "ESA13585625"));
    correctCases.add(new VatIdTestBean("ES", "ESN0040274C"));
    correctCases.add(new VatIdTestBean("ES", "ESB40192700"));
    correctCases.add(new VatIdTestBean("ES", "ESB57406787"));
    correctCases.add(new VatIdTestBean("ES", "ESJ55118814"));

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
    // wrongCases.add(new VatIdTestBean("ES", "ESA13558625"));
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
