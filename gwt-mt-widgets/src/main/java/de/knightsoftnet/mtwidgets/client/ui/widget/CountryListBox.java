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

import de.knightsoftnet.mtwidgets.client.ui.widget.helper.CountryMessages;
import de.knightsoftnet.mtwidgets.client.ui.widget.helper.ListSortEnum;
import de.knightsoftnet.validators.shared.data.CountryEnum;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiConstructor;

import java.util.Arrays;

/**
 * list box of available countries.
 *
 * @author Manfred Tremmel
 *
 */
public class CountryListBox extends SortableIdAndNameListBox<CountryEnum> {

  /**
   * widget ui constructor.
   *
   * @param sort the sort order of the countries
   */
  @UiConstructor
  public CountryListBox(final ListSortEnum sort) {
    super(sort, GWT.create(CountryMessages.class), CountryEnum.values());
  }

  /**
   * fill entries of the listbox.
   *
   * @param pcountryEnums list of entries
   * @deprecated As of release 0.22.0, replaced by
   *             {@link #fillEntryCollections(java.util.Collection)}
   */
  @Deprecated
  public final void fillCountryEntries(final CountryEnum... pcountryEnums) {
    fillEntries(Arrays.asList(pcountryEnums));
  }
}
