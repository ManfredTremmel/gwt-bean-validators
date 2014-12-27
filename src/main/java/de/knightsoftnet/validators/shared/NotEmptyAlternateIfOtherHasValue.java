package de.knightsoftnet.validators.shared;

import de.knightsoftnet.validators.shared.impl.NotEmptyAlternateIfOtherHasValueValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Check if two other fields are alternatively filled if another field has a specified value.
 *
 * @author Manfred Tremmel
 *
 */
@Documented
@Constraint(validatedBy = NotEmptyAlternateIfOtherHasValueValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmptyAlternateIfOtherHasValue {
  /**
   * localized message.
   */
  String message() default "{deKnightsoftnetValidatorsSharedValidationNotEmptyAlternateIfOther"
      + "HasValueMessage}";

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
   * value the compare field must have to check.
   */
  String valueCompare();

  /**
   * payload whatever.
   */
  Class<? extends Payload>[] payload() default {};

  /**
   * Defines several {@code MustBeAlternateFilledIfOtherHasValue} annotations on the same element.
   */
  @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  public @interface List {
    /**
     * must be alternate filled if other is empty value.
     */
    NotEmptyAlternateIfOtherHasValue[] value();
  }
}
