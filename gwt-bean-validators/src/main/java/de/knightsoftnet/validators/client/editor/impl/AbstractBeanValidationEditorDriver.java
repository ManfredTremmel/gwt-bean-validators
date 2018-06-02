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
import de.knightsoftnet.validators.client.editor.CheckTimeEnum;
import de.knightsoftnet.validators.client.event.FormSubmitEvent;
import de.knightsoftnet.validators.client.event.FormSubmitHandler;
import de.knightsoftnet.validators.client.impl.AbstractGwtValidator;
import de.knightsoftnet.validators.client.impl.Validation;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorVisitor;
import com.google.gwt.editor.client.impl.BaseEditorDriver;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.Widget;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/**
 * A base implementation class for generated BeanValidationEditorDriver implementations.
 *
 * @param <T> the type being edited
 * @param <E> the Editor type
 */
public abstract class AbstractBeanValidationEditorDriver<T, E extends Editor<T>>
    extends BaseEditorDriver<T, E> implements BeanValidationEditorDriver<T, E> {

  /**
   * used to make sure, handlers on the input fields are set only once.
   */
  private boolean handlersSet;

  /**
   * manager to handle events.
   */
  private HandlerManager handlerManager;

  /**
   * submit also unchanged values.
   */
  private boolean submitUnchanged;

  /**
   * check input time.
   */
  private CheckTimeEnum checkTime = CheckTimeEnum.ON_KEY_UP;

  /**
   * submit form when enter/return is hit.
   */
  private boolean submitOnReturn = true;

  /**
   * submit form when value changes is hit.
   */
  private boolean submitOnValueChange = false;

  /**
   * submit button.
   */
  private Widget submitButton;

  /**
   * validation groups to limit validation.
   */
  protected Class<?>[] validationGroups;

  /**
   * handler which commits when return is pressed.
   */
  private final KeyPressHandler commitOnReturnHandler;

  /**
   * handler which starts validates on key up.
   */
  private final KeyUpHandler validateOnKeyUpHandler;

  /**
   * handler which starts validation on value changes.
   */
  private final ValueChangeHandler<?> validateOnVueChangeHandler;

  /**
   * handler which handles value changes.
   */
  private final ValueChangeHandler<?> valueChangeHandler;

  /**
   * default constructor.
   */
  @SuppressWarnings("PMD.ConstructorCallsOverridableMethod")
  public AbstractBeanValidationEditorDriver() {
    super();
    commitOnReturnHandler = pevent -> {
      if (pevent.getCharCode() == KeyCodes.KEY_ENTER
          || pevent.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
        tryToSubmitFrom();
      }
    };
    validateOnKeyUpHandler = pevent -> validate();
    validateOnVueChangeHandler = pevent -> validate();
    valueChangeHandler = pevent -> {
      ValueChangeEvent.fire(this, getObject());
      if (submitOnValueChange) {
        tryToSubmitFrom();
      }
    };
    setValidationGroups();
  }

  @Override
  public void edit(final T object) {
    edit(object, checkTime != CheckTimeEnum.ON_SUBMIT);
  }

  private void edit(final T object, final boolean pcheck) {
    super.doEdit(object);
    if (pcheck) {
      validate();
    }
  }

  @Override
  public EditorVisitor createInitializerVisitor() {
    return new BeanValidationInitializer(commitOnReturnHandler, validateOnKeyUpHandler,
        validateOnVueChangeHandler, valueChangeHandler, checkTime, submitOnReturn);
  }

  @Override
  public T flush() {
    doFlush();
    return getObject();
  }

  @Override
  public void initialize(final E editor) {
    doInitialize(editor);
  }

  @Override
  public final boolean validate() {
    boolean valid = false;
    final T object = flush();
    if (!hasErrors()) {
      setConstraintViolations(validateContent(object));
      valid = !hasErrors();
    }
    if (submitButton instanceof HasEnabled && checkTime != CheckTimeEnum.ON_SUBMIT) {
      ((HasEnabled) submitButton).setEnabled(valid && (submitUnchanged || isDirty()));
    }
    return valid;
  }

  protected Set<ConstraintViolation<?>> validateContent(final T pobject) {
    final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    final Set<ConstraintViolation<T>> validationResult =
        validateContent(pobject, (AbstractGwtValidator) validator);
    return new HashSet<>(validationResult == null ? Collections.emptySet() : validationResult);
  }

  // protected Set<ConstraintViolation<T>> validateContent(final T pobject,
  // final AbstractGwtValidator pvalidator) throws IllegalArgumentException {
  // return pvalidator.validateInternal(pobject, validationGroups);
  // }
  protected abstract Set<ConstraintViolation<T>> validateContent(final T pobject,
      final AbstractGwtValidator pvalidator) throws IllegalArgumentException;

  @Override
  public final HandlerRegistration addFormSubmitHandler(final FormSubmitHandler<T> phandler) {
    return ensureHandlers().addHandler(FormSubmitEvent.getType(), phandler);
  }

  @Override
  public final HandlerRegistration addValueChangeHandler(final ValueChangeHandler<T> phandler) {
    return ensureHandlers().addHandler(ValueChangeEvent.getType(), phandler);
  }

  @Override
  public void fireEvent(final GwtEvent<?> pevent) {
    if (handlerManager != null) {
      handlerManager.fireEvent(pevent);
    }
  }

  @Override
  public final boolean tryToSubmitFrom() {
    return tryToSubmitFrom(isDirty());
  }

  @Override
  public final boolean tryToSubmitFrom(final boolean pdirty) {
    boolean result = false;
    if ((submitUnchanged || pdirty) && validate()) {
      if (!submitUnchanged) {
        // edit changed values, so dirty flag is gone and no useless resubmission is done
        edit(getObject(), false);
      }
      FormSubmitEvent.fire(this, getObject());
      result = true;
    }
    return result;
  }

  @Override
  public final boolean isSubmitUnchanged() {
    return submitUnchanged;
  }

  @Override
  public final void setSubmitUnchanged(final boolean psubmitUnchanged) {
    submitUnchanged = psubmitUnchanged;
  }

  @Override
  public final boolean isCheckOnKeyUp() {
    return checkTime == CheckTimeEnum.ON_KEY_UP;
  }

  @Override
  public final void setCheckOnKeyUp(final boolean pcheckOnKeyUp) throws RuntimeException {
    checkHandlerSet();
    if (pcheckOnKeyUp) {
      checkTime = CheckTimeEnum.ON_KEY_UP;
    } else {
      checkTime = CheckTimeEnum.ON_CHANGE;
    }
  }

  @Override
  public final CheckTimeEnum getCheckTime() {
    return checkTime;
  }

  @Override
  public final void setCheckTime(final CheckTimeEnum pcheckTime) throws RuntimeException {
    checkHandlerSet();
    checkTime = pcheckTime;
  }

  @Override
  public final boolean isSubmitOnReturn() {
    return submitOnReturn;
  }

  @Override
  public final void setSubmitOnReturn(final boolean psubmitOnReturn) throws RuntimeException {
    checkHandlerSet();
    submitOnReturn = psubmitOnReturn;
  }

  @Override
  public boolean isSubmitOnValueChange() {
    return submitOnValueChange;
  }

  @Override
  public void setSubmitOnValueChange(final boolean psubmitOnValueChange) {
    submitOnValueChange = psubmitOnValueChange;
  }

  /**
   * check if handlers are set, if it is, it throws a IllegalAccessException.
   *
   * @throws IllegalAccessException when handlers are already set
   */
  private void checkHandlerSet() throws RuntimeException {
    if (handlersSet) {
      throw new RuntimeException("Can only be called before the first edit call!");
    }
  }

  @Override
  public final void setSubmitButton(final Widget psubmitButton) {
    submitButton = psubmitButton;
    if (submitButton instanceof HasClickHandlers) {
      ((HasClickHandlers) submitButton).addClickHandler(pevent -> tryToSubmitFrom());
    }
  }

  /**
   * Ensures the existence of the handler manager.
   *
   * @return the handler manager
   */
  private HandlerManager ensureHandlers() {
    if (handlerManager == null) {
      handlerManager = new HandlerManager(this);
    }
    return handlerManager;
  }

  @Override
  public final void setValidationGroups(final Class<?>... pgroups) {
    validationGroups = pgroups;
  }
}
