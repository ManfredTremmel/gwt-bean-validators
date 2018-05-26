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

package de.knightsoftnet.mtwidgets.client.ui.widget.features;

import de.knightsoftnet.validators.shared.data.ValueWithPos;

/**
 * interface for widgets with formating capabilities.
 *
 * @author Manfred Tremmel
 *
 */
public interface HasFormating {

  /**
   * reformat value from widget.
   */
  void reformatValue();

  /**
   * format and set value.
   *
   * @param pvalue value to format
   */
  void formatValue(final ValueWithPos<String> pvalue);

  /**
   * check a character if it's allowed input value.
   *
   * @param pcharacter character to check
   * @return true if character is allowed
   */
  boolean isAllowedCharacter(final char pcharacter);

  /**
   * check a character if it's character to replace.
   *
   * @param pcharacter character to check
   * @return true if character has to be replaced
   */
  boolean isCharacterToReplace(final char pcharacter);

  /**
   * check a character if it's formating character.
   *
   * @param pcharacter character to check
   * @return true if character is a formating character
   */
  boolean isFormatingCharacter(final char pcharacter);

  /**
   * replace a character.
   *
   * @param pcharacter to replace
   * @return replacement character
   */
  char replaceCharacter(final char pcharacter);
}
