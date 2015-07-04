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
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.ValueBoxBase;

import org.apache.commons.lang3.StringUtils;

/**
 * Key press handler which limits and formats input to iban characters.
 *
 * @author Manfred Tremmel
 */
public class IbanKeyUpHandler implements KeyUpHandler {

  @Override
  public void onKeyUp(final KeyUpEvent pevent) {
    int keyCode = 0;
    if (pevent.getNativeEvent() != null) {
      keyCode = pevent.getNativeEvent().getKeyCode();
    }
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
        if (cursorPos > 0) {
          if (oldValue.charAt(cursorPos - 1) == ' ') {
            newValue = IbanUtil.ibanFormat(StringUtils.substring(oldValue, 0, cursorPos - 1)
                + StringUtils.substring(oldValue, cursorPos));
            newCursorPos = cursorPos - 1;
            changeHandled = true;
          } else {
            newValue = IbanUtil.ibanFormat(oldValue);
            changeHandled = !StringUtils.equals(oldValue, newValue);
          }
        }
        break;
      case KeyCodes.KEY_DELETE:
        if (cursorPos < StringUtils.length(oldValue)) {
          if (oldValue.charAt(cursorPos + 1) == ' ') {
            newValue = IbanUtil.ibanFormat(StringUtils.substring(oldValue, 0, cursorPos)
                + StringUtils.substring(oldValue, cursorPos + 2));
            changeHandled = true;
          } else {
            newValue = IbanUtil.ibanFormat(oldValue);
            changeHandled = !StringUtils.equals(oldValue, newValue);
          }
        }
        break;
      case KeyCodes.KEY_LEFT:
        if (cursorPos > 0) {
          if (oldValue.charAt(cursorPos - 1) == ' ') {
            textBox.setCursorPos(--newCursorPos);
          }
        }
        break;
      case KeyCodes.KEY_RIGHT:
        if (cursorPos < StringUtils.length(oldValue)) {
          if (oldValue.charAt(cursorPos) == ' ') {
            textBox.setCursorPos(++newCursorPos);
          }
        }
        break;
      case KeyCodes.KEY_SHIFT:
      case KeyCodes.KEY_TAB:
      case KeyCodes.KEY_ENTER:
      case KeyCodes.KEY_HOME:
      case KeyCodes.KEY_END:
      case KeyCodes.KEY_UP:
      case KeyCodes.KEY_DOWN:
        break;
      default:
        break;
    }
    if (changeHandled) {
      pevent.getNativeEvent().stopPropagation();
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
