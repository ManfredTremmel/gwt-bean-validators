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

import org.apache.log4j.Logger;
import org.junit.Assert;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 * Abstract validation test.
 *
 * @author Manfred Tremmel
 *
 * @param <E> type of bean to test
 */
public class AbstractValidationTest<E> {

  /**
   * logger for logging messages.
   */
  private static final Logger LOG = Logger.getLogger(AbstractValidationTest.class);

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
      Assert.assertTrue("Should have no validation error " + pbean.toString(), cv1.isEmpty());
    } else {
      Assert.assertFalse("Should have a validation error " + pbean.toString(), cv1.isEmpty());
    }
    for (final ConstraintViolation<E> violation : cv1) {
      Assert.assertEquals("Should be reported by special validator", pexcpetedValidationClass,
          violation.getConstraintDescriptor().getConstraintValidatorClasses().get(0).getName());
      LOG.debug("Error Message of type "
          + violation.getConstraintDescriptor().getConstraintValidatorClasses() + " for field \""
          + violation.getPropertyPath().toString() + "\" with value \"" + pbean.toString()
          + "\", message: " + violation.getMessage());
    }
  }
}
