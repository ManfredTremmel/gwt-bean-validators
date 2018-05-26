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

package de.knightsoftnet.mtwidgets.client.event;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * event which can be thrown by a list editor entry to inform about it's removal.
 *
 * @param <T> type of the Event
 *
 * @author Manfred Tremmel
 *
 */
public class EditorDeleteEvent<T> extends GwtEvent<EditorDeleteEvent.EditorDeleteHandler<T>> {
  private static final Type<EditorDeleteHandler<?>> TYPE = new Type<>();

  public interface EditorDeleteHandler<T> extends EventHandler {
    void onEditorDelete(EditorDeleteEvent<T> event);
  }

  public interface EditorDeleteHandlers<T> extends HasHandlers {
    HandlerRegistration addEditorDeleteHandler(EditorDeleteHandler<T> handler);
  }

  private final Editor<T> editor;

  public EditorDeleteEvent(final Editor<T> peditor) {
    super();
    this.editor = peditor;
  }

  public static Type<EditorDeleteHandler<?>> getType() {
    return EditorDeleteEvent.TYPE;
  }

  @Override
  protected void dispatch(final EditorDeleteHandler<T> handler) {
    handler.onEditorDelete(this);
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  @Override
  public Type<EditorDeleteHandler<T>> getAssociatedType() {
    return (Type) EditorDeleteEvent.TYPE;
  }

  public Editor<T> getEditor() {
    return this.editor;
  }
}
