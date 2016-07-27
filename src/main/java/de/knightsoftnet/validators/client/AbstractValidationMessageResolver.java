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

import com.google.gwt.i18n.client.ConstantsWithLookup;

import java.util.MissingResourceException;

/**
 * ValidationMessageResolver using a {@link ConstantsWithLookup} source.
 */
public abstract class AbstractValidationMessageResolver { // NOPMD
  private final ConstantsWithLookup messages;

  protected AbstractValidationMessageResolver(final ConstantsWithLookup pmessages) {
    this.messages = pmessages;
  }

  /**
   * get localized message for key.
   *
   * @param pkey key of message
   * @return localized text for the key
   */
  public final String get(final String pkey) {
    try {
      // Replace "." with "_" in the key to match what ConstantsImplCreator does.
      return pkey == null ? null : this.messages.getString(pkey.replace(".", "_"));
    } catch (final MissingResourceException e) {
      return null;
    }
  }
}
