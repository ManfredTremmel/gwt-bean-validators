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

/**
 * Simple GWT {@link javax.validation.MessageInterpolator}.
 */
public final class GwtMessageInterpolator extends AbstractBaseMessageInterpolator {
  // This class only has the parts the need to overridden for GWT

  /**
   * Creates a {@link javax.validation.MessageInterpolator MessageInterpolator} MessageInterpolator
   * that uses the default {@link UserValidationMessagesResolver}.
   */
  public GwtMessageInterpolator() { // NOPMD
    this((UserValidationMessagesResolver) GWT.create(UserValidationMessagesResolver.class));
  }

  /**
   * Creates a {@link javax.validation.MessageInterpolator MessageInterpolator} using the supplie
   * {@link UserValidationMessagesResolver}.
   *
   * @param userValidationMessagesResolver user validation messages resolver
   */
  public GwtMessageInterpolator(
      final UserValidationMessagesResolver userValidationMessagesResolver) {
    super(userValidationMessagesResolver);
  }

  public String interpolate(final String messageTemplate, final Context context,
      final GwtLocale locale) {
    return this.gwtInterpolate(messageTemplate, context, locale);
  }
}
