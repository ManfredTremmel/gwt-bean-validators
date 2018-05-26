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
 * helper bean for sorting entries.
 *
 * @author Manfred Tremmel
 *
 */
public class IdAndNameBean<T> {
  private final T id;
  private final String name;

  /**
   * constructor initializing fields.
   *
   * @param pid id of the entry
   * @param pname name of the entry
   */
  public IdAndNameBean(final T pid, final String pname) {
    super();
    this.id = pid;
    this.name = pname;
  }

  public final T getId() {
    return this.id;
  }

  public final String getName() {
    return this.name;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.id);
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
    return Objects.equals(this.id, other.id);
  }
}
