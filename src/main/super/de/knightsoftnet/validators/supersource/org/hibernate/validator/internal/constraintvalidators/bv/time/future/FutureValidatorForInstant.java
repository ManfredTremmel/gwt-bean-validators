/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package org.hibernate.validator.internal.constraintvalidators.bv.time.future;

import de.knightsoftnet.validators.client.constraints.AbstractNotGwtCompatibleValidator;

import javax.validation.constraints.Future;

/**
 * Check that the {@code java.time.Instant} passed is in the future.
 *
 * @author Khalid Alqinyah
 * @author Guillaume Smet
 * @author Manfred Tremmel - disabled for GWT, no Instant available
 */
public class FutureValidatorForInstant extends AbstractNotGwtCompatibleValidator<Future, Object> {
}
