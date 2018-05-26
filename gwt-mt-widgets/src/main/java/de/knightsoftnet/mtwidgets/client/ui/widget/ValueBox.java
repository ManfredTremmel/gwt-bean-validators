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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.text.shared.Parser;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * A text box able to parse its displayed value.
 *
 * @param <T> the value type
 */
public class ValueBox<T> extends ValueBoxBaseWithEditorErrors<T> {

  /**
   * Creates a ValueBox widget that wraps an existing &lt;input type='text'&gt; element.
   *
   * <p>
   * This element must already be attached to the document. If the element is removed from the
   * document, you must call {@link RootPanel#detachNow(Widget)}.
   * </p>
   *
   * @param <T> the value type
   *
   * @param element the element to be wrapped
   * @param renderer rendering routine
   * @param parser parsing routine
   * @return value box
   */
  public static <T> ValueBox<T> wrap(final Element element, final Renderer<T> renderer,
      final Parser<T> parser) {
    // Assert that the element is attached.
    assert Document.get().getBody().isOrHasChild(element);

    final ValueBox<T> valueBox = new ValueBox<>(element, renderer, parser);

    // Mark it attached and remember it for cleanup.
    valueBox.onAttach();
    RootPanel.detachOnWindowClose(valueBox);

    return valueBox;
  }

  /**
   * This constructor may be used by subclasses to explicitly use an existing element. This element
   * must be an &lt;input&gt; element whose type is 'text'.
   *
   * @param element the element to be used
   * @param renderer rendering routine
   * @param parser parsing routine
   */
  protected ValueBox(final Element element, final Renderer<T> renderer, final Parser<T> parser) {
    super(element, renderer, parser);
    // BiDi input is not expected - disable direction estimation.
    this.setDirectionEstimator(false);
    if (LocaleInfo.getCurrentLocale().isRTL()) {
      this.setDirection(Direction.LTR);
    }
    assert InputElement.as(element).getType().equalsIgnoreCase("text");
  }

  /**
   * This constructor may be used by subclasses to explicitly use an existing element. This element
   * must be an &lt;input&gt; element the type is given as separate parameter and is not limited.
   *
   * @param element the element to be used
   * @param inputType input type of the element to set /
   * @param renderer rendering routine
   * @param parser parsing routine
   */
  protected ValueBox(final elemental.html.InputElement element, final String inputType,
      final Renderer<T> renderer, final Parser<T> parser) {
    super(element, renderer, parser);
    element.setAttribute("type", inputType);
    // BiDi input is not expected - disable direction estimation.
    this.setDirectionEstimator(false);
    if (LocaleInfo.getCurrentLocale().isRTL()) {
      this.setDirection(Direction.LTR);
    }
  }

  /**
   * Gets the maximum allowable length.
   *
   * @return the maximum length, in characters
   */
  public int getMaxLength() {
    return this.getInputElement().getMaxLength();
  }

  /**
   * Gets the number of visible characters.
   *
   * @return the number of visible characters
   */
  public int getVisibleLength() {
    return this.getInputElement().getSize();
  }

  /**
   * Sets the maximum allowable length.
   *
   * @param length the maximum length, in characters
   */
  public void setMaxLength(final int length) {
    this.getInputElement().setMaxLength(length);
  }

  /**
   * Sets the number of visible characters.
   *
   * @param length the number of visible characters
   */
  public void setVisibleLength(final int length) {
    this.getInputElement().setSize(length);
  }
}