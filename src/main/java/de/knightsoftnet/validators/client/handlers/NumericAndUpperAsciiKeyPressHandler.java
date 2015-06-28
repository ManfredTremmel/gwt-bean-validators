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

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.ValueBoxBase;

import org.apache.commons.lang3.CharUtils;

/**
 * Key press handler which limits input to UperCase ASCII and numeric characters.
 *
 * @author Manfred Tremmel
 */
public class NumericAndUpperAsciiKeyPressHandler implements KeyPressHandler {

  @Override
  public void onKeyPress(final KeyPressEvent pevent) {
    int keyCode = 0;
    if (pevent.getNativeEvent() != null) {
      keyCode = pevent.getNativeEvent().getKeyCode();
    }
    final char charCode = pevent.getCharCode();

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
        // check for allowed characters
        if (CharUtils.isAsciiNumeric(charCode) || CharUtils.isAsciiAlphaUpper(charCode)) {
          return;
        }
        // convert lower case characters
        if (CharUtils.isAsciiAlphaLower(charCode)) {
          final ValueBoxBase<?> textBox = (ValueBoxBase<?>) pevent.getSource();
          final int cursorPos = textBox.getCursorPos();
          final String oldValue = textBox.getText();
          final String newValue = oldValue.substring(0, cursorPos) + Character.toUpperCase(charCode)
              + oldValue.substring(cursorPos);

          textBox.cancelKey();
          textBox.setText(newValue);
          textBox.setCursorPos(cursorPos + 1);
          DomEvent.fireNativeEvent(Document.get().createChangeEvent(), textBox);
          return;
        }
        // nothing matched, cancel event
        ((ValueBoxBase<?>) pevent.getSource()).cancelKey();
        break;
    }
  }
}
