/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.gwt.validation.client.impl;

import de.knightsoftnet.validators.client.spi.GwtValidationProvider;

import javax.validation.spi.BootstrapState;

/**
 * Dummy to fix compile problems as long validation-api 1.0 implementation is part of gwt.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtConfiguration extends de.knightsoftnet.validators.client.impl.GwtConfiguration {

  public GwtConfiguration(final GwtValidationProvider pgwtValidationProvider,
      final BootstrapState pstate) {
    super(pgwtValidationProvider, pstate);
  }
}
