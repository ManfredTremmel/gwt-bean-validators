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

package de.knightsoftnet.validators.client.impl.metadata;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;

import javax.validation.groups.Default;

/**
 * Contains all the information known about the inheritance information for validation groups.
 */
public class ValidationGroupsMetadata {

  /**
   * Builder for {@link ValidationGroupsMetadata}.
   */
  public static class Builder {
    private final Map<Class<?>, Set<Class<?>>> inheritanceinheritanceMap;
    private final Map<Class<?>, List<Class<?>>> sequenceMap;

    private Builder() {
      inheritanceinheritanceMap = new HashMap<>();
      sequenceMap = new HashMap<>();
      addGroup(Default.class);
    }

    /**
     * Adds a group to the inheritance map. May optionally include parents of the group.
     *
     * @param group The validation group to add.
     * @param parents A list of validation groups which {@code group} extends. Can be empty if the
     *        group contains no parents.
     * @return Builder
     */
    public Builder addGroup(final Class<?> group, final Class<?>... parents) {
      inheritanceinheritanceMap.put(group, new HashSet<>(Arrays.asList(parents)));
      return this;
    }

    /**
     * Adds a group sequence to the sequence map.
     *
     * @param groupSequence The class representing the sequence (annotated with &#064;GroupSequence)
     * @param sequenceGroups The groups in the sequence.
     * @return Builder
     */
    public Builder addSequence(final Class<?> groupSequence, final Class<?>... sequenceGroups) {
      sequenceMap.put(groupSequence, Arrays.asList(sequenceGroups));
      return this;
    }

    public ValidationGroupsMetadata build() {
      return new ValidationGroupsMetadata(inheritanceinheritanceMap, // NOPMD
          sequenceMap);
    }
  }

  /**
   * Creates a builder populated only with the {@link Default} group.
   *
   * @return group builder
   */
  public static Builder builder() {
    return new Builder(); // NOPMD
  }

  private final Map<Class<?>, Set<Class<?>>> inheritanceMapping;
  private final Map<Class<?>, List<Class<?>>> sequenceMapping;

  private ValidationGroupsMetadata(final Map<Class<?>, Set<Class<?>>> inheritanceinheritanceMap,
      final Map<Class<?>, List<Class<?>>> sequenceMap) {
    inheritanceMapping = Collections.unmodifiableMap(inheritanceinheritanceMap);
    sequenceMapping = Collections.unmodifiableMap(sequenceMap);
  }

  /**
   * Checks if a given group has been added to the inheritance map.
   *
   * @param group group to check
   * @return true if group is contained in keys
   */
  public boolean containsGroup(final Class<?> group) {
    return inheritanceMapping.containsKey(group);
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof ValidationGroupsMetadata)) {
      return false;
    }
    final ValidationGroupsMetadata otherObj = (ValidationGroupsMetadata) other;
    return inheritanceMapping.equals(otherObj.inheritanceMapping)
        && sequenceMapping.equals(otherObj.sequenceMapping);
  }

  /**
   * Finds all of the validation groups extended by an intial set of groups.
   *
   * @param baseGroups The initial set of groups to find parents of. These groups must have been
   *        added to the inheritance map already.
   * @return A unified set of groups and their parents.
   * @throws IllegalArgumentException If an initial group has not been added to the map before
   *         calling this method.
   */
  public Set<Class<?>> findAllExtendedGroups(final Collection<Class<?>> baseGroups)
      throws IllegalArgumentException {
    final Set<Class<?>> found = new HashSet<>();
    final Stack<Class<?>> remaining = new Stack<>();
    // initialize
    baseGroups.forEach(group -> {
      if (!inheritanceMapping.containsKey(group)) {
        throw new IllegalArgumentException("The collection of groups contains a group which"
            + " was not added to the map. Be sure to call addGroup() for all groups first.");
      }
      remaining.push(group);
    });
    // traverse
    while (!remaining.isEmpty()) {
      final Class<?> current = remaining.pop();
      found.add(current);
      inheritanceMapping.get(current).forEach(parent -> {
        if (!found.contains(parent)) {
          remaining.push(parent);
        }
      });
    }
    return found;
  }

  /**
   * Recursively gets all of the groups and sequence groups in the map (children and parents alike)
   * in one flat set.
   *
   * @return set of groups
   */
  public Set<Class<?>> getAllGroupsAndSequences() {
    final Set<Class<?>> allGroups = new HashSet<>();
    for (final Map.Entry<Class<?>, Set<Class<?>>> entry : inheritanceMapping.entrySet()) {
      allGroups.add(entry.getKey());
      allGroups.addAll(entry.getValue());
    }
    allGroups.addAll(sequenceMapping.keySet());
    return allGroups;
  }

  /**
   * Returns all the known group sequence classes.
   *
   * @return set of groups
   */
  public Set<Class<?>> getGroupSequences() {
    return sequenceMapping.keySet();
  }

  /**
   * If the group has been added to the map then its parent groups (of one level above) are
   * retrieved. Otherwise null is returned.
   *
   * @param group validation group class
   * @return set of groups
   *
   * @see #containsGroup(Class)
   * @see #findAllExtendedGroups(Collection)
   */
  public Set<Class<?>> getParentsOfGroup(final Class<?> group) {
    return inheritanceMapping.get(group);
  }

  /**
   * Returns all of the groups added to the map (but not their parents).
   *
   * @return set of groups
   */
  public Set<Class<?>> getRootGroups() {
    return inheritanceMapping.keySet();
  }

  /**
   * If the sequence class has been added to the map then the actual sequence list is retrieved.
   * Otherwise null is returned.
   *
   * @param sequence class
   * @return list of classes
   */
  public List<Class<?>> getSequenceList(final Class<?> sequence) {
    return sequenceMapping.get(sequence);
  }

  @Override
  public int hashCode() {
    return Objects.hash(inheritanceMapping, sequenceMapping);
  }

  /**
   * Checks if a group extends other groups (has parents).
   *
   * @param group group to check
   * @return true if group has parents
   */
  public boolean hasParents(final Class<?> group) {
    final Set<Class<?>> possibleParents = getParentsOfGroup(group);
    return possibleParents != null && !possibleParents.isEmpty();
  }

  public boolean isInheritanceMapEmpty() {
    return inheritanceMapping.isEmpty();
  }

  /**
   * Checks if a given class is a group sequence map.
   *
   * @param sequence class to check
   * @return true if class is a sequence
   */
  public boolean isSeqeuence(final Class<?> sequence) {
    return sequenceMapping.containsKey(sequence);
  }

  public boolean isSequenceMapEmpty() {
    return sequenceMapping.isEmpty();
  }

  @Override
  public String toString() {
    return "ValidationGroupsMetaData{inheritanceMap=" + inheritanceMapping + ", " + "sequenceMap="
        + sequenceMapping + "}";
  }
}
