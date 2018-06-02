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

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * data list to provide suggestions.
 *
 * @author Manfred Tremmel
 *
 */
public class DataListWidget extends Composite implements TakesValue<List<Suggestion>> {

  private final HTMLPanel panel;
  private List<Suggestion> value;

  /**
   * default constructor.
   */
  @UiConstructor
  public DataListWidget() {
    super();
    panel = new HTMLPanel("datalist", StringUtils.EMPTY);
    initWidget(panel);
  }

  @Override
  public void setValue(final List<Suggestion> pvalue) {
    value = pvalue;
    final SafeHtmlBuilder options = new SafeHtmlBuilder();
    for (final Suggestion entry : pvalue) {
      if (StringUtils.isEmpty(entry.getDisplayString())) {
        options.appendHtmlConstant("<option value=\"" + entry.getReplacementString() + "\"/>");

      } else {
        options.appendHtmlConstant("<option value=\"" + entry.getReplacementString() + "\" label=\""
            + entry.getDisplayString() + "\"/>");
      }
    }
    panel.getElement().setInnerSafeHtml(options.toSafeHtml());
  }

  @Override
  public List<Suggestion> getValue() {
    return value;
  }
}
