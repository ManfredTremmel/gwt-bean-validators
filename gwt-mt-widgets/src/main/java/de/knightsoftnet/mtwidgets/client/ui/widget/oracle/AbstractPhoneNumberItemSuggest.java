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

import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;

/**
 * abstract suggest entry of phone number suggest widgets.
 *
 * @author Manfred Tremmel
 *
 */
public abstract class AbstractPhoneNumberItemSuggest implements Suggestion {

  private String countryCode;
  private String countryName;
  private String areaCode;
  private String areaName;

  /**
   * default constructor.
   */
  public AbstractPhoneNumberItemSuggest() {
    this(null, null, null, null);
  }

  /**
   * constructor initializing fields.
   *
   *
   * @param pcountryCode country code to set
   * @param pcountryName country name to set
   * @param pareaCode area code to set
   * @param pareaName area name to set
   */
  public AbstractPhoneNumberItemSuggest(final String pcountryCode, final String pcountryName,
      final String pareaCode, final String pareaName) {
    super();
    this.countryCode = pcountryCode;
    this.countryName = pcountryName;
    this.areaCode = pareaCode;
    this.areaName = pareaName;
  }

  @Override
  public abstract String getDisplayString();

  @Override
  public abstract String getReplacementString();

  public final String getCountryCode() {
    return this.countryCode;
  }

  public final void setCountryCode(final String pcountryCode) {
    this.countryCode = pcountryCode;
  }

  public final String getCountryName() {
    return this.countryName;
  }

  public final void setCountryName(final String pcountryName) {
    this.countryName = pcountryName;
  }

  public final String getAreaCode() {
    return this.areaCode;
  }

  public final void setAreaCode(final String pareaCode) {
    this.areaCode = pareaCode;
  }

  public final String getAreaName() {
    return this.areaName;
  }

  public final void setAreaName(final String pareaName) {
    this.areaName = pareaName;
  }
}
