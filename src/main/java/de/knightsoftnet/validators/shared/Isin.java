package de.knightsoftnet.validators.shared;

import de.knightsoftnet.validators.shared.impl.IsinValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;

/**
 * Check a string if it's a valid ISIN.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
@Documented
@Constraint(validatedBy = IsinValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
  ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Size(min = IsinValidator.ISIN_LENGTH, max = IsinValidator.ISIN_LENGTH)
public @interface Isin {
  /**
   * localized message.
   */
  String message() default "{deKnightsoftnetValidatorsSharedValidationIsinMessage}";

  /**
   * groups to use.
   */
  Class<?>[] groups() default {};

  /**
   * payload whatever.
   */
  Class<? extends Payload>[] payload() default {};

  /**
   * Defines several {@code @ISIN} annotations on the same element.
   */
  @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
    ElementType.CONSTRUCTOR, ElementType.PARAMETER})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  public @interface List {
    /**
     * email value.
     */
    Isin[] value();
  }
}
