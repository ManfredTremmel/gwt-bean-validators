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

package de.knightsoftnet.validators.client.editor.impl;

import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;
import de.knightsoftnet.validators.client.editor.HasParentDriverSetter;

import com.google.gwt.editor.client.CompositeEditor;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * Synchronizes a list of objects and a list of Editors.
 *
 * @param <T> the type of data being edited
 * @param <E> the type of Editor
 */
public class ListValidationEditorWrapper<T, E extends Editor<? super T>> extends AbstractList<T> {
  private final List<T> backing;
  private final CompositeEditor.EditorChain<T, E> chain;
  private final List<E> editors;
  private final EditorSource<E> editorSource;
  private final List<T> workingCopy;

  /**
   * constructor.
   *
   * @param backing list of data entries.
   * @param chain editor deligator
   * @param editorSource editor source
   * @param pparentDriver parent driver
   */
  public ListValidationEditorWrapper(final List<T> backing,
      final CompositeEditor.EditorChain<T, E> chain, final EditorSource<E> editorSource,
      final BeanValidationEditorDriver<?, ?> pparentDriver) {
    super();
    this.backing = backing;
    this.chain = chain;
    this.editorSource = editorSource;
    if (this.chain instanceof HasParentDriverSetter) {
      ((HasParentDriverSetter) this.chain).setParentDriver(pparentDriver);
    }
    this.editors = new ArrayList<>(backing.size());
    this.workingCopy = new ArrayList<>(backing);
  }


  @Override
  public void add(final int index, final T element) {
    this.workingCopy.add(index, element);
    final E subEditor = this.editorSource.create(index);
    this.editors.add(index, subEditor);
    final int size = this.editors.size();
    for (int i = index + 1; i < size; i++) {
      this.editorSource.setIndex(this.editors.get(i), i);
    }
    this.chain.attach(element, subEditor);
  }

  @Override
  public T get(final int index) {
    return this.workingCopy.get(index);
  }

  @Override
  public T remove(final int index) {
    final T toReturn = this.workingCopy.remove(index);
    final E subEditor = this.editors.remove(index);
    this.editorSource.dispose(subEditor);
    final int size = this.editors.size();
    for (int i = index; i < size; i++) {
      this.editorSource.setIndex(this.editors.get(i), i);
    }
    this.chain.detach(subEditor);
    return toReturn;
  }

  @Override
  public T set(final int index, final T element) {
    final T toReturn = this.workingCopy.set(index, element);
    this.chain.attach(element, this.editors.get(index));
    return toReturn;
  }

  @Override
  public int size() {
    return this.workingCopy.size();
  }

  /**
   * Must be called after construction. This is a two-phase initialization so that ListEditor can
   * assign its list field before any sub-editors might call {@link ListEditor#getList()}
   */
  void attach() {
    this.editors.addAll(this.editorSource.create(this.workingCopy.size(), 0));
    final int size = this.workingCopy.size();
    for (int i = 0; i < size; i++) {
      this.chain.attach(this.workingCopy.get(i), this.editors.get(i));
    }
  }

  void detach() {
    final int size = this.editors.size();
    for (int i = 0; i < size; i++) {
      this.chain.detach(this.editors.get(i));
      this.editorSource.dispose(this.editors.get(i));
    }
  }

  void flush() {
    final int size = this.workingCopy.size();
    for (int i = 0; i < size; i++) {
      final E subEditor = this.editors.get(i);
      final T value = this.chain.getValue(subEditor);
      // Use of object-identity intentional
      if (this.workingCopy.get(i) != value) {
        this.workingCopy.set(i, value);
      }
    }
    this.backing.clear();
    this.backing.addAll(this.workingCopy);
  }

  /**
   * For testing only.
   */
  List<? extends E> getEditors() {
    return this.editors;
  }

  /**
   * Checks whether that ListEditorWrapper can be reused for the passed list.
   * <p>
   * The ListEditorWrapper can be reused if and only if the backing list is the same instance as the
   * passed list.
   * </p>
   */
  boolean isSameValue(final List<T> value) {
    // identity check intentional
    return this.backing == value;
  }

  /**
   * Refresh the editors in case the backing list has been modified from outside the
   * ListEditorWrapper list.
   * <p>
   * This is basically the opposite from {@link #flush()}. It's used to reuse sub-editors instead of
   * recreating a ListEditorWrapper from scratch.
   * </p>
   */
  void refresh() {
    int pos = 0;
    for (final T item : this.backing) {
      if (pos < this.size()) {
        this.set(pos, item);
      } else {
        assert pos == this.size();
        this.add(pos, item);
      }
      pos++;
    }
    while (this.backing.size() < this.size()) {
      this.remove(this.size() - 1);
    }
    assert this.backing.size() == this.size();
    assert this.backing.equals(this.workingCopy);
  }
}
