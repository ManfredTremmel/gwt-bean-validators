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

package de.knightsoftnet.navigation.client.gin;

import de.knightsoftnet.gwtp.spring.client.session.DummySession;
import de.knightsoftnet.gwtp.spring.client.session.Session;
import de.knightsoftnet.gwtp.spring.shared.models.MinimumUser;
import de.knightsoftnet.gwtp.spring.shared.models.User;
import de.knightsoftnet.navigation.client.OwnPlaceManagerImpl;
import de.knightsoftnet.navigation.client.ui.navigation.DummyNavigationStructure;
import de.knightsoftnet.navigation.client.ui.navigation.NameTokens;
import de.knightsoftnet.navigation.client.ui.navigation.NavigationPresenter;
import de.knightsoftnet.navigation.client.ui.navigation.NavigationStructure;
import de.knightsoftnet.navigation.client.ui.navigation.TreeNavigationView;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

import javax.inject.Singleton;

public class ClientModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    // dummy, redefine in your application
    this.install(new DefaultModule.Builder().placeManager(OwnPlaceManagerImpl.class)
        .defaultPlace(NameTokens.LOGIN).errorPlace(NameTokens.LOGIN)
        .unauthorizedPlace(NameTokens.LOGIN).build());

    this.bindPresenter(NavigationPresenter.class, NavigationPresenter.MyView.class,
        TreeNavigationView.class, NavigationPresenter.MyProxy.class);

    this.bind(User.class).to(MinimumUser.class).in(Singleton.class);
    this.bind(Session.class).to(DummySession.class).in(Singleton.class);
    this.bind(NavigationStructure.class).to(DummyNavigationStructure.class).in(Singleton.class);
  }
}
