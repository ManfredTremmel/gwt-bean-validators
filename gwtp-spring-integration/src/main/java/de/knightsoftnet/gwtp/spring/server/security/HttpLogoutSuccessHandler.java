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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * http logout success handler for gwt applications. based on the work of
 * https://github.com/imrabti/gwtp-spring-security
 *
 * @author Manfred Tremmel
 */
@Component
public class HttpLogoutSuccessHandler implements LogoutSuccessHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(HttpLogoutSuccessHandler.class);

  private final CsrfCookieHandler csrfCookieHandler;

  @Inject
  public HttpLogoutSuccessHandler(final CsrfCookieHandler pcsrfCookieHandler) {
    super();
    csrfCookieHandler = pcsrfCookieHandler;
  }

  @Override
  public void onLogoutSuccess(final HttpServletRequest prequest,
      final HttpServletResponse presponse, final Authentication pauthentication)
      throws IOException {
    LOGGER.info("User logged out!");
    presponse.setStatus(HttpServletResponse.SC_OK);
    csrfCookieHandler.setCookie(prequest, presponse);
  }
}
