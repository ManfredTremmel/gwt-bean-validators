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

package de.knightsoftnet.validators.server.data;

import de.knightsoftnet.validators.client.data.BankAccountBicATConstants;
import de.knightsoftnet.validators.client.data.BankAccountBicConstants;
import de.knightsoftnet.validators.client.data.BankAccountBicDEConstants;
import de.knightsoftnet.validators.shared.data.BankAccountBicSharedConstants;

import com.google.gwt.core.client.GWT;

/**
 * Read gwt constants from properties file on client side.
 *
 * @author Manfred Tremmel
 *
 */
public class CreateBankAccountBicMapConstantsClass {

  private static volatile BankAccountBicConstants bicMapConstants = null;

  /**
   * Instantiates a class via deferred binding.
   *
   * @return the new instance, which must be cast to the requested class
   */
  public static BankAccountBicSharedConstants create() {
    if (bicMapConstants == null) { // NOPMD it's thread save!
      synchronized (BankAccountBicConstants.class) {
        if (bicMapConstants == null) {
          final BankAccountBicConstants bicMapConstantsTmp = new BankAccountBicConstants();

          final BankAccountBicATConstants bankAccountBicAtConstants =
              GWT.create(BankAccountBicATConstants.class);
          bicMapConstantsTmp.addBankAccounts("AT", bankAccountBicAtConstants.bankAccounts());

          final BankAccountBicDEConstants bankAccountBicDeConstants =
              GWT.create(BankAccountBicDEConstants.class);
          bicMapConstantsTmp.addBankAccounts("DE", bankAccountBicDeConstants.bankAccounts());

          bicMapConstants = bicMapConstantsTmp;
        }
      }
    }
    return bicMapConstants;
  }
}
