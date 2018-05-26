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

package de.knightsoftnet.navigation.client.ui.navigation;

import com.google.gwt.safehtml.shared.SafeHtml;

/**
 * The <code>NavigationEntryInterface</code> defines one menu entry or folders with subentries as
 * interface.
 *
 * @author Manfred Tremmel
 */
public interface NavigationEntryInterface {

  /**
   * get menu value.
   *
   * @return the menuValue
   */
  SafeHtml getMenuValue();

  /**
   * get token.
   *
   * @return the token
   */
  String getToken();

  /**
   * get full token.
   *
   * @return the token with static and dynamic part
   */
  String getFullToken();

  /**
   * get token dynamic.
   *
   * @return the tokenDynamic
   */
  String getTokenDynamic();

  /**
   * set token dynamic.
   *
   * @param ptokenDynamic the tokenDynamic to set
   */
  void setTokenDynamic(String ptokenDynamic);

  /**
   * get parent entry.
   *
   * @return the parentEntry
   */
  NavigationEntryInterface getParentEntry();

  /**
   * add a parent entry.
   *
   * @param pparentEntry entry to add
   */
  void setParentEntry(NavigationEntryInterface pparentEntry);

  /**
   * is entry open on startup.
   *
   * @return the openOnStartup
   */
  boolean isOpenOnStartup();

  /**
   * check if this entry should be displayed.
   *
   * @return true if it should be displayed
   */
  boolean canReveal();

}
