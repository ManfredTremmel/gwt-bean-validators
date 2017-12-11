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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.ValidationProviderResolver;
import javax.validation.spi.ValidationProvider;

/**
 * The default GWT {@link ValidationProviderResolver}. This always returns the single default
 * ValidationProvider using {@link GWT#create(Class)}.
 */
public final class GwtValidationProviderResolver implements ValidationProviderResolver {

  private static final List<ValidationProvider<?>> DEFAULT_LIST =
      Collections.unmodifiableList(createValidationProviderList());

  private static ArrayList<ValidationProvider<?>> createValidationProviderList() {
    final ArrayList<ValidationProvider<?>> temp = new ArrayList<>();
    final ValidationProvider<?> provider = GWT.create(ValidationProvider.class);
    temp.add(provider);
    return temp;
  }

  @Override
  public List<ValidationProvider<?>> getValidationProviders() {
    return DEFAULT_LIST;
  }
}
