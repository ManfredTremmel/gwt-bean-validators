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

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Response;
import com.gwtplatform.dispatch.rest.client.RestCallback;
import com.gwtplatform.dispatch.shared.ActionException;

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
public abstract class AbstractSimpleRestCallback<V extends HasShowMessage, R, //
    H extends HttpMessages> implements RestCallback<R> {

  protected final V view;
  protected final Session session;
  private final H httpMessage;
  private Response response;

  /**
   * constructor.
   *
   * @param pview view
   * @param psession session data
   */
  public AbstractSimpleRestCallback(final V pview, final Session psession) {
    this(pview, psession, GWT.create(HttpMessages.class));
  }

  /**
   * constructor.
   *
   * @param pview view
   * @param psession session data
   * @param phttpMessage http code messages to show
   */
  public AbstractSimpleRestCallback(final V pview, final Session psession, final H phttpMessage) {
    super();
    this.view = pview;
    this.session = psession;
    this.httpMessage = phttpMessage;
  }

  @Override
  public void onFailure(final Throwable pcaught) {
    try {
      throw pcaught;
    } catch (final ActionException e) {
      switch (this.response.getStatusCode()) {
        case 401: // Unauthorized: happens when session times out
        case 403: // Forbidden: happens when csrf token times out
          this.session.readSessionData();
          break;
        default:
          this.view.showMessage(this.httpMessage.messageHttpCode(this.response.getStatusCode()));
          break;
      }
    } catch (final Throwable e) {
      this.view.showMessage(e.getMessage());
    }
  }

  @Override
  public void setResponse(final Response presponse) {
    this.response = presponse;
  }

  @Override
  public abstract void onSuccess(final R presult);
}
