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
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.gwtplatform.dispatch.rest.client.RestCallback;
import com.gwtplatform.dispatch.shared.ActionException;

import de.knightsoftnet.gwtp.spring.client.converter.ValidationResultDataConverter;
import de.knightsoftnet.gwtp.spring.client.factory.ValidationFactory;
import de.knightsoftnet.gwtp.spring.client.session.Session;
import de.knightsoftnet.gwtp.spring.shared.data.ValidationResultInterface;

/**
 * Async callback implementation with error handling.
 *
 * @author Manfred Tremmel
 *
 * @param <P> presenter type
 * @param <D> data type given to the server
 * @param <V> view or widget which implements EditorWithErrorHandling interface
 * @param <R> rest result type
 * @param <H> http response message type
 */
public abstract class AbstractRestCallback<P, D, V extends EditorWithErrorHandling<P, D>, R, //
    H extends HttpMessages> implements RestCallback<R> {

  protected final V view;
  protected final D data;
  protected final Session session;
  private final ValidationResultDataConverter<D> validationConverter;
  private final H httpMessage;
  private Response response;

  /**
   * constructor.
   *
   * @param pview view
   * @param pdata date to handle
   * @param psession session data
   */
  public AbstractRestCallback(final V pview, final D pdata, final Session psession) {
    this(pview, pdata, psession, GWT.create(HttpMessages.class));
  }

  /**
   * constructor.
   *
   * @param pview view
   * @param pdata date to handle
   * @param psession session data
   * @param phttpMessage http code messages to show
   */
  public AbstractRestCallback(final V pview, final D pdata, final Session psession,
      final H phttpMessage) {
    super();
    this.view = pview;
    this.data = pdata;
    this.session = psession;
    this.httpMessage = phttpMessage;
    this.validationConverter = new ValidationResultDataConverter<>();
  }

  @Override
  public void onFailure(final Throwable pcaught) {
    try {
      throw pcaught;
    } catch (final ActionException e) {
      switch (this.response.getStatusCode()) {
        case 400:
          final ValidationFactory validationFactory = GWT.create(ValidationFactory.class);
          final AutoBean<ValidationResultInterface> validationResultWraper = AutoBeanCodex
              .decode(validationFactory, ValidationResultInterface.class, this.response.getText());
          this.view.setConstraintViolations(
              this.validationConverter.convert(validationResultWraper.as(), this.data));
          break;
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
