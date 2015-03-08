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

package de.knightsoftnet.validators.client.editor;

import de.knightsoftnet.validators.client.event.HasFormSubmitHandlers;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.EditorDriver;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.user.client.ui.Widget;

/**
 * Automates editing of simple bean-like objects. The {@link EditorDelegate} provided from this
 * driver has a no-op implementation of {@link EditorDelegate#subscribe()}.
 *
 * <pre>
 * interface MyDriver extends AbstractBeanValidationEditorDriver&lt;MyObject, MyObjectEditor&gt; {
 * }
 * 
 * MyDriver instance = GWT.create(MyDriver.class);
 * {
 *   MyObjectEditor editor = new MyObjectEditor();
 *   instance.initialize(editor);
 *   // Do stuff
 *   instance.edit(myObjectInstance);
 *   // Do more stuff
 *   instance.flush();
 * }
 * </pre>
 *
 * <p>
 * Note that this interface is intended to be implemented by generated code and is subject to API
 * expansion in the future.
 *
 * @param <T> the type being edited
 * @param <E> the Editor for the type
 * @see com.google.gwt.editor.client.testing.MockSimpleBeanEditorDriver
 */
public interface BeanValidationEditorDriver<T, E extends Editor<? super T>> extends
    EditorDriver<T>, HasFormSubmitHandlers<T>, HasValueChangeHandlers<T> {

  /**
   * Push the data in an object graph into the Editor given to {@link #initialize}.
   *
   * @param object the object providing input data
   * @throws IllegalStateException if {@link #initialize} has not been called
   */
  void edit(T object);

  /**
   * Update the object being edited with the current state of the Editor.
   *
   * @return the object passed into {@link #edit(Object)}
   * @throws IllegalStateException if {@link #edit(Object)} has not been called
   */
  @Override
  T flush();

  /**
   * Initialize the editor driver.
   *
   * @param editor the Editor to populate
   */
  void initialize(E editor);

  /**
   * start bean validation.
   *
   * @return true if created bean is valid
   */
  boolean validate();

  /**
   * try to submit form, if validation is ok, a
   * {@link de.knightsoftnet.validators.client.event.FormSubmitEvent} is thrown.
   *
   * @return true if submit is done, otherwise false
   */
  boolean tryToSubmitFrom();

  /**
   * submit unchanged forms.
   *
   * @return true if unchanged forms can be submitted
   */
  boolean isSubmitUnchanged();

  /**
   * setter to change submit unchanged forms.
   *
   * @param psubmitUnchanged the submitUnchanged to set
   */
  void setSubmitUnchanged(boolean psubmitUnchanged);

  /**
   * get state of validation on key up (check on every key up event).
   * 
   * @return true if it's active
   */
  boolean isCheckOnKeyUp();

  /**
   * set state of validation on key up (check on every key up event). to change this state is only
   * possible, before the first edit call was done.
   * 
   * @param pcheckOnKeyUp true/false
   * @throws IllegalAccessException if this method is called after a edit call
   */
  void setCheckOnKeyUp(boolean pcheckOnKeyUp) throws IllegalAccessException;

  /**
   * get state of submit on return (form is submitted if it's valid and return/enter is pressed).
   * 
   * @return true if it's active
   */
  boolean isSubmitOnReturn();

  /**
   * set state of submit on return (form is submitted if it's valid and return/enter is pressed). to
   * change this state is only possible, before the first edit call was done.
   * 
   * @param pcheckOnKeyUp true/false
   * @throws IllegalAccessException if this method is called after a edit call
   */
  void setSubmitOnReturn(boolean psubmitOnReturn) throws IllegalAccessException;

  /**
   * setter to add as submit button which is automatically .
   *
   * @param psubmitButton the submitUnchanged to set
   */
  void setSubmitButton(Widget psubmitButton);
}
