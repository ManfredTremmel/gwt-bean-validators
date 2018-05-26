/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package org.hibernate.validator.internal.constraintvalidators.hv;

import de.knightsoftnet.validators.client.constraints.AbstractNotGwtCompatibleValidator;

import org.hibernate.validator.constraints.ParameterScriptAssert;

import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

/**
 * Validator for the {@link ParameterScriptAssert} constraint annotation.
 *
 * @author Gunnar Morling
 * @author Manfred Tremmel - GWT port
 */
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class ParameterScriptAssertValidator
    extends AbstractNotGwtCompatibleValidator<ParameterScriptAssert, Object[]> {
}
