package de.knightsoftnet.validators.client;

import de.knightsoftnet.validators.shared.IbanFormated;

import org.junit.Test;

public class IbanFormatedTest extends AbstractValidationTest<IbanFormatedTest.IbanTestBean> {

  public class IbanTestBean {

    @IbanFormated
    private final String iban;

    public IbanTestBean(final String piban) {
      super();
      this.iban = piban;
    }

    public String getIban() {
      return this.iban;
    }
  }

  /**
   * empty iban is allowed.
   */
  @Test
  public final void testEmptyIbanIsAllowed() {
    super.validationTest(new IbanTestBean(null), true, null);
  }

  /**
   * correct iban is allowed.
   */
  @Test
  public final void testCorrectIbanIsAllowed() {
    super.validationTest(new IbanTestBean("DE16 7016 0000 0000 5554 44"), true, null);
    super.validationTest(new IbanTestBean("DE49 4306 0967 0000 0334 01"), true, null);
    super.validationTest(new IbanTestBean("AT24 2011 1822 2121 9800"), true, null);
    super.validationTest(new IbanTestBean("CH16 0900 0000 8777 6876 6"), true, null);
    super.validationTest(new IbanTestBean("IT73 O050 1803 2000 0000 0125 125"), true, null);
  }

  /**
   * iban with country which is not part of SEPA country list.
   */
  @Test
  public final void testWrongCountryIbanIsWrong() {
    super.validationTest(new IbanTestBean("XY16 7016 0000 0000 5554 44"), false,
        "de.knightsoftnet.validators.shared.impl.IbanFormatedValidator");
  }

  /**
   * iban with country which is not part of SEPA country list.
   */
  @Test
  public final void testToSmallIbanIsWrong() {
    super.validationTest(new IbanTestBean("DE12 3456 1"), false,
        "org.hibernate.validator.constraints.impl.SizeValidatorForString");
  }

  /**
   * iban with country which is not part of SEPA country list.
   */
  @Test
  public final void testToBigIbanIsWrong() {
    super.validationTest(new IbanTestBean("DE16 7016 0000 0000 5554 4412 3456 7890 123"), false,
        "org.hibernate.validator.constraints.impl.SizeValidatorForString");
  }

  /**
   * iban with checksum error.
   */
  @Test
  public final void testChecksumErrorIbanIsWrong() {
    super.validationTest(new IbanTestBean("DE16 7061 0000 0000 5554 44"), false,
        "de.knightsoftnet.validators.shared.impl.IbanFormatedValidator");
  }

  /**
   * correct iban, but wrong formated error.
   */
  @Test
  public final void testWrongformatedIbanIsWrong() {
    super.validationTest(new IbanTestBean("DE167 016 0000 0000 5554 44"), false,
        "de.knightsoftnet.validators.shared.impl.IbanFormatedValidator");
    super.validationTest(new IbanTestBean("DE49 4306 09670000 0334 01"), false,
        "de.knightsoftnet.validators.shared.impl.IbanFormatedValidator");
    super.validationTest(new IbanTestBean("AT24 2011 1822 2121 980 0"), false,
        "de.knightsoftnet.validators.shared.impl.IbanFormatedValidator");
    super.validationTest(new IbanTestBean("CH16-0900-0000-8777-6876-6"), false,
        "de.knightsoftnet.validators.shared.impl.IbanFormatedValidator");
  }
}
