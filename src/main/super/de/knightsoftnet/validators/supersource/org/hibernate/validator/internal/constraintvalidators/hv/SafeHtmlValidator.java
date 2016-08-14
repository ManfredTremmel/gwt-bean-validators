/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0 See the license.txt file in the root directory or
 * <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package org.hibernate.validator.internal.constraintvalidators.hv;

import de.knightsoftnet.validators.client.constraints.AbstractNotGwtCompatibleValidator;

import org.hibernate.validator.constraints.SafeHtml;

/**
 * Validate that the string does not contain malicious code.
 *
 * <p>
 * It uses <a href="http://www.jsoup.org">JSoup</a> as the underlying parser/sanitizer library.
 * </p>
 *
 * @author George Gastaldi
 * @author Hardy Ferentschik
 * @author Manfred Tremmel - GWT port
 */
public class SafeHtmlValidator extends AbstractNotGwtCompatibleValidator<SafeHtml, CharSequence> {
}
