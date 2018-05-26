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

package de.knightsoftnet.mtwidgets.client.ui.widget;

import de.knightsoftnet.mtwidgets.client.ui.handler.HandlerFactory;
import de.knightsoftnet.mtwidgets.client.ui.widget.features.HasFormating;
import de.knightsoftnet.validators.shared.data.ValueWithPos;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.Event;

import org.apache.commons.lang3.StringUtils;

public abstract class AbstractFormatingTextBox extends TextBox implements HasFormating {

  /**
   * default constructor.
   */
  public AbstractFormatingTextBox() {
    super();
    this.addKeyPressHandler(HandlerFactory.getFilterReplAndFormatStrKeyPressHandler());
    this.addKeyUpHandler(HandlerFactory.getFormatStrKeyUpHandler());
    this.sinkEvents(Event.ONPASTE);
  }

  @Override
  public void onBrowserEvent(final Event pevent) {
    // Checking for paste event
    if (pevent.getTypeInt() == Event.ONPASTE) {
      Scheduler.get().scheduleDeferred(() -> this.reformatValue());
      return;
    }
    super.onBrowserEvent(pevent);
  }

  @Override
  public void reformatValue() {
    final ValueWithPos<String> unformatedEntry =
        new ValueWithPos<>(this.getValue(), this.getCursorPos());
    this.formatValue(unformatedEntry);
  }

  protected void setTextWithPos(final ValueWithPos<String> formatedEntry) {
    this.setValue(formatedEntry.getValue(), false);
    if (formatedEntry.getPos() > StringUtils.length(formatedEntry.getValue()) //
        || formatedEntry.getPos() < 0) {
      this.setCursorPos(formatedEntry.getValue().length());
    } else {
      this.setCursorPos(formatedEntry.getPos());
    }
  }
}
