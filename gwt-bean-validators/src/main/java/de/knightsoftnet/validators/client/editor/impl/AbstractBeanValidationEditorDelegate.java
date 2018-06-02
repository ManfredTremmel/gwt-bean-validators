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

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorVisitor;
import com.google.gwt.editor.client.impl.AbstractEditorDelegate;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * A limited EditorDelegate for editing standard bean-like objects.
 *
 * @param <T> the type being editor
 * @param <E> the type of editor
 */
public abstract class AbstractBeanValidationEditorDelegate<T, E extends Editor<T>>
    extends AbstractEditorDelegate<T, E> implements HasParentDriverSetter {

  private BeanValidationEditorDriver<?, ?> parentDriver;

  @Override
  public final void setParentDriver(final BeanValidationEditorDriver<?, ?> pparentDriver) {
    parentDriver = pparentDriver;
  }

  @Override
  public HandlerRegistration subscribe() {
    return null;
  }

  @Override
  protected EditorVisitor createInitializerVisitor() {
    if (parentDriver != null) {
      return parentDriver.createInitializerVisitor();
    }
    return new BeanValidationInitializer(null, null, null, null, null, false);
  }
}
