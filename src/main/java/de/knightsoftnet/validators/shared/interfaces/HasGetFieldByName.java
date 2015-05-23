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

package de.knightsoftnet.validators.shared.interfaces;

import de.knightsoftnet.validators.client.AbstractGwtReflectGetterFactory;

/**
 * interface for beans which provides a get field by name method.
 *
 * @author Manfred Tremmel
 *
 * @deprecated As of release 0.6.0, replaced by {@link AbstractGwtReflectGetterFactory}
 */
@Deprecated
public interface HasGetFieldByName {

  /**
   * get a field by name.
   *
   * @param pname name of the field
   * @return value of the field.
   */
  Object getFieldByName(String pname);
}
