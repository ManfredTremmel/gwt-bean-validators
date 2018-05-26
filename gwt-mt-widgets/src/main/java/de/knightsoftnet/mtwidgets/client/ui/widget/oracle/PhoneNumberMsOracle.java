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

import org.apache.commons.lang3.StringUtils;

/**
 * suggest oracle of phone number suggest widget.
 *
 * @author Manfred Tremmel
 *
 */
public class PhoneNumberMsOracle extends AbstractPhoneNumberLocalOracle<PhoneNumberMsItemSuggest> {

  /**
   * default constructor.
   */
  public PhoneNumberMsOracle() {
    super(PhoneNumberMsItemSuggest.class);
  }

  @Override
  protected PhoneNumberMsItemSuggest createInstance(final PhoneNumberData pentry) {
    return new PhoneNumberMsItemSuggest(pentry.getCountryCode(), pentry.getCountryName(),
        pentry.getAreaCode(), pentry.getAreaName());
  }

  @Override
  protected boolean needSuggest(final String pentry) {
    return StringUtils.isNotEmpty(pentry) && !StringUtils.contains(pentry, ')');
  }
}
