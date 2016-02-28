package de.knightsoftnet.validators.server.rest.api;

import de.knightsoftnet.validators.shared.data.PhoneNumberData;
import de.knightsoftnet.validators.shared.data.PhoneNumberDataWithFormats;
import de.knightsoftnet.validators.shared.data.ValueWithPos;
import de.knightsoftnet.validators.shared.data.ValueWithPosAndCountry;
import de.knightsoftnet.validators.shared.util.PhoneNumberUtil;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("phonenumber")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PhoneNumberService {

  /**
   * phone number utils to test phone numbers.
   */
  private final PhoneNumberUtil phoneNumberUtil = new PhoneNumberUtil();

  @GET
  @Path("parse")
  public PhoneNumberData parsePhoneNumber(@QueryParam("language") final String planguage,
      @QueryParam("country") final String pcountry,
      @QueryParam("phonenumber") final String pphoneNumber) {
    return this.phoneNumberUtil.parsePhoneNumber(pphoneNumber, pcountry);
  }


  @PUT
  @Path("parsewithpos")
  public ValueWithPos<PhoneNumberData> parsePhoneNumber(
      final ValueWithPosAndCountry<String> pphoneNumber) {
    return this.phoneNumberUtil.parsePhoneNumber(pphoneNumber, pphoneNumber.getCountry());
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
  @Path("parseandformat")
  public PhoneNumberDataWithFormats parseAndFormatPhoneNumber(
      @QueryParam("language") final String planguage, @QueryParam("country") final String pcountry,
      @QueryParam("phonenumber") final String pphoneNumber) {
    final PhoneNumberDataWithFormats result = new PhoneNumberDataWithFormats(
        this.phoneNumberUtil.parsePhoneNumber(pphoneNumber, pcountry));
    result.setCommonInternational(this.phoneNumberUtil.formatCommonInternational(result));
    result.setCommonNational(this.phoneNumberUtil.formatCommonNational(result));
    result.setDin5008International(this.phoneNumberUtil.formatDin5008International(result));
    result.setDin5008National(this.phoneNumberUtil.formatDin5008National(result));
    result.setE123International(this.phoneNumberUtil.formatE123International(result));
    result.setE123National(this.phoneNumberUtil.formatE123National(result));
    result.setMs(this.phoneNumberUtil.formatMs(result));
    result.setUrl(this.phoneNumberUtil.formatUrl(result));
    return result;
  }


  @GET
  @Path("formate123")
  public String formatE123(@QueryParam("language") final String planguage,
      @QueryParam("country") final String pcountry,
      @QueryParam("phonenumber") final String pphoneNumber) {
    return this.phoneNumberUtil.formatE123(pphoneNumber, pcountry);
  }

  @PUT
  @Path("formate123withpos")
  public ValueWithPos<String> formatE123WithPos(final ValueWithPosAndCountry<String> pphoneNumber) {
    return this.phoneNumberUtil.formatE123WithPos(pphoneNumber, pphoneNumber.getCountry());
  }

  @GET
  @Path("formate123international")
  public String formatE123International(@QueryParam("language") final String planguage,
      @QueryParam("country") final String pcountry,
      @QueryParam("phonenumber") final String pphoneNumber) {
    return this.phoneNumberUtil.formatE123International(pphoneNumber, pcountry);
  }

  @PUT
  @Path("formate123internationalwithpos")
  public ValueWithPos<String> formatE123InternationalWithPos(
      final ValueWithPosAndCountry<String> pphoneNumber) {
    return this.phoneNumberUtil.formatE123InternationalWithPos(pphoneNumber,
        pphoneNumber.getCountry());
  }

  @GET
  @Path("formate123national")
  public String formatE123National(@QueryParam("language") final String planguage,
      @QueryParam("country") final String pcountry,
      @QueryParam("phonenumber") final String pphoneNumber) {
    return this.phoneNumberUtil.formatE123National(pphoneNumber, pcountry);
  }

  @PUT
  @Path("formate123nationalwithpos")
  public ValueWithPos<String> formatE123NationalWithPos(
      final ValueWithPosAndCountry<String> pphoneNumber) {
    return this.phoneNumberUtil.formatE123NationalWithPos(pphoneNumber, pphoneNumber.getCountry());
  }


  @GET
  @Path("formatdin5008")
  public String formatDin5008(@QueryParam("language") final String planguage,
      @QueryParam("country") final String pcountry,
      @QueryParam("phonenumber") final String pphoneNumber) {
    return this.phoneNumberUtil.formatDin5008(pphoneNumber, pcountry);
  }

  @PUT
  @Path("formatdin5008withpos")
  public ValueWithPos<String> formatDin5008WithPos(
      final ValueWithPosAndCountry<String> pphoneNumber) {
    return this.phoneNumberUtil.formatDin5008WithPos(pphoneNumber, pphoneNumber.getCountry());
  }

  @GET
  @Path("formatdin5008international")
  public String formatDin5008International(@QueryParam("language") final String planguage,
      @QueryParam("country") final String pcountry,
      @QueryParam("phonenumber") final String pphoneNumber) {
    return this.phoneNumberUtil.formatDin5008International(pphoneNumber, pcountry);
  }

  @PUT
  @Path("formatdin5008internationalwithpos")
  public ValueWithPos<String> formatDin5008InternationalWithPos(
      final ValueWithPosAndCountry<String> pphoneNumber) {
    return this.phoneNumberUtil.formatDin5008InternationalWithPos(pphoneNumber,
        pphoneNumber.getCountry());
  }

  @GET
  @Path("formatdin5008national")
  public String formatDin5008National(@QueryParam("language") final String planguage,
      @QueryParam("country") final String pcountry,
      @QueryParam("phonenumber") final String pphoneNumber) {
    return this.phoneNumberUtil.formatDin5008National(pphoneNumber, pcountry);
  }

  @PUT
  @Path("formatdin5008nationalwithpos")
  public ValueWithPos<String> formatDin5008NationalWithPos(
      final ValueWithPosAndCountry<String> pphoneNumber) {
    return this.phoneNumberUtil.formatDin5008NationalWithPos(pphoneNumber,
        pphoneNumber.getCountry());
  }


  @GET
  @Path("formatrfc3966")
  public String formatRfc3966(@QueryParam("language") final String planguage,
      @QueryParam("country") final String pcountry,
      @QueryParam("phonenumber") final String pphoneNumber) {
    return this.phoneNumberUtil.formatRfc3966(pphoneNumber, pcountry);
  }

  @PUT
  @Path("formatrfc3966withpos")
  public ValueWithPos<String> formatRfc3966WithPos(
      final ValueWithPosAndCountry<String> pphoneNumber) {
    return this.phoneNumberUtil.formatRfc3966WithPos(pphoneNumber, pphoneNumber.getCountry());
  }


  @GET
  @Path("formatms")
  public String formatMs(@QueryParam("language") final String planguage,
      @QueryParam("country") final String pcountry,
      @QueryParam("phonenumber") final String pphoneNumber) {
    return this.phoneNumberUtil.formatMs(pphoneNumber, pcountry);
  }

  @PUT
  @Path("formatmswithpos")
  public ValueWithPos<String> formatMsWithPos(final ValueWithPosAndCountry<String> pphoneNumber) {
    return this.phoneNumberUtil.formatMsWithPos(pphoneNumber, pphoneNumber.getCountry());
  }


  @GET
  @Path("formaturl")
  public String formatUrl(@QueryParam("language") final String planguage,
      @QueryParam("country") final String pcountry,
      @QueryParam("phonenumber") final String pphoneNumber) {
    return this.phoneNumberUtil.formatUrl(pphoneNumber, pcountry);
  }

  @PUT
  @Path("formaturlwithpos")
  public ValueWithPos<String> formatUrlWithPos(final ValueWithPosAndCountry<String> pphoneNumber) {
    return this.phoneNumberUtil.formatUrlWithPos(pphoneNumber, pphoneNumber.getCountry());
  }


  @GET
  @Path("formatcommon")
  public String formatCommon(@QueryParam("language") final String planguage,
      @QueryParam("country") final String pcountry,
      @QueryParam("phonenumber") final String pphoneNumber) {
    return this.phoneNumberUtil.formatCommon(pphoneNumber, pcountry);
  }

  @PUT
  @Path("formatcommonwithpos")
  public ValueWithPos<String> formatCommonWithPos(
      final ValueWithPosAndCountry<String> pphoneNumber) {
    return this.phoneNumberUtil.formatCommonWithPos(pphoneNumber, pphoneNumber.getCountry());
  }

  @GET
  @Path("formatcommoninternational")
  public String formatCommonInternational(@QueryParam("language") final String planguage,
      @QueryParam("country") final String pcountry,
      @QueryParam("phonenumber") final String pphoneNumber) {
    return this.phoneNumberUtil.formatCommonInternational(pphoneNumber, pcountry);
  }

  @PUT
  @Path("formatcommoninternationalwithpos")
  public ValueWithPos<String> formatCommonInternationalWithPos(
      final ValueWithPosAndCountry<String> pphoneNumber) {
    return this.phoneNumberUtil.formatCommonInternationalWithPos(pphoneNumber,
        pphoneNumber.getCountry());
  }

  @GET
  @Path("formatcommonnational")
  public String formatCommonNational(@QueryParam("language") final String planguage,
      @QueryParam("country") final String pcountry,
      @QueryParam("phonenumber") final String pphoneNumber) {
    return this.phoneNumberUtil.formatCommonNational(pphoneNumber, pcountry);
  }

  @PUT
  @Path("formatcommonnationalwithpos")
  public ValueWithPos<String> formatCommonNationalWithPos(
      final ValueWithPosAndCountry<String> pphoneNumber) {
    return this.phoneNumberUtil.formatCommonNationalWithPos(pphoneNumber,
        pphoneNumber.getCountry());
  }

  @GET
  @Path("getsuggestions")
  public List<PhoneNumberData> getSuggestions(@QueryParam("language") final String planguage,
      @QueryParam("search") final String psearch, @QueryParam("limit") final int plimit) {
    return this.phoneNumberUtil.getSuggstions(psearch, plimit);
  }
}
