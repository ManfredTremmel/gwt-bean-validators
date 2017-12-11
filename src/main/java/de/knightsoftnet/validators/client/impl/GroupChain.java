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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.validation.GroupDefinitionException;
import javax.validation.groups.Default;

/**
 * An instance of {@code GroupChain} defines the group order during one full validation call.
 * <p>
 * Modified from the Hibernate validator for use with GWT.
 * </p>
 */
public final class GroupChain {

  /**
   * The list of single groups to be used this validation.
   */
  private final List<Group> groupList = new ArrayList<>();

  /**
   * The different sequences for this validation. The map contains the list of groups mapped to
   * their sequence name.
   */
  private final Map<Class<?>, List<Group>> sequenceMap = new HashMap<>();

  /**
   * check if default group sequence is expandable.
   *
   * @param defaultGroupSequence list of group classes
   * @throws GroupDefinitionException if it's no default group
   */
  public void checkDefaultGroupSequenceIsExpandable(final List<Class<?>> defaultGroupSequence)
      throws GroupDefinitionException {
    for (final Map.Entry<Class<?>, List<Group>> entry : this.sequenceMap.entrySet()) {
      final Class<?> sequence = entry.getKey();
      final List<Group> groups = entry.getValue();
      final List<Group> defaultGroupList = this.buildTempGroupList(defaultGroupSequence, sequence);
      final int defaultGroupIndex = this.containsDefaultGroupAtIndex(sequence, groups);
      if (defaultGroupIndex != -1) {
        this.ensureDefaultGroupSequenceIsExpandable(groups, defaultGroupList, defaultGroupIndex);
      }
    }
  }

  public Collection<Group> getAllGroups() {
    return this.groupList;
  }

  public Iterator<Group> getGroupIterator() {
    return this.groupList.iterator();
  }

  public Iterator<List<Group>> getSequenceIterator() {
    return this.sequenceMap.values().iterator();
  }

  /**
   * insert a single group.
   *
   * @param group to add
   */
  public void insertGroup(final Group group) {
    if (!this.groupList.contains(group)) {
      this.groupList.add(group);
    }
  }

  /**
   * insert a sequence (list) of groups.
   *
   * @param groups list of groups to add
   */
  public void insertSequence(final List<Group> groups) {
    if (groups != null && !groups.isEmpty()) {
      if (!this.sequenceMap.containsValue(groups)) {
        this.sequenceMap.put(groups.get(0).getSequence(), groups);
      }
    }
  }

  @Override
  public String toString() {
    return "GroupChain{" + "groupList=" + this.groupList + ", sequenceMap=" + this.sequenceMap
        + "}";
  }

  private List<Group> buildTempGroupList(final List<Class<?>> defaultGroupSequence,
      final Class<?> sequence) {
    final List<Group> groups = new ArrayList<>();
    for (final Class<?> clazz : defaultGroupSequence) {
      final Group g = new Group(clazz, sequence);
      groups.add(g);
    }
    return groups;
  }

  private int containsDefaultGroupAtIndex(final Class<?> sequence, final List<Group> groupList) {
    final Group defaultGroup = new Group(Default.class, sequence);
    return groupList.indexOf(defaultGroup);
  }

  private void ensureDefaultGroupSequenceIsExpandable(final List<Group> groupList,
      final List<Group> defaultGroupList, final int defaultGroupIndex)
      throws GroupDefinitionException {
    for (int i = 0; i < defaultGroupList.size(); i++) {
      final Group group = defaultGroupList.get(i);
      if (group.getGroup().equals(Default.class)) {
        // we don't have to consider the default group since it is the one we want to replace
        continue;
      }
      // check whether the sequence contains group of the default group sequence
      final int index = groupList.indexOf(group);
      if (index == -1) {
        continue; // if the group is not in the sequence we can continue
      }

      if (i == 0 && index == defaultGroupIndex - 1
          || i == defaultGroupList.size() - 1 && index == defaultGroupIndex + 1) {
        // if we are at the beginning or end of he defaultGroupSequence and the matches are either
        // directly before resp after we can continue as well, since we basically have two groups
        continue;
      }
      throw new GroupDefinitionException("Unable to expand default group list " + defaultGroupList
          + " into sequence " + groupList);
    }
  }
}
