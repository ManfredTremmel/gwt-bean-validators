package de.knightsoftnet.validators.shared.beans;

import javax.validation.constraints.NotNull;

/**
 * bar.
 */
public class Bar extends IdType {

  @NotNull
  private String three;
  @NotNull
  private String four;

  public String getThree() {
    return this.three;
  }

  public void setThree(final String three) {
    this.three = three;
  }

  public String getFour() {
    return this.four;
  }

  public void setFour(final String four) {
    this.four = four;
  }

  @Override
  public String toString() {
    return "BAR: 3:'" + this.three + "' 4:'" + this.four + "'";
  }
}
