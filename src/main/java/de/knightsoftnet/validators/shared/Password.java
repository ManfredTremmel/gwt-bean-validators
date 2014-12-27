package de.knightsoftnet.validators.shared;

import de.knightsoftnet.validators.shared.impl.PasswordValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Check passwords if they fulfill some complexity rules - interface.
 * <ul>
 * <li>upper-/lowercase</li>
 * <li>digits</li>
 * <li>special character</li>
 * </ul>
 * size limits should be added by separate size annotation.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
    ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
  /**
   * localized message.
   */
  String message() default "{deKnightsoftnetValidatorsSharedValidationPasswordMessage}";

  /**
   * groups to use.
   */
  Class<?>[] groups() default {};

  /**
   * minimum number rules that have to be fulfilled.
   */
  int minRules();

  /**
   * payload whatever.
   */
  Class<? extends Payload>[] payload() default {};

  /**
   * Defines several {@code Password} annotations on the same element.
   */
  @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
      ElementType.CONSTRUCTOR, ElementType.PARAMETER})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  public @interface List {
    /**
     * must be filled if other is empty value.
     */
    Password[] value();
  }
}
