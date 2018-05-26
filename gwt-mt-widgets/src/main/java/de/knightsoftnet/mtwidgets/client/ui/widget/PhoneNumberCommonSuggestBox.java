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

import de.knightsoftnet.mtwidgets.client.ui.widget.oracle.PhoneNumberCommonOracle;
import de.knightsoftnet.validators.shared.data.ValueWithPos;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * phone number common suggest widget.
 *
 * @author Manfred Tremmel
 *
 */
public class PhoneNumberCommonSuggestBox extends AbstractPhoneNumberSuggestBox {

  /**
   * default constructor.
   */
  public PhoneNumberCommonSuggestBox() {
    super(new PhoneNumberCommonOracle());
  }

  @Override
  public void formatValue(final ValueWithPos<String> pvalue) {
    this.setTextWithPos(this.phoneNumberUtil.formatCommonWithPos(pvalue,
        StringUtils.upperCase(Objects.toString(this.countryCodeField.getValue()))));
  }

  @Override
  public boolean isFormatingCharacter(final char pcharacter) {
    return pcharacter == '+' || pcharacter == ' ' || pcharacter == '/' || pcharacter == '-'
        || pcharacter == '(' || pcharacter == ')';
  }
}
