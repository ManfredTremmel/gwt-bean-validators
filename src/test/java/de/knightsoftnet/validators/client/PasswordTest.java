package de.knightsoftnet.validators.client;

import de.knightsoftnet.validators.shared.Password;

import org.junit.Test;

public class PasswordTest extends AbstractValidationTest<PasswordTest.PasswordTestBean> {

  public class PasswordTestBean {

    @Password(minRules = 2)
    private final String password;

    public PasswordTestBean(final String ppassword) {
      super();
      this.password = ppassword;
    }

    public String getPassword() {
      return this.password;
    }
  }

  /**
   * empty gln is allowed.
   */
  @Test
  public final void testEmptyPasswordIsAllowed() {
    super.validationTest(new PasswordTestBean(null), true, null);
  }

  /**
   * correct passwords are allowed.
   */
  @Test
  public final void testCorrectPasswordsAreAllowed() {
    super.validationTest(new PasswordTestBean("Test123"), true, null);
    super.validationTest(new PasswordTestBean("Password!"), true, null);
    super.validationTest(new PasswordTestBean("1password%"), true, null);
  }

  /**
   * wrong passwords are not allowed.
   */
  @Test
  public final void testWrongPasswordsAreWrong() {
    super.validationTest(new PasswordTestBean("test"), false,
        "de.knightsoftnet.validators.shared.impl.PasswordValidator");
    super.validationTest(new PasswordTestBean("test123"), false,
        "de.knightsoftnet.validators.shared.impl.PasswordValidator");
    super.validationTest(new PasswordTestBean("müller"), false,
        "de.knightsoftnet.validators.shared.impl.PasswordValidator");
  }
}
