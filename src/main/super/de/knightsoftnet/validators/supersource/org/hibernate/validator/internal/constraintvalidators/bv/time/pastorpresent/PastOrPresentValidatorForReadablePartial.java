/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package org.hibernate.validator.internal.constraintvalidators.bv.time.pastorpresent;

import de.knightsoftnet.validators.client.constraints.AbstractNotGwtCompatibleValidator;

import javax.validation.constraints.PastOrPresent;

/**
 * Check if Joda Time type who implements {@code org.joda.time.ReadablePartial} is in the past.
 *
 * @author Kevin Pollet &lt;kevin.pollet@serli.com&gt; (C) 2011 SERLI
 * @author Manfred Tremmel - disabled for GWT, no ReadablePartial available
 */
public class PastOrPresentValidatorForReadablePartial
    extends AbstractNotGwtCompatibleValidator<PastOrPresent, Object> {
}
