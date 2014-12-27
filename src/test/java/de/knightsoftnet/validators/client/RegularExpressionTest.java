package de.knightsoftnet.validators.client;

import de.knightsoftnet.validators.shared.RegularExpression;

import org.junit.Test;

public class RegularExpressionTest extends
    AbstractValidationTest<RegularExpressionTest.RegularExpressionTestBean> {

  public class RegularExpressionTestBean {

    @RegularExpression
    private final String regEx;

    public RegularExpressionTestBean(final String pregEx) {
      super();
      this.regEx = pregEx;
    }

    public String getRegEx() {
      return this.regEx;
    }
  }

  /**
   * empty regular expression is allowed.
   */
  @Test
  public final void testEmptyRecExIsAllowed() {
    super.validationTest(new RegularExpressionTestBean(null), true, null);
  }

  /**
   * correct regular expressions are allowed.
   */
  @Test
  public final void testCorrectRegExAreAllowed() {
    super.validationTest(new RegularExpressionTestBean(
        "^\\$(\\d{1,3}(\\,\\d{3})*|(\\d+))(\\.\\d{2})?$"), true, null);
    super.validationTest(new RegularExpressionTestBean(
        ".*(\\.[Jj][Pp][Gg]|\\.[Gg][Ii][Ff]|\\.[Jj][Pp][Ee][Gg]|\\.[Pp][Nn][Gg])"), true, null);
    super.validationTest(new RegularExpressionTestBean("^([a-zA-Z0-9]+(?: [a-zA-Z0-9]+)*)$"), true,
        null);
    super.validationTest(new RegularExpressionTestBean(
        "^http[s]?://twitter\\.com/(#!/)?[a-zA-Z0-9]{1,15}[/]?$"), true, null);
    super.validationTest(new RegularExpressionTestBean("((EE|EL|DE|PT)-?)?[0-9]{9}"), true, null);
    super.validationTest(new RegularExpressionTestBean("^((\\-|d|l|p|s){1}(\\-|r|w|x){9})$"), true,
        null);
  }

  /**
   * wrong regular expressions are not allowed.
   */
  @Test
  public final void testWrongRegExAreWrong() {
    super.validationTest(new RegularExpressionTestBean(
        "^\\$(\\d{1,3}(\\,\\d{3})*|(\\d+))(\\.\\d{d2})?$"), false,
        "de.knightsoftnet.validators.shared.impl.RegularExpressionValidator");
    super.validationTest(new RegularExpressionTestBean(
        ".*\\.[Jj][Pp][Gg]|\\.[Gg][Ii][Ff]|\\.[Jj][Pp][Ee][Gg]|\\.[Pp][Nn][Gg])"), false,
        "de.knightsoftnet.validators.shared.impl.RegularExpressionValidator");
    super.validationTest(new RegularExpressionTestBean("^([a-zA-Z0-9+(?: [a-zA-Z0-9]+)*)$"), false,
        "de.knightsoftnet.validators.shared.impl.RegularExpressionValidator");
    super.validationTest(new RegularExpressionTestBean(
        "^http[s]?://twitter\\.com/(#!/)?[a-zA-Z0-9]{1,a5}[/]?$"), false,
        "de.knightsoftnet.validators.shared.impl.RegularExpressionValidator");
    super.validationTest(new RegularExpressionTestBean("((EE|EL|DE|PT)-?)?[0-9]{9"), false,
        "de.knightsoftnet.validators.shared.impl.RegularExpressionValidator");
    super.validationTest(new RegularExpressionTestBean("^((\\-|d|l|p|s){1}(\\-|r|w|\\x){9})$"),
        false, "de.knightsoftnet.validators.shared.impl.RegularExpressionValidator");
  }
}
