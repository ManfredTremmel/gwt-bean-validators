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

package de.knightsoftnet.validators.shared.beans;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.constraints.Size;

public class HibernateSizeCollectionTestBean {

  @Size(min = 5, max = 20)
  private final Collection<String> value;

  /**
   * constructor.
   *
   * @param psize size of the collection to create.
   */
  public HibernateSizeCollectionTestBean(final int psize) {
    super();
    this.value = new ArrayList<>(psize);
    for (int i = 0; i < psize; i++) {
      this.value.add(Integer.toString(i));
    }
  }

  public final Collection<String> getValue() {
    return this.value;
  }

  @Override
  public String toString() {
    return "HibernateSizeCollectionTestBean [value=" + this.value + "]";
  }
}
