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

package de.knightsoftnet.validators.client.services;

import de.knightsoftnet.gwtp.spring.shared.Parameters;
import de.knightsoftnet.gwtp.spring.shared.ResourcePaths.PhoneNumber;
import de.knightsoftnet.gwtp.spring.shared.data.PhoneNumberDataWithFormats;
import de.knightsoftnet.gwtp.spring.shared.data.ValueWithPosAndCountry;
import de.knightsoftnet.validators.shared.data.PhoneNumberData;
import de.knightsoftnet.validators.shared.data.ValueWithPos;

import com.gwtplatform.dispatch.rest.shared.RestAction;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path(PhoneNumber.ROOT)
public interface PhoneNumberRestService {

  @GET
  @Path(PhoneNumber.PARSE_PHONE_NUMBER)
  RestAction<PhoneNumberData> parsePhoneNumber(
      @QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber);


  @PUT
  @Path(PhoneNumber.PARSE_WITH_POS)
  RestAction<ValueWithPos<PhoneNumberData>> parsePhoneNumber(
      final ValueWithPosAndCountry<String> pphoneNumber);

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
  RestAction<PhoneNumberDataWithFormats> parseAndFormatPhoneNumber(
      @QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber);


  @GET
  @Path(PhoneNumber.FORMAT_E123)
  RestAction<String> formatE123(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber);

  @PUT
  @Path(PhoneNumber.FORMAT_E123_WITH_POS)
  RestAction<ValueWithPos<String>> formatE123WithPos(
      final ValueWithPosAndCountry<String> pphoneNumber);

  @GET
  @Path(PhoneNumber.FORMAT_E123_INTERNATIONAL)
  RestAction<String> formatE123International(
      @QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber);

  @PUT
  @Path(PhoneNumber.FORMAT_E123_INTERNATIONAL_WITH_POS)
  RestAction<ValueWithPos<String>> formatE123InternationalWithPos(
      final ValueWithPosAndCountry<String> pphoneNumber);

  @GET
  @Path(PhoneNumber.FORMAT_E123_NATIONAL)
  RestAction<String> formatE123National(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber);

  @PUT
  @Path(PhoneNumber.FORMAT_E123_NATIONAL_WITH_POS)
  RestAction<ValueWithPos<String>> formatE123NationalWithPos(
      final ValueWithPosAndCountry<String> pphoneNumber);


  @GET
  @Path(PhoneNumber.FORMAT_DIN5008)
  RestAction<String> formatDin5008(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber);

  @PUT
  @Path(PhoneNumber.FORMAT_DIN5008_WITH_POS)
  RestAction<ValueWithPos<String>> formatDin5008WithPos(
      final ValueWithPosAndCountry<String> pphoneNumber);

  @GET
  @Path(PhoneNumber.FORMAT_DIN5008_INTERNATIONAL)
  RestAction<String> formatDin5008International(
      @QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber);

  @PUT
  @Path(PhoneNumber.FORMAT_DIN5008_INTERNATIONAL_WITH_POS)
  RestAction<ValueWithPos<String>> formatDin5008InternationalWithPos(
      final ValueWithPosAndCountry<String> pphoneNumber);

  @GET
  @Path(PhoneNumber.FORMAT_DIN5008_NATIONAL)
  RestAction<String> formatDin5008National(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber);

  @PUT
  @Path(PhoneNumber.FORMAT_DIN5008_NATIONAL_WITH_POS)
  RestAction<ValueWithPos<String>> formatDin5008NationalWithPos(
      final ValueWithPosAndCountry<String> pphoneNumber);


  @GET
  @Path(PhoneNumber.FORMAT_RFC3966)
  RestAction<String> formatRfc3966(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber);

  @PUT
  @Path(PhoneNumber.FORMAT_RFC3966_WITH_POS)
  RestAction<ValueWithPos<String>> formatRfc3966WithPos(
      final ValueWithPosAndCountry<String> pphoneNumber);


  @GET
  @Path(PhoneNumber.FORMAT_MS)
  RestAction<String> formatMs(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber);

  @PUT
  @Path(PhoneNumber.FORMAT_MS_WITH_POS)
  RestAction<ValueWithPos<String>> formatMsWithPos(
      final ValueWithPosAndCountry<String> pphoneNumber);


  @GET
  @Path(PhoneNumber.FORMAT_URL)
  RestAction<String> formatUrl(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber);

  @PUT
  @Path(PhoneNumber.FORMAT_URL_WITH_POS)
  RestAction<ValueWithPos<String>> formatUrlWithPos(
      final ValueWithPosAndCountry<String> pphoneNumber);


  @GET
  @Path(PhoneNumber.FORMAT_COMMON)
  RestAction<String> formatCommon(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber);

  @PUT
  @Path(PhoneNumber.FORMAT_COMMON_WITH_POS)
  RestAction<ValueWithPos<String>> formatCommonWithPos(
      final ValueWithPosAndCountry<String> pphoneNumber);


  @GET
  @Path(PhoneNumber.FORMAT_COMMON_INTERNATIONAL)
  RestAction<String> formatCommonInternational(
      @QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber);

  @PUT
  @Path(PhoneNumber.FORMAT_COMMON_INTERNATIONAL_WITH_POS)
  RestAction<ValueWithPos<String>> formatCommonInternationalWithPos(
      final ValueWithPosAndCountry<String> pphoneNumber);

  @GET
  @Path(PhoneNumber.FORMAT_COMMON_NATIONAL)
  RestAction<String> formatCommonNational(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber);

  @PUT
  @Path(PhoneNumber.FORMAT_COMMON_NATIONAL_WITH_POS)
  RestAction<ValueWithPos<String>> formatCommonNationalWithPos(
      final ValueWithPosAndCountry<String> pphoneNumber);

  @GET
  @Path(PhoneNumber.GET_SUGGESTIONS)
  RestAction<List<PhoneNumberData>> getSuggestions(
      @QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.SEARCH) final String psearch,
      @QueryParam(Parameters.LIMIT) final int plimit);


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
  RestAction<Boolean> validate(@QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber,
      @QueryParam(Parameters.DIN_5008) final Boolean pdin5008,
      @QueryParam(Parameters.E123) final Boolean pe123,
      @QueryParam(Parameters.URI) final Boolean puri, @QueryParam(Parameters.MS) final Boolean pms,
      @QueryParam(Parameters.COMMON) final Boolean pcommon);
}
