/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package org.hibernate.validator.internal.constraintvalidators.bv;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;

import org.hibernate.validator.internal.constraintvalidators.AbstractEmailValidator;
import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;

import java.lang.invoke.MethodHandles;

import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;

/**
 * Checks that a given character sequence (e.g. string) is a well-formed email address.
 *
 * @author Guillaume Smet
 * @author Manfred Tremmel (GWT port)
 */
public class EmailValidator extends AbstractEmailValidator<Email> {

  private static final Log LOG = LoggerFactory.make(MethodHandles.lookup()); // NOPMD

  private RegExp pattern;

  @Override
  public void initialize(final Email emailAnnotation) {
    super.initialize(emailAnnotation);

    final Pattern.Flag[] flags = emailAnnotation.flags();
    final StringBuilder flagString = new StringBuilder();
    for (final Pattern.Flag flag : flags) {
      flagString.append(this.toString(flag));
    }

    // we only apply the regexp if there is one to apply
    if (!".*".equals(emailAnnotation.regexp()) || emailAnnotation.flags().length > 0) {
      try {
        this.pattern = RegExp.compile(emailAnnotation.regexp(), flagString.toString());
      } catch (final RuntimeException e) {
        throw LOG.getInvalidRegularExpressionException(e);
      }
    }
  }

  @Override
  public boolean isValid(final CharSequence value, final ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }

    final boolean isValid = super.isValid(value, context);
    if (this.pattern == null || !isValid) {
      return isValid;
    }

    final MatchResult matcher = this.pattern.exec(value.toString());
    return matcher != null;
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
