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

import de.knightsoftnet.validators.shared.data.ValueWithPos;
import de.knightsoftnet.validators.shared.util.IsbnUtil;

import org.apache.commons.lang3.CharUtils;

/**
 * text box for isbn input, supported are isbn10 numbers.
 *
 * @author Manfred Tremmel
 *
 */
public class Isbn10TextBox extends AbstractFormatingTextBox {

  /**
   * default constructor.
   */
  public Isbn10TextBox() {
    super();
    setMaxLength(13);
    setVisibleLength(13);
  }

  @Override
  public void formatValue(final ValueWithPos<String> pvalue) {
    setTextWithPos(IsbnUtil.isbn10FormatWithPos(pvalue));
  }

  @Override
  public boolean isAllowedCharacter(final char pcharacter) {
    return CharUtils.isAsciiNumeric(pcharacter);
  }

  @Override
  public boolean isCharacterToReplace(final char pcharacter) {
    return false;
  }

  @Override
  public boolean isFormatingCharacter(final char pcharacter) {
    return pcharacter == '-';
  }

  @Override
  public char replaceCharacter(final char pcharacter) {
    return pcharacter;
  }
}
