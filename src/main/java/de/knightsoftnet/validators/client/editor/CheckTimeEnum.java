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

package de.knightsoftnet.validators.client.editor;

/**
 * Enumeration of times when check should be done.
 * 
 * @author Manfred Tremmel
 *
 */
public enum CheckTimeEnum {
  /**
   * Check on every key up, so when ever a sign is typed in, a check is done. It includes a check on
   * change and a last check on submit. This is the default setting.
   */
  ON_KEY_UP,

  /**
   * Check when ever a field sends a change event, so text fields initiates a validation if cursor
   * leafs them. It includes a check on on submit.
   */
  ON_CHANGE,

  /**
   * Check is done, when submit.
   */
  ON_SUBMIT
}
