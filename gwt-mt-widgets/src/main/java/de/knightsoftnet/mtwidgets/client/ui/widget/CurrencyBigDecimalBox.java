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

import de.knightsoftnet.mtwidgets.client.ui.handler.HandlerFactory;
import de.knightsoftnet.mtwidgets.client.ui.widget.helper.BigDecimalParser;
import de.knightsoftnet.mtwidgets.client.ui.widget.helper.BigDecimalRenderer;
import de.knightsoftnet.mtwidgets.client.ui.widget.helper.CurrencyBigDecimalParser;
import de.knightsoftnet.mtwidgets.client.ui.widget.helper.CurrencyBigDecimalRenderer;

import elemental.client.Browser;

import java.math.BigDecimal;

/**
 * double box for currency input.
 *
 * @author Manfred Tremmel
 *
 */
public class CurrencyBigDecimalBox extends AbstractMinMaxTextBox<BigDecimal> {

  /**
   * default constructor.
   */
  public CurrencyBigDecimalBox() {
    super(Browser.getDocument().createInputElement(), "text", CurrencyBigDecimalRenderer.instance(),
        CurrencyBigDecimalParser.instance(), BigDecimalRenderer.instance(),
        BigDecimalParser.instance(), HandlerFactory.getCurrencyKeyPressHandler());
  }
}
