package de.knightsoftnet.validators.client;

import de.knightsoftnet.validators.shared.Isin;

import org.junit.Test;

public class IsinTest extends AbstractValidationTest<IsinTest.IsinTestBean> {

  public class IsinTestBean {

    @Isin
    private final String isin;

    public IsinTestBean(final String pisin) {
      super();
      this.isin = pisin;
    }

    public String getIsin() {
      return this.isin;
    }
  }

  /**
   * empty isin is allowed.
   */
  @Test
  public final void testEmptyIsbnIsAllowed() {
    super.validationTest(new IsinTestBean(null), true, null);
  }

  /**
   * correct isin is allowed.
   */
  @Test
  public final void testCorrectIsbnIsAllowed() {
    super.validationTest(new IsinTestBean("EU0009652627"), true, null);
    super.validationTest(new IsinTestBean("EU000A0T74M4"), true, null);
    super.validationTest(new IsinTestBean("DE000BAY0017"), true, null);
    super.validationTest(new IsinTestBean("AU0000XVGZA3"), true, null);
    super.validationTest(new IsinTestBean("XF0000C14922"), true, null);
  }

  /**
   * isin with wrong checksum.
   */
  @Test
  public final void testWrongChecksumIsbnIsWrong() {
    super.validationTest(new IsinTestBean("EU1009652627"), false,
        "de.knightsoftnet.validators.shared.impl.IsinValidator");
    super.validationTest(new IsinTestBean("EU000A0T74M5"), false,
        "de.knightsoftnet.validators.shared.impl.IsinValidator");
    super.validationTest(new IsinTestBean("DE100BAY0017"), false,
        "de.knightsoftnet.validators.shared.impl.IsinValidator");
    super.validationTest(new IsinTestBean("AU0000XVGZB3"), false,
        "de.knightsoftnet.validators.shared.impl.IsinValidator");
    super.validationTest(new IsinTestBean("XF0000C14822"), false,
        "de.knightsoftnet.validators.shared.impl.IsinValidator");
  }

  /**
   * isin size is not valid.
   */
  @Test
  public final void testWrongSizeIsbnIsWrong() {
    super.validationTest(new IsinTestBean("EU000965262"), false,
        "org.hibernate.validator.constraints.impl.SizeValidatorForString");
    super.validationTest(new IsinTestBean("EU000A0T74M45"), false,
        "org.hibernate.validator.constraints.impl.SizeValidatorForString");
  }
}
