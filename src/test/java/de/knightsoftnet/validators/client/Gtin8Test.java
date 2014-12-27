package de.knightsoftnet.validators.client;

import de.knightsoftnet.validators.shared.Gtin8;

import org.junit.Test;

public class Gtin8Test extends AbstractValidationTest<Gtin8Test.Gtin8TestBean> {

  public class Gtin8TestBean {

    @Gtin8
    private final String gtin;

    public Gtin8TestBean(final String pgtin) {
      super();
      this.gtin = pgtin;
    }

    public String getGtin() {
      return this.gtin;
    }
  }

  /**
   * empty gtin8 is allowed.
   */
  @Test
  public final void testEmptyGtin8IsAllowed() {
    super.validationTest(new Gtin8TestBean(null), true, null);
  }

  /**
   * correct gtin8 is allowed.
   */
  @Test
  public final void testCorrectGtin8IsAllowed() {
    super.validationTest(new Gtin8TestBean("12345670"), true, null);
    super.validationTest(new Gtin8TestBean("40267708"), true, null);
    super.validationTest(new Gtin8TestBean("96385074"), true, null);
  }

  /**
   * gtin8 with wrong checksum.
   */
  @Test
  public final void testWrongChecksumGtin8IsWrong() {
    super.validationTest(new Gtin8TestBean("12345678"), false,
        "de.knightsoftnet.validators.shared.impl.Gtin8Validator");
    super.validationTest(new Gtin8TestBean("40627708"), false,
        "de.knightsoftnet.validators.shared.impl.Gtin8Validator");
    super.validationTest(new Gtin8TestBean("96386074"), false,
        "de.knightsoftnet.validators.shared.impl.Gtin8Validator");
  }

  /**
   * gtin8 which is to small.
   */
  @Test
  public final void testToSmallGtin8IsWrong() {
    super.validationTest(new Gtin8TestBean("1234567"), false,
        "org.hibernate.validator.constraints.impl.SizeValidatorForString");
  }

  /**
   * gtin8 which is to big.
   */
  @Test
  public final void testToBigGtin8IsWrong() {
    super.validationTest(new Gtin8TestBean("123456701"), false,
        "org.hibernate.validator.constraints.impl.DigitsValidatorForString");
  }

  /**
   * gtin8 which is not numeric.
   */
  @Test
  public final void testNotNumericGtin8IsWrong() {
    super.validationTest(new Gtin8TestBean("1234567Y"), false,
        "org.hibernate.validator.constraints.impl.DigitsValidatorForString");
  }
}
