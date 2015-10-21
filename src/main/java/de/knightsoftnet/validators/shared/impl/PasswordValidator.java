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

import de.knightsoftnet.validators.shared.Password;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check passwords if they fulfill some complexity rules.
 * <ul>
 * <li>lowercase</li>
 * <li>upercase</li>
 * <li>digits</li>
 * <li>special character</li>
 * </ul>
 * Using <code>blacklist</code> you can give a comma separated list of words which are not allowed
 * to be part of the password. Default is no entry.<br>
 * Using <code>disalowedStartChars</code> you can define characters which are not allowed as first
 * character in the password. Default is no entry.<br>
 * With <code>maxRepeatChar</code> you can limit the repeat of a single character, default is 0
 * which means no limitation.<br>
 * size limits should be added by separate size annotation.
 *
 * @author Manfred Tremmel
 *
 */
public class PasswordValidator implements ConstraintValidator<Password, Object> {

  /**
   * patterns to check.
   */
  private static final String[] PATTERNS = new String[] {"[a-z]", "[A-Z]", "[0-9]", "[^\\s]"};

  /**
   * localized message if blacklisted.
   */
  private String messageBlacklist;

  /**
   * localized message if start character is not allowed.
   */
  private String messageStartCharacters;

  /**
   * localized message if maximum repeat of a char is reached.
   */
  private String messageMaxRepeat;

  /**
   * minimum number rules that have to be fulfilled.
   */
  private int minRules;

  /**
   * Comma separated list of words which are not allowed as part of the password.
   */
  private List<String> blacklist;

  /**
   * Characters which are not allowed at the beginning of a password.
   */
  private char[] disalowedStartChars;

  /**
   * maximum repeats of a single character in a row.
   */
  private int maxRepeatChar;

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final Password pconstraintAnnotation) {
    this.messageBlacklist = pconstraintAnnotation.messageBlacklist();
    this.messageStartCharacters = pconstraintAnnotation.messageStartCharacters();
    this.messageMaxRepeat = pconstraintAnnotation.messageMaxRepeat();
    this.minRules = pconstraintAnnotation.minRules();
    if (pconstraintAnnotation.blacklist() == null) {
      this.blacklist = Collections.emptyList();
    } else {
      final String[] entries = pconstraintAnnotation.blacklist().split(",");
      this.blacklist = new ArrayList<>(entries.length);
      for (final String entry : entries) {
        if (StringUtils.isNotEmpty(entry)) {
          this.blacklist.add(entry.trim().toLowerCase());
        }
      }
    }
    if (StringUtils.isEmpty(pconstraintAnnotation.disalowedStartChars())) {
      this.disalowedStartChars = null;
    } else {
      this.disalowedStartChars = pconstraintAnnotation.disalowedStartChars().toCharArray();
    }
    this.maxRepeatChar = pconstraintAnnotation.maxRepeatChar();
  }

  /**
   * {@inheritDoc} check if given object is valid.
   *
   * @see javax.validation.ConstraintValidator#isValid(Object,
   *      javax.validation.ConstraintValidatorContext)
   */
  @Override
  public final boolean isValid(final Object pvalue, final ConstraintValidatorContext pcontext) {
    final String valueAsString = Objects.toString(pvalue, null);
    return StringUtils.isEmpty(valueAsString)
        || this.countCriteriaMatches(valueAsString) >= this.minRules
            && !this.isBlacklist(pcontext, valueAsString)
            && !this.startsWithDisalowedCharacter(pcontext, valueAsString)
            && !this.maxRepeatCharacterExceded(pcontext, valueAsString);
  }

  private int countCriteriaMatches(final String ppassword) {
    String password = ppassword;
    int fulFilledCriterias = 0;
    for (final String pattern : PasswordValidator.PATTERNS) {
      if (password.matches(".*" + pattern + ".*")) {
        fulFilledCriterias += 1;
        password = password.replaceAll(pattern, StringUtils.EMPTY);
      }
    }
    return fulFilledCriterias;
  }

  private boolean isBlacklist(final ConstraintValidatorContext pcontext,
      final String pvalueAsString) {
    if (!this.blacklist.isEmpty()) {
      final String valueLowerCase = pvalueAsString.toLowerCase();
      for (final String blacklistEntry : this.blacklist) {
        if (valueLowerCase.contains(blacklistEntry)) {
          pcontext.disableDefaultConstraintViolation();
          pcontext.buildConstraintViolationWithTemplate(this.messageBlacklist)
              .addConstraintViolation();
          return true;
        }
      }
    }
    return false;
  }

  private boolean startsWithDisalowedCharacter(final ConstraintValidatorContext pcontext,
      final String pvalueAsString) {
    if (this.disalowedStartChars != null) {
      final char firstChar = pvalueAsString.charAt(0);
      for (final char startChar : this.disalowedStartChars) {
        if (firstChar == startChar) {
          pcontext.disableDefaultConstraintViolation();
          pcontext.buildConstraintViolationWithTemplate(this.messageStartCharacters)
              .addConstraintViolation();
          return true;
        }
      }
    }
    return false;
  }

  private boolean maxRepeatCharacterExceded(final ConstraintValidatorContext pcontext,
      final String pvalueAsString) {
    if (this.maxRepeatChar <= 1) {
      return false;
    }
    int currentCount = 0;
    char oldChar = Character.MIN_VALUE;
    for (final char singleChar : pvalueAsString.toLowerCase().toCharArray()) {
      if (singleChar == oldChar) {
        currentCount++;
        if (currentCount >= this.maxRepeatChar) {
          pcontext.disableDefaultConstraintViolation();
          pcontext.buildConstraintViolationWithTemplate(this.messageMaxRepeat)
              .addConstraintViolation();
          return true;
        }
      } else {
        currentCount = 0;
        oldChar = singleChar;
      }
    }
    return false;
  }
}
