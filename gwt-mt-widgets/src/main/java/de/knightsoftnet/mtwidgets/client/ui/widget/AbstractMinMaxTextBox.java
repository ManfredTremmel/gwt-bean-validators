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

import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.text.shared.Parser;
import com.google.gwt.text.shared.Renderer;

import elemental.client.Browser;
import elemental.html.InputElement;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;

/**
 * number box for input without separators.
 *
 * @author Manfred Tremmel
 *
 * @param <T> type of the value
 */
public abstract class AbstractMinMaxTextBox<T> extends ValueBox<T> {

  protected final Renderer<T> numberRenderer;
  protected final Parser<T> numberParser;

  /**
   * constructor.
   *
   * @param pnumberRenderer number renderer
   * @param pnumberParser number parser
   * @param pkeyPressHandler key press handler
   */
  public AbstractMinMaxTextBox(final Renderer<T> pnumberRenderer, final Parser<T> pnumberParser,
      final KeyPressHandler pkeyPressHandler) {
    this(Browser.getDocument().createInputElement(), "number", pnumberRenderer, pnumberParser,
        pnumberRenderer, pnumberParser, pkeyPressHandler);
  }

  /**
   * constructor.
   *
   * @param pelement input element
   * @param ptype type of the input element
   * @param pnumberRenderer number renderer
   * @param pnumberParser number parser
   * @param pkeyPressHandler key press handler
   */
  public AbstractMinMaxTextBox(final InputElement pelement, final String ptype,
      final Renderer<T> pnumberRenderer, final Parser<T> pnumberParser,
      final KeyPressHandler pkeyPressHandler) {
    this(pelement, ptype, pnumberRenderer, pnumberParser, pnumberRenderer, pnumberParser,
        pkeyPressHandler);
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
  public AbstractMinMaxTextBox(final Renderer<T> pnumberRenderer, final Parser<T> pnumberParser,
      final Renderer<T> pplainNumberRenderer, final Parser<T> pplainNumberParser,
      final KeyPressHandler pkeyPressHandler) {
    this(Browser.getDocument().createInputElement(), "number", pnumberRenderer, pnumberParser,
        pplainNumberRenderer, pplainNumberParser, pkeyPressHandler);
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
  public AbstractMinMaxTextBox(final InputElement pelement, final String ptype,
      final Renderer<T> pnumberRenderer, final Parser<T> pnumberParser,
      final Renderer<T> pplainNumberRenderer, final Parser<T> pplainNumberParser,
      final KeyPressHandler pkeyPressHandler) {
    super(pelement, ptype, pnumberRenderer, pnumberParser);
    this.numberRenderer = pplainNumberRenderer;
    this.numberParser = pplainNumberParser;
    if (pkeyPressHandler != null) {
      this.addKeyPressHandler(pkeyPressHandler);
    }
  }

  /**
   * set minimum allowed value.
   *
   * @param pmin minimum value allowed
   */
  public void setMin(final T pmin) {
    if (pmin == null) {
      this.getInputElement().removeAttribute("min");
    } else {
      this.getInputElement().setMin(this.numberRenderer.render(pmin));
    }
  }

  /**
   * set minimum allowed value.
   *
   * @param pmin minimum value allowed
   */
  public void setMin(final String pmin) {
    try {
      this.setMin(this.numberParser.parse(pmin));
    } catch (final ParseException e) {
      this.setMin((T) null);
    }
  }

  /**
   * get minimum allowed value.
   *
   * @return minimum value allowed
   */
  public T getMin() {
    if (StringUtils.isEmpty(this.getInputElement().getMin())) {
      return null;
    }
    try {
      return this.numberParser.parse(this.getInputElement().getMin());
    } catch (final ParseException e) {
      return null;
    }
  }

  /**
   * set maximum allowed value.
   *
   * @param pmax maximum value allowed
   */
  public void setMax(final T pmax) {
    if (pmax == null) {
      this.getInputElement().removeAttribute("max");
    } else {
      this.getInputElement().setMax(this.numberRenderer.render(pmax));
    }
  }

  /**
   * set maximum allowed value.
   *
   * @param pmax maximum value allowed
   */
  public void setMax(final String pmax) {
    try {
      this.setMax(this.numberParser.parse(pmax));
    } catch (final ParseException e) {
      this.setMax((T) null);
    }
  }


  /**
   * get maximum allowed value.
   *
   * @return maximum value allowed
   */
  public T getMax() {
    if (StringUtils.isEmpty(this.getInputElement().getMax())) {
      return null;
    }
    try {
      return this.numberParser.parse(this.getInputElement().getMax());
    } catch (final ParseException e) {
      return null;
    }
  }

  /**
   * set distance value should be increased/decreased when using up/down buttons.
   *
   * @param pstep step distance
   */
  public void setStep(final Integer pstep) {
    if (pstep == null) {
      this.getInputElement().removeAttribute("step");
    } else {
      this.getInputElement().setStep(pstep.toString());
    }
  }

  /**
   * set distance value should be increased/decreased when using up/down buttons.
   *
   * @param pstep step distance
   */
  public void setStep(final String pstep) {
    try {
      this.setStep(Integer.valueOf(pstep));
    } catch (final NumberFormatException e) {
      this.setStep((Integer) null);
    }
  }


  /**
   * get distance value should be increased/decreased when using up/down buttons.
   *
   * @return maximum value allowed
   */
  public Integer getStep() {
    if (StringUtils.isEmpty(this.getInputElement().getStep())) {
      return null;
    }
    try {
      return Integer.valueOf(this.getInputElement().getStep());
    } catch (final NumberFormatException e) {
      return null;
    }
  }
}
