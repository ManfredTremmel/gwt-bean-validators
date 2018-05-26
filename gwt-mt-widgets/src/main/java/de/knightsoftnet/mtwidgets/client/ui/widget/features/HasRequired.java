package de.knightsoftnet.mtwidgets.client.ui.widget.features;

public interface HasRequired {

  /**
   * Reflects the <code><a href=
   * "https://developer.mozilla.org/en/HTML/Element/input#attr-required">required</a></code>
   * HTML&nbsp;attribute, indicating that the user must fill in a value before submitting a form.
   *
   * @return required true/false
   */
  boolean isRequired();

  /**
   * Reflects the <code><a href=
   * "https://developer.mozilla.org/en/HTML/Element/input#attr-required">required</a></code>
   * HTML&nbsp;attribute, indicating that the user must fill in a value before submitting a form.
   *
   * @param arg required true/false
   */
  void setRequired(boolean arg);
}
