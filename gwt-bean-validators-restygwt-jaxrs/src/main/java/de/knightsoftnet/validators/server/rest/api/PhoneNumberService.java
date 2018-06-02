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

package de.knightsoftnet.validators.server.rest.api;

import de.knightsoftnet.validators.shared.Parameters;
import de.knightsoftnet.validators.shared.ResourcePaths.PhoneNumber;
import de.knightsoftnet.validators.shared.data.PhoneNumberData;
import de.knightsoftnet.validators.shared.data.PhoneNumberDataWithFormats;
import de.knightsoftnet.validators.shared.data.ValueWithPos;
import de.knightsoftnet.validators.shared.data.ValueWithPosAndCountry;
import de.knightsoftnet.validators.shared.util.LocaleUtil;
import de.knightsoftnet.validators.shared.util.PhoneNumberUtil;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * phone number web service brings phone number util functions to client.
 *
 * @author Manfred Tremmel
 */
@Path(PhoneNumber.ROOT)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PhoneNumberService {

  /**
   * phone number utils to test phone numbers.
   */
  private final PhoneNumberUtil phoneNumberUtil = new PhoneNumberUtil();

  @GET
  @Path(PhoneNumber.PARSE_PHONE_NUMBER)
  public PhoneNumberData parsePhoneNumber(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber) {
    return phoneNumberUtil.parsePhoneNumber(pphoneNumber, pcountry,
        LocaleUtil.convertLanguageToLocale(planguage));
  }


  @PUT
  @Path(PhoneNumber.PARSE_WITH_POS)
  public ValueWithPos<PhoneNumberData> parsePhoneNumber(
      final ValueWithPosAndCountry<String> pphoneNumber) {
    return phoneNumberUtil.parsePhoneNumber(pphoneNumber, pphoneNumber.getCountry());
  }

  /**
   * parse and reformat the phone number in all available formats.
   *
   * @param planguage language to use
   * @param pcountry default country
   * @param pphoneNumber phone number to format
   * @return PhoneNumberDataWithFormats
   */
  @GET
  @Path(PhoneNumber.PARSE_AND_FORMAT)
  public PhoneNumberDataWithFormats parseAndFormatPhoneNumber(
      @QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber) {
    final PhoneNumberDataWithFormats result = new PhoneNumberDataWithFormats(phoneNumberUtil
        .parsePhoneNumber(pphoneNumber, pcountry, LocaleUtil.convertLanguageToLocale(planguage)));
    result.setCommonInternational(phoneNumberUtil.formatCommonInternational(result));
    result.setCommonNational(phoneNumberUtil.formatCommonNational(result));
    result.setDin5008International(phoneNumberUtil.formatDin5008International(result));
    result.setDin5008National(phoneNumberUtil.formatDin5008National(result));
    result.setE123International(phoneNumberUtil.formatE123International(result));
    result.setE123National(phoneNumberUtil.formatE123National(result));
    result.setMs(phoneNumberUtil.formatMs(result));
    result.setUrl(phoneNumberUtil.formatUrl(result));
    return result;
  }


  @GET
  @Path(PhoneNumber.FORMAT_E123)
  public String formatE123(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber) {
    return phoneNumberUtil.formatE123(pphoneNumber, pcountry);
  }

  @PUT
  @Path(PhoneNumber.FORMAT_E123_WITH_POS)
  public ValueWithPos<String> formatE123WithPos(final ValueWithPosAndCountry<String> pphoneNumber) {
    return phoneNumberUtil.formatE123WithPos(pphoneNumber, pphoneNumber.getCountry());
  }

  @GET
  @Path(PhoneNumber.FORMAT_E123_INTERNATIONAL)
  public String formatE123International(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber) {
    return phoneNumberUtil.formatE123International(pphoneNumber, pcountry);
  }

  @PUT
  @Path(PhoneNumber.FORMAT_E123_INTERNATIONAL_WITH_POS)
  public ValueWithPos<String> formatE123InternationalWithPos(
      final ValueWithPosAndCountry<String> pphoneNumber) {
    return phoneNumberUtil.formatE123InternationalWithPos(pphoneNumber, pphoneNumber.getCountry());
  }

  @GET
  @Path(PhoneNumber.FORMAT_E123_NATIONAL)
  public String formatE123National(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber) {
    return phoneNumberUtil.formatE123National(pphoneNumber, pcountry);
  }

  @PUT
  @Path(PhoneNumber.FORMAT_E123_NATIONAL_WITH_POS)
  public ValueWithPos<String> formatE123NationalWithPos(
      final ValueWithPosAndCountry<String> pphoneNumber) {
    return phoneNumberUtil.formatE123NationalWithPos(pphoneNumber, pphoneNumber.getCountry());
  }


  @GET
  @Path(PhoneNumber.FORMAT_DIN5008)
  public String formatDin5008(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber) {
    return phoneNumberUtil.formatDin5008(pphoneNumber, pcountry);
  }

  @PUT
  @Path(PhoneNumber.FORMAT_DIN5008_WITH_POS)
  public ValueWithPos<String> formatDin5008WithPos(
      final ValueWithPosAndCountry<String> pphoneNumber) {
    return phoneNumberUtil.formatDin5008WithPos(pphoneNumber, pphoneNumber.getCountry());
  }

  @GET
  @Path(PhoneNumber.FORMAT_DIN5008_INTERNATIONAL)
  public String formatDin5008International(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber) {
    return phoneNumberUtil.formatDin5008International(pphoneNumber, pcountry);
  }

  @PUT
  @Path(PhoneNumber.FORMAT_DIN5008_INTERNATIONAL_WITH_POS)
  public ValueWithPos<String> formatDin5008InternationalWithPos(
      final ValueWithPosAndCountry<String> pphoneNumber) {
    return phoneNumberUtil.formatDin5008InternationalWithPos(pphoneNumber,
        pphoneNumber.getCountry());
  }

  @GET
  @Path(PhoneNumber.FORMAT_DIN5008_NATIONAL)
  public String formatDin5008National(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber) {
    return phoneNumberUtil.formatDin5008National(pphoneNumber, pcountry);
  }

  @PUT
  @Path(PhoneNumber.FORMAT_DIN5008_NATIONAL_WITH_POS)
  public ValueWithPos<String> formatDin5008NationalWithPos(
      final ValueWithPosAndCountry<String> pphoneNumber) {
    return phoneNumberUtil.formatDin5008NationalWithPos(pphoneNumber, pphoneNumber.getCountry());
  }


  @GET
  @Path(PhoneNumber.FORMAT_RFC3966)
  public String formatRfc3966(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber) {
    return phoneNumberUtil.formatRfc3966(pphoneNumber, pcountry);
  }

  @PUT
  @Path(PhoneNumber.FORMAT_RFC3966_WITH_POS)
  public ValueWithPos<String> formatRfc3966WithPos(
      final ValueWithPosAndCountry<String> pphoneNumber) {
    return phoneNumberUtil.formatRfc3966WithPos(pphoneNumber, pphoneNumber.getCountry());
  }


  @GET
  @Path(PhoneNumber.FORMAT_MS)
  public String formatMs(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber) {
    return phoneNumberUtil.formatMs(pphoneNumber, pcountry);
  }

  @PUT
  @Path(PhoneNumber.FORMAT_MS_WITH_POS)
  public ValueWithPos<String> formatMsWithPos(final ValueWithPosAndCountry<String> pphoneNumber) {
    return phoneNumberUtil.formatMsWithPos(pphoneNumber, pphoneNumber.getCountry());
  }


  @GET
  @Path(PhoneNumber.FORMAT_URL)
  public String formatUrl(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber) {
    return phoneNumberUtil.formatUrl(pphoneNumber, pcountry);
  }

  @PUT
  @Path(PhoneNumber.FORMAT_URL_WITH_POS)
  public ValueWithPos<String> formatUrlWithPos(final ValueWithPosAndCountry<String> pphoneNumber) {
    return phoneNumberUtil.formatUrlWithPos(pphoneNumber, pphoneNumber.getCountry());
  }


  @GET
  @Path(PhoneNumber.FORMAT_COMMON)
  public String formatCommon(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber) {
    return phoneNumberUtil.formatCommon(pphoneNumber, pcountry);
  }

  @PUT
  @Path(PhoneNumber.FORMAT_COMMON_WITH_POS)
  public ValueWithPos<String> formatCommonWithPos(
      final ValueWithPosAndCountry<String> pphoneNumber) {
    return phoneNumberUtil.formatCommonWithPos(pphoneNumber, pphoneNumber.getCountry());
  }

  @GET
  @Path(PhoneNumber.FORMAT_COMMON_INTERNATIONAL)
  public String formatCommonInternational(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber) {
    return phoneNumberUtil.formatCommonInternational(pphoneNumber, pcountry);
  }

  @PUT
  @Path(PhoneNumber.FORMAT_COMMON_INTERNATIONAL_WITH_POS)
  public ValueWithPos<String> formatCommonInternationalWithPos(
      final ValueWithPosAndCountry<String> pphoneNumber) {
    return phoneNumberUtil.formatCommonInternationalWithPos(pphoneNumber,
        pphoneNumber.getCountry());
  }

  @GET
  @Path(PhoneNumber.FORMAT_COMMON_NATIONAL)
  public String formatCommonNational(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber) {
    return phoneNumberUtil.formatCommonNational(pphoneNumber, pcountry);
  }

  @PUT
  @Path(PhoneNumber.FORMAT_COMMON_NATIONAL_WITH_POS)
  public ValueWithPos<String> formatCommonNationalWithPos(
      final ValueWithPosAndCountry<String> pphoneNumber) {
    return phoneNumberUtil.formatCommonNationalWithPos(pphoneNumber, pphoneNumber.getCountry());
  }


  @GET
  @Path(PhoneNumber.GET_SUGGESTIONS)
  public List<PhoneNumberData> getSuggestions(
      @QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.SEARCH) final String psearch,
      @QueryParam(Parameters.LIMIT) final int plimit) {
    return phoneNumberUtil.getSuggstions(psearch, plimit,
        LocaleUtil.convertLanguageToLocale(planguage));
  }


  /**
   * validate a phone number.
   *
   * @param pcountry default country
   * @param pphoneNumber phone number to check
   * @param pdin5008 set to true if DIN 5008 format is allowed
   * @param pe123 set to true if E123 format is allowed
   * @param puri set to true if URI format is allowed
   * @param pms set to true if Microsoft format is allowed
   * @param pcommon set to true if common format is allowed
   * @return true if number is valid
   */
  @GET
  @Path(PhoneNumber.VALIDATE)
  public Boolean validate(@QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber,
      @QueryParam(Parameters.DIN_5008) final Boolean pdin5008,
      @QueryParam(Parameters.E123) final Boolean pe123,
      @QueryParam(Parameters.URI) final Boolean puri, @QueryParam(Parameters.MS) final Boolean pms,
      @QueryParam(Parameters.COMMON) final Boolean pcommon) {
    final PhoneNumberData parsedNumber = phoneNumberUtil.parsePhoneNumber(pphoneNumber, pcountry);
    if (parsedNumber.isValid()) {
      if (BooleanUtils.isTrue(pdin5008)
          && (StringUtils.equals(pphoneNumber, phoneNumberUtil.formatDin5008National(parsedNumber))
              || StringUtils.equals(pphoneNumber,
                  phoneNumberUtil.formatDin5008International(parsedNumber)))) {
        return Boolean.TRUE;
      }
      if (BooleanUtils.isTrue(pe123)
          && (StringUtils.equals(pphoneNumber, phoneNumberUtil.formatE123National(parsedNumber))
              || StringUtils.equals(pphoneNumber,
                  phoneNumberUtil.formatE123International(parsedNumber)))) {
        return Boolean.TRUE;
      }
      if (BooleanUtils.isTrue(puri)
          && StringUtils.equals(pphoneNumber, phoneNumberUtil.formatUrl(parsedNumber))) {
        return Boolean.TRUE;
      }
      if (BooleanUtils.isTrue(pms)
          && StringUtils.equals(pphoneNumber, phoneNumberUtil.formatMs(parsedNumber))) {
        return Boolean.TRUE;
      }
      if (BooleanUtils.isTrue(pcommon)
          && (StringUtils.equals(pphoneNumber, phoneNumberUtil.formatCommonNational(parsedNumber))
              || StringUtils.equals(pphoneNumber,
                  phoneNumberUtil.formatCommonInternational(parsedNumber)))) {
        return Boolean.TRUE;
      }
    }
    return Boolean.FALSE;
  }
}
