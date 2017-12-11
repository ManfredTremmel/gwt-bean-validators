/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent;

import de.knightsoftnet.validators.client.constraints.AbstractNotGwtCompatibleValidator;

import javax.validation.constraints.FutureOrPresent;

/**
 * Check if Joda Time type who implements {@code import org.joda.time.ReadableInstant} is in the
 * future.
 *
 * @author Kevin Pollet &lt;kevin.pollet@serli.com&gt; (C) 2011 SERLI
 * @author Manfred Tremmel - disabled for GWT, no ReadableInstant available
 */
public class FutureOrPresentValidatorForReadableInstant
    extends AbstractNotGwtCompatibleValidator<FutureOrPresent, Object> {
}
