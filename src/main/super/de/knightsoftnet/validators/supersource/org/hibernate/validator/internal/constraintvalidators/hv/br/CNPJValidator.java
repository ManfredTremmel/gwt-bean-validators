/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package org.hibernate.validator.internal.constraintvalidators.hv.br;

import com.google.gwt.regexp.shared.RegExp;

import org.hibernate.validator.constraints.Mod11Check;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.internal.constraintvalidators.hv.Mod11CheckValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * CNPJValidator.
 *
 * @author Hardy Ferentschik
 * @author Manfred Tremmel - GWT implementation
 */
@SuppressWarnings("checkstyle:abbreviationaswordinname")
public class CNPJValidator implements ConstraintValidator<CNPJ, CharSequence> {
  private static final RegExp DIGITS_ONLY = RegExp.compile("\\d+");

  private final Mod11CheckValidator withSeparatorMod11Validator1 = new Mod11CheckValidator();
  private final Mod11CheckValidator withSeparatorMod11Validator2 = new Mod11CheckValidator();

  private final Mod11CheckValidator withoutSeparatorMod11Validator1 = new Mod11CheckValidator();
  private final Mod11CheckValidator withoutSeparatorMod11Validator2 = new Mod11CheckValidator();

  @Override
  public void initialize(final CNPJ constraintAnnotation) {
    // validates CNPJ strings with separator, eg 91.509.901/0001-69
    // there are two checksums generated. The first over the digits prior the hyphen with the first
    // check digit being the digit directly after the hyphen. The second checksum is over all digits
    // pre hyphen + first check digit. The check digit in this case is the second digit after the
    // hyphen
    this.withSeparatorMod11Validator1.initialize(0, 14, 16, true, 9, '0', '0',
        Mod11Check.ProcessingDirection.RIGHT_TO_LEFT);
    this.withSeparatorMod11Validator2.initialize(0, 16, 17, true, 9, '0', '0',
        Mod11Check.ProcessingDirection.RIGHT_TO_LEFT);

    // validates CNPJ strings without separator, eg 91509901000169
    // checksums as described above, except there are no separator characters
    this.withoutSeparatorMod11Validator1.initialize(0, 11, 12, true, 9, '0', '0',
        Mod11Check.ProcessingDirection.RIGHT_TO_LEFT);
    this.withoutSeparatorMod11Validator2.initialize(0, 12, 13, true, 9, '0', '0',
        Mod11Check.ProcessingDirection.RIGHT_TO_LEFT);
  }

  @Override
  public boolean isValid(final CharSequence value, final ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }

    if (DIGITS_ONLY.exec(value.toString()) == null) {
      return this.withSeparatorMod11Validator1.isValid(value, context)
          && this.withSeparatorMod11Validator2.isValid(value, context);
    } else {
      return this.withoutSeparatorMod11Validator1.isValid(value, context)
          && this.withoutSeparatorMod11Validator2.isValid(value, context);
    }
  }
}
