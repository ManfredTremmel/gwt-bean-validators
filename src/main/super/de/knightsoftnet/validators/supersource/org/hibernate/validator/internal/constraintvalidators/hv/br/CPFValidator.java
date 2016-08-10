/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package org.hibernate.validator.internal.constraintvalidators.hv.br;

import com.google.gwt.regexp.shared.RegExp;

import org.hibernate.validator.constraints.Mod11Check;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.internal.constraintvalidators.hv.Mod11CheckValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * CPF Validator.
 *
 * @author Hardy Ferentschik
 * @author Manfred Tremmel - GWT port
 */
@SuppressWarnings("checkstyle:abbreviationaswordinname")
public class CPFValidator implements ConstraintValidator<CPF, CharSequence> {
  private static final RegExp DIGITS_ONLY = RegExp.compile("^\\d+$");
  private static final RegExp SINGLE_DASH_SEPARATOR = RegExp.compile("^\\d+-\\d\\d$");

  private final Mod11CheckValidator withSeparatorMod11Validator1 = new Mod11CheckValidator();
  private final Mod11CheckValidator withSeparatorMod11Validator2 = new Mod11CheckValidator();

  private final Mod11CheckValidator withDashOnlySeparatorMod11Validator1 =
      new Mod11CheckValidator();
  private final Mod11CheckValidator withDashOnlySeparatorMod11Validator2 =
      new Mod11CheckValidator();

  private final Mod11CheckValidator withoutSeparatorMod11Validator1 = new Mod11CheckValidator();
  private final Mod11CheckValidator withoutSeparatorMod11Validator2 = new Mod11CheckValidator();

  @Override
  public void initialize(final CPF constraintAnnotation) {
    // validates CPF strings with separator, eg 134.241.313-00
    // there are two checksums generated. The first over the digits prior the hyphen with the first
    // check digit being the digit directly after the hyphen. The second checksum is over all digits
    // pre hyphen + first check digit. The check digit in this case is the second digit after the
    // hyphen
    this.withSeparatorMod11Validator1.initialize(0, 10, 12, true, Integer.MAX_VALUE, '0', '0',
        Mod11Check.ProcessingDirection.RIGHT_TO_LEFT);
    this.withSeparatorMod11Validator2.initialize(0, 12, 13, true, Integer.MAX_VALUE, '0', '0',
        Mod11Check.ProcessingDirection.RIGHT_TO_LEFT);

    // validates CPF strings with separator, eg 134241313-00
    this.withDashOnlySeparatorMod11Validator1.initialize(0, 8, 10, true, Integer.MAX_VALUE, '0',
        '0', Mod11Check.ProcessingDirection.RIGHT_TO_LEFT);
    this.withDashOnlySeparatorMod11Validator2.initialize(0, 10, 11, true, Integer.MAX_VALUE, '0',
        '0', Mod11Check.ProcessingDirection.RIGHT_TO_LEFT);

    // validates CPF strings without separator, eg 13424131300
    // checksums as described above, except there are no separator characters
    this.withoutSeparatorMod11Validator1.initialize(0, 8, 9, true, Integer.MAX_VALUE, '0', '0',
        Mod11Check.ProcessingDirection.RIGHT_TO_LEFT);
    this.withoutSeparatorMod11Validator2.initialize(0, 9, 10, true, Integer.MAX_VALUE, '0', '0',
        Mod11Check.ProcessingDirection.RIGHT_TO_LEFT);
  }

  @Override
  public boolean isValid(final CharSequence value, final ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }

    if (DIGITS_ONLY.exec(value.toString()) != null) { // NOPMD
      return this.withoutSeparatorMod11Validator1.isValid(value, context)
          && this.withoutSeparatorMod11Validator2.isValid(value, context);
    } else if (SINGLE_DASH_SEPARATOR.exec(value.toString()) != null) { // NOPMD
      return this.withDashOnlySeparatorMod11Validator1.isValid(value, context)
          && this.withDashOnlySeparatorMod11Validator2.isValid(value, context);
    } else {
      return this.withSeparatorMod11Validator1.isValid(value, context)
          && this.withSeparatorMod11Validator2.isValid(value, context);

    }
  }
}
