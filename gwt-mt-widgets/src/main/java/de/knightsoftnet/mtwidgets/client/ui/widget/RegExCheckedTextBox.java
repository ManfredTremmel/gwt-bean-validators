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

import com.google.gwt.uibinder.client.UiConstructor;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;

/**
 * text box for RegEx checked input.
 *
 * @author Manfred Tremmel
 *
 */
public class RegExCheckedTextBox extends TextBox {

  private final String regEx;

  /**
   * default constructor.
   *
   * @param regex regular expression for input check
   */
  @UiConstructor
  public RegExCheckedTextBox(final String regex) {
    super();
    regEx = regex;
    addKeyPressHandler(HandlerFactory.getRegExKeyPressHandler(regex));
  }

  @Override
  public String getValueOrThrow() throws ParseException {
    final String result = getValue();
    boolean matches = false;
    try {
      matches = StringUtils.defaultString(result).matches(regEx);
    } catch (final Exception e) {
      throw new ParseException(e.getMessage(), 0); // NOPMD we don't need stack trace
    }

    if (!matches) {
      throw new ParseException("doesn't match regex: '" + regEx + "'", 0);
    }
    return result;
  }
}
