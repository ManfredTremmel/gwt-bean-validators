/*
 * Hibernate helper class.
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package org.hibernate.validator.internal.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Provides some methods for simplified collection instantiation.
 *
 * @author Gunnar Morling
 * @author Kevin Pollet &lt;kevin.pollet@serli.com&gt; (C) 2011 SERLI
 * @author Hardy Ferentschik
 * @author Guillaume Smet
 */
public final class CollectionHelper {

  private CollectionHelper() {}

  public static <K, V> HashMap<K, V> newHashMap() {
    return new HashMap<>();
  }

  public static <K, V> HashMap<K, V> newHashMap(final int size) {
    return new HashMap<>(getInitialCapacityFromExpectedSize(size));
  }

  public static <K, V> HashMap<K, V> newHashMap(final Map<K, V> map) {
    return new HashMap<>(map);
  }

  public static <K, V> ConcurrentHashMap<K, V> newConcurrentHashMap() {
    return new ConcurrentHashMap<>();
  }

  public static <T> HashSet<T> newHashSet() {
    return new HashSet<>();
  }

  public static <T> HashSet<T> newHashSet(final int size) {
    return new HashSet<>(getInitialCapacityFromExpectedSize(size));
  }

  public static <T> HashSet<T> newHashSet(final Collection<? extends T> collection) {
    return new HashSet<>(collection);
  }

  /**
   * new has set from iterable.
   *
   * @param iterable iterable to add to hash set
   * @return hash set
   */
  public static <T> HashSet<T> newHashSet(final Iterable<? extends T> iterable) {
    final HashSet<T> set = newHashSet();
    for (final T t : iterable) {
      set.add(t);
    }
    return set;
  }

  public static <T> ArrayList<T> newArrayList() {
    return new ArrayList<>();
  }

  public static <T> ArrayList<T> newArrayList(final int size) {
    return new ArrayList<>(size);
  }

  /**
   * new array list from iterable.
   *
   * @param iterables iterables to add to array list
   * @return hash set
   */
  @SafeVarargs
  public static <T> ArrayList<T> newArrayList(final Iterable<T>... iterables) {
    final ArrayList<T> resultList = newArrayList();
    for (final Iterable<T> oneIterable : iterables) {
      for (final T oneElement : oneIterable) {
        resultList.add(oneElement);
      }
    }
    return resultList;
  }

  @SafeVarargs
  public static <T> Set<T> asSet(final T... ts) {
    return new HashSet<>(Arrays.asList(ts));
  }

  /**
   * list to imuteable list.
   *
   * @param list list to convert
   * @return imuteable list
   */
  public static <T> List<T> toImmutableList(final List<? extends T> list) {
    switch (list.size()) {
      case 0:
        return Collections.emptyList();
      case 1:
        return Collections.singletonList(list.get(0));
      default:
        return Collections.unmodifiableList(list);
    }
  }

  /**
   * list to imuteable set.
   *
   * @param set set to convert
   * @return imuteable set
   */
  public static <T> Set<T> toImmutableSet(final Set<? extends T> set) {
    switch (set.size()) {
      case 0:
        return Collections.emptySet();
      case 1:
        return Collections.singleton(set.iterator().next());
      default:
        return Collections.unmodifiableSet(set);
    }
  }

  /**
   * list to imuteable map.
   *
   * @param map map to convert
   * @return imuteable map
   */
  public static <K, V> Map<K, V> toImmutableMap(final Map<K, V> map) {
    switch (map.size()) {
      case 0:
        return Collections.emptyMap();
      case 1:
        final Entry<K, V> entry = map.entrySet().iterator().next();
        return Collections.singletonMap(entry.getKey(), entry.getValue());
      default:
        return Collections.unmodifiableMap(map);
    }
  }

  /**
   * As the default loadFactor is of 0.75, we need to calculate the initial capacity from the
   * expected size to avoid resizing the collection when we populate the collection with all the
   * initial elements. We use a calculation similar to what is done in {@link HashMap#putAll(Map)}.
   *
   * @param expectedSize the expected size of the collection
   * @return the initial capacity of the collection
   */
  private static int getInitialCapacityFromExpectedSize(final int expectedSize) {
    if (expectedSize < 3) {
      return expectedSize + 1;
    }
    return (int) (expectedSize / 0.75f + 1.0f);
  }

  /**
   * Builds an {@link Iterator} for a given array. It is (un)necessarily ugly because we have to
   * deal with array of primitives.
   *
   * @param object a given array
   * @return an {@code Iterator} iterating over the array
   */
  @SuppressWarnings({"unchecked", "rawtypes"}) // Reflection is used to ensure the correct types are
                                               // used
  public static Iterator<?> iteratorFromArray(final Object object) {
    return new ArrayIterator(accessorFromArray(object), object);
  }

  /**
   * Builds an {@link Iterable} for a given array. It is (un)necessarily ugly because we have to
   * deal with array of primitives.
   *
   * @param object a given array
   * @return an {@code Iterable} providing iterators over the array
   */
  @SuppressWarnings({"unchecked", "rawtypes"}) // Reflection is used to ensure the correct types are
                                               // used
  public static Iterable<?> iterableFromArray(final Object object) {
    return new ArrayIterable(accessorFromArray(object), object);
  }

  private static ArrayAccessor<?, ?> accessorFromArray(final Object object) {
    ArrayAccessor<?, ?> accessor;

    final Class<?> componentType = object.getClass().getComponentType();

    if (componentType != null
        && (object.getClass().getSuperclass() != null || object.getClass() instanceof Object)) {
      accessor = ArrayAccessor.OBJECT;
    } else if (object.getClass() == boolean[].class) {
      accessor = ArrayAccessor.BOOLEAN;
    } else if (object.getClass() == int[].class) {
      accessor = ArrayAccessor.INT;
    } else if (object.getClass() == long[].class) {
      accessor = ArrayAccessor.LONG;
    } else if (object.getClass() == double[].class) {
      accessor = ArrayAccessor.DOUBLE;
    } else if (object.getClass() == float[].class) {
      accessor = ArrayAccessor.FLOAT;
    } else if (object.getClass() == byte[].class) {
      accessor = ArrayAccessor.BYTE;
    } else if (object.getClass() == short[].class) { // NOPMD
      accessor = ArrayAccessor.SHORT;
    } else if (object.getClass() == char[].class) {
      accessor = ArrayAccessor.CHAR;
    } else {
      throw new IllegalArgumentException(
          "Provided object " + object + " is not a supported array type");
    }
    return accessor;
  }

  private static class ArrayIterable<A, T> implements Iterable<T> {
    private final ArrayAccessor<A, T> accessor;
    private final A values;

    public ArrayIterable(final ArrayAccessor<A, T> accessor, final A values) {
      this.accessor = accessor;
      this.values = values;
    }

    @Override
    public final Iterator<T> iterator() {
      return new ArrayIterator<>(this.accessor, this.values);
    }
  }

  private static class ArrayIterator<A, T> implements Iterator<T> {
    private final ArrayAccessor<A, T> accessor;
    private final A values;
    private int current = 0;

    public ArrayIterator(final ArrayAccessor<A, T> accessor, final A values) {
      this.accessor = accessor;
      this.values = values;
    }

    @Override
    public boolean hasNext() {
      return this.current < this.accessor.size(this.values);
    }

    @Override
    public T next() {
      final T result = this.accessor.get(this.values, this.current);
      this.current++;
      return result;
    }
  }

  private interface ArrayAccessor<A, T> {

    int size(A array);

    T get(A array, int index);

    ArrayAccessor<Object[], Object> OBJECT = new ArrayAccessor<Object[], Object>() {
      @Override
      public int size(final Object[] array) {
        return array.length;
      }

      @Override
      public Object get(final Object[] array, final int index) {
        return array[index];
      }
    };

    ArrayAccessor<boolean[], Boolean> BOOLEAN = new ArrayAccessor<boolean[], Boolean>() {
      @Override
      public int size(final boolean[] array) {
        return array.length;
      }

      @Override
      public Boolean get(final boolean[] array, final int index) {
        return array[index];
      }
    };

    ArrayAccessor<int[], Integer> INT = new ArrayAccessor<int[], Integer>() {
      @Override
      public int size(final int[] array) {
        return array.length;
      }

      @Override
      public Integer get(final int[] array, final int index) {
        return array[index];
      }
    };

    ArrayAccessor<long[], Long> LONG = new ArrayAccessor<long[], Long>() {
      @Override
      public int size(final long[] array) {
        return array.length;
      }

      @Override
      public Long get(final long[] array, final int index) {
        return array[index];
      }
    };

    ArrayAccessor<double[], Double> DOUBLE = new ArrayAccessor<double[], Double>() {
      @Override
      public int size(final double[] array) {
        return array.length;
      }

      @Override
      public Double get(final double[] array, final int index) {
        return array[index];
      }
    };

    ArrayAccessor<float[], Float> FLOAT = new ArrayAccessor<float[], Float>() {
      @Override
      public int size(final float[] array) {
        return array.length;
      }

      @Override
      public Float get(final float[] array, final int index) {
        return array[index];
      }
    };

    ArrayAccessor<byte[], Byte> BYTE = new ArrayAccessor<byte[], Byte>() {
      @Override
      public int size(final byte[] array) {
        return array.length;
      }

      @Override
      public Byte get(final byte[] array, final int index) {
        return array[index];
      }
    };

    ArrayAccessor<short[], Short> SHORT = new ArrayAccessor<short[], Short>() { // NOPMD
      @Override
      public int size(final short[] array) { // NOPMD
        return array.length;
      }

      @Override
      public Short get(final short[] array, final int index) { // NOPMD
        return array[index];
      }
    };

    ArrayAccessor<char[], Character> CHAR = new ArrayAccessor<char[], Character>() {
      @Override
      public int size(final char[] array) {
        return array.length;
      }

      @Override
      public Character get(final char[] array, final int index) {
        return array[index];
      }
    };
  }
}
