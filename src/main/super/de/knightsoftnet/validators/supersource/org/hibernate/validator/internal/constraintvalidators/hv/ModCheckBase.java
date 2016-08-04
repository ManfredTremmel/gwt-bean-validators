/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package org.hibernate.validator.internal.constraintvalidators.hv;

import com.google.gwt.regexp.shared.RegExp;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidatorContext;

/**
 * ModCheckBase contains all shared methods and options used by Mod Check Validators.
 * http://en.wikipedia.org/wiki/Check_digit
 *
 * @author George Gastaldi
 * @author Hardy Ferentschik
 * @author Victor Rezende dos Santos
 * @author Manfred Tremmel - GWT port
 */
public abstract class ModCheckBase { // NOPMD

  private static final Log LOG = LoggerFactory.make(); // NOPMD

  private static final RegExp NUMBERS_ONLY_REGEXP = RegExp.compile("[^0-9]");

  private static final int DEC_RADIX = 10;

  /**
   * The start index for the checksum calculation.
   */
  private int startIndex;

  /**
   * The end index for the checksum calculation.
   */
  private int endIndex;

  /**
   * The index of the checksum digit.
   */
  private int checkDigitIndex;

  private boolean ignoreNonDigitCharacters;

  /**
   * valid check.
   * 
   * @param value value to check.
   * @param context constraint validator context
   * @return true if valid
   */
  public boolean isValid(final CharSequence value, final ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }

    final String valueAsString = value.toString();
    String digitsAsString;
    char checkDigit;
    try {
      digitsAsString = this.extractVerificationString(valueAsString);
      checkDigit = this.extractCheckDigit(valueAsString);
    } catch (final IndexOutOfBoundsException e) {
      return false;
    }
    digitsAsString = this.stripNonDigitsIfRequired(digitsAsString);

    List<Integer> digits;
    try {
      digits = this.extractDigits(digitsAsString);
    } catch (final NumberFormatException e) {
      return false;
    }

    return this.isCheckDigitValid(digits, checkDigit);
  }

  public abstract boolean isCheckDigitValid(List<Integer> digits, char checkDigit);

  protected void initialize(final int startIndex, final int endIndex, final int checkDigitIndex,
      final boolean ignoreNonDigitCharacters) {
    this.startIndex = startIndex;
    this.endIndex = endIndex;
    this.checkDigitIndex = checkDigitIndex;
    this.ignoreNonDigitCharacters = ignoreNonDigitCharacters;

    this.validateOptions();
  }

  /**
   * Returns the numeric {@code int} value of a {@code char}
   *
   * @param value the input {@code char} to be parsed
   *
   * @return the numeric {@code int} value represented by the character.
   *
   * @throws NumberFormatException in case character is not a digit
   */
  protected int extractDigit(final char value) throws NumberFormatException {
    if (Character.isDigit(value)) {
      return Character.digit(value, DEC_RADIX);
    } else {
      throw LOG.getCharacterIsNotADigitException(value);
    }
  }

  /**
   * Parses the {@link String} value as a {@link List} of {@link Integer} objects
   *
   * @param value the input string to be parsed
   *
   * @return List of {@code Integer} objects.
   *
   * @throws NumberFormatException in case any of the characters is not a digit
   */
  private List<Integer> extractDigits(final String value) throws NumberFormatException {
    final List<Integer> digits = new ArrayList<Integer>(value.length());
    final char[] chars = value.toCharArray();
    for (final char c : chars) {
      digits.add(this.extractDigit(c));
    }
    return digits;
  }

  private boolean validateOptions() {
    if (this.startIndex < 0) {
      throw LOG.getStartIndexCannotBeNegativeException(this.startIndex);
    }

    if (this.endIndex < 0) {
      throw LOG.getEndIndexCannotBeNegativeException(this.endIndex);
    }

    if (this.startIndex > this.endIndex) {
      throw LOG.getInvalidRangeException(this.startIndex, this.endIndex);
    }

    if (this.checkDigitIndex > 0 && this.startIndex <= this.checkDigitIndex
        && this.endIndex > this.checkDigitIndex) {
      throw LOG.getInvalidCheckDigitException(this.startIndex, this.endIndex);
    }

    return true;
  }

  private String stripNonDigitsIfRequired(final String value) {
    if (this.ignoreNonDigitCharacters) {
      return NUMBERS_ONLY_REGEXP.replace(value, StringUtils.EMPTY);
    } else {
      return value;
    }
  }

  private String extractVerificationString(final String value) throws IndexOutOfBoundsException {
    // the string contains the check digit, just return the digits to verify
    if (this.endIndex == Integer.MAX_VALUE) {
      return value.substring(0, value.length() - 1);
    } else if (this.checkDigitIndex == -1) {
      return value.substring(this.startIndex, this.endIndex);
    } else {
      return value.substring(this.startIndex, this.endIndex + 1);
    }
  }

  private char extractCheckDigit(final String value) throws IndexOutOfBoundsException {
    // take last character of string to be validated unless the index is given explicitly
    if (this.checkDigitIndex == -1) {
      if (this.endIndex == Integer.MAX_VALUE) {
        return value.charAt(value.length() - 1);
      } else {
        return value.charAt(this.endIndex);
      }
    } else {
      return value.charAt(this.checkDigitIndex);
    }
  }

}
