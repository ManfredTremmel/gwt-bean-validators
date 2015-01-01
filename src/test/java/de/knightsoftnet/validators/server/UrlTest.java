/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.knightsoftnet.validators.server;

import de.knightsoftnet.validators.shared.beans.UrlTestBean;

import org.junit.Test;

public class UrlTest extends AbstractValidationTest<UrlTestBean> {


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
