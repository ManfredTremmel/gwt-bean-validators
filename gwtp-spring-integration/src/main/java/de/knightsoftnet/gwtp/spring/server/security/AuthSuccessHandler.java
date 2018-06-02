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

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import de.knightsoftnet.gwtp.spring.server.converter.UserDetailsConverter;

/**
 * authentication success handler for gwt applications. based on the work of
 * https://github.com/imrabti/gwtp-spring-security
 *
 * @author Manfred Tremmel
 */
@Component
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
  private static final Logger LOGGER = LoggerFactory.getLogger(AuthSuccessHandler.class);

  private final ObjectMapper mapper;
  private final CsrfCookieHandler csrfCookieHandler;
  private final UserDetailsConverter userDetailsConverter;

  @Inject
  protected AuthSuccessHandler(final CsrfCookieHandler pcsrfCookieHandler,
      final UserDetailsConverter puserDetailsConverter) {
    super();
    final MappingJackson2HttpMessageConverter pmessageConverter =
        new MappingJackson2HttpMessageConverter();
    mapper = pmessageConverter.getObjectMapper();
    csrfCookieHandler = pcsrfCookieHandler;
    userDetailsConverter = puserDetailsConverter;
  }

  @Override
  public void onAuthenticationSuccess(final HttpServletRequest prequest,
      final HttpServletResponse presponse, final Authentication pauthentication)
      throws IOException, ServletException {
    csrfCookieHandler.setCookie(prequest, presponse);

    if (pauthentication.isAuthenticated()) {
      presponse.setStatus(HttpServletResponse.SC_OK);
      LOGGER.info("User is authenticated!");
      LOGGER.debug(pauthentication.toString());

      final PrintWriter writer = presponse.getWriter();
      mapper.writeValue(writer,
          userDetailsConverter.convert((UserDetails) pauthentication.getPrincipal()));
      writer.flush();
    } else {
      presponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
  }
}
