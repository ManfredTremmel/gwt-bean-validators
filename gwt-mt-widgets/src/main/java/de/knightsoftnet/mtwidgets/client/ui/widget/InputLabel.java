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
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.i18n.client.HasDirection;
import com.google.gwt.i18n.shared.DirectionEstimator;
import com.google.gwt.i18n.shared.HasDirectionEstimator;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.annotations.IsSafeHtml;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.DirectionalTextHelper;
import com.google.gwt.user.client.ui.HasDirectionalText;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

/**
 * label for input field, based on
 * http://stackoverflow.com/questions/1533899/gwt-is-there-a-label-widget.
 *
 * @author Manfred Tremmel
 *
 */
public class InputLabel extends Widget implements HasDirectionalText, HasDirectionEstimator {

  final DirectionalTextHelper directionalTextHelper;
  private boolean init = false;

  public InputLabel() {
    this(Document.get().createLabelElement());
  }

  /**
   * constructor.
   *
   * @param element to use
   */
  public InputLabel(final Element element) {
    super();
    assert LabelElement.TAG.equalsIgnoreCase(element.getTagName());

    this.setElement(element);
    directionalTextHelper = new DirectionalTextHelper(getElement(), true);
  }

  @Override
  public DirectionEstimator getDirectionEstimator() {
    return directionalTextHelper.getDirectionEstimator();
  }

  @Override
  public void setDirectionEstimator(final DirectionEstimator directionEstimator) {
    directionalTextHelper.setDirectionEstimator(directionEstimator);
  }

  @Override
  public void setDirectionEstimator(final boolean enabled) {
    directionalTextHelper.setDirectionEstimator(enabled);
  }

  private InputElement getInputElement(final Widget widget) {
    if (widget.getElement().hasTagName(InputElement.TAG)) {
      return InputElement.as(widget.getElement());
    }
    final NodeList<Element> l = widget.getElement().getElementsByTagName(InputElement.TAG);
    if (l.getLength() > 0) {
      return InputElement.as(l.getItem(0));
    }

    return null;
  }

  /**
   * set widget to reference to.
   *
   * @param target reference field
   */
  public void setFor(final IsWidget target) {
    if (init) {
      return;
    }
    init = true;
    //

    final InputElement input = getInputElement(target.asWidget());
    if (input != null) {
      if (!input.hasAttribute("id")) {
        input.setId(DOM.createUniqueId());
      }
      getElement().setAttribute("for", input.getId());
    }
  }

  public void setForm(final String form) {
    getElement().setAttribute("form", form);
  }

  @Override
  public String getText() {
    return directionalTextHelper.getTextOrHtml(false);
  }

  @Override
  public void setText(@IsSafeHtml final String text) {
    directionalTextHelper.setTextOrHtml(text, false);
  }

  @Override
  public void setText(final String text, final HasDirection.Direction dir) {
    directionalTextHelper.setTextOrHtml(text, dir, false);
  }

  public void setHtml(final SafeHtml text) {
    directionalTextHelper.setHtml(text);
  }

  @Override
  public HasDirection.Direction getTextDirection() {
    return directionalTextHelper.getTextDirection();
  }
}
