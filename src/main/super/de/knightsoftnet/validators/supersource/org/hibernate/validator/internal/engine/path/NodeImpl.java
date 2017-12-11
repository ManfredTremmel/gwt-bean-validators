/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package org.hibernate.validator.internal.engine.path;

import org.hibernate.validator.internal.util.Contracts;
import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.validation.ElementKind;
import javax.validation.Path;
import javax.validation.Path.BeanNode;
import javax.validation.Path.ConstructorNode;
import javax.validation.Path.ContainerElementNode;
import javax.validation.Path.CrossParameterNode;
import javax.validation.Path.MethodNode;
import javax.validation.Path.ParameterNode;
import javax.validation.Path.PropertyNode;
import javax.validation.Path.ReturnValueNode;

/**
 * Immutable implementation of a {@code Path.Node}.
 *
 * @author Hardy Ferentschik
 * @author Gunnar Morling
 * @author Manfred Tremmel (gwt port)
 */
public class NodeImpl implements Path.PropertyNode, Path.MethodNode, Path.ConstructorNode,
    Path.BeanNode, Path.ParameterNode, Path.ReturnValueNode, Path.CrossParameterNode,
    Path.ContainerElementNode, org.hibernate.validator.path.PropertyNode,
    org.hibernate.validator.path.ContainerElementNode, Serializable {
  private static final long serialVersionUID = 2075466571633860499L;
  private static final Class<?>[] EMPTY_CLASS_ARRAY = new Class<?>[] {};

  private static final Log LOG = LoggerFactory.make(MethodHandles.lookup()); // NOPMD

  private static final String INDEX_OPEN = "[";
  private static final String INDEX_CLOSE = "]";
  private static final String TYPE_PARAMETER_OPEN = "<";
  private static final String TYPE_PARAMETER_CLOSE = ">";

  public static final String RETURN_VALUE_NODE_NAME = "<return value>";
  public static final String CROSS_PARAMETER_NODE_NAME = "<cross-parameter>";
  public static final String ITERABLE_ELEMENT_NODE_NAME = "<iterable element>";
  public static final String LIST_ELEMENT_NODE_NAME = "<list element>";
  public static final String MAP_KEY_NODE_NAME = "<map key>";
  public static final String MAP_VALUE_NODE_NAME = "<map value>";

  private final String name;
  private final NodeImpl parent;
  private final boolean isIterableValue;
  private final Integer index;
  private final Object key;
  private final ElementKind kind;

  // type-specific attributes
  private final Class<?>[] parameterTypes;
  private final Integer parameterIndex;
  private final Object value;
  private final Class<?> containerClass;
  private final Integer typeArgumentIndex;

  private int hashCodeValue = -1;
  private String valueAsString;

  /**
   * constructor, do not use.
   */
  public NodeImpl(final String name, final NodeImpl parent, final boolean isIterable,
      final Integer index, final Object key, final ElementKind kind,
      final Class<?>[] parameterTypes, final Integer parameterIndex, final Object value,
      final Class<?> containerClass, final Integer typeArgumentIndex) {
    this.name = name;
    this.parent = parent;
    this.index = index;
    this.key = key;
    this.value = value;
    this.isIterableValue = isIterable;
    this.kind = kind;
    this.parameterTypes = parameterTypes;
    this.parameterIndex = parameterIndex;
    this.containerClass = containerClass;
    this.typeArgumentIndex = typeArgumentIndex;
  }

  /**
   * create property node.
   *
   * @param name name of the node
   * @param parent parent node
   * @return new node implementation
   */
  // TODO It would be nicer if we could return PropertyNode
  public static NodeImpl createPropertyNode(final String name, final NodeImpl parent) {
    return new NodeImpl( //
        name, //
        parent, //
        false, //
        null, //
        null, //
        ElementKind.PROPERTY, //
        EMPTY_CLASS_ARRAY, //
        null, //
        null, //
        null, //
        null //
    );
  }

  /**
   * create container element node.
   *
   * @param name name of the node
   * @param parent parent node
   * @return new node implementation
   */
  public static NodeImpl createContainerElementNode(final String name, final NodeImpl parent) {
    return new NodeImpl( //
        name, //
        parent, //
        false, //
        null, //
        null, //
        ElementKind.CONTAINER_ELEMENT, //
        EMPTY_CLASS_ARRAY, //
        null, //
        null, //
        null, //
        null //
    );
  }

  /**
   * create parameter node.
   *
   * @param name name of the node
   * @param parent parent node
   * @return new node implementation
   */
  public static NodeImpl createParameterNode(final String name, final NodeImpl parent,
      final int parameterIndex) {
    return new NodeImpl( //
        name, //
        parent, //
        false, //
        null, //
        null, //
        ElementKind.PARAMETER, //
        EMPTY_CLASS_ARRAY, //
        parameterIndex, //
        null, //
        null, //
        null //
    );
  }

  /**
   * create cross parameter node node.
   *
   * @param parent parent node
   * @return new node implementation
   */
  public static NodeImpl createCrossParameterNode(final NodeImpl parent) {
    return new NodeImpl( //
        CROSS_PARAMETER_NODE_NAME, //
        parent, //
        false, //
        null, //
        null, //
        ElementKind.CROSS_PARAMETER, //
        EMPTY_CLASS_ARRAY, //
        null, //
        null, //
        null, //
        null //
    );
  }

  /**
   * create method node.
   *
   * @param name name of the node
   * @param parent parent node
   * @param parameterTypes parameter types
   * @return new node implementation
   */
  public static NodeImpl createMethodNode(final String name, final NodeImpl parent,
      final Class<?>[] parameterTypes) {
    return new NodeImpl( //
        name, //
        parent, //
        false, //
        null, //
        null, //
        ElementKind.METHOD, //
        parameterTypes, //
        null, //
        null, //
        null, //
        null //
    );
  }

  /**
   * create constructor node.
   *
   * @param name name of the node
   * @param parent parent node
   * @param parameterTypes parameter types
   * @return new node implementation
   */
  public static NodeImpl createConstructorNode(final String name, final NodeImpl parent,
      final Class<?>[] parameterTypes) {
    return new NodeImpl( //
        name, //
        parent, //
        false, //
        null, //
        null, //
        ElementKind.CONSTRUCTOR, //
        parameterTypes, //
        null, //
        null, //
        null, //
        null //
    );
  }

  /**
   * create bean node.
   *
   * @param parent parent node
   * @return new node implementation
   */
  public static NodeImpl createBeanNode(final NodeImpl parent) {
    return new NodeImpl( //
        null, //
        parent, //
        false, //
        null, //
        null, //
        ElementKind.BEAN, EMPTY_CLASS_ARRAY, //
        null, //
        null, //
        null, //
        null //
    );
  }

  /**
   * create return node.
   *
   * @param parent parent node
   * @return new node implementation
   */
  public static NodeImpl createReturnValue(final NodeImpl parent) {
    return new NodeImpl( //
        RETURN_VALUE_NODE_NAME, //
        parent, //
        false, //
        null, //
        null, //
        ElementKind.RETURN_VALUE, //
        EMPTY_CLASS_ARRAY, //
        null, //
        null, //
        null, //
        null //
    );
  }

  /**
   * make it iterable.
   *
   * @param node node to build
   * @return iterable node implementation
   */
  public static NodeImpl makeIterable(final NodeImpl node) {
    return new NodeImpl( //
        node.name, //
        node.parent, //
        true, //
        null, //
        null, //
        node.kind, //
        node.parameterTypes, //
        node.parameterIndex, //
        node.value, //
        node.containerClass, //
        node.typeArgumentIndex //
    );
  }

  /**
   * make it iterable and set index.
   *
   * @param node node to build
   * @param index index to set
   * @return iterable node implementation
   */
  public static NodeImpl makeIterableAndSetIndex(final NodeImpl node, final Integer index) {
    return new NodeImpl( //
        node.name, //
        node.parent, //
        true, //
        index, //
        null, //
        node.kind, //
        node.parameterTypes, //
        node.parameterIndex, //
        node.value, //
        node.containerClass, //
        node.typeArgumentIndex //
    );
  }

  /**
   * make it iterable and set map key.
   *
   * @param node node to build
   * @param key map key to est
   * @return iterable node implementation
   */
  public static NodeImpl makeIterableAndSetMapKey(final NodeImpl node, final Object key) {
    return new NodeImpl( //
        node.name, //
        node.parent, //
        true, //
        null, //
        key, //
        node.kind, //
        node.parameterTypes, //
        node.parameterIndex, //
        node.value, //
        node.containerClass, //
        node.typeArgumentIndex //
    );
  }

  /**
   * set property value to a copied node.
   *
   * @param node node to build
   * @param value value to set
   * @return new created node implementation
   */
  public static NodeImpl setPropertyValue(final NodeImpl node, final Object value) {
    return new NodeImpl( //
        node.name, //
        node.parent, //
        node.isIterableValue, //
        node.index, //
        node.key, //
        node.kind, //
        node.parameterTypes, //
        node.parameterIndex, //
        value, //
        node.containerClass, //
        node.typeArgumentIndex //
    );
  }

  /**
   * set type parameter to a copied node.
   *
   * @param node node to build
   * @param containerClass container class to set
   * @param typeArgumentIndex type argument index to set
   * @return new created node implementation
   */
  public static NodeImpl setTypeParameter(final NodeImpl node, final Class<?> containerClass,
      final Integer typeArgumentIndex) {
    return new NodeImpl( //
        node.name, //
        node.parent, //
        node.isIterableValue, //
        node.index, //
        node.key, //
        node.kind, //
        node.parameterTypes, //
        node.parameterIndex, //
        node.value, //
        containerClass, //
        typeArgumentIndex //
    );
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

  @Override
  public Class<?> getContainerClass() {
    Contracts.assertTrue(
        this.kind == ElementKind.BEAN || this.kind == ElementKind.PROPERTY
            || this.kind == ElementKind.CONTAINER_ELEMENT,
        "getContainerClass() may only be invoked for nodes of type ElementKind.BEAN, "
            + "ElementKind.PROPERTY or ElementKind.CONTAINER_ELEMENT.");
    if (this.parent == null) {
      return null;
    }
    return this.parent.containerClass;
  }

  @Override
  public Integer getTypeArgumentIndex() {
    Contracts.assertTrue(
        this.kind == ElementKind.BEAN || this.kind == ElementKind.PROPERTY
            || this.kind == ElementKind.CONTAINER_ELEMENT,
        "getTypeArgumentIndex() may only be invoked for nodes of type ElementKind.BEAN, "
            + "ElementKind.PROPERTY or ElementKind.CONTAINER_ELEMENT.");
    if (this.parent == null) {
      return null;
    }
    return this.parent.typeArgumentIndex;
  }

  public NodeImpl getParent() {
    return this.parent;
  }

  @Override
  public ElementKind getKind() {
    return this.kind;
  }

  @Override
  public <T extends Path.Node> T as(final Class<T> nodeType) throws ClassCastException { // NOPMD
    if (this.kind == ElementKind.BEAN && nodeType == BeanNode.class
        || this.kind == ElementKind.CONSTRUCTOR && nodeType == ConstructorNode.class
        || this.kind == ElementKind.CROSS_PARAMETER && nodeType == CrossParameterNode.class
        || this.kind == ElementKind.METHOD && nodeType == MethodNode.class
        || this.kind == ElementKind.PARAMETER && nodeType == ParameterNode.class
        || this.kind == ElementKind.PROPERTY && (nodeType == PropertyNode.class
            || nodeType == org.hibernate.validator.path.PropertyNode.class)
        || this.kind == ElementKind.RETURN_VALUE && nodeType == ReturnValueNode.class
        || this.kind == ElementKind.CONTAINER_ELEMENT && (nodeType == ContainerElementNode.class
            || nodeType == org.hibernate.validator.path.ContainerElementNode.class)) {
      return (T) this;
    }

    throw LOG.getUnableToNarrowNodeTypeException(this.getClass(), this.kind, nodeType);
  }

  @Override
  public List<Class<?>> getParameterTypes() {
    return Arrays.asList(this.parameterTypes);
  }

  @Override
  public int getParameterIndex() {
    Contracts.assertTrue(this.kind == ElementKind.PARAMETER,
        "getParameterIndex() may only be invoked for nodes of type ElementKind.PARAMETER.");
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

    if (this.getName() != null) {
      builder.append(this.getName());
    }

    if (includeTypeParameterInformation(this.containerClass, this.typeArgumentIndex)) {
      builder.append(TYPE_PARAMETER_OPEN);
      builder.append('?');
      builder.append(TYPE_PARAMETER_CLOSE);
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

  // TODO: this is used to reduce the number of differences until we agree on the string
  // representation
  // it introduces some inconsistent behavior e.g. you get '<V>' for a Multimap but not for a Map
  private static boolean includeTypeParameterInformation(final Class<?> containerClass,
      final Integer typeArgumentIndex) {
    if (containerClass == null || typeArgumentIndex == null) {
      return false;
    }

    // if (containerClass.getTypeParameters().length < 2) {
    // return false;
    // }
    // return !(Map.class.isAssignableFrom(containerClass) && typeArgumentIndex == 1);
    throw new UnsupportedOperationException("GWT does not support getTypeArgumentIndex().");
  }

  /**
   * build hash code.
   *
   * @return created hash code
   */
  public final int buildHashCode() {
    return Objects.hash(this.index, this.isIterableValue, this.key, this.kind, this.name,
        this.parameterIndex, this.parameterTypes, this.parent, this.containerClass,
        this.typeArgumentIndex);
  }

  @Override
  public int hashCode() {
    if (this.hashCodeValue == -1) {
      this.hashCodeValue = this.buildHashCode();
    }

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
        && Objects.equals(this.parent, other.parent)
        && Objects.equals(this.containerClass, other.containerClass)
        && Objects.equals(this.typeArgumentIndex, other.typeArgumentIndex);
  }
}
