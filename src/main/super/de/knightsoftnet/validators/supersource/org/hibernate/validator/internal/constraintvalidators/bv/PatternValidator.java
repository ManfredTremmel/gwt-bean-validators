/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package org.hibernate.validator.internal.constraintvalidators.bv;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.hibernate.validator.internal.engine.messageinterpolation.util.InterpolationHelper;
import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;

import java.lang.invoke.MethodHandles;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;

/**
 * pattern validator.
 *
 * @author Hardy Ferentschik
 * @author Manfred Tremmel - GWT port
 */
public class PatternValidator implements ConstraintValidator<Pattern, CharSequence> {

  private static final Log LOG = LoggerFactory.make(MethodHandles.lookup()); // NOPMD

  private RegExp pattern = null;
  private String escapedRegexp;

  @Override
  public void initialize(final Pattern parameters) {
    final Pattern.Flag[] flags = parameters.flags();
    final StringBuilder flagString = new StringBuilder();
    for (final Pattern.Flag flag : flags) {
      flagString.append(this.toString(flag));
    }
    try {
      this.pattern = RegExp.compile(parameters.regexp(), flagString.toString());
    } catch (final RuntimeException e) {
      throw LOG.getInvalidRegularExpressionException(e);
    }
    this.escapedRegexp = InterpolationHelper.escapeMessageParameter(parameters.regexp());
  }

  @Override
  public boolean isValid(final CharSequence value,
      final ConstraintValidatorContext constraintValidatorContext) {
    if (value == null || this.pattern == null) {
      return true;
    }
    if (constraintValidatorContext instanceof HibernateConstraintValidatorContext) {
      constraintValidatorContext.unwrap(HibernateConstraintValidatorContext.class)
          .addMessageParameter("regexp", this.escapedRegexp);
    }

    final MatchResult match = this.pattern.exec(value.toString());
    if (match == null) {
      return false;
    }
    // Must match the entire string
    return match.getGroup(0).length() == value.length();
  }

  private String toString(final Flag pflag) {
    String value;
    switch (pflag) {
      case CASE_INSENSITIVE:
      case UNICODE_CASE:
        value = "i";
        break;
      case MULTILINE:
        value = "m";
        break;
      default:
        throw LOG
            .getIllegalArgumentException(pflag + " is not a suppoted gwt Pattern (RegExp) flag");
    }
    return value;
  }
}
