package de.knightsoftnet.validators.client.decorators;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.event.dom.client.HasBlurHandlers;
import com.google.gwt.event.dom.client.HasFocusHandlers;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.Widget;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public abstract class AbstractDecoratorWithLabel<T> extends AbstractDecorator<T> {

  /**
   * A ClientBundle that provides images and decoratorStyle sheets for the decorator.
   */
  public interface ResourcesLabel extends Resources {

    /**
     * The styles used in this widget.
     *
     * @return decorator style
     */
    @Override
    @Source("EditorWithLabelDecorator.gss")
    DecoratorStyleWithLabel decoratorStyle();
  }

  /**
   * the default resources.
   */
  private static volatile ResourcesLabel defaultResource;


  /**
   * label of the widget.
   */
  @Ignore
  private Widget label;

  public AbstractDecoratorWithLabel(final PanelLocationEnum perrorLocation) {
    this(perrorLocation, getDefaultResources());
  }

  public AbstractDecoratorWithLabel(final PanelLocationEnum perrorLocation,
      final ResourcesLabel presource) {
    super(perrorLocation, presource);
  }

  /**
   * get default resource, if not set, create one.
   *
   * @return default resource.
   */
  protected static ResourcesLabel getDefaultResources() {
    if (defaultResource == null) { // NOPMD it's thread save!
      synchronized (Resources.class) {
        if (defaultResource == null) {
          defaultResource = GWT.create(ResourcesLabel.class);
        }
      }
    }
    return defaultResource;
  }

  /**
   * Set the label of widget.
   *
   * @param plabel a label widget
   */
  @UiChild(limit = 1, tagname = "label")
  public void setChildLabel(final Widget plabel) {
    label = plabel;
    getLayout().add(label);
  }

  /**
   * Set the widget that the EditorPanel will display. This method will automatically call
   * {@link #setEditor}.
   *
   * @param pwidget a {@link IsEditor} widget
   */
  @Override
  @UiChild(limit = 1, tagname = "widget")
  public void setChildWidget(final TakesValue<T> pwidget) {
    widget = (Widget) pwidget;
    contents.add(widget);
    setEditor(new ExtendedValueBoxEditor<>(pwidget, this));
    if (pwidget instanceof HasFocusHandlers) {
      ((HasFocusHandlers) pwidget).addFocusHandler(pevent -> addStyleToLabel());
    }
    if (pwidget instanceof HasBlurHandlers) {
      ((HasBlurHandlers) pwidget).addBlurHandler(pevent -> {
        boolean hide = true;
        if (widget instanceof TakesValue<?>) {
          hide = StringUtils.isEmpty(Objects.toString(((TakesValue<?>) widget).getValue(), null));
        }
        if (hide) {
          removeStyleFromLabel();
        }
      });
    }
  }

  @Override
  public void setValue(final T pvalue, final boolean pfireEvents) {
    super.setValue(pvalue, pfireEvents);
    if (StringUtils.isEmpty(Objects.toString(pvalue, null))) {
      removeStyleFromLabel();
    } else {
      addStyleToLabel();
    }
  }

  @Override
  public void clearErrors() {
    super.clearErrors();
    if (contents.getWidget() instanceof TakesValue<?>) {
      if (StringUtils
          .isEmpty(Objects.toString(((TakesValue<?>) contents.getWidget()).getValue(), null))) {
        removeStyleFromLabel();
      } else {
        addStyleToLabel();
      }
    }
  }

  private void addStyleToLabel() {
    if (!label.getElement()
        .hasClassName(((DecoratorStyleWithLabel) decoratorStyle).labelStyleFocused())) {
      label.getElement()
          .addClassName(((DecoratorStyleWithLabel) decoratorStyle).labelStyleFocused());
    }
  }

  private void removeStyleFromLabel() {
    if (label.getElement()
        .hasClassName(((DecoratorStyleWithLabel) decoratorStyle).labelStyleFocused())) {
      label.getElement()
          .removeClassName(((DecoratorStyleWithLabel) decoratorStyle).labelStyleFocused());
    }
  }
}
