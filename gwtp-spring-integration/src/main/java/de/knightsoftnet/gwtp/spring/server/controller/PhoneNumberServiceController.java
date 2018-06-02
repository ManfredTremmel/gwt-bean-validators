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

package de.knightsoftnet.gwtp.spring.server.controller;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

import java.util.List;

import de.knightsoftnet.gwtp.spring.shared.Parameters;
import de.knightsoftnet.gwtp.spring.shared.ResourcePaths.PhoneNumber;
import de.knightsoftnet.gwtp.spring.shared.data.PhoneNumberDataWithFormats;
import de.knightsoftnet.gwtp.spring.shared.data.ValueWithPosAndCountry;
import de.knightsoftnet.validators.shared.data.PhoneNumberData;
import de.knightsoftnet.validators.shared.data.ValueWithPos;
import de.knightsoftnet.validators.shared.util.LocaleUtil;
import de.knightsoftnet.validators.shared.util.PhoneNumberUtil;

/**
 * phone number web service brings phone number util functions to client.
 *
 * @author Manfred Tremmel
 */
@RestController
@RequestMapping(PhoneNumber.ROOT)
public class PhoneNumberServiceController {

  /**
   * phone number utils to test phone numbers.
   */
  private final PhoneNumberUtil phoneNumberUtil = new PhoneNumberUtil();

  @RequestMapping(value = PhoneNumber.PARSE_PHONE_NUMBER, method = RequestMethod.GET)
  @PermitAll
  public PhoneNumberData parsePhoneNumber(
      @RequestParam(value = Parameters.LANGUAGE, required = true) final String planguage,
      @RequestParam(value = Parameters.COUNTRY, required = true) final String pcountry,
      @RequestParam(value = Parameters.PHONE_NUMBER, required = true) final String pphoneNumber) {
    return phoneNumberUtil.parsePhoneNumber(pphoneNumber, pcountry,
        LocaleUtil.convertLanguageToLocale(planguage));
  }

  @RequestMapping(value = PhoneNumber.PARSE_WITH_POS, method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  @PermitAll
  @ResponseBody
  public ValueWithPos<PhoneNumberData> parsePhoneNumber(
      @RequestBody final ValueWithPosAndCountry<String> pphoneNumber) {
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
  @RequestMapping(value = PhoneNumber.PARSE_AND_FORMAT, method = RequestMethod.GET)
  @PermitAll
  public PhoneNumberDataWithFormats parseAndFormatPhoneNumber(
      @RequestParam(value = Parameters.LANGUAGE, required = true) final String planguage,
      @RequestParam(value = Parameters.COUNTRY, required = true) final String pcountry,
      @RequestParam(value = Parameters.PHONE_NUMBER, required = true) final String pphoneNumber) {
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


  @RequestMapping(value = PhoneNumber.FORMAT_E123, method = RequestMethod.GET)
  @PermitAll
  public String formatE123(
      @RequestParam(value = Parameters.LANGUAGE, required = true) final String planguage,
      @RequestParam(value = Parameters.COUNTRY, required = true) final String pcountry,
      @RequestParam(value = Parameters.PHONE_NUMBER, required = true) final String pphoneNumber) {
    return phoneNumberUtil.formatE123(pphoneNumber, pcountry);
  }

  @RequestMapping(value = PhoneNumber.FORMAT_E123_WITH_POS, method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  @PermitAll
  @ResponseBody
  public ValueWithPos<String> formatE123WithPos(
      @RequestBody final ValueWithPosAndCountry<String> pphoneNumber) {
    return phoneNumberUtil.formatE123WithPos(pphoneNumber, pphoneNumber.getCountry());
  }

  @RequestMapping(value = PhoneNumber.FORMAT_E123_INTERNATIONAL, method = RequestMethod.GET)
  @PermitAll
  public String formatE123International(
      @RequestParam(value = Parameters.LANGUAGE, required = true) final String planguage,
      @RequestParam(value = Parameters.COUNTRY, required = true) final String pcountry,
      @RequestParam(value = Parameters.PHONE_NUMBER, required = true) final String pphoneNumber) {
    return phoneNumberUtil.formatE123International(pphoneNumber, pcountry);
  }

  @RequestMapping(value = PhoneNumber.FORMAT_E123_INTERNATIONAL_WITH_POS,
      method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  @PermitAll
  @ResponseBody
  public ValueWithPos<String> formatE123InternationalWithPos(
      @RequestBody final ValueWithPosAndCountry<String> pphoneNumber) {
    return phoneNumberUtil.formatE123InternationalWithPos(pphoneNumber, pphoneNumber.getCountry());
  }

  @RequestMapping(value = PhoneNumber.FORMAT_E123_NATIONAL, method = RequestMethod.GET)
  @PermitAll
  public String formatE123National(
      @RequestParam(value = Parameters.LANGUAGE, required = true) final String planguage,
      @RequestParam(value = Parameters.COUNTRY, required = true) final String pcountry,
      @RequestParam(value = Parameters.PHONE_NUMBER, required = true) final String pphoneNumber) {
    return phoneNumberUtil.formatE123National(pphoneNumber, pcountry);
  }

  @RequestMapping(value = PhoneNumber.FORMAT_E123_NATIONAL_WITH_POS, method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  @PermitAll
  @ResponseBody
  public ValueWithPos<String> formatE123NationalWithPos(
      @RequestBody final ValueWithPosAndCountry<String> pphoneNumber) {
    return phoneNumberUtil.formatE123NationalWithPos(pphoneNumber, pphoneNumber.getCountry());
  }


  @RequestMapping(value = PhoneNumber.FORMAT_DIN5008, method = RequestMethod.GET)
  @PermitAll
  public String formatDin5008(
      @RequestParam(value = Parameters.LANGUAGE, required = true) final String planguage,
      @RequestParam(value = Parameters.COUNTRY, required = true) final String pcountry,
      @RequestParam(value = Parameters.PHONE_NUMBER, required = true) final String pphoneNumber) {
    return phoneNumberUtil.formatDin5008(pphoneNumber, pcountry);
  }

  @RequestMapping(value = PhoneNumber.FORMAT_DIN5008_WITH_POS, method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  @PermitAll
  @ResponseBody
  public ValueWithPos<String> formatDin5008WithPos(
      @RequestBody final ValueWithPosAndCountry<String> pphoneNumber) {
    return phoneNumberUtil.formatDin5008WithPos(pphoneNumber, pphoneNumber.getCountry());
  }

  @RequestMapping(value = PhoneNumber.FORMAT_DIN5008_INTERNATIONAL, method = RequestMethod.GET)
  @PermitAll
  public String formatDin5008International(
      @RequestParam(value = Parameters.LANGUAGE, required = true) final String planguage,
      @RequestParam(value = Parameters.COUNTRY, required = true) final String pcountry,
      @RequestParam(value = Parameters.PHONE_NUMBER, required = true) final String pphoneNumber) {
    return phoneNumberUtil.formatDin5008International(pphoneNumber, pcountry);
  }

  @RequestMapping(value = PhoneNumber.FORMAT_DIN5008_INTERNATIONAL_WITH_POS,
      method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  @PermitAll
  @ResponseBody
  public ValueWithPos<String> formatDin5008InternationalWithPos(
      @RequestBody final ValueWithPosAndCountry<String> pphoneNumber) {
    return phoneNumberUtil.formatDin5008InternationalWithPos(pphoneNumber,
        pphoneNumber.getCountry());
  }

  @RequestMapping(value = PhoneNumber.FORMAT_DIN5008_NATIONAL, method = RequestMethod.GET)
  @PermitAll
  public String formatDin5008National(
      @RequestParam(value = Parameters.LANGUAGE, required = true) final String planguage,
      @RequestParam(value = Parameters.COUNTRY, required = true) final String pcountry,
      @RequestParam(value = Parameters.PHONE_NUMBER, required = true) final String pphoneNumber) {
    return phoneNumberUtil.formatDin5008National(pphoneNumber, pcountry);
  }

  @RequestMapping(value = PhoneNumber.FORMAT_DIN5008_NATIONAL_WITH_POS, method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  @PermitAll
  @ResponseBody
  public ValueWithPos<String> formatDin5008NationalWithPos(
      @RequestBody final ValueWithPosAndCountry<String> pphoneNumber) {
    return phoneNumberUtil.formatDin5008NationalWithPos(pphoneNumber, pphoneNumber.getCountry());
  }


  @RequestMapping(value = PhoneNumber.FORMAT_RFC3966, method = RequestMethod.GET)
  @PermitAll
  public String formatRfc3966(
      @RequestParam(value = Parameters.LANGUAGE, required = true) final String planguage,
      @RequestParam(value = Parameters.COUNTRY, required = true) final String pcountry,
      @RequestParam(value = Parameters.PHONE_NUMBER, required = true) final String pphoneNumber) {
    return phoneNumberUtil.formatRfc3966(pphoneNumber, pcountry);
  }

  @RequestMapping(value = PhoneNumber.FORMAT_RFC3966_WITH_POS, method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  @PermitAll
  @ResponseBody
  public ValueWithPos<String> formatRfc3966WithPos(
      @RequestBody final ValueWithPosAndCountry<String> pphoneNumber) {
    return phoneNumberUtil.formatRfc3966WithPos(pphoneNumber, pphoneNumber.getCountry());
  }


  @RequestMapping(value = PhoneNumber.FORMAT_MS, method = RequestMethod.GET)
  @PermitAll
  public String formatMs(
      @RequestParam(value = Parameters.LANGUAGE, required = true) final String planguage,
      @RequestParam(value = Parameters.COUNTRY, required = true) final String pcountry,
      @RequestParam(value = Parameters.PHONE_NUMBER, required = true) final String pphoneNumber) {
    return phoneNumberUtil.formatMs(pphoneNumber, pcountry);
  }

  @RequestMapping(value = PhoneNumber.FORMAT_MS_WITH_POS, method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  @PermitAll
  @ResponseBody
  public ValueWithPos<String> formatMsWithPos(
      @RequestBody final ValueWithPosAndCountry<String> pphoneNumber) {
    return phoneNumberUtil.formatMsWithPos(pphoneNumber, pphoneNumber.getCountry());
  }


  @RequestMapping(value = PhoneNumber.FORMAT_URL, method = RequestMethod.GET)
  @PermitAll
  public String formatUrl(
      @RequestParam(value = Parameters.LANGUAGE, required = true) final String planguage,
      @RequestParam(value = Parameters.COUNTRY, required = true) final String pcountry,
      @RequestParam(value = Parameters.PHONE_NUMBER, required = true) final String pphoneNumber) {
    return phoneNumberUtil.formatUrl(pphoneNumber, pcountry);
  }

  @RequestMapping(value = PhoneNumber.FORMAT_URL_WITH_POS, method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  @PermitAll
  @ResponseBody
  public ValueWithPos<String> formatUrlWithPos(
      @RequestBody final ValueWithPosAndCountry<String> pphoneNumber) {
    return phoneNumberUtil.formatUrlWithPos(pphoneNumber, pphoneNumber.getCountry());
  }


  @RequestMapping(value = PhoneNumber.FORMAT_COMMON, method = RequestMethod.GET)
  @PermitAll
  public String formatCommon(
      @RequestParam(value = Parameters.LANGUAGE, required = true) final String planguage,
      @RequestParam(value = Parameters.COUNTRY, required = true) final String pcountry,
      @RequestParam(value = Parameters.PHONE_NUMBER, required = true) final String pphoneNumber) {
    return phoneNumberUtil.formatCommon(pphoneNumber, pcountry);
  }

  @RequestMapping(value = PhoneNumber.FORMAT_COMMON_WITH_POS, method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  @PermitAll
  @ResponseBody
  public ValueWithPos<String> formatCommonWithPos(
      @RequestBody final ValueWithPosAndCountry<String> pphoneNumber) {
    return phoneNumberUtil.formatCommonWithPos(pphoneNumber, pphoneNumber.getCountry());
  }


  @RequestMapping(value = PhoneNumber.FORMAT_COMMON_INTERNATIONAL, method = RequestMethod.GET)
  @PermitAll
  public String formatCommonInternational(
      @RequestParam(value = Parameters.LANGUAGE, required = true) final String planguage,
      @RequestParam(value = Parameters.COUNTRY, required = true) final String pcountry,
      @RequestParam(value = Parameters.PHONE_NUMBER, required = true) final String pphoneNumber) {
    return phoneNumberUtil.formatCommonInternational(pphoneNumber, pcountry);
  }

  @RequestMapping(value = PhoneNumber.FORMAT_COMMON_INTERNATIONAL_WITH_POS,
      method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  @PermitAll
  @ResponseBody
  public ValueWithPos<String> formatCommonInternationalWithPos(
      @RequestBody final ValueWithPosAndCountry<String> pphoneNumber) {
    return phoneNumberUtil.formatCommonInternationalWithPos(pphoneNumber,
        pphoneNumber.getCountry());
  }

  @RequestMapping(value = PhoneNumber.FORMAT_COMMON_NATIONAL, method = RequestMethod.GET)
  @PermitAll
  public String formatCommonNational(
      @RequestParam(value = Parameters.LANGUAGE, required = true) final String planguage,
      @RequestParam(value = Parameters.COUNTRY, required = true) final String pcountry,
      @RequestParam(value = Parameters.PHONE_NUMBER, required = true) final String pphoneNumber) {
    return phoneNumberUtil.formatCommonNational(pphoneNumber, pcountry);
  }

  @RequestMapping(value = PhoneNumber.FORMAT_COMMON_NATIONAL_WITH_POS, method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  @PermitAll
  @ResponseBody
  public ValueWithPos<String> formatCommonNationalWithPos(
      @RequestBody final ValueWithPosAndCountry<String> pphoneNumber) {
    return phoneNumberUtil.formatCommonNationalWithPos(pphoneNumber, pphoneNumber.getCountry());
  }


  @RequestMapping(value = PhoneNumber.GET_SUGGESTIONS, method = RequestMethod.GET)
  @PermitAll
  public List<PhoneNumberData> getSuggestions(
      @RequestParam(value = Parameters.LANGUAGE, required = true) final String planguage,
      @RequestParam(value = Parameters.SEARCH, required = true) final String psearch,
      @RequestParam(value = Parameters.LIMIT, required = true) final int plimit) {
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
  @RequestMapping(value = PhoneNumber.VALIDATE, method = RequestMethod.GET)
  @PermitAll
  public Boolean validate(
      @RequestParam(value = Parameters.COUNTRY, required = true) final String pcountry,
      @RequestParam(value = Parameters.PHONE_NUMBER, required = true) final String pphoneNumber,
      @RequestParam(value = Parameters.DIN_5008, required = false) final Boolean pdin5008,
      @RequestParam(value = Parameters.E123, required = false) final Boolean pe123,
      @RequestParam(value = Parameters.URI, required = false) final Boolean puri,
      @RequestParam(value = Parameters.MS, required = false) final Boolean pms,
      @RequestParam(value = Parameters.COMMON, required = false) final Boolean pcommon) {
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
