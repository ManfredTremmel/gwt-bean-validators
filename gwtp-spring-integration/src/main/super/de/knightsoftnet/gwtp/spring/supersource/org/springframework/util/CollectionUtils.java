/*
 * Copyright 2002-2018 the original author or authors.
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

package org.springframework.util;

import com.google.gwt.core.shared.GwtIncompatible;

import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;

/**
 * Miscellaneous collection utility methods. Mainly for internal use within the framework.
 *
 * @author Juergen Hoeller
 * @author Rob Harrop
 * @author Arjen Poutsma
 * @since 1.1.3
 */
public abstract class CollectionUtils {

  /**
   * Return {@code true} if the supplied Collection is {@code null} or empty. Otherwise, return
   * {@code false}.
   *
   * @param collection the Collection to check
   * @return whether the given Collection is empty
   */
  public static boolean isEmpty(@Nullable final Collection<?> collection) {
    return collection == null || collection.isEmpty();
  }

  /**
   * Return {@code true} if the supplied Map is {@code null} or empty. Otherwise, return
   * {@code false}.
   *
   * @param map the Map to check
   * @return whether the given Map is empty
   */
  public static boolean isEmpty(@Nullable final Map<?, ?> map) {
    return map == null || map.isEmpty();
  }

  /**
   * Convert the supplied array into a List. A primitive array gets converted into a List of the
   * appropriate wrapper type.
   * <p>
   * <b>NOTE:</b> Generally prefer the standard {@link Arrays#asList} method. This
   * {@code arrayToList} method is just meant to deal with an incoming Object value that might be an
   * {@code Object[]} or a primitive array at runtime.
   * <p>
   * A {@code null} source value will be converted to an empty List.
   *
   * @param source the (potentially primitive) array
   * @return the converted List result
   * @see ObjectUtils#toObjectArray(Object)
   * @see Arrays#asList(Object[])
   */
  @SuppressWarnings("rawtypes")
  public static List arrayToList(@Nullable final Object source) {
    return Arrays.asList(ObjectUtils.toObjectArray(source));
  }

  /**
   * Merge the given array into the given Collection.
   *
   * @param array the array to merge (may be {@code null})
   * @param collection the target Collection to merge the array into
   */
  @SuppressWarnings("unchecked")
  public static <E> void mergeArrayIntoCollection(@Nullable final Object array,
      final Collection<E> collection) {
    final Object[] arr = ObjectUtils.toObjectArray(array);
    for (final Object elem : arr) {
      collection.add((E) elem);
    }
  }

  /**
   * Merge the given Properties instance into the given Map, copying all properties (key-value
   * pairs) over.
   * <p>
   * Uses {@code Properties.propertyNames()} to even catch default properties linked into the
   * original Properties instance.
   *
   * @param props the Properties instance to merge (may be {@code null})
   * @param map the target Map to merge the properties into
   */
  @GwtIncompatible("incompatible method")
  @SuppressWarnings("unchecked")
  public static <K, V> void mergePropertiesIntoMap(@Nullable final Properties props,
      final Map<K, V> map) {
    if (props != null) {
      for (final Enumeration<?> en = props.propertyNames(); en.hasMoreElements();) {
        final String key = (String) en.nextElement();
        Object value = props.get(key);
        if (value == null) {
          // Allow for defaults fallback or potentially overridden accessor...
          value = props.getProperty(key);
        }
        map.put((K) key, (V) value);
      }
    }
  }


  /**
   * Check whether the given Iterator contains the given element.
   *
   * @param iterator the Iterator to check
   * @param element the element to look for
   * @return {@code true} if found, {@code false} else
   */
  public static boolean contains(@Nullable final Iterator<?> iterator, final Object element) {
    if (iterator != null) {
      while (iterator.hasNext()) {
        final Object candidate = iterator.next();
        if (ObjectUtils.nullSafeEquals(candidate, element)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Check whether the given Enumeration contains the given element.
   *
   * @param enumeration the Enumeration to check
   * @param element the element to look for
   * @return {@code true} if found, {@code false} else
   */
  public static boolean contains(@Nullable final Enumeration<?> enumeration, final Object element) {
    if (enumeration != null) {
      while (enumeration.hasMoreElements()) {
        final Object candidate = enumeration.nextElement();
        if (ObjectUtils.nullSafeEquals(candidate, element)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Check whether the given Collection contains the given element instance.
   * <p>
   * Enforces the given instance to be present, rather than returning {@code true} for an equal
   * element as well.
   *
   * @param collection the Collection to check
   * @param element the element to look for
   * @return {@code true} if found, {@code false} else
   */
  public static boolean containsInstance(@Nullable final Collection<?> collection,
      final Object element) {
    if (collection != null) {
      for (final Object candidate : collection) {
        if (candidate == element) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Return {@code true} if any element in '{@code candidates}' is contained in '{@code source}';
   * otherwise returns {@code false}.
   *
   * @param source the source Collection
   * @param candidates the candidates to search for
   * @return whether any of the candidates has been found
   */
  public static boolean containsAny(final Collection<?> source, final Collection<?> candidates) {
    if (CollectionUtils.isEmpty(source) || CollectionUtils.isEmpty(candidates)) {
      return false;
    }
    for (final Object candidate : candidates) {
      if (source.contains(candidate)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Return the first element in '{@code candidates}' that is contained in '{@code source}'. If no
   * element in '{@code candidates}' is present in '{@code source}' returns {@code null}. Iteration
   * order is {@link Collection} implementation specific.
   *
   * @param source the source Collection
   * @param candidates the candidates to search for
   * @return the first present object, or {@code null} if not found
   */
  @SuppressWarnings("unchecked")
  @Nullable
  public static <E> E findFirstMatch(final Collection<?> source, final Collection<E> candidates) {
    if (CollectionUtils.isEmpty(source) || CollectionUtils.isEmpty(candidates)) {
      return null;
    }
    for (final Object candidate : candidates) {
      if (source.contains(candidate)) {
        return (E) candidate;
      }
    }
    return null;
  }

  /**
   * Find a single value of the given type in the given Collection.
   *
   * @param collection the Collection to search
   * @param type the type to look for
   * @return a value of the given type found if there is a clear match, or {@code null} if none or
   *         more than one such value found
   */
  @GwtIncompatible("incompatible method")
  @SuppressWarnings("unchecked")
  @Nullable
  public static <T> T findValueOfType(final Collection<?> collection,
      @Nullable final Class<T> type) {
    if (CollectionUtils.isEmpty(collection)) {
      return null;
    }
    T value = null;
    for (final Object element : collection) {
      if (type == null || type.isInstance(element)) {
        if (value != null) {
          // More than one value found... no clear single value.
          return null;
        }
        value = (T) element;
      }
    }
    return value;
  }

  /**
   * Find a single value of one of the given types in the given Collection: searching the Collection
   * for a value of the first type, then searching for a value of the second type, etc.
   *
   * @param collection the collection to search
   * @param types the types to look for, in prioritized order
   * @return a value of one of the given types found if there is a clear match, or {@code null} if
   *         none or more than one such value found
   */
  @GwtIncompatible("incompatible method")
  @Nullable
  public static Object findValueOfType(final Collection<?> collection, final Class<?>[] types) {
    if (CollectionUtils.isEmpty(collection) || ObjectUtils.isEmpty(types)) {
      return null;
    }
    for (final Class<?> type : types) {
      final Object value = CollectionUtils.findValueOfType(collection, type);
      if (value != null) {
        return value;
      }
    }
    return null;
  }

  /**
   * Determine whether the given Collection only contains a single unique object.
   *
   * @param collection the Collection to check
   * @return {@code true} if the collection contains a single reference or multiple references to
   *         the same instance, {@code false} else
   */
  public static boolean hasUniqueObject(final Collection<?> collection) {
    if (CollectionUtils.isEmpty(collection)) {
      return false;
    }
    boolean hasCandidate = false;
    Object candidate = null;
    for (final Object elem : collection) {
      if (!hasCandidate) {
        hasCandidate = true;
        candidate = elem;
      } else if (candidate != elem) {
        return false;
      }
    }
    return true;
  }

  /**
   * Find the common element type of the given Collection, if any.
   *
   * @param collection the Collection to check
   * @return the common element type, or {@code null} if no clear common type has been found (or the
   *         collection was empty)
   */
  @Nullable
  public static Class<?> findCommonElementType(final Collection<?> collection) {
    if (CollectionUtils.isEmpty(collection)) {
      return null;
    }
    Class<?> candidate = null;
    for (final Object val : collection) {
      if (val != null) {
        if (candidate == null) {
          candidate = val.getClass();
        } else if (candidate != val.getClass()) {
          return null;
        }
      }
    }
    return candidate;
  }

  /**
   * Retrieve the last element of the given Set, using {@link SortedSet#last()} or otherwise
   * iterating over all elements (assuming a linked set).
   *
   * @param set the Set to check (may be {@code null} or empty)
   * @return the last element, or {@code null} if none
   * @since 5.0.3
   * @see SortedSet
   * @see LinkedHashMap#keySet()
   * @see java.util.LinkedHashSet
   */
  @Nullable
  public static <T> T lastElement(@Nullable final Set<T> set) {
    if (CollectionUtils.isEmpty(set)) {
      return null;
    }
    if (set instanceof SortedSet) {
      return ((SortedSet<T>) set).last();
    }

    // Full iteration necessary...
    final Iterator<T> it = set.iterator();
    T last = null;
    while (it.hasNext()) {
      last = it.next();
    }
    return last;
  }

  /**
   * Retrieve the last element of the given List, accessing the highest index.
   *
   * @param list the List to check (may be {@code null} or empty)
   * @return the last element, or {@code null} if none
   * @since 5.0.3
   */
  @Nullable
  public static <T> T lastElement(@Nullable final List<T> list) {
    if (CollectionUtils.isEmpty(list)) {
      return null;
    }
    return list.get(list.size() - 1);
  }

  /**
   * Marshal the elements from the given enumeration into an array of the given type. Enumeration
   * elements must be assignable to the type of the given array. The array returned will be a
   * different instance than the array given.
   */
  public static <A, E extends A> A[] toArray(final Enumeration<E> enumeration, final A[] array) {
    final ArrayList<A> elements = new ArrayList<>();
    while (enumeration.hasMoreElements()) {
      elements.add(enumeration.nextElement());
    }
    return elements.toArray(array);
  }

  /**
   * Adapt an enumeration to an iterator.
   *
   * @param enumeration the enumeration
   * @return the iterator
   */
  public static <E> Iterator<E> toIterator(final Enumeration<E> enumeration) {
    return new EnumerationIterator<>(enumeration);
  }

  /**
   * Adapt a {@code Map<K, List<V>>} to an {@code MultiValueMap<K, V>}.
   *
   * @param map the original map
   * @return the multi-value map
   * @since 3.1
   */
  public static <K, V> MultiValueMap<K, V> toMultiValueMap(final Map<K, List<V>> map) {
    return new MultiValueMapAdapter<>(map);
  }

  /**
   * Return an unmodifiable view of the specified multi-value map.
   *
   * @param map the map for which an unmodifiable view is to be returned.
   * @return an unmodifiable view of the specified multi-value map.
   * @since 3.1
   */
  @SuppressWarnings("unchecked")
  public static <K, V> MultiValueMap<K, V> unmodifiableMultiValueMap(
      final MultiValueMap<? extends K, ? extends V> map) {
    Assert.notNull(map, "'map' must not be null");
    final Map<K, List<V>> result = new LinkedHashMap<>(map.size());
    map.forEach((key, value) -> {
      final List<? extends V> values = Collections.unmodifiableList(value);
      result.put(key, (List<V>) values);
    });
    final Map<K, List<V>> unmodifiableMap = Collections.unmodifiableMap(result);
    return CollectionUtils.toMultiValueMap(unmodifiableMap);
  }


  /**
   * Iterator wrapping an Enumeration.
   */
  private static class EnumerationIterator<E> implements Iterator<E> {

    private final Enumeration<E> enumeration;

    public EnumerationIterator(final Enumeration<E> enumeration) {
      this.enumeration = enumeration;
    }

    @Override
    public boolean hasNext() {
      return this.enumeration.hasMoreElements();
    }

    @Override
    public E next() {
      return this.enumeration.nextElement();
    }

    @Override
    public void remove() throws UnsupportedOperationException {
      throw new UnsupportedOperationException("Not supported");
    }
  }


  /**
   * Adapts a Map to the MultiValueMap contract.
   */
  @SuppressWarnings("serial")
  private static class MultiValueMapAdapter<K, V> implements MultiValueMap<K, V>, Serializable {

    private final Map<K, List<V>> map;

    public MultiValueMapAdapter(final Map<K, List<V>> map) {
      Assert.notNull(map, "'map' must not be null");
      this.map = map;
    }

    @Override
    @Nullable
    public V getFirst(final K key) {
      final List<V> values = this.map.get(key);
      return values != null ? values.get(0) : null;
    }

    @Override
    public void add(final K key, @Nullable final V value) {
      final List<V> values = this.map.computeIfAbsent(key, k -> new LinkedList<>());
      values.add(value);
    }

    @Override
    public void addAll(final K key, final List<? extends V> values) {
      final List<V> currentValues = this.map.computeIfAbsent(key, k -> new LinkedList<>());
      currentValues.addAll(values);
    }

    @Override
    public void addAll(final MultiValueMap<K, V> values) {
      for (final Entry<K, List<V>> entry : values.entrySet()) {
        this.addAll(entry.getKey(), entry.getValue());
      }
    }

    @Override
    public void set(final K key, @Nullable final V value) {
      final List<V> values = new LinkedList<>();
      values.add(value);
      this.map.put(key, values);
    }

    @Override
    public void setAll(final Map<K, V> values) {
      values.forEach(this::set);
    }

    @Override
    public Map<K, V> toSingleValueMap() {
      final LinkedHashMap<K, V> singleValueMap = new LinkedHashMap<>(this.map.size());
      this.map.forEach((key, value) -> singleValueMap.put(key, value.get(0)));
      return singleValueMap;
    }

    @Override
    public int size() {
      return this.map.size();
    }

    @Override
    public boolean isEmpty() {
      return this.map.isEmpty();
    }

    @Override
    public boolean containsKey(final Object key) {
      return this.map.containsKey(key);
    }

    @Override
    public boolean containsValue(final Object value) {
      return this.map.containsValue(value);
    }

    @Override
    public List<V> get(final Object key) {
      return this.map.get(key);
    }

    @Override
    public List<V> put(final K key, final List<V> value) {
      return this.map.put(key, value);
    }

    @Override
    public List<V> remove(final Object key) {
      return this.map.remove(key);
    }

    @Override
    public void putAll(final Map<? extends K, ? extends List<V>> map) {
      this.map.putAll(map);
    }

    @Override
    public void clear() {
      this.map.clear();
    }

    @Override
    public Set<K> keySet() {
      return this.map.keySet();
    }

    @Override
    public Collection<List<V>> values() {
      return this.map.values();
    }

    @Override
    public Set<Entry<K, List<V>>> entrySet() {
      return this.map.entrySet();
    }

    @Override
    public boolean equals(final Object other) {
      if (this == other) {
        return true;
      }
      return this.map.equals(other);
    }

    @Override
    public int hashCode() {
      return this.map.hashCode();
    }

    @Override
    public String toString() {
      return this.map.toString();
    }
  }

}
