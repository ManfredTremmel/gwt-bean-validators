package de.knightsoftnet.mtwidgets.client.ui.widget.features;

public interface HasFormNoValidate {

  /**
   * check if validation of the widget is enabled (doesn't affect bean validation).
   *
   * @return true if validation is disabled
   */
  boolean isFormNoValidate();

  /**
   * enable/disable validation of the widget (doesn't affect bean validation).
   *
   * @param arg true if validation should be disabled
   */
  void setFormNoValidate(boolean arg);
}
