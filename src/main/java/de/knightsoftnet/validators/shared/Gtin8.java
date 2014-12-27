package de.knightsoftnet.validators.shared;

import de.knightsoftnet.validators.shared.impl.Gtin8Validator;

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
 * Check a string if it's a valid GTIN8/EAN8.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
@Documented
@Constraint(validatedBy = Gtin8Validator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
  ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Size(min = Gtin8Validator.GTIN8_LENGTH)
@Digits(integer = Gtin8Validator.GTIN8_LENGTH, fraction = 0)
public @interface Gtin8 {
  /**
   * localized message.
   */
  String message() default "{deKnightsoftnetValidatorsSharedValidationGtin8Message}";

  /**
   * groups to use.
   */
  Class<?>[] groups() default {};

  /**
   * payload whatever.
   */
  Class<? extends Payload>[] payload() default {};

  /**
   * Defines several {@code @GTIN8} annotations on the same element.
   */
  @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
    ElementType.CONSTRUCTOR, ElementType.PARAMETER})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  public @interface List {
    /**
     * email value.
     */
    Gtin8[] value();
  }
}
