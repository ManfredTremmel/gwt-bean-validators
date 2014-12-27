package de.knightsoftnet.validators.client;

import de.knightsoftnet.validators.shared.Email;

import org.junit.Test;

public class EmailTest extends AbstractValidationTest<EmailTest.EmailTestBean> {

  public class EmailTestBean {

    @Email
    private final String email;

    public EmailTestBean(final String pemail) {
      super();
      this.email = pemail;
    }

    public String getEmail() {
      return this.email;
    }
  }

  /**
   * empty gln is allowed.
   */
  @Test
  public final void testEmptyEmailIsAllowed() {
    super.validationTest(new EmailTestBean(null), true, null);
  }

  /**
   * correct emails are allowed.
   */
  @Test
  public final void testCorrectEmailsAreAllowed() {
    super.validationTest(new EmailTestBean("jsmith@apache.org"), true, null);
    super.validationTest(new EmailTestBean("jsmith@apache.com"), true, null);
    super.validationTest(new EmailTestBean("jsmith@apache.net"), true, null);
    super.validationTest(new EmailTestBean("jsmith@apache.info"), true, null);
    super.validationTest(new EmailTestBean("someone@yahoo.museum"), true, null);
  }

  /**
   * wrong emails are not allowed.
   */
  @Test
  public final void testWrongEmailsAreWrong() {
    super.validationTest(new EmailTestBean("jsmith@apache."), false,
        "de.knightsoftnet.validators.shared.impl.EmailValidator");
    super.validationTest(new EmailTestBean("jsmith@apache.c"), false,
        "de.knightsoftnet.validators.shared.impl.EmailValidator");
    super.validationTest(new EmailTestBean("someone@yahoo.mu-seum"), false,
        "de.knightsoftnet.validators.shared.impl.EmailValidator");
  }
}
