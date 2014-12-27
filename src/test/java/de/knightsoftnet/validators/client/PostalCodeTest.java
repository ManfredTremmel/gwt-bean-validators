package de.knightsoftnet.validators.client;

import de.knightsoftnet.validators.shared.PostalCode;
import de.knightsoftnet.validators.shared.interfaces.HasGetFieldByName;

import org.junit.Test;

public class PostalCodeTest extends AbstractValidationTest<PostalCodeTest.PostalCodeTestBean> {

  @PostalCode
  public class PostalCodeTestBean implements HasGetFieldByName {

    private final String countryCode;

    private final String postalCode;

    /**
     * constructor initializing fields.
     *
     * @param pcountryCode country code
     * @param ppostalCode postal code
     */
    public PostalCodeTestBean(final String pcountryCode, final String ppostalCode) {
      super();
      this.countryCode = pcountryCode;
      this.postalCode = ppostalCode;
    }

    public String getCountryCode() {
      return this.countryCode;
    }

    public String getPostalCode() {
      return this.postalCode;
    }

    @Override
    public final Object getFieldByName(final String pname) {
      if (pname != null) {
        switch (pname) {
          case "countryCode":
            return this.countryCode;
          case "postalCode":
            return this.postalCode;
          default:
            return null;
        }
      }
      return null;
    }
  }

  /**
   * empty postal codes are allowed.
   */
  @Test
  public final void testEmptyPostalCodeIsAllowed() {
    super.validationTest(new PostalCodeTestBean(null, null), true, null);
  }

  /**
   * correct postal codes are allowed.
   */
  @Test
  public final void testCorrectPostalCodeIsAllowed() {
    super.validationTest(new PostalCodeTestBean("DE", "81925"), true, null);
    super.validationTest(new PostalCodeTestBean("AT", "1100"), true, null);
    super.validationTest(new PostalCodeTestBean("GB", "N1 2PN"), true, null);
    super.validationTest(new PostalCodeTestBean("US", "20001"), true, null);
  }

  /**
   * correct postal codes not matching country rules.
   */
  @Test
  public final void testWrongPostalCodeIsWrong() {
    super.validationTest(new PostalCodeTestBean("AT", "81925"), false,
        "de.knightsoftnet.validators.shared.impl.PostalCodeValidator");
    super.validationTest(new PostalCodeTestBean("US", "1100"), false,
        "de.knightsoftnet.validators.shared.impl.PostalCodeValidator");
    super.validationTest(new PostalCodeTestBean("DE", "N1 2PN"), false,
        "de.knightsoftnet.validators.shared.impl.PostalCodeValidator");
    super.validationTest(new PostalCodeTestBean("GB", "20001"), false,
        "de.knightsoftnet.validators.shared.impl.PostalCodeValidator");
  }
}
