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

package de.knightsoftnet.validators.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * Handler interface for {@link FormSubmitEvent} events.
 *
 * @param <T> the value about to be changed
 */
public interface FormSubmitHandler<T> extends EventHandler {

  /**
   * Called when {@link FormSubmitEvent} is fired.
   *
   * @param event the {@link FormSubmitEvent} that was fired
   */
  void onFormSubmit(FormSubmitEvent<T> event);
}
