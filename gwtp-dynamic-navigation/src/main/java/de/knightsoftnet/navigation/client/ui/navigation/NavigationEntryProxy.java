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

package de.knightsoftnet.navigation.client.ui.navigation;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * The <code>NavigationEntry</code> defines one menu entry.
 *
 * @author Manfred Tremmel
 */
public class NavigationEntryProxy implements NavigationEntryInterface {
  /**
   * entry for the menu, this one my contain text or maybe even pictures.
   */
  private final SafeHtml menuValue;

  /**
   * the proxy place.
   */
  private final ProxyPlace<?> proxyPlace;

  /**
   * menu entry is open on startup.
   */
  private final boolean openOnStartup;

  /**
   * parent entry of this entry (null if we are on top level).
   */
  private NavigationEntryInterface parentEntry;

  /**
   * dynamic part of the token.
   */
  private String tokenDynamic;

  /**
   * constructor for menu entries.
   *
   * @param pmenuValue menu value
   * @param pproxyPlace proxy place to set
   */
  public NavigationEntryProxy(final SafeHtml pmenuValue, final ProxyPlace<?> pproxyPlace) {
    super();
    menuValue = pmenuValue;
    proxyPlace = pproxyPlace;
    openOnStartup = true;
    parentEntry = null;
  }

  @Override
  public final SafeHtml getMenuValue() {
    return menuValue;
  }

  @Override
  public final String getToken() {
    return proxyPlace.getNameToken();
  }

  @Override
  public final String getFullToken() {
    return proxyPlace.getNameToken();
  }

  @Override
  public final String getTokenDynamic() {
    return tokenDynamic;
  }

  @Override
  public final void setTokenDynamic(final String ptokenDynamic) {
    if (StringUtils.equals(ptokenDynamic, getToken())) {
      tokenDynamic = null;
    } else if (StringUtils.startsWith(ptokenDynamic, getToken())) {
      tokenDynamic = ptokenDynamic.substring(getToken().length());
    } else {
      tokenDynamic = ptokenDynamic;
    }
  }

  @Override
  public final NavigationEntryInterface getParentEntry() {
    return parentEntry;
  }

  @Override
  public final void setParentEntry(final NavigationEntryInterface pparentEntry) {
    parentEntry = pparentEntry;
  }

  @Override
  public final boolean isOpenOnStartup() {
    return openOnStartup;
  }

  @Override
  public final boolean canReveal() {
    return proxyPlace.canReveal();
  }

  @Override
  public final int hashCode() {
    return Objects.hash(menuValue, proxyPlace);
  }

  @Override
  public final boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    final NavigationEntryProxy other = (NavigationEntryProxy) obj;
    return Objects.equals(menuValue, other.menuValue)
        && Objects.equals(proxyPlace, other.proxyPlace);
  }
}
