package de.knightsoftnet.validators.shared;

import de.knightsoftnet.validators.shared.impl.AlternateSizeValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Check if a field's size has one of the two given sizes - interface.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
@Documented
@Constraint(validatedBy = AlternateSizeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
  ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface AlternateSize {
  /**
   * localized message.
   */
  String message() default "{deKnightsoftnetValidatorsSharedValidationAlternateSizeMessage}";

  /**
   * groups to use.
   */
  Class<?>[] groups() default {};

  /**
   * first possible size.
   */
  int size1();

  /**
   * second possible size.
   */
  int size2();

  /**
   * payload whatever.
   */
  Class<? extends Payload>[] payload() default {};

  /**
   * Defines several {@code AlternateSize} annotations on the same element.
   */
  @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
    ElementType.CONSTRUCTOR, ElementType.PARAMETER})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  public @interface List {
    /**
     * must be filled if other is empty value.
     */
    AlternateSize[] value();
  }
}
