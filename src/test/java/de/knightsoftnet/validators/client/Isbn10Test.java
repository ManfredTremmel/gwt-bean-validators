package de.knightsoftnet.validators.client;

import de.knightsoftnet.validators.shared.Isbn10;

import org.junit.Test;

public class Isbn10Test extends AbstractValidationTest<Isbn10Test.Isbn10TestBean> {

  public class Isbn10TestBean {

    @Isbn10
    private final String isbn;

    public Isbn10TestBean(final String pisbn) {
      super();
      this.isbn = pisbn;
    }

    public String getIsbn() {
      return this.isbn;
    }
  }

  /**
   * empty isbn10 is allowed.
   */
  @Test
  public final void testEmptyIsbn10IsAllowed() {
    super.validationTest(new Isbn10TestBean(null), true, null);
  }

  /**
   * correct isbn10 is allowed.
   */
  @Test
  public final void testCorrectIsbn10IsAllowed() {
    super.validationTest(new Isbn10TestBean("3807701710"), true, null);
    super.validationTest(new Isbn10TestBean("3807702059"), true, null);
    super.validationTest(new Isbn10TestBean("3807701923"), true, null);
    super.validationTest(new Isbn10TestBean("3866400012"), true, null);
    super.validationTest(new Isbn10TestBean("3937514120"), true, null);
  }

  /**
   * isbn10 with wrong checksum.
   */
  @Test
  public final void testWrongChecksumIsbn10IsWrong() {
    super.validationTest(new Isbn10TestBean("3807701700"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn10Validator");
    super.validationTest(new Isbn10TestBean("3807702058"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn10Validator");
    super.validationTest(new Isbn10TestBean("3807701932"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn10Validator");
    super.validationTest(new Isbn10TestBean("3866402012"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn10Validator");
    super.validationTest(new Isbn10TestBean("3935714120"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn10Validator");
  }

  /**
   * isbn10 which is to small.
   */
  @Test
  public final void testToSmallIsbn10IsWrong() {
    super.validationTest(new Isbn10TestBean("380770193"), false,
        "org.hibernate.validator.constraints.impl.SizeValidatorForString");
  }

  /**
   * isbn10 which is to big.
   */
  @Test
  public final void testToBigIsbn10IsWrong() {
    super.validationTest(new Isbn10TestBean("380770192354"), false,
        "org.hibernate.validator.constraints.impl.DigitsValidatorForString");
  }

  /**
   * isbn10 which is not numeric.
   */
  @Test
  public final void testNotNumericIsbn10IsWrong() {
    super.validationTest(new Isbn10TestBean("38077Y1923"), false,
        "org.hibernate.validator.constraints.impl.DigitsValidatorForString");
  }
}
