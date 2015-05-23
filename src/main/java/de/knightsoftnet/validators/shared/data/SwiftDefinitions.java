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

package de.knightsoftnet.validators.shared.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Some SWIFT definitions for bank accounts.
 *
 * @author Manfred Tremmel
 *
 */
public final class SwiftDefinitions {
  /**
   * map to detect length of IBAN numbers.
   */
  public static final Map<String, Integer> COUNTRY_IBAN_LENGTH = new HashMap<String, Integer>();

  static {
    COUNTRY_IBAN_LENGTH.put("AD", Integer.valueOf(24));
    COUNTRY_IBAN_LENGTH.put("AE", Integer.valueOf(23));
    COUNTRY_IBAN_LENGTH.put("AL", Integer.valueOf(28));
    COUNTRY_IBAN_LENGTH.put("AO", Integer.valueOf(25));
    COUNTRY_IBAN_LENGTH.put("AT", Integer.valueOf(20));
    COUNTRY_IBAN_LENGTH.put("AZ", Integer.valueOf(28));
    COUNTRY_IBAN_LENGTH.put("BA", Integer.valueOf(20));
    COUNTRY_IBAN_LENGTH.put("BE", Integer.valueOf(16));
    COUNTRY_IBAN_LENGTH.put("BF", Integer.valueOf(27));
    COUNTRY_IBAN_LENGTH.put("BG", Integer.valueOf(22));
    COUNTRY_IBAN_LENGTH.put("BH", Integer.valueOf(22));
    COUNTRY_IBAN_LENGTH.put("BI", Integer.valueOf(16));
    COUNTRY_IBAN_LENGTH.put("BJ", Integer.valueOf(28));
    COUNTRY_IBAN_LENGTH.put("BR", Integer.valueOf(29));
    COUNTRY_IBAN_LENGTH.put("CF", Integer.valueOf(27));
    COUNTRY_IBAN_LENGTH.put("CG", Integer.valueOf(27));
    COUNTRY_IBAN_LENGTH.put("CH", Integer.valueOf(21));
    COUNTRY_IBAN_LENGTH.put("CI", Integer.valueOf(28));
    COUNTRY_IBAN_LENGTH.put("CM", Integer.valueOf(27));
    COUNTRY_IBAN_LENGTH.put("CR", Integer.valueOf(21));
    COUNTRY_IBAN_LENGTH.put("CV", Integer.valueOf(25));
    COUNTRY_IBAN_LENGTH.put("CY", Integer.valueOf(28));
    COUNTRY_IBAN_LENGTH.put("CZ", Integer.valueOf(24));
    COUNTRY_IBAN_LENGTH.put("DE", Integer.valueOf(22));
    COUNTRY_IBAN_LENGTH.put("DK", Integer.valueOf(18));
    COUNTRY_IBAN_LENGTH.put("DO", Integer.valueOf(28));
    COUNTRY_IBAN_LENGTH.put("DZ", Integer.valueOf(24));
    COUNTRY_IBAN_LENGTH.put("EE", Integer.valueOf(20));
    COUNTRY_IBAN_LENGTH.put("EG", Integer.valueOf(27));
    COUNTRY_IBAN_LENGTH.put("ES", Integer.valueOf(24));
    COUNTRY_IBAN_LENGTH.put("FI", Integer.valueOf(18));
    COUNTRY_IBAN_LENGTH.put("FO", Integer.valueOf(18));
    COUNTRY_IBAN_LENGTH.put("FR", Integer.valueOf(27));
    COUNTRY_IBAN_LENGTH.put("GA", Integer.valueOf(27));
    COUNTRY_IBAN_LENGTH.put("GB", Integer.valueOf(22));
    COUNTRY_IBAN_LENGTH.put("GE", Integer.valueOf(22));
    COUNTRY_IBAN_LENGTH.put("GI", Integer.valueOf(23));
    COUNTRY_IBAN_LENGTH.put("GL", Integer.valueOf(18));
    COUNTRY_IBAN_LENGTH.put("GR", Integer.valueOf(27));
    COUNTRY_IBAN_LENGTH.put("GT", Integer.valueOf(28));
    COUNTRY_IBAN_LENGTH.put("HR", Integer.valueOf(21));
    COUNTRY_IBAN_LENGTH.put("HU", Integer.valueOf(28));
    COUNTRY_IBAN_LENGTH.put("IE", Integer.valueOf(22));
    COUNTRY_IBAN_LENGTH.put("IL", Integer.valueOf(23));
    COUNTRY_IBAN_LENGTH.put("IR", Integer.valueOf(26));
    COUNTRY_IBAN_LENGTH.put("IS", Integer.valueOf(26));
    COUNTRY_IBAN_LENGTH.put("IT", Integer.valueOf(27));
    COUNTRY_IBAN_LENGTH.put("JO", Integer.valueOf(30));
    COUNTRY_IBAN_LENGTH.put("KW", Integer.valueOf(30));
    COUNTRY_IBAN_LENGTH.put("KZ", Integer.valueOf(20));
    COUNTRY_IBAN_LENGTH.put("LB", Integer.valueOf(28));
    COUNTRY_IBAN_LENGTH.put("LI", Integer.valueOf(21));
    COUNTRY_IBAN_LENGTH.put("LT", Integer.valueOf(20));
    COUNTRY_IBAN_LENGTH.put("LU", Integer.valueOf(20));
    COUNTRY_IBAN_LENGTH.put("LV", Integer.valueOf(21));
    COUNTRY_IBAN_LENGTH.put("MC", Integer.valueOf(27));
    COUNTRY_IBAN_LENGTH.put("MD", Integer.valueOf(24));
    COUNTRY_IBAN_LENGTH.put("ME", Integer.valueOf(22));
    COUNTRY_IBAN_LENGTH.put("MG", Integer.valueOf(27));
    COUNTRY_IBAN_LENGTH.put("MK", Integer.valueOf(19));
    COUNTRY_IBAN_LENGTH.put("ML", Integer.valueOf(28));
    COUNTRY_IBAN_LENGTH.put("MR", Integer.valueOf(27));
    COUNTRY_IBAN_LENGTH.put("MT", Integer.valueOf(31));
    COUNTRY_IBAN_LENGTH.put("MU", Integer.valueOf(30));
    COUNTRY_IBAN_LENGTH.put("MZ", Integer.valueOf(25));
    COUNTRY_IBAN_LENGTH.put("NL", Integer.valueOf(18));
    COUNTRY_IBAN_LENGTH.put("NO", Integer.valueOf(15));
    COUNTRY_IBAN_LENGTH.put("PK", Integer.valueOf(24));
    COUNTRY_IBAN_LENGTH.put("PL", Integer.valueOf(28));
    COUNTRY_IBAN_LENGTH.put("PS", Integer.valueOf(29));
    COUNTRY_IBAN_LENGTH.put("PT", Integer.valueOf(25));
    COUNTRY_IBAN_LENGTH.put("QA", Integer.valueOf(29));
    COUNTRY_IBAN_LENGTH.put("RO", Integer.valueOf(24));
    COUNTRY_IBAN_LENGTH.put("RS", Integer.valueOf(22));
    COUNTRY_IBAN_LENGTH.put("SA", Integer.valueOf(24));
    COUNTRY_IBAN_LENGTH.put("SE", Integer.valueOf(24));
    COUNTRY_IBAN_LENGTH.put("SI", Integer.valueOf(19));
    COUNTRY_IBAN_LENGTH.put("SK", Integer.valueOf(24));
    COUNTRY_IBAN_LENGTH.put("SM", Integer.valueOf(27));
    COUNTRY_IBAN_LENGTH.put("SN", Integer.valueOf(28));
    COUNTRY_IBAN_LENGTH.put("ST", Integer.valueOf(25));
    COUNTRY_IBAN_LENGTH.put("TL", Integer.valueOf(23));
    COUNTRY_IBAN_LENGTH.put("TN", Integer.valueOf(24));
    COUNTRY_IBAN_LENGTH.put("TR", Integer.valueOf(26));
    COUNTRY_IBAN_LENGTH.put("VG", Integer.valueOf(24));
    COUNTRY_IBAN_LENGTH.put("XK", Integer.valueOf(20));
  }

  /**
   * private default constructor.
   */
  private SwiftDefinitions() {
    super();
  }
}
