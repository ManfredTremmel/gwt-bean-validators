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

package de.knightsoftnet.validators.client;

import de.knightsoftnet.validators.client.impl.AbstractGwtValidator;
import de.knightsoftnet.validators.client.impl.GwtValidatorContext;

import com.google.gwt.core.client.GWT;

import javax.validation.ConstraintValidatorFactory;
import javax.validation.MessageInterpolator;
import javax.validation.TraversableResolver;
import javax.validation.Validator;
import javax.validation.ValidatorContext;
import javax.validation.ValidatorFactory;
import javax.validation.spi.ConfigurationState;

/**
 * Abstract {@link ValidatorFactory} that delegates to a GWT generated {@link Validator}.
 * <p>
 * Extend this class create and implement createValidator
 *
 * <pre>
 * public class MyValidatorFactory extends AbstractGwtValidatorFactory {
 *   &#64;GwtValidation(value = {Pojo.class,Other.class})
 *   public static interface GwtValidator extends Validator {
 *   }
 *
 *   public AbstractGwtValidator createValidator (){
 *     return GWT.create(GwtValidator.class));
 *   }
 * }
 * </pre>
 * </p>
 * <p>
 * Then add a line like this to your Gwt Module config (gwt.xml) file.
 * </p>
 *
 * <pre>
 * &lt;replace-with class="com.example.MyValidatorFactory">
 *   &lt;when-type-is class="javax.validation.ValidatorFactory"/>
 * &lt;/replace-with>
 * </pre>
 */
public abstract class AbstractGwtValidatorFactory implements ValidatorFactory {
  private ConstraintValidatorFactory constraintValidatorFactory;
  private MessageInterpolator messageInterpolator;
  private TraversableResolver traversableResolver;

  /**
   * Implement this method to return a {@link GWT#create}d {@link Validator} annotated with
   * {@link GwtValidation}.
   *
   * @return newly created Validator
   */
  public abstract AbstractGwtValidator createValidator();

  /**
   * GWT does not support {@link ConstraintValidatorFactory}, so the object returned by this method
   * will not work.
   */
  @Override
  public final ConstraintValidatorFactory getConstraintValidatorFactory() {
    return this.constraintValidatorFactory;
  }

  @Override
  public final MessageInterpolator getMessageInterpolator() {
    return this.messageInterpolator;
  }

  @Override
  public final TraversableResolver getTraversableResolver() {
    return this.traversableResolver;
  }

  @Override
  public final Validator getValidator() {
    final AbstractGwtValidator validator = this.createValidator();
    validator.init(this.getConstraintValidatorFactory(), this.getMessageInterpolator(),
        this.getTraversableResolver());
    return validator;
  }

  /**
   * initialize factory.
   * 
   * @param configState ConfigurationState
   */
  public final void init(final ConfigurationState configState) {
    final ConstraintValidatorFactory configConstraintValidatorFactory =
        configState.getConstraintValidatorFactory();
    this.constraintValidatorFactory = configConstraintValidatorFactory == null
        ? GWT.<ConstraintValidatorFactory>create(ConstraintValidatorFactory.class)
        : configConstraintValidatorFactory;
    final TraversableResolver configTraversableResolver = configState.getTraversableResolver();
    this.traversableResolver = configTraversableResolver == null ? new DefaultTraversableResolver()
        : configTraversableResolver;
    final MessageInterpolator configMessageInterpolator = configState.getMessageInterpolator();
    this.messageInterpolator = configMessageInterpolator == null ? new GwtMessageInterpolator()
        : configMessageInterpolator;
  }

  /**
   * Unsupported. Always throws an {@link UnsupportedOperationException}.
   *
   * @throws UnsupportedOperationException this isn't supported in gwt
   */
  @Override
  public final <T> T unwrap(final Class<T> type) {
    throw new UnsupportedOperationException("GWT Validation does not support upwrap()");
  }

  @Override
  public final ValidatorContext usingContext() {
    return new GwtValidatorContext(this);
  }
}
