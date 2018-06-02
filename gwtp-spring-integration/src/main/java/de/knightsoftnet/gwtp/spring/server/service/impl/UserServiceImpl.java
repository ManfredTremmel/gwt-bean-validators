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

package de.knightsoftnet.gwtp.spring.server.service.impl;

import org.springframework.stereotype.Service;

import javax.inject.Inject;

import de.knightsoftnet.gwtp.spring.server.security.LoggedInChecker;
import de.knightsoftnet.gwtp.spring.server.service.UserService;
import de.knightsoftnet.gwtp.spring.shared.models.User;

/**
 * user service implementation. based on the work of https://github.com/imrabti/gwtp-spring-security
 *
 * @author Manfred Tremmel
 */
@Service
public class UserServiceImpl implements UserService {

  private final LoggedInChecker loggedInChecker;

  @Inject
  protected UserServiceImpl(final LoggedInChecker ploggedInChecker) {
    loggedInChecker = ploggedInChecker;
  }

  @Override
  public User getCurrentUser() {
    return loggedInChecker.getLoggedInUser();
  }

  @Override
  public Boolean isCurrentUserLoggedIn() {
    return loggedInChecker.getLoggedInUser() != null;
  }
}
