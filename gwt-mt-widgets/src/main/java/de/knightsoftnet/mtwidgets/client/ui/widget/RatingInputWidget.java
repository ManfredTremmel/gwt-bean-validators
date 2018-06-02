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
import de.knightsoftnet.mtwidgets.client.ui.widget.styling.RatingInputStyle;
import de.knightsoftnet.validators.client.decorators.ExtendedValueBoxEditor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.ui.client.adapters.ValueBoxEditor;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasValue;

import elemental.dom.NodeList;
import elemental.html.InputElement;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * input field for star ratings.
 *
 * @author Manfred Tremmel
 *
 */
public class RatingInputWidget extends Composite
    implements HasValue<Integer>, HasEditorErrors<Integer>, IsEditor<ValueBoxEditor<Integer>>,
    Focusable, HasValidationMessageElement {

  private boolean valueChangeHandlerInitialized;
  private final FlowPanel panel;
  private final ValueBoxEditor<Integer> editor;

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
    @Source("styling/RatingInputStyle.gss")
    RatingInputStyle ratingInputStyle();
  }

  /**
   * the default resources.
   */
  private static volatile Resources defaultResource;

  /**
   * default constructor.
   *
   * @param max the number stars to display
   * @param idBase the id of the widget and base for the ids of the single radio buttons
   */
  @UiConstructor
  public RatingInputWidget(final int max, final String idBase) {
    this(max, idBase, RatingInputWidget.getDefaultResources());
  }

  /**
   * constructor with styling information.
   *
   * @param max the number stars to display
   * @param idBase the id of the widget and base for the ids of the single radio buttons
   * @param presource resources with styling information
   */
  public RatingInputWidget(final int max, final String idBase, final Resources presource) {
    super();
    presource.ratingInputStyle().ensureInjected();
    panel = new FlowPanel();
    panel.setStylePrimaryName(presource.ratingInputStyle().ratingArea());
    panel.getElement().setId(idBase);
    initWidget(panel);
    editor = new ExtendedValueBoxEditor<>(this, null);
    for (int i = max; i > 0; i--) {
      final RadioButton radioButton = new RadioButton();
      radioButton.getElement().setId(idBase + Integer.toString(i));
      radioButton.setFormValue(Integer.toString(i));
      radioButton.setName(idBase);
      panel.add(radioButton);
      final InputLabel label = new InputLabel();
      label.setFor(radioButton);
      label.setText(StringUtils.SPACE);
      panel.add(label);
    }
  }

  /**
   * get default resource, if not set, create one.
   *
   * @return default resource.
   */
  protected static Resources getDefaultResources() { // NOPMD it's thread save!
    if (RatingInputWidget.defaultResource == null) {
      synchronized (Resources.class) {
        if (RatingInputWidget.defaultResource == null) {
          RatingInputWidget.defaultResource = GWT.create(Resources.class);
        }
      }
    }
    return RatingInputWidget.defaultResource;
  }

  @Override
  public void setValue(final Integer pvalue) {
    this.setValue(pvalue, false);
  }

  @Override
  public void setValue(final Integer pvalue, final boolean pfireEvents) {
    final Integer oldValue = getValue();
    if (pvalue == null) {
      for (int i = 0; i < panel.getWidgetCount(); i++) {
        if (panel.getWidget(i) instanceof RadioButton) {
          ((RadioButton) panel.getWidget(i)).setValue(Boolean.FALSE, false);
        }
      }
    } else {
      for (int i = 0; i < panel.getWidgetCount(); i++) {
        if (panel.getWidget(i) instanceof RadioButton && StringUtils.equals(pvalue.toString(),
            ((RadioButton) panel.getWidget(i)).getFormValue())) {
          ((RadioButton) panel.getWidget(i)).setValue(Boolean.TRUE, false);
          break;
        }
      }
    }
    if (pfireEvents) {
      ValueChangeEvent.fireIfNotEqual(this, oldValue, pvalue);
    }
  }

  @Override
  public Integer getValue() {
    for (int i = 0; i < panel.getWidgetCount(); i++) {
      if (panel.getWidget(i) instanceof RadioButton) {
        final RadioButton radioButton = (RadioButton) panel.getWidget(i);
        if (BooleanUtils.isTrue(radioButton.getValue())) {
          return Integer.valueOf(radioButton.getFormValue());
        }
      }
    }
    return null;
  }

  @Override
  public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<Integer> phandler) {
    // Is this the first value change handler? If so, time to add handlers
    if (!valueChangeHandlerInitialized) {
      ensureDomEventHandlers();
      valueChangeHandlerInitialized = true;
    }
    return this.addHandler(phandler, ValueChangeEvent.getType());
  }

  protected void ensureDomEventHandlers() {
    for (int i = 0; i < panel.getWidgetCount(); i++) {
      if (panel.getWidget(i) instanceof RadioButton) {
        ((RadioButton) panel.getWidget(i))
            .addValueChangeHandler(event -> ValueChangeEvent.fire(this, getValue()));
      }
    }
  }

  @Override
  public void setTabIndex(final int pindex) {
    int index = pindex;
    for (int i = 0; i < panel.getWidgetCount(); i++) {
      if (panel.getWidget(i) instanceof RadioButton) {
        ((RadioButton) panel.getWidget(i)).setTabIndex(index++);
      }
    }
  }

  @Override
  public int getTabIndex() {
    for (int i = 0; i < panel.getWidgetCount(); i++) {
      if (panel.getWidget(i) instanceof RadioButton) {
        return ((RadioButton) panel.getWidget(i)).getTabIndex();
      }
    }
    return -1;
  }

  @Override
  public void setAccessKey(final char pkey) {
    for (int i = 0; i < panel.getWidgetCount(); i++) {
      if (panel.getWidget(i) instanceof RadioButton) {
        ((RadioButton) panel.getWidget(i)).setAccessKey(pkey);
        return;
      }
    }
  }

  @Override
  public void setFocus(final boolean pfocused) {
    for (int i = 0; i < panel.getWidgetCount(); i++) {
      if (panel.getWidget(i) instanceof RadioButton) {
        ((RadioButton) panel.getWidget(i)).setFocus(pfocused);
        return;
      }
    }
  }

  @Override
  public ValueBoxEditor<Integer> asEditor() {
    return editor;
  }

  @Override
  public void showErrors(final List<EditorError> perrors) {
    final elemental.dom.Element headElement = getElement().cast();
    final NodeList inputElements = headElement.getElementsByTagName("input");
    final Set<String> messages = perrors.stream().filter(error -> editorErrorMatches(error))
        .map(error -> error.getMessage()).collect(Collectors.toSet());
    if (messages.isEmpty()) {
      for (int i = 0; i < inputElements.length(); i++) {
        final InputElement input = (InputElement) inputElements.at(i);
        if (FeatureCheck.supportCustomValidity(input)) {
          input.setCustomValidity(StringUtils.EMPTY);
        }
        if (validationMessageElement == null) {
          input.setTitle(StringUtils.EMPTY);
        }
      }
      if (validationMessageElement != null) {
        validationMessageElement.getElement().removeAllChildren();
      }
    } else {
      final String messagesAsString = ErrorMessageFormater.messagesToString(messages);
      for (int i = 0; i < inputElements.length(); i++) {
        final InputElement input = (InputElement) inputElements.at(i);
        if (FeatureCheck.supportCustomValidity(input)) {
          input.setCustomValidity(messagesAsString);
        }
        if (validationMessageElement == null) {
          input.setTitle(messagesAsString);
        }
      }
      if (validationMessageElement != null) {
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

  @Override
  public void setValidationMessageElement(final HTMLPanel pelement) {
    validationMessageElement = pelement;
  }

  @Override
  public HTMLPanel getValidationMessageElement() {
    return validationMessageElement;
  }
}
