package de.knightsoftnet.validators.client;

import de.knightsoftnet.validators.shared.IsbnFormated;

import org.junit.Test;

public class IsbnFormatedTest extends AbstractValidationTest<IsbnFormatedTest.IsbnTestBean> {

  public class IsbnTestBean {

    @IsbnFormated
    private final String isbn;

    public IsbnTestBean(final String pisbn) {
      super();
      this.isbn = pisbn;
    }

    public String getIsbn() {
      return this.isbn;
    }
  }

  /**
   * empty isbn is allowed.
   */
  @Test
  public final void testEmptyIsbnIsAllowed() {
    super.validationTest(new IsbnTestBean(null), true, null);
  }

  /**
   * correct isbn is allowed.
   */
  @Test
  public final void testCorrectIsbnIsAllowed() {
    super.validationTest(new IsbnTestBean("978-3-83-621802-3"), true, null);
    super.validationTest(new IsbnTestBean("978-3-83-621507-7"), true, null);
    super.validationTest(new IsbnTestBean("978-3-89-864471-6"), true, null);
    super.validationTest(new IsbnTestBean("3-80-770171-0"), true, null);
    super.validationTest(new IsbnTestBean("3-80-770205-9"), true, null);
    super.validationTest(new IsbnTestBean("3-80-770192-3"), true, null);
    super.validationTest(new IsbnTestBean("3-86-640001-2"), true, null);
    super.validationTest(new IsbnTestBean("3-93-751412-0"), true, null);
  }

  /**
   * isbn with wrong checksum.
   */
  @Test
  public final void testWrongChecksumIsbnIsWrong() {
    super.validationTest(new IsbnTestBean("978-3-83-621803-2"), false,
        "de.knightsoftnet.validators.shared.impl.IsbnFormatedValidator");
    super.validationTest(new IsbnTestBean("978-3-83-821507-7"), false,
        "de.knightsoftnet.validators.shared.impl.IsbnFormatedValidator");
    super.validationTest(new IsbnTestBean("978-3-89-964471-6"), false,
        "de.knightsoftnet.validators.shared.impl.IsbnFormatedValidator");
    super.validationTest(new IsbnTestBean("3-80-770170-0"), false,
        "de.knightsoftnet.validators.shared.impl.IsbnFormatedValidator");
    super.validationTest(new IsbnTestBean("3-80-770205-8"), false,
        "de.knightsoftnet.validators.shared.impl.IsbnFormatedValidator");
    super.validationTest(new IsbnTestBean("3-80-770193-2"), false,
        "de.knightsoftnet.validators.shared.impl.IsbnFormatedValidator");
    super.validationTest(new IsbnTestBean("3-86-640201-2"), false,
        "de.knightsoftnet.validators.shared.impl.IsbnFormatedValidator");
    super.validationTest(new IsbnTestBean("3-93-571412-0"), false,
        "de.knightsoftnet.validators.shared.impl.IsbnFormatedValidator");
  }

  /**
   * isbn size is not valid.
   */
  @Test
  public final void testWrongSizeIsbnIsWrong() {
    super.validationTest(new IsbnTestBean("3-80-770205"), false,
        "de.knightsoftnet.validators.shared.impl.AlternateSizeValidator");
    super.validationTest(new IsbnTestBean("3-80-770192-32"), false,
        "de.knightsoftnet.validators.shared.impl.AlternateSizeValidator");
    super.validationTest(new IsbnTestBean("3-93-75141"), false,
        "de.knightsoftnet.validators.shared.impl.AlternateSizeValidator");
    super.validationTest(new IsbnTestBean("978-3-83-621803"), false,
        "de.knightsoftnet.validators.shared.impl.AlternateSizeValidator");
    super.validationTest(new IsbnTestBean("978-3-89-964471-65"), false,
        "de.knightsoftnet.validators.shared.impl.AlternateSizeValidator");
  }
}
