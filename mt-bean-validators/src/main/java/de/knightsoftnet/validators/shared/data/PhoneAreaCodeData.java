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

package de.knightsoftnet.validators.shared.data;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * phone number area code data.
 *
 * @author Manfred Tremmel
 *
 */
public class PhoneAreaCodeData implements Comparable<PhoneAreaCodeData> {
  private final String areaCode;
  private final boolean regEx;
  private final String areaName;
  private final int minLength;
  private final int maxLength;

  /**
   * constructor initializing fields.
   *
   * @param pareaCode area code
   * @param pareaName area name
   * @param pcountryCodeLength length of the country code
   */
  public PhoneAreaCodeData(final String pareaCode, final String pareaName,
      final int pcountryCodeLength) {
    super();
    areaCode = StringUtils.replace(pareaCode, "u5b", "[");
    regEx = !StringUtils.isNumeric(pareaCode);
    if (StringUtils.contains(pareaName, '¡')) {
      final String[] splittedName = StringUtils.defaultString(pareaName).split("¡");
      areaName = splittedName[0];
      if (splittedName.length > 1) {
        minLength = Integer.parseInt(splittedName[1]);
      } else {
        minLength = 2;
      }
      if (splittedName.length > 2) {
        maxLength = Integer.parseInt(splittedName[2]);
      } else {
        maxLength = 15 - pcountryCodeLength - (regEx ? 3 : StringUtils.length(pareaCode));
      }
    } else {
      areaName = pareaName;
      minLength = 2;
      maxLength = 15 - pcountryCodeLength - (regEx ? 2 : StringUtils.length(pareaCode));
    }
  }

  public String getAreaCode() {
    return areaCode;
  }

  public boolean isRegEx() {
    return regEx;
  }

  public String getAreaName() {
    return areaName;
  }

  public final int getMinLength() {
    return minLength;
  }

  public final int getMaxLength() {
    return maxLength;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(areaCode);
  }

  @Override
  public boolean equals(final Object pobj) {
    if (this == pobj) {
      return true;
    }
    if (pobj == null) {
      return false;
    }
    if (this.getClass() != pobj.getClass()) {
      return false;
    }
    final PhoneAreaCodeData other = (PhoneAreaCodeData) pobj;
    return StringUtils.equals(areaCode, other.areaCode);
  }

  @Override
  public int compareTo(final PhoneAreaCodeData pcompare) {
    if (equals(pcompare)) {
      return 0;
    }
    if (pcompare == null) {
      return 1;
    }
    if (regEx == pcompare.regEx) {
      if (areaCode == null) {
        return -1;
      }
      if (areaCode.equals(pcompare.areaCode)) {
        return 0;
      }
      if (pcompare.areaCode == null) {
        return 1;
      }
      if (areaCode.startsWith(pcompare.areaCode)) {
        return -1;
      }
      if (pcompare.areaCode.startsWith(areaCode)) {
        return 1;
      }
      return areaCode.compareTo(pcompare.areaCode);
    }
    return regEx ? 1 : -1;
  }
}
