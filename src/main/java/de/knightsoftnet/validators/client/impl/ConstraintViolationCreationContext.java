/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package de.knightsoftnet.validators.client.impl;

import static org.hibernate.validator.internal.util.CollectionHelper.toImmutableMap;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.hibernate.validator.internal.util.stereotypes.Immutable;

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
  @Immutable
  private final Map<String, Object> messageParameters;
  @Immutable
  private final Map<String, Object> expressionVariables;
  private final Object dynamicPayload;

  public ConstraintViolationCreationContext(final String message, final PathImpl property) {
    this(message, property, Collections.<String, Object>emptyMap(),
        Collections.<String, Object>emptyMap(), null);
  }

  /**
   * constructor.
   */
  public ConstraintViolationCreationContext(final String message, final PathImpl property,
      final Map<String, Object> messageParameters, final Map<String, Object> expressionVariables,
      final Object dynamicPayload) {
    this.message = message;
    this.propertyPath = property;
    this.messageParameters = toImmutableMap(messageParameters);
    this.expressionVariables = toImmutableMap(expressionVariables);
    this.dynamicPayload = dynamicPayload;
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

  public Map<String, Object> getMessageParameters() {
    return this.messageParameters;
  }

  public Object getDynamicPayload() {
    return this.dynamicPayload;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder(128);
    sb.append("ConstraintViolationCreationContext{");
    sb.append("message='").append(this.message).append('\'');
    sb.append(", propertyPath=").append(this.propertyPath);
    sb.append(", messageParameters=").append(this.messageParameters);
    sb.append(", expressionVariables=").append(this.expressionVariables);
    sb.append(", dynamicPayload=").append(this.dynamicPayload);
    sb.append('}');
    return sb.toString();
  }
}
