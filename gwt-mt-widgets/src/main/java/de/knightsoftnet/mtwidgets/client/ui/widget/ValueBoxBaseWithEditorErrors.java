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

import de.knightsoftnet.mtwidgets.client.ui.widget.features.HasAutofocus;
import de.knightsoftnet.mtwidgets.client.ui.widget.features.HasDataList;
import de.knightsoftnet.mtwidgets.client.ui.widget.features.HasFormNoValidate;
import de.knightsoftnet.mtwidgets.client.ui.widget.features.HasPlaceholder;
import de.knightsoftnet.mtwidgets.client.ui.widget.features.HasRequired;
import de.knightsoftnet.mtwidgets.client.ui.widget.features.HasValidationMessageElement;
import de.knightsoftnet.mtwidgets.client.ui.widget.features.HasValidationPattern;
import de.knightsoftnet.mtwidgets.client.ui.widget.features.HasValidity;
import de.knightsoftnet.mtwidgets.client.ui.widget.helper.ErrorMessageFormater;
import de.knightsoftnet.mtwidgets.client.ui.widget.helper.FeatureCheck;

import com.google.gwt.dom.client.Element;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.text.shared.Parser;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ValueBoxBase;

import elemental.html.InputElement;
import elemental.html.ValidityState;

import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValueBoxBaseWithEditorErrors<T> extends ValueBoxBase<T>
    implements HasEditorErrors<T>, HasValidationMessageElement, HasAutofocus, HasRequired,
    HasValidity, HasPlaceholder, HasValidationPattern, HasFormNoValidate, HasDataList {

  private HTMLPanel validationMessageElement;

  protected ValueBoxBaseWithEditorErrors(final Element elem, final Renderer<T> renderer,
      final Parser<T> parser) {
    super(elem, renderer, parser);
  }

  public ValueBoxBaseWithEditorErrors(final InputElement element, final Renderer<T> renderer,
      final Parser<T> parser) {
    super((Element) element, renderer, parser);
  }

  @Override
  public void showErrors(final List<EditorError> errors) {
    final Set<String> messages = new HashSet<>();
    for (final EditorError error : errors) {
      if (this.editorErrorMatches(error)) {
        messages.add(error.getMessage());
      }
    }
    this.showErrors(messages);
  }

  /**
   * show error messages.
   *
   * @param messages set of messages
   */
  public void showErrors(final Set<String> messages) {
    final InputElement inputElement = this.getInputElement();
    if (messages.isEmpty()) {
      if (FeatureCheck.supportCustomValidity(inputElement)) {
        inputElement.setCustomValidity(StringUtils.EMPTY);
      }
      if (this.validationMessageElement == null) {
        inputElement.setTitle(StringUtils.EMPTY);
      } else {
        this.validationMessageElement.getElement().removeAllChildren();
      }
    } else {
      final String messagesAsString = ErrorMessageFormater.messagesToString(messages);
      if (FeatureCheck.supportCustomValidity(inputElement)) {
        inputElement.setCustomValidity(messagesAsString);
      }
      if (this.validationMessageElement == null) {
        inputElement.setTitle(messagesAsString);
      } else {
        this.validationMessageElement.getElement()
            .setInnerSafeHtml(ErrorMessageFormater.messagesToList(messages));
      }
    }
  }

  /**
   * Checks if a error belongs to this widget.
   *
   * @param perror editor error to check
   * @return true if the error belongs to this widget
   */
  protected boolean editorErrorMatches(final EditorError perror) {
    return perror != null && perror.getEditor() != null
        && (this.equals(perror.getEditor()) || perror.getEditor().equals(this.asEditor()));
  }

  public InputElement getInputElement() {
    return this.getElement().cast();
  }

  @Override
  public String getValidationMessage() {
    return this.getInputElement().getValidationMessage();
  }

  @Override
  public ValidityState getValidity() {
    return this.getInputElement().getValidity();
  }

  @Override
  public boolean checkValidity() {
    return this.getInputElement().checkValidity();
  }

  @Override
  public boolean isFormNoValidate() {
    return this.getInputElement().isFormNoValidate();
  }

  @Override
  public void setFormNoValidate(final boolean arg) {
    this.getInputElement().setFormNoValidate(arg);
  }

  @Override
  public boolean isRequired() {
    return this.getInputElement().isRequired();
  }

  @Override
  public void setRequired(final boolean arg) {
    this.getInputElement().setRequired(arg);
  }

  @Override
  public String getPattern() {
    return this.getInputElement().getPattern();
  }

  @Override
  public void setPattern(final String arg) {
    this.getInputElement().setPattern(arg);
  }


  @Override
  public String getPlaceholder() {
    return this.getInputElement().getPlaceholder();
  }

  @Override
  public void setPlaceholder(final String arg) {
    this.getInputElement().setPlaceholder(arg);
  }

  @Override
  public boolean isAutofocus() {
    return this.getInputElement().isAutofocus();
  }

  @Override
  public void setAutofocus(final boolean arg) {
    this.getInputElement().setAutofocus(arg);
  }

  @Override
  public void setValidationMessageElement(final HTMLPanel pelement) {
    this.validationMessageElement = pelement;
  }

  @Override
  public HTMLPanel getValidationMessageElement() {
    return this.validationMessageElement;
  }

  @Override
  public void setDataListWidget(final DataListWidget pdataListWidget) {
    this.getInputElement().setAttribute("list", pdataListWidget.getElement().getId());
  }
}
