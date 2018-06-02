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

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.knightsoftnet.gwtp.spring.client.session.Session;
import de.knightsoftnet.gwtp.spring.shared.models.User;

/**
 * Async callback implementation with error handling.
 *
 * @author Manfred Tremmel
 *
 */
public class RestCallbackBuilder {

  /**
   * create rest callback implementation.
   *
   * @param pcallbackOnSuccess on success callback
   * @param pcallbackOnFailure on failure callback
   * @param <R> rest result type
   * @return RestCallbackImpl
   */
  public static <R> AsyncCallback<R> build(final AsyncCallbackOnSuccess<R> pcallbackOnSuccess,
      final AsyncCallbackOnFailure pcallbackOnFailure) {
    return new AsyncCallback<R>() {

      @Override
      public void onFailure(final Throwable pcaught) {
        pcallbackOnFailure.onFailure(pcaught);
      }

      @Override
      public void onSuccess(final R presult) {
        pcallbackOnSuccess.onSuccess(presult);
      }
    };
  }

  /**
   * create rest callback implementation.
   *
   * @param pview view of the
   * @param psession session
   * @param pcallbackOnSuccess on success callback
   * @param <P> presenter type
   * @param <V> view or widget which implements EditorWithErrorHandling interface
   * @param <R> rest result type
   * @return RestCallbackImpl
   */
  public static <V extends HasShowMessage, R, H extends HttpMessages> RestCallbackSimpleImpl<V, //
      R, H> build(final V pview, final Session psession,
          final AsyncCallbackOnSuccess<R> pcallbackOnSuccess) {
    return new RestCallbackSimpleImpl<>(pview, psession, pcallbackOnSuccess);
  }

  /**
   * create rest callback implementation.
   *
   * @param pview view of the
   * @param psession session
   * @param pcallbackOnSuccess on success callback
   * @param phttpMessage http code messages to show
   * @param <P> presenter type
   * @param <V> view or widget which implements EditorWithErrorHandling interface
   * @param <R> rest result type
   * @return RestCallbackImpl
   */
  public static <V extends HasShowMessage, R, H extends HttpMessages> RestCallbackSimpleImpl<V, //
      R, H> build(final V pview, final Session psession,
          final AsyncCallbackOnSuccess<R> pcallbackOnSuccess, final H phttpMessage) {
    return new RestCallbackSimpleImpl<>(pview, psession, phttpMessage, pcallbackOnSuccess);
  }

  /**
   * create rest callback implementation.
   *
   * @param pview view of the
   * @param pdata data given from server
   * @param psession session
   * @param pcallbackOnSuccess on success callback
   * @param <P> presenter type
   * @param <D> data type given to the server
   * @param <V> view or widget which implements EditorWithErrorHandling interface
   * @param <R> rest result type
   * @param <H> http messages
   * @return RestCallbackImpl
   */
  public static <P, D, V extends EditorWithErrorHandling<P, D>, R, //
      H extends HttpMessages> RestCallbackImpl<P, D, V, R, H> build(final V pview, final D pdata,
          final Session psession, final AsyncCallbackOnSuccess<R> pcallbackOnSuccess) {
    return new RestCallbackImpl<>(pview, pdata, psession, pcallbackOnSuccess);
  }

  /**
   * create rest callback implementation.
   *
   * @param pview view of the
   * @param pdata data given from server
   * @param psession session
   * @param phttpMessage http code messages to show
   * @param pcallbackOnSuccess on success callback
   * @param <P> presenter type
   * @param <D> data type given to the server
   * @param <V> view or widget which implements EditorWithErrorHandling interface
   * @param <R> rest result type
   * @param <H> http messages
   * @return RestCallbackImpl
   */
  public static <P, D, V extends EditorWithErrorHandling<P, D>, R, //
      H extends HttpMessages> RestCallbackImpl<P, D, V, R, H> build(final V pview, final D pdata,
          final Session psession, final H phttpMessage,
          final AsyncCallbackOnSuccess<R> pcallbackOnSuccess) {
    return new RestCallbackImpl<>(pview, pdata, psession, phttpMessage, pcallbackOnSuccess);
  }

  /**
   * create login callback implementation.
   *
   * @param pview view of the
   * @param psession session
   * @param <T> user data
   * @param <V> view or widget which implements EditorWithErrorHandling interface
   * @param <M> login messages
   * @param <H> http messages
   * @return LoginCallback
   */
  public static <T extends User, V extends EditorWithErrorHandling<?, ?>, //
      M extends LoginMessages, H extends HttpMessages> LoginCallback<T, V, M, H> buildLoginCallback(
          final V pview, final Session psession) {
    return new LoginCallback<>(pview, psession);
  }

  /**
   * create login callback implementation.
   *
   * @param pview view of the
   * @param psession session
   * @param ploginErrorMessage error message to show
   * @param <T> user data
   * @param <V> view or widget which implements EditorWithErrorHandling interface
   * @param <M> login messages
   * @param <H> http messages
   * @return LoginCallback
   */
  public static <T extends User, V extends EditorWithErrorHandling<?, ?>, //
      M extends LoginMessages, H extends HttpMessages> LoginCallback<T, V, M, H> buildLoginCallback(
          final V pview, final Session psession, final M ploginErrorMessage) {
    return new LoginCallback<>(pview, psession, ploginErrorMessage);
  }

  /**
   * create login callback implementation.
   *
   * @param pview view of the
   * @param psession session
   * @param ploginErrorMessage error message to show
   * @param phttpMessage http code messages to show
   * @param <T> user data
   * @param <V> view or widget which implements EditorWithErrorHandling interface
   * @param <M> login messages
   * @param <H> http messages
   * @return LoginCallback
   */
  public static <T extends User, V extends EditorWithErrorHandling<?, ?>, //
      M extends LoginMessages, H extends HttpMessages> LoginCallback<T, V, M, H> buildLoginCallback(
          final V pview, final Session psession, final M ploginErrorMessage, final H phttpMessage) {
    return new LoginCallback<>(pview, psession, ploginErrorMessage, phttpMessage);
  }
}
