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

package de.knightsoftnet.validators.client;

import de.knightsoftnet.validators.shared.beans.VatIdTestBean;

public class GwtTestVatIdTest extends AbstractValidationTest<VatIdTestBean> {


  /**
   * empty vat id is allowed.
   */
  public final void testEmptyVatIdIsAllowed() {
    super.validationTest(new VatIdTestBean(null, null), true, null);
  }

  /**
   * correct vat id's are allowed.
   */
  public final void testCorrectVatIdIsAllowed() {
    super.validationTest(new VatIdTestBean("AT", "ATU13585627"), true, null);
    super.validationTest(new VatIdTestBean("BE", "BE0136695962"), true, null);
    super.validationTest(new VatIdTestBean("DK", "DK13585628"), true, null);
    super.validationTest(new VatIdTestBean("DE", "DE136695976"), true, null);
    super.validationTest(new VatIdTestBean("FI", "FI13669598"), true, null);
    super.validationTest(new VatIdTestBean("GR", "EL123456783"), true, null);
    super.validationTest(new VatIdTestBean("IE", "IE8473625E"), true, null);
    super.validationTest(new VatIdTestBean("IT", "IT12345670785"), true, null);
    super.validationTest(new VatIdTestBean("NL", "NL123456782B12"), true, null);
    super.validationTest(new VatIdTestBean("PL", "PL8567346215"), true, null);
    super.validationTest(new VatIdTestBean("PT", "PT136695973"), true, null);
    super.validationTest(new VatIdTestBean("SE", "SE136695975523"), true, null);
    super.validationTest(new VatIdTestBean("SI", "SI59082437"), true, null);
    super.validationTest(new VatIdTestBean("ES", "ESA13585625"), true, null);
  }

  /**
   * vat id's with wrong checksumms are not allowed.
   */
  public final void testWrongChecksumVatIdIsWrong() {
    super.validationTest(new VatIdTestBean("AT", "ATU13586527"), false,
        "de.knightsoftnet.validators.shared.impl.VatIdValidator");
    super.validationTest(new VatIdTestBean("BE", "BE0136695963"), false,
        "de.knightsoftnet.validators.shared.impl.VatIdValidator");
    super.validationTest(new VatIdTestBean("DK", "DK13585627"), false,
        "de.knightsoftnet.validators.shared.impl.VatIdValidator");
    super.validationTest(new VatIdTestBean("DE", "DE136659976"), false,
        "de.knightsoftnet.validators.shared.impl.VatIdValidator");
    super.validationTest(new VatIdTestBean("FI", "FI13669589"), false,
        "de.knightsoftnet.validators.shared.impl.VatIdValidator");
    super.validationTest(new VatIdTestBean("GR", "EL123456784"), false,
        "de.knightsoftnet.validators.shared.impl.VatIdValidator");
    super.validationTest(new VatIdTestBean("IE", "IE8463625E"), false,
        "de.knightsoftnet.validators.shared.impl.VatIdValidator");
    super.validationTest(new VatIdTestBean("IT", "IT12354670785"), false,
        "de.knightsoftnet.validators.shared.impl.VatIdValidator");
    super.validationTest(new VatIdTestBean("NL", "NL123456783B12"), false,
        "de.knightsoftnet.validators.shared.impl.VatIdValidator");
    super.validationTest(new VatIdTestBean("PL", "PL8567436215"), false,
        "de.knightsoftnet.validators.shared.impl.VatIdValidator");
    super.validationTest(new VatIdTestBean("PT", "PT136695974"), false,
        "de.knightsoftnet.validators.shared.impl.VatIdValidator");
    super.validationTest(new VatIdTestBean("SE", "SE136699575523"), false,
        "de.knightsoftnet.validators.shared.impl.VatIdValidator");
    super.validationTest(new VatIdTestBean("SI", "SI59082436"), false,
        "de.knightsoftnet.validators.shared.impl.VatIdValidator");
    super.validationTest(new VatIdTestBean("ES", "ESA13558625"), false,
        "de.knightsoftnet.validators.shared.impl.VatIdValidator");
  }

  /**
   * correct vat id not matching country rules.
   */
  public final void testWrongVatIdIsWrong() {
    super.validationTest(new VatIdTestBean("ES", "ATU13585627"), false,
        "de.knightsoftnet.validators.shared.impl.VatIdValidator");
    super.validationTest(new VatIdTestBean("AT", "BE136695962"), false,
        "de.knightsoftnet.validators.shared.impl.VatIdValidator");
    super.validationTest(new VatIdTestBean("BE", "DK13585628"), false,
        "de.knightsoftnet.validators.shared.impl.VatIdValidator");
    super.validationTest(new VatIdTestBean("DK", "DE136695976"), false,
        "de.knightsoftnet.validators.shared.impl.VatIdValidator");
    super.validationTest(new VatIdTestBean("DE", "FI13669598"), false,
        "de.knightsoftnet.validators.shared.impl.VatIdValidator");
    super.validationTest(new VatIdTestBean("FI", "EL123456783"), false,
        "de.knightsoftnet.validators.shared.impl.VatIdValidator");
    super.validationTest(new VatIdTestBean("GR", "IE8473625E"), false,
        "de.knightsoftnet.validators.shared.impl.VatIdValidator");
    super.validationTest(new VatIdTestBean("IE", "IT12345670785"), false,
        "de.knightsoftnet.validators.shared.impl.VatIdValidator");
    super.validationTest(new VatIdTestBean("IT", "NL123456782B12"), false,
        "de.knightsoftnet.validators.shared.impl.VatIdValidator");
    super.validationTest(new VatIdTestBean("NL", "PL8567346215"), false,
        "de.knightsoftnet.validators.shared.impl.VatIdValidator");
    super.validationTest(new VatIdTestBean("PL", "PT136695973"), false,
        "de.knightsoftnet.validators.shared.impl.VatIdValidator");
    super.validationTest(new VatIdTestBean("PT", "SE136695975523"), false,
        "de.knightsoftnet.validators.shared.impl.VatIdValidator");
    super.validationTest(new VatIdTestBean("SE", "SI59082437"), false,
        "de.knightsoftnet.validators.shared.impl.VatIdValidator");
    super.validationTest(new VatIdTestBean("SI", "ESA13585625"), false,
        "de.knightsoftnet.validators.shared.impl.VatIdValidator");
  }
}
