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
 * phone number country data.
 *
 * @author Manfred Tremmel
 *
 */
public class PhoneCountryData {
  private final String countryCode;
  private final String countryCodeName;
  private final String trunkCode;
  private final String exitCode;
  private final boolean areaCodeMustBeFilled;
  private final PhoneCountryCodeData countryCodeData;


  /**
   * constructor initializing fields.
   *
   * @param pcountryCode country iso code
   * @param pcountryCodeName country name
   * @param ptrunkCode trunk code (used for country internal calls)
   * @param pexitCode exit code (used for international calls
   * @param pareaCodeMustBeFilled true if area code must be filled in this country
   * @param pcountryCodeData country code data
   */
  public PhoneCountryData(final String pcountryCode, final String pcountryCodeName,
      final String ptrunkCode, final String pexitCode, final boolean pareaCodeMustBeFilled,
      final PhoneCountryCodeData pcountryCodeData) {
    super();
    countryCode = pcountryCode;
    countryCodeName = pcountryCodeName;
    trunkCode = ptrunkCode;
    exitCode = pexitCode;
    areaCodeMustBeFilled = pareaCodeMustBeFilled;
    countryCodeData = pcountryCodeData;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public String getCountryCodeName() {
    return countryCodeName;
  }

  public String getTrunkCode() {
    return trunkCode;
  }

  public String getExitCode() {
    return exitCode;
  }

  public final boolean isAreaCodeMustBeFilled() {
    return areaCodeMustBeFilled;
  }

  public PhoneCountryCodeData getCountryCodeData() {
    return countryCodeData;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(countryCode);
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
    final PhoneCountryData other = (PhoneCountryData) pobj;
    return StringUtils.equals(countryCode, other.countryCode);
  }
}
