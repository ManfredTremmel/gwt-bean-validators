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
import de.knightsoftnet.mtwidgets.client.ui.widget.helper.IdAndNameIdComperator;
import de.knightsoftnet.mtwidgets.client.ui.widget.helper.IdAndNameNameComperator;
import de.knightsoftnet.mtwidgets.client.ui.widget.helper.ListSortEnum;
import de.knightsoftnet.mtwidgets.client.ui.widget.helper.MessagesForValues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * a list box with id and name which is sortable and returns id.
 *
 * @author Manfred Tremmel
 *
 * @param <T> type of the id
 */
public class SortableIdAndNameListBox<T extends Comparable<T>> extends IdAndNameListBox<T> {
  private final ListSortEnum sortOrder;
  private final MessagesForValues<T> messages;

  /**
   * widget ui constructor.
   *
   * @param psort the sort order of the countries
   * @param pmessages message resolver
   * @param pids ids to add to listBox
   */
  @SafeVarargs
  public SortableIdAndNameListBox(final ListSortEnum psort, final MessagesForValues<T> pmessages,
      final T... pids) {
    this(psort, pmessages, Arrays.asList(pids));
  }

  /**
   * widget ui constructor.
   *
   * @param psort the sort order of the countries
   * @param pmessages message resolver
   * @param pids ids to add to listBox
   */
  public SortableIdAndNameListBox(final ListSortEnum psort, final MessagesForValues<T> pmessages,
      final Collection<T> pids) {
    super();
    setVisibleItemCount(1);
    this.sortOrder = psort;
    this.messages = pmessages;

    this.fillEntries(pids);
  }

  /**
   * fill entries of the listbox.
   *
   * @param pids list of entries
   */
  public final void fillEntries(final Collection<T> pids) {
    final List<IdAndNameBean<T>> entries = new ArrayList<>();
    for (final T proEnum : pids) {
      entries.add(new IdAndNameBean<>(proEnum, this.messages.name(proEnum)));
    }
    if (this.sortOrder != null) {
      switch (this.sortOrder) {
        case ID_ASC:
          Collections.sort(entries, new IdAndNameIdComperator<T>());
          break;
        case ID_DSC:
          Collections.sort(entries, Collections.reverseOrder(new IdAndNameIdComperator<T>()));
          break;
        case NAME_ASC:
          Collections.sort(entries, new IdAndNameNameComperator<T>());
          break;
        case NAME_DSC:
          Collections.sort(entries, Collections.reverseOrder(new IdAndNameNameComperator<T>()));
          break;
        default:
          break;
      }
    }
    fillEntryCollections(entries);
  }
}
