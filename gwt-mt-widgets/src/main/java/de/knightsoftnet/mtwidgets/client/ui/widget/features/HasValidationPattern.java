package de.knightsoftnet.mtwidgets.client.ui.widget.features;

public interface HasValidationPattern {

  /**
   * get validation pattern.
   *
   * @return regular expression used to validate
   */
  String getPattern();

  /**
   * set validation pattern.
   *
   * @param arg regular expression should be used to validate
   */
  void setPattern(String arg);
}
