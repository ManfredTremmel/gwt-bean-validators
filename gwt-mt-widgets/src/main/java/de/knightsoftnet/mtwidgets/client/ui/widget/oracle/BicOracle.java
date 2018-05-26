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

import de.knightsoftnet.validators.client.data.BicMapConstants;
import de.knightsoftnet.validators.shared.impl.BicValidator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.SuggestOracle;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 * suggest oracle of BIC suggest widget.
 *
 * @author Manfred Tremmel
 *
 */
public class BicOracle extends SuggestOracle {

  /**
   * map of the bic values.
   */
  private static final BicMapConstants BIC_MAP = GWT.create(BicMapConstants.class);

  /**
   * default limit suggests.
   */
  private static final int LIMIT_DEFAULT = 20;

  @Override
  public final boolean isDisplayStringHTML() {
    return true;
  }

  @Override
  public final void requestSuggestions(final Request prequest, final Callback pcallback) {
    final SuggestOracle.Response response = new SuggestOracle.Response();
    if (prequest != null && StringUtils.isNotEmpty(prequest.getQuery())) {
      final int limit;
      if (prequest.getLimit() > 0) {
        limit = prequest.getLimit();
      } else {
        limit = BicOracle.LIMIT_DEFAULT;
      }
      final List<BicItemSuggest> suggestions = new ArrayList<>(limit);
      // first run, starts with exact test
      for (final Entry<String, String> entry : BicOracle.BIC_MAP.bics().entrySet()) {
        if (entry.getKey().startsWith(prequest.getQuery())) {
          suggestions
              .add(new BicItemSuggest(entry.getKey(), entry.getKey().replace(prequest.getQuery(),
                  "<strong>" + prequest.getQuery() + "</strong>"), entry.getValue()));
          if (suggestions.size() >= limit) {
            break;
          }
        }
      }
      // second run, contains
      if (suggestions.isEmpty()) {
        for (final Entry<String, String> entry : BicOracle.BIC_MAP.bics().entrySet()) {
          if (entry.getKey().contains(prequest.getQuery())) {
            suggestions
                .add(new BicItemSuggest(entry.getKey(), entry.getKey().replace(prequest.getQuery(),
                    "<strong>" + prequest.getQuery() + "</strong>"), entry.getValue()));
            if (suggestions.size() >= limit) {
              break;
            }
          }
        }
      }
      // third run, contains with limited length
      if (suggestions.isEmpty()) {
        for (final Entry<String, String> entry : BicOracle.BIC_MAP.bics().entrySet()) {
          if (entry.getKey().contains(
              StringUtils.substring(prequest.getQuery(), 0, BicValidator.BIC_LENGTH_MIN))) {
            if (prequest.getQuery().length() == BicValidator.BIC_LENGTH_MAX) {
              suggestions.add(new BicItemSuggest(prequest.getQuery(),
                  "<strong>" + prequest.getQuery() + "</strong>", entry.getValue()));
            }
            suggestions.add(new BicItemSuggest(entry.getKey(), //
                entry.getKey().replace(StringUtils.substring(prequest.getQuery(), 0, //
                    BicValidator.BIC_LENGTH_MIN), //
                    "<strong>" + StringUtils.substring(prequest.getQuery(), 0, //
                        BicValidator.BIC_LENGTH_MIN) + "</strong>"), //
                entry.getValue()));
            if (suggestions.size() >= limit) {
              break;
            }
          }
        }
      }
      response.setSuggestions(suggestions);
    }
    pcallback.onSuggestionsReady(prequest, response);
  }
}
