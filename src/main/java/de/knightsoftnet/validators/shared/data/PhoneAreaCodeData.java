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

  /**
   * constructor initializing fields.
   *
   * @param pareaCode area code
   * @param pareaName area name
   */
  public PhoneAreaCodeData(final String pareaCode, final String pareaName) {
    super();
    this.areaCode = StringUtils.replace(pareaCode, "u5b", "[");
    this.regEx = !StringUtils.isNumeric(pareaCode);
    this.areaName = pareaName;
  }

  public String getAreaCode() {
    return this.areaCode;
  }

  public boolean isRegEx() {
    return this.regEx;
  }

  public String getAreaName() {
    return this.areaName;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.areaCode);
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
    return StringUtils.equals(this.areaCode, other.areaCode);
  }

  @Override
  public int compareTo(final PhoneAreaCodeData pcompare) {
    if (this.equals(pcompare)) {
      return 0;
    }
    if (pcompare == null) {
      return 1;
    }
    if (this.regEx == pcompare.regEx) {
      if (this.areaCode == null) {
        return -1;
      }
      if (this.areaCode.equals(pcompare.areaCode)) {
        return 0;
      }
      if (pcompare.areaCode == null) {
        return 1;
      }
      if (this.areaCode.startsWith(pcompare.areaCode)) {
        return 1;
      }
      if (pcompare.areaCode.startsWith(this.areaCode)) {
        return -1;
      }
      return this.areaCode.compareTo(pcompare.areaCode);
    }
    return this.regEx ? 1 : -1;
  }
}
