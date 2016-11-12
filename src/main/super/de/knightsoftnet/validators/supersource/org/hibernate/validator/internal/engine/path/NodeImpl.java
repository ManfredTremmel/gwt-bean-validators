/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package org.hibernate.validator.internal.engine.path;

import org.hibernate.validator.internal.util.Contracts;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.validation.ElementKind;
import javax.validation.Path;

/**
 * Immutable implementation of a {@code Path.Node}.
 *
 * @author Hardy Ferentschik
 * @author Gunnar Morling
 * @author Manfred Tremmel (gwt port)
 */
public class NodeImpl implements Path.PropertyNode, Path.MethodNode, Path.ConstructorNode,
    Path.BeanNode, Path.ParameterNode, Path.ReturnValueNode, Path.CrossParameterNode,
    org.hibernate.validator.path.PropertyNode, Serializable {
  private static final long serialVersionUID = 2075466571633860499L;
  private static final Class<?>[] EMPTY_CLASS_ARRAY = new Class<?>[] {};

  private static final String INDEX_OPEN = "[";
  private static final String INDEX_CLOSE = "]";
  private static final String RETURN_VALUE_NODE_NAME = "<return value>";
  private static final String CROSS_PARAMETER_NODE_NAME = "<cross-parameter>";
  private static final String COLLECTION_ELEMENT_NODE_NAME = "<collection element>";

  private final String name;
  private final NodeImpl parent;
  private final boolean isIterableValue;
  private final Integer index;
  private final Object key;
  private final ElementKind kind;
  private final int hashCodeValue;

  // type-specific attributes
  private final Class<?>[] parameterTypes;
  private final Integer parameterIndex;
  private final Object value;

  private String valueAsString;

  protected NodeImpl(final String name, final NodeImpl parent, final boolean indexable,
      final Integer index, final Object key, final ElementKind kind,
      final Class<?>[] parameterTypes, final Integer parameterIndex, final Object value) {
    this.name = name;
    this.parent = parent;
    this.index = index;
    this.key = key;
    this.value = value;
    this.isIterableValue = indexable;
    this.kind = kind;
    this.parameterTypes = parameterTypes;
    this.parameterIndex = parameterIndex;
    this.hashCodeValue = this.buildHashCode();
  }

  // TODO It would be nicer if we could return PropertyNode
  public static NodeImpl createPropertyNode(final String name, final NodeImpl parent) {
    return new NodeImpl(name, parent, false, null, null, ElementKind.PROPERTY, EMPTY_CLASS_ARRAY,
        null, null);
  }

  public static NodeImpl createCollectionElementNode(final NodeImpl parent) {
    return new NodeImpl(COLLECTION_ELEMENT_NODE_NAME, parent, false, null, null,
        ElementKind.PROPERTY, EMPTY_CLASS_ARRAY, null, null);
  }

  public static NodeImpl createParameterNode(final String name, final NodeImpl parent,
      final int parameterIndex) {
    return new NodeImpl(name, parent, false, null, null, ElementKind.PARAMETER, EMPTY_CLASS_ARRAY,
        parameterIndex, null);
  }

  public static NodeImpl createCrossParameterNode(final NodeImpl parent) {
    return new NodeImpl(CROSS_PARAMETER_NODE_NAME, parent, false, null, null,
        ElementKind.CROSS_PARAMETER, EMPTY_CLASS_ARRAY, null, null);
  }

  public static NodeImpl createMethodNode(final String name, final NodeImpl parent,
      final Class<?>[] parameterTypes) {
    return new NodeImpl(name, parent, false, null, null, ElementKind.METHOD, parameterTypes, null,
        null);
  }

  public static NodeImpl createConstructorNode(final String name, final NodeImpl parent,
      final Class<?>[] parameterTypes) {
    return new NodeImpl(name, parent, false, null, null, ElementKind.CONSTRUCTOR, parameterTypes,
        null, null);
  }

  public static NodeImpl createBeanNode(final NodeImpl parent) {
    return new NodeImpl(null, parent, false, null, null, ElementKind.BEAN, EMPTY_CLASS_ARRAY, null,
        null);
  }

  public static NodeImpl createReturnValue(final NodeImpl parent) {
    return new NodeImpl(RETURN_VALUE_NODE_NAME, parent, false, null, null, ElementKind.RETURN_VALUE,
        EMPTY_CLASS_ARRAY, null, null);
  }

  /**
   * make it iterable.
   *
   * @param node node to build
   * @return iterable node implementation
   */
  public static NodeImpl makeIterable(final NodeImpl node) {
    return new NodeImpl(node.name, node.parent, true, null, null, node.kind, node.parameterTypes,
        node.parameterIndex, node.value

    );
  }

  public static NodeImpl setIndex(final NodeImpl node, final Integer index) {
    return new NodeImpl(node.name, node.parent, true, index, null, node.kind, node.parameterTypes,
        node.parameterIndex, node.value);
  }

  public static NodeImpl setMapKey(final NodeImpl node, final Object key) {
    return new NodeImpl(node.name, node.parent, true, null, key, node.kind, node.parameterTypes,
        node.parameterIndex, node.value);
  }

  public static NodeImpl setPropertyValue(final NodeImpl node, final Object value) {
    return new NodeImpl(node.name, node.parent, node.isIterableValue, node.index, node.key,
        node.kind, node.parameterTypes, node.parameterIndex, value);
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public boolean isInIterable() {
    return this.parent != null && this.parent.isIterable();
  }

  public boolean isIterable() {
    return this.isIterableValue;
  }

  @Override
  public Integer getIndex() {
    if (this.parent == null) {
      return null;
    } else {
      return this.parent.index;
    }
  }

  @Override
  public Object getKey() {
    if (this.parent == null) {
      return null;
    } else {
      return this.parent.key;
    }
  }

  public NodeImpl getParent() {
    return this.parent;
  }

  @Override
  public ElementKind getKind() {
    return this.kind;
  }

  @Override
  public <T extends Path.Node> T as(final Class<T> nodeType) { // NOPMD
    throw new UnsupportedOperationException("GWT Validation does not support as(Class<T>)");
  }

  @Override
  public List<Class<?>> getParameterTypes() {
    return Arrays.asList(this.parameterTypes);
  }

  @Override
  public int getParameterIndex() {
    Contracts.assertTrue(this.kind == ElementKind.PARAMETER,
        "getParameterIndex() may only be invoked for nodes of ElementKind.PARAMETER.");
    return this.parameterIndex.intValue();
  }

  @Override
  public Object getValue() {
    return this.value;
  }

  @Override
  public String toString() {
    return this.asString();
  }

  /**
   * create value as to string.
   *
   * @return built value as string
   */
  public String asString() {
    if (this.valueAsString == null) {
      this.valueAsString = this.buildToString();
    }
    return this.valueAsString;
  }

  private String buildToString() {
    final StringBuilder builder = new StringBuilder();

    if (ElementKind.BEAN.equals(this.getKind())) {
      // class level constraints don't contribute to path
      builder.append("");
    } else {
      builder.append(this.getName());
    }

    if (this.isIterable()) {
      builder.append(INDEX_OPEN);
      if (this.index == null) {
        if (this.key != null) {
          builder.append(this.key);
        }
      } else {
        builder.append(this.index);
      }
      builder.append(INDEX_CLOSE);
    }
    return builder.toString();
  }

  public final int buildHashCode() {
    return Objects.hash(this.index, this.isIterableValue, this.key, this.kind, this.name,
        this.parameterIndex, this.parameterTypes, this.parent);
  }

  @Override
  public int hashCode() {
    return this.hashCodeValue;
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
    final NodeImpl other = (NodeImpl) obj;
    return Objects.equals(this.index, other.index) //
        && this.isIterableValue == other.isIterableValue //
        && Objects.equals(this.key, other.key) //
        && Objects.equals(this.kind, other.kind) //
        && Objects.equals(this.name, other.name) //
        && Objects.equals(this.parameterIndex, other.parameterIndex) //
        && Arrays.equals(this.parameterTypes, other.parameterTypes) //
        && Objects.equals(this.parent, other.parent);
  }
}
