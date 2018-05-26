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

package de.knightsoftnet.mtwidgets.client.ui.handler;

import de.knightsoftnet.validators.server.data.CreatePostalCodesMapConstantsClass;
import de.knightsoftnet.validators.shared.data.PostalCodesMapSharedConstants;
import de.knightsoftnet.validators.shared.util.RegExUtil;

import com.google.gwt.user.client.TakesValue;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * KeyPress Handler which allows the input of characters which are part of matching string.
 *
 * @author Manfred Tremmel
 */
public class PostalCodeKeyPressHandler extends SimpleFilterAndReplaceKeyPressHandler {

  /**
   * map of allowed characters per country.
   */
  private static final Map<String, Set<Character>> ALLOWED_CHRACTERS = new HashMap<>();

  /**
   * map of the postal code values.
   */
  private static final PostalCodesMapSharedConstants POSTAL_CODE_MAP =
      CreatePostalCodesMapConstantsClass.create();

  /**
   * reference to country code field.
   */
  private final TakesValue<?> countryCodeField;

  /**
   * constructor initializing reverence to country code field.
   *
   * @param pcountryCodeField country code field
   */
  public PostalCodeKeyPressHandler(final TakesValue<?> pcountryCodeField) {
    super(Collections.<Character>emptySet(), true);
    this.countryCodeField = pcountryCodeField;
  }

  @Override
  public boolean isAllowedCharacter(final char pcharacter) {
    final String countryCode =
        StringUtils.upperCase(Objects.toString(this.countryCodeField.getValue()));
    final Set<Character> currentCharacterSet =
        PostalCodeKeyPressHandler.ALLOWED_CHRACTERS.get(countryCode);
    if (currentCharacterSet == null) {
      this.setAllowedCharacters(RegExUtil.getAllowedCharactersForRegEx(
          PostalCodeKeyPressHandler.POSTAL_CODE_MAP.postalCodes().get(countryCode)));
      PostalCodeKeyPressHandler.ALLOWED_CHRACTERS.put(countryCode, this.getAllowedCharacters());
    } else {
      this.setAllowedCharacters(currentCharacterSet);
    }
    return super.isAllowedCharacter(pcharacter);
  }
}
