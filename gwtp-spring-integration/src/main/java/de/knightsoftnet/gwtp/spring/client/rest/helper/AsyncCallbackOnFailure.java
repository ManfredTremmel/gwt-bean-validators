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

import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;

public interface AsyncCallbackOnFailure {

  /**
   * Called when an asynchronous call fails to complete normally.
   * {@link IncompatibleRemoteServiceException}s, {@link InvocationException}s, or checked
   * exceptions thrown by the service method are examples of the type of failures that can be passed
   * to this method.
   *
   * <p>
   * If <code>caught</code> is an instance of an {@link IncompatibleRemoteServiceException} the
   * application should try to get into a state where a browser refresh can be safely done.
   * </p>
   *
   * @param caught failure encountered while executing a remote procedure call
   */
  void onFailure(Throwable caught);
}
