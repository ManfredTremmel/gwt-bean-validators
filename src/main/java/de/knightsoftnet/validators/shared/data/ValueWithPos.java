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

package de.knightsoftnet.validators.shared.data;

import java.util.Objects;

/**
 * bean with value and cursor position.
 *
 * @author Manfred Tremmel
 *
 * @param <E> Type of value
 */
public class ValueWithPos<E> {
  private E value;
  private int pos;

  /**
   * default constructor.
   */
  public ValueWithPos() {
    super();
  }

  /**
   * constructor initializing fields.
   *
   * @param pvalue value entry
   * @param ppos cursor position
   */
  public ValueWithPos(final E pvalue, final int ppos) {
    super();
    this.value = pvalue;
    this.pos = ppos;
  }

  public final E getValue() {
    return this.value;
  }

  public final void setValue(final E pvalue) {
    this.value = pvalue;
  }

  public final int getPos() {
    return this.pos;
  }

  public final void setPos(final int ppos) {
    this.pos = ppos;
  }

  @Override
  public int hashCode() {
    return Objects.hash(Integer.valueOf(this.pos), this.value);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    @SuppressWarnings("unchecked")
    final ValueWithPos<E> other = (ValueWithPos<E>) obj;
    return this.pos == other.pos && Objects.equals(this.value, other.value);
  }

  @Override
  public String toString() {
    return "ValueWithPos [value=" + this.value + ", pos=" + this.pos + "]";
  }
}
