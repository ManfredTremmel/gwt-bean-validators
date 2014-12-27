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

package de.knightsoftnet.validators.client;

import de.knightsoftnet.validators.shared.EmptyIfOtherIsEmpty;
import de.knightsoftnet.validators.shared.interfaces.HasGetFieldByName;

import org.junit.Test;

public class EmptyIfOtherIsEmptyTest extends
    AbstractValidationTest<EmptyIfOtherIsEmptyTest.TestBean> {

  @EmptyIfOtherIsEmpty(field = "streetNumber", fieldCompare = "street")
  public class TestBean implements HasGetFieldByName {

    private final String street;

    private final String streetNumber;

    /**
     * constructor initializing fields.
     *
     * @param pstreet street to set
     * @param pstreetNumber street number to set
     */
    public TestBean(final String pstreet, final String pstreetNumber) {
      super();
      this.street = pstreet;
      this.streetNumber = pstreetNumber;
    }

    public String getStreet() {
      return this.street;
    }

    public String getStreetNumber() {
      return this.streetNumber;
    }

    @Override
    public final Object getFieldByName(final String pname) {
      if (pname != null) {
        switch (pname) {
          case "street":
            return this.street;
          case "streetNumber":
            return this.streetNumber;
          default:
            return null;
        }
      }
      return null;
    }
  }

  /**
   * both fields are not set, so it's ok.
   */
  @Test
  public final void testNoStreetNoNumberIsAllowed() {
    super.validationTest(new TestBean(null, null), true, null);
    super.validationTest(new TestBean(null, ""), true, null);
    super.validationTest(new TestBean("", null), true, null);
    super.validationTest(new TestBean("", ""), true, null);
  }

  /**
   * street is set, so street number can be what ever it wants.
   */
  @Test
  public final void testStreetFlexibleNumberIsAllowed() {
    super.validationTest(new TestBean("filled", null), true, null);
    super.validationTest(new TestBean("filled", ""), true, null);
    super.validationTest(new TestBean("filled", "filled"), true, null);
  }

  /**
   * street number is set, but no street, that's wrong.
   */
  @Test
  public final void testNumberWithoutStreetWrong() {
    super.validationTest(new TestBean(null, "filled"), false,
        "de.knightsoftnet.validators.shared.impl.EmptyIfOtherIsEmptyValidator");
    super.validationTest(new TestBean("", "filled"), false,
        "de.knightsoftnet.validators.shared.impl.EmptyIfOtherIsEmptyValidator");
  }
}
