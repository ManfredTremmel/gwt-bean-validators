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

import com.google.gwt.regexp.shared.RegExp;

import org.apache.commons.lang3.StringUtils;

/**
 * RegEx Util contails hlper routines for regular expressions.
 *
 * @author Manfred Tremmel
 *
 */
public class RegExUtil {

  /**
   * get all allowed characters which can be part of a String which matches a given regular
   * expression. TODO: this is a first feature incomplete implementation, has to be improved.
   *
   * @param pregEx string contains a regular expression pattern
   * @return string with all characters that can be part of a string that matches the regex
   */
  public static String getAllowedCharactersForRegEx(final String pregEx) {
    if (StringUtils.isEmpty(pregEx)) {
      return null;
    }
    final StringBuilder regExCheck = new StringBuilder();
    boolean inSequence = false;
    boolean isNegativeSequence = false;
    boolean inSize = false;
    boolean isMasked = false;

    for (final char character : pregEx.toCharArray()) {
      switch (character) {
        case '\\':
          if (isMasked || inSequence) {
            regExCheck.append(character);
          }
          if (!inSequence) {
            isMasked = !isMasked;
          }
          break;
        case '^':
          if (inSequence) {
            if (isMasked) {
              regExCheck.append(character);
            } else {
              isNegativeSequence = true;
            }
          }
          isMasked = false;
          break;
        case '$':
        case '*':
        case '+':
        case '?':
        case '|':
          if (isMasked || inSequence) {
            regExCheck.append(character);
          }
          isMasked = false;
          break;
        case '[':
          if (isMasked || inSequence) {
            regExCheck.append(character);
          } else {
            inSequence = true;
            isNegativeSequence = false;
          }
          isMasked = false;
          break;
        case ']':
          if (isMasked) {
            regExCheck.append(character);
          } else {
            inSequence = false;
            isNegativeSequence = false;
          }
          isMasked = false;
          break;
        case '{':
          if (isMasked || inSequence) {
            regExCheck.append(character);
          } else {
            inSize = true;
          }
          isMasked = false;
          break;
        case '}':
          if (isMasked || inSequence) {
            regExCheck.append(character);
          } else {
            inSize = false;
          }
          isMasked = false;
          break;
        case '(':
          if (isMasked || inSequence) {
            regExCheck.append(character);
          }
          isMasked = false;
          break;
        case ')':
          if (isMasked || inSequence) {
            regExCheck.append(character);
          }
          isMasked = false;
          break;
        default:
          if (inSize) {
            if (character != ',' && (character < '0' || character > '9')) {
              regExCheck.append(character);
            }
          } else if (!isNegativeSequence) {
            if (isMasked) {
              regExCheck.append('\\');
            }
            regExCheck.append(character);
          }
          isMasked = false;
          break;
      }
    }
    final RegExp regEx = RegExp.compile("[" + regExCheck.toString() + "]");
    final StringBuilder result = new StringBuilder();
    for (int count = Character.MIN_VALUE; count < Character.MAX_VALUE; count++) {
      if (regEx.exec(String.valueOf((char) count)) != null) {
        result.append((char) count);
      }
    }
    return result.toString();
  }
}
