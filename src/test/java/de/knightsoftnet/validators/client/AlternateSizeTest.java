package de.knightsoftnet.validators.client;

import de.knightsoftnet.validators.shared.AlternateSize;

import org.junit.Test;

public class AlternateSizeTest extends
    AbstractValidationTest<AlternateSizeTest.AlternateSizeTestBean> {

  public class AlternateSizeTestBean {

    @AlternateSize(size1 = 10, size2 = 13)
    private final String value;

    public AlternateSizeTestBean(final String pvalue) {
      super();
      this.value = pvalue;
    }

    public String getIban() {
      return this.value;
    }
  }

  /**
   * empty value is allowed.
   */
  @Test
  public final void testEmptyAlternateSizeIsAllowed() {
    super.validationTest(new AlternateSizeTestBean(null), true, null);
  }

  /**
   * correct sizes are allowed.
   */
  @Test
  public final void testCorrectAlternateSizesAreAllowed() {
    for (final String value : new String[] {"abcABCou!3", "3251202537", "3453136446",
        "4035600210708", "9783453136441"}) {
      super.validationTest(new AlternateSizeTestBean(value), true, null);
    }
  }

  /**
   * wrong sizes are not allowed.
   */
  @Test
  public final void testWrongAlternateSizeAreWrong() {
    for (final String value : new String[] {"308770192", "32512253", "34531365468", "403560821070",
        "978345313654"}) {
      super.validationTest(new AlternateSizeTestBean(value), false,
          "de.knightsoftnet.validators.shared.impl.AlternateSizeValidator");
    }
  }
}
