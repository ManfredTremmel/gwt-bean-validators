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

package de.knightsoftnet.mtwidgets.client.ui.widget;

import de.knightsoftnet.gwtp.spring.client.rest.helper.FutureResult;
import de.knightsoftnet.gwtp.spring.client.session.Session;
import de.knightsoftnet.gwtp.spring.shared.data.ValueWithPosAndCountry;
import de.knightsoftnet.mtwidgets.client.ui.widget.oracle.PhoneNumberUriRestOracle;
import de.knightsoftnet.validators.client.services.PhoneNumberRestService;
import de.knightsoftnet.validators.shared.data.ValueWithPos;

import com.gwtplatform.dispatch.rest.client.RestDispatch;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

/**
 * phone number URI suggest widget.
 *
 * @author Manfred Tremmel
 *
 */
public class PhoneNumberUriRestSuggestBox extends AbstractPhoneNumberRestSuggestBox {

  private final PhoneNumberRestService service;
  private final RestDispatch dispatcher;

  /**
   * default constructor.
   */
  @Inject
  public PhoneNumberUriRestSuggestBox(final RestDispatch pdispatcher,
      final PhoneNumberRestService pservice, final PhoneNumberUriRestOracle poracle,
      final Session psession) {
    super(poracle, psession);
    dispatcher = pdispatcher;
    service = pservice;
  }

  @Override
  public void formatValue(final ValueWithPosAndCountry<String> pkey,
      final FutureResult<ValueWithPos<String>> presult) throws ExecutionException {
    dispatcher.execute(service.formatUrlWithPos(pkey), presult);
  }

  @Override
  public boolean isFormatingCharacter(final char pcharacter) {
    return pcharacter == '+' || pcharacter == '-';
  }
}
