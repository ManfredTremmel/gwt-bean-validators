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

import de.knightsoftnet.validators.shared.Tin;

import javax.validation.Valid;

@Tin(fieldCountryCode = "postalCodeTestBean.countryCode")
public class TinTestBean {

  @Valid
  private final PostalCodeTestBean postalCodeTestBean;

  private final String tin;

  /**
   * constructor initializing fields.
   *
   * @param ppostalCodeTestBean country code
   * @param ptin Tax Identication Number
   */
  public TinTestBean(final PostalCodeTestBean ppostalCodeTestBean, final String ptin) {
    super();
    this.postalCodeTestBean = ppostalCodeTestBean;
    this.tin = ptin;
  }

  public final PostalCodeTestBean getPostalCodeTestBean() {
    return this.postalCodeTestBean;
  }

  public final String getTin() {
    return this.tin;
  }

  @Override
  public String toString() {
    return "TinTestBean [postalCodeTestBean=" + this.postalCodeTestBean + ", tin=" + this.tin + "]";
  }
}
