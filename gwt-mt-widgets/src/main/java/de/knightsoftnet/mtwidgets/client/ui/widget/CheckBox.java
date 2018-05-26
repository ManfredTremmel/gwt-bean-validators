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

import elemental.client.Browser;

import org.apache.commons.lang3.BooleanUtils;

import java.text.ParseException;

/**
 * input field of type checkbox.
 *
 * @author Manfred Tremmel
 *
 */
public class CheckBox extends ValueBox<Boolean> {

  /**
   * default constructor.
   */
  public CheckBox() {
    super(Browser.getDocument().createInputElement(), "checkbox", BooleanRenderer.instance(),
        BooleanParser.instance());
  }

  @Override
  public Boolean getValueOrThrow() throws ParseException {
    return Boolean.valueOf(this.getInputElement().isChecked());
  }

  @Override
  public void setValue(final Boolean value, final boolean fireEvents) {
    final Boolean oldValue = this.getValue();
    this.getInputElement().setChecked(BooleanUtils.isTrue(value));
    if (fireEvents) {
      ValueChangeEvent.fireIfNotEqual(this, oldValue, value);
    }
  }

}
