/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package org.hibernate.validator.internal.engine.path;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;

import org.hibernate.validator.internal.util.Contracts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

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

  private static final String PROPERTY_PATH_SEPARATOR = ".";

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

  private final List<Node> nodeList;
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
    final NodeImpl parent =
        this.nodeList.isEmpty() ? null : (NodeImpl) this.nodeList.get(this.nodeList.size() - 1);
    this.currentLeafNode = NodeImpl.createPropertyNode(nodeName, parent);
    this.nodeList.add(this.currentLeafNode);
    this.hashCodeEntry = -1;
    return this.currentLeafNode;
  }

  public NodeImpl addCollectionElementNode() {
    final NodeImpl parent =
        this.nodeList.isEmpty() ? null : (NodeImpl) this.nodeList.get(this.nodeList.size() - 1);
    this.currentLeafNode = NodeImpl.createCollectionElementNode(parent);
    this.nodeList.add(this.currentLeafNode);
    this.hashCodeEntry = -1;
    return this.currentLeafNode;
  }

  public NodeImpl addParameterNode(final String nodeName, final int index) {
    final NodeImpl parent =
        this.nodeList.isEmpty() ? null : (NodeImpl) this.nodeList.get(this.nodeList.size() - 1);
    this.currentLeafNode = NodeImpl.createParameterNode(nodeName, parent, index);
    this.nodeList.add(this.currentLeafNode);
    this.hashCodeEntry = -1;
    return this.currentLeafNode;
  }

  public NodeImpl addCrossParameterNode() {
    final NodeImpl parent =
        this.nodeList.isEmpty() ? null : (NodeImpl) this.nodeList.get(this.nodeList.size() - 1);
    this.currentLeafNode = NodeImpl.createCrossParameterNode(parent);
    this.nodeList.add(this.currentLeafNode);
    this.hashCodeEntry = -1;
    return this.currentLeafNode;
  }

  public NodeImpl addBeanNode() {
    final NodeImpl parent =
        this.nodeList.isEmpty() ? null : (NodeImpl) this.nodeList.get(this.nodeList.size() - 1);
    this.currentLeafNode = NodeImpl.createBeanNode(parent);
    this.nodeList.add(this.currentLeafNode);
    this.hashCodeEntry = -1;
    return this.currentLeafNode;
  }

  public NodeImpl addReturnValueNode() {
    final NodeImpl parent =
        this.nodeList.isEmpty() ? null : (NodeImpl) this.nodeList.get(this.nodeList.size() - 1);
    this.currentLeafNode = NodeImpl.createReturnValue(parent);
    this.nodeList.add(this.currentLeafNode);
    this.hashCodeEntry = -1;
    return this.currentLeafNode;
  }

  public NodeImpl makeLeafNodeIterable() {
    this.currentLeafNode = NodeImpl.makeIterable(this.currentLeafNode);

    this.nodeList.remove(this.nodeList.size() - 1);
    this.nodeList.add(this.currentLeafNode);
    this.hashCodeEntry = -1;
    return this.currentLeafNode;
  }

  public NodeImpl setLeafNodeIndex(final Integer index) {
    this.currentLeafNode = NodeImpl.setIndex(this.currentLeafNode, index);

    this.nodeList.remove(this.nodeList.size() - 1);
    this.nodeList.add(this.currentLeafNode);
    this.hashCodeEntry = -1;
    return this.currentLeafNode;
  }

  public NodeImpl setLeafNodeMapKey(final Object key) {
    this.currentLeafNode = NodeImpl.setMapKey(this.currentLeafNode, key);

    this.nodeList.remove(this.nodeList.size() - 1);
    this.nodeList.add(this.currentLeafNode);
    this.hashCodeEntry = -1;
    return this.currentLeafNode;
  }

  public NodeImpl setLeafNodeValue(final Object value) {
    this.currentLeafNode = NodeImpl.setPropertyValue(this.currentLeafNode, value);

    this.nodeList.remove(this.nodeList.size() - 1);
    this.nodeList.add(this.currentLeafNode);
    this.hashCodeEntry = -1;
    return this.currentLeafNode;
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
    this.nodeList = new ArrayList<>();
    this.hashCodeEntry = -1;
  }

  private PathImpl(final List<Node> nodeList) {
    this.nodeList = new ArrayList<>(nodeList);
    this.hashCodeEntry = -1;
  }

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
            path.setLeafNodeIndex(i);
          } catch (final NumberFormatException e) {
            path.setLeafNodeMapKey(indexOrKey);
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
   * <a href="http://java.sun.com/docs/books/jls/third_edition/html/lexical.html#3.8">chapter
   * 3.8</a>
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
    return (ch & 0x00007000) >= 0x00005000;
  }

  private static boolean isJavaIdentifierPart(final int ch) {
    return (ch & 0x00003000) != 0;
  }
}
