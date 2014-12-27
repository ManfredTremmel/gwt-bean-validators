package de.knightsoftnet.validators.client;

import de.knightsoftnet.validators.shared.Isbn10Formated;

import org.junit.Test;

public class Isbn10FormatedTest extends AbstractValidationTest<Isbn10FormatedTest.Isbn10TestBean> {

  public class Isbn10TestBean {

    @Isbn10Formated
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
    super.validationTest(new Isbn10TestBean("3-80-770171-0"), true, null);
    super.validationTest(new Isbn10TestBean("3-80-770205-9"), true, null);
    super.validationTest(new Isbn10TestBean("3-80-770192-3"), true, null);
    super.validationTest(new Isbn10TestBean("3-86-640001-2"), true, null);
    super.validationTest(new Isbn10TestBean("3-93-751412-0"), true, null);
  }

  /**
   * isbn10 with wrong checksum.
   */
  @Test
  public final void testWrongChecksumIsbn10IsWrong() {
    super.validationTest(new Isbn10TestBean("3-80-770170-0"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn10FormatedValidator");
    super.validationTest(new Isbn10TestBean("3-80-770205-8"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn10FormatedValidator");
    super.validationTest(new Isbn10TestBean("3-80-770193-2"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn10FormatedValidator");
    super.validationTest(new Isbn10TestBean("3-86-640201-2"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn10FormatedValidator");
    super.validationTest(new Isbn10TestBean("3-93-571412-0"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn10FormatedValidator");
  }

  /**
   * isbn10 which is to small.
   */
  @Test
  public final void testToSmallIsbn10IsWrong() {
    super.validationTest(new Isbn10TestBean("3-80-770193"), false,
        "org.hibernate.validator.constraints.impl.SizeValidatorForString");
  }

  /**
   * isbn10 which is to big.
   */
  @Test
  public final void testToBigIsbn10IsWrong() {
    super.validationTest(new Isbn10TestBean("3-80-770192-354"), false,
        "org.hibernate.validator.constraints.impl.SizeValidatorForString");
  }

  /**
   * isbn10 which has wrong format.
   */
  @Test
  public final void testNotNumericIsbn10IsWrong() {
    super.validationTest(new Isbn10TestBean("3 86-640201-2"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn10FormatedValidator");
    super.validationTest(new Isbn10TestBean("3+93-571412-0"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn10FormatedValidator");
    super.validationTest(new Isbn10TestBean("3-80-770193-2"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn10FormatedValidator");
    super.validationTest(new Isbn10TestBean("3-866-40201-2"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn10FormatedValidator");
    super.validationTest(new Isbn10TestBean("3-93-571-4120"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn10FormatedValidator");
  }
}
