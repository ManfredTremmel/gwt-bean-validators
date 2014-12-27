package de.knightsoftnet.validators.shared;

import de.knightsoftnet.validators.shared.impl.Gtin13Validator;

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
 * Check a string if it's a valid GTIN13/EAN13.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
@Documented
@Constraint(validatedBy = Gtin13Validator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
  ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Size(min = Gtin13Validator.GTIN13_LENGTH)
@Digits(integer = Gtin13Validator.GTIN13_LENGTH, fraction = 0)
public @interface Gtin13 {
  /**
   * localized message.
   */
  String message() default "{deKnightsoftnetValidatorsSharedValidationGtin13Message}";

  /**
   * groups to use.
   */
  Class<?>[] groups() default {};

  /**
   * payload whatever.
   */
  Class<? extends Payload>[] payload() default {};

  /**
   * Defines several {@code @GTIN13} annotations on the same element.
   */
  @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
    ElementType.CONSTRUCTOR, ElementType.PARAMETER})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  public @interface List {
    /**
     * email value.
     */
    Gtin13[] value();
  }
}
