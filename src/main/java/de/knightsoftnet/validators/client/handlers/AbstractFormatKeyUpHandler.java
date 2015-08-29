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
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.ValueBoxBase;

import org.apache.commons.lang3.StringUtils;

/**
 * Key press handler which handles formated values and reformats values.
 *
 * @author Manfred Tremmel
 */
public abstract class AbstractFormatKeyUpHandler implements KeyUpHandler {

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
    String newValue = this.formatValue(oldValue);
    boolean changeHandled = false;

    switch (keyCode) {
      case KeyCodes.KEY_BACKSPACE:
        if (cursorPos > 0 && this.isFormatingCharacter(oldValue.charAt(cursorPos - 1))) {
          newValue =
              this.formatValue(StringUtils.substring(oldValue, 0, cursorPos - 1)
                  + StringUtils.substring(oldValue, cursorPos));
          newCursorPos = cursorPos - 1;
          changeHandled = true;
        }
        break;
      case KeyCodes.KEY_DELETE:
        if (cursorPos < StringUtils.length(oldValue)
            && this.isFormatingCharacter(oldValue.charAt(cursorPos + 1))) {
          newValue =
              this.formatValue(StringUtils.substring(oldValue, 0, cursorPos)
                  + StringUtils.substring(oldValue, cursorPos + 2));
          changeHandled = true;
        }
        break;
      case KeyCodes.KEY_LEFT:
        while (newCursorPos > 0 && this.isFormatingCharacter(oldValue.charAt(newCursorPos - 1))) {
          newCursorPos--;
        }
        if (newCursorPos != cursorPos) {
          textBox.setCursorPos(newCursorPos);
        }
        break;
      case KeyCodes.KEY_RIGHT:
        while (newCursorPos < StringUtils.length(oldValue)
            && this.isFormatingCharacter(oldValue.charAt(newCursorPos))) {
          newCursorPos++;
        }
        if (newCursorPos != cursorPos) {
          textBox.setCursorPos(newCursorPos);
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
        final int charCode = pevent.getNativeKeyCode();
        final boolean ctlr = pevent.isControlKeyDown();
        if (ctlr && charCode == 86) {
          changeHandled = true;
          newCursorPos = StringUtils.length(newValue);
        }
        break;
    }
    if (changeHandled) {
      pevent.getNativeEvent().stopPropagation();
      textBox.setText(newValue);
      if (StringUtils.length(oldValue) == cursorPos //
          || newCursorPos > StringUtils.length(newValue)) {
        textBox.setCursorPos(newValue.length());
      } else {
        textBox.setCursorPos(newCursorPos);
      }
      DomEvent.fireNativeEvent(Document.get().createChangeEvent(), textBox);
    }
  }

  /**
   * check if character is a formating character.
   *
   * @param pcharacter character to check
   * @return true if character is a formating character
   */
  public abstract boolean isFormatingCharacter(final char pcharacter);

  /**
   * format value of the widget.
   *
   * @param pvalue unformated value
   * @return formated value
   */
  public abstract String formatValue(final String pvalue);
}
