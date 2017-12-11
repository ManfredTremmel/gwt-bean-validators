/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package org.hibernate.validator.internal.util.logging;

import java.lang.invoke.MethodHandles.Lookup;

/**
 * @author Hardy Ferentschik
 * @author Kevin Pollet &lt;kevin.pollet@serli.com&gt; (C) 2012 SERLI
 * @author Manfred Tremmel - GWT port
 */
public final class LoggerFactory {
  public static Log make(final Lookup creationContext) {
    return new Log();
  }

  /**
   * private constructor to avoid instantiation.
   */
  private LoggerFactory() {
    super();
  }
}

