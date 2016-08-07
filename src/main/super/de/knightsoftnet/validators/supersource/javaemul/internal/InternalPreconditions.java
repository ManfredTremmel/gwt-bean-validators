/*
 * Copyright 2014 Google Inc.
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

package javaemul.internal;

import static java.lang.System.getProperty;

import java.util.NoSuchElementException;

/**
 * A utility class that provides utility functions to do precondition checks inside GWT-SDK.
 * <p>
 * Following table summarizes the grouping of the checks:
 *
 * <pre>
 * ┌────────┬─────────────────────────────────────────────────────┬───────────────────────────────┐
 * │Group   │Description                                          │Common Exception Types         │
 * ├────────┼─────────────────────────────────────────────────────┼───────────────────────────────┤
 * │BOUNDS  │Checks related to the bound checking in collections. │IndexOutBoundsException        │
 * │        │                                                     │ArrayIndexOutOfBoundsException │
 * ├────────┼─────────────────────────────────────────────────────┼───────────────────────────────┤
 * │API     │Checks related to the correct usage of APIs.         │IllegalStateException          │
 * │        │                                                     │NoSuchElementException         │
 * │        │                                                     │NullPointerException           │
 * │        │                                                     │IllegalArgumentException       │
 * │        │                                                     │ConcurrentModificationException│
 * ├────────┼─────────────────────────────────────────────────────┼───────────────────────────────┤
 * │NUMERIC │Checks related to numeric operations.                │ArithmeticException            │
 * ├────────┼─────────────────────────────────────────────────────┼───────────────────────────────┤
 * │TYPE    │Checks related to java type system.                  │ClassCastException             │
 * │        │                                                     │ArrayStoreException            │
 * ├────────┼─────────────────────────────────────────────────────┼───────────────────────────────┤
 * │CRITICAL│Checks for cases where not failing-fast will keep    │IllegalArgumentException       │
 * │        │the object in an inconsistent state and/or degrade   │                               │
 * │        │debugging significantly. Currently disabling these   │                               │
 * │        │checks is not supported.                             │                               │
 * └────────┴─────────────────────────────────────────────────────┴───────────────────────────────┘
 * </pre>
 * </p>
 *
 * <p>
 * Following table summarizes predefined check levels:
 *
 * <pre>
 * ┌────────────────┬──────────┬─────────┬─────────┬─────────┬─────────┐
 * │Check level     │  BOUNDS  │   API   │ NUMERIC |  TYPE   │CRITICAL │
 * ├────────────────┼──────────┼─────────┼─────────┼─────────┼─────────┤
 * │Normal (default)│    X     │    X    │    X    │    X    │    X    │
 * ├────────────────┼──────────┼─────────┼─────────┼─────────┼─────────┤
 * │Optimized       │          │         │         │    X    │    X    │
 * ├────────────────┼──────────┼─────────┼─────────┼─────────┼─────────┤
 * │Minimal         │          │         │         │         │    X    │
 * ├────────────────┼──────────┼─────────┼─────────┼─────────┼─────────┤
 * │None (N/A yet)  │          │         │         │         │         │
 * └────────────────┴──────────┴─────────┴─────────┴─────────┴─────────┘
 * </pre>
 * </p>
 *
 * <p>
 * Please note that, in development mode (jre.checkedMode=ENABLED), these checks will always be
 * performed regardless of configuration but will be converted to AssertionError if check is
 * disabled. This so that any reliance on related exceptions could be detected early on. For this
 * detection to work properly; it is important for apps to share the same config in all
 * environments.
 * </p>
 */
// Some parts adapted from Guava
public final class InternalPreconditions {

  private static final String CHECK_TYPE = getProperty("jre.checks.type");
  private static final String CHECK_NUMERIC = getProperty("jre.checks.numeric");
  private static final String CHECK_BOUNDS = getProperty("jre.checks.bounds");
  private static final String CHECK_API = getProperty("jre.checks.api");

  // NORMAL
  private static final boolean LEVEL_NORMAL_OR_HIGHER =
      getProperty("jre.checks.checkLevel").equals("NORMAL");
  // NORMAL or OPTIMIZED
  private static final boolean LEVEL_OPT_OR_HIGHER =
      getProperty("jre.checks.checkLevel").equals("OPTIMIZED") || LEVEL_NORMAL_OR_HIGHER;
  // NORMAL or OPTIMIZED or MINIMAL
  private static final boolean LEVEL_MINIMAL_OR_HIGHER =
      getProperty("jre.checks.checkLevel").equals("MINIMAL") || LEVEL_OPT_OR_HIGHER;

  static {
    if (!LEVEL_MINIMAL_OR_HIGHER) {
      throw new IllegalStateException("Incorrect level: " + getProperty("jre.checks.checkLevel"));
    }
  }

  private static final boolean IS_TYPE_CHECKED =
      "AUTO".equals(CHECK_TYPE) && LEVEL_OPT_OR_HIGHER || "ENABLED".equals(CHECK_TYPE);
  private static final boolean IS_BOUNDS_CHECKED =
      "AUTO".equals(CHECK_BOUNDS) && LEVEL_NORMAL_OR_HIGHER || "ENABLED".equals(CHECK_BOUNDS);
  private static final boolean IS_API_CHECKED =
      "AUTO".equals(CHECK_API) && LEVEL_NORMAL_OR_HIGHER || "ENABLED".equals(CHECK_API);
  private static final boolean IS_NUMERIC_CHECKED =
      "AUTO".equals(CHECK_NUMERIC) && LEVEL_NORMAL_OR_HIGHER || "ENABLED".equals(CHECK_NUMERIC);

  private static final boolean IS_ASSERTED = "ENABLED".equals(getProperty("jre.checkedMode"));

  /**
   * This method reports if the code is compiled with type checks. It must be used in places where
   * code can be replaced with a simpler one when we know that no checks will occur. See
   * {@link System#arraycopy(Object, int, Object, int, int)} for example. Please note that
   * {@link #checkType(boolean)} should be preferred where feasible.
   */
  public static boolean isTypeChecked() {
    return IS_TYPE_CHECKED || IS_ASSERTED;
  }

  /**
   * This method reports if the code is compiled with api checks. It must be used in places where
   * code can be replaced with a simpler one when we know that no checks will occur. Please note
   * that {@code #checkXXX(boolean)} should be preferred where feasible.
   */
  public static boolean isApiChecked() {
    return IS_API_CHECKED || IS_ASSERTED;
  }

  /**
   * check type.
   *
   * @param expression true if expression
   */
  public static void checkType(final boolean expression) {
    if (IS_TYPE_CHECKED) {
      checkCriticalType(expression);
    } else if (IS_ASSERTED) {
      try {
        checkCriticalType(expression);
      } catch (final Exception e) {
        throw new AssertionError(e);
      }
    }
  }

  /**
   * check critical type.
   *
   * @param expression true if expression
   */
  public static void checkCriticalType(final boolean expression) {
    if (!expression) {
      throw new ClassCastException();
    }
  }

  /**
   * Ensures the truth of an expression that verifies array type.
   */
  public static void checkArrayType(final boolean expression) {
    if (IS_TYPE_CHECKED) {
      checkCriticalArrayType(expression);
    } else if (IS_ASSERTED) {
      try {
        checkCriticalArrayType(expression);
      } catch (final Exception e) {
        throw new AssertionError(e);
      }
    }
  }

  /**
   * Ensures the truth of an expression that verifies array type.
   */
  public static void checkArrayType(final boolean expression, final Object errorMessage) {
    if (IS_TYPE_CHECKED) {
      checkCriticalArrayType(expression, errorMessage);
    } else if (IS_ASSERTED) {
      try {
        checkCriticalArrayType(expression, errorMessage);
      } catch (final Exception e) {
        throw new AssertionError(e);
      }
    }
  }

  /**
   * check criterial array type.
   *
   * @param expression true if expression
   */
  public static void checkCriticalArrayType(final boolean expression) {
    if (!expression) {
      throw new ArrayStoreException();
    }
  }

  /**
   * check criterial array type.
   *
   * @param expression true if expression
   * @param errorMessage if expression is not true
   */
  public static void checkCriticalArrayType(final boolean expression, final Object errorMessage) {
    if (!expression) {
      throw new ArrayStoreException(String.valueOf(errorMessage));
    }
  }

  /**
   * check arithmetic.
   *
   * @param expression true if expression
   */
  public static void checkArithmetic(final boolean expression) {
    if (IS_NUMERIC_CHECKED) {
      checkCriticalArithmetic(expression);
    } else if (IS_ASSERTED) {
      try {
        checkCriticalArithmetic(expression);
      } catch (final Exception e) {
        throw new AssertionError(e);
      }
    }
  }

  /**
   * check critical arithmetic.
   *
   * @param expression true if expression
   */
  public static void checkCriticalArithmetic(final boolean expression) {
    if (!expression) {
      throw new ArithmeticException();
    }
  }

  /**
   * Ensures the truth of an expression involving existence of an element.
   */
  public static void checkElement(final boolean expression) {
    if (IS_API_CHECKED) {
      checkCriticalElement(expression);
    } else if (IS_ASSERTED) {
      try {
        checkCriticalElement(expression);
      } catch (final Exception e) {
        throw new AssertionError(e);
      }
    }
  }

  /**
   * Ensures the truth of an expression involving existence of an element.
   */
  public static void checkElement(final boolean expression, final Object errorMessage) {
    if (IS_API_CHECKED) {
      checkCriticalElement(expression, errorMessage);
    } else if (IS_ASSERTED) {
      try {
        checkCriticalElement(expression, errorMessage);
      } catch (final Exception e) {
        throw new AssertionError(e);
      }
    }
  }

  /**
   * Ensures the truth of an expression involving existence of an element.
   * <p>
   * For cases where failing fast is pretty important and not failing early could cause bugs that
   * are much harder to debug.
   * </p>
   */
  public static void checkCriticalElement(final boolean expression) {
    if (!expression) {
      throw new NoSuchElementException();
    }
  }

  /**
   * Ensures the truth of an expression involving existence of an element.
   * <p>
   * For cases where failing fast is pretty important and not failing early could cause bugs that
   * are much harder to debug.
   * </p>
   */
  public static void checkCriticalElement(final boolean expression, final Object errorMessage) {
    if (!expression) {
      throw new NoSuchElementException(String.valueOf(errorMessage));
    }
  }

  /**
   * Ensures the truth of an expression involving one or more parameters to the calling method.
   */
  public static void checkArgument(final boolean expression) {
    if (IS_API_CHECKED) {
      checkCriticalArgument(expression);
    } else if (IS_ASSERTED) {
      try {
        checkCriticalArgument(expression);
      } catch (final Exception e) {
        throw new AssertionError(e);
      }
    }
  }

  /**
   * Ensures the truth of an expression involving one or more parameters to the calling method.
   */
  public static void checkArgument(final boolean expression, final Object errorMessage) {
    if (IS_API_CHECKED) {
      checkCriticalArgument(expression, errorMessage);
    } else if (IS_ASSERTED) {
      try {
        checkCriticalArgument(expression, errorMessage);
      } catch (final Exception e) {
        throw new AssertionError(e);
      }
    }
  }

  /**
   * Ensures the truth of an expression involving one or more parameters to the calling method.
   */
  public static void checkArgument(final boolean expression, final String errorMessageTemplate,
      final Object... errorMessageArgs) {
    if (IS_API_CHECKED) {
      checkCriticalArgument(expression, errorMessageTemplate, errorMessageArgs);
    } else if (IS_ASSERTED) {
      try {
        checkCriticalArgument(expression, errorMessageTemplate, errorMessageArgs);
      } catch (final Exception e) {
        throw new AssertionError(e);
      }
    }
  }

  /**
   * Ensures the truth of an expression involving one or more parameters to the calling method.
   * <p>
   * For cases where failing fast is pretty important and not failing early could cause bugs that
   * are much harder to debug.
   * </p>
   */
  public static void checkCriticalArgument(final boolean expression) {
    if (!expression) {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Ensures the truth of an expression involving one or more parameters to the calling method.
   * <p>
   * For cases where failing fast is pretty important and not failing early could cause bugs that
   * are much harder to debug.
   * </p>
   */
  public static void checkCriticalArgument(final boolean expression, final Object errorMessage) {
    if (!expression) {
      throw new IllegalArgumentException(String.valueOf(errorMessage));
    }
  }

  /**
   * Ensures the truth of an expression involving one or more parameters to the calling method.
   * <p>
   * For cases where failing fast is pretty important and not failing early could cause bugs that
   * are much harder to debug.
   * </p>
   */
  public static void checkCriticalArgument(final boolean expression,
      final String errorMessageTemplate, final Object... errorMessageArgs) {
    if (!expression) {
      throw new IllegalArgumentException(format(errorMessageTemplate, errorMessageArgs));
    }
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   *
   * @param expression a boolean expression
   * @throws IllegalStateException if {@code expression} is false
   */
  public static void checkState(final boolean expression) {
    if (IS_API_CHECKED) {
      checkCriticalState(expression);
    } else if (IS_ASSERTED) {
      try {
        checkCriticalState(expression);
      } catch (final Exception e) {
        throw new AssertionError(e);
      }
    }
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   */
  public static void checkState(final boolean expression, final Object errorMessage) {
    if (IS_API_CHECKED) {
      checkCriticalState(expression, errorMessage);
    } else if (IS_ASSERTED) {
      try {
        checkCriticalState(expression, errorMessage);
      } catch (final Exception e) {
        throw new AssertionError(e);
      }
    }
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   * <p>
   * For cases where failing fast is pretty important and not failing early could cause bugs that
   * are much harder to debug.
   * </p>
   */
  public static void checkCriticalState(final boolean expression) {
    if (!expression) {
      throw new IllegalStateException();
    }
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   */
  public static void checkCriticalState(final boolean expression, final Object errorMessage) {
    if (!expression) {
      throw new IllegalStateException(String.valueOf(errorMessage));
    }
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   */
  public static <T> T checkNotNull(final T reference) {
    if (IS_API_CHECKED) {
      checkCriticalNotNull(reference);
    } else if (IS_ASSERTED) {
      try {
        checkCriticalNotNull(reference);
      } catch (final Exception e) {
        throw new AssertionError(e);
      }
    }

    return reference;
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   */
  public static void checkNotNull(final Object reference, final Object errorMessage) {
    if (IS_API_CHECKED) {
      checkCriticalNotNull(reference, errorMessage);
    } else if (IS_ASSERTED) {
      try {
        checkCriticalNotNull(reference, errorMessage);
      } catch (final Exception e) {
        throw new AssertionError(e);
      }
    }
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   */
  public static <T> T checkCriticalNotNull(final T reference) {
    if (reference == null) {
      throw new NullPointerException();
    }
    return reference;
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   */
  public static void checkCriticalNotNull(final Object reference, final Object errorMessage) {
    if (reference == null) {
      throw new NullPointerException(String.valueOf(errorMessage));
    }
  }

  /**
   * Ensures that {@code size} specifies a valid array size (i.e. non-negative).
   */
  public static void checkArraySize(final int size) {
    if (IS_API_CHECKED) {
      checkCriticalArraySize(size);
    } else if (IS_ASSERTED) {
      try {
        checkCriticalArraySize(size);
      } catch (final Exception e) {
        throw new AssertionError(e);
      }
    }
  }

  /**
   * Ensures that {@code size} specifies a valid array size (i.e. non-negative).
   */
  public static void checkCriticalArraySize(final int size) {
    if (size < 0) {
      throw new NegativeArraySizeException("Negative array size: " + size);
    }
  }

  /**
   * Ensures that {@code index} specifies a valid <i>element</i> in a list or string of size
   * {@code size}. An element index may range from zero, inclusive, to {@code size}, exclusive.
   */
  public static void checkElementIndex(final int index, final int size) {
    if (IS_BOUNDS_CHECKED) {
      checkCriticalElementIndex(index, size);
    } else if (IS_ASSERTED) {
      try {
        checkCriticalElementIndex(index, size);
      } catch (final Exception e) {
        throw new AssertionError(e);
      }
    }
  }

  /**
   * Ensures that {@code index} specifies a valid <i>element</i> in a list or string of size
   * {@code size}. An element index may range from zero, inclusive, to {@code size}, exclusive.
   */
  public static void checkCriticalElementIndex(final int index, final int size) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
  }

  /**
   * Ensures that {@code index} specifies a valid <i>position</i> in a list of size {@code size}. A
   * position index may range from zero to {@code size}, inclusive.
   */
  public static void checkPositionIndex(final int index, final int size) {
    if (IS_BOUNDS_CHECKED) {
      checkCriticalPositionIndex(index, size);
    } else if (IS_ASSERTED) {
      try {
        checkCriticalPositionIndex(index, size);
      } catch (final Exception e) {
        throw new AssertionError(e);
      }
    }
  }

  /**
   * Ensures that {@code index} specifies a valid <i>position</i> in a list of size {@code size}. A
   * position index may range from zero to {@code size}, inclusive.
   */
  public static void checkCriticalPositionIndex(final int index, final int size) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
  }

  /**
   * Ensures that {@code start} and {@code end} specify a valid <i>positions</i> in a list of size
   * {@code size}, and are in order. A position index may range from zero to {@code size},
   * inclusive.
   */
  public static void checkPositionIndexes(final int start, final int end, final int size) {
    if (IS_BOUNDS_CHECKED) {
      checkCriticalPositionIndexes(start, end, size);
    } else if (IS_ASSERTED) {
      try {
        checkCriticalPositionIndexes(start, end, size);
      } catch (final Exception e) {
        throw new AssertionError(e);
      }
    }
  }

  /**
   * Ensures that {@code start} and {@code end} specify a valid <i>positions</i> in a list of size
   * {@code size}, and are in order. A position index may range from zero to {@code size},
   * inclusive.
   */
  public static void checkCriticalPositionIndexes(final int start, final int end, final int size) {
    if (start < 0 || end > size) {
      throw new IndexOutOfBoundsException(
          "fromIndex: " + start + ", toIndex: " + end + ", size: " + size);
    }
    if (start > end) {
      throw new IllegalArgumentException("fromIndex: " + start + " > toIndex: " + end);
    }
  }

  /**
   * Checks that array bounds are correct.
   *
   * @throws IllegalArgumentException if {@code start > end}
   * @throws ArrayIndexOutOfBoundsException if the range is not legal
   */
  public static void checkCriticalArrayBounds(final int start, final int end, final int length) {
    if (start > end) {
      throw new IllegalArgumentException("fromIndex: " + start + " > toIndex: " + end);
    }
    if (start < 0 || end > length) {
      throw new ArrayIndexOutOfBoundsException(
          "fromIndex: " + start + ", toIndex: " + end + ", length: " + length);
    }
  }

  /**
   * Checks that string bounds are correct.
   *
   * @throws StringIndexOutOfBoundsException if the range is not legal
   */
  public static void checkCriticalStringBounds(final int start, final int end, final int length) {
    if (start < 0 || end > length || end < start) {
      throw new StringIndexOutOfBoundsException(
          "fromIndex: " + start + ", toIndex: " + end + ", length: " + length);
    }
  }

  /**
   * Substitutes each {@code %s} in {@code template} with an argument. These are matched by
   * position: the first {@code %s} gets {@code args[0]}, etc. If there are more arguments than
   * placeholders, the unmatched arguments will be appended to the end of the formatted message in
   * square braces.
   */
  private static String format(final String ptemplate, final Object... args) {
    final String template = String.valueOf(ptemplate); // null -> "null"

    // start substituting the arguments into the '%s' placeholders
    final StringBuilder builder = new StringBuilder(template.length() + 16 * args.length);
    int templateStart = 0;
    int pos = 0;
    while (pos < args.length) {
      final int placeholderStart = template.indexOf("%s", templateStart);
      if (placeholderStart == -1) {
        break;
      }
      builder.append(template.substring(templateStart, placeholderStart));
      builder.append(args[pos++]);
      templateStart = placeholderStart + 2;
    }
    builder.append(template.substring(templateStart));

    // if we run out of placeholders, append the extra args in square braces
    if (pos < args.length) {
      builder.append(" [");
      builder.append(args[pos++]);
      while (pos < args.length) {
        builder.append(", ");
        builder.append(args[pos++]);
      }
      builder.append(']');
    }

    return builder.toString();
  }

  // Hides the constructor for this static utility class.
  private InternalPreconditions() {}
}
