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

import de.knightsoftnet.validators.client.decorators.AbstractDecorator;
import de.knightsoftnet.validators.client.decorators.ExtendedValueBoxEditor;
import de.knightsoftnet.validators.client.editor.CheckTimeEnum;

import com.google.gwt.editor.client.EditorContext;
import com.google.gwt.editor.client.impl.Initializer;
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

public class BeanValidationInitializer extends Initializer {

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
   * check input time.
   */
  private final CheckTimeEnum checkTime;

  /**
   * submit form when enter/return is hit.
   */
  private final boolean submitOnReturn;

  /**
   * constructor.
   *
   * @param pcommitOnReturnHandler commit on return handler
   * @param pvalidateOnKeyUpHandler on key up handler
   * @param pvalidateOnVueChangeHandler on vue change handler
   * @param pvalueChangeHandler value change handler
   * @param pcheckTime enumeration which describes when to do the check
   * @param psubmitOnReturn submit form if return is pressed in a input field
   */
  public BeanValidationInitializer(final KeyPressHandler pcommitOnReturnHandler,
      final KeyUpHandler pvalidateOnKeyUpHandler,
      final ValueChangeHandler<?> pvalidateOnVueChangeHandler,
      final ValueChangeHandler<?> pvalueChangeHandler, final CheckTimeEnum pcheckTime,
      final boolean psubmitOnReturn) {
    super();
    this.commitOnReturnHandler = pcommitOnReturnHandler;
    this.validateOnKeyUpHandler = pvalidateOnKeyUpHandler;
    this.validateOnVueChangeHandler = pvalidateOnVueChangeHandler;
    this.valueChangeHandler = pvalueChangeHandler;
    this.checkTime = pcheckTime;
    this.submitOnReturn = psubmitOnReturn;
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  @Override
  public <Q> boolean visit(final EditorContext<Q> pctx) {
    final boolean result = super.visit(pctx);
    if (pctx.getEditor() instanceof ExtendedValueBoxEditor<?>
        && ((ExtendedValueBoxEditor<?>) pctx.getEditor()).getDecorator() != null) {
      final AbstractDecorator<?> decorator =
          ((ExtendedValueBoxEditor<?>) pctx.getEditor()).getDecorator();
      decorator.setFocusOnError(this.checkTime == CheckTimeEnum.ON_SUBMIT);
      // add value change handler which delegates changes
      decorator.addValueChangeHandler((ValueChangeHandler) this.valueChangeHandler);
      // if widget has a value change handler, validate on change
      if ((this.checkTime == CheckTimeEnum.ON_CHANGE || this.checkTime == CheckTimeEnum.ON_KEY_UP)
          && this.validateOnVueChangeHandler != null) {
        decorator.addValueChangeHandler((ValueChangeHandler) this.validateOnVueChangeHandler);
      }
      // if widget has a key up handler, validate on key up
      if (this.checkTime == CheckTimeEnum.ON_KEY_UP && this.validateOnKeyUpHandler != null) {
        decorator.addKeyUpHandler(this.validateOnKeyUpHandler);
      }
      // try to submit form on return
      if (this.submitOnReturn && this.commitOnReturnHandler != null) {
        decorator.addKeyPressHandler(this.commitOnReturnHandler);
      }
    } else {
      if (pctx.getEditor() instanceof HasValueChangeHandlers) {
        ((HasValueChangeHandlers) pctx.getEditor()).addValueChangeHandler(this.valueChangeHandler);
        // if widget has a value change handler, validate on change
        if ((this.checkTime == CheckTimeEnum.ON_CHANGE || this.checkTime == CheckTimeEnum.ON_KEY_UP)
            && this.validateOnVueChangeHandler != null) {
          ((HasValueChangeHandlers) pctx.getEditor())
              .addValueChangeHandler(this.validateOnVueChangeHandler);
        }
      }
      // if widget has a key up handler, validate on key up
      if (pctx.getEditor() instanceof HasKeyUpHandlers && this.checkTime == CheckTimeEnum.ON_KEY_UP
          && this.validateOnKeyUpHandler != null) {
        ((HasKeyUpHandlers) pctx.getEditor()).addKeyUpHandler(this.validateOnKeyUpHandler);
      }
      // try to submit form on return
      if (pctx.getEditor() instanceof HasKeyPressHandlers && this.submitOnReturn
          && this.commitOnReturnHandler != null) {
        ((HasKeyPressHandlers) pctx.getEditor()).addKeyPressHandler(this.commitOnReturnHandler);
      }
    }

    return result;
  }
}
