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

import de.knightsoftnet.mtwidgets.client.ui.widget.helper.BooleanParser;
import de.knightsoftnet.mtwidgets.client.ui.widget.helper.BooleanRenderer;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.user.client.ui.FormPanel;

import elemental.client.Browser;

import org.apache.commons.lang3.BooleanUtils;

import java.text.ParseException;

/**
 * input field of type radio button (without label).
 *
 * @author Manfred Tremmel
 *
 */
public class RadioButton extends ValueBox<Boolean> {

  /**
   * default constructor.
   */
  public RadioButton() {
    super(Browser.getDocument().createInputElement(), "radio", BooleanRenderer.instance(),
        BooleanParser.instance());
  }

  /**
   * Returns the value property of the input element that backs this widget. This is the value that
   * will be associated with the CheckBox name and submitted to the server if a {@link FormPanel}
   * that holds it is submitted and the box is checked.
   * <p>
   * Don't confuse this with {@link #getValue}, which returns true or false if the widget is
   * checked.
   * </p>
   *
   * @return value of the form value
   */
  public String getFormValue() {
    return getInputElement().getValue();
  }

  /**
   * Set the value property on the input element that backs this widget. This is the value that will
   * be associated with the CheckBox's name and submitted to the server if a {@link FormPanel} that
   * holds it is submitted and the box is checked.
   * <p>
   * Don't confuse this with {@link #setValue}, which actually checks and unchecks the box.
   * </p>
   *
   * @param value form value to set
   */
  public void setFormValue(final String value) {
    getInputElement().setAttribute("value", value);
  }

  @Override
  public Boolean getValueOrThrow() throws ParseException {
    return Boolean.valueOf(getInputElement().isChecked());
  }

  @Override
  public void setValue(final Boolean value, final boolean fireEvents) {
    final Boolean oldValue = getValue();
    getInputElement().setChecked(BooleanUtils.isTrue(value));
    if (fireEvents) {
      ValueChangeEvent.fireIfNotEqual(this, oldValue, value);
    }
  }
}
