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

import de.knightsoftnet.validators.shared.util.IsbnUtil;

import org.apache.commons.lang3.CharUtils;

/**
 * Key press handler which limits and formats input to isbn 13 characters.
 *
 * @author Manfred Tremmel
 */
public class Isbn13KeyPressHandler extends AbstractFilterReplaceAndFormatKeyPressHandler {

  /**
   * default constructor.
   */
  public Isbn13KeyPressHandler() {
    super(true);
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

  @Override
  public String formatValue(final String pvalue) {
    return IsbnUtil.isbn13Format(pvalue);
  }
}
