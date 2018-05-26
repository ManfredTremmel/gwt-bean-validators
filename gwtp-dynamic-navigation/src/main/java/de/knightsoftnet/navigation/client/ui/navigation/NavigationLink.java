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
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.user.client.ui.Anchor;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;

import java.util.Objects;

/**
 * The <code>NavigationLink</code> defines one menu entry.
 *
 * @author Manfred Tremmel
 */
public class NavigationLink implements NavigationEntryInterface {
  /**
   * entry for the menu, this one my contain text or maybe even pictures.
   */
  private final SafeHtml menuValue;

  /**
   * the url to link to.
   */
  private final SafeUri url;

  /**
   * target place to link to.
   */
  private final String target;

  /**
   * gate keeper to check if user is allowed to see entry.
   */
  private final Gatekeeper gatekeeper;

  /**
   * parent entry of this entry (null if we are on top level).
   */
  private NavigationEntryInterface parentEntry;

  /**
   * constructor for menu entries.
   *
   * @param pmenuValue menu value
   * @param purl url to set
   * @param pgatekeeper gate keeper to set
   */
  public NavigationLink(final SafeHtml pmenuValue, final SafeUri purl,
      final Gatekeeper pgatekeeper) {
    this(pmenuValue, purl, "_blank", pgatekeeper);
  }

  /**
   * constructor for menu entries.
   *
   * @param pmenuValue menu value
   * @param purl url to set
   * @param pgatekeeper gate keeper to set
   */
  public NavigationLink(final SafeHtml pmenuValue, final SafeUri purl, final String ptarget,
      final Gatekeeper pgatekeeper) {
    super();
    this.menuValue = pmenuValue;
    this.url = purl;
    this.target = ptarget;
    this.gatekeeper = pgatekeeper;
    this.parentEntry = null;
  }

  @Override
  public final SafeHtml getMenuValue() {
    return this.menuValue;
  }

  @Override
  public final String getToken() {
    return this.url.asString();
  }

  @Override
  public final String getFullToken() {
    return this.getToken();
  }

  @Override
  public final String getTokenDynamic() {
    return null;
  }

  @Override
  public final void setTokenDynamic(final String ptokenDynamic) {
    // we don't need it
  }

  public String getTarget() {
    return this.target;
  }

  public Anchor getAnchor() {
    return new Anchor(this.menuValue, this.url, this.target);
  }

  @Override
  public final NavigationEntryInterface getParentEntry() {
    return this.parentEntry;
  }

  @Override
  public final void setParentEntry(final NavigationEntryInterface pparentEntry) {
    this.parentEntry = pparentEntry;
  }

  @Override
  public final boolean isOpenOnStartup() {
    return true;
  }

  @Override
  public final boolean canReveal() {
    return this.gatekeeper == null || this.gatekeeper.canReveal();
  }

  @Override
  public final int hashCode() {
    return Objects.hash(this.menuValue, this.url);
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
    final NavigationLink other = (NavigationLink) obj;
    return Objects.equals(this.menuValue, other.menuValue) && Objects.equals(this.url, other.url);
  }

}
