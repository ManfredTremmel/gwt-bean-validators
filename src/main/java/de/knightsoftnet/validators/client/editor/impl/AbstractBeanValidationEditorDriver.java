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

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.editor.client.impl.AbstractExtendedBaseEditorDriver;
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
import com.google.gwt.validation.client.impl.Validation;

import de.knightsoftnet.validators.client.decorators.AbstractDecorator;
import de.knightsoftnet.validators.client.decorators.ExtendedValueBoxEditor;
import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;
import de.knightsoftnet.validators.client.event.FormSubmitEvent;
import de.knightsoftnet.validators.client.event.FormSubmitHandler;

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
public abstract class AbstractBeanValidationEditorDriver<T, E extends Editor<T>> extends
    AbstractExtendedBaseEditorDriver<T, E> implements BeanValidationEditorDriver<T, E> {

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
   * submit button.
   */
  private Widget submitButton;


  @SuppressWarnings({"unchecked", "rawtypes"})
  @Override
  public void edit(final T object) {
    this.doEdit(object);
    this.validate();
    if (!this.handlersSet) {
      this.handlersSet = true;
      for (final LeafValueEditor<?> entry : super.getLeafValueMap().keySet()) {
        if (entry instanceof ExtendedValueBoxEditor<?>) {
          final AbstractDecorator<?> decorator = ((ExtendedValueBoxEditor<?>) entry).getDecorator();
          decorator.setFocusOnError(false);
          // if widget has a value change handler, validate on change
          decorator.addValueChangeHandler(new ValueChangeHandler() {
            @Override
            public void onValueChange(final ValueChangeEvent pevent) {
              AbstractBeanValidationEditorDriver.this.validate();
              ValueChangeEvent.fire(AbstractBeanValidationEditorDriver.this,
                  AbstractBeanValidationEditorDriver.this.getObject());
            }
          });
          // if widget has a key up handler, validate on key up
          decorator.addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(final KeyUpEvent pevent) {
              AbstractBeanValidationEditorDriver.this.validate();
            }
          });
          // try to submit form on return
          decorator.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(final KeyPressEvent pevent) {
              if (pevent.getCharCode() == KeyCodes.KEY_ENTER
                  || pevent.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                AbstractBeanValidationEditorDriver.this.tryToSubmitFrom();
              }
            }
          });
        }
      }
    }
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
      final Set<ConstraintViolation<T>> violations = validator.validate(object);
      this.setConstraintViolations(new ArrayList<ConstraintViolation<?>>(violations));
      valid = !this.hasErrors();
    }
    if (this.submitButton instanceof HasEnabled) {
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
    if (this.validate() && (this.submitUnchanged || this.isDirty())) {
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
  public void setSubmitButton(final Widget psubmitButton) {
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
   * */
  private HandlerManager ensureHandlers() {
    if (this.handlerManager == null) {
      this.handlerManager = new HandlerManager(this);
    }
    return this.handlerManager;
  }
}
