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
    this.label = plabel;
    this.getLayout().add(this.label);
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
    this.widget = (Widget) pwidget;
    this.contents.add(this.widget);
    this.setEditor(new ExtendedValueBoxEditor<>(pwidget, this));
    if (pwidget instanceof HasFocusHandlers) {
      ((HasFocusHandlers) pwidget)
          .addFocusHandler(pevent -> AbstractDecoratorWithLabel.this.addStyleToLabel());
    }
    if (pwidget instanceof HasBlurHandlers) {
      ((HasBlurHandlers) pwidget).addBlurHandler(pevent -> {
        boolean hide = true;
        if (AbstractDecoratorWithLabel.this.widget instanceof TakesValue<?>) {
          hide = StringUtils.isEmpty(Objects
              .toString(((TakesValue<?>) AbstractDecoratorWithLabel.this.widget).getValue(), null));
        }
        if (hide) {
          AbstractDecoratorWithLabel.this.removeStyleFromLabel();
        }
      });
    }
  }

  @Override
  public void setValue(final T pvalue, final boolean pfireEvents) {
    super.setValue(pvalue, pfireEvents);
    if (StringUtils.isEmpty(Objects.toString(pvalue, null))) {
      this.removeStyleFromLabel();
    } else {
      this.addStyleToLabel();
    }
  }

  @Override
  public void clearErrors() {
    super.clearErrors();
    if (this.contents.getWidget() instanceof TakesValue<?>) {
      if (StringUtils.isEmpty(
          Objects.toString(((TakesValue<?>) this.contents.getWidget()).getValue(), null))) {
        this.removeStyleFromLabel();
      } else {
        this.addStyleToLabel();
      }
    }
  }

  private void addStyleToLabel() {
    if (!AbstractDecoratorWithLabel.this.label.getElement()
        .hasClassName(((DecoratorStyleWithLabel) AbstractDecoratorWithLabel.this.decoratorStyle)
            .labelStyleFocused())) {
      AbstractDecoratorWithLabel.this.label.getElement()
          .addClassName(((DecoratorStyleWithLabel) AbstractDecoratorWithLabel.this.decoratorStyle)
              .labelStyleFocused());
    }
  }

  private void removeStyleFromLabel() {
    if (AbstractDecoratorWithLabel.this.label.getElement()
        .hasClassName(((DecoratorStyleWithLabel) AbstractDecoratorWithLabel.this.decoratorStyle)
            .labelStyleFocused())) {
      AbstractDecoratorWithLabel.this.label.getElement().removeClassName(
          ((DecoratorStyleWithLabel) AbstractDecoratorWithLabel.this.decoratorStyle)
              .labelStyleFocused());
    }
  }
}
