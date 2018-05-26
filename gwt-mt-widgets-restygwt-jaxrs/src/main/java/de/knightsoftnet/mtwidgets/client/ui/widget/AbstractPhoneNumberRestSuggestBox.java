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

import de.knightsoftnet.validators.client.rest.api.PhoneNumberServiceAsync;
import de.knightsoftnet.validators.client.rest.api.ServiceFactory;
import de.knightsoftnet.validators.client.rest.helper.FutureResult;
import de.knightsoftnet.validators.shared.data.ValueWithPos;
import de.knightsoftnet.validators.shared.data.ValueWithPosAndCountry;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.SuggestOracle;

import elemental.client.Browser;

import org.apache.commons.lang3.StringUtils;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * abstract phone number suggest widget with rest calls.
 *
 * @author Manfred Tremmel
 *
 */
public abstract class AbstractPhoneNumberRestSuggestBox extends AbstractFormatingSuggestBox {

  protected final PhoneNumberServiceAsync service;
  protected final MethodCallback<ValueWithPos<String>> callback;
  protected TakesValue<?> countryCodeField;

  /**
   * cache map.
   */
  final LoadingCache<ValueWithPosAndCountry<String>, FutureResult<ValueWithPos<String>>> cache;


  /**
   * default constructor.
   */
  public AbstractPhoneNumberRestSuggestBox(final SuggestOracle poracle) {
    super(poracle, new TextBoxWithFormating(Browser.getDocument().createInputElement(), "tel"));
    ((TextBoxWithFormating) this.getValueBox()).setFormating(this);
    this.service = ServiceFactory.getPhoneNumberService();
    this.callback = new MethodCallback<ValueWithPos<String>>() {

      @Override
      public void onFailure(final Method pmethod, final Throwable pexception) {
        GWT.log(pexception.getMessage(), pexception);
      }

      @Override
      public void onSuccess(final Method pmethod, final ValueWithPos<String> presponse) {
        if (presponse != null && StringUtils.isNotEmpty(presponse.getValue())) {
          AbstractPhoneNumberRestSuggestBox.this.setTextWithPos(presponse);
        }
      }
    };
    this.cache =
        CacheBuilder.newBuilder().maximumSize(10000).expireAfterWrite(10, TimeUnit.DAYS).build(
            new CacheLoader<ValueWithPosAndCountry<String>, FutureResult<ValueWithPos<String>>>() {

              @Override
              public FutureResult<ValueWithPos<String>> load(
                  final ValueWithPosAndCountry<String> pkey) {
                final FutureResult<ValueWithPos<String>> result = new FutureResult<>();
                result.addCallback(AbstractPhoneNumberRestSuggestBox.this.callback);
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
          pvalue.getValue(), pvalue.getPos(), Objects.toString(this.countryCodeField.getValue()),
          LocaleInfo.getCurrentLocale().getLocaleName());
      try {
        final FutureResult<ValueWithPos<String>> result = this.cache.get(unformatedEntry);
        if (result.isDone()) {
          this.setTextWithPos(result.get());
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
    this.countryCodeField = pcountryCodeField;
  }

  @Override
  public boolean isAllowedCharacter(final char pcharacter) {
    return pcharacter >= '0' && pcharacter <= '9' || this.isFormatingCharacter(pcharacter);
  }

  @Override
  public boolean isCharacterToReplace(final char pcharacter) {
    return false;
  }

  @Override
  public char replaceCharacter(final char pcharacter) {
    return pcharacter;
  }
}
