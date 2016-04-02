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

import de.knightsoftnet.validators.server.data.CreatePhoneCountryConstantsClass;
import de.knightsoftnet.validators.shared.data.PhoneAreaCodeData;
import de.knightsoftnet.validators.shared.data.PhoneCountryCodeData;
import de.knightsoftnet.validators.shared.data.PhoneCountryData;
import de.knightsoftnet.validators.shared.data.PhoneNumberData;
import de.knightsoftnet.validators.shared.data.PhoneNumberExtendedInterface;
import de.knightsoftnet.validators.shared.data.PhoneNumberInterface;
import de.knightsoftnet.validators.shared.data.ValidationInterface;
import de.knightsoftnet.validators.shared.data.ValueWithPos;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Phone Number Util, format and parse phone numbers.
 *
 * @author Manfred Tremmel
 *
 */
public class PhoneNumberUtil {
  private static final String EXTENSION_SEPARATOR = "-";

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
    this.setCountryCode(pcountryCode, Locale.ROOT);
  }

  /**
   * set country code.
   *
   * @param pcountryCode iso code of country
   */
  public final void setCountryCode(final String pcountryCode) {
    this.setCountryCode(pcountryCode, Locale.ROOT);
  }

  /**
   * set country code.
   *
   * @param pcountryCode iso code of country
   * @param plocale locale to read properties in the correct language
   */
  public final void setCountryCode(final String pcountryCode, final Locale plocale) {
    if (StringUtils.isEmpty(pcountryCode)) {
      this.defaultCountryData = null;
    } else {
      this.defaultCountryData =
          CreatePhoneCountryConstantsClass.create(plocale).countryMap().get(pcountryCode);
    }
  }


  /**
   * parse phone number.
   *
   * @param pphoneNumber phone number as string
   * @return PhoneNumberData
   */
  public PhoneNumberData parsePhoneNumber(final String pphoneNumber) {
    return (PhoneNumberData) this.parsePhoneNumber(pphoneNumber, new PhoneNumberData(),
        this.defaultCountryData);
  }

  /**
   * parse phone number.
   *
   * @param pphoneNumber phone number as string with length
   * @return PhoneNumberData with length
   */
  public ValueWithPos<PhoneNumberData> parsePhoneNumber(final ValueWithPos<String> pphoneNumber) {
    return this.parsePhoneNumber(pphoneNumber, new PhoneNumberData(), this.defaultCountryData);
  }

  /**
   * parse phone number.
   *
   * @param pphoneNumber phone number as string
   * @param pcountryCode iso code of country
   * @return PhoneNumberData
   */
  public PhoneNumberData parsePhoneNumber(final String pphoneNumber, final String pcountryCode) {
    return this.parsePhoneNumber(pphoneNumber, pcountryCode, Locale.ROOT);
  }

  /**
   * parse phone number.
   *
   * @param pphoneNumber phone number as string
   * @param pcountryCode iso code of country
   * @param plocale locale to read properties in the correct language
   * @return PhoneNumberData
   */
  public PhoneNumberData parsePhoneNumber(final String pphoneNumber, final String pcountryCode,
      final Locale plocale) {
    return (PhoneNumberData) this.parsePhoneNumber(pphoneNumber, new PhoneNumberData(),
        CreatePhoneCountryConstantsClass.create(plocale).countryMap()
            .get(StringUtils.defaultString(pcountryCode)));
  }

  /**
   * parse phone number.
   *
   * @param pphoneNumber phone number as string
   * @param pcountryCode iso code of country
   * @return PhoneNumberData
   */
  public ValueWithPos<PhoneNumberData> parsePhoneNumber(final ValueWithPos<String> pphoneNumber,
      final String pcountryCode) {
    return this.parsePhoneNumber(pphoneNumber, pcountryCode, Locale.ROOT);
  }

  /**
   * parse phone number.
   *
   * @param pphoneNumber phone number as string
   * @param pcountryCode iso code of country
   * @param plocale locale to read properties in the correct language
   * @return PhoneNumberData
   */
  public ValueWithPos<PhoneNumberData> parsePhoneNumber(final ValueWithPos<String> pphoneNumber,
      final String pcountryCode, final Locale plocale) {
    return this.parsePhoneNumber(pphoneNumber, new PhoneNumberData(),
        CreatePhoneCountryConstantsClass.create(plocale).countryMap()
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
   * @param pcountryData country data
   * @return PhoneNumberData, the same as in second parameter
   */
  public PhoneNumberInterface parsePhoneNumber(final String pphoneNumber,
      final PhoneNumberInterface pphoneNumberData, final PhoneCountryData pcountryData) {
    if (pphoneNumber == null) {
      return null;
    }
    final ValueWithPos<PhoneNumberData> formatedValue = this.parsePhoneNumber(
        new ValueWithPos<String>(pphoneNumber, -1), pphoneNumberData, pcountryData);
    return formatedValue.getValue();
  }


  /**
   * parse phone number.
   *
   * @param pphoneNumber phone number as string
   * @param pphoneNumberData phone number data to fill
   * @param pcountryData country data
   * @return PhoneNumberData, the same as in second parameter
   */
  public ValueWithPos<PhoneNumberData> parsePhoneNumber(final ValueWithPos<String> pphoneNumber,
      final PhoneNumberInterface pphoneNumberData, final PhoneCountryData pcountryData) {
    if (pphoneNumber == null || pphoneNumberData == null) {
      return null;
    }
    int cursorpos = pphoneNumber.getPos();
    int cursorpossub = 0;
    for (int pos = 0; pos < cursorpos && pos < StringUtils.length(pphoneNumber.getValue()); pos++) {
      final char character = pphoneNumber.getValue().charAt(pos);
      if (character < '0' || character > '9') {
        cursorpossub++;
      }
    }
    cursorpos -= cursorpossub;
    boolean needsAreaCode = false;
    int minLength = 2;
    int maxLength = 15;
    pphoneNumberData.setCountryCode(null);
    pphoneNumberData.setAreaCode(null);
    pphoneNumberData.setLineNumber(null);
    pphoneNumberData.setExtension(null);
    final StringBuilder cleanupString = new StringBuilder(pphoneNumber.getValue().length());
    final boolean containsMinus = StringUtils.contains(pphoneNumber.getValue(), '-');
    boolean hasSeperator = false;
    for (final char character : StringUtils.reverse(pphoneNumber.getValue()).toCharArray()) {
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
        cursorpos -= pcountryData.getExitCode().length();
      } else if (StringUtils.isNotEmpty(pcountryData.getTrunkCode())
          && phoneNumberWork.startsWith(pcountryData.getTrunkCode())) {
        phoneNumberWork = pcountryData.getCountryCodeData().getCountryCode()
            + phoneNumberWork.substring(pcountryData.getTrunkCode().length());
        if (cursorpos >= pcountryData.getTrunkCode().length()) {
          cursorpos -= pcountryData.getTrunkCode().length();
          cursorpos += StringUtils.length(pcountryData.getCountryCodeData().getCountryCode());
        }
      }
    }
    for (final PhoneCountryCodeData countryCode : CreatePhoneCountryConstantsClass.create()
        .countryCodeData()) {
      if (phoneNumberWork.startsWith(countryCode.getCountryCode())) {
        pphoneNumberData.setCountryCode(countryCode.getCountryCode());
        maxLength -= StringUtils.length(countryCode.getCountryCode());
        if (pphoneNumberData instanceof PhoneNumberExtendedInterface) {
          ((PhoneNumberExtendedInterface) pphoneNumberData)
              .setCountryName(countryCode.getCountryCodeName());
        }
        phoneNumberWork = phoneNumberWork.substring(countryCode.getCountryCode().length());
        if (phoneNumberWork.startsWith(EXTENSION_SEPARATOR)) {
          phoneNumberWork = phoneNumberWork.substring(1);
        }
        if (countryCode.getPhoneCountryData() != null) {
          needsAreaCode = countryCode.getPhoneCountryData().isAreaCodeMustBeFilled();
          if (StringUtils.isNotEmpty(countryCode.getPhoneCountryData().getTrunkCode())
              && phoneNumberWork.startsWith(countryCode.getPhoneCountryData().getTrunkCode())) {
            phoneNumberWork = phoneNumberWork
                .substring(countryCode.getPhoneCountryData().getTrunkCode().length());
            if (cursorpos >= countryCode.getPhoneCountryData().getTrunkCode().length()) {
              cursorpos -= countryCode.getPhoneCountryData().getTrunkCode().length();
            }
          }
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
            minLength = areaCode.getMinLength();
            maxLength = areaCode.getMaxLength();
            break;
          } else if (!areaCode.isRegEx() && phoneNumberWork.startsWith(areaCode.getAreaCode())) {
            pphoneNumberData.setAreaCode(areaCode.getAreaCode());
            if (pphoneNumberData instanceof PhoneNumberExtendedInterface) {
              ((PhoneNumberExtendedInterface) pphoneNumberData).setAreaName(areaCode.getAreaName());
            }
            phoneNumberWork = phoneNumberWork.substring(areaCode.getAreaCode().length());
            minLength = areaCode.getMinLength();
            maxLength = areaCode.getMaxLength();
            break;
          }
        }

        if (phoneNumberWork.startsWith(EXTENSION_SEPARATOR)) {
          phoneNumberWork = phoneNumberWork.substring(1);
        }
        if (phoneNumberWork.contains(EXTENSION_SEPARATOR)) {
          final String[] splitedPhoneNumber = phoneNumberWork.split(EXTENSION_SEPARATOR);
          pphoneNumberData.setLineNumber(splitedPhoneNumber[0]);
          if (splitedPhoneNumber.length > 1) {
            pphoneNumberData.setExtension(splitedPhoneNumber[1]);
          }
        } else {
          pphoneNumberData.setLineNumber(phoneNumberWork);
        }
        break;
      }
    }
    if (pphoneNumberData instanceof ValidationInterface) {
      int callNummerLength = StringUtils.length(pphoneNumberData.getLineNumber());
      int completeNumberLength = callNummerLength;
      if (StringUtils.isNotEmpty(pphoneNumberData.getExtension())) {
        // if we do have extensions, phone number including extension may be longer then allowed
        // number, but at least one digit counts
        callNummerLength++;
        completeNumberLength += StringUtils.length(pphoneNumberData.getExtension());
      }
      ((ValidationInterface) pphoneNumberData)
          .setValid(StringUtils.isNotEmpty(pphoneNumberData.getCountryCode())
              && StringUtils.isNotEmpty(pphoneNumberData.getLineNumber())
              && (StringUtils.isNotEmpty(pphoneNumberData.getAreaCode()) || !needsAreaCode)
              && (callNummerLength >= minLength && callNummerLength <= maxLength
                  || completeNumberLength >= minLength && completeNumberLength <= maxLength));
    }
    if (cursorpos < 0) {
      cursorpos = 0;
    } else {
      final int calculatedlength = StringUtils.length(pphoneNumberData.getCountryCode())
          + StringUtils.length(pphoneNumberData.getAreaCode())
          + StringUtils.length(pphoneNumberData.getLineNumber())
          + StringUtils.length(pphoneNumberData.getExtension());
      if (cursorpos > calculatedlength) {
        cursorpos = calculatedlength;
      }
    }
    return new ValueWithPos<PhoneNumberData>(new PhoneNumberData(pphoneNumberData), cursorpos);
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
   * format phone number in E123 format with cursor position handling.
   *
   * @param pphoneNumber phone number as String to format with cursor position
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatE123(final ValueWithPos<String> pphoneNumber) {
    return this.formatE123WithPos(this.parsePhoneNumber(pphoneNumber), this.defaultCountryData);
  }

  /**
   * format phone number in E123 format.
   *
   * @param pphoneNumber phone number as String to format
   * @param pcountryCode iso code of country
   * @return formated phone number as String
   */
  public final String formatE123(final String pphoneNumber, final String pcountryCode) {
    return this.formatE123(this.parsePhoneNumber(pphoneNumber), CreatePhoneCountryConstantsClass
        .create().countryMap().get(StringUtils.defaultString(pcountryCode)));
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
    return this.formatE123(pphoneNumberData, CreatePhoneCountryConstantsClass.create().countryMap()
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
   * format phone number in E123 format with cursor position handling.
   *
   * @param pphoneNumber phone number as String to format with cursor position
   * @param pcountryCode iso code of country
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatE123WithPos(final ValueWithPos<String> pphoneNumber,
      final String pcountryCode) {
    return this.formatE123WithPos(this.parsePhoneNumber(pphoneNumber, pcountryCode),
        CreatePhoneCountryConstantsClass.create().countryMap()
            .get(StringUtils.defaultString(pcountryCode)));
  }

  /**
   * format phone number in E123 format with cursor position handling.
   *
   * @param pphoneNumberData phone number to format with cursor position
   * @param pcountryData country data
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatE123WithPos(
      final ValueWithPos<PhoneNumberData> pphoneNumberData, final PhoneCountryData pcountryData) {
    if (pphoneNumberData != null && pcountryData != null
        && StringUtils.equals(pcountryData.getCountryCodeData().getCountryCode(),
            pphoneNumberData.getValue().getCountryCode())) {
      return this.formatE123NationalWithPos(pphoneNumberData);
    } else {
      return this.formatE123InternationalWithPos(pphoneNumberData);
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
   * @param pphoneNumber phone number to format
   * @param pcountryCode iso code of country
   * @return formated phone number as String
   */
  public final String formatE123International(final String pphoneNumber,
      final String pcountryCode) {
    return this.formatE123International(this.parsePhoneNumber(pphoneNumber, pcountryCode));
  }

  /**
   * format phone number in E123 international format with cursor position handling.
   *
   * @param pphoneNumber phone number as String to format with cursor position
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatE123International(
      final ValueWithPos<String> pphoneNumber) {
    return this.formatE123InternationalWithPos(this.parsePhoneNumber(pphoneNumber));
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
   * format phone number in E123 international format with cursor position handling.
   *
   * @param pphoneNumber phone number as String to format with cursor position
   * @param pcountryCode iso code of country
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatE123InternationalWithPos(
      final ValueWithPos<String> pphoneNumber, final String pcountryCode) {
    return this.formatE123InternationalWithPos(this.parsePhoneNumber(pphoneNumber, pcountryCode));
  }

  /**
   * format phone number in E123 international format with cursor position handling.
   *
   * @param pphoneNumberData phone number to format with cursor position
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatE123InternationalWithPos(
      final ValueWithPos<PhoneNumberData> pphoneNumberData) {
    final StringBuilder resultNumber = new StringBuilder();
    if (pphoneNumberData == null) {
      return null;
    }
    int cursor = pphoneNumberData.getPos();
    if (this.isPhoneNumberNotEmpty(pphoneNumberData.getValue())) {
      cursor++;
      resultNumber.append('+').append(pphoneNumberData.getValue().getCountryCode());
      if (resultNumber.length() <= cursor) {
        cursor++;
      }
      resultNumber.append(' ');
      if (StringUtils.isNotBlank(pphoneNumberData.getValue().getAreaCode())) {
        resultNumber.append(pphoneNumberData.getValue().getAreaCode());
        if (resultNumber.length() <= cursor) {
          cursor++;
        }
        resultNumber.append(' ');
      }
      resultNumber.append(pphoneNumberData.getValue().getLineNumber());
      if (StringUtils.isNotBlank(pphoneNumberData.getValue().getExtension())) {
        resultNumber.append(pphoneNumberData.getValue().getExtension());
      }
    }
    return new ValueWithPos<String>(StringUtils.trimToNull(resultNumber.toString()), cursor);
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
   * format phone number in E123 national format with cursor position handling.
   *
   * @param pphoneNumber phone number as String to format with cursor position
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatE123National(final ValueWithPos<String> pphoneNumber) {
    return this.formatE123NationalWithPos(this.parsePhoneNumber(pphoneNumber));
  }

  /**
   * format phone number in E123 national format.
   *
   * @param pphoneNumber phone number to format
   * @param pcountryCode iso code of country
   * @return formated phone number as String
   */
  public final String formatE123National(final String pphoneNumber, final String pcountryCode) {
    return this.formatE123National(this.parsePhoneNumber(pphoneNumber, pcountryCode));
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
      for (final PhoneCountryCodeData country : CreatePhoneCountryConstantsClass.create()
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
   * format phone number in E123 national format with cursor position handling.
   *
   * @param pphoneNumber phone number as String to format with cursor position
   * @param pcountryCode iso code of country
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatE123NationalWithPos(
      final ValueWithPos<String> pphoneNumber, final String pcountryCode) {
    return this.formatE123NationalWithPos(this.parsePhoneNumber(pphoneNumber, pcountryCode));
  }

  /**
   * format phone number in E123 national format with cursor position handling.
   *
   * @param pphoneNumberData phone number to format with cursor position
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatE123NationalWithPos(
      final ValueWithPos<PhoneNumberData> pphoneNumberData) {
    if (pphoneNumberData == null) {
      return null;
    }
    int cursor = pphoneNumberData.getPos();
    final StringBuilder resultNumber = new StringBuilder();
    if (this.isPhoneNumberNotEmpty(pphoneNumberData.getValue())) {
      PhoneCountryData phoneCountryData = null;
      for (final PhoneCountryCodeData country : CreatePhoneCountryConstantsClass.create()
          .countryCodeData()) {
        if (StringUtils.equals(country.getCountryCode(),
            pphoneNumberData.getValue().getCountryCode())) {
          phoneCountryData = country.getPhoneCountryData();
          break;
        }
      }
      if (phoneCountryData == null) {
        return this.formatE123InternationalWithPos(pphoneNumberData);
      }
      if (cursor > 0) {
        cursor -= StringUtils.length(pphoneNumberData.getValue().getCountryCode());
        cursor += StringUtils.length(phoneCountryData.getTrunkCode());
      }
      cursor++;
      resultNumber.append('(').append(phoneCountryData.getTrunkCode());
      if (StringUtils.isNotBlank(pphoneNumberData.getValue().getAreaCode())) {
        resultNumber.append(pphoneNumberData.getValue().getAreaCode());
      }
      if (resultNumber.length() <= cursor) {
        cursor += 2;
      }
      resultNumber.append(") ");
      resultNumber.append(pphoneNumberData.getValue().getLineNumber());
      if (StringUtils.isNotBlank(pphoneNumberData.getValue().getExtension())) {
        if (resultNumber.length() <= cursor) {
          cursor++;
        }
        resultNumber.append(' ');
        resultNumber.append(pphoneNumberData.getValue().getExtension());
      }
    }
    return new ValueWithPos<String>(StringUtils.trimToNull(resultNumber.toString()), cursor);
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
   * format phone number in DIN 5008 format with cursor position handling.
   *
   * @param pphoneNumber phone number as String to format with cursor position
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatDin5008(final ValueWithPos<String> pphoneNumber) {
    return this.formatDin5008WithPos(this.parsePhoneNumber(pphoneNumber), this.defaultCountryData);
  }

  /**
   * format phone number in DIN 5008 format.
   *
   * @param pphoneNumber phone number as String to format
   * @param pcountryCode iso code of country
   * @return formated phone number as String
   */
  public final String formatDin5008(final String pphoneNumber, final String pcountryCode) {
    return this.formatDin5008(this.parsePhoneNumber(pphoneNumber), CreatePhoneCountryConstantsClass
        .create().countryMap().get(StringUtils.defaultString(pcountryCode)));
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
    return this.formatDin5008(pphoneNumberData, CreatePhoneCountryConstantsClass.create()
        .countryMap().get(StringUtils.defaultString(pcountryCode)));
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
   * format phone number in DIN 5008 format with cursor position handling.
   *
   * @param pphoneNumber phone number as String to format with cursor position
   * @param pcountryCode iso code of country
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatDin5008WithPos(final ValueWithPos<String> pphoneNumber,
      final String pcountryCode) {
    return this.formatDin5008WithPos(this.parsePhoneNumber(pphoneNumber, pcountryCode),
        CreatePhoneCountryConstantsClass.create().countryMap()
            .get(StringUtils.defaultString(pcountryCode)));
  }

  /**
   * format phone number in DIN 5008 format with cursor position handling.
   *
   * @param pphoneNumberData phone number to format with cursor position
   * @param pcountryData country data
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatDin5008WithPos(
      final ValueWithPos<PhoneNumberData> pphoneNumberData, final PhoneCountryData pcountryData) {
    if (pphoneNumberData != null && pcountryData != null
        && StringUtils.equals(pcountryData.getCountryCodeData().getCountryCode(),
            pphoneNumberData.getValue().getCountryCode())) {
      return this.formatDin5008NationalWithPos(pphoneNumberData);
    } else {
      return this.formatDin5008InternationalWithPos(pphoneNumberData);
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
   * format phone number in DIN 5008 international format with cursor position handling.
   *
   * @param pphoneNumber phone number as String to format with cursor position
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatDin5008International(
      final ValueWithPos<String> pphoneNumber) {
    return this.formatDin5008InternationalWithPos(this.parsePhoneNumber(pphoneNumber));
  }

  /**
   * format phone number in DIN 5008 international format.
   *
   * @param pphoneNumber phone number to format
   * @param pcountryCode iso code of country
   * @return formated phone number as String
   */
  public final String formatDin5008International(final String pphoneNumber,
      final String pcountryCode) {
    return this.formatDin5008International(this.parsePhoneNumber(pphoneNumber, pcountryCode));
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
   * format phone number in DIN 5008 international format with cursor position handling.
   *
   * @param pphoneNumber phone number as String to format with cursor position
   * @param pcountryCode iso code of country
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatDin5008InternationalWithPos(
      final ValueWithPos<String> pphoneNumber, final String pcountryCode) {
    return this
        .formatDin5008InternationalWithPos(this.parsePhoneNumber(pphoneNumber, pcountryCode));
  }

  /**
   * format phone number in DIN 5008 international format with cursor position handling.
   *
   * @param pphoneNumberData phone number to format with cursor position
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatDin5008InternationalWithPos(
      final ValueWithPos<PhoneNumberData> pphoneNumberData) {
    final StringBuilder resultNumber = new StringBuilder();
    if (pphoneNumberData == null) {
      return null;
    }
    int cursor = pphoneNumberData.getPos();
    if (this.isPhoneNumberNotEmpty(pphoneNumberData.getValue())) {
      cursor++;
      resultNumber.append('+').append(pphoneNumberData.getValue().getCountryCode());
      if (resultNumber.length() <= cursor) {
        cursor++;
      }
      resultNumber.append(' ');
      if (StringUtils.isNotBlank(pphoneNumberData.getValue().getAreaCode())) {
        resultNumber.append(pphoneNumberData.getValue().getAreaCode());
        if (resultNumber.length() <= cursor) {
          cursor++;
        }
        resultNumber.append(' ');
      }
      resultNumber.append(pphoneNumberData.getValue().getLineNumber());
      if (StringUtils.isNotBlank(pphoneNumberData.getValue().getExtension())) {
        if (resultNumber.length() <= cursor) {
          cursor++;
        }
        resultNumber.append('-');
        resultNumber.append(pphoneNumberData.getValue().getExtension());
      }
    }
    return new ValueWithPos<String>(StringUtils.trimToNull(resultNumber.toString()), cursor);
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
   * format phone number in DIN 5008 national format with cursor position handling.
   *
   * @param pphoneNumber phone number as String to format with cursor position
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatDin5008National(final ValueWithPos<String> pphoneNumber) {
    return this.formatDin5008NationalWithPos(this.parsePhoneNumber(pphoneNumber));
  }

  /**
   * format phone number in DIN 5008 national format.
   *
   * @param pphoneNumber phone number to format
   * @param pcountryCode iso code of country
   * @return formated phone number as String
   */
  public final String formatDin5008National(final String pphoneNumber, final String pcountryCode) {
    return this.formatDin5008National(this.parsePhoneNumber(pphoneNumber, pcountryCode));
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
      for (final PhoneCountryCodeData country : CreatePhoneCountryConstantsClass.create()
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
   * format phone number in DIN 5008 national format with cursor position handling.
   *
   * @param pphoneNumber phone number as String to format with cursor position
   * @param pcountryCode iso code of country
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatDin5008NationalWithPos(
      final ValueWithPos<String> pphoneNumber, final String pcountryCode) {
    return this.formatDin5008NationalWithPos(this.parsePhoneNumber(pphoneNumber, pcountryCode));
  }

  /**
   * format phone number in DIN 5008 national format with cursor position handling.
   *
   * @param pphoneNumberData phone number to format with cursor position
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatDin5008NationalWithPos(
      final ValueWithPos<PhoneNumberData> pphoneNumberData) {
    if (pphoneNumberData == null) {
      return null;
    }
    int cursor = pphoneNumberData.getPos();
    final StringBuilder resultNumber = new StringBuilder();
    if (this.isPhoneNumberNotEmpty(pphoneNumberData.getValue())) {
      PhoneCountryData phoneCountryData = null;
      for (final PhoneCountryCodeData country : CreatePhoneCountryConstantsClass.create()
          .countryCodeData()) {
        if (StringUtils.equals(country.getCountryCode(),
            pphoneNumberData.getValue().getCountryCode())) {
          phoneCountryData = country.getPhoneCountryData();
          break;
        }
      }
      if (phoneCountryData == null) {
        return this.formatDin5008InternationalWithPos(pphoneNumberData);
      }
      if (cursor > 0) {
        cursor -= StringUtils.length(pphoneNumberData.getValue().getCountryCode());
        cursor += StringUtils.length(phoneCountryData.getTrunkCode());
      }
      resultNumber.append(phoneCountryData.getTrunkCode());
      if (StringUtils.isNotBlank(pphoneNumberData.getValue().getAreaCode())) {
        resultNumber.append(pphoneNumberData.getValue().getAreaCode());
      }
      if (resultNumber.length() <= cursor) {
        cursor++;
      }
      resultNumber.append(' ');
      resultNumber.append(pphoneNumberData.getValue().getLineNumber());
      if (StringUtils.isNotBlank(pphoneNumberData.getValue().getExtension())) {
        if (resultNumber.length() <= cursor) {
          cursor++;
        }
        resultNumber.append('-');
        resultNumber.append(pphoneNumberData.getValue().getExtension());
      }
    }
    return new ValueWithPos<String>(StringUtils.trimToNull(resultNumber.toString()), cursor);
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
   * @param pphoneNumber phone number to format
   * @param pcountryCode iso code of country
   * @return formated phone number as String
   */
  public final String formatRfc3966(final String pphoneNumber, final String pcountryCode) {
    return this.formatRfc3966(this.parsePhoneNumber(pphoneNumber, pcountryCode));
  }

  /**
   * format phone number in RFC 3966 format with cursor position handling.
   *
   * @param pphoneNumber phone number as String to format with cursor position
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatRfc3966(final ValueWithPos<String> pphoneNumber) {
    return this.formatRfc3966WithPos(this.parsePhoneNumber(pphoneNumber));
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
   * format phone number in RFC 3966 format with cursor position handling.
   *
   * @param pphoneNumber phone number as String to format with cursor position
   * @param pcountryCode iso code of country
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatRfc3966WithPos(final ValueWithPos<String> pphoneNumber,
      final String pcountryCode) {
    return this.formatRfc3966WithPos(this.parsePhoneNumber(pphoneNumber, pcountryCode));
  }

  /**
   * format phone number in RFC 3966 format with cursor position handling.
   *
   * @param pphoneNumberData phone number to format with cursor position
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatRfc3966WithPos(
      final ValueWithPos<PhoneNumberData> pphoneNumberData) {
    final StringBuilder resultNumber = new StringBuilder();
    if (pphoneNumberData == null) {
      return null;
    }
    int cursor = pphoneNumberData.getPos();
    if (this.isPhoneNumberNotEmpty(pphoneNumberData.getValue())) {
      cursor++;
      resultNumber.append('+').append(pphoneNumberData.getValue().getCountryCode());
      if (resultNumber.length() <= cursor) {
        cursor++;
      }
      resultNumber.append('-');
      if (StringUtils.isNotBlank(pphoneNumberData.getValue().getAreaCode())) {
        resultNumber.append(pphoneNumberData.getValue().getAreaCode());
        if (resultNumber.length() <= cursor) {
          cursor++;
        }
        resultNumber.append('-');
      }
      resultNumber.append(pphoneNumberData.getValue().getLineNumber());
      if (StringUtils.isNotBlank(pphoneNumberData.getValue().getExtension())) {
        resultNumber.append(pphoneNumberData.getValue().getExtension());
      }
    }
    return new ValueWithPos<String>(StringUtils.trimToNull(resultNumber.toString()), cursor);
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
   * @param pphoneNumber phone number to format
   * @param pcountryCode iso code of country
   * @return formated phone number as String
   */
  public final String formatMs(final String pphoneNumber, final String pcountryCode) {
    return this.formatMs(this.parsePhoneNumber(pphoneNumber, pcountryCode));
  }

  /**
   * format phone number in Microsoft canonical address format with cursor position handling.
   *
   * @param pphoneNumber phone number as String to format with cursor position
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatMs(final ValueWithPos<String> pphoneNumber) {
    return this.formatMsWithPos(this.parsePhoneNumber(pphoneNumber));
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
   * format phone number in Microsoft canonical address format with cursor position handling.
   *
   * @param pphoneNumber phone number as String to format with cursor position
   * @param pcountryCode iso code of country
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatMsWithPos(final ValueWithPos<String> pphoneNumber,
      final String pcountryCode) {
    return this.formatMsWithPos(this.parsePhoneNumber(pphoneNumber, pcountryCode));
  }

  /**
   * format phone number in Microsoft canonical address format with cursor position handling.
   *
   * @param pphoneNumberData phone number to format with cursor position
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatMsWithPos(
      final ValueWithPos<PhoneNumberData> pphoneNumberData) {
    if (pphoneNumberData == null) {
      return null;
    }
    int cursor = pphoneNumberData.getPos();
    final StringBuilder resultNumber = new StringBuilder();
    if (this.isPhoneNumberNotEmpty(pphoneNumberData.getValue())) {
      cursor++;
      resultNumber.append('+').append(pphoneNumberData.getValue().getCountryCode());
      if (resultNumber.length() <= cursor) {
        cursor++;
      }
      resultNumber.append(' ');
      if (StringUtils.isNotBlank(pphoneNumberData.getValue().getAreaCode())) {
        if (resultNumber.length() <= cursor) {
          cursor++;
        }
        resultNumber.append('(').append(pphoneNumberData.getValue().getAreaCode());
        if (resultNumber.length() <= cursor) {
          cursor += 2;
        }
        resultNumber.append(") ");
      }
      resultNumber.append(pphoneNumberData.getValue().getLineNumber());
      if (StringUtils.isNotBlank(pphoneNumberData.getValue().getExtension())) {
        if (resultNumber.length() <= cursor) {
          cursor += 3;
        }
        resultNumber.append(" - ");
        resultNumber.append(pphoneNumberData.getValue().getExtension());
      }
    }
    if (cursor < 0) {
      cursor = 0;
    } else if (cursor > resultNumber.length()) {
      cursor = resultNumber.length();
    }
    return new ValueWithPos<String>(StringUtils.trimToNull(resultNumber.toString()), cursor);
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
   * @param pphoneNumber phone number to format
   * @param pcountryCode iso code of country
   * @return formated phone number as String
   */
  public final String formatUrl(final String pphoneNumber, final String pcountryCode) {
    return this.formatUrl(this.parsePhoneNumber(pphoneNumber, pcountryCode));
  }

  /**
   * format phone number in URL format with cursor position handling.
   *
   * @param pphoneNumber phone number as String to format with cursor position
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatUrl(final ValueWithPos<String> pphoneNumber) {
    return this.formatUrlWithPos(this.parsePhoneNumber(pphoneNumber));
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
   * format phone number in URL format with cursor position handling.
   *
   * @param pphoneNumber phone number as String to format with cursor position
   * @param pcountryCode iso code of country
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatUrlWithPos(final ValueWithPos<String> pphoneNumber,
      final String pcountryCode) {
    return this.formatUrlWithPos(this.parsePhoneNumber(pphoneNumber, pcountryCode));
  }

  /**
   * format phone number in URL format with cursor position handling.
   *
   * @param pphoneNumberData phone number to format with cursor position
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatUrlWithPos(
      final ValueWithPos<PhoneNumberData> pphoneNumberData) {
    final StringBuilder resultNumber = new StringBuilder();
    if (pphoneNumberData == null) {
      return null;
    }
    int cursor = pphoneNumberData.getPos();
    if (this.isPhoneNumberNotEmpty(pphoneNumberData.getValue())) {
      cursor++;
      resultNumber.append('+').append(pphoneNumberData.getValue().getCountryCode());
      if (resultNumber.length() <= cursor) {
        cursor++;
      }
      resultNumber.append('-');
      if (StringUtils.isNotBlank(pphoneNumberData.getValue().getAreaCode())) {
        resultNumber.append(pphoneNumberData.getValue().getAreaCode());
        if (resultNumber.length() <= cursor) {
          cursor++;
        }
        resultNumber.append('-');
      }
      resultNumber.append(pphoneNumberData.getValue().getLineNumber());
      if (StringUtils.isNotBlank(pphoneNumberData.getValue().getExtension())) {
        if (resultNumber.length() <= cursor) {
          cursor++;
        }
        resultNumber.append('-');
        resultNumber.append(pphoneNumberData.getValue().getExtension());
      }
    }
    return new ValueWithPos<String>(StringUtils.trimToNull(resultNumber.toString()), cursor);
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
   * format phone number in common format with cursor position handling.
   *
   * @param pphoneNumber phone number as String to format with cursor position
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatCommon(final ValueWithPos<String> pphoneNumber) {
    return this.formatCommonWithPos(this.parsePhoneNumber(pphoneNumber), this.defaultCountryData);
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
   * @param pphoneNumber phone number to format
   * @param pcountryCode iso code of country
   * @return formated phone number as String
   */
  public final String formatCommon(final String pphoneNumber, final String pcountryCode) {
    return this.formatCommon(this.parsePhoneNumber(pphoneNumber, pcountryCode),
        CreatePhoneCountryConstantsClass.create().countryMap()
            .get(StringUtils.defaultString(pcountryCode)));
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
    return this.formatCommon(pphoneNumberData, CreatePhoneCountryConstantsClass.create()
        .countryMap().get(StringUtils.defaultString(pcountryCode)));
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
   * format phone number in common format with cursor position handling.
   *
   * @param pphoneNumber phone number as String to format with cursor position
   * @param pcountryCode iso code of country
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatCommonWithPos(final ValueWithPos<String> pphoneNumber,
      final String pcountryCode) {
    return this.formatCommonWithPos(this.parsePhoneNumber(pphoneNumber, pcountryCode),
        CreatePhoneCountryConstantsClass.create().countryMap()
            .get(StringUtils.defaultString(pcountryCode)));
  }

  /**
   * format phone number in common format with cursor position handling.
   *
   * @param pphoneNumberData phone number to format with cursor position
   * @param pcountryData country data
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatCommonWithPos(
      final ValueWithPos<PhoneNumberData> pphoneNumberData, final PhoneCountryData pcountryData) {
    if (pphoneNumberData != null && pcountryData != null
        && StringUtils.equals(pcountryData.getCountryCodeData().getCountryCode(),
            pphoneNumberData.getValue().getCountryCode())) {
      return this.formatCommonNationalWithPos(pphoneNumberData);
    } else {
      return this.formatCommonInternationalWithPos(pphoneNumberData);
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
   * format phone number in common international format with cursor position handling.
   *
   * @param pphoneNumber phone number as String to format with cursor position
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatCommonInternational(
      final ValueWithPos<String> pphoneNumber) {
    return this.formatCommonInternationalWithPos(this.parsePhoneNumber(pphoneNumber));
  }

  /**
   * format phone number in common international format.
   *
   * @param pphoneNumber phone number to format
   * @param pcountryCode iso code of country
   * @return formated phone number as String
   */
  public final String formatCommonInternational(final String pphoneNumber,
      final String pcountryCode) {
    return this.formatCommonInternational(this.parsePhoneNumber(pphoneNumber, pcountryCode));
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
      for (final PhoneCountryCodeData country : CreatePhoneCountryConstantsClass.create()
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
   * format phone number in common international format with cursor position handling.
   *
   * @param pphoneNumber phone number as String to format with cursor position
   * @param pcountryCode iso code of country
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatCommonInternationalWithPos(
      final ValueWithPos<String> pphoneNumber, final String pcountryCode) {
    return this.formatCommonInternationalWithPos(this.parsePhoneNumber(pphoneNumber, pcountryCode));
  }

  /**
   * format phone number in common international format with cursor position handling.
   *
   * @param pphoneNumberData phone number to format with cursor position
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatCommonInternationalWithPos(
      final ValueWithPos<PhoneNumberData> pphoneNumberData) {
    final StringBuilder resultNumber = new StringBuilder();
    if (pphoneNumberData == null) {
      return null;
    }
    int cursor = pphoneNumberData.getPos();
    if (this.isPhoneNumberNotEmpty(pphoneNumberData.getValue())) {
      PhoneCountryData phoneCountryData = null;
      for (final PhoneCountryCodeData country : CreatePhoneCountryConstantsClass.create()
          .countryCodeData()) {
        if (StringUtils.equals(country.getCountryCode(),
            pphoneNumberData.getValue().getCountryCode())) {
          phoneCountryData = country.getPhoneCountryData();
          break;
        }
      }
      if (phoneCountryData == null) {
        return null;
      }
      cursor++;
      resultNumber.append('+').append(pphoneNumberData.getValue().getCountryCode());
      if (resultNumber.length() <= cursor) {
        cursor++;
      }
      resultNumber.append(' ');
      if (resultNumber.length() <= cursor) {
        cursor += 2;
      }
      resultNumber.append('(').append(phoneCountryData.getTrunkCode());
      if (resultNumber.length() <= cursor) {
        cursor++;
      }
      resultNumber.append(')');
      if (StringUtils.isNotBlank(pphoneNumberData.getValue().getAreaCode())) {
        resultNumber.append(pphoneNumberData.getValue().getAreaCode());
        if (resultNumber.length() <= cursor) {
          cursor++;
        }
        resultNumber.append(' ');
      }
      resultNumber.append(pphoneNumberData.getValue().getLineNumber());
      if (StringUtils.isNotBlank(pphoneNumberData.getValue().getExtension())) {
        if (resultNumber.length() <= cursor) {
          cursor++;
        }
        resultNumber.append('-');
        resultNumber.append(pphoneNumberData.getValue().getExtension());
      }
    }
    return new ValueWithPos<String>(StringUtils.trimToNull(resultNumber.toString()), cursor);
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
   * format phone number in common national format.
   *
   * @param pphoneNumber phone number to format
   * @param pcountryCode iso code of country
   * @return formated phone number as String
   */
  public final String formatCommonNational(final String pphoneNumber, final String pcountryCode) {
    return this.formatCommonNational(this.parsePhoneNumber(pphoneNumber, pcountryCode));
  }

  /**
   * format phone number in common national format with cursor position handling.
   *
   * @param pphoneNumber phone number as String to format with cursor position
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatCommonNational(final ValueWithPos<String> pphoneNumber) {
    return this.formatCommonNationalWithPos(this.parsePhoneNumber(pphoneNumber));
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
      for (final PhoneCountryCodeData country : CreatePhoneCountryConstantsClass.create()
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

  /**
   * format phone number in common national format with cursor position handling.
   *
   * @param pphoneNumber phone number as String to format with cursor position
   * @param pcountryCode iso code of country
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatCommonNationalWithPos(
      final ValueWithPos<String> pphoneNumber, final String pcountryCode) {
    return this.formatCommonNationalWithPos(this.parsePhoneNumber(pphoneNumber, pcountryCode));
  }

  /**
   * format phone number in common national format with cursor position handling.
   *
   * @param pphoneNumberData phone number to format with cursor position
   * @return formated phone number as String with new cursor position
   */
  public final ValueWithPos<String> formatCommonNationalWithPos(
      final ValueWithPos<PhoneNumberData> pphoneNumberData) {
    if (pphoneNumberData == null) {
      return null;
    }
    int cursor = pphoneNumberData.getPos();
    final StringBuilder resultNumber = new StringBuilder();
    if (this.isPhoneNumberNotEmpty(pphoneNumberData.getValue())) {
      PhoneCountryData phoneCountryData = null;
      for (final PhoneCountryCodeData country : CreatePhoneCountryConstantsClass.create()
          .countryCodeData()) {
        if (StringUtils.equals(country.getCountryCode(),
            pphoneNumberData.getValue().getCountryCode())) {
          phoneCountryData = country.getPhoneCountryData();
          break;
        }
      }
      if (phoneCountryData == null) {
        return null;
      }
      if (cursor > 0) {
        cursor -= StringUtils.length(pphoneNumberData.getValue().getCountryCode());
        cursor += StringUtils.length(phoneCountryData.getTrunkCode());
      }
      resultNumber.append(phoneCountryData.getTrunkCode());
      if (resultNumber.length() <= cursor) {
        cursor++;
      }
      resultNumber.append(' ');
      if (StringUtils.isNotBlank(pphoneNumberData.getValue().getAreaCode())) {
        final ValueWithPos<String> areaCode = this.groupIntoParts(
            new ValueWithPos<String>(pphoneNumberData.getValue().getAreaCode(), cursor),
            resultNumber.length(), 2);
        cursor = areaCode.getPos();
        resultNumber.append(areaCode.getValue());
      }
      if (resultNumber.length() <= cursor) {
        cursor += 3;
      }
      resultNumber.append(" / ");
      final ValueWithPos<String> lineNumber = this.groupIntoParts(
          new ValueWithPos<String>(pphoneNumberData.getValue().getLineNumber(), cursor),
          resultNumber.length(), 2);
      cursor = lineNumber.getPos();
      resultNumber.append(lineNumber.getValue());
      if (StringUtils.isNotBlank(pphoneNumberData.getValue().getExtension())) {
        if (resultNumber.length() <= cursor) {
          cursor += 3;
        }
        resultNumber.append(" - ");
        final ValueWithPos<String> extension = this.groupIntoParts(
            new ValueWithPos<String>(pphoneNumberData.getValue().getExtension(), cursor),
            resultNumber.length(), 2);
        cursor = extension.getPos();
        resultNumber.append(extension.getValue());
      }
    }
    return new ValueWithPos<String>(StringUtils.trimToNull(resultNumber.toString()), cursor);
  }

  private final ValueWithPos<String> groupIntoParts(final ValueWithPos<String> pstring,
      final int plength, final int pblockLength) {
    if (pstring == null || pstring.getValue() == null) {
      return new ValueWithPos<String>(StringUtils.EMPTY, pblockLength);
    }
    final StringBuilder formatedSb = new StringBuilder();
    int pos = 0;
    for (final char charCode : pstring.getValue().toCharArray()) {
      if (CharUtils.isAsciiNumeric(charCode)) {
        if (pos > 0 && pos % pblockLength == 0) {
          if (formatedSb.length() + plength <= pstring.getPos()) {
            pstring.setPos(pstring.getPos() + 1);
          }
          formatedSb.append(' ');
        }
        formatedSb.append(charCode);
        pos++;
      }
    }
    pstring.setValue(formatedSb.toString());
    return pstring;
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

  /**
   * get suggestions.
   *
   * @param psearch search string
   * @param plimit limit entries
   * @return list of phone number data
   */
  public final List<PhoneNumberData> getSuggstions(final String psearch, final int plimit) {
    return this.getSuggstions(psearch, plimit, Locale.ROOT);
  }

  /**
   * get suggestions.
   *
   * @param psearch search string
   * @param plimit limit entries
   * @return list of phone number data
   */
  public final List<PhoneNumberData> getSuggstions(final String psearch, final int plimit,
      final Locale plocale) {
    final List<PhoneNumberData> suggestList = new ArrayList<>(plimit);
    final String cleanedPhoneNumber = this.cleanString(psearch);
    PhoneCountryCodeData foundCounty = null;
    final List<PhoneCountryCodeData> possibleCountries = new ArrayList<>(plimit);
    for (final PhoneCountryCodeData countryCode : CreatePhoneCountryConstantsClass.create(plocale)
        .countryCodeData()) {
      if (cleanedPhoneNumber.startsWith(countryCode.getCountryCode())) {
        foundCounty = countryCode;
        break;
      }
      if (countryCode.getCountryCode().startsWith(cleanedPhoneNumber)) {
        possibleCountries.add(countryCode);
      }
    }
    if (foundCounty == null) {
      // we don't have found a matching country, show possible countries
      for (final PhoneCountryCodeData country : possibleCountries) {
        final PhoneNumberData entry = new PhoneNumberData();
        entry.setCountryCode(country.getCountryCode());
        entry.setCountryName(country.getCountryCodeName());
        suggestList.add(entry);
      }
    } else {
      // we do have a country, search for possible area codes
      final String phoneNumberWork =
          StringUtils.substring(cleanedPhoneNumber, foundCounty.getCountryCode().length());
      for (final PhoneAreaCodeData areaCode : foundCounty.getAreaCodeData()) {
        if (!areaCode.isRegEx() && areaCode.getAreaCode().startsWith(phoneNumberWork)) {
          final PhoneNumberData entry = new PhoneNumberData();
          entry.setCountryCode(foundCounty.getCountryCode());
          entry.setCountryName(foundCounty.getCountryCodeName());
          entry.setAreaCode(areaCode.getAreaCode());
          entry.setAreaName(areaCode.getAreaName());
          suggestList.add(entry);
        }
      }
    }
    Collections.sort(suggestList, new PhoneNumberSuggestComperator());
    if (suggestList.size() >= plimit) {
      return suggestList.subList(0, plimit);
    }
    return suggestList;
  }

  private String cleanString(final String pphoneNumber) {
    final StringBuilder cleanupString = new StringBuilder(pphoneNumber.length());
    for (final char character : pphoneNumber.toCharArray()) {
      if (character >= '0' && character <= '9') {
        cleanupString.append(character);
      }
    }
    return cleanupString.toString();
  }
}
