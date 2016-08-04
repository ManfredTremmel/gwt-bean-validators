/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package de.knightsoftnet.validators.client.impl;

import org.hibernate.validator.internal.engine.path.PathImpl;

import java.util.Collections;
import java.util.Map;

/**
 * Container class for the information needed to create a constraint violation.
 *
 * @author Hardy Ferentschik
 */
public class ConstraintViolationCreationContext {
  private final String message;
  private final PathImpl propertyPath;
  private final Map<String, Object> expressionVariables;

  public ConstraintViolationCreationContext(final String message, final PathImpl property) {
    this(message, property, Collections.<String, Object>emptyMap());
  }

  /**
   * constructor.
   */
  public ConstraintViolationCreationContext(final String message, final PathImpl property,
      final Map<String, Object> expressionVariables) {
    this.message = message;
    this.propertyPath = property;
    this.expressionVariables = expressionVariables;
  }

  public final String getMessage() {
    return this.message;
  }

  public final PathImpl getPath() {
    return this.propertyPath;
  }

  public Map<String, Object> getExpressionVariables() {
    return this.expressionVariables;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder(128);
    sb.append("ConstraintViolationCreationContext{");
    sb.append("message='").append(this.message).append('\'');
    sb.append(", propertyPath=").append(this.propertyPath);
    sb.append(", messageParameters=").append(this.expressionVariables);
    sb.append('}');
    return sb.toString();
  }
}
