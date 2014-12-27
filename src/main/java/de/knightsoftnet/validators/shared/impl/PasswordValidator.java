package de.knightsoftnet.validators.shared.impl;

import de.knightsoftnet.validators.shared.Password;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check passwords if they fulfill some complexity rules.
 * <ul>
 * <li>upper-/lowercase</li>
 * <li>digits</li>
 * <li>special character</li>
 * </ul>
 * size limits should be added by separate size annotation.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
public class PasswordValidator implements ConstraintValidator<Password, Object> {

  /**
   * patterns to check.
   */
  private static final String[] PATTERNS = new String[] {"[a-z]", "[A-Z]", "[0-9]", "[^\\s]"};

  /**
   * minimum number rules that have to be fulfilled.
   */
  private int minRules;

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final Password pconstraintAnnotation) {
    this.minRules = pconstraintAnnotation.minRules();
  }

  /**
   * {@inheritDoc} check if given object.
   *
   * @see javax.validation.ConstraintValidator#isValid(Object,
   *      javax.validation.ConstraintValidatorContext)
   */
  @Override
  public final boolean isValid(final Object pvalue, final ConstraintValidatorContext pcontext) {
    final String valueAsString = ObjectUtils.toString(pvalue);
    if (StringUtils.isEmpty(valueAsString)) {
      return true;
    }
    return this.minRules < this.countCriteriaMatches(valueAsString);
  }

  /**
   * count criteria matches.
   *
   * @param ppassword the password to check
   * @return number of fulfilled criteria matches with the given pw
   */
  private int countCriteriaMatches(final String ppassword) {
    String password = ppassword;
    int fulFilledCriterias = 0;
    for (final String pattern : PasswordValidator.PATTERNS) {
      if (password.matches(".*" + pattern + ".*")) {
        fulFilledCriterias += 1;
        password = password.replaceAll(pattern, "");
      }
    }
    return fulFilledCriterias;
  }
}
