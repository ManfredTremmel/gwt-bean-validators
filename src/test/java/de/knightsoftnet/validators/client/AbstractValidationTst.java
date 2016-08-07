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

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 * GWT JUnit <b>integration</b> tests must extend GWTTestCase. Abstract validation test.
 *
 * @author Manfred Tremmel
 *
 * @param <E> type of bean to test
 */
public abstract class AbstractValidationTst<E> extends GWTTestCase {

  protected static final String SIZE_VALIDATOR = "org.hibernate.validator.internal."
      + "constraintvalidators.bv.size.SizeValidatorForCharSequence";
  protected static final String DIGITS_VALIDATOR = "org.hibernate.validator.internal."
      + "constraintvalidators.bv.DigitsValidatorForCharSequence";

  /**
   * Must refer to a valid module that sources this class.
   */
  @Override
  public String getModuleName() {
    return "de.knightsoftnet.validators.GwtBeanValidatorsJUnit";
  }

  /**
   * test validation.
   *
   * @param pbean the bean to test
   * @param pshouldBeOk true if it's expected, that the test brings no validation error
   * @param pexcpetedValidationClass the validator class that will report an error
   */
  public final void validationTest(final E pbean, final boolean pshouldBeOk,
      final String pexcpetedValidationClass) {
    final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    final Set<ConstraintViolation<E>> cv1 = validator.validate(pbean);

    if (pshouldBeOk) {
      assertTrue("Should have no validation error " + pbean.toString(), cv1.isEmpty());
    } else {
      assertFalse("Should have a validation error " + pbean.toString(), cv1.isEmpty());
    }
    for (final ConstraintViolation<E> violation : cv1) {
      if (violation.getConstraintDescriptor().getConstraintValidatorClasses() != null
          && !violation.getConstraintDescriptor().getConstraintValidatorClasses().isEmpty()) {
        assertEquals("Should be reported by special validator", pexcpetedValidationClass,
            violation.getConstraintDescriptor().getConstraintValidatorClasses().get(0).getName());
      }
      GWT.log("Error Message of type "
          + violation.getConstraintDescriptor().getConstraintValidatorClasses() + " for field \""
          + violation.getPropertyPath().toString() + "\" with value \"" + pbean.toString()
          + "\", message: " + violation.getMessage());
    }
  }
}
