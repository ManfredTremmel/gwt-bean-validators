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

import de.knightsoftnet.validators.shared.data.ValueWithPos;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Iban Util, format and compress ibans.
 *
 * @author Manfred Tremmel
 *
 */
public class IbanUtil {
  /**
   * character used for separating blocks.
   */
  public static final char SEPARATOR = ' ';

  private static final int BLOCK_LENGTH = 4;

  /**
   * format iban to four character blocks.
   *
   * @param pentry string to format and cursor position
   * @return formated string with new cursor position
   */
  public static ValueWithPos<String> ibanFormatWithPos(final ValueWithPos<String> pentry) {
    if (pentry == null) {
      return null;
    }
    if (StringUtils.isNotEmpty(pentry.getValue())) {
      final StringBuilder ibanSb = new StringBuilder(pentry.getValue().length());
      int pos = 0;
      int posformated = 0;
      for (final char charCode : pentry.getValue().toCharArray()) {
        if (CharUtils.isAsciiAlphaUpper(charCode) || CharUtils.isAsciiNumeric(charCode)) {
          if (pos > 0 && pos % BLOCK_LENGTH == 0) {
            ibanSb.append(SEPARATOR);
            if (posformated <= pentry.getPos()) {
              pentry.setPos(pentry.getPos() + 1);
            }
            posformated++;
          }
          ibanSb.append(charCode);
          pos++;
          posformated++;
        } else {
          if (posformated < pentry.getPos()) {
            pentry.setPos(pentry.getPos() - 1);
          }
        }
      }
      pentry.setValue(ibanSb.toString());
      if (pentry.getPos() < 0) {
        pentry.setPos(0);
      } else if (pentry.getPos() >= ibanSb.length()) {
        pentry.setPos(ibanSb.length());
      }
    }
    return pentry;
  }

  /**
   * format iban to four character blocks.
   *
   * @param pstring string to format
   * @return formated string
   */
  public static String ibanFormat(final String pstring) {
    if (pstring == null) {
      return null;
    }
    final ValueWithPos<String> formatedValue =
        ibanFormatWithPos(new ValueWithPos<String>(pstring, -1));
    return formatedValue.getValue();
  }

  /**
   * compress iban, remove all blanks inside.
   *
   * @param pstring string to compress
   * @return iban without spaces
   */
  public static String ibanCompress(final String pstring) {
    return StringUtils.remove(pstring, SEPARATOR);
  }
}
