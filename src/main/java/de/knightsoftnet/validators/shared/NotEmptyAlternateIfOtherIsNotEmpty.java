package de.knightsoftnet.validators.shared;

import de.knightsoftnet.validators.shared.impl.NotEmptyAlternateIfOtherIsNotEmptyValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Check if a field is filled if another field is filled.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
@Documented
@Constraint(validatedBy = NotEmptyAlternateIfOtherIsNotEmptyValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmptyAlternateIfOtherIsNotEmpty {
  /**
   * localized message.
   */
  String message() default "{deKnightsoftnetValidatorsSharedValidationNotEmptyAlternateIfOther"
      + "IsNotEmptyMessage}";

  /**
   * groups to use.
   */
  Class<?>[] groups() default {};

  /**
   * field name to check.
   */
  String field();

  /**
   * alternate field name to check.
   */
  String fieldAlternate();

  /**
   * field name to compare.
   */
  String fieldCompare();

  /**
   * payload whatever.
   */
  Class<? extends Payload>[] payload() default {};

  /**
   * Defines several {@code MustBeAlternateFilledIfOtherIsFilled} annotations on the same element.
   */
  @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  public @interface List {
    /**
     * must be alternate filled if other is filled value.
     */
    NotEmptyAlternateIfOtherIsNotEmpty[] value();
  }
}
