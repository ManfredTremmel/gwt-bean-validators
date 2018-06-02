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

package de.knightsoftnet.validators.client.decorators;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.ui.client.adapters.ValueBoxEditor;
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This abstract class combines some methods which are used by decorators.
 *
 * @param <T> the type of data being edited
 */
public abstract class AbstractDecorator<T> extends Composite
    implements HasEditorErrors<T>, IsEditor<ValueBoxEditor<T>>, HasValue<T>,
    HasValueChangeHandlers<T>, HasKeyUpHandlers, HasKeyPressHandlers, Focusable, HasEnabled {

  /**
   * A ClientBundle that provides images and decoratorStyle sheets for the decorator.
   */
  public interface Resources extends ClientBundle {

    /**
     * The styles used in this widget.
     *
     * @return decorator style
     */
    @Source("EditorDecorator.gss")
    DecoratorStyle decoratorStyle();
  }

  /**
   * the default resources.
   */
  private static volatile Resources defaultResource;

  /**
   * content panel.
   */
  protected final SimplePanel contents = new SimplePanel();

  /**
   * value box resource with css.
   */
  protected final DecoratorStyle decoratorStyle;

  /**
   * the widget.
   */
  protected Widget widget;

  /**
   * label to display error message.
   */
  @Ignore
  private final HTML errorLabel = new HTML();

  /**
   * editor to handle input and errors.
   */
  private ExtendedValueBoxEditor<T> editor;

  /**
   * validation state of the entry.
   */
  private boolean focusOnError;

  /**
   * panel which surrounds the widget.
   */
  private final FlowPanel layout;

  /**
   * Constructs a Decorator.
   *
   * @param errorLocation location of the error text
   */
  public AbstractDecorator(final PanelLocationEnum errorLocation) {
    this(errorLocation, getDefaultResources());
  }

  /**
   * Constructs a Decorator using a {@link Widget} widget with error location and style sheet.
   *
   * @param pwidget the widget
   * @param errorLocation location of the error text
   * @param decoratorResource resource with css information
   */
  public AbstractDecorator(final Widget pwidget, final PanelLocationEnum errorLocation,
      final Resources decoratorResource) {
    this(errorLocation, decoratorResource);
    widget = pwidget;
    contents.add(pwidget);
  }

  /**
   * Constructs a Decorator using a {@link Widget} widget with error location.
   *
   * @param pwidget the widget
   * @param errorLocation location of the error text
   */
  public AbstractDecorator(final Widget pwidget, final PanelLocationEnum errorLocation) {
    this(pwidget, errorLocation, getDefaultResources());
  }

  /**
   * Constructs a Decorator using a {@link Widget} widget with error location.
   *
   * @param pwidget the widget
   */
  public AbstractDecorator(final Widget pwidget) {
    this(pwidget, PanelLocationEnum.RIGHT, getDefaultResources());
  }

  /**
   * Constructs a Decorator.
   *
   * @param errorLocation location of the error text
   * @param resource resource with css information
   */
  public AbstractDecorator(final PanelLocationEnum errorLocation, final Resources resource) {
    super();
    // Inject the stylesheet.
    decoratorStyle = resource.decoratorStyle();
    decoratorStyle.ensureInjected();

    layout = createWidgetPanel(errorLocation);
    initWidget(layout);
  }

  private FlowPanel createWidgetPanel(final PanelLocationEnum perrorLocation) {
    final boolean contentFirst =
        perrorLocation == PanelLocationEnum.LEFT && LocaleInfo.getCurrentLocale().isRTL()
            || perrorLocation == PanelLocationEnum.RIGHT && !LocaleInfo.getCurrentLocale().isRTL()
            || perrorLocation == PanelLocationEnum.BOTTOM;
    final FlowPanel layout = new FlowPanel();
    if (perrorLocation == PanelLocationEnum.TOP) {
      layout.add(errorLabel);
      layout.add(contents);
    } else {
      layout.add(contents);
      layout.add(errorLabel);
    }
    switch (perrorLocation) {
      case TOP:
        errorLabel.setStylePrimaryName(decoratorStyle.errorLabelStyleTop());
        contents.setStylePrimaryName(decoratorStyle.contentContainerStyleTop());
        break;
      case BOTTOM:
        errorLabel.setStylePrimaryName(decoratorStyle.errorLabelStyleBottom());
        contents.setStylePrimaryName(decoratorStyle.contentContainerStyleBottom());
        break;
      default:
        if (contentFirst) {
          errorLabel.setStylePrimaryName(decoratorStyle.errorLabelStyleRight());
          contents.setStylePrimaryName(decoratorStyle.contentContainerStyleRight());
        } else {
          errorLabel.setStylePrimaryName(decoratorStyle.errorLabelStyleLeft());
          contents.setStylePrimaryName(decoratorStyle.contentContainerStyleLeft());
        }
        break;
    }
    errorLabel.getElement().getStyle().setDisplay(Display.NONE);
    focusOnError = true;
    return layout;
  }

  public FlowPanel getLayout() {
    return layout;
  }

  /**
   * get default resource, if not set, create one.
   *
   * @return default resource.
   */
  protected static Resources getDefaultResources() {
    if (defaultResource == null) { // NOPMD it's thread save!
      synchronized (Resources.class) {
        if (defaultResource == null) {
          defaultResource = GWT.create(Resources.class);
        }
      }
    }
    return defaultResource;
  }

  /**
   * Set the widget that the EditorPanel will display. This method will automatically call
   * {@link #setEditor}.
   *
   * @param pwidget a {@link IsEditor} widget
   */
  @UiChild(limit = 1, tagname = "widget")
  public void setChildWidget(final TakesValue<T> pwidget) {
    widget = (Widget) pwidget;
    contents.add(widget);
    setEditor(new ExtendedValueBoxEditor<>(pwidget, this));
  }

  @SuppressWarnings("unchecked")
  @Override
  public final HandlerRegistration addValueChangeHandler(final ValueChangeHandler<T> phandler) {
    if (contents.getWidget() instanceof HasValueChangeHandlers<?>) {
      return ((HasValueChangeHandlers<T>) contents.getWidget()).addValueChangeHandler(phandler);
    }
    return null;
  }

  /**
   * add a change handler to clear error state on value change.
   *
   * @param pwidget widget to set the handler to
   */
  protected void addValueChangeHandler(final HasValueChangeHandlers<T> pwidget) {
    pwidget.addValueChangeHandler(event -> clearErrors());
  }

  /**
   * clear errors.
   */
  public void clearErrors() {
    errorLabel.setText(StringUtils.EMPTY);
    errorLabel.getElement().getStyle().setDisplay(Display.NONE);
    if (contents.getWidget() != null) {
      contents.getWidget().removeStyleName(decoratorStyle.errorInputStyle());
      contents.getWidget().removeStyleName(decoratorStyle.validInputStyle());
    }
  }

  /**
   * The default implementation will display, but not consume, received errors whose
   * {@link EditorError#getEditor() getEditor()} method returns the Editor passed into
   * {@link #setEditor}.
   *
   * @param errors a List of {@link EditorError} instances
   */
  @Override
  public void showErrors(final List<EditorError> errors) {
    final Set<String> messages = errors.stream().filter(error -> editorErrorMatches(error))
        .map(error -> error.getMessage()).distinct().collect(Collectors.toSet());

    if (messages.isEmpty()) {
      errorLabel.setText(StringUtils.EMPTY);
      errorLabel.getElement().getStyle().setDisplay(Display.NONE);
      if (contents.getWidget() != null) {
        contents.getWidget().removeStyleName(decoratorStyle.errorInputStyle());
        contents.getWidget().addStyleName(decoratorStyle.validInputStyle());
      }
    } else {
      if (contents.getWidget() != null) {
        contents.getWidget().removeStyleName(decoratorStyle.validInputStyle());
        contents.getWidget().addStyleName(decoratorStyle.errorInputStyle());
        if (focusOnError) {
          setFocus(true);
        }
      }
      final SafeHtmlBuilder sb = new SafeHtmlBuilder();
      messages.forEach(message -> {
        sb.appendEscaped(message);
        sb.appendHtmlConstant("<br />");
      });
      errorLabel.setHTML(sb.toSafeHtml());
      errorLabel.getElement().getStyle().setDisplay(Display.TABLE);
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
        && (equals(perror.getEditor()) || perror.getEditor().equals(editor));
  }

  @SuppressWarnings("unchecked")
  @Override
  public final T getValue() {
    if (contents.getWidget() instanceof TakesValue<?>) {
      return ((TakesValue<T>) contents.getWidget()).getValue();
    }
    return null;
  }

  @Override
  public final void setValue(final T pvalue) {
    setValue(pvalue, false);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void setValue(final T pvalue, final boolean pfireEvents) {
    if (contents.getWidget() instanceof TakesValue<?>) {
      clearErrors();
      if (contents.getWidget() instanceof HasValue<?>) {
        ((HasValue<T>) contents.getWidget()).setValue(pvalue, pfireEvents);
      } else {
        ((TakesValue<T>) contents.getWidget()).setValue(pvalue);
      }
    }
  }

  @Override
  public final void setTabIndex(final int pindex) {
    if (contents.getWidget() instanceof Focusable) {
      ((Focusable) contents.getWidget()).setTabIndex(pindex);
    }
  }

  @Override
  public final int getTabIndex() {
    if (contents.getWidget() instanceof Focusable) {
      return ((Focusable) contents.getWidget()).getTabIndex();
    }
    return 0;
  }

  @Override
  public final void setAccessKey(final char pkey) {
    if (contents.getWidget() instanceof Focusable) {
      ((Focusable) contents.getWidget()).setAccessKey(pkey);
    }
  }

  @Override
  public final void setFocus(final boolean pfocused) {
    if (contents.getWidget() instanceof Focusable) {
      ((Focusable) contents.getWidget()).setFocus(pfocused);
    }
  }

  @Override
  public boolean isEnabled() {
    if (contents.getWidget() instanceof HasEnabled) {
      ((HasEnabled) contents.getWidget()).isEnabled();
    }
    return false;
  }

  @Override
  public void setEnabled(final boolean penabled) {
    if (contents.getWidget() instanceof HasEnabled) {
      ((HasEnabled) contents.getWidget()).setEnabled(penabled);
    }
  }

  /**
   * get the widget.
   *
   * @return the widget
   */
  @Override
  public final Widget getWidget() {
    return widget;
  }

  /**
   * Returns the associated {@link ValueBoxEditor}.
   *
   * @return a {@link ValueBoxEditor} instance
   * @see #setEditor(ExtendedValueBoxEditor)
   */
  @Override
  public ValueBoxEditor<T> asEditor() {
    return editor;
  }

  /**
   * Sets the associated {@link ValueBoxEditor}.
   *
   * @param peditor a {@link ValueBoxEditor} instance
   * @see #asEditor()
   */
  public final void setEditor(final ExtendedValueBoxEditor<T> peditor) {
    editor = peditor;
  }

  /**
   * check if focus on error is active.
   *
   * @return true if widget should get focus on error
   */
  public final boolean isFocusOnError() {
    return focusOnError;
  }

  /**
   * set focus on error flag, if it's true, the widget get's the focus if validation finds an error.
   *
   * @param pfocusOnError the focusOnError to set
   */
  public final void setFocusOnError(final boolean pfocusOnError) {
    focusOnError = pfocusOnError;
  }

  @Override
  public final HandlerRegistration addKeyPressHandler(final KeyPressHandler phandler) {
    if (contents.getWidget() instanceof HasKeyPressHandlers) {
      return ((HasKeyPressHandlers) contents.getWidget()).addKeyPressHandler(phandler);
    }
    return null;
  }

  @Override
  public HandlerRegistration addKeyUpHandler(final KeyUpHandler pkeyUpHandler) {
    if (contents.getWidget() instanceof HasKeyUpHandlers) {
      return ((HasKeyUpHandlers) contents.getWidget()).addKeyUpHandler(pkeyUpHandler);
    }
    return null;
  }
}
