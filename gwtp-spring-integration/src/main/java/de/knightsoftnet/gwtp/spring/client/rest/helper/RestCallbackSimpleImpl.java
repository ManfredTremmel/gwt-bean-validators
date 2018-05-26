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

import de.knightsoftnet.gwtp.spring.client.session.Session;

/**
 * Async callback implementation with error handling.
 *
 * @author Manfred Tremmel
 *
 * @param <V> view or widget which implements HasShowMessage interface
 * @param <R> rest result type
 * @param <H> http response message type
 */
public class RestCallbackSimpleImpl<V extends HasShowMessage, R, H extends HttpMessages>
    extends AbstractSimpleRestCallback<V, R, H> {

  private final AsyncCallbackOnSuccess<R> callbackOnSuccess;

  /**
   * constructor.
   *
   * @param pview view
   * @param psession session data
   * @param pcallbackOnSuccess async callback which is called on success
   */
  public RestCallbackSimpleImpl(final V pview, final Session psession,
      final AsyncCallbackOnSuccess<R> pcallbackOnSuccess) {
    super(pview, psession);
    this.callbackOnSuccess = pcallbackOnSuccess;
  }

  /**
   * constructor.
   *
   * @param pview view
   * @param psession session data
   * @param phttpMessage http message
   * @param pcallbackOnSuccess async callback which is called on success
   */
  public RestCallbackSimpleImpl(final V pview, final Session psession, final H phttpMessage,
      final AsyncCallbackOnSuccess<R> pcallbackOnSuccess) {
    super(pview, psession, phttpMessage);
    this.callbackOnSuccess = pcallbackOnSuccess;
  }

  @Override
  public void onSuccess(final R presult) {
    this.callbackOnSuccess.onSuccess(presult);
  }
}
