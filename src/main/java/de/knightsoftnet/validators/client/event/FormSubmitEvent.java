/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.knightsoftnet.validators.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Represents a form submit event.
 *
 * @param <T> the value about to be changed
 */
public class FormSubmitEvent<T> extends GwtEvent<FormSubmitHandler<T>> {

  /**
   * Handler type.
   */
  private static Type<FormSubmitHandler<?>> type;

  /**
   * value of the form.
   */
  private final T value;

  /**
   * Creates a form submit event.
   *
   * @param pvalue the value
   */
  protected FormSubmitEvent(final T pvalue) {
    super();
    this.value = pvalue;
  }

  /**
   * Fires a form submit event on all registered handlers in the handler manager. If no such
   * handlers exist, this method will do nothing.
   *
   * @param <T> the old value type
   * @param source the source of the handlers
   * @param value the value
   */
  public static <T> void fire(final HasFormSubmitHandlers<T> source, final T value) {
    if (type != null) {
      final FormSubmitEvent<T> event = new FormSubmitEvent<T>(value);
      source.fireEvent(event);
    }
  }

  /**
   * Gets the type associated with this event.
   *
   * @return returns the handler type
   */
  public static Type<FormSubmitHandler<?>> getType() {
    if (type == null) { // NOPMD client side needn't be thread save
      type = new Type<FormSubmitHandler<?>>();
    }
    return type;
  }

  // The instance knows its BeforeSelectionHandler is of type I, but the type
  // field itself does not, so we have to do an unsafe cast here.
  @SuppressWarnings({"unchecked", "rawtypes"})
  @Override
  public final Type<FormSubmitHandler<T>> getAssociatedType() {
    return (Type) type;
  }

  /**
   * Gets the value.
   *
   * @return the value
   */
  public T getValue() {
    return this.value;
  }

  @Override
  public String toDebugString() {
    return super.toDebugString() + this.getValue();
  }

  @Override
  protected void dispatch(final FormSubmitHandler<T> phandler) {
    phandler.onFormSubmit(this);
  }
}
