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

import de.knightsoftnet.mtwidgets.client.ui.widget.helper.IdAndNameBean;

import com.google.gwt.i18n.client.LocaleInfo;

import java.util.ArrayList;
import java.util.Collection;

/**
 * list box which displays all languages available in the frontend.
 *
 * @author Manfred Tremmel
 *
 */
public class UiLanguageListBox extends IdAndNameListBox<String> {

  /**
   * default constructor.
   */
  public UiLanguageListBox() {
    super(UiLanguageListBox.createLanguageList());
    this.setValue(LocaleInfo.getCurrentLocale().getLocaleName(), false);
  }

  private static Collection<IdAndNameBean<String>> createLanguageList() {
    final Collection<IdAndNameBean<String>> languages = new ArrayList<>();
    for (final String lang : LocaleInfo.getAvailableLocaleNames()) {
      if (!"default".equals(lang)) {
        languages.add(new IdAndNameBean<>(lang, LocaleInfo.getLocaleNativeDisplayName(lang)));
      }
    }
    return languages;
  }
}
