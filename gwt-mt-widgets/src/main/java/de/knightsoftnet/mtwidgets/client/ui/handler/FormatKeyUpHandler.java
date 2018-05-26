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

import de.knightsoftnet.mtwidgets.client.ui.widget.features.HasFormating;
import de.knightsoftnet.validators.shared.data.ValueWithPos;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.ValueBoxBase;

import org.apache.commons.lang3.StringUtils;

/**
 * Key press handler which handles formated values and reformats values. It can be attached to
 * widgets which implement HasFormating interface!
 *
 * @param <E> field type
 * @author Manfred Tremmel
 */
public class FormatKeyUpHandler<E> extends ValueBoxFromEvent<E> implements KeyUpHandler {

  @Override
  public void onKeyUp(final KeyUpEvent pevent) {
    int keyCode = 0;
    if (pevent.getNativeEvent() != null) {
      keyCode = pevent.getNativeEvent().getKeyCode();
    }
    final HasFormating formatingWidget = (HasFormating) pevent.getSource();
    final ValueBoxBase<?> textBox = this.getTextBoxFromEvent(pevent);
    final int cursorPos = textBox.getCursorPos();
    final String oldValue = textBox.getText();
    int newCursorPos = cursorPos;

    switch (keyCode) {
      case KeyCodes.KEY_BACKSPACE:
        if (cursorPos > 0 && formatingWidget.isFormatingCharacter(oldValue.charAt(cursorPos - 1))) {
          int offset = -1;
          while (formatingWidget.isFormatingCharacter(oldValue.charAt(cursorPos - 1 + offset))) {
            offset--;
          }
          pevent.getNativeEvent().stopPropagation();
          final String newTmpValue = StringUtils.substring(oldValue, 0, cursorPos + offset)
              + StringUtils.substring(oldValue, cursorPos);
          formatingWidget.formatValue(new ValueWithPos<>(newTmpValue, cursorPos + offset));
        }
        break;
      case KeyCodes.KEY_DELETE:
        if (cursorPos < StringUtils.length(oldValue)
            && formatingWidget.isFormatingCharacter(oldValue.charAt(cursorPos))) {
          int offset = 0;
          while (formatingWidget.isFormatingCharacter(oldValue.charAt(cursorPos + offset))) {
            offset++;
          }
          pevent.getNativeEvent().stopPropagation();
          final String newTmpValue = StringUtils.substring(oldValue, 0, cursorPos)
              + StringUtils.substring(oldValue, cursorPos + offset);
          formatingWidget.formatValue(new ValueWithPos<>(newTmpValue, cursorPos));
        }
        break;
      case KeyCodes.KEY_LEFT:
        while (newCursorPos > 0
            && formatingWidget.isFormatingCharacter(oldValue.charAt(newCursorPos - 1))) {
          newCursorPos--;
        }
        if (newCursorPos != cursorPos) {
          textBox.setCursorPos(newCursorPos);
        }
        break;
      case KeyCodes.KEY_RIGHT:
        while (newCursorPos < StringUtils.length(oldValue)
            && formatingWidget.isFormatingCharacter(oldValue.charAt(newCursorPos))) {
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
          pevent.getNativeEvent().stopPropagation();
          formatingWidget
              .formatValue(new ValueWithPos<>(oldValue, StringUtils.length(oldValue)));
        }
        break;
    }
  }
}
