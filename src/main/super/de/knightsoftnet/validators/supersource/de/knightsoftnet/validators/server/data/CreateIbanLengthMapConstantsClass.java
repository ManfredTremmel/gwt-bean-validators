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

import de.knightsoftnet.validators.client.data.IbanLengthMapConstants;
import de.knightsoftnet.validators.client.data.IbanLengthMapConstantsClient;
import de.knightsoftnet.validators.shared.data.IbanLengthMapSharedConstants;

import com.google.gwt.core.client.GWT;

/**
 * Read gwt constants from properties file on client side.
 *
 * @author Manfred Tremmel
 *
 */
public class CreateIbanLengthMapConstantsClass {

  private static volatile IbanLengthMapConstantsClient ibanLengthMapConstants = null;

  /**
   * Instantiates a class via deferred binding.
   *
   * @return the new instance, which must be cast to the requested class
   */
  public static IbanLengthMapSharedConstants create() {
    if (ibanLengthMapConstants == null) { // NOPMD it's thread save!
      synchronized (IbanLengthMapConstantsClient.class) {
        if (ibanLengthMapConstants == null) {
          final IbanLengthMapConstants ibanLengthMap = GWT.create(IbanLengthMapConstants.class);
          ibanLengthMapConstants = new IbanLengthMapConstantsClient(ibanLengthMap.ibanLengths());
        }
      }
    }
    return ibanLengthMapConstants;
  }
}
