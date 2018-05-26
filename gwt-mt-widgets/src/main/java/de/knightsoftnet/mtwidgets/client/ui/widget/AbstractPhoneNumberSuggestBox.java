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

import de.knightsoftnet.validators.shared.util.PhoneNumberUtil;

import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.SuggestOracle;

import elemental.client.Browser;

/**
 * phone number common international suggest widget.
 *
 * @author Manfred Tremmel
 *
 */
public abstract class AbstractPhoneNumberSuggestBox extends AbstractFormatingSuggestBox {

  protected final PhoneNumberUtil phoneNumberUtil;
  protected TakesValue<?> countryCodeField;

  /**
   * default constructor.
   *
   * @param poracle suggest oracle to use
   */
  public AbstractPhoneNumberSuggestBox(final SuggestOracle poracle) {
    super(poracle, new TextBoxWithFormating(Browser.getDocument().createInputElement(), "tel"));
    this.setWidth("15em");
    this.phoneNumberUtil = new PhoneNumberUtil();
    ((TextBoxWithFormating) this.getValueBox()).setFormating(this);
  }

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
