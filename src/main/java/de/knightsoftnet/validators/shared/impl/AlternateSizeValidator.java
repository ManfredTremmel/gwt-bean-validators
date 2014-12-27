package de.knightsoftnet.validators.shared.impl;

import de.knightsoftnet.validators.shared.AlternateSize;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check if a field's size has one of the two given sizes.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
public class AlternateSizeValidator implements ConstraintValidator<AlternateSize, Object> {
  /**
   * first possible size.
   */
  private int size1;
  /**
   * second possible size.
   */
  private int size2;

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final AlternateSize pconstraintAnnotation) {
    this.size1 = pconstraintAnnotation.size1();
    this.size2 = pconstraintAnnotation.size2();
  }

  /**
   * {@inheritDoc} check if given object.
   *
   * @see javax.validation.ConstraintValidator#isValid(Object,
   *      javax.validation.ConstraintValidatorContext)
   */
  @Override
  public final boolean isValid(final Object pvalue, final ConstraintValidatorContext pcontext) {
    final String valueAsString = ObjectUtils.toString(pvalue);
    if (StringUtils.isEmpty(valueAsString)) {
      return true;
    }
    return StringUtils.length(valueAsString) == this.size1
        || StringUtils.length(valueAsString) == this.size2;
  }
}
