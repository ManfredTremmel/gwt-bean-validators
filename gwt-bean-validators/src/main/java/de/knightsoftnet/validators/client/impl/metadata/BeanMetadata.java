/*
 * Copyright 2012 Google Inc. Copyright 2016 Manfred Tremmel
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

package de.knightsoftnet.validators.client.impl.metadata;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.validation.groups.Default;

/**
 * Describes information about a bean which is used for validation purposes.
 */
public class BeanMetadata {
  private final Class<?> beanClass;
  private final List<Class<?>> defaultGroupSequence;
  private final boolean defaultGroupSequenceRedefined;

  /**
   * constructor.
   *
   * @param beanClass bean class to validate
   * @param defaultGroupSequence group class
   */
  public BeanMetadata(final Class<?> beanClass, final Class<?>... defaultGroupSequence) {
    this.beanClass = beanClass;
    this.defaultGroupSequence = Collections.unmodifiableList(Arrays.asList(defaultGroupSequence));
    defaultGroupSequenceRedefined =
        !(defaultGroupSequence.length == 1 && defaultGroupSequence[0].equals(Default.class));
  }

  public boolean defaultGroupSequenceIsRedefined() {
    return defaultGroupSequenceRedefined;
  }

  public Class<?> getBeanClass() {
    return beanClass;
  }

  public List<Class<?>> getDefaultGroupSequence() {
    return defaultGroupSequence;
  }
}
