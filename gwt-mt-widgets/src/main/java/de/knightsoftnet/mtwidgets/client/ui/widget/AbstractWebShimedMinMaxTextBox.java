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

package de.knightsoftnet.mtwidgets.client.ui.widget;

import de.knightsoftnet.mtwidgets.client.jswrapper.JQuery;
import de.knightsoftnet.mtwidgets.client.ui.widget.resourceloader.WebshimResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.text.shared.Parser;
import com.google.gwt.text.shared.Renderer;

import elemental.events.Event;
import elemental.html.InputElement;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * html5 date box for input with webshim fallback if not supported by browser.
 *
 * @author Manfred Tremmel
 *
 */
public abstract class AbstractWebShimedMinMaxTextBox<T> extends AbstractMinMaxTextBox<T> {

  final boolean nativeSupport;

  /**
   * constructor.
   *
   * @param pelement input element
   * @param ptype type of the input element
   * @param pnumberRenderer number renderer
   * @param pnumberParser number parser
   * @param pkeyPressHandler key press handler
   */
  public AbstractWebShimedMinMaxTextBox(final InputElement pelement, final String ptype,
      final Renderer<T> pnumberRenderer, final Parser<T> pnumberParser,
      final KeyPressHandler pkeyPressHandler) {
    super(pelement, ptype, pnumberRenderer, pnumberParser, pkeyPressHandler);
    this.nativeSupport = this.isNative();
  }

  /**
   * constructor.
   *
   * @param pelement input element
   * @param ptype type of the input element
   * @param pnumberRenderer number renderer
   * @param pnumberParser number parser
   * @param pplainNumberRenderer number renderer for internal usage
   * @param pplainNumberParser number parser for internal usage
   * @param pkeyPressHandler key press handler
   */
  public AbstractWebShimedMinMaxTextBox(final InputElement pelement, final String ptype,
      final Renderer<T> pnumberRenderer, final Parser<T> pnumberParser,
      final Renderer<T> pplainNumberRenderer, final Parser<T> pplainNumberParser,
      final KeyPressHandler pkeyPressHandler) {
    super(pelement, ptype, pnumberRenderer, pnumberParser, pplainNumberRenderer, pplainNumberParser,
        pkeyPressHandler);
    this.nativeSupport = this.isNative();
  }

  /**
   * constructor.
   *
   * @param pnumberRenderer number renderer
   * @param pnumberParser number parser
   * @param pkeyPressHandler key press handler
   */
  public AbstractWebShimedMinMaxTextBox(final Renderer<T> pnumberRenderer,
      final Parser<T> pnumberParser, final KeyPressHandler pkeyPressHandler) {
    super(pnumberRenderer, pnumberParser, pkeyPressHandler);
    this.nativeSupport = this.isNative();
  }

  /**
   * constructor.
   *
   * @param pnumberRenderer number renderer
   * @param pnumberParser number parser
   * @param pplainNumberRenderer number renderer for internal usage
   * @param pplainNumberParser number parser for internal usage
   * @param pkeyPressHandler key press handler
   */
  public AbstractWebShimedMinMaxTextBox(final Renderer<T> pnumberRenderer,
      final Parser<T> pnumberParser, final Renderer<T> pplainNumberRenderer,
      final Parser<T> pplainNumberParser, final KeyPressHandler pkeyPressHandler) {
    super(pnumberRenderer, pnumberParser, pplainNumberRenderer, pplainNumberParser,
        pkeyPressHandler);
    this.nativeSupport = this.isNative();
  }

  @Override
  protected void onEnsureDebugId(final String pbaseId) {
    super.onEnsureDebugId(pbaseId);
    if (!this.nativeSupport) {
      WebshimResources.whenReady(event -> {
        Scheduler.get().scheduleFixedDelay(() -> {
          this.getJQueryElement().updatePolyfill();
          GWT.log("Initialize webshim for: " + getElement().getId());
          try {
            this.getJQueryElement().getShadowElement().addEventListener(Event.CHANGE,
                listener -> ValueChangeEvent.fire(this, getValue()));
          } catch (final Exception exception) {
            GWT.log(exception.getMessage());
          }
          return false;
        }, 1000);
      });
    }
  }

  private boolean isNative() {
    final String rememberValue = getInputElement().getValue();
    getInputElement().setValue("x");
    final String dateInput = getInputElement().getValue();
    getInputElement().setValue(rememberValue);
    return !StringUtils.equals(dateInput, "x");
  }

  @Override
  public void setValue(final T value, final boolean fireEvents) {
    final T oldValue = getValue();
    final String renderedDate = numberRenderer.render(value);
    getInputElement().setValue(renderedDate);
    if (!this.nativeSupport && WebshimResources.isInitialized()) {
      this.getJQueryElement().val(renderedDate);
    }
    if (fireEvents) {
      ValueChangeEvent.fireIfNotEqual(this, oldValue, value);
    }
  }

  @Override
  public void setMin(final T pmin) {
    super.setMin(pmin);
    this.updateAttribute("min", numberRenderer.render(pmin));
  }

  @Override
  public void setMax(final T pmax) {
    super.setMax(pmax);
    this.updateAttribute("max", numberRenderer.render(pmax));
  }

  @Override
  public void setStep(final Integer pstep) {
    super.setStep(pstep);
    this.updateAttribute("step", Objects.toString(pstep, null));
  }

  private void updateAttribute(final String pattr, final String pvalue) {
    if (!this.nativeSupport && WebshimResources.isInitialized()) {
      if (StringUtils.isEmpty(pvalue)) {
        this.getJQueryElement().removeAttr(pattr);
      } else {
        this.getJQueryElement().attr(pattr, pvalue);
      }
    }
  }

  private JQuery getJQueryElement() {
    return JQuery.$("#" + getElement().getId());
  }
}
