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

import de.knightsoftnet.validators.shared.VatId;
import de.knightsoftnet.validators.shared.data.VatIdDefinitions;
import de.knightsoftnet.validators.shared.interfaces.HasGetFieldByName;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check if a vat id field is valid for the selected country.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
public class VatIdValidator implements ConstraintValidator<VatId, HasGetFieldByName> {

  /**
   * error message key.
   */
  private String message;

  /**
   * field name of the country code field.
   */
  private String fieldCountryCode;

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
    this.fieldVatId = pconstraintAnnotation.fieldVatId();
  }

  /**
   * {@inheritDoc} check if given object.
   *
   * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
   *      javax.validation.ConstraintValidatorContext)
   */
  @Override
  public final boolean isValid(final HasGetFieldByName pvalue,
      final ConstraintValidatorContext pcontext) {
    try {
      final String countryCode =
          Objects.toString(pvalue.getFieldByName(this.fieldCountryCode), null);
      final String vatId = Objects.toString(pvalue.getFieldByName(this.fieldVatId), null);
      if (StringUtils.isEmpty(vatId)) {
        return true;
      }

      final String regExCheck = VatIdDefinitions.COUNTRY_VAT_ID_REGEX.get(countryCode);
      if (regExCheck == null) {
        return true;
      }
      if (vatId.matches(regExCheck)) {
        return this.checkSumTest(countryCode, vatId);
      }
      pcontext.disableDefaultConstraintViolation();
      pcontext.buildConstraintViolationWithTemplate(this.message).addNode(this.fieldVatId)
          .addConstraintViolation();
      return false;
    } catch (final Exception ignore) {
      return false;
    }
  }

  private boolean checkSumTest(final String pcountryCode, final String pvatId) {
    final int modulo11 = 11;
    final int modulo97 = 97;
    boolean checkSumOk = false;
    switch (pcountryCode) {
      case "AT":
        final int checkSumAt = pvatId.charAt(10) - '0';
        final int sumAt =
            pvatId.charAt(3) - '0' + squareSum((pvatId.charAt(4) - '0') * 2) + pvatId.charAt(5)
                - '0' + squareSum((pvatId.charAt(6) - '0') * 2) + pvatId.charAt(7) - '0'
                + squareSum((pvatId.charAt(8) - '0') * 2) + pvatId.charAt(9) - '0';
        final int calculatedCheckSumAt = (96 - sumAt) % 10;
        checkSumOk = checkSumAt == calculatedCheckSumAt;
        break;
      case "BE":
        final int numberPartBe = Integer.parseInt(pvatId.substring(2, 10));
        final int checkSumBe = Integer.parseInt(pvatId.substring(10));
        final int calculatedCheckSumBe = modulo97 - numberPartBe % modulo97;
        checkSumOk = checkSumBe == calculatedCheckSumBe;
        break;
      case "DK":
        final int sumDk =
            (pvatId.charAt(2) - '0') * 2 + (pvatId.charAt(3) - '0') * 7 + (pvatId.charAt(4) - '0')
                * 6 + (pvatId.charAt(5) - '0') * 5 + (pvatId.charAt(6) - '0') * 4
                + (pvatId.charAt(7) - '0') * 3 + (pvatId.charAt(8) - '0') * 2 + pvatId.charAt(9)
                - '0';
        checkSumOk = sumDk % modulo11 == 0;
        break;
      case "DE":
        final int checkSumDe = pvatId.charAt(10) - '0';
        final int sumDe =
            ((((((((10 + pvatId.charAt(2) - '0') % 10 * 2 % modulo11 + pvatId.charAt(3) - '0') % 10
                * 2 % modulo11 + pvatId.charAt(4) - '0')
                % 10 * 2 % modulo11 + pvatId.charAt(5) - '0')
                % 10 * 2 % modulo11 + pvatId.charAt(6) - '0')
                % 10 * 2 % modulo11 + pvatId.charAt(7) - '0')
                % 10 * 2 % modulo11 + pvatId.charAt(8) - '0')
                % 10 * 2 % modulo11 + pvatId.charAt(9) - '0')
                % 10 * 2 % modulo11;
        final int calculatedCheckSumDe = modulo11 - sumDe;
        checkSumOk = checkSumDe == calculatedCheckSumDe;
        break;
      case "FI":
        final int checkSumFi = pvatId.charAt(9) - '0';
        final int sumFi =
            (pvatId.charAt(2) - '0') * 7 + (pvatId.charAt(3) - '0') * 9 + (pvatId.charAt(4) - '0')
                * 10 + (pvatId.charAt(5) - '0') * 5 + (pvatId.charAt(6) - '0') * 8
                + (pvatId.charAt(7) - '0') * 4 + (pvatId.charAt(8) - '0') * 2;
        final int calculatedCheckSumFi = modulo11 - sumFi % modulo11;
        checkSumOk = checkSumFi == calculatedCheckSumFi;
        break;
      case "GR":
        final int checkSumGr = pvatId.charAt(10) - '0';
        final int sumGr =
            (pvatId.charAt(2) - '0') * 256 + (pvatId.charAt(3) - '0') * 128
                + (pvatId.charAt(4) - '0') * 64 + (pvatId.charAt(5) - '0') * 32
                + (pvatId.charAt(6) - '0') * 16 + (pvatId.charAt(7) - '0') * 8
                + (pvatId.charAt(8) - '0') * 4 + (pvatId.charAt(9) - '0') * 2;
        final int calculatedCheckSumGr = sumGr % modulo11;
        checkSumOk = checkSumGr == calculatedCheckSumGr;
        break;
      case "IE":
        final char checkSumIe = pvatId.charAt(9);
        String vatIdIe = pvatId;
        if (pvatId.charAt(3) >= 'A' && pvatId.charAt(3) <= 'Z') {
          // old id, can be transfered to new form
          vatIdIe =
              pvatId.substring(0, 2) + "0" + pvatId.substring(4, 9) + pvatId.substring(3, 4)
                  + pvatId.substring(9);
        }
        final int sumIe =
            (vatIdIe.charAt(2) - '0') * 8 + (vatIdIe.charAt(3) - '0') * 7
                + (vatIdIe.charAt(4) - '0') * 6 + (vatIdIe.charAt(5) - '0') * 5
                + (vatIdIe.charAt(6) - '0') * 4 + (vatIdIe.charAt(7) - '0') * 3
                + (vatIdIe.charAt(8) - '0') * 2;
        final int calculatedCheckSumIe = sumIe % 23;
        final char calculatedCheckSumCharIe;
        if (calculatedCheckSumIe == 0) {
          calculatedCheckSumCharIe = 'W';
        } else {
          calculatedCheckSumCharIe = (char) ('A' + calculatedCheckSumIe - 1);
        }
        checkSumOk = checkSumIe == calculatedCheckSumCharIe;
        break;
      case "IT":
        final int checkSumIt = pvatId.charAt(12) - '0';
        final int sumIt =
            pvatId.charAt(2) - '0' + squareSum((pvatId.charAt(3) - '0') * 2) + pvatId.charAt(4)
                - '0' + squareSum((pvatId.charAt(5) - '0') * 2) + pvatId.charAt(6) - '0'
                + squareSum((pvatId.charAt(7) - '0') * 2) + pvatId.charAt(8) - '0'
                + squareSum((pvatId.charAt(9) - '0') * 2) + pvatId.charAt(10) - '0'
                + squareSum((pvatId.charAt(11) - '0') * 2);
        final int calculatedCheckSumIt = 10 - sumIt % 10;
        checkSumOk = checkSumIt == calculatedCheckSumIt;
        break;
      case "LU":
        final int numberPartLu = Integer.parseInt(pvatId.substring(2, 8));
        final int checkSumLu = Integer.parseInt(pvatId.substring(8));
        final int calculatedCheckSumLu = numberPartLu % 89;
        checkSumOk = checkSumLu == calculatedCheckSumLu;
        break;
      case "NL":
        final int checkSumNl = pvatId.charAt(10) - '0';
        final int sumNl =
            (pvatId.charAt(2) - '0') * 9 + (pvatId.charAt(3) - '0') * 8 + (pvatId.charAt(4) - '0')
                * 7 + (pvatId.charAt(5) - '0') * 6 + (pvatId.charAt(6) - '0') * 5
                + (pvatId.charAt(7) - '0') * 4 + (pvatId.charAt(8) - '0') * 3
                + (pvatId.charAt(9) - '0') * 2;
        final int calculatedCheckSumNl = sumNl % modulo11;
        checkSumOk = checkSumNl == calculatedCheckSumNl;
        break;
      case "PL":
        final int checkSumPl = pvatId.charAt(11) - '0';
        final int sumPl =
            (pvatId.charAt(2) - '0') * 6 + (pvatId.charAt(3) - '0') * 5 + (pvatId.charAt(4) - '0')
                * 7 + (pvatId.charAt(5) - '0') * 2 + (pvatId.charAt(6) - '0') * 3
                + (pvatId.charAt(7) - '0') * 4 + (pvatId.charAt(8) - '0') * 5
                + (pvatId.charAt(9) - '0') * 6 + (pvatId.charAt(10) - '0') * 7;
        final int calculatedCheckSumPl = sumPl % modulo11;
        checkSumOk = checkSumPl == calculatedCheckSumPl;
        break;
      case "PT":
        final int checkSumPt = pvatId.charAt(10) - '0';
        final int sumPt =
            (pvatId.charAt(2) - '0') * 9 + (pvatId.charAt(3) - '0') * 8 + (pvatId.charAt(4) - '0')
                * 7 + (pvatId.charAt(5) - '0') * 6 + (pvatId.charAt(6) - '0') * 5
                + (pvatId.charAt(7) - '0') * 4 + (pvatId.charAt(8) - '0') * 3
                + (pvatId.charAt(9) - '0') * 2;
        final int calculatedCheckSumPt = modulo11 - sumPt % modulo11;
        checkSumOk = checkSumPt == calculatedCheckSumPt;
        break;
      case "SE":
        final int checkSumSe = pvatId.charAt(11) - '0';
        final int sumSe =
            squareSum((pvatId.charAt(2) - '0') * 2) + pvatId.charAt(3) - '0'
                + squareSum((pvatId.charAt(4) - '0') * 2) + pvatId.charAt(5) - '0'
                + squareSum((pvatId.charAt(6) - '0') * 2) + pvatId.charAt(7) - '0'
                + squareSum((pvatId.charAt(8) - '0') * 2) + pvatId.charAt(9) - '0'
                + squareSum((pvatId.charAt(10) - '0') * 2);
        final int calculatedCheckSumSe = 10 - sumSe % 10;
        checkSumOk = checkSumSe == calculatedCheckSumSe;
        break;
      case "SI":
        final int checkSumSi = pvatId.charAt(9) - '0';
        final int sumSi =
            (pvatId.charAt(2) - '0') * 8 + (pvatId.charAt(3) - '0') * 7 + (pvatId.charAt(4) - '0')
                * 6 + (pvatId.charAt(5) - '0') * 5 + (pvatId.charAt(6) - '0') * 4
                + (pvatId.charAt(7) - '0') * 3 + (pvatId.charAt(8) - '0') * 2;
        final int calculatedCheckSumSi = modulo11 - sumSi % modulo11;
        checkSumOk = checkSumSi == calculatedCheckSumSi;
        break;
      case "ES":
        final int checkSumEs = pvatId.charAt(10) - '0';
        final int sumEs =
            squareSum((pvatId.charAt(3) - '0') * 2) + pvatId.charAt(4) - '0'
                + squareSum((pvatId.charAt(5) - '0') * 2) + pvatId.charAt(6) - '0'
                + squareSum((pvatId.charAt(7) - '0') * 2) + pvatId.charAt(8) - '0'
                + squareSum((pvatId.charAt(9) - '0') * 2);
        final int calculatedCheckSumEs = 10 - sumEs % 10;
        checkSumOk = checkSumEs == calculatedCheckSumEs;
        break;
      default:
        // for other countries, I haven't found checksum rules
        checkSumOk = true;
        break;
    }
    return checkSumOk;
  }

  private static int squareSum(final int pvalue) {
    int result = 0;
    for (final char valueDigit : String.valueOf(pvalue).toCharArray()) {
      result += Character.digit(valueDigit, 10);
    }
    return result;
  }
}
