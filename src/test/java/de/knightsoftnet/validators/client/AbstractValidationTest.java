package de.knightsoftnet.validators.client;

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
      Assert.assertTrue("Should have no validation error", cv1.isEmpty());
    } else {
      Assert.assertFalse("Should have a validation error", cv1.isEmpty());
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
