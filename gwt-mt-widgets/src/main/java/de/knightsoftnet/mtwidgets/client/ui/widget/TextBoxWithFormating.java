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

import de.knightsoftnet.mtwidgets.client.ui.widget.features.HasFormating;
import de.knightsoftnet.validators.shared.data.ValueWithPos;

import com.google.gwt.dom.client.Element;

import elemental.html.InputElement;

/**
 * text box helper input field with formating support.
 *
 * @author Manfred Tremmel
 *
 */
public class TextBoxWithFormating extends TextBox implements HasFormating {

  private HasFormating formating;

  public TextBoxWithFormating(final InputElement pelement, final String ptype) {
    super((Element) pelement);
    pelement.setAttribute("type", ptype);
  }

  public final HasFormating getFormating() {
    return formating;
  }

  public final void setFormating(final HasFormating formating) {
    this.formating = formating;
  }

  @Override
  public void reformatValue() {
    formating.reformatValue();
  }

  @Override
  public void formatValue(final ValueWithPos<String> pvalue) {
    formating.formatValue(pvalue);
  }

  @Override
  public boolean isAllowedCharacter(final char pcharacter) {
    return formating.isAllowedCharacter(pcharacter);
  }

  @Override
  public boolean isCharacterToReplace(final char pcharacter) {
    return formating.isCharacterToReplace(pcharacter);
  }

  @Override
  public boolean isFormatingCharacter(final char pcharacter) {
    return formating.isFormatingCharacter(pcharacter);
  }

  @Override
  public char replaceCharacter(final char pcharacter) {
    return formating.replaceCharacter(pcharacter);
  }
}
