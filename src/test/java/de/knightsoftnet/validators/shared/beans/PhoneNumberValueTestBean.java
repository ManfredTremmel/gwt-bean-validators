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

package de.knightsoftnet.validators.shared.beans;

import de.knightsoftnet.validators.shared.PhoneNumberValue;

@PhoneNumberValue
public class PhoneNumberValueTestBean {

  private final String countryCode;

  private final String phoneNumber;

  /**
   * constructor initializing field.
   *
   * @param pcountryCode country code to set
   * @param pphoneNumber phone number to set
   */
  public PhoneNumberValueTestBean(final String pcountryCode, final String pphoneNumber) {
    super();
    this.countryCode = pcountryCode;
    this.phoneNumber = pphoneNumber;
  }

  public final String getPhoneNumber() {
    return this.phoneNumber;
  }

  public String getCountryCode() {
    return this.countryCode;
  }

  @Override
  public String toString() {
    return "PhoneNumberValueTestBean [countryCode=" + this.countryCode + ", phoneNumber="
        + this.phoneNumber + "]";
  }

}
