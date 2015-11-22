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

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * phone number region/country code data.
 *
 * @author Manfred Tremmel
 *
 */
public class PhoneCountryCodeData implements Comparable<PhoneCountryCodeData> {
  private final String countryCode;
  private final String countryCodeName;
  private PhoneCountryData phoneCountryData;
  private final Set<PhoneAreaCodeData> areaCodeData;

  /**
   * constructor initializing fields.
   *
   * @param pcountryCode country code
   * @param pcountryCodeName country code name
   */
  public PhoneCountryCodeData(final String pcountryCode, final String pcountryCodeName) {
    super();
    this.countryCode = pcountryCode;
    this.countryCodeName = pcountryCodeName;
    this.areaCodeData = new TreeSet<>();
  }

  public String getCountryCode() {
    return this.countryCode;
  }

  public String getCountryCodeName() {
    return this.countryCodeName;
  }

  public PhoneCountryData getPhoneCountryData() {
    return this.phoneCountryData;
  }

  public void setPhoneCountryData(final PhoneCountryData pphoneCountryData) {
    this.phoneCountryData = pphoneCountryData;
  }

  public Set<PhoneAreaCodeData> getAreaCodeData() {
    return Collections.unmodifiableSet(this.areaCodeData);
  }

  public void addAreaCodeData(final PhoneAreaCodeData pareaCodeData) {
    this.areaCodeData.add(pareaCodeData);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.countryCode);
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
    final PhoneCountryCodeData other = (PhoneCountryCodeData) pobj;
    return StringUtils.equals(this.countryCode, other.countryCode);
  }

  @Override
  public int compareTo(final PhoneCountryCodeData pcompare) {
    if (this.countryCode == null) {
      return -1;
    }
    if (this.countryCode.equals(pcompare.countryCode)) {
      return 0;
    }
    if (pcompare.countryCode == null) {
      return 1;
    }
    if (this.countryCode.startsWith(pcompare.countryCode)) {
      return 1;
    }
    if (pcompare.countryCode.startsWith(this.countryCode)) {
      return -1;
    }
    return this.countryCode.compareTo(pcompare.countryCode);
  }

}
