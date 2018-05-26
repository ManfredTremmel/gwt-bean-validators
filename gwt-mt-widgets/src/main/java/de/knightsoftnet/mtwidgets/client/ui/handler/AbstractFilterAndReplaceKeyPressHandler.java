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

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.ValueBoxBase;

import org.apache.commons.lang3.StringUtils;

/**
 * Abstract key press handler which limits input to allowed characters and replaces characters by
 * other.
 *
 * @author Manfred Tremmel
 */
public abstract class AbstractFilterAndReplaceKeyPressHandler implements KeyPressHandler {

  /**
   * allow copy and paste.
   */
  private final boolean allowCopyAndPast;

  private final NavigationKeys navigationKeys;

  /**
   * constructor.
   *
   * @param pallowCopyAndPast is copy and paste allowed?
   */
  public AbstractFilterAndReplaceKeyPressHandler(final boolean pallowCopyAndPast) {
    super();
    this.allowCopyAndPast = pallowCopyAndPast;
    this.navigationKeys = new NavigationKeys();
  }


  @Override
  public void onKeyPress(final KeyPressEvent pevent) {
    int keyCode = 0;
    if (pevent.getNativeEvent() != null) {
      keyCode = pevent.getNativeEvent().getKeyCode();
    }
    final int charCode = pevent.getCharCode();

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
    if (this.isAllowedCharacter((char) charCode)) {
      return;
    }
    // convert the character
    if (this.isCharacterToReplace((char) charCode)) {
      final ValueBoxBase<?> textBox;
      if (pevent.getSource() instanceof SuggestBox) {
        textBox = ((SuggestBox) pevent.getSource()).getValueBox();
      } else if (pevent.getSource() instanceof ValueBoxBase<?>) {
        textBox = (ValueBoxBase<?>) pevent.getSource();
      } else {
        throw new RuntimeException("Widget type not supported!");
      }
      final int cursorPos = textBox.getCursorPos();
      final int selectionLength = textBox.getSelectionLength();
      final int startDiff;
      final int endDiff;
      if (selectionLength >= 0) {
        startDiff = 0;
        endDiff = selectionLength;
      } else {
        startDiff = selectionLength;
        endDiff = 0;
      }

      final String oldValue = textBox.getText();
      final String newValue = StringUtils.substring(oldValue, 0, cursorPos + startDiff)
          + this.replaceCharacter((char) charCode)
          + StringUtils.substring(oldValue, cursorPos + endDiff);

      textBox.setText(newValue);
      textBox.setCursorPos(cursorPos + 1);
      DomEvent.fireNativeEvent(Document.get().createChangeEvent(), textBox);
    }
    // nothing matched, cancel event
    pevent.getNativeEvent().preventDefault();
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
   * replace character by another.
   *
   * @param pcharacter character to replace
   * @return replaced character
   */
  public abstract char replaceCharacter(final char pcharacter);
}
