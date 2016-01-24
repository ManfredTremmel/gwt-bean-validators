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
import de.knightsoftnet.validators.shared.impl.Isbn10FormatedValidator;
import de.knightsoftnet.validators.shared.impl.Isbn10Validator;
import de.knightsoftnet.validators.shared.impl.Isbn13FormatedValidator;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Isbn Util, format and compress isbn (10 and 13).
 *
 * @author Manfred Tremmel
 *
 */
public class IsbnUtil {
  /**
   * character used for separating blocks.
   */
  public static final char SEPARATOR = '-';

  /**
   * format isbn10 or 13 with separator signs.
   *
   * @param pentry string to format and cursor position
   * @return formated string with new cursor position
   */
  public static ValueWithPos<String> isbnFormatWithPos(final ValueWithPos<String> pentry) {
    if (pentry == null) {
      return null;
    }
    final int length = isbnCompress(pentry.getValue()).length();
    if (length > Isbn10Validator.ISBN10_LENGTH) {
      return isbn13FormatWithPos(pentry);
    } else {
      return isbn10FormatWithPos(pentry);
    }
  }

  /**
   * format isbn10 or 13 with separator signs.
   *
   * @param pstring string to format
   * @return formated string
   */
  public static String isbnFormat(final String pstring) {
    if (pstring == null) {
      return null;
    }
    final int length = isbnCompress(pstring).length();
    if (length > Isbn10Validator.ISBN10_LENGTH) {
      return isbn13Format(pstring);
    } else {
      return isbn10Format(pstring);
    }
  }

  /**
   * format isbn10 with separator signs.
   *
   * @param pentry string to format and cursor position
   * @return formated string with new cursor position
   */
  public static ValueWithPos<String> isbn10FormatWithPos(final ValueWithPos<String> pentry) {
    if (pentry == null) {
      return null;
    }
    if (StringUtils.isNotEmpty(pentry.getValue())) {
      final StringBuilder ibanSb = new StringBuilder(Isbn10FormatedValidator.ISBN10_LENGTH);
      int pos = 0;
      int posformated = 0;
      for (final char charCode : pentry.getValue().toCharArray()) {
        if (CharUtils.isAsciiNumeric(charCode)) {
          if (pos == 1 || pos == 3 || pos == 9) {
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
   * format isbn10 with separator signs.
   *
   * @param pstring string to format
   * @return formated string
   */
  public static String isbn10Format(final String pstring) {
    if (pstring == null) {
      return null;
    }
    final ValueWithPos<String> formatedValue =
        isbn10FormatWithPos(new ValueWithPos<String>(pstring, -1));
    return formatedValue.getValue();
  }

  /**
   * format isbn13 with separator signs.
   *
   * @param pentry string to format and cursor position
   * @return formated string with new cursor position
   */
  public static ValueWithPos<String> isbn13FormatWithPos(final ValueWithPos<String> pentry) {
    if (pentry == null) {
      return null;
    }
    if (StringUtils.isNotEmpty(pentry.getValue())) {
      final StringBuilder ibanSb = new StringBuilder(Isbn13FormatedValidator.ISBN13_LENGTH);
      int pos = 0;
      int posformated = 0;
      for (final char charCode : pentry.getValue().toCharArray()) {
        if (CharUtils.isAsciiNumeric(charCode)) {
          if (pos == 3 || pos == 4 || pos == 6 || pos == 12) {
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
   * format isbn13 with separator signs.
   *
   * @param pstring string to format
   * @return formated string
   */
  public static String isbn13Format(final String pstring) {
    if (pstring == null) {
      return null;
    }
    final ValueWithPos<String> formatedValue =
        isbn13FormatWithPos(new ValueWithPos<String>(pstring, -1));
    return formatedValue.getValue();
  }

  /**
   * compress isbn, remove all separators inside.
   *
   * @param pstring string to compress
   * @return isbn without separators
   */
  public static String isbnCompress(final String pstring) {
    return StringUtils.remove(pstring, SEPARATOR);
  }
}
