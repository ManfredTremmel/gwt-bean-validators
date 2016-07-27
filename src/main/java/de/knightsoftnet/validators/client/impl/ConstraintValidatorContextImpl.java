/*
 * Copyright 2010 Google Inc. Copyright 2016 Manfred Tremmel
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

package de.knightsoftnet.validators.client.impl;

import de.knightsoftnet.validators.client.impl.metadata.MessageAndPath;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderDefinedContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder.NodeContextBuilder;
import javax.validation.ConstraintViolation;
import javax.validation.metadata.ConstraintDescriptor;

/**
 * GWT safe immutable implementation of {@link ConstraintValidatorContext}
 * <p>
 * These objects are very short lived.
 * </p>
 *
 * @param <A> the constraint being validated
 * @param <T> the type of object being validated
 */
public final class ConstraintValidatorContextImpl<A extends Annotation, T>
    implements ConstraintValidatorContext {

  /**
   * Builder for {@link ConstraintValidatorContextImpl}.
   */
  public final class ConstraintViolationBuilderImpl implements ConstraintViolationBuilder {

    private final ConstraintValidatorContextImpl<A, T> context;
    private final String messageTemplate;

    /**
     * constructor.
     *
     * @param constraintValidatorContextImpl context
     * @param messageTemplate message template
     */
    public ConstraintViolationBuilderImpl(
        final ConstraintValidatorContextImpl<A, T> constraintValidatorContextImpl,
        final String messageTemplate) {
      this.context = constraintValidatorContextImpl;
      this.messageTemplate = messageTemplate;
    }

    @Override
    public ConstraintValidatorContext addConstraintViolation() {
      ConstraintValidatorContextImpl.this.messages
          .add(new MessageAndPath(this.context.basePath, this.messageTemplate));
      return this.context;
    }

    @Override
    public NodeBuilderDefinedContext addNode(final String name) {
      return new NodeBuilderDefinedContextImpl(this, this.messageTemplate,
          ConstraintValidatorContextImpl.this.basePath.append(name));
    }
  }

  /**
   * Immutable GWT safe {@link NodeBuilderCustomizableContext}.
   */
  public final class NodeBuilderCustomizableContextImpl implements NodeBuilderCustomizableContext {
    private final String messageTemplate;
    private final ConstraintViolationBuilderImpl parent;
    private final PathImpl path;

    /**
     * constructor.
     *
     * @param parent ConstraintViolationBuilderImpl which is parent
     * @param messageTemplate message template
     * @param path path of the element
     */
    public NodeBuilderCustomizableContextImpl(final ConstraintViolationBuilderImpl parent,
        final String messageTemplate, final PathImpl path) {
      this.parent = parent;
      this.messageTemplate = messageTemplate;
      this.path = path;
    }

    @Override
    public ConstraintValidatorContext addConstraintViolation() {
      return null;
    }

    @Override
    public NodeBuilderCustomizableContext addNode(final String name) {
      return this;
    }

    @Override
    public NodeContextBuilder inIterable() {
      return new NodeContextBuilderImpl(this.path, this.messageTemplate, this.parent);
    }
  }

  /**
   * Immutable GWT safe {@link NodeBuilderDefinedContext}.
   */
  public final class NodeBuilderDefinedContextImpl implements NodeBuilderDefinedContext {

    private final String messageTemplate;
    private final ConstraintViolationBuilderImpl parent;
    private final PathImpl path;

    /**
     * constructor.
     *
     * @param parent ConstraintViolationBuilderImpl which is parent
     * @param messageTemplate message template
     * @param path path of the element
     */
    public NodeBuilderDefinedContextImpl(final ConstraintViolationBuilderImpl parent,
        final String messageTemplate, final PathImpl path) {
      this.parent = parent;
      this.messageTemplate = messageTemplate;
      this.path = path;
    }

    @Override
    public ConstraintValidatorContext addConstraintViolation() {
      ConstraintValidatorContextImpl.this.messages
          .add(new MessageAndPath(this.path, this.messageTemplate));
      return this.parent.context;
    }

    @Override
    public NodeBuilderCustomizableContext addNode(final String name) {
      return new NodeBuilderCustomizableContextImpl(this.parent, this.messageTemplate,
          this.path.append(name));
    }
  }

  /**
   * Immutable GWT safe {@link NodeContextBuilder}.
   */
  public final class NodeContextBuilderImpl implements NodeContextBuilder {

    private final String messageTemplate;

    private final ConstraintViolationBuilderImpl parent;
    private final PathImpl path;

    /**
     * constructor.
     *
     * @param parent ConstraintViolationBuilderImpl which is parent
     * @param messageTemplate message template
     * @param path path of the element
     */
    public NodeContextBuilderImpl(final PathImpl path, final String messageTemplate,
        final ConstraintViolationBuilderImpl parent) {
      super();
      this.path = path;
      this.messageTemplate = messageTemplate;
      this.parent = parent;
    }

    @Override
    public ConstraintValidatorContext addConstraintViolation() {
      return null;
    }

    @Override
    public NodeBuilderCustomizableContext addNode(final String name) {
      return new NodeBuilderCustomizableContextImpl(this.parent, this.messageTemplate,
          this.path.append(name));
    }

    @Override
    public NodeBuilderDefinedContext atIndex(final Integer index) {
      return new NodeBuilderDefinedContextImpl(this.parent, this.messageTemplate,
          this.path.appendIndex(null, index.intValue()));
    }

    @Override
    public NodeBuilderDefinedContext atKey(final Object key) {
      return new NodeBuilderDefinedContextImpl(this.parent, this.messageTemplate,
          this.path.appendKey(null, key));
    }
  }

  private final PathImpl basePath;
  private final ConstraintDescriptor<A> descriptor;

  private boolean disableDefault;
  private final Set<ConstraintViolation<T>> violations = new HashSet<ConstraintViolation<T>>();
  private final HashSet<MessageAndPath> messages = new HashSet<MessageAndPath>();

  /**
   * constructor.
   *
   * @param path path of the element
   * @param descriptor constraint descriptor
   */
  public ConstraintValidatorContextImpl(final PathImpl path,
      final ConstraintDescriptor<A> descriptor) {
    super();
    this.basePath = path;
    this.descriptor = descriptor;
  }

  @Override
  public ConstraintViolationBuilder buildConstraintViolationWithTemplate(
      final String messageTemplate) {
    final ConstraintViolationBuilderImpl builder =
        new ConstraintViolationBuilderImpl(this, messageTemplate);
    return builder;
  }

  @Override
  public void disableDefaultConstraintViolation() {
    this.disableDefault = true;
  }

  @Override
  public String getDefaultConstraintMessageTemplate() {
    return (String) this.descriptor.getAttributes().get("message");
  }

  /**
   * getter for message and path.
   *
   * @return set which includes message and path
   */
  public Set<MessageAndPath> getMessageAndPaths() {
    if (!this.disableDefault) {
      this.messages
          .add(new MessageAndPath(this.basePath, this.getDefaultConstraintMessageTemplate()));
    }
    return this.messages;
  }

  public Set<ConstraintViolation<T>> getViolations() {
    return this.violations;
  }
}
