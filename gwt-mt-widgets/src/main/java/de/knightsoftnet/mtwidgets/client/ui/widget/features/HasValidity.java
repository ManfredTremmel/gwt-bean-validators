package de.knightsoftnet.mtwidgets.client.ui.widget.features;

import elemental.html.ValidityState;

public interface HasValidity {

  /**
   * A localized message that describes the validation constraints that the control does not satisfy
   * (if any). This is the empty string if the control is not a candidate for constraint validation
   * (<strong>willValidate</strong> is false), or it satisfies its constraints.
   *
   * @return localized validation message
   */
  String getValidationMessage();

  /**
   * The validity states that this element is in.
   *
   * @return ValidityState
   */
  ValidityState getValidity();

  /**
   * Returns false if the element is a candidate for constraint validation, and it does not satisfy
   * its constraints. In this case, it also fires an <code>invalid</code> event at the element. It
   * returns true if the element is not a candidate for constraint validation, or if it satisfies
   * its constraints.
   *
   * @return true if input is valid
   */
  boolean checkValidity();
}
