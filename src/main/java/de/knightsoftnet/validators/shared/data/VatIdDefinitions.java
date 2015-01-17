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
 * Some vat id regular expression definitions.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
public final class VatIdDefinitions {

  /**
   * map with vat id regular expressions of different countries.
   */
  public static final Map<String, String> COUNTRY_VAT_ID_REGEX = new HashMap<String, String>();

  static {
    COUNTRY_VAT_ID_REGEX.put("AL", "^AL[JK]\\d{8}[A-Z]$");
    COUNTRY_VAT_ID_REGEX.put("AR", "^AR\\d{11}$");
    COUNTRY_VAT_ID_REGEX.put("AT", "^ATU\\d{8}$");
    COUNTRY_VAT_ID_REGEX.put("AU", "^AU\\d{9}$");
    COUNTRY_VAT_ID_REGEX.put("BE", "^BE\\d{10}$");
    COUNTRY_VAT_ID_REGEX.put("BG", "^BG\\d{9,10}$");
    COUNTRY_VAT_ID_REGEX.put("BR", "^BR\\d{14}$");
    COUNTRY_VAT_ID_REGEX.put("BY", "^УНП\\d{9}$");
    COUNTRY_VAT_ID_REGEX.put("CA", "^CA\\[0-9A-Z]{15}$");
    COUNTRY_VAT_ID_REGEX.put("CH", "^CHE-\\d{3}\\.\\d{3}\\.\\d{3} (TVA|MWST|IVA)$");
    COUNTRY_VAT_ID_REGEX.put("CR", "^CR\\d{9,12}$");
    COUNTRY_VAT_ID_REGEX.put("CY", "^CY\\d{8}[A-Z]$");
    COUNTRY_VAT_ID_REGEX.put("CZ", "^CZ\\d{8,10}$");
    COUNTRY_VAT_ID_REGEX.put("DE", "^DE\\d{9}$");
    COUNTRY_VAT_ID_REGEX.put("DK", "^DK\\d{8}$");
    COUNTRY_VAT_ID_REGEX.put("EC", "^EC\\d{13}$");
    COUNTRY_VAT_ID_REGEX.put("EE", "^EE\\d{9}$");
    COUNTRY_VAT_ID_REGEX.put("ES", "^ES([0-9A-Z]\\d{7}[A-Z]|[A-Z]\\d{7}[0-9A-Z])$");
    COUNTRY_VAT_ID_REGEX.put("FI", "^FI\\d{8}$");
    COUNTRY_VAT_ID_REGEX.put("FR", "^FR[0-9A-Z]{2}\\d{9}$");
    COUNTRY_VAT_ID_REGEX.put("GB", "^GB(GD\\d{3}|HA\\d{3}|\\d{9}|\\d{12})$");
    COUNTRY_VAT_ID_REGEX.put("GR", "^EL\\d{9}$");
    COUNTRY_VAT_ID_REGEX.put("GT", "^GT\\d{8}$");
    COUNTRY_VAT_ID_REGEX.put("HR", "^HR\\d{11}$");
    COUNTRY_VAT_ID_REGEX.put("HU", "^HU\\d{8}$");
    COUNTRY_VAT_ID_REGEX.put("IE", "^IE(\\d[0-9A-Z]\\d{5}[A-W]|\\d{7}[A-W][A-I])$");
    COUNTRY_VAT_ID_REGEX.put("IN", "^IN\\d{11}[CV]$");
    COUNTRY_VAT_ID_REGEX.put("IT", "^IT\\d{11}$");
    COUNTRY_VAT_ID_REGEX.put("LT", "^LT(\\d{9}|\\d{12})$");
    COUNTRY_VAT_ID_REGEX.put("LU", "^LU\\d{8}$");
    COUNTRY_VAT_ID_REGEX.put("LV", "^LV\\d{11}$");
    COUNTRY_VAT_ID_REGEX.put("MT", "^MT\\d{8}$");
    COUNTRY_VAT_ID_REGEX.put("MX", "^MX\\D{3-4}\\d{6}\\D{3}$");
    COUNTRY_VAT_ID_REGEX.put("NL", "^NL\\d{9}B\\d{2}$");
    COUNTRY_VAT_ID_REGEX.put("NO", "^NO\\d{9}$");
    COUNTRY_VAT_ID_REGEX.put("PE", "^PE\\d{11}$");
    COUNTRY_VAT_ID_REGEX.put("PH", "^PH\\d{12}$");
    COUNTRY_VAT_ID_REGEX.put("PL", "^PL\\d{10}$");
    COUNTRY_VAT_ID_REGEX.put("PT", "^PT\\d{9}$");
    COUNTRY_VAT_ID_REGEX.put("RO", "^RO\\d{2,10}$");
    COUNTRY_VAT_ID_REGEX.put("RS", "^RS\\d{9}$");
    COUNTRY_VAT_ID_REGEX.put("RU", "^RU\\d{10,12}$");
    COUNTRY_VAT_ID_REGEX.put("SE", "^SE\\d{10}01$");
    COUNTRY_VAT_ID_REGEX.put("SI", "^SI\\d{8}$");
    COUNTRY_VAT_ID_REGEX.put("SK", "^SK\\d{10}$");
    COUNTRY_VAT_ID_REGEX.put("SM", "^SM\\d{5}$");
    COUNTRY_VAT_ID_REGEX.put("TR", "^TR\\d{10}$");
    COUNTRY_VAT_ID_REGEX.put("UA", "^UA\\d{12}$");
    COUNTRY_VAT_ID_REGEX.put("VE", "^VE[JGVE]-(\\d{9}|\\d{8}-\\d)$");
  }

  /**
   * private default constructor.
   */
  private VatIdDefinitions() {
    super();
  }
}
