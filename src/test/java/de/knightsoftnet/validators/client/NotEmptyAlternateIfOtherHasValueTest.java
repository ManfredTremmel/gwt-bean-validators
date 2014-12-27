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

import de.knightsoftnet.validators.shared.NotEmptyAlternateIfOtherHasValue;
import de.knightsoftnet.validators.shared.interfaces.HasGetFieldByName;

import org.junit.Test;

public class NotEmptyAlternateIfOtherHasValueTest extends
    AbstractValidationTest<NotEmptyAlternateIfOtherHasValueTest.TestBean> {

  @NotEmptyAlternateIfOtherHasValue(field = "vatIdentificationNumber",
      fieldAlternate = "taxIdentificationNumber", fieldCompare = "commercial",
      valueCompare = "true")
  public class TestBean implements HasGetFieldByName {

    private final Boolean commercial;

    private final String vatIdentificationNumber;

    private final String taxIdentificationNumber;

    /**
     * constructor initializing fields.
     *
     * @param pcommercial inidicator private/commercial
     * @param pvatIdentificationNumber vat id to set
     * @param ptaxIdentificationNumber tax identification number to set
     */
    public TestBean(final Boolean pcommercial, final String pvatIdentificationNumber,
        final String ptaxIdentificationNumber) {
      super();
      this.commercial = pcommercial;
      this.vatIdentificationNumber = pvatIdentificationNumber;
      this.taxIdentificationNumber = ptaxIdentificationNumber;
    }

    public Boolean getCommercial() {
      return this.commercial;
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
          case "commercial":
            return this.commercial;
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
   * the value we request is not set, so everything is allowed.
   */
  @Test
  public final void testValueIsNotSetEverythingIsAllowed() {
    super.validationTest(new TestBean(null, null, null), true, null);
    super.validationTest(new TestBean(null, "", null), true, null);
    super.validationTest(new TestBean(null, null, ""), true, null);
    super.validationTest(new TestBean(null, "", ""), true, null);
    super.validationTest(new TestBean(null, "filled", null), true, null);
    super.validationTest(new TestBean(null, null, "filled"), true, null);
    super.validationTest(new TestBean(null, "filled", "filled"), true, null);
    super.validationTest(new TestBean(null, "filled", ""), true, null);
    super.validationTest(new TestBean(null, "", "filled"), true, null);
    super.validationTest(new TestBean(null, "filled", "filled"), true, null);

    super.validationTest(new TestBean(Boolean.FALSE, null, null), true, null);
    super.validationTest(new TestBean(Boolean.FALSE, "", null), true, null);
    super.validationTest(new TestBean(Boolean.FALSE, null, ""), true, null);
    super.validationTest(new TestBean(Boolean.FALSE, "", ""), true, null);
    super.validationTest(new TestBean(Boolean.FALSE, "filled", null), true, null);
    super.validationTest(new TestBean(Boolean.FALSE, null, "filled"), true, null);
    super.validationTest(new TestBean(Boolean.FALSE, "filled", "filled"), true, null);
    super.validationTest(new TestBean(Boolean.FALSE, "filled", ""), true, null);
    super.validationTest(new TestBean(Boolean.FALSE, "", "filled"), true, null);
    super.validationTest(new TestBean(Boolean.FALSE, "filled", "filled"), true, null);
  }

  /**
   * the value we request is set, and alternate fields match.
   */
  @Test
  public final void testValueIsSetFieldsToAllowed() {
    super.validationTest(new TestBean(Boolean.TRUE, "filled", null), true, null);
    super.validationTest(new TestBean(Boolean.TRUE, null, "filled"), true, null);
    super.validationTest(new TestBean(Boolean.TRUE, "filled", "filled"), true, null);
    super.validationTest(new TestBean(Boolean.TRUE, "filled", ""), true, null);
    super.validationTest(new TestBean(Boolean.TRUE, "", "filled"), true, null);
    super.validationTest(new TestBean(Boolean.TRUE, "filled", "filled"), true, null);
  }

  /**
   * the value we request is set, and alternate fields match.
   */
  @Test
  public final void testValueIsSetFieldsNotWrong() {
    super.validationTest(new TestBean(Boolean.TRUE, null, null), false,
        "de.knightsoftnet.validators.shared.impl.NotEmptyAlternateIfOtherHasValueValidator");
    super.validationTest(new TestBean(Boolean.TRUE, null, ""), false,
        "de.knightsoftnet.validators.shared.impl.NotEmptyAlternateIfOtherHasValueValidator");
    super.validationTest(new TestBean(Boolean.TRUE, "", null), false,
        "de.knightsoftnet.validators.shared.impl.NotEmptyAlternateIfOtherHasValueValidator");
    super.validationTest(new TestBean(Boolean.TRUE, "", ""), false,
        "de.knightsoftnet.validators.shared.impl.NotEmptyAlternateIfOtherHasValueValidator");
  }
}
