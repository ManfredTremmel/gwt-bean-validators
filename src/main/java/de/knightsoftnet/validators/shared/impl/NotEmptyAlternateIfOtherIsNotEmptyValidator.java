package de.knightsoftnet.validators.shared.impl;

import de.knightsoftnet.validators.shared.NotEmptyAlternateIfOtherIsNotEmpty;
import de.knightsoftnet.validators.shared.interfaces.HasGetFieldByName;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check if a field is filled if another field is empty.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
public class NotEmptyAlternateIfOtherIsNotEmptyValidator implements
    ConstraintValidator<NotEmptyAlternateIfOtherIsNotEmpty, HasGetFieldByName> {

  /**
   * error message key.
   */
  private String message;
  /**
   * field name to check.
   */
  private String fieldCheckName;
  /**
   * alternate field name to check.
   */
  private String fieldAlternateCheckName;
  /**
   * field name to compare.
   */
  private String fieldCompareName;

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final NotEmptyAlternateIfOtherIsNotEmpty pconstraintAnnotation) {
    this.message = pconstraintAnnotation.message();
    this.fieldCheckName = pconstraintAnnotation.field();
    this.fieldCompareName = pconstraintAnnotation.fieldCompare();
    this.fieldAlternateCheckName = pconstraintAnnotation.fieldAlternate();
  }

  /**
   * {@inheritDoc} check if given object.
   *
   * @see javax.validation.ConstraintValidator#isValid(HasGetFieldByName,
   *      javax.validation.ConstraintValidatorContext)
   */
  @Override
  public final boolean isValid(final HasGetFieldByName pvalue,
      final ConstraintValidatorContext pcontext) {
    try {
      final String fieldCheckValue =
          Objects.toString(pvalue.getFieldByName(this.fieldCheckName), null);
      final String fieldAlternateCheckValue =
          Objects.toString(pvalue.getFieldByName(this.fieldAlternateCheckName), null);
      final String fieldCompareValue =
          Objects.toString(pvalue.getFieldByName(this.fieldCompareName), null);
      if (StringUtils.isNotEmpty(fieldCompareValue) && StringUtils.isEmpty(fieldCheckValue)
          && StringUtils.isEmpty(fieldAlternateCheckValue)) {
        pcontext.disableDefaultConstraintViolation();
        pcontext.buildConstraintViolationWithTemplate(this.message).addNode(this.fieldCheckName)
            .addConstraintViolation();
        pcontext.buildConstraintViolationWithTemplate(this.message)
            .addNode(this.fieldAlternateCheckName).addConstraintViolation();
        return false;
      }
      return true;
    } catch (final Exception ignore) {
      return false;
    }
  }
}
