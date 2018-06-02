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

package de.knightsoftnet.gwtp.spring.server.controller;

import static org.springframework.http.ResponseEntity.ok;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;

import de.knightsoftnet.gwtp.spring.server.service.UserService;
import de.knightsoftnet.gwtp.spring.shared.ResourcePaths;
import de.knightsoftnet.gwtp.spring.shared.models.User;

/**
 * user web service. based on the work of https://github.com/imrabti/gwtp-spring-security
 *
 * @author Manfred Tremmel
 */
@RestController
@RequestMapping(value = ResourcePaths.User.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
  private final UserService userService;

  @Inject
  protected UserController(final UserService puserService) {
    userService = puserService;
  }

  @RequestMapping(value = ResourcePaths.User.LOGIN, method = RequestMethod.GET)
  @PermitAll
  ResponseEntity<Boolean> isCurrentUserLoggedIn() {
    return new ResponseEntity<>(userService.isCurrentUserLoggedIn(), HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET)
  ResponseEntity<User> getCurrentUser() {
    return ok(userService.getCurrentUser());
  }
}
