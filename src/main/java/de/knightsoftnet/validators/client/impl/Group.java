/*
 * Copyright 2012 Google Inc. Copyright 2016 Manfred Tremmel
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

import java.util.Objects;

import javax.validation.groups.Default;

/**
 * Encapsulates a single validation group.
 * <p>
 * Modified from the Hibernate validator for use with GWT.
 * </p>
 */
public class Group {
  /**
   * The actual group.
   */
  private final Class<?> group;

  /**
   * The sequence the group is part of ({@code null}, if this group is not part of a sequence).
   */
  private final Class<?> sequence;

  /**
   * Creates a group that is not part of a sequence with no parents.
   *
   * @param group The validation group.
   */
  public Group(final Class<?> group) {
    this(group, null);
  }

  /**
   * Creates a group that is part of a sequence with no parents.
   *
   * @param group The validation group. Must not be null.
   * @param sequence The sequence the group is a part of. Can be null if the group is not part of a
   *        sequence.
   */
  public Group(final Class<?> group, final Class<?> sequence) {
    if (group == null) {
      throw new IllegalArgumentException("The group class must not be null");
    }
    this.group = group;
    this.sequence = sequence;
  }

  @Override
  public boolean equals(final Object pobject) {
    if (this == pobject) {
      return true;
    }
    if (!(pobject instanceof Group)) {
      return false;
    }
    final Group other = (Group) pobject;
    return Objects.equals(this.group, other.group);
  }

  public Class<?> getGroup() {
    return this.group;
  }

  public Class<?> getSequence() {
    return this.sequence;
  }

  @Override
  public int hashCode() {
    return this.group.hashCode();
  }

  public boolean isDefaultGroup() {
    return this.getGroup().getName().equals(Default.class.getName());
  }

  @Override
  public String toString() {
    return "Group{" + "group=" + this.group.getName() + "}";
  }
}
