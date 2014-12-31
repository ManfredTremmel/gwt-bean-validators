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

import de.knightsoftnet.validators.shared.RegularExpressionTestBean;

import org.junit.Test;

public class RegularExpressionTest extends AbstractValidationTest<RegularExpressionTestBean> {


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
