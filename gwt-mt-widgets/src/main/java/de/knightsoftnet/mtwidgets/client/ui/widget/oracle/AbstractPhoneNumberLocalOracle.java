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

package de.knightsoftnet.mtwidgets.client.ui.widget.oracle;

import de.knightsoftnet.validators.shared.data.PhoneNumberData;
import de.knightsoftnet.validators.shared.util.PhoneNumberUtil;

import com.google.gwt.user.client.ui.SuggestOracle;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * suggest oracle of phone number suggest widget.
 *
 * @author Manfred Tremmel
 *
 */
public abstract class AbstractPhoneNumberLocalOracle<T extends AbstractPhoneNumberItemSuggest>
    extends AbstractPhoneNumberOracle<T> {

  private final PhoneNumberUtil phoneNumberUtil;

  /**
   * constructor initializing with suggestion type.
   *
   * @param ptype class type
   */
  public AbstractPhoneNumberLocalOracle(final Class<T> ptype) {
    super(ptype);
    this.phoneNumberUtil = new PhoneNumberUtil();
  }

  @Override
  public final void requestSuggestions(final Request prequest, final Callback pcallback) {
    final SuggestOracle.Response response = new SuggestOracle.Response();
    if (prequest != null && this.needSuggest(prequest.getQuery())) {
      final List<PhoneNumberData> suggestionList =
          this.phoneNumberUtil.getSuggstions(prequest.getQuery(), this.getLimit(prequest));
      final List<T> suggestions;
      if (suggestionList.isEmpty()) {
        suggestions = Collections.emptyList();
      } else {
        suggestions = suggestionList.stream().map(entry -> this.createInstance(entry))
            .collect(Collectors.toList());
      }
      response.setSuggestions(suggestions);
    }
    pcallback.onSuggestionsReady(prequest, response);
  }
}
