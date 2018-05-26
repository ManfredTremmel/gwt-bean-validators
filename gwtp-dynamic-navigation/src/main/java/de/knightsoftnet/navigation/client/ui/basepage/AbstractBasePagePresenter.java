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

package de.knightsoftnet.navigation.client.ui.basepage;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.proxy.Proxy;

/**
 * abstract presenter of the base page, which embeds the other components.
 *
 * @author Manfred Tremmel
 *
 *
 * @param <V> The {@link View} type.
 * @param <P> The {@link Proxy} type.
 */
public abstract class AbstractBasePagePresenter<V extends View, P extends Proxy<?>>
    extends Presenter<V, P> {

  /**
   * Use this in leaf presenters, inside their {@link #revealInParent} method.
   */
  public static final NestedSlot SLOT_MAIN_CONTENT = new NestedSlot();

  /**
   * constructor getting parameters injected.
   *
   * @param peventBus event bus
   * @param pview view of this page
   * @param pproxy proxy to handle page
   */
  public AbstractBasePagePresenter(final EventBus peventBus, final V pview, final P pproxy) {
    super(peventBus, pview, pproxy, RevealType.Root);
  }

  @Override
  protected abstract void onBind();
}
