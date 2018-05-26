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

package de.knightsoftnet.mtwidgets.client.ui.widget.helper;

import java.util.Objects;

/**
 * helper bean for sorting entries with icon.
 *
 * @author Manfred Tremmel
 *
 */
public class IdAndNamePlusIconBean<T> extends IdAndNameBean<T> {

  private final String iconUrl;

  /**
   * constructor initializing fields.
   *
   * @param pid id of the entry
   * @param pname name of the entry
   * @param piconUrl icon url
   */
  public IdAndNamePlusIconBean(final T pid, final String pname, final String piconUrl) {
    super(pid, pname);
    this.iconUrl = piconUrl;
  }

  public final String getIconUrl() {
    return this.iconUrl;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.getId());
  }

  @Override
  public boolean equals(final Object pobj) {
    if (this == pobj) {
      return true;
    }
    if (pobj == null) {
      return false;
    }
    if (this.getClass() != pobj.getClass()) {
      return false;
    }
    final IdAndNameBean<?> other = (IdAndNameBean<?>) pobj;
    return Objects.equals(this.getId(), other.getId());
  }
}
