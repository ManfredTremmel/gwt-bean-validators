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

import de.knightsoftnet.validators.server.data.CreatePhoneCountryConstantsClass;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

/**
 * Read gwt constants from properties file, server and client side shared parts.
 *
 * @author Manfred Tremmel
 *
 */
public abstract class AbstractCreateClass { // NOPMD, can't include abstract static methods

  protected static PhoneCountryConstantsImpl createPhoneCountryConstants(final Locale plocale) {
    final Map<String, String> phoneCountryNames =
        CreatePhoneCountryConstantsClass.readPhoneCountryNames(plocale);
    final Map<String, String> phoneCountryCodes =
        CreatePhoneCountryConstantsClass.readPhoneCountryCodes(plocale);
    final Set<PhoneCountryCodeData> countryCodeData =
        readPhoneCountryProperties(plocale, phoneCountryNames, phoneCountryCodes);
    return new PhoneCountryConstantsImpl(countryCodeData,
        createMapFromPhoneCountry(plocale, countryCodeData, phoneCountryNames, phoneCountryCodes));
  }

  protected static Set<PhoneCountryCodeData> readPhoneCountryProperties(final Locale plocale,
      final Map<String, String> pphoneCountryNames, final Map<String, String> pphoneCountryCodes) {
    final Set<PhoneCountryCodeData> result = new TreeSet<>();
    for (final Entry<String, String> country : pphoneCountryCodes.entrySet()) {
      final PhoneCountryCodeData countryEntry =
          new PhoneCountryCodeData(country.getKey(), pphoneCountryNames.get(country.getValue()));
      final Map<String, String> phoneRegionCodes;
      if (StringUtils.equals(country.getKey(), "49")) {
        // to much data in German file, has to be splitted into two separate property files
        phoneRegionCodes = new HashMap<>();
        phoneRegionCodes.putAll(
            CreatePhoneCountryConstantsClass.readPhoneRegionCodes(country.getKey(), plocale));
        phoneRegionCodes.putAll(
            CreatePhoneCountryConstantsClass.readPhoneRegionCodes(country.getKey() + "b", plocale));
      } else {
        phoneRegionCodes =
            CreatePhoneCountryConstantsClass.readPhoneRegionCodes(country.getKey(), plocale);
      }
      for (final Entry<String, String> region : phoneRegionCodes.entrySet()) {
        final PhoneAreaCodeData areaData = new PhoneAreaCodeData(region.getKey(), region.getValue(),
            StringUtils.length(country.getKey()));
        countryEntry.addAreaCodeData(areaData);
      }
      result.add(countryEntry);
    }
    return result;
  }

  protected static Map<String, PhoneCountryData> createMapFromPhoneCountry(final Locale plocale,
      final Set<PhoneCountryCodeData> pcountries, final Map<String, String> pphoneCountryNames,
      final Map<String, String> pphoneCountryCodes) {
    final Map<String, PhoneCountryData> countryPhoneMap = new HashMap<>();
    final Map<String, String> phoneTrunkAndExitCodes =
        CreatePhoneCountryConstantsClass.readPhoneTrunkAndExitCodes(plocale);

    for (final PhoneCountryCodeData entry : pcountries) {
      final String countryCode = pphoneCountryCodes.get(entry.getCountryCode());
      if (countryCode.contains("-")) {
        final String[] splittedCountryCodes = StringUtils.split(countryCode, '-');
        for (final String singleCountryCode : splittedCountryCodes) {
          entry.setPhoneCountryData(addCountryToPhoneMap(pphoneCountryNames, countryPhoneMap,
              phoneTrunkAndExitCodes, entry, singleCountryCode));
        }
      } else {
        entry.setPhoneCountryData(addCountryToPhoneMap(pphoneCountryNames, countryPhoneMap,
            phoneTrunkAndExitCodes, entry, countryCode));
      }
    }
    return countryPhoneMap;
  }

  protected static PhoneCountryData addCountryToPhoneMap(
      final Map<String, String> pphoneCountryNames,
      final Map<String, PhoneCountryData> pcountryPhoneMap,
      final Map<String, String> pphoneTrunkAndExitCodes, final PhoneCountryCodeData pentry,
      final String pcountryCode) {
    final String trunkAndExitCodes = pphoneTrunkAndExitCodes.get(pcountryCode);
    final String countryCodeName = pphoneCountryNames.get(pcountryCode);
    final String[] splittedTrunkAndExitCodes =
        StringUtils.defaultString(trunkAndExitCodes).split(",");
    final String trunkCode =
        splittedTrunkAndExitCodes.length >= 1 ? splittedTrunkAndExitCodes[0] : StringUtils.EMPTY;
    final String exitCode =
        splittedTrunkAndExitCodes.length >= 2 ? splittedTrunkAndExitCodes[1] : StringUtils.EMPTY;
    final boolean areaCodeMustBeFilled =
        splittedTrunkAndExitCodes.length >= 3 ? "1".equals(splittedTrunkAndExitCodes[2]) : false;
    final PhoneCountryData countryData = new PhoneCountryData(pcountryCode, countryCodeName,
        trunkCode, exitCode, areaCodeMustBeFilled, pentry);
    pcountryPhoneMap.put(pcountryCode, countryData);
    return countryData;
  }
}
