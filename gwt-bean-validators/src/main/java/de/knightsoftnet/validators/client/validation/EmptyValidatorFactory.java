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

package de.knightsoftnet.validators.client.validation;

import de.knightsoftnet.validators.client.AbstractGwtValidatorFactory;
import de.knightsoftnet.validators.client.GwtValidation;
import de.knightsoftnet.validators.client.impl.AbstractGwtValidator;

import com.google.gwt.core.shared.GWT;

import javax.validation.Validator;

/**
 * The <code>EmptyValidatorFactory</code> class can be used when no groups are used and all
 * validation is done by BeanValidatonEditorDriver.
 *
 * @author Manfred Tremmel
 */
public class EmptyValidatorFactory extends AbstractGwtValidatorFactory {

  /**
   * Validator without entries.
   */
  @GwtValidation(value = {})
  public interface GwtValidator extends Validator {
  }

  @Override
  public final AbstractGwtValidator createValidator() {
    return GWT.create(GwtValidator.class);
  }
}
