/*
 * Copyright 2002-2017 the original author or authors.
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

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * Miscellaneous object utility methods.
 *
 * <p>
 * Mainly for internal use within the framework.
 *
 * <p>
 * Thanks to Alex Ruiz for contributing several enhancements to this class!
 *
 * @author Juergen Hoeller
 * @author Keith Donald
 * @author Rod Johnson
 * @author Rob Harrop
 * @author Chris Beams
 * @author Sam Brannen
 * @since 19.03.2004
 * @see ClassUtils
 * @see CollectionUtils
 * @see StringUtils
 */
public abstract class ObjectUtils {

  private static final int INITIAL_HASH = 7;
  private static final int MULTIPLIER = 31;

  private static final String EMPTY_STRING = "";
  private static final String NULL_STRING = "null";
  private static final String ARRAY_START = "{";
  private static final String ARRAY_END = "}";
  private static final String EMPTY_ARRAY = ObjectUtils.ARRAY_START + ObjectUtils.ARRAY_END;
  private static final String ARRAY_ELEMENT_SEPARATOR = ", ";


  /**
   * Return whether the given throwable is a checked exception: that is, neither a RuntimeException
   * nor an Error.
   *
   * @param ex the throwable to check
   * @return whether the throwable is a checked exception
   * @see java.lang.Exception
   * @see java.lang.RuntimeException
   * @see java.lang.Error
   */
  public static boolean isCheckedException(final Throwable ex) {
    return !(ex instanceof RuntimeException || ex instanceof Error);
  }

  /**
   * Check whether the given exception is compatible with the specified exception types, as declared
   * in a throws clause.
   *
   * @param ex the exception to check
   * @param declaredExceptions the exception types declared in the throws clause
   * @return whether the given exception is compatible
   */
  @GwtIncompatible("incompatible method")
  public static boolean isCompatibleWithThrowsClause(final Throwable ex,
      @Nullable final Class<?>... declaredExceptions) {
    if (!ObjectUtils.isCheckedException(ex)) {
      return true;
    }
    if (declaredExceptions != null) {
      for (final Class<?> declaredException : declaredExceptions) {
        if (declaredException.isInstance(ex)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Determine whether the given object is an array: either an Object array or a primitive array.
   *
   * @param obj the object to check
   */
  public static boolean isArray(@Nullable final Object obj) {
    return obj != null && obj.getClass().isArray();
  }

  /**
   * Determine whether the given array is empty: i.e. {@code null} or of zero length.
   *
   * @param array the array to check
   * @see #isEmpty(Object)
   */
  public static boolean isEmpty(@Nullable final Object[] array) {
    return array == null || array.length == 0;
  }

  /**
   * Determine whether the given object is empty.
   * <p>
   * This method supports the following object types.
   * <ul>
   * <li>{@code Optional}: considered empty if {@link Optional#empty()}</li>
   * <li>{@code Array}: considered empty if its length is zero</li>
   * <li>{@link CharSequence}: considered empty if its length is zero</li>
   * <li>{@link Collection}: delegates to {@link Collection#isEmpty()}</li>
   * <li>{@link Map}: delegates to {@link Map#isEmpty()}</li>
   * </ul>
   * <p>
   * If the given object is non-null and not one of the aforementioned supported types, this method
   * returns {@code false}.
   *
   * @param obj the object to check
   * @return {@code true} if the object is {@code null} or <em>empty</em>
   * @since 4.2
   * @see Optional#isPresent()
   * @see ObjectUtils#isEmpty(Object[])
   * @see StringUtils#hasLength(CharSequence)
   * @see StringUtils#isEmpty(Object)
   * @see CollectionUtils#isEmpty(java.util.Collection)
   * @see CollectionUtils#isEmpty(java.util.Map)
   */
  @SuppressWarnings("rawtypes")
  public static boolean isEmpty(@Nullable final Object obj) {
    if (obj == null) {
      return true;
    }

    if (obj instanceof Optional) {
      return !((Optional) obj).isPresent();
    }
    if (obj instanceof CharSequence) {
      return ((CharSequence) obj).length() == 0;
    }
    if (obj.getClass().isArray()) {
      return Array.getLength(obj) == 0;
    }
    if (obj instanceof Collection) {
      return ((Collection) obj).isEmpty();
    }
    if (obj instanceof Map) {
      return ((Map) obj).isEmpty();
    }

    // else
    return false;
  }

  /**
   * Unwrap the given object which is potentially a {@link java.util.Optional}.
   *
   * @param obj the candidate object
   * @return either the value held within the {@code Optional}, {@code null} if the {@code Optional}
   *         is empty, or simply the given object as-is
   * @since 5.0
   */
  @Nullable
  public static Object unwrapOptional(@Nullable final Object obj) {
    if (obj instanceof Optional) {
      final Optional<?> optional = (Optional<?>) obj;
      if (!optional.isPresent()) {
        return null;
      }
      final Object result = optional.get();
      Assert.isTrue(!(result instanceof Optional), "Multi-level Optional usage not supported");
      return result;
    }
    return obj;
  }

  /**
   * Check whether the given array contains the given element.
   *
   * @param array the array to check (may be {@code null}, in which case the return value will
   *        always be {@code false})
   * @param element the element to check for
   * @return whether the element has been found in the given array
   */
  public static boolean containsElement(@Nullable final Object[] array, final Object element) {
    if (array == null) {
      return false;
    }
    for (final Object arrayEle : array) {
      if (ObjectUtils.nullSafeEquals(arrayEle, element)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Check whether the given array of enum constants contains a constant with the given name,
   * ignoring case when determining a match.
   *
   * @param enumValues the enum values to check, typically the product of a call to MyEnum.values()
   * @param constant the constant name to find (must not be null or empty string)
   * @return whether the constant has been found in the given array
   */
  public static boolean containsConstant(final Enum<?>[] enumValues, final String constant) {
    return ObjectUtils.containsConstant(enumValues, constant, false);
  }

  /**
   * Check whether the given array of enum constants contains a constant with the given name.
   *
   * @param enumValues the enum values to check, typically the product of a call to MyEnum.values()
   * @param constant the constant name to find (must not be null or empty string)
   * @param caseSensitive whether case is significant in determining a match
   * @return whether the constant has been found in the given array
   */
  public static boolean containsConstant(final Enum<?>[] enumValues, final String constant,
      final boolean caseSensitive) {
    for (final Enum<?> candidate : enumValues) {
      if (caseSensitive ? candidate.toString().equals(constant)
          : candidate.toString().equalsIgnoreCase(constant)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Case insensitive alternative to {@link Enum#valueOf(Class, String)}.
   *
   * @param <E> the concrete Enum type
   * @param enumValues the array of all Enum constants in question, usually per Enum.values()
   * @param constant the constant to get the enum value of
   * @throws IllegalArgumentException if the given constant is not found in the given array of enum
   *         values. Use {@link #containsConstant(Enum[], String)} as a guard to avoid this
   *         exception.
   */
  public static <E extends Enum<?>> E caseInsensitiveValueOf(final E[] enumValues,
      final String constant) {
    for (final E candidate : enumValues) {
      if (candidate.toString().equalsIgnoreCase(constant)) {
        return candidate;
      }
    }
    throw new IllegalArgumentException("constant [" + constant + "] does not exist in enum type "
        + enumValues.getClass().getComponentType().getName());
  }

  /**
   * Append the given object to the given array, returning a new array consisting of the input array
   * contents plus the given object.
   *
   * @param array the array to append to (can be {@code null})
   * @param obj the object to append
   * @return the new array (of the same component type; never {@code null})
   */
  public static <A, O extends A> A[] addObjectToArray(@Nullable final A[] array,
      @Nullable final O obj) {
    Class<?> compType = Object.class;
    if (array != null) {
      compType = array.getClass().getComponentType();
    } else if (obj != null) {
      compType = obj.getClass();
    }
    final int newArrLength = array != null ? array.length + 1 : 1;
    @SuppressWarnings("unchecked")
    final A[] newArr = (A[]) Array.newInstance(compType, newArrLength);
    if (array != null) {
      System.arraycopy(array, 0, newArr, 0, array.length);
    }
    newArr[newArr.length - 1] = obj;
    return newArr;
  }

  /**
   * Convert the given array (which may be a primitive array) to an object array (if necessary of
   * primitive wrapper objects).
   * <p>
   * A {@code null} source value will be converted to an empty Object array.
   *
   * @param source the (potentially primitive) array
   * @return the corresponding object array (never {@code null})
   * @throws IllegalArgumentException if the parameter is not an array
   */
  public static Object[] toObjectArray(@Nullable final Object source) {
    if (source instanceof Object[]) {
      return (Object[]) source;
    }
    if (source == null) {
      return new Object[0];
    }
    if (!source.getClass().isArray()) {
      throw new IllegalArgumentException("Source is not an array: " + source);
    }
    final int length = Array.getLength(source);
    if (length == 0) {
      return new Object[0];
    }
    final Class<?> wrapperType = Array.get(source, 0).getClass();
    final Object[] newArray = (Object[]) Array.newInstance(wrapperType, length);
    for (int i = 0; i < length; i++) {
      newArray[i] = Array.get(source, i);
    }
    return newArray;
  }


  // ---------------------------------------------------------------------
  // Convenience methods for content-based equality/hash-code handling
  // ---------------------------------------------------------------------

  /**
   * Determine if the given objects are equal, returning {@code true} if both are {@code null} or
   * {@code false} if only one is {@code null}.
   * <p>
   * Compares arrays with {@code Arrays.equals}, performing an equality check based on the array
   * elements rather than the array reference.
   *
   * @param o1 first Object to compare
   * @param o2 second Object to compare
   * @return whether the given objects are equal
   * @see Object#equals(Object)
   * @see java.util.Arrays#equals
   */
  public static boolean nullSafeEquals(@Nullable final Object o1, @Nullable final Object o2) {
    if (o1 == o2) {
      return true;
    }
    if (o1 == null || o2 == null) {
      return false;
    }
    if (o1.equals(o2)) {
      return true;
    }
    if (o1.getClass().isArray() && o2.getClass().isArray()) {
      return ObjectUtils.arrayEquals(o1, o2);
    }
    return false;
  }

  /**
   * Compare the given arrays with {@code Arrays.equals}, performing an equality check based on the
   * array elements rather than the array reference.
   *
   * @param o1 first array to compare
   * @param o2 second array to compare
   * @return whether the given objects are equal
   * @see #nullSafeEquals(Object, Object)
   * @see java.util.Arrays#equals
   */
  private static boolean arrayEquals(final Object o1, final Object o2) {
    if (o1 instanceof Object[] && o2 instanceof Object[]) {
      return Arrays.equals((Object[]) o1, (Object[]) o2);
    }
    if (o1 instanceof boolean[] && o2 instanceof boolean[]) {
      return Arrays.equals((boolean[]) o1, (boolean[]) o2);
    }
    if (o1 instanceof byte[] && o2 instanceof byte[]) {
      return Arrays.equals((byte[]) o1, (byte[]) o2);
    }
    if (o1 instanceof char[] && o2 instanceof char[]) {
      return Arrays.equals((char[]) o1, (char[]) o2);
    }
    if (o1 instanceof double[] && o2 instanceof double[]) {
      return Arrays.equals((double[]) o1, (double[]) o2);
    }
    if (o1 instanceof float[] && o2 instanceof float[]) {
      return Arrays.equals((float[]) o1, (float[]) o2);
    }
    if (o1 instanceof int[] && o2 instanceof int[]) {
      return Arrays.equals((int[]) o1, (int[]) o2);
    }
    if (o1 instanceof long[] && o2 instanceof long[]) {
      return Arrays.equals((long[]) o1, (long[]) o2);
    }
    if (o1 instanceof short[] && o2 instanceof short[]) {
      return Arrays.equals((short[]) o1, (short[]) o2);
    }
    return false;
  }

  /**
   * Return as hash code for the given object; typically the value of {@code Object#hashCode()}}. If
   * the object is an array, this method will delegate to any of the {@code nullSafeHashCode}
   * methods for arrays in this class. If the object is {@code null}, this method returns 0.
   *
   * @see Object#hashCode()
   * @see #nullSafeHashCode(Object[])
   * @see #nullSafeHashCode(boolean[])
   * @see #nullSafeHashCode(byte[])
   * @see #nullSafeHashCode(char[])
   * @see #nullSafeHashCode(double[])
   * @see #nullSafeHashCode(float[])
   * @see #nullSafeHashCode(int[])
   * @see #nullSafeHashCode(long[])
   * @see #nullSafeHashCode(short[])
   */
  public static int nullSafeHashCode(@Nullable final Object obj) {
    if (obj == null) {
      return 0;
    }
    if (obj.getClass().isArray()) {
      if (obj instanceof Object[]) {
        return ObjectUtils.nullSafeHashCode((Object[]) obj);
      }
      if (obj instanceof boolean[]) {
        return ObjectUtils.nullSafeHashCode((boolean[]) obj);
      }
      if (obj instanceof byte[]) {
        return ObjectUtils.nullSafeHashCode((byte[]) obj);
      }
      if (obj instanceof char[]) {
        return ObjectUtils.nullSafeHashCode((char[]) obj);
      }
      if (obj instanceof double[]) {
        return ObjectUtils.nullSafeHashCode((double[]) obj);
      }
      if (obj instanceof float[]) {
        return ObjectUtils.nullSafeHashCode((float[]) obj);
      }
      if (obj instanceof int[]) {
        return ObjectUtils.nullSafeHashCode((int[]) obj);
      }
      if (obj instanceof long[]) {
        return ObjectUtils.nullSafeHashCode((long[]) obj);
      }
      if (obj instanceof short[]) {
        return ObjectUtils.nullSafeHashCode((short[]) obj);
      }
    }
    return obj.hashCode();
  }

  /**
   * Return a hash code based on the contents of the specified array. If {@code array} is
   * {@code null}, this method returns 0.
   */
  public static int nullSafeHashCode(@Nullable final Object[] array) {
    if (array == null) {
      return 0;
    }
    int hash = ObjectUtils.INITIAL_HASH;
    for (final Object element : array) {
      hash = ObjectUtils.MULTIPLIER * hash + ObjectUtils.nullSafeHashCode(element);
    }
    return hash;
  }

  /**
   * Return a hash code based on the contents of the specified array. If {@code array} is
   * {@code null}, this method returns 0.
   */
  public static int nullSafeHashCode(@Nullable final boolean[] array) {
    if (array == null) {
      return 0;
    }
    int hash = ObjectUtils.INITIAL_HASH;
    for (final boolean element : array) {
      hash = ObjectUtils.MULTIPLIER * hash + Boolean.hashCode(element);
    }
    return hash;
  }

  /**
   * Return a hash code based on the contents of the specified array. If {@code array} is
   * {@code null}, this method returns 0.
   */
  public static int nullSafeHashCode(@Nullable final byte[] array) {
    if (array == null) {
      return 0;
    }
    int hash = ObjectUtils.INITIAL_HASH;
    for (final byte element : array) {
      hash = ObjectUtils.MULTIPLIER * hash + element;
    }
    return hash;
  }

  /**
   * Return a hash code based on the contents of the specified array. If {@code array} is
   * {@code null}, this method returns 0.
   */
  public static int nullSafeHashCode(@Nullable final char[] array) {
    if (array == null) {
      return 0;
    }
    int hash = ObjectUtils.INITIAL_HASH;
    for (final char element : array) {
      hash = ObjectUtils.MULTIPLIER * hash + element;
    }
    return hash;
  }

  /**
   * Return a hash code based on the contents of the specified array. If {@code array} is
   * {@code null}, this method returns 0.
   */
  public static int nullSafeHashCode(@Nullable final double[] array) {
    if (array == null) {
      return 0;
    }
    int hash = ObjectUtils.INITIAL_HASH;
    for (final double element : array) {
      hash = ObjectUtils.MULTIPLIER * hash + Double.hashCode(element);
    }
    return hash;
  }

  /**
   * Return a hash code based on the contents of the specified array. If {@code array} is
   * {@code null}, this method returns 0.
   */
  public static int nullSafeHashCode(@Nullable final float[] array) {
    if (array == null) {
      return 0;
    }
    int hash = ObjectUtils.INITIAL_HASH;
    for (final float element : array) {
      hash = ObjectUtils.MULTIPLIER * hash + Float.hashCode(element);
    }
    return hash;
  }

  /**
   * Return a hash code based on the contents of the specified array. If {@code array} is
   * {@code null}, this method returns 0.
   */
  public static int nullSafeHashCode(@Nullable final int[] array) {
    if (array == null) {
      return 0;
    }
    int hash = ObjectUtils.INITIAL_HASH;
    for (final int element : array) {
      hash = ObjectUtils.MULTIPLIER * hash + element;
    }
    return hash;
  }

  /**
   * Return a hash code based on the contents of the specified array. If {@code array} is
   * {@code null}, this method returns 0.
   */
  public static int nullSafeHashCode(@Nullable final long[] array) {
    if (array == null) {
      return 0;
    }
    int hash = ObjectUtils.INITIAL_HASH;
    for (final long element : array) {
      hash = ObjectUtils.MULTIPLIER * hash + Long.hashCode(element);
    }
    return hash;
  }

  /**
   * Return a hash code based on the contents of the specified array. If {@code array} is
   * {@code null}, this method returns 0.
   */
  public static int nullSafeHashCode(@Nullable final short[] array) {
    if (array == null) {
      return 0;
    }
    int hash = ObjectUtils.INITIAL_HASH;
    for (final short element : array) {
      hash = ObjectUtils.MULTIPLIER * hash + element;
    }
    return hash;
  }

  /**
   * Return the same value as {@link Boolean#hashCode(boolean)}}.
   *
   * @deprecated as of Spring Framework 5.0, in favor of the native JDK 8 variant
   */
  @Deprecated
  public static int hashCode(final boolean bool) {
    return Boolean.hashCode(bool);
  }

  /**
   * Return the same value as {@link Double#hashCode(double)}}.
   *
   * @deprecated as of Spring Framework 5.0, in favor of the native JDK 8 variant
   */
  @Deprecated
  public static int hashCode(final double dbl) {
    return Double.hashCode(dbl);
  }

  /**
   * Return the same value as {@link Float#hashCode(float)}}.
   *
   * @deprecated as of Spring Framework 5.0, in favor of the native JDK 8 variant
   */
  @Deprecated
  public static int hashCode(final float flt) {
    return Float.hashCode(flt);
  }

  /**
   * Return the same value as {@link Long#hashCode(long)}}.
   *
   * @deprecated as of Spring Framework 5.0, in favor of the native JDK 8 variant
   */
  @Deprecated
  public static int hashCode(final long lng) {
    return Long.hashCode(lng);
  }


  // ---------------------------------------------------------------------
  // Convenience methods for toString output
  // ---------------------------------------------------------------------

  /**
   * Return a String representation of an object's overall identity.
   *
   * @param obj the object (may be {@code null})
   * @return the object's identity as String representation, or an empty String if the object was
   *         {@code null}
   */
  public static String identityToString(@Nullable final Object obj) {
    if (obj == null) {
      return ObjectUtils.EMPTY_STRING;
    }
    return obj.getClass().getName() + "@" + ObjectUtils.getIdentityHexString(obj);
  }

  /**
   * Return a hex String form of an object's identity hash code.
   *
   * @param obj the object
   * @return the object's identity code in hex notation
   */
  public static String getIdentityHexString(final Object obj) {
    return Integer.toHexString(System.identityHashCode(obj));
  }

  /**
   * Return a content-based String representation if {@code obj} is not {@code null}; otherwise
   * returns an empty String.
   * <p>
   * Differs from {@link #nullSafeToString(Object)} in that it returns an empty String rather than
   * "null" for a {@code null} value.
   *
   * @param obj the object to build a display String for
   * @return a display String representation of {@code obj}
   * @see #nullSafeToString(Object)
   */
  public static String getDisplayString(@Nullable final Object obj) {
    if (obj == null) {
      return ObjectUtils.EMPTY_STRING;
    }
    return ObjectUtils.nullSafeToString(obj);
  }

  /**
   * Determine the class name for the given object.
   * <p>
   * Returns {@code "null"} if {@code obj} is {@code null}.
   *
   * @param obj the object to introspect (may be {@code null})
   * @return the corresponding class name
   */
  public static String nullSafeClassName(@Nullable final Object obj) {
    return obj != null ? obj.getClass().getName() : ObjectUtils.NULL_STRING;
  }

  /**
   * Return a String representation of the specified Object.
   * <p>
   * Builds a String representation of the contents in case of an array. Returns {@code "null"} if
   * {@code obj} is {@code null}.
   *
   * @param obj the object to build a String representation for
   * @return a String representation of {@code obj}
   */
  public static String nullSafeToString(@Nullable final Object obj) {
    if (obj == null) {
      return ObjectUtils.NULL_STRING;
    }
    if (obj instanceof String) {
      return (String) obj;
    }
    if (obj instanceof Object[]) {
      return ObjectUtils.nullSafeToString((Object[]) obj);
    }
    if (obj instanceof boolean[]) {
      return ObjectUtils.nullSafeToString((boolean[]) obj);
    }
    if (obj instanceof byte[]) {
      return ObjectUtils.nullSafeToString((byte[]) obj);
    }
    if (obj instanceof char[]) {
      return ObjectUtils.nullSafeToString((char[]) obj);
    }
    if (obj instanceof double[]) {
      return ObjectUtils.nullSafeToString((double[]) obj);
    }
    if (obj instanceof float[]) {
      return ObjectUtils.nullSafeToString((float[]) obj);
    }
    if (obj instanceof int[]) {
      return ObjectUtils.nullSafeToString((int[]) obj);
    }
    if (obj instanceof long[]) {
      return ObjectUtils.nullSafeToString((long[]) obj);
    }
    if (obj instanceof short[]) {
      return ObjectUtils.nullSafeToString((short[]) obj);
    }
    final String str = obj.toString();
    return str != null ? str : ObjectUtils.EMPTY_STRING;
  }

  /**
   * Return a String representation of the contents of the specified array.
   * <p>
   * The String representation consists of a list of the array's elements, enclosed in curly braces
   * ({@code "{}"}). Adjacent elements are separated by the characters {@code ", "} (a comma
   * followed by a space). Returns {@code "null"} if {@code array} is {@code null}.
   *
   * @param array the array to build a String representation for
   * @return a String representation of {@code array}
   */
  public static String nullSafeToString(@Nullable final Object[] array) {
    if (array == null) {
      return ObjectUtils.NULL_STRING;
    }
    final int length = array.length;
    if (length == 0) {
      return ObjectUtils.EMPTY_ARRAY;
    }
    final StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i++) {
      if (i == 0) {
        sb.append(ObjectUtils.ARRAY_START);
      } else {
        sb.append(ObjectUtils.ARRAY_ELEMENT_SEPARATOR);
      }
      sb.append(String.valueOf(array[i]));
    }
    sb.append(ObjectUtils.ARRAY_END);
    return sb.toString();
  }

  /**
   * Return a String representation of the contents of the specified array.
   * <p>
   * The String representation consists of a list of the array's elements, enclosed in curly braces
   * ({@code "{}"}). Adjacent elements are separated by the characters {@code ", "} (a comma
   * followed by a space). Returns {@code "null"} if {@code array} is {@code null}.
   *
   * @param array the array to build a String representation for
   * @return a String representation of {@code array}
   */
  public static String nullSafeToString(@Nullable final boolean[] array) {
    if (array == null) {
      return ObjectUtils.NULL_STRING;
    }
    final int length = array.length;
    if (length == 0) {
      return ObjectUtils.EMPTY_ARRAY;
    }
    final StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i++) {
      if (i == 0) {
        sb.append(ObjectUtils.ARRAY_START);
      } else {
        sb.append(ObjectUtils.ARRAY_ELEMENT_SEPARATOR);
      }

      sb.append(array[i]);
    }
    sb.append(ObjectUtils.ARRAY_END);
    return sb.toString();
  }

  /**
   * Return a String representation of the contents of the specified array.
   * <p>
   * The String representation consists of a list of the array's elements, enclosed in curly braces
   * ({@code "{}"}). Adjacent elements are separated by the characters {@code ", "} (a comma
   * followed by a space). Returns {@code "null"} if {@code array} is {@code null}.
   *
   * @param array the array to build a String representation for
   * @return a String representation of {@code array}
   */
  public static String nullSafeToString(@Nullable final byte[] array) {
    if (array == null) {
      return ObjectUtils.NULL_STRING;
    }
    final int length = array.length;
    if (length == 0) {
      return ObjectUtils.EMPTY_ARRAY;
    }
    final StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i++) {
      if (i == 0) {
        sb.append(ObjectUtils.ARRAY_START);
      } else {
        sb.append(ObjectUtils.ARRAY_ELEMENT_SEPARATOR);
      }
      sb.append(array[i]);
    }
    sb.append(ObjectUtils.ARRAY_END);
    return sb.toString();
  }

  /**
   * Return a String representation of the contents of the specified array.
   * <p>
   * The String representation consists of a list of the array's elements, enclosed in curly braces
   * ({@code "{}"}). Adjacent elements are separated by the characters {@code ", "} (a comma
   * followed by a space). Returns {@code "null"} if {@code array} is {@code null}.
   *
   * @param array the array to build a String representation for
   * @return a String representation of {@code array}
   */
  public static String nullSafeToString(@Nullable final char[] array) {
    if (array == null) {
      return ObjectUtils.NULL_STRING;
    }
    final int length = array.length;
    if (length == 0) {
      return ObjectUtils.EMPTY_ARRAY;
    }
    final StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i++) {
      if (i == 0) {
        sb.append(ObjectUtils.ARRAY_START);
      } else {
        sb.append(ObjectUtils.ARRAY_ELEMENT_SEPARATOR);
      }
      sb.append("'").append(array[i]).append("'");
    }
    sb.append(ObjectUtils.ARRAY_END);
    return sb.toString();
  }

  /**
   * Return a String representation of the contents of the specified array.
   * <p>
   * The String representation consists of a list of the array's elements, enclosed in curly braces
   * ({@code "{}"}). Adjacent elements are separated by the characters {@code ", "} (a comma
   * followed by a space). Returns {@code "null"} if {@code array} is {@code null}.
   *
   * @param array the array to build a String representation for
   * @return a String representation of {@code array}
   */
  public static String nullSafeToString(@Nullable final double[] array) {
    if (array == null) {
      return ObjectUtils.NULL_STRING;
    }
    final int length = array.length;
    if (length == 0) {
      return ObjectUtils.EMPTY_ARRAY;
    }
    final StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i++) {
      if (i == 0) {
        sb.append(ObjectUtils.ARRAY_START);
      } else {
        sb.append(ObjectUtils.ARRAY_ELEMENT_SEPARATOR);
      }

      sb.append(array[i]);
    }
    sb.append(ObjectUtils.ARRAY_END);
    return sb.toString();
  }

  /**
   * Return a String representation of the contents of the specified array.
   * <p>
   * The String representation consists of a list of the array's elements, enclosed in curly braces
   * ({@code "{}"}). Adjacent elements are separated by the characters {@code ", "} (a comma
   * followed by a space). Returns {@code "null"} if {@code array} is {@code null}.
   *
   * @param array the array to build a String representation for
   * @return a String representation of {@code array}
   */
  public static String nullSafeToString(@Nullable final float[] array) {
    if (array == null) {
      return ObjectUtils.NULL_STRING;
    }
    final int length = array.length;
    if (length == 0) {
      return ObjectUtils.EMPTY_ARRAY;
    }
    final StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i++) {
      if (i == 0) {
        sb.append(ObjectUtils.ARRAY_START);
      } else {
        sb.append(ObjectUtils.ARRAY_ELEMENT_SEPARATOR);
      }

      sb.append(array[i]);
    }
    sb.append(ObjectUtils.ARRAY_END);
    return sb.toString();
  }

  /**
   * Return a String representation of the contents of the specified array.
   * <p>
   * The String representation consists of a list of the array's elements, enclosed in curly braces
   * ({@code "{}"}). Adjacent elements are separated by the characters {@code ", "} (a comma
   * followed by a space). Returns {@code "null"} if {@code array} is {@code null}.
   *
   * @param array the array to build a String representation for
   * @return a String representation of {@code array}
   */
  public static String nullSafeToString(@Nullable final int[] array) {
    if (array == null) {
      return ObjectUtils.NULL_STRING;
    }
    final int length = array.length;
    if (length == 0) {
      return ObjectUtils.EMPTY_ARRAY;
    }
    final StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i++) {
      if (i == 0) {
        sb.append(ObjectUtils.ARRAY_START);
      } else {
        sb.append(ObjectUtils.ARRAY_ELEMENT_SEPARATOR);
      }
      sb.append(array[i]);
    }
    sb.append(ObjectUtils.ARRAY_END);
    return sb.toString();
  }

  /**
   * Return a String representation of the contents of the specified array.
   * <p>
   * The String representation consists of a list of the array's elements, enclosed in curly braces
   * ({@code "{}"}). Adjacent elements are separated by the characters {@code ", "} (a comma
   * followed by a space). Returns {@code "null"} if {@code array} is {@code null}.
   *
   * @param array the array to build a String representation for
   * @return a String representation of {@code array}
   */
  public static String nullSafeToString(@Nullable final long[] array) {
    if (array == null) {
      return ObjectUtils.NULL_STRING;
    }
    final int length = array.length;
    if (length == 0) {
      return ObjectUtils.EMPTY_ARRAY;
    }
    final StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i++) {
      if (i == 0) {
        sb.append(ObjectUtils.ARRAY_START);
      } else {
        sb.append(ObjectUtils.ARRAY_ELEMENT_SEPARATOR);
      }
      sb.append(array[i]);
    }
    sb.append(ObjectUtils.ARRAY_END);
    return sb.toString();
  }

  /**
   * Return a String representation of the contents of the specified array.
   * <p>
   * The String representation consists of a list of the array's elements, enclosed in curly braces
   * ({@code "{}"}). Adjacent elements are separated by the characters {@code ", "} (a comma
   * followed by a space). Returns {@code "null"} if {@code array} is {@code null}.
   *
   * @param array the array to build a String representation for
   * @return a String representation of {@code array}
   */
  public static String nullSafeToString(@Nullable final short[] array) {
    if (array == null) {
      return ObjectUtils.NULL_STRING;
    }
    final int length = array.length;
    if (length == 0) {
      return ObjectUtils.EMPTY_ARRAY;
    }
    final StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i++) {
      if (i == 0) {
        sb.append(ObjectUtils.ARRAY_START);
      } else {
        sb.append(ObjectUtils.ARRAY_ELEMENT_SEPARATOR);
      }
      sb.append(array[i]);
    }
    sb.append(ObjectUtils.ARRAY_END);
    return sb.toString();
  }

}
