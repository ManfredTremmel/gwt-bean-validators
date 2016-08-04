/*
 * Copyright 2010 Google Inc. Copyright 2016 Manfred Tremmel
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.knightsoftnet.validators.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.shared.GwtLocale;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;

import java.util.Locale;
import java.util.Map;

import javax.validation.MessageInterpolator;

/**
 * Base GWT {@link MessageInterpolator}.
 */
abstract class AbstractBaseMessageInterpolator implements MessageInterpolator {

  // local version because guava is not included.
  private interface Function<F, T> {
    T apply(F from);
  }

  /**
   * Regular expression used to do message interpolation.
   */
  private static final RegExp MESSAGE_PARAMETER_PATTERN = RegExp.compile("(\\{[^\\}]+?\\})", "g");

  // Visible for testing
  static Function<String, String> createAnnotationReplacer(final Map<String, Object> map) {
    return new Function<String, String>() {

      @Override
      public String apply(final String from) {
        final Object object = map.get(from);
        return object == null ? null : object.toString();
      }
    };
  }

  private static Function<String, String> createReplacer(
      final ValidationMessageResolver messageResolver) {
    return new Function<String, String>() {
      @Override
      public String apply(final String from) {
        final Object object = messageResolver.get(from);
        return object == null ? null : object.toString();
      }
    };
  }

  /**
   * Replaces keys using the Default Validation Provider properties.
   */
  private final Function<String, String> providerReplacer = createReplacer(
      (ValidationMessageResolver) GWT.create(ProviderValidationMessageResolver.class));

  /**
   * Replaces keys using the Validation User custom properties.
   */
  private final Function<String, String> userReplacer;

  protected AbstractBaseMessageInterpolator(
      final UserValidationMessagesResolver userValidationMessagesResolver) {
    this.userReplacer = createReplacer(userValidationMessagesResolver);
  }

  @Override
  public final String interpolate(final String messageTemplate, final Context context) {
    return this.gwtInterpolate(messageTemplate, context, null);
  }

  @Override
  public String interpolate(final String messageTemplate, final Context context,
      final Locale locale) {
    // The super sourced GWT version of this calls gwtInterpolate
    return messageTemplate;
  }

  protected final String gwtInterpolate(final String message, final Context context,
      final GwtLocale locale) {
    // see Section 4.3.1.1
    String resolvedMessage = message;
    String step1message;

    // TODO(nchalko) Add a safety to make sure this does not loop forever.
    do {
      do {
        step1message = resolvedMessage;

        // Step 1 Replace message parameters using custom user messages
        // repeat
        resolvedMessage = this.replaceParameters(resolvedMessage, this.userReplacer);
      } while (!step1message.equals(resolvedMessage));

      // Step2 Replace message parameter using the default provider messages.
      resolvedMessage = this.replaceParameters(resolvedMessage, this.providerReplacer);

      // Step 3 repeat from step 1 if step 2 made changes.
    } while (!step1message.equals(resolvedMessage));

    // step 4 resolve annotation attributes
    resolvedMessage = this.replaceParameters(resolvedMessage,
        createAnnotationReplacer(context.getConstraintDescriptor().getAttributes()));

    // Remove escapes (4.3.1)
    resolvedMessage = resolvedMessage.replace("\\{", "{");
    resolvedMessage = resolvedMessage.replace("\\}", "}");
    resolvedMessage = resolvedMessage.replace("\\\\", "\\");
    return resolvedMessage;
  }

  protected final String replaceParameters(final String message,
      final Function<String, String> replacer) {
    final StringBuilder sb = new StringBuilder();
    int index = 0;

    MatchResult matcher = MESSAGE_PARAMETER_PATTERN.exec(message);
    while (matcher != null) {
      final String matched = matcher.getGroup(0);
      sb.append(message.substring(index, matcher.getIndex()));
      final Object value = replacer.apply(this.removeCurlyBrace(matched));
      sb.append(value == null ? matched : value);
      index = MESSAGE_PARAMETER_PATTERN.getLastIndex();
      matcher = MESSAGE_PARAMETER_PATTERN.exec(message);
    }
    if (index < message.length()) {
      sb.append(message.substring(index));
    }
    return sb.toString();
  }

  private String removeCurlyBrace(final String parameter) {
    return parameter.substring(1, parameter.length() - 1);
  }
}
