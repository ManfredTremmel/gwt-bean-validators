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

package de.knightsoftnet.validators.client.handlers;

import de.knightsoftnet.validators.shared.util.PhoneNumberUtil;

import com.google.gwt.user.client.ui.HasValue;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * Key up handler for phone number Common international input fields.
 *
 * @author Manfred Tremmel
 */
public class PhoneNumberCommonInternationalKeyUpHandler extends AbstractFormatKeyUpHandler {

  private final PhoneNumberUtil phoneNumberUtil;
  private final HasValue<?> countryCodeField;

  /**
   * constructor set reference to country code reference.
   *
   * @param pcountryCodeField reference to country code field
   */
  public PhoneNumberCommonInternationalKeyUpHandler(final HasValue<?> pcountryCodeField) {
    super();
    this.countryCodeField = pcountryCodeField;
    this.phoneNumberUtil = new PhoneNumberUtil(Objects.toString(pcountryCodeField.getValue()));
  }

  @Override
  public boolean isFormatingCharacter(final char pcharacter) {
    return pcharacter == ' ';
  }

  @Override
  public String formatValue(final String pvalue) {
    this.phoneNumberUtil.setCountryCode(Objects.toString(this.countryCodeField.getValue()));
    final String formatedValue = this.phoneNumberUtil.formatCommonInternational(pvalue);
    return StringUtils.isEmpty(formatedValue)
        || StringUtils.startsWith(pvalue, formatedValue) && StringUtils.endsWith(pvalue, "-")
            ? pvalue : formatedValue;
  }
}
