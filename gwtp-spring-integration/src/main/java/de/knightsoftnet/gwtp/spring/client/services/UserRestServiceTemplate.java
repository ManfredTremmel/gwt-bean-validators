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

package de.knightsoftnet.gwtp.spring.client.services;

import com.gwtplatform.dispatch.rest.shared.RestAction;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import de.knightsoftnet.gwtp.spring.shared.Parameters;
import de.knightsoftnet.gwtp.spring.shared.ResourcePaths;
import de.knightsoftnet.gwtp.spring.shared.models.User;

/**
 * Definition of the login/logout remote services template.
 *
 * @param <T> user data
 *
 * @author Manfred Tremmel
 */
public interface UserRestServiceTemplate<T extends User> {

  @POST
  @Path(ResourcePaths.User.LOGIN)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  RestAction<T> login(@FormParam(Parameters.USERNAME) String pusername,
      @FormParam(Parameters.PASSWORD) String ppassword);

  @DELETE
  @Path(ResourcePaths.User.LOGIN)
  RestAction<Void> logout();

  @GET
  @Path(ResourcePaths.User.LOGIN)
  RestAction<Boolean> isCurrentUserLoggedIn();

  @GET
  RestAction<T> getCurrentUser();
}
