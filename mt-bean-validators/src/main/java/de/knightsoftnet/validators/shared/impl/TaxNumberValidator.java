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

import de.knightsoftnet.validators.server.data.CreateTaxNumberMapConstantsClass;
import de.knightsoftnet.validators.shared.TaxNumber;
import de.knightsoftnet.validators.shared.data.TaxNumberMapSharedConstants;
import de.knightsoftnet.validators.shared.util.BeanPropertyReaderUtil;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check if a Tax Number field is valid for the selected country.
 *
 * @author Manfred Tremmel
 *
 */
public class TaxNumberValidator implements ConstraintValidator<TaxNumber, Object> {
  /**
   * modulo 11.
   */
  private static final int MODULO_11 = 11;

  /**
   * map of vat id regex values for the different countries.
   */
  private static final TaxNumberMapSharedConstants TAX_NUMBER_MAP =
      CreateTaxNumberMapConstantsClass.create();

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
  private String fieldTaxNumber;

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final TaxNumber pconstraintAnnotation) {
    message = pconstraintAnnotation.message();
    fieldCountryCode = pconstraintAnnotation.fieldCountryCode();
    allowLowerCaseCountryCode = pconstraintAnnotation.allowLowerCaseCountryCode();
    fieldTaxNumber = pconstraintAnnotation.fieldTaxNumber();
  }

  @Override
  public final boolean isValid(final Object pvalue, final ConstraintValidatorContext pcontext) {
    if (pvalue == null) {
      return true;
    }
    try {
      String countryCode =
          BeanPropertyReaderUtil.getNullSaveStringProperty(pvalue, fieldCountryCode);
      if (StringUtils.isEmpty(countryCode)) {
        return true;
      }
      final String taxNumber =
          BeanPropertyReaderUtil.getNullSaveStringProperty(pvalue, fieldTaxNumber);
      if (StringUtils.isEmpty(taxNumber)) {
        return true;
      }

      if (allowLowerCaseCountryCode) {
        countryCode = StringUtils.upperCase(countryCode);
      }

      final String regExCheck = TAX_NUMBER_MAP.taxNumbers().get(countryCode);
      if (regExCheck == null) {
        return true;
      }
      if (taxNumber.matches(regExCheck) && checkSumTest(countryCode, taxNumber)) {
        return true;
      }
      switchContext(pcontext);
      return false;
    } catch (final Exception ignore) {
      switchContext(pcontext);
      return false;
    }
  }

  private void switchContext(final ConstraintValidatorContext pcontext) {
    pcontext.disableDefaultConstraintViolation();
    pcontext.buildConstraintViolationWithTemplate(message).addPropertyNode(fieldTaxNumber)
        .addConstraintViolation();
  }

  private boolean checkSumTest(final String pcountryCode, final String ptaxNumber) {
    boolean checkSumOk = false;
    switch (pcountryCode) {
      case "AT":
        checkSumOk = checkAtTaxNumber(ptaxNumber);
        break;
      case "DE":
        checkSumOk = checkDeTaxNumber(ptaxNumber);
        break;
      case "HR":
        checkSumOk = checkModulo11TaxNumber(ptaxNumber);
        break;
      case "DK":
        checkSumOk = checkDkTaxNumber(ptaxNumber);
        break;
      case "EE":
      case "LT":
        checkSumOk = checkEeTaxNumber(ptaxNumber);
        break;
      case "ES":
        checkSumOk = checkEsTaxNumber(ptaxNumber);
        break;
      case "BA":
      case "ME":
      case "MK":
        checkSumOk = checkUniqueMasterCitizenNumber(ptaxNumber);
        break;
      case "NL":
        checkSumOk = checkNlTaxNumber(ptaxNumber);
        break;
      case "PL":
        checkSumOk = checkPlTaxNumber(ptaxNumber);
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
   * @param ptaxNumber vat id to check
   * @return true if checksum is ok
   */
  private boolean checkAtTaxNumber(final String ptaxNumber) {
    final int checkSum = ptaxNumber.charAt(8) - '0';
    final int sum = ptaxNumber.charAt(0) - '0' + squareSum((ptaxNumber.charAt(1) - '0') * 2) //
        + ptaxNumber.charAt(2) - '0' + squareSum((ptaxNumber.charAt(3) - '0') * 2) //
        + ptaxNumber.charAt(4) - '0' + squareSum((ptaxNumber.charAt(5) - '0') * 2) //
        + ptaxNumber.charAt(6) - '0' + squareSum((ptaxNumber.charAt(7) - '0') * 2);
    final int calculatedCheckSum = (80 - sum) % 10;
    return checkSum == calculatedCheckSum;
  }

  /**
   * check the Tax Identification Number number, country version for Germany.
   *
   * @param ptaxNumber vat id to check
   * @return true if checksum is ok
   */
  private boolean checkDeTaxNumber(final String ptaxNumber) {
    final int fa = Integer.parseInt(StringUtils.substring(ptaxNumber, 2, 4));
    final int sb = Integer.parseInt(StringUtils.substring(ptaxNumber, 5, 8));
    final int checkSum = ptaxNumber.charAt(12) - '0';
    if (StringUtils.startsWith(ptaxNumber, "11")) {
      // Berlin
      final int calculatedCheckSum;
      if (fa >= 27 && fa <= 30 //
          || fa < 31 && (sb < 201 || sb > 693) //
          || fa == 19 && (sb < 200 || sb > 639 && sb < 680 || sb > 680 && sb < 684 || sb > 684) //
          || fa == 37) {
        calculatedCheckSum = ((ptaxNumber.charAt(5) - '0') * 7 //
            + (ptaxNumber.charAt(6) - '0') * 6 //
            + (ptaxNumber.charAt(7) - '0') * 5 //
            + (ptaxNumber.charAt(8) - '0') * 8 //
            + (ptaxNumber.charAt(9) - '0') * 4 //
            + (ptaxNumber.charAt(10) - '0') * 3 //
            + (ptaxNumber.charAt(11) - '0') * 2) //
            % MODULO_11;
      } else {
        calculatedCheckSum = ((ptaxNumber.charAt(2) - '0') * 3 //
            + (ptaxNumber.charAt(3) - '0') * 2 //
            + (ptaxNumber.charAt(4) - '0') * 9 //
            + (ptaxNumber.charAt(5) - '0') * 8 //
            + (ptaxNumber.charAt(6) - '0') * 7 //
            + (ptaxNumber.charAt(7) - '0') * 6 //
            + (ptaxNumber.charAt(8) - '0') * 5 //
            + (ptaxNumber.charAt(9) - '0') * 4 //
            + (ptaxNumber.charAt(10) - '0') * 3 //
            + (ptaxNumber.charAt(11) - '0') * 2) //
            % MODULO_11;
      }
      return checkSum == calculatedCheckSum;
    }
    // TODO find checksum calculation routines for the rest
    return true;
  }

  /**
   * check the Tax Identification Number number, country version for Denmark.
   *
   * @param ptaxNumber vat id to check
   * @return true if checksum is ok
   */
  private boolean checkDkTaxNumber(final String ptaxNumber) {
    final int checkSum = ptaxNumber.charAt(9) - '0';
    final int sum = ((ptaxNumber.charAt(0) - '0') * 4 //
        + (ptaxNumber.charAt(1) - '0') * 2 //
        + (ptaxNumber.charAt(2) - '0') * 3 //
        + (ptaxNumber.charAt(3) - '0') * 7 //
        + (ptaxNumber.charAt(4) - '0') * 6 //
        + (ptaxNumber.charAt(5) - '0') * 5 //
        + (ptaxNumber.charAt(6) - '0') * 4 //
        + (ptaxNumber.charAt(7) - '0') * 3 //
        + (ptaxNumber.charAt(8) - '0') * 2) //
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
   * @param ptaxNumber vat id to check
   * @return true if checksum is ok
   */
  private boolean checkEeTaxNumber(final String ptaxNumber) {
    final int checkSum = ptaxNumber.charAt(10) - '0';
    int calculatedCheckSum = ((ptaxNumber.charAt(0) - '0') * 1 //
        + (ptaxNumber.charAt(1) - '0') * 2 //
        + (ptaxNumber.charAt(2) - '0') * 3 //
        + (ptaxNumber.charAt(3) - '0') * 4 //
        + (ptaxNumber.charAt(4) - '0') * 5 //
        + (ptaxNumber.charAt(5) - '0') * 6 //
        + (ptaxNumber.charAt(6) - '0') * 7 //
        + (ptaxNumber.charAt(7) - '0') * 8 //
        + (ptaxNumber.charAt(8) - '0') * 9 //
        + (ptaxNumber.charAt(9) - '0') * 1) //
        % MODULO_11;
    if (calculatedCheckSum == 10) {
      calculatedCheckSum = ((ptaxNumber.charAt(0) - '0') * 3 //
          + (ptaxNumber.charAt(1) - '0') * 4 //
          + (ptaxNumber.charAt(2) - '0') * 5 //
          + (ptaxNumber.charAt(3) - '0') * 6 //
          + (ptaxNumber.charAt(4) - '0') * 7 //
          + (ptaxNumber.charAt(5) - '0') * 8 //
          + (ptaxNumber.charAt(6) - '0') * 9 //
          + (ptaxNumber.charAt(7) - '0') * 1 //
          + (ptaxNumber.charAt(8) - '0') * 2 //
          + (ptaxNumber.charAt(9) - '0') * 3) //
          % MODULO_11 % 10;
    }
    return checkSum == calculatedCheckSum;
  }

  /**
   * check the Tax Identification Number number, country version for Spain.
   *
   * @param ptaxNumber vat id to check
   * @return true if checksum is ok
   */
  private boolean checkEsTaxNumber(final String ptaxNumber) {
    final char[] checkArray = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J',
        'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
    final char checkSum = ptaxNumber.charAt(8);
    final char calculatedCheckSum;
    final int sum;
    if (StringUtils.isNumeric(StringUtils.substring(ptaxNumber, 0, 8))) {
      // dni
      sum = Integer.parseInt(StringUtils.substring(ptaxNumber, 0, 8)) % 23;
      calculatedCheckSum = checkArray[sum];
    } else if (ptaxNumber.charAt(0) >= 'X' && ptaxNumber.charAt(0) <= 'Z') {
      // cif
      sum = Integer.parseInt(ptaxNumber.charAt(0) - '0' + StringUtils.substring(ptaxNumber, 1, 8))
          % 23;
      calculatedCheckSum = checkArray[sum];
    } else {
      // nie
      final char letter = ptaxNumber.charAt(0);
      final String number = StringUtils.substring(ptaxNumber, 1, 8);

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
   * @param ptaxNumber vat id to check
   * @return true if checksum is ok
   */
  private boolean checkModulo11TaxNumber(final String ptaxNumber) {
    final int checkSum = ptaxNumber.charAt(ptaxNumber.length() - 1) - '0';
    int sum = 10;
    for (int i = 0; i < ptaxNumber.length() - 1; i++) {
      int summe = (ptaxNumber.charAt(i) - '0' + sum) % 10;
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
   * @param ptaxNumber vat id to check
   * @return true if checksum is ok
   */
  private boolean checkUniqueMasterCitizenNumber(final String ptaxNumber) {
    final int checkSum = ptaxNumber.charAt(12) - '0';
    final int sum = ((ptaxNumber.charAt(0) - '0' + ptaxNumber.charAt(6) - '0') * 7 //
        + (ptaxNumber.charAt(1) - '0' + ptaxNumber.charAt(7) - '0') * 6 //
        + (ptaxNumber.charAt(2) - '0' + ptaxNumber.charAt(8) - '0') * 5 //
        + (ptaxNumber.charAt(3) - '0' + ptaxNumber.charAt(9) - '0') * 4 //
        + (ptaxNumber.charAt(4) - '0' + ptaxNumber.charAt(10) - '0') * 3 //
        + (ptaxNumber.charAt(5) - '0' + ptaxNumber.charAt(11) - '0') * 2) //
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
   * @param ptaxNumber vat id to check
   * @return true if checksum is ok
   */
  private boolean checkNlTaxNumber(final String ptaxNumber) {
    final int checkSum = ptaxNumber.charAt(8) - '0';
    int sum = 0;
    for (int i = 0; i < ptaxNumber.length() - 1; i++) {
      sum += (ptaxNumber.charAt(i) - '0') * (9 - i);
    }
    sum -= checkSum;
    return sum % 11 == 0;
  }

  /**
   * check the Tax Identification Number number, country version for Poland.
   *
   * @param ptaxNumber vat id to check
   * @return true if checksum is ok
   */
  private boolean checkPlTaxNumber(final String ptaxNumber) {
    final int checkSum = ptaxNumber.charAt(ptaxNumber.length() - 1) - '0';
    int calculatedCheckSum;
    if (StringUtils.length(ptaxNumber) == 11) {
      // PESEL
      final int sum = (ptaxNumber.charAt(0) - '0') * 1 //
          + (ptaxNumber.charAt(1) - '0') * 3 //
          + (ptaxNumber.charAt(2) - '0') * 7 //
          + (ptaxNumber.charAt(3) - '0') * 9 //
          + (ptaxNumber.charAt(4) - '0') * 1 //
          + (ptaxNumber.charAt(5) - '0') * 3 //
          + (ptaxNumber.charAt(6) - '0') * 7 //
          + (ptaxNumber.charAt(7) - '0') * 9 //
          + (ptaxNumber.charAt(8) - '0') * 1 //
          + (ptaxNumber.charAt(9) - '0') * 3;
      calculatedCheckSum = sum % 10;
      if (calculatedCheckSum != 0) {
        calculatedCheckSum = 10 - sum % 10;
      }
    } else {
      // NIP
      final int sum = (ptaxNumber.charAt(0) - '0') * 6 //
          + (ptaxNumber.charAt(1) - '0') * 5 //
          + (ptaxNumber.charAt(2) - '0') * 7 //
          + (ptaxNumber.charAt(3) - '0') * 2 //
          + (ptaxNumber.charAt(4) - '0') * 3 //
          + (ptaxNumber.charAt(5) - '0') * 4 //
          + (ptaxNumber.charAt(6) - '0') * 5 //
          + (ptaxNumber.charAt(7) - '0') * 6 //
          + (ptaxNumber.charAt(8) - '0') * 7;
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
