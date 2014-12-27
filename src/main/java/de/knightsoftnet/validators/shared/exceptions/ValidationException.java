package de.knightsoftnet.validators.shared.exceptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

import javax.validation.ConstraintViolation;

/**
 * The ValidationException is thrown, when a argument is not valid by bean validation.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
public class ValidationException extends Exception implements Serializable {
  /**
   * serial version unique id.
   */
  private static final long serialVersionUID = 2524551230822310527L;

  /**
   * validation error set.
   */
  private ArrayList<ConstraintViolation<?>> validationErrorSet;

  /**
   * default constructor.
   */
  public ValidationException() {
    super();
    this.validationErrorSet = new ArrayList<ConstraintViolation<?>>(1);
  }

  /**
   * constructor adding a set of validation errors.
   *
   * @param pValidationErrorSet a set of validation errors
   */
  @SuppressWarnings("rawtypes")
  public ValidationException(final Set pvalidationErrorSet) {
    super();
    this.setValidationErrorSet(pvalidationErrorSet);
  }

  /**
   * constructor adding a message text.
   *
   * @param pmessage message to add to the exception
   */
  public ValidationException(final String pmessage) {
    super(pmessage);
    this.validationErrorSet = new ArrayList<ConstraintViolation<?>>(1);
  }

  /**
   * constructor adding a text and another exception/throwable to the exception.
   *
   * @param pmessage message to add to the exception
   * @param pvalidationErrorSet a set of validation errors
   */
  @SuppressWarnings("rawtypes")
  public ValidationException(final String pmessage, final Set pvalidationErrorSet) {
    super(pmessage);
    this.setValidationErrorSet(pvalidationErrorSet);
  }

  /**
   * get validation error set.
   *
   * @return the validationErrorSet
   */
  public final ArrayList<ConstraintViolation<?>> getValidationErrorSet() {
    return this.validationErrorSet;
  }

  /**
   * set validation error set.
   * 
   * @param pvalidationErrorSet the validationErrorSet to set
   */
  @SuppressWarnings({"rawtypes", "unchecked"})
  public final void setValidationErrorSet(final Set pvalidationErrorSet) {
    this.validationErrorSet = new ArrayList<ConstraintViolation<?>>(pvalidationErrorSet);
  }

  /**
   * set validation error set.
   * 
   * @param pvalidationErrorSet the validationErrorSet to set
   */
  public final void setValidationErrorSet(
      final ArrayList<ConstraintViolation<?>> pvalidationErrorSet) {
    this.validationErrorSet = pvalidationErrorSet;
  }
}
