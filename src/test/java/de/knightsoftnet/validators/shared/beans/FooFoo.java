package de.knightsoftnet.validators.shared.beans;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Extends Foo.
 */
public class FooFoo extends Foo {

  @NotNull
  @Valid
  private BarBar barBar;
  @Valid
  private final List<BarBar> barBarList = new ArrayList<>();

  public List<BarBar> getBarBarList() {
    return this.barBarList;
  }

  public BarBar getBarBar() {
    return this.barBar;
  }

  public void setBarBar(final BarBar barBar) {
    this.barBar = barBar;
  }

  @Override
  public String toString() {
    final StringBuilder buildy = new StringBuilder();

    buildy.append(" FooFoo: % ").append(this.barBar);
    for (final BarBar b : this.barBarList) {
      buildy.append(" # ").append(b);
    }
    return super.toString() + " " + buildy.toString();
  }
}
