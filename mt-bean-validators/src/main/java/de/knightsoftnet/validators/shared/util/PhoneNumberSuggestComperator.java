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

import de.knightsoftnet.validators.shared.data.PhoneNumberData;

import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.Objects;

/**
 * comperator to sort phone numbers for suggestion entries. sort order is:
 * <ul>
 * <li>country code, shorter entries are sorted in first, same length entries are sorted by alphabet
 * </li>
 * <li>area code, shorter entries are sorted in first, same length entries are sorted by alphabet
 * </li>
 * <li>line number, sorted by alphabet</li>
 * <li>extension, sorted by alphabet</li>
 * </ul>
 *
 * @author Manfred Tremmel
 */
public class PhoneNumberSuggestComperator implements Comparator<PhoneNumberData> {

  @Override
  public int compare(final PhoneNumberData parg1, final PhoneNumberData parg2) {
    if (Objects.equals(parg1, parg2)) {
      return 0;
    }
    if (parg1 == null) {
      return -1;
    }
    if (parg2 == null) {
      return 1;
    }
    if (StringUtils.equals(parg1.getCountryCode(), parg2.getCountryCode())) {
      if (StringUtils.equals(parg1.getAreaCode(), parg2.getAreaCode())) {
        if (StringUtils.equals(parg1.getLineNumber(), parg2.getLineNumber())) {
          return StringUtils.defaultString(parg1.getExtension())
              .compareTo(StringUtils.defaultString(parg2.getExtension()));
        }
        return StringUtils.defaultString(parg1.getLineNumber())
            .compareTo(StringUtils.defaultString(parg2.getLineNumber()));
      }
      if (StringUtils.length(parg1.getAreaCode()) == StringUtils.length(parg2.getAreaCode())) {
        return parg1.getAreaCode().compareTo(parg2.getAreaCode());
      }
      return StringUtils.length(parg1.getAreaCode()) - StringUtils.length(parg2.getAreaCode());
    }
    if (StringUtils.length(parg1.getCountryCode()) == StringUtils.length(parg2.getCountryCode())) {
      return parg1.getCountryCode().compareTo(parg2.getCountryCode());
    }
    return StringUtils.length(parg1.getCountryCode()) - StringUtils.length(parg2.getCountryCode());
  }
}
