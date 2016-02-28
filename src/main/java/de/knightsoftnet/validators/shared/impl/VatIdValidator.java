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

import de.knightsoftnet.validators.server.data.CreateVatIdMapConstantsClass;
import de.knightsoftnet.validators.shared.VatId;
import de.knightsoftnet.validators.shared.data.VatIdMapSharedConstants;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check if a vat id field is valid for the selected country.
 *
 * @author Manfred Tremmel
 *
 */
public class VatIdValidator implements ConstraintValidator<VatId, Object> {
  /**
   * modulo 11.
   */
  private static final int MODULO_11 = 11;
  /**
   * modulo 97.
   */
  private static final int MODULO_97 = 97;

  /**
   * map of vat id regex values for the different countries.
   */
  private static final VatIdMapSharedConstants VATID_MAP = CreateVatIdMapConstantsClass.create();

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
  private String fieldVatId;

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final VatId pconstraintAnnotation) {
    this.message = pconstraintAnnotation.message();
    this.fieldCountryCode = pconstraintAnnotation.fieldCountryCode();
    this.allowLowerCaseCountryCode = pconstraintAnnotation.allowLowerCaseCountryCode();
    this.fieldVatId = pconstraintAnnotation.fieldVatId();
  }

  @Override
  public final boolean isValid(final Object pvalue, final ConstraintValidatorContext pcontext) {
    if (pvalue == null) {
      return true;
    }
    try {
      String countryCode = BeanUtils.getProperty(pvalue, this.fieldCountryCode);
      final String vatId = BeanUtils.getProperty(pvalue, this.fieldVatId);
      if (StringUtils.isEmpty(vatId)) {
        return true;
      }
      if (StringUtils.isEmpty(countryCode)) {
        return true;
      }

      if (this.allowLowerCaseCountryCode) {
        countryCode = StringUtils.upperCase(countryCode);
      }

      final String regExCheck = VATID_MAP.vatIds().get(countryCode);
      if (regExCheck == null) {
        return true;
      }
      if (vatId.matches(regExCheck) && this.checkSumTest(countryCode, vatId)) {
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
    pcontext.buildConstraintViolationWithTemplate(this.message).addNode(this.fieldVatId)
        .addConstraintViolation();
  }

  private boolean checkSumTest(final String pcountryCode, final String pvatId) {
    boolean checkSumOk = false;
    switch (pcountryCode) {
      case "AT":
        checkSumOk = this.checkAtVatId(pvatId);
        break;
      case "BE":
        checkSumOk = this.checkBeVatId(pvatId);
        break;
      case "DE":
        checkSumOk = this.checkDeVatId(pvatId);
        break;
      case "DK":
        checkSumOk = this.checkDkVatId(pvatId);
        break;
      case "ES":
        checkSumOk = this.checkEsVatId(pvatId);
        break;
      case "FI":
        checkSumOk = this.checkFiVatId(pvatId);
        break;
      case "FR":
        checkSumOk = this.checkFrVatId(pvatId);
        break;
      case "GR":
        checkSumOk = this.checkGrVatId(pvatId);
        break;
      case "IE":
        checkSumOk = this.checkIeVatId(pvatId);
        break;
      case "IT":
        checkSumOk = this.checkItVatId(pvatId);
        break;
      case "LU":
        checkSumOk = this.checkLuVatId(pvatId);
        break;
      case "NL":
        checkSumOk = this.checkNlVatId(pvatId);
        break;
      case "NO":
        checkSumOk = this.checkNoVatId(pvatId);
        break;
      case "PL":
        checkSumOk = this.checkPlVatId(pvatId);
        break;
      case "PT":
        checkSumOk = this.checkPtVatId(pvatId);
        break;
      case "SE":
        checkSumOk = this.checkSeVatId(pvatId);
        break;
      case "SI":
        checkSumOk = this.checkSiVatId(pvatId);
        break;
      default:
        // for other countries, I haven't found checksum rules
        checkSumOk = true;
        break;
    }
    return checkSumOk;
  }

  /**
   * check the VAT identification number, country version for Austria.
   *
   * @param pvatId vat id to check
   * @return true if checksum is ok
   */
  private boolean checkAtVatId(final String pvatId) {
    final int checkSum = pvatId.charAt(10) - '0';
    final int sum = pvatId.charAt(3) - '0' + squareSum((pvatId.charAt(4) - '0') * 2)
        + pvatId.charAt(5) - '0' + squareSum((pvatId.charAt(6) - '0') * 2) + pvatId.charAt(7) - '0'
        + squareSum((pvatId.charAt(8) - '0') * 2) + pvatId.charAt(9) - '0';
    final int calculatedCheckSum = (96 - sum) % 10;
    return checkSum == calculatedCheckSum;
  }

  /**
   * check the VAT identification number, country version for Belgium.
   *
   * @param pvatId vat id to check
   * @return true if checksum is ok
   */
  private boolean checkBeVatId(final String pvatId) {
    final int numberPart = Integer.parseInt(pvatId.substring(2, 10));
    final int checkSum = Integer.parseInt(pvatId.substring(10));
    final int calculatedCheckSumBe = MODULO_97 - numberPart % MODULO_97;
    return checkSum == calculatedCheckSumBe;
  }

  /**
   * check the VAT identification number, country version for Denmark.
   *
   * @param pvatId vat id to check
   * @return true if checksum is ok
   */
  private boolean checkDkVatId(final String pvatId) {
    final int sum = (pvatId.charAt(2) - '0') * 2 + (pvatId.charAt(3) - '0') * 7
        + (pvatId.charAt(4) - '0') * 6 + (pvatId.charAt(5) - '0') * 5 + (pvatId.charAt(6) - '0') * 4
        + (pvatId.charAt(7) - '0') * 3 + (pvatId.charAt(8) - '0') * 2 + pvatId.charAt(9) - '0';
    return sum % MODULO_11 == 0;
  }

  /**
   * check the VAT identification number, country version for Germany.
   *
   * @param pvatId vat id to check
   * @return true if checksum is ok
   */
  private boolean checkDeVatId(final String pvatId) {
    final int checkSum = pvatId.charAt(10) - '0';
    int sum = 10;
    for (int i = 2; i < 10; i++) {
      int summe = (pvatId.charAt(i) - '0' + sum) % 10;
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
   * check the VAT identification number, country version for Finland.
   *
   * @param pvatId vat id to check
   * @return true if checksum is ok
   */
  private boolean checkFiVatId(final String pvatId) {
    final int checkSum = pvatId.charAt(9) - '0';
    final int sum =
        (pvatId.charAt(2) - '0') * 7 + (pvatId.charAt(3) - '0') * 9 + (pvatId.charAt(4) - '0') * 10
            + (pvatId.charAt(5) - '0') * 5 + (pvatId.charAt(6) - '0') * 8
            + (pvatId.charAt(7) - '0') * 4 + (pvatId.charAt(8) - '0') * 2;
    final int calculatedCheckSum = MODULO_11 - sum % MODULO_11;
    return checkSum == calculatedCheckSum;
  }

  /**
   * check the VAT identification number, country version for France.
   *
   * @param pvatId vat id to check
   * @return true if checksum is ok
   */
  private boolean checkFrVatId(final String pvatId) {
    final int checkSum = Integer.parseInt(pvatId.substring(2, 4));
    final int sum = Integer.parseInt(pvatId.substring(4));
    final int calculatedCheckSum = (12 + 3 * (sum % MODULO_97)) % MODULO_97;
    return checkSum == calculatedCheckSum;
  }

  /**
   * check the VAT identification number, country version for Greece.
   *
   * @param pvatId vat id to check
   * @return true if checksum is ok
   */
  private boolean checkGrVatId(final String pvatId) {
    final int checkSum = pvatId.charAt(10) - '0';
    final int sum = (pvatId.charAt(2) - '0') * 256 + (pvatId.charAt(3) - '0') * 128
        + (pvatId.charAt(4) - '0') * 64 + (pvatId.charAt(5) - '0') * 32
        + (pvatId.charAt(6) - '0') * 16 + (pvatId.charAt(7) - '0') * 8
        + (pvatId.charAt(8) - '0') * 4 + (pvatId.charAt(9) - '0') * 2;
    int calculatedCheckSum = sum % MODULO_11;
    if (calculatedCheckSum > 9) {
      calculatedCheckSum = 0;
    }
    return checkSum == calculatedCheckSum;
  }

  /**
   * check the VAT identification number, country version for Ireland.
   *
   * @param pvatId vat id to check
   * @return true if checksum is ok
   */
  private boolean checkIeVatId(final String pvatId) {
    final char checkSum = pvatId.charAt(9);
    String vatId = pvatId;
    if (pvatId.charAt(3) >= 'A' && pvatId.charAt(3) <= 'Z') {
      // old id, can be transfered to new form
      vatId = pvatId.substring(0, 2) + "0" + pvatId.substring(4, 9) + pvatId.substring(2, 3)
          + pvatId.substring(9);
    }
    final int sum = (vatId.charAt(2) - '0') * 8 + (vatId.charAt(3) - '0') * 7
        + (vatId.charAt(4) - '0') * 6 + (vatId.charAt(5) - '0') * 5 + (vatId.charAt(6) - '0') * 4
        + (vatId.charAt(7) - '0') * 3 + (vatId.charAt(8) - '0') * 2;
    final int calculatedCheckSum = sum % 23;
    final char calculatedCheckSumCharIe;
    if (calculatedCheckSum == 0) {
      calculatedCheckSumCharIe = 'W';
    } else {
      calculatedCheckSumCharIe = (char) ('A' + calculatedCheckSum - 1);
    }
    return checkSum == calculatedCheckSumCharIe;
  }

  /**
   * check the VAT identification number, country version for Italy.
   *
   * @param pvatId vat id to check
   * @return true if checksum is ok
   */
  private boolean checkItVatId(final String pvatId) {
    final int checkSum = pvatId.charAt(12) - '0';
    final int sum = pvatId.charAt(2) - '0' + squareSum((pvatId.charAt(3) - '0') * 2)
        + pvatId.charAt(4) - '0' + squareSum((pvatId.charAt(5) - '0') * 2) + pvatId.charAt(6) - '0'
        + squareSum((pvatId.charAt(7) - '0') * 2) + pvatId.charAt(8) - '0'
        + squareSum((pvatId.charAt(9) - '0') * 2) + pvatId.charAt(10) - '0'
        + squareSum((pvatId.charAt(11) - '0') * 2);
    final int calculatedCheckSumIt = 10 - sum % 10;
    return checkSum == calculatedCheckSumIt;
  }

  /**
   * check the VAT identification number, country version for Luxembourg.
   *
   * @param pvatId vat id to check
   * @return true if checksum is ok
   */
  private boolean checkLuVatId(final String pvatId) {
    final int numberPart = Integer.parseInt(pvatId.substring(2, 8));
    final int checkSum = Integer.parseInt(pvatId.substring(8));
    final int calculatedCheckSum = numberPart % 89;
    return checkSum == calculatedCheckSum;
  }

  /**
   * check the VAT identification number, country version for Netherlands.
   *
   * @param pvatId vat id to check
   * @return true if checksum is ok
   */
  private boolean checkNlVatId(final String pvatId) {
    final int checkSum = pvatId.charAt(10) - '0';
    final int sum = (pvatId.charAt(2) - '0') * 9 + (pvatId.charAt(3) - '0') * 8
        + (pvatId.charAt(4) - '0') * 7 + (pvatId.charAt(5) - '0') * 6 + (pvatId.charAt(6) - '0') * 5
        + (pvatId.charAt(7) - '0') * 4 + (pvatId.charAt(8) - '0') * 3
        + (pvatId.charAt(9) - '0') * 2;
    final int calculatedCheckSum = sum % MODULO_11;
    return checkSum == calculatedCheckSum;
  }

  /**
   * check the VAT identification number, country version for Norway.
   *
   * @param pvatId vat id to check
   * @return true if checksum is ok
   */
  private boolean checkNoVatId(final String pvatId) {
    final int checkSum = pvatId.charAt(10) - '0';
    final int sum = (pvatId.charAt(2) - '0') * 3 + (pvatId.charAt(3) - '0') * 2
        + (pvatId.charAt(4) - '0') * 7 + (pvatId.charAt(5) - '0') * 6 + (pvatId.charAt(6) - '0') * 5
        + (pvatId.charAt(7) - '0') * 4 + (pvatId.charAt(8) - '0') * 3
        + (pvatId.charAt(9) - '0') * 2;
    final int calculatedCheckSum = MODULO_11 - sum % MODULO_11;
    return checkSum == calculatedCheckSum;
  }

  /**
   * check the VAT identification number, country version for Poland.
   *
   * @param pvatId vat id to check
   * @return true if checksum is ok
   */
  private boolean checkPlVatId(final String pvatId) {
    final int checkSum = pvatId.charAt(11) - '0';
    final int sum = (pvatId.charAt(2) - '0') * 6 + (pvatId.charAt(3) - '0') * 5
        + (pvatId.charAt(4) - '0') * 7 + (pvatId.charAt(5) - '0') * 2 + (pvatId.charAt(6) - '0') * 3
        + (pvatId.charAt(7) - '0') * 4 + (pvatId.charAt(8) - '0') * 5 + (pvatId.charAt(9) - '0') * 6
        + (pvatId.charAt(10) - '0') * 7;
    final int calculatedCheckSumPl = sum % MODULO_11;
    return checkSum == calculatedCheckSumPl;
  }

  /**
   * check the VAT identification number, country version for Portugal.
   *
   * @param pvatId vat id to check
   * @return true if checksum is ok
   */
  private boolean checkPtVatId(final String pvatId) {
    final int checkSum = pvatId.charAt(10) - '0';
    final int sum = (pvatId.charAt(2) - '0') * 9 + (pvatId.charAt(3) - '0') * 8
        + (pvatId.charAt(4) - '0') * 7 + (pvatId.charAt(5) - '0') * 6 + (pvatId.charAt(6) - '0') * 5
        + (pvatId.charAt(7) - '0') * 4 + (pvatId.charAt(8) - '0') * 3
        + (pvatId.charAt(9) - '0') * 2;
    int calculatedCheckSum = MODULO_11 - sum % MODULO_11;
    if (calculatedCheckSum > 9) {
      calculatedCheckSum = 0;
    }
    return checkSum == calculatedCheckSum;
  }

  /**
   * check the VAT identification number, country version for Sweden.
   *
   * @param pvatId vat id to check
   * @return true if checksum is ok
   */
  private boolean checkSeVatId(final String pvatId) {
    final int checkSum = pvatId.charAt(11) - '0';
    final int sum = squareSum((pvatId.charAt(2) - '0') * 2) + pvatId.charAt(3) - '0'
        + squareSum((pvatId.charAt(4) - '0') * 2) + pvatId.charAt(5) - '0'
        + squareSum((pvatId.charAt(6) - '0') * 2) + pvatId.charAt(7) - '0'
        + squareSum((pvatId.charAt(8) - '0') * 2) + pvatId.charAt(9) - '0'
        + squareSum((pvatId.charAt(10) - '0') * 2);
    int calculatedCheckSum = 10 - sum % 10;
    if (calculatedCheckSum == 10) {
      calculatedCheckSum = 0;
    }
    return checkSum == calculatedCheckSum;
  }

  /**
   * check the VAT identification number, country version for Slovenia.
   *
   * @param pvatId vat id to check
   * @return true if checksum is ok
   */
  private boolean checkSiVatId(final String pvatId) {
    boolean checkSumOk;
    final int checkSum = pvatId.charAt(9) - '0';
    final int sum = (pvatId.charAt(2) - '0') * 8 + (pvatId.charAt(3) - '0') * 7
        + (pvatId.charAt(4) - '0') * 6 + (pvatId.charAt(5) - '0') * 5 + (pvatId.charAt(6) - '0') * 4
        + (pvatId.charAt(7) - '0') * 3 + (pvatId.charAt(8) - '0') * 2;
    int calculatedCheckSum = MODULO_11 - sum % MODULO_11;
    if (calculatedCheckSum == 11) {
      checkSumOk = false;
    } else {
      if (calculatedCheckSum == 10) {
        calculatedCheckSum = 0;
      }
      checkSumOk = checkSum == calculatedCheckSum;
    }
    return checkSumOk;
  }

  /**
   * check the VAT identification number, country version for Spain. TODO
   * http://www.pruefziffernberechnung.de/U/USt-IdNr.shtml routine doesn't work for alphabetic
   * checksums, so it's not used at the moment
   *
   * @param pvatId vat id to check
   * @return true if checksum is ok
   */
  private boolean checkEsVatId(final String pvatId) { // NOPMD
    final boolean checkSumOk = true;
    // if (pvatId.charAt(2) >= 'A' && pvatId.charAt(2) <= 'Z') {
    // final int checkSumEs;
    // if (pvatId.charAt(10) >= 'A' && pvatId.charAt(10) <= 'Z') {
    // checkSumEs = pvatId.charAt(10) - 'A';
    // } else {
    // checkSumEs = pvatId.charAt(10) - '0';
    // }
    // final int sumEs =
    // squareSum((pvatId.charAt(3) - '0') * 2) + pvatId.charAt(4) - '0'
    // + squareSum((pvatId.charAt(5) - '0') * 2) + pvatId.charAt(6) - '0'
    // + squareSum((pvatId.charAt(7) - '0') * 2) + pvatId.charAt(8) - '0'
    // + squareSum((pvatId.charAt(9) - '0') * 2);
    // int calculatedCheckSumEs = 10 - sumEs % 10;
    // if (calculatedCheckSumEs == 10) {
    // calculatedCheckSumEs = 0;
    // }
    // checkSumOk = checkSumEs == calculatedCheckSumEs;
    // } else {
    // final char checkSumEs = pvatId.charAt(10);
    // final int sumEs = Integer.parseInt(pvatId.substring(2, 10));
    // final char calculatedCheckSumEs;
    // switch (sumEs % 23) {
    // case 0:
    // calculatedCheckSumEs = 'T';
    // break;
    // case 1:
    // calculatedCheckSumEs = 'R';
    // break;
    // case 2:
    // calculatedCheckSumEs = 'W';
    // break;
    // case 3:
    // calculatedCheckSumEs = 'A';
    // break;
    // case 4:
    // calculatedCheckSumEs = 'G';
    // break;
    // case 5:
    // calculatedCheckSumEs = 'M';
    // break;
    // case 6:
    // calculatedCheckSumEs = 'Y';
    // break;
    // case 7:
    // calculatedCheckSumEs = 'F';
    // break;
    // case 8:
    // calculatedCheckSumEs = 'P';
    // break;
    // case 9:
    // calculatedCheckSumEs = 'D';
    // break;
    // case 10:
    // calculatedCheckSumEs = 'X';
    // break;
    // case 11:
    // calculatedCheckSumEs = 'B';
    // break;
    // case 12:
    // calculatedCheckSumEs = 'N';
    // break;
    // case 13:
    // calculatedCheckSumEs = 'J';
    // break;
    // case 14:
    // calculatedCheckSumEs = 'Z';
    // break;
    // case 15:
    // calculatedCheckSumEs = 'S';
    // break;
    // case 16:
    // calculatedCheckSumEs = 'Q';
    // break;
    // case 17:
    // calculatedCheckSumEs = 'V';
    // break;
    // case 18:
    // calculatedCheckSumEs = 'H';
    // break;
    // case 19:
    // calculatedCheckSumEs = 'L';
    // break;
    // case 20:
    // calculatedCheckSumEs = 'C';
    // break;
    // case 21:
    // calculatedCheckSumEs = 'K';
    // break;
    // case 22:
    // calculatedCheckSumEs = 'E';
    // break;
    // default:
    // calculatedCheckSumEs = ' ';
    // break;
    // }
    // checkSumOk = calculatedCheckSumEs == checkSumEs;
    // }
    return checkSumOk;
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
