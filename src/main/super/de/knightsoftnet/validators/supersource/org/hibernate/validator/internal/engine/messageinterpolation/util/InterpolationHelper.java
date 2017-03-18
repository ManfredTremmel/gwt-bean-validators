/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package org.hibernate.validator.internal.engine.messageinterpolation.util;

import com.google.gwt.regexp.shared.RegExp;

/**
 * Utilities used for message interpolation.
 *
 * @author Guillaume Smet
 */
public final class InterpolationHelper {

  public static final char BEGIN_TERM = '{';
  public static final char END_TERM = '}';
  public static final char EL_DESIGNATOR = '$';
  public static final char ESCAPE_CHARACTER = '\\';

  private static final RegExp ESCAPE_MESSAGE_PARAMETER_PATTERN =
      RegExp.compile("([\\" + ESCAPE_CHARACTER + BEGIN_TERM + END_TERM + EL_DESIGNATOR + "])");

  private InterpolationHelper() {}

  /**
   * escape message parameter.
   *
   * @param messageParameter parameter to escape
   * @return escaped string
   */
  public static String escapeMessageParameter(final String messageParameter) {
    if (messageParameter == null) {
      return null;
    }
    return ESCAPE_MESSAGE_PARAMETER_PATTERN.replace(messageParameter,
        String.valueOf(ESCAPE_CHARACTER) + "$1"); // NOPMD
  }
}
