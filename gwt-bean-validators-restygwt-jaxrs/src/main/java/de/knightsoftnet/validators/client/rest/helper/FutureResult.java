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

package de.knightsoftnet.validators.client.rest.helper;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * future result for asynchronous call caching. Based on RPC Future Result from
 * http://stackoverflow.com/questions/34851180/how-to-cache-server-results-in-gwt-with-guava
 *
 * @author Manfred Tremmel
 *
 * @param <T> type of the result
 */
public class FutureResult<T> implements MethodCallback<T> {
  private enum State {
    SUCCEEDED, FAILED, INCOMPLETE;
  }

  private State state = State.INCOMPLETE;
  private final Set<MethodCallback<T>> listeners = new LinkedHashSet<>();
  private T value;
  private Throwable error;

  /**
   * get result of the call.
   *
   * @return result of call
   * @throws IllegalStateException if call isn't done.
   */
  public T get() throws IllegalStateException {
    switch (this.state) {
      case INCOMPLETE:
        // Do not block browser so just throw ex
        throw new IllegalStateException("The server response did not yet recieved.");
      case FAILED:
        throw new IllegalStateException(this.error);
      case SUCCEEDED:
        return this.value;
      default:
        throw new IllegalStateException("Something very unclear");
    }
  }

  /**
   * add callback which is informed about changes.
   *
   * @param pcallback callback which is informed about changes
   */
  public void addCallback(final MethodCallback<T> pcallback) {
    if (pcallback != null) {
      this.listeners.add(pcallback);
    }
  }

  public boolean isDone() {
    return this.state == State.SUCCEEDED;
  }

  @Override
  public void onFailure(final Method pmethod, final Throwable pexception) {
    this.state = State.FAILED;
    this.error = pexception;
    this.listeners.forEach(callback -> callback.onFailure(pmethod, pexception));
  }

  @Override
  public void onSuccess(final Method pmethod, final T presponse) {
    this.value = presponse;
    this.state = State.SUCCEEDED;
    this.listeners.forEach(callback -> callback.onSuccess(pmethod, presponse));
  }
}
