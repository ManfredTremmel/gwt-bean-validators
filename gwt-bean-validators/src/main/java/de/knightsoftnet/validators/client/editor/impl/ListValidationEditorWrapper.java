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
    if (chain instanceof HasParentDriverSetter) {
      ((HasParentDriverSetter) chain).setParentDriver(pparentDriver);
    }
    editors = new ArrayList<>(backing.size());
    workingCopy = new ArrayList<>(backing);
  }


  @Override
  public void add(final int index, final T element) {
    workingCopy.add(index, element);
    final E subEditor = editorSource.create(index);
    editors.add(index, subEditor);
    final int size = editors.size();
    for (int i = index + 1; i < size; i++) {
      editorSource.setIndex(editors.get(i), i);
    }
    chain.attach(element, subEditor);
  }

  @Override
  public T get(final int index) {
    return workingCopy.get(index);
  }

  @Override
  public T remove(final int index) {
    final T toReturn = workingCopy.remove(index);
    final E subEditor = editors.remove(index);
    editorSource.dispose(subEditor);
    final int size = editors.size();
    for (int i = index; i < size; i++) {
      editorSource.setIndex(editors.get(i), i);
    }
    chain.detach(subEditor);
    return toReturn;
  }

  @Override
  public T set(final int index, final T element) {
    final T toReturn = workingCopy.set(index, element);
    chain.attach(element, editors.get(index));
    return toReturn;
  }

  @Override
  public int size() {
    return workingCopy.size();
  }

  /**
   * Must be called after construction. This is a two-phase initialization so that ListEditor can
   * assign its list field before any sub-editors might call {@link ListEditor#getList()}
   */
  void attach() {
    editors.addAll(editorSource.create(workingCopy.size(), 0));
    final int size = workingCopy.size();
    for (int i = 0; i < size; i++) {
      chain.attach(workingCopy.get(i), editors.get(i));
    }
  }

  void detach() {
    final int size = editors.size();
    for (int i = 0; i < size; i++) {
      chain.detach(editors.get(i));
      editorSource.dispose(editors.get(i));
    }
  }

  void flush() {
    final int size = workingCopy.size();
    for (int i = 0; i < size; i++) {
      final E subEditor = editors.get(i);
      final T value = chain.getValue(subEditor);
      // Use of object-identity intentional
      if (workingCopy.get(i) != value) {
        workingCopy.set(i, value);
      }
    }
    backing.clear();
    backing.addAll(workingCopy);
  }

  /**
   * For testing only.
   */
  List<? extends E> getEditors() {
    return editors;
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
    return backing == value;
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
    for (final T item : backing) {
      if (pos < size()) {
        set(pos, item);
      } else {
        assert pos == size();
        add(pos, item);
      }
      pos++;
    }
    while (backing.size() < size()) {
      remove(size() - 1);
    }
    assert backing.size() == size();
    assert backing.equals(workingCopy);
  }
}
