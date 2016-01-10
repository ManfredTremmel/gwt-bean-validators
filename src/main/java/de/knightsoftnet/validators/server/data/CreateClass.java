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

package de.knightsoftnet.validators.server.data;

import de.knightsoftnet.validators.shared.data.AbstractCreateClass;
import de.knightsoftnet.validators.shared.data.PhoneCountryConstantsImpl;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Read gwt constants from properties file on server side.
 *
 * @author Manfred Tremmel
 *
 */
public class CreateClass extends AbstractCreateClass {

  private static final String PROPERTY_PACKAGE = "de.knightsoftnet.validators.client.data.";

  private static volatile BicMapConstantsImpl bicMapConstants = null;
  private static volatile IbanLengthMapConstantsImpl ibanLengthMapConstants = null;
  private static volatile PostalCodesMapConstantsImpl postalCodesMapConstants = null;
  private static volatile VatIdMapConstantsImpl vatIdMapConstants = null;
  private static volatile PhoneCountryConstantsImpl phoneCountryConstants = null;

  /**
   * Instantiates a class via deferred binding.
   *
   * <p>
   * The argument to {@link #create(Class)}&#160;<i>must</i> be a class literal because the
   * Production Mode compiler must be able to statically determine the requested type at
   * compile-time. This can be tricky because using a {@link Class} variable may appear to work
   * correctly in Development Mode.
   * </p>
   *
   * @param pclassLiteral a class literal specifying the base class to be instantiated
   * @return the new instance, which must be cast to the requested class
   */
  @SuppressWarnings("unchecked")
  public static <T> T create(final Class<?> pclassLiteral) {
    if (pclassLiteral.equals(de.knightsoftnet.validators.shared.data.BicMapSharedConstants.class)) {
      if (bicMapConstants == null) { // NOPMD it's thread save!
        synchronized (BicMapConstantsImpl.class) {
          if (bicMapConstants == null) {
            bicMapConstants =
                new BicMapConstantsImpl(readMapFromProperties("BicMapConstants", "bics"));
          }
        }
      }
      return (T) bicMapConstants;
    } else if (pclassLiteral
        .equals(de.knightsoftnet.validators.shared.data.IbanLengthMapSharedConstants.class)) {
      if (ibanLengthMapConstants == null) { // NOPMD it's thread save!
        synchronized (IbanLengthMapConstantsImpl.class) {
          if (ibanLengthMapConstants == null) {
            ibanLengthMapConstants = new IbanLengthMapConstantsImpl(
                readMapFromProperties("IbanLengthMapConstants", "ibanLengths"));
          }

        }
      }
      return (T) ibanLengthMapConstants;
    } else if (pclassLiteral
        .equals(de.knightsoftnet.validators.shared.data.PostalCodesMapSharedConstants.class)) {
      if (postalCodesMapConstants == null) { // NOPMD it's thread save!
        synchronized (PostalCodesMapConstantsImpl.class) {
          if (postalCodesMapConstants == null) {
            postalCodesMapConstants = new PostalCodesMapConstantsImpl(
                readMapFromProperties("PostalCodesMapConstants", "postalCodes"));
          }
        }
      }
      return (T) postalCodesMapConstants;
    } else if (pclassLiteral
        .equals(de.knightsoftnet.validators.shared.data.VatIdMapSharedConstants.class)) {
      if (vatIdMapConstants == null) { // NOPMD it's thread save!
        synchronized (VatIdMapConstantsImpl.class) {
          if (vatIdMapConstants == null) {
            vatIdMapConstants =
                new VatIdMapConstantsImpl(readMapFromProperties("VatIdMapConstants", "vatIds"));
          }
        }
      }
      return (T) vatIdMapConstants;
    } else if (pclassLiteral
        .equals(de.knightsoftnet.validators.shared.data.PhoneCountrySharedConstants.class)) {
      if (phoneCountryConstants == null) { // NOPMD it's thread save!
        synchronized (PhoneCountryConstantsImpl.class) {
          if (phoneCountryConstants == null) {
            phoneCountryConstants = createPhoneCountryConstants();
          }
        }
      }
      return (T) phoneCountryConstants;
    }
    return null;
  }

  private static Map<String, String> readMapFromProperties(final String pmapName,
      final String pmapRoot) {
    final ResourceBundle bundle =
        ResourceBundle.getBundle(PROPERTY_PACKAGE + pmapName, Locale.ROOT, new Utf8Control());
    final Map<String, String> map = new HashMap<>();
    final String mapNames = StringUtils.defaultString(bundle.getString(pmapRoot));
    if (StringUtils.isNotEmpty(mapNames)) {
      for (final String key : mapNames.split(",")) {
        map.put(key, bundle.getString(key));
      }
    }
    return map;
  }


  /**
   * read phone country names.
   *
   * @return map of phone country code and name
   */
  public static Map<String, String> readPhoneCountryNames() {
    return readMapFromProperties("PhoneCountryNameConstants", "phoneCountryNames");
  }

  /**
   * read phone country codes and country iso codes.
   *
   * @return map of phone country code and country iso code
   */
  public static Map<String, String> readPhoneCountryCodes() {
    return readMapFromProperties("PhoneCountryCodeConstants", "phoneCountryCodes");
  }

  /**
   * read phone region codes for one country.
   *
   * @return map of area code and area name
   */
  public static Map<String, String> readPhoneRegionCodes(final String pphoneCountryCode) {
    return readMapFromProperties("PhoneRegionCode" + pphoneCountryCode + "Constants",
        "phoneRegionCodes" + pphoneCountryCode);
  }

  /**
   * read phone trunk an exit code map from property file.
   *
   * @return map of country code and combined string of trunk an exit code
   */
  public static Map<String, String> readPhoneTrunkAndExitCodes() {
    return readMapFromProperties("PhoneCountryTrunkAndExitCodesConstants",
        "phoneTrunkAndExitCodes");
  }
}
