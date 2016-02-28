package de.knightsoftnet.validators.client.rest.api;

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

@Path("/api/phonenumber")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface PhoneNumberServiceAsync extends RestService {

  @GET
  @Path("parse")
  void parsePhoneNumber(@QueryParam("language") final String planguage,
      @QueryParam("country") final String pcountry,
      @QueryParam("phonenumber") final String pphoneNumber,
      MethodCallback<PhoneNumberData> pcallback);

  @PUT
  @Path("parsewithpos")
  void parsePhoneNumber(final ValueWithPosAndCountry<String> pphoneNumber,
      MethodCallback<ValueWithPos<PhoneNumberData>> pcallback);


  @GET
  @Path("parseandformat")
  void parseAndFormatPhoneNumber(@QueryParam("language") final String planguage,
      @QueryParam("country") final String pcountry,
      @QueryParam("phonenumber") final String pphoneNumber,
      MethodCallback<PhoneNumberDataWithFormats> pcallback);


  @GET
  @Path("formate123")
  void formatE123(@QueryParam("language") final String planguage,
      @QueryParam("country") final String pcountry,
      @QueryParam("phonenumber") final String pphoneNumber, MethodCallback<String> pcallback);

  @PUT
  @Path("formate123withpos")
  void formatE123WithPos(final ValueWithPosAndCountry<String> pphoneNumber,
      MethodCallback<ValueWithPos<String>> pcallback);

  @GET
  @Path("formate123international")
  void formatE123International(@QueryParam("language") final String planguage,
      @QueryParam("country") final String pcountry,
      @QueryParam("phonenumber") final String pphoneNumber, MethodCallback<String> pcallback);

  @PUT
  @Path("formate123internationalwithpos")
  void formatE123InternationalWithPos(final ValueWithPosAndCountry<String> pphoneNumber,
      MethodCallback<ValueWithPos<String>> pcallback);

  @GET
  @Path("formate123national")
  void formatE123National(@QueryParam("language") final String planguage,
      @QueryParam("country") final String pcountry,
      @QueryParam("phonenumber") final String pphoneNumber, MethodCallback<String> pcallback);

  @PUT
  @Path("formate123nationalwithpos")
  void formatE123NationalWithPos(final ValueWithPosAndCountry<String> pphoneNumber,
      MethodCallback<ValueWithPos<String>> pcallback);


  @GET
  @Path("formatdin5008")
  void formatDin5008(@QueryParam("language") final String planguage,
      @QueryParam("country") final String pcountry,
      @QueryParam("phonenumber") final String pphoneNumber, MethodCallback<String> pcallback);

  @PUT
  @Path("formatdin5008withpos")
  void formatDin5008WithPos(final ValueWithPosAndCountry<String> pphoneNumber,
      MethodCallback<ValueWithPos<String>> pcallback);

  @GET
  @Path("formatdin5008international")
  void formatDin5008International(@QueryParam("language") final String planguage,
      @QueryParam("country") final String pcountry,
      @QueryParam("phonenumber") final String pphoneNumber, MethodCallback<String> pcallback);

  @PUT
  @Path("formatdin5008internationalwithpos")
  void formatDin5008InternationalWithPos(final ValueWithPosAndCountry<String> pphoneNumber,
      MethodCallback<ValueWithPos<String>> pcallback);

  @GET
  @Path("formatdin5008national")
  void formatDin5008National(@QueryParam("language") final String planguage,
      @QueryParam("country") final String pcountry,
      @QueryParam("phonenumber") final String pphoneNumber, MethodCallback<String> pcallback);

  @PUT
  @Path("formatdin5008nationalwithpos")
  void formatDin5008NationalWithPos(final ValueWithPosAndCountry<String> pphoneNumber,
      MethodCallback<ValueWithPos<String>> pcallback);


  @GET
  @Path("formatrfc3966")
  void formatRfc3966(@QueryParam("language") final String planguage,
      @QueryParam("country") final String pcountry,
      @QueryParam("phonenumber") final String pphoneNumber, MethodCallback<String> pcallback);

  @PUT
  @Path("formatrfc3966withpos")
  void formatRfc3966WithPos(final ValueWithPosAndCountry<String> pphoneNumber,
      MethodCallback<ValueWithPos<String>> pcallback);


  @GET
  @Path("formatms")
  void formatMs(@QueryParam("language") final String planguage,
      @QueryParam("country") final String pcountry,
      @QueryParam("phonenumber") final String pphoneNumber, MethodCallback<String> pcallback);

  @PUT
  @Path("formatmswithpos")
  void formatMsWithPos(final ValueWithPosAndCountry<String> pphoneNumber,
      MethodCallback<ValueWithPos<String>> pcallback);


  @GET
  @Path("formaturl")
  void formatUrl(@QueryParam("language") final String planguage,
      @QueryParam("country") final String pcountry,
      @QueryParam("phonenumber") final String pphoneNumber, MethodCallback<String> pcallback);

  @PUT
  @Path("formaturlwithpos")
  void formatUrlWithPos(final ValueWithPosAndCountry<String> pphoneNumber,
      MethodCallback<ValueWithPos<String>> pcallback);


  @GET
  @Path("formatcommon")
  void formatCommon(@QueryParam("language") final String planguage,
      @QueryParam("country") final String pcountry,
      @QueryParam("phonenumber") final String pphoneNumber, MethodCallback<String> pcallback);

  @PUT
  @Path("formatcommonwithpos")
  void formatCommonWithPos(final ValueWithPosAndCountry<String> pphoneNumber,
      MethodCallback<ValueWithPos<String>> pcallback);

  @GET
  @Path("formatcommoninternational")
  void formatCommonInternational(@QueryParam("language") final String planguage,
      @QueryParam("country") final String pcountry,
      @QueryParam("phonenumber") final String pphoneNumber, MethodCallback<String> pcallback);

  @PUT
  @Path("formatcommoninternationalwithpos")
  void formatCommonInternationalWithPos(final ValueWithPosAndCountry<String> pphoneNumber,
      MethodCallback<ValueWithPos<String>> pcallback);

  @GET
  @Path("formatcommonnational")
  void formatCommonNational(@QueryParam("language") final String planguage,
      @QueryParam("country") final String pcountry,
      @QueryParam("phonenumber") final String pphoneNumber, MethodCallback<String> pcallback);

  @PUT
  @Path("formatcommonnationalwithpos")
  void formatCommonNationalWithPos(final ValueWithPosAndCountry<String> pphoneNumber,
      MethodCallback<ValueWithPos<String>> pcallback);

  @GET
  @Path("getsuggestions")
  void getSuggestions(@QueryParam("language") final String planguage,
      @QueryParam("search") final String psearch, @QueryParam("limit") final int plimit,
      MethodCallback<List<PhoneNumberData>> pcallback);


  @GET
  @Path("validate")
  void validate(@QueryParam("country") final String pcountry,
      @QueryParam("phonenumber") final String pphoneNumber,
      @QueryParam("din5008") final Boolean pdin5008, @QueryParam("e123") final Boolean pe123,
      @QueryParam("uri") final Boolean puri, @QueryParam("ms") final Boolean pms,
      @QueryParam("common") final Boolean pcommon, MethodCallback<Boolean> pcallback);
}
