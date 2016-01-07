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

import de.knightsoftnet.validators.client.data.BicMapConstants;
import de.knightsoftnet.validators.client.data.IbanLengthMapConstants;
import de.knightsoftnet.validators.client.data.PhoneCountryCodeConstants;
import de.knightsoftnet.validators.client.data.PhoneCountryNameConstants;
import de.knightsoftnet.validators.client.data.PhoneCountryTrunkAndExitCodesConstants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode1Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode20Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode211Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode212Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode213Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode216Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode218Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode220Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode221Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode222Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode223Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode224Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode225Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode226Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode227Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode228Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode229Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode230Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode231Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode232Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode233Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode234Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode235Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode236Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode237Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode238Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode239Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode240Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode241Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode242Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode243Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode244Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode245Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode246Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode247Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode248Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode249Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode250Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode251Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode252Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode253Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode254Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode255Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode256Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode257Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode258Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode260Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode261Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode262Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode263Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode264Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode265Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode266Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode267Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode268Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode269Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode27Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode290Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode291Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode297Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode298Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode299Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode30Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode31Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode32Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode33Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode34Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode350Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode351Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode352Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode353Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode354Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode355Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode356Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode357Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode358Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode359Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode36Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode370Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode371Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode372Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode373Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode374Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode375Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode376Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode377Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode378Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode379Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode380Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode381Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode382Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode383Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode385Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode386Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode387Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode388Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode39Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode40Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode41Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode420Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode421Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode423Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode43Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode44Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode45Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode46Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode47Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode48Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode49Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode49bConstants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode500Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode501Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode502Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode503Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode504Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode505Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode506Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode507Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode508Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode509Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode51Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode52Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode53Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode54Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode55Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode56Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode57Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode58Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode590Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode591Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode592Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode593Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode594Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode595Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode596Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode597Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode598Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode599Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode60Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode61Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode62Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode63Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode64Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode65Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode66Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode670Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode672Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode673Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode674Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode675Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode676Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode677Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode678Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode679Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode680Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode681Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode682Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode683Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode685Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode686Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode687Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode688Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode689Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode690Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode691Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode692Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode7Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode800Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode808Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode81Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode82Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode84Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode850Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode852Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode853Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode855Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode86Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode870Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode875Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode876Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode877Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode878Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode879Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode880Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode881Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode882Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode883Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode886Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode888Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode90Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode91Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode92Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode93Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode94Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode95Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode960Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode961Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode962Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode963Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode964Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode965Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode966Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode967Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode968Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode970Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode971Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode972Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode973Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode974Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode975Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode976Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode977Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode979Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode98Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode991Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode992Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode993Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode994Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode995Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode996Constants;
import de.knightsoftnet.validators.client.data.PhoneRegionCode998Constants;
import de.knightsoftnet.validators.client.data.PostalCodesMapConstants;
import de.knightsoftnet.validators.client.data.VatIdMapConstants;
import de.knightsoftnet.validators.shared.data.BicMapSharedConstants;
import de.knightsoftnet.validators.shared.data.IbanLengthMapSharedConstants;
import de.knightsoftnet.validators.shared.data.PhoneAreaCodeData;
import de.knightsoftnet.validators.shared.data.PhoneCountryCodeData;
import de.knightsoftnet.validators.shared.data.PhoneCountryConstantsImpl;
import de.knightsoftnet.validators.shared.data.PhoneCountryData;
import de.knightsoftnet.validators.shared.data.PostalCodesMapSharedConstants;
import de.knightsoftnet.validators.shared.data.VatIdMapSharedConstants;

import com.google.gwt.core.client.GWT;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

/**
 * Read gwt constants from properties file on client side.
 *
 * @author Manfred Tremmel
 *
 */
public class CreateClass {

  private static volatile BicMapConstants bicMapConstants = null;
  private static volatile IbanLengthMapConstants ibanLengthMapConstants = null;
  private static volatile PostalCodesMapConstants postalCodesMapConstants = null;
  private static volatile VatIdMapConstants vatIdMapConstants = null;
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
    if (pclassLiteral.equals(BicMapSharedConstants.class)) {
      if (bicMapConstants == null) { // NOPMD it's thread save!
        synchronized (BicMapConstants.class) {
          if (bicMapConstants == null) {
            bicMapConstants = GWT.create(BicMapConstants.class);
          }
        }
      }
      return (T) bicMapConstants;
    } else if (pclassLiteral.equals(IbanLengthMapSharedConstants.class)) {
      if (ibanLengthMapConstants == null) { // NOPMD it's thread save!
        synchronized (IbanLengthMapConstants.class) {
          if (ibanLengthMapConstants == null) {
            ibanLengthMapConstants = GWT.create(IbanLengthMapConstants.class);
          }
        }
      }
      return (T) ibanLengthMapConstants;
    } else if (pclassLiteral.equals(PostalCodesMapSharedConstants.class)) {
      if (postalCodesMapConstants == null) { // NOPMD it's thread save!
        synchronized (PostalCodesMapConstants.class) {
          if (postalCodesMapConstants == null) {
            postalCodesMapConstants = GWT.create(PostalCodesMapConstants.class);
          }
        }
      }
      return (T) postalCodesMapConstants;
    } else if (pclassLiteral.equals(VatIdMapSharedConstants.class)) {
      if (vatIdMapConstants == null) { // NOPMD it's thread save!
        synchronized (VatIdMapConstants.class) {
          if (vatIdMapConstants == null) {
            vatIdMapConstants = GWT.create(VatIdMapConstants.class);
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


  private static PhoneCountryConstantsImpl createPhoneCountryConstants() {
    final PhoneCountryNameConstants phoneCountryNames = GWT.create(PhoneCountryNameConstants.class);
    final PhoneCountryCodeConstants phoneCountryCodes = GWT.create(PhoneCountryCodeConstants.class);
    final Set<PhoneCountryCodeData> countryCodeData = readPhoneCountryProperties(
        phoneCountryNames.phoneCountryNames(), phoneCountryCodes.phoneCountryCodes());
    return new PhoneCountryConstantsImpl(countryCodeData, createMapFromPhoneCountry(countryCodeData,
        phoneCountryNames.phoneCountryNames(), phoneCountryCodes.phoneCountryCodes()));
  }

  private static Set<PhoneCountryCodeData> readPhoneCountryProperties(
      final Map<String, String> pphoneCountryNames, final Map<String, String> pphoneCountryCodes) {
    final Set<PhoneCountryCodeData> result = new TreeSet<>();
    for (final Entry<String, String> country : pphoneCountryCodes.entrySet()) {
      final PhoneCountryCodeData countryEntry =
          new PhoneCountryCodeData(country.getKey(), pphoneCountryNames.get(country.getValue()));
      final Map<String, String> phoneRegionCodes;
      switch (country.getKey()) {
        case "1":
          final PhoneRegionCode1Constants regionCodes1 =
              GWT.create(PhoneRegionCode1Constants.class);
          phoneRegionCodes = regionCodes1.phoneRegionCodes1();
          break;
        case "20":
          final PhoneRegionCode20Constants regionCodes20 =
              GWT.create(PhoneRegionCode20Constants.class);
          phoneRegionCodes = regionCodes20.phoneRegionCodes20();
          break;
        case "211":
          final PhoneRegionCode211Constants regionCodes211 =
              GWT.create(PhoneRegionCode211Constants.class);
          phoneRegionCodes = regionCodes211.phoneRegionCodes211();
          break;
        case "212":
          final PhoneRegionCode212Constants regionCodes212 =
              GWT.create(PhoneRegionCode212Constants.class);
          phoneRegionCodes = regionCodes212.phoneRegionCodes212();
          break;
        case "213":
          final PhoneRegionCode213Constants regionCodes213 =
              GWT.create(PhoneRegionCode213Constants.class);
          phoneRegionCodes = regionCodes213.phoneRegionCodes213();
          break;
        case "216":
          final PhoneRegionCode216Constants regionCodes216 =
              GWT.create(PhoneRegionCode216Constants.class);
          phoneRegionCodes = regionCodes216.phoneRegionCodes216();
          break;
        case "218":
          final PhoneRegionCode218Constants regionCodes218 =
              GWT.create(PhoneRegionCode218Constants.class);
          phoneRegionCodes = regionCodes218.phoneRegionCodes218();
          break;
        case "220":
          final PhoneRegionCode220Constants regionCodes220 =
              GWT.create(PhoneRegionCode220Constants.class);
          phoneRegionCodes = regionCodes220.phoneRegionCodes220();
          break;
        case "221":
          final PhoneRegionCode221Constants regionCodes221 =
              GWT.create(PhoneRegionCode221Constants.class);
          phoneRegionCodes = regionCodes221.phoneRegionCodes221();
          break;
        case "222":
          final PhoneRegionCode222Constants regionCodes222 =
              GWT.create(PhoneRegionCode222Constants.class);
          phoneRegionCodes = regionCodes222.phoneRegionCodes222();
          break;
        case "223":
          final PhoneRegionCode223Constants regionCodes223 =
              GWT.create(PhoneRegionCode223Constants.class);
          phoneRegionCodes = regionCodes223.phoneRegionCodes223();
          break;
        case "224":
          final PhoneRegionCode224Constants regionCodes224 =
              GWT.create(PhoneRegionCode224Constants.class);
          phoneRegionCodes = regionCodes224.phoneRegionCodes224();
          break;
        case "225":
          final PhoneRegionCode225Constants regionCodes225 =
              GWT.create(PhoneRegionCode225Constants.class);
          phoneRegionCodes = regionCodes225.phoneRegionCodes225();
          break;
        case "226":
          final PhoneRegionCode226Constants regionCodes226 =
              GWT.create(PhoneRegionCode226Constants.class);
          phoneRegionCodes = regionCodes226.phoneRegionCodes226();
          break;
        case "227":
          final PhoneRegionCode227Constants regionCodes227 =
              GWT.create(PhoneRegionCode227Constants.class);
          phoneRegionCodes = regionCodes227.phoneRegionCodes227();
          break;
        case "228":
          final PhoneRegionCode228Constants regionCodes228 =
              GWT.create(PhoneRegionCode228Constants.class);
          phoneRegionCodes = regionCodes228.phoneRegionCodes228();
          break;
        case "229":
          final PhoneRegionCode229Constants regionCodes229 =
              GWT.create(PhoneRegionCode229Constants.class);
          phoneRegionCodes = regionCodes229.phoneRegionCodes229();
          break;
        case "230":
          final PhoneRegionCode230Constants regionCodes230 =
              GWT.create(PhoneRegionCode230Constants.class);
          phoneRegionCodes = regionCodes230.phoneRegionCodes230();
          break;
        case "231":
          final PhoneRegionCode231Constants regionCodes231 =
              GWT.create(PhoneRegionCode231Constants.class);
          phoneRegionCodes = regionCodes231.phoneRegionCodes231();
          break;
        case "232":
          final PhoneRegionCode232Constants regionCodes232 =
              GWT.create(PhoneRegionCode232Constants.class);
          phoneRegionCodes = regionCodes232.phoneRegionCodes232();
          break;
        case "233":
          final PhoneRegionCode233Constants regionCodes233 =
              GWT.create(PhoneRegionCode233Constants.class);
          phoneRegionCodes = regionCodes233.phoneRegionCodes233();
          break;
        case "234":
          final PhoneRegionCode234Constants regionCodes234 =
              GWT.create(PhoneRegionCode234Constants.class);
          phoneRegionCodes = regionCodes234.phoneRegionCodes234();
          break;
        case "235":
          final PhoneRegionCode235Constants regionCodes235 =
              GWT.create(PhoneRegionCode235Constants.class);
          phoneRegionCodes = regionCodes235.phoneRegionCodes235();
          break;
        case "236":
          final PhoneRegionCode236Constants regionCodes236 =
              GWT.create(PhoneRegionCode236Constants.class);
          phoneRegionCodes = regionCodes236.phoneRegionCodes236();
          break;
        case "237":
          final PhoneRegionCode237Constants regionCodes237 =
              GWT.create(PhoneRegionCode237Constants.class);
          phoneRegionCodes = regionCodes237.phoneRegionCodes237();
          break;
        case "238":
          final PhoneRegionCode238Constants regionCodes238 =
              GWT.create(PhoneRegionCode238Constants.class);
          phoneRegionCodes = regionCodes238.phoneRegionCodes238();
          break;
        case "239":
          final PhoneRegionCode239Constants regionCodes239 =
              GWT.create(PhoneRegionCode239Constants.class);
          phoneRegionCodes = regionCodes239.phoneRegionCodes239();
          break;
        case "240":
          final PhoneRegionCode240Constants regionCodes240 =
              GWT.create(PhoneRegionCode240Constants.class);
          phoneRegionCodes = regionCodes240.phoneRegionCodes240();
          break;
        case "241":
          final PhoneRegionCode241Constants regionCodes241 =
              GWT.create(PhoneRegionCode241Constants.class);
          phoneRegionCodes = regionCodes241.phoneRegionCodes241();
          break;
        case "242":
          final PhoneRegionCode242Constants regionCodes242 =
              GWT.create(PhoneRegionCode242Constants.class);
          phoneRegionCodes = regionCodes242.phoneRegionCodes242();
          break;
        case "243":
          final PhoneRegionCode243Constants regionCodes243 =
              GWT.create(PhoneRegionCode243Constants.class);
          phoneRegionCodes = regionCodes243.phoneRegionCodes243();
          break;
        case "244":
          final PhoneRegionCode244Constants regionCodes244 =
              GWT.create(PhoneRegionCode244Constants.class);
          phoneRegionCodes = regionCodes244.phoneRegionCodes244();
          break;
        case "245":
          final PhoneRegionCode245Constants regionCodes245 =
              GWT.create(PhoneRegionCode245Constants.class);
          phoneRegionCodes = regionCodes245.phoneRegionCodes245();
          break;
        case "246":
          final PhoneRegionCode246Constants regionCodes246 =
              GWT.create(PhoneRegionCode246Constants.class);
          phoneRegionCodes = regionCodes246.phoneRegionCodes246();
          break;
        case "247":
          final PhoneRegionCode247Constants regionCodes247 =
              GWT.create(PhoneRegionCode247Constants.class);
          phoneRegionCodes = regionCodes247.phoneRegionCodes247();
          break;
        case "248":
          final PhoneRegionCode248Constants regionCodes248 =
              GWT.create(PhoneRegionCode248Constants.class);
          phoneRegionCodes = regionCodes248.phoneRegionCodes248();
          break;
        case "249":
          final PhoneRegionCode249Constants regionCodes249 =
              GWT.create(PhoneRegionCode249Constants.class);
          phoneRegionCodes = regionCodes249.phoneRegionCodes249();
          break;
        case "250":
          final PhoneRegionCode250Constants regionCodes250 =
              GWT.create(PhoneRegionCode250Constants.class);
          phoneRegionCodes = regionCodes250.phoneRegionCodes250();
          break;
        case "251":
          final PhoneRegionCode251Constants regionCodes251 =
              GWT.create(PhoneRegionCode251Constants.class);
          phoneRegionCodes = regionCodes251.phoneRegionCodes251();
          break;
        case "252":
          final PhoneRegionCode252Constants regionCodes252 =
              GWT.create(PhoneRegionCode252Constants.class);
          phoneRegionCodes = regionCodes252.phoneRegionCodes252();
          break;
        case "253":
          final PhoneRegionCode253Constants regionCodes253 =
              GWT.create(PhoneRegionCode253Constants.class);
          phoneRegionCodes = regionCodes253.phoneRegionCodes253();
          break;
        case "254":
          final PhoneRegionCode254Constants regionCodes254 =
              GWT.create(PhoneRegionCode254Constants.class);
          phoneRegionCodes = regionCodes254.phoneRegionCodes254();
          break;
        case "255":
          final PhoneRegionCode255Constants regionCodes255 =
              GWT.create(PhoneRegionCode255Constants.class);
          phoneRegionCodes = regionCodes255.phoneRegionCodes255();
          break;
        case "256":
          final PhoneRegionCode256Constants regionCodes256 =
              GWT.create(PhoneRegionCode256Constants.class);
          phoneRegionCodes = regionCodes256.phoneRegionCodes256();
          break;
        case "257":
          final PhoneRegionCode257Constants regionCodes257 =
              GWT.create(PhoneRegionCode257Constants.class);
          phoneRegionCodes = regionCodes257.phoneRegionCodes257();
          break;
        case "258":
          final PhoneRegionCode258Constants regionCodes258 =
              GWT.create(PhoneRegionCode258Constants.class);
          phoneRegionCodes = regionCodes258.phoneRegionCodes258();
          break;
        case "260":
          final PhoneRegionCode260Constants regionCodes260 =
              GWT.create(PhoneRegionCode260Constants.class);
          phoneRegionCodes = regionCodes260.phoneRegionCodes260();
          break;
        case "261":
          final PhoneRegionCode261Constants regionCodes261 =
              GWT.create(PhoneRegionCode261Constants.class);
          phoneRegionCodes = regionCodes261.phoneRegionCodes261();
          break;
        case "262":
          final PhoneRegionCode262Constants regionCodes262 =
              GWT.create(PhoneRegionCode262Constants.class);
          phoneRegionCodes = regionCodes262.phoneRegionCodes262();
          break;
        case "263":
          final PhoneRegionCode263Constants regionCodes263 =
              GWT.create(PhoneRegionCode263Constants.class);
          phoneRegionCodes = regionCodes263.phoneRegionCodes263();
          break;
        case "264":
          final PhoneRegionCode264Constants regionCodes264 =
              GWT.create(PhoneRegionCode264Constants.class);
          phoneRegionCodes = regionCodes264.phoneRegionCodes264();
          break;
        case "265":
          final PhoneRegionCode265Constants regionCodes265 =
              GWT.create(PhoneRegionCode265Constants.class);
          phoneRegionCodes = regionCodes265.phoneRegionCodes265();
          break;
        case "266":
          final PhoneRegionCode266Constants regionCodes266 =
              GWT.create(PhoneRegionCode266Constants.class);
          phoneRegionCodes = regionCodes266.phoneRegionCodes266();
          break;
        case "267":
          final PhoneRegionCode267Constants regionCodes267 =
              GWT.create(PhoneRegionCode267Constants.class);
          phoneRegionCodes = regionCodes267.phoneRegionCodes267();
          break;
        case "268":
          final PhoneRegionCode268Constants regionCodes268 =
              GWT.create(PhoneRegionCode268Constants.class);
          phoneRegionCodes = regionCodes268.phoneRegionCodes268();
          break;
        case "269":
          final PhoneRegionCode269Constants regionCodes269 =
              GWT.create(PhoneRegionCode269Constants.class);
          phoneRegionCodes = regionCodes269.phoneRegionCodes269();
          break;
        case "27":
          final PhoneRegionCode27Constants regionCodes27 =
              GWT.create(PhoneRegionCode27Constants.class);
          phoneRegionCodes = regionCodes27.phoneRegionCodes27();
          break;
        case "290":
          final PhoneRegionCode290Constants regionCodes290 =
              GWT.create(PhoneRegionCode290Constants.class);
          phoneRegionCodes = regionCodes290.phoneRegionCodes290();
          break;
        case "291":
          final PhoneRegionCode291Constants regionCodes291 =
              GWT.create(PhoneRegionCode291Constants.class);
          phoneRegionCodes = regionCodes291.phoneRegionCodes291();
          break;
        case "297":
          final PhoneRegionCode297Constants regionCodes297 =
              GWT.create(PhoneRegionCode297Constants.class);
          phoneRegionCodes = regionCodes297.phoneRegionCodes297();
          break;
        case "298":
          final PhoneRegionCode298Constants regionCodes298 =
              GWT.create(PhoneRegionCode298Constants.class);
          phoneRegionCodes = regionCodes298.phoneRegionCodes298();
          break;
        case "299":
          final PhoneRegionCode299Constants regionCodes299 =
              GWT.create(PhoneRegionCode299Constants.class);
          phoneRegionCodes = regionCodes299.phoneRegionCodes299();
          break;
        case "30":
          final PhoneRegionCode30Constants regionCodes30 =
              GWT.create(PhoneRegionCode30Constants.class);
          phoneRegionCodes = regionCodes30.phoneRegionCodes30();
          break;
        case "31":
          final PhoneRegionCode31Constants regionCodes31 =
              GWT.create(PhoneRegionCode31Constants.class);
          phoneRegionCodes = regionCodes31.phoneRegionCodes31();
          break;
        case "32":
          final PhoneRegionCode32Constants regionCodes32 =
              GWT.create(PhoneRegionCode32Constants.class);
          phoneRegionCodes = regionCodes32.phoneRegionCodes32();
          break;
        case "33":
          final PhoneRegionCode33Constants regionCodes33 =
              GWT.create(PhoneRegionCode33Constants.class);
          phoneRegionCodes = regionCodes33.phoneRegionCodes33();
          break;
        case "34":
          final PhoneRegionCode34Constants regionCodes34 =
              GWT.create(PhoneRegionCode34Constants.class);
          phoneRegionCodes = regionCodes34.phoneRegionCodes34();
          break;
        case "350":
          final PhoneRegionCode350Constants regionCodes350 =
              GWT.create(PhoneRegionCode350Constants.class);
          phoneRegionCodes = regionCodes350.phoneRegionCodes350();
          break;
        case "351":
          final PhoneRegionCode351Constants regionCodes351 =
              GWT.create(PhoneRegionCode351Constants.class);
          phoneRegionCodes = regionCodes351.phoneRegionCodes351();
          break;
        case "352":
          final PhoneRegionCode352Constants regionCodes352 =
              GWT.create(PhoneRegionCode352Constants.class);
          phoneRegionCodes = regionCodes352.phoneRegionCodes352();
          break;
        case "353":
          final PhoneRegionCode353Constants regionCodes353 =
              GWT.create(PhoneRegionCode353Constants.class);
          phoneRegionCodes = regionCodes353.phoneRegionCodes353();
          break;
        case "354":
          final PhoneRegionCode354Constants regionCodes354 =
              GWT.create(PhoneRegionCode354Constants.class);
          phoneRegionCodes = regionCodes354.phoneRegionCodes354();
          break;
        case "355":
          final PhoneRegionCode355Constants regionCodes355 =
              GWT.create(PhoneRegionCode355Constants.class);
          phoneRegionCodes = regionCodes355.phoneRegionCodes355();
          break;
        case "356":
          final PhoneRegionCode356Constants regionCodes356 =
              GWT.create(PhoneRegionCode356Constants.class);
          phoneRegionCodes = regionCodes356.phoneRegionCodes356();
          break;
        case "357":
          final PhoneRegionCode357Constants regionCodes357 =
              GWT.create(PhoneRegionCode357Constants.class);
          phoneRegionCodes = regionCodes357.phoneRegionCodes357();
          break;
        case "358":
          final PhoneRegionCode358Constants regionCodes358 =
              GWT.create(PhoneRegionCode358Constants.class);
          phoneRegionCodes = regionCodes358.phoneRegionCodes358();
          break;
        case "359":
          final PhoneRegionCode359Constants regionCodes359 =
              GWT.create(PhoneRegionCode359Constants.class);
          phoneRegionCodes = regionCodes359.phoneRegionCodes359();
          break;
        case "36":
          final PhoneRegionCode36Constants regionCodes36 =
              GWT.create(PhoneRegionCode36Constants.class);
          phoneRegionCodes = regionCodes36.phoneRegionCodes36();
          break;
        case "370":
          final PhoneRegionCode370Constants regionCodes370 =
              GWT.create(PhoneRegionCode370Constants.class);
          phoneRegionCodes = regionCodes370.phoneRegionCodes370();
          break;
        case "371":
          final PhoneRegionCode371Constants regionCodes371 =
              GWT.create(PhoneRegionCode371Constants.class);
          phoneRegionCodes = regionCodes371.phoneRegionCodes371();
          break;
        case "372":
          final PhoneRegionCode372Constants regionCodes372 =
              GWT.create(PhoneRegionCode372Constants.class);
          phoneRegionCodes = regionCodes372.phoneRegionCodes372();
          break;
        case "373":
          final PhoneRegionCode373Constants regionCodes373 =
              GWT.create(PhoneRegionCode373Constants.class);
          phoneRegionCodes = regionCodes373.phoneRegionCodes373();
          break;
        case "374":
          final PhoneRegionCode374Constants regionCodes374 =
              GWT.create(PhoneRegionCode374Constants.class);
          phoneRegionCodes = regionCodes374.phoneRegionCodes374();
          break;
        case "375":
          final PhoneRegionCode375Constants regionCodes375 =
              GWT.create(PhoneRegionCode375Constants.class);
          phoneRegionCodes = regionCodes375.phoneRegionCodes375();
          break;
        case "376":
          final PhoneRegionCode376Constants regionCodes376 =
              GWT.create(PhoneRegionCode376Constants.class);
          phoneRegionCodes = regionCodes376.phoneRegionCodes376();
          break;
        case "377":
          final PhoneRegionCode377Constants regionCodes377 =
              GWT.create(PhoneRegionCode377Constants.class);
          phoneRegionCodes = regionCodes377.phoneRegionCodes377();
          break;
        case "378":
          final PhoneRegionCode378Constants regionCodes378 =
              GWT.create(PhoneRegionCode378Constants.class);
          phoneRegionCodes = regionCodes378.phoneRegionCodes378();
          break;
        case "379":
          final PhoneRegionCode379Constants regionCodes379 =
              GWT.create(PhoneRegionCode379Constants.class);
          phoneRegionCodes = regionCodes379.phoneRegionCodes379();
          break;
        case "380":
          final PhoneRegionCode380Constants regionCodes380 =
              GWT.create(PhoneRegionCode380Constants.class);
          phoneRegionCodes = regionCodes380.phoneRegionCodes380();
          break;
        case "381":
          final PhoneRegionCode381Constants regionCodes381 =
              GWT.create(PhoneRegionCode381Constants.class);
          phoneRegionCodes = regionCodes381.phoneRegionCodes381();
          break;
        case "382":
          final PhoneRegionCode382Constants regionCodes382 =
              GWT.create(PhoneRegionCode382Constants.class);
          phoneRegionCodes = regionCodes382.phoneRegionCodes382();
          break;
        case "383":
          final PhoneRegionCode383Constants regionCodes383 =
              GWT.create(PhoneRegionCode383Constants.class);
          phoneRegionCodes = regionCodes383.phoneRegionCodes383();
          break;
        case "385":
          final PhoneRegionCode385Constants regionCodes385 =
              GWT.create(PhoneRegionCode385Constants.class);
          phoneRegionCodes = regionCodes385.phoneRegionCodes385();
          break;
        case "386":
          final PhoneRegionCode386Constants regionCodes386 =
              GWT.create(PhoneRegionCode386Constants.class);
          phoneRegionCodes = regionCodes386.phoneRegionCodes386();
          break;
        case "387":
          final PhoneRegionCode387Constants regionCodes387 =
              GWT.create(PhoneRegionCode387Constants.class);
          phoneRegionCodes = regionCodes387.phoneRegionCodes387();
          break;
        case "388":
          final PhoneRegionCode388Constants regionCodes388 =
              GWT.create(PhoneRegionCode388Constants.class);
          phoneRegionCodes = regionCodes388.phoneRegionCodes388();
          break;
        case "39":
          final PhoneRegionCode39Constants regionCodes39 =
              GWT.create(PhoneRegionCode39Constants.class);
          phoneRegionCodes = regionCodes39.phoneRegionCodes39();
          break;
        case "40":
          final PhoneRegionCode40Constants regionCodes40 =
              GWT.create(PhoneRegionCode40Constants.class);
          phoneRegionCodes = regionCodes40.phoneRegionCodes40();
          break;
        case "41":
          final PhoneRegionCode41Constants regionCodes41 =
              GWT.create(PhoneRegionCode41Constants.class);
          phoneRegionCodes = regionCodes41.phoneRegionCodes41();
          break;
        case "420":
          final PhoneRegionCode420Constants regionCodes420 =
              GWT.create(PhoneRegionCode420Constants.class);
          phoneRegionCodes = regionCodes420.phoneRegionCodes420();
          break;
        case "421":
          final PhoneRegionCode421Constants regionCodes421 =
              GWT.create(PhoneRegionCode421Constants.class);
          phoneRegionCodes = regionCodes421.phoneRegionCodes421();
          break;
        case "423":
          final PhoneRegionCode423Constants regionCodes423 =
              GWT.create(PhoneRegionCode423Constants.class);
          phoneRegionCodes = regionCodes423.phoneRegionCodes423();
          break;
        case "43":
          final PhoneRegionCode43Constants regionCodes43 =
              GWT.create(PhoneRegionCode43Constants.class);
          phoneRegionCodes = regionCodes43.phoneRegionCodes43();
          break;
        case "44":
          final PhoneRegionCode44Constants regionCodes44 =
              GWT.create(PhoneRegionCode44Constants.class);
          phoneRegionCodes = regionCodes44.phoneRegionCodes44();
          break;
        case "45":
          final PhoneRegionCode45Constants regionCodes45 =
              GWT.create(PhoneRegionCode45Constants.class);
          phoneRegionCodes = regionCodes45.phoneRegionCodes45();
          break;
        case "46":
          final PhoneRegionCode46Constants regionCodes46 =
              GWT.create(PhoneRegionCode46Constants.class);
          phoneRegionCodes = regionCodes46.phoneRegionCodes46();
          break;
        case "47":
          final PhoneRegionCode47Constants regionCodes47 =
              GWT.create(PhoneRegionCode47Constants.class);
          phoneRegionCodes = regionCodes47.phoneRegionCodes47();
          break;
        case "48":
          final PhoneRegionCode48Constants regionCodes48 =
              GWT.create(PhoneRegionCode48Constants.class);
          phoneRegionCodes = regionCodes48.phoneRegionCodes48();
          break;
        case "49":
          final PhoneRegionCode49Constants regionCodes49a =
              GWT.create(PhoneRegionCode49Constants.class);
          final PhoneRegionCode49bConstants regionCodes49b =
              GWT.create(PhoneRegionCode49bConstants.class);
          phoneRegionCodes = new HashMap<>(regionCodes49a.phoneRegionCodes49().size()
              + regionCodes49b.phoneRegionCodes49b().size());
          phoneRegionCodes.putAll(regionCodes49a.phoneRegionCodes49());
          phoneRegionCodes.putAll(regionCodes49b.phoneRegionCodes49b());
          break;
        case "500":
          final PhoneRegionCode500Constants regionCodes500 =
              GWT.create(PhoneRegionCode500Constants.class);
          phoneRegionCodes = regionCodes500.phoneRegionCodes500();
          break;
        case "501":
          final PhoneRegionCode501Constants regionCodes501 =
              GWT.create(PhoneRegionCode501Constants.class);
          phoneRegionCodes = regionCodes501.phoneRegionCodes501();
          break;
        case "502":
          final PhoneRegionCode502Constants regionCodes502 =
              GWT.create(PhoneRegionCode502Constants.class);
          phoneRegionCodes = regionCodes502.phoneRegionCodes502();
          break;
        case "503":
          final PhoneRegionCode503Constants regionCodes503 =
              GWT.create(PhoneRegionCode503Constants.class);
          phoneRegionCodes = regionCodes503.phoneRegionCodes503();
          break;
        case "504":
          final PhoneRegionCode504Constants regionCodes504 =
              GWT.create(PhoneRegionCode504Constants.class);
          phoneRegionCodes = regionCodes504.phoneRegionCodes504();
          break;
        case "505":
          final PhoneRegionCode505Constants regionCodes505 =
              GWT.create(PhoneRegionCode505Constants.class);
          phoneRegionCodes = regionCodes505.phoneRegionCodes505();
          break;
        case "506":
          final PhoneRegionCode506Constants regionCodes506 =
              GWT.create(PhoneRegionCode506Constants.class);
          phoneRegionCodes = regionCodes506.phoneRegionCodes506();
          break;
        case "507":
          final PhoneRegionCode507Constants regionCodes507 =
              GWT.create(PhoneRegionCode507Constants.class);
          phoneRegionCodes = regionCodes507.phoneRegionCodes507();
          break;
        case "508":
          final PhoneRegionCode508Constants regionCodes508 =
              GWT.create(PhoneRegionCode508Constants.class);
          phoneRegionCodes = regionCodes508.phoneRegionCodes508();
          break;
        case "509":
          final PhoneRegionCode509Constants regionCodes509 =
              GWT.create(PhoneRegionCode509Constants.class);
          phoneRegionCodes = regionCodes509.phoneRegionCodes509();
          break;
        case "51":
          final PhoneRegionCode51Constants regionCodes51 =
              GWT.create(PhoneRegionCode51Constants.class);
          phoneRegionCodes = regionCodes51.phoneRegionCodes51();
          break;
        case "52":
          final PhoneRegionCode52Constants regionCodes52 =
              GWT.create(PhoneRegionCode52Constants.class);
          phoneRegionCodes = regionCodes52.phoneRegionCodes52();
          break;
        case "53":
          final PhoneRegionCode53Constants regionCodes53 =
              GWT.create(PhoneRegionCode53Constants.class);
          phoneRegionCodes = regionCodes53.phoneRegionCodes53();
          break;
        case "54":
          final PhoneRegionCode54Constants regionCodes54 =
              GWT.create(PhoneRegionCode54Constants.class);
          phoneRegionCodes = regionCodes54.phoneRegionCodes54();
          break;
        case "55":
          final PhoneRegionCode55Constants regionCodes55 =
              GWT.create(PhoneRegionCode55Constants.class);
          phoneRegionCodes = regionCodes55.phoneRegionCodes55();
          break;
        case "56":
          final PhoneRegionCode56Constants regionCodes56 =
              GWT.create(PhoneRegionCode56Constants.class);
          phoneRegionCodes = regionCodes56.phoneRegionCodes56();
          break;
        case "57":
          final PhoneRegionCode57Constants regionCodes57 =
              GWT.create(PhoneRegionCode57Constants.class);
          phoneRegionCodes = regionCodes57.phoneRegionCodes57();
          break;
        case "58":
          final PhoneRegionCode58Constants regionCodes58 =
              GWT.create(PhoneRegionCode58Constants.class);
          phoneRegionCodes = regionCodes58.phoneRegionCodes58();
          break;
        case "590":
          final PhoneRegionCode590Constants regionCodes590 =
              GWT.create(PhoneRegionCode590Constants.class);
          phoneRegionCodes = regionCodes590.phoneRegionCodes590();
          break;
        case "591":
          final PhoneRegionCode591Constants regionCodes591 =
              GWT.create(PhoneRegionCode591Constants.class);
          phoneRegionCodes = regionCodes591.phoneRegionCodes591();
          break;
        case "592":
          final PhoneRegionCode592Constants regionCodes592 =
              GWT.create(PhoneRegionCode592Constants.class);
          phoneRegionCodes = regionCodes592.phoneRegionCodes592();
          break;
        case "593":
          final PhoneRegionCode593Constants regionCodes593 =
              GWT.create(PhoneRegionCode593Constants.class);
          phoneRegionCodes = regionCodes593.phoneRegionCodes593();
          break;
        case "594":
          final PhoneRegionCode594Constants regionCodes594 =
              GWT.create(PhoneRegionCode594Constants.class);
          phoneRegionCodes = regionCodes594.phoneRegionCodes594();
          break;
        case "595":
          final PhoneRegionCode595Constants regionCodes595 =
              GWT.create(PhoneRegionCode595Constants.class);
          phoneRegionCodes = regionCodes595.phoneRegionCodes595();
          break;
        case "596":
          final PhoneRegionCode596Constants regionCodes596 =
              GWT.create(PhoneRegionCode596Constants.class);
          phoneRegionCodes = regionCodes596.phoneRegionCodes596();
          break;
        case "597":
          final PhoneRegionCode597Constants regionCodes597 =
              GWT.create(PhoneRegionCode597Constants.class);
          phoneRegionCodes = regionCodes597.phoneRegionCodes597();
          break;
        case "598":
          final PhoneRegionCode598Constants regionCodes598 =
              GWT.create(PhoneRegionCode598Constants.class);
          phoneRegionCodes = regionCodes598.phoneRegionCodes598();
          break;
        case "599":
          final PhoneRegionCode599Constants regionCodes599 =
              GWT.create(PhoneRegionCode599Constants.class);
          phoneRegionCodes = regionCodes599.phoneRegionCodes599();
          break;
        case "60":
          final PhoneRegionCode60Constants regionCodes60 =
              GWT.create(PhoneRegionCode60Constants.class);
          phoneRegionCodes = regionCodes60.phoneRegionCodes60();
          break;
        case "61":
          final PhoneRegionCode61Constants regionCodes61 =
              GWT.create(PhoneRegionCode61Constants.class);
          phoneRegionCodes = regionCodes61.phoneRegionCodes61();
          break;
        case "62":
          final PhoneRegionCode62Constants regionCodes62 =
              GWT.create(PhoneRegionCode62Constants.class);
          phoneRegionCodes = regionCodes62.phoneRegionCodes62();
          break;
        case "63":
          final PhoneRegionCode63Constants regionCodes63 =
              GWT.create(PhoneRegionCode63Constants.class);
          phoneRegionCodes = regionCodes63.phoneRegionCodes63();
          break;
        case "64":
          final PhoneRegionCode64Constants regionCodes64 =
              GWT.create(PhoneRegionCode64Constants.class);
          phoneRegionCodes = regionCodes64.phoneRegionCodes64();
          break;
        case "65":
          final PhoneRegionCode65Constants regionCodes65 =
              GWT.create(PhoneRegionCode65Constants.class);
          phoneRegionCodes = regionCodes65.phoneRegionCodes65();
          break;
        case "66":
          final PhoneRegionCode66Constants regionCodes66 =
              GWT.create(PhoneRegionCode66Constants.class);
          phoneRegionCodes = regionCodes66.phoneRegionCodes66();
          break;
        case "670":
          final PhoneRegionCode670Constants regionCodes670 =
              GWT.create(PhoneRegionCode670Constants.class);
          phoneRegionCodes = regionCodes670.phoneRegionCodes670();
          break;
        case "672":
          final PhoneRegionCode672Constants regionCodes672 =
              GWT.create(PhoneRegionCode672Constants.class);
          phoneRegionCodes = regionCodes672.phoneRegionCodes672();
          break;
        case "673":
          final PhoneRegionCode673Constants regionCodes673 =
              GWT.create(PhoneRegionCode673Constants.class);
          phoneRegionCodes = regionCodes673.phoneRegionCodes673();
          break;
        case "674":
          final PhoneRegionCode674Constants regionCodes674 =
              GWT.create(PhoneRegionCode674Constants.class);
          phoneRegionCodes = regionCodes674.phoneRegionCodes674();
          break;
        case "675":
          final PhoneRegionCode675Constants regionCodes675 =
              GWT.create(PhoneRegionCode675Constants.class);
          phoneRegionCodes = regionCodes675.phoneRegionCodes675();
          break;
        case "676":
          final PhoneRegionCode676Constants regionCodes676 =
              GWT.create(PhoneRegionCode676Constants.class);
          phoneRegionCodes = regionCodes676.phoneRegionCodes676();
          break;
        case "677":
          final PhoneRegionCode677Constants regionCodes677 =
              GWT.create(PhoneRegionCode677Constants.class);
          phoneRegionCodes = regionCodes677.phoneRegionCodes677();
          break;
        case "678":
          final PhoneRegionCode678Constants regionCodes678 =
              GWT.create(PhoneRegionCode678Constants.class);
          phoneRegionCodes = regionCodes678.phoneRegionCodes678();
          break;
        case "679":
          final PhoneRegionCode679Constants regionCodes679 =
              GWT.create(PhoneRegionCode679Constants.class);
          phoneRegionCodes = regionCodes679.phoneRegionCodes679();
          break;
        case "680":
          final PhoneRegionCode680Constants regionCodes680 =
              GWT.create(PhoneRegionCode680Constants.class);
          phoneRegionCodes = regionCodes680.phoneRegionCodes680();
          break;
        case "681":
          final PhoneRegionCode681Constants regionCodes681 =
              GWT.create(PhoneRegionCode681Constants.class);
          phoneRegionCodes = regionCodes681.phoneRegionCodes681();
          break;
        case "682":
          final PhoneRegionCode682Constants regionCodes682 =
              GWT.create(PhoneRegionCode682Constants.class);
          phoneRegionCodes = regionCodes682.phoneRegionCodes682();
          break;
        case "683":
          final PhoneRegionCode683Constants regionCodes683 =
              GWT.create(PhoneRegionCode683Constants.class);
          phoneRegionCodes = regionCodes683.phoneRegionCodes683();
          break;
        case "685":
          final PhoneRegionCode685Constants regionCodes685 =
              GWT.create(PhoneRegionCode685Constants.class);
          phoneRegionCodes = regionCodes685.phoneRegionCodes685();
          break;
        case "686":
          final PhoneRegionCode686Constants regionCodes686 =
              GWT.create(PhoneRegionCode686Constants.class);
          phoneRegionCodes = regionCodes686.phoneRegionCodes686();
          break;
        case "687":
          final PhoneRegionCode687Constants regionCodes687 =
              GWT.create(PhoneRegionCode687Constants.class);
          phoneRegionCodes = regionCodes687.phoneRegionCodes687();
          break;
        case "688":
          final PhoneRegionCode688Constants regionCodes688 =
              GWT.create(PhoneRegionCode688Constants.class);
          phoneRegionCodes = regionCodes688.phoneRegionCodes688();
          break;
        case "689":
          final PhoneRegionCode689Constants regionCodes689 =
              GWT.create(PhoneRegionCode689Constants.class);
          phoneRegionCodes = regionCodes689.phoneRegionCodes689();
          break;
        case "690":
          final PhoneRegionCode690Constants regionCodes690 =
              GWT.create(PhoneRegionCode690Constants.class);
          phoneRegionCodes = regionCodes690.phoneRegionCodes690();
          break;
        case "691":
          final PhoneRegionCode691Constants regionCodes691 =
              GWT.create(PhoneRegionCode691Constants.class);
          phoneRegionCodes = regionCodes691.phoneRegionCodes691();
          break;
        case "692":
          final PhoneRegionCode692Constants regionCodes692 =
              GWT.create(PhoneRegionCode692Constants.class);
          phoneRegionCodes = regionCodes692.phoneRegionCodes692();
          break;
        case "7":
          final PhoneRegionCode7Constants regionCodes7 =
              GWT.create(PhoneRegionCode7Constants.class);
          phoneRegionCodes = regionCodes7.phoneRegionCodes7();
          break;
        case "800":
          final PhoneRegionCode800Constants regionCodes800 =
              GWT.create(PhoneRegionCode800Constants.class);
          phoneRegionCodes = regionCodes800.phoneRegionCodes800();
          break;
        case "808":
          final PhoneRegionCode808Constants regionCodes808 =
              GWT.create(PhoneRegionCode808Constants.class);
          phoneRegionCodes = regionCodes808.phoneRegionCodes808();
          break;
        case "81":
          final PhoneRegionCode81Constants regionCodes81 =
              GWT.create(PhoneRegionCode81Constants.class);
          phoneRegionCodes = regionCodes81.phoneRegionCodes81();
          break;
        case "82":
          final PhoneRegionCode82Constants regionCodes82 =
              GWT.create(PhoneRegionCode82Constants.class);
          phoneRegionCodes = regionCodes82.phoneRegionCodes82();
          break;
        case "84":
          final PhoneRegionCode84Constants regionCodes84 =
              GWT.create(PhoneRegionCode84Constants.class);
          phoneRegionCodes = regionCodes84.phoneRegionCodes84();
          break;
        case "850":
          final PhoneRegionCode850Constants regionCodes850 =
              GWT.create(PhoneRegionCode850Constants.class);
          phoneRegionCodes = regionCodes850.phoneRegionCodes850();
          break;
        case "852":
          final PhoneRegionCode852Constants regionCodes852 =
              GWT.create(PhoneRegionCode852Constants.class);
          phoneRegionCodes = regionCodes852.phoneRegionCodes852();
          break;
        case "853":
          final PhoneRegionCode853Constants regionCodes853 =
              GWT.create(PhoneRegionCode853Constants.class);
          phoneRegionCodes = regionCodes853.phoneRegionCodes853();
          break;
        case "855":
          final PhoneRegionCode855Constants regionCodes855 =
              GWT.create(PhoneRegionCode855Constants.class);
          phoneRegionCodes = regionCodes855.phoneRegionCodes855();
          break;
        case "86":
          final PhoneRegionCode86Constants regionCodes86 =
              GWT.create(PhoneRegionCode86Constants.class);
          phoneRegionCodes = regionCodes86.phoneRegionCodes86();
          break;
        case "870":
          final PhoneRegionCode870Constants regionCodes870 =
              GWT.create(PhoneRegionCode870Constants.class);
          phoneRegionCodes = regionCodes870.phoneRegionCodes870();
          break;
        case "875":
          final PhoneRegionCode875Constants regionCodes875 =
              GWT.create(PhoneRegionCode875Constants.class);
          phoneRegionCodes = regionCodes875.phoneRegionCodes875();
          break;
        case "876":
          final PhoneRegionCode876Constants regionCodes876 =
              GWT.create(PhoneRegionCode876Constants.class);
          phoneRegionCodes = regionCodes876.phoneRegionCodes876();
          break;
        case "877":
          final PhoneRegionCode877Constants regionCodes877 =
              GWT.create(PhoneRegionCode877Constants.class);
          phoneRegionCodes = regionCodes877.phoneRegionCodes877();
          break;
        case "878":
          final PhoneRegionCode878Constants regionCodes878 =
              GWT.create(PhoneRegionCode878Constants.class);
          phoneRegionCodes = regionCodes878.phoneRegionCodes878();
          break;
        case "879":
          final PhoneRegionCode879Constants regionCodes879 =
              GWT.create(PhoneRegionCode879Constants.class);
          phoneRegionCodes = regionCodes879.phoneRegionCodes879();
          break;
        case "880":
          final PhoneRegionCode880Constants regionCodes880 =
              GWT.create(PhoneRegionCode880Constants.class);
          phoneRegionCodes = regionCodes880.phoneRegionCodes880();
          break;
        case "881":
          final PhoneRegionCode881Constants regionCodes881 =
              GWT.create(PhoneRegionCode881Constants.class);
          phoneRegionCodes = regionCodes881.phoneRegionCodes881();
          break;
        case "882":
          final PhoneRegionCode882Constants regionCodes882 =
              GWT.create(PhoneRegionCode882Constants.class);
          phoneRegionCodes = regionCodes882.phoneRegionCodes882();
          break;
        case "883":
          final PhoneRegionCode883Constants regionCodes883 =
              GWT.create(PhoneRegionCode883Constants.class);
          phoneRegionCodes = regionCodes883.phoneRegionCodes883();
          break;
        case "886":
          final PhoneRegionCode886Constants regionCodes886 =
              GWT.create(PhoneRegionCode886Constants.class);
          phoneRegionCodes = regionCodes886.phoneRegionCodes886();
          break;
        case "888":
          final PhoneRegionCode888Constants regionCodes888 =
              GWT.create(PhoneRegionCode888Constants.class);
          phoneRegionCodes = regionCodes888.phoneRegionCodes888();
          break;
        case "90":
          final PhoneRegionCode90Constants regionCodes90 =
              GWT.create(PhoneRegionCode90Constants.class);
          phoneRegionCodes = regionCodes90.phoneRegionCodes90();
          break;
        case "91":
          final PhoneRegionCode91Constants regionCodes91 =
              GWT.create(PhoneRegionCode91Constants.class);
          phoneRegionCodes = regionCodes91.phoneRegionCodes91();
          break;
        case "92":
          final PhoneRegionCode92Constants regionCodes92 =
              GWT.create(PhoneRegionCode92Constants.class);
          phoneRegionCodes = regionCodes92.phoneRegionCodes92();
          break;
        case "93":
          final PhoneRegionCode93Constants regionCodes93 =
              GWT.create(PhoneRegionCode93Constants.class);
          phoneRegionCodes = regionCodes93.phoneRegionCodes93();
          break;
        case "94":
          final PhoneRegionCode94Constants regionCodes94 =
              GWT.create(PhoneRegionCode94Constants.class);
          phoneRegionCodes = regionCodes94.phoneRegionCodes94();
          break;
        case "95":
          final PhoneRegionCode95Constants regionCodes95 =
              GWT.create(PhoneRegionCode95Constants.class);
          phoneRegionCodes = regionCodes95.phoneRegionCodes95();
          break;
        case "960":
          final PhoneRegionCode960Constants regionCodes960 =
              GWT.create(PhoneRegionCode960Constants.class);
          phoneRegionCodes = regionCodes960.phoneRegionCodes960();
          break;
        case "961":
          final PhoneRegionCode961Constants regionCodes961 =
              GWT.create(PhoneRegionCode961Constants.class);
          phoneRegionCodes = regionCodes961.phoneRegionCodes961();
          break;
        case "962":
          final PhoneRegionCode962Constants regionCodes962 =
              GWT.create(PhoneRegionCode962Constants.class);
          phoneRegionCodes = regionCodes962.phoneRegionCodes962();
          break;
        case "963":
          final PhoneRegionCode963Constants regionCodes963 =
              GWT.create(PhoneRegionCode963Constants.class);
          phoneRegionCodes = regionCodes963.phoneRegionCodes963();
          break;
        case "964":
          final PhoneRegionCode964Constants regionCodes964 =
              GWT.create(PhoneRegionCode964Constants.class);
          phoneRegionCodes = regionCodes964.phoneRegionCodes964();
          break;
        case "965":
          final PhoneRegionCode965Constants regionCodes965 =
              GWT.create(PhoneRegionCode965Constants.class);
          phoneRegionCodes = regionCodes965.phoneRegionCodes965();
          break;
        case "966":
          final PhoneRegionCode966Constants regionCodes966 =
              GWT.create(PhoneRegionCode966Constants.class);
          phoneRegionCodes = regionCodes966.phoneRegionCodes966();
          break;
        case "967":
          final PhoneRegionCode967Constants regionCodes967 =
              GWT.create(PhoneRegionCode967Constants.class);
          phoneRegionCodes = regionCodes967.phoneRegionCodes967();
          break;
        case "968":
          final PhoneRegionCode968Constants regionCodes968 =
              GWT.create(PhoneRegionCode968Constants.class);
          phoneRegionCodes = regionCodes968.phoneRegionCodes968();
          break;
        case "970":
          final PhoneRegionCode970Constants regionCodes970 =
              GWT.create(PhoneRegionCode970Constants.class);
          phoneRegionCodes = regionCodes970.phoneRegionCodes970();
          break;
        case "971":
          final PhoneRegionCode971Constants regionCodes971 =
              GWT.create(PhoneRegionCode971Constants.class);
          phoneRegionCodes = regionCodes971.phoneRegionCodes971();
          break;
        case "972":
          final PhoneRegionCode972Constants regionCodes972 =
              GWT.create(PhoneRegionCode972Constants.class);
          phoneRegionCodes = regionCodes972.phoneRegionCodes972();
          break;
        case "973":
          final PhoneRegionCode973Constants regionCodes973 =
              GWT.create(PhoneRegionCode973Constants.class);
          phoneRegionCodes = regionCodes973.phoneRegionCodes973();
          break;
        case "974":
          final PhoneRegionCode974Constants regionCodes974 =
              GWT.create(PhoneRegionCode974Constants.class);
          phoneRegionCodes = regionCodes974.phoneRegionCodes974();
          break;
        case "975":
          final PhoneRegionCode975Constants regionCodes975 =
              GWT.create(PhoneRegionCode975Constants.class);
          phoneRegionCodes = regionCodes975.phoneRegionCodes975();
          break;
        case "976":
          final PhoneRegionCode976Constants regionCodes976 =
              GWT.create(PhoneRegionCode976Constants.class);
          phoneRegionCodes = regionCodes976.phoneRegionCodes976();
          break;
        case "977":
          final PhoneRegionCode977Constants regionCodes977 =
              GWT.create(PhoneRegionCode977Constants.class);
          phoneRegionCodes = regionCodes977.phoneRegionCodes977();
          break;
        case "979":
          final PhoneRegionCode979Constants regionCodes979 =
              GWT.create(PhoneRegionCode979Constants.class);
          phoneRegionCodes = regionCodes979.phoneRegionCodes979();
          break;
        case "98":
          final PhoneRegionCode98Constants regionCodes98 =
              GWT.create(PhoneRegionCode98Constants.class);
          phoneRegionCodes = regionCodes98.phoneRegionCodes98();
          break;
        case "991":
          final PhoneRegionCode991Constants regionCodes991 =
              GWT.create(PhoneRegionCode991Constants.class);
          phoneRegionCodes = regionCodes991.phoneRegionCodes991();
          break;
        case "992":
          final PhoneRegionCode992Constants regionCodes992 =
              GWT.create(PhoneRegionCode992Constants.class);
          phoneRegionCodes = regionCodes992.phoneRegionCodes992();
          break;
        case "993":
          final PhoneRegionCode993Constants regionCodes993 =
              GWT.create(PhoneRegionCode993Constants.class);
          phoneRegionCodes = regionCodes993.phoneRegionCodes993();
          break;
        case "994":
          final PhoneRegionCode994Constants regionCodes994 =
              GWT.create(PhoneRegionCode994Constants.class);
          phoneRegionCodes = regionCodes994.phoneRegionCodes994();
          break;
        case "995":
          final PhoneRegionCode995Constants regionCodes995 =
              GWT.create(PhoneRegionCode995Constants.class);
          phoneRegionCodes = regionCodes995.phoneRegionCodes995();
          break;
        case "996":
          final PhoneRegionCode996Constants regionCodes996 =
              GWT.create(PhoneRegionCode996Constants.class);
          phoneRegionCodes = regionCodes996.phoneRegionCodes996();
          break;
        case "998":
          final PhoneRegionCode998Constants regionCodes998 =
              GWT.create(PhoneRegionCode998Constants.class);
          phoneRegionCodes = regionCodes998.phoneRegionCodes998();
          break;
        default:
          phoneRegionCodes = Collections.emptyMap();
          break;
      }
      for (final Entry<String, String> region : phoneRegionCodes.entrySet()) {
        final PhoneAreaCodeData areaData =
            new PhoneAreaCodeData(region.getKey(), region.getValue());
        countryEntry.addAreaCodeData(areaData);
      }
      result.add(countryEntry);
    }
    return result;
  }

  private static Map<String, PhoneCountryData> createMapFromPhoneCountry(
      final Set<PhoneCountryCodeData> pcountries, final Map<String, String> pphoneCountryNames,
      final Map<String, String> pphoneCountryCodes) {
    final Map<String, PhoneCountryData> countryPhoneMap = new HashMap<>();
    final PhoneCountryTrunkAndExitCodesConstants phoneTrunkAndExitCodes =
        GWT.create(PhoneCountryTrunkAndExitCodesConstants.class);
    for (final PhoneCountryCodeData entry : pcountries) {
      final String countryCode = pphoneCountryCodes.get(entry.getCountryCode());
      if (countryCode.contains("-")) {
        final String[] splittedCountryCodes = StringUtils.split(countryCode, '-');
        for (final String singleCountryCode : splittedCountryCodes) {
          entry.setPhoneCountryData(addCountryToPhoneMap(pphoneCountryNames, countryPhoneMap,
              phoneTrunkAndExitCodes.phoneTrunkAndExitCodes(), entry, singleCountryCode));
        }
      } else {
        entry.setPhoneCountryData(addCountryToPhoneMap(pphoneCountryNames, countryPhoneMap,
            phoneTrunkAndExitCodes.phoneTrunkAndExitCodes(), entry, countryCode));
      }
    }
    return countryPhoneMap;
  }

  private static PhoneCountryData addCountryToPhoneMap(final Map<String, String> pphoneCountryNames,
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
