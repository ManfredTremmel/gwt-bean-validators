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

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.Window;

import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * set of navigation keys which have to be allowed.
 *
 * @author Manfred Tremmel
 *
 */
public class NavigationKeys {

  private final Set<Integer> navigationChars;

  /**
   * default constructor.
   */
  public NavigationKeys() {
    super();
    this.navigationChars = new HashSet<>();
    this.navigationChars.add(KeyCodes.KEY_TAB);
    this.navigationChars.add(KeyCodes.KEY_ENTER);
    if (StringUtils.contains(StringUtils.upperCase(Window.Navigator.getUserAgent()), "GECKO/")) {
      this.navigationChars.add(KeyCodes.KEY_ALT);
      this.navigationChars.add(KeyCodes.KEY_BACKSPACE);
      this.navigationChars.add(KeyCodes.KEY_CTRL);
      this.navigationChars.add(KeyCodes.KEY_DELETE);
      this.navigationChars.add(KeyCodes.KEY_DOWN);
      this.navigationChars.add(KeyCodes.KEY_END);
      this.navigationChars.add(KeyCodes.KEY_ESCAPE);
      this.navigationChars.add(KeyCodes.KEY_HOME);
      this.navigationChars.add(KeyCodes.KEY_LEFT);
      this.navigationChars.add(KeyCodes.KEY_PAGEDOWN);
      this.navigationChars.add(KeyCodes.KEY_PAGEUP);
      this.navigationChars.add(KeyCodes.KEY_RIGHT);
      this.navigationChars.add(KeyCodes.KEY_SHIFT);
      this.navigationChars.add(KeyCodes.KEY_UP);
    }
  }

  /**
   * get a set of navigation character keys.
   *
   * @return set of characters with navigation keys that shouldn't be blocked
   */
  public Set<Integer> getNavigationChars() {
    return this.navigationChars;
  }

  /**
   * check if key value is a navigation key.
   *
   * @param pkeyCode code of the key
   * @return true if it's a navigation key
   */
  public boolean isNavigationKey(final int pkeyCode) {
    return this.navigationChars.contains(Integer.valueOf(pkeyCode));
  }
}
