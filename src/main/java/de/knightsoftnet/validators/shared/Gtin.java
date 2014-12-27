package de.knightsoftnet.validators.shared;

import de.knightsoftnet.validators.shared.impl.Gtin13Validator;
import de.knightsoftnet.validators.shared.impl.Gtin8Validator;
import de.knightsoftnet.validators.shared.impl.GtinValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Digits;

/**
 * Check a string if it's a valid GTIN/EAN.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
@Documented
@Constraint(validatedBy = GtinValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
  ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@AlternateSize(size1 = Gtin8Validator.GTIN8_LENGTH, size2 = Gtin13Validator.GTIN13_LENGTH)
@Digits(integer = Gtin13Validator.GTIN13_LENGTH, fraction = 0)
public @interface Gtin {
  /**
   * localized message.
   */
  String message() default "{deKnightsoftnetValidatorsSharedValidationGtinMessage}";

  /**
   * groups to use.
   */
  Class<?>[] groups() default {};

  /**
   * payload whatever.
   */
  Class<? extends Payload>[] payload() default {};

  /**
   * Defines several {@code @GTIN} annotations on the same element.
   */
  @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
    ElementType.CONSTRUCTOR, ElementType.PARAMETER})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  public @interface List {
    /**
     * email value.
     */
    Gtin[] value();
  }
}
