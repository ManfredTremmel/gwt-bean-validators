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

package de.knightsoftnet.navigation.client;

import de.knightsoftnet.navigation.client.event.ChangePlaceEvent;
import de.knightsoftnet.navigation.client.ui.navigation.NavigationStructure;

import com.google.gwt.place.shared.PlaceHistoryHandler.Historian;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.proxy.PlaceManagerImpl;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.gwtplatform.mvp.shared.proxy.TokenFormatter;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;

import javax.inject.Inject;

public class OwnPlaceManagerImpl extends PlaceManagerImpl {
  private final PlaceRequest defaultPlaceRequest;
  private final PlaceRequest errorPlaceRequest;
  private final PlaceRequest unauthorizedPlaceRequest;

  /**
   * constructor injecting parameters.
   *
   * @param peventBus event bus
   * @param ptokenFormatter token formatter
   * @param pdefaultPlaceNameToken default place name token
   * @param perrorPlaceNameToken error place name token
   * @param punauthorizedPlaceNameToken unauthorized place name token
   * @param phistorian historian
   * @param pnavigationStructure navigation structure
   */
  @Inject
  public OwnPlaceManagerImpl(final EventBus peventBus, final TokenFormatter ptokenFormatter,
      @DefaultPlace final String pdefaultPlaceNameToken,
      @ErrorPlace final String perrorPlaceNameToken,
      @UnauthorizedPlace final String punauthorizedPlaceNameToken, final Historian phistorian,
      final NavigationStructure pnavigationStructure) {
    super(peventBus, ptokenFormatter, phistorian);

    pnavigationStructure.setActiveNavigationEntryInterface(pdefaultPlaceNameToken);
    if (StringUtils.contains(pdefaultPlaceNameToken, '{')) {
      defaultPlaceRequest = new PlaceRequest.Builder().nameToken(pdefaultPlaceNameToken)
          .with(Collections.emptyMap()).build();
    } else {
      defaultPlaceRequest = new PlaceRequest.Builder().nameToken(pdefaultPlaceNameToken).build();
    }
    if (StringUtils.contains(pdefaultPlaceNameToken, '{')) {
      errorPlaceRequest = new PlaceRequest.Builder().nameToken(perrorPlaceNameToken)
          .with(Collections.emptyMap()).build();
    } else {
      errorPlaceRequest = new PlaceRequest.Builder().nameToken(perrorPlaceNameToken).build();
    }
    if (StringUtils.contains(pdefaultPlaceNameToken, '{')) {
      unauthorizedPlaceRequest = new PlaceRequest.Builder().nameToken(punauthorizedPlaceNameToken)
          .with(Collections.emptyMap()).build();
    } else {
      unauthorizedPlaceRequest =
          new PlaceRequest.Builder().nameToken(punauthorizedPlaceNameToken).build();
    }
  }

  @Override
  public void revealDefaultPlace() {
    this.revealPlace(defaultPlaceRequest, false);
  }

  @Override
  public void revealErrorPlace(final String pinvalidHistoryToken) {
    this.revealPlace(errorPlaceRequest, false);
  }

  @Override
  public void revealUnauthorizedPlace(final String punauthorizedHistoryToken) {
    this.revealRelativePlace(unauthorizedPlaceRequest);
  }

  @Override
  protected void doRevealPlace(final PlaceRequest prequest, final boolean pupdateBrowserUrl) {
    super.doRevealPlace(prequest, pupdateBrowserUrl);
    getEventBus().fireEvent(new ChangePlaceEvent(prequest));
  }
}
