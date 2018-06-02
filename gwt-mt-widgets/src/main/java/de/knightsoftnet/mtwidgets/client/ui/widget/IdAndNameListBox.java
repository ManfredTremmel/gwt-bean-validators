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
import de.knightsoftnet.mtwidgets.client.ui.widget.features.HasValidationMessageElement;
import de.knightsoftnet.mtwidgets.client.ui.widget.helper.ErrorMessageFormater;
import de.knightsoftnet.mtwidgets.client.ui.widget.helper.FeatureCheck;
import de.knightsoftnet.mtwidgets.client.ui.widget.helper.IdAndNameBean;
import de.knightsoftnet.validators.client.decorators.ExtendedValueBoxEditor;

import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.ui.client.adapters.ValueBoxEditor;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.ListBox;

import elemental.html.SelectElement;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * a list box with id and name.
 *
 * @author Manfred Tremmel
 *
 */
public class IdAndNameListBox<T extends Comparable<T>> extends ListBox implements HasValue<T>,
    HasEditorErrors<T>, IsEditor<ValueBoxEditor<T>>, HasValidationMessageElement, HasAutofocus {
  private boolean valueChangeHandlerInitialized;
  protected final List<IdAndNameBean<T>> entries;
  private final ValueBoxEditor<T> editor;

  private HTMLPanel validationMessageElement;

  /**
   * constructor.
   */
  public IdAndNameListBox() {
    this(Collections.emptyList());
  }

  /**
   * constructor.
   *
   * @param pentries collection of id and name bean entries
   */
  public IdAndNameListBox(final Collection<? extends IdAndNameBean<T>> pentries) {
    super();
    this.entries = new ArrayList<>();
    this.editor = new ExtendedValueBoxEditor<>(this, null);
    this.fillEntryCollections(pentries);
  }

  /**
   * fill entries of the listbox.
   *
   * @param pentries list of entries
   */
  public final void fillEntryCollections(final Collection<? extends IdAndNameBean<T>> pentries) {
    this.entries.clear();
    this.entries.addAll(pentries);

    clear();
    for (final IdAndNameBean<T> entry : this.entries) {
      this.addItem(entry.getName(), Objects.toString(entry.getId()));
    }
  }

  @Override
  public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<T> phandler) {
    // Is this the first value change handler? If so, time to add handlers
    if (!this.valueChangeHandlerInitialized) {
      this.ensureDomEventHandlers();
      this.valueChangeHandlerInitialized = true;
    }
    return this.addHandler(phandler, ValueChangeEvent.getType());
  }

  protected void ensureDomEventHandlers() {
    addChangeHandler(pevent -> ValueChangeEvent.fire(this, this.getValue()));
  }

  @Override
  public T getValue() {
    if (getSelectedIndex() >= 0 && getSelectedIndex() < this.entries.size()) {
      return this.entries.get(getSelectedIndex()).getId();
    }
    return null;
  }

  @Override
  public void setValue(final T pvalue) {
    this.setValue(pvalue, false);
  }

  @Override
  public void setValue(final T pvalue, final boolean pfireEvents) {
    int pos = 0;
    final T oldValue = this.getValue();
    for (final IdAndNameBean<T> entry : this.entries) {
      if (Objects.equals(pvalue, entry.getId())) {
        setSelectedIndex(pos);
        if (pfireEvents) {
          ValueChangeEvent.fireIfNotEqual(this, oldValue, pvalue);
        }
        return;
      }
      pos++;
    }
    setSelectedIndex(-1);
    if (pfireEvents) {
      ValueChangeEvent.fireIfNotEqual(this, oldValue, null);
    }
  }

  @Override
  public void showErrors(final List<EditorError> perrors) {
    final SelectElement selectElement = getElement().cast();
    final Set<String> messages = perrors.stream().filter(error -> this.editorErrorMatches(error))
        .map(error -> error.getMessage()).collect(Collectors.toSet());
    if (messages.isEmpty()) {
      if (FeatureCheck.supportCustomValidity(selectElement)) {
        selectElement.setCustomValidity(StringUtils.EMPTY);
      }
      if (this.validationMessageElement == null) {
        selectElement.setTitle(StringUtils.EMPTY);
      } else {
        this.validationMessageElement.getElement().removeAllChildren();
      }
    } else {
      final String messagesAsString = ErrorMessageFormater.messagesToString(messages);
      if (FeatureCheck.supportCustomValidity(selectElement)) {
        selectElement.setCustomValidity(messagesAsString);
      }
      if (this.validationMessageElement == null) {
        selectElement.setTitle(messagesAsString);
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
        && (equals(perror.getEditor()) || perror.getEditor().equals(this.asEditor()));
  }

  @Override
  public ValueBoxEditor<T> asEditor() {
    return this.editor;
  }

  @Override
  public boolean isAutofocus() {
    final SelectElement selectElement = getElement().cast();
    return selectElement.isAutofocus();
  }

  @Override
  public void setAutofocus(final boolean arg) {
    final SelectElement selectElement = getElement().cast();
    selectElement.setAutofocus(arg);
  }

  @Override
  public void setValidationMessageElement(final HTMLPanel pelement) {
    this.validationMessageElement = pelement;
  }

  @Override
  public HTMLPanel getValidationMessageElement() {
    return this.validationMessageElement;
  }
}
