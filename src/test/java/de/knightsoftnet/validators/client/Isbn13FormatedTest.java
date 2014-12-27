package de.knightsoftnet.validators.client;

import de.knightsoftnet.validators.shared.Isbn13Formated;

import org.junit.Test;

public class Isbn13FormatedTest extends AbstractValidationTest<Isbn13FormatedTest.Isbn13TestBean> {

  public class Isbn13TestBean {

    @Isbn13Formated
    private final String isbn;

    public Isbn13TestBean(final String pisbn) {
      super();
      this.isbn = pisbn;
    }

    public String getIsbn() {
      return this.isbn;
    }
  }

  /**
   * empty isbn13 is allowed.
   */
  @Test
  public final void testEmptyIsbn13IsAllowed() {
    super.validationTest(new Isbn13TestBean(null), true, null);
  }

  /**
   * correct isbn13 is allowed.
   */
  @Test
  public final void testCorrectIsbn13IsAllowed() {
    super.validationTest(new Isbn13TestBean("978-3-83-621802-3"), true, null);
    super.validationTest(new Isbn13TestBean("978-3-83-621507-7"), true, null);
    super.validationTest(new Isbn13TestBean("978-3-89-864471-6"), true, null);
  }

  /**
   * isbn13 with wrong checksum.
   */
  @Test
  public final void testWrongChecksumIsbn13IsWrong() {
    super.validationTest(new Isbn13TestBean("978-3-83-621803-2"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn13FormatedValidator");
    super.validationTest(new Isbn13TestBean("978-3-83-821507-7"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn13FormatedValidator");
    super.validationTest(new Isbn13TestBean("978-3-89-964471-6"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn13FormatedValidator");
  }

  /**
   * isbn13 which is to small.
   */
  @Test
  public final void testToSmallIsbn13IsWrong() {
    super.validationTest(new Isbn13TestBean("978-3-83-621803"), false,
        "org.hibernate.validator.constraints.impl.SizeValidatorForString");
  }

  /**
   * isbn13 which is to big.
   */
  @Test
  public final void testToBigIsbn13IsWrong() {
    super.validationTest(new Isbn13TestBean("978-3-83-621803-21"), false,
        "org.hibernate.validator.constraints.impl.SizeValidatorForString");
  }
}
