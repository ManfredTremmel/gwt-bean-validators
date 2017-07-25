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
import com.google.gwt.editor.client.EditorVisitor;
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
 * </p>
 *
 * @param <T> the type being edited
 * @param <E> the Editor for the type
 */
public interface BeanValidationEditorDriver<T, E extends Editor<? super T>>
    extends EditorDriver<T>, HasFormSubmitHandlers<T>, HasValueChangeHandlers<T> {

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
   * try to submit with dirty as parameter.
   *
   * @param pdirty dirty flag, if true form is handled as dirty/changed
   * @return true if submit is done
   */
  boolean tryToSubmitFrom(final boolean pdirty);

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
   * @deprecated As of release 0.8.2, replaced by getCheckTime()
   */
  @Deprecated
  boolean isCheckOnKeyUp();

  /**
   * set state of validation on key up (check on every key up event), to change this state is only
   * possible, before the first edit call was done.
   *
   * @param pcheckOnKeyUp true/false
   * @throws RuntimeException if this method is called after a edit call
   * @deprecated As of release 0.8.2, replaced by setCheckTime()
   */
  @Deprecated
  void setCheckOnKeyUp(boolean pcheckOnKeyUp) throws RuntimeException;

  /**
   * get the time of validation.
   *
   * @return check time enumeration
   */
  CheckTimeEnum getCheckTime();

  /**
   * set time of validation, to change this state is only possible, before the first edit call was
   * done.
   *
   * @param pcheckTime check time enumeration
   * @throws RuntimeException if this method is called after a edit call
   */
  void setCheckTime(CheckTimeEnum pcheckTime) throws RuntimeException;

  /**
   * get state of submit on return (form is submitted if it's valid and return/enter is pressed).
   *
   * @return true if it's active
   */
  boolean isSubmitOnReturn();

  /**
   * set state of submit on return (form is submitted if it's valid and return/enter is pressed). To
   * change this state is only possible, before the first edit call was done.
   *
   * @param psubmitOnReturn true/false
   * @throws RuntimeException if this method is called after a edit call
   */
  void setSubmitOnReturn(boolean psubmitOnReturn) throws RuntimeException;

  /**
   * get state of submit on value change (form is submitted if it's valid and value has changed).
   *
   * @return true if it's active
   */
  boolean isSubmitOnValueChange();

  /**
   * set state of submit on value change (form is submitted if it's valid and value has changed). To
   * change this state is only possible, before the first edit call was done.
   *
   * @param psubmitOnValueChange true/false
   */
  void setSubmitOnValueChange(boolean psubmitOnValueChange);

  /**
   * setter to add as submit button which is automatically .
   *
   * @param psubmitButton the submitUnchanged to set
   */
  void setSubmitButton(Widget psubmitButton);

  /**
   * set validation groups.
   *
   * @param pgroups group or list of groups targeted for validation (default to
   *        {@link javax.validation.groups.Default})
   */
  void setValidationGroups(Class<?>... pgroups);

  /**
   * create a initializer visitor implementation.
   * 
   * @return editor visitor
   */
  EditorVisitor createInitializerVisitor();
}
