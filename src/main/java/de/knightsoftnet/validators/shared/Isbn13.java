package de.knightsoftnet.validators.shared;

import de.knightsoftnet.validators.shared.impl.Isbn13Validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

/**
 * Check a string if it's a valid ISBN13.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
@Documented
@Constraint(validatedBy = Isbn13Validator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
  ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Size(min = Isbn13Validator.ISBN13_LENGTH)
@Digits(integer = Isbn13Validator.ISBN13_LENGTH, fraction = 0)
public @interface Isbn13 {
  /**
   * localized message.
   */
  String message() default "{deKnightsoftnetValidatorsSharedValidationIsbn13Message}";

  /**
   * groups to use.
   */
  Class<?>[] groups() default {};

  /**
   * payload whatever.
   */
  Class<? extends Payload>[] payload() default {};

  /**
   * Defines several {@code @ISBN13} annotations on the same element.
   */
  @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
    ElementType.CONSTRUCTOR, ElementType.PARAMETER})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  public @interface List {
    /**
     * email value.
     */
    Isbn13[] value();
  }
}
