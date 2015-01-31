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
 * Postal code regex definitions for the known countrys.
 *
 * @author Manfred Tremmel
 *
 *
 */
public final class PostalCodeDefinitions {

  /**
   * regular expression for checking postal codes in great britten.
   */
  private static final String REGEX_GBR = "^GIR[ ]?0AA|((AB|AL|B|BA|BB|BD"
      + "|BH|BL|BN|BR|BS|BT|CA|CB|CF|CH|CM|CO|CR|CT|CV|CW|DA|DD|DE|DG"
      + "|DH|DL|DN|DT|DY|E|EC|EH|EN|EX|FK|FY|G|GL|GY|GU|HA|HD|HG|HP|HR"
      + "|HS|HU|HX|IG|IM|IP|IV|JE|KA|KT|KW|KY|L|LA|LD|LE|LL|LN|LS|LU|M"
      + "|ME|MK|ML|N|NE|NG|NN|NP|NR|NW|OL|OX|PA|PE|PH|PL|PO|PR|RG|RH|RM"
      + "|S|SA|SE|SG|SK|SL|SM|SN|SO|SP|SR|SS|ST|SW|SY|TA|TD|TF|TN|TQ|TR"
      + "|TS|TW|UB|W|WA|WC|WD|WF|WN|WR|WS|WV|YO|ZE)(\\d[\\dA-Z]?[ ]?"
      + "\\d[ABD-HJLN-UW-Z]{2}))|BFPO[ ]?\\d{1,4}$";

  /**
   * map with postal code checks for different countries.
   */
  public static final Map<String, String> COUNTRY_POSTAL_CODE_REGEX = new HashMap<String, String>();

  static {
    COUNTRY_POSTAL_CODE_REGEX.put("AC", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("AD", "^AD[0-9]{3}$");
    COUNTRY_POSTAL_CODE_REGEX.put("AE", "^\\d{6}$");
    COUNTRY_POSTAL_CODE_REGEX.put("AF", "[0-9]{4}*$");
    COUNTRY_POSTAL_CODE_REGEX.put("AG", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("AI", REGEX_GBR);
    COUNTRY_POSTAL_CODE_REGEX.put("AL", "^[0-9]{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("AM", "^(37)?\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("AN", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("AO", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("AQ", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("AR", "^([A-HJ-NP-Z])?\\d{4}([A-Z]{3})?$");
    COUNTRY_POSTAL_CODE_REGEX.put("AS", "^96799$");
    COUNTRY_POSTAL_CODE_REGEX.put("AT", "^\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("AU", "^\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("AW", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("AZ", "^\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("BA", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("BB", "^(BB\\d{5})?$");
    COUNTRY_POSTAL_CODE_REGEX.put("BD", "^\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("BE", "^\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("BF", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("BG", "^\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("BI", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("BJ", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("BL", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("BM", "^[A-Z]{2}[ ]?[A-Z0-9]{2}$");
    COUNTRY_POSTAL_CODE_REGEX.put("BN", "^[A-Z]{2}[ ]?\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("BO", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("BQ", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("BR", "^\\d{5}[\\-]?\\d{3}$");
    COUNTRY_POSTAL_CODE_REGEX.put("BS", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("BT", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("BW", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("BY", "^\\d{6}$");
    COUNTRY_POSTAL_CODE_REGEX.put("BZ", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("CA",
        "^[ABCEGHJKLMNPRSTVXY]\\d[ABCEGHJ-NPRSTV-Z][ ]?\\d[ABCEGHJ-NPRSTV-Z]\\d$");
    COUNTRY_POSTAL_CODE_REGEX.put("CC", "^6799$");
    COUNTRY_POSTAL_CODE_REGEX.put("CD", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("CF", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("CG", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("CH", "^\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("CI", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("CK", "^\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("CL", "^\\d{7}$");
    COUNTRY_POSTAL_CODE_REGEX.put("CM", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("CN", "^\\d{6}$");
    COUNTRY_POSTAL_CODE_REGEX.put("CO", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("CR", "^\\d{4,5}|\\d{3}-\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("CU", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("CV", "^\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("CW", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("CX", "^6798$");
    COUNTRY_POSTAL_CODE_REGEX.put("CY", "^\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("CZ", "^\\d{3}[ ]?\\d{2}$");
    COUNTRY_POSTAL_CODE_REGEX.put("DE", "^([1-9]\\d|0[1-9])\\d{3}$");
    COUNTRY_POSTAL_CODE_REGEX.put("DJ", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("DK", "^\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("DM", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("DO", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("DZ", "^[0-9]{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("EC", "^([A-Z]\\d{4}[A-Z]|(?:[A-Z]{2})?\\d{6})?$");
    COUNTRY_POSTAL_CODE_REGEX.put("EE", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("EG", "^[0-9]{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("EH", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("ER", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("ES", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("ET", "^\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("FI", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("FJ", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("FK", "^FIQQ 1ZZ$");
    COUNTRY_POSTAL_CODE_REGEX.put("FM", "^(9694[1-4])([ \\-]\\d{4})?$");
    COUNTRY_POSTAL_CODE_REGEX.put("FO", "^\\d{3}$");
    COUNTRY_POSTAL_CODE_REGEX.put("FR", "^\\d{2}[ ]?\\d{3}$");
    COUNTRY_POSTAL_CODE_REGEX.put("GA", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("GB", REGEX_GBR);
    COUNTRY_POSTAL_CODE_REGEX.put("GD", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("GE", "^\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("GF", "^9[78]3\\d{2}$");
    COUNTRY_POSTAL_CODE_REGEX.put("GG", "^GY\\d[\\dA-Z]?[ ]?\\d[ABD-HJLN-UW-Z]{2}$");
    COUNTRY_POSTAL_CODE_REGEX.put("GH", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("GI", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("GL", "^39\\d{2}$");
    COUNTRY_POSTAL_CODE_REGEX.put("GM", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("GN", "^\\d{3}$");
    COUNTRY_POSTAL_CODE_REGEX.put("GP", "^9[78][01]\\d{2}$");
    COUNTRY_POSTAL_CODE_REGEX.put("GQ", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("GR", "^\\d{3}[ ]?\\d{2}$");
    COUNTRY_POSTAL_CODE_REGEX.put("GS", "^SIQQ 1ZZ$");
    COUNTRY_POSTAL_CODE_REGEX.put("GT", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("GU", "^969[123]\\d([ \\-]\\d{4})?$");
    COUNTRY_POSTAL_CODE_REGEX.put("GW", "^\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("GY", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("HK", "^\\d{6}$");
    COUNTRY_POSTAL_CODE_REGEX.put("HN", "^(?:\\d{5})$");
    COUNTRY_POSTAL_CODE_REGEX.put("HR", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("HT", "^\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("HU", "^\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("ID", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("IE", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("IL", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("IM", "^IM\\d[\\dA-Z]?[ ]?\\d[ABD-HJLN-UW-Z]{2}$");
    COUNTRY_POSTAL_CODE_REGEX.put("IN", "^\\d{6}$");
    COUNTRY_POSTAL_CODE_REGEX.put("IO", "^BBND 1ZZ$");
    COUNTRY_POSTAL_CODE_REGEX.put("IQ", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("IR", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("IS", "^\\d{3}$");
    COUNTRY_POSTAL_CODE_REGEX.put("IT", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("JE", "^JE\\d[\\dA-Z]?[ ]?\\d[ABD-HJLN-UW-Z]{2}$");
    COUNTRY_POSTAL_CODE_REGEX.put("JM", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("JO", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("JP", "^\\d{3}-\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("KE", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("KG", "^\\d{6}$");
    COUNTRY_POSTAL_CODE_REGEX.put("KH", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("KI", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("KM", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("KN", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("KP", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("KR", "^\\d{3}[\\-]\\d{3}$");
    COUNTRY_POSTAL_CODE_REGEX.put("KW", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("KY", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("KZ", "^\\d{6}$");
    COUNTRY_POSTAL_CODE_REGEX.put("LA", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("LB", "^(\\d{4}([ ]?\\d{4})?)?$");
    COUNTRY_POSTAL_CODE_REGEX.put("LC", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("LI", "^(948[5-9])|(949[0-7])$");
    COUNTRY_POSTAL_CODE_REGEX.put("LK", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("LR", "^\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("LS", "^\\d{3}$");
    COUNTRY_POSTAL_CODE_REGEX.put("LT", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("LU", "^\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("LV", "^\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("LY", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("MA", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("MC", "^980\\d{2}$");
    COUNTRY_POSTAL_CODE_REGEX.put("MD", "^\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("ME", "^8\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("MF", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("MG", "^\\d{3}$");
    COUNTRY_POSTAL_CODE_REGEX.put("MH", "^969[67]\\d([ \\-]\\d{4})?$");
    COUNTRY_POSTAL_CODE_REGEX.put("MK", "^\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("ML", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("MM", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("MN", "^\\d{6}$");
    COUNTRY_POSTAL_CODE_REGEX.put("MO", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("MP", "^9695[012]([ \\-]\\d{4})?$");
    COUNTRY_POSTAL_CODE_REGEX.put("MQ", "^9[78]2\\d{2}$");
    COUNTRY_POSTAL_CODE_REGEX.put("MR", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("MS", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("MT", "^[A-Z]{3}[ ]?\\d{2,4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("MU", "^(\\d{3}[A-Z]{2}\\d{3})?$");
    COUNTRY_POSTAL_CODE_REGEX.put("MV", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("MW", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("MX", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("MY", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("MZ", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("NA", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("NC", "^988\\d{2}$");
    COUNTRY_POSTAL_CODE_REGEX.put("NE", "^\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("NF", "^2899$");
    COUNTRY_POSTAL_CODE_REGEX.put("NG", "^(\\d{6})?$");
    COUNTRY_POSTAL_CODE_REGEX.put("NI", "^((\\d{4}-)?\\d{3}-\\d{3}(-\\d{1})?)?$");
    COUNTRY_POSTAL_CODE_REGEX.put("NL", "^\\d{4}[ ]?[A-Z]{2}$");
    COUNTRY_POSTAL_CODE_REGEX.put("NO", "^\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("NP", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("NR", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("NU", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("NZ", "^\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("OM", "^(PC )?\\d{3}$");
    COUNTRY_POSTAL_CODE_REGEX.put("PA", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("PE", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("PF", "^987\\d{2}$");
    COUNTRY_POSTAL_CODE_REGEX.put("PG", "^\\d{3}$");
    COUNTRY_POSTAL_CODE_REGEX.put("PH", "^\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("PK", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("PL", "^\\d{2}-\\d{3}$");
    COUNTRY_POSTAL_CODE_REGEX.put("PM", "^9[78]5\\d{2}$");
    COUNTRY_POSTAL_CODE_REGEX.put("PN", "^PCRN 1ZZ$");
    COUNTRY_POSTAL_CODE_REGEX.put("PR", "^00[679]\\d{2}([ \\-]\\d{4})?$");
    COUNTRY_POSTAL_CODE_REGEX.put("PS", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("PT", "^\\d{4}([\\-]\\d{3})?$");
    COUNTRY_POSTAL_CODE_REGEX.put("PW", "^96940$");
    COUNTRY_POSTAL_CODE_REGEX.put("PY", "^\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("QA", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("RE", "^9[78]4\\d{2}$");
    COUNTRY_POSTAL_CODE_REGEX.put("RO", "^\\d{6}$");
    COUNTRY_POSTAL_CODE_REGEX.put("RS", "^\\d{6}$");
    COUNTRY_POSTAL_CODE_REGEX.put("RU", "^\\d{6}$");
    COUNTRY_POSTAL_CODE_REGEX.put("RW", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("SA", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("SB", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("SC", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("SD", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("SE", "^\\d{3}[ ]?\\d{2}$");
    COUNTRY_POSTAL_CODE_REGEX.put("SG", "^\\d{6}$");
    COUNTRY_POSTAL_CODE_REGEX.put("SH", "^(ASCN|STHL) 1ZZ$");
    COUNTRY_POSTAL_CODE_REGEX.put("SI", "^\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("SJ", "^\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("SK", "^\\d{3}[ ]?\\d{2}$");
    COUNTRY_POSTAL_CODE_REGEX.put("SL", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("SM", "^4789\\d$");
    COUNTRY_POSTAL_CODE_REGEX.put("SN", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("SO", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("SR", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("SS", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("ST", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("SV", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("SX", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("SY", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("SZ", "^[HLMS]\\d{3}$");
    COUNTRY_POSTAL_CODE_REGEX.put("TB", "^\\d{6}$");
    COUNTRY_POSTAL_CODE_REGEX.put("TC", "^TKCA 1ZZ$");
    COUNTRY_POSTAL_CODE_REGEX.put("TD", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("TF", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("TG", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("TH", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("TJ", "^\\d{6}$");
    COUNTRY_POSTAL_CODE_REGEX.put("TK", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("TL", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("TM", "^\\d{6}$");
    COUNTRY_POSTAL_CODE_REGEX.put("TN", "^\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("TO", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("TR", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("TT", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("TV", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("TW", "^\\d{3}(\\d{2})?$");
    COUNTRY_POSTAL_CODE_REGEX.put("TZ", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("UA", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("UG", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("US", "^\\d{5}([ \\-]\\d{4})?$");
    COUNTRY_POSTAL_CODE_REGEX.put("UY", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("UZ", "^\\d{6}$");
    COUNTRY_POSTAL_CODE_REGEX.put("VA", "^00120$");
    COUNTRY_POSTAL_CODE_REGEX.put("VC", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("VE", "^\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("VG", "^[A-Z]{2}\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("VI", "^008(([0-4]\\d)|(5[01]))([ \\-]\\d{4})?$");
    COUNTRY_POSTAL_CODE_REGEX.put("VN", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("VU", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("WF", "^986\\d{2}$");
    COUNTRY_POSTAL_CODE_REGEX.put("WS", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("YE", "^.*$");
    COUNTRY_POSTAL_CODE_REGEX.put("YT", "^976\\d{2}$");
    COUNTRY_POSTAL_CODE_REGEX.put("ZA", "^\\d{4}$");
    COUNTRY_POSTAL_CODE_REGEX.put("ZM", "^\\d{5}$");
    COUNTRY_POSTAL_CODE_REGEX.put("ZW", "^.*$");
  }

  /**
   * private default constructor.
   */
  private PostalCodeDefinitions() {
    super();
  }
}
