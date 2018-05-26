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

package de.knightsoftnet.validators.client.data;

import de.knightsoftnet.validators.shared.data.BankAccountBicSharedConstants;
import de.knightsoftnet.validators.shared.data.CountryBankAccountData;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BankAccountBicConstants implements BankAccountBicSharedConstants {
  private final Map<CountryBankAccountData, String> bankAccountBicMap;

  public BankAccountBicConstants() {
    super();
    this.bankAccountBicMap = new HashMap<>();
  }

  public BankAccountBicConstants(final Map<CountryBankAccountData, String> pmap) {
    super();
    this.bankAccountBicMap = pmap;
  }

  @Override
  public Map<CountryBankAccountData, String> bankAccounts() {
    return this.bankAccountBicMap;
  }

  @Override
  public void addBankAccounts(final String pcountry, final Map<String, String> pmap) {
    this.bankAccountBicMap.putAll(pmap.entrySet().stream().collect(Collectors.toMap( //
        entry -> new CountryBankAccountData(pcountry, entry.getKey()), //
        entry -> entry.getValue())));
  }
}
