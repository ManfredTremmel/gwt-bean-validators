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

import de.knightsoftnet.validators.server.data.CreateClass;
import de.knightsoftnet.validators.shared.data.PhoneAreaCodeData;
import de.knightsoftnet.validators.shared.data.PhoneCountryCodeData;
import de.knightsoftnet.validators.shared.data.PhoneCountryData;
import de.knightsoftnet.validators.shared.data.PhoneCountrySharedConstants;
import de.knightsoftnet.validators.shared.data.PhoneNumberData;
import de.knightsoftnet.validators.shared.data.PhoneNumberExtendedInterface;
import de.knightsoftnet.validators.shared.data.PhoneNumberInterface;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Phone Number Util, format and parse phone numbers.
 *
 * @author Manfred Tremmel
 *
 */
public class PhoneNumberUtil {
  private static final PhoneCountrySharedConstants COUNTRY_CONSTANTS =
      CreateClass.create(PhoneCountrySharedConstants.class);
  private PhoneCountryData defaultCountryData;

  public PhoneNumberUtil() {
    this(null);
  }

  /**
   * constructor with default country.
   *
   * @param pcountryCode iso code of country
   */
  public PhoneNumberUtil(final String pcountryCode) {
    super();
    this.setCountryCode(pcountryCode);
  }

  /**
   * set country code.
   *
   * @param pcountryCode iso code of country
   */
  public final void setCountryCode(final String pcountryCode) {
    if (StringUtils.isEmpty(pcountryCode)) {
      this.defaultCountryData = null;
    } else {
      this.defaultCountryData = PhoneNumberUtil.COUNTRY_CONSTANTS.countryMap().get(pcountryCode);
    }
  }


  /**
   * parse phone number.
   *
   * @param pphoneNumber phone number as string
   * @return PhoneNumberData
   */
  public PhoneNumberInterface parsePhoneNumber(final String pphoneNumber) {
    return this.parsePhoneNumber(pphoneNumber, new PhoneNumberData(), this.defaultCountryData);
  }

  /**
   * parse phone number.
   *
   * @param pphoneNumber phone number as string
   * @param pcountryCode iso code of country
   * @return PhoneNumberData
   */
  public PhoneNumberInterface parsePhoneNumber(final String pphoneNumber,
      final String pcountryCode) {
    return this.parsePhoneNumber(pphoneNumber, new PhoneNumberData(),
        PhoneNumberUtil.COUNTRY_CONSTANTS.countryMap()
            .get(StringUtils.defaultString(pcountryCode)));
  }


  /**
   * parse phone number.
   *
   * @param pphoneNumber phone number as string
   * @param pphoneNumberData phone number data to fill
   * @return PhoneNumberData, the same as in second parameter
   */
  public PhoneNumberInterface parsePhoneNumber(final String pphoneNumber,
      final PhoneNumberInterface pphoneNumberData) {
    return this.parsePhoneNumber(pphoneNumber, pphoneNumberData, this.defaultCountryData);
  }

  /**
   * parse phone number.
   *
   * @param pphoneNumber phone number as string
   * @param pphoneNumberData phone number data to fill
   * @param pcountryCode iso code of country
   * @return PhoneNumberData, the same as in second parameter
   */
  public PhoneNumberInterface parsePhoneNumber(final String pphoneNumber,
      final PhoneNumberInterface pphoneNumberData, final String pcountryCode) {
    return this.parsePhoneNumber(pphoneNumber, pphoneNumberData, PhoneNumberUtil.COUNTRY_CONSTANTS
        .countryMap().get(StringUtils.defaultString(pcountryCode)));
  }


  /**
   * parse phone number.
   *
   * @param pphoneNumber phone number as string
   * @param pphoneNumberData phone number data to fill
   * @param pcountryData country data
   * @return PhoneNumberData, the same as in second parameter
   */
  public PhoneNumberInterface parsePhoneNumber(final String pphoneNumber,
      final PhoneNumberInterface pphoneNumberData, final PhoneCountryData pcountryData) {
    if (pphoneNumber == null || pphoneNumberData == null) {
      return null;
    }
    pphoneNumberData.setCountryCode(null);
    pphoneNumberData.setAreaCode(null);
    pphoneNumberData.setLineNumber(null);
    pphoneNumberData.setExtension(null);
    final StringBuilder cleanupString = new StringBuilder(pphoneNumber.length());
    final boolean containsMinus = StringUtils.contains(pphoneNumber, '-');
    boolean hasSeperator = false;
    for (final char character : StringUtils.reverse(pphoneNumber).toCharArray()) {
      switch (character) {
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
          cleanupString.append(character);
          break;
        case '-':
          if (!hasSeperator) {
            cleanupString.append(character);
            hasSeperator = true;
          }
          break;
        case ' ':
          if (!hasSeperator && !containsMinus && cleanupString.length() <= 5) {
            cleanupString.append('-');
            hasSeperator = true;
          }
          break;
        default:
          // ignore all other characters
          break;
      }
    }
    String phoneNumberWork = StringUtils.reverse(cleanupString.toString());
    if (pcountryData != null) {
      if (StringUtils.isNotEmpty(pcountryData.getExitCode())
          && phoneNumberWork.startsWith(pcountryData.getExitCode())) {
        phoneNumberWork = phoneNumberWork.substring(pcountryData.getExitCode().length());
      } else if (StringUtils.isNotEmpty(pcountryData.getTrunkCode())
          && phoneNumberWork.startsWith(pcountryData.getTrunkCode())) {
        phoneNumberWork = pcountryData.getCountryCodeData().getCountryCode()
            + phoneNumberWork.substring(pcountryData.getTrunkCode().length());
      }
    }
    for (final PhoneCountryCodeData countryCode : PhoneNumberUtil.COUNTRY_CONSTANTS
        .countryCodeData()) {
      if (phoneNumberWork.startsWith(countryCode.getCountryCode())) {
        pphoneNumberData.setCountryCode(countryCode.getCountryCode());
        if (pphoneNumberData instanceof PhoneNumberExtendedInterface) {
          ((PhoneNumberExtendedInterface) pphoneNumberData)
              .setCountryName(countryCode.getCountryCodeName());
        }
        phoneNumberWork = phoneNumberWork.substring(countryCode.getCountryCode().length());
        if (phoneNumberWork.charAt(0) == '-') {
          phoneNumberWork = phoneNumberWork.substring(1);
        }
        if (countryCode.getPhoneCountryData() != null
            && StringUtils.isNotEmpty(countryCode.getPhoneCountryData().getTrunkCode())
            && phoneNumberWork.startsWith(countryCode.getPhoneCountryData().getTrunkCode())) {
          phoneNumberWork =
              phoneNumberWork.substring(countryCode.getPhoneCountryData().getTrunkCode().length());
        }
        for (final PhoneAreaCodeData areaCode : countryCode.getAreaCodeData()) {
          if (areaCode.isRegEx() && phoneNumberWork.matches("^" + areaCode.getAreaCode() + ".*")) {
            final String areaCodeRemember = phoneNumberWork;
            phoneNumberWork =
                phoneNumberWork.replaceFirst(areaCode.getAreaCode(), StringUtils.EMPTY);
            pphoneNumberData.setAreaCode(areaCodeRemember.substring(0,
                areaCodeRemember.length() - phoneNumberWork.length()));
            if (pphoneNumberData instanceof PhoneNumberExtendedInterface) {
              ((PhoneNumberExtendedInterface) pphoneNumberData).setAreaName(areaCode.getAreaName());
            }
            break;
          } else if (!areaCode.isRegEx() && phoneNumberWork.startsWith(areaCode.getAreaCode())) {
            pphoneNumberData.setAreaCode(areaCode.getAreaCode());
            if (pphoneNumberData instanceof PhoneNumberExtendedInterface) {
              ((PhoneNumberExtendedInterface) pphoneNumberData).setAreaName(areaCode.getAreaName());
            }
            phoneNumberWork = phoneNumberWork.substring(areaCode.getAreaCode().length());
            break;
          }
        }
        if (StringUtils.isNotEmpty(phoneNumberWork) && phoneNumberWork.charAt(0) == '-') {
          phoneNumberWork = phoneNumberWork.substring(1);
        }
        if (phoneNumberWork.contains("-")) {
          final String[] splitedPhoneNumber = phoneNumberWork.split("-");
          pphoneNumberData.setLineNumber(splitedPhoneNumber[0]);
          pphoneNumberData.setExtension(splitedPhoneNumber[1]);
        } else {
          pphoneNumberData.setLineNumber(phoneNumberWork);
        }
        break;
      }
    }
    return pphoneNumberData;
  }

  /**
   * format phone number in E123 format.
   *
   * @param pphoneNumber phone number as String to format
   * @return formated phone number as String
   */
  public final String formatE123(final String pphoneNumber) {
    return this.formatE123(this.parsePhoneNumber(pphoneNumber), this.defaultCountryData);
  }

  /**
   * format phone number in E123 format.
   *
   * @param pphoneNumber phone number as String to format
   * @param pcountryCode iso code of country
   * @return formated phone number as String
   */
  public final String formatE123(final String pphoneNumber, final String pcountryCode) {
    return this.formatE123(this.parsePhoneNumber(pphoneNumber), PhoneNumberUtil.COUNTRY_CONSTANTS
        .countryMap().get(StringUtils.defaultString(pcountryCode)));
  }

  /**
   * format phone number in E123 format.
   *
   * @param pphoneNumberData phone number to format
   * @return formated phone number as String
   */
  public final String formatE123(final PhoneNumberInterface pphoneNumberData) {
    return this.formatE123(pphoneNumberData, this.defaultCountryData);
  }

  /**
   * format phone number in E123 format.
   *
   * @param pphoneNumberData phone number to format
   * @param pcountryCode iso code of country
   * @return formated phone number as String
   */
  public final String formatE123(final PhoneNumberInterface pphoneNumberData,
      final String pcountryCode) {
    return this.formatE123(pphoneNumberData, PhoneNumberUtil.COUNTRY_CONSTANTS.countryMap()
        .get(StringUtils.defaultString(pcountryCode)));
  }

  /**
   * format phone number in E123 format.
   *
   * @param pphoneNumberData phone number to format
   * @param pcountryData country data
   * @return formated phone number as String
   */
  public final String formatE123(final PhoneNumberInterface pphoneNumberData,
      final PhoneCountryData pcountryData) {
    if (pphoneNumberData != null && pcountryData != null && StringUtils.equals(
        pcountryData.getCountryCodeData().getCountryCode(), pphoneNumberData.getCountryCode())) {
      return this.formatE123National(pphoneNumberData);
    } else {
      return this.formatE123International(pphoneNumberData);
    }
  }

  /**
   * format phone number in E123 international format.
   *
   * @param pphoneNumber phone number as String to format
   * @return formated phone number as String
   */
  public final String formatE123International(final String pphoneNumber) {
    return this.formatE123International(this.parsePhoneNumber(pphoneNumber));
  }

  /**
   * format phone number in E123 international format.
   *
   * @param pphoneNumberData phone number to format
   * @return formated phone number as String
   */
  public final String formatE123International(final PhoneNumberInterface pphoneNumberData) {
    final StringBuilder resultNumber = new StringBuilder();
    if (this.isPhoneNumberNotEmpty(pphoneNumberData)) {
      resultNumber.append('+').append(pphoneNumberData.getCountryCode()).append(' ');
      if (StringUtils.isNotBlank(pphoneNumberData.getAreaCode())) {
        resultNumber.append(pphoneNumberData.getAreaCode()).append(' ');
      }
      resultNumber.append(pphoneNumberData.getLineNumber());
      if (StringUtils.isNotBlank(pphoneNumberData.getExtension())) {
        resultNumber.append(pphoneNumberData.getExtension());
      }
    }
    return StringUtils.trimToNull(resultNumber.toString());
  }

  /**
   * format phone number in E123 national format.
   *
   * @param pphoneNumber phone number as String to format
   * @return formated phone number as String
   */
  public final String formatE123National(final String pphoneNumber) {
    return this.formatE123National(this.parsePhoneNumber(pphoneNumber));
  }

  /**
   * format phone number in E123 national format.
   *
   * @param pphoneNumberData phone number to format
   * @return formated phone number as String
   */
  public final String formatE123National(final PhoneNumberInterface pphoneNumberData) {
    final StringBuilder resultNumber = new StringBuilder();
    if (this.isPhoneNumberNotEmpty(pphoneNumberData)) {
      PhoneCountryData phoneCountryData = null;
      for (final PhoneCountryCodeData country : PhoneNumberUtil.COUNTRY_CONSTANTS
          .countryCodeData()) {
        if (StringUtils.equals(country.getCountryCode(), pphoneNumberData.getCountryCode())) {
          phoneCountryData = country.getPhoneCountryData();
          break;
        }
      }
      if (phoneCountryData == null) {
        return this.formatE123International(pphoneNumberData);
      }
      resultNumber.append('(').append(phoneCountryData.getTrunkCode());
      if (StringUtils.isNotBlank(pphoneNumberData.getAreaCode())) {
        resultNumber.append(pphoneNumberData.getAreaCode());
      }
      resultNumber.append(") ");
      resultNumber.append(pphoneNumberData.getLineNumber());
      if (StringUtils.isNotBlank(pphoneNumberData.getExtension())) {
        resultNumber.append(' ');
        resultNumber.append(pphoneNumberData.getExtension());
      }
    }
    return StringUtils.trimToNull(resultNumber.toString());
  }

  /**
   * format phone number in DIN 5008 format.
   *
   * @param pphoneNumber phone number as String to format
   * @return formated phone number as String
   */
  public final String formatDin5008(final String pphoneNumber) {
    return this.formatDin5008(this.parsePhoneNumber(pphoneNumber), this.defaultCountryData);
  }

  /**
   * format phone number in DIN 5008 format.
   *
   * @param pphoneNumber phone number as String to format
   * @param pcountryCode iso code of country
   * @return formated phone number as String
   */
  public final String formatDin5008(final String pphoneNumber, final String pcountryCode) {
    return this.formatDin5008(this.parsePhoneNumber(pphoneNumber), PhoneNumberUtil.COUNTRY_CONSTANTS
        .countryMap().get(StringUtils.defaultString(pcountryCode)));
  }

  /**
   * format phone number in DIN 5008 format.
   *
   * @param pphoneNumberData phone number to format
   * @return formated phone number as String
   */
  public final String formatDin5008(final PhoneNumberInterface pphoneNumberData) {
    return this.formatDin5008(pphoneNumberData, this.defaultCountryData);
  }

  /**
   * format phone number in DIN 5008 format.
   *
   * @param pphoneNumberData phone number to format
   * @param pcountryCode iso code of country
   * @return formated phone number as String
   */
  public final String formatDin5008(final PhoneNumberInterface pphoneNumberData,
      final String pcountryCode) {
    return this.formatDin5008(pphoneNumberData, PhoneNumberUtil.COUNTRY_CONSTANTS.countryMap()
        .get(StringUtils.defaultString(pcountryCode)));
  }

  /**
   * format phone number in DIN 5008 format.
   *
   * @param pphoneNumberData phone number to format
   * @param pcountryData country data
   * @return formated phone number as String
   */
  public final String formatDin5008(final PhoneNumberInterface pphoneNumberData,
      final PhoneCountryData pcountryData) {
    if (pphoneNumberData != null && StringUtils.equals(
        pcountryData.getCountryCodeData().getCountryCode(), pphoneNumberData.getCountryCode())) {
      return this.formatDin5008National(pphoneNumberData);
    } else {
      return this.formatDin5008International(pphoneNumberData);
    }
  }

  /**
   * format phone number in DIN 5008 international format.
   *
   * @param pphoneNumber phone number as String to format
   * @return formated phone number as String
   */
  public final String formatDin5008International(final String pphoneNumber) {
    return this.formatDin5008International(this.parsePhoneNumber(pphoneNumber));
  }

  /**
   * format phone number in DIN 5008 international format.
   *
   * @param pphoneNumberData phone number to format
   * @return formated phone number as String
   */
  public final String formatDin5008International(final PhoneNumberInterface pphoneNumberData) {
    final StringBuilder resultNumber = new StringBuilder();
    if (this.isPhoneNumberNotEmpty(pphoneNumberData)) {
      resultNumber.append('+').append(pphoneNumberData.getCountryCode()).append(' ');
      if (StringUtils.isNotBlank(pphoneNumberData.getAreaCode())) {
        resultNumber.append(pphoneNumberData.getAreaCode()).append(' ');
      }
      resultNumber.append(pphoneNumberData.getLineNumber());
      if (StringUtils.isNotBlank(pphoneNumberData.getExtension())) {
        resultNumber.append('-');
        resultNumber.append(pphoneNumberData.getExtension());
      }
    }
    return StringUtils.trimToNull(resultNumber.toString());
  }

  /**
   * format phone number in DIN 5008 national format.
   *
   * @param pphoneNumber phone number as String to format
   * @return formated phone number as String
   */
  public final String formatDin5008National(final String pphoneNumber) {
    return this.formatDin5008National(this.parsePhoneNumber(pphoneNumber));
  }

  /**
   * format phone number in DIN 5008 national format.
   *
   * @param pphoneNumberData phone number to format
   * @return formated phone number as String
   */
  public final String formatDin5008National(final PhoneNumberInterface pphoneNumberData) {
    final StringBuilder resultNumber = new StringBuilder();
    if (this.isPhoneNumberNotEmpty(pphoneNumberData)) {
      PhoneCountryData phoneCountryData = null;
      for (final PhoneCountryCodeData country : PhoneNumberUtil.COUNTRY_CONSTANTS
          .countryCodeData()) {
        if (StringUtils.equals(country.getCountryCode(), pphoneNumberData.getCountryCode())) {
          phoneCountryData = country.getPhoneCountryData();
          break;
        }
      }
      if (phoneCountryData == null) {
        return this.formatDin5008International(pphoneNumberData);
      }
      resultNumber.append(phoneCountryData.getTrunkCode());
      if (StringUtils.isNotBlank(pphoneNumberData.getAreaCode())) {
        resultNumber.append(pphoneNumberData.getAreaCode());
      }
      resultNumber.append(' ');
      resultNumber.append(pphoneNumberData.getLineNumber());
      if (StringUtils.isNotBlank(pphoneNumberData.getExtension())) {
        resultNumber.append('-');
        resultNumber.append(pphoneNumberData.getExtension());
      }
    }
    return StringUtils.trimToNull(resultNumber.toString());
  }

  /**
   * format phone number in RFC 3966 format.
   *
   * @param pphoneNumber phone number as String to format
   * @return formated phone number as String
   */
  public final String formatRfc3966(final String pphoneNumber) {
    return this.formatRfc3966(this.parsePhoneNumber(pphoneNumber));
  }

  /**
   * format phone number in RFC 3966 format.
   *
   * @param pphoneNumberData phone number to format
   * @return formated phone number as String
   */
  public final String formatRfc3966(final PhoneNumberInterface pphoneNumberData) {
    final StringBuilder resultNumber = new StringBuilder();
    if (this.isPhoneNumberNotEmpty(pphoneNumberData)) {
      resultNumber.append('+').append(pphoneNumberData.getCountryCode()).append('-');
      if (StringUtils.isNotBlank(pphoneNumberData.getAreaCode())) {
        resultNumber.append(pphoneNumberData.getAreaCode()).append('-');
      }
      resultNumber.append(pphoneNumberData.getLineNumber());
      if (StringUtils.isNotBlank(pphoneNumberData.getExtension())) {
        resultNumber.append(pphoneNumberData.getExtension());
      }
    }
    return StringUtils.trimToNull(resultNumber.toString());
  }

  /**
   * format phone number in Microsoft canonical address format.
   *
   * @param pphoneNumber phone number as String to format
   * @return formated phone number as String
   */
  public final String formatMs(final String pphoneNumber) {
    return this.formatMs(this.parsePhoneNumber(pphoneNumber));
  }

  /**
   * format phone number in Microsoft canonical address format.
   *
   * @param pphoneNumberData phone number to format
   * @return formated phone number as String
   */
  public final String formatMs(final PhoneNumberInterface pphoneNumberData) {
    final StringBuilder resultNumber = new StringBuilder();
    if (this.isPhoneNumberNotEmpty(pphoneNumberData)) {
      resultNumber.append('+').append(pphoneNumberData.getCountryCode()).append(' ');
      if (StringUtils.isNotBlank(pphoneNumberData.getAreaCode())) {
        resultNumber.append('(').append(pphoneNumberData.getAreaCode()).append(") ");
      }
      resultNumber.append(pphoneNumberData.getLineNumber());
      if (StringUtils.isNotBlank(pphoneNumberData.getExtension())) {
        resultNumber.append(" - ");
        resultNumber.append(pphoneNumberData.getExtension());
      }
    }
    return StringUtils.trimToNull(resultNumber.toString());
  }

  /**
   * format phone number in URL format.
   *
   * @param pphoneNumber phone number as String to format
   * @return formated phone number as String
   */
  public final String formatUrl(final String pphoneNumber) {
    return this.formatUrl(this.parsePhoneNumber(pphoneNumber));
  }

  /**
   * format phone number in URL format.
   *
   * @param pphoneNumberData phone number to format
   * @return formated phone number as String
   */
  public final String formatUrl(final PhoneNumberInterface pphoneNumberData) {
    final StringBuilder resultNumber = new StringBuilder();
    if (this.isPhoneNumberNotEmpty(pphoneNumberData)) {
      resultNumber.append('+').append(pphoneNumberData.getCountryCode());
      if (StringUtils.isNotBlank(pphoneNumberData.getAreaCode())) {
        resultNumber.append('-');
        resultNumber.append(pphoneNumberData.getAreaCode());
      }
      resultNumber.append('-');
      resultNumber.append(pphoneNumberData.getLineNumber());
      if (StringUtils.isNotBlank(pphoneNumberData.getExtension())) {
        resultNumber.append('-');
        resultNumber.append(pphoneNumberData.getExtension());
      }
    }
    return StringUtils.trimToNull(resultNumber.toString());
  }

  /**
   * format phone number in Common format.
   *
   * @param pphoneNumber phone number as String to format
   * @return formated phone number as String
   */
  public final String formatCommon(final String pphoneNumber) {
    return this.formatCommon(this.parsePhoneNumber(pphoneNumber));
  }

  /**
   * format phone number in common format.
   *
   * @param pphoneNumberData phone number to format
   * @return formated phone number as String
   */
  public final String formatCommon(final PhoneNumberInterface pphoneNumberData) {
    return this.formatCommon(pphoneNumberData, this.defaultCountryData);
  }

  /**
   * format phone number in common format.
   *
   * @param pphoneNumberData phone number to format
   * @param pcountryCode iso code of country
   * @return formated phone number as String
   */
  public final String formatCommon(final PhoneNumberInterface pphoneNumberData,
      final String pcountryCode) {
    return this.formatCommon(pphoneNumberData, PhoneNumberUtil.COUNTRY_CONSTANTS.countryMap()
        .get(StringUtils.defaultString(pcountryCode)));
  }

  /**
   * format phone number in common format.
   *
   * @param pphoneNumberData phone number to format
   * @param pcountryData country data
   * @return formated phone number as String
   */
  public final String formatCommon(final PhoneNumberInterface pphoneNumberData,
      final PhoneCountryData pcountryData) {
    if (pphoneNumberData != null && pcountryData != null && StringUtils.equals(
        pcountryData.getCountryCodeData().getCountryCode(), pphoneNumberData.getCountryCode())) {
      return this.formatCommonNational(pphoneNumberData);
    } else {
      return this.formatCommonInternational(pphoneNumberData);
    }
  }

  /**
   * format phone number in Common international format.
   *
   * @param pphoneNumber phone number as String to format
   * @return formated phone number as String
   */
  public final String formatCommonInternational(final String pphoneNumber) {
    return this.formatCommonInternational(this.parsePhoneNumber(pphoneNumber));
  }

  /**
   * format phone number in Common international format.
   *
   * @param pphoneNumberData phone number to format
   * @return formated phone number as String
   */
  public final String formatCommonInternational(final PhoneNumberInterface pphoneNumberData) {
    final StringBuilder resultNumber = new StringBuilder();
    if (this.isPhoneNumberNotEmpty(pphoneNumberData)) {
      PhoneCountryData phoneCountryData = null;
      for (final PhoneCountryCodeData country : PhoneNumberUtil.COUNTRY_CONSTANTS
          .countryCodeData()) {
        if (StringUtils.equals(country.getCountryCode(), pphoneNumberData.getCountryCode())) {
          phoneCountryData = country.getPhoneCountryData();
          break;
        }
      }
      if (phoneCountryData == null) {
        return null;
      }
      resultNumber.append('+').append(pphoneNumberData.getCountryCode()).append(' ');
      resultNumber.append('(').append(phoneCountryData.getTrunkCode()).append(')');
      if (StringUtils.isNotBlank(pphoneNumberData.getAreaCode())) {
        resultNumber.append(pphoneNumberData.getAreaCode()).append(' ');
      }
      resultNumber.append(pphoneNumberData.getLineNumber());
      if (StringUtils.isNotBlank(pphoneNumberData.getExtension())) {
        resultNumber.append('-').append(pphoneNumberData.getExtension());
      }
    }
    return StringUtils.trimToNull(resultNumber.toString());
  }

  /**
   * format phone number in Common national format.
   *
   * @param pphoneNumber phone number as String to format
   * @return formated phone number as String
   */
  public final String formatCommonNational(final String pphoneNumber) {
    return this.formatCommonNational(this.parsePhoneNumber(pphoneNumber));
  }

  /**
   * format phone number in Common national format.
   *
   * @param pphoneNumberData phone number to format
   * @return formated phone number as String
   */
  public final String formatCommonNational(final PhoneNumberInterface pphoneNumberData) {
    final StringBuilder resultNumber = new StringBuilder();
    if (this.isPhoneNumberNotEmpty(pphoneNumberData)) {
      PhoneCountryData phoneCountryData = null;
      for (final PhoneCountryCodeData country : PhoneNumberUtil.COUNTRY_CONSTANTS
          .countryCodeData()) {
        if (StringUtils.equals(country.getCountryCode(), pphoneNumberData.getCountryCode())) {
          phoneCountryData = country.getPhoneCountryData();
          break;
        }
      }
      if (phoneCountryData == null) {
        return null;
      }
      resultNumber.append(phoneCountryData.getTrunkCode()).append(' ');
      resultNumber.append(this.groupIntoParts(pphoneNumberData.getAreaCode(), 2));
      resultNumber.append(" / ");
      resultNumber.append(this.groupIntoParts(pphoneNumberData.getLineNumber(), 2));
      if (StringUtils.isNotBlank(pphoneNumberData.getExtension())) {
        resultNumber.append(" - ");
        resultNumber.append(this.groupIntoParts(pphoneNumberData.getExtension(), 2));
      }
    }
    return StringUtils.trimToNull(resultNumber.toString());
  }

  private final String groupIntoParts(final String pstring, final int pblockLength) {
    if (pstring == null) {
      return StringUtils.EMPTY;
    }
    final StringBuilder formatedSb = new StringBuilder();
    int pos = 0;
    for (final char charCode : pstring.toCharArray()) {
      if (CharUtils.isAsciiNumeric(charCode)) {
        if (pos > 0 && pos % pblockLength == 0) {
          formatedSb.append(' ');
        }
        formatedSb.append(charCode);
        pos++;
      }
    }
    return formatedSb.toString();
  }

  /**
   * format phone number to index.
   *
   * @param pphoneNumber phone number as String to format
   * @return formated phone number as Long
   */
  public final Long formatIndex(final String pphoneNumber) {
    return this.formatIndex(this.parsePhoneNumber(pphoneNumber));
  }

  /**
   * format phone number in URL format.
   *
   * @param pphoneNumberData phone number to format
   * @return formated phone number as Long
   */
  public final Long formatIndex(final PhoneNumberInterface pphoneNumberData) {
    if (this.isPhoneNumberNotEmpty(pphoneNumberData)) {
      return Long.valueOf(StringUtils.defaultString(pphoneNumberData.getCountryCode())
          + StringUtils.defaultString(pphoneNumberData.getAreaCode())
          + StringUtils.defaultString(pphoneNumberData.getLineNumber())
          + StringUtils.defaultString(pphoneNumberData.getExtension()));
    }
    return null;
  }

  /**
   * check if phone number is empty.
   *
   * @param pphoneNumberData phone number to check
   * @return true if number is empty
   */
  public final boolean isPhoneNumberEmpty(final PhoneNumberInterface pphoneNumberData) {
    return pphoneNumberData == null || StringUtils.isBlank(pphoneNumberData.getCountryCode())
        || StringUtils.isBlank(pphoneNumberData.getLineNumber());
  }

  /**
   * check if phone number is not empty.
   *
   * @param pphoneNumberData phone number to check
   * @return true if number is not empty
   */
  public final boolean isPhoneNumberNotEmpty(final PhoneNumberInterface pphoneNumberData) {
    return !this.isPhoneNumberEmpty(pphoneNumberData);
  }
}
