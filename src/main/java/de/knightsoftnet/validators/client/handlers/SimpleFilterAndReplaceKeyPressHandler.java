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

import org.apache.commons.lang3.CharUtils;

import java.util.Set;
import java.util.TreeSet;

/**
 * Simple key press handler which limits input to allowed characters.
 *
 * @author Manfred Tremmel
 */
public class SimpleFilterAndReplaceKeyPressHandler extends AbstractFilterAndReplaceKeyPressHandler {

  /**
   * allowed characters.
   */
  private Set<Character> allowedCharacters;

  private boolean containsUpper = false;
  private boolean containsLower = false;

  /**
   * constructor initializing fields.
   *
   * @param pallowedCharacters characters which are allowed to add
   * @param pallowCopyAndPast is copy & paste allowed?
   */
  public SimpleFilterAndReplaceKeyPressHandler(final Set<Character> pallowedCharacters,
      final boolean pallowCopyAndPast) {
    super(pallowCopyAndPast);
    this.setAllowedCharacters(pallowedCharacters);
  }

  /**
   * constructor initializing fields.
   *
   * @param pallowedCharacters characters which are allowed to add
   * @param pallowCopyAndPast is copy & paste allowed?
   */
  public SimpleFilterAndReplaceKeyPressHandler(final String pallowedCharacters,
      final boolean pallowCopyAndPast) {
    super(pallowCopyAndPast);
    this.setAllowedCharacters(pallowedCharacters);
  }

  protected final Set<Character> getAllowedCharacters() {
    return this.allowedCharacters;
  }

  protected final void setAllowedCharacters(final Set<Character> pallowedCharacters) {
    this.allowedCharacters = pallowedCharacters;
    this.containsUpper = false;
    this.containsLower = false;
    for (final char character : pallowedCharacters) {
      this.containsUpper |= CharUtils.isAsciiAlphaUpper(character);
      this.containsLower |= CharUtils.isAsciiAlphaLower(character);
    }
  }

  protected final void setAllowedCharacters(final String pallowedCharacters) {
    this.allowedCharacters = new TreeSet<Character>();
    this.containsUpper = false;
    this.containsLower = false;
    for (final char character : pallowedCharacters.toCharArray()) {
      this.allowedCharacters.add(Character.valueOf(character));
      this.containsUpper |= CharUtils.isAsciiAlphaUpper(character);
      this.containsLower |= CharUtils.isAsciiAlphaLower(character);
    }
  }


  @Override
  public boolean isAllowedCharacter(final char pcharacter) {
    return this.allowedCharacters.contains(Character.valueOf(pcharacter));
  }

  @Override
  public boolean isCharacterToReplace(final char pcharacter) {
    return CharUtils.isAsciiAlphaUpper(pcharacter) && !this.containsUpper && this.containsLower
        && this.allowedCharacters.contains(Character.toLowerCase(pcharacter))
        || CharUtils.isAsciiAlphaLower(pcharacter) && this.containsUpper && !this.containsLower
            && this.allowedCharacters.contains(Character.toUpperCase(pcharacter));
  }

  @Override
  public char replaceCharacter(final char pcharacter) {
    return CharUtils.isAsciiAlphaUpper(pcharacter) ? Character.toLowerCase(pcharacter)
        : Character.toUpperCase(pcharacter);
  }
}
