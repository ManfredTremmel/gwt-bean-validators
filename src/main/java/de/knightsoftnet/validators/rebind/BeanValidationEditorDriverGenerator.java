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

package de.knightsoftnet.validators.rebind;

import com.google.gwt.editor.rebind.AbstractEditorDriverGenerator;
import com.google.gwt.editor.rebind.model.EditorData;

import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;
import de.knightsoftnet.validators.client.editor.impl.AbstractBeanValidationEditorDelegate;
import de.knightsoftnet.validators.client.editor.impl.AbstractBeanValidationEditorDriver;

/**
 * Generates implementations of {@link BeanValidationEditorDriver}.
 */
public class BeanValidationEditorDriverGenerator extends AbstractEditorDriverGenerator {

  @Override
  protected Class<?> getDriverInterfaceType() {
    return BeanValidationEditorDriver.class;
  }

  @Override
  protected Class<?> getDriverSuperclassType() {
    return AbstractBeanValidationEditorDriver.class;
  }

  @Override
  protected Class<?> getEditorDelegateType() {
    return AbstractBeanValidationEditorDelegate.class;
  }

  @Override
  protected String mutableObjectExpression(final EditorData data,
      final String sourceObjectExpression) {
    return sourceObjectExpression;
  }
}
