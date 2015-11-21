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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class PhoneCountryConstantsImpl implements PhoneCountrySharedConstants {
  private final Set<PhoneCountryCodeData> countryCode;
  private final Map<String, PhoneCountryData> countriesMap;

  /**
   * default constructor.
   */
  public PhoneCountryConstantsImpl() {
    super();
    this.countryCode = new TreeSet<>();
    this.countriesMap = new HashMap<>();
  }

  /**
   * constructor initializing set.
   *
   * @param pcountryCode set of country codes
   * @param pcountryMap map of countries with corresponding PhoneCountryCodeData
   */
  public PhoneCountryConstantsImpl(final Set<PhoneCountryCodeData> pcountryCode,
      final Map<String, PhoneCountryData> pcountryMap) {
    super();
    this.countryCode = pcountryCode;
    this.countriesMap = pcountryMap;
  }

  @Override
  public Set<PhoneCountryCodeData> countryCodeData() {
    return this.countryCode;
  }

  @Override
  public Map<String, PhoneCountryData> countryMap() {
    return this.countriesMap;
  }
}
