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

package de.knightsoftnet.validators.client.gin;

import de.knightsoftnet.gwtp.spring.shared.ResourcePaths;

import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Provides;
import com.gwtplatform.dispatch.rest.client.RestApplicationPath;
import com.gwtplatform.dispatch.rest.client.gin.RestDispatchAsyncModule;
import com.gwtplatform.dispatch.shared.SecurityCookie;

import org.apache.commons.lang3.StringUtils;

/**
 * Adding xsrf/csrf protection on client side.
 *
 * @author Manfred Tremmel
 */
public class ServiceModule extends AbstractGinModule {
  @Override
  protected void configure() {
    this.install(new RestDispatchAsyncModule.Builder()
        .xsrfTokenHeaderName(ResourcePaths.XSRF_HEADER).build());

    this.bindConstant().annotatedWith(SecurityCookie.class).to(ResourcePaths.XSRF_COOKIE);
  }

  @Provides
  @RestApplicationPath
  String getApplicationPath() {
    return StringUtils.removeEnd(StringUtils
        .removeEnd(StringUtils.removeEnd(GWT.getModuleBaseURL(), "/"), GWT.getModuleName()), "/");
  }
}
