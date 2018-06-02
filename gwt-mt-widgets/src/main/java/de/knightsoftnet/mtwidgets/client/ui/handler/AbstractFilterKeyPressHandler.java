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

import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;

import java.util.Set;
import java.util.TreeSet;

/**
 * Abstract key press handler which limits input to allowed characters.
 *
 * @author Manfred Tremmel
 */
public abstract class AbstractFilterKeyPressHandler implements KeyPressHandler {

  /**
   * allowed characters.
   */
  private Set<Character> allowedCharacters;

  /**
   * allow copy and paste.
   */
  private final boolean allowCopyAndPast;

  private final NavigationKeys navigationKeys;

  /**
   * constructor initializing fields.
   *
   * @param pallowedCharacters characters which are allowed to add
   * @param pallowCopyAndPast is copy and paste allowed?
   */
  public AbstractFilterKeyPressHandler(final Set<Character> pallowedCharacters,
      final boolean pallowCopyAndPast) {
    super();
    this.setAllowedCharacters(pallowedCharacters);
    allowCopyAndPast = pallowCopyAndPast;
    navigationKeys = new NavigationKeys();
  }

  /**
   * constructor initializing fields.
   *
   * @param pallowedCharacters characters which are allowed to add
   * @param pallowCopyAndPast is copy and paste allowed?
   */
  public AbstractFilterKeyPressHandler(final String pallowedCharacters,
      final boolean pallowCopyAndPast) {
    super();
    this.setAllowedCharacters(pallowedCharacters);
    allowCopyAndPast = pallowCopyAndPast;
    navigationKeys = new NavigationKeys();
  }

  protected final Set<Character> getAllowedCharacters() {
    return allowedCharacters;
  }

  protected final void setAllowedCharacters(final Set<Character> pallowedCharacters) {
    allowedCharacters = pallowedCharacters;
  }

  protected final void setAllowedCharacters(final String pallowedCharacters) {
    allowedCharacters = new TreeSet<>();
    for (final char character : pallowedCharacters.toCharArray()) {
      allowedCharacters.add(Character.valueOf(character));
    }
  }

  @Override
  public void onKeyPress(final KeyPressEvent pevent) {
    int keyCode = 0;
    if (pevent.getNativeEvent() != null) {
      keyCode = pevent.getNativeEvent().getKeyCode();
    }
    final char charCode = pevent.getCharCode();

    // accept navigation keys like cursor right, left, ...
    if (navigationKeys.isNavigationKey(keyCode)) {
      return;
    }
    // Copy, Cut or Paste allowed?
    if (allowCopyAndPast && pevent.isControlKeyDown()
        && (charCode == 'c' || charCode == 'x' || charCode == 'v')) {
      return;
    }
    // check for allowed characters
    if (allowedCharacters.contains(Character.valueOf(charCode))) {
      return;
    }
    // nothing matched, cancel event
    pevent.getNativeEvent().preventDefault();
  }
}
