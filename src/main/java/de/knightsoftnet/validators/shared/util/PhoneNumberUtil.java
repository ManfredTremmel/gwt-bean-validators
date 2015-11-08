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

  public PhoneNumberUtil() {
    super();
    this.countryConstants = CreateClass.create(PhoneCountrySharedConstants.class);
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
          default:
            break;
        }
      }
      String phoneNumberWork = StringUtils.reverse(cleanupString.toString());
      for (final PhoneCountryCodeData countryCode : this.countryConstants.countryCodeData()) {
        if (phoneNumberWork.startsWith(countryCode.getCountryCode())) {
          phoneNumberData.setCountryCode(countryCode.getCountryCode());
          phoneNumberWork = phoneNumberWork.substring(countryCode.getCountryCode().length());
          for (final PhoneAreaCodeData areaCode : countryCode.getAreaCodeData()) {
            if (areaCode.isRegEx() && phoneNumberWork.matches("^" + areaCode.getAreaCode())) {
              final String areaCodeRemember = phoneNumberWork;
              phoneNumberWork =
                  phoneNumberWork.replaceFirst(areaCode.getAreaCode(), StringUtils.EMPTY);
              phoneNumberData.setAreaCode(areaCodeRemember.substring(0,
                  areaCodeRemember.length() - phoneNumberWork.length()));
              break;
            } else if (!areaCode.isRegEx() && phoneNumberWork.startsWith(areaCode.getAreaCode())) {
              phoneNumberData.setAreaCode(areaCode.getAreaCode());
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
}
