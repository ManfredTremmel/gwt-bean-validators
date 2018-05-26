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
 * country and bank account data.
 *
 * @author Manfred Tremmel
 *
 */
public class CountryBankAccountData implements Comparable<CountryBankAccountData> {

  private final String countryCode;
  private final String bankAccount;

  /**
   * constructor initializing fields.
   *
   * @param pcountryCode country code
   * @param pbankAccount bank account
   */
  public CountryBankAccountData(final String pcountryCode, final String pbankAccount) {
    super();
    this.countryCode = pcountryCode;
    this.bankAccount = pbankAccount;
  }

  public final String getCountryCode() {
    return this.countryCode;
  }

  public final String getBankAccount() {
    return this.bankAccount;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.countryCode, this.bankAccount);
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
    final CountryBankAccountData other = (CountryBankAccountData) pobj;
    return StringUtils.equals(this.countryCode, other.countryCode)
        && StringUtils.equals(this.bankAccount, other.bankAccount);
  }

  @Override
  public int compareTo(final CountryBankAccountData pcompare) {
    if (this.equals(pcompare)) {
      return 0;
    }
    if (pcompare == null) {
      return 1;
    }
    final int countryCompare = StringUtils.compare(this.countryCode, pcompare.countryCode);
    if (countryCompare != 0) {
      return countryCompare;
    }
    return StringUtils.compare(this.bankAccount, pcompare.bankAccount);
  }
}
