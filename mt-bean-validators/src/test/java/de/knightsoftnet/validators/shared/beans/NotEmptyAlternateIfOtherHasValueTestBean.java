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

import de.knightsoftnet.validators.shared.NotEmptyAlternateIfOtherHasValue;

@NotEmptyAlternateIfOtherHasValue(field = "vatIdentificationNumber",
    fieldAlternate = "taxIdentificationNumber", fieldCompare = "commercial", valueCompare = "true")
public class NotEmptyAlternateIfOtherHasValueTestBean {

  private final Boolean commercial;

  private final String vatIdentificationNumber;

  private final String taxIdentificationNumber;

  /**
   * constructor initializing fields.
   *
   * @param pcommercial inidicator private/commercial
   * @param pvatIdentificationNumber vat id to set
   * @param ptaxIdentificationNumber tax identification number to set
   */
  public NotEmptyAlternateIfOtherHasValueTestBean(final Boolean pcommercial,
      final String pvatIdentificationNumber, final String ptaxIdentificationNumber) {
    super();
    this.commercial = pcommercial;
    this.vatIdentificationNumber = pvatIdentificationNumber;
    this.taxIdentificationNumber = ptaxIdentificationNumber;
  }

  public Boolean getCommercial() {
    return this.commercial;
  }

  public String getVatIdentificationNumber() {
    return this.vatIdentificationNumber;
  }

  public String getTaxIdentificationNumber() {
    return this.taxIdentificationNumber;
  }

  @Override
  public String toString() {
    return "NotEmptyAlternateIfOtherHasValueTestBean [commercial=" + this.commercial
        + ", vatIdentificationNumber=" + this.vatIdentificationNumber
        + ", taxIdentificationNumber=" + this.taxIdentificationNumber + "]";
  }
}
