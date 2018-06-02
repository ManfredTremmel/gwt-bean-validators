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

import de.knightsoftnet.gwtp.spring.client.session.Session;
import de.knightsoftnet.gwtp.spring.shared.models.User;
import de.knightsoftnet.navigation.client.event.ChangePlaceEvent;
import de.knightsoftnet.navigation.client.event.ChangePlaceEvent.ChangePlaceHandler;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.web.bindery.event.shared.EventBus;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

/**
 * The <code>AbstractNavigationPlace</code> defines the methods which are used to handle
 * navigation/menu entries.
 *
 * @author Manfred Tremmel
 */
public abstract class AbstractNavigationStructure
    implements ChangePlaceHandler, NavigationStructure {

  /**
   * map to find navigation entries for places.
   */
  private final Map<String, NavigationEntryInterface> placeMap;

  /**
   * list of all navigation entries.
   */
  private List<NavigationEntryInterface> fullNavigationList;

  /**
   * list of active navigation entries (if there are not all displayed).
   */
  private List<NavigationEntryInterface> navigationList;

  /**
   * selected navigation entry.
   */
  private String activeToken;

  /**
   * token for login.
   */
  private String loginToken;

  /**
   * default constructor.
   */
  @Inject
  public AbstractNavigationStructure(final EventBus peventBus) {
    this(peventBus, NameTokens.LOGIN);
  }

  /**
   * constructor.
   *
   * @param peventBus event bus
   * @param ploginToken login token
   */
  public AbstractNavigationStructure(final EventBus peventBus, final String ploginToken) {
    super();
    placeMap = new HashMap<>();
    peventBus.addHandler(ChangePlaceEvent.getType(), this);
    loginToken = ploginToken;
  }

  @Inject
  public void init(final Session psession) {
    buildVisibleNavigation(psession == null ? null : psession.getUser());
  }

  @Override
  public final void buildVisibleNavigation(final User puser) {
    fullNavigationList = recursiveGetEntries(buildNavigation());
    generateMapRecursive(fullNavigationList);
    navigationList = fullNavigationList;
  }

  /**
   * build the navigation list.
   *
   * @return list of navigation entries
   */
  protected abstract List<NavigationEntryInterface> buildNavigation();

  /**
   * create map out of the navigation list.
   *
   * @param pnavigationEntries list of navigation entries
   */
  private void generateMapRecursive(final List<NavigationEntryInterface> pnavigationEntries) {
    for (final NavigationEntryInterface entryToAdd : pnavigationEntries) {
      String token = entryToAdd.getToken();
      if (entryToAdd.getMenuValue() != null && token != null) {
        if (token.endsWith("/" + StringUtils.removeStart(loginToken, "/"))) {
          token = loginToken;
        }
        if (!placeMap.containsKey(token)) {
          placeMap.put(token, entryToAdd);
        }
      }
      if (entryToAdd instanceof NavigationEntryFolder) {
        generateMapRecursive(((NavigationEntryFolder) entryToAdd).getSubEntries());
      }
    }
  }

  /**
   * get all navigation entries that can be displayed by a given user.
   *
   * @param pnavigationEntries entries to test
   * @param puser the user to test
   * @return the navigationEntries
   */
  private List<NavigationEntryInterface> recursiveGetEntries(
      final List<NavigationEntryInterface> pnavigationEntries) {
    if (pnavigationEntries == null) {
      return Collections.emptyList();
    }
    return pnavigationEntries.stream().filter(entry -> entry.canReveal()).map(entry -> {
      if (entry instanceof NavigationEntryFolder) {
        return new NavigationEntryFolder(entry.getMenuValue(), entry.isOpenOnStartup(),
            recursiveGetEntries(((NavigationEntryFolder) entry).getSubEntries()));
      } else {
        return entry;
      }
    }).collect(Collectors.toList());
  }

  @Override
  public final List<NavigationEntryInterface> getFullNavigationList() {
    return fullNavigationList;
  }

  @Override
  public final List<NavigationEntryInterface> getNavigationList() {
    return navigationList;
  }

  @Override
  public final void setNavigationList(final List<NavigationEntryInterface> pnavigationList) {
    navigationList = pnavigationList;
  }

  @Override
  public final NavigationEntryInterface getActiveNavigationEntryInterface() {
    return getNavigationForToken(activeToken);
  }

  @Override
  public final void setActiveNavigationEntryInterface(
      final NavigationEntryInterface pactiveNavigationEntryInterface) {
    activeToken =
        pactiveNavigationEntryInterface == null ? null : pactiveNavigationEntryInterface.getToken();
  }

  @Override
  public final void setActiveNavigationEntryInterface(final String ptoken) {
    activeToken = ptoken;
  }

  @Override
  public final NavigationEntryInterface getNavigationForToken(final String ptoken) {
    NavigationEntryInterface entry = placeMap.get(ptoken);
    if (entry == null && StringUtils.contains(ptoken, '?')) {
      final int posSeparator = ptoken.indexOf('?');
      if (posSeparator > 0) {
        entry = placeMap.get(ptoken.substring(0, posSeparator));
      }
    }
    return entry;
  }

  @Override
  public void onChangePlace(final ChangePlaceEvent pevent) {
    this.setActiveNavigationEntryInterface(pevent.getToken());
  }

  @Override
  public final String getLoginToken() {
    return loginToken;
  }

  @Override
  public final void setLoginToken(final String ploginToken) {
    loginToken = ploginToken;
  }

  protected SafeHtml createMenuEntry(final ImageResource pimage, final String ptext) {
    final SafeHtmlBuilder menuShb = new SafeHtmlBuilder();
    menuShb.append(AbstractImagePrototype.create(pimage).getSafeHtml()).append(' ')
        .appendEscaped(ptext);
    return menuShb.toSafeHtml();
  }
}
