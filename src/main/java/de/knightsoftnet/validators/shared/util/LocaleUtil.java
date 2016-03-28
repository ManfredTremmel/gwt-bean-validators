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

package de.knightsoftnet.validators.shared.util;

import java.util.Locale;

/**
 * Locale Util, get locale for language string.
 *
 * @author Manfred Tremmel
 *
 */
public class LocaleUtil {

  /**
   * convert language string to Locale.
   *
   * @param planguage string with language value
   * @return Locale for the language
   */
  public static Locale convertLanguageToLocale(final String planguage) {
    final Locale locale;
    if (planguage == null) {
      locale = null;
    } else {
      final String localeStringUp = planguage.toUpperCase().replace('-', '_');
      if ("DE_DE".equals(localeStringUp) || "DE".equals(localeStringUp)) {
        locale = java.util.Locale.GERMANY;
      } else if ("EN_US".equals(localeStringUp)) {
        locale = java.util.Locale.US;
      } else if ("EN_UK".equals(localeStringUp)) {
        locale = java.util.Locale.UK;
      } else if ("EN_CA".equals(localeStringUp)) {
        locale = java.util.Locale.CANADA;
      } else if ("FR_FR".equals(localeStringUp)) {
        locale = java.util.Locale.FRANCE;
      } else if ("FR_CA".equals(localeStringUp)) {
        locale = java.util.Locale.CANADA_FRENCH;
      } else if ("ZH_CN".equals(localeStringUp)) {
        locale = java.util.Locale.CHINA;
      } else if ("ZH_TW".equals(localeStringUp)) {
        locale = java.util.Locale.TAIWAN;
      } else if ("ZH".equals(localeStringUp)) {
        locale = java.util.Locale.CHINESE;
      } else if ("JP".equals(localeStringUp)) {
        locale = java.util.Locale.JAPAN;
      } else if ("IT".equals(localeStringUp)) {
        locale = java.util.Locale.ITALY;
      } else if ("KO".equals(localeStringUp)) {
        locale = java.util.Locale.KOREA;
      } else if (localeStringUp.contains("_")) {
        final String[] lcoaleSplitted = localeStringUp.split("_");
        if (lcoaleSplitted.length > 2) {
          locale =
              new Locale(lcoaleSplitted[0].toLowerCase(), lcoaleSplitted[1], lcoaleSplitted[2]);
        } else {
          locale = new Locale(lcoaleSplitted[0].toLowerCase(), lcoaleSplitted[1]);
        }
      } else {
        locale = new Locale(planguage);
      }
    }
    return locale;
  }
}
