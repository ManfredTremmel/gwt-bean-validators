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

package de.knightsoftnet.mtwidgets.client.ui.handler;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.ValueBoxBase;

/**
 * helper with method which extracts ValueBoxBase from GwtEvent.
 *
 * @param <E> field type
 * @author Manfred Tremmel
 */
public class ValueBoxFromEvent<E> {

  /**
   * get ValueBoxBase which produced the event.
   *
   * @param pevent event to get box from
   * @return ValueBoxBase
   */
  @SuppressWarnings("unchecked")
  protected ValueBoxBase<E> getTextBoxFromEvent(final GwtEvent<?> pevent) {
    final ValueBoxBase<E> ptextBox;
    if (pevent.getSource() instanceof SuggestBox) {
      ptextBox = (ValueBoxBase<E>) ((SuggestBox) pevent.getSource()).getValueBox();
    } else if (pevent.getSource() instanceof ValueBoxBase<?>) {
      ptextBox = (ValueBoxBase<E>) pevent.getSource();
    } else {
      throw new RuntimeException("Widget type not supported!");
    }
    return ptextBox;
  }
}
