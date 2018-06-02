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
import de.knightsoftnet.mtwidgets.client.ui.widget.helper.IdAndNameIdComperator;
import de.knightsoftnet.mtwidgets.client.ui.widget.helper.IdAndNameNameComperator;
import de.knightsoftnet.mtwidgets.client.ui.widget.helper.ListSortEnum;
import de.knightsoftnet.mtwidgets.client.ui.widget.helper.MessagesForValues;
import de.knightsoftnet.validators.client.decorators.ExtendedValueBoxEditor;

import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.ui.client.adapters.ValueBoxEditor;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.impl.FocusImpl;

import elemental.dom.NodeList;
import elemental.html.InputElement;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * a radio box with id and name which is sortable and returns id.
 *
 * @author Manfred Tremmel
 *
 * @param <T> type of the id
 */
public class SortableIdAndNameRadioButton<T extends Comparable<T>> extends Composite
    implements HasValue<T>, HasEditorErrors<T>, IsEditor<ValueBoxEditor<T>>, Focusable,
    HasValidationMessageElement, HasAutofocus, HasEnabled {
  private boolean valueChangeHandlerInitialized;
  private final String widgetId;
  private final ListSortEnum sortOrder;
  private final MessagesForValues<T> messages;
  private final List<IdAndNameBean<T>> entries;
  private final FlowPanel flowPanel;
  private final Map<T, RadioButton> idToButtonMap;
  private final ValueBoxEditor<T> editor;

  private static final FocusImpl IMPL = FocusImpl.getFocusImplForWidget();

  private HTMLPanel validationMessageElement;
  private boolean enabled;

  /**
   * widget ui constructor.
   *
   * @param pwidgetId widget id which is the same for all created radio buttons
   * @param psort the sort order of the countries
   * @param pmessages message resolver
   * @param pids ids to add to listBox
   */
  @SafeVarargs
  public SortableIdAndNameRadioButton(final String pwidgetId, final ListSortEnum psort,
      final MessagesForValues<T> pmessages, final T... pids) {
    this(pwidgetId, psort, pmessages, Arrays.asList(pids));
  }

  /**
   * widget ui constructor.
   *
   * @param pwidgetId widget id which is the same for all created radio buttons
   * @param psort the sort order of the countries
   * @param pmessages message resolver
   * @param pids ids to add to listBox
   */
  public SortableIdAndNameRadioButton(final String pwidgetId, final ListSortEnum psort,
      final MessagesForValues<T> pmessages, final Collection<T> pids) {
    super();
    this.widgetId = pwidgetId;
    this.sortOrder = psort;
    this.messages = pmessages;
    this.entries = new ArrayList<>(pids.size());
    this.flowPanel = new FlowPanel();
    this.idToButtonMap = new HashMap<>();
    this.enabled = true;

    this.fillEntries(pids);

    initWidget(this.flowPanel);
    this.editor = new ExtendedValueBoxEditor<>(this, null);
  }

  /**
   * fill entries of the radio buttons.
   *
   * @param pids list of entries
   */
  private void fillEntries(final Collection<T> pids) {
    this.entries.clear();
    final Stream<IdAndNameBean<T>> stream =
        pids.stream().map(proEnum -> new IdAndNameBean<>(proEnum, this.messages.name(proEnum)));
    final Stream<IdAndNameBean<T>> sortedStream;

    if (this.sortOrder == null) {
      sortedStream = stream;
    } else {
      switch (this.sortOrder == null ? null : this.sortOrder) {
        case ID_ASC:
          sortedStream = stream.sorted(new IdAndNameIdComperator<T>());
          break;
        case ID_DSC:
          sortedStream = stream.sorted(Collections.reverseOrder(new IdAndNameIdComperator<T>()));
          break;
        case NAME_ASC:
          sortedStream = stream.sorted(new IdAndNameNameComperator<T>());
          break;
        case NAME_DSC:
          sortedStream = stream.sorted(Collections.reverseOrder(new IdAndNameNameComperator<T>()));
          break;
        default:
          sortedStream = stream;
          break;
      }
    }
    this.entries.addAll(sortedStream.collect(Collectors.toList()));

    this.flowPanel.clear();

    this.entries.forEach(entry -> {
      final RadioButton radioButton = new RadioButton(this.widgetId, entry.getName());
      radioButton.setFormValue(Objects.toString(entry.getId()));
      radioButton.setEnabled(this.enabled);
      this.flowPanel.add(radioButton);
      this.idToButtonMap.put(entry.getId(), radioButton);
    });
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
    this.idToButtonMap.values().forEach(radioButton -> radioButton
        .addValueChangeHandler(event -> ValueChangeEvent.fire(this, this.getValue())));
  }

  @Override
  public T getValue() {
    return this.idToButtonMap.entrySet().stream()
        .filter(entry -> BooleanUtils.isTrue(entry.getValue().getValue()))
        .map(entry -> entry.getKey()).findFirst().orElse(null);
  }

  @Override
  public void setValue(final T pvalue) {
    this.setValue(pvalue, false);
  }

  @Override
  public void setValue(final T pvalue, final boolean pfireEvents) {
    final T oldValue = this.getValue();
    final RadioButton radioButton = this.idToButtonMap.get(pvalue);
    if (radioButton == null) {
      this.idToButtonMap.values().forEach(entry -> entry.setValue(Boolean.FALSE));
    } else {
      this.idToButtonMap.get(pvalue).setValue(Boolean.TRUE);
    }
    if (pfireEvents) {
      ValueChangeEvent.fireIfNotEqual(this, oldValue, pvalue);
    }
  }

  @Override
  public int getTabIndex() {
    if (this.flowPanel.getWidgetCount() > 0 && this.flowPanel.getWidget(0) instanceof Focusable) {
      return ((Focusable) this.flowPanel.getWidget(0)).getTabIndex();
    }
    return -1;
  }

  @Override
  public void setAccessKey(final char pkey) {
    if (this.flowPanel.getWidgetCount() > 0 && this.flowPanel.getWidget(0) instanceof Focusable) {
      ((Focusable) this.flowPanel.getWidget(0)).setAccessKey(pkey);
    }
  }

  @Override
  public void setFocus(final boolean pfocused) {
    if (this.flowPanel.getWidgetCount() > 0 && this.flowPanel.getWidget(0) instanceof Focusable) {
      ((Focusable) this.flowPanel.getWidget(0)).setFocus(pfocused);
    }
  }

  @Override
  public void setTabIndex(final int pindex) {
    for (int i = 0; i < this.flowPanel.getWidgetCount(); i++) {
      SortableIdAndNameRadioButton.IMPL.setTabIndex(this.flowPanel.getWidget(i).getElement(),
          pindex + i);
    }
  }

  @Override
  public void showErrors(final List<EditorError> perrors) {
    final elemental.dom.Element headElement = getElement().cast();
    final NodeList inputElements = headElement.getElementsByTagName("input");
    final Set<String> messages = perrors.stream().filter(error -> this.editorErrorMatches(error))
        .map(error -> error.getMessage()).collect(Collectors.toSet());
    if (messages.isEmpty()) {
      for (int i = 0; i < inputElements.length(); i++) {
        final InputElement input = (InputElement) inputElements.at(i);
        if (FeatureCheck.supportCustomValidity(input)) {
          input.setCustomValidity(StringUtils.EMPTY);
        }
        if (this.validationMessageElement == null) {
          input.setTitle(StringUtils.EMPTY);
        }
      }
      if (this.validationMessageElement != null) {
        this.validationMessageElement.getElement().removeAllChildren();
      }
    } else {
      final String messagesAsString = ErrorMessageFormater.messagesToString(messages);
      for (int i = 0; i < inputElements.length(); i++) {
        final InputElement input = (InputElement) inputElements.at(i);
        if (FeatureCheck.supportCustomValidity(input)) {
          input.setCustomValidity(messagesAsString);
        }
        if (this.validationMessageElement == null) {
          input.setTitle(messagesAsString);
        }
      }
      if (this.validationMessageElement != null) {
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
    final elemental.dom.Element headElement = getElement().cast();
    final NodeList inputElements = headElement.getElementsByTagName("input");
    final InputElement input = (InputElement) inputElements.at(0);
    return input.isAutofocus();
  }

  @Override
  public void setAutofocus(final boolean arg) {
    final elemental.dom.Element headElement = getElement().cast();
    final NodeList inputElements = headElement.getElementsByTagName("input");
    final InputElement input = (InputElement) inputElements.at(0);
    input.setAutofocus(arg);
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
  public boolean isEnabled() {
    return this.enabled;
  }

  @Override
  public void setEnabled(final boolean penabled) {
    this.enabled = penabled;
    this.idToButtonMap.values().forEach(entry -> entry.setEnabled(penabled));
  }
}
