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

package de.knightsoftnet.validators.client.rest.api;

import de.knightsoftnet.validators.client.rest.path.PathDefinitionInterface;
import de.knightsoftnet.validators.shared.ResourcePaths.PhoneNumber;

import com.google.gwt.core.client.GWT;

import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestServiceProxy;

/**
 * Factory Class to get Handlers.
 *
 * @author Manfred Tremmel
 */
public class ServiceFactory {

  private static volatile PhoneNumberServiceAsync phoneNumberService;

  /**
   * get a upper case key press handler.
   *
   * @return UpperAsciiKeyPressHandler
   */
  public static final PhoneNumberServiceAsync getPhoneNumberService() { // NOPMD it's thread save!
    if (ServiceFactory.phoneNumberService == null) {
      synchronized (PhoneNumberServiceAsync.class) {
        if (ServiceFactory.phoneNumberService == null) {
          ServiceFactory.phoneNumberService = GWT.create(PhoneNumberServiceAsync.class);
        }
      }
      final PathDefinitionInterface pathDefinition = GWT.create(PathDefinitionInterface.class);
      ((RestServiceProxy) ServiceFactory.phoneNumberService)
          .setResource(new Resource(pathDefinition.getRestBasePath() + "/" + PhoneNumber.ROOT));
    }
    return ServiceFactory.phoneNumberService;
  }
}
