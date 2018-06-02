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

package de.knightsoftnet.navigation.client.ui.navigation;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import javax.inject.Inject;

/**
 * View of the validator Navigation.
 *
 * @author Manfred Tremmel
 *
 */
public class TreeNavigationView extends ViewImpl implements NavigationPresenter.MyView {

  /**
   * view interface.
   */
  interface Binder extends UiBinder<Widget, TreeNavigationView> {
  }

  /**
   * A ClientBundle that provides images and style sheets for the decorator.
   */
  public interface Resources extends ClientBundle {

    /**
     * The styles used in this widget.
     *
     * @return decorator style
     */
    @Source("TreeNavigationView.css")
    TreeNavigationViewStyle navigationStyle();
  }

  /**
   * navigation tree.
   */
  @UiField
  Tree navTree;

  /**
   * navigation tree main entry.
   */
  @UiField
  HTMLPanel navigationMainPoint;

  /**
   * navigation tree test entry.
   */
  @UiField
  TreeItem firstItem;

  /**
   * the selected tree item.
   */
  private TreeItem selectedItem;

  /**
   * resource data.
   */
  private final Resources resources;

  /**
   * map between menu entries and navigation.
   */
  private final Map<TreeItem, NavigationEntryInterface> navigationMap;

  /**
   * constructor with injected parameters.
   *
   * @param puiBinder ui binder
   * @param presources style sheet resource
   */
  @Inject
  public TreeNavigationView(final Binder puiBinder, final Resources presources) {
    super();
    resources = presources;
    resources.navigationStyle().ensureInjected();
    navigationMap = new HashMap<>();
    initWidget(puiBinder.createAndBindUi(this));
  }

  @Override
  public void setPresenter(final NavigationPresenter ppresenter) {
    // we don't need presenter here
  }

  @Override
  public final void createNavigation(final NavigationStructure pnavigation) {
    navigationMap.clear();
    firstItem.removeItems();
    selectedItem = null;
    createRecursiveNavigation(firstItem, pnavigation.getFullNavigationList(),
        pnavigation.getActiveNavigationEntryInterface());
    firstItem.setState(true, false);
    if (selectedItem != null) {
      selectedItem.setSelected(true);
      for (TreeItem openItem = selectedItem.getParentItem(); openItem != null; openItem =
          openItem.getParentItem()) {
        openItem.setState(true, false);
      }
    }
  }

  /**
   * create navigation in a recursive way.
   *
   * @param pitem the item to add new items
   * @param plist the list of the navigation entries
   * @param pactiveEntry the active entry
   */
  public void createRecursiveNavigation(final TreeItem pitem,
      final List<NavigationEntryInterface> plist, final NavigationEntryInterface pactiveEntry) {
    for (final NavigationEntryInterface navEntry : plist) {
      final TreeItem newItem;
      if (navEntry instanceof NavigationEntryFolder) {
        newItem = new TreeItem(navEntry.getMenuValue());
        createRecursiveNavigation(newItem, ((NavigationEntryFolder) navEntry).getSubEntries(),
            pactiveEntry);
        newItem.setState(navEntry.isOpenOnStartup());
      } else if (navEntry instanceof NavigationLink) {
        final Anchor link = ((NavigationLink) navEntry).getAnchor();
        link.setStylePrimaryName(resources.navigationStyle().link());
        newItem = new TreeItem(link);
      } else if (navEntry.getToken() == null) {
        newItem = null;
      } else {
        final InlineHyperlink entryPoint = GWT.create(InlineHyperlink.class);
        entryPoint.setHTML(navEntry.getMenuValue());
        entryPoint.setTargetHistoryToken(navEntry.getFullToken());
        entryPoint.setStylePrimaryName(resources.navigationStyle().link());
        newItem = new TreeItem(entryPoint);
        navigationMap.put(newItem, navEntry);
      }
      if (newItem != null) {
        pitem.addItem(newItem);
        if (pactiveEntry != null && pactiveEntry.equals(navEntry)) {
          selectedItem = newItem;
          selectedItem.setSelected(true);
        }
      }
    }
  }

  /**
   * menu item is selected in the menu tree.
   *
   * @param pselectionEvent selection event.
   */
  @UiHandler("navTree")
  final void menuItemSelected(final SelectionEvent<TreeItem> pselectionEvent) {
    if (selectedItem != null && !selectedItem.equals(pselectionEvent.getSelectedItem())) {
      // workaround, revert selection, it's triggered by the selected page
      pselectionEvent.getSelectedItem().setSelected(false);
      selectedItem.setSelected(true);
    }
  }


  @Override
  public void setSelectedItem(final NavigationEntryInterface pnewItem) {
    for (final Entry<TreeItem, NavigationEntryInterface> entry : navigationMap.entrySet()) {
      if (Objects.equals(pnewItem, entry.getValue())) {
        selectedItem = entry.getKey();
        selectedItem.setSelected(true);
      } else {
        entry.getKey().setSelected(false);
      }
    }
  }
}
