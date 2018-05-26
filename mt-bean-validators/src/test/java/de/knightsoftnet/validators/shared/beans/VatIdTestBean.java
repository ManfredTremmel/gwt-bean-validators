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

import de.knightsoftnet.validators.shared.VatId;

import java.util.Objects;

import javax.validation.Valid;

@VatId(fieldCountryCode = "postalCodeTestBean.countryCode")
public class VatIdTestBean {

  @Valid
  private final PostalCodeTestBean postalCodeTestBean;

  private final String vatId;

  /**
   * constructor initializing fields.
   *
   * @param ppostalCodeTestBean country code
   * @param pvatId postal code
   */
  public VatIdTestBean(final PostalCodeTestBean ppostalCodeTestBean, final String pvatId) {
    super();
    this.postalCodeTestBean = ppostalCodeTestBean;
    this.vatId = pvatId;
  }

  public final PostalCodeTestBean getPostalCodeTestBean() {
    return this.postalCodeTestBean;
  }

  public String getVatId() {
    return this.vatId;
  }

  @Override
  public String toString() {
    return "VatIdTestBean [postalCodeTestBean=" + Objects.toString(this.postalCodeTestBean)
        + ", vatId=" + this.vatId + "]";
  }
}
