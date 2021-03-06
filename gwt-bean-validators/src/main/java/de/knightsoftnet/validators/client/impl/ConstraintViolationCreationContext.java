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
   *
   * @param message validation message
   * @param property path of the field
   * @param messageParameters map of message parameters
   * @param expressionVariables map of expression variables
   * @param dynamicPayload object of the payload
   */
  public ConstraintViolationCreationContext(final String message, final PathImpl property,
      final Map<String, Object> messageParameters, final Map<String, Object> expressionVariables,
      final Object dynamicPayload) {
    this.message = message;
    propertyPath = property;
    this.messageParameters = toImmutableMap(messageParameters);
    this.expressionVariables = toImmutableMap(expressionVariables);
    this.dynamicPayload = dynamicPayload;
  }

  public final String getMessage() {
    return message;
  }

  public final PathImpl getPath() {
    return propertyPath;
  }

  public Map<String, Object> getExpressionVariables() {
    return expressionVariables;
  }

  public Map<String, Object> getMessageParameters() {
    return messageParameters;
  }

  public Object getDynamicPayload() {
    return dynamicPayload;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder(128);
    sb.append("ConstraintViolationCreationContext{");
    sb.append("message='").append(message).append('\'');
    sb.append(", propertyPath=").append(propertyPath);
    sb.append(", messageParameters=").append(messageParameters);
    sb.append(", expressionVariables=").append(expressionVariables);
    sb.append(", dynamicPayload=").append(dynamicPayload);
    sb.append('}');
    return sb.toString();
  }
}
