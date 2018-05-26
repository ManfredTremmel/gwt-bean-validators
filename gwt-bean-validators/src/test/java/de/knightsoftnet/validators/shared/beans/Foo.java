package de.knightsoftnet.validators.shared.beans;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * foo.
 */
public class Foo extends IdType {

  @NotNull
  private String one;
  @NotNull
  private String two;

  @NotNull
  @Valid
  private Bar bar;

  @Valid
  private final List<Bar> barList = new ArrayList<>();

  public String getOne() {
    return this.one;
  }

  public void setOne(final String one) {
    this.one = one;
  }

  public String getTwo() {
    return this.two;
  }

  public void setTwo(final String two) {
    this.two = two;
  }

  public Bar getBar() {
    return this.bar;
  }

  public void setBar(final Bar bar) {
    this.bar = bar;
  }

  public List<Bar> getBarList() {
    return this.barList;
  }

  @Override
  public String toString() {
    final StringBuilder buildy = new StringBuilder();

    buildy.append(" % ").append(this.bar);
    for (final Bar b : this.barList) {
      buildy.append(" # ").append(b);
    }
    return "FOO: 1:'" + this.one + "' 2:'" + this.two + "'" + buildy.toString();
  }
}
