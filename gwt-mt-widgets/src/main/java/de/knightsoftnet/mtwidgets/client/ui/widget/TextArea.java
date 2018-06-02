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
import de.knightsoftnet.mtwidgets.client.ui.widget.features.HasPlaceholder;
import de.knightsoftnet.mtwidgets.client.ui.widget.features.HasRequired;
import de.knightsoftnet.mtwidgets.client.ui.widget.features.HasValidationMessageElement;
import de.knightsoftnet.mtwidgets.client.ui.widget.features.HasValidity;
import de.knightsoftnet.mtwidgets.client.ui.widget.helper.ErrorMessageFormater;
import de.knightsoftnet.mtwidgets.client.ui.widget.helper.FeatureCheck;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TextAreaElement;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.Widget;

import elemental.html.ValidityState;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A text box that allows multiple lines of text to be entered.
 *
 * <p>
 * &lt;img class='gallery' src='doc-files/TextArea.png'/&gt;
 * </p>
 *
 * <h3>CSS Style Rules</h3>
 * <ul class="css">
 * <li>.gwt-TextArea { primary style }</li>
 * <li>.gwt-TextArea-readonly { dependent style set when the text area is read-only }</li>
 * </ul>
 *
 * <h3>Built-in Bidi Text Support</h3>
 * <p>
 * This widget is capable of automatically adjusting its direction according to the input text. This
 * feature is controlled by {@link #setDirectionEstimator}, and is available by default when at
 * least one of the application's locales is right-to-left.
 * </p>
 */
public class TextArea extends TextBoxBase implements HasEditorErrors<String>,
    HasValidationMessageElement, HasAutofocus, HasRequired, HasValidity, HasPlaceholder {

  private HTMLPanel validationMessageElement;

  /**
   * Creates a TextArea widget that wraps an existing &lt;textarea&gt; element.
   *
   * <p>
   * This element must already be attached to the document. If the element is removed from the
   * document, you must call {@link RootPanel#detachNow(Widget)}.
   * </p>
   *
   * @param element the element to be wrapped
   * @return text area widget
   */
  public static TextArea wrap(final Element element) {
    // Assert that the element is attached.
    assert Document.get().getBody().isOrHasChild(element);

    final TextArea textArea = new TextArea(element);

    // Mark it attached and remember it for cleanup.
    textArea.onAttach();
    RootPanel.detachOnWindowClose(textArea);

    return textArea;
  }

  /**
   * Creates an empty text area.
   */
  public TextArea() {
    super(Document.get().createTextAreaElement());
    this.setStyleName("gwt-TextArea");
  }

  /**
   * This constructor may be used by subclasses to explicitly use an existing element. This element
   * must be a &lt;textarea&gt; element.
   *
   * @param element the element to be used
   */
  protected TextArea(final Element element) {
    super(element.<Element>cast());
    TextAreaElement.as(element);
  }

  /**
   * Gets the requested width of the text box (this is not an exact value, as not all characters are
   * created equal).
   *
   * @return the requested width, in characters
   */
  public int getCharacterWidth() {
    return getTextAreaElement().getCols();
  }

  @Override
  public int getCursorPos() {
    return getImpl().getTextAreaCursorPos(getElement());
  }

  @Override
  public int getSelectionLength() {
    return getImpl().getTextAreaSelectionLength(getElement());
  }

  /**
   * Gets the number of text lines that are visible.
   *
   * @return the number of visible lines
   */
  public int getVisibleLines() {
    return getTextAreaElement().getRows();
  }

  /**
   * Sets the requested width of the text box (this is not an exact value, as not all characters are
   * created equal).
   *
   * @param width the requested width, in characters
   */
  public void setCharacterWidth(final int width) {
    getTextAreaElement().setCols(width);
  }

  /**
   * Sets the number of text lines that are visible.
   *
   * @param lines the number of visible lines
   */
  public void setVisibleLines(final int lines) {
    getTextAreaElement().setRows(lines);
  }

  private TextAreaElement getTextAreaElement() {
    return getElement().cast();
  }

  @Override
  public void showErrors(final List<EditorError> perrors) {
    final elemental.html.TextAreaElement element = getInputElement();
    final Set<String> messages = perrors.stream().filter(error -> editorErrorMatches(error))
        .map(error -> error.getMessage()).collect(Collectors.toSet());
    if (messages.isEmpty()) {
      if (FeatureCheck.supportCustomValidity(element)) {
        element.setCustomValidity(StringUtils.EMPTY);
      }
      if (validationMessageElement == null) {
        element.setTitle(StringUtils.EMPTY);
      } else {
        validationMessageElement.getElement().removeAllChildren();
      }
    } else {
      final String messagesAsString = ErrorMessageFormater.messagesToString(messages);
      if (FeatureCheck.supportCustomValidity(element)) {
        element.setCustomValidity(messagesAsString);
      }
      if (validationMessageElement == null) {
        element.setTitle(messagesAsString);
      } else {
        validationMessageElement.getElement()
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
        && (equals(perror.getEditor()) || perror.getEditor().equals(asEditor()));
  }

  public elemental.html.TextAreaElement getInputElement() {
    return getElement().cast();
  }

  @Override
  public String getValidationMessage() {
    return getInputElement().getValidationMessage();
  }

  @Override
  public ValidityState getValidity() {
    return getInputElement().getValidity();
  }

  @Override
  public boolean checkValidity() {
    return getInputElement().checkValidity();
  }

  @Override
  public boolean isRequired() {
    return getInputElement().isRequired();
  }

  @Override
  public void setRequired(final boolean arg) {
    getInputElement().setRequired(arg);
  }

  @Override
  public String getPlaceholder() {
    return getInputElement().getPlaceholder();
  }

  @Override
  public void setPlaceholder(final String arg) {
    getInputElement().setPlaceholder(arg);
  }

  @Override
  public boolean isAutofocus() {
    return getInputElement().isAutofocus();
  }

  @Override
  public void setAutofocus(final boolean arg) {
    getInputElement().setAutofocus(arg);
  }

  @Override
  public void setValidationMessageElement(final HTMLPanel pelement) {
    validationMessageElement = pelement;
  }

  @Override
  public HTMLPanel getValidationMessageElement() {
    return validationMessageElement;
  }

  /**
   * Gets the maximum allowable length of the text box.
   *
   * @return the maximum length, in characters
   */
  public int getMaxLength() {
    return getInputElement().getMaxLength();
  }

  /**
   * Sets the maximum allowable length of the text box.
   *
   * @param length the maximum length, in characters
   */
  public void setMaxLength(final int length) {
    getInputElement().setMaxLength(length);
  }
}
