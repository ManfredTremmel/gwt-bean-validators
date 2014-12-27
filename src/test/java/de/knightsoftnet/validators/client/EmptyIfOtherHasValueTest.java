package de.knightsoftnet.validators.client;

import de.knightsoftnet.validators.shared.EmptyIfOtherHasValue;
import de.knightsoftnet.validators.shared.NotEmptyIfOtherHasValue;
import de.knightsoftnet.validators.shared.interfaces.HasGetFieldByName;

import org.junit.Test;

public class EmptyIfOtherHasValueTest extends
    AbstractValidationTest<EmptyIfOtherHasValueTest.TestBean> {

  @EmptyIfOtherHasValue.List({
      @EmptyIfOtherHasValue(field = "street", fieldCompare = "type",
          valueCompare = "postOfficeBox"),
      @EmptyIfOtherHasValue(field = "postOfficeBox", fieldCompare = "type",
          valueCompare = "street"),})
  @NotEmptyIfOtherHasValue.List({
      @NotEmptyIfOtherHasValue(field = "street", fieldCompare = "type", valueCompare = "street"),
      @NotEmptyIfOtherHasValue(field = "postOfficeBox", fieldCompare = "type",
          valueCompare = "postOfficeBox"),})
  public class TestBean implements HasGetFieldByName {

    private final String type;

    private final String street;

    private final String postOfficeBox;

    /**
     * constructor initializing fields.
     *
     * @param ptype type indicator
     * @param pstreet street to set
     * @param ppostOfficeBox post office box to set
     */
    public TestBean(final String ptype, final String pstreet, final String ppostOfficeBox) {
      super();
      this.type = ptype;
      this.street = pstreet;
      this.postOfficeBox = ppostOfficeBox;
    }

    public String getType() {
      return this.type;
    }

    public String getStreet() {
      return this.street;
    }

    public String getPostOfficeBox() {
      return this.postOfficeBox;
    }

    @Override
    public final Object getFieldByName(final String pname) {
      if (pname != null) {
        switch (pname) {
          case "type":
            return this.type;
          case "street":
            return this.street;
          case "postOfficeBox":
            return this.postOfficeBox;
          default:
            return null;
        }
      }
      return null;
    }
  }

  /**
   * the value we request is not set, so everything is allowed.
   */
  @Test
  public final void testValueIsNotSetEverythingIsAllowed() {
    super.validationTest(new TestBean(null, null, null), true, null);
    super.validationTest(new TestBean(null, "", null), true, null);
    super.validationTest(new TestBean(null, null, ""), true, null);
    super.validationTest(new TestBean(null, "", ""), true, null);
    super.validationTest(new TestBean(null, "filled", null), true, null);
    super.validationTest(new TestBean(null, null, "filled"), true, null);
    super.validationTest(new TestBean(null, "filled", "filled"), true, null);
    super.validationTest(new TestBean(null, "filled", ""), true, null);
    super.validationTest(new TestBean(null, "", "filled"), true, null);
    super.validationTest(new TestBean(null, "filled", "filled"), true, null);
  }

  /**
   * the value we request is set to a type, we do not request, so everything is allowed.
   */
  @Test
  public final void testValueIsSetDifferentEverythingIsAllowed() {
    super.validationTest(new TestBean("filled", null, null), true, null);
    super.validationTest(new TestBean("filled", "", null), true, null);
    super.validationTest(new TestBean("filled", null, ""), true, null);
    super.validationTest(new TestBean("filled", "", ""), true, null);
    super.validationTest(new TestBean("filled", "filled", null), true, null);
    super.validationTest(new TestBean("filled", null, "filled"), true, null);
    super.validationTest(new TestBean("filled", "filled", "filled"), true, null);
    super.validationTest(new TestBean("filled", "filled", ""), true, null);
    super.validationTest(new TestBean("filled", "", "filled"), true, null);
    super.validationTest(new TestBean("filled", "filled", "filled"), true, null);
  }

  /**
   * the value we request is set, and requested fields match.
   */
  @Test
  public final void testValueIsSetFieldsToAllowed() {
    super.validationTest(new TestBean("street", "filled", null), true, null);
    super.validationTest(new TestBean("street", "filled", ""), true, null);
    super.validationTest(new TestBean("postOfficeBox", null, "filled"), true, null);
    super.validationTest(new TestBean("postOfficeBox", "", "filled"), true, null);
  }

  /**
   * the value we request is set, and requested fields do not match.
   */
  @Test
  public final void testValueIsSetFieldsNotWrong() {
    super.validationTest(new TestBean("street", "filled", "filled"), false,
        "de.knightsoftnet.validators.shared.impl.EmptyIfOtherHasValueValidator");
    super.validationTest(new TestBean("postOfficeBox", "filled", "filled"), false,
        "de.knightsoftnet.validators.shared.impl.EmptyIfOtherHasValueValidator");
  }
}
