/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package org.hibernate.validator.internal.constraintvalidators.bv.past;

import de.knightsoftnet.validators.client.constraints.AbstractNotGwtCompatibleValidator;

import org.hibernate.validator.internal.util.IgnoreJava6Requirement;

import javax.validation.constraints.Future;

/**
 * Check that the {@code java.time.OffsetDateTime} passed is in the past.
 *
 * @author Khalid Alqinyah
 * @author Khalid Alqinyah
 * @author Manfred Tremmel - disabled for GWT, no OffsetDateTime available
 */
@IgnoreJava6Requirement
public class PastValidatorForOffsetDateTime
    extends AbstractNotGwtCompatibleValidator<Future, Object> {
}
