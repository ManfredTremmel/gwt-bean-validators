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
 * suggest entry of BIC suggest widget.
 *
 * @author Manfred Tremmel
 *
 */
public class BicItemSuggest implements Suggestion {

  /**
   * bic.
   */
  private String bic;
  /**
   * bic display.
   */
  private String bicDisplay;
  /**
   * bank name.
   */
  private String bankName;

  /**
   * default constructor.
   */
  public BicItemSuggest() {
    this(null, null, null);
  }

  /**
   * constructor initializing fields.
   *
   * @param pbic bic number
   * @param pbicDisplay bic display
   * @param pbankName bank name
   */
  public BicItemSuggest(final String pbic, final String pbicDisplay, final String pbankName) {
    super();
    bic = pbic;
    bicDisplay = pbicDisplay;
    bankName = pbankName;
  }

  @Override
  public String getDisplayString() {
    return bicDisplay + " - " + bankName;
  }

  @Override
  public String getReplacementString() {
    return bic;
  }

  public String getBic() {
    return bic;
  }

  public void setBic(final String pbic) {
    bic = pbic;
  }

  public String getBicDisplay() {
    return bicDisplay;
  }

  public void setBicDisplay(final String pbicDisplay) {
    bicDisplay = pbicDisplay;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(final String pbankName) {
    bankName = pbankName;
  }
}
