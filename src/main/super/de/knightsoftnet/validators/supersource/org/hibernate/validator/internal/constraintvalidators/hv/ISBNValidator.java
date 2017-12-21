/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package org.hibernate.validator.internal.constraintvalidators.hv;

import com.google.gwt.regexp.shared.RegExp;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.ISBN;

import java.util.Objects;
import java.util.function.Function;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Checks that a given character sequence (e.g. string) is a valid ISBN.
 *
 * @author Marko Bekhta
 * @author Manfred Tremmel - GWT implementation
 */
@SuppressWarnings("checkstyle:abbreviationaswordinname")
public class ISBNValidator implements ConstraintValidator<ISBN, CharSequence> {

  /**
   * Pattern to replace all non ISBN characters. ISBN can have digits or 'X'.
   */
  private static final RegExp NOT_DIGITS_OR_NOT_X = RegExp.compile("[^\\dX]", "g");

  private int length;

  private Function<String, Boolean> checkChecksumFunction;

  @Override
  public void initialize(final ISBN constraintAnnotation) {
    switch (constraintAnnotation.type()) {
      case ISBN_10:
        this.length = 10;
        this.checkChecksumFunction = this::checkChecksumISBN10;
        break;
      case ISBN_13:
        this.length = 13;
        this.checkChecksumFunction = this::checkChecksumISBN13;
        break;
      default:
        break;
    }
  }

  @Override
  public boolean isValid(final CharSequence isbn, final ConstraintValidatorContext context) {
    if (isbn == null) {
      return true;
    }

    // Replace all non-digit (or !=X) chars
    final String digits = NOT_DIGITS_OR_NOT_X.replace(Objects.toString(isbn), StringUtils.EMPTY);

    // Check if the length of resulting string matches the expecting one
    if (digits.length() != this.length) {
      return false;
    }

    return this.checkChecksumFunction.apply(digits);
  }

  /**
   * Check the digits for ISBN 10 using algorithm from <a href=
   * "https://en.wikipedia.org/wiki/International_Standard_Book_Number#ISBN-10_check_digits">Wikipedia</a>.
   */
  private boolean checkChecksumISBN10(final String isbn) {
    int sum = 0;
    for (int i = 0; i < isbn.length() - 1; i++) {
      sum += (isbn.charAt(i) - '0') * (i + 1);
    }
    final char checkSum = isbn.charAt(9);
    return sum % 11 == (checkSum == 'X' ? 10 : checkSum - '0');
  }

  /**
   * Check the digits for ISBN 13 using algorithm from <a href=
   * "https://en.wikipedia.org/wiki/International_Standard_Book_Number#ISBN-13_check_digit_calculation">Wikipedia</a>.
   */
  private boolean checkChecksumISBN13(final String isbn) {
    int sum = 0;
    for (int i = 0; i < isbn.length() - 1; i++) {
      sum += (isbn.charAt(i) - '0') * (i % 2 == 0 ? 1 : 3);
    }
    final char checkSum = isbn.charAt(12);
    return 10 - sum % 10 == checkSum - '0';
  }
}
