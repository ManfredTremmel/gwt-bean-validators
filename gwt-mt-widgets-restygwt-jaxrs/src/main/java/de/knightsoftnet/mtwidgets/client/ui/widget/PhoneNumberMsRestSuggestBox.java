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

import de.knightsoftnet.mtwidgets.client.ui.widget.oracle.PhoneNumberMsRestOracle;
import de.knightsoftnet.validators.client.rest.helper.FutureResult;
import de.knightsoftnet.validators.shared.data.ValueWithPos;
import de.knightsoftnet.validators.shared.data.ValueWithPosAndCountry;

import java.util.concurrent.ExecutionException;

/**
 * phone number ms suggest widget.
 *
 * @author Manfred Tremmel
 *
 */
public class PhoneNumberMsRestSuggestBox extends AbstractPhoneNumberRestSuggestBox {

  /**
   * default constructor.
   */
  public PhoneNumberMsRestSuggestBox() {
    super(new PhoneNumberMsRestOracle());
  }

  @Override
  public void formatValue(final ValueWithPosAndCountry<String> pkey,
      final FutureResult<ValueWithPos<String>> presult) throws ExecutionException {
    service.formatMsWithPos(pkey, presult);
  }

  @Override
  public boolean isFormatingCharacter(final char pcharacter) {
    return pcharacter == '+' || pcharacter == ' ' || pcharacter == '-' || pcharacter == '('
        || pcharacter == ')';
  }
}
