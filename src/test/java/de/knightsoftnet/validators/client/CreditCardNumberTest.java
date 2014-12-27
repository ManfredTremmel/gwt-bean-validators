package de.knightsoftnet.validators.client;

import de.knightsoftnet.validators.shared.CreditCardNumber;

import org.junit.Test;

public class CreditCardNumberTest extends
    AbstractValidationTest<CreditCardNumberTest.CreditCardNumberTestBean> {

  public class CreditCardNumberTestBean {

    @CreditCardNumber
    private final String creditCard;

    public CreditCardNumberTestBean(final String pcreditCard) {
      super();
      this.creditCard = pcreditCard;
    }

    public String getCreditCard() {
      return this.creditCard;
    }
  }

  /**
   * empty credit card is allowed.
   */
  @Test
  public final void testEmptyCreditCardIsAllowed() {
    super.validationTest(new CreditCardNumberTestBean(null), true, null);
  }

  /**
   * correct credit card numbers are allowed.
   */
  @Test
  public final void testCorrectCreditCardsAreAllowed() {
    super.validationTest(new CreditCardNumberTestBean("4417123456789113"), true, null);
    super.validationTest(new CreditCardNumberTestBean("4222222222222"), true, null);
    super.validationTest(new CreditCardNumberTestBean("378282246310005"), true, null);
    super.validationTest(new CreditCardNumberTestBean("5105105105105100"), true, null);
    super.validationTest(new CreditCardNumberTestBean("6011000990139424"), true, null);
  }

  /**
   * wrong credit card numbers are not allowed.
   */
  @Test
  public final void testWrongCreditCardsAreWrong() {
    super.validationTest(new CreditCardNumberTestBean("123456789012"), false,
        "de.knightsoftnet.validators.shared.impl.CreditCardNumberValidator");
    super.validationTest(new CreditCardNumberTestBean("12345678901234567890"), false,
        "org.hibernate.validator.constraints.impl.SizeValidatorForString");
    super.validationTest(new CreditCardNumberTestBean("4417123456789112"), false,
        "de.knightsoftnet.validators.shared.impl.CreditCardNumberValidator");
    super.validationTest(new CreditCardNumberTestBean("4417q23456w89113"), false,
        "de.knightsoftnet.validators.shared.impl.CreditCardNumberValidator");
  }
}
