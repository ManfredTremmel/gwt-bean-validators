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

import de.knightsoftnet.validators.shared.NotEmptyAlternateIfOtherIsNotEmpty;
import de.knightsoftnet.validators.shared.interfaces.HasGetFieldByName;

import org.junit.Test;

public class NotEmptyAlternateIfOtherIsNotEmptyTest extends
    AbstractValidationTest<NotEmptyAlternateIfOtherIsNotEmptyTest.TestBean> {

  @NotEmptyAlternateIfOtherIsNotEmpty(field = "vatIdentificationNumber",
      fieldAlternate = "taxIdentificationNumber", fieldCompare = "commercialNumber")
  public class TestBean implements HasGetFieldByName {

    private final String commercialNumber;

    private final String vatIdentificationNumber;

    private final String taxIdentificationNumber;

    /**
     * constructor initializing fields.
     *
     * @param pcommercialNumber number which is only filled by commercial users
     * @param pvatIdentificationNumber vat id to set
     * @param ptaxIdentificationNumber tax identification number to set
     */
    public TestBean(final String pcommercialNumber, final String pvatIdentificationNumber,
        final String ptaxIdentificationNumber) {
      super();
      this.commercialNumber = pcommercialNumber;
      this.vatIdentificationNumber = pvatIdentificationNumber;
      this.taxIdentificationNumber = ptaxIdentificationNumber;
    }

    public String getCommercialNumber() {
      return this.commercialNumber;
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
          case "commercialNumber":
            return this.commercialNumber;
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
   * the compare field is empty, alternate fields can be filled in every way.
   */
  @Test
  public final void testCompareIsEmptyAlternateEverythingIsAllowed() {
    super.validationTest(new TestBean(null, null, null), true, null);
    super.validationTest(new TestBean(null, "", null), true, null);
    super.validationTest(new TestBean(null, null, ""), true, null);
    super.validationTest(new TestBean(null, "", ""), true, null);
    super.validationTest(new TestBean(null, "filled", null), true, null);
    super.validationTest(new TestBean(null, null, "filled"), true, null);
    super.validationTest(new TestBean(null, "filled", "filled"), true, null);
    super.validationTest(new TestBean(null, "filled", ""), true, null);
    super.validationTest(new TestBean(null, "", "filled"), true, null);

    super.validationTest(new TestBean("", null, null), true, null);
    super.validationTest(new TestBean("", "", null), true, null);
    super.validationTest(new TestBean("", null, ""), true, null);
    super.validationTest(new TestBean("", "", ""), true, null);
    super.validationTest(new TestBean("", "filled", null), true, null);
    super.validationTest(new TestBean("", null, "filled"), true, null);
    super.validationTest(new TestBean("", "filled", "filled"), true, null);
    super.validationTest(new TestBean("", "filled", ""), true, null);
    super.validationTest(new TestBean("", "", "filled"), true, null);
  }

  /**
   * the compare field is filled, one of the alternate field (or both) have to be set.
   */
  @Test
  public final void testCompareIsFilledOneOfTheAlternateHasTobEeSetAllowed() {
    super.validationTest(new TestBean("filled", "filled", null), true, null);
    super.validationTest(new TestBean("filled", null, "filled"), true, null);
    super.validationTest(new TestBean("filled", "filled", "filled"), true, null);
    super.validationTest(new TestBean("filled", "filled", ""), true, null);
    super.validationTest(new TestBean("filled", "", "filled"), true, null);
  }

  /**
   * the compare field is set and both alternate fields are empty.
   */
  @Test
  public final void testCompareIsFilledBothAlternatesAreEmptyWrong() {
    super.validationTest(new TestBean("filled", null, null), false,
        "de.knightsoftnet.validators.shared.impl.NotEmptyAlternateIfOtherIsNotEmptyValidator");
    super.validationTest(new TestBean("filled", "", null), false,
        "de.knightsoftnet.validators.shared.impl.NotEmptyAlternateIfOtherIsNotEmptyValidator");
    super.validationTest(new TestBean("filled", null, ""), false,
        "de.knightsoftnet.validators.shared.impl.NotEmptyAlternateIfOtherIsNotEmptyValidator");
    super.validationTest(new TestBean("filled", "", ""), false,
        "de.knightsoftnet.validators.shared.impl.NotEmptyAlternateIfOtherIsNotEmptyValidator");
  }
}
