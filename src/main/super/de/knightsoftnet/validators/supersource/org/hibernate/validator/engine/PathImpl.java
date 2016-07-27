// $Id: PathImpl.java 17744 2009-10-14 14:38:57Z hardy.ferentschik $
/*
 * JBoss, Home of Professional Open Source Copyright 2009, Red Hat, Inc. and/or its affiliates, and
 * individual contributors by the @authors tag. See the copyright.txt in the distribution for a full
 * listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in
 * writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
// Modified by Google: Replace java.util.Pattern with gwt RegExp

package org.hibernate.validator.engine;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.validation.Path;

/**
 * path implementation.
 *
 * @author Hardy Ferentschik
 */
public final class PathImpl implements Path, Serializable {

  private static final long serialVersionUID = 7564511574909882392L;

  /**
   * Regular expression used to split a string path into its elements.
   *
   * @see <a href="http://www.regexplanet.com/simple/index.jsp">Regular expression tester</a>
   */
  private static final RegExp PATH_PATTERN = RegExp.compile("(\\w+)(\\[(\\w*)\\])?(\\.(.*))*");

  private static final String PROPERTY_PATH_SEPERATOR = ".";

  private final List<Node> nodeList;

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
      throw new IllegalArgumentException("null is not allowed as property path.");
    }

    if (propertyPath.length() == 0) {
      return createNewPath(null);
    }

    return parseProperty(propertyPath);
  }

  /**
   * create new path.
   * 
   * @param name node name
   * @return path implementation
   */
  public static PathImpl createNewPath(final String name) {
    final PathImpl path = new PathImpl();
    final NodeImpl node = new NodeImpl(name);
    path.addNode(node);
    return path;
  }

  public static PathImpl createShallowCopy(final Path path) {
    return path == null ? null : new PathImpl(path);
  }

  private PathImpl(final Path path) {
    this.nodeList = new ArrayList<Node>();
    for (final Object pathNode : path) {
      this.nodeList.add(new NodeImpl((Node) pathNode));
    }
  }

  private PathImpl() {
    this.nodeList = new ArrayList<Node>();
  }

  private PathImpl(final List<Node> nodeList) {
    this.nodeList = new ArrayList<Node>();
    for (final Node node : nodeList) {
      this.nodeList.add(new NodeImpl(node));
    }
  }

  public boolean isRootPath() {
    return this.nodeList.size() == 1 && this.nodeList.get(0).getName() == null;
  }

  /**
   * get path without leaf node.
   * 
   * @return path implementation
   */
  public PathImpl getPathWithoutLeafNode() {
    final List<Node> nodes = new ArrayList<Node>(this.nodeList);
    PathImpl path = null;
    if (nodes.size() > 1) {
      nodes.remove(nodes.size() - 1);
      path = new PathImpl(nodes);
    }
    return path;
  }

  public void addNode(final Node node) {
    this.nodeList.add(node);
  }

  /**
   * remove leaf node from path.
   * 
   * @return node without leaf node
   */
  public Node removeLeafNode() {
    if (this.nodeList.size() == 0) {
      throw new IllegalStateException("No nodes in path!");
    }
    if (this.nodeList.size() == 1) {
      throw new IllegalStateException("Root node cannot be removed!");
    }
    return this.nodeList.remove(this.nodeList.size() - 1);
  }

  /**
   * get the node.
   * 
   * @return node implementation
   */
  public NodeImpl getLeafNode() {
    if (this.nodeList.size() == 0) {
      throw new IllegalStateException("No nodes in path!");
    }
    return (NodeImpl) this.nodeList.get(this.nodeList.size() - 1);
  }

  @Override
  public Iterator<Path.Node> iterator() {
    return this.nodeList.iterator();
  }

  /**
   * check if current path is a sub path of the given.
   * 
   * @param path path to check
   * @return true if it is a sub path
   */
  public boolean isSubPathOf(final Path path) {
    final Iterator<Node> pathIter = path.iterator();
    final Iterator<Node> thisIter = this.iterator();
    while (pathIter.hasNext()) {
      final Node pathNode = pathIter.next();
      if (!thisIter.hasNext()) {
        return false;
      }
      final Node thisNode = thisIter.next();
      if (!thisNode.equals(pathNode)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    final Iterator<Path.Node> iter = this.iterator();
    while (iter.hasNext()) {
      final Node node = iter.next();
      builder.append(node.toString());
      if (iter.hasNext()) {
        builder.append(PROPERTY_PATH_SEPERATOR);
      }
    }
    return builder.toString();
  }

  @Override
  public boolean equals(final Object pobject) {
    if (this == pobject) {
      return true;
    }
    if (pobject == null || this.getClass() != pobject.getClass()) {
      return false;
    }

    final PathImpl path = (PathImpl) pobject;
    if (this.nodeList != null && !this.nodeList.equals(path.nodeList)) {
      return false;
    }
    return !(this.nodeList == null && path.nodeList != null);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.nodeList);
  }

  private static PathImpl parseProperty(final String property) {
    final PathImpl path = new PathImpl();
    String tmp = property;
    do {
      final MatchResult matcher = PATH_PATTERN.exec(tmp);
      if (matcher == null) {
        throw new IllegalArgumentException("Unable to parse property path " + property);
      } else {
        final String value = matcher.getGroup(1);
        final String indexed = matcher.getGroup(2);
        final String index = matcher.getGroup(3);
        final NodeImpl node = new NodeImpl(value);
        if (indexed != null) {
          node.setInIterable(true);
        }
        if (index != null && index.length() > 0) {
          try {
            final Integer i = Integer.parseInt(index);
            node.setIndex(i);
          } catch (final NumberFormatException e) {
            node.setKey(index);
          }
        }
        path.addNode(node);
        tmp = matcher.getGroup(5);
      }
    } while (tmp != null);
    return path;
  }

}
