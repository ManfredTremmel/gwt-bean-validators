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

package de.knightsoftnet.gwtp.spring.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

import de.knightsoftnet.gwtp.spring.shared.models.User;

/**
 * event used for user changes (e.g. login or logout).
 *
 * @author Manfred Tremmel
 *
 */
public class ChangeUserEvent extends GwtEvent<ChangeUserEvent.ChangeUserHandler> {
  private static final Type<ChangeUserHandler> TYPE = new Type<>();

  public interface ChangeUserHandler extends EventHandler {
    void onChangeUser(ChangeUserEvent event);
  }

  public interface ChangeUserHandlers extends HasHandlers {
    HandlerRegistration addHasChangeUserEventHandler(ChangeUserHandler phandler);
  }

  private final User user;

  public ChangeUserEvent(final User puser) {
    super();
    user = puser;
  }

  public static Type<ChangeUserHandler> getType() {
    return ChangeUserEvent.TYPE;
  }

  @Override
  protected void dispatch(final ChangeUserHandler phandler) {
    phandler.onChangeUser(this);
  }

  @Override
  public Type<ChangeUserHandler> getAssociatedType() {
    return ChangeUserEvent.TYPE;
  }

  public User getUser() {
    return user;
  }
}
