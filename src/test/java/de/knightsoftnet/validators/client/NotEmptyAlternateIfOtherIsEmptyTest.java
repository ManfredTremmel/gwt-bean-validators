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

import de.knightsoftnet.validators.shared.NotEmptyAlternateIfOtherIsEmpty;
import de.knightsoftnet.validators.shared.interfaces.HasGetFieldByName;

import org.junit.Test;

public class NotEmptyAlternateIfOtherIsEmptyTest extends
    AbstractValidationTest<NotEmptyAlternateIfOtherIsEmptyTest.TestBean> {

  @NotEmptyAlternateIfOtherIsEmpty(field = "vatIdentificationNumber",
      fieldAlternate = "taxIdentificationNumber", fieldCompare = "privatNumber")
  public class TestBean implements HasGetFieldByName {

    private final String privatNumber;

    private final String vatIdentificationNumber;

    private final String taxIdentificationNumber;

    /**
     * constructor initializing fields.
     *
     * @param pprivatNumber a number which is only set when user is no company
     * @param pvatIdentificationNumber vat id to set
     * @param ptaxIdentificationNumber tax identification number to set
     */
    public TestBean(final String pprivatNumber, final String pvatIdentificationNumber,
        final String ptaxIdentificationNumber) {
      super();
      this.privatNumber = pprivatNumber;
      this.vatIdentificationNumber = pvatIdentificationNumber;
      this.taxIdentificationNumber = ptaxIdentificationNumber;
    }

    public String getPrivatNumber() {
      return this.privatNumber;
    }

    public String getVatIdentificationNumber() {
      return this.vatIdentificationNumber;
    }

    public String getTaxIdentificationNumber() {
      return this.taxIdentificationNumber;
    }

    @Override
    public final Object getFieldByName(final String pname) {
      if (pname != null) {
        switch (pname) {
          case "privatNumber":
            return this.privatNumber;
          case "vatIdentificationNumber":
            return this.vatIdentificationNumber;
          case "taxIdentificationNumber":
            return this.taxIdentificationNumber;
          default:
            return null;
        }
      }
      return null;
    }
  }

  /**
   * the compare field is set, alternate fields can be filled in every way.
   */
  @Test
  public final void testCompareIsSetAlternateEverythingIsAllowed() {
    super.validationTest(new TestBean("filled", null, null), true, null);
    super.validationTest(new TestBean("filled", "", null), true, null);
    super.validationTest(new TestBean("filled", null, ""), true, null);
    super.validationTest(new TestBean("filled", "", ""), true, null);
    super.validationTest(new TestBean("filled", "filled", null), true, null);
    super.validationTest(new TestBean("filled", null, "filled"), true, null);
    super.validationTest(new TestBean("filled", "filled", "filled"), true, null);
    super.validationTest(new TestBean("filled", "filled", ""), true, null);
    super.validationTest(new TestBean("filled", "", "filled"), true, null);
  }

  /**
   * the compare field is empty, one of the alternate field (or both) have to be set.
   */
  @Test
  public final void testCompareIsEmptyOneOfTheAlternateHasTobEeSetAllowed() {
    super.validationTest(new TestBean(null, "filled", null), true, null);
    super.validationTest(new TestBean(null, null, "filled"), true, null);
    super.validationTest(new TestBean(null, "filled", "filled"), true, null);
    super.validationTest(new TestBean(null, "filled", ""), true, null);
    super.validationTest(new TestBean(null, "", "filled"), true, null);

    super.validationTest(new TestBean("", "filled", null), true, null);
    super.validationTest(new TestBean("", null, "filled"), true, null);
    super.validationTest(new TestBean("", "filled", "filled"), true, null);
    super.validationTest(new TestBean("", "filled", ""), true, null);
    super.validationTest(new TestBean("", "", "filled"), true, null);
  }

  /**
   * the compare field is empty and both alternate fields are empty.
   */
  @Test
  public final void testCompareIsEmptyBothAlternatesAreEmptyWrong() {
    super.validationTest(new TestBean(null, null, null), false,
        "de.knightsoftnet.validators.shared.impl.NotEmptyAlternateIfOtherIsEmptyValidator");
    super.validationTest(new TestBean(null, "", null), false,
        "de.knightsoftnet.validators.shared.impl.NotEmptyAlternateIfOtherIsEmptyValidator");
    super.validationTest(new TestBean(null, null, ""), false,
        "de.knightsoftnet.validators.shared.impl.NotEmptyAlternateIfOtherIsEmptyValidator");
    super.validationTest(new TestBean(null, "", ""), false,
        "de.knightsoftnet.validators.shared.impl.NotEmptyAlternateIfOtherIsEmptyValidator");

    super.validationTest(new TestBean("", null, null), false,
        "de.knightsoftnet.validators.shared.impl.NotEmptyAlternateIfOtherIsEmptyValidator");
    super.validationTest(new TestBean("", "", null), false,
        "de.knightsoftnet.validators.shared.impl.NotEmptyAlternateIfOtherIsEmptyValidator");
    super.validationTest(new TestBean("", null, ""), false,
        "de.knightsoftnet.validators.shared.impl.NotEmptyAlternateIfOtherIsEmptyValidator");
    super.validationTest(new TestBean("", "", ""), false,
        "de.knightsoftnet.validators.shared.impl.NotEmptyAlternateIfOtherIsEmptyValidator");
  }
}