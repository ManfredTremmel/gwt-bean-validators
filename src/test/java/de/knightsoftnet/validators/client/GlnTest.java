package de.knightsoftnet.validators.client;

import de.knightsoftnet.validators.shared.Gln;

import org.junit.Test;

public class GlnTest extends AbstractValidationTest<GlnTest.GlnTestBean> {

  public class GlnTestBean {

    @Gln
    private final String gln;

    public GlnTestBean(final String pgln) {
      super();
      this.gln = pgln;
    }

    public String getGln() {
      return this.gln;
    }
  }

  /**
   * empty gln is allowed.
   */
  @Test
  public final void testEmptyGlnIsAllowed() {
    super.validationTest(new GlnTestBean(null), true, null);
  }

  /**
   * correct gln is allowed.
   */
  @Test
  public final void testCorrectGlnIsAllowed() {
    super.validationTest(new GlnTestBean("4035600210708"), true, null);
    super.validationTest(new GlnTestBean("4250155401375"), true, null);
    super.validationTest(new GlnTestBean("9004617011702"), true, null);
  }

  /**
   * gln with wrong checksum.
   */
  @Test
  public final void testWrongChecksumGlnIsWrong() {
    super.validationTest(new GlnTestBean("4035600210078"), false,
        "de.knightsoftnet.validators.shared.impl.GlnValidator");
    super.validationTest(new GlnTestBean("4250515401375"), false,
        "de.knightsoftnet.validators.shared.impl.GlnValidator");
    super.validationTest(new GlnTestBean("4035601210078"), false,
        "de.knightsoftnet.validators.shared.impl.GlnValidator");
  }

  /**
   * gln which is to small.
   */
  @Test
  public final void testToSmallGlnIsWrong() {
    super.validationTest(new GlnTestBean("4035600210"), false,
        "org.hibernate.validator.constraints.impl.SizeValidatorForString");
  }

  /**
   * gln which is to big.
   */
  @Test
  public final void testToBigGlnIsWrong() {
    super.validationTest(new GlnTestBean("40356002107081"), false,
        "org.hibernate.validator.constraints.impl.DigitsValidatorForString");
  }

  /**
   * gln which is not numeric.
   */
  @Test
  public final void testNotNumericGlnIsWrong() {
    super.validationTest(new GlnTestBean("403560021070Y"), false,
        "org.hibernate.validator.constraints.impl.DigitsValidatorForString");
  }
}
