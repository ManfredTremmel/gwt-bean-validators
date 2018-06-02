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

import de.knightsoftnet.mtwidgets.client.event.EditorDeleteEvent;
import de.knightsoftnet.mtwidgets.client.event.EditorDeleteEvent.EditorDeleteHandler;

import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.HasEditorDelegate;
import com.google.gwt.user.client.ui.Composite;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * Abstract View of an list item, gwt implementation.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 * @param <D> type of data to edit
 */
public abstract class AbstractListItemView<D> extends Composite
    implements HasEditorDelegate<D>, EditorDeleteEvent.EditorDeleteHandlers<D> {

  @Override
  public void setDelegate(final EditorDelegate<D> pdelegate) {
    pdelegate.subscribe();
  }

  @Override
  public HandlerRegistration addEditorDeleteHandler(final EditorDeleteHandler<D> phandler) {
    return this.addHandler(phandler, EditorDeleteEvent.getType());
  }

  /**
   * removes current entry from list.
   */
  protected void removeThisEntry() {
    fireEvent(new EditorDeleteEvent<>(this));
  }
}
