package de.knightsoftnet.mtwidgets.client.ui.widget.helper;

import de.knightsoftnet.validators.client.decorators.ExtendedValueBoxEditor;

import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.ValueBoxBase;

public class SuggestBoxValueBoxEditor extends ExtendedValueBoxEditor<String> {

  public SuggestBoxValueBoxEditor(final TakesValue<String> ptakesValues) {
    super(ptakesValues, null);
  }

  /**
   * get text box.
   *
   * @return text box base of the widget
   */
  @Ignore
  public TextBoxBase getTextBox() {
    if (getTakesValues() instanceof SuggestBox) {
      return (TextBoxBase) ((SuggestBox) getTakesValues()).getValueBox();
    }
    return null;
  }

  /**
   * get value box base.
   *
   * @return value box base of the widget
   */
  @Ignore
  public ValueBoxBase<String> getValueBox() {
    if (getTakesValues() instanceof SuggestBox) {
      return ((SuggestBox) getTakesValues()).getValueBox();
    }
    return null;
  }
}
