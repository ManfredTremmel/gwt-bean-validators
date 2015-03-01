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

package de.knightsoftnet.validators.client.decorators;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.ui.client.adapters.ValueBoxEditor;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.ValueBoxBase;

import java.text.ParseException;

public class ExtendedValueBoxEditor<T> extends ValueBoxEditor<T> {

  private final ExtendedValueBoxEditorMessages messages;

  private final TakesValue<T> takesValues;
  private EditorDelegate<T> delegate;
  private final AbstractDecorator<T> decorator;

  /**
   * constructor uses widget as base.
   *
   * @param ptakesValues widget which is able to set and get value from/to
   * @param pdecorator corresponding decorator
   */
  public ExtendedValueBoxEditor(final TakesValue<T> ptakesValues,
      final AbstractDecorator<T> pdecorator) {
    super(null);
    this.messages = GWT.create(ExtendedValueBoxEditorMessages.class);
    this.takesValues = ptakesValues;
    this.decorator = pdecorator;
    if (this.takesValues instanceof ValueBoxBase<?>) {
      this.delegate = ((ValueBoxBase<T>) this.takesValues).asEditor().getDelegate();
    }
  }

  @Override
  public EditorDelegate<T> getDelegate() {
    return this.delegate;
  }

  @Override
  public T getValue() {
    T value = null;
    if (this.takesValues instanceof ValueBoxBase<?>) {
      try {
        value = ((ValueBoxBase<T>) this.takesValues).getValueOrThrow();
      } catch (final ParseException e) {
        final String entryAsText = ((ValueBoxBase<T>) this.takesValues).getText();
        final String localizedMessage = this.messages.parseExceptionMessage(entryAsText);
        if (this.delegate != null) {
          this.delegate.recordError(localizedMessage, entryAsText, e);
        }
      }
    } else {
      value = this.takesValues.getValue();
    }
    return value;
  }

  @Override
  public void setDelegate(final EditorDelegate<T> pdelegate) {
    this.delegate = pdelegate;
  }

  @Override
  public void setValue(final T pvalue) {
    this.takesValues.setValue(pvalue);
    this.decorator.clearErrors();
  }

  /**
   * getter for the decorator.
   *
   * @return the decorator
   */
  public final AbstractDecorator<T> getDecorator() {
    return this.decorator;
  }

  /**
   * getter for the widget which takes the values.
   *
   * @return the takesValues
   */
  public final TakesValue<T> getTakesValues() {
    return this.takesValues;
  }
}
