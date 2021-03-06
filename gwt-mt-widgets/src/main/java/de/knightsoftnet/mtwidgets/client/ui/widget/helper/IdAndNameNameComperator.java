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

import java.text.Collator;
import java.util.Comparator;
import java.util.Objects;

public class IdAndNameNameComperator<T extends Comparable<T>>
    implements Comparator<IdAndNameBean<T>> {

  private final Collator collator;

  /**
   * default constructor.
   */
  public IdAndNameNameComperator() {
    super();
    this.collator = Collator.getInstance();
  }

  @Override
  public int compare(final IdAndNameBean<T> pfirst, final IdAndNameBean<T> psecond) {
    if (Objects.equals(pfirst, psecond)) {
      return 0;
    }
    if (pfirst == null || pfirst.getName() == null) {
      return -1;
    }
    if (psecond == null || psecond.getName() == null) {
      return 1;
    }

    return this.collator.compare(pfirst.getName(), psecond.getName());
  }
}
