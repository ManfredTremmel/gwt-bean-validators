/*
 * Hibernate pathImpl.
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package org.hibernate.validator.internal.engine.path;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;

import org.hibernate.validator.internal.util.Contracts;
import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.validation.ElementKind;
import javax.validation.Path;

/**
 * Default implementation of {@code javax.validation.Path}.
 *
 * @author Hardy Ferentschik
 * @author Gunnar Morling
 * @author Kevin Pollet &lt;kevin.pollet@serli.com&gt; (C) 2011 SERLI
 * @author Manfred Tremmel ported to gwt
 */
@SuppressWarnings("checkstyle:javadocmethod")
public class PathImpl implements Path, Serializable {
  private static final long serialVersionUID = 7564511574909882392L;
  private static final Log LOG = LoggerFactory.make(MethodHandles.lookup()); // NOPMD

  private static final String PROPERTY_PATH_SEPARATOR = ".";
  private static final int[] A = new int[256];

  /**
   * Regular expression used to split a string path into its elements.
   *
   * @see <a href="http://www.regexplanet.com/simple/index.jsp">Regular expression tester</a>
   */
  private static final String LEADING_PROPERTY_GROUP = "[^\\[\\.]+"; // everything up to a [ or .
  private static final String OPTIONAL_INDEX_GROUP = "\\[(\\w*)\\]";
  private static final String REMAINING_PROPERTY_STRING = "\\.(.*)"; // processed recursively

  private static final RegExp PATH_PATTERN = RegExp.compile("(" + LEADING_PROPERTY_GROUP + ")("
      + OPTIONAL_INDEX_GROUP + ")?(" + REMAINING_PROPERTY_STRING + ")*");
  private static final int PROPERTY_NAME_GROUP = 1;
  private static final int INDEXED_GROUP = 2;
  private static final int INDEX_GROUP = 3;
  private static final int REMAINING_STRING_GROUP = 5;

  private List<Node> nodeList;
  private boolean nodeListRequiresCopy;
  private NodeImpl currentLeafNode;
  private int hashCodeEntry;

  /**
   * Returns a {@code Path} instance representing the path described by the given string. To create
   * a root node the empty string should be passed.
   *
   * @param propertyPath the path as string representation.
   *
   * @return a {@code Path} instance representing the path described by the given string.
   *
   * @throws IllegalArgumentException in case {@code property == null} or {@code property} cannot be
   *         parsed.
   */
  public static PathImpl createPathFromString(final String propertyPath) {
    if (propertyPath == null) {
      throw new IllegalArgumentException("parameter propertyPath must not be null");
    }

    if (propertyPath.length() == 0) {
      return createRootPath();
    }

    return parseProperty(propertyPath);
  }

  // public static PathImpl createPathForExecutable(final ExecutableMetaData executable) {
  // Contracts.assertNotNull(executable,
  // "A method is required to create a method return value path.");
  //
  // final PathImpl path = createRootPath();
  //
  // if (executable.getKind() == ElementKind.CONSTRUCTOR) {
  // path.addConstructorNode(executable.getName(), executable.getParameterTypes());
  // } else {
  // path.addMethodNode(executable.getName(), executable.getParameterTypes());
  // }
  //
  // return path;
  // }

  public static PathImpl createRootPath() {
    final PathImpl path = new PathImpl();
    path.addBeanNode();
    return path;
  }

  public static PathImpl createCopy(final PathImpl path) {
    return new PathImpl(path);
  }

  public boolean isRootPath() {
    return this.nodeList.size() == 1 && this.nodeList.get(0).getName() == null;
  }

  public PathImpl getPathWithoutLeafNode() {
    return new PathImpl(this.nodeList.subList(0, this.nodeList.size() - 1));
  }

  public NodeImpl addPropertyNode(final String nodeName) {
    this.requiresWriteableNodeList();

    final NodeImpl parent = this.currentLeafNode;
    this.currentLeafNode = NodeImpl.createPropertyNode(nodeName, parent);
    this.nodeList.add(this.currentLeafNode);
    this.resetHashCode();
    return this.currentLeafNode;
  }

  public NodeImpl addContainerElementNode(final String nodeName) {
    this.requiresWriteableNodeList();

    final NodeImpl parent = this.currentLeafNode;
    this.currentLeafNode = NodeImpl.createContainerElementNode(nodeName, parent);
    this.nodeList.add(this.currentLeafNode);
    this.resetHashCode();
    return this.currentLeafNode;
  }

  public NodeImpl addParameterNode(final String nodeName, final int index) {
    this.requiresWriteableNodeList();

    final NodeImpl parent = this.currentLeafNode;
    this.currentLeafNode = NodeImpl.createParameterNode(nodeName, parent, index);
    this.nodeList.add(this.currentLeafNode);
    this.resetHashCode();
    return this.currentLeafNode;
  }

  public NodeImpl addCrossParameterNode() {
    this.requiresWriteableNodeList();

    final NodeImpl parent = this.currentLeafNode;
    this.currentLeafNode = NodeImpl.createCrossParameterNode(parent);
    this.nodeList.add(this.currentLeafNode);
    this.resetHashCode();
    return this.currentLeafNode;
  }

  public NodeImpl addBeanNode() {
    this.requiresWriteableNodeList();

    final NodeImpl parent = this.currentLeafNode;
    this.currentLeafNode = NodeImpl.createBeanNode(parent);
    this.nodeList.add(this.currentLeafNode);
    this.resetHashCode();
    return this.currentLeafNode;
  }

  public NodeImpl addReturnValueNode() {
    this.requiresWriteableNodeList();

    final NodeImpl parent = this.currentLeafNode;
    this.currentLeafNode = NodeImpl.createReturnValue(parent);
    this.nodeList.add(this.currentLeafNode);
    this.resetHashCode();
    return this.currentLeafNode;
  }

  public NodeImpl makeLeafNodeIterable() {
    this.requiresWriteableNodeList();

    this.currentLeafNode = NodeImpl.makeIterable(this.currentLeafNode);

    this.nodeList.set(this.nodeList.size() - 1, this.currentLeafNode);
    this.resetHashCode();
    return this.currentLeafNode;
  }

  public NodeImpl makeLeafNodeIterableAndSetIndex(final Integer index) {
    this.requiresWriteableNodeList();

    this.currentLeafNode = NodeImpl.makeIterableAndSetIndex(this.currentLeafNode, index);

    this.nodeList.set(this.nodeList.size() - 1, this.currentLeafNode);
    this.resetHashCode();
    return this.currentLeafNode;
  }

  public NodeImpl makeLeafNodeIterableAndSetMapKey(final Object key) {
    this.requiresWriteableNodeList();

    this.currentLeafNode = NodeImpl.makeIterableAndSetMapKey(this.currentLeafNode, key);

    this.nodeList.set(this.nodeList.size() - 1, this.currentLeafNode);
    this.resetHashCode();
    return this.currentLeafNode;
  }

  public NodeImpl setLeafNodeValueIfRequired(final Object value) {
    // The value is only exposed for property and container element nodes
    if (this.currentLeafNode.getKind() == ElementKind.PROPERTY
        || this.currentLeafNode.getKind() == ElementKind.CONTAINER_ELEMENT) {
      this.requiresWriteableNodeList();

      this.currentLeafNode = NodeImpl.setPropertyValue(this.currentLeafNode, value);

      this.nodeList.set(this.nodeList.size() - 1, this.currentLeafNode);

      // the property value is not part of the NodeImpl hashCode so we don't need to reset the
      // PathImpl hashCode
    }
    return this.currentLeafNode;
  }

  public NodeImpl setLeafNodeTypeParameter(final Class<?> containerClass,
      final Integer typeArgumentIndex) {
    this.requiresWriteableNodeList();

    this.currentLeafNode =
        NodeImpl.setTypeParameter(this.currentLeafNode, containerClass, typeArgumentIndex);

    this.nodeList.set(this.nodeList.size() - 1, this.currentLeafNode);
    this.resetHashCode();
    return this.currentLeafNode;
  }

  public void removeLeafNode() {
    if (!this.nodeList.isEmpty()) {
      this.requiresWriteableNodeList();

      this.nodeList.remove(this.nodeList.size() - 1);
      this.currentLeafNode =
          this.nodeList.isEmpty() ? null : (NodeImpl) this.nodeList.get(this.nodeList.size() - 1);
      this.resetHashCode();
    }
  }

  public NodeImpl getLeafNode() {
    return this.currentLeafNode;
  }

  @Override
  public Iterator<Path.Node> iterator() {
    if (this.nodeList.size() == 0) {
      return Collections.<Path.Node>emptyList().iterator();
    }
    if (this.nodeList.size() == 1) {
      return this.nodeList.iterator();
    }
    return this.nodeList.subList(1, this.nodeList.size()).iterator();
  }

  /**
   * serialize as string.
   *
   * @return string value of the node
   */
  public String asString() {
    final StringBuilder builder = new StringBuilder();
    boolean first = true;
    for (int i = 1; i < this.nodeList.size(); i++) {
      final NodeImpl nodeImpl = (NodeImpl) this.nodeList.get(i);
      final String name = nodeImpl.asString();
      if (name.isEmpty()) {
        // skip the node if it does not contribute to the string representation of the path, eg
        // class level constraints
        continue;
      }

      if (!first) {
        builder.append(PROPERTY_PATH_SEPARATOR);
      }

      builder.append(nodeImpl.asString());

      first = false;
    }
    return builder.toString();
  }

  private void requiresWriteableNodeList() {
    if (!this.nodeListRequiresCopy) {
      return;
    }

    // Usually, the write operation is about adding one more node, so let's make the list one
    // element larger.
    final List<Node> newNodeList = new ArrayList<>(this.nodeList.size() + 1);
    newNodeList.addAll(this.nodeList);
    this.nodeList = newNodeList;
    this.nodeListRequiresCopy = false;
  }

  @Override
  public String toString() {
    return this.asString();
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
    final PathImpl other = (PathImpl) obj;
    if (this.nodeList == null) {
      if (other.nodeList != null) {
        return false;
      }
    } else if (!this.nodeList.equals(other.nodeList)) {
      return false;
    }
    return true;
  }

  @Override
  // deferred hash code building
  public int hashCode() {
    if (this.hashCodeEntry == -1) {
      this.hashCodeEntry = this.buildHashCode();
    }

    return this.hashCodeEntry;
  }

  private int buildHashCode() {
    return Objects.hashCode(this.nodeList);
  }

  /**
   * Copy constructor.
   *
   * @param path the path to make a copy of.
   */
  private PathImpl(final PathImpl path) {
    this(path.nodeList);
    this.currentLeafNode = (NodeImpl) this.nodeList.get(this.nodeList.size() - 1);
  }

  protected PathImpl() {
    this.nodeList = new ArrayList<>(1);
    this.resetHashCode();
    this.nodeListRequiresCopy = false;
  }

  private PathImpl(final List<Node> nodeList) {
    this.nodeList = nodeList;
    this.currentLeafNode = (NodeImpl) nodeList.get(nodeList.size() - 1);
    this.resetHashCode();
    this.nodeListRequiresCopy = true;
  }

  private void resetHashCode() {
    this.hashCodeEntry = -1;
  }

  @SuppressWarnings("checkstyle:rightCurly")
  private static PathImpl parseProperty(final String propertyName) {
    final PathImpl path = createRootPath();
    String tmp = propertyName;
    do {
      final MatchResult matcher = PATH_PATTERN.exec(tmp);
      if (matcher == null) {
        throw new IllegalArgumentException("parameter propertyName is no valid path");
      } else {

        final String value = matcher.getGroup(PROPERTY_NAME_GROUP);
        if (!isValidJavaIdentifier(value)) {
          throw new IllegalArgumentException("parameter propertyName contains no valid name group");
        }

        // create the node
        path.addPropertyNode(value);

        // is the node indexable
        if (matcher.getGroup(INDEXED_GROUP) != null) {
          path.makeLeafNodeIterable();
        }

        // take care of the index/key if one exists
        final String indexOrKey = matcher.getGroup(INDEX_GROUP);
        if (indexOrKey != null && indexOrKey.length() > 0) {
          try {
            final Integer i = Integer.parseInt(indexOrKey);
            path.makeLeafNodeIterableAndSetIndex(i);
          } catch (final NumberFormatException e) {
            path.makeLeafNodeIterableAndSetMapKey(indexOrKey);
          }
        }

        // match the remaining string
        tmp = matcher.getGroup(REMAINING_STRING_GROUP);
      }
    } while (tmp != null);

    if (path.getLeafNode().isIterable()) {
      path.addBeanNode();
    }

    return path;
  }

  /**
   * Validate that the given identifier is a valid Java identifier according to the Java Language
   * Specification,
   * <a href="http://docs.oracle.com/javase/specs/jls/se6/html/lexical.html#3.8">chapter 3.8</a>
   *
   * @param identifier string identifier to validate
   *
   * @return true if the given identifier is a valid Java Identifier
   *
   * @throws IllegalArgumentException if the given identifier is {@code null}
   */
  private static boolean isValidJavaIdentifier(final String identifier) {
    Contracts.assertNotNull(identifier, "identifier param cannot be null");

    if (identifier.length() == 0 || !isJavaIdentifierStart(identifier.charAt(0))) {
      return false;
    }

    for (int i = 1; i < identifier.length(); i++) {
      if (!isJavaIdentifierPart(identifier.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  private static boolean isJavaIdentifierStart(final int ch) {
    final int prop = getProperties(ch);
    return (prop & 0x00007000) >= 0x00005000;
  }

  private static boolean isJavaIdentifierPart(final int ch) {
    final int prop = getProperties(ch);
    return (prop & 0x00003000) != 0;
  }

  private static int getProperties(final int var1) {
    final char var2 = (char) var1;
    return A[var2];
  }

  static {
    @SuppressWarnings("checkstyle:avoidEscapedUnicodeCharacters")
    final char[] var0 = ( //
    "䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ堀䀏倀䀏堀䀏怀䀏倀䀏䠀ဏ䠀ဏ" //
        + "䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ倀䀏倀䀏倀䀏堀䀏怀䀌栀" //
        + "\u0018栀\u0018⠀\u0018⠀怚⠀\u0018栀\u0018栀\u0018\ue800\u0015\ue800" //
        + "\u0016栀\u0018 \u0019㠀\u0018 \u0014㠀\u0018㠀\u0018᠀㘉᠀㘉᠀㘉᠀㘉᠀㘉" //
        + "᠀㘉᠀㘉᠀㘉᠀㘉᠀㘉㠀\u0018栀\u0018\ue800\u0019栀\u0019\ue800\u0019栀" //
        + "\u0018栀\u0018\u0082翡\u0082翡\u0082翡\u0082翡\u0082翡\u0082翡" //
        + "\u0082翡\u0082翡\u0082翡\u0082翡\u0082翡\u0082翡\u0082翡\u0082翡" //
        + "\u0082翡\u0082翡\u0082翡\u0082翡\u0082翡\u0082翡\u0082翡\u0082翡" //
        + "\u0082翡\u0082翡\u0082翡\u0082翡\ue800\u0015栀\u0018\ue800\u0016栀" //
        + "\u001b栀倗栀\u001b\u0081翢\u0081翢\u0081翢\u0081翢\u0081翢\u0081翢" //
        + "\u0081翢\u0081翢\u0081翢\u0081翢\u0081翢\u0081翢\u0081翢\u0081翢" //
        + "\u0081翢\u0081翢\u0081翢\u0081翢\u0081翢\u0081翢\u0081翢\u0081翢" //
        + "\u0081翢\u0081翢\u0081翢\u0081翢\ue800\u0015栀\u0019\ue800\u0016栀" //
        + "\u0019䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ倀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀" //
        + "ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ䠀ဏ㠀" //
        + "\f栀\u0018⠀怚⠀怚⠀怚⠀怚栀\u001c栀\u0018栀\u001b栀\u001c\u0000瀅\ue800" //
        + "\u001d栀\u0019䠀တ栀\u001c栀\u001b⠀\u001c⠀\u0019᠀؋᠀؋栀\u001b\u07fd瀂栀" //
        + "\u0018栀\u0018栀\u001b᠀ԋ\u0000瀅\ue800\u001e栀ࠋ栀ࠋ栀ࠋ栀\u0018\u0082瀁" //
        + "\u0082瀁\u0082瀁\u0082瀁\u0082瀁\u0082瀁\u0082瀁\u0082瀁\u0082瀁\u0082瀁" //
        + "\u0082瀁\u0082瀁\u0082瀁\u0082瀁\u0082瀁\u0082瀁\u0082瀁\u0082瀁\u0082瀁" //
        + "\u0082瀁\u0082瀁\u0082瀁\u0082瀁栀\u0019\u0082瀁\u0082瀁\u0082瀁\u0082瀁" //
        + "\u0082瀁\u0082瀁\u0082瀁\u07fd瀂\u0081瀂\u0081瀂\u0081瀂\u0081瀂\u0081瀂" //
        + "\u0081瀂\u0081瀂\u0081瀂\u0081瀂\u0081瀂\u0081瀂\u0081瀂\u0081瀂\u0081瀂" //
        + "\u0081瀂\u0081瀂\u0081瀂\u0081瀂\u0081瀂\u0081瀂\u0081瀂\u0081瀂\u0081瀂栀" //
        + "\u0019\u0081瀂\u0081瀂\u0081瀂\u0081瀂\u0081瀂\u0081瀂\u0081瀂\u061d瀂").toCharArray();
    assert var0.length == 512;

    int var1 = 0;

    int var3;
    for (int var2 = 0; var1 < 512; A[var2++] = var3 | var0[var1++]) {
      var3 = var0[var1++] << 16;
    }
  }
}
