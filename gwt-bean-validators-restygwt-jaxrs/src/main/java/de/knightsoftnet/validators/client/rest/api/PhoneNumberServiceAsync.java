package de.knightsoftnet.validators.client.rest.api;

import de.knightsoftnet.validators.shared.Parameters;
import de.knightsoftnet.validators.shared.ResourcePaths;
import de.knightsoftnet.validators.shared.ResourcePaths.PhoneNumber;
import de.knightsoftnet.validators.shared.data.PhoneNumberData;
import de.knightsoftnet.validators.shared.data.PhoneNumberDataWithFormats;
import de.knightsoftnet.validators.shared.data.ValueWithPos;
import de.knightsoftnet.validators.shared.data.ValueWithPosAndCountry;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path(ResourcePaths.API_BASE_DIR + PhoneNumber.ROOT)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface PhoneNumberServiceAsync extends RestService {

  @GET
  @Path(PhoneNumber.PARSE_PHONE_NUMBER)
  void parsePhoneNumber(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber,
      MethodCallback<PhoneNumberData> pcallback);

  @PUT
  @Path(PhoneNumber.PARSE_WITH_POS)
  void parsePhoneNumber(final ValueWithPosAndCountry<String> pphoneNumber,
      MethodCallback<ValueWithPos<PhoneNumberData>> pcallback);


  @GET
  @Path(PhoneNumber.PARSE_AND_FORMAT)
  void parseAndFormatPhoneNumber(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber,
      MethodCallback<PhoneNumberDataWithFormats> pcallback);


  @GET
  @Path(PhoneNumber.FORMAT_E123)
  void formatE123(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber,
      MethodCallback<String> pcallback);

  @PUT
  @Path(PhoneNumber.FORMAT_E123_WITH_POS)
  void formatE123WithPos(final ValueWithPosAndCountry<String> pphoneNumber,
      MethodCallback<ValueWithPos<String>> pcallback);

  @GET
  @Path(PhoneNumber.FORMAT_E123_INTERNATIONAL)
  void formatE123International(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber,
      MethodCallback<String> pcallback);

  @PUT
  @Path(PhoneNumber.FORMAT_E123_INTERNATIONAL_WITH_POS)
  void formatE123InternationalWithPos(final ValueWithPosAndCountry<String> pphoneNumber,
      MethodCallback<ValueWithPos<String>> pcallback);

  @GET
  @Path(PhoneNumber.FORMAT_E123_NATIONAL)
  void formatE123National(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber,
      MethodCallback<String> pcallback);

  @PUT
  @Path(PhoneNumber.FORMAT_E123_NATIONAL_WITH_POS)
  void formatE123NationalWithPos(final ValueWithPosAndCountry<String> pphoneNumber,
      MethodCallback<ValueWithPos<String>> pcallback);


  @GET
  @Path(PhoneNumber.FORMAT_DIN5008)
  void formatDin5008(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber,
      MethodCallback<String> pcallback);

  @PUT
  @Path(PhoneNumber.FORMAT_DIN5008_WITH_POS)
  void formatDin5008WithPos(final ValueWithPosAndCountry<String> pphoneNumber,
      MethodCallback<ValueWithPos<String>> pcallback);

  @GET
  @Path(PhoneNumber.FORMAT_DIN5008_INTERNATIONAL)
  void formatDin5008International(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber,
      MethodCallback<String> pcallback);

  @PUT
  @Path(PhoneNumber.FORMAT_DIN5008_INTERNATIONAL_WITH_POS)
  void formatDin5008InternationalWithPos(final ValueWithPosAndCountry<String> pphoneNumber,
      MethodCallback<ValueWithPos<String>> pcallback);

  @GET
  @Path(PhoneNumber.FORMAT_DIN5008_NATIONAL)
  void formatDin5008National(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber,
      MethodCallback<String> pcallback);

  @PUT
  @Path(PhoneNumber.FORMAT_DIN5008_NATIONAL_WITH_POS)
  void formatDin5008NationalWithPos(final ValueWithPosAndCountry<String> pphoneNumber,
      MethodCallback<ValueWithPos<String>> pcallback);


  @GET
  @Path(PhoneNumber.FORMAT_RFC3966)
  void formatRfc3966(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber,
      MethodCallback<String> pcallback);

  @PUT
  @Path(PhoneNumber.FORMAT_RFC3966_WITH_POS)
  void formatRfc3966WithPos(final ValueWithPosAndCountry<String> pphoneNumber,
      MethodCallback<ValueWithPos<String>> pcallback);


  @GET
  @Path(PhoneNumber.FORMAT_MS)
  void formatMs(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber,
      MethodCallback<String> pcallback);

  @PUT
  @Path(PhoneNumber.FORMAT_MS_WITH_POS)
  void formatMsWithPos(final ValueWithPosAndCountry<String> pphoneNumber,
      MethodCallback<ValueWithPos<String>> pcallback);


  @GET
  @Path(PhoneNumber.FORMAT_URL)
  void formatUrl(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber,
      MethodCallback<String> pcallback);

  @PUT
  @Path(PhoneNumber.FORMAT_URL_WITH_POS)
  void formatUrlWithPos(final ValueWithPosAndCountry<String> pphoneNumber,
      MethodCallback<ValueWithPos<String>> pcallback);


  @GET
  @Path(PhoneNumber.FORMAT_COMMON)
  void formatCommon(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber,
      MethodCallback<String> pcallback);

  @PUT
  @Path(PhoneNumber.FORMAT_COMMON_WITH_POS)
  void formatCommonWithPos(final ValueWithPosAndCountry<String> pphoneNumber,
      MethodCallback<ValueWithPos<String>> pcallback);

  @GET
  @Path(PhoneNumber.FORMAT_COMMON_INTERNATIONAL)
  void formatCommonInternational(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber,
      MethodCallback<String> pcallback);

  @PUT
  @Path(PhoneNumber.FORMAT_COMMON_INTERNATIONAL_WITH_POS)
  void formatCommonInternationalWithPos(final ValueWithPosAndCountry<String> pphoneNumber,
      MethodCallback<ValueWithPos<String>> pcallback);

  @GET
  @Path(PhoneNumber.FORMAT_COMMON_NATIONAL)
  void formatCommonNational(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber,
      MethodCallback<String> pcallback);

  @PUT
  @Path(PhoneNumber.FORMAT_COMMON_NATIONAL_WITH_POS)
  void formatCommonNationalWithPos(final ValueWithPosAndCountry<String> pphoneNumber,
      MethodCallback<ValueWithPos<String>> pcallback);

  @GET
  @Path(PhoneNumber.GET_SUGGESTIONS)
  void getSuggestions(@QueryParam(Parameters.LANGUAGE) final String planguage,
      @QueryParam(Parameters.SEARCH) final String psearch,
      @QueryParam(Parameters.LIMIT) final int plimit,
      MethodCallback<List<PhoneNumberData>> pcallback);


  @GET
  @Path(PhoneNumber.VALIDATE)
  void validate(@QueryParam(Parameters.COUNTRY) final String pcountry,
      @QueryParam(Parameters.PHONE_NUMBER) final String pphoneNumber,
      @QueryParam(Parameters.DIN_5008) final Boolean pdin5008,
      @QueryParam(Parameters.E123) final Boolean pe123,
      @QueryParam(Parameters.URI) final Boolean puri, @QueryParam(Parameters.MS) final Boolean pms,
      @QueryParam(Parameters.COMMON) final Boolean pcommon, MethodCallback<Boolean> pcallback);
}
