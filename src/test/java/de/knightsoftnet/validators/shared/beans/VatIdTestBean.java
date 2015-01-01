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

import org.valkyrie.gwt.bean.client.ReflectedBean;

@VatId
public class VatIdTestBean implements ReflectedBean {

  private final String countryCode;

  private final String vatId;

  /**
   * constructor initializing fields.
   *
   * @param pcountryCode country code
   * @param pvatId postal code
   */
  public VatIdTestBean(final String pcountryCode, final String pvatId) {
    super();
    this.countryCode = pcountryCode;
    this.vatId = pvatId;
  }

  public String getCountryCode() {
    return this.countryCode;
  }

  public String getVatId() {
    return this.vatId;
  }

  @Override
  public String toString() {
    return "VatIdTestBean [countryCode=" + this.countryCode + ", vatId=" + this.vatId + "]";
  }
}
