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

import org.apache.commons.lang3.StringUtils;

/**
 * Phone Number Util, format and compress phone numbers.
 *
 * @author Manfred Tremmel
 *
 */
public class PhoneNumberUtil {
  private final PhoneCountrySharedConstants countryConstants;
  private final PhoneCountryData defaultCountryData;

  public PhoneNumberUtil() {
    this(null);
  }

  /**
   * constructor with default country.
   *
   * @param countryCode iso code of country
   */
  public PhoneNumberUtil(final String countryCode) {
    super();
    this.countryConstants = CreateClass.create(PhoneCountrySharedConstants.class);
    if (StringUtils.isEmpty(countryCode)) {
      this.defaultCountryData = null;
    } else {
      this.defaultCountryData = this.countryConstants.countryMap().get(countryCode);
    }
  }


  /**
   * parse phone number.
   *
   * @param pphoneNumber phone number as string
   * @return PhoneNumberData
   */
  public PhoneNumberData parsePhoneNumber(final String pphoneNumber) {
    final PhoneNumberData phoneNumberData = new PhoneNumberData();
    if (pphoneNumber != null) {
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
            break;
        }
      }
      String phoneNumberWork = StringUtils.reverse(cleanupString.toString());
      if (this.defaultCountryData != null) {
        if (StringUtils.isNotEmpty(this.defaultCountryData.getExitCode())
            && phoneNumberWork.startsWith(this.defaultCountryData.getExitCode())) {
          phoneNumberWork =
              phoneNumberWork.substring(this.defaultCountryData.getExitCode().length());
        } else if (StringUtils.isNotEmpty(this.defaultCountryData.getTrunkCode())
            && phoneNumberWork.startsWith(this.defaultCountryData.getTrunkCode())) {
          phoneNumberWork = this.defaultCountryData.getCountryCodeData().getCountryCode()
              + phoneNumberWork.substring(this.defaultCountryData.getTrunkCode().length());
        }
      }
      for (final PhoneCountryCodeData countryCode : this.countryConstants.countryCodeData()) {
        if (phoneNumberWork.startsWith(countryCode.getCountryCode())) {
          phoneNumberData.setCountryCode(countryCode.getCountryCode());
          phoneNumberData.setCountryName(countryCode.getCountryCodeName());
          phoneNumberWork = phoneNumberWork.substring(countryCode.getCountryCode().length());
          if (phoneNumberWork.charAt(0) == '-') {
            phoneNumberWork = phoneNumberWork.substring(1);
          }
          if (countryCode.getPhoneCountryData() != null
              && StringUtils.isNotEmpty(countryCode.getPhoneCountryData().getTrunkCode())
              && phoneNumberWork.startsWith(countryCode.getPhoneCountryData().getTrunkCode())) {
            phoneNumberWork = phoneNumberWork
                .substring(countryCode.getPhoneCountryData().getTrunkCode().length());
          }
          for (final PhoneAreaCodeData areaCode : countryCode.getAreaCodeData()) {
            if (areaCode.isRegEx()
                && phoneNumberWork.matches("^" + areaCode.getAreaCode() + ".*")) {
              final String areaCodeRemember = phoneNumberWork;
              phoneNumberWork =
                  phoneNumberWork.replaceFirst(areaCode.getAreaCode(), StringUtils.EMPTY);
              phoneNumberData.setAreaCode(areaCodeRemember.substring(0,
                  areaCodeRemember.length() - phoneNumberWork.length()));
              phoneNumberData.setAreaName(areaCode.getAreaName());
              break;
            } else if (!areaCode.isRegEx() && phoneNumberWork.startsWith(areaCode.getAreaCode())) {
              phoneNumberData.setAreaCode(areaCode.getAreaCode());
              phoneNumberData.setAreaName(areaCode.getAreaName());
              phoneNumberWork = phoneNumberWork.substring(areaCode.getAreaCode().length());
              break;
            }
          }
          if (phoneNumberWork.charAt(0) == '-') {
            phoneNumberWork = phoneNumberWork.substring(1);
          }
          if (phoneNumberWork.contains("-")) {
            final String[] splitedPhoneNumber = phoneNumberWork.split("-");
            phoneNumberData.setPhoneNumber(splitedPhoneNumber[0]);
            phoneNumberData.setExtension(splitedPhoneNumber[1]);
          } else {
            phoneNumberData.setPhoneNumber(phoneNumberWork);
          }
          break;
        }
      }
    }
    return phoneNumberData;
  }

  /**
   * format phone number in E123 format.
   *
   * @param pphoneNumber phone number as String to format
   * @return formated phone number as String
   */
  public final String formatE123(final String pphoneNumber) {
    return this.formatE123(this.parsePhoneNumber(pphoneNumber));
  }

  /**
   * format phone number in E123 format.
   *
   * @param pphoneNumberData phone number to format
   * @return formated phone number as String
   */
  public final String formatE123(final PhoneNumberData pphoneNumberData) {
    if (pphoneNumberData != null
        && StringUtils.equals(this.defaultCountryData.getCountryCodeData().getCountryCode(),
            pphoneNumberData.getCountryCode())) {
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
  public final String formatE123International(final PhoneNumberData pphoneNumberData) {
    final StringBuilder resultNumber = new StringBuilder();
    if (pphoneNumberData != null) {
      if (StringUtils.isNotBlank(pphoneNumberData.getCountryCode())
          && StringUtils.isNotBlank(pphoneNumberData.getPhoneNumber())) {
        resultNumber.append('+').append(pphoneNumberData.getCountryCode()).append(' ');
        if (StringUtils.isNotBlank(pphoneNumberData.getAreaCode())) {
          resultNumber.append(pphoneNumberData.getAreaCode()).append(' ');
        }
        resultNumber.append(pphoneNumberData.getPhoneNumber());
        if (StringUtils.isNotBlank(pphoneNumberData.getExtension())) {
          resultNumber.append(pphoneNumberData.getExtension());
        }
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
  public final String formatE123National(final PhoneNumberData pphoneNumberData) {
    final StringBuilder resultNumber = new StringBuilder();
    if (pphoneNumberData != null) {
      PhoneCountryData phoneCountryData = null;
      for (final PhoneCountryCodeData country : this.countryConstants.countryCodeData()) {
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
      resultNumber.append(pphoneNumberData.getPhoneNumber());
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
    return this.formatDin5008(this.parsePhoneNumber(pphoneNumber));
  }

  /**
   * format phone number in DIN 5008 format.
   *
   * @param pphoneNumberData phone number to format
   * @return formated phone number as String
   */
  public final String formatDin5008(final PhoneNumberData pphoneNumberData) {
    if (pphoneNumberData != null
        && StringUtils.equals(this.defaultCountryData.getCountryCodeData().getCountryCode(),
            pphoneNumberData.getCountryCode())) {
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
  public final String formatDin5008International(final PhoneNumberData pphoneNumberData) {
    final StringBuilder resultNumber = new StringBuilder();
    if (pphoneNumberData != null) {
      if (StringUtils.isNotBlank(pphoneNumberData.getCountryCode())
          && StringUtils.isNotBlank(pphoneNumberData.getPhoneNumber())) {
        resultNumber.append('+').append(pphoneNumberData.getCountryCode()).append(' ');
        if (StringUtils.isNotBlank(pphoneNumberData.getAreaCode())) {
          resultNumber.append(pphoneNumberData.getAreaCode()).append(' ');
        }
        resultNumber.append(pphoneNumberData.getPhoneNumber());
        if (StringUtils.isNotBlank(pphoneNumberData.getExtension())) {
          resultNumber.append('-');
          resultNumber.append(pphoneNumberData.getExtension());
        }
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
  public final String formatDin5008National(final PhoneNumberData pphoneNumberData) {
    final StringBuilder resultNumber = new StringBuilder();
    if (pphoneNumberData != null) {
      PhoneCountryData phoneCountryData = null;
      for (final PhoneCountryCodeData country : this.countryConstants.countryCodeData()) {
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
      resultNumber.append(pphoneNumberData.getPhoneNumber());
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
  public final String formatRfc3966(final PhoneNumberData pphoneNumberData) {
    final StringBuilder resultNumber = new StringBuilder();
    if (pphoneNumberData != null) {
      if (StringUtils.isNotBlank(pphoneNumberData.getCountryCode())
          && StringUtils.isNotBlank(pphoneNumberData.getPhoneNumber())) {
        resultNumber.append('+').append(pphoneNumberData.getCountryCode()).append('-');
        if (StringUtils.isNotBlank(pphoneNumberData.getAreaCode())) {
          resultNumber.append(pphoneNumberData.getAreaCode()).append('-');
        }
        resultNumber.append(pphoneNumberData.getPhoneNumber());
        if (StringUtils.isNotBlank(pphoneNumberData.getExtension())) {
          resultNumber.append(pphoneNumberData.getExtension());
        }
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
  public final String formatMs(final PhoneNumberData pphoneNumberData) {
    final StringBuilder resultNumber = new StringBuilder();
    if (pphoneNumberData != null) {
      if (StringUtils.isNotBlank(pphoneNumberData.getCountryCode())
          && StringUtils.isNotBlank(pphoneNumberData.getPhoneNumber())) {
        resultNumber.append('+').append(pphoneNumberData.getCountryCode()).append(' ');
        if (StringUtils.isNotBlank(pphoneNumberData.getAreaCode())) {
          resultNumber.append('(').append(pphoneNumberData.getAreaCode()).append(") ");
        }
        resultNumber.append(pphoneNumberData.getPhoneNumber());
        if (StringUtils.isNotBlank(pphoneNumberData.getExtension())) {
          resultNumber.append(" - ");
          resultNumber.append(pphoneNumberData.getExtension());
        }
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
  public final String formatUrl(final PhoneNumberData pphoneNumberData) {
    final StringBuilder resultNumber = new StringBuilder();
    if (pphoneNumberData != null) {
      if (StringUtils.isNotBlank(pphoneNumberData.getCountryCode())
          && StringUtils.isNotBlank(pphoneNumberData.getPhoneNumber())) {
        resultNumber.append('+').append(pphoneNumberData.getCountryCode());
        if (StringUtils.isNotBlank(pphoneNumberData.getAreaCode())) {
          resultNumber.append('-');
          resultNumber.append(pphoneNumberData.getAreaCode());
        }
        resultNumber.append('-');
        resultNumber.append(pphoneNumberData.getPhoneNumber());
        if (StringUtils.isNotBlank(pphoneNumberData.getExtension())) {
          resultNumber.append('-');
          resultNumber.append(pphoneNumberData.getExtension());
        }
      }
    }
    return StringUtils.trimToNull(resultNumber.toString());
  }
}
