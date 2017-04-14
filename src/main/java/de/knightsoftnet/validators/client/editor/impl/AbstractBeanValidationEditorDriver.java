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
import de.knightsoftnet.validators.client.impl.Validation;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorVisitor;
import com.google.gwt.editor.client.impl.BaseEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
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
  private Class<?>[] validationGroups;

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
  public AbstractBeanValidationEditorDriver() {
    super();
    this.commitOnReturnHandler = new KeyPressHandler() {
      @Override
      public void onKeyPress(final KeyPressEvent pevent) {
        if (pevent.getCharCode() == KeyCodes.KEY_ENTER
            || pevent.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
          AbstractBeanValidationEditorDriver.this.tryToSubmitFrom();
        }
      }
    };
    this.validateOnKeyUpHandler = new KeyUpHandler() {
      @Override
      public void onKeyUp(final KeyUpEvent pevent) {
        AbstractBeanValidationEditorDriver.this.validate();
      }
    };
    this.validateOnVueChangeHandler = new ValueChangeHandler<Object>() {
      @Override
      public void onValueChange(final ValueChangeEvent<Object> pevent) {
        AbstractBeanValidationEditorDriver.this.validate();
      }
    };
    this.valueChangeHandler = new ValueChangeHandler<Object>() {
      @Override
      public void onValueChange(final ValueChangeEvent<Object> pevent) {
        ValueChangeEvent.fire(AbstractBeanValidationEditorDriver.this,
            AbstractBeanValidationEditorDriver.this.getObject());
        if (AbstractBeanValidationEditorDriver.this.submitOnValueChange) {
          AbstractBeanValidationEditorDriver.this.tryToSubmitFrom();
        }
      }
    };
  }

  @Override
  public void edit(final T object) {
    this.edit(object, this.checkTime != CheckTimeEnum.ON_SUBMIT);
  }

  private void edit(final T object, final boolean pcheck) {
    super.doEdit(object);
    if (pcheck) {
      this.validate();
    }
  }

  @Override
  protected EditorVisitor createInitializerVisitor() {
    return new BeanValidationInitializer(this.commitOnReturnHandler, this.validateOnKeyUpHandler,
        this.validateOnVueChangeHandler, this.valueChangeHandler, this.checkTime,
        this.submitOnReturn);
  }

  @Override
  public T flush() {
    this.doFlush();
    return this.getObject();
  }

  @Override
  public void initialize(final E editor) {
    this.doInitialize(editor);
  }

  @Override
  public final boolean validate() {
    boolean valid = false;
    final T object = this.flush();
    if (!this.hasErrors()) {
      final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
      final Set<ConstraintViolation<T>> violations;
      if (this.validationGroups == null) {
        violations = validator.validate(object);
      } else {
        violations = validator.validate(object, this.validationGroups);
      }
      this.setConstraintViolations(new ArrayList<ConstraintViolation<?>>(violations));
      valid = !this.hasErrors();
    }
    if (this.submitButton instanceof HasEnabled && this.checkTime != CheckTimeEnum.ON_SUBMIT) {
      ((HasEnabled) this.submitButton)
          .setEnabled(valid && (this.submitUnchanged || this.isDirty()));
    }
    return valid;
  }

  @Override
  public final HandlerRegistration addFormSubmitHandler(final FormSubmitHandler<T> phandler) {
    return this.ensureHandlers().addHandler(FormSubmitEvent.getType(), phandler);
  }

  @Override
  public final HandlerRegistration addValueChangeHandler(final ValueChangeHandler<T> phandler) {
    return this.ensureHandlers().addHandler(ValueChangeEvent.getType(), phandler);
  }

  @Override
  public void fireEvent(final GwtEvent<?> pevent) {
    if (this.handlerManager != null) {
      this.handlerManager.fireEvent(pevent);
    }
  }

  @Override
  public final boolean tryToSubmitFrom() {
    boolean result = false;
    final boolean dirty = this.isDirty();
    if ((this.submitUnchanged || dirty) && this.validate()) {
      this.edit(this.getObject(), false);
      FormSubmitEvent.fire(this, this.getObject());
      result = true;
    }
    return result;
  }

  @Override
  public final boolean isSubmitUnchanged() {
    return this.submitUnchanged;
  }

  @Override
  public final void setSubmitUnchanged(final boolean psubmitUnchanged) {
    this.submitUnchanged = psubmitUnchanged;
  }

  @Override
  public final boolean isCheckOnKeyUp() {
    return this.checkTime == CheckTimeEnum.ON_KEY_UP;
  }

  @Override
  public final void setCheckOnKeyUp(final boolean pcheckOnKeyUp) throws RuntimeException {
    this.checkHandlerSet();
    if (pcheckOnKeyUp) {
      this.checkTime = CheckTimeEnum.ON_KEY_UP;
    } else {
      this.checkTime = CheckTimeEnum.ON_CHANGE;
    }
  }

  @Override
  public final CheckTimeEnum getCheckTime() {
    return this.checkTime;
  }

  @Override
  public final void setCheckTime(final CheckTimeEnum pcheckTime) throws RuntimeException {
    this.checkHandlerSet();
    this.checkTime = pcheckTime;
  }

  @Override
  public final boolean isSubmitOnReturn() {
    return this.submitOnReturn;
  }

  @Override
  public final void setSubmitOnReturn(final boolean psubmitOnReturn) throws RuntimeException {
    this.checkHandlerSet();
    this.submitOnReturn = psubmitOnReturn;
  }

  @Override
  public boolean isSubmitOnValueChange() {
    return this.submitOnValueChange;
  }

  @Override
  public void setSubmitOnValueChange(final boolean psubmitOnValueChange) {
    this.submitOnValueChange = psubmitOnValueChange;
  }

  /**
   * check if handlers are set, if it is, it throws a IllegalAccessException.
   *
   * @throws IllegalAccessException when handlers are already set
   */
  private void checkHandlerSet() throws RuntimeException {
    if (this.handlersSet) {
      throw new RuntimeException("Can only be called before the first edit call!");
    }
  }

  @Override
  public final void setSubmitButton(final Widget psubmitButton) {
    this.submitButton = psubmitButton;
    if (this.submitButton instanceof HasClickHandlers) {
      ((HasClickHandlers) this.submitButton).addClickHandler(new ClickHandler() {
        @Override
        public void onClick(final ClickEvent pevent) {
          AbstractBeanValidationEditorDriver.this.tryToSubmitFrom();
        }
      });
    }
  }

  /**
   * Ensures the existence of the handler manager.
   *
   * @return the handler manager
   */
  private HandlerManager ensureHandlers() {
    if (this.handlerManager == null) {
      this.handlerManager = new HandlerManager(this);
    }
    return this.handlerManager;
  }

  @Override
  public final void setValidationGroups(final Class<?>... pgroups) {
    this.validationGroups = pgroups;
  }
}
