package de.knightsoftnet.validators.shared;

import de.knightsoftnet.validators.shared.impl.BankCountryValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Check if a country field and the country in iban and bic match, interface.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
@Documented
@Constraint(validatedBy = BankCountryValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface BankCountry {
  /**
   * localized message.
   */
  String message() default "{deKnightsoftnetValidatorsSharedValidationBankCountryMessage}";

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
   * field name of the iban field.
   */
  String fieldIban() default "iban";

  /**
   * field name of the bic field.
   */
  String fieldBic() default "bic";

  /**
   * Defines several {@code BankCountry} annotations on the same element.
   */
  @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  public @interface List {
    /**
     * must be alternate filled if other is empty value.
     */
    BankCountry[] value();
  }
}
