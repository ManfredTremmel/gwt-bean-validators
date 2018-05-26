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

import de.knightsoftnet.gwtp.spring.client.event.ChangeUserEvent;
import de.knightsoftnet.gwtp.spring.client.event.ChangeUserEvent.ChangeUserHandler;
import de.knightsoftnet.gwtp.spring.client.session.Session;
import de.knightsoftnet.navigation.client.event.ChangePlaceEvent;
import de.knightsoftnet.navigation.client.event.ChangePlaceEvent.ChangePlaceHandler;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * Presenter of the navigation page.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
public class NavigationPresenter
    extends Presenter<NavigationPresenter.MyView, NavigationPresenter.MyProxy> //
    implements ChangeUserHandler, ChangePlaceHandler {

  public interface MyView extends View {
    /**
     * set a reference to the presenter/activity.
     *
     * @param ppresenter reference to set
     */
    void setPresenter(NavigationPresenter ppresenter);

    /**
     * create navigation.
     *
     * @param pnavigation the structure with the navigation data
     */
    void createNavigation(final NavigationStructure pnavigation);

    /**
     * set new active entry.
     *
     * @param pnewEntry new active entry
     */
    void setSelectedItem(final NavigationEntryInterface pnewEntry);
  }

  @ProxyStandard
  @NoGatekeeper
  public interface MyProxy extends Proxy<NavigationPresenter> {
  }

  private final PlaceManager placeManager;
  private final NavigationStructure navigationStructure;

  private String loginToken;
  private String logoutToken;

  /**
   * constructor with injected parameters.
   *
   * @param peventBus event bus
   * @param pview navigation view
   * @param pproxy navigation proxy
   * @param pplaceManager place manager
   * @param pcurrentSession session data
   * @param pnavigationStructure place data
   */
  @Inject
  public NavigationPresenter(final EventBus peventBus, final NavigationPresenter.MyView pview,
      final MyProxy pproxy, final PlaceManager pplaceManager, final Session pcurrentSession,
      final NavigationStructure pnavigationStructure) {
    super(peventBus, pview, pproxy);
    pview.setPresenter(this);
    this.placeManager = pplaceManager;
    this.navigationStructure = pnavigationStructure;

    this.loginToken = NameTokens.LOGIN;
    this.logoutToken = NameTokens.LOGOUT;

    peventBus.addHandler(ChangeUserEvent.getType(), this);
    pnavigationStructure.buildVisibleNavigation(null);

    this.getView().createNavigation(this.navigationStructure);

    peventBus.addHandler(ChangePlaceEvent.getType(), this);

    pcurrentSession.readSessionData();
  }

  @Override
  public void onChangeUser(final ChangeUserEvent pevent) {
    if (this.tokenEquals(this.placeManager.getCurrentPlaceRequest().getNameToken(),
        this.logoutToken)) {
      final PlaceRequest loginPlaceRequest =
          new PlaceRequest.Builder().nameToken(this.loginToken).build();
      this.placeManager.revealPlace(loginPlaceRequest);
    } else {
      if (this.placeManager.getHierarchyDepth() > 1) {
        this.placeManager.revealRelativePlace(-1);
      } else if (this.tokenEquals(this.placeManager.getCurrentPlaceRequest().getNameToken(),
          this.loginToken) && pevent.getUser() != null && pevent.getUser().isLoggedIn()) {
        this.placeManager.revealDefaultPlace();
      } else if (pevent.getUser() == null || !pevent.getUser().isLoggedIn()) {
        // user not logged in, load page once again, maybe we are no longer allowed to see
        this.placeManager.revealCurrentPlace();
      }
    }
    this.navigationStructure.buildVisibleNavigation(pevent.getUser());
    this.getView().createNavigation(this.navigationStructure);
  }

  private boolean tokenEquals(final String purl1, final String purl2) {
    return StringUtils.equals(StringUtils.removeEnd(StringUtils.removeStart(purl1, "/"), "/"),
        StringUtils.removeEnd(StringUtils.removeStart(purl2, "/"), "/"));
  }

  @Override
  public void onChangePlace(final ChangePlaceEvent pevent) {
    if (pevent != null && StringUtils.isNotEmpty(pevent.getToken())) {
      this.getView()
          .setSelectedItem(this.navigationStructure.getNavigationForToken(pevent.getToken()));
    }
  }

  public String getLoginToken() {
    return this.loginToken;
  }

  public void setLoginToken(final String ploginToken) {
    this.loginToken = ploginToken;
  }

  public String getLogoutToken() {
    return this.logoutToken;
  }

  public void setLogoutToken(final String plogoutToken) {
    this.logoutToken = plogoutToken;
  }
}
