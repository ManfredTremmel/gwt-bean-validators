/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package org.hibernate.validator.internal.util;

/**
 * @author Gunnar Morling
 * @author Hardy Ferentschik
 * @author Kevin Pollet &lt;kevin.pollet@serli.com&gt; (C) 2012 SERLI
 * @author Manfred Tremmel GWT port
 */
public final class Contracts {

  private Contracts() {
    super();
  }

  public static void assertNotNull(final Object pobject) {
    assertNotNull(pobject, "must not be null.");
  }

  /**
   * Asserts that the given object is not {@code null}.
   *
   * @param pobject The object to check.
   * @param pmessage A message text which will be used as message of the resulting exception if the
   *        given object is {@code null}.
   *
   * @throws IllegalArgumentException In case the given object is {@code null}.
   */
  public static void assertNotNull(final Object pobject, final String pmessage) {
    if (pobject == null) {
      throw new IllegalArgumentException(pmessage);
    }
  }

  /**
   * Asserts that the given object is not {@code null}.
   *
   * @param pobject The object to check.
   * @param pname The name of the value to check. A message of the form
   *        "&lt;name&gt; must not be null" will be used as message of the resulting exception if
   *        the given object is {@code null}.
   *
   * @throws IllegalArgumentException In case the given object is {@code null}.
   */
  public static void assertValueNotNull(final Object pobject, final String pname) {
    if (pobject == null) {
      throw new IllegalArgumentException(pname + " must not be null.");
    }
  }

  /**
   * check if pcondition is true, otherwise throw exception.
   *
   * @param pcondition boolean value to check
   * @param pmessage error message to throw if test fails
   */
  public static void assertTrue(final boolean pcondition, final String pmessage) {
    if (!pcondition) {
      throw new IllegalArgumentException(pmessage);
    }
  }

  /**
   * check if a string is not empty.
   *
   * @param pstring string to check
   * @param pmessage error message to throw if test fails
   */
  public static void assertNotEmpty(final String pstring, final String pmessage) {
    if (pstring == null || pstring.length() == 0) {
      throw new IllegalArgumentException(pmessage);
    }
  }
}
