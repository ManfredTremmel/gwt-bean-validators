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
import com.google.gwt.event.logical.shared.ValueChangeEvent;
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
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This abstract class combines some methods which are used by decorators.
 *
 * @param <T> the type of data being edited
 */
public abstract class AbstractDecorator<T> extends Composite
    implements HasEditorErrors<T>, IsEditor<ValueBoxEditor<T>>, HasValue<T>,
    HasValueChangeHandlers<T>, HasKeyUpHandlers, HasKeyPressHandlers, Focusable {

  /**
   * A ClientBundle that provides images and decoratorStyle sheets for the decorator.
   */
  public interface Resources extends ClientBundle {

    /**
     * The styles used in this widget.
     *
     * @return decorator style
     */
    @Source("EditorDecorator.css")
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
  private final DecoratorStyle decoratorStyle;

  /**
   * the widget.
   */
  private Widget widget;

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
   * Constructs a Decorator.
   *
   * @param errorLocation location of the error text
   * @param resource resource with css information
   */
  public AbstractDecorator(final ErrorPanelLocationEnum errorLocation, final Resources resource) {
    super();
    // Inject the stylesheet.
    this.decoratorStyle = resource.decoratorStyle();
    this.decoratorStyle.ensureInjected();

    final boolean contentFirst =
        errorLocation == ErrorPanelLocationEnum.LEFT && LocaleInfo.getCurrentLocale().isRTL()
            || errorLocation == ErrorPanelLocationEnum.RIGHT
                && !LocaleInfo.getCurrentLocale().isRTL()
            || errorLocation == ErrorPanelLocationEnum.BOTTOM;
    final FlowPanel layout = new FlowPanel();
    if (errorLocation == ErrorPanelLocationEnum.TOP) {
      layout.add(this.errorLabel);
      layout.add(this.contents);
    } else {
      layout.add(this.contents);
      layout.add(this.errorLabel);
    }
    switch (errorLocation) {
      case TOP:
        this.errorLabel.setStylePrimaryName(this.decoratorStyle.errorLabelStyleTop());
        this.contents.setStylePrimaryName(this.decoratorStyle.contentContainerStyleTop());
        break;
      case BOTTOM:
        this.errorLabel.setStylePrimaryName(this.decoratorStyle.errorLabelStyleBottom());
        this.contents.setStylePrimaryName(this.decoratorStyle.contentContainerStyleBottom());
        break;
      default:
        if (contentFirst) {
          this.errorLabel.setStylePrimaryName(this.decoratorStyle.errorLabelStyleRight());
          this.contents.setStylePrimaryName(this.decoratorStyle.contentContainerStyleRight());
        } else {
          this.errorLabel.setStylePrimaryName(this.decoratorStyle.errorLabelStyleLeft());
          this.contents.setStylePrimaryName(this.decoratorStyle.contentContainerStyleLeft());
        }
        break;
    }
    this.errorLabel.getElement().getStyle().setDisplay(Display.NONE);
    this.focusOnError = true;
    this.initWidget(layout);
  }

  /**
   * Constructs a Decorator.
   *
   * @param errorLocation location of the error text
   */
  public AbstractDecorator(final ErrorPanelLocationEnum errorLocation) {
    this(errorLocation, getDefaultResources());
  }

  /**
   * Constructs a Decorator using a {@link Widget} widget with error location and style sheet.
   *
   * @param pwidget the widget
   * @param errorLocation location of the error text
   * @param decoratorResource resource with css information
   */
  public AbstractDecorator(final Widget pwidget, final ErrorPanelLocationEnum errorLocation,
      final Resources decoratorResource) {
    this(errorLocation, decoratorResource);
    this.widget = pwidget;
    this.contents.add(pwidget);
  }

  /**
   * Constructs a Decorator using a {@link Widget} widget with error location.
   *
   * @param pwidget the widget
   * @param errorLocation location of the error text
   */
  public AbstractDecorator(final Widget pwidget, final ErrorPanelLocationEnum errorLocation) {
    this(pwidget, errorLocation, getDefaultResources());
  }

  /**
   * Constructs a Decorator using a {@link Widget} widget with error location.
   *
   * @param pwidget the widget
   */
  public AbstractDecorator(final Widget pwidget) {
    this(pwidget, ErrorPanelLocationEnum.RIGHT, getDefaultResources());
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
    this.widget = (Widget) pwidget;
    this.contents.add(this.widget);
    this.setEditor(new ExtendedValueBoxEditor<T>(pwidget, this));
  }

  @SuppressWarnings("unchecked")
  @Override
  public final HandlerRegistration addValueChangeHandler(final ValueChangeHandler<T> phandler) {
    if (this.contents.getWidget() instanceof HasValueChangeHandlers<?>) {
      return ((HasValueChangeHandlers<T>) this.contents.getWidget())
          .addValueChangeHandler(phandler);
    } else {
      return null;
    }
  }

  /**
   * add a change handler to clear error state on value change.
   *
   * @param pwidget widget to set the handler to
   */
  protected void addValueChangeHandler(final HasValueChangeHandlers<T> pwidget) {
    pwidget.addValueChangeHandler(new ValueChangeHandler<T>() {
      @Override
      public void onValueChange(final ValueChangeEvent<T> event) {
        AbstractDecorator.this.clearErrors();
      }
    });
  }

  /**
   * clear errors.
   */
  public final void clearErrors() {
    this.errorLabel.setText("");
    this.errorLabel.getElement().getStyle().setDisplay(Display.NONE);
    if (this.contents.getWidget() != null) {
      this.contents.getWidget().removeStyleName(this.decoratorStyle.errorInputStyle());
      this.contents.getWidget().removeStyleName(this.decoratorStyle.validInputStyle());
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
    final Set<String> messages = new HashSet<String>();
    for (final EditorError error : errors) {
      if (this.editorErrorMatches(error)) {
        messages.add(error.getMessage());
      }
    }
    if (messages.isEmpty()) {
      this.errorLabel.setText("");
      this.errorLabel.getElement().getStyle().setDisplay(Display.NONE);
      if (this.contents.getWidget() != null) {
        this.contents.getWidget().removeStyleName(this.decoratorStyle.errorInputStyle());
        this.contents.getWidget().addStyleName(this.decoratorStyle.validInputStyle());
      }
    } else {
      if (this.contents.getWidget() != null) {
        this.contents.getWidget().removeStyleName(this.decoratorStyle.validInputStyle());
        this.contents.getWidget().addStyleName(this.decoratorStyle.errorInputStyle());
        if (this.focusOnError) {
          this.setFocus(true);
        }
      }
      final SafeHtmlBuilder sb = new SafeHtmlBuilder();
      for (final String message : messages) {
        sb.appendEscaped(message);
        sb.appendHtmlConstant("<br />");
      }
      this.errorLabel.setHTML(sb.toSafeHtml());
      this.errorLabel.getElement().getStyle().setDisplay(Display.TABLE);
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
        && (this.equals(perror.getEditor()) || perror.getEditor().equals(this.editor));
  }

  @SuppressWarnings("unchecked")
  @Override
  public final T getValue() {
    if (this.contents.getWidget() instanceof TakesValue<?>) {
      return ((TakesValue<T>) this.contents.getWidget()).getValue();
    } else {
      return null;
    }
  }

  @Override
  public final void setValue(final T pvalue) {
    this.setValue(pvalue, false);
  }

  @SuppressWarnings("unchecked")
  @Override
  public final void setValue(final T pvalue, final boolean pfireEvents) {
    if (this.contents.getWidget() instanceof TakesValue<?>) {
      this.clearErrors();
      if (this.contents.getWidget() instanceof HasValue<?>) {
        ((HasValue<T>) this.contents.getWidget()).setValue(pvalue, pfireEvents);
      } else {
        ((TakesValue<T>) this.contents.getWidget()).setValue(pvalue);
      }
    }
  }

  @Override
  public final void setTabIndex(final int pindex) {
    if (this.contents.getWidget() instanceof Focusable) {
      ((Focusable) this.contents.getWidget()).setTabIndex(pindex);
    }
  }

  @Override
  public final int getTabIndex() {
    if (this.contents.getWidget() instanceof Focusable) {
      return ((Focusable) this.contents.getWidget()).getTabIndex();
    }
    return 0;
  }

  @Override
  public final void setAccessKey(final char pkey) {
    if (this.contents.getWidget() instanceof Focusable) {
      ((Focusable) this.contents.getWidget()).setAccessKey(pkey);
    }
  }

  @Override
  public final void setFocus(final boolean pfocused) {
    if (this.contents.getWidget() instanceof Focusable) {
      ((Focusable) this.contents.getWidget()).setFocus(pfocused);
    }
  }

  /**
   * get the widget.
   *
   * @return the widget
   */
  @Override
  public final Widget getWidget() {
    return this.widget;
  }

  /**
   * Returns the associated {@link ValueBoxEditor}.
   *
   * @return a {@link ValueBoxEditor} instance
   * @see #setEditor(ExtendedValueBoxEditor)
   */
  @Override
  public ValueBoxEditor<T> asEditor() {
    return this.editor;
  }

  /**
   * Sets the associated {@link ValueBoxEditor}.
   *
   * @param peditor a {@link ValueBoxEditor} instance
   * @see #asEditor()
   */
  public final void setEditor(final ExtendedValueBoxEditor<T> peditor) {
    this.editor = peditor;
  }

  /**
   * check if focus on error is active.
   *
   * @return true if widget should get focus on error
   */
  public final boolean isFocusOnError() {
    return this.focusOnError;
  }

  /**
   * set focus on error flag, if it's true, the widget get's the focus if validation finds an error.
   *
   * @param pfocusOnError the focusOnError to set
   */
  public final void setFocusOnError(final boolean pfocusOnError) {
    this.focusOnError = pfocusOnError;
  }

  @Override
  public final HandlerRegistration addKeyPressHandler(final KeyPressHandler phandler) {
    if (this.contents.getWidget() instanceof HasKeyPressHandlers) {
      return ((HasKeyPressHandlers) this.contents.getWidget()).addKeyPressHandler(phandler);
    } else {
      return null;
    }
  }

  @Override
  public HandlerRegistration addKeyUpHandler(final KeyUpHandler pkeyUpHandler) {
    if (this.contents.getWidget() instanceof HasKeyUpHandlers) {
      return ((HasKeyUpHandlers) this.contents.getWidget()).addKeyUpHandler(pkeyUpHandler);
    } else {
      return null;
    }
  }
}
