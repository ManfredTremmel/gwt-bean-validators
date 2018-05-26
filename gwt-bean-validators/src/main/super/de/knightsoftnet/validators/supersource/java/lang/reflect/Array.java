/*
 * Copyright (c) 1996, 2013, Oracle and/or its affiliates. All rights reserved. DO NOT ALTER OR
 * REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under the terms of the GNU
 * General Public License version 2 only, as published by the Free Software Foundation. Oracle
 * designates this particular file as subject to the "Classpath" exception as provided by Oracle in
 * the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version 2 along with this work;
 * if not, write to the Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA or visit www.oracle.com
 * if you need additional information or have any questions.
 */

package java.lang.reflect;

/**
 * The {@code Array} class provides static methods to dynamically create and access Java arrays.
 *
 * <p>
 * {@code Array} permits widening conversions to occur during a get or set operation, but throws an
 * {@code IllegalArgumentException} if a narrowing conversion would occur.
 * </p>
 *
 * @author Nakul Saraiya
 * @author Manfred Tremmel - GWT "implementation"
 */
public final class Array {

  /**
   * Constructor. Class Array is not instantiable.
   */
  private Array() {
    super();
  }

  /**
   * Creates a new array with the specified component type and length. Invoking this method is
   * equivalent to creating an array as follows: <blockquote>
   *
   * <pre>
   * int[] x = {length};
   * Array.newInstance(componentType, x);
   * </pre>
   *
   * </blockquote>
   *
   * <p>
   * The number of dimensions of the new array must not exceed 255.
   * </p>
   *
   * @param componentType the {@code Class} object representing the component type of the new array
   * @param length the length of the new array
   * @return the new array
   * @exception NullPointerException if the specified {@code componentType} parameter is null
   * @exception IllegalArgumentException if componentType is {@link Void#TYPE} or if the number of
   *            dimensions of the requested array instance exceed 255.
   * @exception NegativeArraySizeException if the specified {@code length} is negative
   */
  public static Object newInstance(final Class<?> componentType, final int length)
      throws NegativeArraySizeException {
    throw new UnsupportedOperationException(
        "GWT java.lang.reflect.Array does not support newInstance()");
  }

  /**
   * Creates a new array with the specified component type and dimensions. If {@code componentType}
   * represents a non-array class or interface, the new array has {@code dimensions.length}
   * dimensions and {@code componentType} as its component type. If {@code componentType} represents
   * an array class, the number of dimensions of the new array is equal to the sum of
   * {@code dimensions.length} and the number of dimensions of {@code componentType}. In this case,
   * the component type of the new array is the component type of {@code componentType}.
   *
   * <p>
   * The number of dimensions of the new array must not exceed 255.
   * </p>
   *
   * @param componentType the {@code Class} object representing the component type of the new array
   * @param dimensions an array of {@code int} representing the dimensions of the new array
   * @return the new array
   * @exception NullPointerException if the specified {@code componentType} argument is null
   * @exception IllegalArgumentException if the specified {@code dimensions} argument is a
   *            zero-dimensional array, if componentType is {@link Void#TYPE}, or if the number of
   *            dimensions of the requested array instance exceed 255.
   * @exception NegativeArraySizeException if any of the components in the specified
   *            {@code dimensions} argument is negative.
   */
  public static Object newInstance(final Class<?> componentType, final int... dimensions)
      throws IllegalArgumentException, NegativeArraySizeException {
    throw new UnsupportedOperationException(
        "GWT java.lang.reflect.Array does not support newInstance()");
  }

  /**
   * Returns the length of the specified array object, as an {@code int}.
   *
   * @param array the array
   * @return the length of the array
   * @exception IllegalArgumentException if the object argument is not an array
   */
  public static native int getLength(Object array) throws IllegalArgumentException /*-{
    @java.lang.reflect.Array::assertIsArray(Ljava/lang/Object;)(array);
    return array.length;
  }-*/;

  /**
   * Returns the value of the indexed component in the specified array object. The value is
   * automatically wrapped in an object if it has a primitive type.
   *
   * @param array the array
   * @param index the index
   * @return the (possibly wrapped) value of the indexed component in the specified array
   * @exception NullPointerException If the specified object is null
   * @exception IllegalArgumentException If the specified object is not an array
   * @exception ArrayIndexOutOfBoundsException If the specified {@code index} argument is negative,
   *            or if it is greater than or equal to the length of the specified array
   */
  public static Object get(final Object array, final int index)
      throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
    throw new UnsupportedOperationException("GWT java.lang.reflect.Array does not support get()");
  }

  /**
   * Returns the value of the indexed component in the specified array object, as a {@code boolean}.
   *
   * @param array the array
   * @param index the index
   * @return the value of the indexed component in the specified array
   * @exception NullPointerException If the specified object is null
   * @exception IllegalArgumentException If the specified object is not an array, or if the indexed
   *            element cannot be converted to the return type by an identity or widening conversion
   * @exception ArrayIndexOutOfBoundsException If the specified {@code index} argument is negative,
   *            or if it is greater than or equal to the length of the specified array
   * @see Array#get
   */
  public static boolean getBoolean(final Object array, final int index)
      throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
    throw new UnsupportedOperationException(
        "GWT java.lang.reflect.Array does not support getBoolean()");
  }

  /**
   * Returns the value of the indexed component in the specified array object, as a {@code byte}.
   *
   * @param array the array
   * @param index the index
   * @return the value of the indexed component in the specified array
   * @exception NullPointerException If the specified object is null
   * @exception IllegalArgumentException If the specified object is not an array, or if the indexed
   *            element cannot be converted to the return type by an identity or widening conversion
   * @exception ArrayIndexOutOfBoundsException If the specified {@code index} argument is negative,
   *            or if it is greater than or equal to the length of the specified array
   * @see Array#get
   */
  public static byte getByte(final Object array, final int index)
      throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
    throw new UnsupportedOperationException(
        "GWT java.lang.reflect.Array does not support getByte()");
  }

  /**
   * Returns the value of the indexed component in the specified array object, as a {@code char}.
   *
   * @param array the array
   * @param index the index
   * @return the value of the indexed component in the specified array
   * @exception NullPointerException If the specified object is null
   * @exception IllegalArgumentException If the specified object is not an array, or if the indexed
   *            element cannot be converted to the return type by an identity or widening conversion
   * @exception ArrayIndexOutOfBoundsException If the specified {@code index} argument is negative,
   *            or if it is greater than or equal to the length of the specified array
   * @see Array#get
   */
  public static char getChar(final Object array, final int index)
      throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
    throw new UnsupportedOperationException(
        "GWT java.lang.reflect.Array does not support getChar()");
  }

  /**
   * Returns the value of the indexed component in the specified array object, as a {@code short}.
   *
   * @param array the array
   * @param index the index
   * @return the value of the indexed component in the specified array
   * @exception NullPointerException If the specified object is null
   * @exception IllegalArgumentException If the specified object is not an array, or if the indexed
   *            element cannot be converted to the return type by an identity or widening conversion
   * @exception ArrayIndexOutOfBoundsException If the specified {@code index} argument is negative,
   *            or if it is greater than or equal to the length of the specified array
   * @see Array#get
   */
  public static short getShort(final Object array, final int index) // NOPMD
      throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
    throw new UnsupportedOperationException(
        "GWT java.lang.reflect.Array does not support getShort()");
  }

  /**
   * Returns the value of the indexed component in the specified array object, as an {@code int}.
   *
   * @param array the array
   * @param index the index
   * @return the value of the indexed component in the specified array
   * @exception NullPointerException If the specified object is null
   * @exception IllegalArgumentException If the specified object is not an array, or if the indexed
   *            element cannot be converted to the return type by an identity or widening conversion
   * @exception ArrayIndexOutOfBoundsException If the specified {@code index} argument is negative,
   *            or if it is greater than or equal to the length of the specified array
   * @see Array#get
   */
  public static int getInt(final Object array, final int index)
      throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
    throw new UnsupportedOperationException(
        "GWT java.lang.reflect.Array does not support getInt()");
  }

  /**
   * Returns the value of the indexed component in the specified array object, as a {@code long}.
   *
   * @param array the array
   * @param index the index
   * @return the value of the indexed component in the specified array
   * @exception NullPointerException If the specified object is null
   * @exception IllegalArgumentException If the specified object is not an array, or if the indexed
   *            element cannot be converted to the return type by an identity or widening conversion
   * @exception ArrayIndexOutOfBoundsException If the specified {@code index} argument is negative,
   *            or if it is greater than or equal to the length of the specified array
   * @see Array#get
   */
  public static long getLong(final Object array, final int index)
      throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
    throw new UnsupportedOperationException(
        "GWT java.lang.reflect.Array does not support getLong()");
  }

  /**
   * Returns the value of the indexed component in the specified array object, as a {@code float}.
   *
   * @param array the array
   * @param index the index
   * @return the value of the indexed component in the specified array
   * @exception NullPointerException If the specified object is null
   * @exception IllegalArgumentException If the specified object is not an array, or if the indexed
   *            element cannot be converted to the return type by an identity or widening conversion
   * @exception ArrayIndexOutOfBoundsException If the specified {@code index} argument is negative,
   *            or if it is greater than or equal to the length of the specified array
   * @see Array#get
   */
  public static float getFloat(final Object array, final int index)
      throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
    throw new UnsupportedOperationException(
        "GWT java.lang.reflect.Array does not support getFloat()");
  }

  /**
   * Returns the value of the indexed component in the specified array object, as a {@code double}.
   *
   * @param array the array
   * @param index the index
   * @return the value of the indexed component in the specified array
   * @exception NullPointerException If the specified object is null
   * @exception IllegalArgumentException If the specified object is not an array, or if the indexed
   *            element cannot be converted to the return type by an identity or widening conversion
   * @exception ArrayIndexOutOfBoundsException If the specified {@code index} argument is negative,
   *            or if it is greater than or equal to the length of the specified array
   * @see Array#get
   */
  public static double getDouble(final Object array, final int index)
      throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
    throw new UnsupportedOperationException(
        "GWT java.lang.reflect.Array does not support getDouble()");
  }

  /**
   * Sets the value of the indexed component of the specified array object to the specified new
   * value. The new value is first automatically unwrapped if the array has a primitive component
   * type.
   *
   * @param array the array
   * @param index the index into the array
   * @param value the new value of the indexed component
   * @exception NullPointerException If the specified object argument is null
   * @exception IllegalArgumentException If the specified object argument is not an array, or if the
   *            array component type is primitive and an unwrapping conversion fails
   * @exception ArrayIndexOutOfBoundsException If the specified {@code index} argument is negative,
   *            or if it is greater than or equal to the length of the specified array
   */
  public static void set(final Object array, final int index, final Object value)
      throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
    throw new UnsupportedOperationException("GWT java.lang.reflect.Array does not support set()");
  }

  /**
   * Sets the value of the indexed component of the specified array object to the specified
   * {@code boolean} value.
   *
   * @param array the array
   * @param index the index into the array
   * @param pboolean the new value of the indexed component
   * @exception NullPointerException If the specified object argument is null
   * @exception IllegalArgumentException If the specified object argument is not an array, or if the
   *            specified value cannot be converted to the underlying array's component type by an
   *            identity or a primitive widening conversion
   * @exception ArrayIndexOutOfBoundsException If the specified {@code index} argument is negative,
   *            or if it is greater than or equal to the length of the specified array
   * @see Array#set
   */
  public static void setBoolean(final Object array, final int index, final boolean pboolean)
      throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
    throw new UnsupportedOperationException(
        "GWT java.lang.reflect.Array does not support setBoolean()");
  }

  /**
   * Sets the value of the indexed component of the specified array object to the specified
   * {@code byte} value.
   *
   * @param array the array
   * @param index the index into the array
   * @param pbyte the new value of the indexed component
   * @exception NullPointerException If the specified object argument is null
   * @exception IllegalArgumentException If the specified object argument is not an array, or if the
   *            specified value cannot be converted to the underlying array's component type by an
   *            identity or a primitive widening conversion
   * @exception ArrayIndexOutOfBoundsException If the specified {@code index} argument is negative,
   *            or if it is greater than or equal to the length of the specified array
   * @see Array#set
   */
  public static void setByte(final Object array, final int index, final byte pbyte)
      throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
    throw new UnsupportedOperationException(
        "GWT java.lang.reflect.Array does not support setByte()");
  }

  /**
   * Sets the value of the indexed component of the specified array object to the specified
   * {@code char} value.
   *
   * @param array the array
   * @param index the index into the array
   * @param pchar the new value of the indexed component
   * @exception NullPointerException If the specified object argument is null
   * @exception IllegalArgumentException If the specified object argument is not an array, or if the
   *            specified value cannot be converted to the underlying array's component type by an
   *            identity or a primitive widening conversion
   * @exception ArrayIndexOutOfBoundsException If the specified {@code index} argument is negative,
   *            or if it is greater than or equal to the length of the specified array
   * @see Array#set
   */
  public static void setChar(final Object array, final int index, final char pchar)
      throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
    throw new UnsupportedOperationException(
        "GWT java.lang.reflect.Array does not support setChar()");
  }

  /**
   * Sets the value of the indexed component of the specified array object to the specified
   * {@code short} value.
   *
   * @param array the array
   * @param index the index into the array
   * @param pshort the new value of the indexed component
   * @exception NullPointerException If the specified object argument is null
   * @exception IllegalArgumentException If the specified object argument is not an array, or if the
   *            specified value cannot be converted to the underlying array's component type by an
   *            identity or a primitive widening conversion
   * @exception ArrayIndexOutOfBoundsException If the specified {@code index} argument is negative,
   *            or if it is greater than or equal to the length of the specified array
   * @see Array#set
   */
  public static void setShort(final Object array, final int index, final short pshort) // NOPMD
      throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
    throw new UnsupportedOperationException(
        "GWT java.lang.reflect.Array does not support setShort()");
  }

  /**
   * Sets the value of the indexed component of the specified array object to the specified
   * {@code int} value.
   *
   * @param array the array
   * @param index the index into the array
   * @param pint the new value of the indexed component
   * @exception NullPointerException If the specified object argument is null
   * @exception IllegalArgumentException If the specified object argument is not an array, or if the
   *            specified value cannot be converted to the underlying array's component type by an
   *            identity or a primitive widening conversion
   * @exception ArrayIndexOutOfBoundsException If the specified {@code index} argument is negative,
   *            or if it is greater than or equal to the length of the specified array
   * @see Array#set
   */
  public static void setInt(final Object array, final int index, final int pint)
      throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
    throw new UnsupportedOperationException(
        "GWT java.lang.reflect.Array does not support setInt()");
  }

  /**
   * Sets the value of the indexed component of the specified array object to the specified
   * {@code long} value.
   *
   * @param array the array
   * @param index the index into the array
   * @param plong the new value of the indexed component
   * @exception NullPointerException If the specified object argument is null
   * @exception IllegalArgumentException If the specified object argument is not an array, or if the
   *            specified value cannot be converted to the underlying array's component type by an
   *            identity or a primitive widening conversion
   * @exception ArrayIndexOutOfBoundsException If the specified {@code index} argument is negative,
   *            or if it is greater than or equal to the length of the specified array
   * @see Array#set
   */
  public static void setLong(final Object array, final int index, final long plong)
      throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
    throw new UnsupportedOperationException(
        "GWT java.lang.reflect.Array does not support setLong()");
  }

  /**
   * Sets the value of the indexed component of the specified array object to the specified
   * {@code float} value.
   *
   * @param array the array
   * @param index the index into the array
   * @param pfloat the new value of the indexed component
   * @exception NullPointerException If the specified object argument is null
   * @exception IllegalArgumentException If the specified object argument is not an array, or if the
   *            specified value cannot be converted to the underlying array's component type by an
   *            identity or a primitive widening conversion
   * @exception ArrayIndexOutOfBoundsException If the specified {@code index} argument is negative,
   *            or if it is greater than or equal to the length of the specified array
   * @see Array#set
   */
  public static void setFloat(final Object array, final int index, final float pfloat)
      throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
    throw new UnsupportedOperationException(
        "GWT java.lang.reflect.Array does not support setFloat()");
  }

  /**
   * Sets the value of the indexed component of the specified array object to the specified
   * {@code double} value.
   *
   * @param array the array
   * @param index the index into the array
   * @param pdouble the new value of the indexed component
   * @exception NullPointerException If the specified object argument is null
   * @exception IllegalArgumentException If the specified object argument is not an array, or if the
   *            specified value cannot be converted to the underlying array's component type by an
   *            identity or a primitive widening conversion
   * @exception ArrayIndexOutOfBoundsException If the specified {@code index} argument is negative,
   *            or if it is greater than or equal to the length of the specified array
   * @see Array#set
   */
  public static void setDouble(final Object array, final int index, final double pdouble)
      throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
    throw new UnsupportedOperationException(
        "GWT java.lang.reflect.Array does not support setDouble()");
  }

  /*
   * Private
   */

  private static void assertIsArray(final Object pobject) {
    assert pobject != null : new NullPointerException();
    assert pobject.getClass().getComponentType() != null : "Not an array class: "
        + pobject.getClass() + " '" + pobject + "'";
  }
}
