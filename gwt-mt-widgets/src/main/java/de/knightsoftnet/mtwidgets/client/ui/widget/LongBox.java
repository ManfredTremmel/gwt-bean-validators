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
import de.knightsoftnet.mtwidgets.client.ui.widget.helper.LongParserWithoutSeparator;
import de.knightsoftnet.mtwidgets.client.ui.widget.helper.LongRendererWithoutSeparator;

import com.google.gwt.text.client.LongParser;
import com.google.gwt.text.client.LongRenderer;

/**
 * long box for input without separators.
 *
 * @author Manfred Tremmel
 *
 */
public class LongBox extends AbstractMinMaxTextBox<Long> {

  /**
   * default constructor.
   */
  public LongBox() {
    super(LongRenderer.instance(), LongParser.instance(), LongRendererWithoutSeparator.instance(),
        LongParserWithoutSeparator.instance(),
        HandlerFactory.getNumericWithSeparatorsKeyPressHandler());
  }
}
