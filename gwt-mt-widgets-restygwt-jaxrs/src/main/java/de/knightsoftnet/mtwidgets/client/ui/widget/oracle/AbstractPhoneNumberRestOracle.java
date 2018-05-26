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

import de.knightsoftnet.validators.client.rest.api.PhoneNumberServiceAsync;
import de.knightsoftnet.validators.client.rest.api.ServiceFactory;
import de.knightsoftnet.validators.client.rest.helper.FutureResult;
import de.knightsoftnet.validators.shared.data.PhoneNumberData;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.ui.SuggestOracle;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * suggest oracle of phone number suggest widget.
 *
 * @author Manfred Tremmel
 *
 */
public abstract class AbstractPhoneNumberRestOracle<T extends AbstractPhoneNumberItemSuggest>
    extends AbstractPhoneNumberOracle<T> {

  private final PhoneNumberServiceAsync service;

  /**
   * cache map.
   */
  private final LoadingCache<String, FutureResult<List<PhoneNumberData>>> cache;

  /**
   * constructor initializing with suggestion type.
   *
   * @param ptype class type
   */
  public AbstractPhoneNumberRestOracle(final Class<T> ptype) {
    super(ptype);
    this.service = ServiceFactory.getPhoneNumberService();

    this.cache = CacheBuilder.newBuilder().maximumSize(10000).expireAfterWrite(10, TimeUnit.DAYS)
        .build(new CacheLoader<String, FutureResult<List<PhoneNumberData>>>() {

          @Override
          public FutureResult<List<PhoneNumberData>> load(final String pkey) {
            final FutureResult<List<PhoneNumberData>> result = new FutureResult<>();
            AbstractPhoneNumberRestOracle.this.service.getSuggestions(
                LocaleInfo.getCurrentLocale().getLocaleName(), pkey,
                AbstractPhoneNumberOracle.LIMIT_DEFAULT, result);
            return result;
          }
        });

  }

  @Override
  public final void requestSuggestions(final Request prequest, final Callback pcallback) {
    final SuggestOracle.Response response = new SuggestOracle.Response();
    if (prequest != null && this.needSuggest(prequest.getQuery())) {
      try {
        final FutureResult<List<PhoneNumberData>> result =
            this.cache.get(this.cleanRequest(prequest));
        result.addCallback(new MethodCallback<List<PhoneNumberData>>() {

          @Override
          public void onFailure(final Method pmethod, final Throwable pexception) {
            GWT.log(pexception.getMessage(), pexception);
            pcallback.onSuggestionsReady(prequest, response);
          }

          @Override
          public void onSuccess(final Method pmethod, final List<PhoneNumberData> presponse) {
            response.setSuggestions(
                AbstractPhoneNumberRestOracle.this.convertListToSuggestions(presponse));
            pcallback.onSuggestionsReady(prequest, response);
          }

        });
        if (result.isDone()) {
          response.setSuggestions(this.convertListToSuggestions(result.get()));
          pcallback.onSuggestionsReady(prequest, response);
        }
      } catch (final ExecutionException e) {
        GWT.log(e.getMessage(), e);
        pcallback.onSuggestionsReady(prequest, response);
      }
    } else {
      pcallback.onSuggestionsReady(prequest, response);
    }
  }

  private List<T> convertListToSuggestions(final List<PhoneNumberData> presponse) {
    final List<T> suggestions;
    if (presponse.isEmpty()) {
      suggestions = Collections.emptyList();
    } else {
      suggestions = new ArrayList<>(presponse.size());
      for (final PhoneNumberData entry : presponse) {
        suggestions.add(AbstractPhoneNumberRestOracle.this.createInstance(entry));
      }
    }
    return suggestions;
  }

  private String cleanRequest(final Request prequest) {
    final StringBuilder cleanupString = new StringBuilder(prequest.getQuery().length());
    for (final char character : prequest.getQuery().toCharArray()) {
      if (character >= '0' && character <= '9') {
        cleanupString.append(character);
      }
    }
    return cleanupString.toString();
  }
}
