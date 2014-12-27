package de.knightsoftnet.validators.shared;

import de.knightsoftnet.validators.shared.impl.Isbn10FormatedValidator;
import de.knightsoftnet.validators.shared.impl.Isbn13FormatedValidator;
import de.knightsoftnet.validators.shared.impl.IsbnFormatedValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Check a string if it's a valid ISBN formated (10 or 13 digits long).
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
@Documented
@Constraint(validatedBy = IsbnFormatedValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
  ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@AlternateSize(size1 = Isbn10FormatedValidator.ISBN10_LENGTH,
size2 = Isbn13FormatedValidator.ISBN13_LENGTH)
public @interface IsbnFormated {
  /**
   * localized message.
   */
  String message() default "{deKnightsoftnetValidatorsSharedValidationIsbnFormatedMessage}";

  /**
   * groups to use.
   */
  Class<?>[] groups() default {};

  /**
   * payload whatever.
   */
  Class<? extends Payload>[] payload() default {};

  /**
   * Defines several {@code @ISBNFormated} annotations on the same element.
   */
  @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
    ElementType.CONSTRUCTOR, ElementType.PARAMETER})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  public @interface List {
    /**
     * email value.
     */
    IsbnFormated[] value();
  }
}
