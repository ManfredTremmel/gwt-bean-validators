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

package de.knightsoftnet.gwtp.spring.client.rest.helper;

import com.google.gwt.i18n.client.Messages;

/**
 * Localized values of http states.
 *
 * @author Manfred Tremmel
 *
 */
public interface HttpMessages extends Messages {

  /**
   * convert message from given http code.
   *
   * @param phttpCode http code
   * @return messageHttpCode
   */
  @DefaultMessage("Unknown response")
  String messageHttpCode(@Select int phttpCode);
}
