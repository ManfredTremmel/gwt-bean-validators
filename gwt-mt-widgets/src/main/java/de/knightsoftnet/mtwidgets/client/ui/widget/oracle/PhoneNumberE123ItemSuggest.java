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

import org.apache.commons.lang3.StringUtils;

/**
 * suggest entry of phone number E123 suggest widget.
 *
 * @author Manfred Tremmel
 *
 */
public class PhoneNumberE123ItemSuggest extends AbstractPhoneNumberItemSuggest {

  /**
   * constructor initializing fields.
   *
   *
   * @param pcountryCode country code to set
   * @param pcountryName country name to set
   * @param pareaCode area code to set
   * @param pareaName area name to set
   */
  public PhoneNumberE123ItemSuggest(final String pcountryCode, final String pcountryName,
      final String pareaCode, final String pareaName) {
    super(pcountryCode, pcountryName, pareaCode, pareaName);
  }

  @Override
  public String getDisplayString() {
    if (StringUtils.isEmpty(getAreaCode())) {
      return "+" + getCountryCode() + " - " + getCountryName();
    }
    return "+" + getCountryCode() + " " + getAreaCode() + " - " + getAreaName();
  }

  @Override
  public String getReplacementString() {
    if (StringUtils.isEmpty(getAreaCode())) {
      return "+" + getCountryCode();
    }
    return "+" + getCountryCode() + " " + getAreaCode();
  }
}
