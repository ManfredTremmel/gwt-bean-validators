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

package de.knightsoftnet.gwtp.spring.client;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;

import org.apache.commons.lang3.BooleanUtils;

import de.knightsoftnet.gwtp.spring.client.rest.helper.AbstractSimpleRestCallback;
import de.knightsoftnet.gwtp.spring.client.rest.helper.HasShowMessage;
import de.knightsoftnet.gwtp.spring.client.rest.helper.HttpMessages;
import de.knightsoftnet.gwtp.spring.client.services.UserRestServiceTemplate;
import de.knightsoftnet.gwtp.spring.client.session.AbstractSession;
import de.knightsoftnet.gwtp.spring.shared.models.User;

/**
 * Session handler using spring rest service.
 *
 * @author Manfred Tremmel
 *
 * @param <T> user data implementation
 */
public class GwtpSpringSession<T extends User> extends AbstractSession implements HasShowMessage {
  private final RestDispatch dispatcher;
  private final UserRestServiceTemplate<T> userService;

  /**
   * constructor with injected parameters.
   *
   * @param peventBus event bus
   * @param pdispatcher rest dispatcher
   * @param puserService user rest service
   */
  public GwtpSpringSession(final EventBus peventBus, final RestDispatch pdispatcher,
      final UserRestServiceTemplate<T> puserService) {
    super(peventBus);
    this.dispatcher = pdispatcher;
    this.userService = puserService;
  }

  /**
   * read session data.
   */
  @Override
  public void readSessionData() {
    this.dispatcher.execute(this.userService.isCurrentUserLoggedIn(),
        new AbstractSimpleRestCallback<GwtpSpringSession<T>, Boolean, HttpMessages>(this, this) {

          @Override
          public void onSuccess(final Boolean presult) {
            if (BooleanUtils.isTrue(presult)) {
              // we do have a logged in user, read it
              GwtpSpringSession.this.dispatcher.execute(
                  GwtpSpringSession.this.userService.getCurrentUser(),
                  new AbstractSimpleRestCallback<GwtpSpringSession<T>, T, HttpMessages>(
                      GwtpSpringSession.this, GwtpSpringSession.this) {

                    @Override
                    public void onSuccess(final T presult) {
                      GwtpSpringSession.this.setUser(presult);
                    }
                  });
            } else {
              GwtpSpringSession.this.setUser(null);
            }
          }

        });
  }

  @Override
  public void showMessage(final String pmessage) {
    GWT.log(pmessage);
  }
}
