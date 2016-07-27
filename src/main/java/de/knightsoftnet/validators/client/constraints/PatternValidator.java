/*
 * Copyright 2010 Google Inc. Copyright 2016 Manfred Tremmel
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.knightsoftnet.validators.client.constraints;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;

/**
 * {@link Pattern} constraint validator implementation.
 * <p>
 * Note this implementation uses {@link RegExp} which differs from {@link java.util.regex.Pattern}.
 * </p>
 */
public class PatternValidator implements ConstraintValidator<Pattern, String> {
  private RegExp pattern;

  @Override
  public final void initialize(final Pattern annotation) {
    final Pattern.Flag[] flags = annotation.flags();
    final StringBuilder flagString = new StringBuilder();
    for (final Pattern.Flag flag : flags) {
      flagString.append(this.toString(flag));
    }
    this.pattern = RegExp.compile(annotation.regexp(), flagString.toString());
  }

  @Override
  public final boolean isValid(final String value, final ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    final MatchResult match = this.pattern.exec(value);
    if (match == null) {
      return false;
    }
    // Must match the entire string
    return match.getGroup(0).length() == value.length();
  }

  private final String toString(final Flag flag) {
    String value;
    switch (flag) {
      case CASE_INSENSITIVE:
      case UNICODE_CASE:
        value = "i";
        break;
      case MULTILINE:
        value = "m";
        break;
      default:
        throw new IllegalArgumentException(flag + " is not a suppoted gwt Pattern (RegExp) flag");
    }
    return value;
  }
}
