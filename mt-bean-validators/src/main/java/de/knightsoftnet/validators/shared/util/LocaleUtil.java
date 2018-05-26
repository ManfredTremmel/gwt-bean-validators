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

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Locale Util, get locale for language string.
 *
 * @author Manfred Tremmel
 *
 */
public class LocaleUtil {
  private static final Map<String, Locale> DEFAULT_MAP = new HashMap<>();

  static {
    LocaleUtil.DEFAULT_MAP.put("DE_DE", Locale.GERMANY);
    LocaleUtil.DEFAULT_MAP.put("DE", Locale.GERMAN);
    LocaleUtil.DEFAULT_MAP.put("EN_US", Locale.US);
    LocaleUtil.DEFAULT_MAP.put("EN_UK", Locale.UK);
    LocaleUtil.DEFAULT_MAP.put("EN_CA", Locale.CANADA);
    LocaleUtil.DEFAULT_MAP.put("FR_FR", Locale.FRANCE);
    LocaleUtil.DEFAULT_MAP.put("FR_CA", Locale.CANADA_FRENCH);
    LocaleUtil.DEFAULT_MAP.put("ZH_CN", Locale.CHINA);
    LocaleUtil.DEFAULT_MAP.put("ZH_TW", Locale.TAIWAN);
    LocaleUtil.DEFAULT_MAP.put("ZH", Locale.CHINESE);
    LocaleUtil.DEFAULT_MAP.put("JP", Locale.JAPAN);
    LocaleUtil.DEFAULT_MAP.put("IT", Locale.ITALY);
    LocaleUtil.DEFAULT_MAP.put("KO", Locale.KOREA);
  }

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
      if (LocaleUtil.DEFAULT_MAP.containsKey(localeStringUp)) {
        locale = LocaleUtil.DEFAULT_MAP.get(localeStringUp);
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
