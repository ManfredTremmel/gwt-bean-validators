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

import de.knightsoftnet.validators.shared.util.IbanUtil;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.ValueBoxBase;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Key press handler which limits and formats input to iban characters.
 *
 * @author Manfred Tremmel
 */
public class IbanKeyPressHandler implements KeyPressHandler {

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

    switch (keyCode) {
      case KeyCodes.KEY_BACKSPACE:
      case KeyCodes.KEY_DELETE:
      case KeyCodes.KEY_LEFT:
      case KeyCodes.KEY_RIGHT:
      case KeyCodes.KEY_SHIFT:
      case KeyCodes.KEY_TAB:
      case KeyCodes.KEY_ENTER:
      case KeyCodes.KEY_HOME:
      case KeyCodes.KEY_END:
      case KeyCodes.KEY_UP:
      case KeyCodes.KEY_DOWN:
        break;
      default:
        // Copy, Cut or Paste allowed?
        if (pevent.isControlKeyDown() && (charCode == 'c' || charCode == 'x' || charCode == 'v')) {
          return;
        }
        // iban compatible character
        if (CharUtils.isAscii(charCode) || CharUtils.isAsciiNumeric(charCode)) {
          final String newTmpValue = StringUtils.substring(oldValue, 0, cursorPos)
              + Character.toUpperCase(charCode) + StringUtils.substring(oldValue, cursorPos);
          newValue = IbanUtil.ibanFormat(newTmpValue);

          if (!StringUtils.equals(oldValue, newValue)) {
            newCursorPos = cursorPos + 1;
            if (newValue.charAt(cursorPos) == ' ') {
              newCursorPos++;
            }
          }
          changeHandled = true;
        } else {
          // nothing matched, cancel event
          pevent.getNativeEvent().preventDefault();
        }
        break;
    }
    if (changeHandled) {
      textBox.cancelKey();
      textBox.setText(newValue);
      if (newCursorPos > newValue.length()) {
        textBox.setCursorPos(newValue.length());
      } else {
        textBox.setCursorPos(newCursorPos);
      }
      DomEvent.fireNativeEvent(Document.get().createChangeEvent(), textBox);
    }
  }
}
