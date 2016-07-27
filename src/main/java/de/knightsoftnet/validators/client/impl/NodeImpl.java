/*
 * Copyright 2010 Google Inc. Copyright 2016 Manfred Tremmel
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.knightsoftnet.validators.client.impl;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.Path.Node;

/**
 * An immutable GWT safe implementation of {@link Node}.
 */
final class NodeImpl implements Node, Serializable {

  private static final long serialVersionUID = 1L;
  public static final Node ROOT_NODE = new NodeImpl(null, null, null, false);

  static Node createIndexedNode(final String name, final Integer index) {
    return new NodeImpl(name, null, index, true);
  }

  static Node createIterableNode(final String name) {
    return new NodeImpl(name, null, null, true);
  }

  static Node createKeyedNode(final String name, final Object key) {
    return new NodeImpl(name, key, null, true);
  }

  static Node createNode(final String name) {
    return new NodeImpl(name, null, null, false);
  }

  private final boolean inIterable;
  private final String name;
  private final Integer index;

  private final Object key;

  private NodeImpl(final String name, final Object key, final Integer index,
      final boolean iterable) {
    this.name = name;
    this.key = key;
    this.index = index;
    this.inIterable = iterable;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof NodeImpl)) {
      return false;
    }
    final NodeImpl that = (NodeImpl) obj;
    return StringUtils.equals(this.name, that.name) //
        && Objects.equals(this.index, that.index) //
        && Objects.equals(this.key, that.key) //
        && this.inIterable == that.inIterable;
  }

  @Override
  public Integer getIndex() {
    return this.index;
  }

  @Override
  public Object getKey() {
    return this.key;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.index, this.key, this.name, this.inIterable);
  }

  @Override
  public boolean isInIterable() {
    return this.inIterable;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    if (this.name != null) {
      sb.append(this.name);
    }
    if (this.isInIterable()) {
      sb.append('[');
      if (this.key == null) {
        if (this.index != null) {
          sb.append(this.index);
        }
      } else {
        sb.append(this.key);
      }
      sb.append(']');
    }
    return sb.toString();
  }
}
