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

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.ValueBoxBase;

import org.apache.commons.lang3.StringUtils;

/**
 * Key press handler which limits and replaces characters and formats input. It can be attached to
 * widgets which implement HasFormating interface!
 *
 * @param <E> field type
 * @author Manfred Tremmel
 */
public class FilterReplaceAndFormatKeyPressHandler<E> extends ValueBoxFromEvent<E>
    implements KeyPressHandler {

  private final NavigationKeys navigationKeys;

  /**
   * default constructor.
   */
  public FilterReplaceAndFormatKeyPressHandler() {
    super();
    this.navigationKeys = new NavigationKeys();
  }

  @Override
  public void onKeyPress(final KeyPressEvent pevent) {
    int keyCode = 0;
    if (pevent.getNativeEvent() != null) {
      keyCode = pevent.getNativeEvent().getKeyCode();
    }
    final char charCode = pevent.getCharCode();
    final String type = pevent.getSource().getClass().getName();
    GWT.log(type);
    final HasFormating formatingWidget = (HasFormating) pevent.getSource();
    final ValueBoxBase<E> textBox = getTextBoxFromEvent(pevent);
    final int cursorPos = textBox.getCursorPos();
    final String oldValue = textBox.getText();

    // accept navigation keys like cursor right, left, ...
    if (this.navigationKeys.isNavigationKey(keyCode)) {
      return;
    }
    // Copy, Cut or Paste allowed?
    if (pevent.isControlKeyDown() && (charCode == 'c' || charCode == 'x' || charCode == 'v')) {
      return;
    }
    // check for allowed characters
    if (formatingWidget.isAllowedCharacter(charCode)
        || formatingWidget.isCharacterToReplace(charCode)) {
      final String newTmpValue =
          StringUtils.substring(oldValue, 0, cursorPos) + formatingWidget.replaceCharacter(charCode)
              + StringUtils.substring(oldValue, cursorPos + textBox.getSelectionLength());
      formatingWidget.formatValue(new ValueWithPos<>(newTmpValue, cursorPos + 1));
    }
    pevent.getNativeEvent().preventDefault();
  }
}
