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

package de.knightsoftnet.mtwidgets.client.ui.widget.helper;

import com.google.gwt.editor.client.adapters.EditorSource;

/**
 * Abstract implementation of DataSource for a ListItem editor.
 *
 * @author Manfred Tremmel
 *
 * @param <D> type of data to edit
 * @param <V> type of the view of a single item
 */
public abstract class AbstractItemEditorSource<D, V extends AbstractListItemView<D>>
    extends EditorSource<V> {

  private final AbstractListEditor<D, V> listEditor;

  public AbstractItemEditorSource(final AbstractListEditor<D, V> plistEditor) {
    super();
    this.listEditor = plistEditor;
  }

  @Override
  public V create(final int index) {
    final V subEditor = this.createItemView();
    this.listEditor.insert(subEditor, index);
    subEditor.addEditorDeleteHandler(event -> this.listEditor.removeEntry(index));
    return subEditor;
  }

  @Override
  public void dispose(final V subEditor) {
    subEditor.removeFromParent();
  }

  @Override
  public void setIndex(final V editor, final int index) {
    this.listEditor.insert(editor, index);
  }

  /**
   * create new instance of a item view.
   *
   * @return item view
   */
  protected abstract V createItemView();
}
