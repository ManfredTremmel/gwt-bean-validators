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

import de.knightsoftnet.validators.server.data.CreateClass;
import de.knightsoftnet.validators.shared.data.PostalCodesMapSharedConstants;
import de.knightsoftnet.validators.shared.util.RegExUtil;

import com.google.gwt.user.client.ui.HasValue;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * KeyPress Handler which allows the input of characters which are part of matching string.
 *
 * @author Manfred Tremmel
 */
public class PostalCodeKeyPressHandler extends AbstractFilterAndReplaceKeyPressHandler {

  /**
   * map of allowed characters per country.
   */
  private static final Map<String, Set<Character>> ALLOWED_CHRACTERS = new HashMap<>();

  /**
   * map of the postal code values.
   */
  private static final PostalCodesMapSharedConstants POSTAL_CODE_MAP =
      CreateClass.create(PostalCodesMapSharedConstants.class);

  /**
   * reference to country code field.
   */
  private final HasValue<?> countryCodeField;

  private boolean containsUpper = false;
  private boolean containsLower = false;
  private Set<Character> currentCharacterSet;

  /**
   * constructor initializing reverence to country code field.
   *
   * @param pcountryCodeField country code field
   */
  public PostalCodeKeyPressHandler(final HasValue<?> pcountryCodeField) {
    super(true);
    this.countryCodeField = pcountryCodeField;
  }

  @Override
  public boolean isAllowedCharacter(final char pcharacter) {
    final String countryCode =
        StringUtils.upperCase(Objects.toString(this.countryCodeField.getValue()));
    this.currentCharacterSet = ALLOWED_CHRACTERS.get(countryCode);
    this.containsUpper = false;
    this.containsLower = false;
    if (this.currentCharacterSet == null) {
      final String characters =
          RegExUtil.getAllowedCharactersForRegEx(POSTAL_CODE_MAP.postalCodes().get(countryCode));
      this.currentCharacterSet = new TreeSet<>();
      for (final char character : characters.toCharArray()) {
        this.currentCharacterSet.add(Character.valueOf(character));
        this.containsUpper |= CharUtils.isAsciiAlphaUpper(character);
        this.containsLower |= CharUtils.isAsciiAlphaLower(character);
      }
      ALLOWED_CHRACTERS.put(countryCode, this.currentCharacterSet);
    } else {
      for (final Character character : this.currentCharacterSet) {
        this.containsUpper |= CharUtils.isAsciiAlphaUpper(character.charValue());
        this.containsLower |= CharUtils.isAsciiAlphaLower(character.charValue());
      }
    }
    return this.currentCharacterSet.contains(Character.valueOf(pcharacter));
  }

  @Override
  public boolean isCharacterToReplace(final char pcharacter) {
    return CharUtils.isAsciiAlphaUpper(pcharacter) && !this.containsUpper && this.containsLower
        && this.currentCharacterSet.contains(Character.toLowerCase(pcharacter))
        || CharUtils.isAsciiAlphaLower(pcharacter) && this.containsUpper && !this.containsLower
            && this.currentCharacterSet.contains(Character.toUpperCase(pcharacter));
  }

  @Override
  public char replaceCharacter(final char pcharacter) {
    return CharUtils.isAsciiAlphaUpper(pcharacter) ? Character.toLowerCase(pcharacter)
        : Character.toUpperCase(pcharacter);
  }
}
