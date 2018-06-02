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

package de.knightsoftnet.navigation.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * event used for place changes (navigate from one page to another).
 *
 * @author Manfred Tremmel
 *
 */
public class ChangePlaceEvent extends GwtEvent<ChangePlaceEvent.ChangePlaceHandler> {
  private static final Type<ChangePlaceHandler> TYPE = new Type<>();

  public interface ChangePlaceHandler extends EventHandler {
    void onChangePlace(ChangePlaceEvent pevent);
  }

  public interface ChangePlaceHandlers extends HasHandlers {
    HandlerRegistration addChangePlaceHandler(ChangePlaceHandler phandler);
  }

  private final PlaceRequest placeRequest;

  public ChangePlaceEvent(final PlaceRequest pplaceRequest) {
    super();
    placeRequest = pplaceRequest;
  }

  public static Type<ChangePlaceHandler> getType() {
    return ChangePlaceEvent.TYPE;
  }

  @Override
  protected void dispatch(final ChangePlaceHandler phandler) {
    phandler.onChangePlace(this);
  }

  @Override
  public Type<ChangePlaceHandler> getAssociatedType() {
    return ChangePlaceEvent.TYPE;
  }

  public final PlaceRequest getPlaceRequest() {
    return placeRequest;
  }

  /**
   * get/create token out of placeRequest.
   *
   * @return token string
   */
  public final String getToken() {
    if (placeRequest == null) {
      return null;
    }
    if (placeRequest.getParameterNames() == null || placeRequest.getParameterNames().size() == 0) {
      return placeRequest.getNameToken();
    }
    final StringBuilder token = new StringBuilder();
    token.append(placeRequest.getNameToken());
    for (final String param : placeRequest.getParameterNames()) {
      token.append(';');
      token.append(param);
      token.append('=');
      token.append(placeRequest.getParameter(param, StringUtils.EMPTY));
    }
    return token.toString();
  }
}
