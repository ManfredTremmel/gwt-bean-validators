package de.knightsoftnet.validators.shared;

import de.knightsoftnet.validators.shared.impl.EmptyIfOtherHasValueValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Check if a field is empty if another field has a given value.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
@Documented
@Constraint(validatedBy = EmptyIfOtherHasValueValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmptyIfOtherHasValue {
  /**
   * localized message.
   */
  String message() default "{deKnightsoftnetValidatorsSharedValidationEmptyIfOtherHasValueMessage}";

  /**
   * groups to use.
   */
  Class<?>[] groups() default {};

  /**
   * field name to check.
   */
  String field();

  /**
   * field name to compare.
   */
  String fieldCompare();

  /**
   * value to compare with field name to compare.
   */
  String valueCompare();

  /**
   * payload whatever.
   */
  Class<? extends Payload>[] payload() default {};

  /**
   * Defines several {@code @MustBeEmptyIfOtherHasValue} annotations on the same element.
   */
  @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  public @interface List {
    /**
     * must be empty if other has the given value.
     */
    EmptyIfOtherHasValue[] value();
  }
}
