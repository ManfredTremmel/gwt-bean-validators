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

import de.knightsoftnet.validators.shared.TaxNumber;

import javax.validation.Valid;

@TaxNumber(fieldCountryCode = "postalCodeTestBean.countryCode")
public class TaxNumberTestBean {

  @Valid
  private final PostalCodeTestBean postalCodeTestBean;

  private final String taxNumber;

  /**
   * constructor initializing fields.
   *
   * @param ppostalCodeTestBean country code
   * @param ptaxNumber Tax Identication Number
   */
  public TaxNumberTestBean(final PostalCodeTestBean ppostalCodeTestBean, final String ptaxNumber) {
    super();
    this.postalCodeTestBean = ppostalCodeTestBean;
    this.taxNumber = ptaxNumber;
  }

  public final PostalCodeTestBean getPostalCodeTestBean() {
    return this.postalCodeTestBean;
  }

  public final String getTaxNumber() {
    return this.taxNumber;
  }

  @Override
  public String toString() {
    return "TaxNumberTestBean [postalCodeTestBean=" + this.postalCodeTestBean + ", taxNumber="
        + this.taxNumber + "]";
  }

}
