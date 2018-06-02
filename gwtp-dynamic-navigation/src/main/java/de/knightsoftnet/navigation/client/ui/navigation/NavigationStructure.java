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

import de.knightsoftnet.gwtp.spring.shared.models.User;
import de.knightsoftnet.navigation.client.event.ChangePlaceEvent;

import java.util.List;

public interface NavigationStructure {

  /**
   * build the visible navigation entries.
   *
   * @param puser the user to build navigation for
   */
  void buildVisibleNavigation(User puser);

  /**
   * get full navigation list.
   *
   * @return the fullNavigationList
   */
  List<NavigationEntryInterface> getFullNavigationList();

  /**
   * get navigation list.
   *
   * @return the navigationList
   */
  List<NavigationEntryInterface> getNavigationList();

  /**
   * set navigation list.
   *
   * @param pnavigationList the navigationList to set
   */
  void setNavigationList(List<NavigationEntryInterface> pnavigationList);

  /**
   * get active navigation entry interface.
   *
   * @return the activeNavigationEntryInterface
   */
  NavigationEntryInterface getActiveNavigationEntryInterface();

  /**
   * set active navigation entry interface.
   *
   * @param pactiveNavigationEntryInterface the activeNavigationEntryInterface to set
   */
  void setActiveNavigationEntryInterface(NavigationEntryInterface pactiveNavigationEntryInterface);

  /**
   * set active navigation entry interface.
   *
   * @param ptoken the token to set
   */
  void setActiveNavigationEntryInterface(String ptoken);

  /**
   * get navigation entry for given token.
   *
   * @param ptoken the token of the place to get navigation entry for
   * @return navigation entry for place or null if none found
   */
  NavigationEntryInterface getNavigationForToken(String ptoken);

  /**
   * called on place changed.
   *
   * @param pevent change place event fired on place change
   */
  void onChangePlace(ChangePlaceEvent pevent);

  /**
   * set the login token.
   *
   * @param ploginToken login token
   */
  void setLoginToken(String ploginToken);

  /**
   * get the login token.
   *
   * @return login token
   */
  String getLoginToken();
}
