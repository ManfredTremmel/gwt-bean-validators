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

import de.knightsoftnet.gwtp.spring.client.rest.helper.AbstractSimpleRestCallback;
import de.knightsoftnet.gwtp.spring.client.rest.helper.FutureResult;
import de.knightsoftnet.gwtp.spring.client.rest.helper.HasShowMessage;
import de.knightsoftnet.gwtp.spring.client.rest.helper.HttpMessages;
import de.knightsoftnet.gwtp.spring.client.session.Session;
import de.knightsoftnet.gwtp.spring.shared.data.ValueWithPosAndCountry;
import de.knightsoftnet.validators.shared.data.ValueWithPos;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.SuggestOracle;

import elemental.client.Browser;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * abstract phone number suggest widget with rest calls.
 *
 * @author Manfred Tremmel
 *
 */
public abstract class AbstractPhoneNumberRestSuggestBox extends AbstractFormatingSuggestBox
    implements HasShowMessage {

  protected final AsyncCallback<ValueWithPos<String>> callback;
  protected TakesValue<?> countryCodeField;

  /**
   * cache map.
   */
  final LoadingCache<ValueWithPosAndCountry<String>, FutureResult<ValueWithPos<String>>> cache;


  /**
   * default constructor.
   */
  public AbstractPhoneNumberRestSuggestBox(final SuggestOracle poracle, final Session psession) {
    super(poracle, new TextBoxWithFormating(Browser.getDocument().createInputElement(), "tel"));
    ((TextBoxWithFormating) getValueBox()).setFormating(this);
    callback =
        new AbstractSimpleRestCallback<AbstractPhoneNumberRestSuggestBox, ValueWithPos<String>, //
            HttpMessages>(this, psession) {

          @Override
          public void onSuccess(final ValueWithPos<String> presponse) {
            if (presponse != null && StringUtils.isNotEmpty(presponse.getValue())) {
              AbstractPhoneNumberRestSuggestBox.this.setTextWithPos(presponse);
            }
          }

        };
    cache = CacheBuilder.newBuilder().maximumSize(10000).expireAfterWrite(10, TimeUnit.DAYS).build(
        new CacheLoader<ValueWithPosAndCountry<String>, FutureResult<ValueWithPos<String>>>() {

          @Override
          public FutureResult<ValueWithPos<String>> load(
              final ValueWithPosAndCountry<String> pkey) {
            final FutureResult<ValueWithPos<String>> result = new FutureResult<>();
            result.addCallback(callback);
            try {
              AbstractPhoneNumberRestSuggestBox.this.formatValue(pkey, result);
            } catch (final ExecutionException e) {
              GWT.log(e.getMessage(), e);
            }
            return result;
          }
        });
  }

  @Override
  public void formatValue(final ValueWithPos<String> pvalue) {
    if (pvalue == null || StringUtils.isEmpty(pvalue.getValue())) {
      this.setValue(StringUtils.EMPTY);
    } else {
      final ValueWithPosAndCountry<String> unformatedEntry = new ValueWithPosAndCountry<>(
          pvalue.getValue(), pvalue.getPos(), Objects.toString(countryCodeField.getValue()),
          LocaleInfo.getCurrentLocale().getLocaleName());
      try {
        final FutureResult<ValueWithPos<String>> result = cache.get(unformatedEntry);
        if (result.isDone()) {
          setTextWithPos(result.get());
        }
      } catch (final ExecutionException e) {
        GWT.log(e.getMessage(), e);
      }
    }
  }

  public abstract void formatValue(ValueWithPosAndCountry<String> pkey,
      FutureResult<ValueWithPos<String>> presult) throws ExecutionException;

  /**
   * set reference to a field which contains the country code.
   *
   * @param pcountryCodeField field which contains the country code
   */
  public final void setCountryCodeReferenceField(final TakesValue<?> pcountryCodeField) {
    countryCodeField = pcountryCodeField;
  }

  @Override
  public boolean isAllowedCharacter(final char pcharacter) {
    return pcharacter >= '0' && pcharacter <= '9' || isFormatingCharacter(pcharacter);
  }

  @Override
  public boolean isCharacterToReplace(final char pcharacter) {
    return false;
  }

  @Override
  public char replaceCharacter(final char pcharacter) {
    return pcharacter;
  }

  @Override
  public void showMessage(final String pmessage) {
    GWT.log(pmessage);
  }
}
