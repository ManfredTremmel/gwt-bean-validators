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

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;

/**
 * abstract presenter with default functionality used in forms.
 *
 * @author Manfred Tremmel
 *
 * @param <P> proxy type
 * @param <V> view type
 * @param <F> editable data type
 */
public abstract class AbstractPresenterWithErrorHandling<P extends Proxy<?>, //
    V extends EditorWithErrorHandling<? extends AbstractPresenterWithErrorHandling<P, V, F>, F>, F>
    extends Presenter<V, P> {

  public AbstractPresenterWithErrorHandling(final EventBus peventBus, final V pview, final P pproxy,
      final GwtEvent.Type<RevealContentHandler<?>> pslot) {
    super(peventBus, pview, pproxy, pslot);
  }


  @Override
  protected void onReveal() {
    super.onReveal();
    Scheduler.get().scheduleDeferred(
        () -> AbstractPresenterWithErrorHandling.this.getView().setFocusOnFirstWidget());
  }
}
