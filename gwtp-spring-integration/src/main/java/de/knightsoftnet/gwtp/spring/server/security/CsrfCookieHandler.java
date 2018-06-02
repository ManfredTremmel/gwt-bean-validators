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

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import de.knightsoftnet.gwtp.spring.shared.ResourcePaths;

/**
 * set csrf/xsrf cookie.
 *
 * @author Manfred Tremmel
 */
@Component
public class CsrfCookieHandler {

  /**
   * set csrf/xsrf cookie.
   *
   * @param prequest http servlet request
   * @param presponse http servlet response
   * @throws IOException when io operation fails
   */
  public void setCookie(final HttpServletRequest prequest, final HttpServletResponse presponse)
      throws IOException {
    final CsrfToken csrf = (CsrfToken) prequest.getAttribute(CsrfToken.class.getName());
    if (csrf != null) {
      Cookie cookie = WebUtils.getCookie(prequest, ResourcePaths.XSRF_COOKIE);
      final String token = csrf.getToken();
      if (cookie == null || token != null && !token.equals(cookie.getValue())) {
        cookie = new Cookie(ResourcePaths.XSRF_COOKIE, token);
        cookie.setPath(
            StringUtils.defaultString(StringUtils.trimToNull(prequest.getContextPath()), "/"));
        presponse.addCookie(cookie);
      }
    }
  }
}
