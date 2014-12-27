package de.knightsoftnet.validators.client;

import de.knightsoftnet.validators.shared.Url;

import org.junit.Test;

public class UrlTest extends AbstractValidationTest<UrlTest.UrlTestBean> {

  public class UrlTestBean {

    @Url
    private final String url;

    public UrlTestBean(final String purl) {
      super();
      this.url = purl;
    }

    public String getUrl() {
      return this.url;
    }
  }

  /**
   * empty url is allowed.
   */
  @Test
  public final void testEmptyUrlIsAllowed() {
    super.validationTest(new UrlTestBean(null), true, null);
  }

  /**
   * correct urls are allowed.
   */
  @Test
  public final void testCorrectUrlsAreAllowed() {
    super.validationTest(new UrlTestBean("http://www.google.com"), true, null);
    super.validationTest(new UrlTestBean("http://www.google.com/"), true, null);
    super.validationTest(new UrlTestBean("http://tech.yahoo.com/rc/desktops/102;_ylt="
        + "Ao8yevQHlZ4On0O3ZJGXLEQFLZA5"), true, null);
    super.validationTest(new UrlTestBean("https://blah.gov/blah-blah.as"), true, null);
  }

  /**
   * wrong urls are not allowed.
   */
  @Test
  public final void testWrongEmailsAreWrong() {
    super.validationTest(new UrlTestBean("tz:\\temp\\ fi*le?na:m<e>.doc"), false,
        "de.knightsoftnet.validators.shared.impl.UrlValidator");
    super.validationTest(new UrlTestBean("http://www.blah&quot;blah.com/I have spaces!"), false,
        "de.knightsoftnet.validators.shared.impl.UrlValidator");
    super.validationTest(new UrlTestBean("(http://www.krumedia.com)"), false,
        "de.knightsoftnet.validators.shared.impl.UrlValidator");
  }
}
