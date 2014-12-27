package de.knightsoftnet.validators.client;

import de.knightsoftnet.validators.shared.Gtin;

import org.junit.Test;

public class GtinTest extends AbstractValidationTest<GtinTest.GtinTestBean> {

  public class GtinTestBean {

    @Gtin
    private final String gtin;

    public GtinTestBean(final String pgtin) {
      super();
      this.gtin = pgtin;
    }

    public String getGtin() {
      return this.gtin;
    }
  }

  /**
   * empty gtin is allowed.
   */
  @Test
  public final void testEmptyGtinIsAllowed() {
    super.validationTest(new GtinTestBean(null), true, null);
  }

  /**
   * correct gtin is allowed.
   */
  @Test
  public final void testCorrectGtinIsAllowed() {
    super.validationTest(new GtinTestBean("12345670"), true, null);
    super.validationTest(new GtinTestBean("40267708"), true, null);
    super.validationTest(new GtinTestBean("96385074"), true, null);
    super.validationTest(new GtinTestBean("4035600210708"), true, null);
    super.validationTest(new GtinTestBean("4250155401375"), true, null);
    super.validationTest(new GtinTestBean("9004617011702"), true, null);
  }

  /**
   * gtin with wrong checksum.
   */
  @Test
  public final void testWrongChecksumGtinIsWrong() {
    super.validationTest(new GtinTestBean("12345678"), false,
        "de.knightsoftnet.validators.shared.impl.GtinValidator");
    super.validationTest(new GtinTestBean("40627708"), false,
        "de.knightsoftnet.validators.shared.impl.GtinValidator");
    super.validationTest(new GtinTestBean("96386074"), false,
        "de.knightsoftnet.validators.shared.impl.GtinValidator");
    super.validationTest(new GtinTestBean("4035600210078"), false,
        "de.knightsoftnet.validators.shared.impl.GtinValidator");
    super.validationTest(new GtinTestBean("4250515401375"), false,
        "de.knightsoftnet.validators.shared.impl.GtinValidator");
    super.validationTest(new GtinTestBean("4035601210078"), false,
        "de.knightsoftnet.validators.shared.impl.GtinValidator");
  }

  /**
   * gtin which has the wrong size.
   */
  @Test
  public final void testWrongSizeGtinIsWrong() {
    super.validationTest(new GtinTestBean("1234567"), false,
        "de.knightsoftnet.validators.shared.impl.AlternateSizeValidator");
    super.validationTest(new GtinTestBean("4035600210"), false,
        "de.knightsoftnet.validators.shared.impl.AlternateSizeValidator");
  }

  /**
   * gtin which is not numeric.
   */
  @Test
  public final void testNotNumericGtinIsWrong() {
    super.validationTest(new GtinTestBean("1234567Y"), false,
        "org.hibernate.validator.constraints.impl.DigitsValidatorForString");
    super.validationTest(new GtinTestBean("403560021070Y"), false,
        "org.hibernate.validator.constraints.impl.DigitsValidatorForString");
  }
}
