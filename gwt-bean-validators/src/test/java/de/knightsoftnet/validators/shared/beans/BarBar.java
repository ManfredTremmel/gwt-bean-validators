package de.knightsoftnet.validators.shared.beans;

import javax.validation.constraints.NotNull;

/**
 * Extends bar.
 */
public class BarBar extends Bar {
  @NotNull
  private String barbarone;

  public String getBarbarone() {
    return this.barbarone;
  }

  public void setBarbarone(final String barbarone) {
    this.barbarone = barbarone;
  }

  @Override
  public String toString() {
    return super.toString() + " barbar:'" + this.barbarone + "'";
  }
}
