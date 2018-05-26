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

import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasBlurHandlers;
import com.google.gwt.event.dom.client.HasFocusHandlers;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasAnimation;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestBox.DefaultSuggestionDisplay;
import com.google.gwt.user.client.ui.SuggestBox.SuggestionDisplay;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.google.gwt.user.client.ui.ValueBoxBase;

import elemental.html.ValidityState;

import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SuggestBoxWithEditorErrors extends Composite
    implements HasFocusHandlers, HasBlurHandlers, HasEditorErrors<String>,
    IsEditor<LeafValueEditor<String>>, HasValue<String>, HasText, Focusable, HasAllKeyHandlers,
    HasSelectionHandlers<Suggestion>, HasAnimation, HasEnabled, HasValidationMessageElement,
    HasRequired, HasValidity, HasAutofocus, HasPlaceholder {

  @Ignore
  private final SuggestBox suggestBox;

  /**
   * Constructor for {@link SuggestBox}. Creates a {@link MultiWordSuggestOracle} and
   * {@link TextBox} to use with this {@link SuggestBox}.
   */
  public SuggestBoxWithEditorErrors() {
    this(new MultiWordSuggestOracle(), new TextBox(), new DefaultSuggestionDisplay());
  }

  /**
   * Constructor for {@link SuggestBox}. Creates a {@link TextBox} to use with this
   * {@link SuggestBox}.
   *
   * @param oracle the oracle for this <code>SuggestBox</code>
   */
  public SuggestBoxWithEditorErrors(final SuggestOracle oracle) {
    this(oracle, new TextBox(), new DefaultSuggestionDisplay());
  }

  /**
   * Constructor for {@link SuggestBox}. The text box will be removed from it's current location and
   * wrapped by the {@link SuggestBox}.
   *
   * @param oracle supplies suggestions based upon the current contents of the text widget
   * @param box the text widget
   */
  public SuggestBoxWithEditorErrors(final SuggestOracle oracle,
      final ValueBoxBaseWithEditorErrors<String> box) {
    this(oracle, box, new DefaultSuggestionDisplay());
  }

  /**
   * Constructor for {@link SuggestBox}. The text box will be removed from it's current location and
   * wrapped by the {@link SuggestBox}.
   *
   * @param oracle supplies suggestions based upon the current contents of the text widget
   * @param box the text widget
   * @param suggestDisplay the class used to display suggestions
   */
  public SuggestBoxWithEditorErrors(final SuggestOracle oracle,
      final ValueBoxBaseWithEditorErrors<String> box, final SuggestionDisplay suggestDisplay) {
    super();
    this.suggestBox = new SuggestBox(oracle, box, suggestDisplay);
    this.initWidget(this.suggestBox);
    this.addSelectionHandler(event -> {
      this.suggestBox.setValue(StringUtils.EMPTY, false);
      this.suggestBox.setValue(event.getSelectedItem().getReplacementString(), true);
    });
  }

  @Override
  public void showErrors(final List<EditorError> errors) {
    final Set<String> messages = new HashSet<>();
    for (final EditorError error : errors) {
      if (this.editorErrorMatches(error)) {
        messages.add(error.getMessage());
      }
    }
    ((ValueBoxBaseWithEditorErrors<String>) this.getValueBox()).showErrors(messages);
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

  @Override
  public HandlerRegistration addBlurHandler(final BlurHandler phandler) {
    return this.getValueBox().addBlurHandler(phandler);
  }

  @Override
  public HandlerRegistration addFocusHandler(final FocusHandler phandler) {
    return this.getValueBox().addFocusHandler(phandler);
  }

  @Override
  public String getValidationMessage() {
    return ((ValueBoxBaseWithEditorErrors<String>) this.getValueBox()).getValidationMessage();
  }

  @Override
  public ValidityState getValidity() {
    return ((ValueBoxBaseWithEditorErrors<String>) this.getValueBox()).getValidity();
  }

  @Override
  public boolean checkValidity() {
    return ((ValueBoxBaseWithEditorErrors<String>) this.getValueBox()).checkValidity();
  }


  @Override
  public boolean isRequired() {
    return ((ValueBoxBaseWithEditorErrors<String>) this.getValueBox()).isRequired();
  }

  @Override
  public void setRequired(final boolean arg) {
    ((ValueBoxBaseWithEditorErrors<String>) this.getValueBox()).setRequired(arg);
  }

  @Override
  public boolean isAutofocus() {
    return ((ValueBoxBaseWithEditorErrors<String>) this.getValueBox()).isAutofocus();
  }

  @Override
  public void setAutofocus(final boolean arg) {
    ((ValueBoxBaseWithEditorErrors<String>) this.getValueBox()).setAutofocus(arg);
  }

  @Override
  public LeafValueEditor<String> asEditor() {
    return this.suggestBox.asEditor();
  }

  @Override
  public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<String> handler) {
    return this.suggestBox.addValueChangeHandler(handler);
  }

  @Override
  public String getValue() {
    return this.suggestBox.getValue();
  }

  @Override
  public void setValue(final String value) {
    this.suggestBox.setValue(value);
  }

  @Override
  public void setValue(final String value, final boolean fireEvents) {
    this.suggestBox.setValue(value, fireEvents);
  }

  @Override
  public String getText() {
    return this.suggestBox.getText();
  }

  @Override
  public void setText(final String text) {
    this.suggestBox.setText(text);
  }

  /**
   * Get the ValueBoxBase associated with this suggest box.
   *
   * @return this suggest box's value box
   */
  @Ignore
  public ValueBoxBase<String> getValueBox() {
    return this.suggestBox.getValueBox();
  }

  @Override
  public int getTabIndex() {
    return this.suggestBox.getTabIndex();
  }

  @Override
  public void setAccessKey(final char key) {
    this.suggestBox.setAccessKey(key);
  }

  @Override
  public void setFocus(final boolean focused) {
    this.suggestBox.setFocus(focused);
  }

  @Override
  public void setTabIndex(final int index) {
    this.suggestBox.setTabIndex(index);
  }

  @Override
  public HandlerRegistration addKeyUpHandler(final KeyUpHandler handler) {
    return this.getValueBox().addKeyUpHandler(handler);
  }

  @Override
  public HandlerRegistration addKeyDownHandler(final KeyDownHandler handler) {
    return this.getValueBox().addKeyDownHandler(handler);
  }

  @Override
  public HandlerRegistration addKeyPressHandler(final KeyPressHandler handler) {
    return this.getValueBox().addKeyPressHandler(handler);
  }

  @SuppressWarnings("deprecation")
  @Override
  public boolean isAnimationEnabled() {
    return this.suggestBox.isAnimationEnabled();
  }

  @SuppressWarnings("deprecation")
  @Override
  public void setAnimationEnabled(final boolean enable) {
    this.suggestBox.setAnimationEnabled(enable);
  }

  @Override
  public HandlerRegistration addSelectionHandler(final SelectionHandler<Suggestion> handler) {
    return this.suggestBox.addSelectionHandler(handler);
  }

  @Override
  public boolean isEnabled() {
    return this.suggestBox.isEnabled();
  }

  @Override
  public void setEnabled(final boolean enabled) {
    this.suggestBox.setEnabled(enabled);
  }

  @Override
  public void setValidationMessageElement(final HTMLPanel pelement) {
    ((ValueBoxBaseWithEditorErrors<String>) this.getValueBox())
        .setValidationMessageElement(pelement);
  }

  @Override
  public HTMLPanel getValidationMessageElement() {
    return ((ValueBoxBaseWithEditorErrors<String>) this.getValueBox())
        .getValidationMessageElement();
  }

  @Override
  public String getPlaceholder() {
    return ((ValueBoxBaseWithEditorErrors<String>) this.getValueBox()).getPlaceholder();
  }

  @Override
  public void setPlaceholder(final String placeholder) {
    ((ValueBoxBaseWithEditorErrors<String>) this.getValueBox()).setPlaceholder(placeholder);
  }
}
