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

package de.knightsoftnet.gwtp.spring.client.session;

import com.google.web.bindery.event.shared.EventBus;

import java.util.Objects;

import de.knightsoftnet.gwtp.spring.client.event.ChangeUserEvent;
import de.knightsoftnet.gwtp.spring.shared.models.User;

public abstract class AbstractSession implements Session {
  private final EventBus eventBus;

  private User user;

  /**
   * constructor with injected parameters.
   *
   * @param peventBus event bus
   */
  public AbstractSession(final EventBus peventBus) {
    super();
    eventBus = peventBus;
  }

  @Override
  public abstract void readSessionData();

  @Override
  public User getUser() {
    return user;
  }

  @Override
  public void setUser(final User puser) {
    final boolean changed = !Objects.equals(puser, user);
    user = puser;
    if (changed) {
      eventBus.fireEvent(new ChangeUserEvent(user));
    }
  }

  @Override
  public boolean isLoggedIn() {
    return user != null && user.isLoggedIn();
  }
}
