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

import de.knightsoftnet.mtwidgets.client.ui.widget.features.HasValidationMessageElement;
import de.knightsoftnet.mtwidgets.client.ui.widget.helper.ErrorMessageFormater;
import de.knightsoftnet.mtwidgets.client.ui.widget.helper.FeatureCheck;
import de.knightsoftnet.mtwidgets.client.ui.widget.helper.IdAndNamePlusIconBean;
import de.knightsoftnet.mtwidgets.client.ui.widget.styling.SelectBoxInputStyle;
import de.knightsoftnet.validators.client.decorators.ExtendedValueBoxEditor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.ui.client.adapters.ValueBoxEditor;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasValue;

import elemental.dom.NodeList;
import elemental.html.InputElement;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * select box with icon field for star ratings.
 *
 * @author Manfred Tremmel
 *
 */
public class SelectBoxWithIconInputWidget<T extends Comparable<T>> extends Composite
    implements HasValue<T>, HasEditorErrors<T>, IsEditor<ValueBoxEditor<T>>, Focusable,
    HasValidationMessageElement {

  private boolean valueChangeHandlerInitialized;
  private final FocusPanel panel;
  private final FlowPanel options;
  private final ValueBoxEditor<T> editor;
  private final List<IdAndNamePlusIconBean<T>> entries;
  private final List<RadioButton> radioButtons;
  private final String idBase;
  private final Resources resource;
  private final ClickHandler clickHandler;
  private final ValueChangeHandler<Boolean> valueChangeHandler;

  private HTMLPanel validationMessageElement;

  /**
   * A ClientBundle that provides images and style sheets for the decorator.
   */
  public interface Resources extends ClientBundle {

    /**
     * The styles used in this widget.
     *
     * @return decorator style
     */
    @Source("styling/SelectBoxInputStyle.gss")
    SelectBoxInputStyle selectBoxInputStyle();
  }

  /**
   * the default resources.
   */
  private static volatile Resources defaultResource;

  /**
   * default constructor.
   *
   * @param idBase the id of the widget and base for the ids of the single radio buttons
   */
  @UiConstructor
  public SelectBoxWithIconInputWidget(final String idBase) {
    this(idBase, SelectBoxWithIconInputWidget.getDefaultResources());
  }

  /**
   * constructor with styling information.
   *
   * @param idBase the id of the widget and base for the ids of the single radio buttons
   * @param presource resources with styling information
   */
  public SelectBoxWithIconInputWidget(final String idBase, final Resources presource) {
    super();
    this.resource = presource;
    this.idBase = idBase;
    this.entries = new ArrayList<>();
    this.radioButtons = new ArrayList<>();
    presource.selectBoxInputStyle().ensureInjected();
    this.panel = new FocusPanel();
    this.panel.setStylePrimaryName(presource.selectBoxInputStyle().selectBox());
    this.panel.getElement().setId(idBase);
    this.options = new FlowPanel();
    this.options.setStylePrimaryName(presource.selectBoxInputStyle().options());
    this.panel.add(this.options);
    this.initWidget(this.panel);
    this.valueChangeHandler = event -> ValueChangeEvent.fire(this, this.getValue());
    this.clickHandler = event -> {
      if (StringUtils.contains(this.panel.getStyleName(),
          presource.selectBoxInputStyle().selectBoxSelected())) {
        this.panel.removeStyleName(presource.selectBoxInputStyle().selectBoxSelected());
      } else {
        this.panel.addStyleName(presource.selectBoxInputStyle().selectBoxSelected());
      }
    };
    this.panel.addClickHandler(this.clickHandler);
    this.editor = new ExtendedValueBoxEditor<>(this, null);
  }

  /**
   * get default resource, if not set, create one.
   *
   * @return default resource.
   */
  protected static Resources getDefaultResources() { // NOPMD it's thread save!
    if (SelectBoxWithIconInputWidget.defaultResource == null) {
      synchronized (Resources.class) {
        if (SelectBoxWithIconInputWidget.defaultResource == null) {
          SelectBoxWithIconInputWidget.defaultResource = GWT.create(Resources.class);
        }
      }
    }
    return SelectBoxWithIconInputWidget.defaultResource;
  }

  /**
   * fill entries for the select box.
   *
   * @param pentries list of entries
   */
  @SuppressWarnings("unchecked")
  public void fillEntries(final IdAndNamePlusIconBean<T>... pentries) {
    this.fillEntries(Arrays.asList(pentries));
  }

  /**
   * fill entries for the select box.
   *
   * @param pentries list of entries
   */
  public void fillEntries(final List<IdAndNamePlusIconBean<T>> pentries) {
    this.entries.clear();
    this.radioButtons.clear();
    this.entries.addAll(pentries);
    boolean selected = false;
    for (final IdAndNamePlusIconBean<T> entry : this.entries) {
      final FlowPanel option = new FlowPanel();
      option.setStylePrimaryName(this.resource.selectBoxInputStyle().option());
      final RadioButton radioButton = new RadioButton();
      radioButton.getElement().setId(this.idBase + entry.getId());
      radioButton.setFormValue(Objects.toString(entry.getId()));
      radioButton.setName(this.idBase);
      if (!selected) {
        radioButton.setValue(Boolean.TRUE, false);
        selected = true;
      }
      radioButton.addClickHandler(this.clickHandler);
      option.add(radioButton);
      this.radioButtons.add(radioButton);
      final SafeHtmlBuilder labelShb = new SafeHtmlBuilder();
      labelShb.appendHtmlConstant("<img src=\"" + SafeHtmlUtils.htmlEscape(entry.getIconUrl())
          + "\" alt=\"" + SafeHtmlUtils.htmlEscape(entry.getName()) + "\" />");
      labelShb.appendEscaped(entry.getName());
      final InputLabel label = new InputLabel();
      label.setFor(radioButton);
      label.setHtml(labelShb.toSafeHtml());
      option.add(label);
      this.options.add(option);
    }
  }

  @Override
  public void setValue(final T pvalue) {
    this.setValue(pvalue, false);
  }

  @Override
  public void setValue(final T pvalue, final boolean pfireEvents) {
    final T oldValue = this.getValue();
    if (pvalue == null) {
      for (final RadioButton radioButton : this.radioButtons) {
        radioButton.setValue(Boolean.FALSE, false);
      }
    } else {
      for (final RadioButton radioButton : this.radioButtons) {
        if (StringUtils.equals(pvalue.toString(), radioButton.getFormValue())) {
          radioButton.setValue(Boolean.TRUE, false);
          break;
        }
      }
    }
    if (pfireEvents) {
      ValueChangeEvent.fireIfNotEqual(this, oldValue, pvalue);
    }
  }

  @Override
  public final T getValue() {
    for (final RadioButton radioButton : this.radioButtons) {
      if (BooleanUtils.isTrue(radioButton.getValue())) {
        final String valueAsString = radioButton.getFormValue();
        for (final IdAndNamePlusIconBean<T> entry : this.entries) {
          if (StringUtils.equals(valueAsString, Objects.toString(entry.getId()))) {
            return entry.getId();
          }
        }
      }
    }
    return null;
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
    for (final RadioButton radioButton : this.radioButtons) {
      radioButton.addValueChangeHandler(this.valueChangeHandler);
    }
  }

  @Override
  public void setTabIndex(final int pindex) {
    int index = pindex;
    for (final RadioButton radioButton : this.radioButtons) {
      radioButton.setTabIndex(index++);
    }
  }

  @Override
  public int getTabIndex() {
    for (final RadioButton radioButton : this.radioButtons) {
      return radioButton.getTabIndex();
    }
    return -1;
  }

  @Override
  public void setAccessKey(final char pkey) {
    for (final RadioButton radioButton : this.radioButtons) {
      radioButton.setAccessKey(pkey);
      return;
    }
  }

  @Override
  public void setFocus(final boolean pfocused) {
    for (final RadioButton radioButton : this.radioButtons) {
      radioButton.setFocus(pfocused);
    }
  }

  @Override
  public ValueBoxEditor<T> asEditor() {
    return this.editor;
  }

  @Override
  public void showErrors(final List<EditorError> perrors) {
    final elemental.dom.Element headElement = this.getElement().cast();
    final NodeList inputElements = headElement.getElementsByTagName("input");
    final Set<String> messages = new HashSet<>();
    for (final EditorError error : perrors) {
      if (this.editorErrorMatches(error)) {
        messages.add(error.getMessage());
      }
    }
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
        && (this.equals(perror.getEditor()) || perror.getEditor().equals(this.asEditor()));
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
