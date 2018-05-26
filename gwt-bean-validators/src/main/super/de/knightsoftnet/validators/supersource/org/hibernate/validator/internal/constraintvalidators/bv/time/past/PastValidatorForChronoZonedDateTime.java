/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package org.hibernate.validator.internal.constraintvalidators.bv.time.past;

import de.knightsoftnet.validators.client.constraints.AbstractNotGwtCompatibleValidator;

import javax.validation.constraints.Past;

/**
 * Check that the {@code java.time.chrono.ChronoZonedDateTime} passed is in the past.
 *
 * @author Khalid Alqinyah
 * @author Guillaume Smet
 * @author Manfred Tremmel - disabled for GWT, no ChronoZonedDateTime available
 */
public class PastValidatorForChronoZonedDateTime
    extends AbstractNotGwtCompatibleValidator<Past, Object> {
}
