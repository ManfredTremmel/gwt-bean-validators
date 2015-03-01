/*
 * Copyright 2011 Google Inc.
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

package com.google.gwt.editor.client.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorDriver;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.EditorVisitor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.editor.client.impl.DelegateMap.KeyMethod;
import com.google.gwt.editor.client.testing.EditorHierarchyPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolation;

/**
 * Contains common code shared between the SimpleBeanEditorDriver and RequestFactoryEditorDriver.
 * This is a copy of the BaseEditorDriver form GWT, it's included, because we need access to the
 * leafValueMap, the getter to it is the only change.
 *
 * @param <T> the type of data being edited
 * @param <E> the type of editor
 */
public abstract class AbstractExtendedBaseEditorDriver<T, E extends Editor<T>> {
  private AbstractEditorDelegate<T, E> delegate;
  /**
   * Used for {@link #isDirty()} computations.
   */
  private Map<LeafValueEditor<?>, Object> leafValueMap;
  private E editor;
  private List<EditorError> errors;
  private T object;

  public abstract void accept(EditorVisitor visitor);

  public List<EditorError> getErrors() {
    return this.errors;
  }

  public boolean hasErrors() {
    return !this.errors.isEmpty();
  }

  /**
   * return true if the entry of one or more values have changed.
   *
   * @return true/false
   */
  public boolean isDirty() {
    final DirtCollector c = new DirtCollector();
    this.accept(c);
    return c.isDirty() || !this.leafValueMap.equals(c.getLeafValues());
  }

  public boolean setConstraintViolations(final Iterable<ConstraintViolation<?>> violations) {
    return this.doSetViolations(violations == null ? null : SimpleViolation
        .iterableFromConstrantViolations(violations));
  }

  @Override
  public String toString() {
    if (GWT.isProdMode()) {
      return super.toString();
    } else {
      return this.editor == null ? "Uninitialized" : EditorHierarchyPrinter.toString(this
          .asEditorDriver());
    }
  }

  protected void configureDelegate(final AbstractEditorDelegate<T, E> rootDelegate) {
    rootDelegate.initialize("", this.getEditor());
  }

  protected abstract AbstractEditorDelegate<T, E> createDelegate();

  protected EditorVisitor createInitializerVisitor() {
    return new Initializer();
  }

  protected void doEdit(final T pobject) {
    this.checkEditor();
    this.object = this.delegate.ensureMutable(pobject);
    this.delegate.setObject(this.object);
    this.accept(this.createInitializerVisitor());
    final DirtCollector c = new DirtCollector();
    this.accept(c);
    this.leafValueMap = c.getLeafValues();
  }

  protected void doFlush() {
    this.checkObject();
    this.errors = new ArrayList<EditorError>();
    this.accept(new Flusher());
    this.accept(new ErrorCollector(this.errors));
  }

  protected void doInitialize(final E editor) {
    this.editor = editor;
    this.delegate = this.createDelegate();
    this.configureDelegate(this.delegate);
  }

  protected boolean doSetViolations(final Iterable<SimpleViolation> violations) {
    this.checkObject();
    SimpleViolation.pushViolations(violations, this.asEditorDriver(), this.getViolationKeyMethod());

    // Collect the errors, which will take care of co-editor chains.
    this.errors = new ArrayList<EditorError>();
    this.accept(new ErrorCollector(this.errors));
    return this.hasErrors();
  }

  protected AbstractEditorDelegate<T, E> getDelegate() {
    return this.delegate;
  }

  protected E getEditor() {
    return this.editor;
  }

  protected T getObject() {
    return this.object;
  }

  protected KeyMethod getViolationKeyMethod() {
    return DelegateMap.IDENTITY;
  }

  /**
   * This cast avoids the need to add another parameterization to BaseEditorDriver since the class
   * cannot be declared to extend an unbound interface.
   */
  private EditorDriver<?> asEditorDriver() {
    return (EditorDriver<?>) this;
  }

  private void checkEditor() {
    if (this.editor == null) {
      throw new IllegalStateException("Must call initialize() first");
    }
  }

  private void checkObject() {
    if (this.object == null) {
      throw new IllegalStateException("Must call edit() first");
    }
  }

  /**
   * getter for leaf value map. This is the extension against the original BaseEditorDriver.
   *
   * @return leafValueMap
   */
  protected final Map<LeafValueEditor<?>, Object> getLeafValueMap() {
    return this.leafValueMap;
  }
}
