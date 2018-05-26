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

import de.knightsoftnet.validators.shared.BankCountry;
import de.knightsoftnet.validators.shared.Bic;
import de.knightsoftnet.validators.shared.Iban;

@BankCountry
public class BankCountryTestBean {

  private final String countryCode;

  @Iban
  private final String iban;

  @Bic
  private final String bic;

  /**
   * constructor initializing fields.
   *
   * @param pcountryCode country code to set
   * @param piban iban to set
   * @param pbic bic to set
   */
  public BankCountryTestBean(final String pcountryCode, final String piban, final String pbic) {
    super();
    this.countryCode = pcountryCode;
    this.iban = piban;
    this.bic = pbic;
  }

  public String getCountryCode() {
    return this.countryCode;
  }

  public String getIban() {
    return this.iban;
  }

  public String getBic() {
    return this.bic;
  }

  @Override
  public String toString() {
    return "BankCountryTestBean [countryCode=" + this.countryCode + ", iban=" + this.iban
        + ", bic=" + this.bic + "]";
  }
}
