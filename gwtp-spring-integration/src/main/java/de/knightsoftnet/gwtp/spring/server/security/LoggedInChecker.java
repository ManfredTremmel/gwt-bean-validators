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

package de.knightsoftnet.gwtp.spring.server.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import de.knightsoftnet.gwtp.spring.server.converter.UserDetailsConverter;
import de.knightsoftnet.gwtp.spring.shared.models.User;

/**
 * check if user is logged in. based on the work of https://github.com/imrabti/gwtp-spring-security
 *
 * @author Manfred Tremmel
 */
@Component
public class LoggedInChecker {

  private final UserDetailsConverter userDetailsConverter;

  @Inject
  public LoggedInChecker(final UserDetailsConverter puserDetailsConverter) {
    super();
    userDetailsConverter = puserDetailsConverter;
  }

  /**
   * get logged in user.
   *
   * @return UserData or null if no one is logged in
   */
  public User getLoggedInUser() {
    User user = null;

    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null) {
      final Object principal = authentication.getPrincipal();

      // principal can be "anonymousUser" (String)
      if (principal instanceof UserDetails) {
        user = userDetailsConverter.convert((UserDetails) principal);
      }
    }
    return user;
  }
}
