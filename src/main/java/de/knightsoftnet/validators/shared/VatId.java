package de.knightsoftnet.validators.shared;

import de.knightsoftnet.validators.shared.impl.VatIdValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Check if a vat id field is valid for the selected country - interface.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
@Documented
@Constraint(validatedBy = VatIdValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface VatId {
  /**
   * localized message.
   */
  String message() default "{deKnightsoftnetValidatorsSharedValidationVatIdMessage}";

  /**
   * groups to use.
   */
  Class<?>[] groups() default {};

  /**
   * payload whatever.
   */
  Class<? extends Payload>[] payload() default {};

  /**
   * field name of the country code field.
   */
  String fieldCountryCode() default "countryCode";

  /**
   * field name of the vat id field.
   */
  String fieldVatId() default "vatId";

  /**
   * Defines several {@code @VatId} annotations on the same element.
   */
  @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  public @interface List {
    /**
     * must be filled if other is filled value.
     */
    VatId[] value();
  }
}
