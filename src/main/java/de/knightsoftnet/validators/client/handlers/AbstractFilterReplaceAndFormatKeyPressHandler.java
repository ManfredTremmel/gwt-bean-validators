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

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.ValueBoxBase;

import org.apache.commons.lang3.StringUtils;

/**
 * Key press handler which limits and replaces characters and formats input.
 *
 * @author Manfred Tremmel
 */
public abstract class AbstractFilterReplaceAndFormatKeyPressHandler implements KeyPressHandler {

  /**
   * allow copy and paste.
   */
  private final boolean allowCopyAndPast;

  private final NavigationKeysInterface navigationKeys;

  /**
   * constructor.
   *
   * @param pallowCopyAndPast is copy & paste allowed?
   */
  public AbstractFilterReplaceAndFormatKeyPressHandler(final boolean pallowCopyAndPast) {
    super();
    this.allowCopyAndPast = pallowCopyAndPast;
    this.navigationKeys = GWT.create(NavigationKeysInterface.class);
  }

  @Override
  public void onKeyPress(final KeyPressEvent pevent) {
    int keyCode = 0;
    if (pevent.getNativeEvent() != null) {
      keyCode = pevent.getNativeEvent().getKeyCode();
    }
    final char charCode = pevent.getCharCode();
    final ValueBoxBase<?> textBox;
    if (pevent.getSource() instanceof SuggestBox) {
      textBox = ((SuggestBox) pevent.getSource()).getValueBox();
    } else if (pevent.getSource() instanceof ValueBoxBase<?>) {
      textBox = (ValueBoxBase<?>) pevent.getSource();
    } else {
      throw new RuntimeException("Widget type not supported!");
    }
    final int cursorPos = textBox.getCursorPos();
    int newCursorPos = cursorPos;
    final String oldValue = textBox.getText();
    String newValue = null;
    boolean changeHandled = false;

    // accept navigation keys like cursor right, left, ...
    if (this.navigationKeys.isNavigationKey(keyCode)) {
      return;
    }
    // Copy, Cut or Paste allowed?
    if (this.allowCopyAndPast && pevent.isControlKeyDown()
        && (charCode == 'c' || charCode == 'x' || charCode == 'v')) {
      return;
    }
    // check for allowed characters
    if (this.isAllowedCharacter(charCode) || this.isCharacterToReplace(charCode)) {
      final String newTmpValue =
          StringUtils.substring(oldValue, 0, cursorPos) + this.replaceCharacter(charCode)
              + StringUtils.substring(oldValue, cursorPos + textBox.getSelectionLength());
      newValue = this.formatValue(newTmpValue);

      if (!StringUtils.equals(oldValue, newValue)) {
        newCursorPos = cursorPos + newValue.length() - oldValue.length();
        while (newCursorPos > 0 //
            && this.isFormatingCharacter(newValue.charAt(newCursorPos - 1))) {
          newCursorPos++;
        }
      }
      changeHandled = true;
    } else {
      // nothing matched, cancel event
      pevent.getNativeEvent().preventDefault();
    }
    if (changeHandled) {
      textBox.cancelKey();
      textBox.setText(newValue);
      if (StringUtils.length(oldValue) == cursorPos //
          || newCursorPos > StringUtils.length(newValue) //
          || newCursorPos < 0) {
        textBox.setCursorPos(newValue.length());
      } else {
        textBox.setCursorPos(newCursorPos);
      }
      DomEvent.fireNativeEvent(Document.get().createChangeEvent(), textBox);
    }
  }

  /**
   * check if character is allowed to type in unchanged.
   *
   * @param pcharacter character to check
   * @return true if it's allowed
   */
  public abstract boolean isAllowedCharacter(final char pcharacter);

  /**
   * check if character is one which has to be replaced.
   *
   * @param pcharacter character to check
   * @return true if character has to be replaced
   */
  public abstract boolean isCharacterToReplace(final char pcharacter);

  /**
   * check if character is a formating character.
   *
   * @param pcharacter character to check
   * @return true if character is a formating character
   */
  public abstract boolean isFormatingCharacter(final char pcharacter);

  /**
   * replace character by another.
   *
   * @param pcharacter character to replace
   * @return replaced character
   */
  public abstract char replaceCharacter(final char pcharacter);

  /**
   * format value of the widget.
   *
   * @param pvalue unformated value
   * @return formated value
   */
  public abstract String formatValue(final String pvalue);
}
