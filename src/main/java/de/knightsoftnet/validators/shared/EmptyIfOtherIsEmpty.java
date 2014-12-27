package de.knightsoftnet.validators.shared;

import de.knightsoftnet.validators.shared.impl.EmptyIfOtherIsEmptyValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Check if a field is empty if another field is empty.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
@Documented
@Constraint(validatedBy = EmptyIfOtherIsEmptyValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmptyIfOtherIsEmpty {
  /**
   * localized message.
   */
  String message() default "{deKnightsoftnetValidatorsSharedValidationEmptyIfOtherIsEmptyMessage}";

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
   * payload whatever.
   */
  Class<? extends Payload>[] payload() default {};

  /**
   * Defines several {@code MustBeEmptyIfOtherIsEmpty} annotations on the same element.
   */
  @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  public @interface List {
    /**
     * must be filled if other is empty value.
     */
    EmptyIfOtherIsEmpty[] value();
  }
}
