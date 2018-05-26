/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.knightsoftnet.validators.shared.impl;

import de.knightsoftnet.validators.server.data.CreateTinMapConstantsClass;
import de.knightsoftnet.validators.shared.Tin;
import de.knightsoftnet.validators.shared.data.TinMapSharedConstants;
import de.knightsoftnet.validators.shared.util.BeanPropertyReaderUtil;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check if a Tax Identification Number (TIN) field is valid for the selected country.
 *
 * @author Manfred Tremmel
 *
 */
public class TinValidator implements ConstraintValidator<Tin, Object> {
  /**
   * modulo 11.
   */
  private static final int MODULO_11 = 11;

  /**
   * map of vat id regex values for the different countries.
   */
  private static final TinMapSharedConstants TIN_MAP = CreateTinMapConstantsClass.create();

  /**
   * error message key.
   */
  private String message;

  /**
   * field name of the country code field.
   */
  private String fieldCountryCode;

  /**
   * are lower case country codes allowed (true/false).
   */
  private boolean allowLowerCaseCountryCode;

  /**
   * field name of the vat id field.
   */
  private String fieldTin;

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final Tin pconstraintAnnotation) {
    this.message = pconstraintAnnotation.message();
    this.fieldCountryCode = pconstraintAnnotation.fieldCountryCode();
    this.allowLowerCaseCountryCode = pconstraintAnnotation.allowLowerCaseCountryCode();
    this.fieldTin = pconstraintAnnotation.fieldTin();
  }

  @Override
  public final boolean isValid(final Object pvalue, final ConstraintValidatorContext pcontext) {
    if (pvalue == null) {
      return true;
    }
    try {
      String countryCode =
          BeanPropertyReaderUtil.getNullSaveStringProperty(pvalue, this.fieldCountryCode);
      if (StringUtils.isEmpty(countryCode)) {
        return true;
      }
      final String Tin = BeanPropertyReaderUtil.getNullSaveStringProperty(pvalue, this.fieldTin);
      if (StringUtils.isEmpty(Tin)) {
        return true;
      }

      if (this.allowLowerCaseCountryCode) {
        countryCode = StringUtils.upperCase(countryCode);
      }

      final String regExCheck = TIN_MAP.tins().get(countryCode);
      if (regExCheck == null) {
        return true;
      }
      if (Tin.matches(regExCheck) && this.checkSumTest(countryCode, Tin)) {
        return true;
      }
      this.switchContext(pcontext);
      return false;
    } catch (final Exception ignore) {
      this.switchContext(pcontext);
      return false;
    }
  }

  private void switchContext(final ConstraintValidatorContext pcontext) {
    pcontext.disableDefaultConstraintViolation();
    pcontext.buildConstraintViolationWithTemplate(this.message).addPropertyNode(this.fieldTin)
        .addConstraintViolation();
  }

  private boolean checkSumTest(final String pcountryCode, final String ptin) {
    boolean checkSumOk = false;
    switch (pcountryCode) {
      case "AT":
        checkSumOk = this.checkAtTin(ptin);
        break;
      case "DE":
      case "HR":
        checkSumOk = this.checkModulo11Tin(ptin);
        break;
      case "DK":
        checkSumOk = this.checkDkTin(ptin);
        break;
      case "EE":
      case "LT":
        checkSumOk = this.checkEeTin(ptin);
        break;
      case "ES":
        checkSumOk = this.checkEsTin(ptin);
        break;
      case "BA":
      case "ME":
      case "MK":
        checkSumOk = this.checkUniqueMasterCitizenNumber(ptin);
        break;
      case "NL":
        checkSumOk = this.checkNlTin(ptin);
        break;
      case "PL":
        checkSumOk = this.checkPlTin(ptin);
        break;
      default:
        // for other countries, I haven't found checksum rules
        checkSumOk = true;
        break;
    }
    return checkSumOk;
  }

  /**
   * check the Tax Identification Number number, country version for Austria.
   *
   * @param ptin vat id to check
   * @return true if checksum is ok
   */
  private boolean checkAtTin(final String ptin) {
    final int checkSum = ptin.charAt(8) - '0';
    final int sum = ptin.charAt(0) - '0' + squareSum((ptin.charAt(1) - '0') * 2) //
        + ptin.charAt(2) - '0' + squareSum((ptin.charAt(3) - '0') * 2) //
        + ptin.charAt(4) - '0' + squareSum((ptin.charAt(5) - '0') * 2) //
        + ptin.charAt(6) - '0' + squareSum((ptin.charAt(7) - '0') * 2);
    final int calculatedCheckSum = (80 - sum) % 10;
    return checkSum == calculatedCheckSum;
  }

  /**
   * check the Tax Identification Number number, country version for Denmark.
   *
   * @param ptin vat id to check
   * @return true if checksum is ok
   */
  private boolean checkDkTin(final String ptin) {
    final int checkSum = ptin.charAt(9) - '0';
    final int sum = ((ptin.charAt(0) - '0') * 4 //
        + (ptin.charAt(1) - '0') * 2 //
        + (ptin.charAt(2) - '0') * 3 //
        + (ptin.charAt(3) - '0') * 7 //
        + (ptin.charAt(4) - '0') * 6 //
        + (ptin.charAt(5) - '0') * 5 //
        + (ptin.charAt(6) - '0') * 4 //
        + (ptin.charAt(7) - '0') * 3 //
        + (ptin.charAt(8) - '0') * 2) //
        % MODULO_11;
    int calculatedCheckSum = MODULO_11 - sum;
    if (calculatedCheckSum == 10) {
      calculatedCheckSum = 0;
    }
    return checkSum == calculatedCheckSum;
  }

  /**
   * check the Tax Identification Number number, country version for Estonia.
   *
   * @param ptin vat id to check
   * @return true if checksum is ok
   */
  private boolean checkEeTin(final String ptin) {
    final int checkSum = ptin.charAt(10) - '0';
    int calculatedCheckSum = ((ptin.charAt(0) - '0') * 1 //
        + (ptin.charAt(1) - '0') * 2 //
        + (ptin.charAt(2) - '0') * 3 //
        + (ptin.charAt(3) - '0') * 4 //
        + (ptin.charAt(4) - '0') * 5 //
        + (ptin.charAt(5) - '0') * 6 //
        + (ptin.charAt(6) - '0') * 7 //
        + (ptin.charAt(7) - '0') * 8 //
        + (ptin.charAt(8) - '0') * 9 //
        + (ptin.charAt(9) - '0') * 1) //
        % MODULO_11;
    if (calculatedCheckSum == 10) {
      calculatedCheckSum = ((ptin.charAt(0) - '0') * 3 //
          + (ptin.charAt(1) - '0') * 4 //
          + (ptin.charAt(2) - '0') * 5 //
          + (ptin.charAt(3) - '0') * 6 //
          + (ptin.charAt(4) - '0') * 7 //
          + (ptin.charAt(5) - '0') * 8 //
          + (ptin.charAt(6) - '0') * 9 //
          + (ptin.charAt(7) - '0') * 1 //
          + (ptin.charAt(8) - '0') * 2 //
          + (ptin.charAt(9) - '0') * 3) //
          % MODULO_11 % 10;
    }
    return checkSum == calculatedCheckSum;
  }

  /**
   * check the Tax Identification Number number, country version for Spain.
   *
   * @param ptin vat id to check
   * @return true if checksum is ok
   */
  private boolean checkEsTin(final String ptin) {
    final char[] checkArray = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J',
        'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
    final char checkSum = ptin.charAt(8);
    final char calculatedCheckSum;
    final int sum;
    if (StringUtils.isNumeric(StringUtils.substring(ptin, 0, 8))) {
      // dni
      sum = Integer.parseInt(StringUtils.substring(ptin, 0, 8)) % 23;
      calculatedCheckSum = checkArray[sum];
    } else if (ptin.charAt(0) >= 'X' && ptin.charAt(0) <= 'Z') {
      // cif
      sum = Integer.parseInt(ptin.charAt(0) - '0' + StringUtils.substring(ptin, 1, 8)) % 23;
      calculatedCheckSum = checkArray[sum];
    } else {
      // nie
      final char letter = ptin.charAt(0);
      final String number = StringUtils.substring(ptin, 1, 8);

      int evenSum = 0;
      int oddSum = 0;

      for (int i = 0; i < number.length(); i++) {
        int charAsNum = number.charAt(i) - '0';

        // Odd positions (Even index equals to odd position. i=0 equals first position)
        if (i % 2 == 0) {
          // Odd positions are multiplied first.
          charAsNum *= 2;

          // If the multiplication is bigger than 10 we need to adjust
          oddSum += charAsNum < 10 ? charAsNum : charAsNum - 9;

          // Even positions
          // Just sum them
        } else {
          evenSum += charAsNum;
        }

      }

      final int control_digit = 10 - (evenSum + oddSum) % 10;
      final char control_letter = "JABCDEFGHI".charAt(control_digit);

      switch (letter) {
        case 'A':
        case 'B':
        case 'E':
        case 'H':
          // Control must be a digit
          calculatedCheckSum = (char) (control_digit + '0');
          break;
        case 'K':
        case 'P':
        case 'Q':
        case 'S':
          // Control must be a letter
          calculatedCheckSum = control_letter;
          break;
        default:
          // Can be either
          if (control_letter == checkSum) {
            calculatedCheckSum = control_letter;
          } else {
            calculatedCheckSum = (char) (control_digit + '0');
          }
          break;
      }
    }
    return checkSum == calculatedCheckSum;
  }

  /**
   * check the Tax Identification Number number, default modulo 11.
   *
   * @param ptin vat id to check
   * @return true if checksum is ok
   */
  private boolean checkModulo11Tin(final String ptin) {
    final int checkSum = ptin.charAt(ptin.length() - 1) - '0';
    int sum = 10;
    for (int i = 0; i < ptin.length() - 1; i++) {
      int summe = (ptin.charAt(i) - '0' + sum) % 10;
      if (summe == 0) {
        summe = 10;
      }
      sum = 2 * summe % MODULO_11;
    }
    int calculatedCheckSum = MODULO_11 - sum;
    if (calculatedCheckSum == 10) {
      calculatedCheckSum = 0;
    }
    return checkSum == calculatedCheckSum;
  }

  /**
   * check the Tax Identification Number number, country version for countries using unique master
   * citizen number .
   *
   * @param ptin vat id to check
   * @return true if checksum is ok
   */
  private boolean checkUniqueMasterCitizenNumber(final String ptin) {
    final int checkSum = ptin.charAt(12) - '0';
    final int sum = ((ptin.charAt(0) - '0' + ptin.charAt(6) - '0') * 7 //
        + (ptin.charAt(1) - '0' + ptin.charAt(7) - '0') * 6 //
        + (ptin.charAt(2) - '0' + ptin.charAt(8) - '0') * 5 //
        + (ptin.charAt(3) - '0' + ptin.charAt(9) - '0') * 4 //
        + (ptin.charAt(4) - '0' + ptin.charAt(10) - '0') * 3 //
        + (ptin.charAt(5) - '0' + ptin.charAt(11) - '0') * 2) //
        % MODULO_11;
    int calculatedCheckSum = MODULO_11 - sum;
    if (calculatedCheckSum == 10) {
      calculatedCheckSum = 0;
    }
    return checkSum == calculatedCheckSum;
  }

  /**
   * check the Tax Identification Number number, country version for Netherlands.
   *
   * @param ptin vat id to check
   * @return true if checksum is ok
   */
  private boolean checkNlTin(final String ptin) {
    final int checkSum = ptin.charAt(8) - '0';
    int sum = 0;
    for (int i = 0; i < ptin.length() - 1; i++) {
      sum += (ptin.charAt(i) - '0') * (9 - i);
    }
    sum -= checkSum;
    return sum % 11 == 0;
  }

  /**
   * check the Tax Identification Number number, country version for Poland.
   *
   * @param ptin vat id to check
   * @return true if checksum is ok
   */
  private boolean checkPlTin(final String ptin) {
    final int checkSum = ptin.charAt(ptin.length() - 1) - '0';
    int calculatedCheckSum;
    if (StringUtils.length(ptin) == 11) {
      // PESEL
      final int sum = (ptin.charAt(0) - '0') * 1 //
          + (ptin.charAt(1) - '0') * 3 //
          + (ptin.charAt(2) - '0') * 7 //
          + (ptin.charAt(3) - '0') * 9 //
          + (ptin.charAt(4) - '0') * 1 //
          + (ptin.charAt(5) - '0') * 3 //
          + (ptin.charAt(6) - '0') * 7 //
          + (ptin.charAt(7) - '0') * 9 //
          + (ptin.charAt(8) - '0') * 1 //
          + (ptin.charAt(9) - '0') * 3;
      calculatedCheckSum = sum % 10;
      if (calculatedCheckSum != 0) {
        calculatedCheckSum = 10 - sum % 10;
      }
    } else {
      // NIP
      final int sum = (ptin.charAt(0) - '0') * 6 //
          + (ptin.charAt(1) - '0') * 5 //
          + (ptin.charAt(2) - '0') * 7 //
          + (ptin.charAt(3) - '0') * 2 //
          + (ptin.charAt(4) - '0') * 3 //
          + (ptin.charAt(5) - '0') * 4 //
          + (ptin.charAt(6) - '0') * 5 //
          + (ptin.charAt(7) - '0') * 6 //
          + (ptin.charAt(8) - '0') * 7;
      calculatedCheckSum = sum % MODULO_11;
    }
    return checkSum == calculatedCheckSum;
  }

  /**
   * calculate square sum.
   *
   * @param pvalue value to calculate square sum for
   * @return square sum
   */
  private static int squareSum(final int pvalue) {
    int result = 0;
    for (final char valueDigit : String.valueOf(pvalue).toCharArray()) {
      result += Character.digit(valueDigit, 10);
    }
    return result;
  }
}
